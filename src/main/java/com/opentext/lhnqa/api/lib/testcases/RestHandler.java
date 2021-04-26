package com.opentext.lhnqa.api.lib.testcases;


import static com.jayway.restassured.RestAssured.given;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.testng.xml.XmlTest;

import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.opentext.lhnqa.lib.domain.LegalHoldRequestPojo;
import com.opentext.lhnqa.lib.utils.ExtLogger;

/**
 * @author bdontha
 */
public class RestHandler implements ReferenceData
{
	private ExtLogger logger = new ExtLogger(RestHandler.class.toString());
	public static final String JSON_UTF8 = ContentType.JSON.toString();
	private final String baseEndpoint;
	private final String lhnUsername;
	private final String lhnPassword;
	private final String lhnToken;
	private final int requestProcessWaitTime;


    public RestHandler(@Nonnull final XmlTest config)
    {
		baseEndpoint = config.getParameter("lhn_base_end_point");
		lhnUsername = config.getParameter("lhn_username");
		lhnPassword = config.getParameter("lhn_password");
		lhnToken = config.getParameter("lhn_token");
		requestProcessWaitTime = Integer.parseInt(config.getParameter("requestProcessWaitTime"));
    }


    public Response postLHNJson(@Nonnull final String endpoint, @Nonnull final Object body)
    {
        return postInternal(baseEndpoint,endpoint, body, JSON_UTF8,lhnUsername,lhnPassword);
    }


    private Response postInternal(@Nonnull final String baseEndpoint,@Nonnull final String endpoint, @Nonnull final Object body, @Nonnull final String mediaType,@Nonnull final String userName,@Nonnull final String password)
    {
	   logger.testStepLog("Posting..");
       return givenLHNAccess().accept(mediaType)
        		   			   .contentType(mediaType)
        		   			   .body(body)
        		   			   .baseUri(baseEndpoint)
        		   			   .post(endpoint);
    }

    public Response postLHNRequest(@Nonnull final String endpoint, @Nonnull final LegalHoldRequestPojo request)
    {
    	Response response = null;
		try {
			RequestSpecification fileRequest = givenLHNAccess().accept(JSON_UTF8)
					.multiPart(LHBODYCONTROLNAME, request, JSON_UTF8);;

					request.obtainAllattachmentsOfLHN().forEach(notice -> notice.files.forEach(file -> fileRequest.multiPart(new MultiPartSpecBuilder(file.file)
																		.controlName(file.controlName)
																		.fileName(file.fileName)
																		.mimeType(file.mimeType)
																		.build())));
			response = fileRequest.baseUri(baseEndpoint).post(endpoint);
		} catch (Exception e) {
			logger.testLog(Level.SEVERE, "Error in posting Request");
			e.printStackTrace();
		}
		return response;
    }

    public Response getJson(@Nonnull final String endpoint)
    {
        return getJson(endpoint, null);
    }

    public Response getJson(@Nonnull final String endpoint, @Nullable final Map<String, String> parameters)
    {
        return getInternal(endpoint, JSON_UTF8, parameters);
    }

    private Response getInternal(@Nonnull final String endpoint, @Nonnull final String mediaType, @Nullable final Map<String, String> parameters)
    {
        final Map<String, String> params = parameters == null ? Collections.emptyMap() : parameters;
        return givenLHNAccess().accept(mediaType)
        						 .contentType(mediaType)
        						 .params(params)
        						 .baseUri(baseEndpoint)
        						 .get(endpoint);
    }

    public RequestSpecification givenAccess(String userName , String password) {
    	return	given()
    			.relaxedHTTPSValidation()
    			.auth()
    			.basic(userName, password)
    			.when();
    }

    public RequestSpecification givenAccess(String token ) {
    	return	given()
    			.relaxedHTTPSValidation()
    			.header("x-auth-token", token)
    			.when();
    }

//	public RequestSpecification givenLHNAccess() {
//		return givenAccess(lhnUsername, lhnPassword);
//	}

	public RequestSpecification givenLHNAccess() {
		return givenAccess(lhnToken);
	}

	public int getRequestProcessWaitTime() {
		return requestProcessWaitTime;
	}

	public String getBaseEndpoint() {
		return baseEndpoint;
	}
}
