package com.opentext.lhnqa.api.testcases.custodians;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class ListCustodians extends ApiTestcaseBase {

	ExtLogger LOGGER = new ExtLogger(ListCustodians.class.toString());

	@Test(dataProvider = "ApiDataFromYml",description = "get custodians with valid id", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_Custodians)
	public void getValidCustodians(Map<String, String> testdata) throws JsonMappingException, JsonProcessingException {

		logger.testCaseLog("Executing getValidCustodians ");

		String custodianId = testdata.get("custodianId");

		Response response = restUtil
				.getJson(endpoint_path.replace(placeholder1, "cristnolan") + custodians_path + "/" + custodianId);
		Assert.assertNotNull(response, "Error in listing custodians");
		Assert.assertEquals(response.statusCode(), 200, " Error in listing custodians response code ");
		logger.stepLog("Reading the custodian response");
		CustodianResponsePojo requestResponse = mapper.readValue(response.asString(), CustodianResponsePojo.class);

		logger.stepLog("Validate the custodian response");
		Assert.assertEquals(custodianId, requestResponse.getData().getId(), "Custodian id does not match");
	}

}
