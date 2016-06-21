package Modules.Filtering_Protocol

import geb.Module

/**
 * Created by E002183 on 4/27/2016.
 */
class FilteringProtocolHomeModule extends Module {

    static content = {
        filteringWorkspaceProtocolTable              { $(".active #workspace-protocols #filter-workspace-protocols") }
        numberOfRows                                 { $(".active #workspace-protocols #filter-workspace-protocols tr").size() }
        workSpaceFilterTab                           { $("li a",text:contains("Workspace Filters"))}
        numberOfFilteringProtocols                   { $('.delete-filter.opaque-button.pull-right').size() }
        deleteButtonOfTheFilteringProtocol           { $('.delete-filter.opaque-button.pull-right') }
        deleteButtonOnDialog                         { $(".btn.btn-danger.close-button") }
        closeButtonOnModalDialog                     { $(".close-button",text:contains("Close"))}
        deleteModalDialog                            { $(".modal.modal-overflow.in") }
    }
}
