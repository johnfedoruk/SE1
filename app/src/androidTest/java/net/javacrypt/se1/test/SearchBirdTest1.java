package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import net.javacrypt.se1.R;


//This test edits the birdID 0001 Name and Current Experiment
public class SearchBirdTest1 extends ActivityInstrumentationTestCase2<SplashScreen> {
    private Solo solo;

    public SearchBirdTest1() {
        super(SplashScreen.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }


    //This test searches for the Bird with Id 0001
    public void testRun() {
        //Wait for activity: 'presentationLayer.SplashScreen'
        solo.waitForActivity(presentationLayer.SplashScreen.class, 2000);
        //Wait for activity: 'businessLogicLayer.MainActivity'
        assertTrue("businessLogicLayer.MainActivity is not found!", solo.waitForActivity(businessLogicLayer.MainActivity.class));
        //Click on Search Bird
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btSearchBird));
        //Wait for activity: 'businessLogicLayer.SearchBird'
        assertTrue("businessLogicLayer.SearchBird is not found!", solo.waitForActivity(businessLogicLayer.SearchBird.class));
        //Enter the text: '0001'
        solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.legBandId));
        solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.legBandId), "0001");
        //Click on Search
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.search));
        //Wait for activity: 'businessLogicLayer.ViewBirds'
        assertTrue("businessLogicLayer.ViewBirds is not found!", solo.waitForActivity(businessLogicLayer.ViewBirds.class));
        //Click on ID: 0001 Name: Kevin active Status:
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.item));
        //Wait for activity: 'businessLogicLayer.ViewBird'
        assertTrue("businessLogicLayer.ViewBird is not found!", solo.waitForActivity(businessLogicLayer.ViewBird.class));
        //Click on Edit Bird
    }
}