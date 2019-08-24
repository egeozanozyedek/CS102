import java.util.Iterator;

/*
 * IntBag class, a class that creates a bag that can be filled with int values
 * @author Ege Ozan Özyedek
 * @version v1.3
 * @date 12/03/18
 */
public class IntBag {

    //properties
    private int[] bag;
    private int valid;

    //constructors

    /*
     * This constructor automaticly assigns
     * the IntBag maximum capacity to 100
     */
    public IntBag () {
        bag = new int[ 100 ];
    }

    /*
     * This constructor assigns the value entered by the user to be the max capacity
     * @param value, which is the value of max capacity
     */
    public IntBag ( int value ) {
        bag = new int[ value ];
    }

    //methods

    /*
     * This method adds the requested value to the IntBag
     * it also increases the valid int in the bag
     * @param value, which is the value that will be added to the bag
     */
    public void add( int value ) {
        bag[ valid ] = value;
        if (valid != bag.length) {
            valid++;
        }




    }

    /*
     * This method adds the requested value to the requested index.
     * It moves the other values "up" to make room for the newly added value
     * @param value, which is the value that will be added to the bag
     * @param index, which is the location that the value will be added to
     */
    public void add( int index, int value ) {
        int control;
        if ( valid == bag.length )
            control = valid - 1;
        else
            control = valid;
        if (bag[ index ] > 0) {
            for ( int i = control; i > index; i-- ) {
                bag[ i ] = bag[ i -1 ];
            }
        }
        if ( valid != bag.length )
            valid++;

        bag[ index ] = value;
    }

    /*
     * This method removes the value at the requested index.
     * It moves the other values "down" to fill the room created by removing the value
     * @param index, which is the location that the value will be removed from
     */
    public void remove( int index ) {
        for ( int i = index ; i < valid - 1 ; i++ ) {
            bag[ i ] = bag[ i + 1 ];
        }
        valid--;
    }

    /*
     * This method returns whether the requested value is in the bag or not
     * @param value, to find whether it is in the bag or not
     * @return boolean, to determine if its in the bag
     */
    public boolean contains( int value ) {

        for ( int i = 0; i < valid; i++ ) {
            if ( bag[ i ] == value ) {
                return true;
            }
        }

        return false;
    }

    /*
     * This method returns the valid int value in the bag
     * @return int, the amount of int values in the bag
     */
    public int size() {
        return valid;
    }

    /*
     * This method returns the value at the requested index
     * @param index, where the value is
     * @return int, the value at said index
     */
    public int get( int index ) {
        return bag[ index ];
    }

    /*
     * toString method to print out the valid values and also to see if anything is wrong with the code
     * @return int, all the valid values
     */
    public String toString() {
        String result;
        result = "[ ";
        if (valid == 0)
            return "No valid values.";
        for ( int i = 0; i < valid; i++ ) {
            if ( i == valid - 1 ) {
                result = result + bag[ i ] + " ]";
            }
            else {
                result = result + bag[ i ] + ", ";
            }
        }
        return result;
    }

    /*
     * A method to find all the instances of the given value
     * @param value, the given value
     * @return String, all the indexes of instances
     */
    public IntBag findAll( int value ) {
        IntBag result;
        result = new IntBag (valid);

        for ( int i = 0; i < valid; i++ ) {
            if ( bag[ i ] == value )
                result.add(value);

        }
        return result;
    }

    /*
     * This method returns a IntBagIterator with itself
     * @return IntBagIterator, the new iterator with this bag
     */
    public Iterator iterator()
    {
        return new IntBagIterator(this);
    }
}