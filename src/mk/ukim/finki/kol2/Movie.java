package mk.ukim.finki.kol2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Movie {
    private String movieTitle;
    private List<Integer> ratings;

    public Movie(String movieTitle, int[] ratings) {
        this.movieTitle = movieTitle;
        this.ratings = Arrays.stream(ratings)
                .boxed()  // .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int numberOfRatings() {
        return ratings.size();
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public double averageRating() {
        return ratings.stream()
                .mapToDouble(Double::valueOf)
                .average()
                .orElse(0d);
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", movieTitle, averageRating(), ratings.size());
    }
}
