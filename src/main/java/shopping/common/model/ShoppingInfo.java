package shopping.common.model ;

public class ShoppingInfo {
	private int pnum ;
	private String pname;
	private int qty ;
	private int price ;
	private String image;
	private int point ;
	//private int amount ;
	
	//getter, setter, toString �޼ҵ� ����
		
	public int getPnum() {
		return pnum;
	}
	@Override
	public String toString() {
		return "ShoppingInfo [pnum=" + pnum + ", pname=" + pname + ", qty=" + qty + ", price=" + price + ", image="
				+ image + ", point=" + point + "]";
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}