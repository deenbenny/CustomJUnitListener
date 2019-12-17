package com.servicenow.test.automation.util;

import static com.servicenow.test.automation.config.Constants.ccMail;
import static com.servicenow.test.automation.config.Constants.fromMail;
import static com.servicenow.test.automation.config.Constants.mailPassword;
import static com.servicenow.test.automation.config.Constants.mailUserName;
import static com.servicenow.test.automation.config.Constants.toMail;

import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.servicenow.test.automation.config.Constants;
import com.servicenow.test.automation.model.EmailReport;

/**
 * Created by IntelliJ IDEA.
 * User       Purvi Pipaliya
 * Date       11/12/19
 * Time       12:05 PM
 * Project    reporttestautomation
 */

public class SendEmail {

	public static void main(String[] args) {
		triggerMail("Testing Mail", "Jenkins", "Benz", toMail, ccMail, null);
	}

	public static void triggerMail(final String subject, String content, final String msg,
	                               final String to, final String cc, final List<EmailReport> emailReportList) {
		String[] recipientList = to.split(",");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		String[] ccRecipientList = cc.split(",");
		InternetAddress[] ccRecipientAddress = new InternetAddress[ccRecipientList.length];
		String from = fromMail;
		final String username = mailUserName;
		final String password = mailPassword;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			int counter = 0;
			for (String recipient : recipientList) {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			counter = 0;
			for (String recipient : ccRecipientList) {
				ccRecipientAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
			message.setRecipients(RecipientType.TO, recipientAddress);
			message.setRecipients(RecipientType.CC, ccRecipientAddress);
			message.setSubject(subject);
			content = content.replace("\n", "<br />");

			String mainContent = "";

			for (EmailReport emailReport : emailReportList) {
				mainContent = mainContent + "    <tr style=\"box-sizing: border-box;page-break-inside: avoid;\">\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + Constants.AUTOMATION_PROJECT_NAME + "</b></td>\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + emailReport.getFeatureName() + "</b></td>\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + emailReport.getTotal() + "</b></td>\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + emailReport.getPassed() + "</b></td>\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + emailReport.getFailed() + "</b></td>\n" +
						"      <td bgcolor=\"#D2E1DC\" style=\"box-sizing: border-box;padding: .75rem;vertical-align: top;border-top: 1px solid #FEFEFE;\"><b>" + emailReport.getSkipped() + "</b></td>\n" +
						"    </tr>\n";
			}

			String msgContent = "<html style=\"box-sizing: border-box;font-family: sans-serif;line-height: 1.15;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;-ms-overflow-style: scrollbar;-webkit-tap-highlight-color: transparent;\">\n" +
					"<head style=\"box-sizing: border-box;\">\n" +
					"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css\" integrity=\"sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy\" crossorigin=\"anonymous\" style=\"box-sizing: border-box;\">\n" +
					"</head>\n" +
					"<body style=\"box-sizing: border-box;margin: 0;font-family: -apple-system,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif,&quot;Apple Color Emoji&quot;,&quot;Segoe UI Emoji&quot;,&quot;Segoe UI Symbol&quot;;font-size: 1rem;font-weight: 400;line-height: 1.5;color: #212529;text-align: left;background-color: #fff;\">\n" +
					"<div class=\"container\" style=\"box-sizing: border-box;width: 100%;padding-right: 15px;padding-left: 15px;margin-right: auto;margin-left: auto;\">\n" +
					"<b>" + content + "</b><br><br>" +
					"<table class=\"table table-striped\" style=\"box-sizing: border-box;border-collapse: collapse!important;width: 100%;max-width: 100%;margin-bottom: 1rem;background-color: solid;\">\n" +
					"  <thead style=\"box-sizing: border-box;display: table-header-group;\">\n" +
					"    <tr style=\"box-sizing: border-box;page-break-inside: avoid;\">\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Component</th>\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Feature</th>\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Total Tests</th>\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Passed</th>\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Failed</th>\n" +
					"      <th bgcolor=\"#78AA97\" style=\"box-sizing: border-box;text-align: inherit;padding: .75rem;vertical-align: bottom;border-top: 1px solid #FEFEFE;border-bottom: 2px solid #FEFEFE; color: white;\">Skipped</th>\n" +
					"    </tr>\n" +
					"  </thead>\n" +
					"  <tbody style=\"box-sizing: border-box;\">\n" +
					mainContent +
					"  </tbody>\n" +
					"</table>\n" +
					"<br><br>This is Auto generated Report" +
					"</div>\n" +
					"</body>\n" +
					"</html>\n";
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setContent(msgContent, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException error) {
			error.printStackTrace();
		}
	}
}
