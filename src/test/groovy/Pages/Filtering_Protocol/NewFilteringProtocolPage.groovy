package Pages.Filtering_Protocol

import Modules.Filtering_Protocol.NewFilteringProtocolModule
import Modules.Panel_Builder.PanelBuilderModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/26/2016.
 */
class NewFilteringProtocolPage extends BasePage {

    static at = {
        filteringprotocol.newFilteringProtocolButton.displayed
    }

    static content = {
        filteringprotocol { module NewFilteringProtocolModule }
        panelBuilder {module PanelBuilderModule}
    }

    def createNewFilteringProtocol() {
        click(filteringprotocol.newFilteringProtocolButton, "New Filtering Protocol Button");
    }

    def fillNameAndDescription(String filteringProtocolName, String description) {
        type(filteringprotocol.filterName, filteringProtocolName, "Filtering protocol Name")
        type(filteringprotocol.filterDescription, description, "Filtering protocol Description");
    }

    def chooseConsequenceGenemodelAndExclude(List consequences, List geneModels, List excludes) {
        if (!consequences.size().equals(ZERO)) {
            waitFor { filteringprotocol.dropDownBasedOnLabel("consequence") }
            click(filteringprotocol.dropDownBasedOnLabel("consequence"), "Consequence Drop Down")
            for (String consequence : consequences) {
                waitFor { filteringprotocol.checkboxBasedOnValue(consequence) }
                click(filteringprotocol.checkboxBasedOnValue(consequence), "Consequence Drop Down Value: " + consequence)
            }
            waitFor { filteringprotocol.dropDownBasedOnLabel("consequence") }
            click(filteringprotocol.dropDownBasedOnLabel("consequence"), "Consequence Drop Down")
        }


        if (!geneModels.size().equals(ZERO)) {
            waitFor { filteringprotocol.dropDownBasedOnLabel("gene-model") }
            click(filteringprotocol.dropDownBasedOnLabel("gene-model"), "Gene Models Drop Down")
            for (String geneModel : geneModels) {
                waitFor { filteringprotocol.checkboxBasedOnValue(geneModel) }
                click(filteringprotocol.checkboxBasedOnValue(geneModel), "Gene Models Drop Down Value: " + geneModel)
            }
            waitFor { filteringprotocol.dropDownBasedOnLabel("gene-model") }
            click(filteringprotocol.dropDownBasedOnLabel("gene-model"), "Gene Models Drop Down")
        }


        if (!excludes.size().equals(ZERO)) {
            waitFor { filteringprotocol.dropDownBasedOnLabel("exclude") }
            click(filteringprotocol.dropDownBasedOnLabel("exclude"), "Exclude Drop Down")
            for (String exclude : excludes) {
                waitFor { filteringprotocol.checkboxBasedOnValue(exclude) }
                click(filteringprotocol.checkboxBasedOnValue(exclude), "Exclude Drop Down Value")
            }
            waitFor { filteringprotocol.dropDownBasedOnLabel("exclude") }
            click(filteringprotocol.dropDownBasedOnLabel("exclude"), "Exclude Drop Down")
        }
    }

    def saveFilteringProtocol() {
        click(filteringprotocol.saveFilteringProtocolButton, "Save Filtering Protocol")
        waitFor {filteringprotocol.newFilteringProtocolButton}
        waitFor {filteringprotocol.filteringProtocolWorkSpace}
    }

    def verifyNewFilteringProtocolIsCreated(String filteringprotocolName) {
        clickItemsPerPageAndChooseValue()
        waitFor { filteringprotocol.newFilteringProtocolRowBasedOnName(filteringprotocolName) }
    }

    def clickItemsPerPageAndChooseValue(String value = HUNDRED) {
        if (filteringprotocol.activePaginatorButton.displayed) {
            scrollToCenter(filteringprotocol.activePaginatorButton)
            click(filteringprotocol.activePaginatorButton, "Paginator Button")
            waitFor { panelBuilder.paginatorDropDownValue(value) }
            scrollToCenter(panelBuilder.paginatorDropDownValue(value))
            click(panelBuilder.paginatorDropDownValue(value), "Drop Down Paginator Value: " + value)
            scrollToCenter(filteringprotocol.newFilteringProtocolButton)
        }
    }
}
