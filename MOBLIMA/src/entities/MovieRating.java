package entities;

public enum MovieRating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    NC16("NC-16"),
    M18("M-18"),
    R21("R-21");

    private final String movieRating;

    private MovieRating(){
        movieRating = "";
    }

    private MovieRating(String movieRating){
        this.movieRating = movieRating;
    }
    public boolean equalsTo(String movieRating){
        return this.movieRating.equals(movieRating);
    }
    public String toString(){
        return this.movieRating;
    }
}
