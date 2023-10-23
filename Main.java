class Main {
    public static void main(String[] args) {
        Organization org = new Organization();

        Material m1 = new Material("Milk", "Drink", 100, 10.5, 20.5, 40.5);
        Service s1 = new Service("MedicalSupport", "Medical Support", 101);
        Material m2 = new Material("Sugar", "Food", 102, 4.5, 7.7, 16.0);
        Service s2 = new Service("NurserySupport", "Nursery Support", 103);
        Material m3 = new Material("Rice", "Food", 104, 25, 45.5, 80);
        Service s3 = new Service("BabySitting", "Baby Sitting", 105);


        org.entityList.add(m1);
        org.entityList.add(s1);
        org.entityList.add(m2);
        org.entityList.add(s2);
        org.entityList.add(m3);
        org.entityList.add(s3);

        Admin a1 = new Admin("Nikos Papagewrgiou", "69554822102");
        Beneficiary b1 = new Beneficiary("Giwrgos Liakos", "69835548287", 3);
        Beneficiary b2 = new Beneficiary("Dimitris Tsalikis", "69456877432", 1);
        Donator d1 = new Donator("Sakis Dimitriadis", "69884566732");

        Menu menu = new Menu();

        org.setAdmin(a1);
        org.insertBeneficiary(b1, org);
        org.insertBeneficiary(b2, org);
        org.insertDonator(d1, org);

        System.out.println();
        System.out.println("Admin: " + a1.getName() + " " + a1.getPhone());
        System.out.println("Beneficiary: " + b1.getName() + " " + b1.getPhone());
        System.out.println("Beneficiary: " + b2.getName() + " " + b2.getPhone());
        System.out.println("Donator: " + d1.getName() + " " + d1.getPhone());
        System.out.println();

        menu.identify(org, menu);

    }
}
