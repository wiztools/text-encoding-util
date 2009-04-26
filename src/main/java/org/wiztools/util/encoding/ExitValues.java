package org.wiztools.util.encoding;

/**
 *
 * @author subwiz
 */
public interface ExitValues {
    public int SUCCESS = 0;
    public int COMMAND_LINE_ERROR = 1;
    public int UNRECOGNIZED_NEWLINE = 2;
    public int FILE_NOT_EXISTS_NOT_READABLE = 3;
    public int OUT_FILE_EXISTS = 4;
    public int IN_OUT_FILE_SAME = 5;
    public int CHARSET_ERROR = 6;
    public int STREAM_NOT_IN_ENCODING_TYPE = 7;
    public int IO_ERROR = 8;
}
