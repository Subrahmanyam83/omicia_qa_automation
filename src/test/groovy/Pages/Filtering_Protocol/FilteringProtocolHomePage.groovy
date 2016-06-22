package Pages.Filtering_Protocol

import Modules.Filtering_Protocol.FilteringProtocolHomeModule
import Modules.Filtering_Protocol.NewFilteringProtocolModule
import Modules.Panel_Builder.PanelBuilderModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/27/2016.
 */
class FilteringProtocolHomePage extends BasePage {

    static at = {
        filteringprotocol.newFilteringProtocolButton.displayed
        filteringProtocolHome.filteringWorkspaceProtocolTable
        filteringProtocolHome.workSpaceFilterTab
    }

    static content = {
        filteringprotocol { module NewFilteringProtocolModule }
        filteringProtocolHome { module FilteringProtocolHomeModule }
        panelBuilder {module PanelBuilderModule}
    }

    def deleteAllFilteringProtocols() {
        if (filteringProtocolHome.numberOfRows > 0) {
            while (!filteringProtocolHome.numberOfFilteringProtocols.equals(ZERO)) {
                waitFor {filteringProtocolHome.deleteButtonOfTheFilteringProtocol}
                click(filteringProtocolHome.deleteButtonOfTheFilteringProtocol, "Filtering protocol Delete Button")
                waitFor {filteringProtocolHome.deleteButtonOnDialog}
                click(filteringProtocolHome.deleteButtonOnDialog, "Delete Confirmation on Dialog")
                waitFor {filteringProtocolHome.closeButtonOnModalDialog}
                click(filteringProtocolHome.closeButtonOnModalDialog,"Close Button on Modal Dialog")
                waitFor {panelBuilder.panelLoadingBarWithDisplayNone}
                Thread.sleep(1000L)
            }
        }
    }
}
