package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/10/2015.
 */
@ParseClassName("JobOffers")
public class JobOffers extends ParseObject {
    public JobOffers(){}
    public String getEmploymentType() {
        return getString(JobApplication.EMPLOYMENT_TYPE);
    }

    public void setEmploymentType(String employmentType) {
        put(JobApplication.EMPLOYMENT_TYPE,employmentType);
    }

    public String getCategory() {

        return getString(JobApplication.CATEGORY);
    }

    public void setCategory(String category) {
        put(JobApplication.CATEGORY,category);
    }

    public Date getPublishDate() {
        return getDate(JobApplication.PUBLISH_DATE);
    }

    public void setPublishDate(Date publishDate) {
        put(JobApplication.PUBLISH_DATE,publishDate);
    }

    public String getTitle() {
        return getString(JobApplication.JOB_TITLE);
    }

    public void setTitle(String title) {
        put(JobApplication.JOB_TITLE,title);
    }


    public String getDescription() {
        return getString(JobApplication.JOB_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(JobApplication.JOB_DESCRIPTION,description);
    }

    public List<Student> getListOfApplicants(){
        return getList(JobApplication.JOB_OFFERS_APPLICANT_LIST);
    }
    public void setListOfApplicants(List<Student> list){
        put(JobApplication.JOB_OFFERS_APPLICANT_LIST,list);
    }

    public void addStudentToListOfApplicants(Student s){
        add(JobApplication.JOB_OFFERS_APPLICANT_LIST, s);
    }

    public void removeStudentFromListOfApplicants(Student s){
        List <Student> list = new ArrayList<Student>();
        list.add(s);
        removeAll(JobApplication.JOB_OFFERS_APPLICANT_LIST, list);
    }

    public Company getOfferedBy(){
        return (Company)get(JobApplication.JOB_OFFERS_OFFERED_BY);
    }

    public void setOfferedBy(Company c){
        put(JobApplication.JOB_OFFERS_OFFERED_BY,c);
    }

    public String getSalary(){
        return getString(JobApplication.JOB_OFFERS_SALARY);
    }

    public void setSalary(String salary){
        put(JobApplication.JOB_OFFERS_SALARY,salary);
    }

    public Date getDueDate(){
        return getDate(JobApplication.JOB_OFFERS_DUE_DATE);
    }

    public void setDueDate(Date d){
        put(JobApplication.JOB_OFFERS_DUE_DATE,d);
    }

    public List<String> getSkillsList(){
        return getList(JobApplication.JOB_OFFERS_SKILLS_LIST);
    }
    public void setSkillsList(List<String> list){
        put(JobApplication.JOB_OFFERS_SKILLS_LIST,list);
    }

    public Languages getLanguage(){
        return (Languages)get(JobApplication.JOB_OFFERS_LANGUAGE);
    }

    public void setLanguage(Languages l){
        put(JobApplication.JOB_OFFERS_LANGUAGE,l);
    }
}
