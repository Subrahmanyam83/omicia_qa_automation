package Modules.Login

import geb.Module

/**
 * Created by in02183 on 4/1/2016.
 */
class LoginModule extends Module{

    static content = {

        formPanel      { $(".form_panel") }
        usernameField  {$("#login")}
        passwordField  {$("#password")}
        signInButton   {$("input.btn.btn-large.btn-success.wide")}
        signInLink     {$("div a",text:contains("Sign In"))}

    }
}
