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
public class PhoneBillServlet extends HttpServlet
{
    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLERNUMBER_PARAMETER = "callernumber";
    static final String CalleeNumber_PARAMETER = "calleenumber";
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
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        response.setContentType( "text/plain" );

        String customerName = getParameter( CUSTOMER_PARAMETER, request );

        if (customerName == null) {
            missingRequiredParameter(response, CUSTOMER_PARAMETER);
            return;
        }
        String start = getParameter(START_PARAMETER, request);
        String end = getParameter(END_PARAMETER, request);
        if (start != null&& end ==null) {
            missingRequiredParameter(response, END_PARAMETER);
        }
        if(start == null && end != null) {
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
        for (PhoneCall phoneCall: phoneCalls) {
            String phoneC = phoneCall.toString() + " in duration of " + phoneCall.Duration() + " minutes" + "\n";
            content.append(phoneC);
        }
        final PrintWriter out = response.getWriter();
        out.print(content);
        out.flush();
        out.close();
        response.setStatus(HttpServletResponse.SC_OK);
            } catch (ParserException e)
            {
                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, e.getMessage());
            }

        }

    }

    /**
     * Handles an HTTP POST request by storing the dictionary entry for the
     * "word" and "definition" request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */

//    @Override
//    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
//    {
//        response.setContentType( "customer" );
//
//        String word = getParameter(CUSTOMER_PARAMETER, request );
//        if (word == null) {
//            missingRequiredParameter(response, CUSTOMER_PARAMETER);
//            return;
//        }
//
//
//        String definition = getParameter(DEFINITION_PARAMETER, request );
//        if ( definition == null) {
//            missingRequiredParameter( response, DEFINITION_PARAMETER );
//            return;
//        }
//
//        this.dictionary.put(word, definition);
//
//        PrintWriter pw = response.getWriter();
//        pw.println(Messages.definedWordAs(word, definition));
//        pw.flush();
//
//        response.setStatus( HttpServletResponse.SC_OK);
//    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/plain");
//
//        this.dictionary.clear();
//
//        PrintWriter pw = response.getWriter();
//        pw.println(Messages.allDictionaryEntriesDeleted());
//        pw.flush();
//
//        response.setStatus(HttpServletResponse.SC_OK);
//
//    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with
     * {@link Messages#formatDictionaryEntry(String, String)}
     */
//    private void writeDefinition(String word, HttpServletResponse response) throws IOException {
//        String definition = this.dictionary.get(word);
//
//        if (definition == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//
//        } else {
//            PrintWriter pw = response.getWriter();
//            pw.println(Messages.formatDictionaryEntry(word, definition));
//
//            pw.flush();
//
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }

    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with
     * {@link Messages#formatDictionaryEntry(String, String)}
     */
//    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
//    {
//        PrintWriter pw = response.getWriter();
//        Messages.formatDictionaryEntries(pw, dictionary);
//
//        pw.flush();
//
//        response.setStatus( HttpServletResponse.SC_OK );
//    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }

//    @VisibleForTesting
//    String getDefinition(String word) {
//        return this.dictionary.get(word);
//    }

}
