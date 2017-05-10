import javax.swing.*;
import java.awt.* ;

/**
 * Klasa w której generowana jest główna ramka aplikacji.
 * @author Michał Treter
 */
public class MainApplicationFrame extends JPanel{

    /**
     * Odwołanie do głównej klasy.
     */
    public GeoGraphicX appletReference;

    /**
     * Odwoałanie do przestrzeni do rysowania.
     */
    public DrawingSurface drawSurface;

    /**
     * Odwołanie do panelu operacji.
     */
    public OperationBar operationBar;

    /**
     * Konstruktor naszej klasy.
     * @param mainAppletReference Odwołanie do apletu, z którego został wywołany konstruktor.
     */
    public MainApplicationFrame(GeoGraphicX mainAppletReference){
    	appletReference = mainAppletReference;

	setLayout(new BorderLayout());
        
        drawSurface = new DrawingSurface(mainAppletReference);
        operationBar = new OperationBar(mainAppletReference);
                
        operationBar.setPreferredSize(new Dimension(1280, 50));
        drawSurface.setPreferredSize(new Dimension(1280, 670));
	
        add(operationBar, BorderLayout.PAGE_START);
	add(drawSurface, BorderLayout.CENTER);


	}
}
