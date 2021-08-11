package pl.coderslab.model;

public class DayName {

    private int id;
    private String name;
    private int display_order;

    public DayName(int id, String name, int display_order) {
        this.id = id;
        this.name = name;
        this.display_order = display_order;
    }

    public DayName(){

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

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    @Override
    public String toString() {
        return "DayName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", display_order=" + display_order +
                '}';
    }
}
