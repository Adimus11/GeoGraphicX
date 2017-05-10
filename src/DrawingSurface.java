import javax.swing.*;
import java.awt.* ;
import java.awt.geom.Rectangle2D;
/**
 * Klasa generująca pole na którym będzie odbywało się rysowanie.
 * @author Michał Treter
 */
public class DrawingSurface extends JPanel {
    final private GeoGraphicX appletReference;
    private MouseOperationController mouseController;
    
    /**
     * Konstruktor klasy, przyjmuje jako argument odwołanie do apletu.
     * @param mainWindowReference
     */
    public DrawingSurface(GeoGraphicX mainWindowReference){
        appletReference = mainWindowReference;
        
        setOpaque(true);
        initSurface();
        
    }
    
    private void initSurface(){
        mouseController = new MouseOperationController(appletReference);
        
        addMouseMotionListener(mouseController);
        addMouseListener(mouseController);
        addMouseWheelListener(new Rescaler(appletReference, mouseController));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g){
        Graphics2D graphicsElement = (Graphics2D) g;
        graphicsElement.setPaint(appletReference.backgroundColor);
        graphicsElement.fill(new Rectangle2D.Float(0.0f, 0.0f, 1280.0f, 680.0f));
        
        graphicsElement.setPaint(new Color(0, 0, 0));
        
        if(appletReference.drawableObjects != null){
            for(int i = appletReference.drawableObjects.size() - 1; i >= 0; i--){
                graphicsElement.setPaint(appletReference.drawableObjects.get(i).getColor());
                graphicsElement.fill(appletReference.drawableObjects.get(i).getShape());
            }
        }
        
        graphicsElement.setPaint(Color.BLACK);
        graphicsElement.draw(appletReference.pointerCircle);
        
        for(int i = 0; i < appletReference.temporaryDrawableObjects.size(); i++){
            if(appletReference.isResizingFigure){
                graphicsElement.fill(appletReference.temporaryDrawableObjects.get(i));
            }
            else{
                graphicsElement.draw(appletReference.temporaryDrawableObjects.get(i));
            }
        }
    }
    
}
