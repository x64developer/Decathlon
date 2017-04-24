package com.swedbank.decathlon.data;

import static com.swedbank.decathlon.data.EventType.*;


/**
 * The Enum Gender
 * <p>
 * Holds order of decathlon events order for women and men.
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.Gender)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public enum Gender {


    MEN {
        /* (non-Javadoc)
         * @com.swedbank.decathlon.data.Gender#getEventOrder(com.swedbank.decathlon.data.Gender)
         */
        @Override
        public EventType[] getEventOrder() {
            final EventType[] eventsOrder = {RUN_100, LONG_JUMP, SHORT_JUMP, HIGH_JUMP, RUN_400, RUN_HURDLES_110,
                    DISCUS_THROW, POLE_VAULT, JAVELIN_THROW, RUN_1500};
            return eventsOrder;

        }
    },
    FEMALE {
        /* (non-Javadoc)
         * @com.swedbank.decathlon.data.Gender#getEventOrder(com.swedbank.decathlon.data.Gender)
         */
        @Override
        public EventType[] getEventOrder() {
            final EventType[] eventOrder = {RUN_100, DISCUS_THROW, POLE_VAULT, JAVELIN_THROW, RUN_400, RUN_HURDLES_110,
                    LONG_JUMP, SHORT_JUMP, HIGH_JUMP, RUN_1500};
            return eventOrder;
        }
    };

    /**
     * Get event order.
     *
     * @return
     */
    abstract public EventType[] getEventOrder();

    @Override
    public String toString() {
        return "Gender{" + getEventOrder()+ "}";
    }
}
