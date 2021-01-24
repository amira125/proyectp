package aosgc.board;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

public class Main {
		public static void main(String[] args) {

		ResourceConfig config = new ResourceConfig();
		config.packages("aosgc.board.services");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		String webPort = System.getenv("PORT");
		  if (webPort == null || webPort.isEmpty()) {
		    webPort = "8080";
		  }
		  Server server = new Server(Integer.parseInt(webPort));
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/proyecto/*");

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.destroy();
		}

	}

}
