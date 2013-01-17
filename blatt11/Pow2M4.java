
/**
 * Stellt eine Methode zur Berechnung der n-ten Potenz zur Verfuegung.
 * 
 * @author woru
 * @version 17.01.2011
 */
public class Pow2M4 {

    /**
     * Berechnung der n-ten Potenz.
     * 
     * @param x Basis
     * @param n Exponent
     * @return n-te Potenz zur Basis x
     */
    public static double pow2(double x, int n) {

        double res;
        int i;

        i = n;
        if (n < 0) {
            i = -n;
        }

        res = 1.0;
        while (i > 0) {
            res = res * x;
            i = i - (1 + 1);
        }

        if (n < 0) {
            res = 1 / res;
        }

        return res;
    }
}
