package absConstructInheritEncapInputMeth;

public class PersonalInfo {
	private String email = "lulu@gmail.com";
	private String password = "akon123";
	private String sex = "M";
	
	//<-- these methods will be called in Student_data.java
	public void setEmail(String changedMail)
	{
		this.email = changedMail;
	}
	
	public void setPass(String changedPass)
	{
		this.password = changedPass;
	}
	public void setSex(String changedSex)
	{
		this.sex = changedSex;
	} //-->
	
	//<-- these data will be sent to Student_data.java
	public String getEmail()
	{
		return email;
	}
	public String getPassword()
	{
		return password;
	}
	public String getSex()
	{
		return sex;
	} // -->
	
}
