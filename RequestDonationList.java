import java.util.HashMap;

class RequestDonationList {
    private HashMap<Integer, RequestDonation> rdEntities = new HashMap<Integer, RequestDonation>();

    public RequestDonation get(int id){
        boolean checkId = false;
        for(RequestDonation i: rdEntities.values()){
            int id1 = i.getEnid(i);
            checkId = (id1 == id);
        }
        if(checkId){
            return rdEntities.get(id);
        }
        else{
            return null;
        }
    }



    public void add(RequestDonation rq){
        boolean exists = false;
        for (RequestDonation i: rdEntities.values()){
            if(i.getEnid(i) == rq.getEnid(rq)){
                exists = true;
                break;
            }
        }

        if(exists){
            int id = rq.getEnid(rq);
            double quantity = rq.getQuantity();
            rq.setQuantity(quantity);
            rdEntities.replace(id, rq, rq);
            System.out.println("Request Donation replaced successfully!");
        }
        else{
            rdEntities.put(rq.getEnid(rq), rq);
            System.out.println("Request Donation added successfully!");
        }
    }

    public void remove(RequestDonation rq){
        rdEntities.remove(rq);
    }

    public void modify(RequestDonation rq, double quantity, Organization o, Beneficiary b){
        int id = rq.getEnid(rq);

        try {
            InsufficientAmount.insufficientAmount(id, o, quantity, b);
        }
        catch (InsufficientAmountException iae){
            System.out.println(iae);
        }

        rq.setQuantity(quantity);
        rdEntities.replace(id, rq, rq);
        System.out.println("Quantity changed successfully!");
    }
    public void modify(RequestDonation rq, double quantity, Organization o, Donator d){
        int id = rq.getEnid(rq);
        rq.setQuantity(quantity);
        rdEntities.replace(id, rq, rq);
        System.out.println("Quantity changed successfully!");
    }

    public void monitor(){
        for (RequestDonation i: rdEntities.values()){
            System.out.println("Entity: " + i.getEntity() +"\tQuantity: " + i.getQuantity());
        }
    }

    public void reset(){
        rdEntities.clear();
        System.out.println("Request Donation List reset successfully! ");
    }

    public HashMap<Integer, RequestDonation> getRdEntities() {
        return rdEntities;
    }

    public double getQuantRequestDon(int id){
        return rdEntities.get(id).getQuantity();
    }

}
