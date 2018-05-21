/*Isidore Petersen RELEASE

 * LICENSE: This source file is subject to Attibution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://hack.isiflix.com
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 * Peterson Isidoro a.k.a Isidore Petersen
 *
 */
package financeiro.lancamento;

import java.util.Date;
import java.util.List;

import financeiro.conta.Conta;

public interface LancamentoDAO {

	public void salvar(Lancamento lancamento);

	public void excluir(Lancamento lancamento);

	public Lancamento carregar(Integer lancamento);

	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim);

	public float saldo(Conta conta, Date data);
}
