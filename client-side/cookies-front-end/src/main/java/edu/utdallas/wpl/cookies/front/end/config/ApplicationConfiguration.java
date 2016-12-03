package edu.utdallas.wpl.cookies.front.end.config;


import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Value("")
	private Resource keystoreFile;

	@Value("${server.ssl.key-store-password}")
	private String keystorePass;

	@Value("${server.ssl.keyStoreType}")
	private String keystoreType;

	@Value("${server.ssl.keyAlias}")
	private String keystoreAlias;

	@Value("${server.port}")
	private int tlsPort;

	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {

				connector.setPort(tlsPort);
				connector.setSecure(true);
				connector.setScheme("https");
				connector.setAttribute("keyAlias", "tomcat");
				connector.setAttribute("keystorePass", keystorePass);
				connector.setAttribute("keystoreFile",
						"/Users/Heman/Documents/workstation/Developement_Studio/Java_Laboratory/wpl-cookies/client-side/cookies-front-end/keystore.p12");
				connector.setAttribute("clientAuth", "false");
				connector.setAttribute("sslProtocol", "TLS");
				connector.setAttribute("SSLEnabled", true);

				Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
				proto.setSSLEnabled(true);
				proto.setKeystoreFile(
						"/Users/Heman/Documents/workstation/Developement_Studio/Java_Laboratory/wpl-cookies/client-side/cookies-front-end/keystore.p12");
				proto.setKeystorePass(keystorePass);
				proto.setKeystoreType(keystoreType);
				proto.setKeyAlias(keystoreAlias);
			}
		});

		return factory;
	}

}
