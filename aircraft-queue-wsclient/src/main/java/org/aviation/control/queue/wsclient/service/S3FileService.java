package org.aviation.control.queue.wsclient.service;

import java.io.File;
import java.util.Optional;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.Upload;

public interface S3FileService {
	
	Upload uploadFile(String bucket, String key, File file);
	
	Download downloadFile(String bucket, String key, File file);

    PutObjectResult saveFile(String bucket, String key, File file);
    
    Optional<File> getFile(String bucket, String key);
    
    boolean deleteFile(String bucket, String key);
    
}
