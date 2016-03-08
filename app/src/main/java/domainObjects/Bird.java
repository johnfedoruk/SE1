package domainObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class Bird {

    String id, name, experiment, sex;
    Calendar birthDate, deathDate;
    MedicalHistory history;
    public Bird(String id, String name, String experiment, Calendar birthDate, Calendar deathDate, String sex,MedicalHistory history)
    {
        this.id = id;
        this.name = name;
        this.experiment = experiment;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.sex = sex;
        this.history = history;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return this.id;
    }
    public String getExperiment() { return this.experiment; }

    public Calendar getBirthDate() { return this.birthDate; }
    public Calendar getDeathDate() { return this.deathDate; }

    public String getDateString(Calendar cal) {
        if(cal==null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public String getSex(){return this.sex;}
    public MedicalHistory getMedicalHistory(){return this.history;}

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setExperiment(String experiment){
        this.experiment= experiment;
    }
    public void setBirthDate(Calendar birthDate){this.birthDate = birthDate;}
    public void setDeathDate(Calendar deathDate){
        this.deathDate= deathDate;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setMedicalHistory(){this.history = history;}
}
