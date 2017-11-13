package mk.ukim.finki.kol1;

import java.util.Date;

class TextNewsItem extends NewsItem {
    private String text;

    public TextNewsItem(String title, Date publishDate, Category category, String text) {
        super(title, publishDate, category);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    String getTeaser() {
        if (text.length() > 81)
            return getTitle() + "\n" + ((new Date().getTime() - getPublishDate().getTime()) / 60000)
                    + "\n" + text.substring(0, 80);
        return getTitle() + "\n" + ((new Date().getTime() - getPublishDate().getTime()) / 60000)
                + "\n" + text;
    }
}
