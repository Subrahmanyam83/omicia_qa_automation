package Modules.Clinical_Reporter.ScoreVariantModule

import geb.Module

/**
 * Created by E002183 on 5/20/2016.
 */
class CitationsModule extends Module {

    static content = {
        addNewButtonBasedOnStudyName { String value -> $(".citations-container." + value + " .add-citation") }
        noStudiesRow { String value -> $(".citations-container." + value + " tbody.no-studies") }
        studiesListRow { String value -> $(".citations-container." + value + " tbody.studies-list") }
        citationsCountOnTab { $(".citations-count").text().replace("(", "").replace(")", "").toInteger() }

        /*Cosegregation Modal Popup*/
        pubMedIDTextField { $("#pubmed-id") }
        pubMedCitationText { $(".pubmed-citation") }
        phenoType { $("#phenotype") }
        pedigreeReference { $("#pedigree-reference") }
        affectedWithTextField { $("#affected-with") }
        affectedWithout { $("#affected-without") }
        unaffectedWithTextField { $("#unaffected-with") }
        unaffectedWithoutTextField { $("#unaffected-without") }
        assessmentTextField { $("#assessment") }
        addStudyButton { $(".close-button", text: "Add Study") }
        closeButton { $(".close-button", text: "Close") }
    }
}
