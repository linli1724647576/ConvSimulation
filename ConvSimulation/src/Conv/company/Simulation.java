package Conv.company;
import javax.swing.*;

public class Simulation {
    public static void main(String[] args) {
        /**
         *Suppose the experiment time is 1000 days
         */
        int ExperimentDay = 1000;
        /**
         * initialize the city,every person like a cell
         * 1% chance of infection
         */
        City field = new City(300,300);
        for ( int row = 0; row<field.getHeight(); row++ ) {
            for ( int col = 0; col<field.getWidth(); col++ ) {
                field.place(row, col, new person());
            }
        }
        for ( int row = 0; row<field.getHeight(); row++ ) {
            for ( int col = 0; col<field.getWidth(); col++ ) {
                person cell = field.get(row, col);
                if ( Math.random() < person.InitialRate) {
                    cell.setInflection();
                }
            }
        }
        /**
         *Initialize window
         */
        View view = new View(field);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("City");
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        for ( int i=0; i<ExperimentDay; i++ ) {
            int HealthCell = 0 , InflectionCell = 0 , InHospitalCell = 0 , DeathCell = 0;
            for (int row = 0; row < field.getHeight(); row++) {
                for (int col = 0; col < field.getWidth(); col++) {
                    person cell = field.get(row, col);
                    if(cell.getStatus() == person.HEALTH)  HealthCell++;
                    if(cell.getStatus() == person.INFLECTION) InflectionCell++;
                    if(cell.getStatus() == person.ISOLATION)  InHospitalCell++;
                    if(cell.getStatus() == person.DEATH) DeathCell++;
                    cell.update();
                    person[] neighbour = field.getNeighbour(row, col);
                    if(cell.getStatus() == person.INFLECTION){
                        for ( person c : neighbour ) {
                            if ( c.getStatus() == person.HEALTH ) {
                                if(Math.random()<person.InflectionRate) c.setInflection();
                            }
                        }
                    }
                }
            }
            System.out.println("UPDATE");
            System.out.printf("%d Day ::: The heath: %d . The Inflection: %d . The InHospital: %d . The Death: %d .",i,HealthCell,InflectionCell,InHospitalCell,DeathCell);
            frame.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
