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
    private List<Degree> education;
    private List<String> skills;
    private List<Languages> language_skills;
    private String phoneNumber;
    private String address;
    private String country;
    private String region;
    private File cv;



    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public File getCv() {
        return cv;
    }

    public void setCv(File cv) {
        this.cv = cv;
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

    public List<Degree> getEducation() {
        return education;
    }

    public void setEducation(List<Degree> education) {
        this.education = education;
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
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
