package course.java.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "[USERCOURSE]")
public class UserCourse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "USERACCOUNT")
	private String userAccount;
	
	@Column(name = "COURSEID")
	private int courseId;

	public int getId() {
		return id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}
