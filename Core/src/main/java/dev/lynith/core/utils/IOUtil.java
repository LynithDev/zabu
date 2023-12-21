package dev.lynith.core.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.system.MemoryUtil.memSlice;

public class IOUtil {

    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = ByteBuffer.allocateDirect(newCapacity).order(ByteOrder.nativeOrder());
        ((Buffer) newBuffer).flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public static ByteBuffer resourceToByteBuffer(String path) throws IOException {
        ByteBuffer data = null;
        byte[] bytes;
        path = path.trim();
        if (path.startsWith("http")) {
            bytes = IOUtils.toByteArray(new URL(path));
        } else {
            InputStream stream;
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                stream = new FileInputStream(file);
            } else {
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            }
            if (stream == null) {
                throw new FileNotFoundException(path);
            }
            bytes = IOUtils.toByteArray(stream);
        }
        data = ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder()).put(bytes);
        ((Buffer) data).flip();
        return data;
    }

}
