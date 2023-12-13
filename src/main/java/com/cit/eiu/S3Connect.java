package com.cit.eiu;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/s3connection" })

public class S3Connect extends HttpServlet {
    public static void main(String[] args) {
        AWSCredentials credentials = (AWSCredentials) new InstanceProfileCredentialsProvider(false);

        AmazonS3 s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.AP_NORTHEAST_2)
            .build();

            // Display the bucket names
            s3Client.listBuckets();
            for (Bucket bucket : s3Client.listBuckets()) {
                System.out.println("bucketName=" + bucket.getName());
            }
    }
}
