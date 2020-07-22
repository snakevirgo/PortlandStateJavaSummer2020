package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;
import edu.pdx.cs410J.yal.PhoneCall;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;

public class TextDumper implements PhoneBillDumper<AbstractPhoneBill> {
    private String file_Name;
    private PhoneBill phoneBill;
    TextDumper(String file_Name){
        this.file_Name = file_Name;
   }

    public void dump(AbstractPhoneBill var1) throws IOException {
        this.phoneBill = (PhoneBill) var1;
        BufferedWriter bufferedWriter;


        try {
            OutputStream write_File = new FileOutputStream(file_Name);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(write_File));

            bufferedWriter.write(this.phoneBill.getCustomer());
            bufferedWriter.newLine();
            ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) this.phoneBill.getPhoneCalls();
           for (PhoneCall phoneCall: phoneCalls) {
               String caller = phoneCall.getCaller();
               bufferedWriter.write(caller);
               bufferedWriter.newLine();

               String callee = phoneCall.getCallee();
               bufferedWriter.write(callee);
               bufferedWriter.newLine();


               Date Start = phoneCall.getStartDateTime();
               SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
               SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");
               String StartDate = df.format(Start);
               String StartTime = tf.format(Start);

               bufferedWriter.write(StartDate);
               bufferedWriter.newLine();

               bufferedWriter.write(StartTime);
               bufferedWriter.newLine();

               Date End = phoneCall.getEndDateTime();
               String EndDate = df.format(End);
               String EndTime = tf.format(End);

               bufferedWriter.write(EndDate);
               bufferedWriter.newLine();

               bufferedWriter.write(EndTime);
               bufferedWriter.newLine();
           }
           bufferedWriter.close();
        } catch (Exception err) {
            throw new IOException("Error when dumping file.");
        }

    }

}
