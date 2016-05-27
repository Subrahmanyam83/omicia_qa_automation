package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 4/27/2016.
 */
class VariantInterpretEditModule extends Module {

    static content = {

        /*Headers*/
        interpretVariantModalWindow                 { $('.modal.modal-overflow.in') }
        variantContent                              { $(".edit-variant-content") }
        tab                                         { String tabName -> $("#variant-tabs li a", text: contains(tabName)) }
        currentActiveTab                            { String activeTabName -> $("#variant-tabs li.active a", text: contains(activeTabName)) }

        /*Edit Variants*/
        variantClassificationDropDown               { $("span.filter-option.pull-left", text: "Please select a classification") }
        conditionTextField                          { $("#condition") }
        citationTextField                           { $("#new-pmid") }
        citationAddButton                           { $('.btn.add-pmid') }
        citationAddedConfirmation                   { $("div.abstract", title: contains("The importance of an innervated and intact antrum and pylorus in preventing postoperative duodenogastric reflux"))}
        description                                 { $(".description.input-block-level") }
        statusDropDown                              { $(".btn-group.status") }
        reportDropDown                              { $(".btn-group.to-report") }
        notesTextField                              { $(".lab-notes") }
        addNoteButton                               { $(".btn.btn-primary.add-note") }
        noteHeader                                  { $(".internal-note-header") }
        saveNoteButton                              { $("button.btn", text: "Save Note") }
        dropDownValue                               { String value -> $("div.btn-group.open .dropdown-menu.open ul li a span", text: contains(value)) }

        currentVariantClassificationDropDownValue   { $("div.classification .btn-default .filter-option.pull-left").text()}

        /*Footer Buttons*/
        saveButton                                  { $(".btn.btn-success.close-button", text: "Save") }
        closeButton                                 { $(".btn.cancel-button", text: "Close") }

        /*Variant Evidence*/
        copyToVariantInterpretationAndDescritpion   { $(".btn.btn-info.copy.pull-right.copy-clinvar.padding-both-sides")[0]}
    }
}
