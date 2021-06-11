package com.opentext.lhnqa.api.testcases.matters;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.helper.CustodiansListHelper;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.api.testcases.custodians.CreateCustodians;
import com.opentext.lhnqa.lib.domain.CustodianResponsePojo;
import com.opentext.lhnqa.lib.domain.CustodiansListResponsePojo;
import com.opentext.lhnqa.lib.domain.HrefPojo;
import com.opentext.lhnqa.lib.domain.MatterRequestPojo;
import com.opentext.lhnqa.lib.domain.MatterResponsePojo;
import com.opentext.lhnqa.lib.domain.MatterStatsPojo;
import com.opentext.lhnqa.lib.domain.MattersListResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class CreateMatters extends ApiTestcaseBase {

	static final ExtLogger LOGGER = new ExtLogger(CreateCustodians.class.toString());

	@Test(dataProvider = "ApiDataFromYml", description = "https://ottr.opentext.com/test_case_node/show/2735999", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_MattersCreate)
	public void createValidMatters(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createValidMatters ");

		String tenantId =  testdata.get(DATA_TENANTID);
		String matterEndPoint = MATTERS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId);
		MatterRequestPojo matterRequest = new MatterRequestPojo();
		if (testdata.get(DATA_CUSTODIANID) != null) {
			matterRequest.getCustodians_ids().addAll(CustodiansListHelper.getGlobalCustodians(2, tenantId));
			matterRequest
					.setCustodianMatterCount(getCustodiansMatterCount(tenantId, matterRequest.getCustodians_ids()));
		}

		LOGGER.stepLog("Posting the matter create request");
		Response response = restUtil.postLHNJson(matterEndPoint, matterRequest);
		Assert.assertNotNull(response, "Response of posting matter is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in matter create response code " + response.asString());

		LOGGER.stepLog("Reading the POST Matter response");
		MatterResponsePojo requestResponse = MAPPER.readValue(response.asString(), MatterResponsePojo.class);
		Assert.assertTrue((new Date().getTime() - requestResponse.fetchCreatedAtInDateFormat().getTime()) < 120000,
				"Matter creation timestamp does not match");
		Assert.assertTrue((new Date().getTime() - requestResponse.fetchUpdatedAtInDateFormat().getTime()) < 120000,
				"Matter Update timestamp does not match");
		verifyNewMatterSubLinks(tenantId, matterRequest,requestResponse);

		long matterId = requestResponse.getId();
		LOGGER.stepLog("Reading the GET Matter response");
		Response getResponse = restUtil.getJson(matterEndPoint+"/"+matterId);
		Assert.assertNotNull(getResponse, "Response of posting matter is NULL");
		Assert.assertEquals(getResponse.statusCode(), HttpStatus.SC_OK,
				" Error in matter create response code " + getResponse.asString());
		MatterResponsePojo readResponse = MAPPER.readValue(getResponse.asString(), MatterResponsePojo.class);

		LOGGER.stepLog("Validate the Matter response");
		Assert.assertEquals(requestResponse, readResponse, "Matter data does not match -> Request Matter "
				+ response.asString() + " And Matter Custodian " + MAPPER.writeValueAsString(readResponse));
	}


	@Test(dataProvider = "ApiDataFromYml", description = "https://ottr.opentext.com/test_case_node/show/2756917", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_MattersCreate)
	public void createMatterWithNoNameNumber(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createMatterWithNoNameNumber ");

		String tenantId =  testdata.get(DATA_TENANTID);
		String matterEndPoint = MATTERS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId);
		MatterRequestPojo matterRequest = new MatterRequestPojo();
		matterRequest.setName(testdata.get(DATA_NAME));
		matterRequest.setNumber(testdata.get(DATA_NUMBER));
		matterRequest.getCustodians_ids().addAll(CustodiansListHelper.getGlobalCustodians(2,tenantId));


		LOGGER.stepLog("Posting the matter create request");
		Response response = restUtil.postLHNJson(matterEndPoint, matterRequest);
		Assert.assertNotNull(response, "Response of posting matter is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_UNPROCESSABLE_ENTITY,
				" Able to create matter without matter name/number " + response.asString());
		List<String> expectedErrorAttribute = Arrays.asList(testdata.get(DATA_ERROR_MESSAGE).split(",")).stream().map(msg -> "/data/attributes/" + msg).collect(Collectors.toList());
		List<String> expectedErrorMessage = Arrays.asList(testdata.get(DATA_ERROR_MESSAGE).split(",")).stream().map(msg -> ERROR_EMPTY_ATTRIBUTE).collect(Collectors.toList());
		Assert.assertEquals(response.jsonPath().getList("errors.source.pointer"),expectedErrorAttribute
				,"Attribute name does not match "  + response.asString());
		Assert.assertEquals(response.jsonPath().getList("errors.detail"),
				expectedErrorMessage,"Attribute name does not match "  + response.asString());

	}


	@Test(dataProvider = "ApiDataFromYml", description = "https://ottr.opentext.com/test_case_node/show/2756917", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_MattersCreate)
	public void createMatterWithInvalidCustodians(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createMatterWithInvalidCustodians ");

		String tenantId =  testdata.get(DATA_TENANTID);
		String matterEndPoint = MATTERS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId);
		MatterRequestPojo matterRequest = new MatterRequestPojo();
		if (testdata.get(DATA_CUSTODIANID) == null) {
			matterRequest.getCustodians_ids().addAll(CustodiansListHelper.getGlobalCustodians(1, tenantId));
		}
		matterRequest.getCustodians_ids().add(0L);

		LOGGER.stepLog("Posting the matter create request");
		Response response = restUtil.postLHNJson(matterEndPoint, matterRequest);
		Assert.assertNotNull(response, "Response of posting matter is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_BAD_REQUEST,
				" Able to create matter with invalid custodians " + response.asString());
		Assert.assertEquals(response.jsonPath().getList("errors"),Arrays.asList(ERROR_INVALID_CUSTODIANS)
				,"Error message does not match "  + response.asString());
	}


	@Test(dataProvider = "ApiDataFromYml", description = "https://ottr.opentext.com/test_case_node/show/2735999", groups = {
			REGRESSION_GROUP, SMOKE_GROUP }, priority = priority_MattersCreate)
	public void createMatterWithNoOptionalData(Map<String, String> testdata)
			throws JsonMappingException, JsonProcessingException, ParseException {

		LOGGER.testCaseLog("Executing createMatterWithNoOptionalData ");

		String tenantId =  testdata.get(DATA_TENANTID);
		String matterEndPoint = MATTERS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantId);
		MatterRequestPojo matterRequest = new MatterRequestPojo();
		matterRequest.setNotes(null);
		matterRequest.setCaption(null);
		matterRequest.setPo_number(null);
		matterRequest.setCase_number(null);
		matterRequest.setBusiness_unit(null);
		matterRequest.setRegion(null);
		matterRequest.setMatter_contacts_attributes(null);
		matterRequest.setEmail_display_name(null);
		matterRequest.setEmail_reply_to(null);
		matterRequest.setEmail_notification_text(null);

		LOGGER.stepLog("Posting the matter create request");
		Response response = restUtil.postLHNJson(matterEndPoint, matterRequest);
		Assert.assertNotNull(response, "Response of posting matter is NULL");
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
				" Error in matter create response code " + response.asString());

		LOGGER.stepLog("Reading the POST Matter response");
		MatterResponsePojo requestResponse = MAPPER.readValue(response.asString(), MatterResponsePojo.class);
		verifyNewMatterSubLinks(tenantId, matterRequest,requestResponse);

		long matterId = requestResponse.getId();
		LOGGER.stepLog("Reading the GET Matter response");
		Response getResponse = restUtil.getJson(matterEndPoint+"/"+matterId);
		Assert.assertNotNull(getResponse, "Response of posting matter is NULL");
		Assert.assertEquals(getResponse.statusCode(), HttpStatus.SC_OK,
				" Error in matter create response code " + getResponse.asString());
		MatterResponsePojo readResponse = MAPPER.readValue(getResponse.asString(), MatterResponsePojo.class);

		LOGGER.stepLog("Validate the Matter response");
		Assert.assertEquals(requestResponse, readResponse, "Matter data does not match -> Request Matter "
				+ response.asString() + " And Matter Custodian " + MAPPER.writeValueAsString(readResponse));
	}

	public static void verifyNewMatterSubLinks(String tenantID, MatterRequestPojo matterRequest ,MatterResponsePojo matterResponse)
			throws JsonMappingException, JsonProcessingException {

		String matterlink = restUtil.getBaseEndpoint() + MATTERS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantID) + "/"
				+ matterResponse.getId();
		Assert.assertEquals(matterResponse.get_links().getSelf().getHref(), matterlink,
				"Matter self link does not match");
		Assert.assertNull(matterResponse.get_links().getLegal_holds(), "Legal hold link is available for new matter");

		verifyMatterSubLinksAccess(matterResponse);
		verifyNewMatterSubLinkStats(matterRequest, matterResponse);

		HrefPojo custodianLink = matterRequest.getCustodians_ids().size() > 0
				? matterResponse.get_links().getCustodians()
				: null;
		Assert.assertEquals(custodianLink, matterResponse.get_links().getCustodians(),
				"Custodian link is available when no custodians in matter");
		if (custodianLink != null) {
			verifyNewMatterSubLinkCustodians(matterRequest, matterResponse);
		}
	}


	public static void verifyMatterSubLinksAccess(MatterResponsePojo response) {

		List<String> allLinks = response.get_links().obtainAllLinks();

		for (int i = 0; i < allLinks.size(); i++) {
			if (allLinks.get(i) != null && i != 3 )
				Assert.assertEquals(restUtil.getJson(allLinks.get(i)).statusCode(), HttpStatus.SC_OK,
						" Invalid response code for link " + allLinks.get(i));
		}
	}


	public static void verifyNewMatterSubLinkStats(MatterRequestPojo matterRequest ,MatterResponsePojo matterResponse)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testLog(Level.INFO, "Verifying Matter sublink stats");
		String statsLink = matterResponse.get_links().getStats().getHref();
		Response statsResponseJson = restUtil.getJson(statsLink);
		Assert.assertEquals(statsResponseJson.statusCode(), HttpStatus.SC_OK,
				" Error in matter stats response code " + statsResponseJson.asString());

		MatterStatsPojo statsResponse = MAPPER.readValue(statsResponseJson.asString(), MatterStatsPojo.class);
		Assert.assertEquals(statsResponse.getId(), matterResponse.getId(), "Stats id does not match");
		Assert.assertEquals(statsResponse.getActive_legal_holds(), 0L, "Stats active legal holds does not match");
		Assert.assertEquals(statsResponse.getReleased_legal_holds(), 0L, "Stats released legal holds does not match");
		Assert.assertEquals(statsResponse.getCustodians(), matterRequest.getCustodians_ids().size(), "Stats matters does not match");
		Assert.assertEquals(statsResponse.get_links().getSelf().getHref(), statsLink, "Stats self link does not match");
		Assert.assertEquals(statsResponse.get_links().getMatter().getHref(),
				matterResponse.get_links().getSelf().getHref(), "Stats matter link does not match");

		List<String> allLinks = statsResponse.get_links().obtainAllLinks();
		for (int i = 0; i < allLinks.size(); i++) {
			if (allLinks.get(i) != null)
			Assert.assertEquals(restUtil.getJson(allLinks.get(i)).statusCode(), HttpStatus.SC_OK,
					" Invalid response code for link " + allLinks.get(i));
		}
	}


	public static void verifyNewMatterSubLinkCustodians(MatterRequestPojo matterRequest ,MatterResponsePojo matterResponse)
			throws JsonMappingException, JsonProcessingException {

		LOGGER.testLog(Level.INFO, "Verifying Matter sublink custodian");
		String custodiansLink = matterResponse.get_links().getCustodians().getHref();
		List<Long> requestCustodianIdList = matterRequest.getCustodians_ids();
		Collections.sort(requestCustodianIdList);

		Response custodiansResponseJson = restUtil.getJson(custodiansLink);
		Assert.assertEquals(custodiansResponseJson.statusCode(), HttpStatus.SC_OK,
				" Error in custodian stats response code " + custodiansResponseJson.asString());

		CustodiansListResponsePojo custodiansResponse = MAPPER.readValue(custodiansResponseJson.asString(),
				CustodiansListResponsePojo.class);
		Assert.assertEquals(custodiansResponse.get_links().getSelf().getHref(), custodiansLink,
				"Custodian self link does not match");
		Assert.assertEquals(custodiansResponse.getPage().getTotal_count(), matterRequest.getCustodians_ids().size(),
				"Custodians Count does not match");

		List<CustodianResponsePojo> custodianList = CustodiansListHelper.getAllCustodiansFromListResponse(custodiansResponse);
		List<Long> responseCustodianIdList = custodianList.stream().map(cust -> cust.getId())
				.collect(Collectors.toList());
		Collections.sort(responseCustodianIdList);
		Assert.assertEquals(requestCustodianIdList, responseCustodianIdList, " Custodians list does not match ");
		Map<Long, Long> custodianMatterCount = new HashMap<Long, Long>();

		for (CustodianResponsePojo custodian : custodianList) {
			String matterLink = custodian.get_links().getMatters().getHref();
			Response mattersResponseJson = restUtil.getJson(matterLink);
			Assert.assertEquals(mattersResponseJson.statusCode(), HttpStatus.SC_OK,
					" Error in matter stats response code " + mattersResponseJson.asString());

			MattersListResponsePojo mattersResponse = MAPPER.readValue(mattersResponseJson.asString(),
					MattersListResponsePojo.class);
			List<MatterResponsePojo> matterList = ListMatters.getAllMattersFromListResponse(mattersResponse);
			Assert.assertTrue(matterList.stream().map(mat -> mat.getId()).collect(Collectors.toList())
					.contains(matterResponse.getId()), "Matter does not added to the custodian");

			String statsLink = custodian.get_links().getStats().getHref();
			Response statsResponseJson = restUtil.getJson(statsLink);
			Assert.assertEquals(statsResponseJson.statusCode(), HttpStatus.SC_OK,
					" Error in matter stats response code " + statsResponseJson.asString());

			custodianMatterCount.put(custodian.getId(), statsResponseJson.jsonPath().getLong("total_matters"));
		}

		for (Long CustodianId : custodianMatterCount.keySet()) {
			Assert.assertTrue(
					custodianMatterCount.get(CustodianId)
							.equals(matterRequest.getCustodianMatterCount().get(CustodianId) + 1L),
					" Custodiam total matter count not incremented ");
		}
	}

	public static Map<Long, Long> getCustodiansMatterCount(String tenantID,List<Long> custodianIds){

		Map<Long, Long> custodianMatterCount = new HashMap<Long, Long>();

		for(Long custodianId:custodianIds) {
		String statsLink = CUSTODIANS_ENDPOINT_PATH.replace(PLACEHOLDER1, tenantID)+"/"+custodianId+"/stats";
		Response statsResponseJson = restUtil.getJson(statsLink);
		Assert.assertEquals(statsResponseJson.statusCode(), HttpStatus.SC_OK,
				" Error in matter stats response code " + statsResponseJson.asString());

		custodianMatterCount.put(custodianId, statsResponseJson.jsonPath().getLong("total_matters"));
		}

		return custodianMatterCount;
	}
}