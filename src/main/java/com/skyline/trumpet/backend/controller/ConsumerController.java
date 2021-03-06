package com.skyline.trumpet.backend.controller;

import com.skyline.trumpet.backend.common.Constant;
import com.skyline.trumpet.backend.common.Coordinate;
import com.skyline.trumpet.backend.configuration.DataConfig;
import com.skyline.trumpet.backend.model.Broadcast;
import com.skyline.trumpet.backend.model.BroadcastTags;
import com.skyline.trumpet.backend.model.Greeting;
import com.skyline.trumpet.backend.model.Message;
import com.skyline.trumpet.backend.model.User;
import com.skyline.trumpet.backend.persistence.BroadcastMapper;
import com.skyline.trumpet.backend.service.BroadcastService;
import com.skyline.trumpet.backend.service.TagService;
import com.skyline.trumpet.backend.service.UserService;
import com.skyline.trumpet.backend.util.BroadcastUtil;
import com.skyline.trumpet.backend.util.UtilTools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    public static final long serialVersionUID = 1L;
    //private ServletFileUpload upload;
    private final long MAXSize = 4194304*2L;
    private String filedir = Constant.PHOTO_DIRECTORY;
    FileItemFactory factory = new DiskFileItemFactory();
    private ServletFileUpload upload = new ServletFileUpload(factory);
    
    
    @Resource
    private BroadcastService broadcastService;
    @Resource
    private TagService tagService;
    @Resource
	private UserService userService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/TestHelloworld")
    public Message greeting(){
        Message message = new Message("hello","world");
        return message;
    }
      
//    @RequestMapping(value = "/userRegister", method = RequestMethod.POST,consumes = "application/json")
//	public User userRegister(@RequestBody User user){
////		if(UtilTools.validUser(user)){
////			userService.userRegister(user);
////		}
//		return new User("1","2");
//	}
    
    
    @RequestMapping(value = "/newBroadcast", method = RequestMethod.POST, consumes = "application/json")
    public Broadcast createNewBroadcast(@RequestBody Broadcast broadcast){
    	Timestamp createdDate = new Timestamp(new Date().getTime());
    	String[] ids = broadcast.getTagsId().split(" ");
    	int[] tagIds = new int[ids.length];
    	for(int i=0;i<ids.length;i++){
    		tagIds[i] = Integer.parseInt(ids[i]);
    	}
    	broadcast.setCreatedDate(createdDate);
//    	Broadcast newBroadcast = new Broadcast(1,broadcast.getBrief(),broadcast.getDescription(),
//               1,0,createdDate,broadcast.getExpireDate(),broadcast.getLatitude(),broadcast.getLongitude());    	
    	broadcastService.insertBroadcast(broadcast);
    	tagService.updateRelationship(broadcast.getId(), tagIds);
    	return broadcast;
//    	System.out.println("the result of insertion is : "+ result);
//    	return result;
    }
    
    @RequestMapping(value = "/addNewBroadcast", method = RequestMethod.POST)
    public Broadcast addNewBroadcast(@RequestBody Broadcast broadcast){
    	Timestamp createdDate = new Timestamp(new Date().getTime());
    	String[] ids = broadcast.getTagsId().split(" ");
    	int[] tagIds = new int[ids.length];
    	for(int i=0;i<ids.length;i++){
    		tagIds[i] = Integer.parseInt(ids[i]);
    	}
    	broadcast.setCreatedDate(createdDate);
//    	Broadcast newBroadcast = new Broadcast(1,broadcast.getBrief(),broadcast.getDescription(),
//               1,0,createdDate,broadcast.getExpireDate(),broadcast.getLatitude(),broadcast.getLongitude());    	
    	broadcastService.insertBroadcast(broadcast);
    	tagService.updateRelationship(broadcast.getId(), tagIds);
    	return broadcast;
//    	System.out.println("the result of insertion is : "+ result);
//    	return result;
    }
    
    @RequestMapping(value = "/getBroadcastsInDefaultRange", params={"latitude","longitude"},method = RequestMethod.GET)
    public Broadcast[] retrieveBroadcastsInDefaultRange(@RequestParam(value="latitude") double latitude,
    										@RequestParam(value="longitude") double longitude){ 
    	Map<String,Coordinate> coordinateRange = UtilTools.constructDefaultCoordinateRange(latitude, longitude);
    	List<Broadcast> broadcasts = broadcastService.getBroadcastsInDefaultRange(coordinateRange);
    	return broadcasts.toArray(new Broadcast[broadcasts.size()]);
    }	
    
    
    @RequestMapping(value = "/getBroadcastsByDate")
    public Broadcast[] retrieveBroadcastOrderByDateDesc(){
    	List<Broadcast> broadcasts = broadcastService.getBroadcastsOrderByDateDesc();
    	return broadcasts.toArray(new Broadcast[broadcasts.size()]);
    }
    
    
    @RequestMapping(value = "/newBroadcastWithPhoto")
    public void newBroadcastWithPhoto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
    	List<String> uploadFilesPath = new ArrayList<String>();
    	upload.setSizeMax(MAXSize);
    	PrintWriter out = response.getWriter();
    	try {
			List<FileItem> items = this.upload.parseRequest(request);
			if(items!=null && !items.isEmpty()){
				for(FileItem fileItem : items){
					String name = fileItem.getFieldName();
					//if value is form value
					if(fileItem.isFormField()){
						String value = fileItem.getString();
						request.setAttribute(name, value);
					}else{//if not form value
						String fileName = fileItem.getName();
						String filePath = filedir + File.separator + fileName;
						System.out.println("the path for saving file is:"+filePath);
						File file = new File(filePath);
						InputStream inputStream = fileItem.getInputStream();
						BufferedInputStream buffInputStream = new BufferedInputStream(inputStream);
						FileOutputStream fos = new FileOutputStream(file);
						int len;
						while((len=buffInputStream.read())!= -1){
							fos.write(len);
						}
						fos.flush();
						fos.close();
						buffInputStream.close();
						inputStream.close();
						uploadFilesPath.add(filePath);
						System.out.println("file "+fileName+"upload success");
					}
				}
				System.out.println("file upload successed");
				Broadcast broadcast = BroadcastUtil.constructNewBroadcast(request,uploadFilesPath);
				RequestDispatcher rd = request.getRequestDispatcher("/addNewBroadcast");
				rd.forward(request, response);
				out.write("files upload success");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.write("files upload failed"+e.getMessage());
		} 
	    
	    
    }
    
}