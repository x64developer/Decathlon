package com.swedbank.decathlon.file.parsers;

import com.swedbank.decathlon.DecathlonApp;
import com.swedbank.decathlon.commandline.Parameter;
import com.swedbank.decathlon.data.Decathlon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The abstract Class BaseParser
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.file.parsers.BaseParser)" target="_top">Saulius Bira</a>
 *         Date: 4/20/2017
 */
public abstract class BaseParser implements Parser {

    /**
     * Parse error list.
     */
    protected List<String> parsingErrors;

    @Override
    public Path getDataFile() {
        String value = DecathlonApp.getInstance().getParameterContext().get(Parameter.INPUT_FILE);
        return Paths.get(value);
    }

    @Override
    public Decathlon parse() throws IOException {
        final Decathlon d = parseFile(getDataFile());
        if (!isSuccessful()) {
            Path currentRelativePath = Paths.get("");
            Path parseErrorFile = Paths.get(currentRelativePath.toAbsolutePath().normalize() + File.separator + "DecathlonParse.errors.log");
            System.err.println(String.format("Parsing errors detected! Error count: %d. Error details logged to %s file. Please fix them and try to run app again.",
                    parsingErrors.size(), parseErrorFile.toString()));
            Files.write(parseErrorFile, getParseErrors(), Charset.defaultCharset());
        }
        return d;
    }

    protected abstract Decathlon parseFile(Path inputFile) throws IOException;

    @Override
    public boolean isSuccessful() {
        return parsingErrors.isEmpty();
    }

    @Override
    public List<String> getParseErrors() {
        return parsingErrors;
    }
}
