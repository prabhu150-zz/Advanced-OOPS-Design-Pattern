package Model;

import java.util.Random;

public class Student{

	private String Name, EmailID, RedID;
	private int units;
	private double gpa;
	
	public int getUnits() {
		return this.units;
	}

	public double getGpa() {
		return this.gpa;
	}

	public String getName() {
		return this.Name;
	}

	public String getID() {
		return this.RedID;
	}

	public Student (String name, String email, double gpa, String redid, int units) {
		this.Name = name;
		this.EmailID = email;
		this.gpa = gpa;
		this.RedID = redid;
		this.units = units;
	}

	public Student() // default constructor will initialize a random student with random values
	{
		Student randomStudent = getRandomStudent();
		this.Name = randomStudent.Name;
		this.EmailID = randomStudent.EmailID;
		this.gpa = randomStudent.gpa;
		this.RedID = randomStudent.RedID;
		this.units = randomStudent.units;
	}

	private String randString(int length, int range, char offset)
	{
		//returns a random string for name,email and a random (number) string for redid based on ASCII offset
		String random = "";
		for(int i = 0; i<length ;++i )
			random += (char) (range * Math.random() + offset);
		return random;
	}


	private Student getRandomStudent()
	{
		return new Student(randString(5,26,'A'),
				randString(3,26,'a') + "@gmail.com",
				Math.round(4 * Math.random()), // gpa between 0-4
				randString(5,9,'0'),
				(int) (150* Math.random())); // units between 0-150
	}
}
