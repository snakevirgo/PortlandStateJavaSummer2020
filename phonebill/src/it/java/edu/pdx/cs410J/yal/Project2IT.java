package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void NoCommandLineArgumentsTest() {
    MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

 /* @Test
  public void checkSevenArgumentsTest(){
     MainMethodResult result = invokeMain(Project1.class, "apple","apple", "oragne","1-12-2020","1:30","1-12-2020","1:50");
     assertThat(result.getTextWrittenToStandardOut(), containsString("The number of arguments is valid") );
  }

  */

  @Test
  public void checkInvalidNumberOfArgumentsTest(){
     MainMethodResult result = invokeMain(Project2.class, "ksi","eis","apple","apple", "orange","1-12-2020","1:30","1-12-2020","1:50");
     assertThat(result.getTextWrittenToStandardError(), containsString("The number of arguments is not valid.") );
  }

  @Test
  public void checkCallerPhoneNumberTest(){
     MainMethodResult result = invokeMain(Project2.class, "apple","503-222-33333","503-333-2222","1-12-2020","1:30","1-12-2020","1:50");
     assertThat(result.getTextWrittenToStandardError(), containsString("Caller phone number is malformed.") );
  }

  @Test
  public void checkCalleePhoneNumberTest(){
     MainMethodResult result = invokeMain(Project2.class, "aplle","503-222-3333","503-333-22d22","1-12-2020","1:30","1-12-2020","1:50");
     assertThat(result.getTextWrittenToStandardError(), containsString("Callee phone number is malformed.") );
  }

 /* @Test
  public void checkREADMETest(){
     MainMethodResult result = invokeMain(Project1.class, "-print","-README");
    // assertThat(result.getExitCode(), equalTo(0));
     assertThat(result.getTextWrittenToStandardError(), containsString( "Name: Yan Li. Project 1: The PhoneBill and PhoneCall."
                    + "The assignment is designed to pass in arguments from the command line to record"
                    + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                    + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                    + "-print command prints the description of a phone call, while README command outputs the README."));

  }

  */
  @Test
  public void checkValidityOfPhoneNumber(){
     MainMethodResult result = invokeMain();

  }



}