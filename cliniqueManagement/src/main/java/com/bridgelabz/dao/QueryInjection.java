package com.bridgelabz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.model.Appointment;
import com.bridgelabz.model.Clinique;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
public class QueryInjection  implements JdbcDao
{
	
	DatabaseConnection databaseConnection = new DatabaseConnection();
	// creating object of Statement class and storing the return value of connection method in the same
	
	
	// creating droptable method for dropping the table if they are already created
	public void dropTable() throws SQLException
	{
		Statement stmt= databaseConnection.connection();
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
		
		try{
			String sql5= "DROP TABLE Appoinments";
			stmt.executeUpdate(sql5);
		}catch(Exception e)
		{}
	}
	
	 //<<<<<<<<<----------------For Clinique Table------------------>>>>>>>>>>>>>>>>>>>
    public void insertCliniqueDetails(Clinique clinique) throws SQLException
    {
    	Statement stmt= databaseConnection.connection();
    	try
        {
    		// creating table Clinique and Storing sql query in string
	        String sql = "CREATE TABLE Clinique " +
	                   "(clinique_id INTEGER not NULL, " +
	                   " clinique_Name VARCHAR(255), " + 
	                   " PRIMARY KEY ( clinique_id ))"; 
	        
	        // calling executeUpdate method of Statement class and passing sql query in string format
	        stmt.executeUpdate(sql);
	           
	        System.out.println("Inserted records into the  Clinique table...");
        }
        catch(SQLException e)
        {
        	System.out.println(e);
        }
	     
    	// insert query will insert the values in database table
    	String sql1  = "INSERT INTO Clinique(clinique_id,clinique_Name) VALUES("
					+ clinique.getCliniqueId() + ",'"  + clinique.getCliniqueName() + "')";
           		
  
       	 stmt.executeUpdate(sql1);
    }


        //<<<<<<<<<----------------For Doctor Table------------------>>>>>>>>>>>>>>>>>>>
    public void insertDoctorDetails(Doctor doctor) throws SQLException 
    {
    	Statement stmt= databaseConnection.connection();
        try
        {
        	// creating table Clinique and Storing sql query in string
	        String sql = "CREATE TABLE Doctor " +
	                   "(doct_Id INTEGER not NULL, " +
	                   " doctname VARCHAR(255), " + 
	                   " availability VARCHAR(255), " + 
	                   " Specialization VARCHAR(255), " + 
	                   " PRIMARY KEY (doct_Id  ))"; 
	        
	        stmt.executeUpdate(sql);
	           
	        System.out.println("Inserted records into the  Doctor table...");
        }
        catch(SQLException e)
        {
        	System.out.println("alredy exist");
        }
        
        // insert query will insert the values in database table
        String sql1 = "INSERT INTO Doctor(doct_Id,doctname,availability,Specialization) VALUES("
					+ doctor.getDoctorId() + ",'" +doctor.getDoctorName() + "','"
					+ doctor.getAvailability() + "','" + doctor.getSpecialization() + "')";
	   	 	
	   	 stmt.executeUpdate(sql1);
    }
    
	        //<<<<<<<<<----------------For Patient Table------------------>>>>>>>>>>>>>>>>>>>
	        
    public void insertPatientsDetails(Patient patient) throws SQLException
    {
    	Statement stmt= databaseConnection.connection();
    	try
        {
        	// creating table Clinique and Storing sql query in string
	        String sql = "CREATE TABLE Patient " +
	                   "(patient_Id INTEGER not NULL, " +
	                   " patient_Name VARCHAR(255), " + 
	                   " PRIMARY KEY ( patient_Id ))"; 
	        
	        stmt.executeUpdate(sql);
	           
	        System.out.println("Inserted records into the Patient  table...");
        }
        catch(Exception e)
        {
        	System.out.println("alredy exist");
        }
        
        // insert query will insert the values in database table
        String sql1 = "INSERT INTO Patient(patient_Id,patient_Name) VALUES("
				+ patient.getPatientId() + ",'" + patient.getPatientName()+ "')";
        stmt.executeUpdate(sql1);
    }
   
        //<<<<<<<<<<<<<<<<<<<< for Clinique Doctor>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void insertIntoCliniqDoctorTable(Doctor doctor) throws SQLException
    {
    	Statement stmt= databaseConnection.connection();
    	try
        {
    		// creating table Clinique and Storing sql query in string
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
    	
    	// for loop goes till one doctor is having a list of available cliniques
    	for(int i=0; i<doctor.getListOfAvailability().size(); i++ )
	   	{
    		// insert query will insert the values in database table
	   	 	String sql1 = "INSERT INTO Dr_clinique(doct_Id,clinique_id,availability) VALUES("
					+ doctor.getDoctorId() +
					",'" + doctor.getListOfCliniqueId().get(i)+
					"','"+doctor.getListOfAvailability().get(i)+ "')";
	   	 	stmt.executeUpdate(sql1);
	    }
    }
        
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<for clinique Patient>>>>>>>>>>>>>>>>>>>>>>>>>>
    public void insertIntoCliniqPatientTable(Patient patient) throws SQLException
    {
    	Statement stmt= databaseConnection.connection();
        try
        {
        	// creating table Clinique and Storing sql query in string
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

        // for loop goes till one patient has number of cliniques
        for(int i=0; i<patient.getCliniqueId().size(); i++)
	   	{
        	//insert query will insert the values in database table
		   	 String sql1 = "INSERT INTO Patient_clinique(patient_Id,clinique_id) VALUES("
						+ patient.getPatientId() + ",'" + patient.getCliniqueId().get(i)+ "')";
		   	 stmt.executeUpdate(sql1);
	    }
    }

        //<<<<<<<<<<<<<<<<<<<<<<<<       searching name of patient using id      >>>>>>>>>
    public String searchNameOfPatientUsingId(int patientId) throws SQLException
    {
    	Statement stmt= databaseConnection.connection();
    	// creating object of model Patient Model
    	Patient patient = new Patient();
    	
    	String patientName = null;
    	try
        {
    		// select query will return required data from the table
    		String sql2="SELECT patient_Name FROM Patient WHERE patient_Id="+patientId;
    		// making object of ResultSet class  storing a result of the query in object
    		ResultSet rs= stmt.executeQuery(sql2);
    		// till resultSet has a value
    		while(rs.next())
    		{
    			// -------using getString and getInt method for retriving values using column name-------
    			patientName=  rs.getString("patient_Name");
            	patient.setPatientName(rs.getString("patient_Name"));
    		}
	           
	        rs.close();
        }
        catch(Exception e)
        {
        	System.out.println("alredy exist");
        }

    	return patientName;    
    }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  searching cliniques for patient    >>>>>>>>>>>>>>>>>>>>
    public List<Integer> searchCliniqueForPatient(int patientId) throws SQLException
    { 
    	Statement stmt= databaseConnection.connection();
    	List<Integer> listOfCliniqueId = new ArrayList<Integer>(); 
    	try
        {
    		// select query will return required data from the table
    		String sql2="SELECT * FROM Patient_clinique pc NATURAL JOIN Clinique   WHERE pc.patient_Id="+patientId;
    		// making object of ResultSet class  storing a result of the query in object
    		ResultSet rs = stmt.executeQuery(sql2);
    		// till resultSet has a value
             while(rs.next())
             {
            	// -------using getString and getInt method for retriving values using column name-------
            	 int clinicId= rs.getInt("clinique_id");
	             String clinicName= rs.getString("clinique_Name");
	             listOfCliniqueId.add(clinicId);
             }
             rs.close();
        }
        catch(Exception e)
        {
        	System.out.println("alredy exist");
        }
    	return listOfCliniqueId;    
    }
        
        //<<<<<<<<<<<<<<<<<<<<<<<        Showing available Clinique Information    >>>>>>>>>>>>>>>>>>>
        public List<Clinique> cliniqueInfo(int clinicId) throws SQLException
        { 
        	Statement stmt= databaseConnection.connection();
        	// creating object of Clinique Model class
        	Clinique clinic = new Clinique();
        	List<Clinique> listOfCliniques = new ArrayList<Clinique>(); 
        	try
	        {
        		// select query will return required data from the table
        		String sql2="SELECT * FROM Clinique  WHERE clinique_id="+clinicId;
        		// making object of ResultSet class  storing a result of the query in object
        		ResultSet rs = stmt.executeQuery(sql2);
        		// till resultSet has a value
	             while(rs.next())
	             {
	            	// -------using getString and getInt method for retriving values using column name------- 
	            	 int clinicId1= rs.getInt("clinique_id"); 
	                 String  clinicName= rs.getString("clinique_Name");
	                 
	                 // ---------setting the values in Clinique Model Class----------
	                 clinic.setCliniqueId(clinicId1);
	                 clinic.setCliniqueName(clinicName);
	                 listOfCliniques.add(clinic);
	             }
	             rs.close();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
        	return listOfCliniques;    
        }
  
        //<<<<<<<<<<<<<<<<<<<<     list of doctors available at particular time       <<<<<<<<<<<<<<<<<<
        public List<Doctor> availableDoctorsInTime(String availability, int cliniqueId) throws SQLException
        {
        	Statement stmt= databaseConnection.connection();
        	List<Doctor> listOfAvailableDoctors = new ArrayList<Doctor>();
        	String a = null ;
        	try
	        {
        		// select query will return required data from the table
	        	String sql2="SELECT * FROM Dr_clinique WHERE clinique_id="+cliniqueId+" AND availability='"+availability+"'";
	        	
	        	// making object of ResultSet class  storing a result of the query in object
	        	ResultSet rs = stmt.executeQuery(sql2);
	        	// till resultSet has a value
	             while(rs.next())
	             {
	            	// creating object doctor Model
	            	 Doctor doctor= new Doctor();
	            	// -------using getString and getInt method for retriving values using column name------- 
            	 	int doctID =  rs.getInt("doct_Id");
            	 	doctor.setDoctorId(doctID);
            	 	listOfAvailableDoctors.add(doctor);
	             }
	             rs.close();
	             
	             
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
        	Statement stmt= databaseConnection.connection();
        	List<Doctor> listOfAvailableDoctors = new ArrayList<Doctor>();
        	//String a = null ;
        	try
	        {
	        	String sql2="SELECT * FROM Doctor WHERE doct_Id="+doctId;
	        	ResultSet rs = stmt.executeQuery(sql2);
				while(rs.next())
				{
					Doctor doctor= new Doctor();
						 
					
					int doctID =  rs.getInt("doct_Id");
					String doctName =  rs.getString("doctname");
					String availabilty =  rs.getString("availability");
					String specialization =  rs.getString("Specialization");
					
					doctor.setDoctorId(doctID);
					doctor.setDoctorName(doctName);
					doctor.setSpecialization(specialization);
					doctor.setDoctorId(doctID);
					listOfAvailableDoctors.add(doctor);
				 }
	             rs.close();
	             
	             
	        }
	        catch(Exception e)
	        {
	        	System.out.println("alredy exist");
	        }
	       return listOfAvailableDoctors;    
        }
        
        //<<<<<<<<<<<<<<<<<< check for appoinment >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        public void createAppoinment()
        {
        	Statement stmt= databaseConnection.connection();
        	try
	        {
        		String sql = "CREATE TABLE Appoinments " +
 	                   "(patient_Id INTEGER not NULL, " +
 	                   " patient_Name VARCHAR(255), " + 
 	                   " clinique_Id INTEGER, "+
 	                   " doct_Id INTEGER, "+
 	                   " time VARCHAR(255), " + 
 	                   " Date VARCHAR(255))" ; 
 	                
 	        
    	        stmt.executeUpdate(sql);
 	           
    	       
	         }
	            
        	catch(Exception e)
	        {
	        	//System.out.println("alredy exist");
	        } 
        } 
        
      //<<<<<<<<<<<<<<<<<< check for appoinment >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        public int checkAppoinment(Appointment appoinment)
        {
        	Statement stmt= databaseConnection.connection();
        	Statement stmt1= databaseConnection.connection();
        	int count=0, count2=0;
        	try
	        {
	        	String sql="SELECT * FROM Appoinments a NATURAL JOIN Doctor d WHERE d.doct_Id="+appoinment.getDoctId()+ " AND time='"+appoinment.getTime()+ "' AND Date='"+appoinment.getDate()+"';";
	        	
	        	String sql2="SELECT * FROM Appoinments a NATURAL JOIN Doctor d WHERE a.patient_Id="+appoinment.getPatientId()+" AND d.doct_Id="+appoinment.getDoctId()+ " AND time='"+appoinment.getTime()+ "' AND Date='"+appoinment.getDate()+"';";
	        	ResultSet rs = stmt.executeQuery(sql);
	        	ResultSet rs1 = stmt1.executeQuery(sql2);
				while(rs.next())
				{
					count++;
				}
				while(rs1.next())
				{
					count2++;
				}
				if(count2!=0)
				{
					return 1;
				}

				if(count<2)
				{
					return 2;
				}
				else
				{
					return 3;
				}
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e);
	        }
			
	      return 4;    
        }
       //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<take appointment>>>>>>>>>>>>>>>>
        public void takeAppointment(Appointment appoinment) throws SQLException
        {

        	Statement stmt= databaseConnection.connection();
        	 String sql1 = "INSERT INTO Appoinments(patient_Id,patient_Name,clinique_Id,doct_Id,time,Date) VALUES("
						+ appoinment.getPatientId() +
						",'" +appoinment.getPatientName() + 
						"'," +appoinment.getCliniqueId()+
						"," +appoinment.getDoctId()+
						",'" +appoinment.getTime()+ 
						"','" +appoinment.getDate()+"')";
		   	 stmt.executeUpdate(sql1);
        }
        
        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<take Next day  appointment>>>>>>>>>>>>>>>>
        public void takeNextDayAppoinment(Appointment appoinment) throws SQLException
        {
        	Statement stmt= databaseConnection.connection();
        	
        	
        	 String sql1 = "INSERT INTO Appoinments(patient_Id,patient_Name,clinique_Id,doct_Id,time,Date) VALUES("
						+ appoinment.getPatientId() +
						",'" +appoinment.getPatientName() + 
						"'," +appoinment.getCliniqueId()+
						"," +appoinment.getDoctId()+
						",'" +appoinment.getTime()+ 
						"','" +appoinment.getNextDate()+"')";
		   	 stmt.executeUpdate(sql1);
        }
        
        

}
