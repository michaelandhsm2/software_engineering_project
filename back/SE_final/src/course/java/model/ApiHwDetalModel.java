package course.java.model;

import java.util.Date;

public class ApiHwDetalModel {
	private int hw_id;
	private String hw_name;
	private String studentName;
	private int shw_fraction;
	private String shw_comment;
	private Date deadline;
	private Date shw_time;
	private String shw_status;
	private String late;
	private int file_id;
	private int user_id;
	private int course_id;
	
	public int getHw_id() {
		return hw_id;
	}
	public void setHw_id(int hw_id) {
		this.hw_id = hw_id;
	}

	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getHw_name() {
		return hw_name;
	}
	public void setHw_name(String hw_name) {
		this.hw_name = hw_name;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getShw_fraction() {
		return shw_fraction;
	}
	public void setShw_fraction(int shw_fraction) {
		this.shw_fraction = shw_fraction;
	}
	public String getShw_comment() {
		return shw_comment;
	}
	public void setShw_comment(String shw_comment) {
		this.shw_comment = shw_comment;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getShw_time() {
		return shw_time;
	}
	public void setShw_time(Date shw_time) {
		this.shw_time = shw_time;
	}
	public String getShw_status() {
		return shw_status;
	}
	public void setShw_status(String shw_status) {
		this.shw_status = shw_status;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}

	
}
