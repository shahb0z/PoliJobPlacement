package it.polito.mobile.polijobplacement.Data;

public class Inbox {
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
