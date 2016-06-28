package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsHomeModule extends Module{

    static content = {
        projectNameColumn                                   { $(".project-name-column",text:contains("Project Name")) }

        genomeCountBasedOnProjectName                       {String projectName-> $("div",'data-name':projectName).parent().parent().find("td div.genome_count").text()}
        projectButton                                       {String projectName-> $('div.project-link.truncate','data-name':projectName)}
        eachProject                                         {$('#workspace .project-link.truncate')}
        numberOfProjects                                    {$('#workspace tr').size()}
        projectIconFolder                                   { int index-> $(".folder-icon.private-folder").getAt(index)}
        eachProjectNameText                                 { int index-> $('#workspace tr')[index].find(".project-link.truncate")}

        projectNameInProjectTable                           {String projectName-> $("#workspace div", 'data-name':projectName)}
        createdByBasedOnProjectName                         {String projectName-> $("div",'data-name':projectName).parent().parent().find("td span.truncate").text()}
        dateCreatedBasedOnProjectName                       {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[3].text()}
        dateModifiedBasedOnProjectName                      {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[4].text()}
        projectDescriptionBasedOnProjectName                {String projectName-> $("div",'data-name':projectName).parent().parent().find("td.project-description-column div").text()}

       //Project Search
        projectsTab                                         {String tabName-> $("a",text:contains(tabName))}
        activeProjectTab                                    {String tabName-> $("li.active a",text:contains(tabName))}
        projectSearchTextField                              {$("input.search-query")}
        projectSearchButton                                 {$("button.search-btn")}
        modalPopUp                                          {$(".modal-overflow")}
        projectSearchResultsCount                           {$(".found-results tbody tr.found-project")}
        projectSearchResults                                {int index -> projectSearchResultsCount[index].find("a").text()}
        modalCloseButton                                    {$("button.close-button",text:"Close")}
        modalCloseIcon                                      {$("div.modal-scrollable button.close")}
        noSearchResults                                     {$(".found-results")}
        projectsSubHeaderInSearchModal                      {$(".found-results h4")}
        genomeSubHeaderInSearchModal                        {$(".found-results h4",text:"Genomes")}
        genomeSearchResultsCount                            {$(".found-results tbody tr.found-genome")}
        genomeSearchResults                                 {int index -> genomeSearchResultsCount[index].find("a").text()}

        //Create New Project Modal Window
        newProjectButton                                    {$("#new-project")}
        projectNameField                                    {$("#project_name")}
        shareProjectCheckbox                                {$("#share-project-checkbox")}
        contributorsRole                                    {$("#contributor-radio")}
        editProjectCancelButton                             {$(".cancel-button")}
        optionBasedOnCreateOrEditOrShareProject             {String option-> $("#modal .btn",text: option)}
        projectSucessfullyCreatedAlert                      {$(".alert-text",text: "Project successfully created!")}
        actionsBasedOnProjectName                           {String projectName-> $("div",'data-name':projectName).parent().parent().find("td a.dropdown-toggle.action-button")}
        optionActionsButton                                 {String option-> $(".btn-group.pull-right.open .dropdown-menu a",text:contains(option))}
        projectFieldRequiredError                           {$("#project_name-error")}

        //Edit Project Modal
        projectDescriptionTextField                         {$("#description")}
        projectSavedAlert                                   {$(".alert-text",text: "Project saved!")}

        //Project Details Modal
        projectHeaderName                                   {$("h3.modal-title").text()}
        projectContributors                                 {$("div.userbox a").text()}
        projectDescription                                  {$(".modal-body table td")[1].text()}

        //Share Project Modal
        selectUserDropDown                                  { $(".add-member-select")}
        userDropDownOption                                  { String option-> selectUserDropDown.find("option",text: contains(option))}
        shareButton                                         { $(".add-member")}
        numberOfUsersInShareProjectModal                    { $(".user-table-tbody tr")}
        eachUserNameInShareProjectModal                     { int index-> numberOfUsersInShareProjectModal[index].find(".user-login")}
        userNameOnShareProjectModal                         { String userName-> $(".user-login",text: userName)}
        projectRoleButton                                   { String userName,option-> $(".user-login",text: userName).parent().parent().find("button",text: contains(option))}
        shareChangesSavedAlert                              { $(".alert-text",text: "Changes successfully saved!")}
        actionsCountBasedOnProjectRole                      { $(".btn-group.pull-right.open .dropdown-menu li")}
        actionsListBasedOnProjectRole                       { int index-> actionsCountBasedOnProjectRole[index].find("a").text()}
        projectAccessRemovedAlert                           { $(".alert-text",text: "Successfully removed")}
    }
}
