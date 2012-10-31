package sq1213.blatt02;

import java.util.LinkedList;

/**
 *
 * @author Paranoide
 */
public class Baustofflager
{
    public static enum Material
    {
        TON("Ton"),
        MARMOR("Marmor"),
        GRANIT("Granit");
        
        private String name;
        
        Material(String name)
        {
            this.name = name;
        }
        
        @Override
        public String toString()
        {
            return this.name;
        }
    }
    
    private LinkedList<Fliese> lager;
    
    public Baustofflager()
    {
        this.lager = new LinkedList<>();
    }
    
    
    public boolean registrierung(Material mat, int laenge, int menge, String id)
    {
        boolean success = true;
        
        if (laenge < 17 || laenge > 68)
        {
            success = false;
        }
        
        if (menge < 1 || menge > 9999)
        {
            success = false;
        }
        
        if (!id.matches("F.*2"))
        {
            success = false;
        }
        
        if (success)
        {
            for (int t = 0; t < menge; t++)
            {
                Fliese f = new Fliese(mat, laenge, id);
                this.lager.add(f);
            }
        }
        
        return success;
    }
    
    
    
    private class Fliese
    {
        private Material mat;
        private int laenge;
        private String id;
        
        public Fliese(Material mat, int laenge, String id)
        {
            this.mat = mat;
            this.laenge = laenge;
            this.id = id;
        }
    }
    
    
}
