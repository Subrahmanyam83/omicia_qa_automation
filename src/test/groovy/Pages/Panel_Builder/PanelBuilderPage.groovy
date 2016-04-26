package Pages.Panel_Builder

import Modules.PanelBuilder.PanelBuilderModule
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
        click(panelBuilder.newPanelButton,"New Panel Button");
        waitFor {panelBuilder.createPanelModal.displayed}
        type(panelBuilder.panelNameField,panel_name,"Panel Name");
        type(panelBuilder.panelDescriptionField,panel_description,"Panel Description");
        click(panelBuilder.createPanelButton,"Create Panel Button")
        waitTillElementIsNotPresent(panelBuilder.createPanelModal,"Create Panel Modal")
    }

    def clickOnActionsButtonAndClickAction(String panelName, String action){
        click(panelBuilder.actionButtonBasedOnPanelName(panelName),"Action Button");
        click(panelBuilder.optionsOfActionButton(action),"Action Button -> "+action)
    }




}
