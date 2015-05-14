package it.polito.mobile.polijobplacement.Data;

import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Student")
public class Student extends App_User {
    public Student(){

    }

    //TODO: think about how to retrieve cv data
    public byte[] getCv() {
        final byte[] cv = "".getBytes();
        ParseFile parseFile= (ParseFile)this.get(JobApplication.CV);
        parseFile.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                if (e == null) {
                    // data has the bytes for the resume
                } else {
                    // something went wrong
                }
            }
        });
        return cv;
    }

    public void setCv(byte[] cv) {
        ParseFile parseFile = new ParseFile(JobApplication.CV,cv);
        parseFile.saveInBackground();
        this.put(JobApplication.CV, parseFile);
    }

    public String getGender() {
        return getString(JobApplication.GENDER);
    }

    public void setGender(String gender) {
        this.put(JobApplication.GENDER,gender);
    }

    public String getSurname() {
        return getString(JobApplication.SURNAME);
    }

    public void setSurname(String surname) {
        this.put(JobApplication.SURNAME,surname);
    }


    public Date getBirth_date() {
        return getDate(JobApplication.BIRTH_DATE);
    }

    public void setBirth_date(Date birth_date) {
        this.put(JobApplication.BIRTH_DATE, birth_date);
    }

    public List<Languages> getLanguage_skills() {
        return getList(JobApplication.LANGUAGE_SKILLS);
    }

    public void setLanguage_skills(List<Languages> language_skills) {
        this.put(JobApplication.LANGUAGE_SKILLS, language_skills);
    }

    //add item to skills list
    public void addItemToLanguage_skills(Languages item){
        add(JobApplication.LANGUAGE_SKILLS, item);
    }

    public List<Degree> getEducation() {
        return getList(JobApplication.EDUCATION);
    }

    public void setEducation(List<Degree> education) {
        this.put(JobApplication.EDUCATION, education);
    }

    //add item to skills list
    public void addItemToEducation(Degree item){
        add(JobApplication.EDUCATION, item);
    }

    public List<String> getSkills() {
        return getList(JobApplication.SKILLS);
    }

    public void setSkills(List<String> skills) {
        this.put(JobApplication.SKILLS, skills);
    }

    //add item to skills list
    public void addItemToSkills(String item){
        add(JobApplication.SKILLS, item);
    }
    public List<JobOffers> getJobApplied(){ return getList(JobApplication.JOB_APPLIED);}

    //add single item to the applied list of jobs

    public void addItemToJobApplied(JobOffers item){
        add(JobApplication.JOB_APPLIED,item);
    }

    public void setJobApplied(List<JobOffers> list_jobs){ this.put(JobApplication.JOB_APPLIED, list_jobs);}

    public String getAddress(){ return getString(JobApplication.ADDRESS);}
    public void setAddress(String address){ this.put(JobApplication.ADDRESS, address);}

    //saved or liked jobs

    public List<JobOffers> getJobSaved(){
        return getList(JobApplication.JOB_SAVED);
    }
    public void setJobSaved(List<JobOffers> list_jobs){
        this.put(JobApplication.JOB_SAVED,list_jobs);
    }

    //add item to the saved list of jobs

    public void addItemToJobSaved(JobOffers item){
        add(JobApplication.JOB_SAVED, item);
    }
}
