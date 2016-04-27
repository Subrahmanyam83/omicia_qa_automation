package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 4/26/2016.
 */
class InterpretVariantsModule extends Module {

    static content = {

        variantTable { $("#variants") }
        getEffectBasedOnGene { String geneName -> $(".paginator-row td a", text: geneName).parent().parent().find("td.consequence a") }

    }
}
