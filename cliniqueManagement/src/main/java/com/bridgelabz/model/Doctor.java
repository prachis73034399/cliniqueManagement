package com.bridgelabz.model;

import java.util.List;

import org.json.simple.JSONArray;

public class Doctor
{
 
	public List<String> listOfAvailability;
	List<Integer> listOfCliniqueId;
	public List<Integer> getListOfCliniqueId() {
		return listOfCliniqueId;
	}
	public void setListOfCliniqueId(List<Integer> listOfCliniqueId) {
		this.listOfCliniqueId = listOfCliniqueId;
	}
	public List<String> getListOfAvailability() {
		return listOfAvailability;
	}
	public void setListOfAvailability(List<String> listOfAvailability) {
		this.listOfAvailability = listOfAvailability;
	}
	public Clinique clinique = new Clinique();
	public Clinique getClinique() {
		return clinique;
	}
	public void setClinique(Clinique clinique) {
		this.clinique = clinique;
	}
	public List<String> cliniqueId;
	

	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int doctorId;
	private String doctorName;
	private String specialization;
	private String availability ;
	public String getAvailability() 
	{
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	private int age;
}
