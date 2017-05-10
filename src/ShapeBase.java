
import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;


/**
 * Abstrakcyjna klasa odpowiedzialna za kształt figur.
 * @author Michał Treter
 */
public abstract class ShapeBase implements Serializable{

    /**
     * Kolor Figur.
     */
    protected Color shapeColor = Color.RED;

    
    /**
     * Abstrakcyjna metoda zwracająca kształt figury.
     * @return Kształt figury.
     */
    public abstract Shape getShape();

    /**
     * Abstrakcyjna metoda sprawdzając czy punkt znajduje się w kształcie.
     * @param x X punktu.
     * @param y Y punktu.
     * @return Wartość logiczna prawdziwa jeśli jest, fałszywa w przeciwnym wypadku.
     */
    public abstract boolean isHit(float x, float y);

    /**
     * Abstrakcyjna metoda przesuwająca kształt w osi X i osi Y.
     * @param x Wartość o ile przesunąć w osi X.
     * @param y Wartość o ile przesunąć w osi X.
     */
    public abstract void move(int x, int y);

    /**
     * Abstrakcyjna metoda zmieniająca rozmiar kształtu.
     * @param value Wartość o jaką zostaje zmniejszony rozmiar.
     */
    public abstract void resize(float value);

    /**
     * Abstrakcyjna metoda zmieniająca kolor kształtu.
     * @param newColor Nowy kolor.
     */
    public abstract void setColor(Color newColor);

    /**
     * Abstrakcyjna metoda zwracająca kolor kształtu.
     * @return Aktualny kolor kształtu.
     */
    public abstract Color getColor();
}
