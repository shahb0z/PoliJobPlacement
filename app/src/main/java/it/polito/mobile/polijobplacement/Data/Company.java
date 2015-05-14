package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;

import java.io.File;
import java.util.List;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Company")
public class Company extends App_User {
    public Company(){

    }
    //sector which company works
    public String getField() {
        return getString(JobApplication.FIELD);
    }

    public void setField(String field) {
        this.put(JobApplication.FIELD,field);
    }
    //list of positions offered by the company
    public List<JobOffers> getList_offers() {
        return getList(JobApplication.JOB_OFFERS);
    }

    public void setList_offers(List<JobOffers> list_offers) {
        this.put(JobApplication.JOB_OFFERS, list_offers);
    }
    //add item to list of job offers
    public void addItemToList_offers(JobOffers item){
        add(JobApplication.JOB_OFFERS, item);
    }



    public String getDetail() {
        return getString(JobApplication.DETAIL);
    }

    public void setDetail(String detail) {
        this.put(JobApplication.DETAIL,detail);
    }


}
