import java.util.regex.*;
import java.util.*;

/**
 * TestRegexp class
 *
 * @author Manulaiko <manulaiko@gmail.com>
 * @package PACKAGE_NAME
 */
public class TestRegexp
{
    public static void main(String[] args)
    {
        Scanner in    = new Scanner(System.in);
        String pInput = "";

        while(!pInput.equalsIgnoreCase("exit")) {
            System.out.print("Enter pattern (exit to quit): ");
            pInput = in.nextLine();
            Pattern p = Pattern.compile(pInput);

            ArrayList<String> texts = new ArrayList<String>();
            String input = "";
            while(!input.equalsIgnoreCase("exit")) {
                System.out.print("Enter text (exit to quit): ");
                input = in.nextLine();
                texts.add(input);
            }

            for(String s : texts) {
                Matcher m = p.matcher(s);

                if(m.find()) {
                    System.out.println("Text '" + s + "' matches pattern '" + p.pattern() + "'");
                } else {
                    System.out.println("Text '" + s + "' doesn't match pattern '" + p.pattern() + "'");
                }
            }
        }
    }
}
