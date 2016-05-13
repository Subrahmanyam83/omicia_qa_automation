package Modules.Login

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class HeaderModule extends Module{

    static content = {
        homePageHeaderOmiciaText                  {$(".brand",text:"Omicia Opal")}
        menuDropDown                              {$("a.dropdown-toggle",text:"Menu")}
        menuDropDownValue                         {String value -> $("#profile-menu li a",text:value)}

        signOut                                   {$("li a",text: contains("Sign Out"))}
        omiciaOpalHomePage { $(".brand", text: contains("Omicia Opal")) }
    }
}
