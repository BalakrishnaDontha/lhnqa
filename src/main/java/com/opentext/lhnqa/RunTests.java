package com.opentext.lhnqa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.testng.TestNG;
import com.opentext.lhnqa.lib.utils.ExtLogger;

public class RunTests {

	private static ExtLogger logger = new ExtLogger(RunTests.class.toString());

	public static void main(String[] configFiles) throws IOException {

		if (configFiles.length <= 0) {
			logger.testLog(Level.SEVERE, "Please provide the Config file path <ConfigDev.xml> as first argument");
		} else {
			TestNG testNG = new TestNG();
			List<String> suite = new ArrayList<>();
			File testNGXml = new File(configFiles[0]);

			if (testNGXml.exists()) {
				suite.add(configFiles[0]);
				testNG.setTestSuites(suite);
				testNG.run();
			} else {
				logger.testLog(Level.SEVERE, "Config file does not exist");
			}
		}
	}

}
