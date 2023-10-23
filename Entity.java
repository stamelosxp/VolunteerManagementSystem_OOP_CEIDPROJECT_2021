abstract class Entity {
    private String name;
    private String description;
    private int id;

    public Entity(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getEntityInfo(){
        return "Entity: " + description + "\nEntity name: " + name + "\nEntity id: " + id +"\n";
    }

    abstract public String getDetails();

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getEntityInfo() + getDetails();
    }

    public String getName() {
        return name;
    }

    abstract public String typeOfEntity();
}
