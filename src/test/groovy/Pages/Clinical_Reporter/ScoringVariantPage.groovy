package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ScoringVariantModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 5/12/2016.
 */
class ScoringVariantPage extends BasePage {

    static at = {
        scoringVariant.scoringVariantContainer
    }

    static content = {
        scoringVariant { module ScoringVariantModule }
    }

    /*Header Methods*/
    def clickOnHeaderTab(String tabName){
        waitFor {scoringVariant.headerTab(tabName)}
        click(scoringVariant.headerTab(tabName))
    }

    def getActiveHeaderTab(String headerName){
        return scoringVariant.activeHeaderTab(headerName).isDisplayed
    }

    /*Condition-Gene Methods*/
    def getActiveConditionGeneTab(String tabName){
        return scoringVariant.activeConditionGeneTab(tabName).isDisplayed
    }

    def clickOnTabUnderConditionGenes(String tabName){
        waitFor {scoringVariant.tabNameUnderConditionGene}
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

            def addPMID(int pmid){
                type(scoringVariant.pmidTextBox,pmid,"Condition Value Text Box")
            }

            def chooseInheritance(String inheritance){
                click(scoringVariant.inheritanceDropDown,"Inheritance Drop Down");
                click(scoringVariant.dropDownValue(inheritance),"Inheritance Value")
            }

            def editPrevalance(String prevalance){
                scoringVariant.conditionTextBox.firstElement().clear();
                type(scoringVariant.conditionTextBox,conditionValue,"Condition Value Text Box")
            }

            def choosePenetrance(String penetrance){
                
            }

            def chooseAgeOfOnset(String ageOfOnset){

            }

            def clickSaveOrCancel(String buttonName){

            }


}
