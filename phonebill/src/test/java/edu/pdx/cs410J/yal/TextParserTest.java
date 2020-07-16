package edu.pdx.cs410J.yal;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextParserTest {
     @Test
    public void textParserFileName() throws IOException {
         String file_name = new String();

         TextParser parser = new TextParser(file_name);

         String customerName = "Customer";
         ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
         PhoneBill bill = new PhoneBill(customerName, phoneCalls);
/*
         TestCase.assertEquals("Error when dumping file.", err.getMessage());
         try {
             parser. (bill);
         } catch (IOException err) {

             TestCase.assertEquals("Error when dumping file.", err.getMessage());
         }
*/
         try (
                 InputStream readme = Project2.class.getResourceAsStream("");
         ) {
             assertThat(readme, not(nullValue()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
             String line = reader.readLine();
             assertThat(line, containsString(""));
         }
     }
}

