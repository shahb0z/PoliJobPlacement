package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;

import java.io.File;
import java.util.List;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Company")
public class Company extends App_User {
    private String name;
    private String field;
    private List<JobOffers> list_offers;
    private String detail;
    private File photo;
    private String PhoneNumber;


    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<JobOffers> getList_offers() {
        return list_offers;
    }

    public void setList_offers(List<JobOffers> list_offers) {
        this.list_offers = list_offers;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
