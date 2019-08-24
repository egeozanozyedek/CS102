/*
 * Prime class, a class that tests the IntBag class by using it to find the first 100 prime numbers
 * @author Ege Ozan Özyedek
 * @version v1.5
 * @date 13/02/18
 */
public class Prime {
  
  public static void main(String[] args) {
    
    //constants 
    final int SENTINEL = 100;
    
    //variables
    IntBag bag;
    int j;
    int i;
    boolean control;
    
    //program code
    
    
    bag = new IntBag( SENTINEL ); //constructing the object with SENTINEL max capacity
    
    j = 2; //this is because the first prime number is 2, j will increase from 2 to the 100th prime number in the loop
    
    //this while loops till the size(valid int amount) of bag is 100 (so that it can find the first 100 primes)
    while ( bag.size() < SENTINEL ) {
      control = true; //checks whether the number is prime or not
      
      //i starts from 2 in this for loop everytime because if it started from 1 it would've computed the count
      //as 1 everyloop. 
      for ( i = 0; i < bag.size(); i++ ) {
        //if j(the number that is being checked to see if its prime or not ) and index i's (the number that is being divided till j)
        // divison's remain is 0 then it will change control to false(which makes it so that we know its not a prime number)
        if ( j % bag.get(i) == 0 ) {
          control = false;
          i = j;
        }
      }
      
      //if j was never divided by numbers that came before it (meaning that control was left true) then it is added to the bag
      if ( control ) 
        bag.add( j );
      
      //increases j so that it can check the next number
      j++;
    }

    System.out.println( bag.toString() + "\n" + "Size: " + bag.size());
    
  }
}