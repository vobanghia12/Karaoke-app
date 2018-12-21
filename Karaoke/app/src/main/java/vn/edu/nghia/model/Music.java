package vn.edu.nghia.model;

import java.io.Serializable;

public class Music implements Serializable {
    private String ma;
    private String ten;
    private String caSi;
    private boolean thich;
    private static Music instance;

    public Music(String ma, String ten, String caSi, boolean thich) {
        this.ma = ma;
        this.ten = ten;
        this.caSi = caSi;
        this.thich = thich;
    }

    public Music() {
    }

    /*  public static Music getintance(String ma , String ten , String caSi , boolean thich )
        {
            if(instance == null)
            {
                instance = new Music(ma,ten,caSi,thich);
            }
            return instance;
        }*/
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public boolean isThich() {
        return thich;
    }

    public void setThich(boolean thich) {
        this.thich = thich;
    }
}
