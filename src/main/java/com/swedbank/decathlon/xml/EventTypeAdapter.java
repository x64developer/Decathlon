package com.swedbank.decathlon.xml;

import com.swedbank.decathlon.data.EventType;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * The Class EventTypeAdapter
 * <p>
 * This class is adapter for EventType jaxb serialization.
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.xml.EventTypeAdapter)" target="_top">Saulius Bira</a>
 *         Date: 4/20/2017
 */
public class EventTypeAdapter extends XmlAdapter<String, EventType> {

    @Override
    public EventType unmarshal(String v) throws Exception {
        return EventType.getByName(v);
    }

    @Override
    public String marshal(EventType v) throws Exception {
        return v.getName();
    }
}
