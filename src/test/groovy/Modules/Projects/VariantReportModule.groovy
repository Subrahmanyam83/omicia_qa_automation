package Modules.Projects

import geb.Module

/**
 * Created by E002183 on 5/6/2016.
 */
class VariantReportModule extends Module {

    static content = {

        reportsPageVariantsTable    { $("div.variants-table table#variants").filter('style':"opacity: 1;").find("tbody#report-variants") }
        header                      { $(".header-div legend") }
        headerButton                { String buttonName -> $("div.header-buttons ").find("button", text: buttonName) }

        numberOfGenes               { $("#report-variants .paginator-row").size()}
        columnNameBasedOnGene       { String geneName, String columnName-> $('#report-variants .gene',text:contains(geneName)).parent().parent().find(columnName)}
        dialogBox                   { $("#modal .modal-body")}
        closeButtonOfDialogBox      { $('.btn.btn-primary.close-button')}
        reportTableAppear           { $("li.pull-right .ajax-loading-fade.hide").filter('style':"display: none;")}

        /*VAAST Viewer Page*/
        yAxisLabel                  {$(".plot-box #vaast_plot_yaxis")}
        xAxisLabel                  {$(".plot-box #vaast_plot_xaxis")}
        vaastPlotOverview           {$(".plot-box #vaast_plot_overview")}
    }
}
