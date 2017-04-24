package com.swedbank.decathlon.commandline;

import com.swedbank.decathlon.DecathlonApp;

/**
 * The Enum Parameter
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.commandline.Parameter)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum Parameter {

    INPUT_FILE(true, "inputFile", "Please enter input file path: ", new InputFileValidator()),
    OUTPUT_FILE(true, "outputFile", "Please enter output file path: ", new OutputFileValidator());

    /**
     * Is parameter required
     */
    private final boolean required;
    /**
     * Parameter name
     */
    private final String name;
    /**
     * Parameter input message
     */
    private final String inputDescription;
    /**
     * Parameter validator
     */
    private final IValidator validator;

    /**
     * Parameter enumerator constructor
     *
     * @param required
     * @param name
     * @param inputDescription
     * @param validator
     */
    Parameter(final boolean required, final String name, final String inputDescription, IValidator validator) {
        this.required = required;
        this.name = name;
        this.inputDescription = inputDescription;
        this.validator = validator;
    }

    /**
     * Is required
     *
     * @return
     */
    public boolean isRequired() {
        return this.required;
    }

    /**
     * Get value from app parameter context
     *
     * @return
     */
    public String getValue() {
        return DecathlonApp.getInstance().getParameterContext().get(this);
    }

    /**
     * Check is parameter valid
     *
     * @param value
     * @return
     */
    public boolean isValid(final String value) {
        return this.validator.validate(value);
    }

    /**
     * Get error message from validator.
     *
     * @return
     */
    public String getErrorMessage() {
        return validator.getErrorMessage();
    }

    /**
     * Get infor message from validator.
     * @return
     */
    public String getInfoMessage(){
        return  validator.getInfoMessage();
    }

    /**
     * Get parameter name
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get input notification description
     *
     * @return
     */
    public String getInputDescription() {
        return this.inputDescription;
    }
}
