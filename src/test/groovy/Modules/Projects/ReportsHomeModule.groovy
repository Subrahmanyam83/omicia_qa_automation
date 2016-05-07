package Modules.Projects

import geb.Module

/**
 * Created by E002183 on 5/6/2016.
 */
class ReportsHomeModule extends Module {

    static content = {

        reportsPageVariantsTable { $("div.variants-table table#variants tbody#report-variants") }
        header { $(".header-div legend") }
        headerButton { String buttonName -> $("div.header-buttons ").find("button", text: buttonName) }


    }
}
