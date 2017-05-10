import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 * Klasa, która jest odpowiedzialna za pasek menu.
 * @author Michał Treter
 */
public class MainMenu extends JMenuBar{
    GeoGraphicX appletReference;

    /**
     * Konstuktor klasy, w którym są tworzone pozycje do paska menu oraz dodawane. W nim także każdy element dostaje swoją akcję.
     * @param mainWindowReference Odwołanie do głównego apletu.
     */
    public MainMenu(GeoGraphicX mainWindowReference){
		appletReference = mainWindowReference;

		JMenu fileOperations = new JMenu("Plik");
		JMenu infoTab = new JMenu("Informacje");

		JMenuItem newFile = new JMenuItem(new AbstractAction("Nowy"){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        appletReference.drawableObjects.clear();
                        appletReference.backgroundColor = Color.WHITE;
                        appletReference.repaint();
                    }
                });
		JMenuItem saveFile = new JMenuItem(new AbstractAction("Zapisz plik"){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        Utilities.saveFile(appletReference, appletReference.drawableObjects);
                    }
                });
		JMenuItem openFile;
                openFile = new JMenuItem(new AbstractAction("Wczytaj plik"){
                    
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        try {
                            appletReference.drawableObjects =  Utilities.loadFile(appletReference);
                            appletReference.repaint();
                        } catch (ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(mainWindowReference, "Nie udało sie wczytać pliku");
                        }
                    }
                });

		fileOperations.add(newFile);
		fileOperations.add(saveFile);
		fileOperations.add(openFile);
                
                fileOperations.addSeparator();


                JMenuItem exportPng;
                exportPng = new JMenuItem(new AbstractAction("Ekportuj jako png"){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        if(appletReference.drawableObjects.size() > 0){
                            BufferedImage image = ScreenImage.createImage(appletReference.mainFrameReference.drawSurface);

                            String path = "";

                            JFileChooser chooser = new JFileChooser();
                            int respond = chooser.showSaveDialog(null);

                            if(respond == JFileChooser.APPROVE_OPTION){
                                path = chooser.getSelectedFile()+".png";
                                File filePath = new File(path);

                                try {
                                    ImageIO.write(image, "png", filePath);
                                } catch (IOException ex) {
                                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }  

                        }
                        else{
                            JOptionPane.showMessageDialog(mainWindowReference, "Nic nie zostało narysowane więc nie ma co eksportowac");


                        }
                    }
                });
                
                fileOperations.add(exportPng);
                
                fileOperations.addSeparator();
                
                JMenuItem exitApp = new JMenuItem(new AbstractAction("Wyjdz"){
                    @Override
                    public void actionPerformed(ActionEvent ae){
                        System.exit(0);
                    }
                });
                
                fileOperations.add(exitApp);

		JMenuItem info;
        info = new JMenuItem(new AbstractAction("O programie"){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(mainWindowReference,
                        "GeoGraphicX v1.2 \n" +
                                "Program slużący do rysowania i modyfikowania ksztaltów geometrycznych \n" +
                                "Jeśli znajdziesz jakiś błąd to pamiętaj \"It's not a bug, it's a feature!\" \n" +
                                "Autor: Michał Treter");
                
            }
        });

		infoTab.add(info);
		
		this.add(fileOperations);
		this.add(infoTab);
	}
    
}
