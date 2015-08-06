package com.skyline.trumpet.backend.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.skyline.trumpet.backend.common.Constant;
import com.skyline.trumpet.backend.model.Broadcast;

public class BroadcastUtil {
	public static Broadcast constructNewBroadcast(HttpServletRequest request,List<String> uploadFilesPath){
		Broadcast broadcast = null;
		StringBuilder sb = new StringBuilder();
		long userId = Long.parseLong(request.getParameter("userId"));
		String author = request.getParameter("author");
		int type = Integer.parseInt(request.getParameter("type"));
		String description = request.getParameter("description");
		String avatarUrl = request.getParameter("avatarUrl");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		for(String filePath : uploadFilesPath){
			sb.append(filePath);
			sb.append(Constant.PHOTO_PATH_SEPERATOR);
		}
		String photoUrl = sb.toString();
		
		switch(type){
			case Constant.BROADCAST_GALLERY:
				broadcast = new Broadcast(userId,author,type,description,latitude,longitude,avatarUrl,photoUrl);
				break;
			case Constant.BROADCAST_SPORTS:
				String brief = request.getParameter("brief");
				String tags = request.getParameter("tags");
				break;
			case Constant.BROADCAST_DIET:
				
				break;
			case Constant.BROADCAST_INVITATION:
				
				break;
		}
					
		return broadcast;
	}

}
