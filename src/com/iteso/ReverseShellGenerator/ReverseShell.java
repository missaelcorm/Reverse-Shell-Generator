/**
 * ReverseShell
 */
package com.iteso.ReverseShellGenerator;

public class ReverseShell {

    // Attributes
    private String localIP = "10.10.10.10";
    private int localPort = 9000;
    private boolean requireRootPermissions = false;

    private final static int MIN_PORT = 0;
    private final static int MAX_PORT = 65536;
    public ReverseShell(){
        setLocalIP(this.localIP);
        setLocalPort(this.localPort);
        setPortRequireRootPermissions();
    }
    public void setLocalIP(String localIP){
            this.localIP = localIP;
    }

    public void setLocalPort(int localPort){
        if(localPort >= MIN_PORT && localPort <=  MAX_PORT)
            this.localPort = localPort;
    }

    public String getLocalIP(){
        return this.localIP;
    }

    public int getLocalPort(){
        return this.localPort;
    }

}