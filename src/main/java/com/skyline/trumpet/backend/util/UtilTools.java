package com.skyline.trumpet.backend.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.skyline.trumpet.backend.common.Constant;
import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.model.User;

public class UtilTools {
	
	public static Map<String,Coordinate> constructDefaultCoordinateRange(double latitude, double longitude){
		Map<String,Coordinate> coordinateRange = new HashMap<String,Coordinate>();
		BigDecimal lati = new BigDecimal(latitude);
		BigDecimal longi = new BigDecimal(longitude);
		BigDecimal twoKilometer = new BigDecimal(Constant.TWO_KILOMETER);
		Coordinate ceilingRange = new Coordinate(lati.add(twoKilometer).doubleValue(),longi.add(twoKilometer).doubleValue());
		Coordinate floorRange = new Coordinate(lati.subtract(twoKilometer).doubleValue(),longi.subtract(twoKilometer).doubleValue());
		coordinateRange.put("ceilingRange", ceilingRange);
		coordinateRange.put("floorRange",floorRange);
		return coordinateRange;
	}
	
	public static boolean validUser(User user){
		if (user == null)
			return false;
		else if(user.getUserName()==null || user.getUserName().trim().equals("")
				||user.getPassword()==null || user.getPassword().trim().equals(""))
			return false;
		return true;
	}

}
