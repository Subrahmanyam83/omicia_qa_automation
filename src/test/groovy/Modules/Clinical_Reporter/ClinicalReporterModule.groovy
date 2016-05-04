package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 4/26/2016.
 */
class ClinicalReporterModule extends Module {

    static content = {

        newReport { $(".btn.btn-success.dropdown-toggle.action-button") }
        newReportDropDownValue { String value -> $(".pull-right.dropdown-menu-skinny.dropdown-menu li a", text: value) }
        clinicalReportsTable { $("#report-table #clinical-reporter-reports") }
        modalPopup { $(".modal.modal-overflow.in") }
        loadingReportsProgress { $("#loading") }

        /*New Panel Report*/
        patientIdTextField { $("#accession-id") }
        choosePanelDropDown { $('#select-panel') }
        chooseAssayTypeDropDown { $('#new-assay-type') }
        chooseFilterDropDown { $("#select-filter") }
        includeCosmicCheckBox { $("#include-cosmic") }
        chooseProjectDropDown { $(".projects .btn.dropdown-toggle.btn-default") }

        chooseDropDownValueBasedOntheValue { String value -> $(".dropdown-menu.open li a span", text: contains(value)) }
        genomeFromGenomeList { String value -> $("#genome-list option", title: contains(value)) }
        saveButton { $(".btn.btn-primary.create-report", text: "Save") }

        /*Home Page*/
        reportTable { $("#report-table-div") }
        getStatusBasedonPatientId { String value -> $(".paginator-row td", text: value).parent().find("td")[8].text() }
        actionsButtonBasedOnPatientId { String patientId -> $(".paginator-row td", text: patientId).parent().find("td .action-button") }
        valueOnActionButon { String value -> $('.btn-group.pull-right.open .dropdown-menu li a', text: contains(value)) }

        /*Actions Button*/
        actionsButton { $('.btn.btn-mini.dropdown-toggle.action-button') }
        deleteReportOption { $('.dropdown-menu .delete-report') }
        deleteReportButtonOnDialog { $(".btn.btn-danger.close-button", text: "Delete Report") }

        noReportsFound(required: false) {
            $("div#nothing-found", text: contains("No reports founds: clear your filter or create a new report."))
        }
        deletingReportProgressBar { $(".modal-scrollable .loading-spinner") }
        numberOfClinicalReports { $(".paginator-row").size() }

        /*New Solo Report*/
        numberOfMembersInNewVAASTAnalysis { $("#select-genomes svg g g text") }
        tabBasedOnRelationship { String relationship -> numberOfMembersInNewVAASTAnalysis.filter(text: contains(relationship)) }
        geneBasedOnRelationship { String relationship -> $(".genome-option", text: relationship) }
        maleRadioButton { $("input", type: "radio", value: "male") }
        femaleRadioButton { $("input", type: "radio", value: "female") }
        selectButton { $(".btn.btn-primary.close-button", text: "Select") }


    }
}
