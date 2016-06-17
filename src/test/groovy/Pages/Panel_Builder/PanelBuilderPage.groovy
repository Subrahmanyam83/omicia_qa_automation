package Pages.Panel_Builder

import Modules.Panel_Builder.PanelBuilderModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/25/2016.
 */
class PanelBuilderPage extends BasePage{

    String panelName

    static at = {
        panelBuilder.newPanelButton.displayed
        panelBuilder.navPanelOfPanelBuilder.displayed
        panelBuilder.panelTable
    }

    static content ={
        panelBuilder {module PanelBuilderModule}
    }

    def clickItemsPerPageAndChooseValue(String value = HUNDRED) {
        if (panelBuilder.activePaginatorButton.displayed) {
            click(panelBuilder.activePaginatorButton, "Paginator Button")
            waitFor { panelBuilder.paginatorDropDownValue(value) }
            click(panelBuilder.paginatorDropDownValue(value), "Drop Down Paginator Value: " + value)
        }
    }

    def createNewPanel(String panel_name,String panel_description){
        waitFor { panelBuilder.newPanelButton.displayed }
        click(panelBuilder.newPanelButton,"New Panel Button");
        waitFor {panelBuilder.createPanelModal.displayed}
        type(panelBuilder.panelNameField,panel_name,"Panel Name");
        type(panelBuilder.panelDescriptionField,panel_description,"Panel Description");
        click(panelBuilder.createPanelButton,"Create Panel Button")
        click(panelBuilder.closeButtonOnDialogWindow,"Close Button on Modal Dialog")
        clickItemsPerPageAndChooseValue()
        waitFor { panelBuilder.panelRowBasedOnPanelName(panel_name) }
    }

    def clickOnActionsButtonBasedOnAndClickAction(String panelName, String action) {
        waitFor { panelBuilder.actionButtonBasedOnPanelName(panelName) }
        click(panelBuilder.actionButtonBasedOnPanelName(panelName),"Action Button");
        click(panelBuilder.optionsOfActionButton(action),"Action Button -> "+action)
    }

    def deleteAllPanels() {
        Thread.sleep(3000L)
        while (!panelBuilder.numberOfPanelsOnWorkSpacePanel.equals(ZERO)) {
            waitFor {panelBuilder.actionButton}
            click(panelBuilder.actionButton, "Action Button of Panel");
            waitFor {panelBuilder.deletePanelUnderActionDropDpwn}
            click(panelBuilder.deletePanelUnderActionDropDpwn, "Delete Panel Aoption under Actions Drop Down")
            waitFor {panelBuilder.deletePanelButtonOnDialog}
            click(panelBuilder.deletePanelButtonOnDialog, "Delete Button on Dialog");
            waitFor {panelBuilder.closeButtonOnDialogWindow}
            click(panelBuilder.closeButtonOnDialogWindow, "Close button of the Delete Panel Dialog")
            Thread.sleep(1000)
        }
    }
}
