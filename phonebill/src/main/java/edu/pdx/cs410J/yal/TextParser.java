package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
          if(line == null || line.equals("")) {
              throw  new ParserException("The file is malformed.");
          }
          String callee = line;


          line = bufferedReader.readLine();
          if(line == null || line.equals("")) {
              throw  new ParserException("The file is malformed.");
          }
          String startDate = line;


          line = bufferedReader.readLine();
          if(line == null || line.equals("")) {
              throw  new ParserException("The file is malformed.");
          }
          String startTime = line;


          line = bufferedReader.readLine();
          if(line == null || line.equals("")) {
              throw new ParserException("The file is malformed.");
          }
          String endDate = line;

          line = bufferedReader.readLine();
          if(line == null || line.equals("")) {
              throw  new ParserException("The file is malformed.");
          }
          String endTime = line;

            phoneCall = new PhoneCall(caller, callee, startDate, startTime, endDate, endTime);
            this.phoneBill.addPhoneCall(phoneCall);
          line = bufferedReader.readLine();
      }
      return this.phoneBill;
    } catch (Exception err) {
             throw new ParserException("The file might be malformed or not exist.");
         }
  }
}



//no text file: create new a one. -> dump the information
//existing one: parse the content -> array -> append information what you want to dump to array -> dump it


