# Server.xml HTTPS Connector configuration:

    <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="200" SSLEnabled="true" scheme="https" secure="true"
               SSLVerifyClient="optional" keystoreType="JKS"
               keystoreFile="/Users/Heman/Documents/workstation/Developement_Studio/Java_Laboratory/wpl-cookies/backend-side/keys/cookies-server.jks" 
               keystorePass="Cookies123" clientAuth="false" sslProtocol="TLS"
               truststoreFile="/Users/Heman/Documents/workstation/Developement_Studio/Java_Laboratory/wpl-cookies/backend-side/keys/cookies-server.jks" 
			   truststorePass="Cookies123" 
			   truststoreType="JKS"  />
               
# In case of Errors regarding Java permissions, add the following line in catalina.properties:
    permission java.security.AllPermission;
    
    Note: Add this in WEB APPLICATION PERMISSIONS
    
# Also add crt file into java keystore (cacerts)
