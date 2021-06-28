# Execution Commands

Please execute below commands to run the suite.
1.  Build the project by gradle command : gradlew buildFatJar
2.  Create Tar file by gradle command : gradlew createPackage
3.  Untar the tar.gz available in the deployment folder
4.  Config & test data files are available in untar folder , please make sure both are under same folder.
5.  Local Machine execution via command prompt
    - Downloaded the required browsers in local machine
    - Download the required browser drivers in local machine from https://www.seleniumhq.org/download/
    - Change directory to folder where LHN-QA-Fat-jar-x.x.x.jar available in untar folder
    -  Command 1 : To run with default drivers available in tar 
    
        ```sh
        $ java  -jar LHN-QA-Fat-jar-0.0.1.jar <config file path> 
        Ex: 
        $ java  -jar LHN-QA-Fat-jar-0.0.1.jar  "C:\lhn-qa\deployment\testNG.xml"
        ```
    -   Command 2: To run with specified drivers 
        ```sh
        $ java -Dwebdriver.chrome.driver=<driverPath>  -Dwebdriver.gecko.driver=<driverPath> -Dwebdriver.ie.driver=<driverPath>  -jar LHN-QA-Fat-jar-0.0.1.jar <config file path>
        ```
        Ex:
        ```sh
        $ java -Dwebdriver.chrome.driver="C:\chromedriver.exe"  -Dwebdriver.gecko.driver="C:\geckodriver.exe"  -Dwebdriver.ie.driver="C:\IEDriverServer.exe"  -jar LHN-QA-Fat-jar-0.0.1.jar  "C:\resources\testNG.xml"
        ```

6.  Remote machine execution via command prompt
    -   Downloaded the required browsers in remote machine
    -   Download the required browser drivers in remote machine from https://www.seleniumhq.org/download/
    -   Download the Selenium Standalone Server from https://www.seleniumhq.org/download/
    -   Run the Selenium Standalone Server with below command at remote machine 
    -  Command :
    
        ```sh
        $ java -Dwebdriver.chrome.driver=<driverPath>  -Dwebdriver.gecko.driver=<driverPath> -Dwebdriver.ie.driver=<driverPath>  -jar selenium-server-standalone-x.yy.z.jar -port <port as specified in config file>
        ```
    -   Run the below command in local to trigger scripts at remote machine
    -   Command :  
        ```sh
        $ java  -jar LHN-QA-Fat-jar-0.0.1.jar <config file path>
        ```
        Ex:
        
        ```sh
        $ java  -jar LHN-QA-Fat-jar-0.0.1.jar  "C:\lhn-qa\deployment\testNG.xml"
        ```

# Test Results

-   Test results can be viewed at "< un-tar path >\deployment\test-output\emailable-report.html"

# Internet Explorer setup required for selenium driver in windows

  - Follow the instructions provided at https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver 
  - Please use IE driver 32-bit version as Perceptiv supports the same.


### Important IE Config settings


* The IEDriverServer exectuable must be downloaded and placed in your PATH.
* On IE 7 or higher on Windows Vista or Windows 7, you must set the Protected Mode settings for each zone to be the same value.
    -   The value can be on or off, as long as it is the same for every zone. 
    -   To set the Protected Mode settings, choose "Internet Options..." from the Tools menu, and click on the Security tab.For each zone, there will be a check box at the bottom of the tab labeled "Enable Protected Mode"
* Additionally, "Enhanced Protected Mode" must be disabled for IE 10 and higher. This option is found in the Advanced tab of the Internet Options dialog.
* The browser zoom level must be set to 100% so that the native mouse events can be set to the correct coordinates.
* For Windows 10, you also need to set "Change the size of text, apps, and other items" to 100% in display settings.
* For IE 11 only, you will need to set a registry entry on the target computer so that the driver can maintain a connection to the instance of Internet Explorer it creates. 
    -   For 32-bit Windows installations, the key you must examine in the registry editor is HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. 
	-   For 64-bit Windows installations, the key is HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE. 
	-   Please note that the FEATURE_BFCACHE subkey may or may not be present, and should be created if it is not present. Important: Inside this key, create a DWORD value named iexplore.exe with the value of 0.
* Helpfull notes : http://toolsqa.com/selenium-webdriver/challenges-to-run-selenium-scripts-with-ie-browser/
