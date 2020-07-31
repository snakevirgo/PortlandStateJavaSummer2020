package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class TextParser implements PhoneBillParser<AbstractPhoneBill> {
     private String file_name;
     private PhoneBill phoneBill;

     TextParser(String file_name) {
          this.file_name = file_name; //file_name is the customer

     }

    /**
     *
     * @return
     * @throws ParserException
     */
     public AbstractPhoneBill parse() throws ParserException {
         InputStream read_File;
         BufferedReader bufferedReader;
         try {
             read_File = new FileInputStream(this.file_name);
             bufferedReader = new BufferedReader(new InputStreamReader(read_File));


             String line = bufferedReader.readLine();
             if (line == null) {
                 throw new ParserException("The file is empty");
             }
             String customerName = line;
             phoneBill = new PhoneBill(customerName, new ArrayList<>());

             line = bufferedReader.readLine();
             while (line != null) {
                 PhoneCall phoneCall;
                 String caller = line;

                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformed.");
                 }
                 String callee = line;


                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformatted with invalid start date.");
                 }
                 String startDate = line;


                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformatted with invalid start time.");
                 }
                 String startTime = line;


                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformatted with invlid end date .");
                 }
                 String endDate = line;

                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformed with invalid end time.");
                 }
                 String endTime = line;

                 phoneCall = new PhoneCall(caller, callee, startDate, startTime, endDate, endTime);
                 this.phoneBill.addPhoneCall(phoneCall);
                 line = bufferedReader.readLine();
             }
             return this.phoneBill;
              }
         catch (Exception err) {
            File file = new File(this.file_name);
            if (!file.exists()) {
                try {
                    String dir[] = this.file_name.split("/");
                    int l = dir.length;
                    if (l == 2) {
                        File d = new File(dir[0]);
                        if (!d.exists()) {
                            if (d.mkdir()) {
                                System.out.println(dir[0] + " is created");
                            } else {
                                System.err.println(dir[0] + " cannot be created");
                            }
                        }
                    }
                    file.createNewFile();

                } catch (IOException er) {
                    throw new ParserException(er.getMessage());
                }
                throw  new ParserException("New File is created");

            }
            else {
                throw new ParserException(err.getMessage());
            }
        }
    }
}

//no text file: create new a one. -> dump the information
//existing one: parse the content -> array -> append information what you want to dump to array -> dump it


