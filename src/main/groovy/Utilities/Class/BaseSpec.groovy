package Utilities.Class

import Utilities.Data.ExcelReader
import Utilities.Reports.Extent.ExtentReportFactory
import com.relevantcodes.extentreports.ExtentTest
import com.relevantcodes.extentreports.LogStatus
import geb.testng.GebTest
import org.apache.log4j.Logger
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import org.testng.annotations.*

import java.lang.reflect.Method

/**
 * Created by in02183 on 2/3/2016.
 */
class BaseSpec extends GebTest implements ITestListener,Constants{
    public log;
    String rootDir = new File(".").getCanonicalPath() /*Absolute Path*/
    public String sep = File.separator;

    public ExcelReader xlsrdr = new ExcelReader(System.getProperty("user.dir") + "/src/main/resources/TestData.xls".replace("/", sep), "TestData");
    public static Map sysInfo = new HashMap();
    ExtentTest etest;
    public static ThreadLocal extentTest = new ThreadLocal<ExtentTest>();

    public static synchronized ExtentTest getEreportTest() {
        return extentTest.get();
    }

    public static synchronized void setEReporterTest(ExtentTest etest){
        extentTest.set(etest);
    }

    /* Runs Before Every Suite*/
    @BeforeSuite
    def beforeSuite(){
        convertPropertiesToSystemProperties();
    }

    /*Runs before every Class or Spec*/
    @BeforeClass
    def beforeClass(){
        log = Logger.getLogger(this.class);
        if(System.getProperty("geb.env").equals("safari")){
            driver.manage().deleteAllCookies()
        }
    }

    /*Runs before every Method or Test*/
    @BeforeMethod
    def beforeMethod(Method method){
        ExtentReportFactory.getTest(method.getName(), "Test Case Name: "+method.getName());
        etest = ExtentReportFactory.getTest();
        etest.log(LogStatus.INFO,"Started Executing Test Case: "+method.getName())
        BasePage.setEReporterTest(etest);
        BaseSpec.setEReporterTest(etest);
    }

    /*Runs After every Method or Test*/
    @AfterMethod
    def afterMethod(Method method) {
        ExtentReportFactory.closeTest(method.getName());
    }

    /*Runs after every Class or Spec*/
    @AfterClass
    def afterClass() {
    }

    /*Runs after every Suite*/
    @AfterSuite
    def afterSuite(){
        ExtentReportFactory.closeReport();
        //changeExtentReportHtml();
    }

    def changeExtentReportHtml(){
        String rootDir = new File(".").getCanonicalPath()
        String content = new File(rootDir+ System.getProperty("extent.reports.directory").replace("/",File.separator)).getText('UTF-8');
        content = content.replaceAll("<div class='container'>","<div class='container'>\n" +
                "<table id='Logos' class='testData'>\n" +
                "<colgroup>\n" +
                "<col style='width: 20%' />\n" +
                "<col style='width: 20%' />\n" +
                "<col style='width: 20%' />\n" +
                "<col style='width: 20%' />\n" +
                "</colgroup>\n" +
                "<thead> \n" +
                "\n" +
                "<tr class='content' height=\"30\">\n" +
                "<th style=\"padding:0px;padding-left:7px\" class ='Logos' colspan='auto' rowspan='1' >\n" +
                "<img style=\"margin:0px\" align ='left' src= \"../../src/main/resources/Logos/omicia_logo.png\"></img>\n" +
                "</th>\n" +
                "<th style=\"padding:0px\" class = 'Logos' colspan='auto' rowspan='1' >\n" +
                "<img style=\"margin:0px\" align ='right' src= \"../../src/main/resources/Logos/gallop_logo.png\"></img>\n" +
                "</th> \n" +
                "</tr>\n" +
                "\n" +
                "</thead> \n" +
                "</table>");

        File fold=new File(rootDir+System.getProperty("extent.reports.directory").replace("/",File.separator));
        fold.delete();

        File fnew=new File(rootDir+System.getProperty("extent.reports.directory").replace("/",File.separator));
        FileWriter f2 = new FileWriter(fnew, false);
        f2.write(content);
        f2.close();
    }

    /* Overriding the resetBrowser of the GebTest*/
    void resetBrowser(){
    }

    /* This Function reads the project.properties file and puts each K,V pair into System Properties.*/
    public void convertPropertiesToSystemProperties() {
        sysInfo = new HashMap();
        String rootDir = new File(".").getCanonicalPath() /*Absolute Path*/
        String projectPropertiesPath = rootDir + "/src/main/resources/project.properties".replace('/', File.separator)
        Properties properties = new Properties()
        properties.load(new FileInputStream(projectPropertiesPath))
        properties.each { key, value ->
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
                sysInfo.put(key,value);
            }
        }
    }

    def generateRandom() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    /**
     * Invoked each time before a test will be invoked.
     * The <code>ITestResult</code> is only partially filled with the references to
     * class, method, start millis and status.
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    void onTestStart(ITestResult result) {
    }

    /**
     * Invoked each time a test succeeds.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    void onTestSuccess(ITestResult result) {
        getEreportTest().log(INFO,"Test Case: '"+result.getName()+"' PASSED SUCESSFULLY");
    }

    /**
     * Invoked each time a test fails.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    void onTestFailure(ITestResult result) {
        report(result.getName());
        getEreportTest().log(FAIL, "Test Case '" +result.getName()+"' Failed. Reason: "+result.throwable,result.throwable)
        if(rootDir.contains("job")){
            getEreportTest().log(WARNING, "Failure Snapshot of Test Case: '"+result.getName()+"' below:" + getEreportTest().addScreenCapture("../ws/reports/Geb-Reports/"+result.getName()+".png"));
        }
        else{
            getEreportTest().log(WARNING, "Failure Snapshot of Test Case: '"+result.getName()+"' below:" + getEreportTest().addScreenCapture("../Geb-Reports/"+result.getName()+".png"));
        }
    }

    /**
     * Invoked each time a test is skipped.
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    void onTestSkipped(ITestResult result) {
    }

    /**
     * Invoked each time a method fails but has been annotated with
     * successPercentage and this failure still keeps it within the
     * success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    /**
     * Invoked after the test class is instantiated and before
     * any configuration method is called.
     */
    @Override
    void onStart(ITestContext context) {
    }

    /**
     * Invoked after all the tests have run and all their
     * Configuration methods have been called.
     */
    @Override
    void onFinish(ITestContext context) {
    }
}

