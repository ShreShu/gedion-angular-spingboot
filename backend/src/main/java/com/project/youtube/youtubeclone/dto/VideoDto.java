package com.project.youtube.youtubeclone.dto;

import java.util.Set;

import com.project.youtube.youtubeclone.model.VideoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

	private String id;
	private String title;
	private String description;
	private Set<String> tags;
	private String videourl;
	private VideoStatus videoStatus;
	private String thumbnailUrl;
	
}
