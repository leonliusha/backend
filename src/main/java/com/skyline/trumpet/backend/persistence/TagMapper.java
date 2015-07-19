package com.skyline.trumpet.backend.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skyline.trumpet.backend.model.Tag;

public interface TagMapper {
	
	@Insert("Insert into tag(tag_name,tag_count)VALUES(#{tag_name},#{tag_count}) ")
	void insertTag(Tag tag);
	
	@Select("Select * from tag")
	List<Tag> getTags();
	
	@Update("Update tag set tag_count = tag_count + 1 where tag_name = #{tag_name}")
	void updateTagCount(Tag tag);
		
	@Insert("Insert into tag_relation(tag_id,broadcast_id) VALUES (#{tag_id},#{broadcast_id})")
	void insertTagRelation(@Param("tag_id")int tag_id, @Param("broadcast_id")int broadcast_id);

}
