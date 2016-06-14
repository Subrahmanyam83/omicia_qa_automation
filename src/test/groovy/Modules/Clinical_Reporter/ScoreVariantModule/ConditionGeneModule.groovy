package Modules.Clinical_Reporter.ScoreVariantModule

import geb.Module

/**
 * Created by E002183 on 5/13/2016.
 */
class ConditionGeneModule extends Module {

    static content = {

        /*Container*/
        scoringVariantContainer         { $('.expanded-variant-container') }
        modalPopUp                      { $('.modal.modal-overflow.in')}

        /*Headers*/
        headerTab                       { String headerTabName-> $('.expanded-variant-container .tabs-view div ul.nav-tabs li a',text:contains(headerTabName))}
        activeHeaderTab                 { String headerTabName-> $('.expanded-variant-container .tabs-view div ul.nav-tabs li.active a',text:contains(headerTabName))}
        numberOfCitationsOnTab          { $('.expanded-variant-container .tabs-view div ul.nav-tabs li a','data-tab-id':"citations").text()}

        /*Condition Gene*/
        activeConditionGeneTab          { String tabName-> $('li.active',text:contains(tabName))}
        tabNameUnderConditionGene       { String tabName-> $('li a',text:contains(tabName))}
        workspaceConditionGeneTable     { $(".table.ws-condition-genes")}
        conditionNameInTable            { String conditionName-> workspaceConditionGeneTable.find("tbody tr td",text:contains(conditionName))}
        checkBoxBasedOnConditionName    { String conditionName,int index-> conditionNameInTable(conditionName).getAt(index).parent().find(".selected input")}

        NLPPhenotypeTable               { $("div.nlp-phenotype-mapper.active") }
        NLPPhenotypeColumnNames         { $("table.nlp thead tr") }

        ClinVarOmimTable                { $("div.clinvar-and-omim-condition-genes.active") }
        ClinVarOminColumnNames          { $("table.clinvar-omim thead tr") }
        columnNameBasedOnCondition      { int index, String conditionName, String columnName -> $("table.clinvar-omim tbody tr").getAt(index).find("td.condition", text: contains(conditionName)).parent().find("td a", text: contains(columnName)) }

        workSpaceConditionGeneText      { $("div.no-cgs-alert", text: contains("There are no conditions associated with this gene in this workspace yet. Curate one from the ClinVar and OMIM Condition-Genes tab or add one.")) }
        numberOfWorkSpaceConditionRows  { $(".ws-condition-genes tbody tr").size() }
        actionsButtonBasedOnCondition   { String conditionName, int index -> workspaceConditionGeneTable.find("tbody tr td.condition", text: contains(conditionName)).getAt(index).parent().find("td.actions a.btn") }
        actionButtonValue               { String actionName, String conditionName, int index -> actionsButtonBasedOnCondition(conditionName, index).parent().find(text: actionName) }
        deleteButtonOnModalPopup        { $(".close-button", text: "Delete") }

        /*Workspace Condition Gene*/
        conditionUnderWorkSpaceConditionGeneTab { int index, String conditionName -> $("table.ws-condition-genes tbody tr").getAt(index).find("td.condition", text: conditionName) }
        inheritanceTextBasedOnCondition { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".inheritance") }
        prevalanceTextBasedOnCondition  { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".prevalence") }
        ageOfOnsetTextBasedOnCondition  { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".age-of-onset") }
        penetranceTextBasedOnCondition  { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".penetrance") }
        notesViewBasedOnCondition       { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".notes a") }
        notesTextBasedOnCondition       { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".notes") }
        notesOnModalPopup               { $(".modal.modal-overflow.in .modal-body").text().trim() }
        PMIDTextBasedOnCondition        { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".pmids") }
        PMIDLinkBasedOnCondition        { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".pmids a") }
        checkboxBasedCondition          { int index, String conditionName -> conditionUnderWorkSpaceConditionGeneTab(index, conditionName).parent().find(".selected .select-condition-gene") }
        addConditionGeneLink            { $("a.new-condition-gene",text:contains("add one"))}
        newConditionGeneButton          { $("a.new-condition-gene",text:contains("+ New Condition-Gene"))}



        /*Edit Condition Gene*/
        conditionTextBox                { modalPopUp.find("#condition")}
        conditionSuggestionMenu         { $(".tt-menu.tt-open") }
        conditionSuggestionMenuValue    { String value-> conditionSuggestionMenu.find(".tt-dataset .tt-suggestion .tt-highlight",text:value)}
        notesTextBox                    { modalPopUp.find("#notes")}
        pmidTextBox                     { modalPopUp.find(".input-medium")}
        addPmidButton                   { modalPopUp.find(".btn.add-pmid")}
        pmidTextSuccessAlert            { String message -> modalPopUp.find(".abstract", text: contains(message)) }
        inheritanceDropDown             { modalPopUp.find(".filter-option")}
        dropDownValue                   { String value-> modalPopUp.find(".dropdown-menu.open ul li a span",text:contains(value))}
        prevalanceTextBox               { modalPopUp.find('name':'prevalence')}
        penetranceDropDown              { modalPopUp.find(".control-label", text: "Penetrance").parent().find(".filter-option") }
        ageOfOnsetDropDown              { modalPopUp.find(".control-label", text: "Age of Onset").parent().find(".filter-option") }
        cancelButton                    { modalPopUp.find(".cancel-button", text: "Cancel") }
        saveButton                      { modalPopUp.find(".close-button", text: "Save") }
        closeButton                     { modalPopUp.find(".close-button", text: "Close") }
    }
}
