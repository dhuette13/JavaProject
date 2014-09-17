package archeologyp1.mpt;

import java.io.File;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import archeologyp1.shared.Map;
import archeologyp1.shared.Utilities;

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

public class UserCoordinate{
        
        int userCoord;
        int userView;
        
        public static changeCoordinate(){
                
                System.out.println("What would you like to change?");
                System.out.println("1 ) A single coordinate");
                System.out.println("2 ) An entire row");
                System.out.println("::> ");
                userCoord = input.nextInt();
                
        }
        
        pubic static changeViewing(){
                
                System.out.println("What would you like to change?");
                System.out.println("1 ) The whole map");
                System.out.println("2 ) A group of symbols");
                System.out.println("::> ");
                userView = input.nextInt();
                
        }
        
}
