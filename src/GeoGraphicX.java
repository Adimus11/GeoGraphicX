import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;
import java.util.ArrayList;


/**
 * Klasa zawierająca adapter, który informuje co należy zrobić w momencie zamknięcia okna.
 * @author Michał Treter
 */
class GeoGraphicXWindowAdapter extends WindowAdapter {
  @Override
  public void windowClosing(WindowEvent e) { 
      System.exit(0); 
  }
}

/**
 * Główna klasa programu, zawiera metodę main oraz rozszerza aplet.
 * @author Michał Treter
 */
public class GeoGraphicX extends JApplet {

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces ryoswania prostokąta.
     */
    public boolean isDrawingSquare = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces ryoswania okręgu.
     */
    public boolean isDrawingCircle = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces ryoswania wielokątu.
     */
    public boolean isDrawingPoly = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces zmiany koloru figury.
     */
    public boolean isChangingColor = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces zmiany rozmiaru figury.
     */
    public boolean isResizingFigure = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny jakikolwiek proces modyfikowania figury w aplikacji.
     */
    public boolean isBusy = false;

    /**
     * Zmienna logiczna określająca czy jest teraz aktywny proces usuwania figury.
     */
    public boolean isDeleting = false;
    
    /**
     * Lista zawierająca figury do rysowania. 
     */
    public ArrayList<ShapeBase> drawableObjects = new ArrayList<>();

    /**
     * Lista zawierająca tymczasowe figury pomocniczne do rysowania.
     */
    public ArrayList<Shape> temporaryDrawableObjects = new ArrayList<>();

    /**
     * Celownik, który pojawia się kiedy jakiś proces modyfikowania figury jest aktywny.
     */
    public tempCircle pointerCircle = new tempCircle();

    /**
     * Pole przechowujące informacjie o kolorze tła.
     */
    public Color backgroundColor = new Color(255, 255, 255, 255);

    /**
     * Odwołanie do głównej ramki aplikacji, w której wszystko sie odbywa.
     */
    public MainApplicationFrame mainFrameReference;
    /**
     * Metoda inicjalizująca apletu.
     */
    
    @Override
    public void init() {
        setLayout(new FlowLayout());
        setJMenuBar(new MainMenu(this));
        
        mainFrameReference = new MainApplicationFrame(this);
        add(mainFrameReference);
        
        pointerCircle.setPosition(-20.0f, -20.0f);
    }

    /**
     * Główna metoda main naszej aplikacji
     * @param args parametry wpisane przy uruchumieniu.
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("GeoGraphicX");
        mainFrame.setSize(1280, 720);

        GeoGraphicX mainApplicationFrame = new GeoGraphicX();
        mainFrame.add(mainApplicationFrame);

        mainApplicationFrame.init();
        
        mainFrame.addWindowListener(new GeoGraphicXWindowAdapter());

        mainFrame.setVisible(true);
  }
}
