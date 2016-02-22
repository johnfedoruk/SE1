package businessLogicLayer;

class ListItem {
    public String titlePrefix;
    public String title;
    public String infoPrefix;
    public String info;
    public ListItem(String titlePrefix,String title,String infoPrefix,String info) {
        this.titlePrefix = titlePrefix;
        this.title = title;
        this.infoPrefix = infoPrefix;
        this.info = info;
    }
}