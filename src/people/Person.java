package people;

public class Person{
	
	private String name;
	private String surname;
	private String address;
	private String telephoneNumber;
	private int age;
	
	
	public Person(String name, String surname, String address, String telephoneNumber, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.age = age;
	}

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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void print() {
		System.out.println("-------------------------------");
		System.out.println("Name: " + this.name);
		System.out.println("Surname: " + this.surname);
		System.out.println("Address: " + this.address);
		System.out.println("Telephone Number: " + this.telephoneNumber);
		System.out.println("Age: " + this.age);
		System.out.println("-------------------------------");
	}
	
	

}
