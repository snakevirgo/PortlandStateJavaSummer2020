package edu.pdx.cs410J.yal;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class PhoneBillServletTest {

  @Test
  public void requestWithNoCustomerReturnMissingParameter() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
//    PrintWriter pw = mock(PrintWriter.class);
//
//    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, Messages.missingRequiredParameter("customer"));
  }

//  @Test
//  public void addPhoneCallToBill() throws ServletException, IOException {
//    PhoneBillServlet servlet = new PhoneBillServlet();
//
//    String customer = "customer";
//    String callerPhonenumber = "503-123-2222";
//    String calleePhonenumber = "534-111-1111";
//    String start = "01/01/2020 1:12 am";
//    String end = "01/02/2020 1:12 pm";
//
//
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    when(request.getParameter("customer")).thenReturn(customer);
//    when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
//    when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
//    when(request.getParameter("start")).thenReturn(start);
//    when(request.getParameter("end")).thenReturn(end);
//
//
//    HttpServletResponse response = mock(HttpServletResponse.class);
//    PrintWriter pw = mock(PrintWriter.class);
//    when(response.getWriter()).thenReturn(pw);
//
//    servlet.doPost(request, response);
//    //verify(pw,times(0).println(any(String.class));
//    verify(response).setStatus(HttpServletResponse.SC_OK);

//    PhoneBill phoneBill = servlet.getPhoneBill();
//    //assertThat(phoneBill, notNullValue());
//    //assertThat(phoneBill, equalTo(customer));
//
//    PhoneCall phoneCall = phoneBill.getPhoneCalls().iterator().next();
//    assertThat(phoneCall.getCaller(), equalTo(callerPhonenumber));
//    assertThat(phoneCall.getCallee(),equalTo(calleePhonenumber));
//    assertThat(phoneCall.getCaller(), equalTo(start));
//    assertThat(phoneCall.getCaller(), equalTo(end));
  //}
    @Test
    public void addPhoneCallToBillNullCustomer() throws ServletException, IOException {
        PhoneBillServlet servlet = new PhoneBillServlet();

        String customer = null;
        String callerPhonenumber = "503-123-2222";
        String calleePhonenumber = "534-111-1111";
        String start = "01/01/2020 1:12 am";
        String end = "01/02/2020 1:12 pm";


        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn(customer);
        when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
        when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
        when(request.getParameter("start")).thenReturn(start);
        when(request.getParameter("end")).thenReturn(end);


        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        //verify(pw,times(0).println(any(String.class));
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"customer\" is missing");

//    PhoneBill phoneBill = servlet.getPhoneBill();
//    //assertThat(phoneBill, notNullValue());
//    //assertThat(phoneBill, equalTo(customer));
//
//    PhoneCall phoneCall = phoneBill.getPhoneCalls().iterator().next();
//    assertThat(phoneCall.getCaller(), equalTo(callerPhonenumber));
//    assertThat(phoneCall.getCallee(),equalTo(calleePhonenumber));
//    assertThat(phoneCall.getCaller(), equalTo(start));
//    assertThat(phoneCall.getCaller(), equalTo(end));
    }


  @Test
    public void addPhoneCallToBillNullcallerPhonenumber() throws ServletException, IOException {
        PhoneBillServlet servlet = new PhoneBillServlet();

        String customer = "customer";
        String callerPhonenumber = null;
        String calleePhonenumber = "534-111-1111";
        String start = "01/01/2020 1:12 am";
        String end = "01/02/2020 1:12 pm";


        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn(customer);
        when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
        when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
        when(request.getParameter("start")).thenReturn(start);
        when(request.getParameter("end")).thenReturn(end);


        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        //verify(pw,times(0).println(any(String.class));
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"callerNumber\" is missing");
}

 @Test
    public void addPhoneCallToBillNullcalleePhonenumber() throws ServletException, IOException {
        PhoneBillServlet servlet = new PhoneBillServlet();

        String customer = "customer";
        String callerPhonenumber = "503-123-2222";
        String calleePhonenumber = null;
        String start = "01/01/2020 1:12 am";
        String end = "01/02/2020 1:12 pm";


        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn(customer);
        when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
        when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
        when(request.getParameter("start")).thenReturn(start);
        when(request.getParameter("end")).thenReturn(end);


        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        //verify(pw,times(0).println(any(String.class));
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"calleeNumber\" is missing");
}

 @Test
    public void addPhoneCallToBillNullStart() throws ServletException, IOException {
        PhoneBillServlet servlet = new PhoneBillServlet();

        String customer = "customer";
        String callerPhonenumber = "503-123-2222";
        String calleePhonenumber = "534-111-1111";
        String start = null;
        String end = "01/02/2020 1:12 pm";


        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn(customer);
        when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
        when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
        when(request.getParameter("start")).thenReturn(start);
        when(request.getParameter("end")).thenReturn(end);


        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        //verify(pw,times(0).println(any(String.class));
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"start\" is missing");
}

    @Test
    public void addPhoneCallToBillNullEnd() throws ServletException, IOException {
        PhoneBillServlet servlet = new PhoneBillServlet();

        String customer = "customer";
        String callerPhonenumber = "503-123-2222";
        String calleePhonenumber = "534-111-1111";
        String start = "01/01/2020 1:12 am";
        String end = null;


        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn(customer);
        when(request.getParameter("callerNumber")).thenReturn(callerPhonenumber);
        when(request.getParameter("calleeNumber")).thenReturn(calleePhonenumber);
        when(request.getParameter("start")).thenReturn(start);
        when(request.getParameter("end")).thenReturn(end);


        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(pw);

        servlet.doPost(request, response);
        //verify(pw,times(0).println(any(String.class));
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "The required parameter \"end\" is missing");
    }
  @Test
  public void doPostCustomer()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("testServer");
    when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }
  @Test
  public void doPostCustomerFailed()  throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("testServer");
    when(request.getParameter("callerNumber")).thenReturn("541-111-111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
    verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid caller phone number input!");
  }

    @Test
    public void doPostCustomerFailedDate()  throws  IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn("testServer");
        when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
        when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
        when(request.getParameter("start")).thenReturn("01/01/220 1:12 am");
        when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
        HttpServletResponse response = mock(HttpServletResponse.class);
        try {
            servlet.doPost(request, response);
        } catch (ServletException err) {
            System.err.println("It shouldn't be here");
        }
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid start date time input!");
    }
    @Test
    public void doPostCustomerFailedDate2()  throws  IOException{
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("customer")).thenReturn("testServer");
        when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
        when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
        when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
        when(request.getParameter("end")).thenReturn("01/02/220 1:12 pm");
        HttpServletResponse response = mock(HttpServletResponse.class);
        try {
            servlet.doPost(request, response);
        } catch (ServletException err) {
            System.err.println("It shouldn't be here");
        }
        verify(response).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Invalid end date time input!");
    }


  @Test
  public void doGetCustomer() throws  IOException{
    PhoneBillServlet servlet = new PhoneBillServlet();

   HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("testServer");
    when(request.getParameter("callerNumber")).thenReturn("541-111-1111");
    when(request.getParameter("calleeNumber")).thenReturn("541-478-4777");
    when(request.getParameter("start")).thenReturn("01/01/2020 1:12 am");
    when(request.getParameter("end")).thenReturn("01/02/2020 1:12 pm");
    HttpServletResponse response = mock(HttpServletResponse.class);
    try {
      servlet.doPost(request, response);
    } catch (ServletException err) {
      System.err.println("It shouldn't be here");
    }
  // -------------------- doGet ---------------------------
    HttpServletRequest Arequest = mock(HttpServletRequest.class);
    when(Arequest.getParameter("customer")).thenReturn("testServer");
    HttpServletResponse Aresponse = mock(HttpServletResponse.class);
    try {
      StringWriter stringWriter = new StringWriter();
      PrintWriter pw = new PrintWriter(stringWriter, true);
      when(Aresponse.getWriter()).thenReturn(pw);

      servlet.doGet(Arequest, Aresponse);
    } catch (IOException err) {
      System.err.println(err.getMessage());
    }
    verify(Aresponse).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  public void requestCustomerWithNoPhoneBillReturnsNotFound() throws IOException {

    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("customer")).thenReturn("d");
    HttpServletResponse response = mock(HttpServletResponse.class);

    servlet.doGet(request, response);
    int status = response.getStatus();
    assertThat(status, equalTo(0));
//    verify(response).;
  }

//  @Ignore
//  @Test
//  public void addOneWordToDictionary() throws ServletException, IOException {
//    PhoneBillServlet servlet = new PhoneBillServlet();
//
//    String word = "TEST WORD";
//    String definition = "TEST DEFINITION";
//
//    HttpServletRequest request = mock(HttpServletRequest.class);
//    when(request.getParameter("word")).thenReturn(word);
//    when(request.getParameter("definition")).thenReturn(definition);
//
//    HttpServletResponse response = mock(HttpServletResponse.class);
//
//    // Use a StringWriter to gather the text from multiple calls to println()
//    StringWriter stringWriter = new StringWriter();
//    PrintWriter pw = new PrintWriter(stringWriter, true);
//
//    when(response.getWriter()).thenReturn(pw);
//
////    servlet.doPost(request, response);
//
//    assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition) ));
//
//    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
//    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
//    verify(response).setStatus(statusCode.capture());
//
//    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
//
////    assertThat(servlet.getDefinition(word), equalTo(definition));
//  }



}
