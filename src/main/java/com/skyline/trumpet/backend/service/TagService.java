package com.skyline.trumpet.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyline.trumpet.backend.model.Tag;
import com.skyline.trumpet.backend.persistence.TagMapper;

@Service
public class TagService {
	@Autowired
	private TagMapper tagMapper;
	
	public void insertTag(Tag tag){
		tagMapper.insertTag(tag);
	}
	
	public List<Tag> getTags(){
		return tagMapper.getTags();
	}
	
	public void updateRelationship(long broadcast_id, int[] tag_id){
		int id = (int)broadcast_id;
		for(int i=0;i<tag_id.length;i++){
			tagMapper.insertTagRelation(tag_id[i], id);
		}
	}
	
}
