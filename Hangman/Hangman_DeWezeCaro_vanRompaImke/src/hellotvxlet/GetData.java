package hellotvxlet;

import java.util.Scanner;

public class GetData {
    
    private Scanner input;
    
    public GetData()
    {
        input = new Scanner(System.in);
    }
    
    public String aWord() {
        return input.nextLine();
    }
    
    public int aNumber()
    {
        return input.nextInt();
    }
}
