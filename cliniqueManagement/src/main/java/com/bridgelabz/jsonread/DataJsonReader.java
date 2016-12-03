package com.bridgelabz.jsonread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.dao.JdbcDao;
import com.bridgelabz.dao.QueryInjection;
import com.bridgelabz.model.Clinique;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;


public class DataJsonReader<T>
{
	public void jsonDoctorRead(File file) throws ParseException, SQLException
	{
		
		JdbcDao jdbcDao = new QueryInjection();
		//creating JSonParser Object it is use to convert a JSON text to a JavaScript object.
		JSONParser parser = new JSONParser();
	
		try
		{    
			Object object = parser.parse(new FileReader(file));
			JSONObject object1 = (JSONObject) object;//json object created
			JSONArray doctorsArray = (JSONArray) object1.get("Doctors");
			JSONArray cliniqueArray = (JSONArray) object1.get("Cliniques");

			JSONArray patientsArray = (JSONArray) object1.get("Patients");
			
			jdbcDao.dropTable();
		//<<<<<------------ for reading clinique data----------->>>>>>>>>>			
			for(int i=0; i< cliniqueArray.size(); i++)
			{
				System.out.println("Prachi");
				Clinique clinique= new Clinique();
				
				JSONObject cliniques = (JSONObject) cliniqueArray.get(i);
				
				
				String cliniqueName = (String)cliniques.get("Name");
				
			
				clinique.setCliniqueName(cliniqueName);
			
				int cliniqueId = Integer.parseInt(String.valueOf(cliniques.get("ID")));
				
				clinique.setCliniqueId(cliniqueId);
				
				jdbcDao.insertCliniqueDetails(clinique);
				

			}

			//<<<<<------------ for reading doctor data----------->>>>>>>>>>
			for(int i=0; i< doctorsArray.size(); i++)
			{
				List<Integer> listOfCliniqueId= new ArrayList<Integer>();
				List<String> listOfAvailability = new ArrayList<String>();
				Doctor doctor = new Doctor();
				JSONObject doctors= (JSONObject) doctorsArray.get(i);
				Object obj = doctors.get("Name");
				String name = (String)obj;
				doctor.setDoctorName(name);
			
				obj = doctors.get("DoctID");
				int idInfo = Integer.parseInt(String.valueOf(obj));
				doctor.setDoctorId(idInfo);
				
				obj = doctors.get("Specialization");
				String specialization = (String)obj;
				doctor.setSpecialization(specialization);


				int clinid=0;
				JSONArray array =  (JSONArray) doctors.get("Clinic");
				for (int j = 0; j < array.size(); j++)
				{
					JSONObject clinicObject= (JSONObject)array.get(j);
					clinid= Integer.parseInt(String.valueOf(clinicObject.get("ClinicID")));
					listOfCliniqueId.add(clinid);
					
					String availability1 = (String) clinicObject.get("Availability");
					System.out.println("FFFFFFFFF"+availability1);
					listOfAvailability.add(availability1);
				}

				doctor.setListOfAvailability(listOfAvailability);
				 doctor.setListOfCliniqueId(listOfCliniqueId);
				
				jdbcDao.insertDoctorDetails(doctor);
				jdbcDao.insertIntoCliniqDoctorTable(doctor);
				
			}
			
			//<<<<<------------ for reading patient data----------->>>>>>>>>>
			for(int i=0; i< patientsArray.size(); i++)
			{
				System.out.println("pRATIK");
				
				Patient patient= new Patient();
				JSONObject patients= (JSONObject) patientsArray.get(i);

				Object obj = patients.get("MobileNo");
				String mblNumber = (String)obj;
				patient.setMobileNumber(mblNumber);
			

				
				obj = patients.get("ID");
				int id = Integer.parseInt(String.valueOf(obj));
				patient.setPatientId(id);
				System.out.println("idddd"+id);
				

				
				obj = patients.get("Name");
				String name = (String)obj;
				patient.setPatientName(name);
				
				JSONArray a =  (JSONArray) patients.get("Clinique Id");
		
				patient.setCliniqueId(a);

				jdbcDao.insertPatientsDetails(patient);
				jdbcDao.insertIntoCliniqPatientTable(patient);

				
			}
		} 
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	


}
