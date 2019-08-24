import cs102.ds.List;

/**
 * A class which tests the List class by calling its methods
 * @author EgeOzan
 * @since 7 May 2018
 * @version 1.0
 */
public class Lab07a {
    public static void main( String[] args) {

        //constants

        //variables
        String[] data = new String[] { "Hello", "World", "My", "Name", "Is", "Ege"};
        List list;

        //program code

        list = new List();
        list.print();

        for ( int i = 0; i < data.length; i++) {
            list = new List();
            for ( int j = 0; j <= i; j++) {
                list.addToHead( data[j]);
            }
            list.print(); //works
            System.out.println();
        }

        list.addToTail( "Am I on the tail?"); //works
        
        list.addToHead( "\nAm I on the head?"); //works
        list.print();

        list.removeFromHead(); //works
        list.print();
        
        System.out.println( "\n***Reverse***");
        list.printReverse();
        System.out.println( "\n***Print***");
        list.print();

        System.out.println();
        System.out.println( "Data at index 234: " + list.getData( 234)); //invalid index, should return null //works
        System.out.println( "Data at index 4: " + list.getData( 4) + "\n\n"); //valid index, should return item //works

        list = new List();
        System.out.println( list.isEmpty()); //works
        list.addToHead(" Not empty anymore?");
        System.out.println( list.isEmpty()); //works
        
    }
}
