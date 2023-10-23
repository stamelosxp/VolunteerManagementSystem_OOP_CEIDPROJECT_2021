import java.util.*;

public class Organization {
    private String name;
    private Admin admin;

    public ArrayList<Entity> entityList = new ArrayList<Entity>();
    public ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    public ArrayList<Donator> donatorList = new ArrayList<Donator>();

    private RequestDonationList currentDonations;

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void addEntity(Entity en){}

    public void getMaterialList(){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).typeOfEntity().compareTo("Material")==0){
                System.out.println("Material ID: " + entityList.get(i).getId() + "\t Material Name: " + entityList.get(i).getName());
            }
            else{
                continue;
            }
        }
    }

    public int getMaterialCount(){
        int counterMaterial = 0;
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).typeOfEntity().compareTo("Material")==0){
                counterMaterial++;
            }
            else{
                continue;
            }
        }
        return counterMaterial;
    }

    public int getServiceCount(){
        int counterService = 0;
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).typeOfEntity().compareTo("Service")==0){
                counterService++;
            }
            else{
                continue;
            }
        }
        return counterService;
    }

    public void getServiceList(){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).typeOfEntity().compareTo("Service")==0){
                System.out.println("Service ID: "+ entityList.get(i).getId() + "\tService Name: " + entityList.get(i).getName());
            }
            else{
                continue;
            }
        }
    }

    public void getSpecificMaterialDetails(int id){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getId() == id){
                System.out.println(entityList.get(i).getDetails());
                break;
            }
            else continue;
        }

    }

    public void getSpecificServiceDetails(int id){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getId() == id){
                System.out.println(entityList.get(i).getDetails());
                break;
            }
            else continue;
        }
    }

    public Material getMaterial(int id){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getId() == id){
                return (Material) entityList.get(i);
            }
            else continue;
        }
        return null;
    }

    public Service getService(int id){
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getId() == id){
                return (Service) entityList.get(i);
            }
            else continue;
        }
        return null;
    }

    public Beneficiary getBeneficiaryByName(String name) {
        int counter = -1;

        for (int i = 0; i < beneficiaryList.size(); i++) {
            if (beneficiaryList.get(i).getName().toUpperCase().compareTo(name.toUpperCase())==0) {
                counter = i;
                break;
            }
        }
        if (counter == -1){
            return null;
        }
        else {
            return beneficiaryList.get(counter);
        }
    }

    public Donator getDonatorByName(String name){
        int counter = -1;
        for(int i=0; i < donatorList.size(); i++){
            if(donatorList.get(i).getName().compareTo(name)==0){
                counter = i;
                break;
            }
        }

        if(counter == -1){
            return null;
        }
        else {
            return donatorList.get(counter);
        }
    }

    public void removeEntity(Entity en){
        entityList.remove(en);
    }

    public void insertDonator(Donator d, Organization o){

        try {
            UserExists.insertDon(d, o);
        }
        catch (UserExistsException uee){
            System.out.println(uee);
        }
    }

    public void insertBeneficiary(Beneficiary b, Organization o){
        try {
            UserExists.insertBen(b, o);
        }
        catch (UserExistsException uee){
            System.out.println(uee);
        }
    }

    public void removeDonator(Donator d){
        donatorList.remove(d);
    }

    public void removeBeneficiary(Beneficiary b){
        beneficiaryList.remove(b);
    }

    public void listEntities(){
        System.out.println("Materials: ");
        getMaterialList();
        System.out.println("Services: ");
        getServiceList();
    }

    public RequestDonationList getCurrentDonations() {
        return currentDonations;
    }

    public void setCurrentDonations(RequestDonationList currentDonations) {
        this.currentDonations = currentDonations;
    }

    public void getBeneficiaryList() {
        for(int i=0; i<beneficiaryList.size(); i++){
            System.out.println("Name: " + beneficiaryList.get(i).getName() + "\tPhone Number: " + beneficiaryList.get(i).getPhone() + "\tNo. of persons : " + beneficiaryList.get(i).getNoPersons());
        }
    }

    public void getDonatorList(){
        for(int i=0; i<donatorList.size(); i++){
            System.out.println("Name: " +donatorList.get(i).getName() + "\tPhone Number: " + donatorList.get(i).getPhone());
        }
    }

    public void listBeneficiaries(){
        for(int i=0; i<beneficiaryList.size(); i++){
            System.out.println("Name: " + beneficiaryList.get(i).getName() + " Phone Number: " + beneficiaryList.get(i).getPhone() + "No. of persons : " + beneficiaryList.get(i).getNoPersons());
            System.out.println("Revieve list for " + beneficiaryList.get(i).getName()+": ");
            for(int j=0; j<beneficiaryList.get(i).getReceiveList().size(); j++){
                System.out.println(beneficiaryList.get(i).getReceiveList().get(j));
            }
        }
    }

    public void listDonators(){
        for(int i=0; i<donatorList.size(); i++){
            System.out.println("Name: " +donatorList.get(i).getName() + " Phone Number: " + beneficiaryList.get(i).getPhone());
        }
    }

    public String getName() {
        return name;
    }

    public Entity getEntityByName(String name){
        int counter = 0;
        for(int i=0; i<entityList.size(); i++){
            if(entityList.get(i).getName().compareTo(name)==0){
                counter = i;
                break;
            }
        }
        return entityList.get(counter);
    }
}

