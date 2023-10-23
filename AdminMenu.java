import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMenu {
    public static void adminMessage(Admin a) {
        System.out.println("Hello! Welcome to our organization, you are logged in as Admin!" +
                "\nName: " + a.getName() + "\n" + "Phone Number: " + a.getPhone());
    }

    public static int mainMenu() {
        int numOfChoice;
        Scanner scan = new Scanner(System.in);

        System.out.println(
                        "\n1. View" +
                        "\n2. Monitor Organization" +
                        "\n3. Logout" +
                        "\n4. Exit");
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numOfChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 5);
                scan.nextLine();
                return numOfChoice;
            }
            catch (InputMismatchException ime) {
                System.out.println("Please only integer! Try again!");
                scan.nextLine();
                continue;
            }
            catch (NumOfChoiceException noce) {
                System.out.println(noce);
                scan.nextLine();
                continue;
            }
        }
    }

    public static void viewAdmin (Admin a,Organization o, Menu menu){
            Scanner scan = new Scanner(System.in);
            System.out.println(
                            "\n1. Material, currently (" + o.getMaterialCount() + ") materials in stock." +
                            "\n2. Services, currently (" + o.getServiceCount() + ") services." +
                            "\n3. Back" +
                            "\n4. Logout" +
                            "\n5. Exit");

            int numOfChoice;
            while (true) {
                try {
                    System.out.print("Insert number of your choice: ");
                    numOfChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
                    scan.nextLine();
                    break;
                }
                catch (InputMismatchException ime) {
                    System.out.println("Please only integer! Try again!");
                    scan.nextLine();
                    continue;
                }
                catch (NumOfChoiceException noce) {
                    System.out.println(noce);
                    scan.nextLine();
                    continue;
                }
            }

            switch (numOfChoice) {
                case 1:
                    o.getMaterialList();
                    int materialId;
                    while (true) {
                        try {
                            System.out.print("Insert material ID for more information: ");
                            materialId = ExistsEntity.existEntity(o.getMaterial(scan.nextInt()), o);
                            scan.nextLine();
                            break;
                        } catch (InputMismatchException ime) {
                            System.out.println("Please only integers! Try again!");
                            scan.nextLine();
                            continue;
                        } catch (ExistsEntityException exe) {
                            System.out.println(exe);
                            scan.nextLine();
                            continue;
                        }
                    }

                    //Εμφανίζει τις πληροφοριες για ενα material με βαση το id
                    o.getSpecificMaterialDetails(materialId);
                    AdminMenu.viewAdmin(a, o, menu);
                    break;

                case 2:
                    //Εμφανίζει τη λίστα με τα διθέσιμα service
                    o.getServiceList();
                    int serviceId;
                    while (true) {
                        try {
                            System.out.print("Insert service ID for more information: ");
                            serviceId = ExistsEntity.existsEntity(scan.nextInt(), o);
                            scan.nextLine();
                            break;
                        }
                        catch (InputMismatchException ime) {
                            System.out.println(ime);
                            scan.nextLine();
                            continue;
                        }
                        catch (ExistsEntityException exe) {
                            System.out.println(exe);
                            System.out.println("Please try again!");
                            scan.nextLine();
                            continue;
                        }
                    }

                    //Εμφανίζει περισσότερες πληροφορίες για ένα service με βάση το ID
                    o.getSpecificServiceDetails(serviceId);
                    AdminMenu.viewAdmin(a, o, menu);
                    break;
                case 3:
                    menu.adminMenu(a, o, menu);
                    break;
                case 4:
                    System.out.println("You are logging out...");
                    menu.identify(o, menu);
                    break;
                case 5:
                    System.out.println("Thank you for checking us out!");
                    System.exit(0);
                    break;

            }
        }

    public static void monitorOrgAdmin(Admin a, Organization o, Menu menu){
        Scanner scan = new Scanner(System.in);

        System.out.println(
                            "\n1. Beneficiaries List " +
                            "\n2. Donators List " +
                            "\n3. Reset receivedList for Beneficiaries" +
                            "\n4. Back" +
                            "\n5. Logout" +
                            "\n6. Exit");
        System.out.println();

        int numOfChoice;
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numOfChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 7);
                scan.nextLine();
                break;
            }
            catch (InputMismatchException ime) {
                System.out.println("Please only integer! Try again!");
                scan.nextLine();
                continue;
            }
            catch (NumOfChoiceException noce){
                System.out.println(noce);
                scan.nextLine();
                continue;
            }
        }

        switch (numOfChoice) {
            case 1:
                //Εμφανίζεται η λίστα με τους Beneficiary
                if(o.beneficiaryList.size()==0){
                    System.out.println("No registered beneficiaries in the organization!");
                    AdminMenu.monitorOrgAdmin(a, o, menu);
                    break;
                }
                else {
                    o.getBeneficiaryList();
                }

                String beneficiaryName;
                while (true) {
                    try {
                        System.out.print("\nInsert the name of the beneficiary: ");
                        String s = "";
                        s += scan.nextLine();
                        beneficiaryName = UserExists.beneficiaryExists(s, o);
                        break;
                    }
                    catch (UserExistsException bxe) {
                        System.out.println(bxe);
                        continue;
                    }
                }


                System.out.println(
                                "\n1. More information about received offers from " + beneficiaryName +
                                "\n2. Clear reveivedList for " + beneficiaryName +
                                "\n3. Delete " + beneficiaryName + " from Organizarion" +
                                "\n4. Back" +
                                "\n5. Logout" +
                                "\n6. Exit");

                int numOfChoice2;
                while (true) {
                    try {
                        System.out.print("\nInsert number of your choice: ");
                        numOfChoice2 = NumOfChoice.numOfChoice(scan.nextInt(), 0, 7);
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime) {
                        System.out.println("Please only integer! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (NumOfChoiceException noce){
                        System.out.println(noce);
                        scan.nextLine();
                        continue;
                    }
                }

                Beneficiary b = o.getBeneficiaryByName(beneficiaryName);
                switch (numOfChoice2){
                    case 1:
                        if(b.getReceiveList().size()==0){
                            System.out.println("\nBeneficiary has not received any offers yet!");
                            AdminMenu.monitorOrgAdmin(a, o, menu);
                            break;
                        }
                        else {
                            for(int i=0; i<b.getReceiveList().size(); i++){
                                System.out.println(i+1+". Receive: ");
                                System.out.println("Entity: " + b.getReceiveList().get(i).getRdEntities().get(i).getEntity());
                                System.out.println("Quatity: " + b.getReceiveList().get(i).getRdEntities().get(i).getQuantity());
                                if(b.getReceiveList().get(i).getRdEntities().get(i).getEntity().getClass() == Material.class){
                                    double q1 = QuantityByPeople.QuantityByPeople(b, o.getMaterial(b.getReceiveList().get(i).getRdEntities().get(i).getEntity().getId()));
                                    double q2 = b.getReceiveList().get(i).getRdEntities().get(i).getQuantity();
                                    System.out.println("Quantity left: " + (q1-q2));
                                }
                            }
                        }
                        AdminMenu.monitorOrgAdmin(a, o, menu);
                        break;
                    case 2:
                        try{
                            b.getReceiveList().clear();
                            System.out.println("Beneficiary's received list has been cleared successfully!");
                        }
                        catch (NullPointerException npe){
                            System.out.println("Something went wrong!");
                        }
                        AdminMenu.monitorOrgAdmin(a, o, menu);
                        break;
                    case 3:
                        try {
                            o.beneficiaryList.remove(b);
                            System.out.println("Beneficiary has been removed successfully!");
                        }
                        catch (NullPointerException npe){
                            System.out.println("Something went wrong!");
                        }
                        AdminMenu.monitorOrgAdmin(a,o, menu);
                        break;
                    case 4:
                        menu.adminMenu(a, o, menu);
                        break;
                    case 5:
                        System.out.println("You are logging out...");
                        menu.identify(o, menu);
                        break;
                    case 6:
                        System.out.println("Thank you for checking us out!");
                        System.exit(0);
                        break;
                }
                break;

            case 2:
                //Εμφανίζει τη λίστα με τους donators
                if(o.donatorList.size()==0){
                    System.out.println("No registered donators in the organization!");
                    menu.adminMenu(a, o, menu);
                    break;
                }
                else {
                    o.getDonatorList();
                }

                String donatorName;
                while (true){
                    try {
                        System.out.print("Insert the name of Donator: ");
                        String s = "";
                        s += scan.nextLine();
                        donatorName = UserExists.donatorExists(s, o);
                        break;
                    }
                    catch (UserExistsException bxe){
                        System.out.println(bxe);
                        continue;
                    }
                }

                Donator d = o.getDonatorByName(donatorName);
                System.out.println(
                                "\n1. More information about offers from " + donatorName +
                                "\n2. Delete " + donatorName + " from Organizarion" +
                                "\n3. Back" +
                                "\n4. Logout" +
                                "\n5. Exit");

                int numOfChoice3;
                while (true) {
                    try {
                        System.out.print("Insert number of your choice: ");
                        numOfChoice3 = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime) {
                        System.out.println("Please only integer! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (NumOfChoiceException noce){
                        System.out.println(noce);
                        scan.nextLine();
                        continue;
                    }
                }

                switch (numOfChoice3){
                    case 1:
                        if(d.getOffersList().size()==0){
                            System.out.println("This donator has not offered anything yet!");
                            menu.adminMenu(a, o, menu);
                        }
                        else {
                            for(int i=0; i<d.getOffersList().size(); i++){
                                System.out.println((i+1) + ". Offer");
                                System.out.println("Entity: " + d.getOffersList().get(i).getE().getName());
                                System.out.println("Quantity: " + d.getOffersList().get(i).getQuantity());
                            }
                        }
                        break;
                    case 2:
                        try {
                            o.donatorList.remove(d);
                            System.out.println("Donator has been removed successfully!");
                            break;
                        }
                        catch (NullPointerException npe){
                            System.out.println(npe);
                        }
                        AdminMenu.monitorOrgAdmin(a, o, menu);
                        break;
                    case 4:
                        AdminMenu.monitorOrgAdmin(a, o, menu);
                        break;
                    case 5:
                        System.out.println("You are logging out...");
                        menu.identify(o, menu);
                        break;
                    case 6:
                        System.out.println("Thank you for checking us out!");
                        System.exit(0);
                        break;
                }
                break;
            case 3:
                try{
                    for(int i=0; i < o.beneficiaryList.size(); i++){
                        o.beneficiaryList.get(i).getReceiveList().clear();
                    }
                }
                catch (Exception e){
                    System.out.println("Something went wrong!");
                }
                menu.adminMenu(a, o, menu);
                break;
            case 4:
                menu.adminMenu(a, o, menu);
                break;
            case 5:
                System.out.println("You are logging out...");
                menu.identify(o, menu);
                break;
            case 6:
                System.out.println("Thank you for checking us out!");
                System.exit(0);
                break;
        }
    }
}
