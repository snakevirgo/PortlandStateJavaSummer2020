package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

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
}

