import java.util.ArrayList;

public class Aapning extends HvitRute{
    
    public Aapning(int rad, int kolonne, Labyrint labyrint){
        super(rad, kolonne, labyrint);
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti){
        ArrayList<Tuppel> nySti = new ArrayList<Tuppel>(sti);
        nySti.add(new Tuppel(rad, kolonne));

        labyrint.leggTilUtvei(nySti);
    }
}
