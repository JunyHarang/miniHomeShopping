package shopping.common.model ;

import java.util.HashMap;
import java.util.Map;

public class MyCartList {
	private Map<Integer, Integer> orderlists = null ;
	
	public MyCartList() {
		this.orderlists = new HashMap<Integer, Integer>();
	}
	
	public void RemoveAllProductInfo(){
		this.orderlists.clear(); 
	}
	
	public Map<Integer, Integer> GetAllOrderList(){
		return this.orderlists ;
	}
	
	public void EditOrder( int pmkey, int stock ){
		this.AddOrder(pmkey, stock); 
	}
	
	public void  DeleteOrder( int pmkey ){
		this.orderlists.remove(pmkey) ;  
	}
	
	public void AddOrder(  int pmkey, int stock ){
		if ( this.orderlists.containsKey( pmkey) ) {
			orderlists.put(pmkey, orderlists.get(pmkey)  + stock ) ;
		} else {
			orderlists.put(pmkey, stock ) ;
		}
	}
}