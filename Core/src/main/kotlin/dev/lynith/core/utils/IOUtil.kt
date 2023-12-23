package dev.lynith.core.utils

import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.file.Files

object IOUtil {

    @JvmStatic
    @Throws(IOException::class)
    fun resourceToByteBuffer(path: String, clazz: Class<*>): ByteBuffer {
        val path = path.trim { it <= ' ' }
        val bytes: ByteArray
        val stream: InputStream?
        val file = File(path)

        stream = if (file.exists() && file.isFile()) {
            Files.newInputStream(file.toPath())
        } else {
            clazz.getResourceAsStream(path)
        }

        if (stream == null) {
            throw FileNotFoundException(path)
        }

        bytes = IOUtils.toByteArray(stream)
        val data = ByteBuffer.allocateDirect(bytes.size).order(ByteOrder.nativeOrder()).put(bytes)

        (data as Buffer).flip()

        return data

    }
}
