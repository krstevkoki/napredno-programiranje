package mk.ukim.finki.kol1;

import java.util.Date;

abstract class NewsItem {
    private String title;
    private Date publishDate;
    private Category category;

    public NewsItem(String title, Date publishDate, Category category) {
        this.title = title;
        this.publishDate = publishDate;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    abstract String getTeaser();
}
