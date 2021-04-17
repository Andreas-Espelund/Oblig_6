import java.util.ArrayList;
public class HvitRute extends Rute {
    
    public HvitRute(int rad, int kolonne, Labyrint labyrint){
        super(rad,kolonne,labyrint);
    }

    @Override
    public char tilTegn(){
        return ".".charAt(0);
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti){
        besoekt = true;
        
        ArrayList<Tuppel> nySti = new ArrayList<Tuppel>(sti);
        nySti.add(new Tuppel(rad, kolonne));

        for (Rute r : naboer){
            if (r != null){
                if(! r.besoekt()){
                    r.gaa(nySti);
                }
            }
            
            
        }
    }
}
