package entities;


public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    SCIFI("Science Fiction"),
    SPORTS("Sports"),
    THRILLER("Thriller"),
    MYSTERY("Mystery");
    
    private final String genreName;

    private Genre(){
        this.genreName = "";
    }

    private Genre(String genre)
    {
        this.genreName = genre;
    }
    public String toString(){
        return this.genreName;
    }

    public boolean equalsTo(String genre){
        return genreName.equals(genre);
    }
}
