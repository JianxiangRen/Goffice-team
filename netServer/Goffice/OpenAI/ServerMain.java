import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerMain {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8001);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new UploadServlet()), "/upload");
        server.start();
        server.join();
    }
}
