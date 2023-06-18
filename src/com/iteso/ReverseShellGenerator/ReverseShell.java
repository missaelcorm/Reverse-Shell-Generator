/**
 * ReverseShell
 */
package com.iteso.ReverseShellGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReverseShell {

    // Attributes
    private String localIP = "10.10.10.10";
    private int localPort = 9000;
    private boolean requireRootPermissions = false;

    // Statics Variables
    private final static int PRIVILEGED_PORTS = 1024;
    private final static int MIN_PORT = 0;
    private final static int MAX_PORT = 65536;
    private final static String IP_REGEXP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    // Listeners
    public static enum LISTENER{
        NC, NC_FREEBSD, BUSYBOX_NC, NCAT, NACT_EXE, NCAT_TLS,
        RLWRAP_NC, RUST_CAT, RUSTCAT_COMMAND_HISTORY, PWNCAT,
        WINDOWS_CONPTY, SOCAT_TTY, POWERCAT, MSFCONSOLE, HOAXSHELL
    }

    public ReverseShell(){
        setLocalIP(this.localIP);
        setLocalPort(this.localPort);
        setPortRequireRootPermissions();
    }

    public void setLocalIP(String localIP){
        if(isIPValid())
            this.localIP = localIP;
    }

    public void setLocalPort(int localPort){
        if(localPort >= MIN_PORT && localPort <=  MAX_PORT)
            this.localPort = localPort;
    }

    private boolean isPortPrivileged(){
        return this.localPort < PRIVILEGED_PORTS ? true : false;
    }

    private void setPortRequireRootPermissions(){
        this.requireRootPermissions = isPortPrivileged();
    }

    public String getLocalIP(){
        return this.localIP;
    }

    public int getLocalPort(){
        return this.localPort;
    }

    public boolean isIPValid(){
        Pattern pattern = Pattern.compile(IP_REGEXP, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.getLocalIP());
        return matcher.find();
    }

    public String toString(){
        return "{" +
                    "\"localIP\": " + "\"" + getLocalIP() + "\"," +
                    "\"localPort\": " + getLocalPort() + "," +
                    "\"requireRootPermission\": " + isPortPrivileged() +
                "}";
    }
}