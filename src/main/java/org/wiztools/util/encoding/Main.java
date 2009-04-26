package org.wiztools.util.encoding;

import static org.wiztools.util.encoding.ExitValues.*;

/**
 *
 * @author Subhash Chandran <subhash.chandran@sella.it>
 */
public class Main {
    
    static final String cmdHelp = "Usage: java -jar text-encoding-util.jar <main_command> <sub_parameter(s)>\n\n" +
            "Main command can be one of:\n" +
            "\tconvert\n" +
            "\ttest\n" +
            "\thelp\n";
    
    private static String[] excludeFirstArrayCopy(String[] arg){
        String[] arr = new String[arg.length - 1];
        for(int i = 0; i < (arg.length-1); i++){
            arr[i] = arg[i+1];
        }
        return arr;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] arg) {
        if(arg.length < 1){
            System.err.println(cmdHelp);
            System.exit(COMMAND_LINE_ERROR);
        }
        else{
            String mainCommand = arg[0];
            if("convert".equals(mainCommand)){
                EncodingConverterMain.main(excludeFirstArrayCopy(arg));
            }
            else if("test".equals(mainCommand)){
                EncodingTesterMain.main(excludeFirstArrayCopy(arg));
            }
            else if("help".equals(mainCommand)){
                System.out.println(cmdHelp + "\n" + EncodingConverterMain.cmdHelp + "\n" + EncodingTesterMain.cmdHelp);
                System.exit(SUCCESS);
            }
            else{
                System.err.println("Invalid main command specified!");
                System.err.println(cmdHelp);
                System.exit(COMMAND_LINE_ERROR);
            }
        }
    }
}
