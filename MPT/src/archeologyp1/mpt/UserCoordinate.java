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
        
        //int userCoord;
        //int userView;
        
        boolean bool = true;
        
        // changeCoord case 1 variables
        int a; //first user input in changeCoord
        int a2;
        int a3;
        Scanner sc = new Scanner(System.in);
        char c;
        int b;
        int check1; 
        
        //changeCoord case 2 variables
        int r1;
        int check2;
        
        public static changeCoordinate(int OR){
                
                /*
                System.out.println("What would you like to change?");
                System.out.println("1 ) A single coordinate");
                System.out.println("2 ) An entire row");
                System.out.println("::> ");
                userCoord = input.nextInt();
                */
                
                System.out.println("What would you like to do?");
                System.out.println("1 ) Change just one coordinate");
                System.out.println("2 ) Change an entire row");
                System.out.println("::> ");
                a = input.nextInt();
                
                switch(a){
                case 1:
                        
                        while(bool){
                                System.out.println("What column?");
                                System.out.println("::> ");
                                c = sc.next().charAt(0);
                                
                                System.out.println("What row?");
                                System.out.println("::> ");
                                b = input.nextInt();
                                
                                System.out.println("The following coordinate is what you want to change: "+c+", "+b);
                                System.out.println("If this is correct, please hit 1. If it is not, please hit 0.");
                                System.out.println("::> ");
                                check1 = input.nextInt();
                                
                                if(check1 == 1){
                                        bool = false;
                                }
                                else if(check1 == 0){
                                        continue;
                                }
                                else{
                                        System.out.println("This input is not correct. Please try again.");
                                        continue;
                                }
                        }
                        singleCoord(c, b);
                
                case 2: 
                        
                        bool = true;
                        
                        while(bool){
                                
                                System.out.println("Which row would you like to edit?");
                                System.out.println("::> ");
                                r1 = input.nextInt();
                                
                                System.out.println("The row you entered to edit is: "+r1);
                                System.out.println("If correct, please enter 1. If not, please hit 0.");
                                System.out.println("::> ");
                                check2 = input.nextInt();
                                
                                if(check2 == 1){
                                     bool = false;   
                                }
                                else if(check 2 == 0){
                                        continue;
                                }
                                else{
                                        System.out.println("This input is incorrect. Please try again.");
                                        continue;
                                }
                        }
                        
                        rowCoord(r1);
                        
                }
                
                
        }
        
        pubic static changeViewing(){
                
                /*
                System.out.println("What would you like to change?");
                System.out.println("1 ) The whole map");
                System.out.println("2 ) A group of symbols");
                System.out.println("::> ");
                userView = input.nextInt();
                */
                
        }
        
        public void singleCoord(char col, int row1){
                
        }
        
        public void rowCoord(int row2){
                
        }
        
}
