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
        waitFor { scoringVariant.classificationText }
        return scoringVariant.classificationText.text()
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

    def getCriterionCounterText() {
        return scoringVariant.criterionCounter
    }

    def clickPreviousCounter() {
        waitFor { scoringVariant.previousCriterionArrow }
        click(scoringVariant.previousCriterionArrow, "Previous Criterion Counter")
    }

    def clickNextCounter() {
        waitFor { scoringVariant.nextCriterionArrow }
        click(scoringVariant.nextCriterionArrow, "Previous Criterion Counter")
    }


}
