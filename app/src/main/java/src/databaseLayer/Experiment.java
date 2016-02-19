package src.databaseLayer;

import java.util.Date;

/**
 * Created by Jose Matundan on 2/14/2016.
 */
public class Experiment {


    private String StudyTitle, StudyType, GroupWithinExperiment, Experimenters, Notes;
    Date StartDate, EndDate;

    public Experiment( String StudyTitle, String StudyType,String GroupWithinExperiment,Date StartDate, Date EndDate, String Experimenters,String Notes) {
        this.StudyTitle = StudyTitle;
        this.StudyType = StudyType;
        this.GroupWithinExperiment = GroupWithinExperiment;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Experimenters = Experimenters;
        this.Notes = Notes;
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
    public Date getStartDate() {
        return this.StartDate;
    }
    public Date getEndDate(){
        return this.EndDate;
    }
    public String getExperimenters(){
        return this.Experimenters;
    }
    public String getNotes() {
        return this.Notes;
    }

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
    public void setStartDate(Date StartDate){
        this.StartDate = StartDate;
    }
    public void setEndDate(Date EndDate){
        this.EndDate = EndDate;
    }
    public void setExperimenters(String Experimenters){
        this.Experimenters = Experimenters;
    }
    public void setNotes(String Notes){
        this.Notes = Notes;
    }


}
