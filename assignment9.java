public class CohenSutherland {
    private static final int INSIDE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    private static int computeOutCode(double x, double y, double xMin, double yMin, double xMax, double yMax) {
        int code = INSIDE;

        if (x < xMin) {
            code |= LEFT;
        } else if (x > xMax) {
            code |= RIGHT;
        }

        if (y < yMin) {
            code |= BOTTOM;
        } else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }

    public static void cohenSutherlandLineClip(double x1, double y1, double x2, double y2, double xMin, double yMin, double xMax, double yMax) {
        int outCode1 = computeOutCode(x1, y1, xMin, yMin, xMax, yMax);
        int outCode2 = computeOutCode(x2, y2, xMin, yMin, xMax, yMax);

        while (true) {
            if ((outCode1 & outCode2) != 0) {
                System.out.println("Line is completely outside the clipping window.");
                return;
            }

            if (outCode1 == 0 && outCode2 == 0) {
                System.out.println("Line is completely inside the clipping window.");
                System.out.println("Clipped line: (" + x1 + ", " + y1 + ") - (" + x2 + ", " + y2 + ")");
                return;
            }

            double x = 0;
            double y = 0;
            int outCode = outCode1 != 0 ? outCode1 : outCode2;

            if ((outCode & TOP) != 0) {
                x = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                y = yMax;
            } else if ((outCode & BOTTOM) != 0) {
                x = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                y = yMin;
            } else if ((outCode & RIGHT) != 0) {
                y = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                x = xMax;
            } else if ((outCode & LEFT) != 0) {
                y = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                x = xMin;
            }

            if (outCode == outCode1) {
                x1 = x;
                y1 = y;
                outCode1 = computeOutCode(x1, y1, xMin, yMin, xMax, yMax);
            } else {
                x2 = x;
                y2 = y;
                outCode2 = computeOutCode(x2, y2, xMin, yMin, xMax, yMax);
            }
        }
    }

    public static void main(String[] args) {
        double x1 = 100;
        double y1 = 100;
        double x2 = 300;
        double y2 = 300;
        double xMin = 150;
        double yMin = 150;
        double xMax = 250;
        double yMax = 250;

        cohenSutherlandLineClip(x1, y1, x2, y2, xMin, yMin, xMax, yMax);
    }
}