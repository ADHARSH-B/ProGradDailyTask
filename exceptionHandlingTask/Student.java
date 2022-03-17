package ExceptionHandlingTask;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String college;
	private String department;
	private String yearOfStudy;

	Student(int id, String name, String college, String department, String yearOfStudy) {
		this.name = name;
		this.id = id;
		this.college = college;
		this.department = department;
		this.yearOfStudy = yearOfStudy;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}

	public String getCollegeName() {
		return this.college;
	}

	public String getDepartmentName() {
		return this.department;
	}

	public String getYearOfStudy() {
		return this.yearOfStudy;
	}

}
