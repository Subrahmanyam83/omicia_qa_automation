package Pages.Gene_Sets

import Modules.GeneSets.GeneSetsModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/22/2016.
 */
class GeneSetsPage extends BasePage{

    static at = {
        geneSets.newGeneSetButton.isDisplayed()
    }

    static content = {
        geneSets {module GeneSetsModule}
    }

    def clickOnSetType(String setType){
        click(geneSets.geneSetTypes(setType))
    }

    def createNewGeneSet(String name, String description, String geneType){
        click(geneSets.newGeneSetButton,"New Gene Set Button")
        type(geneSets.newGeneSetNameField,name,"New Gene Set Name Field");
        type(geneSets.newGeneSetDescription,description,"New Gene Set Description Field");
        click(geneSets.newGeneSetTypeDropDown,"Gene Set Type Button");
        click(geneSets.newGeneSetTypeValue(geneType),"New Gene Set Type Value");
        click(geneSets.newGeneSetSaveButton,"New Gene Set Save Button");
        waitTillElementIsNotPresent(geneSets.newGeneSetModalDialog, "New Gene Set Modal Dialog");
    }

    def performActionBasedOnGeneSetName(String setType,String geneSetName, String action){
        click(geneSets.geneSetTypes(setType),"Header Gene Type: "+setType)
        click(geneSets.actionButtonBasedOneneSet(geneSetName),"Actions Button of Test Gene Set")
        click(geneSets.performActionOnActionButton(action),"some action on Action Button of Test Gene Set ")
    }

    def addGenesToGeneSet(String geneSymbols){
        waitFor("fast") {geneSets.addGenesTextField.displayed}
        type(geneSets.addGenesTextField,geneSymbols,"Gene Symbols Field");
        click(geneSets.addGenesNextButton,"Add Gene Next Button");
        waitFor("fast") {geneSets.addGenesSaveButton}
        click(geneSets.addGenesSaveButton, "Add Gene Save Changes Button")
        waitTillElementIsNotPresent(geneSets.addGeneModalDialog, "Add Gene Modal Dialog");
    }
}
