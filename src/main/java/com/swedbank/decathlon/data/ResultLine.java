package com.swedbank.decathlon.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class  ResultLine
 * <b>
 * This class holds result line of one athlete.
 * <b>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.ResultLine)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
@XmlRootElement(name = "resultLine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"rank", "totalPoints", "athlete", "results"})
public class ResultLine implements Serializable, Comparable<ResultLine> {

    private final  Athlete athlete;
    private final List<Result> results;
    private final int totalPoints;
    private String rank;


    /**
     * Dummy ResultLine constructor for jaxb serialization.
     * It is hidden from outside world.
     *
     * @deprecated Don't use this constructor it is only for jaxb serialization.
     */
    private ResultLine() {
        this.athlete = new Athlete("","");
        this.results = new ArrayList<>(EventType.values().length);
        this.totalPoints = 0;
    }

    /**
     * Result line constructor
     *
     * @param athlete
     * @param results
     * @param totalPoints
     */
    public ResultLine(Athlete athlete, List<Result> results, int totalPoints) {
        this.athlete = athlete;
        this.results = results;
        this.totalPoints = totalPoints;
    }

    /**
     * Set rank.
     *
     * @param rank result rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Get rank.
     * @return result rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * Get athlete
     * @return athlete details
     */
    public Athlete getAthlete() {
        return athlete;
    }

    /**
     * Get athlete results
     * @return results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * Get total points
     *
     * @return
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Get total point count from result line
     *
     * @param results
     * @return
     */
    public static int getTotalPoints(List<Result> results) {
        int total = 0;
        for (Result r : results) {
            total += r.getPoints();
        }
        return total;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "ResultLine{" +
                "athlete=" + athlete +
                ", results=" + Arrays.toString(results.toArray()) +
                ", totalPoints=" + totalPoints +
                '}';
    }

    @Override
    public int compareTo(ResultLine o) {
        return totalPoints > o.getTotalPoints() ? -1 : (totalPoints < o.getTotalPoints()) ? 1 : 0;
    }
}
