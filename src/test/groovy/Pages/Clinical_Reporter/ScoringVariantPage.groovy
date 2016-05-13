package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ScoringVariantModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 5/12/2016.
 */
class ScoringVariantPage extends BasePage {

    static at = {
        scoringVariant.scoringVariantContainer
    }

    static content = {
        scoringVariant { module ScoringVariantModule }
    }
}
