package cn.zzx.common.pojo;

public class Programme {
    private int unit;
    private int singleCount;
    private int manyCount;
    private int judgeCount;

    public Programme(int unit, int singleCount, int manyCount, int judgeCount) {
        this.unit = unit;
        this.singleCount = singleCount;
        this.manyCount = manyCount;
        this.judgeCount = judgeCount;
    }

    @Override
    public String toString() {
        return "Programme{" +
                "unit=" + unit +
                ", singleCount=" + singleCount +
                ", manyCount=" + manyCount +
                ", judgeCount=" + judgeCount +
                '}';
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getSingleCount() {
        return singleCount;
    }

    public void setSingleCount(int singleCount) {
        this.singleCount = singleCount;
    }

    public int getManyCount() {
        return manyCount;
    }

    public void setManyCount(int manyCount) {
        this.manyCount = manyCount;
    }

    public int getJudgeCount() {
        return judgeCount;
    }

    public void setJudgeCount(int judgeCount) {
        this.judgeCount = judgeCount;
    }
}
