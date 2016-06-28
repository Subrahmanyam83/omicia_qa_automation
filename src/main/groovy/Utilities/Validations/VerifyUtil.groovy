package Utilities.Validations

import org.testng.Assert


/**
 * Created by E002149 on 6/10/2016.
 */

class VerifyUtil {
    private ArrayList<String> results = new ArrayList<String>()

    /**
     * Takes two argument
     * @param condition - That needs to verified
     * @param message - Failure message in case verification fails
     * @return - returns current object for further use
     */
    public def verify(boolean condition,String message){
        if(!condition) results.add(message)
        return this
    }

    /**
     * Use this to verify the test results.
     * This method will assert the results and fails the test in case any of the test are failed.
     */
    public def assertTestResult(){
        Assert.assertTrue(results.size()==0,results.toString())
        return results.size() == 0
    }

    /**
     * Use this to Assert all the test results
     * This method will assert the results and fails the test in case any of the test are failed.
     * @param message - Failure message in case verification fails
     *
     */
    public def assertTestResult(String message){
        Assert.assertTrue(results.size()==0,message+" "+results.toString())
        return results.size() == 0
    }
}