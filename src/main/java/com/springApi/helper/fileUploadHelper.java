package com.springApi.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class fileUploadHelper {

	public final String UPLOAD_DIR = "D:\\images";

	public boolean uploadfile(MultipartFile multipartfile) {

		boolean f = false;

		try {
			Files.copy(multipartfile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + multipartfile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
