package mk.ukim.finki.kol1;

import java.util.Date;

class MediaNewsItem extends NewsItem {
    private String url;
    private int viewsCount;

    public MediaNewsItem(String title, Date publishDate, Category category, String url, int viewsCount) {
        super(title, publishDate, category);
        this.url = url;
        this.viewsCount = viewsCount;
    }

    public String getUrl() {
        return url;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    @Override
    String getTeaser() {
        return getTitle() + "\n" + ((new Date().getTime() - getPublishDate().getTime()) / 60000) +
                "\n" + url + "\n" + viewsCount;
    }
}
