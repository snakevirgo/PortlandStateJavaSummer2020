package edu.pdx.cs410J.yal;

import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Unit tests for the {@link edu.pdx.cs410J.yal.PhoneBillRestClient.PhoneBillRestException} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class PhoneBillRestExceptionTest {
    @Test(expected = PhoneBillRestClient.PhoneBillRestException.class)
    public void test() throws PhoneBillRestClient.PhoneBillRestException {
        PhoneBillRestClient a = new PhoneBillRestClient("localhost", 8080);
       throw  a.new PhoneBillRestException(HTTP_OK);
    }
}
