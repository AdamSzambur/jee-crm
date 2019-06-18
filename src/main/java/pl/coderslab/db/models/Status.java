package pl.coderslab.db.models;

public class Status {
    private int id;
    private String value;

    public Status() {
    }

    public Status(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
