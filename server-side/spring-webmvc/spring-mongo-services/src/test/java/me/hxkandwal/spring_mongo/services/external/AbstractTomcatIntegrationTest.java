package me.hxkandwal.spring_mongo.services.external;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.naming.resources.VirtualDirContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractTomcatIntegrationTest {

	private static Tomcat tomcat;
	private static Integer portNumber;
	private static String host = "http://localhost";
	private static final String REST_URL = "/event-services/api";
	private static String webappDirLocation = "src/main/webapp/";

	public static Tomcat getTomcat() {
		return tomcat;
	}

	public static String getRestAddress() {
		return getHost() + ":" + getPortNumber() + REST_URL;
	}

	public static String getHost() {
		return host;
	}

	public static void setTomcat(Tomcat tomcat) {
		AbstractTomcatIntegrationTest.tomcat = tomcat;
	}

	public static Integer getPortNumber() {
		return portNumber;
	}

	public static void setPortNumber(Integer portNumber) {
		AbstractTomcatIntegrationTest.portNumber = portNumber;
	}

	public static String getWebappDirLocation() {
		return webappDirLocation;
	}

	@BeforeClass
	@SuppressWarnings("resource")
	public static void setUp() {
		try {
			setPortNumber(Integer.valueOf(new ServerSocket(0).getLocalPort()));
			System.setProperty("spring.profiles.active", "dev");

			setTomcat(new Tomcat());
			getTomcat().setBaseDir("target");

			StandardContext ctx = (StandardContext) getTomcat().addWebapp("/event-analytics-services", new File(getWebappDirLocation()).getAbsolutePath());
			VirtualDirContext resources = new VirtualDirContext();
			resources.setExtraResourcePaths("/WEB-INF/classes=" +  new File("target/classes"));
			ctx.setResources(resources);
			ctx.setProcessTlds(false);
			
			getTomcat().setPort(getPortNumber());
			getTomcat().start();
			getTomcat().getHost().setAppBase("target");

		} catch (ServletException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (LifecycleException exception) {
			exception.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDown() {
		shutdownTomcat();
		setPortNumber(null);
		setTomcat(null);
	}

	public static final void shutdownTomcat() {
		try {
			if (getTomcat() != null
					&& getTomcat().getServer() != null
					&& getTomcat().getServer().getState() != LifecycleState.DESTROYED) {
				
				if (getTomcat().getServer().getState() != LifecycleState.STOPPED) {
					getTomcat().stop();
				}
				
				getTomcat().destroy();
				getTomcat().getServer().await();
			}
		} catch (Exception e) {
			// consume exception.
		}
	}

}
