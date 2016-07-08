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
        waitFor {curatePanel.defaultFilterDropDown}
        click(curatePanel.defaultFilterDropDown,"Curate Panel Filer Drop Down");
        waitFor {curatePanel.valueOfDefaultFilterDropDown(value)}
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

    def getNamesOfGenesAdded() {
        List GenesAdded = new ArrayList();
        for (int i = 0; i < getNumberOfPanelGenes(); i++) {
            GenesAdded.add(curatePanel.nameOfGenesAdded[i].text());
        }
        return GenesAdded.toString().replaceAll("\\[|\\]", "")
    }
}
