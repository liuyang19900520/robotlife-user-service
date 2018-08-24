package com.liuyang19900520.robotlife.common.util;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;

public class LS3Utils {

    //AKIAJ22JR65NTXU64ZHQ
//    AKIAJ6R7YXFZK2CTEHIQ
    //ZfAMXYy3ycFOgJIEcCKYQw6F81BBFpBSVbilryGj


    public static String ACCESS_KEY_ID = "AKIAJOPFF54CPFXMHK7Q";
    public static String SECRET_ACCESS_KEY = "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";
    public static String BUCKET_NAME = "robotlife/markdown";

    public static BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY_ID,
            SECRET_ACCESS_KEY);
    public static AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.AP_NORTHEAST_1).build();

    public static boolean putObj(String key, byte[] contentBytes, InputStream in) {
        try {
            Long contentLength = Long.valueOf(contentBytes.length);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);

            /*
             * Reobtain the tmp uploaded file as input stream
             */

            s3.putObject(BUCKET_NAME, key, in, metadata);

            return true;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            return false;
        }

    }

    public static boolean putObj(File file) {
        try {
            s3.putObject(BUCKET_NAME, file.getName(), file);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            return false;
        }

    }


    /*
     * Get the object from S3
     */
    public static File getFileObject(String bucketName, String objectKey, String path) {
        try {
            S3Object o = s3.getObject(bucketName, objectKey);
            S3ObjectInputStream s3is = o.getObjectContent();
            File newFile = new File(path + objectKey);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();

            return newFile;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

}
