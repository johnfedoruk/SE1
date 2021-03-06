package net.javacrypt.se1.test;

import presentationLayer.SplashScreen;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class ViewBirdList extends ActivityInstrumentationTestCase2<SplashScreen> {
  	private Solo solo;
  	
  	public ViewBirdList() {
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
        //Click on Search
		solo.clickOnView(solo.getView(net.javacrypt.se1.R.id.search));
        //Wait for activity: 'businessLogicLayer.ViewBirds'
		assertTrue("businessLogicLayer.ViewBirds is not found!", solo.waitForActivity(businessLogicLayer.ViewBirds.class));
	}
}
