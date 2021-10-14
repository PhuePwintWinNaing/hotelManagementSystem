package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.scene.control.PasswordField;

public class ValidChecker {
	
	public static boolean isValidCity(String city)
	{
		String reg="^[A-Z][a-z]+$";
		return Pattern.matches(reg,city);
	}
	
	public static boolean isValidMail(String mail)
	    {
	        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	                            "[a-zA-Z0-9_+&*-]+)*@" +
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	                            "A-Z]{2,7}$";
	                              
	        Pattern pat = Pattern.compile(regex);
	        if (mail == null)
	            return false;
	        return pat.matcher(mail).matches();
	    }
 
	public static boolean isValidName(String n)
	{
		String reg = "^[A-Z][a-z]*( [A-Z][a-z]+)*";
		return Pattern.matches(reg, n);	
	}
	
	public static boolean isValidAdminStaffId(String s)
	{
		
		/*fom => [a-z]{3}
		-     =>[-]
		ppwn =>[a-z]{2,7}
		01 =>[0-9]{2,3}
		
		*/
		String reg = "^[a-z]{3}[-][a-z]{2,7}[0-9]{2,3}$";
		return Pattern.matches(reg,s);

		
	}
	
	public static boolean isValidReceptionistStaffId(String s)
	{
		
		/*fo => [a-z]{2}
		-     =>[-]
		ppwn =>[a-z]{2,7}
		01 =>[0-9]{2,3}
		
		*/
		String reg = "^[a-z]{2}[-][a-z]{2,7}[0-9]{2,3}$";
		return Pattern.matches(reg,s);

		
	}
	
	
	public static boolean isValidNRC(String nrc)
	{
		/*
		 [0-9](1,2}    => may be one digit or two digit
		 /   => 1/ ..... 14/
		  (([A-Z][a-z]+){2,4}|([A-Z][A-Z]+){2,4}|([a-z][a-z]+){2,4})=>SaKaNa or PaMaNa or PaKaKha or MaMaNa
		 [(]         => (
		 [)]         => )
		 [(](([A-Z])|([a-z])|([A-Z][a-z]{4})|([A-Z]{5})|([a-z]{5}))[)]  => N or n or Naing or NAING or naing
		 [0-9]{6]        => number 6
		 ^[0-9]{1,2}/([A-Z][a-z]+){2,4}[(][A-Z][a-z]{4}[)][0-9]{6}$
		 */
		String regex = "^[0-9]{1,2}/(([A-Z][a-z]+){2,4}|([A-Z][A-Z]+){2,4}|([a-z][a-z]+){2,4})[(](([A-Z])|([a-z])|([A-Z][a-z]{4})|([A-Z]{5})|([a-z]{5}))[)][0-9]{6}$";
		return Pattern.matches(regex, nrc);
	}
	
	public static boolean isValidPassword(String password)
    {
		//password format
		/*
		 * digit (6 => 15)
		 */

		try {
			String regex = "^[0-9]{6,15}$";
			return Pattern.matches(regex, password);
		}catch(Exception e)
		{
			return false;
		}
  
    }
	
	public static boolean isValidPhoneNumber(String ph)
	{
		String regex = "^09([0-9]{9})$";
		return Pattern.matches(regex, ph);
	}
	
	public static String digestMsg(String str)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte arr[]=md.digest();
			return new String(arr);
			
		} catch (NoSuchAlgorithmException e) {
			
			return "unsucess";
		}
		
		
	}
	
	
	public static boolean isDuplicatePhone(String ph)
	{
		ArrayList<Admin> adminAl = DBHandler.getAllAdmins();
		ArrayList<Receptionist> receptionistAl = DBHandler.getAllReceptionists();
		for(int i=0; i<adminAl.size() && i<receptionistAl.size(); i++)
		{
			if(ph.equals(adminAl.get(i).getPhone()) || ph.equals(receptionistAl.get(i).getPhone()))
			{
				return true;
			}
		}   
		return false;
	}
	
	public static boolean isDuplicateNRC(String nrc)
	{
		ArrayList<Admin> adminAl = DBHandler.getAllAdmins();
		ArrayList<Receptionist> receptionistAl = DBHandler.getAllReceptionists();
		for(int i=0; i<adminAl.size() && i<receptionistAl.size(); i++)
		{
			if(nrc.equals(adminAl.get(i).getNrc()) || nrc.equals(receptionistAl.get(i).getNrc()))
			{
				return true;
			}
		}   
		return false;
	}
	
	public static boolean isDuplicateStaffId(String sid)
	{
		ArrayList<Admin> adminAl = DBHandler.getAllAdmins();
		ArrayList<Receptionist> receptionistAl = DBHandler.getAllReceptionists();
		for(int i=0; i<adminAl.size() && i<receptionistAl.size(); i++)
		{
			if(sid.equals(adminAl.get(i).getStaffId()) || sid.equals(receptionistAl.get(i).getStaffId()))
			{
				return true;
			}
		}   
		return false;
	}
	
	public static void main(String args[])
	{
//		if(isValidPassword("1234567"))
//		{
//			System.out.println("Success");
//		}else {
//			System.out.println("Fail");
//		}
		
//		if(isValidReceptionistStaffId("fo-ppwn011"))
//		{
//			System.out.println("Success");
//		}
//		else {
//			System.out.println("Failed");
//		}
		

	}

	
}
