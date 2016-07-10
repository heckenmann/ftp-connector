package de.heckenmann.jca.ftpconnector;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;
import javax.security.auth.Subject;

/**
 * @author heckenmann
 */
@ConnectionDefinition(connectionFactory = FtpConnectionFactory.class,
        connectionFactoryImpl = FtpConnectionFactoryImpl.class,
        connection = FtpConnection.class,
        connectionImpl = FtpConnectionImpl.class)
public class FtpManagedConnectionFactory implements ManagedConnectionFactory, ResourceAdapterAssociation {

    private static final long serialVersionUID = 1L;

    private String host;
    private int port;
    private String user;
    private String password;

    private ResourceAdapter ra;
    private PrintWriter logwriter;

    public FtpManagedConnectionFactory() {
        Logger.getLogger(FtpManagedConnectionFactory.class.getName()).info("FtpManagedConnectionFactory loaded");
    }

    @Override
    public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException {
        return new FtpConnectionFactoryImpl(this, cxManager);
    }

    @Override
    public Object createConnectionFactory() throws ResourceException {
        throw new ResourceException("This resource adapter doesn't support non-managed environments");
    }

    @Override
    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        return new FtpManagedConnection(this);
    }

    @Override
    public ManagedConnection matchManagedConnections(Set connectionSet, Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        ManagedConnection result = null;

        Iterator it = connectionSet.iterator();
        while (result == null && it.hasNext()) {
            ManagedConnection mc = (ManagedConnection) it.next();
            if (mc instanceof FtpManagedConnection) {
                FtpManagedConnection hwmc = (FtpManagedConnection) mc;
                result = hwmc;
            }
        }

        return result;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        logwriter = out;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logwriter;
    }

    @Override
    public ResourceAdapter getResourceAdapter() {
        return ra;
    }

    @Override
    public void setResourceAdapter(ResourceAdapter ra) throws ResourceException {
        this.ra = ra;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
