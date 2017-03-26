package edu.bsuir.mySQLTestLib.model;

public class Users  {

	private int id;
	private String passwordHash;
	private String temporaryPasswordHash;
	private String mail;
	private int roleId;

	public Users() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getTemporaryPasswordHash() {
		return temporaryPasswordHash;
	}

	public void setTemporaryPasswordHash(String temporaryPasswordHash) {
		this.temporaryPasswordHash = temporaryPasswordHash;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
