public class Requests extends RequestDonationList{

    private boolean checkQuant = false;
    private boolean checkBen = false;
    private Entity e;
    private double quant;

    public Requests(double quantity, Beneficiary b, Organization o, Material m){
        boolean isMaterial = false;
        try{
            InsufficientAmount.insufficientAmount(m.getId(), o, quantity, b);
            checkQuant = true;
            e = m;
        }
        catch(InsufficientAmountException iae){
            System.out.println(iae);
        }

        try {
            ValidRequestDonation.validRequestDonation(m, b, quantity);
            checkBen = true;
            quant = quantity;
        }
        catch (ValidRequestDonationException vde) {
            System.out.println(vde);
        }

    }


    public Requests(double quantity, Beneficiary b, Organization o, Service s){
        try{
            InsufficientAmount.insufficientAmount(s.getId(), o, quantity, b);
            checkQuant = true;
            e = s;
            quant = quantity;
        }
        catch(InsufficientAmountException iae){
            System.out.println(iae);
        }
    }

    public static Requests newRequest(double quantity, Beneficiary b, Organization o, Service s){
        return new Requests(quantity, b, o, s);
    }

    public static Requests newRequest(double quantity, Beneficiary b, Organization o, Material m){
        return new Requests(quantity, b, o, m);
    }

    public void add(RequestDonation rq) {
        super.add(rq);
    }

    public void commit(Requests r,Beneficiary b, Organization o){
        try {
            RequestsCheck.requestsCheck(r);
            System.out.println("11111111111111");
            //o.getCurrentDonations().get(e.getId()).setQuantity(o.getCurrentDonations().get(e.getId()).getQuantity() - quant);
            System.out.println("2222222222222222222222");

            for(int i=0; i<b.getRequestsList().size(); i++){
                b.getReceiveList().add(b.getRequestsList().get(i));
                System.out.println("55555555555");
                b.getRequestsList().remove(i);
            }
        }
        catch (RequestsCheckException rce){
            System.out.println(rce);
        }
    }

    public boolean isCheckQuant() {
        return checkQuant;
    }

    public boolean isCheckBen() {
        return checkBen;
    }

    public Entity getE() {
        return e;
    }

    public double getQuant() {
        return quant;
    }
}
