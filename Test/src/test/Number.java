package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class Number {
	public static void main(String[] args) {
		try {
 
			Map mMap = new HashMap();
			mMap.put("PostgreSQL", 1);
			mMap.put("DB2", 2);
			mMap.put("Oracle", 3);
			mMap.put("MySQL", "egy:1,ketto:2,harom:3");
 
			//Iterator iter = mMap.entrySet().iterator();
 
			//while (iter.hasNext()) {
			//	Map.Entry mEntry = (Map.Entry) iter.next();
			//	System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
			//}

			System.out.println("Value : " + mMap.get("MySQL"));
 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
