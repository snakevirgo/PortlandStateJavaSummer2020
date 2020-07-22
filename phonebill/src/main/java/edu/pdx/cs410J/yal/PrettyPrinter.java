package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PrettyPrinter implements PhoneBillDumper<AbstractPhoneBill> {

    private String fileName;

    PrettyPrinter(String fileName) {
        this.fileName = fileName;
    }

    public void dump(AbstractPhoneBill var1) throws IOException{
        PhoneBill phoneBill = (PhoneBill) var1;
        ArrayList<PhoneCall> phoneCalls = (ArrayList<PhoneCall>) phoneBill.getPhoneCalls();
        String customerName = phoneBill.getCustomer();
        Collections.sort(phoneCalls);

        // print out the result
        int l = phoneCalls.size();
        StringBuilder content = new StringBuilder(customerName + " has " + l + " phone calls \n");
        for (PhoneCall phoneCall: phoneCalls) {
            String phoneC = phoneCall.toString() + " in duration " + phoneCall.Duration() + " minutes" + "\n";
            content.append(phoneC);
        }

        if (this.fileName.equals("-")) {
            System.out.println(content);
            return;
        }

        File file = new File(this.fileName);

        try {
            String dir[] = this.fileName.split("/");
            int li = dir.length;
            if (li == 0) {
                throw new IOException("File name was not given");
            } else if (li == 2) {
                File d = new File(dir[0]);
                if (!d.exists()) {
                    d.mkdir();
                }
            }
            if (!file.exists()) {
                boolean isFileCreated = file.createNewFile();
            }
        } catch (IOException er) {
            throw new IOException("Err when creating file");
        }



        BufferedWriter bufferedWriter;
        try {
            OutputStream write_File = new FileOutputStream(fileName);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(write_File));

            bufferedWriter.write(String.valueOf(content));
            bufferedWriter.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            throw new IOException("Error when dumping file.");
        }    //java.text.DateFormat.SHORT
    }

}
