package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;


public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    private final String customer;
    private ArrayList<PhoneCall> calls;

    //customer constructor

    /**
     *
     * @param customer
     * @param calls
     */
    public PhoneBill(String customer, ArrayList<PhoneCall> calls) {
        this.customer = customer;
        this.calls = calls;
    }

    /**
     *
     * @return
     */
    @Override
    public String getCustomer() {
        return customer;
    }

    /**
     * @param phoneCall
     */
    @Override
    public void addPhoneCall(PhoneCall phoneCall) {
        calls.add(phoneCall);
    }

    /**
     * @return
     */
    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return calls;
    }


}
