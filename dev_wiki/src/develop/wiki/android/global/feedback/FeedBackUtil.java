package develop.wiki.android.global.feedback;

import develop.wiki.android.common.network.mail.Mail;
import develop.wiki.android.common.network.mail.MailSender;
import develop.wiki.android.global.debug.LogUtil;

public class FeedBackUtil {
	private static final String TAG = "FeedBackUtil";
	
	public static void feedbackByMail(Mail mail){
		LogUtil.d(TAG, "feedbackByMail mail");
		MailSender mailSender = new MailSender();
		mailSender.sendMail(mail);
	}
}
