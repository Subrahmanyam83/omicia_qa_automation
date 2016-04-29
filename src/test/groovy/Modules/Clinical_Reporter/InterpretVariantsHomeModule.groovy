package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 4/26/2016.
 */
class InterpretVariantsHomeModule extends Module {

    static content = {

        geneNameLink { String variantName, int index -> $(".paginator-row .gene-symbol .gene", text: variantName).getAt(index) }

        /*Home Page*/
        variantTable { $("#variants") }
        getEffectBasedOnVariant { String variantName, int index -> geneNameLink(variantName, index).parent().parent().find("td.consequence a") }
        getClassBasedOnVariant { String variantName, int index -> geneNameLink(variantName, index).parent().parent().find("td.classification-sort-order span") }
        getStatusBasedOnVariant { String variantName, int index -> geneNameLink(variantName, index).parent().parent().find("td.status") }
        getReportSectionBasedOnVariant { String variantName, int index -> geneNameLink(variantName, index).parent().parent().find("td.to-report") }
        interpretVariantLinkBasedOnVariant { String variantName, int index -> geneNameLink(variantName, index).parent().parent().find("td.edit-variant a") }
        getVariantIdWithIndexBasedOnVariantName { String variantName, int index -> $(".paginator-row .gene-symbol .gene", text: variantName).getAt(index).parent().parent().getAttribute("id") }

        /*Headers*/
        reviewReportButton { $(".review-report") }
        showHideColumnButton { $(".btn.change-columns") }

        modalPopUp { $(".modal.modal-overflow.in") }

        /*Show Hide Columns*/
        checkBoxBasedOnColumnName { String columnName -> $(".row-fluid div label.checkbox", text: columnName).find(".checkbox") }
        ifCheckBoxIsChecked { String columnName -> $(".row-fluid div label.checkbox", text: columnName).find(".checkbox").getAttribute("checked") }
        updateShowHideColumnsButton { $(".btn.btn-primary.close-button", text: "Update") }
        cancelSHowHideCoulmnsButton { $(".btn.cancel-button", text: "Cancel") }


    }
}
