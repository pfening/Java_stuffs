package itim;

import com.ibm.itim.apps.ApplicationException;
import com.ibm.itim.apps.InitialPlatformContext;
import com.ibm.itim.apps.PlatformContext;
import com.ibm.itim.apps.jaas.callback.PlatformCallbackHandler;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Test2 {
    
public static void main(String[] args) {

					
	String platformContextFactory = null;
	String ejbUser = null;
	String appServerURL = null;
	String ejbPwd = null;
	try {
	/*'context.properties' is the name of the properties file you created with 
	all your security information. You only need to pass the name of the 
	file, the file extension is not required.*/		
		ResourceBundle rb = ResourceBundle.getBundle("itim.context");		
		appServerURL = rb.getString("appServerURL");
		ejbUser = rb.getString("ejbUser");
		platformContextFactory = rb.getString("platformContextFactory");
		ejbPwd = rb.getString("ejbPwd");
		System.out.println("Will connect to TIM server at url: " + appServerURL);
		System.out.println("Will connect to TIM with user: " + ejbUser);
		System.out.println("Will connect to TIM with password: " + ejbPwd);		
	}
	catch ( MissingResourceException e ) {
		String statusValue = "Can't find the context because of a MissingResourceException : " + e;
		e.printStackTrace();
						
	}

	Hashtable env = new Hashtable();
	env.put(InitialPlatformContext.CONTEXT_FACTORY, platformContextFactory);
	env.put(PlatformContext.PLATFORM_URL, appServerURL);
	env.put(PlatformContext.PLATFORM_PRINCIPAL, ejbUser);
	env.put(PlatformContext.PLATFORM_CREDENTIALS, ejbPwd);
	PlatformContext platform = null;
		
		
	try {
		platform = new InitialPlatformContext(env);
	}
	catch ( RemoteException e ) {
		String statusValue = "Can't create a new platform because of a RemoteException : " + e;
	}
	catch ( ApplicationException e ) {
		String statusValue = "Can't create a new platform because of a ApplicationException : " + e;
	}
}
        
        public Subject getSubject(PlatformContext platform, String user, String password){
	Subject subject = null;
	try {
		PlatformCallbackHandler handler = new PlatformCallbackHandler(user,password);
		handler.setPlatformContext(platform);
		/*ITIM is the configuration set in the JAAS setting in WAS
		Please refer to the article entitled 
		"Integrating Portal with ITIM" for additional 
		configuration instructions if you use WAS as the 
		Application Server*/
		LoginContext lc = new LoginContext("ITIM",handler);
		lc.login();
		subject = lc.getSubject();
	}
	
	catch(LoginException e){
	}

	return subject;
}

    
}


