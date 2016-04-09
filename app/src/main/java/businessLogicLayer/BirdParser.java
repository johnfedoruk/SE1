package businessLogicLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import domainObjects.Bird;

/**
 * Created by Kaj on 4/8/2016.
 *
 * When using this class, please have bird.txt in the root directory of the app.
 * Bird are of format id, name, birth date, sex
 */
public class BirdParser {

    static String birdFile = "bird.txt";
    BufferedReader fileReader;
    public BirdParser()
    {
        try
        {
            fileReader = new BufferedReader(new FileReader(birdFile));
        } catch (IOException ioe) {
            ioe.getStackTrace();
        }
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

        if(birdInfo.length == 3)
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

            temp = new Bird(id, name, "", bDate, "", sex, "true");
        }

        return temp;
    }
}
