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
    }

    static content ={
        panelBuilder {module PanelBuilderModule}
    }

    def createNewPanel(String panel_name,String panel_description){
        waitFor { panelBuilder.newPanelButton.displayed }
        click(panelBuilder.newPanelButton,"New Panel Button");
        waitFor {panelBuilder.createPanelModal.displayed}
        type(panelBuilder.panelNameField,panel_name,"Panel Name");
        type(panelBuilder.panelDescriptionField,panel_description,"Panel Description");
        click(panelBuilder.createPanelButton,"Create Panel Button")
        waitTillElementIsNotPresent(panelBuilder.createPanelModal,"Create Panel Modal")
        waitFor { panelBuilder.panelRowBasedOnPanelName(panel_name).displayed }
    }

    def clickOnActionsButtonBasedOnAndClickAction(String panelName, String action) {
        waitFor { panelBuilder.actionButtonBasedOnPanelName(panelName).displayed }
        click(panelBuilder.actionButtonBasedOnPanelName(panelName),"Action Button");
        click(panelBuilder.optionsOfActionButton(action),"Action Button -> "+action)
    }

    def deleteAllPanels() {
        while (!panelBuilder.numberOfPanelsOnWorkSpacePanel.equals(ZERO)) {
            click(panelBuilder.actionButton, "Action Button of Panel");
            click(panelBuilder.deletePanelUnderActionDropDpwn, "Delete Panel Aoption under Actions Drop Down")
            click(panelBuilder.deletePanelButtonOnDialog, "Delete Button on Dialog");
            click(panelBuilder.closeButtonOnDialogWindow, "Close button of the Delete Panel Dialog")
            Thread.sleep(500)
        }
    }
}
