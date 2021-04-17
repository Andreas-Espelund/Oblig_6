import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Labyrint
 */
public class Labyrint {

    Rute[][] rutenett;
    int rader;
    int kolonner;
    ArrayList<ArrayList<Tuppel>> utveier = new ArrayList<>();
    
    

    public Labyrint(File fil) throws FileNotFoundException{
        Scanner sc = new Scanner(fil);
        String[] biter = sc.nextLine().split(" ");
        rader = Integer.parseInt(biter[0]);
        kolonner = Integer.parseInt(biter[1]);
        rutenett = new Rute[rader][kolonner];
        for (int i = 0 ; i < rader ; i ++){
            char[] tegn = sc.nextLine().toCharArray();
            for (int j = 0 ; j < kolonner ; j++){
                Rute nyRute;
                if (String.valueOf(tegn[j]).equals(".")){
                    if (i == 0 || i == rader-1 || j == 0 || j == kolonner-1){
                        nyRute = new Aapning(i,j,this);
                    }else{
                        nyRute = new HvitRute(i, j, this);
                    }
                }else{
                    nyRute = new SortRute(i, j, this);
                }
                rutenett[i][j] = nyRute;
                
            }
        }

        settNaboer();
        

    }
    private void settNaboer(){
        //setter vertikale naboforhold, nord og syd
        for (int i = 0; i < rader-1 ; i++){
            for (int j = 0; j < kolonner ; j++){
                //setter sydlige nabo, og den sydlige naboen setter nordlige nabo
                rutenett[i][j].settSydligeNabo(rutenett[i+1][j]);
            }
        }
        //setter horistontale naboforohld
        for (int i = 0; i < rader ; i ++){
            for (int j = 0; j < kolonner-1 ; j++){
                //setter oestlige nabo, og den oestlige naboen setter vestlige nabo
                rutenett[i][j].settOestligeNabo(rutenett[i][j+1]);
            }
        }
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kolonne, int rad){
        utveier.clear();
        rutenett[rad][kolonne].finnUtvei();
        tilbakestillRuter();
        return utveier;
    }

    public void leggTilUtvei(ArrayList<Tuppel> sti){
        utveier.add(sti);
    }

    public void tilbakestillRuter(){
        for (int i = 0; i < rader ; i ++){
            for (int j = 0; j < kolonner ; j++){
                rutenett[i][j].tilbakestill();
            }
        }
    }

    public String toString(){
        
        String returstreng = "";
        int utganger = 0;

        for (int i = 0 ; i < rader ; i++) {
            for (int j = 0 ; j < kolonner ; j++) {
                returstreng += rutenett[i][j].tilTegn();
                if (rutenett[i][j] instanceof Aapning) utganger ++;
            }
            returstreng+="\n";
        }
        System.out.println("Utganger: "+utganger);
    return returstreng;
    }
}