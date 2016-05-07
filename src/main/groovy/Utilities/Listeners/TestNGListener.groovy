package Utilities.Listeners

import Utilities.Class.BaseSpec
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

/**
 * Created by E002183 on 5/2/2016.
 */
class TestNGListener extends BaseSpec implements ITestListener {
    /**
     * Invoked each time before a test will be invoked.
     * The <code>ITestResult</code> is only partially filled with the references to
     * class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    void onTestStart(ITestResult result) {

    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    void onTestSuccess(ITestResult result) {
        getEreportTest().log(INFO, "Test Case: '" + result.getName() + "' PASSED SUCESSFULLY");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    void onTestFailure(ITestResult result) {
        System.out.println("Hi This test case has failed")
        report(result.getName());
        getEreportTest().log(FAIL, "Test Case '" + result.getName() + "' Failed. Reason: " + result.throwable, result.throwable)
        if (rootDir.contains("job")) {
            getEreportTest().log(WARNING, "Failure Snapshot of Test Case: '" + result.getName() + "' below:" + getEreportTest().addScreenCapture("../ws/reports/Geb-Reports/" + result.getName() + ".png"));
        } else {
            getEreportTest().log(WARNING, "Failure Snapshot of Test Case: '" + result.getName() + "' below:" + getEreportTest().addScreenCapture("../Geb-Reports/" + result.getName() + ".png"));
        }

    }

    /**
     * Invoked each time a test is skipped.
     *
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
