package businessLogicLayer;

import android.content.Context;

import net.javacrypt.se1.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import domainObjects.Bird;
import domainObjects.MedicalHistory;

/**
 * Created by Kaj on 4/8/2016.
 *
 * When using this class, please have bird.txt in the root directory of the app.
 * Bird are of format id, name, birth date, sex
 */
public class BirdParser {

    BufferedReader fileReader;
    public BirdParser(BufferedReader inputFile)
    {
        fileReader = inputFile;
    }

    public ArrayList<String> parseBirds()
    {
        ArrayList<String> errorList = new ArrayList<>();
        String currLine;
        String[] birdInfo;

        try
        {
            currLine = fileReader.readLine();
            while(currLine != null)
            {
                birdInfo = currLine.split(",");
                Bird temp = processBird(birdInfo);

                if(temp != null)
                {
                    MainActivity.db.addBird(temp);
                }
                else
                {
                    errorList.add(currLine);
                }

                currLine = fileReader.readLine();
            }


        } catch (IOException ioe) {
            ioe.getStackTrace();
        }

        return errorList;
    }

    private Bird processBird(String [] birdInfo)
    {
        Bird temp = null;
        String [] splitArr;
        String id = "";
        String name = "";
        String bDate = "";
        String sex = "";

        if(birdInfo.length == 4)
        {
            id = birdInfo[0];
            name = birdInfo[1];
            bDate = birdInfo[2];
            sex = birdInfo[3];
            splitArr = id.split("\\(");

            if(splitArr.length > 1)
            {
                id = splitArr[0];
            }

            splitArr = bDate.split("/-");

            if(splitArr.length > 1)
            {
                bDate = splitArr[2] + "-" + splitArr[1] + "-" + splitArr[0];
            }
            else
            {
                bDate = splitArr[0] + "-" + "01" + "-" + "01";
            }



            temp = new Bird(id, name, "Exp1", MainActivity.db.getCalendar(1993,04,33), MainActivity.db.getCalendar(1993,04,33), sex, new MedicalHistory( MainActivity.db.getCalendar(2016, 2, 22),"Chicken pox","Tylenol","did not work"),true, "123", "123");
        }

        return temp;
    }
}
