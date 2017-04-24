package com.swedbank.decathlon.commandline;

import com.swedbank.decathlon.common.Error;
import com.swedbank.decathlon.file.FileType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

/**
 * The Class InputFileValidator
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.commandline.InputFileValidator)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public class InputFileValidator extends FileParameterValidator {

    @Override
    boolean checkFile(Path filePath) {

        if (!Files.exists(filePath)) {
            errorMessage = String.format(Error.FILE_NOT_FOUND.getMessage(), filePath.toAbsolutePath().normalize());
        }

        if (!Files.isReadable(filePath) && errorMessage.isEmpty()) {
            errorMessage = String.format(Error.FILE_CANT_READ.getMessage(), filePath.toAbsolutePath().normalize());
        }

        return errorMessage.isEmpty();
    }

    @Override
    EnumSet<FileType> getValidFileTypes() {
        return EnumSet.of(FileType.TXT);
    }
}
