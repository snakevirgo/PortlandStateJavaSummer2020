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

  @Test(expected = UnsupportedOperationException.class)
  public void getStartTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall(null);
    call.getStartTimeString();
  }

  @Test
  public void initiallyAllPhoneCallsHaveTheSameCallee() {
    String callee = "Callee";
    PhoneCall call = new PhoneCall(callee);
    assertThat(call.getCallee(), equalTo(callee));
  }

  @Test
  public void forProject1ItIsOkayIfGetStartTimeReturnsNull() {
    PhoneCall call = new PhoneCall(null);
    assertThat(call.getStartTime(), is(nullValue()));
  }

  //validates phone numbers and dates
  
}
