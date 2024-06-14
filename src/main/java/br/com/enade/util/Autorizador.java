package br.com.enade.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.enade.model.Tbusuario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent evento) {
		
		
		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		
		
		System.out.println(nomePagina);

		if ("/login.xhtml".equals(nomePagina) || "/cadastroAluno.xhtml".equals(nomePagina)) {
			return;
		}

		Tbusuario usuarioLogado = (Tbusuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuarioLogado != null) {
			return;
		}

		// redirecionamento para login.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
	}

	public void beforePhase(PhaseEvent event) {
		// lançamos uma exceção.
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
