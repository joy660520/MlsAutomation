package mail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.internet.AddressException;

import org.apache.http.cookie.SM;

import base.ReadProperties;
import mail.ReadHtml;

public class AutoMail { //用這個功能的時候Gmail的"允許低安全性應用程式存取權"要開啟
	public static void main(String[] args) throws AddressException, FileNotFoundException, IOException {
		MailInfo mailInfo = new MailInfo();
		String sendTo[] = readMail("sendto");
		String cclist[] = readMail("cc");
		
		
		
		mailInfo.setMailServerHost(ReadProperties.getprop("mail", "MailServerHost"));
		mailInfo.setMailServerPort(ReadProperties.getprop("mail", "MailServerPort"));
		mailInfo.setValidate(true);
		mailInfo.setUsername(ReadProperties.getprop("mail", "Username"));
		mailInfo.setPassword(ReadProperties.getprop("mail", "Password"));// 您的邮箱密码
		mailInfo.setFromAddress(ReadProperties.getprop("mail", "FromAddress"));
		mailInfo.setToAddress(sendTo);
		mailInfo.setSubject(ReadProperties.getprop("mail", "Subject"));
		mailInfo.setCcAddress(cclist);
		

		String mailcontent = ReadHtml.readString("./target/surefire-reports/html/overview.html");
		String cssvalue = ReadHtml.readString("./target/surefire-reports/html/reportng.css");
		String changestr = "<style type=\"text/css\">h1 {display : inline}" + cssvalue + "</style>";
		mailcontent = mailcontent.replace("<link href=\"reportng.css\" rel=\"stylesheet\" type=\"text/css\" />",
				changestr);
		String logostr = "<h1 style=\"color:red ; font-size:50px;font-family: '楷體','楷體_GB2312';\">MAVEN_DEMO</h1><h1>自動化測試報告</h1>";
		mailcontent = mailcontent.replace("<h1>Test Results Report</h1>", logostr);
		mailInfo.setContent(mailcontent);
		SendMail.sendHtmlMail(mailInfo);// 发送html格式邮件

	}

	public static String[] readMail(String mailtype) throws FileNotFoundException, IOException

	{

		int i = 0;
		Properties props = new Properties();
		props.load(new FileInputStream("./target/test-classes/properties/" + mailtype + ".properties"));
		String[] mailto = new String[props.size()];

		for (Enumeration enu = props.elements(); enu.hasMoreElements();) {
			String key = (String) enu.nextElement();

			mailto[i] = key;
			i++;
		}
		return mailto;
	}

}
