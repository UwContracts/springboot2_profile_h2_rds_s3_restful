package org.aviation.control.queue.wsclient.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.aviation.control.queue.wsclient.service.S3FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class S3FileServiceImpl implements S3FileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(S3FileServiceImpl.class);

	@Value("${java.io.tmpdir}")
	private String tmpdir;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private TransferManager transferManager;
	
	@Override
	public Upload uploadFile(String bucket, String key, File file) {
		
		return transferManager.upload(bucket, key, file);
	}

	@Override
	public Download downloadFile(String bucket, String key, File file) {
		
		return transferManager.download(bucket, key, file);
	}

	@Override
    public PutObjectResult saveFile(String bucket, String key, File file) {
    	
        return amazonS3.putObject(new PutObjectRequest(bucket, key, file));
    }
    
	@Override
    public Optional<File> getFile(String bucket, String key) {
		Optional<File> result = Optional.empty();
		File file = new File(tmpdir + File.separator + key);
		InputStream objectContent = null;
		OutputStream output = null;
		try {
			objectContent = amazonS3.getObject(bucket, key).getObjectContent();
			
			output = new BufferedOutputStream(new FileOutputStream(file));
			IOUtils.copy(objectContent, output);
			
			result = Optional.of(file);
		} catch (Exception e) {
			LOGGER.warn("Error getting object from S3 by key: " + key, e);
			
		} finally {
			IOUtils.closeQuietly(objectContent);
			IOUtils.closeQuietly(output);
		}
        return result;
    }

	@Override
	public boolean deleteFile(String bucket, String key) {
		try {
			amazonS3.deleteObject(bucket, key);
			
			return true;
		} catch (Exception e) {
			LOGGER.warn("Error deleting object from S3 by key: " + key, e);
			
		}
		return false;
	}
    
}
