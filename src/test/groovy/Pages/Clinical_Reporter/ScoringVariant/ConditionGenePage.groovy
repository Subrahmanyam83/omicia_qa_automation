package Pages.Clinical_Reporter.ScoringVariant

import Modules.Clinical_Reporter.ScoreVariantModule.ConditionGeneModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/12/2016.
 */
class ConditionGenePage extends BasePage {

    static at = {
        conditionGene.scoringVariantContainer
    }

    static content = {
        conditionGene { module ConditionGeneModule }
    }

    /*Header Methods*/

    def clickOnHeaderTab(String tabName) {
        waitFor { conditionGene.headerTab(tabName) }
        click(conditionGene.headerTab(tabName), "Header Tab: " + tabName)
    }

    def getActiveHeaderTab(String headerName) {
        return conditionGene.activeHeaderTab(headerName).isDisplayed()
    }

    /*Condition-Gene Methods*/

    def getActiveTabUnderConditionGeneTab(String tabName) {
        waitFor("shortwait") {conditionGene.activeConditionGeneTab(tabName)}
        return conditionGene.activeConditionGeneTab(tabName).isDisplayed()
    }

    def clickOnTabUnderConditionGenes(String tabName) {
        waitFor { conditionGene.tabNameUnderConditionGene(tabName) }
        click(conditionGene.tabNameUnderConditionGene(tabName), "Tab under Condition Name: " + tabName)
    }

    def verifyContentUnderConditionGeneTabs(String tabName) {
        switch (tabName) {
            case WORKSPACE_CONDITION_GENES:
                waitFor { conditionGene.workSpaceConditionGeneText }
                break;

            case CLINIVAR_OMIM:
                waitFor { conditionGene.ClinVarOmimTable }
                Assert.assertEquals(conditionGene.ClinVarOminColumnNames.text().replace("\n", " "), CLINVAR_OMIM_COLUMN_NAMES, "ClinVar and OMIM column names are different from: [" + CLINVAR_OMIM_COLUMN_NAMES + "]")
                break;

            case NLP_PHENOTYPE:
                waitFor { conditionGene.NLPPhenotypeTable }
                Assert.assertEquals(conditionGene.NLPPhenotypeColumnNames.text(), NLP_PHENOTYPE_COLUMN_NAMES, "NLP Phenotype Columns Names are not matching to: [" + NLP_PHENOTYPE_COLUMN_NAMES + "]")
                break;
        }
    }

/*CLIN VAR AND OMIM CONDITION GENES*/

    def clickOnColumnBasedOnConditionUnderClinVarAndOminTab(String conditionName, String columnName, int index = 0) {
        waitFor { conditionGene.columnNameBasedOnCondition(index, conditionName, columnName) }
        click(conditionGene.columnNameBasedOnCondition(index, conditionName, columnName), "Column Name: '" + columnName + "' Based on Condition: '" + conditionName + "'")
        if (columnName.equals(COPY_TO_WORKSPACE)) {
            waitFor { conditionGene.modalPopUp }
        }
    }

/*WORKSPACE CONDITION GENE METHODS*/

    def addNewConditionGene(){
        waitFor {conditionGene.addConditionGeneLink}
        click(conditionGene.addConditionGeneLink,"Add New Condition Gene Link")
    }

    def clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(String conditionName, String action, int index = 0) {
        switch (action) {

            case DELETE:
                int conditionGenes = getnumberOfWorkSpaceConditionRows()
                waitFor { conditionGene.actionsButtonBasedOnCondition(conditionName, index) }
                click(conditionGene.actionsButtonBasedOnCondition(conditionName, index), "Actions Button")
                waitFor { conditionGene.actionButtonValue(action, conditionName, index) }
                click(conditionGene.actionButtonValue(action, conditionName, index), "Actions Value: " + action)
                waitFor { conditionGene.deleteButtonOnModalPopup }
                click(conditionGene.deleteButtonOnModalPopup, "Delete Button on Modal Popup")
                waitFor { conditionGene.closeButton }
                click(conditionGene.closeButton, "Close Button")
                waitFor {getnumberOfWorkSpaceConditionRows() == (conditionGenes - 1)}
                break;

            case EDIT:
                waitFor { conditionGene.actionsButtonBasedOnCondition(conditionName, index) }
                click(conditionGene.actionsButtonBasedOnCondition(conditionName, index), "Actions Button")
                waitFor { conditionGene.actionButtonValue(action, conditionName, index) }
                click(conditionGene.actionButtonValue(action, conditionName, index), "Actions Value: " + action)
                waitFor { conditionGene.modalPopUp }
                break;
        }
    }

    def getInheritanceBasedOnCondition(String conditionName, int index = 0) {
        return conditionGene.inheritanceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getPrevalanceBasedOnCondition(String conditionName, int index = 0) {
        return conditionGene.prevalanceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getAgeOfOnsetBasedOnCondition(String conditionName, int index = 0) {
        return conditionGene.ageOfOnsetTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getPenetranceBasedOnCondition(String conditionName, int index = 0) {
        return conditionGene.penetranceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getNotesBasedOnCondition(String conditionName, boolean notesPresent = true, int index = 0) {
        if (notesPresent.equals(true)) {
            waitFor { conditionGene.notesViewBasedOnCondition(index, conditionName) }
            click(conditionGene.notesViewBasedOnCondition(index, conditionName), "View Link of Notes");
            waitFor { conditionGene.modalPopUp }
            String notesText = conditionGene.notesOnModalPopup
            click(conditionGene.closeButton, "Close Button On Modal Popup")
            return notesText
        } else if (notesPresent.equals(false)) {
            return conditionGene.notesTextBasedOnCondition(index, conditionName).text().trim()
        }
    }

    def getPMIDBasedOnCondition(String conditionName, int index = 0) {
        return conditionGene.PMIDTextBasedOnCondition(index, conditionName).text().trim()
    }

    def clickPMIDBasedOnCondition(String conditionName, int index = 0){
        waitFor {conditionGene.PMIDLinkBasedOnCondition(index, conditionName)}
        click(conditionGene.PMIDLinkBasedOnCondition(index, conditionName),"PMID link for the condition: "+conditionName)
    }

    def getnumberOfWorkSpaceConditionRows() {
        return conditionGene.numberOfWorkSpaceConditionRows
    }

    def editConditionGene(List list) {
        if (!list.get(0).equals(NONE)) {
            editCondition(list.get(0))
        }
        if(list.size()>1){
            if (!list.get(1).equals(NONE)) {
                editNote(list.get(1))
            }
        }
        if(list.size()>2){
            if (!list.get(2).equals(NONE)) {
                addPMID(list.get(2))
            }
        }
        if(list.size()>3){
            if (!list.get(3).equals(NONE)) {
                chooseInheritance(list.get(3))
            }
        }
        if(list.size()>4){
            if (!list.get(4).equals(NONE)) {
                editPrevalance(list.get(4))
            }
        }
        if(list.size()>5){
            if (!list.get(5).equals(NONE)) {
                choosePenetrance(list.get(5))
            }
        }
        if(list.size()>6){
            if (!list.get(6).equals(NONE)) {
                chooseAgeOfOnset(list.get(6))
            }
        }
    }

    /*Edit Condition Gene*/
    def editCondition(String conditionValue) {
        conditionGene.conditionTextBox.firstElement().clear();
        type(conditionGene.conditionTextBox, conditionValue, "Condition Value Text Box")
        waitFor {conditionGene.conditionSuggestionMenuValue(conditionValue)}
        click(conditionGene.conditionSuggestionMenuValue(conditionValue),"Condition Value from the Condition Drop Down: "+conditionValue)
    }

    def editNote(String note) {
        conditionGene.notesTextBox.firstElement().clear();
        type(conditionGene.notesTextBox, note, "Note Text Box")
    }

    def addPMID(String pmid) {
        type(conditionGene.pmidTextBox, pmid, "Condition Value Text Box")
        click(conditionGene.addPmidButton, "Add PMID Button")
        switch (pmid) {
            case "1":
                waitFor {
                    conditionGene.pmidTextSuccessAlert("Formate assay in body fluids: application in methanol poisoning.")
                }
                break;

            case "2":
                waitFor {
                    conditionGene.pmidTextSuccessAlert("Delineation of the intimate details of the backbone conformation of pyridine nucleotide")
                }
                break;
        }
    }

    def chooseInheritance(String inheritance) {
        click(conditionGene.inheritanceDropDown, "Inheritance Drop Down");
        click(conditionGene.dropDownValue(inheritance), "Inheritance Value")
    }

    def editPrevalance(String prevalance) {
        conditionGene.prevalanceTextBox.firstElement().clear();
        type(conditionGene.prevalanceTextBox, prevalance, "Prevalance Text Box")
    }

    def choosePenetrance(String penetrance) {
        click(conditionGene.penetranceDropDown, "Penetrance Drop Box")
        click(conditionGene.dropDownValue(penetrance), "Penetrance Drop Down Value: " + penetrance)
    }

    def chooseAgeOfOnset(String ageOfOnset) {
        click(conditionGene.ageOfOnsetDropDown, "Age OF Onset Drop Down");
        click(conditionGene.dropDownValue(ageOfOnset), "Drop Down Value of Age Of Onset: " + ageOfOnset);
    }

    def clickSaveOrCancel(String buttonName) {
        switch (buttonName) {
            case SAVE:
                click(conditionGene.saveButton, "Save Button");
                waitFor { conditionGene.closeButton }
                click(conditionGene.closeButton, "Close Button of Modal Popup")
                break;

            case CANCEL:
                click(conditionGene.cancelButton, "Cancel Button on Modal Popup");
                break;
        }
    }

    def waitTillYouAreInActiveTab(String tabName) {
        waitFor { conditionGene.activeConditionGeneTab(tabName) }
    }

    def clickOnCheckBoxBasedOnCondition(String conditionName, int index = 0) {
        waitFor { conditionGene.checkboxBasedCondition(index, conditionName) }
        click(conditionGene.checkboxBasedCondition(index, conditionName), "Checkbox based on the Condition")
    }

    def clickOnNewConditionGeneButton(){
        waitFor {conditionGene.newConditionGeneButton}
        click(conditionGene.newConditionGeneButton,"New Condition Gene Button")
        waitFor {conditionGene.modalPopUp}
    }
}
