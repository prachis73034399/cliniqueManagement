package com.bridgelabz.model;

public class Appointment 
{
private int patientId;
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}
public int getCliniqueId() {
	return cliniqueId;
}
public void setCliniqueId(int cliniqueId) {
	this.cliniqueId = cliniqueId;
}
public int getDoctId() {
	return doctId;
}
public void setDoctId(int doctId) {
	this.doctId = doctId;
}
public String getPatientName() {
	return patientName;
}
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
private int cliniqueId;
private int doctId;
private String patientName;
private String time;
private String date;
private String nextDate;
public String getNextDate() {
	return nextDate;
}
public void setNextDate(String nextDate) {
	this.nextDate = nextDate;
}
	
}
