package edu.pdx.cs410J.yal;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit tests for the {@link TextDumper} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class TextDumperTest {
    @Test
    public void TestDumper() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/2020", "12:30", "01/10/2020", "13:30");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void TestDumperFailed() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-3333", "503-111-1111", "01/10/202", "12:30", "01/10/2020", "13:30");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void TestDumperFailed1() {
        String writer = "test";
        TextDumper dumper =  new TextDumper(writer);
        String customerName = "Customer";
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        PhoneCall number2;
        try {
            number2 = new PhoneCall("503-222-333", "503-111-1111", "01/10/202", "12:30", "01/10/2020", "13:30");
            phoneCalls.add(number2);
            PhoneBill bill = new PhoneBill(customerName,phoneCalls);
            dumper.dump(bill);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


}
