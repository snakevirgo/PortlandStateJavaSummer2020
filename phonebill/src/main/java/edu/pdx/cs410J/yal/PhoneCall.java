package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private final String callee; // new field callee added

  //constructor
  public PhoneCall(String callee) {
    this.callee = callee;
  }

  @Override
  public String getCaller() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getCallee() {
    return this.callee;
  }

  @Override
  public String getStartTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
