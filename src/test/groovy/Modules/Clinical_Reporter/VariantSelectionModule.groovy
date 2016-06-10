package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 5/2/2016.
 */
class VariantSelectionModule extends Module {

    static content = {

        /*Headers*/
        resetFilterButton                                               { $(".header-buttons .btn.reset-filters") }
        showHideColumnsButton                                           { $(".header-buttons .btn.change-columns") }
        runPhevorButton                                                 { $(".header-buttons .btn.run-phevor") }
        addSelectedVariantsButton                                       { $(".header-buttons .btn.btn-success") }
        variantInterpretationButton                                     { $(".header-buttons .btn.interpret-variants") }
        modalPopup                                                      { $(".modal-overflow") }
        modalCloseButton                                                { $(".modal-overflow .close")}
        HPOTermsText                                                    { $(".dl-horizontal dt",text:contains("HPO Terms"))}
        getHPOTermsValue                                                { HPOTermsText.parent().find("dd a").text()}
        numberOfCheckBoxesInShowHideColumns                             { $(".modal-overflow label.checkbox").size()}

        /*Filters*/
        filtersPane                                                     { $("div.span2.filters") }

        /*Variant Table*/
        variantsTable                                                   { $("table#variants tbody#variants") }
        variantTableColumnText                                          { $("#variants thead tr th") }
        numberOfItems                                                   { $(".total") }
        itemText                                                        { $(".total", text: "Items") }

        variantNameLink                                                 { String variantName, int index -> $(".paginator-row .gene-symbol .gene", text: variantName).getAt(index) }
        getPositionDBSNAP                                               { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.chromosome-int") }
        getChangeBasedOnVariant                                         { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.variant-change div") }
        getEffectBasedOnVariant                                         { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.consequence a") }
        getVAASTGeneRank                                                { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.vaast-gene-rank") }
        getVAASTRank                                                    { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.rank") }
        getPhevorGeneRank                                               { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.phevor-gene-rank") }
        getCheckBoxBasedOnVariant                                       { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.select input") }
        getInheritanceMode                                              { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.inheritance-mode") }
        getVVPCADD                                                      { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.vvp-cadd") }
        getVAASTVScore                                                  { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.variantScore") }
        getVAASTGScore                                                  { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.geneScore") }
        chromosomePositionBasedOnVariant                                { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.chromosome-int .position a",'target':'ucsc')}
        chromosomeDBSNPBasedOnVariant                                   { String variantName, int index -> variantNameLink(variantName, index).parent().parent().find("td.chromosome-int .position a",'target':'dbsnp')}
        isCheckboxCheckedBasedOnVariant                                 { String variantName, int index -> variantNameLink(variantName, index).parent().parent().filter(".warning").displayed }

        /*MISSENSE Effect Modal Popup*/
        transcriptsDiv                                                  { $(".variant-consequence-table-div")}
        consensusTable                                                  { $(".ccds-table-div")}
        nnSpliceTable                                                   { $(".nn-splice-table-div")}
        proteinDomainTable                                              { $(".protein-domain-table-div")}

        /*Phevor Tab*/
        phevorTextField                                                 { $(".hpo-terms-view div div input", placeholder: "Start typing here...") }
        dropDownValue                                                   { String value -> $(".tt-menu.tt-open .tt-dataset div", "data-value": value) }
        runButtonOnPhevor                                               { $(".btn.btn-primary.close-button", text: "Run") }
        runingPhevorProgressBar                                         { $(".loading-spinner .progress.active") }
        phevorProgressBar                                               { $(".modal-scrollable div div")[1] }

        /*Modal Popup*/
        addNoteTextField                                                { $(".modal-overflow #add-variants-note") }
        addVariantButtonInModalPopup                                    { $(".modal-overflow .modal-footer .close-button") }
        crossButtonOnModalDialog                                        { $(".modal-overflow .modal-header .close") }

    }
}
