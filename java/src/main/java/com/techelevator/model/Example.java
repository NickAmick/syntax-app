package com.techelevator.model;

import java.util.List;
import java.util.Objects;

public class Example {

    private int exampleID;
    private String title;
    private String snippet;
    private String languageType;
    private Integer languageId;
    private List<Tag> tagList;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public int getExampleID() {
        return exampleID;
    }

    public void setExampleID(int exampleID) {
        this.exampleID = exampleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return exampleID == example.exampleID && Objects.equals(title, example.title) && Objects.equals(snippet, example.snippet) && Objects.equals(languageType, example.languageType) && Objects.equals(languageId, example.languageId) && Objects.equals(tagList, example.tagList) && Objects.equals(description, example.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleID, title, snippet, languageType, languageId, tagList, description);
    }
}
