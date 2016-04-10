package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class AddNewBird2 extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public AddNewBird2() {
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
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird));
        //Wait for activity: 'businessLogicLayer.AddNewBird'
		assertTrue("businessLogicLayer.AddNewBird is not found!", solo.waitForActivity(businessLogicLayer.AddBird.class));
        //Enter the text: '00006'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId), "00006");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirdName));
        //Set default small timeout to 15660 milliseconds
		Timeout.setSmallTimeout(15660);
        //Enter the text: 'hahaha'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName), "hahaha");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Set default small timeout to 27354 milliseconds
		Timeout.setSmallTimeout(27354);
        //Enter the text: 'no right now'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtExperiment), "no right now");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '23-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate), "23-4-2016");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '30-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDeathDate), "30-4-2016");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtFatherId));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtMotherId));
        //Enter the text: '123'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtMotherId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtMotherId), "123");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtFatherId));
        //Enter the text: '321'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtFatherId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtFatherId), "321");
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
