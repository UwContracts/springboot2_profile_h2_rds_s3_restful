package org.aviation.control.queue;

import java.util.List;

import org.aviation.control.queue.domain.entity.AircraftSizeEntity;
import org.aviation.control.queue.domain.entity.AircraftTypeEntity;
import org.aviation.control.queue.domain.repository.AircraftSizeRepository;
import org.aviation.control.queue.domain.repository.AircraftTypeRepository;
import org.aviation.control.queue.service.util.AircraftSampleDataProvider;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class Application {

	@Autowired
	private Environment environment;
	
	@Autowired
	private AircraftSizeRepository sizeRepository;
	
	@Autowired
	private AircraftTypeRepository typeRepository;
	
	@Autowired
	private AircraftSampleDataProvider dataProvider;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
	@Bean
	@Profile("!local")
	public TextEncryptor getTextEncryptor() {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(environment.getProperty("jasypt_encryptor_password"));
		return textEncryptor;
	}
	
	@Bean
    public CommandLineRunner commandLineRunner() {
		 return runner -> {
			 List<AircraftSizeEntity> aircraftSize = dataProvider.getAircraftSize();
			 aircraftSize.stream().forEach(acSize -> {
				 if (!sizeRepository.findById(acSize.getId()).isPresent()) {
					 sizeRepository.save(acSize);
				 }
			 });
			 
			 List<AircraftTypeEntity> aircraftType = dataProvider.getAircraftType();
			 aircraftType.stream().forEach(acType -> {
				 if (!typeRepository.findById(acType.getId()).isPresent()) {
					 typeRepository.save(acType);
				 }
			 });
        };
	}
	
}
