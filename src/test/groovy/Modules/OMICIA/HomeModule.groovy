package Modules.OMICIA

import geb.Module

/**
 * Created by in02183 on 4/1/2016.
 */
class HomeModule extends Module{

    static content = {

        uploadGenomesButton         {$("h3",text:"Upload Genomes")}
        projectsButton              {$("h3",text:"Projects")}
        clinicalReporterButton      {$("h3",text:"Clinical Reporter")}
        filteringProtocolsButton    {$("h3",text:"Filtering Protocols")}
    }
}
