package test;

import java.io.IOException;
import java.util.*;
import javax.mail.*;

public class Mail2 {

    public static void main(String argv[]) throws MessagingException, IOException {
  
    String user = "pfening@gmail.com";
    String password = "Pfg770922";

    Properties props = System.getProperties();
    
    Session session = Session.getInstance(props, null);
    Store store = session.getStore("imaps");
    store.connect("imap.gmail.com", user, password);
   
    Folder inbox = store.getFolder("inbox");
   
        inbox.open(Folder.READ_WRITE); 
        int messageCount = inbox.getMessageCount();
        System.out.println("Total Messages " + messageCount);
        
        int startMessage = messageCount - 3;
        int endMessage = messageCount;
 
        if (messageCount < 5) {
            startMessage = 0;
        }
 
        Message[] messages = inbox.getMessages(startMessage, endMessage);
 
        for (Message message : messages) {
 
            boolean isMessageRead = true;
 
            for (Flags.Flag flag : message.getFlags().getSystemFlags()) {
                if (flag == Flags.Flag.SEEN) {
                    isMessageRead = true;
                    break;
                }
            }
 
            message.setFlag(Flags.Flag.SEEN, true);
            System.out.println(message.getSubject() + " "+ (isMessageRead ? " [READ]" : " [UNREAD]"));

            if (message.isMimeType("text/plain")) { 
                System.out.println(message.getContent());
            }
            
            if (message.isMimeType("multipart/*")) { 
                Multipart mp = (Multipart) message.getContent();
                for (int i = 0; i < mp.getCount(); i++) {
                String s = (String) mp.getBodyPart(i).getContent();
                if (s != null)
                    System.out.println(s);
            }
            }

        }
 
        inbox.close(true);
        System.out.println("Done....");
        store.close();
 }
}
