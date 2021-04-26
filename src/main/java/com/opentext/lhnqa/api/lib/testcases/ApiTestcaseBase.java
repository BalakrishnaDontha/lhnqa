package com.opentext.lhnqa.api.lib.testcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.logging.Level;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.lhnqa.api.lib.utils.ApiUtils;
import com.opentext.lhnqa.lib.utils.ExtLogger;
import com.opentext.lhnqa.lib.utils.TestDataAPIYamlRepo;

public class ApiTestcaseBase implements ReferenceData {
	static final ExtLogger LOGGER = new ExtLogger(ApiTestcaseBase.class.toString());
	public static RestHandler restUtil;
	public Random rand;

	public String nativesPath;
	public ApiUtils apiUtil;
	public static final ObjectMapper MAPPER = new ObjectMapper();

	@BeforeClass(alwaysRun = true)
	public void intializeSetup(XmlTest config) throws JsonParseException, JsonMappingException, IOException {

		// Getting native files path based on TestNG file. Hence maintain same folder
		// structure
		File testNGFile = new File(config.getSuite().getFileName());
		nativesPath = testNGFile.getParent() + File.separator + NATIVESFOLDER + File.separator;

		restUtil = new RestHandler(config);
		apiUtil = new ApiUtils(restUtil, nativesPath);
		rand = new Random();
	}

	@AfterClass(alwaysRun = true)
	public void cleanUpMethod() {
		LOGGER.testLog(Level.INFO, "Initiating the flush");
		// TODO For data cleanup
	}

	@DataProvider(name = "ApiDataFromYml")
	public Object[][] testCaseDataProviderYml(ITestContext context, Method m) {
		return TestDataAPIYamlRepo.getDataRepo(context).getData(m.getName());
	}

	public String getNativesPath() {
		return nativesPath;
	}
}
