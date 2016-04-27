package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsModule extends Module{

    static content = {
        projectsPageTitleLegend             {$("form legend",text:contains("Project: "))}
        selectAllGenomesCheckBox            {$("#select-all")}
        actionsButton                       {$(".btn.dropdown-toggle.action-button",text:contains("Actions"))}
        actionsButtonOptions                {String name -> $("li a",text:contains(name))}
        deleteGenomesButton                 {$(".btn.btn-danger",text:contains("Delete Genome(s)"))}
        genomesDeletedConfirmationText      {$("#nothing-found",text:contains("No Genomes to display"))}
        deleteProjectButton                 {$(".btn.btn-danger.close-button",text:"Delete Project")}
        getNumberOfGenes                    {$("#genome-list tr").size()}
        noGenomesToDisplay(required:false)  {$('#nothing-found')}

        selectCoumnBasedOnGeneLabel(required:false)   {String geneName, String columnName ->$("#genome-list .genome-detail span",text:contains(geneName)).parent().parent().parent().parent().find(columnName)}
    }
}
