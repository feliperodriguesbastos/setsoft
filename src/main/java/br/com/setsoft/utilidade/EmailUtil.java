package br.com.setsoft.utilidade;

import java.util.ArrayList;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.sun.xml.internal.ws.util.UtilException;

public class EmailUtil {
	
	//para o yahoo
//	private static final String HOSTNAME = "smtp.mail.yahoo.com.br";
//	private static final int PORTA = 25;
//	private static final boolean TLS = false;
//	private static final String USERNAME = "setsoft";
//	private static final String PASSWORD = "setsoft";
//	private static final String EMAILORIGEM = "setsoft@yahoo.com.br";
	
	//para o gmail
	private static final String HOSTNAME = "smtp.gmail.com";
	private static final int PORTA = 587;
	private static final boolean TLS = true;
	private static final String USERNAME = "admtfg@gmail.com";
	private static final String PASSWORD = "jlpadm2012";
	private static final String EMAILORIGEM = "admtfg@gmail.com";
	
	
	public static  Email conectaEmail() throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(PORTA);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setTLS(TLS);
//		email.setFrom(EMAILORIGEM);
		return email;
	}
	
	public static void enviarEmail(Mensagem mensagem) throws UtilException{
//		Email email = new SimpleEmail();
//		email = conectaEmail();
//		email.setSubject(mensagem.getTitulo());
//		email.setMsg(mensagem.getMensagem());
//		email.addTo(mensagem.getDestino());
//		@SuppressWarnings("unused")
//		String resposta = email.send();
		
		try{
			Email email = new SimpleEmail();
			email = conectaEmail();
			email.setSubject(mensagem.getTitulo());
			email.setMsg(mensagem.getMensagem());
			
			if(mensagem.getDe() != null)
				email.setFrom(mensagem.getDe());
			else
				email.setFrom(EMAILORIGEM);
			
			if(mensagem.getPara() != null){
				List<InternetAddress> destinatariosNormais = montaDestinatarios(mensagem.getPara());
				if(!destinatariosNormais.isEmpty())
					email.setTo(destinatariosNormais);
			}
			
			if(mensagem.getComCopia() != null){
				List<InternetAddress> destinatariosCopias = montaDestinatarios(mensagem.getComCopia());
				if(!destinatariosCopias.isEmpty())
					email.setCc(destinatariosCopias);
			}
			
			if(mensagem.getComCopiaOculta() != null){
				List<InternetAddress> destinatariosOcultos = montaDestinatarios(mensagem.getComCopiaOculta());
				if(!destinatariosOcultos.isEmpty())
					email.setBcc(destinatariosOcultos);
			}		
			
			@SuppressWarnings("unused")
			String resposta = email.send();
			
		} catch (EmailException e){
			throw new UtilException(e);
		} catch (AddressException e){
			throw  new UtilException(e);
		}
				
	}
	
//	//usando JNDI
//	public static void enviarEmail(Mensagem mensagem) throws UtilException {
//		try{
//			Context initialContext = new InitialContext();
//			Context envContext = (Context) initialContext.lookup("java:/comp/env");
//			Session session = (Session) envContext.lookup("mail/Session");
//			SimpleEmail email = new SimpleEmail();
//			email.setMailSession(session);
//			
//			if(mensagem.getDe() != null)
//				email.setFrom(mensagem.getDe());
//			else
//				email.setFrom(session.getProperty("mail.smtp.user"));
//			
//			if(mensagem.getPara() != null){
//				List<InternetAddress> destinatariosNormais = montaDestinatarios(mensagem.getPara());
//				if(destinatariosNormais != null)
//					email.setTo(destinatariosNormais);
//			}
//			
//			if(mensagem.getComCopia() != null){
//				List<InternetAddress> destinatariosCopias = montaDestinatarios(mensagem.getComCopia());
//				if(destinatariosCopias != null)
//					email.setCc(destinatariosCopias);
//			}
//			
//			if(mensagem.getComCopiaOculta() != null){
//				List<InternetAddress> destinatariosOcultos = montaDestinatarios(mensagem.getComCopiaOculta());
//				if(destinatariosOcultos != null)
//					email.setBcc(destinatariosOcultos);
//			}
//			
//			email.setSubject(mensagem.getTitulo());
//			email.setMsg(mensagem.getMensagem());
//			email.setSentDate(new Date());
//			email.send();
//			
//			
//		} catch (EmailException e){
//			throw new UtilException(e);
//		} catch (NamingException e){
//			throw new UtilException(e);
//		} catch (AddressException e){
//			throw  new UtilException(e);
//		}
//	}
	
	private static List<InternetAddress> montaDestinatarios(String destinatarios) throws AddressException {
		
		List<String> listaString = StringUtil.converterStringParaListaString(destinatarios, ConstanteUtil.SEPARADOR_EMAIL);
		
		List<InternetAddress> emails = new ArrayList<InternetAddress>();
		
		for (String email : listaString) {
			emails.add(new InternetAddress(email));
		}
		
		return emails;
	}
}