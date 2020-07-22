package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import jdk.jshell.spi.ExecutionControlProvider;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * The main class for the CS410J Phone Bill Project.
 */
public class Project3 {
  /**
   * Main function. All the arguments from command are parsed here. The conditional if and else
   * statements help to put them apart.
   *
   * * @param args
   *
   * return 0 for success and 1 for error
   */
  public static void main(String[] args) {
    //PhoneCall call = new PhoneCall(null);  // Refer to one of Dave's classes so that we can be sure it is on the classpat

    //arguments
    String customer1 = "";
    String caller1 = "";
    String callee1 = "";
    String startDate = "";
    String startTime = "";
    String endDate = "";
    String endTime = "";
    String textFile = "";
    String prettyFileName = "";

    //flag for print
    int flagPrint = 0;
    int flagFile = 0;
    int flagPretty = 0;
   if (args.length == 0) {
     System.err.println("Missing command line arguments");
     System.exit(1);
   }else if(args.length < 0){
     System.err.println("Unknown command line argument");
     System.exit(1);
    } else {
      for (int i = 0; i < args.length; ++i) {
        String arg = args[i];
        if (arg.charAt(0) == '-') {
          String action = arg.substring(1);
          if (action.equals("print")) {
            flagPrint = 1;

          }else if(action.equals("textFile")){
            flagFile = 1;
            if (i+1 < args.length) {
              textFile = args[i+1];
            }
            else {
              System.err.print("Text file name is not given.");
              System.exit(1);
            }
            i++;
          } else if (action.equals("README")) {
            String readme = "Name: Yan Li. Project 3: The PhoneBill and PhoneCall."
                    + "The assignment is designed to pass in arguments from the command line to record"
                    + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                    + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                    + "-print command prints the description of a phone call, while README command outputs the README.";
            System.out.println(readme);
            System.exit(0);
          }
          else if(action.equals("pretty")) {
            flagPretty = 1;
            if (i+1 < args.length) {
                prettyFileName = args[i+1];
                i++;
            }
            else {
                System.err.print("pretty file is not given.");
                System.exit(1);
            }
          }

          else {
            System.err.print("The command is not recognized.");
            System.exit(1);
          }
        } else {
          if (args.length - i == 9) {
            customer1 = args[i];

            caller1 = args[i + 1];
            callee1 = args[i + 2];

            startDate = args[i + 3];
            startTime = args[i + 4] + " " + args[i+5];

            endDate = args[i + 6];
            endTime = args[i + 7] + " " + args[i+8];

              break;
          } else {
            System.err.print("The number of arguments is not valid.");
            System.exit(1);
          }
        }
      }

      PhoneCall call = null;
      ArrayList<PhoneCall> calls = new ArrayList<>();
      try {
          call = new PhoneCall(caller1, callee1, startDate, startTime, endDate, endTime);
          calls.add(call);
      }
       catch (Exception err) {
          String errMsg = err.getMessage();
          System.err.print(errMsg);
          System.exit(1);
       }

      PhoneBill phoneBill = null;
     if (flagFile == 1) {

          try {
            TextParser textParser = new TextParser(textFile); // allocate the reader
            phoneBill = (PhoneBill) textParser.parse();  //reading text file content into phone bill
            String customerName = phoneBill.getCustomer();
            if (!customerName.equals(customer1)) {
              System.err.print("The customer name in the file is not equal to the given customer name.");
              System.exit(1);
            }

            phoneBill.addPhoneCall(call);
          } catch (ParserException err) {
            String errMsg = err.getMessage();
            if (!errMsg.equals("The file is empty") && ! errMsg.equals("New File is created")) {
              System.err.print(err.getMessage());
              System.exit(1);
            }
            phoneBill = new PhoneBill(customer1, calls);
          }


        //  phoneBill = new PhoneBill(customer1, calls);

      // phoneBill.addPhoneCall(call);
        TextDumper textDumper = new TextDumper(textFile); // writing phone bill to the file

        try  {
          textDumper.dump(phoneBill);
        } catch (IOException err) {
          System.err.print(err.getMessage());
          System.exit(1);
        }
     } else {
       phoneBill = new PhoneBill(customer1, calls);
     }

      if (flagPrint == 1) {
        System.out.println(phoneBill.toString());
        System.out.println(call.toString());
      }

      if (flagPretty == 1){
          PrettyPrinter pretty = new PrettyPrinter(prettyFileName);
          try {
              pretty.dump(phoneBill);
          }catch(IOException e){
              System.err.println(e.getMessage());
              System.exit(1);
          }
      }

      /*
    for (String arg : args) {
      System.out.println(arg);
    }*/

      System.exit(0);

    }
  }

//  /**
//   * This private function will check the validity format of phone number.
//   * @param phoneNumber
//   * @return 0 for malformed phone number, 1 for correct phone number
//   */
  /*// "541-512-4564" splittedPhoneNUmber = ["541","512","4564"]
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
/*
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
/*
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
  */
}