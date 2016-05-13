package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 5/13/2016.
 */
class ScoringVariantModule extends Module {

    static content = {
        scoringVariantContainer { $('.expanded-variant-container') }
    }
}
