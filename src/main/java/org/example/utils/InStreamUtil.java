package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Derek-huang
 */
public class InStreamUtil {
    public static InputStream loadFileToStreamFromResource(String filepath) {
        InputStream in = InStreamUtil.class.getResourceAsStream(filepath);
        return in;
    }

    /**
     * load Properties from class path
     * @param file
     * @param property
     * @throws IOException
     */
    public static void loadPropertyFileFromResource(String filepath, Properties property) throws IOException {
        try (InputStream in = InStreamUtil.class.getResourceAsStream(filepath)) {
            if (property != null) {
                property.load(in);
            }
        }
    }
}
