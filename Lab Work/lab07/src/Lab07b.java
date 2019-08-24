import cs102.ds.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which tests the Stack class by evaluating postfix expressions
 * @author EgeOzan
 * @since 7 May 2018
 * @version 1.0
 */
public class Lab07b {
    public static void main( String[] args) {

        //constants

        //variables
        Stack stack;
        Scanner scan;
        String input;
        ArrayList<String> results;


        //program code
        stack = new Stack();
        results = new ArrayList<String>();
        scan = new Scanner( System.in);


        System.out.println( "Enter one or multiple postfix expressions to be evaluated or enter -1 to exit the program");
        input = scan.next();
        while ( !input.equals( "-1")) {

            int first, second;
            char c;
            for ( int i = 0; i < input.length(); i++) {
                c = input.charAt( i);
                if ( Character.isDigit( c)) {
                    stack.push( c + "");
                }
               else {
                    second = Integer.parseInt( stack.pop());
                    first = Integer.parseInt( stack.pop());

                    //the char c determines the operation
                    if ( c == '+')
                        stack.push( first + second + "");
                    if ( c == '-')
                        stack.push( first - second + "");
                    if ( c == '*')
                        stack.push( first * second + "");
                    if ( c == '/')
                        stack.push( first / second + "");
                }
            }
            input = scan.next();
            results.add( stack.pop());
        }

        if ( results.size() == 0)
            System.out.println( "No results found");
        else
            System.out.println( "Results: " + results);

        System.out.println( "Goodbye!");

    }
}
