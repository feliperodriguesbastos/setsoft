package br.com.setsoft.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.setsoft.enuns.EnumOperacao;
import br.com.setsoft.interfaces.ICrudGenerico;
import br.com.setsoft.interfaces.IEntidadeBase;
import br.com.setsoft.interfaces.arquivo.IArquivo;
import br.com.setsoft.interfaces.arquivo.ISuporteArquivo;
import br.com.setsoft.modelo.Arquivo;
import br.com.setsoft.remetente.IRemetente;
import br.com.setsoft.remetente.RemetenteWeb;
import br.com.setsoft.utilidade.StringUtil;

public abstract class ControladorGenerico<T extends IEntidadeBase<PK>, PK extends Serializable> {
	
	private EnumOperacao operacao = EnumOperacao.BUSCAR;	

	public void prepararAdicionar(ActionEvent ae) {
		instanciarObjetoPersistente();
		setOperacao(EnumOperacao.ADICIONAR);
	}
	
	public void prepararVisualizar(ActionEvent ae){
		prepararObjetoPersistente();
		setOperacao(EnumOperacao.VISUALIZAR);
	}

	public void prepararAlterar(ActionEvent ae) {
		prepararObjetoPersistente();
		setOperacao(EnumOperacao.ALTERAR);
	}	
	
	public void prepararExcluir(ActionEvent ae) {
		prepararObjetoPersistente();
		setOperacao(EnumOperacao.EXCLUIR);
	}

	public void cancelar(ActionEvent ae) {		
		instanciarObjetoPersistente();		
		setOperacao(EnumOperacao.BUSCAR);		
	}
	
	@SuppressWarnings("unchecked")
	public void instanciarObjetoPersistente(){
		try {
			setObjeto((T)getObjeto().getClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void prepararObjetoPersistente(){
//		T objetoSelecionado = getListaObjeto().getRowData();
//		setObjeto(objetoSelecionado);
	}
	
	@SuppressWarnings("unchecked")
	public void realizarBusca(ActionEvent ae){
//		List<T> lista = getDao().buscarPorFiltro(getObjeto());
//		DataModel<T> listaDataModel = new ListDataModel<T>(lista);
//		setListaObjeto(listaDataModel);
		List<T> lista = getDao().buscarPorFiltro(getObjeto());
		setListaObjeto(lista);
	}
	
	@SuppressWarnings("unchecked")
	public void realizarBuscarTodos(ActionEvent ae){
//		List<T> lista = getDao().buscarTodos();
//		DataModel<T> listaDataModel = new ListDataModel<T>(lista);
//		setListaObjeto(listaDataModel);
		List<T> lista = getDao().buscarTodos();
		setListaObjeto(lista);
	}
	
	@SuppressWarnings("unchecked")
	public void realizarInsercao(ActionEvent ae) throws Exception {
		getDao().adicionar(getObjeto());
	}
	
	@SuppressWarnings("unchecked")
	public void realizarAlteracao(ActionEvent ae) throws Exception {
		getDao().alterar(getObjeto());
	}
	
	@SuppressWarnings("unchecked")
	public void realizarExclusao(ActionEvent ae) throws Exception {
		getDao().excluir(getObjeto());
	}
	
	@SuppressWarnings("incomplete-switch")
	public void realizarCRUD(ActionEvent ae) throws Exception{
		
		try {
			
			executarPreOperacao();
			
			switch(getOperacao()){
				case ADICIONAR:{				
					realizarInsercao(ae);		
					break;
					}
				case ALTERAR:{
					realizarAlteracao(ae);			
					break;
					}
				case EXCLUIR:{
					realizarExclusao(ae);			
					break;
					}
			}	
			
			executarPosOperacao();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if (exibirMensagemErro()) {
				exibirMensagemErro(e);
			}
			
			return;
		}
		
		exibirMensagemSucesso();
		instanciarObjetoPersistente();
		if (efetuarBuscarTodos()) {
			realizarBuscarTodos(ae);
		}
		setOperacao(EnumOperacao.BUSCAR);
	}
	
	protected void exibirMensagemSucesso() {
		registrarMensagemInfo("Operação realizada com sucesso!");
	}
	
	protected void exibirMensagemErro(Exception e) {
		registrarMensagemErro("Operação não realizada! Erro:"+e);
	}
	
	public void registrarMensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
	
	public void registrarMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}
	
	protected Boolean exibirMensagemErro(){return true;}
	protected Boolean efetuarBuscarTodos(){return true;}
	
	protected void executarPreOperacao() throws Exception{};
	protected void executarPosOperacao() throws Exception{};
	
	protected abstract T getObjeto();
	protected abstract void setObjeto(T objeto);
//	protected abstract DataModel<T> getListaObjeto();
//	protected void setListaObjeto(DataModel<T> listaObjeto) {};
	
	protected abstract List<T> getListaObjeto();
	protected void setListaObjeto(List<T> listaObjeto) {};
	
	@SuppressWarnings("rawtypes")
	protected abstract ICrudGenerico getDao();

	public EnumOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(EnumOperacao operacao) {
		this.operacao = operacao;
	}
	
	public Boolean isBuscando(){
		return EnumOperacao.BUSCAR.equals(getOperacao());
	}
	
	public Boolean isAdicionando(){
		return EnumOperacao.ADICIONAR.equals(getOperacao());
	}
	
	public Boolean isVisualizando(){
		return EnumOperacao.VISUALIZAR.equals(getOperacao());
	}		
	
	public Boolean isAlterando(){
		return EnumOperacao.ALTERAR.equals(getOperacao());
	}	
	
	public Boolean isExcluindo(){
		return EnumOperacao.EXCLUIR.equals(getOperacao());
	}
	
	private Arquivo arquivoSelecionado;
	
	public Arquivo getArquivoSelecionado() {
		
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(Arquivo arquivoSelecionado) {
		
		this.arquivoSelecionado = arquivoSelecionado;
	}
	
	public void upload(FileUploadEvent event) throws Exception {
		

		try {
			UploadedFile file = event.getFile();		
			this.adicionarArquivo(new Arquivo(file.getContents(), StringUtil.normalizer(file.getFileName())));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	private void adicionarArquivo(IArquivo arquivo) {
		
		((ISuporteArquivo)getObjeto()).adicionarArquivo(arquivo);
	}
	
	public void download() throws IOException {
		
		IRemetente remetente = new RemetenteWeb();
		remetente.enviar(getArquivoSelecionado());		
	}
	
	public void removerArquivo() {
		
		((ISuporteArquivo)getObjeto()).removerArquivo(this.getArquivoSelecionado());
	}

}
