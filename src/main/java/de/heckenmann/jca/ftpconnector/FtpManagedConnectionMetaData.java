package de.heckenmann.jca.ftpconnector;

import java.util.logging.Logger;
import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;

/**
 *
 * @author heckenmann
 */
class FtpManagedConnectionMetaData implements ManagedConnectionMetaData {

    public FtpManagedConnectionMetaData() {
        Logger.getLogger(FtpManagedConnectionMetaData.class.getName()).info("FtpManagedConnectionMetaData loaded");
    }

    /**
     * Returns Product name of the underlying EIS instance connected through the
     * ManagedConnection.
     *
     * @return Product name of the EIS instance
     * @throws ResourceException Thrown if an error occurs
     */
    @Override
    public String getEISProductName() throws ResourceException {
        return "FTP Resource Adapter";
    }

    /**
     * Returns Product version of the underlying EIS instance connected through
     * the ManagedConnection.
     *
     * @return Product version of the EIS instance
     * @throws ResourceException Thrown if an error occurs
     */
    @Override
    public String getEISProductVersion() throws ResourceException {
        return "1.0";
    }

    /**
     * Returns maximum limit on number of active concurrent connections
     *
     * @return Maximum limit for number of active concurrent connections
     * @throws ResourceException Thrown if an error occurs
     */
    @Override
    public int getMaxConnections() throws ResourceException {
        return 50;
    }

    /**
     * Returns name of the user associated with the ManagedConnection instance
     *
     * @return Name of the user
     * @throws ResourceException Thrown if an error occurs
     */
    @Override
    public String getUserName() throws ResourceException {
        return null;
    }

}
