package com.royalfriends.bookies;

public class CategoryRVModal {
    private String category;
    private String CategoryImageUrl;

    public CategoryRVModal(String category, String categoryImageUrl) {
        this.category = category;
        CategoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
