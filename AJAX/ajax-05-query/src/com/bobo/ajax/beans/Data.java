package com.bobo.ajax.beans;

/**
 * ClassName: Data
 * PackageName: com.bobo.ajax.beans
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/7 21:08
 * @Version 1.0
 */
public class Data {
    private String content;

    public Data() {
    }

    public Data(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Data{" +
                "content='" + content + '\'' +
                '}';
    }
}
