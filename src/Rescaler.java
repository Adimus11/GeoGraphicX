
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Klasa odpowiedzialna za interpretację działania kółka myszy.
 * Kiedy jest włączona odpowiednia akcja, przy pomocy kółka myszy figura zmienia swój rozmiar.
 * @author Michał Treter
 */
    class Rescaler implements MouseWheelListener{
        private GeoGraphicX appletReference;
        private MouseOperationController mouseReference;
        
        public Rescaler(GeoGraphicX mainWindowReference, MouseOperationController mouseControllerReference){
            appletReference = mainWindowReference;
            mouseReference = mouseControllerReference;
        }
        
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            if(mouseReference.hitIndex != -1 && appletReference.isResizingFigure){
               
                appletReference.drawableObjects.get(mouseReference.hitIndex).resize(e.getWheelRotation() * 5.0f);
                appletReference.repaint();
            }
            
        }
    }
