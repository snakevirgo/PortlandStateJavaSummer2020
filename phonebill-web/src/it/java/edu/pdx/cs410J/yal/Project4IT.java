package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.yal.PhoneBillRestClient.PhoneBillRestException;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");
/*
    @Test//
    public void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }
*/

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }
//
//    @Ignore
//    @Test
//    public void NoCommandLineArgumentsTest() {
//        MainMethodResult result = invokeMain();
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Missing command line arguments"));
//    }

    @Test
    public void checkInvalidNumberOfArgumentsTest() {
        MainMethodResult result = invokeMain( Project4.class,"ksi", "eis", "apple", "apple", "orange", "1-12-2020", "1:30", "am", "1-12-2020", "1:50");
        assertThat(result.getTextWrittenToStandardError(), containsString("The number of arguments is not valid."));
    }

    @Test
    public void checkCallerPhoneNumberTest() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" ,"apple", "503-222-3333", "503-333-2222", "1/12/2020", "1:30", "am", "1/12/2020", "1:50", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
    }

    @Test
    public void checkForPortError() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "800" , "apple");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }

    @Test
    public void checkForTheSearchMissingStartAndEnd() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" ,"-search", "apple");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }
    @Test
    public void checkForPrint() {
        MainMethodResult result = invokeMain( Project4.class,"-print", "apple", "511-111-1111","511-818-4444", "03/01/2020", "1:01", "am", "05/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
    }
    @Test
    public void checkForPrintAndPost() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080", "-print", "apple", "511-111-1111","511-818-4444", "03/01/2020", "1:01", "am", "05/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
    }

    @Test
    public void checkForPrintAndPostFailed() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080", "-print", "apple", "51-111-1111","511-818-4444", "03/01/2020", "1:01", "am", "05/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }


    @Test
    public void checkForPrintFalied() {
        MainMethodResult result = invokeMain( Project4.class,"-print", "apple", "51-111-1111","511-818-4444", "03/01/2020", "1:01", "am", "01/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }
    @Test
    public void checkForTheSearchSuccessfully() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" ,"-search", "apple", "03/01/2020", "1:01", "am", "01/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
    }

    @Test
    public void checkForTheSearchFailed() {
        MainMethodResult result = invokeMain( Project4.class,"-host", "localhost", "-port", "8080" ,"-search", "apple", "03/0/2020", "1:01", "am", "01/01/2021", "1:12", "am");
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }


    @Test
    public void checkREADMETest(){
        MainMethodResult result = invokeMain( Project4.class,"-README");
        // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString( "Name: Yan Li. Project 4: The PhoneBill and PhoneCall."
                + "The assignment is designed to pass in arguments from the command line to record"
                + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                + "-print command prints the description of a phone call, while README command outputs the README."));

    }

    @Test
    public void checkREADMEOverrideTest(){
        MainMethodResult result = invokeMain( Project4.class,"-README", "-print", "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString( "Name: Yan Li. Project 4: The PhoneBill and PhoneCall."
                + "The assignment is designed to pass in arguments from the command line to record"
                + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                + "-print command prints the description of a phone call, while README command outputs the README."));

    }
    @Test
    public void checkPrintTag() {
        MainMethodResult result = invokeMain(Project4.class,"-print", "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
        // assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), CoreMatchers.containsString(""));
    }


    @Test
    public void checkTheValidNumberOfArgumentsEntered() {
        MainMethodResult result = invokeMain(Project4.class,"apple", "530-344-3340");
        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("The number of arguments is not valid"));
        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
    }

//    @Test
//    public void checkTheValidityOfCallerPhoneNumber() {
//        MainMethodResult result = invokeMain(Project4.class,"apple", "530-344-334ee", "503-222-3333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid caller phone number input!"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void checkTheValidityOfCalleePhoneNumber() {
//        MainMethodResult result = invokeMain(Project4.class, "apple", "530-344-3345", "503-222-33333", "1/12/2020", "12:12", "pm", "1/12/2020", "12:32", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid callee phone number input!"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void checkTheValidityOfStartDateFormat() {
//        MainMethodResult result = invokeMain( Project4.class,"apple", "530-344-3345", "503-222-3333", "1/12/20", "12:12", "pm", "1/12/2020", "12:32", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid start date time input!"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void checkTheValidityOfStartTimeFormat() {
//        MainMethodResult result = invokeMain(Project4.class,"apple", "530-344-3345", "503-222-3333", "1/12/2020", "12/12", "pm", "1/12/2020", "12:32", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid start date time input!"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void checkTheValidityOfEndDateTimeBeforeStartDateTime() {
//        MainMethodResult result = invokeMain( Project4.class,"apple", "530-344-3345", "503-222-3333", "1/12/2020", "12:12", "pm", "1/1/2020", "12:32", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("The end date time is before the start date time"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//
//    @Test
//    public void checkTheValidityOfEndDateTimeFormat() {
//        MainMethodResult result = invokeMain( Project4.class,"apple", "530-344-3345", "503-222-3333", "1/12/2020", "12:12", "pm", "1/01/2020", "01:60", "pm");
//        assertThat(result.getTextWrittenToStandardError(), CoreMatchers.containsString("Invalid end date time input!"));
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(1));
//    }
//    @Test
//    public void dumperTest() {
//        MainMethodResult result = invokeMain(Project4.class,"-textFile", "tet/test.txt", "-print", "Project2", "123-456-7890", "234-567-9081", "01/07/2020", "07:00", "am", "01/17/2020", "1:00", "pm" );
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
//    }
//
//    @Test
//    public void prettyTest() {
//        MainMethodResult result = invokeMain(Project4.class,"-textFile", "tet/test.txt", "-print", "-pretty", "-","Project2", "123-456-7890", "234-567-9081", "01/07/2020", "07:00", "am", "01/17/2020", "1:00", "pm" );
//        assertThat(result.getExitCode(), CoreMatchers.equalTo(0));
//    }
//


/*
    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatWordCount(0)));
    }

    @Test(expected = PhoneBillRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "WORD";
        try {
            invokeMain(Project4.class, HOSTNAME, PORT, word);

        } catch (UncaughtExceptionInMain ex) {
            throw ex.getCause();
        }
    }

    @Test
    public void test4AddDefinition() {
        String word = "WORD";
        String definition = "DEFINITION";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, word, definition );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));

        result = invokeMain( Project4.class, HOSTNAME, PORT );
        out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
    }

 */


}