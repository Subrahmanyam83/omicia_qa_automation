package Modules.Panel_Builder

import geb.Module

/**
 * Created by E002183 on 4/25/2016.
 */
class PanelBuilderModule extends Module{

    static content = {
        newPanelButton                                  { $("#page-content .btn.btn-success.new-panel.pull-right") }
        navPanelOfPanelBuilder                          { $("#app-container #page-content .nav.nav-tabs") }
        panelTable                                      { $(".active #panel-table #panel-builder-panels") }
        numberOfPanelItems                              { $(".active .pull-left.total ") }
        loadingPanelText                                { $("#loading", text: contains("Loading panels...")) }
        activePaginatorButton                           { $(".active div.btn-group.paginator.limit .btn") }
        paginatorDropDownValue                          { String value -> $(".active div.btn-group.paginator.limit.open li a span", text: value) }

        /*Panel Builder Home Page*/
        actionButton                                    { $('.btn.btn-mini.dropdown-toggle.action-button') }
        deletePanelUnderActionDropDpwn                  { $(".btn-group.pull-right.open .dropdown-menu .delete-panel") }
        deletePanelButtonOnDialog                       { $('.btn.btn-danger.close-button', text: "Delete") }
        closeButtonOnDialogWindow                       { $('.btn.btn-primary.close-button', text: "Close") }
        panelRowBasedOnPanelName                        { String panelName -> $("tr.paginator-row td a", text: panelName) }

        /*Create Panel*/
        createPanelModal                                { $(".modal.modal-overflow.in") }
        panelNameField                                  { $(".modal.modal-overflow.in #panel_name") }
        panelDescriptionField                           { $(".modal.modal-overflow.in #panel_description") }
        createPanelButton                               { $('.modal-footer .btn.btn-primary.close-button') }
        cancelPanelButton                               { $('.modal-footer .btn.cancel-button') }

        actionButtonBasedOnPanelName                    {String panelName -> $("#panel-builder-panels .paginator-row .view-panel",text:panelName).parent().parent().find("td a.action-button",text:contains("Actions"))}
        optionsOfActionButton                           {String option-> $(".btn-group.pull-right.open .dropdown-menu ."+option)}
        numberOfPanelsOnWorkSpacePanel                  {$("ul.nav.nav-tabs li a", text: "Workspace Panels").parent().parent().parent().find(".tab-content .workspace-panels .paginator-row").size()}
    }
}
