package course.java.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "[COURSE]")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "COURSEID", unique=true)
	private int courseId;
	
	@Column(name = "COURSENAME")
	private String courseName;
	
	@Column(name = "PROFESSORACCOUNT")
	private String professorAccount;
	
	@Column(name = "ASSISTANTACCOUNT")
	private String assistantAccount;
	
	@Column(name = "OUTLINE")
	private String outline;
	
	@Column(name = "SEMESTER")
	private String semester;
	
	@Column(name = "CREDITS")
	private float credits;
	
	@Column(name = "HOURS")
	private int hours;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public float getCredits() {
		return credits;
	}

	public void setCredits(float credits) {
		this.credits = credits;
	}
	
	public int getHours() {
		return hours;
	}
	
	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getProfessorAccount() {
		return professorAccount;
	}

	public void setProfessorAccount(String professorAccount) {
		this.professorAccount = professorAccount;
	}

	public String getAssistantAccount() {
		return assistantAccount;
	}

	public void setAssistantAccount(String assistantAccount) {
		this.assistantAccount = assistantAccount;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

}
