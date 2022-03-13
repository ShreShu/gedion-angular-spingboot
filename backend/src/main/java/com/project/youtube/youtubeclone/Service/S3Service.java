package com.project.youtube.youtubeclone.Service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{

	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Override
	public String uploadFile(MultipartFile file) {
	
		//prepare a unique for file
	String filenameExtension= StringUtils.getFilenameExtension(file.getOriginalFilename());
	String key=UUID.randomUUID().toString()+"."+filenameExtension;
	
	ObjectMetadata metadata = new ObjectMetadata();
	metadata.setContentLength(file.getSize());
	metadata.setContentType(file.getContentType());
	
	
	//upload file to S3
	try {
	amazonS3Client.putObject("skyoutubebucket",key,file.getInputStream(),metadata);
	} catch(IOException ioex) {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"An exception Occured while uploading the file");
	}
	
	
	amazonS3Client.setObjectAcl("skyoutubebucket",key,CannedAccessControlList.PublicRead);
	
	return amazonS3Client.getResourceUrl("skyoutubebucket", key);
	}
}
