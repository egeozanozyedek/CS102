package cs102.ds;

/**
 * A class which pushes or pops String data into a list
 * @author EgeOzan
 * @since 7 May 2018
 * @version 1.0
 */
public class Stack {

    //properties
    private List list;

    //constructors

    /**
     * Constructor of class which creates and empty stack
     */
    public Stack() {
        list = new List();
    }

    //methods

    /**
     * A method which "pushes" the item to the head of stack
     * @param item, the data that will be added to the head
     */
    public void push( String item) {
        list.addToHead( item);
    }

    /**
     * A method which "pops" the item from stack
     * @return the item that is removed or null
     */
    public String pop() {
        return list.removeFromHead();
    }

    /**
     * A method which returns whether the stack is empty or not
     * @return true or false depending on the stack being empty or not
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
