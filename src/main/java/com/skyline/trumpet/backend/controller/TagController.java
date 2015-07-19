package com.skyline.trumpet.backend.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skyline.trumpet.backend.model.Tag;
import com.skyline.trumpet.backend.service.TagService;

@RestController
public class TagController {
	
	@Resource
	private TagService tagService;
	
	@RequestMapping("/getTags")
	public Tag[] getTags(){
		List<Tag> tags =  tagService.getTags();
		return tags.toArray(new Tag[tags.size()]);
	}
	
	@RequestMapping(value="/newTag",method=RequestMethod.POST,consumes="application/json")
	public void createNewTag(@RequestBody Tag tag){
		tagService.insertTag(tag);
	}

}
