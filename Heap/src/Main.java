//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        System.out.println(adder("Top"));
    }
    public static int adder(String word){
        int arr2 = 0;
        for (int i = 0; i<word.length();i++){
            char k = word.charAt(i);
            arr2+=(int)k;
        }
        return arr2;
    }

}