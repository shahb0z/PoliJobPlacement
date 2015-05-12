package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Degree")
public class Degree extends ParseObject {
    private String major;
    private String type;
    private String university;
    private Date startYear;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }



    public Date getEndyear() {
        return endyear;
    }

    public void setEndyear(Date endyear) {
        this.endyear = endyear;
    }

    private Date endyear;


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartYear() {
        return startYear;
    }

    public void setStartYear(Date startYear) {
        this.startYear = startYear;
    }

}
