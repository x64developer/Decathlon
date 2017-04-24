package com.swedbank.decathlon.commandline;

import com.swedbank.decathlon.common.Error;
import com.swedbank.decathlon.file.FileType;
import com.swedbank.decathlon.file.FileUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * The abstract Class FileParameterValidator
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.commandline.FileParameterValidator)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
abstract class FileParameterValidator implements IValidator {

    protected String errorMessage = "";
    protected String infoMessage = "";

    private EnumSet<FileType> supportedFormats;

    @Override
    public boolean validate(final String value) {

        errorMessage = "";
        Path path;

        if (!value.contains(File.pathSeparator) && !value.contains("\\")) {
            final String fullPath = Paths.get("").toAbsolutePath() + File.separator + value;
            path = Paths.get(fullPath);
        } else {
            path = Paths.get(value);
        }

        return checkFile(path) && validExtension(path);
    }

    /**
     * Check does file exist and accessible for application.
     *
     * @param filePath
     * @return
     */
    abstract boolean checkFile(final Path filePath);

    /**
     * Get valid file types.
     * @return
     */
    abstract EnumSet<FileType> getValidFileTypes();

    /**
     * Check does extension valid for known files types to the app.
     *
     * @param filePath
     * @return
     */
    private boolean validExtension(final Path filePath) {

        String ext = FileUtil.getExtension(filePath.toString());
        if (ext.isEmpty()) {
            errorMessage = Error.FILE_NO_EXT.getMessage();
            return false;
        }
        //Iterating over known file types
        for (FileType ft : getValidFileTypes()) {
            if (ext.toLowerCase().equals(ft.name().toLowerCase())) {
                return true;
            }
        }
        errorMessage = String.format(Error.FILE_UNSUPPORTED_EXT.getMessage(), getValidFileTypes());
        return false;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getInfoMessage() {
        return infoMessage;
    }
}
