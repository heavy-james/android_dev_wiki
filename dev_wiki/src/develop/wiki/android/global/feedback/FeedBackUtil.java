package develop.wiki.android.global.feedback;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

import develop.wiki.android.common.network.mail.Mail;
import develop.wiki.android.global.debug.LogUtil;

public class FeedBackUtil {
	private static final String TAG = "FeedBackUtil";
	private static boolean isMailling = false;
	
	public static void feedbackByMail(){
		if(isMailling){
			return;
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isMailling = true;
				Thread.currentThread().setContextClassLoader(getClass().getClassLoader() );
				MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
				mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
				mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
				mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
				mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
				mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822"); 
				LogUtil.d(TAG, "feedbackByMail create mail");
				String smtp = "smtp.126.com";  
				String from = "zhanhaifei@126.com";  
				String to = "418349142@qq.com";  
				String copyto = "";  
				String subject = "crash log";  
				String content = "this is a crash log";  
				String username="zhanhaifei@126.com";  
				String password="ZHan~2533517";  
				String[] attachedFiles = new String[]{LogUtil.getLogFileName(), LogUtil.getANRLogFile()};
				Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, attachedFiles);
				LogUtil.d(TAG, "feedbackByMail send mail finished");
				LogUtil.releaseSource();
				isMailling = false;
				System.exit(0);
			}
		}).start();
	}
}
