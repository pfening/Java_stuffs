/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lotus;

import lotus.domino.* ;

public class NABTest 
{
 static String host = "D06ML461/06/M/IBM" ; // server address
 static String cName = "gabor_pfening@cz.ibm.com" ; //username
 static String dbname = "names.nsf"; // database (e.g. mail\\hnagashi.nsf)
 static String password = "Olu$ka66" ;  // internet password

  public static void main(String args[]){
     try{
   Session s = NotesFactory.createSession(host,cName,password) ;
   Database db = s.getDatabase("", dbname );
 if(!db.isOpen()){
  db.open();
 }
   String title = db.getTitle();
   String uname = s.getUserName();
   
   System.out.println("UserName： " + uname );
   System.out.println("ServerName： " + db.getServer());
   System.out.println("Title： " + title);

        }catch(NotesException ne){
   System.out.println(ne.id + ne.text);
 }catch (Exception e){}
  }
}