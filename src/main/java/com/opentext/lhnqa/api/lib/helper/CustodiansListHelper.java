package com.opentext.lhnqa.api.lib.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.domain.CustodiansListResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class CustodiansListHelper extends ApiTestcaseBase  {

	static final ExtLogger LOGGER = new ExtLogger(CustodiansListHelper.class.toString());


	public static CustodianResponsePojo getCustodian(long custodianId, String tenantId)
			throws JsonMappingException, JsonProcessingException {

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId);
		Response getCustodian = restUtil.getJson(custodianEndPoint + "/" + custodianId);
		Assert.assertNotNull(getCustodian, "Response of listing custodian is NULL");
		Assert.assertEquals(getCustodian.statusCode(), HttpStatus.SC_OK, " Error in listing custodian response code ");
		CustodianResponsePojo readResponse = MAPPER.readValue(getCustodian.asString(), CustodianResponsePojo.class);

		return readResponse;
	}


	public static List<Long> getGlobalCustodians(int noOfCustodians,String tenantId)
			throws JsonMappingException, JsonProcessingException {

		//This will work for 'noOfCustodians' count up to 100
		List<Long> custodianIds = new ArrayList<Long>();

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId) + "?page_size="+ noOfCustodians;
		Response getCustodians = restUtil.getJson(custodianEndPoint);
		Assert.assertNotNull(getCustodians, "Response of listing custodian is NULL");
		Assert.assertEquals(getCustodians.statusCode(), HttpStatus.SC_OK, " Error in listing custodian response code ");

		CustodiansListResponsePojo requestResponse = MAPPER.readValue(getCustodians.asString(),CustodiansListResponsePojo.class);
		int totalCustodians = requestResponse.getPage().getTotal_count();
		int totalPages = (int) Math.floor(totalCustodians/noOfCustodians);
		int pageNumber = new Random().nextInt(totalPages) + 1;

		getCustodians = restUtil.getJson(custodianEndPoint+"&page_number="+pageNumber);
		Assert.assertNotNull(getCustodians, "Response of listing custodian is NULL");
		Assert.assertEquals(getCustodians.statusCode(), HttpStatus.SC_OK, " Error in listing custodian response code ");
		requestResponse = MAPPER.readValue(getCustodians.asString(),CustodiansListResponsePojo.class);

		custodianIds.addAll(requestResponse.get_embedded().getCustodians().stream().map(custodian -> custodian.getId()).collect(Collectors.toList()));

		if (custodianIds.size() < noOfCustodians) {
			custodianIds.addAll(CustodianCreateHelper.createCustodiansGetId(noOfCustodians - custodianIds.size(), tenantId));
		}
		return custodianIds;
	}


	public static List<Long> getAllGlobalCustodians(String tenantId)
			throws JsonMappingException, JsonProcessingException {

		List<Long> custodianIds = new ArrayList<Long>();
		String nextUrl = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId) + "?page_size=100";
		boolean hasMore = false;

		do {
			Response getCustodians = restUtil.getJson(nextUrl);
			Assert.assertNotNull(getCustodians, "Response of listing custodian is NULL");
			Assert.assertEquals(getCustodians.statusCode(), HttpStatus.SC_OK, " Error in listing custodian response code ");

			CustodiansListResponsePojo requestResponse = MAPPER.readValue(getCustodians.asString(),CustodiansListResponsePojo.class);
			custodianIds.addAll(requestResponse.get_embedded().getCustodians().stream().map(custodian -> custodian.getId()).collect(Collectors.toList()));
			hasMore = requestResponse.getPage().isHas_more();
			nextUrl = hasMore ?requestResponse.get_links().getNext().getHref():null;

		}while(hasMore);

		return custodianIds;
	}

	public static List<CustodianResponsePojo> getAllCustodiansFromListResponse(
			CustodiansListResponsePojo custodiansListResponse) throws JsonMappingException, JsonProcessingException {

		List<CustodianResponsePojo> custodianList = new ArrayList<CustodianResponsePojo>();
		custodianList.addAll(custodiansListResponse.get_embedded().getCustodians());
		boolean hasMore = custodiansListResponse.getPage().isHas_more();
		String nextPageUrl = hasMore ? custodiansListResponse.get_links().getNext().getHref() : null;

		while (hasMore) {
			Response response = restUtil.getJson(nextPageUrl);
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
					" Error in custodian list response code " + response.asString());
			CustodiansListResponsePojo requestResponse = MAPPER.readValue(response.asString(),
					CustodiansListResponsePojo.class);
			Assert.assertEquals(nextPageUrl, requestResponse.get_links().getSelf().getHref(),
					"Self link does not match");
			custodianList.addAll(requestResponse.get_embedded().getCustodians());
			hasMore = requestResponse.getPage().isHas_more();
			nextPageUrl = hasMore ? requestResponse.get_links().getNext().getHref() : null;
		}

		return custodianList;
	}
}
