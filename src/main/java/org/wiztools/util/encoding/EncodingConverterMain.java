package org.wiztools.util.encoding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import static org.wiztools.util.encoding.ExitValues.*;

/**
 *
 * @author subwiz
 */
public class EncodingConverterMain {
    
    static final String cmdHelp = "`convert' command requires 5 parameters:\n" +
                    "\t1. Input file\n" +
                    "\t2. Input encoding\n" +
                    "\t3. Output file\n" +
                    "\t4. Output encoding\n" +
                    "\t5. Newline character of output file [Can be one of: CR, LF or CRLF]\n";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] arg) {
        if(arg.length != 5){
            System.err.println(cmdHelp);
            System.exit(COMMAND_LINE_ERROR);
        }
        
        try{
            File inFile = new File(arg[0]);
            Charset inCharset = Charset.forName(arg[1]);
            File outFile = new File(arg[2]);
            Charset outCharset = Charset.forName(arg[3]);
            
            // Newline character processing:
            String t = arg[4];
            String newLine = null;
            
            if("CR".equals(t)){
                newLine = "\r";
            }
            else if("LF".equals(t)){
                newLine = "\n";
            }
            else if("CRLF".equals(t)){
                newLine = "\r\n";
            }
            else{
                System.err.println("Unrecognized new-line character (should be one of: CR, LF, CRLF): " + t);
                System.exit(UNRECOGNIZED_NEWLINE);
            }

            // Check for in-file existence
            if((!inFile.exists()) || (!inFile.canRead())){
                System.err.println("Input file does not exist, or cannot read!");
                System.exit(FILE_NOT_EXISTS_NOT_READABLE);
            }
            
            // Check if in-file and out-file are both the same
            if(inFile.equals(outFile)){
                System.err.println("Output file should be different from input file.");
                System.exit(IN_OUT_FILE_SAME);
            }

            // Check if out-file exists
            if(outFile.exists()){
                System.err.println("Out file exists! Please delete existing file, or give another out file.");
                System.exit(OUT_FILE_EXISTS);
            }
            
            // Now, convert!
            try{
                EncodingUtil.convert(inFile,
                        inCharset,
                        outFile,
                        outCharset,
                        newLine);
            }
            catch(UnsupportedEncodingException ex){
                System.err.println(ex.getMessage());
            }
            catch(FileNotFoundException ex){
                System.err.println(ex.getMessage());
            }
            catch(IOException ex){
                System.err.println(ex.getMessage());
            }
            System.exit(SUCCESS);
        }
        catch(IllegalCharsetNameException ex){
            System.err.println("IllegalCharsetName: " + ex.getMessage());
            System.exit(CHARSET_ERROR);
        }
        catch(UnsupportedCharsetException ex){
            System.err.println("UnsupportedCharset: " + ex.getMessage());
            System.exit(CHARSET_ERROR);
        }
    }
}
