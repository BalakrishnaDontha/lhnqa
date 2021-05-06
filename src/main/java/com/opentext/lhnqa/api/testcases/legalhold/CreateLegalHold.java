package com.opentext.lhnqa.api.testcases.legalhold;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.api.lib.utils.ApiUtils.LegalHoldControlNames;
import com.opentext.lhnqa.api.testcases.custodians.ListCustodians;
import com.opentext.lhnqa.lib.domain.LHLinksPojo;
import com.opentext.lhnqa.lib.domain.LegalHoldRequestPojo;
import com.opentext.lhnqa.lib.domain.LegalHoldResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class CreateLegalHold  extends ApiTestcaseBase {

	static final ExtLogger LOGGER = new ExtLogger(CreateLegalHold.class.toString());


	@Test(dataProvider = "ApiDataFromYml",description = "Draft legal hold", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_LegalHold)
	public void draftLegalHold(Map<String, String> testdata) throws IOException {

		LOGGER.testCaseLog("Executing draftLegalHold ");
		String endPoint = ENDPOINT_PATH.replace(PLACEHOLDER1,  testdata.get(DATA_TENANTID)) + MATTERS_PATH + "/" + testdata.get(DATA_MATTERID)+ LEGALHOLD_PATH;

		LegalHoldRequestPojo request = new LegalHoldRequestPojo();
		request.getCustodians().addAll(ListCustodians.getAnyCustodians(1,  testdata.get(DATA_TENANTID)));
		request.attachDocumentsToLHN(apiUtil.attachDocumentsToLegalHold(testdata.get(DATA_DOC_NAME),testdata.get(DATA_DOC_MIMETYPE),LegalHoldControlNames.LHNOTICECONTROLNAME.getLabel()));
		LOGGER.stepLog("Posting the LH request");
		Response response = restUtil.postLHNRequest(endPoint, request);
		Assert.assertNotNull(response, "Error in posting legal hold");
		Assert.assertEquals(response.statusCode(), 200, " Error in posting legal hold response code " + response.asString());

		LOGGER.stepLog("Reading the POST LH response");
		LegalHoldResponsePojo requestResponse = MAPPER.readValue(response.asString(), LegalHoldResponsePojo.class);
		verifyLHLinks(requestResponse);
		verifyLHLinksFormat(testdata,requestResponse);

		Response getResponse = restUtil.getJson(ENDPOINT_PATH.replace(PLACEHOLDER1,  testdata.get(DATA_TENANTID)) + LEGALHOLD_PATH + "/" + requestResponse.getId());
		Assert.assertNotNull(getResponse, "Error in listing custodians");
		Assert.assertEquals(getResponse.statusCode(), HttpStatus.SC_OK, " Error in getting legal hold response code ");

		LOGGER.stepLog("Reading the GET LH response");
		LegalHoldResponsePojo getLHResponse = MAPPER.readValue(getResponse.asString(), LegalHoldResponsePojo.class);

		LOGGER.stepLog("Validate the legal hold response");
		Assert.assertTrue(requestResponse.equals(getLHResponse), "legal hold does not match");
	}


	@Test(dataProvider = "ApiDataFromYml",description = "get legal hold with valid id", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_LegalHold)
	public void getLegalHold(Map<String, String> testdata) throws JsonMappingException, JsonProcessingException  {

		LOGGER.testCaseLog("Executing getLegalHold ");

		Response response = restUtil
				.getJson(ENDPOINT_PATH.replace(PLACEHOLDER1,  testdata.get(DATA_TENANTID)) + MATTERS_PATH + "/" + testdata.get(DATA_MATTERID)+ LEGALHOLD_PATH + "/" + testdata.get(DATA_LEGALHOLDID));
		Assert.assertNotNull(response, "Error in listing custodians");
		Assert.assertEquals(response.statusCode(), 200, " Error in getting legal hold response code ");
		LOGGER.stepLog("Reading the legal hold response");
		LegalHoldResponsePojo requestResponse = MAPPER.readValue(response.asString(), LegalHoldResponsePojo.class);

		LOGGER.stepLog("Validate the legal hold response");
		Assert.assertEquals(testdata.get(DATA_LEGALHOLDID), requestResponse.getId(), "legal hold id does not match");
	}


	@Test(dataProvider = "ApiDataFromYml",description = "Verify invalid legal hold attachment", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_LegalHold)
	public void verifyLHInvalidFile(Map<String, String> testdata) throws IOException  {

		LOGGER.testCaseLog("Executing verifyLHInvalidFile ");

		String endPoint = ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID)) + MATTERS_PATH + "/"
				+ testdata.get(DATA_MATTERID) + LEGALHOLD_PATH;
		List<Boolean> lhDraftStatus = Arrays.asList(true, false);

		for (boolean status : lhDraftStatus) {
			LegalHoldRequestPojo request = new LegalHoldRequestPojo();
			request.setDraft(status);
			request.getCustodians().addAll(ListCustodians.getAnyCustodians(1,  testdata.get(DATA_TENANTID)));
			request.attachDocumentsToLHN(apiUtil.attachDocumentsToLegalHold(testdata.get(DATA_DOC_NAME),
					testdata.get(DATA_DOC_MIMETYPE), testdata.get(LHDOCCONTROLNAME)));
			LOGGER.stepLog("Posting the LH request with draft status " + status);
			Response response = restUtil.postLHNRequest(endPoint, request);

			Assert.assertNotNull(response, "Error in posting legal hold ");
			Assert.assertEquals(response.statusCode(), Integer.parseInt(testdata.get(DATA_RESPONSE_CODE)),
					" Error in posting legal hold response code " + response.asString());
			String[] errorMessages = testdata.get(DATA_ERROR_MESSAGE).split("&");
			for (int i = 0; i < errorMessages.length; i++) {
				Assert.assertEquals(response.jsonPath().getString("errors[" + i + "]"), errorMessages[i],
						" Error message does not match "+ response.jsonPath().getString("errors"));
			}
		}
	}


	@Test(dataProvider = "ApiDataFromYml",description = "Verify invalid legal hold attachment", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_LegalHold)
	public void sendLegalHoldWithNoCustodians(Map<String, String> testdata){

		LOGGER.testCaseLog("Executing sendLegalHoldWithNoCustodians ");
		String endPoint = ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID)) + MATTERS_PATH + "/"
				+ testdata.get(DATA_MATTERID) + LEGALHOLD_PATH;

		LegalHoldRequestPojo request = new LegalHoldRequestPojo();
		request.setDraft(false);
	    request.setCustodians(Arrays.asList());

	    LOGGER.stepLog("Posting the LH request");
		Response response = restUtil.postLHNRequest(endPoint, request);

		Assert.assertNotNull(response, "Error in posting legal hold ");
		Assert.assertEquals(response.statusCode(), Integer.parseInt(testdata.get(DATA_RESPONSE_CODE)),
				" Error in posting legal hold response code " + response.asString());
		Assert.assertEquals(response.jsonPath().getString("errors[0]"), testdata.get(DATA_ERROR_MESSAGE),
				" Error message does not match");
	}

	@Test(dataProvider = "ApiDataFromYml",description = "Verify invalid legal hold attachment", groups = { REGRESSION_GROUP,
			SMOKE_GROUP }, priority = priority_LegalHold)
	public void legalHoldWithInvalidCustodians(Map<String, String> testdata) throws JsonMappingException, JsonProcessingException{

		LOGGER.testCaseLog("Executing legalHoldWithInvalidCustodians ");
		String endPoint = ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID)) + MATTERS_PATH + "/"
				+ testdata.get(DATA_MATTERID) + LEGALHOLD_PATH;
		List<Boolean> lhDraftStatus = Arrays.asList(true, false);

		for (boolean status : lhDraftStatus) {
			LegalHoldRequestPojo request = new LegalHoldRequestPojo();
			request.setDraft(status);

			if (testdata.get(DATA_CUSTODIANID).isEmpty()) {
				request.getCustodians().addAll(ListCustodians.getAnyCustodians(1,  testdata.get(DATA_TENANTID)));
				request.getCustodians().add(0L);
			} else
				request.setCustodians(Arrays.asList(testdata.get(DATA_CUSTODIANID).split(",")).stream()
						.map(value -> Long.valueOf(value)).collect(Collectors.toList()));

			LOGGER.stepLog("Posting the LH Draft("+ status + ")request with Custodians " + request.getCustodians());
			Response response = restUtil.postLHNRequest(endPoint, request);

			Assert.assertNotNull(response, "Error in posting legal hold ");
			Assert.assertEquals(response.statusCode(), Integer.parseInt(testdata.get(DATA_RESPONSE_CODE)),
					" Error in posting legal hold response code " + response.asString());
			Assert.assertEquals(response.jsonPath().getString("errors"), testdata.get(DATA_ERROR_MESSAGE),
					" Error message does not match");
		}
	}


	public void verifyLHLinksFormat(Map<String, String> testdata,LegalHoldResponsePojo response) {

		String lhPath = restUtil.getBaseEndpoint() + ENDPOINT_PATH.replace(PLACEHOLDER1,  testdata.get(DATA_TENANTID))
		+ LEGALHOLD_PATH + "/" + response.getId();

		String tenantPath =  restUtil.getBaseEndpoint() + ENDPOINT_PATH.replace(PLACEHOLDER1,  testdata.get(DATA_TENANTID));
		LHLinksPojo links = response.get_links();

		Assert.assertEquals(lhPath, links.getSelf().getHref(), "legal hold self link format does not match");
		//Assert.assertEquals(tenantPath + "/user" + userId, links.getUser().getHref(), "legal hold user link format does not match");
		Assert.assertEquals(lhPath + "/custodians", links.getCustodians().getHref(), "legal hold user custodians link format does not match");
		Assert.assertEquals(lhPath + "/hold_notice", links.getHold_notice().getHref(), "legal hold user hold_notice link format does not match");
		Assert.assertEquals(lhPath + "/acknowledgement_reminder_notice", links.getAcknowledgement_reminder_notice().getHref(), "legal hold user acknowledgement_reminder_notice link format does not match");
		Assert.assertEquals(lhPath + "/hold_reminder_notice", links.getHold_reminder_notice_hold().getHref(), "legal hold user hold_reminder_notice_hold link format does not match");
		Assert.assertEquals(lhPath + "/escalation_notice", links.getEscalation_notice().getHref(), "legal hold user escalation_notice link format does not match");
		Assert.assertEquals(lhPath + "/release_notice", links.getRelease_notice().getHref(), "legal hold user release_notice link format does not match");
		//Assert.assertEquals(lhPath + "/history", links.getHistory().getHref(), "legal hold user history link format does not match");
		Assert.assertEquals(lhPath + "/stats", links.getStats().getHref(), "legal hold user stats link format does not match");
		Assert.assertEquals(tenantPath + MATTERS_PATH+"/" + testdata.get(DATA_MATTERID) , links.getMatter().getHref(), "legal hold user matter link format does not match");
	}

	public void verifyLHLinks(LegalHoldResponsePojo response) {

		List<String> allLinks = response.get_links().obtainAllLinks();

		for (int i = 0; i < allLinks.size(); i++) {
			if(allLinks.get(i)!=null)
			Assert.assertEquals(restUtil.getJson(allLinks.get(i)).statusCode(),
					HttpStatus.SC_OK, " Invalid response code for link " + allLinks.get(i)  );
		}
	}


}
