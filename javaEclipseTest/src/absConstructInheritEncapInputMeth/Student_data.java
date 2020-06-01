package absConstructInheritEncapInputMeth;

import java.util.Scanner;
public class Student_data extends UsersFamily{
	
	String studentName, healthy;
	float mathGrades, englishGrades, scienceGrades, peGrades;
	float weight, height, bmi;
	 
	PersonalInfo info = new PersonalInfo();
	public Student_data(){
		
		
		userInput();
		displayData();	
		//displayUserFamBckgrnd();
		getOldDataRecords();
	}
	void getOldDataRecords()
	{
		String oldEmail = info.getEmail();
		String oldPassword = info.getPassword();
		String oldSex = info.getSex();
		
		System.out.print("\nOld Records\n");
		System.out.print("Email: "+oldEmail+"\n");
		System.out.print("Password: "+oldPassword+"\n");
		System.out.print("Sex: "+oldSex+"\n");
		
	}
	
	void userInput(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Student name: ");
		studentName = input.next();
		System.out.println();
		
		System.out.print("GRADES\n");
		System.out.print("Enter student grades\n");
		System.out.print("Math: ");
		mathGrades = input.nextFloat();
		System.out.print("English: ");
		englishGrades = input.nextFloat();
		System.out.print("Science: ");
		scienceGrades = input.nextFloat();
		System.out.print("PE: ");
		peGrades = input.nextFloat();
		System.out.println();
		
		System.out.print("HEALTH\n");
		System.out.print("Enter student's weight: ");
		weight = input.nextFloat();
		System.out.print("Enter student's height: ");
		height = input.nextFloat();
		
		//<-- computes BMI
		bmi = weight/(height*height);
		
		if(bmi > 25 || bmi < 18.4)
		{
			healthy = "Unhealthy";
		}
		else
		{
			healthy = "Healthy";
		}
		// -->
		
		input.close();
	}
	
	
	void displayData()
	{
		System.out.print("\nStudent data\n");
		System.out.print("Student name: "+studentName+"\n");
		System.out.print("Math: "+mathGrades+"\n");
		System.out.print("English: "+englishGrades+"\n");
		System.out.print("English: "+englishGrades+"\n");
		System.out.print("Science: "+scienceGrades+"\n");
		System.out.print("P.E.: "+peGrades+"\n");
		System.out.print("Fitness\n");
		System.out.print("Height: "+height+"\n");
		System.out.print("Weight: "+weight+"\n");
		System.out.print("BMI: "+bmi+"\n");
		System.out.print("Health condition: "+healthy+"\n");
	}
	
	public void displayUserFamBckgrnd() { //<-- this method came from UserFamily(Abstract Class)
		System.out.print("\nMy name is "+studentName+"My grandparents live in "+country+" and they always say "+grandFather+" at "+grandMother+". They are "+combinedAge+" years old.");
	}
	
}