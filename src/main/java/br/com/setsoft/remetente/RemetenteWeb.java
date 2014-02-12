package br.com.setsoft.remetente;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.setsoft.interfaces.arquivo.IArquivo;

public class RemetenteWeb implements IRemetente {

	@Override
	public void enviar(IArquivo arquivo) {
		final FacesContext context = FacesContext.getCurrentInstance();
		final HttpServletResponse httpResponse = (HttpServletResponse) context.getExternalContext().getResponse();
		// httpResponse.setContentType();
		httpResponse.setCharacterEncoding("ISO-8859-1");
		httpResponse.addHeader("Content-Disposition", "attachment;filename=" + arquivo.getTituloArquivo());
		try {
			httpResponse.getOutputStream().write(arquivo.getArquivo());
			httpResponse.getOutputStream().flush();
			context.responseComplete();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
