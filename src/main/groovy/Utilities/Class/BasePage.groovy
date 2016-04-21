package Utilities.Class

import Utilities.Data.ExcelReader
import com.relevantcodes.extentreports.ExtentTest
import geb.Page
import geb.content.SimplePageContent
import geb.navigator.Navigator

/**
 * Created by in02183 on 2/3/2016.
 */
class BasePage extends Page implements Constants{

    public String rootDir = new File(".").getCanonicalPath();
    public String sep = File.separator;
    public ExcelReader xlsrdr = new ExcelReader(rootDir+ "/src/main/resources/TestData.xls".replace('/', sep),"Credentials");
    public static ThreadLocal extentTest = new ThreadLocal<ExtentTest>()

    public static synchronized ExtentTest getEreportTest() {
        return extentTest.get();
    }

    public static synchronized void setEReporterTest(ExtentTest etest){
        extentTest.set(etest);
    }

    public void acceptAlert(String alertName){
        driver.switchTo().alert().accept();
        getEreportTest().log(PASS,"ALERT ACCEPT: "+ alertName);
    }

    public void waitForElement(SimplePageContent navigator, String elementName){
        waitFor {navigator.firstElement().displayed}
    }

    public void type(Navigator navigator, String value,String elementName){
        waitForElement(navigator,elementName)
        navigator << value;
        getEreportTest().log(PASS,"TYPE: "+elementName);
    }

    public void click(Navigator navigator,String elementName){
        waitForElement(navigator,elementName)
        navigator.click()
        getEreportTest().log(PASS,"CLICK: "+elementName);
    }

    public void sendKeys(Navigator navigator,String value, String elementName){
        waitForElement(navigator,elementName)
        navigator.firstElement().sendKeys(value);
        getEreportTest().log(PASS,"SENDKEYS: "+elementName);
    }

    public boolean waitTillElementIsNotPresent(Navigator navigator,String elementName) throws Throwable{
        try{
            for(int i=0;i<30;i++) {
                if (!(navigator.isDisplayed())) {
                    getEreportTest().log(PASS,"ELEMENT NOT PRESENT: "+elementName);
                    return true;
                } else {
                    Thread.sleep(1000L);
                }
            }
        }
        catch (Exception e){
        }
        return false;
    }
}
