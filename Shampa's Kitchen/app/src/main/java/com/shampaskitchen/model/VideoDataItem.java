package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoDataItem{

	@JsonProperty("video_link")
	private String videoLink;

	@JsonProperty("video_title")
	private String videoTitle;

	@JsonProperty("video_status")
	private String videoStatus;

	@JsonProperty("video_id")
	private String videoId;

	public void setVideoLink(String videoLink){
		this.videoLink = videoLink;
	}

	public String getVideoLink(){
		return videoLink;
	}

	public void setVideoTitle(String videoTitle){
		this.videoTitle = videoTitle;
	}

	public String getVideoTitle(){
		return videoTitle;
	}

	public void setVideoStatus(String videoStatus){
		this.videoStatus = videoStatus;
	}

	public String getVideoStatus(){
		return videoStatus;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	@Override
 	public String toString(){
		return 
			"VideoDataItem{" + 
			"video_link = '" + videoLink + '\'' + 
			",video_title = '" + videoTitle + '\'' + 
			",video_status = '" + videoStatus + '\'' + 
			",video_id = '" + videoId + '\'' + 
			"}";
		}
}