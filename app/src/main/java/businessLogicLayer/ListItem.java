package businessLogicLayer;

class ListItem {
    public String titlePrefix;
    public String title;
    public String infoPrefix;
    public String info;
    public String status;
    public String statusinfo;
    public ListItem(String titlePrefix,String title,String infoPrefix,String info,String status,String statusinfo) {
        this.titlePrefix = titlePrefix;
        this.title = title;
        this.infoPrefix = infoPrefix;
        this.info = info;
        this.status = status;
        this.statusinfo = statusinfo;
    }

    public ListItem(String titlePrefix, String title, String infoPrefix, String info)
    {
        this.titlePrefix = titlePrefix;
        this.title = title;
        this.infoPrefix = infoPrefix;
        this.info = info;
    }
}