package Pages.Login

import Modules.Login.LoginModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class LoginPage extends BasePage{

    static at = { login.formPanel.isDisplayed() }

    static content = {
        login {module LoginModule}
    }

    String userName = xlsrdr.getDataArrayBySheet("Credentials")[1][0]
    String password = xlsrdr.getDataArrayBySheet("Credentials")[1][1]

    def signIn(){
        type(login.usernameField,userName,"UserName Field");
        type(login.passwordField,password,"Password Field");
        click(login.signInButton,"Sign In Button");
    }
}
