package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 5/13/2016.
 */
class ScoringVariantModule extends Module {

    static content = {

        /*Container*/
        scoringVariantContainer         {$('.expanded-variant-container') }
        modalPopUp                      {$('.modal.modal-overflow.in')}

        /*Headers*/
        headerTab                       {String headerTabName-> $('.expanded-variant-container .tabs-view div ul.nav-tabs li a',text:contains(headerTabName))}
        activeHeaderTab                 {String headerTabName-> $('.expanded-variant-container .tabs-view div ul.nav-tabs li.active a',text:contains(headerTabName))}
        numberOfCitationsOnTab          {$('.expanded-variant-container .tabs-view div ul.nav-tabs li a','data-tab-id':"citations").text()}

        /*Condition Gene*/
        activeConditionGeneTab          {String tabName-> $('li.active',text:contains(tabName))}
        tabNameUnderConditionGene       {String tabName-> $('li a',text:contains(tabName))}
        newConditionGeneButton          {$(".new-condition-gene",text:"+ New Condition-Gene")}
        workspaceConditionGeneTable     {$(".table.ws-condition-genes")}
        conditionNameInTable            {String conditionName-> workspaceConditionGeneTable.find("tbody tr td",text:contains(conditionName))}
        checkBoxBasedOnConditionName    {String conditionName,int index-> conditionNameInTable(conditionName).getAt(index).parent().find(".selected input")}

        /*Edit Condition Gene*/
        conditionTextBox                {modalPopUp.find("#condition")}
        notesTextBox                    {modalPopUp.find("#notes")}
        pmidTextBox                     {modalPopUp.find(".input-medium")}
        addPmidButton                   {modalPopUp.find(".btn.add-pmid")}
        pmidTextSuccessAlert            {modalPopUp.find(".abstract")}
        inheritanceDropDown             {modalPopUp.find(".filter-option")}
        dropDownValue                   {String value-> modalPopUp.find(".dropdown-menu.open ul li a span",text:contains(value))}
        prevalanceTextBox               {modalPopUp.find('name':'prevalence')}
        penetranceDropDown              {modalPopUp.find(".control-label",text:"Penetrance").find(".filter-option")}
        ageOfOnsetDorpDown              {modalPopUp.find(".control-label",text:"Age of Onset").find(".filter-option")}
        saveButton                      {modalPopUp.find(".cancel-button",text:"Cancel")}
        cancelButton                    {modalPopUp.find(".close-button",text:"Save")}
    }
}
