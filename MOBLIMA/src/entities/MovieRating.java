package entities;

public enum MovieRating {
    G("G"),
    PG("PG"),
    PG13("PG13"),
    NC16("NC16"),
    M18("M18"),
    R21("R21");

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
