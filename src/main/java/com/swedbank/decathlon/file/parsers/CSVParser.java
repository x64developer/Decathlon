package com.swedbank.decathlon.file.parsers;

import com.swedbank.decathlon.data.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * The Class CSVParser
 * <p>
 * CSV parser
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.file.parsers.CSVParser)" target="_top">Saulius Bira</a>
 *         Date: 4/20/2017
 */
public class CSVParser extends BaseParser {


    /**
     * Splitter for CSV file
     */
    private String splitter;


    /**
     * Create CSVParser with custom splitter.
     *
     * @param splitter
     */
    public CSVParser(String splitter) {
        super();
        this.splitter = splitter;
        this.parsingErrors = new ArrayList<>();
    }

    /**
     * Create CSVParser with default "," splitter.
     */
    public CSVParser() {
        //set default standard scv splitter
        this(",");
    }

    @Override
    public Decathlon parseFile(Path sourcePath) throws IOException {

        BufferedReader br = Files.newBufferedReader(sourcePath);
        String line;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<ResultLine> results = new ArrayList<>();
        int expectedColumns = EventType.values().length + 1;
        int lineCount = 0;
        while ((line = br.readLine()) != null) {
            ++lineCount;
            String[] data = line.split(splitter);
            if (data.length != expectedColumns) {
                parsingErrors.add(String.format("Line %d: incorrect number of columns expected: %d, current count: %d, parsing skips this line.",
                        lineCount, expectedColumns, data.length));
                continue;
            }
            Athlete athlete = parseAthlete(data[0].split(" "), lineCount);
            List<Result> athleteResults = parseAthleteResults(data, lineCount);
            ResultLine rl = new ResultLine(athlete, athleteResults, ResultLine.getTotalPoints(athleteResults));
            results.add(rl);
        }
        br.close();
        //sort results by total score
        Collections.sort(results);
        Decathlon d = new Decathlon(year, results);
        return d;
    }

    /**
     * Parse athlete results from data line.
     *
     * @param athleteData
     * @param lineCount
     * @return
     */
    private List<Result> parseAthleteResults(final String[] athleteData, final int lineCount) {

        final List<Result> results = new ArrayList<>(EventType.values().length);

        int counter = 1;
        //Iterate over events
        for (EventType e : EventType.values()) {
            String athletePerformance = athleteData[counter];
            //Build result from data line
            Result.ResultBuilder rb = new Result.ResultBuilder(e, athletePerformance);
            float ap = 0f;
            try {
                ap = e.getUnit().getValueFromString(athletePerformance);
            } catch (NumberFormatException nex) {
                parsingErrors.add(String.format("Line %d: problems with parsing athlete performance %s. Please check data and make it valid.",
                        lineCount, athletePerformance));
            }
            rb.setPoints(e.countPoints(ap));
            results.add(rb.build());
            counter++;
        }
        return results;
    }

    /**
     * Parse athlete data from data line.
     *
     * @param athleteData
     * @param lineCount
     * @return
     */
    private Athlete parseAthlete(final String[] athleteData, final int lineCount) {
        Athlete a;
        switch (athleteData.length) {
            case 0:
                a = new Athlete("", "");
                parsingErrors.add(String.format("Line %d: empty data in athlete details", lineCount));
                break;
            case 1:
                a = new Athlete(athleteData[0], "");
                parsingErrors.add(String.format("Line %d: athlete data contains only name %s", lineCount,
                        athleteData[0]));
                break;
            case 2:
                a = new Athlete(athleteData[0], athleteData[1]);
                break;
            default:
                a = new Athlete(athleteData[0], athleteData[1]);
                parsingErrors.add(
                        String.format("Line %d: athlete data contains %d words only first two were parsed as name and surname.",
                                lineCount, athleteData.length));
                break;
        }
        return a;
    }

}
