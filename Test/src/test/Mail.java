package test;

import java.util.*;
import java.io.*;
import javax.mail.*;

public class Mail
{

    public static void main(String argv[]) {
  
    String user = "pfening@gmail.com";
    String password = "Pfg770922";

    try {
    Properties props = System.getProperties();
    
    //props.setProperty("mail.imaps.host", "imap.gmail.com");
    //props.setProperty("mail.imaps.port", "993");
    //props.setProperty("mail.imaps.connectiontimeout", "5000");
    //props.setProperty("mail.imaps.timeout", "5000");
    //props.setProperty("mail.store.protocol", "imaps");
    
    Session session = Session.getInstance(props, null);
    Store store = null;
    store = session.getStore("imaps");
    store.connect("imap.gmail.com", user, password);
    
    System.out.println(store);
   
   // Open the Folder
   String mbox = "INBOX";
   Folder folder = store.getDefaultFolder();
   if (folder == null) {
    System.out.println("No default folder");
    System.exit(1);
   }

   folder = folder.getFolder(mbox);
   if (!folder.exists()) {
    System.out.println(mbox + "  does not exist");
    System.exit(1);
   }

   if (!(folder instanceof UIDFolder)) {
    System.out.println(
     "This Provider or this folder does not support UIDs");
    System.exit(1);
   }

   UIDFolder ufolder = (UIDFolder)folder;

   folder.open(Folder.READ_WRITE);
   int totalMessages = folder.getMessageCount();

   if (totalMessages == 0) {
    System.out.println("Empty folder");
    folder.close(false);
    store.close();
    System.exit(1);
   }

   // Attributes & Flags for ALL messages ..
   Message[] msgs = ufolder.getMessagesByUID(1, UIDFolder.LASTUID);
   // Use a suitable FetchProfile
   FetchProfile fp = new FetchProfile();
   fp.add(FetchProfile.Item.ENVELOPE);
   fp.add(FetchProfile.Item.FLAGS);
   folder.fetch(msgs, fp);

   for (int i = 0; i < msgs.length; i++) {
    System.out.println("--------------------------");
    System.out.println("MESSAGE UID #" + 
      ufolder.getUID(msgs[i]) + ":");
    dumpPart(msgs[i]);
   }

   folder.close(false);
   store.close();
  } catch (Exception ex) {
   System.out.println("Oops, got exception! " + ex.getMessage());
   ex.printStackTrace();
   System.exit(1);
  }
 }

    public static void dumpPart(Part p) throws Exception 
 {
  if (p instanceof Message) { 
   dumpEnvelope((Message)p);
  }
  System.out.println("CONTENT-TYPE: " + p.getContentType());

  Object o = p.getContent();
  if (o instanceof String) {
   System.out.println("This is a String");
   System.out.println("---------------------------");
   System.out.println((String)o);
  } else if (o instanceof Multipart) {
   System.out.println("This is a Multipart");
   System.out.println("---------------------------");
   Multipart mp = (Multipart)o;
   int count = mp.getCount();
   for (int i = 0; i < count; i++)
    dumpPart(mp.getBodyPart(i));
  } else if (o instanceof Message) {
   System.out.println("This is a Nested Message");
   System.out.println("---------------------------");
   dumpPart((Part)o);
  } else if (o instanceof InputStream) {
   InputStream is = (InputStream)o;
   int c;
   while ((c = is.read()) != -1) {
    System.out.write(c);
   }
  }

 }

    public static void dumpEnvelope(Message m) throws Exception 
 {
  System.out.println("This is the message envelope");
  System.out.println("---------------------------");
  Address[] a;
  // FROM 
  if ((a = m.getFrom()) != null) {
   for (int j = 0; j < a.length; j++)
    System.out.println("FROM: " + a[j].toString());
  }

  // TO
  if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
   for (int j = 0; j < a.length; j++)
    System.out.println("TO: " + a[j].toString());
  }

  // SUBJECT
  System.out.println("SUBJECT: " + m.getSubject());

  // DATE
  Date d = m.getSentDate();
  System.out.println("SendDate: " +
     (d != null ? d.toString() : "UNKNOWN"));

  // SIZE
  System.out.println("Size: " + m.getSize());
 }
}
