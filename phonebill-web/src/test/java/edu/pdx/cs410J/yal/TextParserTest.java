package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit tests for the {@link TextParser} class.
 *
 * You'll need to update these unit tests as you build out you program.
 */
public class TextParserTest {
     @Test
    public void textParserFileName() {
         TextParser textParser = new TextParser("aaa");
         try {
             textParser.parse();
         } catch (ParserException err) {
             System.err.println(err.getMessage());
         }
     }

    @Test
    public void textParserFileName1() throws Exception{
         TextDumper dumper = new TextDumper("aaa");
         PhoneCall call = new PhoneCall("541-111-1111", "541-454-4111", "2/20/2020", "1:00 pm", "03/21/2020","1:10 am");
        ArrayList<PhoneCall> arrayList = new ArrayList<>();
        arrayList.add(call);
         PhoneBill phoneBill = new PhoneBill("aaa", arrayList);
        dumper.dump(phoneBill);
        TextParser textParser = new TextParser("aaa");
        try {
            textParser.parse();
        } catch (ParserException err) {
            System.err.println(err.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void textParserFileName2() throws Exception{
        TextDumper dumper = new TextDumper("aaa");
        PhoneCall call = new PhoneCall("541-111-1111", "51-454-4111", "2/20/2020", "1:00 pm", "03/21/2020","1:10 am");
        ArrayList<PhoneCall> arrayList = new ArrayList<>();
        arrayList.add(call);
        PhoneBill phoneBill = new PhoneBill("aaa", arrayList);
        dumper.dump(phoneBill);
        TextParser textParser = new TextParser("aaa");
        try {
            textParser.parse();
        } catch (ParserException err) {
            System.err.println(err.getMessage());
        }
    }
}

