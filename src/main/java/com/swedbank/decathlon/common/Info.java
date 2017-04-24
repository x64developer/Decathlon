package com.swedbank.decathlon.common;

/**
 * The Enum Info
 * <p>
 * Contains error message used in application.
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.common.Info)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum Info {

    FILE_OVERRIDE("Output file will be overridden %s.");

    /**
     * The message
     */
    private final String message;

    /**
     * Info constructor
     *
     * @param message
     */
    Info(String message) {
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
