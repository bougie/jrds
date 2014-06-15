/*##########################################################################
 _##
 _##  $Id: Hosts.java 236 2014-06-15 13:37:42 +0100 (dim., 15 juin 2015) bougie $
 _##
 _##########################################################################*/

package jrds.webapp;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jrds.HostsList;
import jrds.HostInfo;

/**
 * A servlet wich show the list of hosts
 * @author David Hymonnet
 */
public final class Hosts extends JrdsServlet {
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		res.setContentType("text/plain");
		res.addHeader("Cache-Control", "no-cache");
		ServletOutputStream out = res.getOutputStream();

		Collection<HostInfo> hosts = getHostsList().getHosts();
		for(HostInfo host: hosts) {
			out.println(host.getName());
		}
	}
}
