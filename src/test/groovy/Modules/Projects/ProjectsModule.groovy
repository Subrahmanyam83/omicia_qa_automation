package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsModule extends Module{

    static content = {

        /*Header*/
        projectNameFromHeader               { $("#genomes-form legend")}

        projectsPageTitleLegend             { $("form legend",text:contains("Project: "))}
        selectAllGenomesCheckBox            { $("#select-all")}
        actionsButton                       { $(".btn.dropdown-toggle.action-button",text:contains("Actions"))}
        actionsButtonOptions                { String name -> $("li a",text:contains(name))}
        deleteGenomesButton                 { $(".btn.btn-danger",text:contains("Delete Genome(s)"))}
        genomesDeletedConfirmationText      { $("#nothing-found",text:contains("No Genomes to display"))}
        deleteProjectButton                 { $(".btn.btn-danger.close-button",text:"Delete Project")}
        closeButtonOnDialogPopup            { $(".btn",text:contains("Close"))}
        getNumberOfGenes                    { $("#genome-list tr").size()}
        noGenomesToDisplay(required:false)  { $('#nothing-found',text:contains("No Genomes to display."))}
        availableVariantReports             { $("#genome-list tr td.report-table.report-type a", text: contains("Report")).size() }
        launchApp                           { $(".launch-app",text:contains("Launch App")) }
        dropDownMenuValue                   { String value -> $("ul.dropdown-menu li a", text: contains(value)) }
        numberOfVariantReports              { $("td.report-table.report-type").size() }
        variantReportType                   { String value -> $("td.report-type a", text: contains(value)) }

        selectColumnBasedOnGeneLabel(required: false) { String geneName, String columnName -> $("#genome-list .genome-detail span", text: contains(geneName)).parent().parent().parent().parent().find(columnName) }

        /*Report Type*/
        reportLink                          { String value, int index-> $("#genome-list tr td.report-type.report-table a", text: contains(value)).getAt(index) }
        deleteButtonAgainstReport           { String value, int index-> $("i",'data-report-type':value).getAt(index)}
        reportsUnderReportType              { $("#genome-list .report-table.report-type")}

        /*Modal Popup Locators*/
        deleteReportButton                  { $(".do-delete",text:contains("Delete Report"))}
        fadedBackgroundWindow               { $(".modal-backdrop.fade.in")}
    }
}
