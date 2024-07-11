package edu.one.dojo.steps;

import edu.one.dojo.utils.HelperClass;

public class CommonSteps {


    //@Before
    public void setup() {
        HelperClass.setUpDriver();
    }

    //@After
    public void tearDown() {
        HelperClass.tearDown();
    }

}
