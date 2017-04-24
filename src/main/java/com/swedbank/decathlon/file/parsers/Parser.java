package com.swedbank.decathlon.file.parsers;

import com.swedbank.decathlon.data.Decathlon;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * The Interface Parser
 * Created by Saulius Bira on 4/12/2017.
 */
public interface Parser {

    /**
     * Get data file
     * @return
     */
    Path getDataFile();

    /**
     * Perform parsing.
     * @return
     * @throws IOException
     */
    Decathlon parse() throws IOException;

    /**
     * Parsed data successfully
     *
     * @return
     */
    boolean isSuccessful();

    /**
     * Get parse errors
     *
     * @return
     */
    List<String> getParseErrors();
}
