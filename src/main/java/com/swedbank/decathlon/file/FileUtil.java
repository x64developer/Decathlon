package com.swedbank.decathlon.file;

import java.io.File;

/**
 * The Class FileUtil
 * <p>
 * This is helper class it static helper methods.
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.file.FileUtil)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public class FileUtil {

    /**
     * Extracts file extension from file path.
     *
     * @param fullPath
     * @return
     */
    public static String getExtension(final String fullPath) {
        String ext = "";

        // c:/test.dot/data.txt
        final String fileName = fullPath.substring(fullPath.lastIndexOf(File.separator) + 1);
        if (fileName.contains(".")) {
            ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        }
        return ext;
    }
}
