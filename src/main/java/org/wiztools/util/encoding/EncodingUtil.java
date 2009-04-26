package org.wiztools.util.encoding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.MalformedInputException;

/**
 *
 * @author subwiz
 */
public class EncodingUtil {
    
    /**
     * Converts between the text-file encoding formats.
     * 
     * @param inFile The input file
     * @param inEnc Encoding type of input file
     * @param outFile The output file
     * @param outEnc Encoding type of output file
     * @param newLine The new-line character in output file.
     */
    public static void convert(final File inFile,
            final Charset inEnc,
            final File outFile,
            final Charset outEnc,
            final String newLine) 
            throws UnsupportedEncodingException,
            FileNotFoundException, IOException{
        
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            br = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(inFile), inEnc));
            
            pw = new PrintWriter(outFile, outEnc.name());
            
            String str = null;
            while((str = br.readLine()) != null){
                pw.print(str);
                pw.print(newLine);
            }
        }
        finally{
            if(br != null){
                try{
                    br.close();
                }
                catch(IOException ex){
                    System.err.println(ex.getMessage());
                }
            }
            if(pw != null){
                pw.flush();
                pw.close();
            }
        }
    }

    /*
     * This is the method used to test encoding.
     */
    public static void testEncoding(final InputStream in, final Charset charset) throws IOException, MalformedInputException {
        try{
            byte[] b = new byte[4096];
            CharsetDecoder decoder = charset.newDecoder();
            for (int n; (n = in.read(b)) != -1;) {
                CharBuffer charBuffer = null;

                // Can throw MalformedInputException:
                charBuffer = decoder.decode(ByteBuffer.wrap(b, 0, n));

                charBuffer.rewind(); // Bring the buffer's pointer to 0
            }
        }
        finally{
            try{
                in.close();
            }
            catch(IOException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
}
