package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class CreateNewExperiment extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public CreateNewExperiment() {
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
        //Set default small timeout to 18308 milliseconds
		Timeout.setSmallTimeout(18308);
        //Click on Create Experiment
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btCreateExperiment));
        //Wait for activity: 'businessLogicLayer.AddExperiment'
		assertTrue("businessLogicLayer.AddExperiment is not found!", solo.waitForActivity(businessLogicLayer.AddExperiment.class));
        //Enter the text: 'NewExperimentTest'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStudyTitle));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStudyTitle), "NewExperimentTest");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtStudyType));
        //Enter the text: 'Operant'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStudyType));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStudyType), "Operant");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtGroupWithinExperiment));
        //Enter the text: '1'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtGroupWithinExperiment));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtGroupWithinExperiment), "1");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtStartDate));
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '1-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStartDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtStartDate), "1-4-2016");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperimenters));
        //Enter the text: 'Experimenter1, Experimenter2'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperimenters));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperimenters), "Experimenter1, Experimenter2");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtNotes));
        //Click on Create Experiment
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btCreateExperiment, 1));
	}
}
