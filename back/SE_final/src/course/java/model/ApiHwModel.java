package course.java.model;

import java.util.Date;

public class ApiHwModel {
	private String hw_name;
	private String hw_info;
	private Date deadline;
	private String deadlineString;
	private String late;
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getHw_name() {
		return hw_name;
	}
	public void setHw_name(String hw_name) {
		this.hw_name = hw_name;
	}
	public String getHw_info() {
		return hw_info;
	}
	public void setHw_info(String hw_info) {
		this.hw_info = hw_info;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getDeadlineString() {
		return deadlineString;
	}
	public void setDeadlineString(String deadlineString) {
		this.deadlineString = deadlineString;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	
}
