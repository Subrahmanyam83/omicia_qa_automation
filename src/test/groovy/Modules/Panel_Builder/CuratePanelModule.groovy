package Modules.Panel_Builder

import geb.Module

/**
 * Created by E002183 on 4/25/2016.
 */
class CuratePanelModule extends Module{

    static content ={

        panelNameField                      {$('#panel_name')}
        panelDescriptionField               {$('#panel_description')}
        addGeneButton                       {$('#add-gene')}
        savePanelPropertiesButton           {$('#save-panel')}
        returnToPanelsButton                {$(".btn.btn-primary.navigate-back")}
        defaultFilterDropDown               {$(".filter-option.pull-left")}
        valueOfDefaultFilterDropDown        {String value-> $('.dropdown-menu.open li a .text',text:contains(value))}
        getNumberOfPanelGenes               {$("#panel-builder-regions tr")}
    }
}
