public class QuantityByPeople {
    public static double QuantityByPeople(Beneficiary b , Material m){
        if(b.getNoPersons() == 1){
            return m.level1;
        }
        else if(b.getNoPersons() >= 2 || b.getNoPersons() <= 4){
            return m.level2;
        }
        else{
            return m.level3;
        }
    }
}

