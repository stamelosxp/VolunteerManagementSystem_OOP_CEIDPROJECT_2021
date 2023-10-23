import java.util.InputMismatchException;
import java.util.Scanner;

public class BeneficiaryMenu {
    public static void beneficiaryMessage(Beneficiary b) {
      System.out.println("Hello! Welcome to our organization, you are logged in as Beneficiary!" +
      "\nName: " + b.getName() + "\n" + "Phone Number: " + b.getPhone());
    }

    public static int mainMenu() {
        Scanner scan = new Scanner(System.in);
        int numOfChoice;
        System.out.println(
                        "\n1. Add Request" +
                        "\n2. Show Requests" +
                        "\n3. Commit" +
                        "\n4. Logout" +
                        "\n5. Exit");
        while (true) {
            try {
                System.out.print("Insert number of your choice: ");
                numOfChoice = NumOfChoice.numOfChoice(scan.nextInt(), 0, 6);
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

    public static void addRequests(Beneficiary b, Organization o, Menu menu) {
        Scanner scan = new Scanner(System.in);
        System.out.println(
                            "\n1. Material, currently (" + o.getMaterialCount() + ") materials in stock." +
                            "\n2. Services, currently (" + o.getServiceCount() + ") services." +
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

        switch (numChoice) {
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

                System.out.println("Would you like to request this material?");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for no): ");
                    String requestChoice = scan.nextLine();
                    if(requestChoice.toUpperCase().compareTo("Y")==0 || requestChoice.toUpperCase().compareTo("YES")==0){
                        double quantityChoice;
                        while (true){
                            try{
                                System.out.print("Insert the quantity which you would like to request: ");
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

                        Requests r = Requests.newRequest(quantityChoice, b, o, o.getMaterial(materialid));
                        try {
                            b.add(r);
                            System.out.println("Material requested successfully!");
                        }
                        catch (Exception e){
                            System.out.println("Something went wrong!");
                        }
                        break;
                    }
                    else if(requestChoice.toUpperCase().compareTo("N")==0 || requestChoice.toUpperCase().compareTo("NO")==0) {
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

                        switch (numChoice2) {
                            case 1:
                                BeneficiaryMenu.addRequests(b, o, menu);
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
                        serviceId = ExistsEntity.existsService(scan.nextInt(),o);
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime){
                        System.out.println(ime);
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

                System.out.println("Would you like to request this service?");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for no): ");
                    String donateChoice = scan.nextLine();
                    if(donateChoice.toUpperCase().compareTo("Y") == 0 || donateChoice.toUpperCase().compareTo("YES") == 0){
                        double hoursChoice;
                        while (true){
                            try{
                                System.out.print("Insert the hours which you would like to request: ");
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

                        Requests r = Requests.newRequest(hoursChoice, b, o, o.getService(serviceId));

                        try {
                            b.add(r);
                            System.out.println("Service requested successfully!");
                        }
                        catch (Exception e){
                            System.out.println("Something went wrong!");
                        }
                        break;
                    }
                    else if(donateChoice.toUpperCase().compareTo("N") == 0 || donateChoice.toUpperCase().compareTo("N0") == 0){
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
                                BeneficiaryMenu.addRequests(b, o, menu);
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
            case 3:
                menu.beneficiaryMenu(b, o, menu);
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

    public static void showRequests(Beneficiary b , Organization o , Menu menu){
        Scanner scan = new Scanner(System.in);

        System.out.println("All material/services received from " +b.getName()+":\n");
        if(b.getReceiveList().size() == 0) {
            System.out.println("\tYou don't have receive any material/service yet!");
        }
        else {
            b.getReceiveListDetails(o, b);
        }

        while (true){
            System.out.println("All requests from " + b.getName() +":\n");
            if(b.getRequestsList().size() == 0){
                System.out.println("You don't have any request yet!");
                System.out.println("Would you like to request a Material/Service: ");
                while (true){
                    System.out.print("Please insert y (for yes) or n (for n): ");
                    String choiceYorN = scan.nextLine();
                    if(choiceYorN.toUpperCase().compareTo("Y") == 0 || choiceYorN.toUpperCase().compareTo("YES") == 0){
                        BeneficiaryMenu.addRequests(b, o, menu);
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
                                menu.beneficiaryMenu(b, o, menu);
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
            }
            else
            {
                break;
            }
        }

        for(int i=0; i<b.getRequestsList().size(); i++){
            System.out.println((i+1) + ". Request");
            System.out.println("\tEntity: " + b.getRequestsList().get(i).getE().getName());
            System.out.println("\tQuantity: " + b.getRequestsList().get(i).getQuant());
        }

        String requestChoice;
        while (true){
            try {
                System.out.print("Insert the name of entity: ");
                requestChoice = ExistsEntity.existBenEntity(o.getEntityByName(scan.nextLine()), b, o);
                break;
            }
            catch (ExistsEntityException exe){
                System.out.println(exe);
                scan.nextLine();
                continue;
            }
        }

        System.out.print(
                        "\n1. Delete "+ requestChoice +
                        "\n2. Edit quantity for " + requestChoice +
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
            catch (NumOfChoiceException noce) {
                System.out.println(noce);
                scan.nextLine();
                continue;
            }
        }
        switch (numChoice){
            case 1:
                for(int i=0; i<b.getRequestsList().size(); i++){
                    if(b.getRequestsList().get(i).getE() == o.getEntityByName(requestChoice)){
                        b.remove(b.getRequestsList().get(i));
                        System.out.println("Request has been removed successfully!");
                        break;
                    }
                }
                menu.beneficiaryMenu(b, o, menu);
                break;
            case 2:

                double newQuantity;
                while (true){
                    try {
                        System.out.print("Insert the new quantity: ");
                        newQuantity = NumOfChoice.numOfChoice(scan.nextDouble());
                        scan.nextLine();
                        break;
                    }
                    catch (InputMismatchException ime){
                        System.out.println("Please insert a number for quantity! Try again!");
                        scan.nextLine();
                        continue;
                    }
                    catch (NumOfChoiceException noce){
                        System.out.println(noce);
                        scan.nextLine();
                        continue;
                    }
                }

                for(int i=0; i<b.getRequestsList().size(); i++) {
                    if (b.getRequestsList().get(i).getRdEntities().get(i).getEntity() == o.getEntityByName(requestChoice)) {
                        b.getRequestsList().get(i).modify(b.getRequestsList().get(i).getRdEntities().get(i), newQuantity, o, b);
                        break;
                    }
                }
                menu.beneficiaryMenu(b, o, menu);
                break;
            case 3:
                menu.beneficiaryMenu(b, o, menu);
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

    public static void beneficiaryCommit(Organization o, Beneficiary b, Menu menu){
        boolean check=false;
        try {
            check = b.commit(b, o);
        }
        catch (Exception e){
            System.out.println("Something went wrong!");
        }


        if(check){
            System.out.println("Request committed successfully!");
        }
        else {
            System.out.println("Request list is empty!");
        }
        menu.beneficiaryMenu(b, o, menu);
    }
}
