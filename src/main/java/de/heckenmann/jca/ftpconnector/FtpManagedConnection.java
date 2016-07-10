package de.heckenmann.jca.ftpconnector;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

/**
 *
 * @author heckenmann
 */
public class FtpManagedConnection implements ManagedConnection {

    private static final Logger LOG = Logger.getLogger(FtpManagedConnection.class.getName());

    private FtpManagedConnectionFactory cf;

    private PrintWriter logWriter;

    private Object connection;

    /**
     * Listeners
     */
    private List<ConnectionEventListener> listeners;

    /**
     * Constructor.
     *
     * @param cf
     */
    public FtpManagedConnection(FtpManagedConnectionFactory cf) {
        this.cf = cf;
        this.logWriter = null;
        this.listeners = new ArrayList<>(1);
        this.connection = new FtpConnectionImpl(this, cf);
    }

    @Override
    public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException {
        connection = new FtpConnectionImpl(this, cf);
        return connection;
    }

    @Override
    public void destroy() throws ResourceException {
        LOG.info("destroy()");
        this.connection = null;
    }

    @Override
    public void cleanup() throws ResourceException {
        LOG.info("cleanup()");
    }

    @Override
    public void associateConnection(Object connection) throws ResourceException {
        this.connection = connection;
    }

    @Override
    public void addConnectionEventListener(ConnectionEventListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        listeners.add(listener);
    }

    @Override
    public void removeConnectionEventListener(ConnectionEventListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        listeners.remove(listener);
    }

    @Override
    public XAResource getXAResource() throws ResourceException {
        throw new NotSupportedException("GetXAResource not supported");
    }

    @Override
    public LocalTransaction getLocalTransaction() throws ResourceException {
        throw new NotSupportedException("LocalTransaction not supported");
    }

    @Override
    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        return new FtpManagedConnectionMetaData();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws ResourceException {
        this.logWriter = out;
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return logWriter;
    }

}
