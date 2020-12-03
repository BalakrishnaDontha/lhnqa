package com.opentext.lhnqa.lib.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.testng.ITestContext;
import org.testng.xml.XmlTest;
import org.yaml.snakeyaml.Yaml;

import com.opentext.lhnqa.exception.KeywordException;


public class TestDataAPIYamlRepo {

  private static ExtLogger LOGGER = new ExtLogger(TestDataAPIYamlRepo.class.toString());

  private static Map<String, Map<String, Map<String, String>>> pages;

  private String dataFileName;

  private String testDataFilePath;

  private XmlTest config;

  private static TestDataAPIYamlRepo testRepo = null;

  @SuppressWarnings("unchecked")
  private TestDataAPIYamlRepo(ITestContext context)
  {
    try
    {
      this.config = context.getCurrentXmlTest();
      this.dataFileName = config.getParameter("test_data_api_file");

      // TestNG file and TestData file should be under same folder
      // Get the test data sheet location
      File testNGFile = new File(context.getCurrentXmlTest().getSuite().getFileName());
      testDataFilePath = testNGFile.getParent() + File.separator + dataFileName;
      File testDataFile = new File(testDataFilePath);

      Yaml yml = new Yaml();
      InputStream repoYaml = new FileInputStream(testDataFile);
      BufferedReader reader = new BufferedReader(new InputStreamReader(repoYaml, StandardCharsets.UTF_8));
      pages = (Map<String, Map<String, Map<String, String>>>) yml.load(reader);
      LOGGER.testLog(Level.INFO, "Test data file loaded sucessfully");
      repoYaml.close();

		} catch (Exception e) {
			LOGGER.testLog(Level.SEVERE,"Not able to load the Test data file ");
			 e.printStackTrace();
		}
	}

	/*
   * Get the testCaseNamefrom object repo file
   */
  public Map<String, Map<String, String>> getTestDataSets(String testCaseName)
  {
    Map<String, Map<String, String>> testDataSets = pages.get(testCaseName);
    if(testDataSets == null) {

      throw new KeywordException(
          "Testcase name " + testCaseName + " is not available in the testdata yml file ");
    }
    return testDataSets;
	}

  public Object[][] getData(String testCaseName)
  {
    Map<String, Map<String, String>> mappedTestData = getTestDataSets(testCaseName);
    Object[][] testCaseData = new Object[mappedTestData.size()][1];
    List<String> dataSets = new ArrayList<String>(mappedTestData.keySet());
    for (int i = 0; i < dataSets.size(); i++)
    {
      testCaseData[i][0] = mappedTestData.get(dataSets.get(i));
    }

    return testCaseData;
  }

	/*
	 * Single ton instance as we do only read operations to object repo file
	 */
  public static TestDataAPIYamlRepo getDataRepo(ITestContext context)
  {
    if (testRepo == null)
    {
      testRepo = new TestDataAPIYamlRepo(context);
		}
    return testRepo;
	}

}
