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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.usuario.Usuario;

public class AcaoDAOHibernate implements AcaoDAO {

	private Session	session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Acao acao) {
		this.session.saveOrUpdate(acao);
	}

	@Override
	public void excluir(Acao acao) {
		this.session.delete(acao);
	}

	@Override
	public Acao carregar(String codigo) {
		return (Acao) this.session.get(Acao.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acao> listar(Usuario usuario) {
		Criteria criteria = this.session.createCriteria(Acao.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		return criteria.list();
	}
}
