package javaEclipseTest;

public class EncapsulationTest extends PolymorphNaman{
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
	
	public void animalSound() {
		System.out.print("EncapClass to.");
	}
	
	//hides sensitive data si encap, use get and set para maaccess sila
}
