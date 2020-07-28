package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;


public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private final String customer;
    private ArrayList<PhoneCall> calls;

    //customer constructor
    public PhoneBill(String customer, ArrayList<PhoneCall> calls) {
        this.customer = customer;
        this.calls = calls;
    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void addPhoneCall(PhoneCall phoneCall) {
        calls.add(phoneCall);
    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return calls;
    }


}
