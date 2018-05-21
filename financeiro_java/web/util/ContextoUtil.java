/*Isidore Petersen RELEASE

 * LICENSE: This source file is subject to Attibution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://hack.isiflix.com
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 * Peterson Isidoro a.k.a Isidore Petersen
 *
 */
package financeiro.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import financeiro.web.ContextoBean;

public class ContextoUtil {

	public static ContextoBean getContextoBean() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		ContextoBean contextoBean = (ContextoBean) session.getAttribute("contextoBean");
		
		return contextoBean;
	}
}
