package Modules.Filtering_Protocol

import geb.Module

/**
 * Created by E002183 on 4/26/2016.
 */
class NewFilteringProtocolModule extends Module {

    static content = {

        newFilteringProtocolButton { $('.new-filter') }

        /*New Filtering Protocol*/
        filterName { $('.input-block-level.filter-name') }
        filterDescription { $('.input-block-level.filter-description') }
        dropDownBasedOnLabel { String labelName -> $("." + labelName + " .btn.input-medium.btn-small.pull-right.multiselect-default-state") }
        checkboxBasedOnValue { String value -> $(".multiselect-menu.multiselect-corner-all .multiselect-checkboxes label span", text: contains(value)).parent().find("input", type: "checkbox") }
        saveFilteringProtocolButton { $(".btn.btn-primary.pull-right.save-button", text: "Save Filtering Protocol") }
        newFilteringProtocolRowBasedOnName { String value -> $("a", text: value) }


    }
}
