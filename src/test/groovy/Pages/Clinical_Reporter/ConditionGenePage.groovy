package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ConditionGeneModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/12/2016.
 */
class ConditionGenePage extends BasePage {

    static at = {
        scoringVariant.scoringVariantContainer
    }

    static content = {
        scoringVariant { module ConditionGeneModule }
    }

    /*Header Methods*/
    def clickOnHeaderTab(String tabName){
        waitFor {scoringVariant.headerTab(tabName)}
        click(scoringVariant.headerTab(tabName))
    }

    def getActiveHeaderTab(String headerName){
        return scoringVariant.activeHeaderTab(headerName).isDisplayed()
    }

    /*Condition-Gene Methods*/

    def getActiveTabUnderConditionGeneTab(String tabName) {
        return scoringVariant.activeConditionGeneTab(tabName).isDisplayed()
    }

    def clickOnTabUnderConditionGenes(String tabName){
        waitFor { scoringVariant.tabNameUnderConditionGene(tabName) }
        click(scoringVariant.tabNameUnderConditionGene(tabName))
    }

    def verifyContentUnderConditionGeneTabs(String tabName) {
        switch (tabName) {
            case CLINIVAR_OMIM:
                waitFor { scoringVariant.workSpaceConditionGeneText }
                break;

            case WORKSPACE_CONDITION_GENES:
                waitFor { scoringVariant.ClinVarOmimTable }
                Assert.assertEquals(scoringVariant.ClinVarOminColumnNames.text().replace("\n", " "), CLINVAR_OMIM_COLUMN_NAMES, "ClinVar and OMIM column names are different from: [" + CLINVAR_OMIM_COLUMN_NAMES + "]")
                break;

            case NLP_PHENOTYPE:
                waitFor { scoringVariant.NLPPhenotypeTable }
                Assert.assertEquals(scoringVariant.NLPPhenotypeColumnNames.text(), NLP_PHENOTYPE_COLUMN_NAMES, "NLP Phenotype Columns Names are not matching to: [" + NLP_PHENOTYPE_COLUMN_NAMES + "]")
                break;
        }
    }

/*CLIN VAR AND OMIM CONDITION GENES*/

    def clickOnColumnBasedOnConditionUnderClinVarAndOminTab(String conditionName, String columnName, int index = 0) {
        waitFor { scoringVariant.columnNameBasedOnCondition(index, conditionName, columnName) }
        click(scoringVariant.columnNameBasedOnCondition(index, conditionName, columnName), "Column Name: '" + columnName + "' Based on Condition: '" + conditionName + "'")
        if (columnName.equals(COPY_TO_WORKSPACE)) {
            waitFor { scoringVariant.modalPopUp }
        }
    }

/*WORKSPACE CONDITION GENE METHODS*/

    def clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(String conditionName, String action, int index = 0) {
        switch (action) {

            case DELETE:
                waitFor { scoringVariant.actionsButtonBasedOnCondition(conditionName, index) }
                click(scoringVariant.actionsButtonBasedOnCondition(conditionName, index), "Actions Button")
                waitFor { scoringVariant.actionButtonValue(action, conditionName, index) }
                click(scoringVariant.actionButtonValue(action, conditionName, index), "Actions Value: " + action)
                waitFor { scoringVariant.deleteButtonOnModalPopup }
                click(scoringVariant.deleteButtonOnModalPopup, "Delete Button on Modal Popup")
                waitFor { scoringVariant.closeButton }
                click(scoringVariant.closeButton, "Close Button")
                break;

            case EDIT:
                waitFor { scoringVariant.actionsButtonBasedOnCondition(conditionName, index) }
                click(scoringVariant.actionsButtonBasedOnCondition(conditionName, index), "Actions Button")
                waitFor { scoringVariant.actionButtonValue(action, conditionName, index) }
                click(scoringVariant.actionButtonValue(action, conditionName, index), "Actions Value: " + action)
                waitFor { scoringVariant.modalPopUp }
                break;
        }
    }

    def getInheritanceBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.inheritanceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getPrevalanceBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.prevalanceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getAgeOfOnsetBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.ageOfOnsetTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getPenetranceBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.penetranceTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getNotesBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.notesBasedOnCondition(index, conditionName).text().trim()
    }

    def getPMIDBasedOnCondition(String conditionName, int index = 0) {
        return scoringVariant.PMIDTextBasedOnCondition(index, conditionName).text().trim()
    }

    def getnumberOfWorkSpaceConditionRows() {
        return scoringVariant.numberOfWorkSpaceConditionRows
    }

    def editCondition(List list) {
        if (!list.get(0).equals(NONE)) {
            editCondition(list.get(0))
        }
        if (!list.get(1).equals(NONE)) {
            editNote(list.get(1))
        }
        if (!list.get(2).equals(NONE)) {
            addPMID(list.get(2))
        }
        if (!list.get(3).equals(NONE)) {
            chooseInheritance(list.get(3))
        }
        if (!list.get(4).equals(NONE)) {
            editPrevalance(list.get(4))
        }
        if (!list.get(5).equals(NONE)) {
            choosePenetrance(list.get(5))
        }
        if (!list.get(6).equals(NONE)) {
            chooseAgeOfOnset(list.get(6))
        }
    }

    /*Edit Condition Gene*/
            def editCondition(String conditionValue){
                scoringVariant.conditionTextBox.firstElement().clear();
                type(scoringVariant.conditionTextBox,conditionValue,"Condition Value Text Box")
            }

            def editNote(String note){
                scoringVariant.notesTextBox.firstElement().clear();
                type(scoringVariant.notesTextBox,note,"Note Text Box")
            }

    def addPMID(String pmid) {
                type(scoringVariant.pmidTextBox,pmid,"Condition Value Text Box")
            }

            def chooseInheritance(String inheritance){
                click(scoringVariant.inheritanceDropDown,"Inheritance Drop Down");
                click(scoringVariant.dropDownValue(inheritance),"Inheritance Value")
            }

            def editPrevalance(String prevalance){
                scoringVariant.prevalanceTextBox.firstElement().clear();
                type(scoringVariant.prevalanceTextBox, prevalance, "Prevalance Text Box")
            }

            def choosePenetrance(String penetrance){
                click(scoringVariant.penetranceDropDown, "Penetrance Drop Box")
                click(scoringVariant.dropDownValue(penetrance), "Penetrance Drop Down Value: " + penetrance)
            }

            def chooseAgeOfOnset(String ageOfOnset){
                click(scoringVariant.ageOfOnsetDropDown, "Age OF Onset Drop Down");
                click(scoringVariant.dropDownValue(ageOfOnset), "Drop Down Value of Age Of Onset: " + ageOfOnset);
            }

            def clickSaveOrCancel(String buttonName){
                switch (buttonName) {
                    case SAVE:
                        click(scoringVariant.saveButton, "Save Button");
                        waitFor { scoringVariant.closeButton }
                        click(scoringVariant.closeButton, "Close Button of Modal Popup")
                        break;

                    case CANCEL:
                        click(scoringVariant.cancelButton);
                        break;
                }
            }


}
