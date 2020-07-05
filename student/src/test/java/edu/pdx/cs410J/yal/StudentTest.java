package edu.pdx.cs410J.yal;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = createStudentNamed(name);
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  public void toStringContainsStudentName(){
    String name = "Pat";
    //String pat =
    Student pat = createStudentNamed(name);
    assertThat(pat.toString(),containsString(name));  //hamcrest gives better error message than regular junit

  }

  private Student createStudentNamed(String name) {
    return new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
  }

//make sure the toString has a gpa
  @Test
  public void toStringContainsGpa(){
    double gpa = 3.76;
    Student pat = new Student("Pat", new ArrayList<>(), gpa, "Doesn't matter");
    assertThat(pat.toString(), containsString("has a GPA of " + gpa));
  }

}
