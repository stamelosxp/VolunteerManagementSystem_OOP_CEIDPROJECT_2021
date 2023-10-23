import java.util.InputMismatchException;
import java.util.Scanner;

class DonatorMenu {
    public static void donatorMessage(Donator d){
      System.out.println("Hello! Welcome to our organization, you are logged in as Donator!" +
      "\nName: " + d.getName() + "\n" + "Phone Number: " + d.getPhone());
    }

    public static int mainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println(
                        "\n1. Add Offer" +
                        "\n2. Show Offers" +
                        "\n3. Commit" +
                        "\n4. Logout" +
                        "\n5. Exit");
        int numOfChoice;
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numOfChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
                scan.nextLine();
                return numOfChoice;
            } catch (InputMismatchException ime) {
                System.out.println("Please only integer! Try again!");
                scan.nextLine();
                continue;
            } catch (NumOfChoiceException noce) {
                System.out.println(noce);
                scan.nextLine();
                continue;
            }
        }
    }

    public static void addOffers(Donator d, Organization o, Menu menu){
        Scanner scan = new Scanner(System.in);
        System.out.println(
                            "\n1. Material, currently (" + o.getMaterialCount() + ") materials in stock." +
                            "\n2. Services, currently (" +  o.getServiceCount() +") services." +
                            "\n3. Back" +
                            "\n4. Logout" +
                            "\n5. Exit");
        int numChoice;
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
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

        switch (numChoice){
            case 1:
                //Εμφανίζει τη λίστα με τα διαθέσιμα Material
                o.getMaterialList();

                int materialid;
                while(true){
                    try {
                        System.out.print("Insert material ID for more information: ");
                        materialid = ExistsEntity.existsMaterial(scan.nextInt(), o);
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime){
                        System.out.println("Please only integers! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (ExistsEntityException exe){
                        System.out.println(exe);
                        System.out.println("Please try again!");
                        scan.nextLine();
                        continue;
                    }
                }

                //Παιρνει πληροφοριες για ενα material με βαση το id
                o.getSpecificMaterialDetails(materialid);

                System.out.println("Would you like to donate this material?");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for no): ");
                    String donateChoice = scan.nextLine();
                    if(donateChoice.toUpperCase().compareTo("Y")==0 || donateChoice.toUpperCase().compareTo("YES")==0){
                        double quantityChoice;
                        while (true){
                            try{
                                System.out.print("Insert the quantity which you would like to donate: ");
                                quantityChoice = NumOfChoice.numOfChoice(scan.nextDouble());
                                scan.nextLine();
                                break;
                            }
                            catch (InputMismatchException eme){
                                System.out.println("Please insert a number for quantity!Try again!");
                                scan.nextLine();
                                continue;
                            }
                            catch (NumOfChoiceException noce){
                                System.out.println(noce);
                                scan.nextLine();
                                continue;
                            }
                        }
                        try {
                            d.add(o.getMaterial(materialid), quantityChoice, o);
                            System.out.println("Offer added successfully!");
                        }
                        catch (Exception e){
                            System.out.println("Something went wrong!");
                        }
                        menu.donatorMenu(d, o, menu);
                        break;
                    }
                    else if(donateChoice.toUpperCase().compareTo("N")==0 || donateChoice.toUpperCase().compareTo("NO")==0){
                        System.out.println(
                                            "\n1. Back" +
                                            "\n2. Logout" +
                                            "\n3. Exit");
                        int numChoice2;
                        while (true) {
                            try {
                                System.out.print("Insert number of your choice: ");
                                numChoice2 = NumOfChoice.numOfChoice(scan.nextInt(), 0, 4);
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

                        switch (numChoice2){
                            case 1:
                                menu.donatorMenu(d, o, menu);
                                break;
                            case 2:
                                System.out.println("You are logging out...");
                                menu.identify(o, menu);
                                break;
                            case 3:
                                System.out.println("Thank you for checking us out!");
                                System.exit(0);
                                break;
                        }
                    }
                }
                break;
            case 2:
                //Εμφανίζει τη λίστα με τα διθέσιμα service
                o.getServiceList();

                int serviceId;
                while(true){
                    try {
                        System.out.print("Insert service ID for more information: ");
                        serviceId = ExistsEntity.existsService(scan.nextInt(), o);
                        scan.next();
                        break;
                    }
                    catch (InputMismatchException ime){
                        System.out.println("Please only integer! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (ExistsEntityException exe){
                        System.out.println(exe);
                        System.out.println("Please try again!");
                        scan.nextLine();
                        continue;
                    }
                }

                //Εμφανίζει περισσότερες πληροφορίες για ένα service με βάση το ID
                o.getSpecificServiceDetails(serviceId);

                System.out.println("Would you like to donate this service?");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for no): ");
                    String donateChoice = scan.nextLine();
                    if(donateChoice.toUpperCase().equals("Y") || donateChoice.toUpperCase().equals("YES")){
                        double hoursChoice;
                        while (true){
                            try{
                                System.out.print("Insert the hours which you would like to offer: ");
                                hoursChoice = NumOfChoice.numOfChoice(scan.nextDouble());
                                scan.nextLine();
                                break;
                            }
                            catch (InputMismatchException eme){
                                System.out.println("Please insert a number for hours!Try again!");
                                scan.nextLine();
                                continue;
                            }
                            catch (NumOfChoiceException noce){
                                System.out.println(noce);
                                scan.nextLine();
                                continue;
                            }
                        }

                        RequestDonation.createRqD(o.getService(serviceId), hoursChoice);
                        d.add(o.getService(serviceId), hoursChoice, o);
                        DonatorMenu.addOffers(d, o, menu);
                        break;
                    }
                    else if(donateChoice.toUpperCase().compareTo("N")==0 || donateChoice.toUpperCase().compareTo("NO")==0){
                        System.out.println(
                                        "\n1. Back" +
                                        "\n2. Logout" +
                                        "\n3. Exit");
                        int numChoice3;
                        while (true) {
                            try {
                                System.out.print("Insert number of your choice: ");
                                numChoice3 = NumOfChoice.numOfChoice(scan.nextInt(), 0, 4);
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

                        switch (numChoice3){
                            case 1:
                                DonatorMenu.addOffers(d, o, menu);
                                break;
                            case 2:
                                System.out.println("You are logging out...");
                                menu.identify(o, menu);
                                break;
                            case 3:
                                System.out.println("Thank you for checking us out!");
                                System.exit(0);
                                break;
                        }
                        break;
                    }
                }
                break;
            case 3:
                menu.donatorMenu(d, o, menu);
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

    public static void showOffers(Donator d, Organization o, Menu menu){
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("All offers from " + d.getName() +":\n");
            if(d.getOffersList().size() == 0){
                System.out.println("You haven't made any offers yet!");
                System.out.println("Would you like to offer a Material or Service: ");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for n): ");
                    String choiceYorN = scan.nextLine();
                    if(choiceYorN.toUpperCase().compareTo("Y") == 0 || choiceYorN.toUpperCase().compareTo("YES") == 0){
                        DonatorMenu.addOffers(d, o, menu);
                        break;
                    }
                    else if(choiceYorN.toUpperCase().compareTo("N") == 0 || choiceYorN.toUpperCase().compareTo("NO") == 0){
                        System.out.println(
                                        "\n1. Back" +
                                        "\n2. Logout" +
                                        "\n3. Exit");
                        int numChoice;
                        while (true) {
                            try {
                                System.out.print("Insert number of your choice: ");
                                numChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 4);
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

                        switch (numChoice){
                            case 1:
                                menu.donatorMenu(d, o, menu);
                                break;
                            case 2:
                                System.out.println("You are logging out...");
                                menu.identify(o, menu);
                                break;
                            case 3:
                                System.out.println("Thank you for checking us out!");
                                System.exit(0);
                                break;
                        }
                        break;
                    }
                    break;
                }
                break;
            }
            break;
        }



        for (int i=0; i<d.getOffersList().size(); i++){
            System.out.println((i+1) + ". Offer: ");
            System.out.println("\tEntity: " + d.getOffersList().get(i).getE().getName());
            System.out.println("\tQuantity: " + d.getOffersList().get(i).getQuantity());
        }

        String offerChoice;
        while (true){
            try {
                System.out.print("Insert the name of entity: ");
                offerChoice = ExistsEntity.existDonEntity(o.getEntityByName(scan.nextLine()), d, o);
                break;
            }
            catch (ExistsEntityException exe){
                System.out.println(exe);
                scan.nextLine();
                continue;
            }
        }
        System.out.print(
                        "\n1. Delete "+ offerChoice +
                        "\n2. Edit quantity for " + offerChoice +
                        "\n3. Back" +
                        "\n4. Logout" +
                        "\n5. Exit");
        int numChoice;
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
                scan.nextLine();
                break;
            } catch (InputMismatchException ime) {
                System.out.println("Please only integer! Try again!");
                scan.nextLine();
                continue;
            } catch (NumOfChoiceException noce) {
                System.out.println(noce);
                scan.nextLine();
                continue;
            }
        }
        switch (numChoice){
            case 1:
                for(int i=0; i<d.getOffersList().size(); i++){
                    if(d.getOffersList().get(i).getE() == o.getEntityByName(offerChoice)){
                        d.remove(d.getOffersList().get(i), o);
                        System.out.println("Offer deleted successfully!");
                        DonatorMenu.mainMenu();
                        break;
                    }
                }
                break;
            case 2:
                int flag=0;
                double newQuantity;
                for(int i=0; i<d.getOffersList().size(); i++){
                    if(d.getOffersList().get(i).getE() == o.getEntityByName(offerChoice));
                    {
                        flag = i;
                        break;
                    }
                }
                while (true){
                    try {
                        System.out.print("Insert the new quantity which you would like to offer: ");
                        newQuantity = NumOfChoice.numOfChoice(scan.nextDouble());
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime){
                        System.out.println("Please insert a number for new quantity! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (NumOfChoiceException noce){
                        System.out.println(noce);
                        scan.nextLine();
                        continue;
                    }
                }
                try {
                    RequestDonation rq = d.getOffersList().get(flag).getRdEntities().get(flag);
                    d.getOffersList().get(flag).modify(rq, newQuantity,o, d);
                    System.out.println("Quantity changed successfully!");
                }
                catch (Exception e){
                    System.out.println("Something went wrong!");
                }
                menu.donatorMenu(d, o, menu);
                break;
            case 3:
                menu.donatorMenu(d, o, menu);
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

    public static void donatorCommit(Donator d, Organization o, Menu menu){
        d.commit(d, o);
        System.out.println("Offer committed successfully!");
        menu.donatorMenu(d, o, menu);
    }
}
