package org.home.bookmart.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by roger on 9/13/15.
 */
@XmlRootElement
public class Request {
    private Collection<Integer> guids = new ArrayList<>();
    private String comment = "";

    public Request(Collection<Integer> guids, String comment) {
        this.guids = guids;
        this.comment = comment;
    }

    public Request() {
    }

    public Collection<Integer> getGuids() {
        return guids;
    }

    public void setGuids(Collection<Integer> guids) {
        this.guids = guids;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Request{" +
                "guids=" + guids +
                ", comment='" + comment + '\'' +
                '}';
    }
}
