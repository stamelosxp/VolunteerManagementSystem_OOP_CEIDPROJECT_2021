public class InsufficientAmount {
    public static void insufficientAmount(int id, Organization o, double quant, Beneficiary b) throws InsufficientAmountException {
        boolean flagAvailableQuant;
        int cnt = -1;
        if (quant < 0) {
            throw new InsufficientAmountException("Invalid quantity!");
        }
        for(int i=0; i<o.entityList.size(); i++){
            if(o.entityList.get(i).getId() == id){
                cnt = i;
                break;
            }
        }


        flagAvailableQuant = (quant <= QuantityByPeople.QuantityByPeople(b, (Material) o.entityList.get(cnt)));

        if(!(b.getReceiveList().size()==0)) {
            int cnt2 = -1;
            for (int i = 0; i < b.getReceiveList().size(); i++) {
                if (b.getReceiveList().get(i).getRdEntities().get(i).getEntity() == o.entityList.get(cnt)) {
                    cnt2 = i;
                    break;
                }
            }
            if (flagAvailableQuant) {
                if (quant > QuantityByPeople.QuantityByPeople(b, (Material) o.entityList.get(cnt)) - b.getReceiveList().get(cnt2).getQuantRequestDon(o.entityList.get(cnt).getId()))//Ελεγχουμε αν η ποσοτητα που ζηταει ειναι μεγαλυτερη απο αυτη που δικαιουται
                {
                    throw new InsufficientAmountException("Υοu have already received the amount you are entitled to!");
                }
            }
        }
    }
}
