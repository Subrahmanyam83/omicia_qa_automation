package Pages.OMICIA

import Modules.OMICIA.HeaderModule
import Modules.OMICIA.UploadGenomeModule
import Modules.OMICIA.HomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class UploadGenomePage extends BasePage{

    static at = {uploadGenome.projectDropDown}

    static content = {
        uploadGenome {module UploadGenomeModule}
        home {module HomeModule}
        header {module HeaderModule}
    }

    def fillUploadGenomeForm(String projectName,boolean uploadGene,boolean createNewProject){
        String filepath = "C:\\Subrahmanyam\\Projects\\Omicia\\VCF.vcf";
        File f = new File(filepath);
        String GenomeLabel = "Genome-Label-1";
        String ExternalID = "External-ID-1";

        if(createNewProject){
            click(uploadGenome.newProjectButton, "New Project Button");
            type(uploadGenome.projectNameField,projectName, "Project Name Field");
            click(uploadGenome.createProjectButton, "Create Project Button");
            waitTillElementIsNotPresent(uploadGenome.projectCreatedModalDialog, "Project Created Alert");
        }
        if(uploadGene){
            sendKeys(uploadGenome.browseVcfFileButton,filepath,"Browse VCF File Button")
        }
        type(uploadGenome.genomeLabel,GenomeLabel,"Genome Label");
        type(uploadGenome.externalId,ExternalID," External ID");
        click(uploadGenome.genderDropDown,"Gender Drop Down");
        click(uploadGenome.genderValue("Male"), "Select Gender as Male");
        click(uploadGenome.confirmCheckBox,"Confirm Check Box");
        click(uploadGenome.uploadButton, "Upload Button");
    }

    def waitForTheVCFFileToUpload(){
        waitFor {uploadGenome.uploadSuccessMessage}
        Thread.sleep(3000L)
    }

}
