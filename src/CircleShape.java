
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Klasa odpowiedzialna za koło.
 * @author Michał Treter
 */
public class CircleShape extends ShapeBase{

    /**
     * Pole przechowujace kształt koła w tej klasie.
     */
    private Ellipse2D.Float shapeOfFigure;
    
    /**
     *  Konstruktor klasy, nadaja kołu pusty kształt.
     */
    public CircleShape(){
        shapeOfFigure = new Ellipse2D.Float();
    }
    
    /**
     * Metoda publiczna, której zadaniem jest ustawić rozmiar i położenie koła.
     * @param x Wartość w osi X.
     * @param y Wartość w osi Y.
     * @param r Wartość promienia okręgu.
     */
    public void setDimensions(float x, float y, float r){
        shapeOfFigure.setFrame(x, y, r, r);
    }
    
    /**
     * Metoda odpowiedzialna za przemieszczanie koła.
     * @param x Wartość o którą nalezy przesunąć koło w osi X.
     * @param y Wartość o którą nalezy przesunąć koło w osi Y.
     */
    @Override
    public void move(int x, int y){
        shapeOfFigure.x += x;
        shapeOfFigure.y += y;
    }
    
    /**
     * Metoda, która przechytuje i zwraca kształt figury.
     * @return zwrócony kształ figury.
     */
    @Override
    public Shape getShape(){
        return shapeOfFigure;
    }
    
    /**
     *  Metoda odpowiedzialna za sprawdzenie czy dane współrzedne znajdują sie w figurze.
     * @param x Współrzędna X kliknięcia.
     * @param y Współrzędna Y kliknięcia.
     * @return Wartość logiczna, prawdziwa jeśli punkty są w figurze, zaś fałyszwa w przeciwnym wypadku.
     */
    @Override
    public boolean isHit(float x, float y) {
        return shapeOfFigure.getBounds2D().contains(x, y);
    }

    /**
     * Metoda ustawiająca kolor naszej figurze.
     * @param newColor Nowy kolor jaki należy ustawić figurze.
     */
    @Override
    public void setColor(Color newColor) {
        if(newColor != null){
            shapeColor = newColor;
        }
    }

    /**
     * Metoda zwracająca kolor figury.
     * @return Zwrócony kolor jaki figura aktualnie posiada.
     */
    @Override
    public Color getColor() {
        return shapeColor;
    }

    /**
     * Metoda zmieniająca rozmiar figury.
     * @param value Wartość o jaką zostanie zwiększona figura.
     */
    @Override
    public void resize(float value) {
        shapeOfFigure.height += value;
        shapeOfFigure.width += value;
    }
    
}
