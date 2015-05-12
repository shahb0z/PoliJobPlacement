package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by user on 5/13/2015.
 */
@ParseClassName("Jobs_by_Type")
public class Jobs_by_Type extends ParseObject {
    private static String TYPE = "type"; // education, engineering....
    private List<JobOffers> list_jobs;

    public void setTYPE(String type){
        this.put(TYPE, type);
    }


}
