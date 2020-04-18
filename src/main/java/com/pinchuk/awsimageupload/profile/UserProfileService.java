package com.pinchuk.awsimageupload.profile;

import com.pinchuk.awsimageupload.bucket.BucketName;
import com.pinchuk.awsimageupload.filestore.FileStore;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author Pinchuk Yevhen
 * @created 18/04/2020
 */
@Service
@AllArgsConstructor
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);

        isImage(file);

        UserProfile user = getUserProfileOrThrow(userProfileId);

        Map<String, String> metaData = extractMetaData(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metaData), file.getInputStream());
            user.setUserProfileImageLink(Optional.ofNullable(fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String> metaData = new HashMap<>();
        metaData.put("Content-Type", file.getContentType());
        metaData.put("Content-Length", String.valueOf(file.getSize()));
        return metaData;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("file must be image");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
    }

    byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId);

        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId());

        return user.getUserProfileImageLink()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }
}
