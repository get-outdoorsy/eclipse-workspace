package javaEclipseTest;

import java.util.Scanner;

public class UserInput {
	Scanner input = new Scanner(System.in);	
	
	String userFname;
	String userLname;
	int userAge;
	
	public UserInput()
	{
		enterCredentials();
	}
	
	public void enterCredentials()
	{
		System.out.print("\n\nEnter new credentials\n");
		System.out.print("First Name: ");
		userFname = input.next();
		System.out.print("Last Name: ");
		userLname = input.next();
		System.out.print("Age: ");
		userAge = input.nextInt();
	}
}
