package de.heckenmann.jca.ftpconnector;

import java.io.Serializable;
import javax.resource.Referenceable;
import javax.resource.ResourceException;

/**
 *
 * @author heckenmann
 */
public interface FtpConnectionFactory extends Serializable, Referenceable {

    public FtpConnection getConnection() throws ResourceException;

}
