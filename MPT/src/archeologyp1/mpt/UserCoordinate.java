package archeologyp1.mpt;

import java.util.Scanner;

// 1) Change a coordinate
// a) Change a single coordinate
// b) Change a row
// - wizard: feature, viewing symbol, excavated?, find
// 3) Change a viewing symbol
// a) Whole map
// b) Group of symbols
// 4) Print map
// 5) Export map
// 6) Save map

public class UserCoordinate {
        private static Scanner input;
        
        public UserCoordinate(){
        	input = new Scanner(System.in);
        }
        
        public static void changeCoordinate(){
                int userCoord;
                System.out.println("What would you like to change?");
                System.out.println("1 ) A single coordinate");
                System.out.println("2 ) An entire row");
                System.out.println("::> ");
                userCoord = input.nextInt();
        }
        
        public static void changeViewing(){
        		int userView;
                System.out.println("What would you like to change?");
                System.out.println("1 ) The whole map");
                System.out.println("2 ) A group of symbols");
                System.out.println("::> ");
                userView = input.nextInt();
                
        }
        
}
