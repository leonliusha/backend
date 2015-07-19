package com.skyline.trumpet.backend.model;

import java.util.List;

public class BroadcastTags {

	    private Broadcast broadcast;
	    private Integer[] tags;
	    public BroadcastTags(){};
	    public BroadcastTags(Broadcast broadcast,Integer[] tags){
	        this.broadcast = broadcast;
	        this.tags = tags;
	    }
	    public Broadcast getBroadcast() {
	        return broadcast;
	    }

	    public void setBroadcast(Broadcast broadcast) {
	        this.broadcast = broadcast;
	    }

	    public Integer[] getTags() {
	        return tags;
	    }

	    public void setTags(Integer[] tags) {
	        this.tags = tags;
	    }

	    
	    
	    
	
}
