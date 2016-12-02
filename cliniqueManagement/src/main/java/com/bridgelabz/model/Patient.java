package com.bridgelabz.model;

import org.json.simple.JSONArray;

public class Patient 
{
	public JSONArray getCliniqueId() {
		return cliniqueId;
	}
	public void setCliniqueId(JSONArray cliniqueId) {
		this.cliniqueId = cliniqueId;
	}
	private JSONArray cliniqueId; 
	private int patientId;
	private String patientName;
	
	private String mobileNumber;
	
	private int doctorsId;
	private int age;

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getDoctorsId() {
		return doctorsId;
	}
	public void setDoctorsId(int doctorsId) {
		this.doctorsId = doctorsId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
