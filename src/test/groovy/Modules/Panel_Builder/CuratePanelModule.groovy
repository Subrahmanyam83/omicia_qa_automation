package Modules.Panel_Builder

import geb.Module

/**
 * Created by E002183 on 4/25/2016.
 */
class CuratePanelModule extends Module{

    static content ={

        panelNameField                      { $('#panel_name') }
        panelDescriptionField               { $('#panel_description') }
        addGeneButton                       { $('#add-gene') }
        savePanelPropertiesButton           { $('#save-panel') }
        returnToPanelsButton                { $(".btn.btn-primary.navigate-back") }
        defaultFilterDropDown               { $(".filter-option.pull-left") }
        valueOfDefaultFilterDropDown        { String value-> $('.open .dropdown-menu.open li a .text',text:contains(value)) }
        getNumberOfPanelGenes               { $("#panel-builder-regions tr") }
        nameOfGenesAdded                    { $("#panel-builder-regions .gene-symbol") }
        modalPopup                          { $(".modal.modal-overflow.in") }

        /*Actions*/
        actionButtonBasedOnGeneName         { String geneName-> $("#panel-builder-regions .paginator-row td a",text: contains(geneName)).parent().parent().find("a.action-button")}
        valueUnderActionButtonBasedOnGene   { String actionName-> $("td div.btn-group.pull-right.open ul.dropdown-menu li a",text:contains(actionName))}

        /*Condition Gene Modal Popup*/
        workspaceConditionGenesTab          { $("li a",text: contains("Workspace Condition-Genes"))}
        tabName                             { String tabName-> $("li a",text:contains(tabName))}
    }
}
