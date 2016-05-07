package Modules.Filtering_Protocol

import geb.Module

/**
 * Created by E002183 on 4/27/2016.
 */
class FilteringProtocolHomeModule extends Module {

    static content = {
        filteringWorkspaceProtocolTable { $(".active #workspace-protocols #filter-workspace-protocols") }
        numberOfRows { $(".active #workspace-protocols #filter-workspace-protocols tr") }

        numberOfFilteringProtocols { $('.delete-filter.opaque-button.pull-right').size() }
        deleteButtonOfTheFilteringProtocol { $('.delete-filter.opaque-button.pull-right') }
        deleteButtonOnDialog { $(".btn.btn-danger.close-button") }
        deleteModalDialog { $(".modal.modal-overflow.in") }

    }
}
