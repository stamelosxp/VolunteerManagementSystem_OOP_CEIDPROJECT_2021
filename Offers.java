public class Offers extends RequestDonationList{
    private boolean check = false;

    private double quantity;
    private Entity e;

    public Offers(double quantity, Material m){
        this.quantity=quantity;
        e = m;
    }

    public Offers(double quantity, Service s){
        this.quantity=quantity;
        e = s;
    }

    public static Offers newOffer(double quantity, Material m){
        return new Offers(quantity, m);
    }

    public static Offers newOffer(double quantity, Service s){
        return new Offers(quantity, s);
    }

    public Entity getE() {
        return e;
    }

    public double getQuantity() {
        return quantity;
    }

    public void commit(Organization o, Donator d){
        try {
            for (int i=0; i<d.getOffersList().size(); i++){
                o.getCurrentDonations().getRdEntities().put(d.getOffersList().get(i).e.getId(),d.getOffersList().get(i).get(i));
            }
            check = true;
        }
        catch (NullPointerException npe){
            System.out.println();
        }

        if(check){
            getRdEntities().clear();
        }
    }

    @Override
    public void add(RequestDonation rq) {
        super.add(rq);
    }

    @Override
    public void modify(RequestDonation rq, double quantity, Organization o, Donator d) {
        super.modify(rq, quantity, o, d);
    }

    @Override
    public void remove(RequestDonation rq) {
        super.remove(rq);
    }

    @Override
    public void reset() {
        super.reset();
    }


}
