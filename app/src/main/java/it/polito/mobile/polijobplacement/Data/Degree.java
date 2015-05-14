package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 5/12/2015.
 */
@ParseClassName("Degree")
public class Degree extends ParseObject {
    public Degree(){}
    public String getUniversity() {
        return getString(JobApplication.UNIVERSITY);
    }

    public void setUniversity(String university) {
        put(JobApplication.UNIVERSITY,university);
    }



    public Date getEndyear() {
        return getDate(JobApplication.DEGREE_END_YEAR);
    }

    public void setEndyear(Date endyear) {
        put(JobApplication.DEGREE_END_YEAR,endyear);
    }




    public String getMajor() {
        return getString(JobApplication.DEGREE_MAJOR);
    }

    public void setMajor(String major) {
        put(JobApplication.DEGREE_MAJOR,major);
    }

    public String getType() {
        return getString(JobApplication.DEGREE_TYPE);
    }

    public void setType(String type) {
        put(JobApplication.DEGREE_TYPE,type);
    }

    public Date getStartYear() {
        return getDate(JobApplication.DEGREE_START_YEAR);
    }

    public void setStartYear(Date startYear) {
        put(JobApplication.DEGREE_START_YEAR,startYear);
    }

}
