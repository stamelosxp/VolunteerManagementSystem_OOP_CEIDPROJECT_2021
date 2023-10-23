class RequestDonation {
    private  Entity entity;
    private double quantity;

    public RequestDonation(Entity entity, double quantity){
        this.entity = entity;
        this.quantity = quantity;
    }

    public static RequestDonation createRqD(Entity entity, double quantity){
        return new RequestDonation(entity, quantity);
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getEnid(RequestDonation rq){
        return rq.entity.getId();
    }

    public Entity getEntity() {
        return entity;
    }

    public double getQuantity() {
        return quantity;
    }
}
