package Modules.UploadGenome

import geb.Module

/**
 * Created by in02183 on 4/5/2016.
 */
class UploadGenomeModule extends Module{

    static content = {
        projectDropDown         {$(".btn.dropdown-toggle.btn-default",'data-id':"project_id")}
        newProjectButton        {$("#new_project_button")}

        /* New Project Frame*/
        projectNameField                {$("#project_name")}
        createProjectButton             {$(".btn.btn-primary.close-button",text: "Create Project")}
        projectSucessfullyCreatedAlert  {$(".alert-text",text: "Project successfully created!")}
        closeButton                     {$(".btn.btn-primary.close-button",text: "Close")}
        projectCreatedAlert             {$("div.alert-content")}

        browseVcfFileButton             {$("#genome")}
        genomeLabel                     {$("#genome_label")}
        externalId                      {$("#external_id")}
        genderDropDown                  {$(".btn.dropdown-toggle.btn-default",'data-id':"genome_sex").find(".filter-option.pull-left")}
        genderValue                     {String value -> $(".btn-group.bootstrap-select.input-xlarge.input-group-btn.open .text",text:value)}
        confirmCheckBox                 {$(".checkbox").find(name:"assembly_version")}
        uploadButton                    {$(".btn.btn-primary",value:"Upload")}

        uploadSuccessMessage            {$('.alert.alert-block.alert-success')}
        projectCreatedModalDialog       {$('.modal-scrollable')}

        vcfFileNotUploadedError         {$(".required.input-xlarge.text-error",name:"genome")}
    }
}
