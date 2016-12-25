package kn_14_5_Shkils;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirthd;

	@Override
	public int hashCode() {
		if (this.getId() == null)
			return 0;
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (this.getId() == null && ((User) obj).getId() == null)
			return true;
		return this.getId().equals(((User) obj).getId());
	}

	public User() {
	}

	public User(Long id, String firstName, String lastName, Date dateOfBirthd) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		setDateOfBirthd(dateOfBirthd);
	}

	public User(String firstName, String lastName, Date dateOfBirthd) {
		this.firstName = firstName;
		this.lastName = lastName;
		setDateOfBirthd(dateOfBirthd);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirthd() {
		return dateOfBirthd;
	}

	@SuppressWarnings("deprecation")
	public void setDateOfBirthd(Date dateOfBirthd) {
		Date temp = new Date(0);
		temp.setDate(dateOfBirthd.getDate());
		temp.setYear(dateOfBirthd.getYear());
		temp.setMonth(dateOfBirthd.getMonth());
		this.dateOfBirthd = temp;
	}

	public Object getFullName() {
		return getLastName() + ", " + getFirstName();
	}

	public Object getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(getDateOfBirthd());
		return currentYear - calendar.get(Calendar.YEAR);
	}

}
