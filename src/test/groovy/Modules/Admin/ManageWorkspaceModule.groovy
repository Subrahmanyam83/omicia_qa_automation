package Modules.Admin

import geb.Module

/**
 * Created by E002183 on 5/18/2016.
 */
class ManageWorkspaceModule extends Module {

    static content = {

        /*Search Results*/
        searchTextField                 { $(".form-search #name") }
        searchButton                    { $(".button-search", text: "Search") }
        searchValueExistence            { String value -> $("tbody#workspace-results-tbody tr td", text: value) }
        manageButtonBasedOnSearchValue  { String searchValue -> searchValueExistence(searchValue).parent().find("td .manage-ws", text: contains("Manage")) }
        idBasedOnName                   { String workspaceName -> searchValueExistence(workspaceName).parent().find(".workspace-id") }

        /*Manage WorkSpace Home Page*/
        headerText                      { $("div h2") }
        tabName                         { String tabName -> $("#workspace-info-tabs .nav-tabs li a", text: contains(tabName)) }
        activeTabName                   { String tabName -> $("#workspace-info-tabs .nav-tabs li.active a", text: contains(tabName)) }

        /*Groups*/
        checkBoxBasedOnLabel            { String groupName -> $("label.checkbox", text: contains(groupName)).find("input") }

        /*Payment Info*/
        addPOAccountButton              { $("#add-po-account") }
        modalPopup                      { $(".modal.modal-overflow.in") }
        nameTextField                   { modalPopup.find("#name") }
        validUntilTextField             { modalPopup.find("#valid-until") }
        omiciaAminContactTextField      { modalPopup.find("#administrator-contact") }
        customerAdminContactTextField   { modalPopup.find("#customer-contact") }
        workspaceDefaultCheckbox        { modalPopup.find("#make-default") }
        saveButton                      { $(".modal-footer .close-button", text: "Save") }
        closeButton                     { $(".close-button", text: "Close") }
        cancelButton                    { $(".modal-footer .cancel-button", text: "Cancel") }
        numberOfPOAccounts              { $('.po-accounts-div tbody tr').size() }

        /*Members*/
        membersTableSize                { $("#members-table tbody tr").size() }
        deleteButton                    { $(".remove-member") }
        addMemberButton                 { $("#add-member") }
        searchFieldInAddUserModal       { $(".form-search .search-query")}
        searchButtonInAddUserModal      { $(".form-search .btn")}
        resultsTableInAddUserModal      { $("#user-table")}
        addUserButtoninAddUserModal     { $(".add-this-member")}
        addedButtoninAddUserModal       { $(".add-this-member",text: contains("Added"))}
        workspaceMemberExistence        { String userloginemail-> $("#members-table tbody td",text:contains(userloginemail))}
        deleteIconBasedOnMember         { String useremail-> workspaceMemberExistence(useremail).parent().find("button.remove-member")}
        eachMemberNameInMembersTab      { int index-> $("#members-table tbody tr")[index].find("td")[2] }
    }
}
