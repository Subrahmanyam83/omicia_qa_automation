package Modules.Projects

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsHomeModule extends Module{

    static content = {
        projectNameColumn                { $(".project-name-column",text:contains("Project Name")) }

        genomeCountBasedOnProjectName    { String projectName-> $("div",'data-name':projectName).parent().parent().find("td div.genome_count").text() }
        projectButton                    { String projectName-> $('div.project-link.truncate','data-name':projectName) }
        eachProject                      { $('#workspace .project-link.truncate') }
        numberOfProjects                 { $('#workspace tr').size() }
        projectIconFolder                { int index-> $(".folder-icon.private-folder").getAt(index)}
    }
}
