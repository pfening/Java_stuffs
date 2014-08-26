
package test;

import java.util.HashMap;
import java.util.Map;

public class NewMain {
  public static void main(String args[]) {

    String[] names = { "A", "J", "B", "E", "P" };
    String[] ids = { "1", "5", "9", "8", "7" };

    Map<String, String> IDMap = new HashMap<String, String>();

    for (int i = 0; i < names.length; i++)
      IDMap.put(ids[i], names[i]);

    System.out.println(IDMap.size() + " Students entered: ");
    System.out.println(IDMap);

  }
}
