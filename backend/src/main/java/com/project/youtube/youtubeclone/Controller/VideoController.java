package com.project.youtube.youtubeclone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.youtube.youtubeclone.Service.VideoService;
import com.project.youtube.youtubeclone.dto.UploadVideoresponse;
import com.project.youtube.youtubeclone.dto.VideoDto;
import com.project.youtube.youtubeclone.model.Vedio;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@PostMapping("/upload")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UploadVideoresponse uploadVideo(@RequestParam("file") MultipartFile file ) {
		if(file.isEmpty()) {
		System.out.println("file is empty");
		}
		else {
			System.out.println("No Empty");
		}
		return videoService.uploadVideo(file);
		
	}
	
	//need to check
	@PostMapping("/thumbnail")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String uploadthumbnail(@RequestParam("file") MultipartFile file,@RequestParam("videoId") String videoId) {
		if(file.isEmpty()) {
		System.out.println("file is empty");
		}
		else {
			System.out.println("No Empty");
		}
		return videoService.uploadthumbnail(file,videoId);
		
	}
	
	@PutMapping("/edit")
	@ResponseStatus(code=HttpStatus.OK)
	public VideoDto editVideoMetadata(@RequestBody VideoDto videodto) {
		return videoService.editVideo(videodto);
	}
	
	@GetMapping("/allvideos")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Vedio> allvedios(){
		return videoService.allVideos();
	}
	
	@GetMapping("/video/{id}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public Vedio video(@PathVariable String id) {
		
		return videoService.getVideoById(id);
	}
}
