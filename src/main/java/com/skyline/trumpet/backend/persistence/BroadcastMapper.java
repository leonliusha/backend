package com.skyline.trumpet.backend.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.model.Broadcast;

public interface BroadcastMapper {
	@Options(useGeneratedKeys=true,keyProperty="id", keyColumn="id")
	@Insert("INSERT INTO broadcast(user_id,author,broadcast_type,brief,description,amount,count,created_date,expire_date,latitude,longitude,tags) VALUES(#{userId},#{author},#{type},#{brief},#{description},#{amount},#{count},#{createdDate},#{expireDate},#{latitude},#{longitude},#{tags})")
	void insertBroadcast(Broadcast broadcast);
	
	@Results(value={
			@Result(column="broadcast_type", property="type"),
			@Result(column="created_date", property="createdDate"),
			@Result(column="expire_date", property="expireDate"),
			@Result(column="user_id", property="userId")
	}
			)
	@Select("select * from broadcast where expire_date > #{current_timeStamp} and latitude between #{floorLatitude} and #{ceilingLatitude} and longitude between #{floorLongitude} and #{ceilingLongitude};")
	List<Broadcast> getBroadcastsInDefaultRange(@Param("current_timeStamp") Timestamp current_timeStamp, @Param("ceilingLatitude") double ceilingLatitude,@Param("floorLatitude")double floorLatitude,@Param("ceilingLongitude")double ceilingLongitude,@Param("floorLongitude")double floorLongitude);

	
	//TO DO
	//this @Result is UGLY, UGLY, trying to figure out a way to address them only ONCE!!!
	@Results(value={
			@Result(column="broadcast_type", property="type"),
			@Result(column="created_date", property="createdDate"),
			@Result(column="expire_date", property="expireDate"),
			@Result(column="user_id", property="userId")
	}
			)
	@Select("select * from broadcast order by created_date desc;")
	List<Broadcast> getBroadcastsOrderByDateDesc();
	
	@Results(value={
			@Result(column="broadcast_type", property="type"),
			@Result(column="created_date", property="createdDate"),
			@Result(column="expire_date", property="expireDate"),
			@Result(column="user_id", property="userId")
	}
			)
	@Select("select * from broadcast where user_id = #{userId}")
	List<Broadcast> getBroadcastByUserId(@Param("userId") long userId);
}
