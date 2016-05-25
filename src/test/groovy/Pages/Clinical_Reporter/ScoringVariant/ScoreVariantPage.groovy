package Pages.Clinical_Reporter.ScoringVariant

import Modules.Clinical_Reporter.ScoreVariantModule.ConditionGeneModule
import Modules.Clinical_Reporter.ScoreVariantModule.ScoringVariantModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/19/2016.
 */
class ScoreVariantPage extends BasePage {

    static at = {
        conditionGene.activeHeaderTab("Score Variant")
    }

    static content = {
        scoringVariant { module ScoringVariantModule }
        conditionGene { module ConditionGeneModule }
    }

    def waitTillAlertDisappears() {
        waitFor { scoringVariant.alertText }
        waitTillElementIsNotPresent(scoringVariant.alertText, "Alert Success Message for Condition Gene addition")
    }

    def clickOnHeaderTab(String tabName) {
        waitFor { conditionGene.headerTab(tabName) }
        click(conditionGene.headerTab(tabName), "Header Tab: " + tabName)
    }

    def getInferredClassification() {
        waitFor { scoringVariant.inferredClassificationText }
        return scoringVariant.inferredClassificationText.text()
    }

    def getScoringHeader(){
        waitFor {scoringVariant.scoringHeaderText}
        return scoringVariant.scoringHeaderText.text().trim()
    }

    def getAssignedClassification() {
        waitFor { scoringVariant.assignedClassificationText }
        return scoringVariant.assignedClassificationText.text()
    }

    def clickOnTab(String tabName) {
        waitFor { scoringVariant.footerTab(tabName) }
        click(scoringVariant.footerTab(tabName), "Footer Tab:: " + tabName)
    }

    def addVariantDescription() {
        clickOnTab(VARIANT_DESCRIPTION)
        type(scoringVariant.variantDescriptionTextField, VARIANT_DESCRIPTION, "Variant Description Text Field")
    }

    def addInternalNote() {
        clickOnTab(INTERNAL_NOTES)
        int old_notes = scoringVariant.numberOfInternalNotesList
        type(scoringVariant.labNotesTextField, INTERNAL_NOTES, "Internal Notes Text Field")
        click(scoringVariant.addNoteButton, "Add Note Button")
        waitFor { scoringVariant.notesList }
        Assert.assertEquals(scoringVariant.numberOfInternalNotesList, old_notes + 1, "Internal Note is not added to the list in Score Variant Tab.")
    }

    def setClassification(String classification = "", boolean changeClassification = false){
        if(changeClassification == false){
            waitFor {scoringVariant.setClassificationButton}
            scrollToCenter(scoringVariant.setClassificationButton)
            click(scoringVariant.setClassificationButton,"Set Classification Button")
            waitFor {scoringVariant.setClassificationButtonOnPopup}
            click(scoringVariant.setClassificationButtonOnPopup,"Set Classification Button on Modal Popup")
            waitFor {scoringVariant.closeButton}
            click(scoringVariant.closeButton,"Close Button on Modal Popup")
        }
        else{

        }
    }

    def verifyTextOfNote(int index = 0) {
        return scoringVariant.internalNoteText(index)
    }

    def verifyNumberOfHistoryRows() {
        waitFor { scoringVariant.historyContainer }
        return scoringVariant.numberOfHistoryRows
    }

    def verifyScoringSummaryDefaultText() {
        waitFor { scoringVariant.scoringSummaryDefaultText }
    }

    def clickPreviousCounter() {
        waitFor { scoringVariant.previousCriterionArrow }
        click(scoringVariant.previousCriterionArrow, "Previous Criterion Counter")
    }

    def clickNextCounter() {
        waitFor { scoringVariant.nextCriterionArrow }
        click(scoringVariant.nextCriterionArrow, "Previous Criterion Counter")
    }

    def startScoring(List list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(SKIP_CRITERIA) || list.get(i).equals(CLEAR_SELECTION)){
                String currentCiterionText = getCurrentCriterion()
                click(scoringVariant.optionsLinkName(list.get(i)),"Link: "+list.get(i))
                waitFor {!scoringVariant.criterionCounter.text().trim().equals(currentCiterionText)}
            }
            else{
                String currentCiterionText = getCurrentCriterion()
                click(scoringVariant.radioButtonBasedOnName(list.get(i)),"Link: "+list.get(i))
                Thread.sleep(500L)
                if(!(i.equals(list.size() - 1))){
                    waitFor {!scoringVariant.criterionCounter.text().trim().equals(currentCiterionText)}
                }
            }
        }
    }

    def getCurrentCriterion(){
        return scoringVariant.criterionCounter.text().trim()
    }

    def getScoringProgressText(){
        return scoringVariant.scoringProgressText.text().trim()
    }


}
