package tests.files.model;

public class JsonFileModel {
    private String title;
    private String author;
    private Integer year;
    private Integer pages;
    private String language;
    private String[] tags;
    private AvailabilityModel availability;

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Integer getYear() {
        return year;
    }
    public Integer getPages() {
        return pages;
    }
    public String getLanguage() {
        return language;
    }
    public String[] getTags() {
        return tags;
    }
    public AvailabilityModel getAvailability() {
        return availability;
    }
}