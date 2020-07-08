package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private String callee; // new field callee added
  private String caller;
  private String start;
  private String end;

  /**
   *
   * @param callee
   */
  public PhoneCall(String caller, String callee,String start, String end ) {
    this.caller = caller;
    this.callee = callee;
    this.start = start;
    this.end = end;
  }

  @Override
  public String getCaller() {
    return this.caller;
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
