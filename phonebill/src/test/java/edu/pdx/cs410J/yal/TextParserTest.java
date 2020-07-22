package edu.pdx.cs410J.yal;

import edu.pdx.cs410J.ParserException;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
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

