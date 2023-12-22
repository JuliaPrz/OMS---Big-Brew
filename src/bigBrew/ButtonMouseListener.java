package bigBrew;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

public class ButtonMouseListener implements MouseListener {
    
    private final JButton button;

    public ButtonMouseListener(JButton button) {
        this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse clicked event 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse pressed event
        
        List<String> buttonTexts = Arrays.asList("Order", "Menu", "History", "Log Out", "Log In"); // Create a list containing the button texts
        boolean sidePanelButton = buttonTexts.contains(button.getText()); // return true the button contain the texts
        
        if (sidePanelButton){
            button.setBackground(new Color(20, 20, 20));
            button.setOpaque(true);
        }
        else{
            button.setBackground(new Color(187, 111, 26));
            button.setOpaque(true);
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse released event
        List<String> buttonTexts = Arrays.asList("Order", "Menu", "History", "Log Out", "Log In"); // Create a list containing the button texts
        boolean sidePanelButton = buttonTexts.contains(button.getText()); // return true the button contain the texts
        
        if (sidePanelButton){
            button.setBackground(new Color(187, 111, 26));
            button.setOpaque(true);
        } else {
            button.setBackground(new Color(20, 20, 20));
            button.setOpaque(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
        List<String> buttonTexts = Arrays.asList("Order", "Menu", "History", "Log Out", "Log In"); // Create a list containing the button texts
        boolean sidePanelButton = buttonTexts.contains(button.getText()); // return true the button contain the texts
        
      if (sidePanelButton){
            button.setBackground(new Color(187, 111, 26));
            button.setOpaque(true);
        } else {
            button.setBackground(new Color(20, 20, 20));
            button.setOpaque(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exited event
        
        List<String> buttonTexts = Arrays.asList("Order", "Menu", "History", "Log Out", "Log In"); // Create a list containing the button texts
        boolean sidePanelButton = buttonTexts.contains(button.getText()); // return true the button contain the texts
        
        if (sidePanelButton){
             button.setBackground(new Color(38, 38, 38));
             button.setOpaque(true);
        } else {
            button.setBackground(new Color(187, 111, 26));
            button.setOpaque(true);
        }
    } 
}
