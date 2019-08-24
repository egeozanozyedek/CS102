import java.util.Scanner;
import shapes.*;

/*
 * A class that tests Shape class
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 12/03/18
 */
public class ShapeTester {
    public static void main(String[] args)
    {
        //constants

        //variables
        Scanner scan;
        ShapeContainer container;
        int input, rad, width, height;
        int x, y;
        int index;


        //program code

        scan = new Scanner( System.in);
        container = null;

        //do while to ask the user untill they enter the exit number
        do {
            Shape shape;
            System.out.println( printMenu());
            input = scan.nextInt();

            //if input is 1 it will create a empty container
            if ( input == 1)
            {
                container = new ShapeContainer();
                System.out.println("Empty Container created.");
            }

            //if the container is null and the input is lower than 10 (this is so that the user can exit whenever they want)
            //it will print an error message 
            else if( container == null && input < 10 )
            {
                System.out.println("Container not created.");
            }
            
            //else it will fall into here
            else
            {
                //asks the user a radius and adds a circle to container
                if ( input == 2)
                {
                    System.out.println( "Enter radius");
                    rad = scan.nextInt();

                    shape = new Circle( rad);
                    container.add( shape);
                }
                
                //asks the user a side and adds a square to container
                else if ( input == 3)
                {
                    System.out.println( "Enter side");
                    width = scan.nextInt();

                    shape = new Square( width);
                    container.add( shape);
                }
                
                //asks the user sides and adds a rectangle to container
                else if ( input == 4)
                {
                    System.out.println( "Enter width");
                    width = scan.nextInt();
                    System.out.println( "Enter height");
                    height = scan.nextInt();

                    shape = new Rectangle( width, height);
                    container.add( shape);
                }

                //prints total area
                else if ( input == 5)
                {
                    System.out.println("Total Area: " + container.getArea());
                }

                //prints container object
                else if ( input == 6)
                {
                    System.out.println(container);
                }

                //returns and selects the first shape which contains x and y
                else if ( input == 7)
                {
                    System.out.println("Enter x coordinate");
                    x = scan.nextInt();
                    System.out.println("Enter y coordinate");
                    y = scan.nextInt();
                    if (container.findFirst(x, y) == null)
                    {
                        System.out.println("Unable to find shape which contains said point and/or is not selected already");
                    }
                    else
                    {
                        container.findFirst(x, y).setSelected(true);
                        System.out.println("Selected shape");
                    }
                }

                //if the input is 8 the user can remove all selected shapes
                else if ( input == 8)
                {
                    container.removeSelected();
                    System.out.println("Selected shape(s) removed.");
                }

                //if the input is 9 the user can change the x,y coordinates of a selected shape
                else if ( input == 9)
                {
                    if ( container.getSize() > 0) {
                      
                        do {
                            System.out.println("Enter index of said shape.");
                            index = scan.nextInt();
                            if ( index > container.getSize() - 1)
                                System.out.println("Invalid index.");
                        } while (index > container.getSize() - 1);
                        
                        System.out.println("Enter new x coordinate");
                        x = scan.nextInt();
                        System.out.println("Enter new y coordinate");
                        y = scan.nextInt();
                        container.getShape(index).setLocation(x, y);
                        System.out.println("New coordinates set.");
                    }
                    else {
                        System.out.println("Empty container.");
                    }
                }
                
               // prints an error message if the input is not viable
                else if ( input != 10)
                {
                    System.out.println("Invalid input.");
                }

            }

        } while ( input != 10 );

        System.out.println( "Goodbye!");
        scan.close();
    }

    /*
     * A static method that prints the menu
     * @return String, the menu
     */
    private static String printMenu()
    {
        return "\n(1) Create Shape Container\n" + "(2) Add Circle\n" + "(3) Add Square\n"
                + "(4) Add Rectangle\n" + "(5) Get Total Area\n" + "(6) Get Details\n"
                + "(7) Search for Point\n" + "(8) Remove Selected\n"
                + "(9) Set New Location of Specified Shape\n" + "(10) Exit\n";
    }
}
