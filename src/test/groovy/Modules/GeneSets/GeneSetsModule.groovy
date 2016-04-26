package Modules.GeneSets

import geb.Module

/**
 * Created by E002183 on 4/22/2016.
 */
class GeneSetsModule extends Module{

    static content ={

        /*Set Types*/
        geneSetTypes                {String geneType-> $("#bioset-tab a",text:contains(geneType))}

        /*New Gene Set Creation*/
        newGeneSetButton            {$(".new-gene-set")}
        newGeneSetNameField         {$('#bioset_name')}
        newGeneSetDescription       {$('#set_description')}
        newGeneSetTypeDropDown      {$("button","data-id":'set_type')}
        newGeneSetTypeValue         {String value-> $('.dropdown-menu.open .text',text:contains(value))}
        newGeneSetSaveButton        {$(".btn.btn-primary.close-button",text:"Save Gene Set")}
        newGeneSetCancelButton      {$(".btn.cancel-button",text:"Cancel")}
        newGeneSetModalDialog       {$('.modal-scrollable')}

        /*Action Button*/
        actionButtonBasedOneneSet   {String geneName-> $("#blacklist_tbody tr td a",text:geneName).parent().parent().find(".action-button")}
        performActionOnActionButton {String action-> $(".btn-group.pull-right.open ."+action)}

        /*Add Gene Modal*/
        addGenesTextField           {$(".validating #genes")}
        addGenesNextButton          {$(".modal-footer .btn.btn-success",text:"Next >")}
        addGenesCancelButton        {$(".btn.cancel-button",text:"Cancel")}
        addGenesSaveButton          {$(".btn.btn-primary.close-button",text:"Save Changes")}
        addGeneModalDialog          {$(".modal.modal-overflow.in")}


    }
}
