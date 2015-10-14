package org.home.bookmart.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by roger on 9/13/15.
 */
@XmlRootElement
public class Book {
    private int guid;
    private String name;
    private String description;

    public Book(int guid, String name, String description) {
        this.guid = guid;
        this.name = name;
        this.description = description;
    }

    public Book() {}

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
