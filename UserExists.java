public class UserExists extends Exception {
    public static String beneficiaryExists(String name, Organization o) throws UserExistsException {
        if (!(o.beneficiaryList.contains(o.getBeneficiaryByName(name)))) {
            throw new UserExistsException(name + " is not exists in organization!");
        }
        return name;
    }

    public static String donatorExists(String name, Organization o) throws UserExistsException {
        if (!(o.donatorList.contains(o.getDonatorByName(name)))) {
            throw new UserExistsException(name + " is not exists in organization!");
        }

        return name;
    }

    public static void insertDon(Donator d, Organization o) throws UserExistsException{
        if(o.donatorList.contains(d)){
            throw new UserExistsException("This donator is already exists in organization!");
        }
        else{
            o.donatorList.add(d);
            System.out.println("Donator registered successfully!");
        }
    }
    public static void insertBen(Beneficiary b, Organization o) throws UserExistsException{
        if(o.beneficiaryList.contains(b)){
            throw new UserExistsException("This donator is already exists in organization!");
        }
        else{
            o.beneficiaryList.add(b);
            System.out.println("Beneficiary registered successfully!");
        }
    }
}