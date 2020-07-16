package edu.pdx.cs410J.yal;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.*;
import java.util.ArrayList;

public class TextDumperTest {
    @Test
    public void textDumperFileName() {
        String writer = new String();

        TextDumper dumper =  new TextDumper(writer);

        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneBill bill = new PhoneBill(customerName,phoneCalls);

       try{
           dumper.dump(bill);
       }catch(IOException err) {

           TestCase.assertEquals("Error when dumping file.", err.getMessage());
       }
    }


}
