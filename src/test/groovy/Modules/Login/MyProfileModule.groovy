package Modules.Login

import geb.Module

/**
 * Created by E002149 on 6/17/2016.
 */
class MyProfileModule  extends Module {

    static content = {

        myProfileHeaderText                       {$(".offset2 legend",text:"My Profile")}
        userName                                  {$(".dl-horizontal").find("dd")[1].text()}
    }
}
