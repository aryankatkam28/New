import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PolygonFill extends JPanel implements MouseListener {
    int[][] polygon = {
        {100, 150}, {200, 250}, {300, 200}, {250, 100}, {150, 50}
    };

    Color fillColor = Color.RED;
    BufferedImage canvas;

    public PolygonFill() {
        this.addMouseListener(this);
        canvas = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        drawPolygon();
    }

    // Draw the polygon outline
    void drawPolygon() {
        Graphics2D g = canvas.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setColor(Color.WHITE);
        Polygon p = new Polygon();
        for (int[] point : polygon) {
            p.addPoint(point[0], point[1]);
        }
        g.drawPolygon(p);
        repaint();
    }

    // Flood fill algorithm (recursive)
    public void floodFill(int x, int y, Color oldColor, Color newColor) {
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight())
            return;

        if (canvas.getRGB(x, y) != oldColor.getRGB())
            return;

        canvas.setRGB(x, y, newColor.getRGB());

        floodFill(x + 1, y, oldColor, newColor);
        floodFill(x - 1, y, oldColor, newColor);
        floodFill(x, y + 1, oldColor, newColor);
        floodFill(x, y - 1, oldColor, newColor);
    }

    // Seed fill algorithm (non-recursive, using stack)
    public void seedFill(int x, int y, Color oldColor, Color newColor) {
        java.util.Stack<Point> stack = new java.util.Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int px = p.x, py = p.y;

            if (px < 0 || px >= canvas.getWidth() || py < 0 || py >= canvas.getHeight())
                continue;

            if (canvas.getRGB(px, py) == oldColor.getRGB()) {
                canvas.setRGB(px, py, newColor.getRGB());
                stack.push(new Point(px + 1, py));
                stack.push(new Point(px - 1, py));
                stack.push(new Point(px, py + 1));
                stack.push(new Point(px, py - 1));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }

    // Mouse click event – triggers fill
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Color targetColor = new Color(canvas.getRGB(x, y));

        String[] options = {"Flood Fill", "Seed Fill", "Cancel"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose fill method:", "Fill Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 0)
            floodFill(x, y, targetColor, fillColor);
        else if (choice == 1)
            seedFill(x, y, targetColor, fillColor);

        repaint();
    }

    // Unused mouse events
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // Main function
    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Fill (Flood & Seed)");
        PolygonFill panel = new PolygonFill();
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}