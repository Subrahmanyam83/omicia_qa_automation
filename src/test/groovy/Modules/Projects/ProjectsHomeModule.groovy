package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsHomeModule extends Module{

    static content = {
        projectNameColumn                {$(".project-name-column",text:contains("Project Name"))}

        genomeCountBasedOnProjectName    {String projectName-> $("div",'data-name':projectName).parent().parent().find("td div.genome_count").text()}
        projectButton                    {String projectName-> $('div.project-link.truncate','data-name':projectName)}
        eachProject                      {$('#workspace .project-link.truncate')}
        numberOfProjects                 {$('#workspace tr').size()}

        projectNameInProjectTable        {String projectName-> $("#workspace div", 'data-name':projectName)}
        createdByBasedOnProjectName      {String projectName-> $("div",'data-name':projectName).parent().parent().find("td span.truncate").text()}
        dateCreatedBasedOnProjectName    {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[3].text()}
        dateModifiedBasedOnProjectName   {String projectName-> $("div",'data-name':projectName).parent().parent().find("td")[4].text()}

       //Project Search
        publicProjectsTab                {$("a",text: "Public Projects")}
        workspaceProjectsTab             {$("a",text:'Workspace Projects')}
        projectSearchTextField           {$("input.search-query")}
        projectSearchButton              {$("button.search-btn")}
        projectsModalSubHeader           {$(".found-results h4")}
        projectSearchResults             {$(".found-results tbody tr").size()}
        searchModalCloseButton           {$(".close-button")}

        //Create New Project
        newProjectButton                 {$("#new_project_button")}
        projectNameField                 {$("#project_name")}
        createProjectButton              {$(".btn.btn-primary.close-button",text: "Create Project")}
        projectSucessfullyCreatedAlert   {$(".alert-text",text: "Project successfully created!")}
        closeButton                      {$(".btn.btn-primary.close-button",text: "Close")}
        actionsBasedOnProjectName        {String projectName-> $("div",'data-name':projectName).parent().parent().find("td a.dropdown-toggle.action-button")}
        optionActionsButton              {String option-> $(".btn-group.pull-right.open .dropdown-menu a",text:option)}

        //Edit Project
        projectDescriptionTextField     {$("#description")}
        saveButtonInEditProject         {$(".btn-primary",text:"Save")}
        projectSavedAlert               {$(".alert-text",text: "Project saved!")}
        projectDescBasedOnProjectName   {String projectName-> $("div",'data-name':projectName).parent().parent().find("td div",title: contains('Description:')).text()}

        //Project Details Page
        projectHeaderName               {$("h3.modal-title").text()}
        projectContributors             {$("div.userbox a").text()}
        projectDetailsCloseButton       {$(".close-button")}
    }
}
