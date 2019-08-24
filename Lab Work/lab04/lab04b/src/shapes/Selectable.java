package shapes;
/*
 * Selectable interface which makes shapes selectable by determining if they contain a point
 * @author Ege Ozan Özyedek
 * @version v1.0
 * @date 13/03/18
 */
public interface Selectable {

    /*
     * Interface method to get the selected state of shape
     * @return boolean, the statement on selected
     */
    boolean getSelected();

    /*
     * Interface method to select or deselect shape
     * @param c, the t/f to be set
     */
    void setSelected(boolean c);

    /*
     * Interface method to determine if the shape contains a specified x and y point
     * @return Shape, either null or this Shape depending on if it contains the said point
     * @param x, x coordinate
     * @param y, y coordinate
     */
    Shape contains( int x, int y);
}
