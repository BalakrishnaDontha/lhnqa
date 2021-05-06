package com.opentext.lhnqa.api.lib.testcases;

public interface ReferenceData {



	// Constants for Test data sheet
	String DATA_TENANTID = "tenantId";
	String DATA_MATTERID = "matterId";
	String DATA_LEGALHOLDID = "legalholdId";
	String DATA_DRAFTSTATUS = "draftStatus";
	String DATA_CUSTODIANID = "custodianId";
	String DATA_DOC_NAME = "docName";
	String DATA_DOC_MIMETYPE = "docMimeType";
	String DATA_HOLDNOTICE_DOC = "holdnoticeDoc";
	String DATA_HOLDNOTICE_DOC_MIMETYPE = "holdnoticeDocMimeType";
	String DATA_ACKNOTICE_DOC = "acknoticeDoc";
	String DATA_ACKNOTICE_DOC_MIMETYPE = "acknoticeDocMimeType";
	String DATA_HOLDREMNOTICE_DOC = "holdremnoticeDoc";
	String DATA_HOLDREMNOTICE_DOC_MIMETYPE = "holdremnoticeDocMimeType";
	String DATA_ESCNOTICE_DOC = "escnoticeDoc";
	String DATA_ESCNOTICE_DOC_MIMETYPE = "escnoticeDocMimeType";
	String DATA_RELNOTICE_DOC = "relnoticeDoc";
	String DATA_RELNOTICE_DOC_MIMETYPE = "relnoticeDocMimeType";
	String DATA_ERROR_MESSAGE = "errorMessage";
	String DATA_RESPONSE_CODE = "responseCode";
	String DATA_CUSTODIAN_FNAME = "custodianFirstName";
	String DATA_CUSTODIAN_LNAME = "custodianLastName";
	String DATA_CUSTODIAN_EMAIL = "custodianEmail";
	String DATA_EMAIL_ID = "emailId";
	String DATA_PAGE_SIZE = "pageSize";

	// Test case Groups
	String REGRESSION_GROUP = "Regression";
	String SMOKE_GROUP = "Smoke";

	// Others
	String NATIVESFOLDER = "LegalHoldDoc";
	String PLACEHOLDER1="placeholder1";
	String PLACEHOLDER2="placeholder2";
	String LHDOCCONTROLNAME="lhDocControlName";
	String LHBODYCONTROLNAME = "legal_hold";
	String LHNACONTROLNAME = "hold_notice_attachments[]";
	String LHACKCONTROLNAME = "acknowledgement_reminder_notice_attachments[]";
	String LHREMCONTROLNAME = "hold_reminder_notice_attachments[]";
	String RELNCONTROLNAME = "release_notice_attachments[]";
	String ESCNCONTROLNAME = "escalation_notice_attachments[]";


	// Constants for End points
	String ENDPOINT_PATH ="/t/" + PLACEHOLDER1   + "/api/v3";
	String CUSTODIANS_PATH = "/custodians";
	String MATTERS_PATH = "/matters";
	String LEGALHOLD_PATH = "/legal_holds";
	String CUSTODIANS_ENDPOINT_PATH = ENDPOINT_PATH + CUSTODIANS_PATH;
	String MATTERS_ENDPOINT_PATH = ENDPOINT_PATH + MATTERS_PATH;
	String LEGALHOLD_ENDPOINT_PATH = ENDPOINT_PATH + LEGALHOLD_PATH;

	// priority
	int priority_CustodiansCreate = 1;
	int priority_CustodiansList= 2;
	int priority_Matters = 3;
	int priority_LegalHold = 4;
	int priority_Folders = 5;
	int priority_Tenant= 6;

	//error messages
	String ERROR_HOLDNOTICE_EMPTYFILE = " notice attachments file size must be greater than 0 Bytes";
	String ERROR_EMAIL_CUSTODIAN = "Email is invalid";
	String ERROR_EMAIL_SUPERVISOR = "Supervisor email is invalid";
	String ERROR_EMAIL_DELEGATOR = "Delegate email is invalid";

	//Email
	String[]  VALID_EMAIL_ADDRESS = {"simple@example.com","very.common@example.com","disposable.style.email.with+symbol@example.com","other.email-with-hyphen@example.com",
									"fully-qualified-domain@example.com","user.name+tag+sorting@example.com","x@example.com","example-indeed@strange-example.com",
									"admin@mailserver1","example@s.example","example@example.com1","\" \"@example.org","\"john..doe\"@example.org"};
	String[] INVALID_EMAIL_ADDRESS = {"Abc.example.com","A@b@c@example.com","a\"b(c)d,e:f;g<h>i[j\\k]l@example.com","just\"not\"right@example.com","this is\"not\\allowed@example.com",
									 "this\\ still\\\"not\r\nallowed@example.com","1234567890123456789012345678901234567890123456789012345678901234+x@example.com",
									 "john..doe@example.com","john.doe@example..com","example@example.3com","example@example.42","example@example.a"};

	//DateFormat
	String TIMESTAMP_DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

}
