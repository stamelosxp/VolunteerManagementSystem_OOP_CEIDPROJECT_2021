import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;

class Menu {
    Scanner scan = new Scanner(System.in);
    private String phoneNum;

    public void identify(Organization o, Menu menu) {
        System.out.print("Insert your Phone number: ");
        phoneNum = scan.nextLine();

        boolean isadminphone = (phoneNum.equals(o.getAdmin().getPhone()));

        boolean isRegistered = false;

        if (isadminphone) {
            AdminMenu.adminMessage(o.getAdmin());
            adminMenu(o.getAdmin(), o, menu);
            isRegistered = true;
        }
        else
        {
            for (int i = 0; i < o.donatorList.size(); i++) {
                if (phoneNum.equals(o.donatorList.get(i).getPhone())) {
                    donatorMenu(o.donatorList.get(i), o, menu);
                    isRegistered = true;
                    break;
                }
            }
            for (int i = 0; i < o.beneficiaryList.size(); i++) {
                if (phoneNum.equals(o.beneficiaryList.get(i).getPhone())) {
                    beneficiaryMenu(o.beneficiaryList.get(i), o, menu);
                    isRegistered = true;
                    break;
                }
            }


            System.out.println("Would you like to register to our Organization? ");
            while (true) {
                if (!isRegistered) {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Please insert y (for yes) or n (for no): ");
                    String register = scan.nextLine();

                    if (register.toUpperCase().compareTo("Y")==0 || register.toUpperCase().compareTo("YES")==0) {
                        System.out.println(
                                "\nRegister as: "
                                + "\n1. Donator "
                                + "\n2. Beneficiary"
                                + "\n3. Back"
                                + "\n4. Exit");
                        int choiceDorB;
                        while (true) {
                            try {
                                System.out.print("Insert number of your choice: ");
                                choiceDorB = NumOfChoice.numOfChoice(scan.nextInt(), 0, 5);
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
                        switch (choiceDorB) {
                            case 1:
                                System.out.print("Insert your name: ");
                                String nameChoice = "";
                                nameChoice += scan.nextLine();
                                boolean registerChoice = true;
                                System.out.println("Would you like to register with this phone number " + phoneNum + "?");
                                while (registerChoice) {
                                    System.out.print("Please insert y (for yes) or n (for no): ");
                                    String checkNumChoice = scan.nextLine();
                                    if (checkNumChoice.toUpperCase().compareTo("Υ")==0 || checkNumChoice.toUpperCase().compareTo("YES")==0) {
                                        Donator d = Donator.newDonator(nameChoice, phoneNum);
                                        try {
                                            UserExists.insertDon(d, o);
                                        }
                                        catch (UserExistsException uee){
                                            System.out.println(uee);
                                            identify(o, menu);
                                        }

                                        o.insertDonator(d, o);
                                        DonatorMenu.donatorMessage(d);
                                        donatorMenu(d, o, menu);
                                        break;
                                    }
                                    else if (checkNumChoice.toUpperCase().compareTo("N")==0 || checkNumChoice.toUpperCase().compareTo("NO")==0) {
                                        System.out.print("Insert the phone number you would like to use: ");
                                        String newPhoneNum = scan.nextLine();
                                        Donator d = Donator.newDonator(nameChoice, newPhoneNum);
                                        try {
                                            UserExists.insertDon(d, o);
                                        }
                                        catch (UserExistsException uee){
                                            System.out.println(uee);
                                            identify(o, menu);
                                        }
                                        o.insertDonator(d, o);
                                        DonatorMenu.donatorMessage(d);
                                        donatorMenu(d, o, menu);
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("Insert your name: ");
                                String nameChoice2 = "";
                                nameChoice2 += scan.nextLine();

                                int numOfPeople;
                                while (true) {
                                    try {
                                        System.out.print("How many people are in your household: ");
                                        numOfPeople = NumOfChoice.numOfChoice(scan.nextInt());
                                        scan.nextLine();
                                        break;
                                    }
                                    catch (InputMismatchException ime) {
                                        System.out.println("Please integer! Please try again!");
                                        scan.nextLine();
                                        continue;
                                    }
                                    catch (NumOfChoiceException noce) {
                                        System.out.println(noce);
                                        scan.nextLine();
                                        continue;
                                    }
                                }

                                System.out.println("Would you like to register with this phone number " + phoneNum + "?");

                                while (true) {
                                    System.out.print("Please insert y (for yes) or n (for no): ");
                                    String checkNumChoicex = scan.nextLine();

                                    if (checkNumChoicex.toUpperCase().compareTo("Y")==0 || checkNumChoicex.toUpperCase().compareTo("YES")==0) {
                                        Beneficiary b = Beneficiary.newBeneficiary(nameChoice2, phoneNum, numOfPeople);
                                        try {
                                            UserExists.insertBen(b, o);
                                        }
                                        catch (UserExistsException uee){
                                            System.out.println(uee);
                                            identify(o, menu);
                                        }
                                        o.insertBeneficiary(b, o);
                                        BeneficiaryMenu.beneficiaryMessage(b);
                                        beneficiaryMenu(b,o,menu);
                                        break;
                                    }
                                    else if (checkNumChoicex.toUpperCase().compareTo("N")==0 || checkNumChoicex.toUpperCase().compareTo("NO")==0) {
                                        System.out.print("Insert the phone number you would like to use: ");
                                        String newPhoneNum = scan.nextLine();
                                        Beneficiary b = Beneficiary.newBeneficiary(nameChoice2, newPhoneNum, numOfPeople);
                                        try {
                                            UserExists.insertBen(b, o);
                                        }
                                        catch (UserExistsException uee){
                                            System.out.println(uee);
                                            identify(o, menu);
                                            break;
                                        }
                                        o.insertBeneficiary(b, o);
                                        BeneficiaryMenu.beneficiaryMessage(b);
                                        beneficiaryMenu(b, o, menu);
                                        break;
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("You are logging out...");
                                identify(o, menu);
                                break;
                            case 4:
                                System.out.println("Thank you for checking us out!");
                                System.exit(0);
                                break;
                        }
                        break;
                    }
                    else if (register.toUpperCase().compareTo("N")==0 || register.toUpperCase().compareTo("NO")==0) {
                        System.out.println("Thank you for checking us out!");
                        System.exit(0);
                    }
                }

            }
        }
    }

    public void donatorMenu(Donator d, Organization o, Menu menu) {
        int numOfChoice = DonatorMenu.mainMenu();
        while (true) {
            switch (numOfChoice) {
                case 1:
                    DonatorMenu.addOffers(d, o, menu);
                    break;
                case 2:
                    DonatorMenu.showOffers(d, o, menu);
                    break;
                case 3:
                    DonatorMenu.donatorCommit(d, o, menu);
                    break;
                case 4:
                    System.out.println("You are logging out...");
                    identify(o, menu);
                    break;
                case 5:
                    System.out.println("Thank you for checking us out!");
                    System.exit(0);
                    break;
            }
        }
    }

    public void beneficiaryMenu(Beneficiary b, Organization o, Menu menu) {
        int numOfChoice = BeneficiaryMenu.mainMenu();
        while (true) {
            switch (numOfChoice) {
                case 1:
                    BeneficiaryMenu.addRequests(b, o, menu);
                    break;
                case 2:
                    BeneficiaryMenu.showRequests(b, o, menu);
                    break;
                case 3:
                    BeneficiaryMenu.beneficiaryCommit(o, b, menu);
                    break;
                case 4:
                    System.out.println("You are logging out...");
                    identify(o, menu);
                    break;
                case 5:
                    System.out.println("Thank you for checking us out!");
                    System.exit(0);
                    break;
            }
        }
    }

    public void adminMenu(Admin a, Organization o, Menu menu){
        int numOfChoice = AdminMenu.mainMenu();
        while (true){
            switch (numOfChoice){
                case 1:
                    AdminMenu.viewAdmin(a, o, menu);
                    break;
                case 2:
                    AdminMenu.monitorOrgAdmin(a, o, menu);
                    break;
                case 3:
                    System.out.println("You are logging out...");
                    identify(o, menu);
                    break;
                case 4:
                    System.out.println("Thank you for checking us out!");
                    System.exit(0);
                    break;
            }
        }
    }
}