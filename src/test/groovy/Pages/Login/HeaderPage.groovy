package Pages.Login

import Modules.Login.HeaderModule
import Modules.Login.LoginModule
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
        waitFor {header.menuDropDown}
        click(header.menuDropDown,"Menu Drop Down");
        click(header.menuDropDownValue(pageName), "Menu Drop Down Value")
    }

    def signOut(){
        click(header.signOut,"Header Sign Out Button");
        waitFor {login.signInLink}
    }

    def goToHomePage() {
        waitFor { header.omiciaOpalHomePage }
        click(header.omiciaOpalHomePage, "HomePage Button on Header")
    }

    def clickOnOPALAdminAndChooseTab(String tabName) {
        waitFor { header.opalAdminButton }
        click(header.opalAdminButton, "OPAL ADMIN Button")
        waitFor { header.opalAdminTabs(tabName) }
        click(header.opalAdminTabs(tabName), "Opal Admin Tab: " + tabName)
    }

    def clickOnEmailOnHeaderPage(){
        waitFor {header.userEmailOnHeaderPage}
        click(header.userEmailOnHeaderPage,"Email on Header Page")
    }
}
