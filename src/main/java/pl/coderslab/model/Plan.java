package pl.coderslab.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Plan {
    private int id;
    private String name;
    private String description;
    private Date created;

    public Plan(String name, String description, Date created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Plan() {
    }

    public Plan(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
