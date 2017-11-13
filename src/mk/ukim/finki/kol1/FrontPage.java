package mk.ukim.finki.kol1;

import java.util.ArrayList;
import java.util.List;

class FrontPage {
    private ArrayList<NewsItem> newsItems;
    private Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.newsItems = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem) {
        newsItems.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category) {
        ArrayList<NewsItem> filtered = new ArrayList<>();
        for (NewsItem newsItem : newsItems)
            if (newsItem.getCategory().equals(category))
                filtered.add(newsItem);
        return filtered;
    }

    private boolean containsCategory(String category) {
        for (Category cat : categories)
            if (category.equals(cat.getName()))
                return true;
        return false;
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        if (containsCategory(category)) {
            return listByCategory(new Category(category));
        } else throw new CategoryNotFoundException(String.format("Category %s was not found", category));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (NewsItem newsItem : newsItems) {
            sb.append(newsItem.getTeaser());
            sb.append("\n");
        }
        return sb.toString();
    }
}
