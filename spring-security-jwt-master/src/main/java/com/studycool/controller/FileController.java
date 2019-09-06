package com.studycool.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.studycool.model.Blogs;
import com.studycool.model.DBFile;
import com.studycool.service.impl.FileService;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/api")
public class FileController {

	
	@Autowired
    private FileService DBFileStorageService;
	
	
	
	

	@PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/file")
    public String uploadFile(@RequestPart("file") MultipartFile file,HttpServletRequest request) {
    	
    	try {
    	
    		String option=request.getParameter("option");	
    		String description=request.getParameter("description");
    		
    		return  DBFileStorageService.storeFile(file,option,description);

		} catch (Exception e) {
			e.printStackTrace();
			return null;			
		}
        
    }

  
	@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long fileId) {
        // Load file from database
    	try {
    		   DBFile dbFile = DBFileStorageService.getFile(fileId);

    	        return ResponseEntity.ok()
    	                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
    	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
    	                .body(new ByteArrayResource(dbFile.getData()));
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;			
		}
    }
	
	
	@GetMapping("/file")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public @ResponseBody ResponseEntity<?> getAll()
	{
		try {
			List<DBFile> files =DBFileStorageService.findDbFile();
	        if (files==null) {
	            return new ResponseEntity<String>("unable to get!", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<DBFile>>(files, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
}


	@DeleteMapping("/file/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") long id)
	{
		try {
			boolean result =DBFileStorageService.deleteFile(id);
	        if (!result) {
	            return new ResponseEntity<String>("unable to delete!", HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<String>("deleted", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
}
	
	
	
	@GetMapping("/file/start/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public @ResponseBody ResponseEntity<?> startFile(@PathVariable("id") long id)
	{
		try {
			DBFileStorageService.startFile(id);
	       
	        return new ResponseEntity<String>("started", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	
}