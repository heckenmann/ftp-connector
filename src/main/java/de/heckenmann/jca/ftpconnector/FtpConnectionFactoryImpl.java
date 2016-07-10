package de.heckenmann.jca.ftpconnector;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

/**
 *
 * @author heckenmann
 */
class FtpConnectionFactoryImpl implements FtpConnectionFactory {

    private Reference reference;

    private FtpManagedConnectionFactory mcf;
    private ConnectionManager cm;

    /**
     * Default constructor
     *
     * @param mcf ManagedConnectionFactory
     * @param cxManager ConnectionManager
     */
    public FtpConnectionFactoryImpl(FtpManagedConnectionFactory mcf,
            ConnectionManager cxManager) {
        this.mcf = mcf;
        this.cm = cxManager;
    }

    /**
     * Get connection from factory
     *
     * @return RAConnection instance
     * @exception ResourceException Thrown if a connection can't be obtained
     */
    @Override
    public FtpConnection getConnection() throws ResourceException {
        return (FtpConnection) cm.allocateConnection(mcf, null);
    }

    /**
     * Get the Reference instance.
     *
     * @return Reference instance
     * @exception NamingException Thrown if a reference can't be obtained
     */
    @Override
    public Reference getReference() throws NamingException {
        return reference;
    }

    /**
     * Set the Reference instance.
     *
     * @param reference A Reference instance
     */
    @Override
    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
