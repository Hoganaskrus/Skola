package mountain;

import java.util.HashMap;

import java.util.Map;


import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {

	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private Map<Side, Point> map;

	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		map = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {

		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		g.penDown();
		fractaltriangle(g, order, a, b, c, dev);

	}

	public void fractaltriangle(TurtleGraphics turtle, int order, Point x, Point y, Point z, double dev) {

		if (order == 0) {
			turtle.moveTo(x.getX(), x.getY());
			turtle.forwardTo(y.getX(), y.getY());
			turtle.forwardTo(z.getX(), z.getY());
			turtle.forwardTo(x.getX(), x.getY());

		} else {
			Side Sxy = new Side(x, y);
			Side Syz = new Side(y, z);
			Side Szx = new Side(z, x);
			Point xy;
			Point yz;
			Point zx;
			if (map.containsKey(Sxy)) {
				xy = map.get(Sxy);
				map.remove(Sxy);
			} else {
				xy = new Point((x.getX() + y.getX())/2, (int) ((x.getY()+y.getY())/2 + RandomUtilities.randFunc(dev)));
				map.put(Sxy, xy);
			}
			if (map.containsKey(Syz)) {
				yz = map.get(Syz);
				map.remove(Syz);
			} else {
				yz = new Point((y.getX() + z.getX())/2, (int) ((y.getY()+z.getY())/2 + RandomUtilities.randFunc(dev)));
				map.put(Syz, yz);
			}
			if (map.containsKey(Szx)) {
				zx = map.get(Szx);
				map.remove(Szx);
			} else {
				zx = new Point((z.getX() + x.getX())/2, (int) ((z.getY()+x.getY())/2 + RandomUtilities.randFunc(dev)));
				map.put(Szx, zx);
			}

			fractaltriangle(turtle, order - 1, x, xy, zx, dev / 2);
			fractaltriangle(turtle, order - 1, xy, y, yz, dev / 2);
			fractaltriangle(turtle, order - 1, zx, yz, z, dev / 2);
			// Det är den sista som fkar
			fractaltriangle(turtle, order - 1, xy, yz, zx, dev / 2);

		}
	}

}
