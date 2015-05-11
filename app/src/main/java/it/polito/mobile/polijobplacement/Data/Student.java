package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Student")
public class Student extends App_User {
    private String name;
    private String surname;
    private String gender;
    private Date birth_date;
    private File profile_photo;
    private List<Degree> edu_skills;
    private List<Languages> language_skills;
    private String phoneNumber;
    private String address;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public File getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(File profile_photo) {
        this.profile_photo = profile_photo;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public List<Languages> getLanguage_skills() {
        return language_skills;
    }

    public void setLanguage_skills(List<Languages> language_skills) {
        this.language_skills = language_skills;
    }

    public List<Degree> getEdu_skills() {
        return edu_skills;
    }

    public void setEdu_skills(List<Degree> edu_skills) {
        this.edu_skills = edu_skills;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
