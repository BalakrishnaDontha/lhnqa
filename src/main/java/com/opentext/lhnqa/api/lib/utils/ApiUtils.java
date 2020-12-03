package com.opentext.lhnqa.api.lib.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.lhnqa.api.lib.testcases.ReferenceData;
import com.opentext.lhnqa.api.lib.testcases.RestHandler;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class ApiUtils implements ReferenceData {

	ExtLogger logger = new ExtLogger(ApiUtils.class.toString());
	RestHandler restUtil;
	ObjectMapper mapper = new ObjectMapper();
	public String nativesPath;

	public ApiUtils(RestHandler restUtil, String nativesPath) {
		this.restUtil = restUtil;
		this.nativesPath = nativesPath;
	}

}
