package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.ArrayList;

public class TextParser implements PhoneBillParser<AbstractPhoneBill> {
     private String file_name;
     private PhoneBill phoneBill;

     TextParser(String file_name) {
          this.file_name = file_name; //file_name is the customer

     }

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
                     throw new ParserException("The file is malformed.");
                 }
                 String startDate = line;


                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformed.");
                 }
                 String startTime = line;


                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformed.");
                 }
                 String endDate = line;

                 line = bufferedReader.readLine();
                 if (line == null || line.equals("")) {
                     throw new ParserException("The file is malformed.");
                 }
                 String endTime = line;

                 phoneCall = new PhoneCall(caller, callee, startDate, startTime, endDate, endTime);
                 this.phoneBill.addPhoneCall(phoneCall);
                 line = bufferedReader.readLine();
             }
             return this.phoneBill;
         } catch (Exception err) {
             String msg = err.getMessage();
             if (msg.contains("The system cannot find the")) {
                 try {
                     String dir[] = this.file_name.split("/");
                     int l = dir.length;
                     if (l == 0) {
                         throw new ParserException("File name was not given");
                     }
                     else
                      if (l == 2) {
                         File d = new File(dir[0]);
                         d.mkdir();
                     }
                     File file = new File(this.file_name);
                     boolean isCreated = file.createNewFile();


                 } catch (IOException er) {
                     throw new ParserException(er.getMessage());
                 }
                 throw  new ParserException("New File is created");
             } else {
                 throw new ParserException("The file might be malformed.");
             }
         }
     }
}



//no text file: create new a one. -> dump the information
//existing one: parse the content -> array -> append information what you want to dump to array -> dump it


