package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {

   /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
   /*
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(mainClass, args);
    }
*/
   private MainMethodResult invokeMain(String... args){return invokeMain( Project3.class, args);}
    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void NoCommandLineArgumentsTest() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void checkInvalidNumberOfArgumentsTest() {
        MainMethodResult result = invokeMain( "ksi", "eis", "apple", "apple", "orange", "1-12-2020", "1:30", "am", "1-12-2020", "1:50");
        assertThat(result.getTextWrittenToStandardError(), containsString("The number of arguments is not valid."));
    }

    @Test
    public void checkCallerPhoneNumberTest() {
        MainMethodResult result = invokeMain( "apple", "503-222-33333", "503-333-2222", "1-12-2020", "1:30", "am", "1-12-2020", "1:50", "am");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid caller phone number input!"));
    }

    @Test
    public void checkCalleePhoneNumberTest() {
        MainMethodResult result = invokeMain( "aplle", "503-222-3333", "503-333-22d22", "1-12-2020", "1:30", "pm", "1-12-2020", "1:50", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid callee phone number input!"));
    }
     @Test
     public void checkREADMETest(){
        MainMethodResult result = invokeMain( "-README");
       // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString( "Name: Yan Li. Project 3: The PhoneBill and PhoneCall."
                       + "The assignment is designed to pass in arguments from the command line to record"
                       + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                       + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                       + "-print command prints the description of a phone call, while README command outputs the README."));

     }

    @Test
    public void checkREADMEOverrideTest(){
        MainMethodResult result = invokeMain( "-README", "-print", "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString( "Name: Yan Li. Project 3: The PhoneBill and PhoneCall."
                + "The assignment is designed to pass in arguments from the command line to record"
                + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                + "-print command prints the description of a phone call, while README command outputs the README."));

    }
     @Test
    public void checkPrintTag() {
        MainMethodResult result = invokeMain("-print", "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString(""));
    }


    @Test
    public void checkTheValidNumberOfArgumentsEntered() {
        MainMethodResult result = invokeMain("apple", "530-344-3340");
        assertThat(result.getTextWrittenToStandardError(), containsString("The number of arguments is not valid"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfCallerPhoneNumber() {
        MainMethodResult result = invokeMain("apple", "530-344-334ee", "503-222-3333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid caller phone number input!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfCalleePhoneNumber() {
        MainMethodResult result = invokeMain( "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid callee phone number input!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfStartDateFormat() {
        MainMethodResult result = invokeMain( "apple", "530-344-3345", "503-222-3333", "1/12/20", "12:12", "pm", "1/12/2020", "12:32", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid start date time input!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfStartTimeFormat() {
        MainMethodResult result = invokeMain( "apple", "530-344-3345", "503-222-3333", "1/12/2020", "12/12", "pm", "1/12/2020", "12:32", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid start date time input!"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfEndDateTimeBeforeStartDateTime() {
        MainMethodResult result = invokeMain( "apple", "530-344-3345", "503-222-3333", "1/12/2020", "12:12", "pm", "1/1/2020", "12:32", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("The end date time is before the start date time"));
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void checkTheValidityOfEndDateTimeFormat() {
        MainMethodResult result = invokeMain( "apple", "530-344-3345", "503-222-3333", "1/12/2020", "12:12", "pm", "1/01/2020", "01:60", "pm");
        assertThat(result.getTextWrittenToStandardError(), containsString("Invalid end date time input!"));
        assertThat(result.getExitCode(), equalTo(1));
    }
    @Test
    public void dumperTest() {
        MainMethodResult result = invokeMain("-textFile", "tet/test.txt", "-print", "Project2", "123-456-7890", "234-567-9081", "01/07/2020", "07:00", "am", "01/17/2020", "1:00", "pm" );
        assertThat(result.getExitCode(), equalTo(0));
    }

    @Test
    public void prettyTest() {
        MainMethodResult result = invokeMain("-textFile", "tet/test.txt", "-print", "-pretty", "-","Project2", "123-456-7890", "234-567-9081", "01/07/2020", "07:00", "am", "01/17/2020", "1:00", "pm" );
        assertThat(result.getExitCode(), equalTo(0));
    }

    /*@Test
    public void checkTheValidityOfPrintCommand() {
        MainMethodResult result = invokeMain( "-print","apple", "530-344-3345", "503-222-3333", "1/12/2020", "12:12", "1/01/2020", "12:55");
        assertThat(result.getTextWrittenToStandardError(), containsString("Wrong end time format"));
        assertThat(result.getExitCode(), equalTo(1));
    }

     */


}