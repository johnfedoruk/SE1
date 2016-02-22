package databaseLayer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jose Matundan on 2/20/2016.
 */
public class MedicalHistory {

    Calendar dateOfReport;
    String healthIssue,treatment,Notes;
    public MedicalHistory(Calendar dateOfReport, String healthIssue,String treatment,String Notes)
    {
        this.dateOfReport = dateOfReport;
        this.healthIssue = healthIssue;
        this.treatment = treatment;
        this.Notes = Notes;
    }

    public Calendar getDateOfReport() {return this.dateOfReport;}
    public String getDateString(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public String healthIssue() {return this.healthIssue;}
    public String treatment() {return this.treatment;}
    public String Notes() {return this.Notes;}

    public void setDateOfReport(Calendar DateOfReport){this.dateOfReport = dateOfReport;}
    public void setHealthIssue(String healthIssue){this.healthIssue = healthIssue;}
    public void setTreatment(String treatment){this.treatment = treatment;}
    public void setNotes(String Notes){this.Notes = Notes;}

}
