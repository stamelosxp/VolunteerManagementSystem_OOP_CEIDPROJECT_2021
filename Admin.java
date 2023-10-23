public class Admin extends User{
    private static boolean isAdmin = true;

    public Admin(String name, String phone) {
        super(name, phone);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
