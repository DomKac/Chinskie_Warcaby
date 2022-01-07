import javax.swing.*;

class Guzik extends JButton {

    public int x;
    public int y;
    //Color kolor;

    public Guzik(int x,int y){
        this.x = x;
        this.y = y;
        //this.kolor = kolor;
    }

    public int getXguzika(){
        return this.x;
    }
    public int getYguzika(){
        return this.y;
    }

}

