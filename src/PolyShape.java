
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za wielokąt.
 * @author Michał Treter
 */
public class PolyShape extends ShapeBase{

    /**
     * Kształt wielokątu.
     */
    protected Polygon polyShape;

    /**
     * Zmienna zawierjąca X środka figury.
     */
    protected float centerX,

    /**
     * Zmienna zawierjąca Y środka figury.
     */
    centerY;
 
    /**
     * Konstuktor klasy. Przypisuje pusty wielokąt.
     */
    public PolyShape(){
        polyShape = new Polygon();
    }
    
    /**
     * Metoda ustawiająca pozycje oraz rozmiar wielokątu poprzez dodawanie punktów do jego struktury.
     * @param pointsList
     */
    public void setDimensions(ArrayList<Point> pointsList){
        for(int i = 0; i < pointsList.size(); i++){
            polyShape.addPoint(pointsList.get(i).x, pointsList.get(i).y);       
        }
    }
    
    private void setCenter(){
        float maxX, minX, maxY, minY;
        
        maxX = polyShape.xpoints[0];
        minX = maxX;
        maxY = polyShape.ypoints[0];
        minY = maxY;
        
        for(int i = 1; i < polyShape.npoints; i++){
            if(minX > polyShape.xpoints[i]){
                minX = polyShape.xpoints[i];
            }
            if(maxX < polyShape.xpoints[i]){
                maxX = polyShape.xpoints[i];
            }
            if(minY > polyShape.ypoints[i]){
                minY = polyShape.ypoints[i];
            }
            if(maxY < polyShape.ypoints[i]){
                maxY = polyShape.ypoints[i];
            }
        }
        centerX = (minX + maxX) / 2;
        centerY = (minY + maxY) / 2;
        
    }
    
    /**
     * Metoda przesuwająca wielokąt w osi X i osi Y.
     * @param x Wartość o jaką należy przesunąć figurę w osi X.
     * @param y Wartość o jaką należy przesunąć figurę w osi Y.
     */
    @Override
    public void move(int x, int y){
        for(int i = 0; i < polyShape.npoints; i++){
            polyShape.xpoints[i] += x;
            polyShape.ypoints[i] += y;
        }
        
        polyShape.invalidate();
    }
    
    /**
     * Metoda zwracająca kształt figury.
     * @return Kształ wielokątu.
     */
    @Override
    public Shape getShape() {
        return polyShape;
    }

    /**
     * Metoda odpowiedzialna za sprawdzenie czy dane współrzedne znajdują sie w figurze.
     * @param x Współrzędna X kliknięcia.
     * @param y Współrzędna Y kliknięcia.
     * @return Wartość logiczna, prawdziwa jeśli punkty są w figurze, zaś fałyszwa w przeciwnym wypadku.
     */
    @Override
    public boolean isHit(float x, float y) {
        return polyShape.getBounds2D().contains(x, y);
    }

    /**
     * Metoda ustawiająca figurze nowy kolor.
     * @param newColor Nowy kolor.
     */
    @Override
    public void setColor(Color newColor) {
        if(newColor != null){
            shapeColor = newColor;
        }
    }

    /**
     * Metoda zwracjąca akutalny kolor figury.
     * @return aktualny kolor figury.
     */
    @Override
    public Color getColor() {
        return shapeColor;
    }

    /**
     * Metoda zmieniająca rozmiar figur.
     * @param value Wartość o jaką należy zmienić rozmiar figury.
     */
    @Override
    public void resize(float value) {
        setCenter();
        
        for(int i = 0; i < polyShape.npoints; i++){
            if(value > 0){
                polyShape.xpoints[i] *= (1.1);
                polyShape.ypoints[i] *= (1.1);
            }
            else{
               polyShape.xpoints[i] *= (0.9);
               polyShape.ypoints[i] *= (0.9); 
            }
        }
        
        float tempX = centerX, tempY = centerY;
        
        setCenter();
        
        tempX = centerX - tempX;
        tempY = centerY - tempY;
        
        for(int i = 0; i < polyShape.npoints; i++){
            polyShape.xpoints[i] -= tempX;
            polyShape.ypoints[i] -= tempY;
        }
                   
        polyShape.invalidate();
    }
    
}
