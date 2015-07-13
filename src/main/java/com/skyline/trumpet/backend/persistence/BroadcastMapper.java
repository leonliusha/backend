package com.skyline.trumpet.backend.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.model.Broadcast;

public interface BroadcastMapper {
	@Insert("INSERT INTO broadcast(broadcast_type,brief,description,amount,count,created_date,expire_date,latitude,longitude) VALUES(#{type},#{brief},#{description},#{amount},#{count},#{createdDate},#{expireDate},#{latitude},#{longitude})")
	void insertBroadcast(Broadcast broadcast);
	
	@Results(value={
			@Result(column="broadcast_type", property="type"),
			@Result(column="created_date", property="createdDate"),
			@Result(column="expire_date", property="expireDate")
	}
			)
	@Select("select * from broadcast where latitude between #{floorLatitude} and #{ceilingLatitude} and longitude between #{floorLongitude} and #{ceilingLongitude};")
	List<Broadcast> getBroadcastsInDefaultRange(@Param("ceilingLatitude") double ceilingLatitude,@Param("floorLatitude")double floorLatitude,@Param("ceilingLongitude")double ceilingLongitude,@Param("floorLongitude")double floorLongitude);

}
