package com.opentext.lhnqa.api.lib.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.domain.CustodiansRequestPojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class CustodianCreateHelper extends ApiTestcaseBase {

	static final ExtLogger LOGGER = new ExtLogger(CustodianCreateHelper.class.toString());


	public static List<Long> createCustodiansGetId(int noOfCustodians, String tenant) throws JsonProcessingException {

		List<Long> custodians = new ArrayList<Long>();
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenant);
		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();

		for (int count = 1; count <= noOfCustodians; count++) {
			Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
			Assert.assertNotNull(response, "Response of creating custodian is NULL for "+ MAPPER.writeValueAsString(custodianRequest));
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
					" Error in custodian create response code " + response.asString());
			custodians.add(response.jsonPath().getLong("id"));
		}

		return custodians;
	}

	public static List<CustodianResponsePojo> createCustodiansGetResponse(int noOfCustodians, String tenant) throws JsonProcessingException {

		List<CustodianResponsePojo> custodians = new ArrayList<CustodianResponsePojo>();
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenant);
		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();

		for (int count = 1; count <= noOfCustodians; count++) {
			Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
			Assert.assertNotNull(response, "Response of creating custodian is NULL for "+ MAPPER.writeValueAsString(custodianRequest));
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
					" Error in custodian create response code " + response.asString());
			CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
			custodians.add(requestResponse);
		}

		return custodians;
	}


	public static long createCustodianGetId(CustodiansRequestPojo custodianRequest, String tenant) throws JsonProcessingException {

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenant);

		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of creating custodian is NULL for "+ MAPPER.writeValueAsString(custodianRequest));
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());

		return response.jsonPath().getLong("id");
	}

	public static CustodianResponsePojo createCustodianGetResponse(CustodiansRequestPojo custodianRequest,
			String tenant) throws JsonProcessingException {

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenant);

		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response,
				"Response of creating custodian is NULL for " + MAPPER.writeValueAsString(custodianRequest));
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());

		return MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
	}

}
