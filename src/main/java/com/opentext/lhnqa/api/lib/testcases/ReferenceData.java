package com.opentext.lhnqa.api.lib.testcases;

public interface ReferenceData {



	// Constants for Test data sheet


	// Test case Groups
	String REGRESSION_GROUP = "Regression";
	String SMOKE_GROUP = "Smoke";

	// Others
	String nativesFolder = "LegalHoldDoc";
	String placeholder1="placeholder1";

	// Constants for End points
	String endpoint_path ="/t/" + placeholder1   + "/api/v3";
	String custodians_path = "/custodians";

	// priority
	int priority_Custodians = 1;
	int priority_Matters = 2;
	int priority_LegalHold = 3;
	int priority_Folders = 4;
	int priority_Tenant= 5;


}
