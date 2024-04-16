package people;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class Vector{
	private List<Person> peopleList = new ArrayList<>();

	public void addPerson(Person person) throws IOException {
        peopleList.add(person);
        this.saveinFile();
    }

    public void removePerson(Person person) throws IOException {
    	peopleList.remove(person);
    	this.saveinFile();
    }

	public void print() {
		for (int i=0; i<this.peopleList.size(); i++) {
			this.peopleList.get(i).print();
		}

	}

	public List<Person> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<Person> peopleList) {
		this.peopleList = peopleList;
	}
	
	public void loadFromFile() throws FileNotFoundException {
		File folder = new File("./information");
		for (File fileEntry : folder.listFiles()) {
			Scanner myReader = new Scanner(fileEntry);
			String [] data = myReader.nextLine().split(";");
			Person p = new Person(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
			peopleList.add(p);
			myReader.close();
	        }
	    }
	
	
	public void saveinFile() throws IOException{
		Files.createDirectories(Paths.get("./information"));
		File folder = new File("./information");
		for (File fileEntry : folder.listFiles()) {
			fileEntry.delete();
		}
		for (int i=0; i < peopleList.size(); i++) {
			PrintWriter writer = new PrintWriter("./information/Person" + (i+1) + ".txt", "UTF-8");
			Person tmp = peopleList.get(i);
			writer.println(tmp.getName()+";"+tmp.getSurname()+";"+tmp.getAddress()+";"+tmp.getTelephoneNumber()+";"+tmp.getAge()+";");
			writer.close();
		}
		
	}

}
