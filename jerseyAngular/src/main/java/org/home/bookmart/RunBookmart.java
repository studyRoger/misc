package org.home.bookmart;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.home.bookmart.resource.BookRS;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.URI;

/**
 * Created by roger on 9/13/15.
 */
public class RunBookmart {


    public static void main(String[] args) throws Exception {
        ResourceConfig config = new ResourceConfig(BookRS.class);
        config.register(JacksonFeature.class);

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);

        /*int port = 9998;
        Server server = new Server(port);
        ContextHandler context = new ContextHandler();
        context.setContextPath("/");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        resource_handler.setResourceBase(".");

        server.setHandler(resource_handler);
        System.out.println(System.getProperty("jetty.home"));
        System.out.println(System.getProperty("jetty.base"));
        File directory = new File(".");
        System.out.println(directory.getAbsolutePath());

        server.start();
        server.join();*/

    }
}
