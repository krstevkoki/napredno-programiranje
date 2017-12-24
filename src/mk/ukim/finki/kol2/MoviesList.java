package mk.ukim.finki.kol2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class MoviesList {
    private List<Movie> movies;

    public MoviesList() {
        this.movies = new LinkedList<>();
    }

    public void addMovie(String title, int[] ratings) {
        Movie movie = new Movie(title, ratings);
        movies.add(movie);
    }

    public List<Movie> top10ByAvgRating() {
        return movies.stream()
                .sorted(Comparator
                        .comparing(Movie::averageRating)
                        .reversed()
                        .thenComparing(Movie::getMovieTitle))
                .limit(10)
                .collect(Collectors.toList());
    }

    private double maxRatingsFromMovies() {
        return movies.stream()
                .mapToDouble(Movie::numberOfRatings)
                .max()
                .orElse(0d);
    }

    public List<Movie> top10ByRatingCoef() {
        return movies.stream()
                .sorted((o1, o2) -> {
                    double maxRating = maxRatingsFromMovies();
                    int result = Double.compare(o1.averageRating() * o1.numberOfRatings() / maxRating,
                            o2.averageRating() * o2.numberOfRatings() / maxRating);
                    if (result == 0)
                        return o1.getMovieTitle().compareTo(o2.getMovieTitle());
                    return -result;  // reversed order
                })
                .limit(10)
                .collect(Collectors.toList());
    }
}
