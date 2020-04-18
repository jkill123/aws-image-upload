package com.pinchuk.awsimageupload.bucket;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Pinchuk Yevhen
 * @created 18/04/2020
 */
@Getter
@AllArgsConstructor
public enum BucketName {

    PROFILE_IMAGE("pinchuk-image-upload");
    private final String bucketName;

}
