package chat;

public class XmppTest {
	
	public static void main(String[] args) throws Exception {
		
		String username = "pfening@gmail.com";
		String password = "Ale%ander12";
		
		XmppManager xmppManager = new XmppManager("talk.google.com", 5222);
		xmppManager.TLSEnabled = true;
		xmppManager.init();
		xmppManager.performLogin(username, password);
		xmppManager.setStatus(true, "Hello everyone");
		
		String buddyJID = "brunner000@gmail.com";
		String buddyName = "brunner000";
		xmppManager.createEntry(buddyJID, buddyName);
		
		xmppManager.sendMessage("Hello", "brunner000@gmail.com");
		
		xmppManager.printRoster();
		
		boolean isRunning = true;
		
		while (isRunning) {
			Thread.sleep(50);
		}
		
		xmppManager.destroy();
		
	}

}