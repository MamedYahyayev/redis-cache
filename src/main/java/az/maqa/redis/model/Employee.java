package az.maqa.redis.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8789295154837159494L;

	private String name;

	private String surname;

	private String email;

	private Integer experience;

	private Date hireDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

}
