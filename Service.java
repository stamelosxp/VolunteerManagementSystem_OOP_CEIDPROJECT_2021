class Service extends Entity{

    public Service(String name, String description, int id) {
        super(name, description, id);
    }

    @Override
    public String getDetails() {
        return "\n"+typeOfEntity()+"\n";
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String typeOfEntity() {
        return "Service";
    }
}
