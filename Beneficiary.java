import java.util.ArrayList;

public class Beneficiary extends User {
    private int noPersons = 1;

    private ArrayList<RequestDonationList> receiveList;
    private ArrayList<Requests> requestsList;

    public Beneficiary(String name, String phone, int noPersons) {
        super(name, phone);
        this.noPersons = noPersons;
        receiveList = new ArrayList<RequestDonationList>();
        requestsList = new ArrayList<Requests>();
    }

    public static Beneficiary newBeneficiary(String name, String phone, int noPersons) {
        return new Beneficiary(name, phone, noPersons);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    public int getNoPersons() {
        return noPersons;
    }

    public void add(Requests r) {
        requestsList.add(r);
    }

    public void remove(Requests r) {
        requestsList.remove(r);
    }

    public ArrayList<RequestDonationList> getReceiveList() {
        return receiveList;
    }

    public ArrayList<Requests> getRequestsList() {
        return requestsList;
    }

    public boolean commit(Beneficiary b, Organization o){
        if(!(requestsList.size()==0)){
            for(int i=0; i<b.requestsList.size(); i++){
                requestsList.get(i).commit(requestsList.get(i), b, o);
                System.out.println("FTASAME EDWWWX");
            }
            return true;
        }
        else {
            return false;
        }
    }

    public void getReceiveListDetails(Organization o, Beneficiary b){
        boolean check;
        for(int i=0; i<receiveList.size(); i++){
            System.out.println((i+1) + ". Received");
            int id = o.entityList.get(i).getId();
            RequestDonation rd = receiveList.get(id).get(id);
            System.out.println("Entity: " + rd.getEntity().getName());
            System.out.println("Quantity: " + rd.getQuantity());

            if(o.entityList.get(i).typeOfEntity().compareTo("Material")==0){
                double q1 = QuantityByPeople.QuantityByPeople(b, o.getMaterial(id));
                double q2 = rd.getQuantity();
                System.out.println("Quantity left: " + (q1-q2));
            }
        }
    }
}