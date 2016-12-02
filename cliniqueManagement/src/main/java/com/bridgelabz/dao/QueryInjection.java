package com.bridgelabz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.model.Clinique;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
public class QueryInjection  implements JdbcDao
{
	Doctor doctor;
	Clinique clinique;
	Patient patient;
	int patient1;

	DatabaseConnection databaseConnection = new DatabaseConnection();
	Statement stmt= databaseConnection.connection();
	
	public void dropTable() throws SQLException
	{
		try
		{
			String sql1= "DROP TABLE Clinique";
			stmt.executeUpdate(sql1);
		}
		catch(Exception e)
		{}
		
		try
		{
			String sql2= "DROP TABLE Doctor";
			stmt.executeUpdate(sql2);
		}
		catch(Exception e)
		{}
		
		try
		{
			String sql3= "DROP TABLE Patient";
			stmt.executeUpdate(sql3);
		}
		catch(Exception e)
		{}
		try{
			String sql4= "DROP TABLE Dr_clinique";
			stmt.executeUpdate(sql4);
		}catch(Exception e)
		{}
		try{
			String sql4= "DROP TABLE Patient_clinique";
			stmt.executeUpdate(sql4);
		}catch(Exception e)
		{}
	}
	
	 //<<<<<<<<<----------------For Clinique Table------------------>>>>>>>>>>>>>>>>>>>
    public void insertCliniqueDetails( Clinique clinique) throws SQLException
    {
    	try
        {
	        String sql = "CREATE TABLE Clinique " +
	                   "(clinique_id INTEGER not NULL, " +
	                   " clinique_Name VARCHAR(255), " + 
	                   " PRIMARY KEY ( clinique_id ))"; 
	        
	        stmt.executeUpdate(sql);
	           
	        System.out.println("Inserted records into the  Clinique table...");
        }
        catch(SQLException e)
        {
        	System.out.println(e);
        }
	        
    	String sql1  = "INSERT INTO Clinique(clinique_id,clinique_Name) VALUES("
					+ clinique.getCliniqueId() + ",'"  + clinique.getCliniqueName() + "')";
           		
    	System.out.println("SSQL"+sql1);
       	 stmt.executeUpdate(sql1);
    }


        //<<<<<<<<<----------------For Doctor Table------------------>>>>>>>>>>>>>>>>>>>
    public void insertDoctorDetails(Doctor doctor) throws SQLException 
    {
    	
        try
        {
	        String sql = "CREATE TABLE Doctor " +
	                   "(doct_Id INTEGER not NULL, " +
	                   " doctname VARCHAR(255), " + 
	                   " availability VARCHAR(255), " + 
	                  /* " clinique_Id VARCHAR(255), " + */
	                   " Specialization VARCHAR(255), " + 
	                   " PRIMARY KEY (doct_Id  ))"; 
	        
	        stmt.executeUpdate(sql);
	           
	        System.out.println("Inserted records into the  Doctor table...");
        }
        catch(SQLException e)
        {
        	System.out.println("alredy exist");
        }
        
        
        String sql1 = "INSERT INTO Doctor(doct_Id,doctname,availability,Specialization) VALUES("
					+ doctor.getDoctorId() + ",'" +doctor.getDoctorName() + "','"
					+ doctor.getAvailability() + "','" + doctor.getSpecialization() + "')";
	   	 	
	   	 stmt.executeUpdate(sql1);
    }
    
	        //<<<<<<<<<----------------For Patient Table------------------>>>>>>>>>>>>>>>>>>>
	        
        public void insertPatientsDetails(Patient patient) throws SQLException
        {
	        try
	        {
		        String sql = "CREATE TABLE Patient " +
		                   "(patient_Id INTEGER not NULL, " +
		                  /* "(clinique_id INTEGER , " +*/
		                   " patient_Name VARCHAR(255), " + 
		                  
		                   " PRIMARY KEY ( patient_Id ))"; 
		        
		        stmt.executeUpdate(sql);
		           
		        System.out.println("Inserted records into the Patient  table...");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
	        
	      
       	 	
	        String sql1 = "INSERT INTO Patient(patient_Id,patient_Name) VALUES("
					+ patient.getPatientId() + ",'" + patient.getPatientName()+ "')";
					
       
           		System.out.println("SSQL"+sql1);
           		stmt.executeUpdate(sql1);
        }
       
        //<<<<<<<<<<<<<<<<<<<< for Clinique Doctor>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        public void insertIntoCliniqDoctorTable(Doctor doctor) throws SQLException
        {
        	try
	        {
		        String sql = "CREATE TABLE Dr_clinique " +
		                   "(doct_Id INTEGER not NULL, " +
		                   " availability VARCHAR(255), " + 
		                   "clinique_id INTEGER )"; 
		        
		        stmt.executeUpdate(sql);
		           
		        System.out.println("Inserted records into the DrClinic  table...");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
        	
        	for(int i=0; i<doctor.getListOfAvailability().size(); i++ )
		   	{
		   	 	String sql1 = "INSERT INTO Dr_clinique(doct_Id,clinique_id,availability) VALUES("
						+ doctor.getDoctorId() + ",'" + doctor.getListOfCliniqueId().get(i)+"','"+doctor.getListOfAvailability().get(i)+ "')";
		   	 	stmt.executeUpdate(sql1);
		    }
        }
        
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<for clinique Patient>>>>>>>>>>>>>>>>>>>>>>>>>>
        public void insertIntoCliniqPatientTable(Patient patient) throws SQLException
        {
        	
	        try
	        {
		        String sql = "CREATE TABLE Patient_clinique " +
		                   "(patient_Id INTEGER not NULL, " +
		                   "clinique_id INTEGER )"; 
		        stmt.executeUpdate(sql);
		        System.out.println("Inserted records into the DrClinic  table...");
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
 	
	        for(int i=0; i<patient.getCliniqueId().size(); i++)
		   	{
		   	 String sql1 = "INSERT INTO Patient_clinique(patient_Id,clinique_id) VALUES("
						+ patient.getPatientId() + ",'" + patient.getCliniqueId().get(i)+ "')";
		   	 stmt.executeUpdate(sql1);
		    }
        }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        public String searchNameOfPatientUsingId(int patientId) throws SQLException
        {
        	// creating object of model Patient
        	Patient patient = new Patient();
        	String patientName = null;
        	try
	        {
        		String sql2="SELECT patient_Name FROM Patient WHERE patient_Id="+patientId;
        		ResultSet rs1 = stmt.executeQuery(sql2);
        		while(rs1.next())
        		{
        			//Retrieve by column name
	            	 patientName=  rs1.getString("patient_Name");
	            	 patient.setPatientName(rs1.getString("patient_Name"));
        		}
		           
		        rs1.close();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
	       return patientName;    
        }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        public List<Integer> searchCliniqueForPatient(int patientId) throws SQLException
        { 
        	List<Integer> listOfCliniqueId = new ArrayList<Integer>(); 
        	try
	        {
        		String sql2="SELECT * FROM Patient_clinique pc NATURAL JOIN Clinique   WHERE pc.patient_Id="+patientId;
        		ResultSet rs1 = stmt.executeQuery(sql2);
	             while(rs1.next())
	             {
	            	 int clinicId= rs1.getInt("clinique_id");
		             String clinicName= rs1.getString("clinique_Name");
		             listOfCliniqueId.add(clinicId);
		             
		                
		         }
	             rs1.close();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
        	return listOfCliniqueId;    
        }
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        public List<Clinique> cliniqueInfo(int clinicId) throws SQLException
        { 
        	Clinique clinic = new Clinique();
        	List<Clinique> listOfCliniques = new ArrayList<Clinique>(); 
        	try
	        {
        		String sql2="SELECT * FROM Clinique  WHERE clinique_id="+clinicId;
	           
	             ResultSet rs1 = stmt.executeQuery(sql2);
	             while(rs1.next())
	             {
	            	 int clinicId1= rs1.getInt("clinique_id"); 
	                 String  clinicName= rs1.getString("clinique_Name");
	                 
	                 clinic.setCliniqueId(clinicId1);
	                 clinic.setCliniqueName(clinicName);
	                 listOfCliniques.add(clinic);
	             }
	             rs1.close();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
        	return listOfCliniques;    
        }
  
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        public List<Doctor> availableDoctorsInTime(String availability, int cliniqueId) throws SQLException
        {
        	List<Doctor> listOfAvailableDoctors = new ArrayList<Doctor>();
        	String a = null ;
        	try
	        {
	        	String sql2="SELECT * FROM Dr_clinique WHERE clinique_id="+cliniqueId+" AND availability='"+availability+"'";
	        	System.out.println("SQL QUERY"+ sql2);
	        	ResultSet rs1 = stmt.executeQuery(sql2);
	           

	            
	             while(rs1.next())
	             {
	            	 Doctor doctor= new Doctor();
	            	 System.out.println("Rahul");
            	 	int doctID =  rs1.getInt("doct_Id");
            	 	doctor.setDoctorId(doctID);
            	 	listOfAvailableDoctors.add(doctor);
	             }
	             rs1.close();
	             
	             
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
	       return listOfAvailableDoctors;    
        }
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
        public List<Doctor> doctorsInfo(int doctId) throws SQLException
        {
        	List<Doctor> listOfAvailableDoctors = new ArrayList<Doctor>();
        	//String a = null ;
        	try
	        {
	        	String sql2="SELECT * FROM Doctor WHERE doct_Id="+doctId;
	        	
	        	ResultSet rs1 = stmt.executeQuery(sql2);
	        	while(rs1.next())
	             {
	            	 Doctor doctor= new Doctor();
	            	 

            	 	int doctID =  rs1.getInt("doct_Id");
            	 	String doctName =  rs1.getString("doctname");
	            	 String availabilty =  rs1.getString("availability");
	            	 String specialization =  rs1.getString("Specialization");
	            	
	            	 doctor.setDoctorId(doctID);
	            	 doctor.setDoctorName(doctName);
	            	// doctor.se(availabilty);
	            	 doctor.setSpecialization(specialization);
            	 	doctor.setDoctorId(doctID);
            	 	listOfAvailableDoctors.add(doctor);
	             }
	             rs1.close();
	             
	             
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
	       return listOfAvailableDoctors;    
        }
       
}
