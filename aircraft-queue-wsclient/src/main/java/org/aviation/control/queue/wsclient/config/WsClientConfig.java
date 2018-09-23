package org.aviation.control.queue.wsclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

@Configuration
public class WsClientConfig {

	@Value("${s3_accelerateModeEnabled:false}")
	private boolean accelerateModeEnabled;
	
	@Value("${s3_region}")
	private String region;
	
	@Value("${s3_accessId}")
	private String accessId;
	
	@Value("${s3_accessKey}")
	private String accessKey;
	
	@Value("${s3_connectionTimeout:2000}")
	private int connectionTimeout;
	
	@Value("${s3_requestTimeout:10000}")
	private int requestTimeout;
	
	@Value("${s3_socketTimeout:10000}")
	private int socketTimeout;
	
	@Bean
	public AmazonS3 getAmazonS3() {
		
		ClientConfiguration config = new ClientConfigurationFactory().getConfig();
		config.setConnectionTimeout(connectionTimeout);
		config.setRequestTimeout(requestTimeout);
		config.setSocketTimeout(socketTimeout);
				
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessId, accessKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withAccelerateModeEnabled(accelerateModeEnabled)
					.withRegion(Regions.fromName(region))
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.withClientConfiguration(config)
					.build();		
		
        return s3Client;
    }

	@Bean
	@Autowired
	public TransferManager getTransferManager(AmazonS3 amazonS3) {
		return TransferManagerBuilder.standard().withS3Client(amazonS3).build();
	}
    
}
