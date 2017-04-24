package com.swedbank.decathlon.file.parsers;

import com.swedbank.decathlon.data.Decathlon;
import com.swedbank.decathlon.data.EventType;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by H20016276 on 4/21/2017.
 */
public class CSVParserTest {


    /**
     * Test how application will work when it get empty file.
     *
     * @throws Exception
     */
    @Test
    public void parseFileNoData() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("TestNoData.txt").getFile());
        Decathlon d = new CSVParser(";").parseFile(file.toPath());

        assertEquals(true, d.getResults().isEmpty());
    }

    /**
     * Test how application will work when it gets first line empty.
     *
     * @throws Exception
     */
    @Test
    public void parseFileFirstEmptyLine() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("TestEmptyFistLine.txt").getFile());
        CSVParser p = new CSVParser(";");
        Decathlon d = p.parseFile(file.toPath());

        assertEquals(3, d.getResults().size());
        assertEquals(1, p.getParseErrors().size());

    }

    /**
     * Test checks is data imported correctly and and calculations.
     *
     * @throws Exception
     */
    @Test
    public void parseFileTenRowsData() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("TestData.txt").getFile());
        Decathlon d = new CSVParser(";").parseFile(file.toPath());

        //Test import data
        assertEquals(10, d.getResults().size());
        assertEquals("1 - 3", d.getResults().get(1).getRank());
        assertEquals("Super1", d.getResults().get(1).getAthlete().getName());
        assertEquals("Super1", d.getResults().get(1).getAthlete().getSurname());
        assertEquals("10.395", d.getResults().get(1).getResults().get(0).getAthletePerformance());
        assertEquals("7.76", d.getResults().get(1).getResults().get(1).getAthletePerformance());
        assertEquals("18.4", d.getResults().get(1).getResults().get(2).getAthletePerformance());
        assertEquals("2.208", d.getResults().get(1).getResults().get(3).getAthletePerformance());
        assertEquals("46.17", d.getResults().get(1).getResults().get(4).getAthletePerformance());
        assertEquals("13.8", d.getResults().get(1).getResults().get(5).getAthletePerformance());
        assertEquals("56.17", d.getResults().get(1).getResults().get(6).getAthletePerformance());
        assertEquals("5.287", d.getResults().get(1).getResults().get(7).getAthletePerformance());
        assertEquals("77.19", d.getResults().get(1).getResults().get(8).getAthletePerformance());
        assertEquals("3.53.79", d.getResults().get(1).getResults().get(9).getAthletePerformance());

        assertEquals("100 m", d.getResults().get(1).getResults().get(0).getEvent().getName());

        //Test calculation
        assertEquals(10000, d.getResults().get(1).getTotalPoints());
        for (int i = 0; i < EventType.values().length; i++) {
            assertEquals(1000, d.getResults().get(1).getResults().get(i).getPoints());
        }

        //check last record
        assertEquals("10", d.getResults().get(9).getRank());
        assertEquals(3084, d.getResults().get(9).getTotalPoints());

    }

    /**
     * Teste case to check if one line has not all athlete performances it will skipped and logged error in parse errors.
     *
     * @throws Exception
     */
    @Test
    public void parseFileOneBadLine() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("TestOneBadLine.txt").getFile());
        CSVParser p = new CSVParser(";");
        Decathlon d = p.parseFile(file.toPath());

        assertEquals(9, d.getResults().size());
        assertEquals(1, p.getParseErrors().size());
    }

    /**
     * Test various scenario when athlete data incorrect.
     * @throws Exception
     */
    @Test
    public void parseFileBadData() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("TestBadData.txt").getFile());
        CSVParser p = new CSVParser(";");
        Decathlon d = p.parseFile(file.toPath());

        assertEquals(3, d.getResults().size());
        assertEquals(5, p.getParseErrors().size());
    }

}