package lesson8.data;

public enum Tab {
    What("Lorem Ipsum is simply dummy text"),
    Origin("Contrary to popular belief, Lorem Ipsum is not simply random text"),
    Use("It is a long established fact that a reader");

    public final String description;

    Tab(String description) {
        this.description = description;
    }
}
