package Modules.Login

import geb.Module

/**
 * Created by in02183 on 4/1/2016.
 */
class HomeModule extends Module{

    static content = {

        homePageTab                          { String tabName -> $("h3", text: tabName) }
        uploadGenomesButton                  { $("h3", text: "Upload Genomes") }
        projectsButton                       { $("h3", text: "Projects") }
        clinicalReporterButton               { $("h3", text: "Clinical Reporter") }
        filteringProtocolsButton             { $("h3", text: "Filtering Protocols") }
        geneSets                             { $("h3", text: "Gene Sets") }
        workSpaceDropDown                    { $(".workspace-label") }
        workSpaceDropDownValue               { String workSpace -> $("#workspaces-list li a", text: contains(workSpace)) }
        switchWorkspaceAlert                 { String value -> $(".alert.alert-success", text: contains(value)) }
        numberOfWorkSpacesRows               { $("#workspaces-list li") }
        createNewWorkspaceLink               { $("li a", text: contains("Create New Workspace")) }

        /*New Workspace Page*/
        createNewWorkspaceButton             { $(".btn-primary.save", text: contains("Create New Workspace")) }
        newWorkspaceButton                   { $(".new-workspace", text: contains("New Workspace")) }
        workspaceNameTextField               { $("#workspace-name") }
        returnToListButton                   { $(".close-button", text: contains("Return to list")) }
        workSpaceRow                         { String workspaceName -> $("tr.paginator-row td", text: contains(workspaceName)) }
        numberOfWorkSpaces                   { $("tbody#workspaces tr").size() }
        paginatorDropDown                    { $(".paginator.limit .btn") }
        paginatorDropDownValue               { String value -> $(".paginator.open .dropdown-menu.open ul li a span", text: contains(value)) }

        /*DropDown*/
        workspaceIdBasedOnWorkspaceName      { String workSpaceName -> $('li.workspace-item a', text: workSpaceName).getAttribute("href").split("workspace_id=")[1] }


    }
}
