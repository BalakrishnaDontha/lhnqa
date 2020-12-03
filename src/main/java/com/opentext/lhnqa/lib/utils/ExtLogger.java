package com.opentext.lhnqa.lib.utils;import java.util.logging.Level;import java.util.logging.Logger;import org.testng.Reporter;/** * Logger. Logs messages on console and html reports. *  * @author bdontha *  */public class ExtLogger {    private Logger log;    public ExtLogger(String loggername) {        log = Logger.getLogger(loggername);    }    public void testLog(Level level, String logMsg) {        // To Log On Console        log.log(level, logMsg);        // To Log in Testng Html Report        Reporter.log(logMsg);    }    public void stepLog(String logMsg) {        // To Log On Console    	System.out.println(logMsg);        // To Log in Testng Html Report        Reporter.log(logMsg);    }    public void testStepLog(String logMsg) {        stepLog("=====>     " + logMsg + "       <======");    }    public void testCaseLog(String logMsg) {        stepLog("******************************************************************");        stepLog("#####>     " + logMsg);        stepLog("******************************************************************");    }}