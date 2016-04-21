package Pages.OMICIA

import Modules.OMICIA.HeaderModule
import Modules.OMICIA.HomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class OmiciaHomePage extends BasePage{

    static at = {header.homePageHeaderOmiciaText.displayed}

    static content = {
        home {module HomeModule}
        header {module HeaderModule}
    }

    def openUploadGenomes(){
        click(home.uploadGenomesButton,"Upload Genome Button");
    }

    def openProjects(){
        click(home.projectsButton,"Projects Button");
    }

    def openClinicalReporter(){
        click(home.clinicalReporterButton,"Clinical Reporter Button");
    }
}
