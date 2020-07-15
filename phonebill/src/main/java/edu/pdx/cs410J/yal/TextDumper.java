package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.PhoneBillDumper;
import edu.pdx.cs410J.yal.PhoneCall;
import java.io.*;
import java.util.ArrayList;

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

               String StartString = phoneCall.getStartTimeString();
               String splittedStartString[] = StartString.split(" ");
               if (splittedStartString.length != 2) {
                   throw new IOException("Malformatted Start Date Time");
               }
               String startDate = splittedStartString[0];
               String startTime = splittedStartString[1];
               bufferedWriter.write(startDate);
               bufferedWriter.newLine();
               bufferedWriter.write(startTime);
               bufferedWriter.newLine();

               String EndString = phoneCall.getEndTimeString();
               String splittedEndString[] = EndString.split(" ");
               if (splittedEndString.length != 2) {
                   throw new IOException("Malformatted End Date Time");
               }
               String endDate = splittedEndString[0];
               bufferedWriter.write(endDate);
               bufferedWriter.newLine();
               String endTime = splittedEndString[1];
               bufferedWriter.write(endTime);
               bufferedWriter.newLine();
           }
           bufferedWriter.close();
        } catch (Exception err) {
            throw new IOException("Error when dumping file.");
        }

    }

}
