package src.databaseLayer;

import java.util.Date;

/**
 * Created by pure__000 on 2016-02-16.
 */
public class Bird {

    String id,name,experiment,sex;
    Date birthDate,deathDate;

    public Bird(String id, String name,String experiment,Date birthDate,Date deathDate,String sex)
    {
        this.id = id;
        this.name = name;
        this.experiment = experiment;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.sex = sex;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return this.id;
    }
    public String getExperiment(){return this.experiment;}
    public Date getBirthDate(){return this.birthDate;}
    public Date getDeathDate(){return this.deathDate;}
    public String getSex(){return this.sex;}

    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setExperiment(String experiment){
        this.experiment= experiment;
    }
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }
    public void setDeathDate(Date deathDate){
        this.deathDate= deathDate;
    }
    public void setSex(String sex){
        this.sex = sex;

    }


}
