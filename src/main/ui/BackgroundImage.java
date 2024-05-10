package ui;

import javax.swing.*;
import java.awt.*;

// creates a JPanel with a background image
class BackgroundImage extends JPanel {
    private Image image;

    // EFFECT: sets background image
    public BackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    // EFFECT: allows componetns to be painted over the background image
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}

