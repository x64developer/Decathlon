package com.swedbank.decathlon.commandline;

/**
 * The Interface IValidator
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.commandline.IValidator)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public interface IValidator {

    /**
     * Validates value
     *
     * @param value
     * @return
     */
    boolean validate(final String value);

    /**
     * Get error message if occurred during validation.
     *
     * @return
     */
    String getErrorMessage();

    /**
     * Get info message
     *
     * @return
     */
    String getInfoMessage();
}
