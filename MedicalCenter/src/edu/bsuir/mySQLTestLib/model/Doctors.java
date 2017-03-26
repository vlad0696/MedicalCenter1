package edu.bsuir.mySQLTestLib.model;

public class Doctors {
	private int id;
	private String speciality;
	private String category;
	private String departament;
	private int post;

	public Doctors() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}
}
