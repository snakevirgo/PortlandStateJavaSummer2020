package edu.pdx.cs410J.yal;

import java.util.ArrayList;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {
  /**
   * Main function
   * * @param args
   */
  public static void main(String[] args) {
    //PhoneCall call = new PhoneCall(null);  // Refer to one of Dave's classes so that we can be sure it is on the classpat


    String customer1 = "";
    String caller1 = "";
    String callee1 = "";
    String startDate = "";
    String startTime = "";

    String endDate = "";
    String endTime = "";


    int flagPrint = 0;

    //System.out.println(call);

    if (args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    } else {
      for (int i = 0; i < args.length; ++i) {
        String arg = args[i];
        if (arg.length() < 1) {
          System.err.println("Unknown command");
          System.exit(1);
        } else if (arg.charAt(0) == '-') {
          String action = arg.substring(1);
          if (action.equals("print")) {
            flagPrint = 1;
          } else if (action.equals("README")) {
            String readme = "This is readme. Read me more";
            System.out.println(readme);
            System.exit(0);
          }
        } else {
          if (args.length - i == 7) {
            customer1 = args[i];
            caller1 = args[i + 1];
            if (checkPhoneNumber(caller1) == 0) {
              System.err.println("Caller phone number is malformed.");
              System.exit(0);
            }
            callee1 = args[i + 2];
            if (checkPhoneNumber(callee1) == 0) {
              System.err.println("Callee phone number is malformed.");
              System.exit(0);
            }
            startDate = args[i + 3];
            if (checkDate(startDate) == 0) {
              System.err.println("Wrong start date format.");
              System.exit(1);
            }
            startTime = args[i + 4];
            if (checkTime(startTime) == 0) {
              System.err.println("Wrong start time format.");
              System.exit(0);
            }

            endDate = args[i + 5];
            if (checkDate(endDate) == 0) {
              System.err.println("Wrong end date format.");
              System.exit(0);
            }
            endTime = args[i + 6];
            if (checkTime(endTime) == 0) {
              System.err.println("Wrong end time format.");
              System.exit(0);
            }

            break;
          } else {
            System.err.print("The number of arguments is not valid");
            System.exit(1);
          }
        }
      }

      PhoneCall call = new PhoneCall(caller1, callee1, startDate, startTime, endDate, endTime);
      ArrayList<PhoneCall> calls = new ArrayList<>();
      calls.add(call);
      PhoneBill bill = new PhoneBill(customer1, calls);
      if (flagPrint == 1) {
        System.out.println(bill.toString());
        System.out.println(call.toString());
      }

      /*
    for (String arg : args) {
      System.out.println(arg);
    }*/

      System.exit(1);

    }
  }

  /**
   * This function will check the formation of phone number
   *
   * @param phoneNumber
   * @return 0 for malformed phone number, 1 for correct phone number
   */
  // "541-512-4564" ["541","512","4564"]
  private static int checkPhoneNumber(String phoneNumber) {
    if (phoneNumber.length() != 12) {
      return 0;
    }
    String splittedPhoneNumber[] = phoneNumber.split("-");
    if (splittedPhoneNumber.length != 3) {
      return 0;
    }

    for (String number : splittedPhoneNumber) {
      try {
        Integer.parseInt(number);
      } catch (Exception e) {
        return 0;
      }
    }
    return 1;
  }

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

  private static int checkDate(String date) {
    if (date.length() < 8) {
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