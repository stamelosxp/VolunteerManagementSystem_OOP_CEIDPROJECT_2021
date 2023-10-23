public class RequestsCheck {
    public static void requestsCheck(Requests r) throws RequestsCheckException{
        if(!(r.isCheckBen() && r.isCheckQuant())){
            throw new RequestsCheckException("Something went wrong! Unsuccessful commit request!");
        }
    }
}
