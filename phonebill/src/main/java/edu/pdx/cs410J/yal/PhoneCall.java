package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {
  private String callee; // new field callee added
  private String caller;
  private Date startDateTime;
  private Date endDateTime;

  /**
   * @param callee
   */
  public PhoneCall(String caller, String callee, String startDate, String startTime, String endDate, String endTime) throws Exception {

    if (checkPhoneNumber(caller) == 0) {
      throw new Exception("Invalid caller phone number input!");
    }
    this.caller = caller;
    if (checkPhoneNumber(callee) == 0) {
      throw new Exception("Invalid callee phone number input!");
    }
    this.callee = callee;

    String startDT = startDate + " " + startTime;
    try {
      this.startDateTime = this.convertToDateFormat(startDT);
    } catch (Exception err) {
      throw new Exception("Invalid start date time input!");
    }

    String endDT = endDate + " " + endTime;
    try {
      this.endDateTime = this.convertToDateFormat(endDT);
    } catch (Exception err) {
      throw new Exception("Invalid end date time input!");
    }

    long dur = this.Duration();
    if (dur < 0) {
      throw new Exception("The end date time is before the start date time");
    }
  }

  public Date getStartDateTime() {
    return this.startDateTime;
  }

  public Date getEndDateTime() {
    return this.endDateTime;
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
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    return dateFormat.format(this.startDateTime).toString();
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    return dateFormat.format(this.endDateTime).toString();
//    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  private Date convertToDateFormat(String datetime) throws Exception {
    Date resultedValue = null;
    datetime = datetime.toUpperCase();

    String splittedDateTime[] =  datetime.split(" ");
    if (splittedDateTime.length == 3) {
      String date[] = splittedDateTime[0].split("/");
      if (date.length == 3) {
        String y = date[2];
        if (y.length() == 4) {
          try {
            SimpleDateFormat check = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            check.setLenient(false);
            resultedValue = check.parse(datetime);
          } catch (ParseException err) {
            throw new Exception(err.getMessage());
          }
        }
      }
    }
    if (resultedValue == null) {
      throw new Exception("The format of the day or time is incorrect");
    }

    return resultedValue;
  }
// 1s = 1000 ms
//  1m = 60 s
  //get the duration of each phone call in minutes
  public long Duration(){
    long diff = this.endDateTime.getTime() - this.startDateTime.getTime();
    return (diff / 60000);
  }
  //compare 2 phone calls: cmp StartDateTime, if StartDateTime equal,
  // It will then compare caller the return the result of that comparison.
  //  <0, 0 , > 0
  @Override
  public int compareTo(PhoneCall target) {
    Date startDate = target.getStartDateTime();
    int dCmp = this.startDateTime.compareTo(startDate);
    if (dCmp == 0) {
      return this.getCaller().compareTo(target.getCaller());
    }
    return dCmp;
  }

  /**
   * This private function will check the validity format of phone number.
   *
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
}
///**
//   * This private function checks the validity format of the time.
//   * It checks the length and the location of ":" as part of its time format
//   * @param time
//   * @return 0 for malformed time format, 1 for correct time
//   */
  // "541-512-4564" ["541","512","4564"]
  // 1:1 19:31
//
//  private static int checkTime(String time) {
//    if (time.length() < 3 || time.length() > 5) {
//      return 0;
//    }
//
//    String splittedTime[] = time.split(":");
//    if (splittedTime.length != 2) {
//      return 0;
//    }
//
//    for (int i = 0; i < splittedTime.length; i++) {
//      try {
//        String val = splittedTime[i];
//        int intVal = Integer.parseInt(val);
//        if (i == 0) {
//          if (intVal < 0 || intVal > 23) {
//            return 0;
//          }
//        } else {
//          if (intVal < 0 || intVal > 59) {
//            return 0;
//          }
//        }
//      } catch (Exception e) {
//        return 0;
//      }
//    }
//    return 1;
//  }
//
//  /**
//   *  This private function checks the validity format of the date.
//   * It checks the length and the location of "/" as part of its date format
//   *
//   * @param date
//   * @return 0 for malformed date format, 1 for correct date
//   */
//  private static int checkDate(String date) {
//    if (date.length() < 9) {
//      return 0;
//    }
//    String splittedDate[] = date.split("/");
//    if (splittedDate.length != 3) {
//      return 0;
//    }
//
//    for (int i = 0; i < splittedDate.length; i++) {
//      try {
//        String val = splittedDate[i];
//        int intVal = Integer.parseInt(val);
//        if (i == 2) {
//          if (val.length() != 4) {
//            return 0;
//          }
//        } else if (i == 0) {
//          if (intVal < 1 || intVal > 12) {
//            return 0;
//          }
//        } else {
//          if (intVal < 0 || intVal > 31) {
//            return 0;
//          }
//        }
//      } catch (Exception e) {
//        return 0;
//      }
//    }
//    return 1;
//  }
//}
