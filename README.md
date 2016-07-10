# JCA FTP Connector
INCOMPLETE

## Wildfly Subsystem Konfiguration
`
<subsystem xmlns="urn:jboss:domain:resource-adapters:4.0">
            <resource-adapters>
                <resource-adapter id="FtpConnector">
                    <archive>
                        FtpConnector.rar
                    </archive>
                    <transaction-support>NoTransaction</transaction-support>
                    <connection-definitions>
                        <connection-definition class-name="de.heckenmann.jca.ftpconnector.FtpManagedConnectionFactory" jndi-name="java:/ftp/localhost" pool-name="FtpConnectionFactory">
                            <config-property name="host">
                                localhost
                            </config-property>
                            <config-property name="password">
                                pw
                            </config-property>
                            <config-property name="port">
                                21
                            </config-property>
                            <config-property name="user">
                                user
                            </config-property>
                            <pool>
                                <min-pool-size>0</min-pool-size>
                                <max-pool-size>100</max-pool-size>
                            </pool>
                            <security>
                                <application/>
                            </security>
                        </connection-definition>
                    </connection-definitions>
                </resource-adapter>
            </resource-adapters>
        </subsystem>
`
