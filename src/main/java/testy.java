import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Ramka extends JFrame {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    JButton[][] pola_planszy = new JButton[17][25];


    int[][] plansza = {
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            { 2,-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1, 6},
            {-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1},
            {-1,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1,-1},
            {-1,-1,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1,-1,-1},
            {-1,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1,-1,-1,-1},
            {-1,-1,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1,-1,-1},
            {-1,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1,-1},
            {-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1},
            { 3,-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1, 5},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};

    public int get_current_X(String coordinates){

        String x = "";
        int i = 0;
        while (coordinates.charAt(i) != ','){
            x = x + coordinates.charAt(i);
            i++;

        }
        return Integer.parseInt(x);
    }

    public int get_current_Y(String coordinates){

        String y = "";
        int i = 0;
        while (coordinates.charAt(i) != ','){
            i++;
        }
        i++;
        while (i<coordinates.length()){
            y = y + coordinates.charAt(i);
            i++;
        }
        return Integer.parseInt(y);
    }
    // TODO: 

    public void koloruj_mozliwe_pola(int currentX,int currentY){

        if(pola_planszy[currentX][currentY-2] != null){
            if(pola_planszy[currentX][currentY-2].getBackground() == Color.WHITE) {
                pola_planszy[currentX][currentY - 2].setBackground(Color.GRAY);
            }
        }
        if(pola_planszy[currentX][currentY+2] != null){
            if(pola_planszy[currentX][currentY+2].getBackground() == Color.WHITE) {
                pola_planszy[currentX][currentY + 2].setBackground(Color.GRAY);
            }
        }
        if(pola_planszy[currentX-1][currentY-1] != null){
            if(pola_planszy[currentX-1][currentY-1].getBackground() == Color.WHITE) {
                pola_planszy[currentX - 1][currentY - 1].setBackground(Color.GRAY);
            }
        }
        if(pola_planszy[currentX+1][currentY+1] != null){
            if(pola_planszy[currentX+1][currentY+1].getBackground() == Color.WHITE) {
                pola_planszy[currentX + 1][currentY + 1].setBackground(Color.GRAY);
            }
        }
        if(pola_planszy[currentX-1][currentY+1] != null){
            if(pola_planszy[currentX-1][currentY+1].getBackground() == Color.WHITE) {
                pola_planszy[currentX - 1][currentY + 1].setBackground(Color.GRAY);
            }
        }
        if(pola_planszy[currentX+1][currentY-1] != null){
            if(pola_planszy[currentX+1][currentY-1].getBackground() == Color.WHITE) {
                pola_planszy[currentX + 1][currentY - 1].setBackground(Color.GRAY);
            }
        }
    }


    public ActionListener wyb_pionek = new ActionListener() {

        public boolean wybrano_piona = true; // pomaga określić czy trzeba wybrać pionka czy ruszyć pionka
        // true -> kliknięcie pola_planszy wybiera pionka którego chcemy ruszyć
        // false -> kilknięcie pola_planszy stawia wcześniej wybranego piona na wybrane miejsce
        Color kolor_piona;
        int currentX;
        int currentY;

        @Override
        public void actionPerformed(ActionEvent e) {

            String coordinates = ((JComponent) e.getSource()).getName();
            System.out.println(coordinates);
            currentX = get_current_X(coordinates);
            currentY = get_current_Y(coordinates);
            System.out.println(currentX);
            System.out.println(currentY);

            System.out.println(wybrano_piona);
            if(wybrano_piona){

                kolor_piona = pola_planszy[currentX][currentY].getBackground();

                koloruj_mozliwe_pola(currentX, currentY);

                wybrano_piona = false;
                System.out.println("wybrano_piona: " + wybrano_piona);
                System.out.println(kolor_piona);
                System.out.println();
            }
            else{
                System.out.println("Teraz nalezy wybrac gdzie sie ruszyc");
                System.out.println(kolor_piona);
                pola_planszy[currentX][currentY].setBackground(kolor_piona);

                System.out.println("wybrano_piona: " + wybrano_piona);
                System.out.println();
                wybrano_piona = true;
            }

        }
    };


    /**
     * Tworzenie planszy
     */
    Ramka(){
        super("Chinskie Warcaby");
        setBounds(200,200,640,480);
        addWindowListener(new MyWindowAdapter());
        setLayout(new GridLayout(17, 25));

        for(int x=0;x<17;x++)
        {
            for(int y=0; y<25 ; y++) {

                if (plansza[x][y] == -1){

                    JPanel niegrywalne_pole = new JPanel();
                    this.add(niegrywalne_pole);
                }
                else {
                    pola_planszy[x][y] = new JButton();
                    pola_planszy[x][y].setName(x + "," + y);

                    //System.out.println(pola_planszy[x][y].getName());
                    this.add(pola_planszy[x][y]);

                    pola_planszy[x][y].addActionListener(wyb_pionek);

                    if (plansza[x][y] == 0) {
                        this.pola_planszy[x][y].setBackground(Color.WHITE);

                    } else if (plansza[x][y] == 1) {
                        this.pola_planszy[x][y].setBackground(Color.PINK);

                    } else if (plansza[x][y] == 2) {
                        this.pola_planszy[x][y].setBackground(Color.ORANGE);

                    } else if (plansza[x][y] == 3) {
                        this.pola_planszy[x][y].setBackground(Color.YELLOW);

                    } else if (plansza[x][y] == 4) {
                        this.pola_planszy[x][y].setBackground(Color.BLUE);

                    } else if (plansza[x][y] == 5) {
                        this.pola_planszy[x][y].setBackground(Color.RED);

                    } else if (plansza[x][y] == 6) {
                        this.pola_planszy[x][y].setBackground(Color.GREEN);

                    }

                }
            }
        }
        setResizable(true);
    }
}


/*--------------------------------------------------------------------*/

/**
 * Glowna klasa programu
 */

public class testy {

    /** * Tworzenie ramki o własciowsciach klasy "Ramka" */
    public Ramka frame;

    /**
     * Glowna metoda odpalajaca program i wyswietlajaca ramke
     * @param args args
     */
    public static void main(String[] args){
        testy p = new testy();
        p.frame = new Ramka();
        p.frame.setVisible(true);
    }
}
