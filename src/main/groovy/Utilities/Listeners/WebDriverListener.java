package Utilities.Listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * Created by in02183 on 2/8/2016.
 */
public class WebDriverListener implements WebDriverEventListener {

    /**
     * Called before {@link WebDriver#get get(String url)} respectively
     * {@link WebDriver.Navigation#to navigate().to(String url)}.
     *
     * @param url    URL
     * @param driver WebDriver
     */
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    /**
     * Called after {@link WebDriver#get get(String url)} respectively
     * {@link WebDriver.Navigation#to navigate().to(String url)}. Not called, if an
     * exception is thrown.
     *
     * @param url    URL
     * @param driver WebDriver
     */
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    /**
     * Called before {@link WebDriver.Navigation#back navigate().back()}.
     *
     * @param driver WebDriver
     */
    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    /**
     * Called after {@link WebDriver.Navigation navigate().back()}. Not called, if an
     * exception is thrown.
     *
     * @param driver WebDriver
     */
    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    /**
     * Called before {@link WebDriver.Navigation#forward navigate().forward()}.
     *
     * @param driver WebDriver
     */
    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    /**
     * Called after {@link WebDriver.Navigation#forward navigate().forward()}. Not called,
     * if an exception is thrown.
     *
     * @param driver WebDriver
     */
    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    /**
     * Called before {@link WebDriver.Navigation#refresh navigate().refresh()}.
     *
     * @param driver WebDriver
     */
    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    /**
     * Called after {@link WebDriver.Navigation#refresh navigate().refresh()}. Not called,
     * if an exception is thrown.
     *
     * @param driver WebDriver
     */
    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    /**
     * Called before {@link WebDriver#findElement WebDriver.findElement(...)}, or
     * {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElement
     * WebElement.findElement(...)}, or {@link WebElement#findElement WebElement.findElements(...)}.
     *
     * @param by      locator being used
     * @param element will be <code>null</code>, if a find method of <code>WebDriver</code> is called.
     * @param driver  WebDriver
     */
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    /**
     * Called after {@link WebDriver#findElement WebDriver.findElement(...)}, or
     * {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElement
     * WebElement.findElement(...)}, or {@link WebElement#findElement WebElement.findElements(...)}.
     *
     * @param by      locator being used
     * @param element will be <code>null</code>, if a find method of <code>WebDriver</code> is called.
     * @param driver  WebDriver
     */
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

    }

    /**
     * Called before {@link WebElement#click WebElement.click()}.
     *
     * @param element the WebElement being used for the action
     * @param driver  WebDriver
     */
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    /**
     * Called after {@link WebElement#click WebElement.click()}. Not called, if an exception is
     * thrown.
     *
     * @param element the WebElement being used for the action
     * @param driver  WebDriver
     */
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    /**
     * Called before {@link WebElement#clear WebElement.clear()}, {@link WebElement#sendKeys
     * WebElement.sendKeys(...)}.
     *
     * @param element the WebElement being used for the action
     * @param driver  WebDriver
     */
    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {

    }

    /**
     * Called after {@link WebElement#clear WebElement.clear()}, {@link WebElement#sendKeys
     * WebElement.sendKeys(...)}}. Not called, if an exception is thrown.
     *
     * @param element the WebElement being used for the action
     * @param driver  WebDriver
     */
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {

    }

    /**
     * Called before {@link RemoteWebDriver#executeScript(String, Object[]) }
     *
     * @param script the script to be executed
     * @param driver WebDriver
     */
    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    /**
     * Called after {@link RemoteWebDriver#executeScript(String, Object[]) }.
     * Not called if an exception is thrown
     *
     * @param script the script that was executed
     * @param driver WebDriver
     */
    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    /**
     * Called whenever an exception would be thrown.
     *
     * @param throwable the exception that will be thrown
     * @param driver    WebDriver
     */
    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }
}
