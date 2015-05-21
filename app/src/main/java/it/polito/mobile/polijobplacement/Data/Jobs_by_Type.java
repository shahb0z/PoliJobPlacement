package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/13/2015.
 */
@ParseClassName("Jobs_by_Type")
public class Jobs_by_Type extends ParseObject {
    private static String TYPE = "type";
    private static String LIST = "list";// education, engineering....
    private ArrayList<JobOffers> list_jobs;

    public Jobs_by_Type() {
        list_jobs = new ArrayList<>();
    }






    public List<JobOffers> getList_jobs(){
        return getList(LIST);
    }
    public void setList_jobs(List<JobOffers> list){
        put(LIST,list);
    }

    public void setTYPE(String type){
        this.put("type", type);
    }
    public String getTYPE(){ return  getString("type");}

    public void add(JobOffers j){

        list_jobs.add(j);
        this.put(LIST, list_jobs);
    }
}
