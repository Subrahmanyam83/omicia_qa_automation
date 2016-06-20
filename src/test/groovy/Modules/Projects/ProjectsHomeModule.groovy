package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsHomeModule extends Module{

    static content = {
        projectNameColumn                                   {$(".project-name-column",text:contains("Project Name"))}

        genomeCountBasedOnProjectName                       {String projectName-> $("div",'data-name':projectName).parent().parent().find("td div.genome_count").text()}
        projectButton                                       {String projectName-> $('div.project-link.truncate','data-name':projectName)}
        eachProject                                         {$('#workspace .project-link.truncate')}
        numberOfProjects                                    {$('#workspace tr').size()}

        projectNameInProjectTable                           {String projectName-> $("#workspace div", 'data-name':projectName)}
        createdByBasedOnProjectName                         {String projectName-> $("div",'data-name':projectName).parent().parent().find("td span.truncate").text()}
        dateCreatedBasedOnProjectName                       {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[3].text()}
        dateModifiedBasedOnProjectName                      {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[4].text()}

       //Project Search
        publicProjectsTab                                   {$("a",text: "Public Projects")}
        workspaceProjectsTab                                {$("a",text:'Workspace Projects')}
        projectSearchTextField                              {$("input.search-query")}
        projectSearchButton                                 {$("button.search-btn")}
        modalPopUp                                          {$(".modal-overflow")}
        projectSearchResultsCount                           {$(".found-results tbody tr.found-project")}
        projectSearchResults                                {String index -> projectSearchResultsCount[index].find("a").text()}
        modalCloseButton                                    {$(".close-button")}
        noSearchResults                                     {$(".found-results",text:"No results found.")}
        projectsSubHeaderInSearchModal                      {$(".found-results h4",text:"Projects")}
        genomeSubHeaderInSearchModal                        {$(".found-results h4",text:"Genomes")}
        genomeSearchResultsCount                            {$(".found-results tbody tr.found-genome")}
        genomeSearchResults                                 {String index -> genomeSearchResultsCount[index].find("a").text()}

        //Create New Project Modal Window
        newProjectButton                                    {$("#new_project_button")}
        projectNameField                                    {$("#project_name")}
        shareProjectCheckbox                                {$("#share-project-checkbox")}
        contributorsRole                                    {$("contributor-radio")}
        editProjectCancelButton                             {$(".cancel-button")}
        optionBasedOnCreateOrEditProject                    {String option-> $("#modal .btn",text: option)}
        projectSucessfullyCreatedAlert                      {$(".alert-text",text: "Project successfully created!")}
        actionsBasedOnProjectName                           {String projectName-> $("div",'data-name':projectName).parent().parent().find("td a.dropdown-toggle.action-button")}
        optionActionsButton                                 {String option-> $(".btn-group.pull-right.open .dropdown-menu a",text:option)}
        projectFieldRequiredError                           {$("#project_name-error")}

        //Edit Project
        projectDescriptionTextField                         {$("#description")}
        projectSavedAlert                                   {$(".alert-text",text: "Project saved!")}
        projectDescBasedOnProjectName                       {String projectName-> $("div",'data-name':projectName).parent().parent().find("td div",title: contains('Description:')).text()}

        //Project Details Page
        projectHeaderName                                   {$("h3.modal-title").text()}
        projectContributors                                 {$("div.userbox a").text()}
        projectDescription                                  {$(".modal-body table td")[1].text()}
    }
}
