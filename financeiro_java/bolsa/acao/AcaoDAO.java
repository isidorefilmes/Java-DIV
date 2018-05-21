/*Isidore Petersen RELEASE

 * LICENSE: This source file is subject to Attibution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://hack.isiflix.com
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 * Peterson Isidoro a.k.a Isidore Petersen
 *
 */
package financeiro.bolsa.acao;

import java.util.List;

import financeiro.usuario.Usuario;

public interface AcaoDAO {

	public void salvar(Acao acao);

	public void excluir(Acao acao);

	public Acao carregar(String codigo);

	public List<Acao> listar(Usuario usuario);

}
