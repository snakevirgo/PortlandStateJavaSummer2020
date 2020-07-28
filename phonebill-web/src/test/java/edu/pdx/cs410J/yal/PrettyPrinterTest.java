package edu.pdx.cs410J.yal;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit tests for the {@link PrettyPrinter} class.
 *
 * This pretty print test tests to see if the phone bill can be dumped
 * and printed out in the DateFormat SHORT as expected.
 */
public class PrettyPrinterTest {
    @Test
    public void prettyTest() {
        String writer = "test";
        PrettyPrinter dumper =  new PrettyPrinter(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "1:30 pm", "01/10/2020", "1:30 pm");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
