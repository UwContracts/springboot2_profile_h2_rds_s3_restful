package org.aviation.control.queue.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.aviation.control.queue.wsclient.service.S3FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.transfer.Upload;

@RestController
@RequestMapping(value = "/api")
public class S3FileController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(S3FileController.class);

	@Value("${s3_bucketName}")
	private String bucketName;
	
	@Value("${java.io.tmpdir}")
	private String tmpdir;
	
	@Autowired
	private S3FileService s3FileService;
	
	@RequestMapping(value = "/delete/{key}", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(@PathVariable("key") String key) {
		return s3FileService.deleteFile(bucketName, key); 
	}
	
	@RequestMapping(value = "/download/{key}", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] download(@PathVariable("key") String key) {
		
		Optional<File> fileOpt = s3FileService.getFile(bucketName, key);
		if (fileOpt.isPresent()) {
			File file = fileOpt.get();
			try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
				
				return IOUtils.toByteArray(input);
				
			} catch (Exception e) {
				LOGGER.error("Error reading file downloaded from S3", e);

			} finally  {
				file.delete();
			}
		}
		return null;
	}	
	
	@RequestMapping(value = "/upload", method = {RequestMethod.POST, RequestMethod.PUT})
    public Map<String, String> upload(@RequestPart(value = "file") MultipartFile multipartFile) {
		
		Map<String, String> result = new HashMap<>();
		result.put("result", Boolean.FALSE.toString());
		
		if (!multipartFile.isEmpty()) {
			String key = UUID.randomUUID().toString();
			File file = null;
			try {
				file = this.convertMultiPartToFile(multipartFile);
				
				Upload upload = s3FileService.uploadFile(bucketName, key, file);
				upload.waitForCompletion();
				
				result.put("result", key);
				
			} catch (Exception e) {
				LOGGER.error("Error uploading file to S3", e);
				result.put("result", e.getMessage());
			} finally {
				if (file != null) {
					file.delete();
				}
			}
		}
        return result;
    }

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File tempFile = new File(tmpdir + File.separator + file.getOriginalFilename());
	    OutputStream output = new BufferedOutputStream(new FileOutputStream(tempFile));
	    try {
			IOUtils.copy(file.getInputStream(), output);
		} finally {
			IOUtils.closeQuietly(file.getInputStream());
			IOUtils.closeQuietly(output);
		}
		LOGGER.info("File saved at {}", tempFile.getAbsolutePath());
	    return tempFile;
	}
	
}
