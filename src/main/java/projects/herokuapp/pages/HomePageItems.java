package projects.herokuapp.pages;

public enum HomePageItems {
    CONTEXT_MENU("Context Menu"),
    FRAMES("Frames"),
    DYNAMIC_CONTROLS("Dynamic Controls"),
    FILE_UPLOAD("File Upload");

    private final String item;

    HomePageItems(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }
}
