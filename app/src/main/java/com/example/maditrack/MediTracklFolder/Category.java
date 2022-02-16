package com.example.maditrack.MediTracklFolder;

public class Category {

    private String categorycode, categoryname, categorydescription,categoryreminder;
    private int id;

    public Category() {

    }

    public Category(int id, String categorycode, String categoryname, String categorydescription,String categoryreminder) {
        this.id = id;
        this.categorycode = categorycode;
        this.categoryname = categoryname;
        this.categorydescription = categorydescription;
        this.categoryreminder = categoryreminder;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public String getCategoryreminder() {
        return categoryreminder;
    }

    public void setCategoryreminder(String categoryreminder) {
        this.categoryreminder = categoryreminder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}