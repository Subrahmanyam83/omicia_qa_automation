package Pages.Login

import Modules.Login.HeaderModule
import Modules.Login.HomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class OmiciaHomePage extends BasePage{

    static at = {
        header.homePageHeaderOmiciaText.displayed
    }

    static content = {
        home {module HomeModule}
        header {module HeaderModule}
    }

    def openTab(String tabName){
        click(home.homePageTab(tabName),tabName)
    }

    def openUploadGenomes(){
        waitFor {home.uploadGenomesButton}
        click(home.uploadGenomesButton,"Upload Genome Button");
    }

    def openProjects(){
        click(home.projectsButton,"Projects Button");
    }
}
