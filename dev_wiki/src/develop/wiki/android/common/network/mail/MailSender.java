package develop.wiki.android.common.network.mail;

import java.util.Date;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import develop.wiki.android.global.debug.LogUtil;

public class MailSender {
	
	private static final String TAG = "MailSender";
	public boolean sendMail(final Mail mail){
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				LogUtil.d(TAG, "MailSender run");
				if(!mail.isValide()){
					return;
				}
				Authenticator ath = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						PasswordAuthentication passwordauth = new PasswordAuthentication(mail.getUserName(), mail.getPassWord());
						return passwordauth;
					}
					
				};
				Session sendMailSession = Session.getDefaultInstance(mail.toProperties(), ath);
				try {
					Message mailMessage = new MimeMessage(sendMailSession);
					Address fromAddress = new InternetAddress(mail.getFromAddress());
					Address toAddress = new InternetAddress(mail.getToAddress());
					mailMessage.setFrom(fromAddress);
					mailMessage.setRecipient(Message.RecipientType.TO, toAddress);
					mailMessage.setSubject(mail.getSubject());
					mailMessage.setText(mail.getContent());
					mailMessage.setSentDate(new Date());
					Transport.send(mailMessage);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// TODO: handle finally clause
				}
			}

		}).start();
		return true;
	}

}
