package com.swedbank.decathlon.commandline;

import com.swedbank.decathlon.common.Error;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Created by H20016276 on 4/13/2017.
 */
public class FilePamaterValidatorTest {

    /**
     * File parameter validator test when file not found.
     */
    @Test
    public void fileParameterValidatorFileNotFound() {
        FileParameterValidator fv = new InputFileValidator();

        fv.validate("TestDataNoFile.txt");

        //file doesn't exist
        assertEquals("File not found message validation.", Error.FILE_NOT_FOUND.getMessage(), fv.getErrorMessage());

    }

    /**
     *  File parameter validator test when file has unsupported extension.
     */
    @Test
    public void fileParameterValidatorFileUnsupportedExtension() {

        FileParameterValidator fv = new InputFileValidator();

        File f = new File("TestData.xml");

        try {
            f.createNewFile();
        } catch (IOException e) {
            System.err.println("Can't create TestData.xml file.");
        }
        fv.validate("TestData.xml");
        assertEquals("Unsupported extension.", Error.FILE_UNSUPPORTED_EXT.getMessage(), fv.getErrorMessage());

        f.delete();
    }

    /**
     *  File parameter validator test when file has no extension.
     */
    @Test
    public void fileParameterValidatorFileNoExtension() {

        FileParameterValidator fv = new InputFileValidator();

        File f = new File("TestData");

        try {
            if (!f.createNewFile()) {
                System.out.println("Cant create file.");
            }
        } catch (IOException e) {
            System.err.println("Can't create TestData.xml file.");
        }
        fv.validate("TestData");
        assertEquals("Unsupported extension.", Error.FILE_NO_EXT.getMessage(), fv.getErrorMessage());

        f.delete();
    }


    /**
     *  File parameter validator test when file is ok.
     */
    @Test
    public void fileParameterValidatorFileOK() {
        FileParameterValidator fv = new InputFileValidator();

        File f = new File("TestReadData.txt");

        try {
            if (!f.createNewFile()) {
                System.out.println("Cant create file.");
            }

        } catch (IOException e) {
            System.err.println("Can't create TestReadData.txt file.");
        }

        fv.validate("TestReadData.txt");

        assertEquals("Can't read file", true, fv.getErrorMessage().isEmpty());

        f.delete();
    }


}
