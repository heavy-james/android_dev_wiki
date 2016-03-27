package develop.wiki.android.common.network.mail;

import java.util.Properties;

import android.text.TextUtils;

public class Mail {
	private String userName;
	private String passWord;
	private String serverHost;
	private String serverPort;
	private String fromAddress;
	private String toAddress;
	private String subject;
	private String content;
	private String[] attachFileNames;
	
	
	public void setUserInfo(String name,String password){
		userName = name;
		passWord = password;
	}
	
	public void setServer(String hostName,String port){
		serverHost = hostName;
		serverPort = port;
	}
	
	public void setMailInfo(String from,String to,String subject,String content,String[] fileNames){
		fromAddress = from;
		toAddress = to;
		this.subject = subject;
		this.content = content;
		attachFileNames = fileNames;
	}
	
	public boolean isValide(){
		boolean result = TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord);
		result |= TextUtils.isEmpty(serverHost) || TextUtils.isEmpty(serverPort);
		result |= TextUtils.isEmpty(fromAddress) || TextUtils.isEmpty(toAddress);
		return !result;
	}

	public Properties toProperties(){
		Properties properties = new Properties();
		properties.put("UserName", userName);
		properties.put("PassWord", passWord);
		properties.put("ServerHost", serverHost);
		properties.put("ServerPort", serverPort);
		properties.put("fromAddress", fromAddress);
		properties.put("toAddress", toAddress);
		properties.put("subject", subject);
		properties.put("content", content);
		return properties;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getServerHost() {
		return serverHost;
	}

	public String getServerPort() {
		return serverPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
	
	
}
