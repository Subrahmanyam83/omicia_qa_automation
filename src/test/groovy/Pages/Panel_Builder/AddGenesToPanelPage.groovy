package Pages.Panel_Builder

import Modules.Panel_Builder.AddGenesToPanelModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/25/2016.
 */
class AddGenesToPanelPage extends BasePage{

    static at = {
        addGenesToPanel.navTab.displayed
        addGenesToPanel.addGeneSymbolsField.displayed
    }

    static content ={
        addGenesToPanel {module AddGenesToPanelModule}
    }

    def clickOnTab(String tabName){
        click(addGenesToPanel.tabName(tabName),tabName)
    }

    def typeAndSelectTextOnRunPhevorField(String value){
        waitFor {addGenesToPanel.runPhevorTextField.displayed}
        type(addGenesToPanel.runPhevorTextField,value,"Run Phevor Text Field")
        waitFor {addGenesToPanel.dropDownValue(value)}
        click(addGenesToPanel.dropDownValue(value),"Drop Down Value-> "+value)
    }

    def clickOnGenes(List genesSeparatedBySpaces){
        for (String gene: genesSeparatedBySpaces){
            click(addGenesToPanel.checkBoxBasedOnGene(gene),"Checkbox of the Gene Named: "+gene)
        }
    }

    def addGenesByGivingSymbols(String value) {
        waitFor { addGenesToPanel.geneSymbolsAddTextField }
        type(addGenesToPanel.geneSymbolsAddTextField, value, "Add Genes By Symbol Text Field")
    }

    def clickOnButton(String button){

        if(button.equals("Add Genes")){
            waitFor { addGenesToPanel.addGene.displayed }
            click(addGenesToPanel.addGene, "Add Genes Button")
        }
        else if(button.equals("Back")){
            waitFor { addGenesToPanel.backButton.displayed }
            click(addGenesToPanel.backButton,"Back Button on Add Genes to panel Page")
        }
        else if(button.equals("Run Phevor")){
            waitFor { addGenesToPanel.runPhevorButton.displayed }
            click(addGenesToPanel.runPhevorButton,"Run Phevor Button")
            waitTillElementIsNotPresent(addGenesToPanel.runningPhevorProgressBar,"Running Phevor Prpgress Bar")
        }
    }


}
