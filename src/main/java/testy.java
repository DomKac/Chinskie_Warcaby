import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Ramka extends JFrame {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    public JButton[][] pola_planszy = new JButton[19][29];

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


    public void make_neighbour_grey(int neighbourX, int neighbourY){

        if(pola_planszy[neighbourX][neighbourY] != null){
            if(pola_planszy[neighbourX][neighbourY].getBackground() == Color.WHITE) {
                pola_planszy[neighbourX][neighbourY].setBackground(Color.GRAY);
            }
        }
    }

    public void make_ALL_neighbours_grey(int currentX, int currentY){
        make_neighbour_grey(currentX,currentY-2); // koloruje sąsiada na zachód
        make_neighbour_grey(currentX,currentY+2); // koloruje sąsiada na wschód
        make_neighbour_grey(currentX-1,currentY-1); // koloruje sąsiada na północny-zachód
        make_neighbour_grey(currentX-1,currentY+1); // koloruje sąsiada na północny-wschód
        make_neighbour_grey(currentX+1,currentY-1); // koloruje sąsiada na południowy-zachód
        make_neighbour_grey(currentX+1,currentY+1); // koloruje sąsiada na południowy-wschód
    }

    public void mark_possible_jumps(int neighbourX, int neighbourY, int next_neighbourX, int next_neighbourY){

        if(pola_planszy[neighbourX][neighbourY] != null && pola_planszy[next_neighbourX][next_neighbourY] != null){
            if (pola_planszy[neighbourX][neighbourY].getBackground() != Color.WHITE && pola_planszy[neighbourX][neighbourY].getBackground() != Color.GRAY){
                if(pola_planszy[next_neighbourX][next_neighbourY].getBackground()==Color.WHITE) {
                    pola_planszy[next_neighbourX][next_neighbourY].setBackground(Color.GRAY);
                    check_jump_ALL(next_neighbourX, next_neighbourY);
                }
            }
        }
    }

    public void check_jump_ALL(int currentX, int currentY){
        mark_possible_jumps(currentX,currentY-2,currentX,currentY-4); // jump na zachód
        mark_possible_jumps(currentX,currentY+2,currentX,currentY+4); // jump na wschód
        mark_possible_jumps(currentX-1,currentY-1,currentX-2,currentY-2); // jump na północny-zachód
        mark_possible_jumps(currentX-1,currentY+1,currentX-2,currentY+2); // jump na północny-wschód
        mark_possible_jumps(currentX+1,currentY-1,currentX+2,currentY-2); // jump na południowy-zachód
        mark_possible_jumps(currentX+1,currentY+1,currentX+2,currentY+2); // jump na południowy-wschód
    }

    public void check_ALL(int currentX, int currentY){

        make_ALL_neighbours_grey(currentX,currentY);
        check_jump_ALL(currentX,currentY);

    }

    void clear_grey(){
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
                check_ALL(currentX, currentY);
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
                    clear_grey();
                    wybrano_piona = true;
                }
                else if (previousX == currentX && previousY == currentY){
                    clear_grey();
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
