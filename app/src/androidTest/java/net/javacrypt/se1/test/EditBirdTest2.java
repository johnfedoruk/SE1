package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class EditBirdTest2 extends ActivityInstrumentationTestCase2<SplashScreen> {
    private Solo solo;

    public EditBirdTest2() {
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

    public void testRun() {
        //Wait for activity: 'presentationLayer.SplashScreen'
        solo.waitForActivity(presentationLayer.SplashScreen.class, 2000);
        //Wait for activity: 'businessLogicLayer.MainActivity'
        assertTrue("businessLogicLayer.MainActivity is not found!", solo.waitForActivity(businessLogicLayer.MainActivity.class));
        //Click on Search Bird
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btSearchBird));
        //Wait for activity: 'businessLogicLayer.SearchBird'
        assertTrue("businessLogicLayer.SearchBird is not found!", solo.waitForActivity(businessLogicLayer.SearchBird.class));
        solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.legBandId));
        solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.legBandId), "0002");
        //Click on Search
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.search));
        //Wait for activity: 'businessLogicLayer.ViewBirds'
        assertTrue("businessLogicLayer.ViewBirds is not found!", solo.waitForActivity(businessLogicLayer.ViewBirds.class));

        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.item));
        //Wait for activity: 'businessLogicLayer.ViewBird'
        assertTrue("businessLogicLayer.ViewBird is not found!", solo.waitForActivity(businessLogicLayer.ViewBird.class));
        //Click on Edit Bird
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.editBird));
        //Wait for activity: 'businessLogicLayer.EditBird'
        assertTrue("businessLogicLayer.EditBird is not found!", solo.waitForActivity(businessLogicLayer.EditBird.class));
        //Click on TestNewExperiment
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Click on TestNewExperiment
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Set default small timeout to 12877 milliseconds
        Timeout.setSmallTimeout(12877);
        //Enter the text: 'Edited Experiment Name'
        solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment), "Edited Experiment Name");
        //Click on Kevin
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirdName, 1));
        //Enter the text: 'Kevin2'
        solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName, 1));
        solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName, 1), "Kevin2");
        //Click on Add Bird
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.radioFemale));
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird, 1));
        //Wait for activity: 'businessLogicLayer.EditBirdSuccess'
        assertTrue("businessLogicLayer.EditBirdSuccess is not found!", solo.waitForActivity(businessLogicLayer.EditBirdSuccess.class));
        //Click on Back To Menu
        solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btBackToMenu));
    }
}