
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Klasa zawierająca funkcje ułatwiające działanie programu.
 * @author Michał Treter
 */
public class Utilities {

    /**
     * Funkcja zwracająca mniejszą wartość.
     * @param cord1 Wartość pierwsza.
     * @param cord2 Wartość druga.
     * @return Mniejsza z tych wartości.
     */
    public static int findLower(int cord1, int cord2){
        if(cord1 > cord2){
            return cord2;
        }
        else{
            return cord1;
        }
    }
    
    /**
     * Funkcja znajdująca kolizję punktu kliknięcia z jedną z figur.
     * @param givenList Lista figur.
     * @param cordX X kliknięcia.
     * @param cordY Y Kliknięcia.
     * @return Numer elementu na liście z którym jest wykryta kolizja. Lub -1 kiedy jej nie ma.
     */
    public static int findCollision(ArrayList<ShapeBase> givenList, int cordX, int cordY){
        int foundIndex = -1;
        
        for(int i = 0; i < givenList.size(); i++){
            if(givenList.get(i).isHit(cordX, cordY)){
                foundIndex = i;
                i = givenList.size();
            }
        }
        
        return foundIndex;
    }
    
    /**
     * Funkcja eksportująca listę obiektów do pliku.
     * @param appletReference Referencja do głównego apletu.
     * @param givenList Lista figur.
     */
    public static void saveFile(GeoGraphicX appletReference, ArrayList<ShapeBase> givenList){
        if(appletReference.drawableObjects.size() > 0){
            String path = "";

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "GeoGraphicX File Format", "geox");
            chooser.setFileFilter(filter);
            
            int respond = chooser.showSaveDialog(null);

            if(respond == JFileChooser.APPROVE_OPTION){
                path = chooser.getSelectedFile()+".geox";
                                
                try {
                    FileOutputStream fos = new FileOutputStream(path);
                    try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        oos.writeObject(givenList);
                    }
                } 
                catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  

        }
        else{
            JOptionPane.showMessageDialog(appletReference, "Nic nie zostalo narysowane wiec nie ma co zapisywac");
        }
    }
    
    /**
     * Funkcja wczytująca listę figur z pliku.
     * @param appletReference Referencja do głównego apletu.
     * @return Lista wczytana z pliku.
     * @throws ClassNotFoundException Wyjątek kiedy nastąpi niezgodność klas.
     */
    public static ArrayList<ShapeBase> loadFile(GeoGraphicX appletReference) throws ClassNotFoundException{
        String path = "";
        ArrayList<ShapeBase> loadedFile = null;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "GeoGraphicX File Format", "geox");
        chooser.setFileFilter(filter);
        
        int respond = chooser.showOpenDialog(null);

        if(respond == JFileChooser.APPROVE_OPTION){
            path = chooser.getSelectedFile().toString();
                                
            try {
                FileInputStream fis = new FileInputStream(path);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    loadedFile = (ArrayList<ShapeBase>) ois.readObject();
                }
            } 
            catch (IOException ex) {
                JOptionPane.showMessageDialog(appletReference, "Wczytanie pliku nie powiodlo sie");
            }
        }  

        else{
            JOptionPane.showMessageDialog(appletReference, "Wczytywanie anulowane przez użytkownika");
        }
        
        
        return loadedFile;
    }
    
}
