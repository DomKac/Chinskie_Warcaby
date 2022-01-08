import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Ramka extends JFrame {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    JButton[][] pola_planszy = new JButton[19][29];

    final int[][] plansza = {
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1, 2,-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1, 6,-1,-1},
            {-1,-1,-1, 2,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1, 6,-1,-1,-1},
            {-1,-1,-1,-1, 2,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1, 6,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1, 2,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 6,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1,-1,-1,-1},
            {-1,-1,-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1,-1,-1},
            {-1,-1, 3,-1, 3,-1, 3,-1, 3,-1, 0,-1, 0,-1, 0,-1, 0,-1, 0,-1, 5,-1, 5,-1, 5,-1, 5,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
    };


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

    // TODO: combo

    public void koloruj_pole(int possibleX, int possibleY){

        if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
            pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
        }
        /*
        else if (pola_planszy[possibleX][possibleY].getBackground() != Color.GRAY){
            koloruj_mozliwe_pola(possibleX,possibleY);
        }

        else if (pola_planszy[possibleX][possibleY] != null && pola_planszy[possibleX][possibleY].getBackground() != Color.GRAY){
            System.out.println(pola_planszy[possibleX][possibleY].getBackground());
            koloruj_pole(possibleX,possibleY);
        }

         */
    }

    public void check_W(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX][currentY-2] != null){

            possibleX = currentX;
            possibleY = currentY-2;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX][possibleY-2] != null && pola_planszy[possibleX][possibleY-2].getBackground()==Color.WHITE) {
                    check_W(possibleX, possibleY);
                }
            }

        }
    }
    public void check_NW(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX-1][currentY-1] != null){

            possibleX = currentX-1;
            possibleY = currentY-1;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX-1][possibleY-1] != null && pola_planszy[possibleX-1][possibleY-1].getBackground()==Color.WHITE) {
                    check_NW(possibleX, possibleY);
                }
            }

        }
    }
    public void check_SW(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX+1][currentY-1] != null){

            possibleX = currentX+1;
            possibleY = currentY-1;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX+1][possibleY-1] != null && pola_planszy[possibleX+1][possibleY-1].getBackground()==Color.WHITE) {
                    check_SW(possibleX, possibleY);
                }
            }

        }
    }
    public void check_E(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX][currentY+2] != null){

            possibleX = currentX;
            possibleY = currentY+2;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX][possibleY+2] != null && pola_planszy[possibleX][possibleY+2].getBackground()==Color.WHITE) {
                    check_E(possibleX, possibleY);
                }
            }

        }
    }
    public void check_NE(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX-1][currentY+1] != null){

            possibleX = currentX-1;
            possibleY = currentY+1;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX-1][possibleY+1] != null && pola_planszy[possibleX-1][possibleY+1].getBackground()==Color.WHITE) {
                    check_NE(possibleX, possibleY);
                }
            }

        }
    }
    public void check_SE(int currentX, int currentY){

        int possibleX;
        int possibleY;

        if(pola_planszy[currentX+1][currentY+1] != null){

            possibleX = currentX+1;
            possibleY = currentY+1;
            if(pola_planszy[possibleX][possibleY].getBackground() == Color.WHITE) {
                pola_planszy[possibleX][possibleY].setBackground(Color.GRAY);
            }
            else {
                if(pola_planszy[possibleX+1][possibleY+1] != null && pola_planszy[possibleX+1][possibleY+1].getBackground()==Color.WHITE) {
                    check_SE(possibleX, possibleY);
                }
            }

        }
    }



    public void koloruj_mozliwe_pola(int currentX,int currentY){

        check_W(currentX,currentY);
        check_SW(currentX,currentY);
        check_NW(currentX,currentY);
        check_E(currentX,currentY);
        check_SE(currentX,currentY);
        check_NE(currentX,currentY);

        /*
        int possibleX;
        int possibleY;


        if(pola_planszy[currentX][currentY-2] != null){

            possibleX = currentX;
            possibleY = currentY-2;

            koloruj_pole(possibleX,possibleY);
        }
        if(pola_planszy[currentX][currentY+2] != null){

            possibleX = currentX;
            possibleY = currentY+2;

            koloruj_pole(possibleX,possibleY);
        }
        if(pola_planszy[currentX-1][currentY-1] != null){

            possibleX = currentX-1;
            possibleY = currentY-1;

            koloruj_pole(possibleX,possibleY);
        }
        if(pola_planszy[currentX+1][currentY+1] != null){

            possibleX = currentX+1;
            possibleY = currentY+1;

            koloruj_pole(possibleX,possibleY);
        }
        if(pola_planszy[currentX-1][currentY+1] != null){

            possibleX = currentX-1;
            possibleY = currentY+1;

            koloruj_pole(possibleX,possibleY);
        }
        if(pola_planszy[currentX+1][currentY-1] != null){

            possibleX = currentX+1;
            possibleY = currentY-1;

            koloruj_pole(possibleX,possibleY);
        }

         */

    }

    void clear_grey_2(){
        for(int x=1; x<=17; x++){
            for (int y=2; y<=26; y++){
                if(pola_planszy[x][y] != null){
                    if(pola_planszy[x][y].getBackground()==Color.GRAY){
                        pola_planszy[x][y].setBackground(Color.WHITE);
                    }
                }
            }
        }
    }

    public void clear_grey(int previousX, int previousY) {
        if(pola_planszy[previousX][previousY-2] != null){
            if(pola_planszy[previousX][previousY-2].getBackground() == Color.GRAY) {
                pola_planszy[previousX][previousY - 2].setBackground(Color.WHITE);
            }
        }
        if(pola_planszy[previousX][previousY+2] != null){
            if(pola_planszy[previousX][previousY+2].getBackground() == Color.GRAY) {
                pola_planszy[previousX][previousY + 2].setBackground(Color.WHITE);
            }
        }
        if(pola_planszy[previousX-1][previousY-1] != null){
            if(pola_planszy[previousX-1][previousY-1].getBackground() == Color.GRAY) {
                pola_planszy[previousX - 1][previousY - 1].setBackground(Color.WHITE);
            }
        }
        if(pola_planszy[previousX+1][previousY+1] != null){
            if(pola_planszy[previousX+1][previousY+1].getBackground() == Color.GRAY) {
                pola_planszy[previousX + 1][previousY + 1].setBackground(Color.WHITE);
            }
        }
        if(pola_planszy[previousX-1][previousY+1] != null){
            if(pola_planszy[previousX-1][previousY+1].getBackground() == Color.GRAY) {
                pola_planszy[previousX - 1][previousY + 1].setBackground(Color.WHITE);
            }
        }
        if(pola_planszy[previousX+1][previousY-1] != null){
            if(pola_planszy[previousX+1][previousY-1].getBackground() == Color.GRAY) {
                pola_planszy[previousX + 1][previousY - 1].setBackground(Color.WHITE);
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
        int previousX;
        int previousY;

        @Override
        public void actionPerformed(ActionEvent e) {

            String coordinates = ((JComponent) e.getSource()).getName();
            System.out.println(coordinates);
            currentX = get_current_X(coordinates);
            currentY = get_current_Y(coordinates);

            System.out.println(currentX);
            System.out.println(currentY);

            if(wybrano_piona){

                if(pola_planszy[currentX][currentY].getBackground() != Color.WHITE){

                kolor_piona = pola_planszy[currentX][currentY].getBackground();
                koloruj_mozliwe_pola(currentX, currentY);
                previousX = currentX;
                previousY = currentY;
                wybrano_piona = false;

                System.out.println();
                }
                else{
                    System.out.println("Wybierz kolorowe pole ");

                }
            }
            else{
                if(pola_planszy[currentX][currentY].getBackground() == Color.GRAY) {
                    System.out.println("Teraz nalezy wybrac gdzie sie ruszyc");
                    pola_planszy[currentX][currentY].setBackground(kolor_piona);
                    pola_planszy[previousX][previousY].setBackground(Color.WHITE);
                    //clear_grey(previousX,previousY);
                    clear_grey_2();
                    wybrano_piona = true;
                }
                else if (previousX == currentX && previousY == currentY){
                    //clear_grey(previousX,previousY);
                    clear_grey_2();
                    wybrano_piona = true;
                }
                else{
                    System.out.println("zle pole debilu");
                }

            }

        }
    };


    /**
     * Tworzenie planszy
     */
    Ramka(int liczba_graczy){
        super("Chinskie Warcaby");
        setBounds(200,200,640,480);
        addWindowListener(new MyWindowAdapter());
        setLayout(new GridLayout(19, 29));

        for(int x=0;x<19;x++)
        {
            for(int y=0; y<29 ; y++) {

                if (plansza[x][y] == -1){

                    JPanel niegrywalne_pole = new JPanel();
                    this.add(niegrywalne_pole);
                }
                else {
                    pola_planszy[x][y] = new JButton();
                    pola_planszy[x][y].setName(x + "," + y);
                    pola_planszy[x][y].addActionListener(wyb_pionek);
                    this.add(pola_planszy[x][y]);

                    koloruj_pole_w_zaleznosci_od_liczby_graczy(x,y,liczba_graczy);
                }
            }
        }
        setResizable(true);
    }

    public void koloruj_pole_w_zaleznosci_od_liczby_graczy(int x, int y, int liczba_graczy){

        if(liczba_graczy==2){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 4) {
                pola_planszy[x][y].setBackground(Color.BLUE);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==3){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==4){
            if (plansza[x][y] == 2) {
                pola_planszy[x][y].setBackground(Color.ORANGE);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else if (plansza[x][y] == 6) {
                pola_planszy[x][y].setBackground(Color.GREEN);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }
        else if (liczba_graczy==6){
            if (plansza[x][y] == 1) {
                pola_planszy[x][y].setBackground(Color.PINK);
            }
            else if (plansza[x][y] == 2) {
                pola_planszy[x][y].setBackground(Color.ORANGE);
            }
            else if (plansza[x][y] == 3) {
                pola_planszy[x][y].setBackground(Color.YELLOW);
            }
            else if (plansza[x][y] == 4) {
                pola_planszy[x][y].setBackground(Color.BLUE);
            }
            else if (plansza[x][y] == 5) {
                pola_planszy[x][y].setBackground(Color.RED);
            }
            else if (plansza[x][y] == 6) {
                pola_planszy[x][y].setBackground(Color.GREEN);
            }
            else {
                pola_planszy[x][y].setBackground(Color.WHITE);
            }
        }


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
        p.frame = new Ramka(6 );
        p.frame.setVisible(true);
    }
}
