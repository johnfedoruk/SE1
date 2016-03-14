package domainObjects;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by pure__000 on 2016-02-16.
 *
 * Additional Constructor added by Kaj on 2016-03-08
 */
public class Bird implements Serializable {

    String id, name, experiment, sex, mom, dad;
    Calendar birthDate, deathDate;
    MedicalHistory history;
    boolean status;
    public Bird(String id, String name, String experiment, Calendar birthDate, Calendar deathDate, String sex, MedicalHistory history, boolean status)
    {
        this.id = id;
        this.name = name;
        this.experiment = experiment;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.sex = sex;
        this.history = history;
        this.status = status;
    }

    public Bird(String id, String name, String experiment, Calendar birthDate, Calendar deathDate, String sex, MedicalHistory history, boolean status, String mom, String dad)
    {
        this.id = id;
        this.name = name;
        this.experiment = experiment;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.sex = sex;
        this.history = history;
        this.status = status;
        this.mom = mom;
        this.dad = dad;
    }

    public Bird(String id, String name, String experiment, String birthDate, String deathDate, String sex, String status, String mom, String dad)
    {
        this(id, name, experiment, birthDate, deathDate, sex, status);

        if(dad == null)
            this.dad = "";
        else
            this.dad = dad;

        if(mom == null)
            this.mom = "";
        else
            this.mom = mom;
    }


    public Bird(String id, String name, String experiment, String birthDate, String deathDate, String sex, String status)
    {

        if(id == null)
        {
            this.id = "";
        }
        else
        {
            this.id = id;
        }

        if(name == null)
        {
            this.name = "";
        }
        else
        {
            this.name = name;
        }

        if(experiment == null)
        {
            experiment = "";
        }
        else
        {
            this.experiment = experiment;
        }

        Calendar bDate = Calendar.getInstance();
        Calendar dDate = Calendar.getInstance();

        if(birthDate != null && !birthDate.equals(""))
        {
            bDate.setTimeInMillis(Long.parseLong(birthDate));
            this.birthDate = bDate;
        }
        else {
            this.birthDate = null;
        }

        if(deathDate != null && !deathDate.equals(""))
        {
            dDate.setTimeInMillis(Long.parseLong(deathDate));
            this.deathDate = dDate;
        }
        else
        {
            this.deathDate = null;
        }

        this.sex = sex;
        this.history = null;
        if(status.equals("true"))
        {
            this.status = true;
        }
        else
        {
            this.status = false;
        }
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

    public String getMom() { return this.mom; }
    public String getDad() { return this.dad; }

    public Calendar getBirthDate() { return this.birthDate; }
    public Calendar getDeathDate() { return this.deathDate; }
    public String getDateString(Calendar cal,String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(str);
            return sdf.format(cal.getTime());
        }catch(Exception e) {
            return null;
        }
    }
    public String getDateString(Calendar cal) {
        if(cal==null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    public String getSex(){return this.sex;}
    public MedicalHistory getMedicalHistory(){return this.history;}
    public boolean getStatus(){return this.status;}

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

    public void setMom(String mom) { this.mom = mom; }
    public void setDad(String dad) { this.dad = dad; }

    public void setMedicalHistory(MedicalHistory history){this.history = history;}
    public void setStatus(boolean status){this.status = status;}

    public Boolean equals(Bird comp)
    {
        Boolean check = true;
        if(this.status != comp.getStatus())
        {
            check = false;
        }

        if(!this.id.equals(comp.getId()))
        {
            check = false;
        }

        if(!this.name.equals(comp.getName()))
        {
            check = false;
        }

        if( this.birthDate != null && comp.birthDate != null && this.birthDate.compareTo(comp.getBirthDate()) != 0 )
        {
            check = false;
        }

        if( this.deathDate != null && comp.deathDate != null && this.deathDate.compareTo(comp.getDeathDate()) != 0 )
        {
            check = false;
        }

        if(!comp.getMedicalHistory().toString().equals(this.history.toString()) )
        {
            check = false;
        }

        if(comp.getSex().equals(this.sex))
        {
            check = false;
        }

        return check;
    }
}
