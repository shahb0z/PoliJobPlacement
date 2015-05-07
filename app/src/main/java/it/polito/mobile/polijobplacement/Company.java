package it.polito.mobile.polijobplacement;

/**
 * Created by Admin on 5/6/2015.
 */

public class Company
{
    public String Name = null;
    public  String Sector= null;
    public  String Tittle= null;
    public  String Detail= null;
    public Company(String Name, String Sector, String Tittle ,String Detail){
        this.Name = Name;
        this.Sector = Sector;
        this.Tittle = Tittle;
        this.Detail = Detail;


    }

}
 class Inbox {
    public String From = null;
    public String subject= null;
    public String message= null;
    public String date = null;

    public Inbox( String From,String subject,String message,String date)
    {
        this.From  = From;
        this.subject = subject;
        this.message= message;
        this.date = date;
    }
}