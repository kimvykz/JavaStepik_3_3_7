import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program extends JPanel implements KeyListener {
    private BufferedImage image;
    private int x = 0, y = 0, width = 100, height = 100;
    private final int moveAmount = 50;
    private final int moveAmountShift = 100;


    public Program(){
        try {
            image = ImageIO.read(new File("artyom.jpg"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);
        addKeyListener(this);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, x, y, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (x <= 0) {
                    x = this.getWidth() - width;
                }
                else {
                    x -= (e.isShiftDown() ? moveAmountShift : moveAmount);
                }
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                if (x >= this.getWidth() - (e.isShiftDown()? moveAmountShift : moveAmount)) {
                    x = 0;
                }
                else {
                    x += (e.isShiftDown()? moveAmountShift : moveAmount);
                }
                repaint();
                break;
            case KeyEvent.VK_UP:
                if (y <= 0) {
                    y = this.getHeight() - height;
                }
                else {
                    y -= (e.isShiftDown() ? moveAmountShift : moveAmount);
                }
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                if (y >= this.getHeight() - (e.isShiftDown()? moveAmountShift : moveAmount)){
                    y = 0;
                }
                else {
                    y += (e.isShiftDown()? moveAmountShift : moveAmount);
                }
                repaint();
                break;
            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Object");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Program());
        frame.pack();
        frame.setVisible(true);
    }
}
