import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import static java.util.Collections.swap;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
/**
 * Klasa odpowiedzialna za kontrole ruchów oraz kliknięć myszy.
 * W klasie tej także zawarta jest cała logika programu oraz tego jak należy wykonywać poszczególne operacje
 * na poszczególnych figurach, oraz nadzoruje proces tworzenia nowych figur i usuwania wybranych.
 * @author Michał Treter
 */
public class MouseOperationController extends MouseAdapter{
    private int x, y, prevX = -1, prevY = -1, xOnClick = 0, yOnClick = 0;

    /**
     * Zmienna przechowująca numer figury z listy, która został kliknięty.
     */
    public int hitIndex = -1;
    private ArrayList<Point> polyPoints = new ArrayList<>();
    private GeoGraphicX appletReference;
      
    /**
     * Konstruktor naszej klasy.
     * @param mainWindowReference Odwołanie do głównego apletu.
     */
    public MouseOperationController(GeoGraphicX mainWindowReference){
        appletReference = mainWindowReference;
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        if(appletReference.isBusy){
            x = e.getX();
            y = e.getY();
        
            appletReference.pointerCircle.setPosition(x, y);
            
            if(appletReference.isDrawingSquare && prevX != -1 && prevY != -1){
                Rectangle2D.Float tempRectOutline = new Rectangle2D.Float();
                tempRectOutline.setRect(Utilities.findLower(prevX, e.getX()), Utilities.findLower(prevY, e.getY()), abs(e.getX() - prevX), abs(e.getY() - prevY));
                
                for(int i = appletReference.temporaryDrawableObjects.size() - 1; i > 0; i--){
                    appletReference.temporaryDrawableObjects.remove(i);
                }
                appletReference.temporaryDrawableObjects.add(tempRectOutline);
            }
            
            if(appletReference.isDrawingCircle && prevX != -1 && prevY != -1){
                int radius;
                radius = (int) sqrt(pow(e.getX() - prevX, 2) + pow(e.getY() - prevY, 2));
                
                Ellipse2D.Float tempCircleOutline = new Ellipse2D.Float();
                tempCircleOutline.setFrame(Utilities.findLower(e.getX(), prevX), Utilities.findLower(e.getY(), prevY), radius, radius);
                
                for(int i = appletReference.temporaryDrawableObjects.size() - 1; i > 0; i--){
                    appletReference.temporaryDrawableObjects.remove(i);
                }
                appletReference.temporaryDrawableObjects.add(tempCircleOutline);
            }
        
            appletReference.repaint();
        }
        else{
            if(appletReference.pointerCircle.x > 0 || appletReference.pointerCircle.y > 0){
                appletReference.pointerCircle.setPosition(-20.0f, -20.0f);
                
                appletReference.repaint();
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        appletReference.pointerCircle.setPosition(-20.0f, -20.0f);
        
        appletReference.repaint();
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        
        if(xOnClick == -1 && yOnClick == -1){
            xOnClick = e.getX() - 1;
            yOnClick = e.getY() - 1;
        }
        
        hitIndex = Utilities.findCollision(appletReference.drawableObjects, xOnClick, yOnClick);
        
        if(hitIndex > 0){
            ShapeBase temp = appletReference.drawableObjects.get(hitIndex);
            
            for(int i = hitIndex - 1; i >= 0; i-- ){
                swap(appletReference.drawableObjects, i, i + 1); 
            }
        }
        
        if(!appletReference.isBusy && hitIndex != -1){
            
            
            
            appletReference.drawableObjects.get(hitIndex).move(e.getX() - xOnClick, e.getY() - yOnClick);
            
            xOnClick = e.getX();
            yOnClick = e.getY();
        }
        
        appletReference.repaint();
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        hitIndex = -1;
        
        xOnClick = -1;
        yOnClick = -1;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        xOnClick = e.getX();
        yOnClick = e.getY();
        
        if(e.getButton() == MouseEvent.BUTTON3){
            appletReference.isBusy = false;
            appletReference.isDrawingSquare = false;
            appletReference.isDrawingCircle = false;
            appletReference.isDrawingPoly = false;
            appletReference.isResizingFigure = false;
            appletReference.isChangingColor = false;
            appletReference.isDeleting = false;
            
            appletReference.mainFrameReference.operationBar.addCircle.setEnabled(true);
            appletReference.mainFrameReference.operationBar.addRect.setEnabled(true);
            appletReference.mainFrameReference.operationBar.addPoly.setEnabled(true);
            appletReference.mainFrameReference.operationBar.resizeAction.setEnabled(true);
            appletReference.mainFrameReference.operationBar.colorChangeAction.setEnabled(true);
            appletReference.mainFrameReference.operationBar.deleteAction.setEnabled(true);
            
            appletReference.temporaryDrawableObjects.clear();
            polyPoints.clear();
            
            appletReference.repaint();
            
            prevX = -1;
            prevY = -1;
        }
        
        if(e.getButton() == MouseEvent.BUTTON1){
            if(appletReference.isBusy){
                
                if(appletReference.isResizingFigure && hitIndex != -1){
                    appletReference.temporaryDrawableObjects.add(appletReference.pointerCircle);
                }
                
                if(appletReference.isDrawingSquare){
                    if(prevX == -1 && prevY == -1){
                        prevX = e.getX();
                        prevY = e.getY();
                    }
                    else{
                        RectShape tempRectShape = new RectShape();
                        tempRectShape.setDimensions(Utilities.findLower(prevX, e.getX()), Utilities.findLower(prevY, e.getY()), abs(e.getX() - prevX), abs(e.getY() - prevY));
                        appletReference.drawableObjects.add(tempRectShape);
                        appletReference.temporaryDrawableObjects.clear();
                        
                        appletReference.isBusy = false;
                        appletReference.isDrawingSquare = false;
                        
                        prevX = -1;
                        prevY = -1;
                        
                        appletReference.mainFrameReference.operationBar.addRect.setEnabled(true);
                    }
                }
                
                if(appletReference.isDrawingCircle){
                    if(prevX == -1 && prevY == -1){
                        prevX = e.getX();
                        prevY = e.getY();
                    }
                    else{
                        int radius;
                        radius = (int) sqrt(pow(e.getX() - prevX, 2) + pow(e.getY() - prevY, 2));
                        
                        CircleShape tempCircleShape = new CircleShape();
                        tempCircleShape.setDimensions(Utilities.findLower(e.getX(), prevX), Utilities.findLower(e.getY(), prevY), radius);
                        
                        appletReference.temporaryDrawableObjects.clear();
                        appletReference.drawableObjects.add(tempCircleShape);
                        
                        appletReference.isBusy = false;
                        appletReference.isDrawingCircle = false;
                        
                        prevX = -1;
                        prevY = -1;
                        
                        appletReference.mainFrameReference.operationBar.addCircle.setEnabled(true);
                    }
                }
                
                if(appletReference.isDrawingPoly){
                    if(prevX == -1 && prevY == -1){
                        prevX = e.getX();
                        prevY = e.getY();
                        
                        polyPoints.add(new Point(e.getX(), e.getY()));
                        
                        tempCircle tempPolyPoint = new tempCircle();
                        tempPolyPoint.setPosition(e.getX(), e.getY());
                        
                        appletReference.temporaryDrawableObjects.add(0, tempPolyPoint);
                    }
                    else{
                        if(appletReference.temporaryDrawableObjects.get(0).getBounds2D().contains(e.getX(), e.getY())){
                            PolyShape tempPolyShape = new PolyShape();
                            tempPolyShape.setDimensions(polyPoints);
                            polyPoints.clear();
                            
                            prevX = -1;
                            prevY = -1;
                            
                            appletReference.isBusy = false;
                            appletReference.isDrawingPoly = false;
                            appletReference.temporaryDrawableObjects.clear();
                            
                            appletReference.drawableObjects.add(tempPolyShape);
                            
                            appletReference.mainFrameReference.operationBar.addPoly.setEnabled(true);
                        }
                        else{
                            polyPoints.add(new Point(e.getX(), e.getY()));
                            appletReference.temporaryDrawableObjects.add(new Line2D.Float(prevX, prevY, e.getX(), e.getY()));
                            appletReference.temporaryDrawableObjects.add(appletReference.pointerCircle);
                            
                            prevX = e.getX();
                            prevY = e.getY();
                        }
                    }
                }
                
                if(appletReference.isDeleting){
                    boolean deleted = false;
                    
                    hitIndex = Utilities.findCollision(appletReference.drawableObjects, xOnClick, yOnClick);
                    
                    if(hitIndex != -1){
                        deleted = true;
                        appletReference.drawableObjects.remove(hitIndex);
                        hitIndex = -1;
                    }
                    
                    if(!deleted){
                        JOptionPane.showMessageDialog(appletReference,
                        "Nie wybrano zadnej figury do usuniecia.",
                        "Brak wybranej figury",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    appletReference.isBusy = false;
                    appletReference.isDeleting = false;
                    
                    appletReference.mainFrameReference.operationBar.deleteAction.setEnabled(true);
                }
                
                if(appletReference.isChangingColor){
                    
                    hitIndex = Utilities.findCollision(appletReference.drawableObjects, xOnClick, yOnClick);
                    
                    if(hitIndex != -1){
                        Color newColor = appletReference.drawableObjects.get(hitIndex).getColor();
                        newColor = JColorChooser.showDialog(null, "Zmien Kolor Figury", Color.BLACK);
                        appletReference.drawableObjects.get(hitIndex).setColor(newColor);
                    }
                    else{
                        JOptionPane.showMessageDialog(appletReference,
                        "Nie wybrano zadnej figury do zmiany koloru.",
                        "Brak wybranej figury",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    
                    appletReference.isBusy = false;
                    appletReference.isChangingColor = false;
                    
                    appletReference.mainFrameReference.operationBar.colorChangeAction.setEnabled(true);
                }
                
                if(appletReference.isResizingFigure){
                    hitIndex = Utilities.findCollision(appletReference.drawableObjects, e.getX(), e.getY());

                    if(hitIndex != -1){
                        appletReference.temporaryDrawableObjects.add(appletReference.pointerCircle);
                    }
                }
                
            }
        }
        appletReference.repaint();
    }
}
