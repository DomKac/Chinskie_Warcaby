import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MainPanel extends JPanel {

    /** * Tablica guzikow, ktore beda naszymi polami planszy**/
    public JButton[][] pola_planszy = new JButton[19][29];
    public JPanel[][] niegrywalne_pola = new JPanel[19][29];


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

    public boolean check_win_BLUE(){
        return (pola_planszy[1][14].getBackground() == Color.BLUE && pola_planszy[2][13].getBackground() == Color.BLUE && pola_planszy[2][15].getBackground() == Color.BLUE &&
                pola_planszy[3][12].getBackground() == Color.BLUE && pola_planszy[3][14].getBackground() == Color.BLUE && pola_planszy[3][16].getBackground() == Color.BLUE &&
                pola_planszy[4][11].getBackground() == Color.BLUE && pola_planszy[4][13].getBackground() == Color.BLUE && pola_planszy[4][15].getBackground() == Color.BLUE &&
                pola_planszy[4][17].getBackground() == Color.BLUE);
    }
    public boolean check_win_PINK(){
        return (pola_planszy[17][14].getBackground() == Color.PINK && pola_planszy[16][13].getBackground() == Color.PINK && pola_planszy[16][15].getBackground() == Color.PINK &&
                pola_planszy[15][12].getBackground() == Color.PINK && pola_planszy[15][14].getBackground() == Color.PINK && pola_planszy[15][16].getBackground() == Color.PINK &&
                pola_planszy[14][11].getBackground() == Color.PINK && pola_planszy[14][13].getBackground() == Color.PINK && pola_planszy[14][15].getBackground() == Color.PINK &&
                pola_planszy[14][17].getBackground() == Color.PINK);
    }
    public boolean check_win_RED(){
        return (pola_planszy[5][2].getBackground() == Color.RED && pola_planszy[5][4].getBackground() == Color.RED && pola_planszy[5][6].getBackground() == Color.RED &&
                pola_planszy[5][8].getBackground() == Color.RED && pola_planszy[6][3].getBackground() == Color.RED && pola_planszy[6][5].getBackground() == Color.RED &&
                pola_planszy[6][7].getBackground() == Color.RED && pola_planszy[7][4].getBackground() == Color.RED && pola_planszy[7][6].getBackground() == Color.RED &&
                pola_planszy[8][5].getBackground() == Color.RED);
    }
    public boolean check_win_GREEN(){
        return (pola_planszy[13][2].getBackground() == Color.GREEN && pola_planszy[13][4].getBackground() == Color.GREEN && pola_planszy[13][6].getBackground() == Color.GREEN &&
                pola_planszy[13][8].getBackground() == Color.GREEN && pola_planszy[12][3].getBackground() == Color.GREEN && pola_planszy[12][5].getBackground() == Color.GREEN &&
                pola_planszy[12][7].getBackground() == Color.GREEN && pola_planszy[11][4].getBackground() == Color.GREEN && pola_planszy[11][6].getBackground() == Color.GREEN &&
                pola_planszy[10][5].getBackground() == Color.GREEN);
    }
    public boolean check_win_ORANGE(){
        return (pola_planszy[13][20].getBackground() == Color.ORANGE && pola_planszy[13][22].getBackground() == Color.ORANGE && pola_planszy[13][24].getBackground() == Color.ORANGE &&
                pola_planszy[13][26].getBackground() == Color.ORANGE && pola_planszy[12][21].getBackground() == Color.ORANGE && pola_planszy[12][23].getBackground() == Color.ORANGE &&
                pola_planszy[12][25].getBackground() == Color.ORANGE && pola_planszy[11][22].getBackground() == Color.ORANGE && pola_planszy[11][24].getBackground() == Color.ORANGE &&
                pola_planszy[10][23].getBackground() == Color.ORANGE);
    }
    public boolean check_win_YELLOW(){
        return (pola_planszy[5][20].getBackground() == Color.YELLOW && pola_planszy[5][22].getBackground() == Color.YELLOW && pola_planszy[5][24].getBackground() == Color.YELLOW &&
                pola_planszy[5][26].getBackground() == Color.YELLOW && pola_planszy[6][21].getBackground() == Color.YELLOW && pola_planszy[6][23].getBackground() == Color.YELLOW &&
                pola_planszy[6][25].getBackground() == Color.YELLOW && pola_planszy[7][22].getBackground() == Color.YELLOW && pola_planszy[7][24].getBackground() == Color.YELLOW &&
                pola_planszy[8][23].getBackground() == Color.YELLOW);
    }

    public boolean check_ENDGAME(){
        return check_win_BLUE() || check_win_YELLOW() || check_win_GREEN() || check_win_PINK() || check_win_RED() || check_win_ORANGE();
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
                    if(check_ENDGAME()){
                        System.out.println("KONIEC!");
                        //System.exit(0);
                    }
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
    MainPanel(int liczba_graczy){
        //super("Chinskie Warcaby");
        //setBounds(200,200,640,480);
        //addWindowListener(new MyWindowAdapter());
        //setLayout(new BorderLayout());
       // JLabel text = new JLabel("Dduppa");
        //this.add(text,BorderLayout.PAGE_START);
        //JButton pass = new JButton("PASS");
        //this.add(pass, BorderLayout.PAGE_END);
        //JPanel panel_plansza = new JPanel();
        setLayout(new GridLayout(19, 29));
        //this.add(panel_plansza);

        for(int x=0;x<19;x++)
        {
            for(int y=0; y<29 ; y++) {

                if (plansza[x][y] == -1){

                    niegrywalne_pola[x][y] = new JPanel();
                    this.add(niegrywalne_pola[x][y]);
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
        //setResizable(true);
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

class InstrukcjaRamka extends JFrame{

    InstrukcjaRamka(){
        super("Instrukcja");
        JTextField text = new JTextField();
        setBounds(200,200,640,560);
        text.setEditable(false);
        text.setBackground(Color.WHITE);
        this.add(text);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}



class Ramka extends JFrame{

    MainPanel panel_z_plansza;



    Ramka(int liczba_graczy){
        super("Chinskie Warcaby");
        setBounds(200,200,640,560);
        addWindowListener(new MyWindowAdapter());
        setLayout(new BorderLayout());

        panel_z_plansza = new MainPanel(liczba_graczy);
        this.add(panel_z_plansza,BorderLayout.CENTER);

        JPanel up_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(up_panel, BorderLayout.PAGE_START);
        JPanel down_panel = new JPanel();
        up_panel.setLayout(new GridLayout(1,2));
        this.add(down_panel, BorderLayout.PAGE_END);

        JTextField which_player = new JTextField();
        which_player.setEditable(false);
        which_player.setText("Tura gracza: ");
        which_player.setHorizontalAlignment(JTextField.CENTER);
        which_player.setBackground(Color.WHITE);
        up_panel.add(which_player);

        JTextField your_color = new JTextField();
        your_color.setEditable(false);
        your_color.setText("Twój kolor to: ");
        your_color.setHorizontalAlignment(JTextField.CENTER);
        your_color.setBackground(Color.PINK);
        up_panel.add(your_color);

        ActionListener open_instruction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InstrukcjaRamka nowa_instrukcja = new InstrukcjaRamka();
                nowa_instrukcja.setVisible(true);
            }
        };

        JButton instrukcja = new JButton("Instrukcja");
        down_panel.add(instrukcja);
        instrukcja.addActionListener(open_instruction);

        JButton pass = new JButton("PASS");
        down_panel.add(pass);

        setResizable(true);

    }

}

/*--------------------------------------------------------------------*/

/**
 * Glowna klasa programu
 */

public class testy {

    /** * Tworzenie ramki o własciowsciach klasy "MainPanel" */
    public Ramka frame;

    /**
     * Glowna metoda odpalajaca program i wyswietlajaca ramke
     * @param args args
     */
    public static void main(String[] args){
        testy p = new testy();
        p.frame = new Ramka(6);
        p.frame.setVisible(true);

    }
}
