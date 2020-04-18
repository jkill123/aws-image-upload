package com.pinchuk.awsimageupload.profile;

import com.pinchuk.awsimageupload.datastore.FakeUserProfileDataStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pinchuk Yevhen
 * @created 18/04/2020
 */
@Repository
@AllArgsConstructor
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    List<UserProfile> getUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }
}
