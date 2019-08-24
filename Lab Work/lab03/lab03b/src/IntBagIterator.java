import java.util.Iterator;
/*
 * IntBagIterator, which is a class that implements iterator to list IntBag elements
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 12/03/18
 */
public class IntBagIterator implements Iterator {
    //properties
    private IntBag aBag;
    private int index;

    //constructors

    /*
     * This constructor assigns the bag to aBag and sets the index to 0
     * @param bag, which is the IntBag that will be iterated
     */
    public IntBagIterator( IntBag bag)
    {
        aBag = bag;
        index = aBag.size() - 1;
    }

    //methods

    /*
     * A method to determine whether the IntBag has another element next
     * @return boolean, true or false depending on whether the bag has a next element or not
     */
    public boolean hasNext() {
        if(index >= 0) {
            return true;
        }
        index = aBag.size() - 1;  
        return false;
    }

    /*
     * Returns the next element of IntBag
     * @result Integer, which is the next element of IntBag
     */
    public Object next() {
        int result = aBag.get(index);
        index--;
        return result;
    }
}
