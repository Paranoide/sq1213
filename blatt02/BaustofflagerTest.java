package sq1213.blatt02;

import static sq1213.blatt02.Baustofflager.Material;
import static sq1213.blatt02.Baustofflager.Material.*;

/**
 *
 * @author Paranoide
 */
public class BaustofflagerTest
{
    public static void main(String[] args)
    {
        Baustofflager bsl = new Baustofflager();
        boolean s;
        
        // Testfaelle
        Material[] materials = {TON, MARMOR, GRANIT, TON, MARMOR, GRANIT};
        int[] laengen = {17, 50, 68, 1, 50, 50};
        int[] mengen  = {1, 3456, 9999, 1, 100000, 1};
        String[] ids  = {"F1232", "FABCDE2", "Fa1348i9ujk2", "F1232", "F1232", "ABC"};
        
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
