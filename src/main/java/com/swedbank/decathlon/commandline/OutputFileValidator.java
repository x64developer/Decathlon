package com.swedbank.decathlon.commandline;

import com.swedbank.decathlon.common.Error;
import com.swedbank.decathlon.common.Info;
import com.swedbank.decathlon.file.FileType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;

/**
 * The Class OutputFileValidator
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.commandline.OutputFileValidator)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public class OutputFileValidator extends FileParameterValidator {
    @Override
    boolean checkFile(Path filePath) {

        if (Files.exists(filePath)) {
            infoMessage = String.format(Info.FILE_OVERRIDE.getMessage(), filePath.toAbsolutePath().normalize());
        } else {
            try {
                Files.createFile(filePath);
            } catch (IOException ioe) {
                errorMessage = Error.FILE_CANT_CREATE.getMessage();
            }
        }

        if (!Files.isWritable(filePath)) {
            errorMessage = Error.FILE_CANT_WRITE.getMessage();
        }

        return errorMessage.isEmpty();
    }

    @Override
    EnumSet<FileType> getValidFileTypes() {
        return EnumSet.of(FileType.XML);
    }
}
