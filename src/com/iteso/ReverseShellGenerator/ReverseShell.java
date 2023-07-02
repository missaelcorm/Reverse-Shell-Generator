/**
 * ReverseShell
 */
package com.iteso.ReverseShellGenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iteso.ReverseShellGenerator.Interfaces.ReverseGetShellCode;
import com.iteso.ReverseShellGenerator.Reverse.Shells.*;

public class ReverseShell implements ReverseGetShellCode{

    // Attributes
    protected String localIP = "10.10.10.10";
    protected int localPort = 9000;
    private boolean requireRootPermissions = false;
    protected LISTENER listener = null;
    protected SHELLS shellType = null;
    protected String shell = "";

    // Statics Variables
    private final static int PRIVILEGED_PORTS = 1024;
    private final static int MIN_PORT = 0;
    private final static int MAX_PORT = 65536;
    private final static String IP_REGEXP = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    // Listeners
    public enum LISTENER{
        NC, NC_FREEBSD, BUSYBOX_NC, NCAT, NCAT_EXE, NCAT_TLS,
        RLWRAP_NC, RUST_CAT, RUSTCAT_COMMAND_HISTORY, PWNCAT,
        WINDOWS_CONPTY, SOCAT, SOCAT_TTY, POWERCAT, MSFCONSOLE, HOAXSHELL
    }

    // Reverse Shells
    public enum REVERSE_TYPE{
        BASH_i, BASH_196, BASH_READ_LINE, BASH_5, BASH_UDP, NC_MKFIFO, NC_e, NC_exe_e, BUSYBOX_NC_e,
        NC_c, NCAT_e, NCAT_EXE_e, NCAT_UDP, RUSTCAT, C, C_WINDOWS, C_SHARP_TCP_CLIENT, C_SHARP_BASH_i,
        HASHKELL_1, PERL, PERL_NO_SH, PERL_PENTESTMONKEY, PHP_PENTESTMONKEY, PHP_IVAN_SINCEK, PHP_CMD,
        PHP_CMD_2, PHP_CMD_SMALL, PHP_EXEC, PHP_SHELL_EXEC, PHP_SYSTEM, PHP_PASSTHRU, PHP, PHP_POPEN,
        PHP_PROC_OPEN, WINDOWS_CONPTY, POWERSHELL_1, POWERSHELL_2, POWERSHELL_3, POWERSHELL_4_TLS,
        POWERSHELL_3_B64, PYTHON_1, PYTHON_2, PYTHON3_1, PYTHON3_2, PYTHON3_WINDOWS, PYTHON3_SHORTEST,
        RUBY_1, RUBY_NO_SH, SOCAT_1, SOCAT_2_TTY, SQLITE3_NC_MKFIFO, NODE_JS, NODE_JS_2, JAVA_1, JAVA_2,
        JAVA_3, JAVA_WEB, JAVA_TWO_WAY, JAVASCRIPT, GROOVY, TELNET, ZSH, LUA_1, LUA_2, GOLANG, VLANG,
        AWK, DART, CRYSTAL_SYSTEM, CRYSTAL_CODE
    }

    // Shells
    public enum SHELLS{
        SH("sh"), BIN_SH("/bin/sh"), BASH("bash"), BIN_BASH("/bin/bash"), CMD("cmd"),
        POWERSHELL("powershell"), PWSH("pwsh"), ASH("ash"), BSH("bsh"), CSH("csh"),
        KSH("ksh"), ZSH("zsh"), PDKSH("pdksh"), TCSH("tcsh"), MKSH("mksh"), DASH("dash");

        protected String shell = "";
        SHELLS(String shell){
            setShell(shell);
        }

        public void setShell(String shell) {
            this.shell = shell;
        }

        public String getShell() {
            return shell;
        }
    }

    protected ReverseShell(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        setLocalIP(localIP);
        setLocalPort(localPort);
        setPortRequireRootPermissions();
        setListener(listener);
        setShellType(shellType);
    }

    public static ReverseShell getShellInstance(String localIP, int localPort, LISTENER listener, REVERSE_TYPE revType, SHELLS shellType){
        switch(revType){
            case BASH_i:
                return new Bash_i(localIP, localPort, listener, shellType);
            case BASH_196:
                break;
            case BASH_5:
                break;
            case BASH_READ_LINE:
                break;
            case BASH_UDP:
                break;
            case NC_MKFIFO:
                break;
            case NC_e:
                break;
            case AWK:
                break;
            case BUSYBOX_NC_e:
                break;
            case C:
                break;
            case CRYSTAL_CODE:
                break;
            case CRYSTAL_SYSTEM:
                break;
            case C_SHARP_BASH_i:
                break;
            case C_SHARP_TCP_CLIENT:
                break;
            case C_WINDOWS:
                break;
            case DART:
                break;
            case GOLANG:
                break;
            case GROOVY:
                break;
            case HASHKELL_1:
                break;
            case JAVASCRIPT:
                break;
            case JAVA_1:
                break;
            case JAVA_2:
                break;
            case JAVA_3:
                break;
            case JAVA_TWO_WAY:
                break;
            case JAVA_WEB:
                break;
            case LUA_1:
                break;
            case LUA_2:
                break;
            case NCAT_EXE_e:
                break;
            case NCAT_UDP:
                break;
            case NCAT_e:
                break;
            case NC_c:
                break;
            case NC_exe_e:
                break;
            case NODE_JS:
                break;
            case NODE_JS_2:
                break;
            case PERL:
                break;
            case PERL_NO_SH:
                break;
            case PERL_PENTESTMONKEY:
                break;
            case PHP:
                break;
            case PHP_CMD:
                break;
            case PHP_CMD_2:
                break;
            case PHP_CMD_SMALL:
                break;
            case PHP_EXEC:
                break;
            case PHP_IVAN_SINCEK:
                break;
            case PHP_PASSTHRU:
                break;
            case PHP_PENTESTMONKEY:
                break;
            case PHP_POPEN:
                break;
            case PHP_PROC_OPEN:
                break;
            case PHP_SHELL_EXEC:
                break;
            case PHP_SYSTEM:
                break;
            case POWERSHELL_1:
                break;
            case POWERSHELL_2:
                break;
            case POWERSHELL_3:
                break;
            case POWERSHELL_3_B64:
                break;
            case POWERSHELL_4_TLS:
                break;
            case PYTHON3_1:
                break;
            case PYTHON3_2:
                break;
            case PYTHON3_SHORTEST:
                break;
            case PYTHON3_WINDOWS:
                break;
            case PYTHON_1:
                break;
            case PYTHON_2:
                break;
            case RUBY_1:
                break;
            case RUBY_NO_SH:
                break;
            case RUSTCAT:
                break;
            case SOCAT_1:
                break;
            case SOCAT_2_TTY:
                break;
            case SQLITE3_NC_MKFIFO:
                break;
            case TELNET:
                break;
            case VLANG:
                break;
            case WINDOWS_CONPTY:
                break;
            case ZSH:
                break;
            default:
                break;
        }

        return null;
    }

    public void setLocalIP(String localIP){
        if(isIPValid())
            this.localIP = localIP;
    }

    public void setLocalPort(int localPort){
        if(localPort >= MIN_PORT && localPort <=  MAX_PORT)
            this.localPort = localPort;
    }

    public void setListener(LISTENER listener) {
        this.listener = listener;
    }

    public void setShellType(SHELLS shellType) {
        this.shellType = shellType;
        this.shell = this.shellType.getShell();
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

    public SHELLS getShellType() {
        return shellType;
    }

    public String getShell() {
        return shell;
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
/* 
    public String getShellCode(){
        if(this instanceof Bash_i)
            return ((Bash_i)this).getShell();

        return null;
    }*/

    public String getListenerCode(){
        String sudoStr = isPortPrivileged() ? "sudo " : "";

        switch(this.listener){
            case NC:
                return String.format("%snc -lvnp %s", sudoStr, getLocalPort());
            case BUSYBOX_NC:
                return String.format("%sbusybox nc -lp %s", sudoStr, getLocalPort());
            case HOAXSHELL:
                return String.format("%spython3 -c \"$(curl -s https://raw.githubusercontent.com/t3l3machus/hoaxshell/main/revshells/hoaxshell-listener.py)\" -t {type} -p %s", sudoStr, getLocalPort());
            case MSFCONSOLE:
                return "NOT SUPPORTED";
            //    return String.format("%smsfconsole -q -x \"use multi/handler; set payload {payload}; set lhost %s; set lport %s; exploit\"", sudoStr, getLocalIP(), getLocalPort());
            case NCAT_EXE:
                return String.format("%sncat.exe -lvnp %s", sudoStr, getLocalPort());
            case NCAT:
                return String.format("%sncat -lvnp %s", sudoStr, getLocalPort());
            case NCAT_TLS:
                return String.format("%sncat --ssl -lvnp %s", sudoStr, getLocalPort());
            case NC_FREEBSD:
                return String.format("%snc -lvn %s", sudoStr, getLocalPort());
            case POWERCAT:
                return String.format("%spowercat -l -p %s", sudoStr, getLocalPort());
            case PWNCAT:
                return String.format("%spython3 -m pwncat -lp %s", sudoStr, getLocalPort());
            case RLWRAP_NC:
                return String.format("%srlwrap -cAr nc -lvnp %s", sudoStr, getLocalPort());
            case RUSTCAT_COMMAND_HISTORY:
                return String.format("%srcat -lHp %s", sudoStr, getLocalPort());
            case RUST_CAT:
                return String.format("%srcat -lp %s", sudoStr, getLocalPort());
            case SOCAT:
                return String.format("%ssocat -d -d TCP-LISTEN:%s STDOUT", sudoStr, getLocalPort());
            case SOCAT_TTY:
                return String.format("%ssocat -d -d file:`tty`,raw,echo=0 TCP-LISTEN:%s", sudoStr, getLocalPort());
            case WINDOWS_CONPTY:
                return String.format("%sstty raw -echo; (stty size; cat) | nc -lvnp %s", sudoStr, getLocalPort());
            default:
                break;
        }

        return null;
    }

    @Override
    public String getShellCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShell'");
    }

}