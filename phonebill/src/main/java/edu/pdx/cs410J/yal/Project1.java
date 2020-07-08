package edu.pdx.cs410J.yal;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {
    //PhoneCall call = new PhoneCall(null);  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    /*String customer1;
    String caller1;
    String callee1;
    String start1;
    String end1;
    String variable = args[8];

    if(args[0] == "-print")
    {

    }
     */

 //System.out.println(call);

    if( args.length == 0) {
      System.err.println("Missing command line arguments");
    }

    for(int i = 0; i < args.length; ++i)
    {
      System.out.println(args[i]);
    }
    /*
    for (String arg : args) {
      System.out.println(arg);
    }*/

    System.exit(1);

  }

}