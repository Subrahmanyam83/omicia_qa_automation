package Modules.Clinical_Reporter.ScoreVariantModule

import geb.Module

/**
 * Created by E002183 on 5/19/2016.
 */
class ScoringVariantModule extends Module {

    static content = {

        /*Header*/
        scoringHeaderText               { $(".scoring-criterion-padder .header")}
        inferredClassificationText      { $(".scoring-criterion-padder .inferred_classification") }
        assignedClassificationText      { $(".scoring-criterion-padder .assigned_classification") }
        alertText {
            $(".alert-text", text: "Variant has been associated with the \"MYASTHENIC SYNDROME, CONGENITAL, WITH PRE- AND POSTSYNAPTIC DEFECTS\" condition. You may now begin scoring the variant.")
        }

        /*Footer Tabs*/
        footerTab                       { String tabName -> $(".notes-and-description-tabs .nav-tabs li a", text: contains(tabName)) }
        variantDescriptionTextField     { $('textarea.variant-description') }
        labNotesTextField               { $(".active .lab-notes") }
        addNoteButton                   { $("button.add-note", text: "Add Note") }
        setClassificationButton         { $(".set-classification")}
        numberOfInternalNotesList       { $(".internal-notes-list .internal-notes div.internal-note").size() }
        notesList                       { $(".internal-notes-list .internal-notes div.internal-note") }
        internalNoteText                { int index -> $(".internal-notes-list .internal-notes div.internal-note").getAt(index).find(".internal-note-content").text() }
        internalNoteBasedOnNote         { String note-> $(".internal-note .internal-note-content",text:contains(note))}

        historyContainer                { $(".curated-variant-history") }
        numberOfHistoryRows             { $(".curated-variant-history div.scoring-history-container").size() }
        scoringSummaryDefaultText       { $(".well.variant-summary", text: contains("No ACMG Criteria answered.")) }

        /*Score Variant*/
        criterionCounter                { $(".criterion-counter") }
        nextCriterionArrow              { $(".next-criterion") }
        previousCriterionArrow          { $(".previous-criterion") }
        radioButtonBasedOnName          { String value -> $('label.radio', text: value).find("input") }
        optionsLinkName                 { String value -> $('.options a', text: value) }
        scoringProgressText             { $("p.scoring-progress")}
        completedScroingProgressText    { $(".scoring-progress").filter('style':"color: green; font-weight: bold;")}

        /*Set Classification Popup*/
        setClassificationButtonOnPopup  {$(".close-button",text:"Set Classification")}
        closeButton                     {$(".close-button",text:"Close")}


    }
}
