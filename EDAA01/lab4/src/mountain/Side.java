package mountain;

public class Side {

	private Point a;
	private Point b;

	public Side(Point a, Point b) {
		this.a = a;
		this.b = b;

	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	
	/*
	 Returna false om Object != Side. Kolla om this Side är samma som Side param. genom att om S1 = ((3,3), (4,4)) eller ((4,4), (3,3)).
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			if((a.getX() == ((Side) obj).getA().getX() && b.getX() == ((Side) obj).getB().getX()) && (a.getY() == ((Side) obj).getA().getY() && b.getY() == ((Side) obj).getB().getY())){
				return true;
			}
			if(((a.getX() == ((Side) obj).getB().getX() && b.getX() == ((Side) obj).getA().getX())) && ((a.getY() == ((Side) obj).getB().getY() && b.getY() == ((Side) obj).getA().getY()))){
				return true;
			}
			return false;
		}
		return false;

	}

	@Override
	public int hashCode() {

		return a.hashCode() + b.hashCode();
	}

}
