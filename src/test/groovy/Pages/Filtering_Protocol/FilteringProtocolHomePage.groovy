package Pages.Filtering_Protocol

import Modules.Filtering_Protocol.FilteringProtocolHomeModule
import Modules.Filtering_Protocol.NewFilteringProtocolModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/27/2016.
 */
class FilteringProtocolHomePage extends BasePage {

    static at = {
        filteringprotocol.newFilteringProtocolButton.displayed
        filteringProtocolHome.filteringWorkspaceProtocolTable
    }

    static content = {
        filteringprotocol { module NewFilteringProtocolModule }
        filteringProtocolHome { module FilteringProtocolHomeModule }
    }

    def deleteAllFilteringProtocols() {
        if (filteringProtocolHome.numberOfRows.size() > 0) {
            while (!filteringProtocolHome.numberOfFilteringProtocols.equals(ZERO)) {
                click(filteringProtocolHome.deleteButtonOfTheFilteringProtocol, "Filtering protocol Delete Button")
                click(filteringProtocolHome.deleteButtonOnDialog, "Delete Confirmation on Dialog")
                waitTillElementIsNotPresent((filteringProtocolHome.deleteModalDialog), "Delete Filtering protocol Dialog")
            }
        }
    }
}