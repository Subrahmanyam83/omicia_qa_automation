package Pages.Panel_Builder

import Modules.Panel_Builder.CuratePanelModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/25/2016.
 */
class CuratePanelPage extends BasePage{

    static at = {
        curatePanel.addGeneButton.displayed
        curatePanel.panelNameField.displayed
    }

    static content ={
        curatePanel {module CuratePanelModule}
    }

    def chooseFilter(String value){
        click(curatePanel.defaultFilterDropDown,"Curate Panel Filer Drop Down");
        click(curatePanel.valueOfDefaultFilterDropDown(value),"Curate Panel Drop Down Value: "+value)
    }

    def clickOnCuratePanelHeaderButton(String buttonName){
        switch (buttonName) {
            case ADD_GENE:
                click(curatePanel.addGeneButton, buttonName);
                break;
        }
    }

    def getNumberOfPanelGenes(){
        return curatePanel.getNumberOfPanelGenes.size()
    }
}
