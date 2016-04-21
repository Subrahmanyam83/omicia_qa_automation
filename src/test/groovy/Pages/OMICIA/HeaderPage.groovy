package Pages.OMICIA

import Modules.OMICIA.HeaderModule
import Modules.OMICIA.LoginModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/5/2016.
 */
class HeaderPage extends BasePage{

    static at = {header.homePageHeaderOmiciaText.displayed}

    static content = {
        header {module HeaderModule}
        login  {module LoginModule}
    }

    def clickOnMenuAndSelectOption(String pageName){
        click(header.menuDropDown,"Menu Drop Down");
        click(header.menuDropDownValue(pageName), "Menu Drop Down Value")
    }

    def signOut(){
        click(header.signOut,"Header Sign Out Button");
        waitFor {login.signInLink}
    }
}
