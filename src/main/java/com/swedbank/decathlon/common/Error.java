package com.swedbank.decathlon.common;

/**
 * The Enum Error
 * <p>
 * Contains error message used in application.
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.common.Error)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum Error {

    FILE_NOT_FOUND("File %s doesn't exits, please check path is it correct."),
    FILE_CANT_READ("Cannot read file %s, please check permissions."),
    FILE_CANT_CREATE("Cannot create file, please check permissions."),
    FILE_CANT_WRITE("Cannot write to file, please check permissions."),
    FILE_NO_EXT("File should have extension."),
    FILE_UNSUPPORTED_EXT("Application doesn't support this type of file. Supported types %s.");

    /**
     * The message
     */
    private final String message;

    /**
     * Error constructor
     *
     * @param message
     */
    Error(String message) {
        this.message = message;
    }

    /**
     * Get message
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }
}
