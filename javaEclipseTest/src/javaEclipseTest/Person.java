package javaEclipseTest;

public class Person extends TestAbstract { //<-- na inherit ang TestAbstract na class
	//attributes or fields
	int age;
	String frstName;
	String lName;
	
	
	public Person() // <-- this a constructor
	{
		age = 21;
		frstName = "Jay";
		lName = "Mendezabal";
		
	}
	
	public Person(String changedFName, String changedLName, int changedAge)
	{
		frstName = changedFName;
		lName = changedLName;
		age = changedAge;
	}
	
	public void displayInfo()
	{
		System.out.print("Para saan ang abstract?");
	}
	
	//tungkol naman to sa mga constuctors and methods calling
}
