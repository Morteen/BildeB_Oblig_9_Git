
import ij.ImagePlus;
import  ij.plugin.filter.Binary;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

/**
 * Oppgave 3 i oblig 9  Plugin som lager to bilder et av gradient styrken og et
 * av gradient retningen
 *
 * @author Tine og Morten
 */
public class White_tophat implements PlugInFilter {

    public int setup(String arg, ImagePlus im) {
        return DOES_8G;

    }
    

    public void run(ImageProcessor ip) {
         ImageProcessor sortKopi= ip.duplicate();
          ImageProcessor hvitKopi= ip.duplicate();
                 ImagePlus sortBilde,hvitBilde;
         
sort(sortKopi);
hvit(hvitKopi);
        hvitBilde = new ImagePlus("Hvit tophat", hvitKopi);
        hvitBilde.show();
        sortBilde = new ImagePlus("Sorttophat", sortKopi);
        sortBilde.show();

    }
  public  void hvit(ImageProcessor ip){
 ip. erode();
  ip.dilate();
  }
  
  public  void sort(ImageProcessor ip){
   ip.dilate();
     ip. erode();
    
  }
   
   
   

}//slutt på klassen
