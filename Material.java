class Material extends Entity{
    final double level1;
    final double level2;
    final double level3;

    public Material(String name, String description, int id, double level1, double level2, double level3) {
        super(name, description, id);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    @Override
    public String getDetails(){
        return "\nLevel 1: " + level1 + "\tLevel 2: " + level2 + "\tLevel 3: " + level3 + "\t" +typeOfEntity()+"\n";
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String typeOfEntity() {
        return "Material";
    }
}
