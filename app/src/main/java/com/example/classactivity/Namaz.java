package com.example.classactivity;



public class Namaz {


    private Integer id;
    private String NamazType;
    private String NamazName;
    private String RakatCount;
    private boolean withJamat;
    private String date;

    public Namaz(Integer id, String type,String name, String count, boolean withJamat,String dt) {
        this.id=id;
        this.NamazType=type;
        this.NamazName = name;
        this.RakatCount = count;
        this.withJamat = withJamat;
        this.date=dt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNamazName() {
        return NamazName;
    }

    public void setNamazName(String namazName) {
        this.NamazName = namazName;
    }

    public String getRakatCount() {
        return RakatCount;
    }

    public void setRakatCount(String rakatCount) {
        this.RakatCount = rakatCount;
    }

    public boolean isWithJamat() {
        return withJamat;
    }

    public void setWithJamat(boolean withJamat) {
        this.withJamat = withJamat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamazType() {
        return NamazType;
    }

    public void setNamazType(String namazType) {
        NamazType = namazType;
    }

    @Override
    public String toString() {
        return "Namaz (Type:" + NamazType + ", Name:" + NamazName + ", Count:" + RakatCount + ", With Jamat:" + withJamat + ", Date:" + date + ")";
    }

}

