package com.opentext.lhnqa.api.testcases.custodians;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.helper.CustodiansListHelper;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.domain.CustodianStatsPojo;
import com.opentext.lhnqa.lib.domain.CustodiansLinksPojo;
import com.opentext.lhnqa.lib.domain.CustodiansRequestPojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class CreateCustodians extends ApiTestcaseBase {

	static final ExtLogger LOGGER = new ExtLogger(CreateCustodians.class.toString());

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.708] [LHN.709] Verify date fields of newly created custodian- https://ottr.opentext.com/test_case_node/show/3016497,"
			+ "https://ottr.opentext.com/test_case_node/show/3016499", groups = { REGRESSION_GROUP,
					SMOKE_GROUP }, priority = priority_CustodiansCreate)
	public void createValidCustodians(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createValidCustodians ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));
		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = response.as(CustodianResponsePojo.class);

		Assert.assertTrue((new Date().getTime() - requestResponse.fetchCreatedAtInDateFormat().getTime()) < 250000,
				"Custodian creation timestamp does not match");
		Assert.assertTrue((new Date().getTime() - requestResponse.fetchUpdatedAtInDateFormat().getTime()) < 250000,
				"Custodian Update timestamp does not match");
		Assert.assertEquals(custodianRequest.getFirst_name() + " "+ custodianRequest.getLast_name(),requestResponse.getName(),
				"Custodian name does not match");
		Assert.assertTrue(requestResponse.getId() > 0,"Custodian name does not match");
		verifyNewCustodianSubLinks(testdata.get(DATA_TENANTID), requestResponse);

		LOGGER.stepLog("Reading the GET custodian response");
		long custodianId = requestResponse.getId();
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.707] Create a custodian with all details - https://ottr.opentext.com/test_case_node/show/3016495", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianVariousNames(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianVariousNames ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setFirst_name(testdata.get(DATA_CUSTODIAN_FNAME));
		custodianRequest.setLast_name(testdata.get(DATA_CUSTODIAN_LNAME) + rand.nextInt());

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = response.as(CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.710] Create a custodian without supervisor details - https://ottr.opentext.com/test_case_node/show/3016500", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianNoSupervisor(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianNoSupervisor ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setSupervisor_name(testdata.get("supervisorName"));
		custodianRequest.setSupervisor_email(testdata.get("supervisorEmail"));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();
		verifyNewCustodianSubLinks(testdata.get(DATA_TENANTID), requestResponse);

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.711] Create a custodian without delegate details - https://ottr.opentext.com/test_case_node/show/3016501", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianNoDelegator(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianNoDelegator ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setDelegate_name(testdata.get("delegatorName"));
		custodianRequest.setDelegate_email(testdata.get("delegatorEmail"));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();
		verifyNewCustodianSubLinks(testdata.get(DATA_TENANTID), requestResponse);

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.712] Create a custodian with only mandatory fields - https://ottr.opentext.com/test_case_node/show/3016503", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianNoSupervisorAndDelegator(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianNoSupervisorAndDelegator ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo(true);
		custodianRequest.setFirst_name("QAAUTOCustodian");
		custodianRequest.setLast_name(""+rand.nextInt());
		custodianRequest.setEmail("QAAutomation@otxyz.com");

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.713] Create a custodian without mandatory fields - https://ottr.opentext.com/test_case_node/show/3016537", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianWithoutMandatoryAtrributes(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createCustodianWithoutMandatoryAtrributes ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));
		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setFirst_name(testdata.get(DATA_CUSTODIAN_FNAME));
		custodianRequest.setLast_name(testdata.get(DATA_CUSTODIAN_LNAME));
		custodianRequest.setEmail(testdata.get(DATA_CUSTODIAN_EMAIL));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
				"Error in custodian create response code " + response.asString());
		Assert.assertEquals(response.jsonPath().getList("errors"),
				Arrays.asList(testdata.get(DATA_ERROR_MESSAGE).split(",")), "Error message does not match");
	}

	//Bug - LHN-794
	@Test(dataProvider = "ValidEmailId", description = "[LHN.715] Create custodian with valid email - https://ottr.opentext.com/test_case_node/show/3017401", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiyCustodiansValidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiyCustodiansValidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setEmail(testdata.get(DATA_EMAIL_ID));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in creating custodian with email id " + testdata.get(DATA_EMAIL_ID) + " And error is : " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));

	}

	//Bug - LHN-794
	@Test(dataProvider = "ValidEmailId", description = "[LHN.715] Create custodian with valid email - https://ottr.opentext.com/test_case_node/show/3017401", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiySupervisorValidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiySupervisorValidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setSupervisor_email(testdata.get(DATA_EMAIL_ID));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, " Error in creating custodian with Supervisor email id "
				+ testdata.get(DATA_EMAIL_ID) + " And error is : " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));

	}

	//Bug - LHN-794
	@Test(dataProvider = "ValidEmailId", description = "[LHN.715] Create custodian with valid email - https://ottr.opentext.com/test_case_node/show/3017401", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiyDelegatorValidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiyDelegatorValidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setDelegate_email(testdata.get(DATA_EMAIL_ID));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, " Error in creating custodian with Delegator email id "
				+ testdata.get(DATA_EMAIL_ID) + " And error is : " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = MAPPER.readValue(response.asString(), CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	//Bug - LHN-794
	@Test(dataProvider = "InvalidEmailId", description = "[LHN.716] Create a custodian with invalid email - https://ottr.opentext.com/test_case_node/show/3017412", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiyCustodiansInvalidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiyCustodiansInvalidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setEmail(testdata.get(DATA_EMAIL_ID));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
				" No Error in creating custodian for email id " + testdata.get(DATA_EMAIL_ID));
		Assert.assertEquals(response.jsonPath().getList("errors").get(0), ERROR_EMAIL_CUSTODIAN,
				"Error message does not match for " + testdata.get(DATA_EMAIL_ID));

	}

	//Bug - LHN-794
	@Test(dataProvider = "InvalidEmailId", description = "[LHN.716] Create a custodian with invalid email - https://ottr.opentext.com/test_case_node/show/3017412", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiySupervisorInvalidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiySupervisorInvalidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

			CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
			custodianRequest.setSupervisor_email(testdata.get(DATA_EMAIL_ID));

			LOGGER.stepLog("Posting the Custodian create request");
			Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
			Assert.assertNotNull(response, "Response of posting custodian is NULL");
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
					" No Error in creating custodian for supervisor email id " + testdata.get(DATA_EMAIL_ID));
			Assert.assertEquals(response.jsonPath().getList("errors").get(0), ERROR_EMAIL_SUPERVISOR,
					"Error message does not match for " + testdata.get(DATA_EMAIL_ID));
	}

	//Bug - LHN-794
	@Test(dataProvider = "InvalidEmailId", description = "[LHN.716] Create a custodian with invalid email - https://ottr.opentext.com/test_case_node/show/3017412", groups = {
			REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void verfiyDelegatorInvalidEmail(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing verfiyDelegatorInvalidEmail ");
		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setDelegate_email(testdata.get(DATA_EMAIL_ID));

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
				" No Error in creating custodian for delegator email id " + testdata.get(DATA_EMAIL_ID));
		Assert.assertEquals(response.jsonPath().getList("errors").get(0), ERROR_EMAIL_DELEGATOR,
				"Error message does not match for " + testdata.get(DATA_EMAIL_ID));
	}


	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.718] Verify min length of fields while creating a custodians - https://ottr.opentext.com/test_case_node/show/3017460",
			groups = {REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianWithMinFieldLength(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianWithMinFieldLength ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setFirst_name(testdata.get(DATA_CUSTODIAN_FNAME));
		custodianRequest.setLast_name(testdata.get(DATA_CUSTODIAN_LNAME));
		custodianRequest.setPhone("1");
		custodianRequest.setTitle("M");
		custodianRequest.setEmployee_id("0");
		custodianRequest.setEmployee_status("A");
		custodianRequest.setEmployee_type("F");
		custodianRequest.setDepartment("S");
		custodianRequest.setLocation("M");
		custodianRequest.setFunction("N");
		custodianRequest.setBusiness("N");
		custodianRequest.setCountry("M");
		custodianRequest.setSupervisor_name("K");
		custodianRequest.setDelegate_name("Y");

		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = response.as(CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}


	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.717] Verify max length of fields while creating a custodian - https://ottr.opentext.com/test_case_node/show/3017454",
			groups = {REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianWithMaxFieldLength(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianWithMaxFieldLength ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setFirst_name(testdata.get(DATA_CUSTODIAN_FNAME));
		custodianRequest.setLast_name(testdata.get(DATA_CUSTODIAN_LNAME));
		custodianRequest.setPhone(testdata.get("phone"));
		custodianRequest.setTitle(testdata.get("title"));
		custodianRequest.setEmployee_id(testdata.get("employeeId"));
		custodianRequest.setEmployee_status(testdata.get("employeeStatus"));
		custodianRequest.setEmployee_type(testdata.get("employeeType"));
		custodianRequest.setDepartment(testdata.get("department"));
		custodianRequest.setLocation(testdata.get("location"));
		custodianRequest.setFunction(testdata.get("function"));
		custodianRequest.setBusiness(testdata.get("business"));
		custodianRequest.setCountry(testdata.get("country"));
		custodianRequest.setSupervisor_name(testdata.get("supervisorName"));
		custodianRequest.setDelegate_name(testdata.get("delegateName"));


		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in custodian create response code " + response.asString());
		verifyResponseWithRequest(response,custodianRequest);

		LOGGER.stepLog("Reading the POST Custodian response");
		CustodianResponsePojo requestResponse = response.as(CustodianResponsePojo.class);
		long custodianId = requestResponse.getId();

		LOGGER.stepLog("Reading the GET custodian response");
		CustodianResponsePojo readResponse = CustodiansListHelper.getCustodian(custodianId,testdata.get(DATA_TENANTID));

		LOGGER.stepLog("Validate the custodian response");
		Assert.assertEquals(requestResponse, readResponse, "Custodian data does not match -> Request custodian "
				+ response.asString() + " And Get Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	// Bug - LHN-1910
	@Test(dataProvider = "ApiDataFromYml", description = "[LHN.717] Verify max length of fields while creating a custodian - https://ottr.opentext.com/test_case_node/show/3017454",
			groups = {REGRESSION_GROUP }, priority = priority_CustodiansCreate)
	public void createCustodianBeyondMaxFieldLength(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testCaseLog("Executing createCustodianBeyondMaxFieldLength ");

		String custodianEndPoint = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, testdata.get(DATA_TENANTID));
		String firstName = testdata.get(DATA_CUSTODIAN_FNAME) == null? "QAFirst" + rand.nextInt():testdata.get(DATA_CUSTODIAN_FNAME);
		String lastName = testdata.get(DATA_CUSTODIAN_LNAME) == null? "QALast" + rand.nextInt():testdata.get(DATA_CUSTODIAN_LNAME);

		CustodiansRequestPojo custodianRequest = new CustodiansRequestPojo();
		custodianRequest.setFirst_name(firstName);
		custodianRequest.setLast_name(lastName);
		custodianRequest.setPhone(testdata.get("phone"));
		custodianRequest.setTitle(testdata.get("title"));
		custodianRequest.setEmployee_id(testdata.get("employeeId"));
		custodianRequest.setEmployee_status(testdata.get("employeeStatus"));
		custodianRequest.setEmployee_type(testdata.get("employeeType"));
		custodianRequest.setDepartment(testdata.get("department"));
		custodianRequest.setLocation(testdata.get("location"));
		custodianRequest.setFunction(testdata.get("function"));
		custodianRequest.setBusiness(testdata.get("business"));
		custodianRequest.setCountry(testdata.get("country"));
		custodianRequest.setSupervisor_name(testdata.get("supervisorName"));
		custodianRequest.setDelegate_name(testdata.get("delegateName"));


		LOGGER.stepLog("Posting the Custodian create request");
		Response response = restUtil.postLHNJson(custodianEndPoint, custodianRequest);
		Assert.assertNotNull(response, "Response of posting custodian is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
				" Error in custodian create response code " + response.asString());
		Assert.assertEquals(response.jsonPath().getList("errors").get(0), testdata.get(DATA_ERROR_MESSAGE),
				"Error message does not match ");

	}

	public static void verifyResponseWithRequest(Response response,CustodiansRequestPojo custodianRequest) throws JsonMappingException, JsonProcessingException {

		CustodiansRequestPojo responseInRequestFormat = MAPPERIGNOREUNKNOWPROPERTIES.readValue(response.asString(),CustodiansRequestPojo.class);

		Assert.assertEquals(responseInRequestFormat,custodianRequest, "Custodian data does not match -> Request custodian "
				+ MAPPER.writeValueAsString(custodianRequest) + " And Response Custodian " +  MAPPER.writeValueAsString(responseInRequestFormat) );
	}

	public static void verifyNewCustodianSubLinks(String tenantID, CustodianResponsePojo custodianResponse)
			throws JsonMappingException, JsonProcessingException {
		verifyCustodianSubLinksFormat(tenantID, custodianResponse);
		verifyCustodianSubLinksAccess(custodianResponse);
		verifyNewCustodianSubLinkStats(custodianResponse);
		Assert.assertNull(custodianResponse.get_links().getLegal_holds(),
				"New custodian legal hold sub link is not null");
		Assert.assertNull(custodianResponse.get_links().getMatters(), "New custodian matters sub link is not null");
	}

	public static void verifyCustodianSubLinksAccess(CustodianResponsePojo response) {

		List<String> allLinks = response.get_links().obtainAllLinks();

		for (int i = 0; i < allLinks.size(); i++) {
			if (allLinks.get(i) != null)
				Assert.assertEquals(restUtil.getJson(allLinks.get(i)).statusCode(), HttpStatus.SC_OK,
						" Invalid response code for link " + allLinks.get(i));
		}
	}

	public static void verifyCustodianSubLinksFormat(String tenantID, CustodianResponsePojo response) {

		String custodianPath = restUtil.getBaseEndpoint() + CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantID)
				+ "/" + response.getId();
		CustodiansLinksPojo links = response.get_links();

		Assert.assertEquals(custodianPath, links.getSelf().getHref(), "custodian self link format does not match");
		Assert.assertEquals(custodianPath + "/stats", links.getStats().getHref(),
				"custodian stats link format does not match");
		if (links.getLegal_holds() != null)
			Assert.assertEquals(custodianPath + "/legal_holds", links.getLegal_holds().getHref(),
					"custodian legal holds link format does not match");
		if (links.getMatters() != null)
			Assert.assertEquals(custodianPath + "/matters", links.getMatters().getHref(),
					"custodian matters link format does not match");

	}

	public static void verifyNewCustodianSubLinkStats(CustodianResponsePojo custodianResponse)
			throws JsonMappingException, JsonProcessingException {

		String statsLink = custodianResponse.get_links().getStats().getHref();
		Response statsResponseJson = restUtil.getJson(statsLink);
		Assert.assertEquals(statsResponseJson.statusCode(), HttpStatus.SC_OK,
				" Error in custodian stats response code " + statsResponseJson.asString());

		CustodianStatsPojo statsResponse = MAPPER.readValue(statsResponseJson.asString(), CustodianStatsPojo.class);
		Assert.assertEquals(statsResponse.getId(), custodianResponse.getId(), "Stats id does not match");
		Assert.assertEquals(statsResponse.getTotal_legal_holds(), 0L, "Stats total legal holds does not match");
		Assert.assertEquals(statsResponse.getActive_legal_holds(), 0L, "Stats active legal holds does not match");
		Assert.assertEquals(statsResponse.getTotal_matters(), 0L, "Stats matters does not match");
		Assert.assertEquals(statsResponse.get_links().getSelf().getHref(), statsLink, "Stats self link does not match");
		Assert.assertEquals(statsResponse.get_links().getCustodian().getHref(),
				custodianResponse.get_links().getSelf().getHref(), "Stats custodian link does not match");

		List<String> allLinks = statsResponse.get_links().obtainAllLinks();
		for (int i = 0; i < allLinks.size(); i++) {
			if (allLinks.get(i) != null)
			Assert.assertEquals(restUtil.getJson(allLinks.get(i)).statusCode(), HttpStatus.SC_OK,
					" Invalid response code for link " + allLinks.get(i));
		}
	}


}
