public class ValidRequestDonation {
    public static void validRequestDonation(Material m, Beneficiary b, double quantity) throws ValidRequestDonationException{
        int people = b.getNoPersons();
        boolean checkLv1 = false;
        boolean checkLv2 = false;
        boolean checkLv3 = false;
        boolean quantityLv1;
        boolean quantityLv2;
        boolean quantityLv3;

        if(people==1){
            checkLv1 = true;
        }
        else if(people>=2 && people<=4){
            checkLv2 = true;
        }
        else{
            checkLv3 = true;
        }

        if (checkLv1){
            quantityLv1 = (quantity <= m.level1);
            if(!quantityLv1){
                throw new ValidRequestDonationException("Invalid quantity!");
            }
        }
        else if(checkLv2){
            quantityLv2 = (quantity <= m.level2);
            if(!quantityLv2){
                throw new ValidRequestDonationException("Invalid quantity!");
            }
        }
        else if(checkLv3){
            quantityLv3 = (quantity <= m.level3);
            if(!quantityLv3){
                throw new ValidRequestDonationException("Invalid quantity!");
            }
        }
    }
}
