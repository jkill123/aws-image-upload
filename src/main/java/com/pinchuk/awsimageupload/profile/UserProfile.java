package com.pinchuk.awsimageupload.profile;

import lombok.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Pinchuk Yevhen
 * @created 18/04/2020
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private UUID userProfileId;
    private String userName;
    private Optional<String> userProfileImageLink; // S3 key

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId, that.userProfileId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userProfileImageLink, that.userProfileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, userName, userProfileImageLink);
    }
}
