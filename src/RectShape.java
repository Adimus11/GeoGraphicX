
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Klasa odpowiedzialna za prostokąt.
 * @author Michał Treter
 */
public class RectShape extends ShapeBase{

    /**
     * Kształt prostokątu.
     */
    protected Rectangle2D.Float shapeOfFigure = new Rectangle2D.Float();
    
    /**
     * Konstruktor klasy, zeruje kształt prostokątu.
     */
    public RectShape(){
        this.shapeOfFigure.setRect(0.0f, 0.0f, 0.0f, 0.0f);
    }
    
    /**
     * Metoda ustawiająca rozmiar oraz polożenie prostokątu.
     * @param x Punkt na osi X.
     * @param y Punkt na osi Y.
     * @param width Szerokość prostokąta.
     * @param height Wysokość prostokąta.
     */
    public void setDimensions(float x, float y, float width, float height) {
        shapeOfFigure.setRect(x, y, width, height);
    }
    
    /**
     * Metoda przesuwająca prostokąt w osi X i osi Y.
     * @param x Wartość o jaką należy przesunąć figurę w osi X.
     * @param y Wartość o jaką należy przesunąć figurę w osi Y.
     */
    @Override
    public void move(int x, int y){
        shapeOfFigure.x += x;
        shapeOfFigure.y += y;
    }
    
    /**
     * Metoda zwracająca kształt figury.
     * @return Kształ prostokątu.
     */
    @Override
    public Shape getShape(){
        return shapeOfFigure;
    }
    
    /**
     * Metoda ustawiająca figurze nowy kolor.
     * @param newColor Nowy kolor.
     */
    @Override
    public void setColor(Color newColor){
        if(newColor != null){
            shapeColor = newColor;
        }
    }

    /**
     * Metoda odpowiedzialna za sprawdzenie czy dane współrzedne znajdują sie w figurze.
     * @param x Współrzędna X kliknięcia.
     * @param y Współrzędna Y kliknięcia.
     * @return Wartość logiczna, prawdziwa jeśli punkty są w figurze, zaś fałyszwa w przeciwnym wypadku.
     */
    @Override
    public boolean isHit(float x, float y) {
        return shapeOfFigure.getBounds2D().contains(x, y);
    }
    
    /**
     * Metoda zwracjąca akutalny kolor figury.
     * @return aktualny kolor figury.
     */
    @Override
    public Color getColor(){
        return shapeColor;
    }

    /**
     * Metoda zmieniająca rozmiar figur.
     * @param value Wartość o jaką należy zmienić rozmiar figury.
     */
    @Override
    public void resize(float value) {
        shapeOfFigure.height += value;
        shapeOfFigure.width += value;
    }
}