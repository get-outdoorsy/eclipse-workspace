package javaEclipseTest;

import java.io.*;
import java.util.*;

public class FileTest {
	
	static String pathFile;
	
	public FileTest(){
		Scanner input = new Scanner(System.in);
		
		String delete;
		
		createFile();
		writeFile();
		readFile();
		
		System.out.print("Do you want to delete file? Yes|No");
		delete = input.next();
		if(delete.toLowerCase().equals("yes"))
		{
			deleteFile();
		}else {
			System.out.print("Thank you using the program.");
		}
		
	}
	
	public static void createFile() {
		try {
			File fileCreation = new File("C:\\Users\\Epoy\\Desktop\\fileKo.txt");
			if(fileCreation.exists()) {
				System.out.print("File already exists.");
				pathFile = fileCreation.getAbsolutePath();
				//fileName = fileCreation.getName();
				
			}else {
				fileCreation.createNewFile();
				System.out.print("File created: "+fileCreation.getName());
			}
		}catch(IOException e) {
			System.out.print("Error occured.");
			e.printStackTrace();
		}
	}
	
	public static void writeFile() {
		try {
			FileWriter updateFile = new FileWriter("C:\\Users\\Epoy\\Desktop\\fileKo.txt");
			updateFile.write("kupal ka ba?");
			updateFile.close();
			System.out.print("Successful file write.");
		}catch(IOException e) {
			System.out.print("Error occured");
			e.printStackTrace();
		}
		
	}
	public static void readFile() {
		try {
			File readFile = new File(pathFile);
			Scanner scan = new Scanner(readFile);
			
			while(scan.hasNextLine()) {
				String data = scan.nextLine();
				System.out.print(data);
			}
			scan.close();
			
		}catch(Exception e) {
			System.out.print("Error");
			e.printStackTrace();
		}
	}
	
	public static void deleteFile() {
		
		
		try {
			File fileDelete = new File(pathFile);
			if(fileDelete.delete()) {
				System.out.print("Successfully deleted "+fileDelete.getName());
			}else {
				System.out.print("Deletion error.");
			}
		}catch(Exception e) {
			System.out.print("Error occured.");
			e.printStackTrace();
		}
	}
}
