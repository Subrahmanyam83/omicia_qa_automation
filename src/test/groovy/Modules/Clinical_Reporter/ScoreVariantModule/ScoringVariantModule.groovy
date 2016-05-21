package Modules.Clinical_Reporter.ScoreVariantModule

import geb.Module

/**
 * Created by E002183 on 5/19/2016.
 */
class ScoringVariantModule extends Module {

    static content = {

        /*Header*/
        classificationText { $(".scoring-criterion-padder .header strong") }
        alertText {
            $(".alert-text", text: "Variant has been associated with the \"MYASTHENIC SYNDROME, CONGENITAL, WITH PRE- AND POSTSYNAPTIC DEFECTS\" condition. You may now begin scoring the variant.")
        }

        /*Footer Tabs*/
        footerTab { String tabName -> $(".notes-and-description-tabs .nav-tabs li a", text: contains(tabName)) }
        variantDescriptionTextField { $('textarea.variant-description') }
        labNotesTextField { $(".active .lab-notes") }
        addNoteButton { $("button.add-note", text: "Add Note") }
        numberOfInternalNotesList { $(".internal-notes-list .internal-notes div.internal-note").size() }
        notesList { $(".internal-notes-list .internal-notes div.internal-note") }
        internalNoteText { int index -> $(".internal-notes-list .internal-notes div.internal-note").getAt(index).find(".internal-note-content").text() }

        historyContainer { $(".curated-variant-history") }
        numberOfHistoryRows { $(".curated-variant-history div.scoring-history-container").size() }
        scoringSummaryDefaultText { $(".well.variant-summary", text: contains("No ACMG Criteria answered.")) }

        /*Score Variant*/
        criterionCounter { $(".criterion-counter").text() }
        nextCriterionArrow { $(".next-criterion") }
        previousCriterionArrow { $(".previous-criterion") }
        radioButtonBasedOnName { String value -> $('label.radio', text: value).find("input") }
        linkName { String value -> $('.options a', text: value) }


    }
}
