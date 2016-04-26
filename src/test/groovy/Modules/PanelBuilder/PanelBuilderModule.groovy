package Modules.PanelBuilder

import geb.Module

/**
 * Created by E002183 on 4/25/2016.
 */
class PanelBuilderModule extends Module{

    static content = {
        newPanelButton                                  {$("#page-content .btn.btn-success.new-panel.pull-right")}
        navPanelOfPanelBuilder                          {$("#app-container #page-content .nav.nav-tabs")}

        /*Create Panel*/
        createPanelModal                                {$(".modal.modal-overflow.in")}
        panelNameField                                  {$(".modal.modal-overflow.in #panel_name")}
        panelDescriptionField                           {$(".modal.modal-overflow.in #panel_description")}
        createPanelButton                               {$('.modal-footer .btn.btn-primary.close-button')}
        cancelPanelButton                               {$('.modal-footer .btn.cancel-button')}

        actionButtonBasedOnPanelName                    {String panelName -> $("#panel-builder-panels .paginator-row .view-panel",text:panelName).parent().parent().find(".btn.btn-mini.dropdown-toggle.action-button")}
        optionsOfActionButton                           {String option-> $(".btn-group.pull-right.open .dropdown-menu ."+option)}

    }
}
