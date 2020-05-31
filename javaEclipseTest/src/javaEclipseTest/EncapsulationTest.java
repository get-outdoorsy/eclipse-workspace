package javaEclipseTest;

public class EncapsulationTest {
	private String password = "121233";
	private int email = 2211;
	
	public void setPassword(String newPass)
	{
		this.password = newPass;
	}
	
	public int getEmail()
	{
		return email;
	}
	
	public String getPassword()
	{
		return password;
	}
}
