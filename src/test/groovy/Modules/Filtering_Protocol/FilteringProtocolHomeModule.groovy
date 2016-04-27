package Modules.Filtering_Protocol

import geb.Module

/**
 * Created by E002183 on 4/27/2016.
 */
class FilteringProtocolHomeModule extends Module {

    static content = {

        deleteButtonOfTheFilteringProtocol { $('.delete-filter.opaque-button.pull-right') }
        deleteButtonOnDialog { $(".btn.btn-danger.close-button") }
        deleteModalDialog { $(".modal.modal-overflow.in") }

    }
}
