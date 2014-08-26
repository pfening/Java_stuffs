/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lotus;

import lotus.domino.*;
public class MyClass
{
    public static void main(String argv[])    
    {    
       try    
           {        
               NotesThread.sinitThread(); // start thread            
               Session s = NotesFactory.createSession();            
               // Operational code goes here
           }        
               catch(Exception e)
           {        
               e.printStackTrace();
           }        
               finally
           {        
               NotesThread.stermThread(); // must terminate every thread
           }
        }    
    }