package model.entities;

public class Course {
	
	private Integer id;
	private String name;
	private int workload;
	private double price;
	
	public Course() {
	}
	
	public Course(Integer id, String name, int workload, double price) {
		super();
		this.id = id;
		this.name = name;
		this.workload = workload;
		this.price = price;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWorkload() {
		return workload;
	}
	public void setWorkload(int workload) {
		this.workload = workload;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", workload=" + workload + ", price=" + price + "]";
	}
	
	
	
	
	

}
