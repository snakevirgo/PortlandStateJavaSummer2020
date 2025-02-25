package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneBill} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneBillTest {
    @Test
    public void createPhoneBill() {
        PhoneBill phoneBill = new PhoneBill("adf", new ArrayList<PhoneCall>());
    }

    @Test
    public void getCustomerTest(){
        ArrayList<PhoneCall> calls1 = new ArrayList<>();
        PhoneBill customerName = new PhoneBill("bill", calls1);
        assertThat(customerName.getCustomer(), equalTo( "bill") );
    }

    @Test
    public void addPhoneCallTest(){
        PhoneCall variable;
        try {
            variable = new PhoneCall("Apple", "Orange", "01/02/2020", "13:00:00", "01/20/2020", "13:00:00");
            ArrayList<PhoneCall> calls1 = new ArrayList<>();
            PhoneBill variable2 = new PhoneBill("Banana", calls1);
            variable2.addPhoneCall(variable);  //added phone call to phone bill
            ArrayList<PhoneCall> array = (ArrayList<PhoneCall>) variable2.getPhoneCalls();
            assertThat(array.get(0), equalTo(variable));
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void failedAddPhoneCallTest(){
        PhoneCall variable;
        try {
            variable = new PhoneCall("Apple", "Orange", "01-02/2020", "13:00:00", "01/20/2020", "13:00:00");
            ArrayList<PhoneCall> calls1 = new ArrayList<>();
            PhoneBill variable2 = new PhoneBill("Banana", calls1);
            variable2.addPhoneCall(variable);  //added phone call to phone bill
            ArrayList<PhoneCall> array = (ArrayList<PhoneCall>) variable2.getPhoneCalls();
            assertThat(array.get(0), equalTo(variable));
        }catch(Exception e){
            System.err.println("It needs to be here");
        }
    }


    @Test
    public void getPhoneCallsTest(){
         ArrayList<PhoneCall> calls1 = new ArrayList<>();
         PhoneBill customerName = new PhoneBill("bill", calls1);
         assertThat(customerName.getPhoneCalls(), equalTo(calls1) );
    }
    /*
    @Test(expected = UnsupportedOperationException.class)
  public void getStartTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall(null);
    call.getStartTimeString();
  }

     */

}
