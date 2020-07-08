package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private String callee; // new field callee added
  private String caller;
  private String startDate;
  private String startTime;
  private String endDate;
  private String endTime;

  /**
   *
   * @param callee
   */
  public PhoneCall(String caller, String callee,String startDate, String startTime, String endDate, String endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startDate = startDate;
    this.startTime = startTime;
    this.endDate = endDate;
    this.endTime = endTime;
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
      return this.startDate + " " + this.startTime;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
      return this.endDate + " " + this.endTime;
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
