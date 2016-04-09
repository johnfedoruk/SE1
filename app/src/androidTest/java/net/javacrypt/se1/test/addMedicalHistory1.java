package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class addMedicalHistory1 extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public addMedicalHistory1() {
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
        //Set default small timeout to 17094 milliseconds
		Timeout.setSmallTimeout(17094);
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird));
        //Wait for activity: 'businessLogicLayer.AddBird'
		assertTrue("businessLogicLayer.AddBird is not found!", solo.waitForActivity(businessLogicLayer.AddBird.class));
        //Set default small timeout to 35824 milliseconds
		Timeout.setSmallTimeout(35824);
        //Enter the text: '0005'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId), "0005");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
        //Assert that: 'Add Medical History' is shown
		assertTrue("'Add Medical History' is not shown!", solo.waitForView(solo.getView(net.javacrypt.se1.R.id.txtAddMedicalHistory)));
        //Click on ImageView
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.imgAddMedicalHistory));
        //Wait for activity: 'businessLogicLayer.AddMedicalHistory'
		assertTrue("businessLogicLayer.AddMedicalHistory is not found!", solo.waitForActivity(businessLogicLayer.AddMedicalHistory.class));
        //Click on 确定
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '18-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDateOfReport));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDateOfReport), "18-4-2016");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtHealthIssue));
        //Enter the text: 'no issue'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtHealthIssue));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtHealthIssue), "no issue");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtTreatment));
        //Enter the text: 'warer'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtTreatment));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtTreatment), "warer");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtNotes));
        //Click on Add Medical History
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddMedicalHistory));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird, 1));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirdName));
        //Enter the text: '0005'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName), "0005");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
        //Click on 确定
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '22-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate), "22-4-2016");
        //Click on Female
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.radioFemale));
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird, 1));
        //Wait for activity: 'businessLogicLayer.AddBirdSuccess'
		assertTrue("businessLogicLayer.AddBirdSuccess is not found!", solo.waitForActivity(businessLogicLayer.AddBirdSuccess.class));
        //Click on Back To Menu
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btBackToMenu));
	}
}
