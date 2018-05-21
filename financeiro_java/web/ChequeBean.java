/*Isidore Petersen RELEASE

 * LICENSE: This source file is subject to Attibution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://hack.isiflix.com
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 * Peterson Isidoro a.k.a Isidore Petersen
 *
 */
package financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financeiro.cheque.Cheque;
import financeiro.cheque.ChequeRN;
import financeiro.conta.Conta;
import financeiro.util.RNException;
import financeiro.web.util.ContextoUtil;
import financeiro.web.util.MensagemUtil;

@ManagedBean(name="chequeBean")
@RequestScoped
public class ChequeBean {

	private Cheque				selecionado	= new Cheque();
	private List<Cheque>	lista				= null;
	private Integer				chequeInicial;
	private Integer				chequeFinal;

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Conta conta = contextoBean.getContaAtiva();
		
		int totalCheques = 0;
		if (this.chequeInicial == null || this.chequeFinal == null) {
			String texto = MensagemUtil.getMensagem("cheque_erro_sequencia");
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
		} else if (this.chequeFinal.intValue() < this.chequeInicial.intValue()) {
			String texto = MensagemUtil.getMensagem("cheque_erro_inicial_final", this.chequeInicial, this.chequeFinal);
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
		} else {
			ChequeRN chequeRN = new ChequeRN();
			totalCheques = chequeRN.salvarSequencia(conta, this.chequeInicial, this.chequeFinal);
			String texto = MensagemUtil.getMensagem("cheque_info_cadastro", this.chequeFinal, totalCheques);
			FacesMessage msg = new FacesMessage(texto);
			context.addMessage(null, msg);
			this.lista = null;
		}
	}

	public void excluir() {
		ChequeRN chequeRN = new ChequeRN();
		try {
			chequeRN.excluir(this.selecionado);
		} catch (RNException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			String texto = MensagemUtil.getMensagem("cheque_erro_excluir");
			FacesMessage msg = new FacesMessage(texto);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			context.addMessage(null, msg);
		}
		this.lista = null;
	}

	public void cancelar() {
		ChequeRN chequeRN = new ChequeRN();
		try {
			chequeRN.cancelarCheque(this.selecionado);
		} catch (RNException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			String texto = MensagemUtil.getMensagem("cheque_erro_cancelar");
			FacesMessage msg = new FacesMessage(texto);
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			context.addMessage(null, msg);
		}
		this.lista = null;
	}

	public List<Cheque> getLista() {
		if (this.lista == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaAtiva();

			ChequeRN chequeRN = new ChequeRN();
			this.lista = chequeRN.listar(conta);
		}
		return this.lista;
	}

	public Cheque getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Cheque selecionado) {
		this.selecionado = selecionado;
	}

	public Integer getChequeInicial() {
		return chequeInicial;
	}

	public void setChequeInicial(Integer chequeInicial) {
		this.chequeInicial = chequeInicial;
	}

	public Integer getChequeFinal() {
		return chequeFinal;
	}

	public void setChequeFinal(Integer chequeFinal) {
		this.chequeFinal = chequeFinal;
	}
}
