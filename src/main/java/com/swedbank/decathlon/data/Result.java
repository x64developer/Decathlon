package com.swedbank.decathlon.data;

import com.swedbank.decathlon.xml.EventTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * The Class Result
 * <p>
 * This class holds event result data.
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.Result)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"event", "athletePerformance", "points"})
public class Result implements Serializable {

    @XmlJavaTypeAdapter(EventTypeAdapter.class)
    private final EventType event;
    private final String athletePerformance;
    private final int points;

    /**
     * Dummy Result constructor for jaxb serialization.
     * It is hidden from outside world.
     *
     * @deprecated Don't use this constructor it is only for jaxb serialization.
     */
    private Result() {
        event = null;
        athletePerformance = "0.0";
        points = 0;
    }

    /**
     * Result constructor
     *
     * @param builder
     */
    private Result(final ResultBuilder builder) {
        this.event = builder.event;
        this.athletePerformance = builder.athletePerformance;
        this.points = builder.points;
    }

    /**
     * Get event type
     *
     * @return
     */
    public EventType getEvent() {
        return event;
    }

    /**
     * Get athlete score
     *
     * @return
     */
    public String getAthletePerformance() {
        return athletePerformance;
    }

    /**
     * Get athlete points
     *
     * @return
     */
    public int getPoints() {
        return points;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "Result{" +
                "event=" + event +
                ", athletePerformance='" + athletePerformance + '\'' +
                ", points=" + points +
                '}';
    }

    /**
     * The Class ResultBuilder
     */
    public static class ResultBuilder {
        private final EventType event;
        private final String athletePerformance;
        private int points;

        public ResultBuilder(final EventType event, final String athletePerformance) {
            this.event = event;
            this.athletePerformance = athletePerformance;
        }

        public ResultBuilder setPoints(final int points) {
            this.points = points;
            return this;
        }

        public Result build() {
            return new Result(this);
        }

    }
}
