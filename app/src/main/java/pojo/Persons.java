package pojo;

import android.net.Uri;

/**
 * Created by sunny on 6/12/15.
 */
public class Persons {
    public String profilePic;
    public String name;
    public String contact_id;

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
