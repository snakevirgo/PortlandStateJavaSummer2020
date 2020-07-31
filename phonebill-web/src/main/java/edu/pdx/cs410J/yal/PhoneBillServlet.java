package edu.pdx.cs410J.yal;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.w3c.dom.Text;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
// http://host:port/phonebill/calls?customer=name&start=startDateTime&end=endDateTime
// ∗T returns all of given pmat used by TextDumper
public class PhoneBillServlet extends HttpServlet {
    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLERNUMBER_PARAMETER = "callerNumber";
    static final String CALLEENUMBER_PARAMETER = "calleeNumber";
    static final String START_PARAMETER = "start";
    static final String END_PARAMETER = "end";

    private final Map<String, String> dictionary = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "word" HTTP parameter to the HTTP response.  If the
     * "word" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        String customerName = getParameter(CUSTOMER_PARAMETER, request);

        if (customerName == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }
//        }else{
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, Messages.noPhoneBillForCustomer(customerName));
//        }
        String start = getParameter(START_PARAMETER, request);
        String end = getParameter(END_PARAMETER, request);
        if (start != null && end == null) {
            missingRequiredParameter(response, END_PARAMETER);
        }
        if (start == null && end != null) {
            missingRequiredParameter(response, START_PARAMETER);

        }
        if (start == null && end == null) {
            TextParser textParser = new TextParser(customerName);
            PhoneBill phoneBill;

            try {
                phoneBill = (PhoneBill) textParser.parse();
                ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) phoneBill.getPhoneCalls();
                String phoneBillCustomer = phoneBill.getCustomer();
                Collections.sort(phoneCalls);

                // print out the result
                int l = phoneCalls.size();
                StringBuilder content = new StringBuilder(phoneBillCustomer + " has " + l + " phone calls \n");
                for (PhoneCall phoneCall : phoneCalls) {
                    String phoneC = phoneCall.toString() + " in duration of " + phoneCall.Duration() + " minutes" + "\n";
                    content.append(phoneC);
                }
                final PrintWriter out = response.getWriter();
                out.print(content);
                out.flush();
                out.close();
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ParserException e) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            }

        }

        if (start != null && end != null) {
            String splittedStart[] = start.split(" "); // [10/10/2020] [12:10]
            if (splittedStart.length != 3) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid start date!");
                return;
            }


            String startDate = splittedStart[0];
            String startTime = splittedStart[1] + " " + splittedStart[2];

            String splittedEnd[] = end.split(" ");
            if (splittedEnd.length != 3) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid end date");
                return;
            }

            String endDate = splittedEnd[0];
            String endTime = splittedEnd[1] + " " + splittedEnd[2];

            TextParser textParser = new TextParser(customerName);
            PhoneBill phoneBill;
            PhoneCall inputPhoneCall;
            try {
                phoneBill = (PhoneBill) textParser.parse();
                inputPhoneCall = new PhoneCall("111-111-1111", "111-111-1111", startDate, startTime, endDate, endTime);
                ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) phoneBill.getPhoneCalls();
                String phoneBillCustomer = phoneBill.getCustomer();
                Collections.sort(phoneCalls);

                // print out the result
                StringBuilder content = new StringBuilder();
                int count = 0;
                for (PhoneCall phoneCall : phoneCalls) {
//                    " Phone call with startDate =< inputStart and with endDate >= inputEnd"
                    if (phoneCall.getStartDateTime().compareTo(inputPhoneCall.getEndDateTime()) <= 0 &&
                            phoneCall.getEndDateTime().compareTo(inputPhoneCall.getStartDateTime()) >=0  ) {
                        String phoneC = phoneCall.toString() + " in duration of " + phoneCall.Duration() + " minutes" + "\n";
                        content.append(phoneC);
                        count ++;
                    }
                }
                String newContent = phoneBillCustomer + " has " + count + " phone calls in between the time " + start + " and " + end + "\n" + content;


                final PrintWriter out = response.getWriter();
                out.print(newContent);
                out.flush();
                out.close();
                response.setStatus(HttpServletResponse.SC_OK);
            } catch ( Exception e) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            }
        }
    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        String customerName = getParameter(CUSTOMER_PARAMETER, request);

        if (customerName == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }

        String callerNumber = getParameter(CALLERNUMBER_PARAMETER, request);
        if (callerNumber == null) {
            missingRequiredParameter(response, CALLERNUMBER_PARAMETER);
            return;
        }

        String calleeNumber = getParameter(CALLEENUMBER_PARAMETER, request);
        if (calleeNumber == null) {

            missingRequiredParameter(response, CALLEENUMBER_PARAMETER);
            return;
        }
        String start = getParameter(START_PARAMETER, request);
        if (start == null) {
            missingRequiredParameter(response, START_PARAMETER);
            return;
        }

        String end = getParameter(END_PARAMETER, request);
        if (end == null) {
            missingRequiredParameter(response, END_PARAMETER);
            return;
        }
        String splittedStart[] = start.split(" "); // [10/10/2020] [12:10]
        if (splittedStart.length != 3) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid start date!");
            return;
        }


        String startDate = splittedStart[0];
        String startTime = splittedStart[1] + " " + splittedStart[2];

        String splittedEnd[] = end.split(" ");
        if (splittedEnd.length != 3) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid end date");
            return;
        }

        String endDate = splittedEnd[0];
        String endTime = splittedEnd[1] + " " + splittedEnd[2];

        PhoneCall call = null;
        ArrayList<PhoneCall> calls = new ArrayList<>();
        try {
            call = new PhoneCall(callerNumber, calleeNumber, startDate, startTime, endDate, endTime);
            calls.add(call);
        } catch (Exception err) {
            String errMsg = err.getMessage();
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, errMsg);
            return;
        }

        PhoneBill phoneBill = null;

        try {
            TextParser textParser = new TextParser(customerName); // allocate the reader
            phoneBill = (PhoneBill) textParser.parse();  //reading text file content into phone bill
            String customer1 = phoneBill.getCustomer();
            if (!customerName.equals(customer1)) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The customer name in the file is not equal to the given customer name.");
                return;
                //System.err.print("The customer name in the file is not equal to the given customer name.");
                //System.exit(1);
            }

            phoneBill.addPhoneCall(call);
        } catch (ParserException err) {
            String errMsg = err.getMessage();
            if (!errMsg.equals("The file is empty") && !errMsg.equals("New File is created")) {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, errMsg);
                //System.err.print(err.getMessage());
                //System.exit(1);
            }
            phoneBill = new PhoneBill(customerName, calls);
        }
        TextDumper textDumper = new TextDumper(customerName); // writing phone bill to the file, using customer name

        try {
            textDumper.dump(phoneBill);
//            response.
        } catch (IOException err) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, err.getMessage());
            return;
            //System.err.print(err.getMessage());
            //System.exit(1);
        }


        response.setStatus(HttpServletResponse.SC_OK);
    }


    /**
     * Writes an error message about a missing parameter to the HTTP response.
     * <p>
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter(HttpServletResponse response, String parameterName)
            throws IOException {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }




    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     * <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }



}
