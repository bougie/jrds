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

import org.apache.log4j.Logger;

/**
 * A servlet wich show the list of hosts
 * @author David Hymonnet
 */
public final class Hosts extends JrdsServlet {
    static final private Logger logger = Logger.getLogger(Hosts.class);

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            JrdsJSONWriter w = new JrdsJSONWriter(res);
            w.object();

            w.key("items");
            w.array();
            w.newLine();
            Collection<HostInfo> hosts = getHostsList().getHosts();
            for(HostInfo host: hosts) {
                w.value(host.getName());
            }
            w.endArray();

            w.newLine();
            w.endObject();
            w.newLine();
            w.flush();
        } catch (Exception e) {
            logger.warn("Failed request: " + req.getRequestURI() + ": " + e, e);
        }
    }
}
