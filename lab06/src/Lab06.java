import java.util.ArrayList;

/*
 * A Class which contains recursive methods
 * @author Ege Ozan Özyedek
 * @version v1.5
 * @date 17/04/18
 */
public class Lab06 {
  
  //properties
  int occurrence, number, i, min, decimal, n, c, minIndex, index;
  String binary, numbers;
  ArrayList<Integer> intList;
  
  
  //constructors
  
  /*
   * Constructor of Lab06 which declares two strings as "" to avoid them being
   * printed as null 
   */
  public Lab06() {
    binary = "";
    numbers = "";
    intList = new ArrayList< Integer>();
  }
  
  
  //methods
  
  /*
   * A recursive method which finds the times the character e is occurred in a string
   * @param str, the string that is going to be searched for occurence
   * @return occurence, the occurence of e
   */
  public int occurrenceE( String str) {
    
    if ( i > str.length() - 1) { 
      return 0;
    }
    
    if ( str.charAt( i) == 'e') { 
      return occurrenceE( str.substring(1)) + 1;
    }
    
    else {
      return occurrenceE( str.substring(1)) + 0;
    }
    
  }
  
  /*
   * A recursive method which converts decimals to binary numbers(base 2)
   * @param value, int that will be converted
   * @return the binary value in String form
   */
  public String toBinary( int value ) {
    
    if ( value == 0) { 
      return binary;
    }
    
    binary = ( value % 2) + binary;
    value = value / 2;
    return toBinary( value);
  }
  
  /*
   * A recursive method which determines if the arraylist contains strings lexicographicly
   * @param list, arraylist  which contains strings
   * @return false or true, depending on the lexicography
   */
  public boolean isLexicographic(ArrayList< String> list) {
    
    if ( list.size() < 2) { 
      return true; 
    }
    
    if ( list.get( 0).compareTo(list.get( 1)) > 0 ) { 
      return false; 
    }
    
    list.remove( 0);
    return isLexicographic( list);
  }
  
  /*
   * A recursive method which prints all N digited numbers which are written from small to big digits
   * @param n, int digit count
   * @return all the numbers that fits the criteria in String form
   */
  public String printNDigitEvenNumbers( int n) {
    
    if ( number == 0) {
      number = (int) Math.pow( 10, (n - 1));
    }
    
    if ( number >= Math.pow( 10, n)) { 
      return numbers;
    }
    
    if ( criteria( number) && number % 2 == 0) { 
      numbers = numbers + number + " "; 
    }
    
    number++;
    return printNDigitEvenNumbers( n);
    
  }
  
  /*
   * A recursive method which is the criteria for the recursive method printNDigitEvenNumbers
   * @param num, the number that is going to be checked to fit the criteria
   * @return true or false depending on whether the number fits the criteria or not
   */
  private boolean criteria( int num){
    
    int remainder;
    remainder = num % 10;
    
    if ( num < 10) {  
      return true;
    }
    
    num = num / 10;
    
    if ( remainder <= num % 10)  { 
      return false; 
    }
    
    return criteria( num);
  }
  
  /*
   * A recursive method which converts binary to decimal numbers(base 10)
   * @param value, int that will be converted
   * @return the decimal int value 
   */
  public int toDecimal( int value) {
    
    if( value == 0) { 
      return decimal;
    }
    
    decimal += (value % 10) * Math.pow( 2, n);
    
    value = value / 10;
    n++;
    return toDecimal( value);
  }
  
  /*
   * A recursive method which orders a list of numbers from small to big
   * @param list, the integer array list
   * @return the return list which is ordered
   */
  public ArrayList order( ArrayList<Integer> list) {
    
    if ( list.size() == 0) { 
      return intList;
    }
    
    index = findMinIndex( list);
    intList.add( list.get( index));
    list.remove( index);
    
    return order( list);
  }
  
  /*
   * A recursive method which finds the minimum values's index
   * @param list, the integer array list
   * @return the minimum number's index
   */
  public int findMinIndex( ArrayList<Integer> list) {
    
    if ( c == 0) { 
      minIndex = 0; min = list.get( 0);
    }
    
    if ( c > list.size() - 1) { 
      c = 0; 
      return minIndex;
    } 
    
    if ( list.get(c) < min ) { 
      minIndex = c; 
      min = list.get( c);
    }
    
    c++;
    return findMinIndex( list);
  }
  
}