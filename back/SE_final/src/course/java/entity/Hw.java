package course.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "[HW]")
public class Hw implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "COURSEID")
	private int courseId;
	
	@Column(name = "HWNAME")
	private String hwName;
	
	@Column(name = "OUTLINE")
	private String outline;
	
	@Column(name = "LATE")
	private boolean late;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEADLINE")
	private Date deadline;

	public int getId() {
		return id;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getHwName() {
		return hwName;
	}

	public void setHwName(String hwName) {
		this.hwName = hwName;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
