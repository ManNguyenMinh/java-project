package com.cit.eiu;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.partitions.model.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

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
        // Specify the AWS region
        // software.amazon.awssdk.regions.Region region = Region.AP_SOUTHEAST_2; // Change this to your desired region

        // Create an S3 client
        S3Client s3 = S3Client.builder().region(Region.AP_SOUTHEAST_2).credentialsProvider(DefaultCredentialsProvider.create()).build();

        try {
            // List all S3 buckets in the account
            ListBucketsResponse response = s3.listBuckets();
            for (software.amazon.awssdk.services.s3.model.Bucket bucket : response.buckets()) {
                System.out.println("Bucket Name: " + bucket.name());
            }
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}

