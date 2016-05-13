package Utilities.Class

import Utilities.Data.ExcelReader
import com.relevantcodes.extentreports.ExtentTest
import geb.Page
import geb.content.SimplePageContent
import geb.navigator.Navigator
import org.apache.http.client.fluent.Request

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
        getEreportTest().log(PASS,"Accept on Alert: "+ alertName+" was successful");
    }

    public void waitForElement(SimplePageContent navigator, String elementName){
        waitFor {navigator.firstElement().displayed}
    }

    public void type(Navigator navigator, String value,String elementName){
        waitForElement(navigator,elementName)
        click(navigator,"Text Field: "+elementName)
        navigator << value;
        getEreportTest().log(PASS,"Entered : '"+value+"' on "+elementName);
    }

    public void click(Navigator navigator,String elementName){
        waitForElement(navigator,elementName)
        navigator.click()
        getEreportTest().log(PASS,"Clicked on: "+elementName);
    }

    public void sendKeys(Navigator navigator,String value, String elementName){
        waitForElement(navigator,elementName)
        navigator.firstElement().sendKeys(value);
        getEreportTest().log(PASS,"Entered : '"+value+"' on "+elementName);
    }

    public boolean waitTillElementIsNotPresent(Navigator navigator,String elementName) throws Throwable{
        try{
            for(int i=0;i<30;i++) {
                if (!(navigator.isDisplayed())) {
                    getEreportTest().log(PASS,"Wait on the Element Not Present successful on the element: "+elementName);
                    Thread.sleep(1000L)
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

    public void scrollToCenter(Navigator navigator) {
        int center = driver.manage().window().getSize().getHeight() / 2
        int locatorHeight = navigator.firstElement().getLocation().getY() - center
        js.exec("window.scrollTo(0,$locatorHeight);")
        Thread.sleep(1000)
    }

    public int getResponseCode(String url) {
        try {
            return Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
