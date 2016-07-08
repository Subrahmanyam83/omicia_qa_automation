package Pages.Login

import Modules.Login.MyProfileModule
import Utilities.Class.BasePage

/**
 * Created by E002149 on 6/17/2016.
 */
class MyProfilePage extends BasePage{

    static at = {myprofile.myProfileHeaderText.isDisplayed()}

    static content = {
        myprofile{ module MyProfileModule}
    }

    def getUserNameOnMyProfilePage(){
        return myprofile.userName.trim()
    }
}
