public class Tuppel {
    private int rad;
    private int kolonne;

    public Tuppel(int rad, int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;

    }

    public String toString(){
        return "(" + kolonne +","+rad+")";
    }
}
