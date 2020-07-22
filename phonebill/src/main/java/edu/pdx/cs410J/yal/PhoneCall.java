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
  public PhoneCall(String caller, String callee,String startDate, String startTime, String endDate, String endTime) throws Exception{

    if (checkPhoneNumber(caller) == 0) {
        throw new Exception("Invalid caller phone number input!");
    }
    this.caller = caller;
    if(checkPhoneNumber(callee) == 0){
        throw new Exception("Invalid callee phone number input!");
    }
    this.callee = callee;
    if(checkDate(startDate) == 0){
        throw new Exception("Invalid start date input!");
    }
    this.startDate = startDate;
    if(checkTime(startTime) == 0){
        throw new Exception("Invalid start time input!");
    }
    this.startTime = startTime;
    if(checkDate(endDate) == 0){
        throw new Exception("Invalid end date input!");
    }
    this.endDate = endDate;
    if(checkTime(endTime) == 0){
        throw new Exception("Invalid end time input!");
    }
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

  /**
   * This private function will check the validity format of phone number.
   * @param phoneNumber
   * @return 0 for malformed phone number, 1 for correct phone number
   */
  // "541-512-4564" splittedPhoneNUmber = ["541","512","4564"]
  private static int checkPhoneNumber(String phoneNumber) {
    if (phoneNumber.length() != 12) {
      return 0;
    }
    String splittedPhoneNumber[] = phoneNumber.split("-");
    if (splittedPhoneNumber.length != 3) {
      return 0;
    }
    // number = "4564"
    for (String number : splittedPhoneNumber) {
      try {
        Integer.parseInt(number);
      } catch (Exception e) {
        return 0;
      }
    }
    return 1;
  }
/**
   * This private function checks the validity format of the time.
   * It checks the length and the location of ":" as part of its time format
   * @param time
   * @return 0 for malformed time format, 1 for correct time
   */
  // "541-512-4564" ["541","512","4564"]
  // 1:1 19:31

  private static int checkTime(String time) {
    if (time.length() < 3 || time.length() > 5) {
      return 0;
    }

    String splittedTime[] = time.split(":");
    if (splittedTime.length != 2) {
      return 0;
    }

    for (int i = 0; i < splittedTime.length; i++) {
      try {
        String val = splittedTime[i];
        int intVal = Integer.parseInt(val);
        if (i == 0) {
          if (intVal < 0 || intVal > 23) {
            return 0;
          }
        } else {
          if (intVal < 0 || intVal > 59) {
            return 0;
          }
        }
      } catch (Exception e) {
        return 0;
      }
    }
    return 1;
  }

  /**
   *  This private function checks the validity format of the date.
   * It checks the length and the location of "/" as part of its date format
   *
   * @param date
   * @return 0 for malformed date format, 1 for correct date
   */
  private static int checkDate(String date) {
    if (date.length() < 9) {
      return 0;
    }
    String splittedDate[] = date.split("/");
    if (splittedDate.length != 3) {
      return 0;
    }

    for (int i = 0; i < splittedDate.length; i++) {
      try {
        String val = splittedDate[i];
        int intVal = Integer.parseInt(val);
        if (i == 2) {
          if (val.length() != 4) {
            return 0;
          }
        } else if (i == 0) {
          if (intVal < 1 || intVal > 12) {
            return 0;
          }
        } else {
          if (intVal < 0 || intVal > 31) {
            return 0;
          }
        }
      } catch (Exception e) {
        return 0;
      }
    }
    return 1;
  }
}
