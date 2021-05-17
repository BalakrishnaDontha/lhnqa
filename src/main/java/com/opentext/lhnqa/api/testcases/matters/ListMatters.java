package com.opentext.lhnqa.api.testcases.matters;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jayway.restassured.response.Response;
import com.opentext.lhnqa.api.lib.testcases.ApiTestcaseBase;
import com.opentext.lhnqa.api.testcases.custodians.ListCustodians;
import com.opentext.lhnqa.lib.domain.MatterResponsePojo;
import com.opentext.lhnqa.lib.domain.MattersListResponsePojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class ListMatters extends ApiTestcaseBase  {

	static final ExtLogger LOGGER = new ExtLogger(ListCustodians.class.toString());


	public static List<MatterResponsePojo> getAllMattersFromListResponse(
			MattersListResponsePojo mattersListResponse) throws JsonMappingException, JsonProcessingException {

		List<MatterResponsePojo> matterList = new ArrayList<MatterResponsePojo>();
		matterList.addAll(mattersListResponse.get_embedded().getMatters());
		boolean hasMore = mattersListResponse.getPage().isHas_more();
		String nextPageUrl = hasMore ? mattersListResponse.get_links().getNext().getHref() : null;

		while (hasMore) {
			Response response = restUtil.getJson(nextPageUrl);
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,
					" Error in custodian list response code " + response.asString());
			MattersListResponsePojo requestResponse = MAPPER.readValue(response.asString(),
					MattersListResponsePojo.class);
			Assert.assertEquals(nextPageUrl, requestResponse.get_links().getSelf().getHref(),
					"Self link does not match");
			matterList.addAll(requestResponse.get_embedded().getMatters());
			hasMore = requestResponse.getPage().isHas_more();
			nextPageUrl = hasMore ? requestResponse.get_links().getNext().getHref() : null;
		}

		return matterList;
	}

}
