package Modules.PanelBuilder

import geb.Module

/**
 * Created by E002183 on 4/25/2016.
 */
class AddGenesToPanelModule extends Module{

    static content = {

        navTab                      {$(".nav.nav-tabs")}
        addGeneSymbolsField         {$("#add-gene-symbols")}
        tabName                     {String value-> $('a',"data-tab-id":value)}

        runPhevorTextField          {$(".tab-content .tab-pane.phevor.active .bootstrap-tagsinput input",type:"text","placeholder":"Start typing here...")}
        dropDownValue               {String value -> $(".typeahead.dropdown-menu li","data-value":value)}
        runPhevorButton             {$(".btn.btn-success.run-phevor")}
        runningPhevorProgressBar    {$(".progress.progress-striped.active .bar")}
        checkBoxBasedOnGene         {String value -> $(".phevor-results tr td input",type:"checkbox","data-gene":value)}

        addGene                     {String value -> $("button",text:value)}
        backButton                  {$(".pull-right .btn .back-text")}
        alertMessage                {$('.alert-success .alert-text',text:"Genes added to panel!")}




    }
}
