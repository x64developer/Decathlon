package com.swedbank.decathlon.data;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EventType
 * <p>
 * This enumerator holds information related to the decathlon events name constants measure units.
 * </p>
 * <pre>
 * Event point calculation:
 * The 2001 IAAF points tables use the following formulae
 * Points = INT(A(B - P)<sup>C</sup>) for track events (faster time produces a better score)
 * Points = INT(A(P - B)<sup>C</sup>) for field events (greater distance or height produces a better score)
 * A, B and C are parameters that vary by discipline.
 * </pre>
 * <p>
 * <table style="text-align: center;">
 * <thead><tr>
 * <th>Event</th>
 * <th><i><b>A</b></i></th>
 * <th><i><b>B</b></i></th>
 * <th><i><b>C</b></i></th>
 * </tr></thead><tbody>
 * <tr>
 * <td align="left">100 m</td><td>25.4347</td><td>18</td><td>1.81</td>
 * </tr>
 * <tr>
 * <td align="left">Long jump</td><td>0.14354</td><td>220</td><td>1.4</td>
 * </tr>
 * <tr>
 * <td align="left">Shot put</td><td>51.39</td><td>1.5</td><td>1.05</td>
 * </tr>
 * <tr>
 * <td align="left">High jump</td><td>0.8465</td><td>75</td><td>1.42</td>
 * </tr>
 * <tr>
 * <td align="left">400 m</td><td>1.53775</td><td>82</td><td>1.81</td>
 * </tr>
 * <tr>
 * <td align="left">110 m hurdles</td><td>5.74352</td><td>28.5</td><td>1.92</td>
 * </tr>
 * <tr>
 * <td align="left">Discus throw</td><td>12.91</td><td>4</td><td>1.1</td>
 * </tr>
 * <tr>
 * <td align="left">Pole vault</td><td>0.2797</td><td>100</td><td>1.35</td>
 * </tr>
 * <tr>
 * <td align="left">Javelin throw</td><td>10.14</td><td>7</td><td>1.08</td>
 * </tr>
 * <tr>
 * <td align="left">1500 m</td><td>0.03768</td><td>480</td><td>1.85</td>
 * </tr>
 * <tfoot></tfoot></table>
 * <p>
 * Values <b>A</b>, <b>B</b> and <b>C</b> were taken from: <a href="https://en.wikipedia.org/wiki/Decathlon">Decathlon info page</a>
 * </p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.EventType)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum EventType {

    /**
     * 100 m
     */
    RUN_100("100 m", Unit.SECONDS, 25.4347f, 18f, 1.81f),
    /**
     * Long jump
     */
    LONG_JUMP("Long jump", Unit.CENTIMETERS, 0.14354f, 220f, 1.4f),
    /**
     * Shot put
     */
    SHORT_JUMP("Short put", Unit.METERS, 51.39f, 1.5f, 1.05f),
    /**
     * High jump
     */
    HIGH_JUMP("High jump", Unit.CENTIMETERS, 0.8465f, 75f, 1.42f),
    /**
     * 400 m
     */
    RUN_400("400 m", Unit.SECONDS, 1.53775f, 82f, 1.81f),
    /**
     * 110 m hurdles
     */
    RUN_HURDLES_110("110 m hurdles", Unit.SECONDS, 5.74352f, 28.5f, 1.92f),
    /**
     * Discus throw
     */
    DISCUS_THROW("Discus throw", Unit.METERS, 12.91f, 4f, 1.1f),
    /**
     * Pole vault
     */
    POLE_VAULT("Pole vault", Unit.CENTIMETERS, 0.2797f, 100f, 1.35f),
    /**
     * Javelin throw
     */
    JAVELIN_THROW("Javelin throw", Unit.METERS, 10.14f, 7f, 1.08f),
    /**
     * 1500 m
     */
    RUN_1500("1500 m", Unit.MINUTES, 0.03768f, 480f, 1.85f);

    /**
     * Event name
     */
    private final String name;

    /**
     * Event unit
     */
    private final Unit unit;
    /**
     * A constant
     */
    private final float A;
    /**
     * B constant
     */
    private final float B;
    /**
     * C constant
     */
    private final float C;

    /**
     * lookup table by name
     */
    private static final Map<String, EventType> lookup =
            new HashMap();

    static {
        //Create reverse lookup hash map
        for (EventType et : EventType.values())
            lookup.put(et.name(), et);
    }

    /**
     * Event type constructor
     *
     * @param name
     * @param unit
     * @param A
     * @param B
     * @param C
     */
    EventType(final String name, final Unit unit, final float A, final float B, final float C) {
        this.name = name;
        this.unit = unit;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    /**
     * Count points based on athlete score.
     *
     * @param athleteScore
     * @return
     */
    public int countPoints(final float athleteScore) {
        int points;
        switch (this.unit) {
            case METERS:
            case CENTIMETERS:
                //Points = INT(A(P — B)C) for field events (greater distance or height produces a better score)
                points = (int) Math.floor(A * Math.pow(athleteScore - B, C));
                break;
            case SECONDS:
            case MINUTES:
                //Points = INT(A(B — P)C) for track events (faster time produces a better score)
                points = (int) Math.floor(A * Math.pow(B - athleteScore, C));
                break;
            default:
                points = 0;
                break;
        }
        return points;
    }

    /**
     * Get event name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get event measure unit
     *
     * @return
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Get A constant
     *
     * @return
     */
    public float getA() {
        return A;
    }

    /**
     * Get B constant
     *
     * @return
     */
    public float getB() {
        return B;
    }

    /**
     * Get C constant
     *
     * @return
     */
    public float getC() {
        return C;
    }

    public static EventType getByName(String name) {
        return lookup.get(name);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "EventType{" +
                "name='" + name + '\'' +
                ", unit=" + unit +
                ", A=" + A +
                ", B=" + B +
                ", C=" + C +
                '}';
    }
}
