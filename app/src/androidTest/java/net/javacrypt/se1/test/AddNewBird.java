package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class AddNewBird extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public AddNewBird() {
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
        //Set default small timeout to 427457 milliseconds
		Timeout.setSmallTimeout(427457);
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird));
        //Wait for activity: 'businessLogicLayer.AddNewBird'
		assertTrue("businessLogicLayer.AddNewBird is not found!", solo.waitForActivity(businessLogicLayer.AddBird.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtLegBandId));
        //Enter the text: '111'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId), "111");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirdName));
        //Enter the text: 'Kevin'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName), "Kevin");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Enter the text: 'Experiment1'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment), "Experiment1");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '6-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate), "6-4-2016");
        //Click on Male
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.radioMale));
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird, 1));
        //Wait for activity: 'businessLogicLayer.AddBirdSuccess'
		assertTrue("businessLogicLayer.AddBirdSuccess is not found!", solo.waitForActivity(businessLogicLayer.AddBirdSuccess.class));
        //Click on Back To Menu
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btBackToMenu));
	}
}
