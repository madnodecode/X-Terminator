package com.paypal;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;
import org.json.JSONObject;

 
public class restClient {
 
	private static WebResource bugClient = null;
	
	 public static void main(String[] args) {
			
			try {
		 
				Client client = Client.create();
				//input the rest resource end point URL
				bugClient = client.resource("");
				ClientResponse resp = bugClient.accept("application/json").get(ClientResponse.class);
				
				if (resp.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + resp.getStatus());
				}
				
				ObjectMapper mapper = new ObjectMapper();
				List<BugFeed> bugFeedList = mapper.readValue(resp.getEntity(String.class), TypeFactory.defaultInstance().constructCollectionType(List.class,  
						   BugFeed.class));
				
				System.out.println(bugFeedList);
				for(BugFeed bug : bugFeedList) {	
					if(bugFeedList.size()<=0){
						
					}
					else{
						emailSetup(bug);
					}
					
				}
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
	 }
	 
	 
	 public static void emailSetup(BugFeed bug){
		 
		 	//initialize username password
		 	final String username = "";
			final String password = "";
		 
		 	Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.host", "atom.corp.ebay.com");
			
		    Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						System.out.println("username:"+username);
						return new PasswordAuthentication(username, password);
					}
				  });
		 try {
			 	//pass jira url
			    String jiraBugUrl = ""+bug.getBugId();
				Message message = new MimeMessage(session);
				//sender email id
				message.setFrom(new InternetAddress(""));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(""+bug.getDeveloperemail()+","+bug.getVerifierEmail()));
				//pass CC Recipients
				message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(""));
				message.setSubject("Open Bug: "+jiraBugUrl);
				message.setText("Dear "+ bug.getDeveloperemail()+
						",\n\n"+"You have a bug pending, please act on it."+"\n\n\n"
						+"Bug Id			: "+jiraBugUrl+"\n"
						+"Bug Description	: "+bug.getIssueDesc()+"\n"
						+"Bug Status		: "+bug.getIssueStatus()+"\n"
						+"Bug Priority		: "+bug.getIssuePriority()+"\n"
						+"Project Name		: "+bug.getProjectName()+"\n"
						+"Verifier Email		: "+bug.getVerifierEmail());


				Transport.send(message);
				System.out.println("Done");

			} 
			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	 }
}



