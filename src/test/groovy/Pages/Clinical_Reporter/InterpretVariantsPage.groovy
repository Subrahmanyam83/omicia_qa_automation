package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.InterpretVariantsModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/26/2016.
 */
class InterpretVariantsPage extends BasePage {

    static at = {
        interpretVariants.variantTable.displayed
    }

    static content = {
        interpretVariants { module InterpretVariantsModule }
    }

    def getEffectBasedOnGene(String geneName) {
        waitFor { interpretVariants.getEffectBasedOnGene(geneName).displayed }
        return interpretVariants.getEffectBasedOnGene(geneName).text().trim()
    }
}
