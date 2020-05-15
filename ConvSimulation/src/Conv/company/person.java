package Conv.company;
import java.awt.Graphics;
import java.awt.Color;
public class person {
    /**
     * The person's status
     * Record the duration of each state
     */
    private int status;
    private int T;
    /**
     * If the person has four status,HEALTH,INFECTION,ISOLATION,DEATH
     * They will form a circular network
     */
    public static final int HEALTH=0;
    public static final int INFLECTION=1;
    public static final int ISOLATION=2;
    public static final int DEATH=3;

    /**
     * CureRate
     * InflectionRate
     */
    public static final double CureRate = 0.95;  //0.95 0.99
    public static final double InflectionRate = 0.1; //0.1 0.05
    public static final double InitialRate = 0.005;  //0.005 0.0005 0.00005

    /**
     *Update status daily
     *If you are healthy , you will stay healthy
     * If you are INFLECTION and the T large 3 ,you will be detected and isolation
     * If you are in hospital, and the length of hospital stay spans more than 8 days,you will setHeath
     * but you have 0.95's rate dead
     * if you have dead,the time will be static
     */

    public void update(){
        if(status == HEALTH){
            this.T ++;
        }
        else if(status == INFLECTION){
            if(this.T<2)  this.T ++;  //2 3 4
            else setIsolation();
        }
        else if(status == ISOLATION){
            if(this.T<8)  this.T++;
            else {
                if(Math.random()<CureRate) this.setHealth();
                else setDeath();
            };
        }
    }

    /**
     * getStatus
     * @return The preson's status
     */
    public int getStatus() {
        return status;
    }

    /**
     * discharge from hospital and curl successfully
     */
    public void setHealth(){
        this.status = HEALTH;
        this.T = 0;
    }

    /**
     *If you have been inflected,the status will change from HEALTH to INFLECTION
     */
    public void setInflection(){
        this.status = INFLECTION;
        this.T = 0;
    }

    /**
     * You're going to be quarantined in a while
     */
    public void setIsolation(){
        this.status = ISOLATION;
        this.T = 0;
    }

    /**
     * Death
     */
    public void setDeath(){
        this.status = DEATH;
        this.T = 0;
    }


    /**
     * draw a little cell
     * @param g little Graphics
     * @param x x_position
     * @param y y_position
     * @param size size
     * The different statuses have different color
     * HTALTH --- GREEN
     * INFLECTION --- RED
     * ISOLATION --- ORANGE
     * DEATH --- BLACK
     */
    public void draw(Graphics g, int x, int y, int size) {
        g.drawRect(x, y, size, size);
        if ( status == HEALTH ) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, size, size);
        }
        if(status == INFLECTION){
            g.setColor(Color.RED);
            g.fillRect(x, y, size, size);
        }
        if(status ==  ISOLATION){
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, size, size);
        }
        if(status == DEATH){
            g.setColor(Color.BLACK);
            g.fillRect(x, y, size, size);
        }
    }

}
