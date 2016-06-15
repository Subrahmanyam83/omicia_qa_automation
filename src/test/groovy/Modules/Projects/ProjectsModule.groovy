package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsModule extends Module{

    static content = {
        projectsPageTitleLegend                          { $("form legend",text:contains("Project: ")) }
        selectAllGenomesCheckBox                         { $("#select-all") }
        actionsButton                                    { $(".btn.dropdown-toggle.action-button",text:contains("Actions")) }
        actionsButtonOptions                             { String name -> $("li a",text:contains(name)) }
        deleteGenomesButton                              { $(".btn.btn-danger",text:contains("Delete Genome(s)")) }
        genomesDeletedConfirmationText                   { $("#nothing-found",text:contains("No Genomes to display")) }
        deleteProjectButton                              { $(".btn.btn-danger.close-button",text:"Delete Project") }
        getNumberOfGenes                                 { $("#genome-list tr").size() }
        noGenomesToDisplay(required:false)               { $('#nothing-found') }
        availableVariantReports                          { $("#genome-list tr td.report-table.report-type a", text: contains("Report")).size() }
        launchApp                                        { $(".launch-app") }
        dropDownMenuValue                                { String value -> $("ul.dropdown-menu li a", text: contains(value)) }
        numberOfVariantReports                           { $("td.report-table.report-type").size() }
        variantReportType                                { String value -> $("td.report-type a", text: contains(value)) }

        selectColumnBasedOnGeneLabel(required: false)    { String geneName, String columnName -> $("#genome-list .genome-detail span", text: contains(geneName)).parent().parent().parent().parent().find(columnName) }

        vaastSoloReportLink                              { $("#genome-list tr td.report-type.report-table a", text: contains("VAAST Solo Report")) }
        vaastTrioReportLInk                              { $("#genome-list tr td.report-type.report-table a", text: contains("VAAST Trio Report")) }
        vaastQuadReportLInk                              { $("#genome-list tr td.report-type.report-table a", text: contains("VAAST Quad Report")) }
        flexTrioReportLInk                               { $("#genome-list tr td.report-type.report-table a", text: contains("Flex Trio Report")) }
        flexQuadReportLInk                               { $("#genome-list tr td.report-type.report-table a", text: contains("Flex Quad Report")) }
        getProjectHeaderTitle                            { $("#genomes-form legend").text() }
    }
}
