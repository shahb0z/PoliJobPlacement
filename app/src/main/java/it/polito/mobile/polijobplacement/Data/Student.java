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
        this.put(JobApplication.CV,parseFile);
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
        this.put(JobApplication.BIRTH_DATE,birth_date);
    }

    public List<Languages> getLanguage_skills() {
        return getList(JobApplication.LANGUAGE_SKILLS);
    }

    public void setLanguage_skills(List<Languages> language_skills) {
        this.put(JobApplication.LANGUAGE_SKILLS,language_skills);
    }

    public List<Degree> getEducation() {
        return getList(JobApplication.EDUCATION);
    }

    public void setEducation(List<Degree> education) {
        this.put(JobApplication.EDUCATION,education);
    }

    public List<String> getSkills() {
        return getList(JobApplication.SKILLS);
    }

    public void setSkills(List<String> skills) {
        this.put(JobApplication.SKILLS,skills);
    }

    public List<Messages> getMessages(){ return getList(JobApplication.MESSAGES);}

    public void setMessages(List<Messages> message){ this.put(JobApplication.MESSAGES, message);}

    public List<JobOffers> getJobApplied(){ return getList(JobApplication.JOB_APPLIED);}

    public void setJobApplied(List<JobOffers> list_jobs){ this.put(JobApplication.JOB_APPLIED, list_jobs);}

    public String getAddress(){ return getString(JobApplication.ADDRESS);}
    public void setAddress(String address){ this.put(JobApplication.ADDRESS, address);}
}
