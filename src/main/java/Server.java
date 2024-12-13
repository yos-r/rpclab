import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

import impl.CalculatriceServiceImpl;
import impl.GestionNotes;
import java.io.IOException;

public class Server {
    public static void main(String[] args) throws XmlRpcException,  IOException {
        WebServer webServer = new WebServer(8081);
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        
        // Enregistrer les services
        phm.addHandler("Calculatrice", CalculatriceServiceImpl.class);
        phm.addHandler("GestionNotes", GestionNotes.class);

        xmlRpcServer.setHandlerMapping(phm);
        webServer.start();

        System.out.println("Serveur XML-RPC démarré sur le port 8081...");
    }
}