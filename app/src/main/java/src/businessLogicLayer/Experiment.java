package src.businessLogicLayer;

/**
 * Created by Jose Matundan on 2/14/2016.
 */
public class Experiment {

    String txtStudyTitle, txtStudyType, txtGroupWithinExperiment, txtExperimenters, txtNotes;
    int txtStartDate, txtEndDate;

    public Experiment( String txtStudyTitle, String txtStudyType,String txtGroupWithinExperiment,int txtStartDate, int txtEndDate, String txtExperimenters,String txtNotes)
    {
        this.txtStudyTitle = txtStudyTitle;
        this.txtStudyType = txtStudyType;
        this.txtGroupWithinExperiment = txtGroupWithinExperiment;
        this.txtStartDate = txtStartDate;
        this.txtEndDate = txtEndDate;
        this.txtExperimenters = txtExperimenters;
        this.txtNotes = txtNotes;
    }

}
