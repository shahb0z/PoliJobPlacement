package it.polito.mobile.polijobplacement.Data;

import java.util.List;

/**
 * Created by Admin on 5/6/2015.
 */

public class Company
{
    public String Name = null;
    public  String Sector= null;
    public  String Tittle= null;
    public  String Detail= null;
    List<JobOffers> jobOffers;
    private String PhoneNumber;

    public Company(String Name, String Sector, String Tittle ,String Detail){
        this.Name = Name;
        this.Sector = Sector;
        this.Tittle = Tittle;
        this.Detail = Detail;


    }

}
