package edu.bsuir.mySQLTestLib.model;

public class Payments {
	private int id;
	private int date;
	private int cost;

	
	public Payments() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
