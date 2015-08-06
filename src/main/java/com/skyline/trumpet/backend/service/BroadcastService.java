package com.skyline.trumpet.backend.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.configuration.DataConfig;
import com.skyline.trumpet.backend.model.Broadcast;
import com.skyline.trumpet.backend.persistence.BroadcastMapper;

@Service

public class BroadcastService{
	@Autowired
	private BroadcastMapper broadcastMapper;
	
	public void insertBroadcast(Broadcast broadcast){		
		broadcastMapper.insertBroadcast(broadcast);;
	}
	
	public List<Broadcast> getBroadcastsInDefaultRange(Map<String, Coordinate> coordinateRange){
		Coordinate ceilingRange = coordinateRange.get("ceilingRange");
		Coordinate floorRange = coordinateRange.get("floorRange");
		double ceilingLatitude = ceilingRange.getLatitude();
		double ceilingLongitude = ceilingRange.getLongitude();
		double floorLatitude = floorRange.getLatitude();
		double floorLongitude = floorRange.getLongitude();
		Timestamp current_timeStamp = new Timestamp(new Date().getTime());
		return broadcastMapper.getBroadcastsInDefaultRange(current_timeStamp,ceilingLatitude,floorLatitude,ceilingLongitude,floorLongitude);
	}
	
	public List<Broadcast> getBroadcastsOrderByDateDesc(){
		return broadcastMapper.getBroadcastsOrderByDateDesc();
	}

}
