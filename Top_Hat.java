
import ij.ImagePlus;
import ij.plugin.filter.Binary;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/**
 * Oppgave 3 i oblig 9 Plugin som lager to bilder et med en såkalt hvit top hat
 * og en sort top hat. Vi tar det som en forutsetning at man bruker et binært
 * bilde, eller så ville vi lagt inn en terskel metode.
 *
 * @author Tine og Morten
 */
public class Top_Hat implements PlugInFilter {

    public int setup(String arg, ImagePlus im) {
        return DOES_8G;

    }

    public void run(ImageProcessor ip) {
        hvitHatt(ip);
        sortHatt(ip);

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metoden gjøre en åpnings operasjon på en kopi av bildet, for så å trekke
     * fra forandringen fra original bildet og laget et utbilde som bare viser hva som ble forandret
     *
     * @param iper et ImageProcessor objekt
     */
    public void hvitHatt(ImageProcessor ip) {
        ImagePlus hvitBilde;
        ImageProcessor kopi = ip.duplicate();
        ImageProcessor utBilde = ip.duplicate();

        open(kopi);

        int w = ip.getWidth();
        int h = ip.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                utBilde.putPixel(x, y, ip.getPixel(x, y) - kopi.getPixel(x, y));//orginal bildet  minus det åpnede bildet
            }
        }

        hvitBilde = hvitBilde = new ImagePlus("Hvit Tophat", utBilde);
        hvitBilde.show();

    }
    
     /**
     * Metoden gjøre en lukke operasjon på en kopi av bildet, for så å trekke
     *  original bildet fra bildet med lukke operasjoenn og laget et utbilde som bare viser hva som ble forandret
     *
     * @param iper et ImageProcessor objekt
     */

    public void sortHatt(ImageProcessor ip) {
        ImagePlus sortBilde;
        ImageProcessor kopi = ip.duplicate();
        ImageProcessor utBilde = ip.duplicate();
        close(kopi);
        int w = ip.getWidth();
        int h = ip.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                utBilde.putPixel(x, y, kopi.getPixel(x, y) - ip.getPixel(x, y)); // bildet som er lukket minus original bildet
            }
        }

        sortBilde = new ImagePlus("Sort Tophat", utBilde);
        sortBilde.show();

    }

    ////////////////////////////////////////////Hjelpemetoder/////////////////////////////////////////////////////////////////
    
    /**
     *Erosjon fulgt av en dilatasjon kalles en åpning 
     * og fjerner lyse elementer som er mindre enn struktur elementet
     * @param  ip  er bildet som blir sendt som parameter
     */
    private void open(ImageProcessor ip) {
        ip.erode();
        ip.dilate();

    }
/**
 * Dilatesjon fulgt av erosjon kalles en lukking og fjerner mørke strukturer mindre enn struktur elementet, 
 * @param ip er bildet sendt som parameter
 */
    private void close(ImageProcessor ip) {
        ip.dilate();
        ip.erode();

    }

}//slutt på klassen
