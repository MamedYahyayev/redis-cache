package az.maqa.redis.request;

public class RequestEmployee {

	private String name;

	private String surname;

	private String email;

	private Integer experience;

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

	@Override
	public String toString() {
		return "RequestEmployee [name=" + name + ", surname=" + surname + ", email=" + email + ", experience="
				+ experience + "]";
	}

}
