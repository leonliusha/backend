package com.skyline.trumpet.backend.controller;

import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.configuration.DataConfig;
import com.skyline.trumpet.backend.model.Broadcast;
import com.skyline.trumpet.backend.model.Greeting;
import com.skyline.trumpet.backend.model.Message;
import com.skyline.trumpet.backend.persistence.BroadcastMapper;
import com.skyline.trumpet.backend.service.BroadcastService;
import com.skyline.trumpet.backend.util.UtilTools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/7/3.
 */

@RestController
public class ConsumerController {
	//private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
    
    @Resource
    private BroadcastService broadcastService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/helloworld")
    public Message greeting(){
        Message message = new Message("hello","world");
        return message;
    }
    
    
    @RequestMapping(value = "/newBroadcast", method = RequestMethod.POST, consumes = "application/json")
    public Broadcast createNewBroadcast(@RequestBody Broadcast broadcast){
    	Timestamp createdDate = new Timestamp(new Date().getTime());
    	Broadcast newBroadcast = new Broadcast(1,broadcast.getBrief(),broadcast.getDescription(),
               1,0,createdDate,broadcast.getExpireDate(),broadcast.getLatitude(),broadcast.getLongitude());    	
    	System.out.println(newBroadcast.toString());
    	broadcastService.insertBroadcast(newBroadcast);
    	return newBroadcast;
//    	System.out.println("the result of insertion is : "+ result);
//    	return result;
    	
    }
    
    @RequestMapping(value = "/getBroadcastsInDefaultRange", params={"latitude","longitude"},method = RequestMethod.GET)
    public Broadcast[] retrieveBroadcastsInDefaultRange(@RequestParam(value="latitude") double latitude,
    										@RequestParam(value="longitude") double longitude){ 
    	
    	Map<String,Coordinate> coordinateRange = UtilTools.constructDefaultCoordinateRange(latitude, longitude);
    	List<Broadcast> broadcasts = broadcastService.getBroadcastsInDefaultRange(coordinateRange);
//    	Broadcast newBroadcast1 = new Broadcast(1,"这是我的第一个微播","不错哦",
//                1,0,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),31.145491,121.093407);
//    	Broadcast newBroadcast2 = new Broadcast(1,"这是我的第2个微播","不错哦",
//                1,0,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),31.145491,121.103407);
//    	Broadcast newBroadcast3 = new Broadcast(1,"这是我的第3个微播","不错哦",
//                1,0,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),31.125491,121.093407);
//    	Broadcast newBroadcast4 = new Broadcast(1,"这是我的第4个微播","不错哦",
//                1,0,new Timestamp(new Date().getTime()),new Timestamp(new Date().getTime()),31.125491,121.103407);
//    	broadcasts.add(newBroadcast4);
//    	broadcasts.add(newBroadcast3);
//    	broadcasts.add(newBroadcast2);
//    	broadcasts.add(newBroadcast1);
    	System.out.println("the size of broadcasts is :"+broadcasts.size());
    	return broadcasts.toArray(new Broadcast[broadcasts.size()]);
    }
}