import javax.swing.*;
import java.awt.* ;
import java.awt.event.* ;
/**
 * Klasa zawierająca pasek operacji.
 * @author Michał Treter
 */
public class OperationBar extends JPanel implements ActionListener{

    /**
     * Odwołanie do głównego apletu.
     */
    public GeoGraphicX appletReference;

    /**
     * Przycisk dodawania prostokąta.
     */
    public JButton addRect;

    /**
     * Przycisk dodawania okręgu.
     */
    public JButton addCircle;

    /**
     * Przycisk dodawania wielokątu.
     */
    public JButton addPoly;

    /**
     * Przycisk aktywujący akcję zmiany rozmiaru figury.
     */
    public JButton resizeAction;

    /**
     * Przycisk aktywujący akcje zmiany koloru figury.
     */
    public JButton colorChangeAction;

    /**
     * Przycisk aktywujący akcję usuwania figury.
     */
    public JButton deleteAction;

    /**
     * Przycisk aktywujący akcję zmiany koloru tła.
     */
    public JButton changeBackgroundAction;

    /**
     * Kontruktor klasy w, którym zostaję stworzony każdy z przycisków
     * następnie każdemu z nich zostaje przypisana akcja. Na koniec
     * przyciski zostają dodane do jednego panelu.
     * @param mainWindowReference Odwołanie do głównego apletu.
     */
    public OperationBar(GeoGraphicX mainWindowReference){
		appletReference = mainWindowReference;

		setOpaque(true);
		setBackground(Color.BLUE);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		addRect = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/add_rect.png")));
                addRect.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isDrawingSquare = true;
                        
                        addRect.setEnabled(false);
                    }
                });
                
		addCircle = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/add_circle.png")));
                addCircle.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isDrawingCircle = true;
                        
                        addCircle.setEnabled(false);
                    }
                });
                
		addPoly = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/add_poly.png")));
                addPoly.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isDrawingPoly = true;
                        
                        addPoly.setEnabled(false);
                    }
                });
                
		resizeAction = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/resize.png")));
                resizeAction.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isResizingFigure = true;
                        
                        resizeAction.setEnabled(false);
                    }
                });
                
		colorChangeAction = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/color_pick.png")));
                colorChangeAction.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isChangingColor = true;
                        
                        colorChangeAction.setEnabled(false);
                    }
                });
                
		deleteAction = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/delete.png")));
                deleteAction.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.isBusy = true;
                        appletReference.isDeleting = true;
                        
                        deleteAction.setEnabled(false);
                    }
                });
                
                changeBackgroundAction = new JButton(new ImageIcon(getClass().getClassLoader().getResource("Assets/change_background.png")));
                changeBackgroundAction.addActionListener((ActionEvent e) -> {
                    if(!appletReference.isBusy){
                        appletReference.backgroundColor = JColorChooser.showDialog(null, "Zmien Kolor Tla", Color.WHITE);
                        appletReference.repaint();
                    }
                });

		this.add(addRect);
		this.add(addCircle);
		this.add(addPoly);
		this.add(resizeAction);
		this.add(colorChangeAction);
		this.add(deleteAction);
                this.add(changeBackgroundAction);

	}

        @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

        @Override
	public void actionPerformed(ActionEvent e){

	}
}
