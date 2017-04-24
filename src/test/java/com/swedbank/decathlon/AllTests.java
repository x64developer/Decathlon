package com.swedbank.decathlon;

import com.swedbank.decathlon.commandline.FilePamaterValidatorTest;
import com.swedbank.decathlon.file.parsers.CSVParserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by H20016276 on 4/21/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        FilePamaterValidatorTest.class,
        CSVParserTest.class}
)

public class AllTests {
}
