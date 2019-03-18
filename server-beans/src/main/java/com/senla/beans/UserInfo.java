package com.senla.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "info")
public class UserInfo extends AbstractModel{
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "school")
	private String school;
	@Column(name = "high_school")
	private String highSchool;
	@Column(name = "about_user")
	private String aboutUser;
	@Column(name = "skype")
	private String skype;
	@Column(name = "phone")
	private String phone;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@OneToOne(mappedBy = "userInfo")
	private User user;

	public UserInfo() {
	}

	public UserInfo(Date birthday, String school, String highSchool, String aboutUser, String skype, String phone, String firstName, String lastName) {
		this.birthday = birthday;
		this.school = school;
		this.highSchool = highSchool;
		this.aboutUser = aboutUser;
		this.skype = skype;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getSchool() {
		return school;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public String getAboutUser() {
		return aboutUser;
	}

	public String getSkype() {
		return skype;
	}

	public String getPhone() {
		return phone;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public void setAboutUser(String aboutUser) {
		this.aboutUser = aboutUser;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
