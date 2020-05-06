package cn.zzx.common.pojo;

public class Chapter {
    private int id;
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                '}';
    }
}
