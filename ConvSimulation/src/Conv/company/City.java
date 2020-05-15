package Conv.company;
import java.util.ArrayList;
public class City {
    /**
     *Suppose there is a city where everyone lives quietly in their own place,
     * The city has it width and lenth,
     *One person lives in each unit
     */
    private int width;
    private int height;
    private person[][] field;

    /**
     * create a city (constructor)
     * @param width  width
     * @param height height
     */
    public City(int width, int height) {
        this.width = width;
        this.height = height;
        field = new person[height][width];
    }

    /**
     * get Width and Height
     * @return width and height
     */
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public person place(int row, int col, person o) {
        person ret = field[row][col];
        field[row][col] = o;
        return ret;
    }

    /**
     * get the appoint unit's person
     * @param row row
     * @param col col
     * @return person class
     */
    public person get(int row, int col) {
        return field[row][col];
    }

    /**
     *Get everyone's neighbors
     * @param row row
     * @param col col
     * @return A array of persons
     */
    public person[] getNeighbour(int row, int col) {
        ArrayList<person> list = new ArrayList<person>();
        for ( int i=-1; i<2; i++ ) {
            for ( int j=-1; j<2; j++ ) {
                int r = row+i;
                int c = col+j;
                if ( r >-1 && r<height && c>-1 && c<width && !(r== row && c == col) ) {
                    list.add(field[r][c]);
                }
            }
        }
        return list.toArray(new person[list.size()]);
    }

    /**
     * The city will be clear
     */
    public void clear() {
        for ( int i=0; i<height; i++ ) {
            for ( int j=0; j<width; j++ ) {
                field[i][j] = null;
            }
        }
    }
}
