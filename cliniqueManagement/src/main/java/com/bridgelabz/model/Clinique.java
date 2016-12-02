package com.bridgelabz.model;

public class Clinique 
{
	private int cliniqueId;
	private String cliniqueName;
	private String location;
	
	private String doctorId;
	private String patientId;
	public int getCliniqueId() {
		return cliniqueId;
	}
	public void setCliniqueId(int cliniqueId) {
		this.cliniqueId = cliniqueId;
	}
	public String getCliniqueName() {
		return cliniqueName;
	}
	public void setCliniqueName(String cliniqueName) {
		this.cliniqueName = cliniqueName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	
}
