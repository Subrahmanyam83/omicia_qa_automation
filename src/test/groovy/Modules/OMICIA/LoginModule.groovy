package Modules.OMICIA

import geb.Module
import org.openqa.selenium.By

/**
 * Created by in02183 on 4/1/2016.
 */
class LoginModule extends Module{

    static content = {
        usernameField  {$("#login")}
        passwordField  {$("#password")}
        signInButton   {$("input.btn.btn-large.btn-success.wide")}

        signInLink     {$("div a",text:contains("Sign In"))}

    }
}
