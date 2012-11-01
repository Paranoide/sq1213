package sq1213.blatt02;

import sq1213.blatt02.Baustofflager.Material;
import static sq1213.blatt02.Baustofflager.Material.*;

/**
 *
 * @author Paranoide
 */
public class BaustofflagerTest
{
    /**
     * Die gute, alte main...
     * @param args Kommandozeilen-Krams
     */
    public static void main(String[] args)
    {
        Baustofflager bsl = new Baustofflager();
        boolean s;
        
        // Testfaelle
        Material[] materials = {TON, MARMOR, GRANIT, /*STEIN,*/ TON, MARMOR,
                                GRANIT, TON, MARMOR, GRANIT};
        int[] laengen = {17, 68, 17, /*68,*/ 16, 69, 17, 68, 17, 68};
        int[] mengen  = {1, 9999, 1, /*9999,*/ 1, 9999, 0, 10000, 1, 9999};
        String[] ids  = {"F12", "F22", "F32", /*"F42",*/ "F52",
                         "F62", "F72", "F82", "A92", "F93"};
        
        for (int t = 0; t < materials.length; t++)
        {
            s = bsl.registrierung(materials[t], laengen[t], mengen[t], ids[t]);
            
            String output = "Material: " + materials[t] + "\n" + 
                            "Laenge:   " + laengen[t] + "\n" +
                            "Menge:    " + mengen[t] + "\n" +
                            "ID:       " + ids[t] + "\n" +
                            "ERGEBNIS: " + s + "\n";
            System.out.println(output);
        }
        
    }
}
