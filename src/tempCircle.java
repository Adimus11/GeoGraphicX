
import java.awt.geom.Ellipse2D;

/**
 *
 * @author adimus
 */
public class tempCircle extends Ellipse2D.Float{

    /**
     *
     */
    public tempCircle(){
        super(-20.0f, -20.0f, 20.0f, 20.0f);
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y){
        this.x = x - 10.0f;
        this.y = y - 10.0f;
    }
}
