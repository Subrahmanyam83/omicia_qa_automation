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
        loginWithUser(NORMAL_USER);
    }

    def signInWIthCredentials(String userID, String passWord) {
        type(login.usernameField, userID, "UserName Field");
        type(login.passwordField, passWord, "Password Field");
        click(login.signInButton, "Sign In Button");
    }

    def loginWithUser(String user) {
        switch (user) {

            case NORMAL_USER:
                String userName = xlsrdr.getDataArrayBySheet("Credentials")[1][0]
                String password = xlsrdr.getDataArrayBySheet("Credentials")[1][1]
                signInWIthCredentials(userName, password);
                break;

            case ADMIN:
                String userName = xlsrdr.getDataArrayBySheet("Credentials")[2][0]
                String password = xlsrdr.getDataArrayBySheet("Credentials")[2][1]
                signInWIthCredentials(userName, password);
                break;

            case OWNER:
                String userName = xlsrdr.getDataArrayBySheet("Credentials")[3][0]
                String password = xlsrdr.getDataArrayBySheet("Credentials")[3][1]
                signInWIthCredentials(userName, password);
                break;
        }
    }
}
