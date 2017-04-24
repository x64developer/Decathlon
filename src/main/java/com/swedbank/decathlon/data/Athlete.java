package com.swedbank.decathlon.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * The Class Athlete
 * <p>
 * This class contains decathlon athlete details name and surname.
 * </p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.data.Athlete)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
@XmlRootElement(name = "athlete")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "surname"})
public class Athlete implements Serializable {

    /**
     * The name
     */
    private final String name;
    /**
     * The surname
     */
    private final String surname;

    /**
     * Dummy Athlete constructor for jaxb serialization.
     * It is hidden from outside world.
     * @deprecated Don't use this constructor it is only for jaxb serialization.
     */
    private Athlete() {
        name = "";
        surname = "";
    }

    /**
     * Athlete constructor
     *
     * @param name
     * @param surname
     */
    public Athlete(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Get athlete name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Get athlete surname
     *
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
