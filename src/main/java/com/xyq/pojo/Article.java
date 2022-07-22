package com.xyq.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @author cwtt
 */
@ExcelTarget("articles")
public class Article implements Serializable {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "内容" , width = 80.0)
    private String content;
    @Excel(name = "作者")
    private String nickname;
    @Excel(name = "标签")
    private String label;

    public Article() {
    }

    public Article(Integer id, String title, String content, String nickname, String label) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", nickname='" + nickname + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
