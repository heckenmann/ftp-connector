package de.heckenmann.jca.ftpconnector;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

/**
 * @author heckenmann
 */
public class FtpConnectionImpl implements FtpConnection {

    private static final Logger LOG = Logger.getLogger(FtpConnectionImpl.class.getName());

    private FtpManagedConnection mc;

    private FtpManagedConnectionFactory mcf;

    private FTPClient client;

    /**
     * Konstruktor.
     *
     * @param mc
     * @param mcf
     */
    public FtpConnectionImpl(FtpManagedConnection mc, FtpManagedConnectionFactory mcf) {
        this.mc = mc;
        this.mcf = mcf;
    }

    /**
     * Erstellt einen Client.
     */
    private void login() {
        try {
            this.client = new FTPClient();
            client.connect(mcf.getHost(), mcf.getPort());
            client.login(mcf.getUser(), mcf.getPassword());
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Meldet sich vom ftp-Server ab.
     */
    private void logout() {
        try {
            this.client.logout();
            this.client.disconnect();
        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
    }

    @Override
    public byte[] get(String filename) {
        login();
        byte[] result = null;
        try {
            InputStream stream = client.retrieveFileStream(filename);
            BufferedInputStream bis = new BufferedInputStream(stream);
            result = new byte[bis.available()];
            bis.read(result);
            stream.close();
        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        logout();
        return result;
    }

    @Override
    public boolean write(String filename, byte[] content) {
        login();
        logout();
        return true;
    }

    @Override
    public List<String> list() {
        login();
        String[] names = null;
        try {
            names = client.listNames();
        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }
        logout();
        return Arrays.asList(names);
    }

    @Override
    public boolean delete(String filename) {
        login();
        try {
            client.deleteFile(filename);
        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
            return false;
        } finally {
            logout();
        }
        return true;
    }
}
