import java.awt.*;
import javax.swing.*;

public class CircleDrawing extends JFrame {
    private int algorithm = 1; 
    private int style = 1;     

    private int radius = 100;
    private int centerX = 250;
    private int centerY = 250;

    public CircleDrawing() {
        setTitle("Circle Drawing Algorithms");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createMenu();
        setVisible(true);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

       
        JMenu algoMenu = new JMenu("Algorithm");
        String[] algoNames = {"DDA", "Bresenham", "Midpoint"};
        for (int i = 0; i < algoNames.length; i++) {
            JMenuItem item = new JMenuItem(algoNames[i]);
            final int selectedAlgo = i + 1;
            item.addActionListener(e -> {
                algorithm = selectedAlgo;
                repaint();
            });
            algoMenu.add(item);
        }

        JMenu styleMenu = new JMenu("Style");
        String[] styleNames = {"Solid", "Dotted", "Dashed"};
        for (int i = 0; i < styleNames.length; i++) {
            JMenuItem item = new JMenuItem(styleNames[i]);
            final int selectedStyle = i + 1;
            item.addActionListener(e -> {
                style = selectedStyle;
                repaint();
            });
            styleMenu.add(item);
        }

        menuBar.add(algoMenu);
        menuBar.add(styleMenu);
        setJMenuBar(menuBar);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g);
    }

    private void drawCircle(Graphics g) {
        switch (algorithm) {
            case 1 -> drawCircleDDA(g);
            case 2 -> drawCircleBresenham(g);
            case 3 -> drawCircleMidpoint(g);
        }
    }

    
    private void drawCircleDDA(Graphics g) {
        for (double theta = 0; theta < 360; theta += 0.5) {
            int x = (int) (radius * Math.cos(Math.toRadians(theta)));
            int y = (int) (radius * Math.sin(Math.toRadians(theta)));
            drawStyledPixel(g, centerX + x, centerY + y, theta);
        }
    }

    private void drawCircleBresenham(Graphics g) {
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;
        int i = 0;

        while (x <= y) {
            drawSymmetricPoints(g, x, y, i++);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    private void drawCircleMidpoint(Graphics g) {
        int x = 0;
        int y = radius;
        int p = 1 - radius;
        int i = 0;

        while (x <= y) {
            drawSymmetricPoints(g, x, y, i++);
            x++;
            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }
        }
    }

    private void drawSymmetricPoints(Graphics g, int x, int y, int step) {
        // Solid: step always passes, Dotted: skip some, Dashed: pattern
        if (shouldDraw(step)) {
            putPixel(g, centerX + x, centerY + y);
            putPixel(g, centerX - x, centerY + y);
            putPixel(g, centerX + x, centerY - y);
            putPixel(g, centerX - x, centerY - y);
            putPixel(g, centerX + y, centerY + x);
            putPixel(g, centerX - y, centerY + x);
            putPixel(g, centerX + y, centerY - x);
            putPixel(g, centerX - y, centerY - x);
        }
    }

    private void drawStyledPixel(Graphics g, int x, int y, double angle) {
        int step = (int)(angle * 10); // simulate step
        if (shouldDraw(step)) {
            putPixel(g, x, y);
        }
    }

    private boolean shouldDraw(int step) {
        return switch (style) {
            case 1 -> true; // Solid
            case 2 -> step % 5 == 0; // Dotted
            case 3 -> (step % 15 < 8); // Dashed
            default -> true;
        };
    }

    private void putPixel(Graphics g, int x, int y) {
        g.fillRect(x, y, 1, 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CircleDrawing::new);
    }
}

