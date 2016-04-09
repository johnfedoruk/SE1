package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class addMedicalHistory2 extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public addMedicalHistory2() {
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
        //Set default small timeout to 10833 milliseconds
		Timeout.setSmallTimeout(10833);
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird));
        //Wait for activity: 'businessLogicLayer.AddNewBird'
		assertTrue("businessLogicLayer.AddNewBird is not found!", solo.waitForActivity(businessLogicLayer.AddBird.class));
        //Press menu back key
		solo.goBack();
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird));
        //Wait for activity: 'businessLogicLayer.AddNewBird'
		assertTrue("businessLogicLayer.AddNewBird is not found!", solo.waitForActivity(businessLogicLayer.AddBird.class));
        //Enter the text: '00066666'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtLegBandId), "00066666");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirdName));
        //Enter the text: 'newbird'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirdName), "newbird");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtExperiment));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
        //Click on 确定
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '22-4-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtBirthDate), "22-4-2016");
        //Click on Male
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.radioMale));
        //Assert that: 'Add Medical History' is shown
		assertTrue("'Add Medical History' is not shown!", solo.waitForView(solo.getView(net.javacrypt.se1.R.id.txtAddMedicalHistory)));
        //Click on ImageView
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.imgAddMedicalHistory));
        //Wait for activity: 'businessLogicLayer.AddMedicalHistory'
		assertTrue("businessLogicLayer.AddMedicalHistory is not found!", solo.waitForActivity(businessLogicLayer.AddMedicalHistory.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.prev));
        //Click on 确定
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Enter the text: '2-3-2016'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDateOfReport));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtDateOfReport), "2-3-2016");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtHealthIssue));
        //Set default small timeout to 24398 milliseconds
		Timeout.setSmallTimeout(24398);
        //Enter the text: 'mental issue'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtHealthIssue));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtHealthIssue), "mental issue");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtTreatment));
        //Set default small timeout to 36647 milliseconds
		Timeout.setSmallTimeout(36647);
        //Enter the text: 'water'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtTreatment));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtTreatment), "water");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtNotes));
        //Enter the text: 'w2wwwewwwwwwwwwwwweeeeeeeeeeeessssssdddddddrdrrrrrrfrfftfttfttttttytytttyyy'
		solo.clearEditText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtNotes));
		solo.enterText((android.widget.EditText) solo.getView(net.javacrypt.se1.R.id.txtNotes), "w2wwwewwwwwwwwwwwweeeeeeeeeeeessssssdddddddrdrrrrrrfrfftfttfttttttytytttyyyyyyyyyyyyyyyyyuyyyyyyyyyyyyuuuu7gggghhgzggshshhhshhshshhshdjdjjdndjjjddjjdndndnjdndjjdjjjjjdjdjjdjdjjdjjddjdjjddjjdjdjdjjdjdjjdjdjdjdjdjfnfjjdjdjjxjjdjx");
        //Click on Add Medical History
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddMedicalHistory));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.txtDeathDate));
        //Click on Add Bird
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btAddBird, 1));
        //Wait for activity: 'businessLogicLayer.AddBirdSuccess'
		assertTrue("businessLogicLayer.AddBirdSuccess is not found!", solo.waitForActivity(businessLogicLayer.AddBirdSuccess.class));
        //Click on Back To Menu
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.btBackToMenu));
	}
}
