package com.opentext.lhnqa.api.testcases.custodians;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.helper.CustodianCreateHelper;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.domain.CustodiansListResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class ListCustodians extends ApiTestcaseBase  {

	static final ExtLogger LOGGER = new ExtLogger(ListCustodians.class.toString());

	@Test(dataProvider = "ApiDataFromYml", description = "Get all custodians - https://ottr.opentext.com/test_case_node/show/2727405", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_CustodiansList)
	public void listAllCustodiansVariousPageSize(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing listAllCustodiansVariousPageSize ");
		List<CustodianResponsePojo> newCustodians = CustodianCreateHelper.createCustodiansGetResponse(2, testdata.get(DATA_TENANTID));

		int pageSize = Integer.parseInt(testdata.get(DATA_PAGE_SIZE));
		String nextPageUrl = restUtil.getBaseEndpoint()+CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID))+"?page_size="+pageSize;
		List<CustodianResponsePojo> custodianList = new ArrayList<CustodianResponsePojo>();
		int totalCustodians = 0;
		boolean hasMore=false;
		int actualPageCount = 0;

		do {
			LOGGER.stepLog("Getting the Custodian List page: " + (actualPageCount+1));
			Response response = restUtil.getJson(nextPageUrl);
			Assert.assertNotNull(response, "Response of listing custodian is NULL");
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
					" Error in custodian list response code " + response.asString());

			CustodiansListResponsePojo requestResponse = MAPPER.readValue(response.asString(),CustodiansListResponsePojo.class);
			hasMore = requestResponse.getPage().isHas_more();
			totalCustodians = requestResponse.getPage().getTotal_count();
			custodianList.addAll(requestResponse.get_embedded().getCustodians());
			Assert.assertEquals(nextPageUrl,requestResponse.get_links().getSelf().getHref(),"Self link does not match");
			if (hasMore) {
				Assert.assertNotNull(requestResponse.get_links().getNext(), "No next URL found");
				nextPageUrl = requestResponse.get_links().getNext().getHref();
				Assert.assertTrue(requestResponse.get_embedded().getCustodians().size() == pageSize,
						" Error in custodian listing page size ");
			} else {
				Assert.assertTrue(requestResponse.get_embedded().getCustodians().size() <= pageSize,
						" Error in custodian listing page size ");
			}
			actualPageCount++;
		}while(hasMore);

		int totalPages = (int) Math.ceil((double)totalCustodians/pageSize);
		Assert.assertEquals(custodianList.size(),totalCustodians,"Total Custodian counts does not match");
		Assert.assertEquals(actualPageCount,totalPages,"Total Page counts does not match");
		Assert.assertTrue(custodianList.containsAll(newCustodians),"Newly created custodians does not exist in the list");

	}


	@Test(dataProvider = "ApiDataFromYml", description = "Get all custodians - https://ottr.opentext.com/test_case_node/show/2727405", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_CustodiansList)
	public void listAllCustodiansInvalidPageSize(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing listAllCustodiansInvalidPageSize ");

		String pageSizeString = testdata.get(DATA_PAGE_SIZE) == null ? "20" : testdata.get(DATA_PAGE_SIZE);
		int pageSize = NumberUtils.isCreatable(pageSizeString)? Integer.parseInt(pageSizeString) : 1;
		pageSize = pageSize <= 0 ? 1 : pageSize;
		pageSize = pageSize > 100 ? 100 : pageSize;
		String nextPageUrl = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID)) + "?page_size="
				+ pageSize;

		LOGGER.stepLog("Getting the Custodian List ");
		Response response = restUtil.getJson(nextPageUrl);
		Assert.assertNotNull(response, "Response of listing custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian list response code " + response.asString());

		CustodiansListResponsePojo requestResponse = MAPPER.readValue(response.asString(),
				CustodiansListResponsePojo.class);
		boolean hasMore = requestResponse.getPage().isHas_more();
		if (hasMore) {
			Assert.assertTrue(requestResponse.get_embedded().getCustodians().size() == pageSize,
					" Error in custodian listing page size ");
		} else {
			Assert.assertTrue(requestResponse.get_embedded().getCustodians().size() <= pageSize,
					" Error in custodian listing page size ");
		}

	}



}
