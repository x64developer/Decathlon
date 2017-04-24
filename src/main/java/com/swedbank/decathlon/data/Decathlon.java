package com.swedbank.decathlon.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * The Class Decathlon
 * <p>
 * This class represent decathlon combined event, results.
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.Decathlon)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
@XmlRootElement(name = "decathlon")
@XmlAccessorType(XmlAccessType.FIELD)
public class Decathlon implements Serializable {

    /**
     * Decathlon year
     */
    private int year;
    /**
     * Decathlon results collection.
     */
    @XmlElement(name = "resultLine")
    private final List<ResultLine> results;

    /**
     * Dummy Decathlon constructor for jaxb serialization.
     * It is hidden from outside world.
     *
     * @deprecated Don't use this constructor it is only for jaxb serialization.
     */
    private Decathlon() {
        this.year = 2017;
        this.results = new ArrayList<>();
    }


    /**
     * Decathlon constructor
     *
     * @param year
     * @param results
     */
    public Decathlon(final int year, final List<ResultLine> results) {
        this.year = year;
        Collections.sort(results);
        this.results = getRankedResults(results);
    }


    /**
     * Set ranks to the result lines.
     *
     * @param results
     * @return
     */
    private static List<ResultLine> getRankedResults(List<ResultLine> results) {
        final Map<Integer, String> rankTable = getRankTable(results);
        List<ResultLine> lineResults = results;
        for (ResultLine rl : lineResults) {
            rl.setRank(rankTable.get(rl.getTotalPoints()));
        }
        return lineResults;
    }

    /**
     * Get rank table according total points.
     *
     * @param results
     * @return
     */
    private static Map<Integer, String> getRankTable(List<ResultLine> results) {
        Map<Integer, String> pointsRankTable = new HashMap<>();
        int count = 0;
        int groupStart = 1;
        int prevPoints = -1;
        //Iterate over results
        for (ResultLine rl : results) {
            if (prevPoints > -1) {
                //if total point different from previous add this group
                if (prevPoints != rl.getTotalPoints()) {
                    pointsRankTable.put(prevPoints, groupStart == count ? Integer.toString(groupStart) : groupStart + " - " + count);
                    groupStart = count + 1;
                    prevPoints = rl.getTotalPoints();
                }
            } else {
                prevPoints = rl.getTotalPoints();
            }
            if (count == results.size() - 1) {
                pointsRankTable.put(prevPoints, groupStart == count + 1 ? Integer.toString(groupStart) : groupStart + " - " + results.size());
            }
            count++;
        }
        return pointsRankTable;
    }

    /**
     * Get decathlon year.
     *
     * @return
     */
    public int getYear() {
        return year;
    }


    /**
     * Get decathlon results.
     *
     * @return
     */
    public List<ResultLine> getResults() {
        return results;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "Decathlon{" +
                "year=" + year +
                ", results=" + Arrays.toString(results.toArray()) +
                '}';
    }
}
