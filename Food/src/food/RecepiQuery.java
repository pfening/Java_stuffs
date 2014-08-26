package food;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecepiQuery {
    private static double prcgram;
    private static double price=0;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
                
        String sql = "SELECT * FROM recepie";
        ResultSet res = selectdb.read(sql);

        while(res.next())
        {
            String name = res.getString("name");
            String des = res.getString("description");
            String recept = res.getString("ingredients");        
            System.out.println(name+" "+des);
            String ing[]=recept.split(";");
            for (String item : ing) {
            System.out.println(item.split(":")[0] + " " +item.split(":")[1]+"g");
            MaterialQuery(item.split(":")[0],item.split(":")[1]);
            }
            System.out.println("This food cost "+price+" czk");
        }
}
    
    private static void MaterialQuery(String material, String requanty) throws SQLException, ClassNotFoundException {

        int req=Integer.parseInt(requanty);
        
        String sql = "SELECT * FROM material WHERE name='"+material+"'";
        ResultSet m = selectdb.read(sql);

        while(m.next()){
            String name = m.getString("name");
            double quant = m.getInt("quantity");
            String uni = m.getString("unit");
            int prc = m.getInt("price");
            double prcgram=(prc/quant)*req;
        //System.out.println(name+" "+quant+" "+uni+" "+prcgram);
        price = price + prcgram;
        }
    }
    
}
