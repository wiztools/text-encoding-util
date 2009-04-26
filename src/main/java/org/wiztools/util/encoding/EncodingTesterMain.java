package org.wiztools.util.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnsupportedCharsetException;
import static org.wiztools.util.encoding.ExitValues.*;

/**
 *
 * @author subwiz
 */
public class EncodingTesterMain {
    
    static final String cmdHelp = "`test' command requires 2 parameters:\n" +
                    "\t1. File\n" +
                    "\t2. Encoding type to test against\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] arg) {
        if(arg.length != 2){
            System.err.println(cmdHelp);
            System.exit(COMMAND_LINE_ERROR);
        }
        
        File inFile = new File(arg[0]);
        
        if(!inFile.canRead()){
            System.err.println("`" + inFile.getName() + "' does not exist or is not readable.");
            System.exit(FILE_NOT_EXISTS_NOT_READABLE);
        }
        
        Charset charset = null;
        try{
            charset = Charset.forName(arg[1]);
            EncodingUtil.testEncoding(new FileInputStream(inFile), charset);
            System.out.println("`" + inFile.getName() + "' is compatible with " + charset.name() + ".");
        }
        catch(IllegalCharsetNameException ex){
            System.err.println("IllegalCharsetName: " + ex.getCharsetName());
            System.exit(CHARSET_ERROR);
        }
        catch(UnsupportedCharsetException ex){
            System.err.println("UnsupportedCharset: " + ex.getCharsetName());
            System.exit(CHARSET_ERROR);
        }
        catch(MalformedInputException ex){
            System.err.println("File ( " + inFile.getName() + " ) not encoded in " + charset.name() + ".");
            System.exit(STREAM_NOT_IN_ENCODING_TYPE);
        }
        catch(IOException ex){
            ex.printStackTrace(System.err);
            System.exit(IO_ERROR);
        }
    }
}
