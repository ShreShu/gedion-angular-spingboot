package com.project.youtube.youtubeclone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.youtube.youtubeclone.Repository.VideoRepository;
import com.project.youtube.youtubeclone.dto.UploadVideoresponse;
import com.project.youtube.youtubeclone.dto.VideoDto;
import com.project.youtube.youtubeclone.model.Vedio;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService {

	@Autowired
	private S3Service s3Service;

	@Autowired
	private VideoRepository videoRepository;
	public UploadVideoresponse uploadVideo(MultipartFile file) {
		//upload file to S3
		String vedioUrl=s3Service.uploadFile(file);
		//Save Video data to database
		Vedio video= new Vedio();
		video.setVideourl(vedioUrl);
		
	Vedio savedVideo=videoRepository.save(video);
	return new UploadVideoresponse(savedVideo.getId(),savedVideo.getVideourl());
	}
	
	
	public VideoDto editVideo(VideoDto videoDto ) {
		Vedio savedVideo= videoRepository.findById(videoDto.getId()).orElseThrow(()-> new IllegalArgumentException("Cannot find Vedio by Id"+videoDto.getId()));
		
		savedVideo.setTitle(videoDto.getTitle());
		savedVideo.setDescription(videoDto.getDescription());
		savedVideo.setTags(videoDto.getTags());
		savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
		savedVideo.setVideoStatus(videoDto.getVideoStatus());
		
		videoRepository.save(savedVideo);
		return videoDto;
		
	}
	
	public String uploadthumbnail(MultipartFile file,String videoId) {
		Vedio savedVideo= videoRepository.findById(videoId).orElseThrow(()-> new IllegalArgumentException("Cannot find Vedio by Id"+videoId));
		String thumbnail= s3Service.uploadFile(file);
		savedVideo.setThumbnailUrl(thumbnail);
		videoRepository.save(savedVideo);
		
		return thumbnail;
	}
	
	public List<Vedio> allVideos(){
		return videoRepository.findAll();
	}
	
	public Vedio getVideoById(String id) {
		
		return videoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No video found of this id"));
	}
}
