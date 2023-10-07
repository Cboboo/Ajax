package com.bobo.ajax.beans;

/**
 * ClassName: City
 * PackageName: com.bobo.ajax.beans
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/6 19:17
 * @Version 1.0
 */
public class City {
    private int id;
    private String code;
    private String name;
    private String pcode;

    public City() {
    }

    public City(int id, String code, String name, String pcode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.pcode = pcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pcode='" + pcode + '\'' +
                '}';
    }
}
