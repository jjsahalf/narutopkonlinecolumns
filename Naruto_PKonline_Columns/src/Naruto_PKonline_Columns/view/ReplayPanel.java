package Naruto_PKonline_Columns.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tetris.controller.ControllerForReplay;
import tetris.entities.GroundForReplay;
import tetris.view.GamePanelForReplay;
import tetris.entities.ShapeFactory;

/**
 *
 * @author SHY
 */
public class ReplayPanel extends JPanel {

    private ShapeFactory shapeFactory = new ShapeFactory();
    private GroundForReplay ground = new GroundForReplay();
    private GamePanelForReplay gamePanel = new GamePanelForReplay();
    private ControllerForReplay controller = new ControllerForReplay(shapeFactory, ground, gamePanel);
    Thread thread = new Thread(new PaintThread());

    public ReplayPanel() {
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolKit.getScreenSize();
        this.setSize(dimension);
        this.setOpaque(false);
        this.setLayout(null);
        gamePanel.setBounds(200, 20, gamePanel.getWidth() + 10, gamePanel.getHeight() + 30);
        this.add(gamePanel);
        try {
            controller.newGame();
        } catch (IOException ex) {
            Logger.getLogger(ReplayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ReplayPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.add(new ReplayPanel());
        frame.setVisible(true);
    }
}
