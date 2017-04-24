package com.swedbank.decathlon.data;

/**
 * The Enum EventType Unit
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.Unit)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum Unit {

    MINUTES,
    SECONDS,
    METERS,
    CENTIMETERS;

    /**
     * Get unit value from string representation.
     *
     * @param value
     * @return
     */
    public float getValueFromString(final String value) {
        float returnValue;
        switch (this) {
            case CENTIMETERS:
                //convert meters to centimeters
                returnValue = 100f * Float.valueOf(value);
                break;
            case MINUTES:
                //custom format parsing 2.23.75
                int minutes = Integer.parseInt(value.substring(0, value.indexOf(".")));
                float seconds = Float.parseFloat(value.substring(value.indexOf(".") + 1, value.length()));
                returnValue = minutes * 60f + seconds;
                break;
            default:
                returnValue = Float.valueOf(value);
                break;
        }
        return returnValue;
    }

    /**
     * Get string print from float value.
     *
     * @param value
     * @return
     */
    /*public String formatOutput(final float value) {
        String returnValue;
        switch (this) {
            case CENTIMETERS:
                returnValue = Float.toString(value / 100f);
                break;
            case MINUTES:
                int minutes = (int) value / 60;
                returnValue = Float.toString((float) value % 60);
                break;
            default:
                returnValue = Float.toString(value);
                break;
        }
        return returnValue;
    }*/
}
