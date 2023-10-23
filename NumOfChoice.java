public class NumOfChoice {
    public static int numOfChoice (int a, int start, int finish) throws NumOfChoiceException{
        if(a<start || a>finish){
            throw new NumOfChoiceException("Please insert integers only from " + start + " to "+finish+"! Try again!");
        }
        return a;
    }

    public static int numOfChoice (int a) throws NumOfChoiceException{
        if(a<0){
            throw new NumOfChoiceException("Please only positive integer! Try again!");
        }
        return a;
    }
    public static double numOfChoice (double a) throws NumOfChoiceException{
        if(a<0){
            throw new NumOfChoiceException("Please only positive integer! Try again!");
        }
        return a;
    }
}
