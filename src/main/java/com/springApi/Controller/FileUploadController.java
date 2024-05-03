package com.springApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.springApi.helper.*;
@RestController
public class FileUploadController {

	@Autowired
    private fileUploadHelper fileUploadHelper;

	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		System.out.println(((file.getSize()/8)/8)+"MB");
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		try {
			
			//validation
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must conatin file");
			}
			if(!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpeg file is allowed in content type");
			}
			
		  boolean f =fileUploadHelper.uploadfile(file);
		  
		  if(f) {
			return ResponseEntity.ok("File is upload Successfully");  
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong !..? Try again.");
	}
	
}
