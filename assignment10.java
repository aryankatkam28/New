import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Transformations extends JPanel {
    private double translateX = 0;
    private double translateY = 0;
    private double rotationAngle = 0;
    private double shearX = 0;
    private double shearY = 0;
    private double scaleX = 1;
    private double scaleY = 1;

    public Transformations() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Create a rectangle
        Rectangle2D.Double rect = new Rectangle2D.Double(100, 100, 100, 50);

        // Apply transformations
        AffineTransform transform = new AffineTransform();
        transform.translate(translateX, translateY);
        transform.rotate(rotationAngle);
        transform.shear(shearX, shearY);
        transform.scale(scaleX, scaleY);

        // Draw the transformed rectangle
        Shape transformedRect = transform.createTransformedShape(rect);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw original rectangle
        Rectangle2D.Double originalRect = new Rectangle2D.Double(100, 100, 100, 50);
        g2d.setColor(Color.BLACK);
        g2d.draw(originalRect);

        // Apply transformations
        AffineTransform transform = new AffineTransform();
        transform.translate(translateX, translateY);
        transform.rotate(rotationAngle);
        transform.shear(shearX, shearY);
        transform.scale(scaleX, scaleY);

        // Draw transformed rectangle
        Shape transformedRect = transform.createTransformedShape(originalRect);
        g2d.setColor(Color.RED);
        g2d.draw(transformedRect);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Transformations");
        Transformations transformations = new Transformations();

        // Apply transformations
        transformations.translateX = 100;
        transformations.translateY = 100;
        transformations.rotationAngle = Math.PI / 4;
        transformations.shearX = 0.5;
        transformations.shearY = 0;
        transformations.scaleX = 1.5;
        transformations.scaleY = 1.5;

        frame.add(transformations);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}