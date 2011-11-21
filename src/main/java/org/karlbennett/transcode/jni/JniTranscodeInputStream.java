package org.karlbennett.transcode.jni;

import org.karlbennett.transcode.media.io.MediaFile;
import org.karlbennett.transcode.media.io.TranscodeInputStream;

import java.io.IOException;

/**
 * User: karl
 * Date: 21/11/11
 * <p/>
 * A  <code>TranscodeInputStream</code> implemented with native code.
 */
public class JniTranscodeInputStream implements TranscodeInputStream {

    private long pointer; // This will hold the reference to the native class.

    private MediaFile input; // The input media file.
    private MediaFile output; // The properties of the transcoded output file.


    /**
     * Initialise the native class and return it's pointer.
     *
     * @param input  - the input media file.
     * @param output - the trancoded output media file.
     *
     * @return the reference to the native class.
     */
    private native long init(MediaFile input, MediaFile output);

    /**
     *  Read a bytes worth of data from the native transcoded stream.
     *
     * @param pointer - the reference to the native class.
     *
     * @return the byte that was read.
     */
    private native int nativeRead(long pointer);

    /**
     * Try and read as meany bytes from the native transcoded stream that will fit into the provided array .
     *
     * @param pointer - the reference to the native class.
     * @param bytes - the array to hold the bytes that were read.
     * @return the number of bytes read.
     */
    private native int nativeRead(long pointer, byte[] bytes);

    /**
     * Try and read <code>len</code> bytes from the native transcoded stream into the provided byte array, where writing into the provided
     * array will start at <code>off</code> index.
     *
     * @param pointer - the reference to the native class.
     * @param bytes - the array to hold the bytes that were read.
     * @param off   - the offset within the bytes array where the bytes should start being inserted.
     * @param len   - the maximum number of bytes that should be read.
     * @return the number of bytes read.
     */
    private native int nativeRead(long pointer, byte[] bytes, int off, int len);


    /**
     * Create a transcode input stream that has <code>input</code> as it's source and produces a media file stream with the properties
     * provided in <code>output</code>.
     *
     * @param input  - the input media file.
     * @param output - the trancoded output media file.
     */
    public JniTranscodeInputStream(MediaFile input, MediaFile output) {

        this.pointer = init(input, output);

        this.input = input;
        this.output = output;
    }


    @Override
    public int read() throws IOException {

        return nativeRead(pointer);
    }

    @Override
    public int read(byte[] bytes) throws IOException {

        return nativeRead(pointer, bytes);
    }

    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {

        return nativeRead(pointer, bytes, off, len);
    }

    /**
     * Get the input media file.
     *
     * @return the input media file.
     */
    public MediaFile getInput() {
        return input;
    }

    /**
     * Get the output media file.
     *
     * @return the output media file.
     */
    public MediaFile getOutput() {
        return output;
    }
}
