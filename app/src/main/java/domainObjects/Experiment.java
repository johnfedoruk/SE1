package domainObjects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jose Matundan on 2/14/2016.
 */
public class Experiment implements Serializable {


    private String StudyTitle, StudyType, GroupWithinExperiment, Experimenters, Notes;
    Calendar StartDate = Calendar.getInstance(), EndDate=Calendar.getInstance();
    boolean status;

    public Experiment( String StudyTitle, String StudyType,String GroupWithinExperiment,Calendar StartDate, Calendar EndDate, String Experimenters,String Notes,boolean status) {
        this.StudyTitle = StudyTitle;
        this.StudyType = StudyType;
        this.GroupWithinExperiment = GroupWithinExperiment;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Experimenters = Experimenters;
        this.Notes = Notes;
        this.status = status;
    }

    public Experiment( String StudyTitle, String StudyType,String GroupWithinExperiment,String StartDate, String EndDate, String Experimenters,String Notes,String status) {
        this.StudyTitle = StudyTitle;
        this.StudyType = StudyType;
        this.GroupWithinExperiment = GroupWithinExperiment;
        this.Experimenters = Experimenters;
        this.Notes = Notes;

        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        StartDate = getDateString(sDate);
        EndDate = getDateString(eDate);

        this.status = true;
    }



    //getters
    public String getStudyTitle() {
    return this.StudyTitle;
    }
    public String getStudyType(){
    return this.StudyType;
    }
    public String getGroupWithinExperiment() {
        return this.GroupWithinExperiment;
    }
    public Calendar getStartDate() {
        return this.StartDate;
    }
    public Calendar getEndDate(){
        return this.EndDate;
    }
    public String getDateString(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public String getExperimenters(){
        return this.Experimenters;
    }
    public String getNotes() {
        return this.Notes;
    }
    public boolean getStatus() {return this.status;}

    //setters
    public void setStudyTitle(String StudyTitle){
        this.StudyTitle = StudyTitle;
    }
    public void setStudyType(String StudyType){
        this.StudyType = StudyType;
    }
    public void setGroupWithinExperiment(String GroupWithinExperiment) {
        this.GroupWithinExperiment = GroupWithinExperiment;
    }
    public void setStartDate(Calendar StartDate){
        this.StartDate = StartDate;
    }
    public void setEndDate(Calendar EndDate){
        this.EndDate = EndDate;
    }
    public void setExperimenters(String Experimenters){
        this.Experimenters = Experimenters;
    }
    public void setNotes(String Notes){
        this.Notes = Notes;
    }
    public void setStatus(boolean status){this.status = status;}


}
