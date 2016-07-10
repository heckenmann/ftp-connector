package de.heckenmann.jca.ftpconnector;

import java.util.List;

/**
 * @author heckenmann
 */
public interface FtpConnection {

    public byte[] get(String filename);

    public boolean write(String filename, byte[] content);

    public boolean delete(String filename);

    public List<String> list();
}
