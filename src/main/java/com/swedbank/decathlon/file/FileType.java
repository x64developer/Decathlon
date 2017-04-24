package com.swedbank.decathlon.file;

import com.swedbank.decathlon.file.parsers.CSVParser;
import com.swedbank.decathlon.file.parsers.Parser;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum FileType
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.file.FileType)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum FileType {
    TXT(new CSVParser(";")),
    XML();

    private Parser p;

    /**
     * FileType constructor
     */
    FileType() {
        p = null;
    }

    /**
     * FileType constructor
     *
     * @param p
     */
    FileType(Parser p) {
        this.p = p;
    }

    /**
     * Get proper parser for file type
     *
     * @return
     */
    public Parser getDataParser() {
        return this.p;
    }

    /**
     * FileType lookup map
     */
    private static final Map<String, FileType> lookup =
            new HashMap();

    static {
        //Create reverse lookup hash map
        for (FileType d : FileType.values())
            lookup.put(d.name().toLowerCase(), d);
    }

    /**
     * Get file type by extension
     *
     * @param extension
     * @return
     */
    public static FileType getFileTypeByExtension(final String extension) {
        return lookup.get(extension);
    }
}
