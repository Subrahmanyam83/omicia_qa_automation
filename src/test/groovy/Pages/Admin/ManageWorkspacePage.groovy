package Pages.Admin

import Modules.Admin.ManageWorkspaceModule
import Modules.Panel_Builder.PanelBuilderModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/18/2016.
 */
class ManageWorkspacePage extends BasePage {

    static at = {
        manageworkspace.searchTextField
    }

    static content = {
        manageworkspace { module ManageWorkspaceModule }
        panelBuilder {module PanelBuilderModule}
    }

    def search(String searchValue, boolean clickManage = false) {
        waitFor { manageworkspace.searchTextField }
        type(manageworkspace.searchTextField, searchValue, "Search Text Field")
        click(manageworkspace.searchButton, "Search Button on Manage Workspace Page")
        waitFor("shortwait") { manageworkspace.searchValueExistence(searchValue) }
        if (clickManage.equals(true)) {
            click(manageworkspace.manageButtonBasedOnSearchValue(searchValue), "Manage Button")
            waitFor { manageworkspace.headerText }
        }
    }

    def clickManageBasedOnNameOrId(String value) {
        click(manageworkspace.manageButtonBasedOnSearchValue(value), "Manage Button based on Name or ID")
    }

    def getIDBasedOnWorkspaceName(String workspaceName) {
        waitFor { manageworkspace.idBasedOnName(workspaceName) }
        return manageworkspace.idBasedOnName(workspaceName).text().trim()
    }

    def getHeaderTextOnManageWorkspacePage() {
        waitFor { manageworkspace.headerText }
        return manageworkspace.headerText.text()
    }

    def goToTab(String tabName) {
        waitFor { manageworkspace.tabName(tabName) }
        click(manageworkspace.tabName(tabName), "Manage Workspace Tab Name: " + tabName)
        waitFor { manageworkspace.activeTabName(tabName) }
    }



/*GROUPS TAB FUNCTIONS*/
    def clickOnCheckBoxUnderGroupsTab(List groupList) {
        groupList.each { group ->
            if (!manageworkspace.checkBoxBasedOnLabel(group).firstElement().isSelected()) {
                click(manageworkspace.checkBoxBasedOnLabel(group), "Checkbox: " + group + " under WorkSpace Groups")
            }
        }
    }



/*PAYMENT INFO TAB FUNCTIONS*/
    def addPOAccount() {
        waitFor { manageworkspace.addPOAccountButton }
        int numberOfPOAccounts = getNumberOfPOAccounts()
        click(manageworkspace.addPOAccountButton, "Add PO Account Button")
        waitFor { manageworkspace.modalPopup }
        type(manageworkspace.nameTextField, "Admin", "PO popup::Name Text Field")
        type(manageworkspace.validUntilTextField, "08/17/2022", "PO popup::Valid Until Text Field")
        click(manageworkspace.nameTextField, "PO popup::Name Text Field")
        type(manageworkspace.omiciaAminContactTextField, "Admin", "PO popup::Omicia Admin Contact Text Field")
        type(manageworkspace.customerAdminContactTextField, "Admin", "PO popup::Customer Admin Contact Text Field")
        waitFor {manageworkspace.workspaceDefaultCheckbox}
        click(manageworkspace.workspaceDefaultCheckbox, "PO Popup:: Workspace Default Checkbox")
        waitFor {manageworkspace.saveButton}
        click(manageworkspace.saveButton, "PO Popup:: Save Button")
        waitFor { manageworkspace.closeButton }
        click(manageworkspace.closeButton, "Close Button on Modal Popup of PO Popup")
        Assert.assertEquals(getNumberOfPOAccounts(), numberOfPOAccounts + 1, "Number of PO Accounts are not equal to: " + getNumberOfPOAccounts())
    }
    def getNumberOfPOAccounts() {
        return manageworkspace.numberOfPOAccounts
    }



/*MEMEBERS TAB FUNCTIONS*/
    def deleteAllMembersFromMembersTab(){
        while(manageworkspace.membersTableSize()>0){
            click(manageworkspace.deleteButton,"Delete Button on Each User")
            Thread.sleep(1000L)
        }
    }
    def addMemberToWorkspace(String userEmail){
        waitFor {manageworkspace.addMemberButton}
        click(manageworkspace.addMemberButton,"Add Member button")
        waitFor {manageworkspace.searchFieldInAddUserModal}
        type(manageworkspace.searchFieldInAddUserModal,userEmail,"Search Text field in Add user modal")
        click(manageworkspace.searchButtonInAddUserModal,"Search button")
        waitFor {manageworkspace.addUserButtoninAddUserModal}
        click(manageworkspace.addUserButtoninAddUserModal,"Add User Button")
        waitFor {manageworkspace.addedButtoninAddUserModal}
        click(manageworkspace.closeButton,"Close Button")
    }
    def deleteMembersFromWorkspace(String userEmail){
        waitFor {manageworkspace.deleteIconBasedOnMember(userEmail)}
        click(manageworkspace.deleteIconBasedOnMember(userEmail),"Delete icon")
        Thread.sleep(2000)
    }
    def checkIfMemberIsDisplayed(String memberEmail){
        for(int i=0;i<manageworkspace.membersTableSize;i++){
            if(manageworkspace.eachMemberNameInMembersTab(i).text().trim().equals(memberEmail)){
                return true
            }
        }
        return false
    }
    def checkIfMemberIsNotDisplayed(String memberEmail){
        for(int i=0;i<manageworkspace.membersTableSize;i++){
            if(manageworkspace.eachMemberNameInMembersTab(i).text().trim().equals(memberEmail)){
                return false
            }
        }
        return true
    }



/*CLINICAL REPORTS FUNCTIONS*/
    def clickItemsPerPageAndChooseValue(String value = HUNDRED) {
        Thread.sleep(3000L);
        if (panelBuilder.activePaginatorButton.displayed) {
            waitFor {panelBuilder.activePaginatorButton}
            scrollToCenter(panelBuilder.activePaginatorButton)
            Thread.sleep(2000)
            click(panelBuilder.activePaginatorButton, "Paginator Button")
            waitFor { panelBuilder.paginatorDropDownValue(value) }
            scrollToCenter(panelBuilder.paginatorDropDownValue(value))
            click(panelBuilder.paginatorDropDownValue(value), "Drop Down Paginator Value: " + value)
        }
    }
    def deleteAllClinicalReports(){
        clickItemsPerPageAndChooseValue()
        while(!manageworkspace.numberOfClinicalReports.equals(ZERO)){
            waitFor {manageworkspace.deleteClinicalReportsButton}
            click(manageworkspace.deleteClinicalReportsButton,"First Clinical Report Delete Button at Manage Workspace Page")
            waitFor {manageworkspace.deleteButtonOnModalPopup}
            click(manageworkspace.deleteButtonOnModalPopup,"Delete CR button on Modal Popup at Manage Workspace Page")
            waitFor {manageworkspace.closeButtonOnModalPopup}
            click(manageworkspace.closeButtonOnModalPopup,"Close Button on Modal Popup")
        }
    }
}
