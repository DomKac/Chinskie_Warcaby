import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Ramka_2 extends JFrame {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    Guzik pola_planszy[][] = new Guzik[17][25];
    //JButton pola_planszy[][] = new JButton[17][25];
    Color kolor_piona;

    int plansza[][] = {
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


    public boolean wybrano_piona = true; // pomaga określić czy trzeba wybrać pionka czy ruszyć pionka
    // true -> kliknięcie pola_planszy wybiera pionka którego chcemy ruszyć
    // false -> kilknięcie pola_planszy stawia wcześniej wybranego piona na wybrane miejsce


    public ActionListener wyb_pionek = new ActionListener() {

        //public Color kolor_piona;


        @Override
        public void actionPerformed(ActionEvent e) {

            /*

            if(wybrano_piona){
                kolor_piona = pola_planszy[x][y].getBackground();
                //kolor_piona = pola_planszy[x][y].getBackground();


                if(pola_planszy[x][y-2] != null){
                    if(pola_planszy[x][y-2].getBackground() == Color.WHITE) {
                        pola_planszy[x][y - 2].setBackground(Color.GRAY);
                    }
                }
                if(pola_planszy[x][y+2] != null){
                    if(pola_planszy[x][y+2].getBackground() == Color.WHITE) {
                        pola_planszy[x][y + 2].setBackground(Color.GRAY);
                    }
                }
                if(pola_planszy[x-1][y-1] != null){
                    if(pola_planszy[x-1][y-1].getBackground() == Color.WHITE) {
                        pola_planszy[x - 1][y - 1].setBackground(Color.GRAY);
                    }
                }
                if(pola_planszy[x+1][y+1] != null){
                    if(pola_planszy[x+1][y+1].getBackground() == Color.WHITE) {
                        pola_planszy[x + 1][y + 1].setBackground(Color.GRAY);
                    }
                }
                if(pola_planszy[x-1][y+1] != null){
                    if(pola_planszy[x-1][y+1].getBackground() == Color.WHITE) {
                        pola_planszy[x - 1][y + 1].setBackground(Color.GRAY);
                    }
                }
                if(pola_planszy[x+1][y-1] != null){
                    if(pola_planszy[x+1][y-1].getBackground() == Color.WHITE) {
                        pola_planszy[x + 1][y - 1].setBackground(Color.GRAY);
                    }
                }



                System.out.println("dupa guzika (" + getX() + ", " + getY() + ")");
                //wybrano_piona = false;
            }

             */
        }
    };
    ActionListener move_piona = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!wybrano_piona){
                System.out.println("dupa guzika (" + getX() + ", " + getY() + ")");
            }

        }
    };

    /**
     * Tworzenie planszy
     */
    Ramka_2(){
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

                    Guzik pionek = new Guzik(x,y);
                    this.add(pionek);

                    pionek.addActionListener(wyb_pionek);
                    pionek.addActionListener(move_piona);

                    //System.out.println("Wspolrzedne (" + pola_planszy[x][y].getX() + ", " + pola_planszy[x][y].getY());

                    /*
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

                     */
                }
            }
        }
        setResizable(true);
    }
}
