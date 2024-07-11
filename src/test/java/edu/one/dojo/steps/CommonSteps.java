package edu.one.dojo.steps;

import edu.one.dojo.utils.HelperClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CommonSteps {


    @Before
    public void setup() {
        HelperClass.setUpDriver();
    }

    @After
    public void tearDown() {
        HelperClass.tearDown();
    }

}
