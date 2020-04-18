package com.pinchuk.awsimageupload.datastore;

import com.pinchuk.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Pinchuk Yevhen
 * @created 18/04/2020
 */
@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("09e17657-7b83-4e1e-9c77-40e8f2fb6e05"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("76d8bb7f-124f-4c33-ba04-25fd7c74b05f"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}
