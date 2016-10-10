package kn_14_5_Shkil;

import java.util.Calendar;
import java.util.Date;

public class User {

	//MARK: - Properites:

	private Long _id;
	private Date _dateOfBirthd;
	private String _firstName;
	private String _lastName;

	//MARK: - Accessors:

	public Long id() {
		return _id;
	}

	public void setId(Long id) {
		this._id = id;
	}

	public String firstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	public String lastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public Date dateOfBirthd() {
		return _dateOfBirthd;
	}

	public void setDateOfBirthd(Date dateOfBirthd) {
		this._dateOfBirthd = dateOfBirthd;
	}

	public Object fullName() {
		return getLastName() + ", " + getFirstName();
	}

	public Object getAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.setTime(dateOfBirthd());
		return currentYear- calendar.get(Calendar.YEAR);
	}
}
