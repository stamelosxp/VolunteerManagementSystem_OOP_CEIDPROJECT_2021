import java.util.ArrayList;

public class Donator extends  User{
    private ArrayList<Offers> offersList = new ArrayList<Offers>();

    public Donator(String name, String phone) {
        super(name, phone);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    public static Donator newDonator(String name, String phone){
        return new Donator(name,phone);
    }

    public void add(Material m, double quantity, Organization or){
        try {
            ExistsEntity.existEntity(m, or);
            RequestDonation rq = RequestDonation.createRqD(m, quantity);
            Offers o = Offers.newOffer(quantity, m);
            offersList.add(o);
            o.add(rq);
        }
        catch (ExistsEntityException exe){
            System.out.println(exe);
        }
    }

    public void add(Service s, double quantity, Organization or) {
        try {
            ExistsEntity.existEntity(s, or);
            RequestDonation rq = RequestDonation.createRqD(s, quantity);
            Offers o = Offers.newOffer(quantity, s);
            offersList.add(o);
            o.add(rq);
        }
        catch (ExistsEntityException exe){
            System.out.println(exe);
        }
    }
    public void remove(Offers o, Organization or){
        offersList.remove(o);
    }

    public void modify(Offers o, double quantity){
        int point = offersList.indexOf(o);
        offersList.set(point, o);

    }

    public void commit(Donator d, Organization o){
        for(int i=0; i<offersList.size(); i++){
            offersList.get(i).commit(o, d);
        }
    }

    public void clear(){
        offersList.clear();
    }

    public ArrayList<Offers> getOffersList() {
        return offersList;
    }
}
