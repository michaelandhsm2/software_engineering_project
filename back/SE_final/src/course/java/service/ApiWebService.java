package course.java.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import course.java.entity.Hw;
import course.java.entity.Course;
import course.java.entity.FileEntity;
import course.java.entity.User;
import course.java.entity.UserCourse;
import course.java.entity.UserHw;
import course.java.model.ApiHwDetalModel;
import course.java.model.ApiCourseModel;
import course.java.model.ApiHwModel;
import course.java.model.ApiUserModel;
import course.java.springdata.HwRepository;
import course.java.springdata.CourseRepository;
import course.java.springdata.FileRepository;
import course.java.springdata.UserCourseRepository;
import course.java.springdata.UserHwRepository;
import course.java.springdata.UserRepository;

@RestController
@RequestMapping(value = "/api")
@Transactional("jpaTransactionManager")
public class ApiWebService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserCourseRepository userCourseRepository;
	
	@Autowired
	HwRepository hwRepository;
	
	@Autowired
	UserHwRepository userHwRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	FileRepository fileRepository;

//account
//-----------------------------------------------------------------------------------------------------------------------------------//
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ApiUserModel login(@RequestParam("account") String account, @RequestParam("password") String password) {
		User user = userRepository.findByAccount(account);
		ApiUserModel userModel = new ApiUserModel();
		if (!user.getPassword().equals(password)) {
			userModel.setMember_name("error");
			return userModel;
		}
		userModel.setMember_id(user.getId());
		userModel.setMember_name(user.getUsername());
		userModel.setPermissions(user.getRole());
		userModel.setMember_account(user.getAccount());
		return userModel;
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody String createUser(
			@RequestParam("username") String username,
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("role") String role) {
		
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setUsername(username);
		user.setRole(role);
		userRepository.save(user);
		return "OK";
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public @ResponseBody List<ApiUserModel> getUser() {
		List<User> users = (List<User>) userRepository.findAll();
		List<ApiUserModel> apiUserModels = new ArrayList<ApiUserModel>();
		for ( User user : users ) {
			ApiUserModel apiUserModel = new ApiUserModel();
			apiUserModel.setMember_id(user.getId());
			apiUserModel.setMember_name(user.getUsername());
			apiUserModel.setPermissions(user.getRole());
			apiUserModel.setMember_account(user.getAccount());;
			apiUserModels.add(apiUserModel);
		}	
		return apiUserModels;
	}
	
	@RequestMapping(value = "/getSingleUser", method = RequestMethod.POST)
	public @ResponseBody ApiUserModel getSingleUser(@RequestParam("user_id") int user_id) {
		User user = userRepository.findOne(user_id);
		ApiUserModel apiUserModel = new ApiUserModel();
		apiUserModel.setMember_id(user.getId());
		apiUserModel.setMember_name(user.getUsername());
		apiUserModel.setPermissions(user.getRole());
		apiUserModel.setMember_account(user.getAccount());
		return apiUserModel;
	}
	
	@RequestMapping(value = "/getStudents", method = RequestMethod.POST)
	public @ResponseBody List<ApiUserModel> getStudents(@RequestParam("courseId") int course_Id) {
		Course course = courseRepository.findOne(course_Id);
		List<UserCourse> userCourses = (List<UserCourse>) userCourseRepository.findByCourseId(course.getCourseId());
		List<ApiUserModel> apiUserModels = new ArrayList<ApiUserModel>();
		for ( UserCourse userCourse : userCourses ) {
			try {
				User user = userRepository.findByAccount(userCourse.getUserAccount());

				ApiUserModel apiUserModel = new ApiUserModel();
				apiUserModel.setMember_id(user.getId());
				apiUserModel.setMember_name(user.getUsername());
				apiUserModel.setPermissions(user.getRole());
				apiUserModel.setMember_account(user.getAccount());;
				apiUserModels.add(apiUserModel);
			}catch(Exception e){
				
			}
		}	
		return apiUserModels;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public @ResponseBody String deleteUser() {
		
		userRepository.deleteAll();
		return "OK";
	}

	@RequestMapping(value = "/createUserCourse", method = RequestMethod.POST)
	public @ResponseBody String createUserCourse(
			@RequestParam("studentAccount") String studentAccount,
			@RequestParam("courseId") int courseId) {
		
		UserCourse userCourse = new UserCourse();
		userCourse.setCourseId(courseId);
		userCourse.setAccount(studentAccount);
		userCourseRepository.save(userCourse);
		return "OK";
	}
	
	@RequestMapping(value = "/deleteUserCourse", method = RequestMethod.POST)
	public @ResponseBody String deleteUserCourse() {
		
		userCourseRepository.deleteAll();
		return "OK";
	}
//teacher & student
	//-----------------------------------------------------------------------------------------------------------------------------------//
	@RequestMapping(value = "/class", method = RequestMethod.POST)
	public @ResponseBody List<ApiCourseModel> getClass(@RequestParam("member_id") int id, @RequestParam("role") String list_type) {
		User user = userRepository.findOne(id);
		List<ApiCourseModel> apiSubjectModels = new ArrayList<ApiCourseModel>();
		if (list_type.equals("student")) {
			List<UserCourse> userClasses = userCourseRepository.findByUserAccount(user.getAccount());  
			for ( UserCourse userClass : userClasses) {
				Course course = courseRepository.findByCourseId(userClass.getCourseId());
				ApiCourseModel apiSubjectModelTemp = new ApiCourseModel();
				apiSubjectModelTemp.setCourse_id(course.getId());
				apiSubjectModelTemp.setCourse_name(course.getCourseName());
				apiSubjectModels.add(apiSubjectModelTemp);
			}
			return apiSubjectModels;
		}
		else if (list_type.equals("teacher")) {
			List<Course> courses = courseRepository.findByProfessor(user.getAccount());
			for (Course course : courses) {
				ApiCourseModel apiSubjectModelTemp = new ApiCourseModel();
				apiSubjectModelTemp.setCourse_id(course.getId());
				apiSubjectModelTemp.setCourse_name(course.getCourseName());
				apiSubjectModels.add(apiSubjectModelTemp);
			}
			return apiSubjectModels;
		}
		else if (list_type.equals("admin")) {
			List<Course> courses = (List<Course>) courseRepository.findAll();
			for (Course course : courses) {
				ApiCourseModel apiSubjectModelTemp = new ApiCourseModel();
				apiSubjectModelTemp.setCourse_id(course.getId());
				apiSubjectModelTemp.setCourse_name(course.getCourseName());
				apiSubjectModels.add(apiSubjectModelTemp);
			}
			return apiSubjectModels;
		}
		else
			return apiSubjectModels;
	}
	
	@RequestMapping(value = "/singleClass", method = RequestMethod.POST)
	public @ResponseBody ApiCourseModel getSingleClass(@RequestParam("course_id") int id) {
		Course course = courseRepository.findOne(id);
		ApiCourseModel apiCourseModel = new ApiCourseModel();
		apiCourseModel.setCourse_id(course.getCourseId());
		apiCourseModel.setCourse_name(course.getCourseName());
		User prof = userRepository.findByAccount(course.getProfessorAccount());
		apiCourseModel.setProfessor(prof.getUsername());
		return apiCourseModel;
	}
	
	@RequestMapping(value = "/getAllClass", method = RequestMethod.POST)
	public @ResponseBody List<ApiCourseModel> getAllClass() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		List<ApiCourseModel> apiCourseModels = new ArrayList<ApiCourseModel>();
		for ( Course course : courses ) {
			ApiCourseModel apiCourseModel = new ApiCourseModel();
			apiCourseModel.setCourse_id(course.getCourseId());
			String assistantAccount, professorAccount;
			try {
				assistantAccount = userRepository.findByAccount(course.getAssistantAccount()).getUsername();
			}catch(Exception exp){
				assistantAccount = "未知";
			}
			try {
				professorAccount = userRepository.findByAccount(course.getProfessorAccount()).getUsername();
			}catch(Exception exp){
				professorAccount = "未知";
			}
			apiCourseModel.setAssistant(assistantAccount);
			apiCourseModel.setProfessor(professorAccount);
			apiCourseModel.setCourse_name(course.getCourseName());
			apiCourseModel.setCredits(course.getCredits());
			apiCourseModel.setHours(course.getHours());
			apiCourseModel.setOutline(course.getOutline());
			apiCourseModel.setSemester(course.getSemester());
			apiCourseModels.add(apiCourseModel);
		}	
		return apiCourseModels;
	}
	
	@RequestMapping(value = "/createClass", method = RequestMethod.POST)
	public @ResponseBody String createCourse(
			@RequestParam("courseName") String courseName,
			@RequestParam("courseId") int courseId,
			@RequestParam("professorAccount") String professorAccount,
			@RequestParam("assistantAccount") String assistantAccount,
			@RequestParam("outline") String outline,
			@RequestParam("semester") String semester,
			@RequestParam("credits") float credits,
			@RequestParam("hours") int hours) {
		
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setProfessorAccount(professorAccount);
		course.setAssistantAccount(assistantAccount);
		course.setOutline(outline);
		course.setSemester(semester);
		course.setCredits(credits);
		course.setHours(hours);
		courseRepository.save(course);
		return "OK";
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
	public @ResponseBody String deleteCourse() {
		
		courseRepository.deleteAll();
		return "OK";
	}
//student
//-----------------------------------------------------------------------------------------------------------------------------------//
	@RequestMapping(value = "/hwDetail", method = RequestMethod.POST)
	public @ResponseBody List<ApiHwDetalModel> getHwDetail(
			@RequestParam("course_id") int classId, 
			@RequestParam("member_id") int userId) {
		Course course = courseRepository.findOne(classId);
		List<Hw> hws = hwRepository.findByCourseId(course.getCourseId());
		User user = userRepository.findOne(userId);
		List<ApiHwDetalModel> apiHwDetalModels = new ArrayList<ApiHwDetalModel>();
		for ( Hw hw : hws ) {
			ApiHwDetalModel apiHwDetalModel = new ApiHwDetalModel();
			apiHwDetalModel.setHw_id(hw.getId());
			apiHwDetalModel.setHw_name(hw.getHwName());
			apiHwDetalModel.setDeadline(hw.getDeadline());
			apiHwDetalModel.setLate((hw.isLate())? "開放" : "關閉");
			apiHwDetalModel.setStudentName(user.getUsername());
			UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hw.getId());
			Date date = new Date();
			if(userHw == null) {
				apiHwDetalModel.setShw_status("未繳交");
				if (date.after(hw.getDeadline()) )
					apiHwDetalModel.setShw_status("不得補交");
				apiHwDetalModel.setShw_fraction(0);
			}else {
				apiHwDetalModel.setShw_status("已繳交");
				if ( userHw.getCreatedAt().after(hw.getDeadline()) ) 
					apiHwDetalModel.setShw_status("遲交");
				apiHwDetalModel.setShw_fraction(userHw.getScore());
				apiHwDetalModel.setShw_comment(userHw.getComments());
				FileEntity file = fileRepository.findOne(userHw.getFileId());
				apiHwDetalModel.setShw_time(file.getTimestamp());
				apiHwDetalModel.setFile_id(file.getId());
				
			}
			apiHwDetalModels.add(apiHwDetalModel);		
		}
		return apiHwDetalModels;
	}
	
	@RequestMapping(value = "/unitHwContent", method = RequestMethod.POST)
	public @ResponseBody ApiHwModel getUnitHwContent(@RequestParam("hwId") int hwId) {
		Hw hw = hwRepository.findOne(hwId);
		ApiHwModel apiHwModel = new ApiHwModel();
		apiHwModel.setHw_name(hw.getHwName());
		apiHwModel.setHw_info(hw.getOutline());
		apiHwModel.setId(hw.getId());
		apiHwModel.setLate((hw.isLate())? "開放" : "關閉");
		apiHwModel.setDeadlineString(FORMATTER.format(hw.getDeadline()));
		return apiHwModel;
	}
	
	@RequestMapping(value = "/updateScore", method = RequestMethod.POST)
	public @ResponseBody String getUpdateScore(
			@RequestParam("hw_id") int hwId, 
			@RequestParam("member_id") int userId,
			@RequestParam("shw_fraction") int score,
			@RequestParam("comments") String comments) {
		User user = userRepository.findOne(userId);
		UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hwId);	
		userHw.setScore(score);
		userHw.setComments(comments);
		userHw = userHwRepository.save(userHw);
		return "OK";
	}

//teacher
//-----------------------------------------------------------------------------------------------------------------------------------//	
	@RequestMapping(value = "/hwClassAll", method = RequestMethod.POST)
	public @ResponseBody List<ApiHwModel> getHwClassAll(@RequestParam("course_id") int course_Id) {
		Course course = courseRepository.findOne(course_Id);
		List<Hw> hws = hwRepository.findAllByclassId(course.getCourseId());
		List<ApiHwModel> apiHwModels = new ArrayList<ApiHwModel>();
		for ( Hw hw : hws ) {
			ApiHwModel apiHwModel = new ApiHwModel();
			apiHwModel.setHw_name(hw.getHwName());
			apiHwModel.setLate((hw.isLate())? "開放" : "關閉");
			apiHwModel.setId(hw.getId());
			apiHwModel.setDeadline(hw.getDeadline());
			apiHwModels.add(apiHwModel);
		}	
		return apiHwModels;
	}
	
	@RequestMapping(value = "/hwAllDetail", method = RequestMethod.POST)
	public @ResponseBody List<ApiHwDetalModel> getHwAllDetail(@RequestParam("hw_id") int hwId) {

		Hw hw = hwRepository.findOne(hwId);
		List<UserCourse> userCourses = (List<UserCourse>) userCourseRepository.findByCourseId(hw.getCourseId());

		List<ApiHwDetalModel> apiHwModels = new ArrayList<ApiHwDetalModel>();
		for ( UserCourse userCourse : userCourses ) {
			User user = userRepository.findByAccount(userCourse.getUserAccount());		
			UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hwId);
			ApiHwDetalModel apiHwModel = new ApiHwDetalModel();
			apiHwModel.setStudentName(user.getUsername());
			apiHwModel.setUser_id(user.getId());
			apiHwModel.setHw_name(hw.getHwName());
			Course course = courseRepository.findByCourseId(userCourse.getCourseId());
			apiHwModel.setCourse_id(course.getId());
			Date date = new Date();
			if(userHw == null) {
				apiHwModel.setShw_status("未繳交");
				if (date.after(hw.getDeadline()) )
					apiHwModel.setShw_status("不得補交");
				apiHwModel.setShw_fraction(0);
				apiHwModel.setShw_comment("");
				apiHwModel.setShw_time(new Date());			
				apiHwModel.setFile_id(0);
			}else {
				apiHwModel.setShw_status("已繳交");
				if ( userHw.getCreatedAt().after(hw.getDeadline()) ) 
					apiHwModel.setShw_status("遲交");
				apiHwModel.setShw_fraction(userHw.getScore());
				apiHwModel.setShw_comment(userHw.getComments());
				FileEntity file = fileRepository.findOne(userHw.getFileId());
				apiHwModel.setShw_time(file.getTimestamp());
				apiHwModel.setFile_id(userHw.getFileId());
			}
			apiHwModels.add(apiHwModel);
		}
		return apiHwModels;
	}
	
	@RequestMapping(value = "/hwAllDetail2", method = RequestMethod.POST)
	public @ResponseBody List<ApiHwDetalModel> getUserAllDetail(
			@RequestParam("user_id") int userId,
			@RequestParam("course_id") int courseId) {
		
		Course course = courseRepository.findOne(courseId);
		List<Hw> hws = hwRepository.findByCourseId(course.getCourseId());		
		
		List<ApiHwDetalModel> apiHwModels = new ArrayList<ApiHwDetalModel>();
		for ( Hw hw : hws ) {
			User user = userRepository.findOne(userId);		
			UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hw.getId());
			ApiHwDetalModel apiHwModel = new ApiHwDetalModel();
			apiHwModel.setStudentName(user.getUsername());
			apiHwModel.setHw_id(hw.getId());
			apiHwModel.setUser_id(user.getId());
			apiHwModel.setHw_name(hw.getHwName());
			Date date = new Date();
			if(userHw == null) {
				apiHwModel.setShw_status("未繳交");
				if (date.after(hw.getDeadline()) )
					apiHwModel.setShw_status("不得補交");
				apiHwModel.setShw_fraction(0);
				apiHwModel.setShw_comment("");
				apiHwModel.setShw_time(new Date());			
				apiHwModel.setFile_id(0);
			}else {
				apiHwModel.setShw_status("已繳交");
				if ( userHw.getCreatedAt().after(hw.getDeadline()) ) 
					apiHwModel.setShw_status("遲交");
				apiHwModel.setShw_fraction(userHw.getScore());
				apiHwModel.setShw_comment(userHw.getComments());
				FileEntity file = fileRepository.findOne(userHw.getFileId());
				apiHwModel.setShw_time(file.getTimestamp());
				apiHwModel.setFile_id(userHw.getFileId());
			}
			apiHwModels.add(apiHwModel);
		}
		return apiHwModels;
	}
	
	@RequestMapping(value = "/hwOneDetail", method = RequestMethod.POST)
	public @ResponseBody ApiHwDetalModel getHwOneDetail(
			@RequestParam("hw_id") int hwId,
			@RequestParam("user_id") int userId) {

		User user = userRepository.findOne(userId);		
		Hw hw = hwRepository.findOne(hwId);
		Date date = new Date();
		UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hwId);
		ApiHwDetalModel apiHwModel = new ApiHwDetalModel();
		apiHwModel.setStudentName(user.getUsername());
		apiHwModel.setUser_id(user.getId());
		if(userHw == null) {
			apiHwModel.setShw_status("未繳交");
			if (date.after(hw.getDeadline()) )
				apiHwModel.setShw_status("不得補交");
			apiHwModel.setShw_fraction(0);
			apiHwModel.setShw_comment("");
			apiHwModel.setShw_time(new Date());			
			apiHwModel.setFile_id(0);
		}else {
			apiHwModel.setShw_status("已繳交");
			if ( userHw.getCreatedAt().after(hw.getDeadline()) ) 
				apiHwModel.setShw_status("遲交");
			apiHwModel.setShw_fraction(userHw.getScore());
			apiHwModel.setShw_comment(userHw.getComments());
			FileEntity file = fileRepository.findOne(userHw.getFileId());
			apiHwModel.setShw_time(file.getTimestamp());
			apiHwModel.setFile_id(userHw.getFileId());
		}
		return apiHwModel;
	}
	
	@RequestMapping(value = "/createHw", method = RequestMethod.POST)
	public @ResponseBody String createHw(
			@RequestParam("courseId") int courseId,
			@RequestParam("hwName") String hwName,
			@RequestParam("outline") String outline,
			@RequestParam("late") Boolean late,
			@RequestParam("deadline") String deadline) {
		
		Hw hw = new Hw();
		Course course = courseRepository.findOne(courseId);
		hw.setCourseId(course.getCourseId());

		hw.setDeadline(dateFormatter(deadline));
		hw.setHwName(hwName);
		hw.setOutline(outline);
		hw.setLate(late);
		hwRepository.save(hw);
		return "OK";
	}
	
	@RequestMapping(value = "/updateHw", method = RequestMethod.POST)
	public @ResponseBody String updateHw(
			@RequestParam("hwId") int hwId,
			@RequestParam("hwName") String hwName,
			@RequestParam("outline") String outline,
			@RequestParam("late") Boolean late,
			@RequestParam("deadline") String deadline) {
		
		Hw hw = hwRepository.findOne(hwId);
		hw.setDeadline(dateFormatter(deadline));
		hw.setHwName(hwName);
		hw.setOutline(outline);
		hw.setLate(late);
		hwRepository.save(hw);
		return "OK";
	}
	
	//upload/download
	//-----------------------------------------------------------------------------------------------------------------------------------//	
		
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C:\\temp\\";
    //The name of the scoring process
//    private static String SCORING_PROCESS = "C:/home/software_engineering_project/back/scoring_process_return.exe";
    private static String SCORING_PROCESS = "C:\\home\\software_engineering_project\\back\\final.exe";
//    private static String SCORING_PROCESS = "C:/temp/final.exe";
    
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-mm-dd");
   
	//	需要裝commons-file-upload-1.3.3.jar & commons-io-2.6.jar
	// Upload Bean Setting ==> WebContent/WEB-INF/bean-config 
	@RequestMapping(value = "/upload", method = RequestMethod.POST) 
	public String fileModel(@RequestParam("shw_file") MultipartFile uploadfile,
			@RequestParam("hw_id") int hwId,
			@RequestParam("member_id") int userId) {
			
			FileEntity file;
			int retValue = -1;

	        try {
	        	byte[] bytes = uploadfile.getBytes();				
	        	
	            file = new FileEntity();
				file.setFileName(uploadfile.getOriginalFilename());
				file.setIsActive(true);
				file = fileRepository.save(file);

				Path path = Paths.get(UPLOADED_FOLDER + file.getId() + "_" + file.getFileName().replace(" ", "_"));
				
	            Files.write(path, bytes);
	            
	            User user = userRepository.findOne(userId);
	    		UserHw userHw = userHwRepository.findByUserAccountAndHwId(user.getAccount(), hwId);	
	    		if(userHw == null) {
	    			userHw = new UserHw();
	    			userHw.setHwId(hwId);
	    			userHw.setUserAccount(user.getAccount());
	    		}else {
	    			FileEntity fileEntity = fileRepository.findOne(userHw.getFileId());
	    			fileEntity.setIsActive(false);
	    			fileRepository.save(fileEntity);
	    		}
	    		userHw.setFileId(file.getId());
    			userHw.setComments("");
	    		try {
	    			String testFile = UPLOADED_FOLDER + file.getId() + "_" + file.getFileName().replace(" ", "_");
    				System.out.println(SCORING_PROCESS+" "+testFile);
	    			Process cmdProc = Runtime.getRuntime().exec(SCORING_PROCESS+" "+testFile);
	    			BufferedReader stdoutReader = new BufferedReader(
	    			         new InputStreamReader(cmdProc.getInputStream()));
	    			String line;
	    			while ((line = stdoutReader.readLine()) != null) {
    				    System.out.println(line);
//	    			   // process procs standard output here
//	    				Pattern pattern = Pattern.compile("score = \\d+");
//	    				Matcher matcher = pattern.matcher(line);
//	    				if (matcher.find())
//	    				{
//	    				    System.out.println(matcher.group(1));
//	    				    retValue = Integer.parseInt(matcher.group(1));
//	    				    System.out.println(retValue);
//	    				}
	    			}

	    			BufferedReader stderrReader = new BufferedReader(
	    			         new InputStreamReader(cmdProc.getErrorStream()));
	    			while ((line = stderrReader.readLine()) != null) {
    				    System.out.println(line);
	    			   // process procs standard error here
	    			}
	    			retValue = cmdProc.exitValue();
	    			System.out.println(retValue);
	    			userHw.setScore(retValue);	    			
	    		}catch (Exception e) {
	    			retValue = -1;
	    			userHw.setScore(0);	    			
	    		}
	    		userHw = userHwRepository.save(userHw);
	        } catch (IOException e) {
	            return "BAD_REQUEST\n"+e.getMessage()+"\n"+e.getStackTrace();
	        }
	        

	        return "Successfully uploaded ("+ file.getId() +") - " + uploadfile.getOriginalFilename();

    }
	
	@RequestMapping(value = "/download/{file_id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("file_id") int id)
	        throws IOException {
		
		FileEntity fileEntity = fileRepository.findOne(id);
		
		File file = new File(UPLOADED_FOLDER+ fileEntity.getId()+ "_" + fileEntity.getFileName().replace(" ", "_"));
				
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "filename=" + fileEntity.getFileName());

	    return ResponseEntity
	            .ok()
	            .headers(headers)
	            .contentLength((int)file.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(new InputStreamResource(new FileInputStream(file)));
	}
	
	private Date dateFormatter(String string) {
        Date date;
		try {
			date = FORMATTER.parse(string);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
	
}