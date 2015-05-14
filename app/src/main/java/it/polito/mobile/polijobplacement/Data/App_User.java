package it.polito.mobile.polijobplacement.Data;

import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

/**
 * Created by user on 5/7/2015.
 */
@ParseClassName("_User")
public class App_User extends ParseUser {

    public App_User(){

    }

    public boolean isProfileCompleted(){
        return getBoolean(JobApplication.IS_PROFILE_COMPLETED);
    }

    public void setProfileCompleted(){
        this.put(JobApplication.IS_PROFILE_COMPLETED,"true");
    }

    public String getType() {
        return getString(JobApplication.TYPE);
    }

    public void setType(String type) {
        this.put(JobApplication.TYPE, type);
    }

    public void setName(String name) {
        this.put(JobApplication.NAME, name);
    }

    public String getName(){ return getString(JobApplication.NAME);}
    //TODO: think about how to retrieve image data
    public byte[] getProfile_photo() {
        final byte[] image = "".getBytes();
        ParseFile parseFile= (ParseFile)this.get(JobApplication.PROFILE_PHOTO);
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
        return image;
    }

    public void setProfile_photo(byte[] profile_photo) {
        ParseFile parseFile = new ParseFile(JobApplication.PROFILE_PHOTO,profile_photo);
        parseFile.saveInBackground();
        this.put(JobApplication.PROFILE_PHOTO, parseFile);
    }

    public String getPhoneNumber() {
        return getString(JobApplication.PHONE_NUMBER);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.put(JobApplication.PHONE_NUMBER,phoneNumber);
    }

    public String getAddress() {
        return getString(JobApplication.ADDRESS);
    }

    public void setAddress(String address) {
        this.put(JobApplication.ADDRESS,address);
    }

    public List<Messages> getMessages(){ return getList(JobApplication.MESSAGES);}

    public void setMessages(List<Messages> message){ this.put(JobApplication.MESSAGES, message);}

    //add item to message list
    public void addItemToMessages(Messages item){
        add(JobApplication.MESSAGES, item);
    }

    //country for users
    public String getCountry(){
        return getString(JobApplication.COUNTRY);
    }
    public void setCountry(String s){
        put(JobApplication.COUNTRY, s);
    }

    //city for users
    public String getCity(){
        return getString(JobApplication.CITY);
    }
    public void setCity(String s){
        put(JobApplication.CITY,s);
    }
}
