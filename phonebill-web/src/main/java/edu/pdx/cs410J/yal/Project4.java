package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.apache.groovy.json.internal.IO;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String[] args) {

        //arguments
        String customer1 = "";
        String caller1 = "";
        String callee1 = "";
        String startDate = "";
        String startTime = "";
        String endDate = "";
        String endTime = "";

        String hostName = "";
        int port = 0;
        //flag for print
        int flagPrint = 0;
        int flagPost = 0;
        int flagHostName = 0;
        int flagSearch = 0;
        int flagPort = 0;
        int printPhoneCall = 0;
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        } else if (args.length < 0) {
            System.err.println("Unknown command line argument");
            System.exit(1);
        } else {
            for (int i = 0; i < args.length; ++i) {
                String arg = args[i];
                if (arg.charAt(0) == '-') {
                    String action = arg.substring(1);
                    if (action.equals("print")) {
                        flagPrint = 1;
                    } else if (action.equals("README")) {
                        String readme = "Name: Yan Li. Project 4: The PhoneBill and PhoneCall."
                                + "The assignment is designed to pass in arguments from the command line to record"
                                + "brief phone call records indicating the customer name, caller, callee and their timestamps"
                                + "Time stamps are in month-day-year. In addition, there are two options that are allowed: "
                                + "-print command prints the description of a phone call, while README command outputs the README.";
                        System.out.println(readme);
                        System.exit(0);
                    } else if (action.equals("host")) {
                        if (args.length - i < 1) {
                            System.err.print("The number of arguments is not valid.");
                            System.exit(1);
                        }
                        flagHostName = 1;

                        hostName = args[i + 1];
                        i++;
                    } else if (action.equals("port")) {
                        if (args.length - i < 1) {
                            System.err.print("The number of arguments is not valid.");
                            System.exit(1);
                        }
                        try {
                            port = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException err) {
                            System.err.print("The port is not an integer");
                            System.exit(1);
                        }
                        i++;
                        flagPort = 1;
                    } else if (action.equals("search")) {
                        flagSearch = 1;
                    } else {
                        System.err.print("The command is not recognized.");
                        System.exit(1);
                    }
                } else {
                    if (args.length - i == 1) {
                        customer1 = arg;
                        printPhoneCall = 1;
                    } else if (flagSearch == 1 && args.length - i == 7) {
                        customer1 = args[i];
                        startDate = args[i + 1];
                        startTime = args[i + 2] + " " + args[i + 3];

                        endDate = args[i + 4];
                        endTime = args[i + 5] + " " + args[i + 6];
                        break;
                    }
                else if (args.length - i == 9) {
                        customer1 = args[i];

                        caller1 = args[i + 1];
                        callee1 = args[i + 2];

                        startDate = args[i + 3];
                        startTime = args[i + 4] + " " + args[i + 5];

                        endDate = args[i + 6];
                        endTime = args[i + 7] + " " + args[i + 8];
                        if (flagHostName == 1) {
                            flagPost = 1;
                        }

                        break;
                    } else {
                        System.err.print("The number of arguments is not valid.");
                        System.exit(1);
                    }
                }
            }
        }

        if (flagHostName + flagPort == 1) {
            System.err.println("The host name or the post is missing");
            System.exit(1);
        }

        if (printPhoneCall == 1 && flagSearch == 0) {
            if (flagHostName == 1) {
                PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);

                try {
                    String response = client.getPhoneCalls(customer1);
                    System.out.println(response);
                } catch (IOException | PhoneBillRestClient.PhoneBillRestException  err) {
                    System.err.println(err.getMessage());
                    System.exit(1);
                }
            } else {
                System.err.println("The host name or the post is missing");
                System.exit(1);
            }
        } else if (flagPost == 1 ) {
//             PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
            PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
            try {
                client.postNewPhoneCall(customer1, caller1, callee1, startDate + " " + startTime, endDate + " " + endTime);
            } catch (IOException | PhoneBillRestClient.PhoneBillRestException  err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        } else if (flagSearch == 1 && flagHostName == 1) {
            if (args.length < 12) {
                System.err.println("Search needs name, start and end");
                System.exit(1);
            }

                // doing search here
                PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
                try {
                    String res = client.searchPhoneCalls(customer1, startDate + " " + startTime, endDate + " " + endTime);
                    System.out.println(res);
                    System.exit(0);
                } catch (IOException | PhoneBillRestClient.PhoneBillRestException err) {
                    System.err.println(err.getMessage());
                    System.exit(1);
                }


        }
        PhoneBill phoneBill;
        PhoneCall call;
        if (flagPrint == 1) {
            try {
                call = new PhoneCall(caller1, callee1, startDate, startTime, endDate, endTime);
                ArrayList<PhoneCall> calls = new ArrayList<>();
                calls.add(call);
                phoneBill = new PhoneBill(customer1, calls);
                System.out.println(phoneBill.toString());
                System.out.println(call.toString());
            }
            catch (Exception err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        }

        System.exit(0);
    }
      /*
    for (String arg : args) {
      System.out.println(arg);
    }*/


    /**
     * Makes sure that the give response has the expected HTTP status code
     *
     * @param code     The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode(int code, HttpRequestHelper.Response response) {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                    response.getCode(), response.getContent()));
        }
    }

    /**
     *
     * @param message
     */
    private static void error(String message) {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }
}

//    /**
//     * Prints usage information for this program and exits
//     * @param message An error message to print
//     */
//    private static void usage( String message )
//    {
//        PrintStream err = System.err;
//        err.println("** " + message);
//        err.println();
//        err.println("usage: java Project4 host port [word] [definition]");
//        err.println("  host         Host of web server");
//        err.println("  port         Port of web server");
//        err.println("  word         Word in dictionary");
//        err.println("  definition   Definition of word");
//        err.println();
//        err.println("This simple program posts words and their definitions");
//        err.println("to the server.");
//        err.println("If no definition is specified, then the word's definition");
//        err.println("is printed.");
//        err.println("If no word is specified, all dictionary entries are printed");
//        err.println();
//
//        System.exit(1);
//    }