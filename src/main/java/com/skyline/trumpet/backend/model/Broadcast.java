package com.skyline.trumpet.backend.model;
import java.sql.Timestamp;

import org.joda.time.DateTime;

/**
 * Created by Administrator on 2015/7/3.
 */
public class Broadcast {
    private long id;
    private long userId;
    private String author;
    private int type;
    private String brief;
    private String description;
    private int amount;
    private int count;
    private Timestamp createdDate;
    private Timestamp expireDate;
   	private double latitude;
 	private double longitude;
	private String tags;
	private String tagsId;
	private String avatarUrl;
	private String photoUrl;

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getAvatarUrl() {
	    return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
	    this.avatarUrl = avatarUrl;
	}
	
    public String getTagsId() {

        return tagsId;
    }

    public void setTagsId(String tagsId) {
        this.tagsId = tagsId;
    }

    public Broadcast(){}
//    //constructor for app
//    public ReservePost(int type, String title, String description, int amount, int count){
//        this.type = type;
//        this.title = title;
//        this.description = description;
//        this.amount = amount;
//        this.count = count;
//        this.currentDate = new DateTime();
//    }

    public Broadcast(long userId,String author,int type, String description, double latitude, 
    				double longitude, String avatarUrl, String photoUrl){
    	this.userId = userId;
        this.author = author;
    	this.type = type;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avatarUrl = avatarUrl;
        this.photoUrl = photoUrl;
    }
    
    //constructor for backend
    public Broadcast(long userId,String author,int type, String brief, String description, int amount, 
    				int count, Timestamp createdDate, Timestamp expireDate, double latitude, double longitude){
        this.userId = userId;
        this.author = author;
    	this.type = type;
        this.brief = brief;
        this.description = description;
        this.amount = amount;
        this.count = count;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.latitude = latitude;
        this.longitude = longitude;
        
    }
    
    public Broadcast(long userId,String author,int type, String brief, String description, int amount, 
    					int count, Timestamp createdDate, Timestamp expireDate, String tags, double latitude, double longitude,
    					String avatarUrl, String photoUrl){
        this.userId = userId;
        this.author = author;
    	this.type = type;
        this.brief = brief;
        this.description = description;
        this.amount = amount;
        this.count = count;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.tags = tags;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avatarUrl = avatarUrl;
        this.photoUrl = photoUrl;
        
    }

    public String toString(){
        return getType() + getBrief() + getDescription() + getCreatedDate() + getExpireDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCreatedDate(Timestamp createdDate){
        this.createdDate = createdDate;
    }

    public Timestamp getCreatedDate(){
        return createdDate;
    }
    
    public Timestamp getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}
    
    public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}