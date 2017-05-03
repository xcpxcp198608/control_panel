package com.wiatec.control_panel.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailMaster {
	
//	private String host = "mail.wiatec.com";
//	private String sendAddress = "patrickxu@wiatec.com";
//	private String userName = "patrickxu@wiatec.com";
//	private String password ="83L6jJ";
	
//	private String SSL_FACTORY="javax.net.ssl.SSLSocketFactory";  
//	private String sendAddress = "ld_reg@foxmail.com";
//	private String userName = "ld_reg@foxmail.com";
//	private String password ="xbfixpknxoeqdbbe";
	
	private String sendAddress = "bactivation@legacy.direct";
	private String userName = "bactivation@legacy.direct";
	private String password ="Wangwang123#";
	
	private String emailSubject = "WELCOME TO BTVi3";
	private String emailContent = "";
	
	public boolean send (String receiverAddress){
		Properties properties;
		Session session;
		MimeMessage message;
		Transport transport = null;
		
		if(receiverAddress == null || receiverAddress.equals("")){
			System.out.println("receiverAddress error");
			return false;
		}
		properties = new Properties();
		
//		properties.put("mail.smtp.host", host);
//		properties.put("mail.smtp.auth", "true");
		
//		properties.setProperty("mail.smtp.host","smtp.qq.com") ;  
//		properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);  
//		properties.setProperty("mail.smtp.socketFactory.fallback", "false") ;  
//		properties.setProperty("mail.smtp.port","465") ;  
//		properties.put("mail.smtp.starttls.enable","true");  
//		properties.setProperty("mail.smtp.socketFactory.port","465") ;  
//		properties.setProperty("mail.smtp.auth","true") ;   
		
		properties.setProperty("mail.smtp.host","smtp.office365.com") ;  
		properties.setProperty("mail.smtp.port","587") ;  
		properties.put("mail.smtp.starttls.enable","true");  
		properties.setProperty("mail.smtp.socketFactory.port","587") ;  
		properties.setProperty("mail.smtp.auth","true") ;   
		
		session = Session.getDefaultInstance(properties, null);
		message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sendAddress));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverAddress));
			message.setSubject(emailSubject);
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(emailContent,"text/html");
			multipart.addBodyPart(mimeBodyPart);
			message.setContent(multipart);
			message.setSentDate(new Date());
			transport = session.getTransport("smtp");
			transport.connect((String)properties.get("mail.smtp.host"), userName ,password);
			transport.sendMessage(message , message.getRecipients(MimeMessage.RecipientType.TO));
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(transport != null){
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setEmailContent(String userName ,String token,String language){
		StringBuilder stringBuilder = new StringBuilder();
		if("English".equals(language)){
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 by Legacy Direct"+"<br/>");
			stringBuilder.append("Thank you for registering your BTVi3."+"<br/>");
			stringBuilder.append("To finish your sign up process, please click on the button below to activate your account"+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>ACTIVATE MY ACCOUNT</a>"+"<br/><br/>");
			stringBuilder.append("If you have any questions or if you’re encountering problems, our support team at support@legacy.direct is happy to assist you. Alternatively you can visit our Support Desk."+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else if("Spanish".equals(language)){
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 by Legacy Direct"+"<br/>");
			stringBuilder.append("Thank you for registering your BTVi3."+"<br/>");
			stringBuilder.append("To finish your sign up process, please click on the button below to activate your account"+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>ACTIVATE MY ACCOUNT</a>"+"<br/><br/>");
			stringBuilder.append("If you have any questions or if you’re encountering problems, our support team at support@legacy.direct is happy to assist you. Alternatively you can visit our Support Desk."+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else if("Chinese".equals(language)){
			stringBuilder.append("亲爱的 "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 by Legacy Direct"+"<br/>");
			stringBuilder.append("感谢您的注册"+"<br/>");
			stringBuilder.append("请点击下方的激活按钮完成您的注册"+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>激活</a>"+"<br/><br/>");
			stringBuilder.append("如果您有任何问题，请联系 support@legacy.direct 我们会很高兴为您服务"+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else if("French".equals(language)){
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 par Legacy Direct"+"<br/>");
			stringBuilder.append("Nous vous remercions de votre enregistrement sur BTVi3."+"<br/>");
			stringBuilder.append("Pour terminer votre inscription,  veuillez activer votre compte en cliquant sur le bouton ci-dessous:"+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>ACTIVER MON COMPTE</a>"+"<br/><br/>");
			stringBuilder.append("Si vous avez des questions ou vous rencontrez des problèmes, veuillez contacter notre équipe d'assistance qui est heureuse de vous aider : support@legacy.direct " +
					"Vous pouvez également visiter notre Centre d’assistance (Support Desk)."+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else if("Italian".equals(language)){
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 by Legacy Direct"+"<br/>");
			stringBuilder.append("Grazie per aver registrato il Vostro prodotto BTVi3."+"<br/>");
			stringBuilder.append("Per completare il processo di attivazione, per cortesia cliccate sul link di seguito per finalizzare l'attivazione del Vostro account."+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>ACTIVATE MY ACCOUNT</a>"+"<br/><br/>");
			stringBuilder.append("Per qualsiasi altra domanda o se avete qualsiasi dubbio e/o problema, il nostro team di supporto sará felice di fornirvi qualsiasi risposta attraverso la mail  support@legacy.direct " +
					"In alternativa potete visitare il nostro Support Desk."+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else if("German".equals(language)){
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 von Legacy Direct"+"<br/>");
			stringBuilder.append("Vielen Dank für die Registrierung Ihres BTVi3-Gerätes."+"<br/>");
			stringBuilder.append("Um das Anmelden zu beenden, klicken Sie bitte auf die Taste unten, um Ihr Konto zu aktivieren."+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>KTIVIEREN SIE MEIN KONTO</a>"+"<br/><br/>");
			stringBuilder.append("Wenn Sie Fragen oder Anregungen haben, steht Ihnen unser Support-Team unter support@legacy.direct gerne zur Verfügung. Alternativ können Sie unser Support Desk besuchen. "+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}else{
			stringBuilder.append("Dear "+userName+":<br/>");
			stringBuilder.append("<br/>");
			stringBuilder.append("BTVi3 by Legacy Direct"+"<br/>");
			stringBuilder.append("Thank you for registering your BTVi3."+"<br/>");
			stringBuilder.append("To finish your sign up process, please click on the button below to activate your account"+"<br/><br/>");
			stringBuilder.append("<a href='http://appota.gobeyondtv.co:8080/control_panel/user/confirmEmail?userInfo.token="+token+"'>ACTIVATE MY ACCOUNT</a>"+"<br/><br/>");
			stringBuilder.append("If you have any questions or if you’re encountering problems, our support team at support@legacy.direct is happy to assist you. Alternatively you can visit our Support Desk."+"<br/><br/>");
			stringBuilder.append("Legacy Direct"+"<br/>");
		}



		this.emailContent = stringBuilder.toString();
	}
	
}
