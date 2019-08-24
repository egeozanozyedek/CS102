package cs102.ds;

/**
 * A class which contains Nodes which contain data, so basically a list
 * @author EgeOzan
 * @since 8 May 2018
 * @version 1.1
 */
public class List {

    /**
     * A class which contains data and the Node next to them
     * @author EgeOzan
     * @since 7 May 2018
     * @version 1.0
     */
    private static class Node {
        private String data;
        private Node next;
    }

    //properties
    private Node head;

    //constructors

    /**
     * Constructor of List class, initializes head to null
     */
    public List() {
        head = null;
    }

    //methods

    /**
     * A method to add String data to the head
     * @param item, the data to be stored in the head
     */
    public void addToHead( String item) {
        Node newNode;
        newNode = new Node();
        newNode.data = item;
        newNode.next = head;
        head = newNode;
    }

    /**
     * A method to add String data to the tail of the list
     * @param item, the String data to be added to the tail
     * @return item
     */
    public String addToTail( String item) {
        Node newNode, dataNode;
        newNode = head;
        while ( newNode.next != null){
            newNode = newNode.next;
        }
        dataNode = new Node();
        dataNode.data = item;
        newNode.next = dataNode;
        return item;
    }

    /**
     * A method which removes the data from the head and replaces the head with the next;
     * @return null or the item depending on whether the head exists or not
     */
    public String removeFromHead() {

        if ( head == null)
            return null;

        String item = head.data;
        head = head.next;
        return item;
    }

    /**
     * A method that returns whether the List is empty or not
     * @return true or false depending on whether the head is null ( head == null being true means the list is empty)
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * A method that returns the data at the desired index
     * @param index, the data index
     * @return null or the data  at the index depending on the index being valid or invalid
     */
    public String getData( int index) {
        Node dataNode;
        dataNode = head;
        for ( int i = 0; i < index; i++) {
            dataNode = dataNode.next;
            if (dataNode == null)
                return null;
        }
        return dataNode.data;
    }

    /**
     * A method to print data the List contains
     */
    public void print() {
        if ( isEmpty())
            System.out.println( "Empty List");
        else {
            Node newNode;
            newNode = head;
            System.out.println("--Data--");
            System.out.println(newNode.data);
            while (newNode.next != null) {
                newNode = newNode.next;
                System.out.println(newNode.data);
            }
        }
    }

    /**
     * A method which prints the data the List contains in reverse
     */
    public void printReverse() {
        Node newNode;
        newNode = new Node();
        newNode.data = removeFromHead();
        if ( newNode.data != null) {
            printReverse();
            System.out.println( newNode.data );
            addToHead( newNode.data);
        }
    }
}
