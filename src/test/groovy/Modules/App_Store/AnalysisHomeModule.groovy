package Modules.App_Store

import geb.Module

/**
 * Created by E002183 on 5/5/2016.
 */
class AnalysisHomeModule extends Module {

    static content = {
        runButton            { $("#submit") }
        selectGenesTab       { $("#select-genomes") }
    }
}
