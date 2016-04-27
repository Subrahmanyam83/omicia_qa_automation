package Modules.Projects

import geb.Module

/**
 * Created by E002183 on 4/22/2016.
 */
class VariantReportModule extends Module{

    static content = {

        numberOfGenes           {$("#report-variants .paginator-row").size()}
        columnNameBasedOnGene   {String geneName, String columnName-> $('#report-variants .gene',text:contains(geneName)).parent().parent().find(columnName)}
        dialogBox               {$("#modal .modal-body")}
        closeButtonOfDialogBox  {$('.btn.btn-primary.close-button')}
    }
}
