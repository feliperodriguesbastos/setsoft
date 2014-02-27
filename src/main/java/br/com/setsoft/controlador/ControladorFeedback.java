package br.com.setsoft.controlador;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.setsoft.controlador.service.ControladorFeedbackService;
import br.com.setsoft.controlador.service.ControladorUsuarioService;
import br.com.setsoft.enuns.EnumOperacao;
import br.com.setsoft.modelo.Feedback;
import br.com.setsoft.modelo.Usuario;
import br.com.setsoft.utilidade.EmailUtil;
import br.com.setsoft.utilidade.Mensagem;

import com.sun.xml.internal.ws.util.UtilException;

@Named
@ViewScoped
public class ControladorFeedback extends ControladorGenerico<Feedback, Integer> {
	
	private Feedback feedback;
	
	private List<Feedback> listaFeedback;
	
	private List<Usuario> usuarios;
	
	@Inject
	private ControladorFeedbackService controladorFeedbackService;
	
	@Inject
	private ControladorLogin controladorLogin;
	
	@Inject
	private ControladorUsuarioService controladorUsuarioService;
	
	@PostConstruct
	private void init() {
		feedback = new Feedback();
		listaFeedback = getDao().buscarTodos();
		this.usuarios = this.controladorUsuarioService.buscarTodos();
		this.setOperacao(EnumOperacao.BUSCAR);
	}
	
	@Override
	public void prepararAdicionar(final ActionEvent ae) {

		super.prepararAdicionar(ae);
		this.getObjeto().setDataCadastro(new Date());
		this.getObjeto().setColaborador(this.controladorLogin.getUsuario());
	}
	
	@Override
	protected void executarPosOperacao() throws Exception {
		super.executarPosOperacao();
		
		if (this.isAdicionando()) {
			this.enviarEmail();
		}
	}

	private void enviarEmail() {
		
		if (this.controladorLogin.getUsuario().possuiEmail()) {
			
			Mensagem mensagem = new Mensagem();
			mensagem.setTitulo("setsoft - Agradecimento");
			mensagem.setMensagem(obterCorpoMensagem());
			mensagem.setPara(this.controladorLogin.getUsuario().getEmail());
			mensagem.setComCopia("bitmarques@gmail.com");
			
			try{
				EmailUtil.enviarEmail(mensagem);
				this.registrarMensagemInfo("E-mail enviado com sucesso!");
			} catch (UtilException ex){
				this.registrarMensagemErro("Erro no envio de e-mail: "+ex);
			}
		}		
	}
	
	private String obterCorpoMensagem() {
		
		StringBuilder corpoMensagem = new StringBuilder();
		corpoMensagem.append("Agradecemos por relatar seu(a): " + this.getObjeto().getTipoFeedback().getTipo());
		corpoMensagem.append("\n\nVocê escreveu: ");
		corpoMensagem.append("\n\n" +"\"" + this.getObjeto().getDescricao() +"\"");
		
		corpoMensagem.append("\n\nContinue colaborando com seu feedback.");
		
		corpoMensagem.append("\n\n******Este e-mail foi enviado automaticamente pelo Sistema Setsoft. Por Favor, não responder.******");	
		corpoMensagem.append("\n\nAtenciosamente,");
		corpoMensagem.append("\nJoel Marques\nAdministrador do Sistema");
		
		return corpoMensagem.toString();
	}

	@Override
	protected Feedback getObjeto() {
		return feedback;
	}

	@Override
	protected void setObjeto(Feedback objeto) {
		this.feedback = objeto;
	}

	@Override
	protected List<Feedback> getListaObjeto() {
		return listaFeedback;
	}
	
	@Override
	protected void setListaObjeto(List<Feedback> listaObjeto) {
		this.listaFeedback = listaObjeto;
	}

	@Override
	protected ControladorFeedbackService getDao() {
		return controladorFeedbackService;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public List<Feedback> getListaFeedback() {
		return listaFeedback;
	}

	public void setListaFeedback(List<Feedback> listaFeedback) {
		this.listaFeedback = listaFeedback;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}