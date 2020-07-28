package edu.pdx.cs410J.yal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneCallTest {

    //  @Test(expected = UnsupportedOperationException.class)
//  public void getStartTimeStringNeedsToBeImplemented() {
//    PhoneCall call = new PhoneCall(null);
//    call.getStartTimeString();
//  }
//
    @Test
    public void initiallyAllPhoneCallsHaveTheSameCallee() {
        String callee = "Callee";
        PhoneCall call;
        try {
            call = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "12:30", "01/10/2020", "13:30");
            assertThat(call.getCallee(), equalTo("503-111-1111"));
        } catch (Exception er){
            System.err.println("It should not be here");
        }

    }

//  @Test
//  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
//    PhoneCall call = new PhoneCall(null);
//    assertThat(call.getStartTime(), is(nullValue()));
//  }

    //validates phone numbers and dates
    @Test
    public void getCallerTest(){
        PhoneCall number1;
        try {
            number1 = new PhoneCall("503-222-3333", "503-111-1111", "01/15/2020", "12:30", "1/20/2020", "13:30");
            assertThat(number1.getCaller(), equalTo("503-222-3333"));
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void getCalleeTest(){
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "12:30", "01/10/2020", "13:30");
            assertThat(number2.getCallee(), equalTo("503-111-1111"));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getStartTimeStringTest(){
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "12:30", "01/10/2020", "13:30");
            assertThat(number2.getStartTimeString(), equalTo("01/10/2020 12:30"));
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getEndTimeStringTest(){
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "12:30", "01/10/2020", "13:30");
            assertThat(number2.getEndTimeString(), equalTo("01/10/2020 13:30"));
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

//  @Test
//  public void malformedDate(){
//    PhoneCall number2;
//    try {
//      number2 = new PhoneCall("503-222-3333", "503-111-1111", "01-10/2020", "12:30", "01/10/2020", "13:30");
//    }catch(Exception e){
//      System.err.println("It should be here");
//    }
//   }


}
