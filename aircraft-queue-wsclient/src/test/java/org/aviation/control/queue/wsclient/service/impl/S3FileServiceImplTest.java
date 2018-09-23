package org.aviation.control.queue.wsclient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Optional;

import org.aviation.control.queue.service.config.AbstractWsclientTest;
import org.aviation.control.queue.wsclient.service.S3FileService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.Upload;

public class S3FileServiceImplTest extends AbstractWsclientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(S3FileServiceImplTest.class);

	private static final String S3_FILE_KEY = "028876b8-3030-41e1-b7bb-687b737f70b0";

	private static final String FILE_NAME = "NewTab.pdf";

	@Value("${s3_bucketName}")
	private String bucketName;
	
	@Autowired
	private S3FileService s3FileService;
	
	@Test
	public void uploadFile() {
		try {
			File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
			
			Upload uploadFile = s3FileService.uploadFile(bucketName, S3_FILE_KEY, file);
			uploadFile.waitForCompletion();
			
			LOGGER.info("New file is uploaded as key: {}", S3_FILE_KEY);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void downloadFile() {
		File file = null;
		try {
			file = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".tmp");
			LOGGER.info("Temp file created at: {}", file.getAbsolutePath());
			
			Download downloadFile = s3FileService.downloadFile(bucketName, S3_FILE_KEY, file);
			downloadFile.waitForCompletion();
			
			assertThat(file).isNotNull();
			assertThat(file.exists()).isTrue();
			assertThat(file.canRead()).isTrue();
			
			LOGGER.info("Temp file downloaded at: {}", file.getAbsolutePath());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		} finally {
			if (file != null) {
				file.delete();
			}
		}
	}

	@Test
	public void saveFile() {
		try {
			File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
			PutObjectResult s3Obj = s3FileService.saveFile(bucketName, S3_FILE_KEY, file);
			
			assertThat(s3Obj).isNotNull();
			
			LOGGER.info("New file is uploaded as key: {}, versionId: {}", S3_FILE_KEY, s3Obj.getVersionId());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void getFile() {
		File file = null;
		try {
			Optional<File> fileOpt = s3FileService.getFile(bucketName, S3_FILE_KEY);
			
			assertThat(fileOpt.isPresent()).isTrue();
			file = fileOpt.get();
			
			assertThat(file.exists()).isTrue();
			assertThat(file.canRead()).isTrue();
			
			LOGGER.info(file.getAbsolutePath());
		} finally {
			if (file != null) {
				file.delete();
			}
		}
	}

}
