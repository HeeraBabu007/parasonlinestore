package com.mvc.springboot.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

	@PostMapping("/upload")
	public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileBasePath="D:\\eclipse project\\paras project\\EmployeeSpringBootWithJPA\\src\\main\\resources\\upload\\";
		Path path = Paths.get(fileBasePath + fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(fileName)
				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}
	
	@PostMapping("/multi-upload")
	public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files) {
		List<Object> fileDownloadUrls = new ArrayList<>();
		Arrays.asList(files)
				.stream()
				.forEach(file -> fileDownloadUrls.add(uploadToLocalFileSystem(file).getBody()));
		return ResponseEntity.ok(fileDownloadUrls);
	}
	
	@PostMapping("/upload-extra-param")
	public ResponseEntity uploadWithExtraParams(@RequestParam("file") MultipartFile file, @RequestParam String extraParam) {
		//logger.info("Extra param " + extraParam);
		return uploadToLocalFileSystem(file);
	}
	
/*
https://www.devglan.com/spring-boot/spring-boot-file-upload-download
Spring Boot File Upload to Database

application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.user.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

*/
}
