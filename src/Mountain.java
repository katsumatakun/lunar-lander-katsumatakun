import java.awt.*;

public class Mountain {

        private Point p1;
        private Point p2;
        private Point p3;
        private int[] xPoints;
        private int[] yPoints;

        public Mountain(Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            xPoints = new int[3];
            yPoints = new int[3];
            xPoints[0] = (int) p1.getX();
            xPoints[1] = (int) p2.getX();
            xPoints[2] = (int) p3.getX();
            yPoints[0] = (int) p1.getY();
            yPoints[1] = (int) p2.getY();
            yPoints[2] = (int) p3.getY();

        }

        public boolean intersect(Rectangle r){
            boolean result = false;
            double xRange[] = getXRange(r.y+r.height);
            //System.out.println(xRange[0] + " " + xRange[1]);
            double x1 =xRange[0];
            double x2 =xRange[1];
            if(r.y+r.height > p2.y){
                if((x1 < r.x && x2 > r.x) || (x1 < r.x+r.width && x2 > r.x+r.width)){
                    result = true;
                }
            }

            return result;
        }

        private double[] getXRange(double y){
            double a1 = (p1.y-p2.y)/(p1.x-p2.x);
            double a2 = (p2.y-p3.y)/(p2.x-p3.x);
           // System.out.println(a1 + " " + a2);
            double x1 = (y-p2.y+p2.x*a1)/a1;
            double x2 = (y-p2.y+p2.x*a2)/a2;
            double range[] = {x1, x2};
            return range;
        }
        public void paint(Graphics g){

            g.setColor(Color.cyan);
            g.fillPolygon(xPoints,yPoints,3);
        }

}
