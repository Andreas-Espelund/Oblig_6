import java.util.ArrayList;

public abstract class Rute {
    protected int rad;
    protected int kolonne;
    protected Labyrint labyrint;

    //indeks 0 = nord, 1 = oest, 2 = soer, 3 = vest
    Rute nord = null;
    Rute oest = null;
    Rute soer = null;
    Rute vest = null;
    boolean besoekt = false;

    // Indeks 0-nord, 1-oest, 2-syd, 3-vest
    Rute[] naboer = new Rute[4];


    protected Rute(int rad, int kolonne, Labyrint labyrint){
        this.rad = rad;
        this.kolonne = kolonne;
        this.labyrint = labyrint;
    }

    public void settOestligeNabo(Rute r){
        oest = r;
        r.settVestligeNabo(this);
        naboer[1] = r;

    }

    public void settVestligeNabo(Rute r){
        vest = r;
        naboer[3] = r;
    }

    public void settNordligeNabo(Rute r){
        nord = r;
        naboer[0] = r;
    }

    public void settSydligeNabo(Rute r){
        soer = r;
        r.settNordligeNabo(this);
        naboer[2] = r;
    }

    abstract char tilTegn();

    public void tilbakestill(){
        besoekt = false;
    }
    public boolean besoekt(){return besoekt;}

    public void finnUtvei(){
        gaa(new ArrayList<>());
    }
    
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
