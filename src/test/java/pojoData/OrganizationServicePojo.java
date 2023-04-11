package pojoData;

public class OrganizationServicePojo {
    private String name;
    private int founderId;

    public OrganizationServicePojo() {
    }

    public OrganizationServicePojo(String name, int founderId) {
        this.name = name;
        this.founderId = founderId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounderId() {
        return founderId;
    }

    public void setFounderId(int founderId) {
        this.founderId = founderId;
    }
}
