import java.util.Scanner;
import java.lang.Math;

public class Game_Console
{
    public static void main(String args[])
    {
        Game_Console ob = new Game_Console();
        
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Game Console!!!!");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println("This console has two of the best games which I have created...>>>");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println();
        System.out.println("The games are intelligence and brain logic based – Challenging.");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println("Get ready to stress your brain!!!");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println();
        System.out.println("The Gaming Console provides two facilities -->>> ");
        for(int i = 1; i <= 80; i++) ob.wait1();
        
        System.out.println();
        System.out.println("1. Tower of Hanoi");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println("Tower Of Hanoi is a visual logic game based upon logic of moves and accuracy.");
        for(int i = 1; i <= 80; i++) ob.wait1();
        
        System.out.println();
        System.out.println("2. 4 Colour Code Guess");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println("4 Color Code Guess is  a code cracking game based upon intelligence of interpreting clues and cracking the code.");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println();
        
        System.out.println("3. Sudoku Solver");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println("Sudoku Solver is an intelligent solver which can solve sudokus within a blink of an eye!!!!!");
        for(int i = 1; i <= 80; i++) ob.wait1();
        System.out.println();
        
        System.out.println("Which game do you wish to play or do you want the solver to solve your sudoku!!!!... Enter the number --->>>");
        int Game_choose = in.nextInt();
        
        switch(Game_choose)
        {
            case 1:
            {
                System.out.println("Absolutly fine.. so you wish to play... TOWER OF HANOI....>>>> ");
                System.out.println("So Here you go with it....>>>>> ");
                for(int i = 1; i <= 10; i++)
                {
                    ob.wait2();ob.wait2();ob.wait2();
                    System.out.print(".> ");
                }
                System.out.print('\u000C');
                ob.Tower_Of_Hanoi_main();
                break;
            }
            
            case 2:
            {
                System.out.println("Absolutly fine.. so you wish to play... Colour_Code_Guess....>>>> ");
                System.out.println("So Here you go with it....>>>>> ");
                for(int i = 1; i <= 10; i++)
                {
                    ob.wait2();ob.wait2();ob.wait2();
                    System.out.print(".> ");
                }
                System.out.print('\u000C');
                ob.Colour_Code_Guess_main();
                break;
            }
            
            case 3:
            {
                System.out.println("Absolutly fine.. So you wish to Solve Sudokus....>>>>>> ");
                System.out.println("So Here you go with it....>>>>> ");
                for(int i = 1; i <= 10; i++)
                {
                    ob.wait2();ob.wait2();ob.wait2();
                    System.out.print(".> ");
                }
                System.out.print('\u000C');
                ob.Sudoku_Solver_main();
                break;
            }
        }
    }
    
    Scanner in = new Scanner(System.in);
    
    int tower[][] = new int[4][50];
    int tower_no_from;
    int tower_no_to;
    int tow_flr;
    String plr_nm[] = new String[50];
    int block_move_up;
    int block_move_down;
    boolean move_right;
    int move_tower; 
    int animation;
    public  void Tower_Of_Hanoi_main()
    {
        System.out.print('\u000C');
        animation_mode_in();
        game_first_page(animation);
        instructions_rules(animation);
        int tot_plrs = multiplayer();
        
        String msg = "                                        How many number of floors you want in your TOWER OF HANOI";
        tow_flr = input_integer_check(msg, 48, 57);
        
        int move_cnt[] = new int[tot_plrs + 1];
        for(int plr = 1; plr <= tot_plrs; plr++)
        {
            name_in_n_start(plr);
            tower_declare(tow_flr);
            
            System.out.print('\u000C'); 
            for(int z = 1; z <= tow_flr; z++) 
                construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
                
            for(;;)
            {
                if(game_complete_check(tow_flr))
                {
                    String duplct_nm = plr_nm[plr];
                    game_complete(move_cnt[plr], duplct_nm, tot_plrs);
                    break;
                }
                
                System.out.println();
                System.out.println("MOVE NUMBER =>>>> " + move_cnt[plr]);
 
                block_move_input();
                
                if((block_move_input_check(tower_no_from, tower_no_to, tow_flr) == 2))
                {
                    err_msg(block_move_input_check(tower_no_from, tower_no_to, tow_flr));
                    move_cnt[plr] = move_cnt[plr] + 10;
                    continue;
                }
                else if((block_move_input_check(tower_no_from, tower_no_to, tow_flr) == 3))
                {
                    err_msg(block_move_input_check(tower_no_from, tower_no_to, tow_flr));
                    move_cnt[plr] = move_cnt[plr] + 10;
                    continue;
                }
                
                System.out.print('\u000C'); 
                if(animation == 2)
                {
                    block_move(tower_no_from, tower_no_to, tow_flr);
                    for(int z = 1; z <= tow_flr; z++) 
                        construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
                }
                else if(animation == 1)
                    animation(tower_no_from, tower_no_to, tow_flr);
                ++move_cnt[plr];
            }
        }
        if(tot_plrs >= 2)
            multiplayer_result(move_cnt, plr_nm, tot_plrs);
    }
    
    public  void  animation_mode_in()
    {   
        String msg1 = "                                  Do you wish to have animation in your game....>>>> 1. Yes or 2. No...";
        animation = input_integer_check(msg1, 49, 50);
        
        System.out.println("Enter any key to begin...>>>");
        String start = in.next();
    }
    
    public  void name_in_n_start(int player)
    {
        System.out.print('\u000C');
        System.out.println("                                                     What is your sweet name???" );            
        plr_nm[player] = in.next();
        
        System.out.println();
        System.out.println("                     ALL SET???? READY FOR THE IMMEDIATE DUTY OF RESCUE MISSION!!!!...>>>>> ENTER ANY KEY!!!");
        String begin = in.next();
    }
    
    public   void tower_declare(int tow_flr)
    {
        for(int i = 1; i <= tow_flr; i++)
            tower[1][i] = i;
            
        for(int i = 1; i <= tow_flr; i++)
            tower[2][i] = 0;
            
        for(int i = 1; i <= tow_flr; i++)
            tower[3][i] = 0;
    }
  
    public  void block_move_input()
    {
        System.out.println();
        String msg = "The 'NUMBER OF AREA' 'from' which the the blcok has to be lifted....>>>> AND The 'NUMBER OF AREA' 'to' which the block has to placed....>>>>";
        tower_no_from = input_integer_check(msg, 48, 57);
        tower_no_to = input_integer_check(msg, 48, 57);
    }
    
    public  int block_move_input_check(int check_no1, int check_no2, int tow_flr)
    {
        int block_move_input_check = 1;
        if((check_no1 >= 1 && check_no1 <= 3) && (check_no2 >= 1 && check_no2 <= 3))
        {
            for(int i = 1; i <= tow_flr; i++)
            {
                if(tower[check_no1][i] != 0)
                {   
                    for(int j = 1; j <= tow_flr; j++)
                    {
                        if(tower[check_no2][j] != 0)
                        {
                            if(tower[check_no1][i] > tower[check_no2][j])
                                block_move_input_check = 2;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        else
            block_move_input_check = 3;
        return(block_move_input_check);
    }

    public  void block_move(int tower_no_start, int tower_no_end, int tow_flr)
    {
        int block_swap = 0;
      
        for(int i = 1; i <= tow_flr; i++)
        {
            if(i == tow_flr)
            {
                block_swap = tower[tower_no_start][i];
                tower[tower_no_start][i] = 0;
                
                for(int j = 1; j <= tow_flr; j++)
                {
                    if(j == tow_flr)
                    {
                        tower[tower_no_end][j] = block_swap;
                        block_swap = 0;
                        break;
                    }
                    else if(tower[tower_no_end][j + 1] != 0)
                    {
                        tower[tower_no_end][j] = block_swap;
                        block_swap = 0;
                        break;
                    }
                }
                break;
            }
            else if(tower[tower_no_start][i] != 0)
            {
                block_swap = tower[tower_no_start][i];
                tower[tower_no_start][i] = 0;
                
                for(int j = 1; j <= tow_flr; j++)
                {
                    if(j == tow_flr)
                    {
                        tower[tower_no_end][j] = block_swap;
                        block_swap = 0;
                        break;
                    }
                    else if(tower[tower_no_end][j + 1] != 0)
                    {
                        tower[tower_no_end][j] = block_swap;
                        block_swap = 0;
                        break;
                    }
                }
                break;
            }
        }
    }    
    
    public  void construct_tower(int terms1, int terms2, int terms3, int tow_flr, int pole_pr)
    {
        int series_terms[] = new int[4], duplct_terms = 0, l, j, o, r, fr_vb = 1, sp_pr = 0; 
        int for_run_double_print = 3,tow_flr_multiply = 4;
        series_terms[1] = terms1; 
        series_terms[2] = terms2; 
        series_terms[3] = terms3;
        
        if(tow_flr >= 6)
        {
            for_run_double_print = 1;
            tow_flr_multiply = 2;
        }
        else if(tow_flr >= 4)
            for_run_double_print = 2;
           
        for(int k = 1; k <= for_run_double_print; k++)
        {
            for(int p = 1; p <= 2; p++)
            {
                System.out.println();
                for(o = 1; o < 4; o++)
                {
                    duplct_terms = (tow_flr * tow_flr_multiply) - (series_terms[o] * tow_flr_multiply); 
                        
                    if((series_terms[o] == 0) && (pole_pr == 2))    
                    {
                        for(int s = 1; s <= duplct_terms - 1; s++)
                            System.out.print(" ");    
                        System.out.print("||");    
                    }
                    else
                    {
                        for(int s = 1; s <= duplct_terms; s++)
                            System.out.print(" ");
                    }
                        
                    if (p == 1)
                    {
                        for(r = 1; r <= (series_terms[o] * tow_flr_multiply * 2); r++)
                            System.out.print("_");   
                    }
                    else if(p == 2)
                    {
                        for(j = 1; j <= series_terms[o] * tow_flr_multiply; j++)
                            System.out.print("[");
                        for(l = series_terms[o] * tow_flr_multiply; l >= 1; l--)
                            System.out.print("]");                                            
                    }
                    if((series_terms[o] == 0) && (pole_pr == 2))
                    {
                        for(int s = 1; s <= duplct_terms - 1; s++)
                            System.out.print(" ");                    
                    }
                    else
                    {
                        for(int s = 1; s <= duplct_terms ; s++)
                            System.out.print(" ");                    
                    }
                }          
            }
        }   
    }
    
    public  void construct_tower_animation(int terms1, int terms2, int terms3, int tow_flr, boolean mov_right)
    {   
        int series_terms[] = new int[4], duplct_terms = 0, l, j, o, r, fr_vb = 1, sp_pr = 0; 
        int for_run_double_print = 3,tow_flr_multiply = 4, tower_2_3_pr = 4, animation_sp;
        series_terms[1] = terms1; 
        series_terms[2] = terms2; 
        series_terms[3] = terms3;
        
        if((terms2 == 0) && (terms3 == 0))
            tower_2_3_pr = 2;
        if(tow_flr >= 6)
        {
            for_run_double_print = 1;
            tow_flr_multiply = 2;
        }
        else if(tow_flr >= 4)
            for_run_double_print = 2;
           
        for(int i = 1; i <= (((tow_flr * tow_flr_multiply) / 2) * move_tower); i++)
        {
            System.out.print('\u000C');
            for(int k = 1; k <= for_run_double_print; k++)
            {
                for(int p = 1; p <= 2; p++)
                {
                    System.out.println();
                    
                    for(o = 1; o < tower_2_3_pr; o++)
                    {
                        duplct_terms = (tow_flr * tow_flr_multiply) - (series_terms[o] * tow_flr_multiply); 
                        
                        if(o == 1)
                        {
                            if(mov_right)
                                animation_sp = i;
                            else
                                animation_sp = (i * -1);
                        }
                        else
                            animation_sp = 0;
                        
                        for(int s = 1; s <= ((duplct_terms / 4) + (animation_sp)); s++)
                            System.out.print("    ");
                        if (p == 1)
                        {
                            for(r = 1; r <= (series_terms[o] * tow_flr_multiply * 2); r++)
                                System.out.print("_");
                        }
                        else if(p == 2)
                        {
                            for(j = 1; j <= series_terms[o] * tow_flr_multiply; j++)
                                System.out.print("[");        
                            for(l = series_terms[o] * tow_flr_multiply; l >= 1; l--)
                                System.out.print("]");                                            
                        }
                        for(int s = 1; s <= duplct_terms ; s++)
                            System.out.print(" ");                    
                    }          
                }
            }
            
            for(int z = 1; z <= tow_flr; z++) 
                construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
            wait1();wait1();wait1();
        }    
           
    }
    
    public  void animation(int tow_no1, int tow_no2, int tow_flr)
    {
        tower_check_animation(tow_no1, tow_no2, tow_flr);
        int block_move_no = tower[tow_no1][block_move_up];
        
        for(int i = block_move_up; i >= 1;  i--)
        {
            wait1();wait1();wait1();
            System.out.print('\u000C');
            tower[tow_no1][i - 1] = tower[tow_no1][i];
            tower[tow_no1][i] = 0;
            
            construct_tower(0,0,0,tow_flr, 1);
            for(int z = 1; z <= tow_flr; z++)
                construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
        }
        
        if(tow_no1 == 1) construct_tower_animation(block_move_no, 0, 0, tow_flr, move_right);
        else if(tow_no1 == 2) construct_tower_animation(0, block_move_no, 0, tow_flr, move_right);
        else if(tow_no1 == 3) construct_tower_animation(0, 0, block_move_no, tow_flr, move_right);

        tower[tow_no2][1] = block_move_no;
        System.out.print('\u000C');
        
        construct_tower(0,0,0,tow_flr, 1);
        for(int z = 1; z <= tow_flr; z++) 
            construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
        
        for(int j = 1; j <= block_move_down - 1; j++)
        {
            wait1();wait1();wait1();
            System.out.print('\u000C');
            tower[tow_no2][j + 1] = tower[tow_no2][j];
            tower[tow_no2][j] = 0;
            
            construct_tower(0,0,0,tow_flr, 1);
            for(int z = 1; z <= tow_flr; z++)
                construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
            
        }
    }
    
    public  void tower_check_animation(int tower_no1, int tower_no2, int tow_flr)
    {
        block_move_up = 0;
        block_move_down = 0;
        move_tower = 1;
        move_right = true;
        for(int i = 1; i <= tow_flr; i++)
        {
            if(i == tow_flr)
                block_move_up = tow_flr;
            else if(tower[tower_no1][i] != 0)
            {
                block_move_up = i;
                break;
            }
        }
        for(int j = 1; j <= tow_flr; j++)
        {
            if(tower[tower_no2][tow_flr] == 0)
            {
                block_move_down = tow_flr;
                break;
            }
            else if(tower[tower_no2][j] != 0)
            {
                block_move_down = j - 1;
                break;
            }
        }
        if(tower_no1 > tower_no2)   
            move_right = false;
        if(((tower_no1 - tower_no2) == 2) || ((tower_no1 - tower_no2) == -2))
            move_tower = 2;
    }
    
    public  void wait1()
    {
        int wait;
        for(long i = 1; i <= 30000000; i++)
            wait = 1;
    }
    
    public  void wait2()
    {
        long wait = System.currentTimeMillis();
        for(;;)
        {
            if(System.currentTimeMillis() - wait >= 50)
                break;
        }    
    }
    
    public  int multiplayer()
    {
        System.out.print('\u000C');
        int mode;
        System.out.println("                                                     Choose your Game Mode ==>>>>");
        System.out.println();
        System.out.println("                                                   1.Single Player   2.Multi Player");
        System.out.println();
              String msg = "                                            Which game Mode would you prefer to play in ????  ";
        mode = input_integer_check(msg, 49, 50);
        
        int tot_plrs = 1;
        if(mode == 2)
        {
            System.out.println("                                     Ohh!!! That's Awesome we have many RESCUE LIUTINENT MARSHALS!!!!");
            System.out.println("So!!! The MARSHAL who transfers the tower in MINIMUM NUMBER of MOVES is \"GRAND MASTER HEAD OF LIUTENENT MARSHALS\" and also the WINNER");
            System.out.println();
                     msg = "                                       How many MARSHALS(players) do we have for RESCUE MISSION...!!!!  ";
            tot_plrs = input_integer_check(msg, 48, 57);
        }
        return(tot_plrs);
    }
    
    public  void multiplayer_result(int []mov_cnt, String []plar_nm, int total_plrs)
    {
        System.out.print('\u000C');
        System.out.println("                                          What a ROUGH and TOUGH competetion it was INDEED!!!!!!");
        System.out.println();
        System.out.println("                                            Now the MOST AWAITED MOMENT COMES....!!!!!!>>>>>!!");
        System.out.println();
        System.out.println("                                        THE DECLARARTION OF RESULTS OF THE MULTIPLAYER COMPETETION ");
        System.out.println();
        System.out.println("                                It was tough to decide but nevertheless we have the results...... >>>>>>>");
        System.out.println();
        System.out.println();
        
        long min;
        int n = 1, i, x, minPos;
        System.out.println();
        
        long ans[] = new long[total_plrs + 1];
        int swap[] = new int[total_plrs + 1];
        String swap1 = "";
        
        for (int z = 1; z <= total_plrs; z++)
        {
            min = mov_cnt[z];
            minPos = z;
            
            for(int y = z; y <= total_plrs; y++)
            {
                if (mov_cnt[y] < min)
                {
                    min = mov_cnt[y];
                    minPos = y;
                }        
            }
            
            swap1 = plar_nm[z];
            plar_nm[z] = plar_nm[minPos];
            plar_nm[minPos] = swap1;
            
            swap[z] = mov_cnt[z];
            mov_cnt[z] = mov_cnt[minPos];
            mov_cnt[minPos] = swap[z];
            
            ans[z] = min;
        }
        
        for (x = 1; x <= total_plrs; x++)
        {    
            System.out.println();
            if(x == 1)
                System.out.println("The GRAND WINNER HEAD LIUTINENT MARSHAL OF RESCUE .... TO LEAD THE TRAIL IS....  " + plar_nm[1]);
            else if(x == 2)
                System.out.println("The RUNNUER UP.... To FOLLOW " + plar_nm[1] + " IS " + plar_nm[2]);
            else if(x == 3)
                System.out.println("HERE COMES THE BRONZE MEDALIST.... AFTER " + plar_nm[3]);
            else if(x >= 4)
            {
                if(x == 4)
                    System.out.println("The ranking of our dear other marshals follows.... here ..>>>>");
                System.out.print(x + "}} " + plar_nm[x] + "  Moves taken...>>>  " +mov_cnt[x]);
            }
        }
        
    }
    
    public  void err_msg(int err_no)
    {
        for(int i = 1; i <= 15; i++)
            System.out.println();
        if(err_no == 2)
        {    
            System.out.println("                                               OOPS YOU HAVE BROKEN A RULE ----->>>>>>"); 
            System.out.println(); System.out.println();
            System.out.println("                                     A BIGGER SIZE BLOCK CANNOT BE PLACED ON A SMALLER SIZE BLOCK");    
            System.out.println(); System.out.println();
            System.out.println("            This MISTATKE and CARELESSNESS of yours has damaged the ANCIENT TOWER and taken many PRECIOUS LIVES OF PEOPLE!!");
            System.out.println(); System.out.println();
            System.out.println("                                       YOUR PENALTY IS ---- YOUR MOVE COUNT IS INCREASED BY 10 ");
        }
        else if(err_no == 3)
        {
            System.out.println("                                            OOPS YOU HAVE COMMITED A MISTAKE ----->>>>>>"); 
            System.out.println(); System.out.println();
            System.out.println("                                    THE TOWER NUMBER FOR BLOCK MOVE SHOULD BE BETWEEN 1 AND 3!!!");
            System.out.println(); System.out.println();
            System.out.println("                           THIS WRONG DECISION OF YOURS HAS WASTED THE PRESTIGIOUS TIME OF THE RESCUE !!!");
            System.out.println(); System.out.println();
            System.out.println("                                       YOUR PENALTY IS ---- YOUR MOVE COUNT IS INCREASED BY 10 ");
        }
        System.out.println();
            System.out.println("                                     Enter any letter key or Character key to continue the game");
        for(int i = 1; i <= 15; i++)
            System.out.println();    
        String c = in.next();
        
        System.out.print('\u000C');
        for(int z = 1; z <= tow_flr; z++) 
            construct_tower(tower[1][z], tower[2][z], tower[3][z], tow_flr, 2);
    }
    
    public  int input_integer_check(String msg, int digit_start, int digit_end)
    {        
        System.out.println(msg);
        String input_check = "";
        boolean check = false;
        int len = 0;
        for(;;)
        {
            input_check = in.next();
            len = input_check.length();
            for(int j = 0; j <= (len - 1); j++)
            {
                for(int i = digit_start; i <= digit_end; i++)
                {
                    if(input_check.charAt(j) == (char)i)
                    {
                        check = true;
                        break;
                    }
                    else
                        check = false;
                }
                if(!check)
                    break;
            }
            if(!check)
                System.out.println("                                         PLEASE ENTER THE CORRECT INPUT AS SPECIFIED ABOVE!!!!!");
            else 
                break;
        }
        int int_input_check = Integer.parseInt(input_check);
        return int_input_check;
    }
    
    public  boolean game_complete_check(int tow_flr)
    {
        boolean game_complete_chk = true;
        for(int m = 1; m <= tow_flr; m++)
        {
            if((tower[3][m] != m))
                game_complete_chk = false;
        }
        return(game_complete_chk);
    }
    
    public  void game_complete(int move_cnt, String plr_nm, int tot_plrs)
    {
        for(int i = 1; i <= 10; i++)
            System.out.println();
        System.out.println("                                      Awesome!!!! Congratulations " + plr_nm + " You made it!!!!!!!");
        System.out.println();
        System.out.println("                  Marshal you have transferred the TOWER OF HANOI SUCCESFULLY and saved the lives of hundreds of people!!!!!!");
        System.out.println();
        System.out.println("                               Today it is due to your efforts that the MAGESTIC TOWER OF HANOI IS SAFE!!!");        
        System.out.println();
        System.out.println("                                          You have completed the mission in " + move_cnt + " moves");
        System.out.println();
        System.out.println("                                     The whole CITY of SALLINS IS GRATEFUL.. THANKFUL.. A LOT TO YOU!!!");
        System.out.println();
        System.out.println("                                       Thanks a lot AGAIN " + plr_nm + " See you at next emergency!!!");
        System.out.println();
        if(tot_plrs >= 2)
        {
            System.out.println("                                Please press any letter key to continue with the next player"); 
            for(int i = 1; i <= 15; i++)
                System.out.println();    
            String c = in.next();
        }
    }
    
    public  void game_first_page(int animate)
    {
        System.out.print('\u000C');
        int wait_run = 8;
        if(animate == 2)
            wait_run = 0;
        System.out.println("                                          ");
        System.out.println("                                              TTTTTTTTTT     0000      W             W   EEEEEEEEEE   RRRRR    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                                  TT        0    0     W             W   EE           R    R   ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                                  TT       0      0     W     W     W    EEEEEE       RRRRR    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                         __                       TT       0      0      W   W W   W     EEEEEE       RR       ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                         []                       TT        0    0        W W   W W      EE           R RR     ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                        ____                      TT         0000          W     W       EEEEEEEEEE   R   RR   ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                        [[]]                                                                                   ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                      ________                                       0000       FFFFFFFFFFF               ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                      [[[[]]]]                                      0    0      FF                       ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                    ____________                                   0      0     FFFFF                    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                    [[[[[[]]]]]]                                   0      0     FFFFF                    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                  ________________                                  0    0      FF                       ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                  [[[[[[[[]]]]]]]]                                   0000       FF                       ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                ____________________          ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                [[[[[[[[[[]]]]]]]]]]          ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("              ________________________                        HH     HH         A         NN      N      000000     IIIIIIIIII");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("              [[[[[[[[[[[[]]]]]]]]]]]]                        HH     HH        A A        N N     N     0      0        II    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("            ____________________________                      HH     HH       A   A       N  N    N    0        0       II    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("            [[[[[[[[[[[[[[]]]]]]]]]]]]]]                      HHHHHHHHH      A     A      N   N   N   0          0      II    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("          ________________________________                    HH     HH     AAAAAAAAA     N    N  N    0        0       II    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("          [[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]                    HH     HH    A         A    N     N N     0      0        II    ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("        ____________________________________                  HH     HH   A           A   N      NN      000000     IIIIIIIIII");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("        [[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]  ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("      ________________________________________");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("      [[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("    ____________________________________________");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("    [[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]                                    ENTER ANY KEY TO BEGIN THE BRAINY CLASH!!!!");
        for(int i = 1; i <= wait_run; i++) wait1();
        String start = in.next();
        for(int j = 1; j <= 30; j++)
        {
            System.out.println();
            for(int i = 1; i <= wait_run; i++) wait1();
        }
        System.out.print('\u000C');
    }   
    
    public  void instructions_rules(int anim)
    {
        int wait_run = 80;
        if(anim == 2)
            wait_run = -20;
        
        System.out.println("                                    Attention! Help!! Emergency!!! Disaster!!!! Everyone...Listen!!!!");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                          A SEVERE EARTHQUAKE and SHOCK WAVE RELEASE is reported from the SALLINS HANOI AREA!!!!");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                              The ANCIENT HISTORIC ARCHEOLOGICAL \"\"TOWER OF HANOI\"\" is in SERIOUS DANGER");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println();
        System.out.println("       You are the LIEUTINENT MARSHAL OF RESCUE and have been asigned the heavy task of transferring the tower to Safe Zone -- 'Area 3'");
        for(int i = 1; i <= wait_run + 20; i++) wait1();
        System.out.println("                                         The tower is presently in the Danger zone -- 'Area 1' ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                There is also a Partial Danger Zone -- 'Area 2' which is also unsafe!!!");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println();
        System.out.println("                                You have to transfer the tower block by block to SAFE ZONE -- 'Area 3' ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                            To transfer a block you have to enter --- ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                               The 'NUMBER OF AREA' 'from' which the the blcok has to be lifted....>>>> AND ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                    The 'NUMBER OF AREA' 'to' which the block has to placed....>>>>");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                This will transfer the block at the top of the tower to the desired area!!");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                                           THE RULES AND REGULATIONS OF THE GAME ARE....>>>>");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("                   1.>> You can only transfer the block at a top of the tower of any area from one area to other..");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println();
        System.out.println("                              2.>> You cannot place a block of bigger size onto a block of smaller size -- ");
        for(int i = 1; i <= wait_run; i++) wait1();
        System.out.println("               As it will lead to the destruction of the ancient blocks of the tower and death of hundreds of peolpe..>>>");
        for(int i = 1; i <= wait_run + 20; i++) wait1();
        System.out.println();
        System.out.println("    Area 1            Area 2           Area 3     ");
        System.out.println(" (Danger Zone)     (Danger Zone)    (Safe Zone)   ");
        System.out.println("      __                ||              ||        ");
        System.out.println("      []                ||              ||        ");
        System.out.println("    ______              ||              ||        ");
        System.out.println("    [[[]]]              ||              ||        ");
        System.out.println("  __________            ||              ||        ");
        System.out.println("  [[[[[]]]]]            ||              ||        ");
        System.out.println("______________          ||              ||        ");
        System.out.println("[[[[[[[]]]]]]]          ||              ||        ");
        System.out.println("                 For example -- If you have to move the block at the top of area 1 to area 3 ... You should enter ..>>");
        for(int i = 1; i <= wait_run + 20; i++) wait1();
        System.out.println("                      \"1 3\" then press 'Enter Key' OR \" Enter '1' then press 'ENTER KEY' and then enter '3' \"");
        for(int i = 1; i <= wait_run + 20; i++) wait1();
        System.out.println("                               Ready all set!!! Everything clear then enter any key to begin!!11"); 
        String start = in.next();
    }
    
    public  void Colour_Code_Guess_main()
    {
        Scanner in =  new Scanner(System.in);
        System.out.println("");
        System.out.println("Welcome!!! to the '4 COLOUR CODE GUESS GAME'!!!!!!!...");
        System.out.println("PLaying this game Will be a TEST for your IQ*** ");
        System.out.println("The game will generate a SECRET INTELLIGENCE CODE(S.I.C) of any 4 colours from the six colours given ---- ");
        System.out.println("1.'Red' 2.'Green' 3.'Black' 4.'Blue' 5.'Yellow' 6.'White' ");
        System.out.println("The S.I.C may also have repeated colours ");
        System.out.println("Guess the colours of the S.I.C and for that u get 10 chances ");
        System.out.println("So get on with it !!!!! ");
        System.out.println("Put the name of the colours and after every colour put a space ");
        System.out.println(" 'W' will be printed if the colours entered are present in the S.I.C but are present at the wrong place ");
        System.out.println(" 'C' will be printed if the colours entered are present in the S.I.C and also present at the correct place ");
        System.out.println(" Along with these hints the number of the colours present at the correct or the wrong place would also be told ");
        System.out.println("");
        System.out.println("For example ----- ");
        System.out.println("If two colurs are correct but present at wrong places then --- '2 W' will be printed ");
        System.out.println("If three colours are correct and present at correct places then --- '3 C' will be printed ");
        System.out.println("So!!! Everything clear the why waiting..... Double Up!!!!!!");
        System.out.println("");
        System.out.println("The game has two Modes --- 1. Single Player 2. Multi Player(Upto 10 players)..... Which Mode do you want to play in?????? ");
        byte players = 0;
        for(byte h = 1; h <= 10; h++)
        {
            int Mode = in.nextInt();
            if(Mode == 1)
            {    
                players = 1;
                break;
            }
            else if(Mode == 2)
            {
                System.out.println("Enter the number of players (Can enter only upto 10)");
                players = in.nextByte();
                          
                for(byte e = 1; e <= 10; e++)
                {
                    if(players > 10 || players <= 1)
                    {
                        System.out.println("Plz Enter the number of players between 2 to 10 only!!!" );
                        players = in.nextByte();
                    }
                    else
                        break;
                }
                break;
            }
            else
                System.out.println("Please enter either 'Single Player' or 'Multi Player'");

        }
        int Score[] = new int[5];
        String plr_nm[] = new String[5];
        for(byte plr = 1; plr <= players; plr++)
        {
            double rndm_num1 = 0, rndm_num2 = 0;
            int i, digit[] =  new int[100], digit1[] =  new int[100], cnt = 1, cnt1 = 1;
            String[] clr =  new String[10];
            
            clr[0] = "red"; clr[1] = "white";clr[2] = "blue"; clr[3] = "yellow"; clr[4] = "black"; clr[5] = "green";
            clr[6] = "blue"; clr[7] = "green"; clr[8] = "black"; clr[9] = "yellow";
            
            String[] cd_clr =  new String[10];
            System.out.println("");          
            System.out.println("Enter the name of player " +plr);
            plr_nm[plr] = in.next();
            
            System.out.println("");
            System.out.println("Welcome " +plr_nm[plr] + ">> Enter any key to start the game.. ");
            String strt;
            strt = in.next();
            
            System.out.println("");
            System.out.println("Initializing the S.I.C ..... ");
            for(byte c = 1; c <= 10; c++)
            {
                wait1();wait1();wait1();wait1();wait1();
                System.out.print(" .>");
            }
            
            rndm_num1 = Math.random();
            double fnl_num = (rndm_num1 * 1000000000);
            long fnl_num1 = (long)(fnl_num * 1000000);
            while (fnl_num1 != 0) 
            {
                digit[cnt] = (int)(fnl_num1 % 10);
                fnl_num1 = fnl_num1 / 10;
                cnt = cnt + 1;
            }
            rndm_num2 = Math.random();
            double fnl_num2 = (rndm_num2 * 1000000000);
            long fnl_num3 = (long)(fnl_num2 * 1000000);
            while (fnl_num3 != 0) 
            {
                digit1[cnt1] = (int)(fnl_num3 % 10);
                fnl_num3 = fnl_num3 / 10;
                cnt1 = cnt1 + 1;
            }
            System.out.println("");
            cd_clr[1] = clr[digit1[digit[3]]];
            cd_clr[2] = clr[digit1[digit[6]]];
            cd_clr[3] = clr[digit1[digit[9]]];
            cd_clr[4] = clr[digit1[digit[13]]];
            
            System.out.println("Initializing Done!!! Guess it!!!");
            
            for(byte chance = 1; chance <= 10; chance++)
            {
                System.out.println("");
                System.out.println("Chance no. -- "+ chance);
                String ans_clr[] = new String[6];
                ans_clr[1] = in.next();
                ans_clr[2] = in.next();
                ans_clr[3] = in.next();
                ans_clr[4] = in.next();
                
                ans_clr[1] = ans_clr[1].toLowerCase();
                ans_clr[2] = ans_clr[2].toLowerCase();
                ans_clr[3] = ans_clr[3].toLowerCase();
                ans_clr[4] = ans_clr[4].toLowerCase();
                if((cd_clr[1].equals(ans_clr[1])) && (cd_clr[2].equals(ans_clr[2])) && (cd_clr[3].equals(ans_clr[3])) && (cd_clr[4].equals(ans_clr[4])))
                {
                    if(chance == 1)
                        System.out.println("Awesome!!! What a Miracle you have done angel, guessed it right only in one chance????? Keep it up!!!");
                    else if(chance == 3 || chance == 7 || chance == 9)
                        System.out.println("You did it>>> finally got it right, hhhh !!!!!! ");
                    else if(chance == 4 || chance == 5 || chance == 8)
                        System.out.println("Hooray!!! You found the you know what -- Correct answer!!! ");
                    else if(chance == 2 || chance == 6)
                        System.out.println("Ooops!!! .... Thats ................ NOT WRONG -- You get it right man!! Hooohoo!!!! ");
                    else if(chance == 10)
                        System.out.println("Whoosh...!!! VERY VERY LUCKY.. You have found the treasure on the verge of dying...... -- Correct answer!!!!! ");    
                    Score[plr] = (10 - chance + 1) * 10;
                    System.out.println("");
                    System.out.println("Now it's the time to know the Score.... --- ");
                    System.out.println("Name of the player -- " + plr_nm[plr] + " ##   No. of chances -- "+ chance + " ##   Your Score --- " + Score[plr]);
                    break;    
                }
                else
                {
                    if(chance == 1)
                        System.out.println("Ooops!! The answer is wrong plz try again you'll get it");
                    else if(chance == 3 || chance == 7 || chance == 9)
                        System.out.println("I'm Afraid that's incorrect!!!! try again ---- ");
                    else if(chance == 4 || chance == 5 || chance == 8)
                        System.out.println("You did not get it right gamer you'll have to stress your brain again.... ");
                    else if(chance == 2 || chance == 6)
                        System.out.println("Oh! Nooo!!! Not correct.. Go on ");
                    else if(chance == 10)
                        {
                            System.out.println("Sorry!!!!! You have given your best to break the LOCK... But the chances r over.. ");
                            System.out.println("The correct colours are -- ");
                            System.out.println("" + cd_clr[1]);
                            System.out.println("" + cd_clr[2]);
                            System.out.println("" + cd_clr[3]);
                            System.out.println("" + cd_clr[4]);
                            break;
                        }
                    System.out.println("");
                    System.out.println("S.I.C Authentication in progress......!!! ");    
                    byte Wclr_pos[] = new byte[10], Cclr_pos[] = new byte[10], z = 1, y = 1, no_c_pos = 0;
                    
                    for(byte n = 1; n <= 4; n++)
                    {
                        Wclr_pos[z] = n; 
                        if(cd_clr[n].equals(ans_clr[n]))
                        {    
                            Cclr_pos[y] = n;
                            y++;
                            Wclr_pos[z] = 0;
                            z--;
                            no_c_pos++;
                        } 
                        z++;
                    }
                    
                    System.out.println();
                    byte dupWclr_pos[] = new byte[10];
                    byte x = 1, w = 1, p, q, Clr_mch[] = new byte[10], c = 1;
                    for(p = 1; p <= 4; p++)
                    {
                        if((Cclr_pos[1] == p) || (Cclr_pos[2] == p) || (Cclr_pos[3] == p) || (Cclr_pos[4] == p))
                                continue;    
                        for(q = 1; q <= 4; q++)
                        {
                            if((Cclr_pos[1] == q) || (Cclr_pos[2] == q) || (Cclr_pos[3] == q) || (Cclr_pos[4] == q))
                                continue;
                            if((Clr_mch[1] == q) || (Clr_mch[2] == q) || (Clr_mch[3] == q) || (Clr_mch[4] == q))
                                continue;    
                            if((cd_clr[q]).equals(ans_clr[Wclr_pos[w]]))
                            {    
                                dupWclr_pos[x] = Wclr_pos[w];
                                Clr_mch[c] = q;
                                ++x;
                                ++c;
                                break;
                            }   
                        }
                        ++w;
                    }
                    System.out.println();
                    for(byte d = 1; d <= 4; d++)
                    {
                        wait1();
                        System.out.print(" .>");
                    }
                    System.out.println();
                    System.out.println("S.I.C authentication complete### ");
                    System.out.println("The clues are -- ");
                    byte no_w_pos = 0;
                    for(byte ac = 1; ac <= 4; ac++)
                    {
                        if(dupWclr_pos[ac] != 0)
                            ++no_w_pos;
                    }
                    if(no_c_pos != 0)
                        System.out.println(no_c_pos + "C");
                    if(no_w_pos != 0)
                        System.out.println(no_w_pos + "W");    
                }
            }
        }
        int max_scr = Score[1], min_pos = 0, swap[] = new int[11];
        String swap_nm[] = new String[11];
        
        if(players > 1)
        {
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Now the awaited moment comes... To know the results of the Multiplayer competetion ");
            System.out.println("The Ranking of the players is ----- ");
            for(byte g = 1; g <= players; g++)
            {
                max_scr = Score[g];
                min_pos = g;
                for(byte f = g; f <= players; f++)
                {
                    if(Score[f] > max_scr)
                    {
                        max_scr = Score[f];
                        min_pos = f;
                    }
                }
                swap_nm[g] = plr_nm[g];
                plr_nm[g] = plr_nm[min_pos];
                plr_nm[min_pos] = swap_nm[g];
                
                swap[g] = Score[g];
                Score[g] = Score[min_pos];
                Score[min_pos] = swap[g];
                
                System.out.println(" | " + g + ". -- " + plr_nm[g] + " *** " + Score[g] + " points ");
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    
    public  void wait3()
    {
        byte timepass;
        for(long e = 1; e <= 100000000; e++)
        {
            timepass = 1;
        }
    }

    int DupSudokuBlock[][] = new int[10][10];
    int sudoku_block[][] = new int[10][10];
    
    int null_prob_row[][] = new int[10][10];
    int null_prob_col[][] = new int[10][10];
    int no_cont_col[][][] = new int[10][10][10];
    int no_cont_row[][][] = new int[10][10][10];
    int Possibility[][][] = new int[10][10][10];
    int PosCnt[][] = new int[10][10];
    int SolAnsChk = 1, SolCh[] = {1,1,1,1,1,1,1,1,1,1};

    public  void Sudoku_Solver_main()
    {
        Game_Console ob = new Game_Console();
        
        ob.block_input_N_null_prob_row_col();
        ob.SudokuCopy();
        ob.PossibilityCal();
        
        System.out.print('\u000C');
        ob.sudoku_print(ob.sudoku_block);
        
        System.out.println();
        System.out.println("Press S to start!!");
        
        long pre_t = System.currentTimeMillis();
        
        ob.solve(); 
        System.out.print('\u000C');
        System.out.println("The Question Sudoku -->>");
        ob.sudoku_print(ob.DupSudokuBlock);
        System.out.println();
        System.out.println("The Solution of the Sudoku -->>");
        ob.sudoku_print(ob.sudoku_block);

        if(ob.Verification() == 0)
            System.out.println("Verification Pass -- ");
        else
            System.out.println("Verification Fail ");
        
        long curr_t = System.currentTimeMillis();
        System.out.println("The Time taken is -- " + (curr_t - pre_t) + " Milli Seconds ");
    }

    public void SudokuCopy()
    {
        for(int row = 1; row <= 9 ; row++)
        {
            for(int col = 1; col <= 9 ; col++)
            {
                int no = sudoku_block[row][col];
                DupSudokuBlock[row][col] = no;
            }
        }
    }
    
    public int Verification()
    {
        int Result = 0;
        int Row, Col;
        int Val[] = new int[10];

        for (Row = 1; Row <= 9; Row++)
        {
            for (Col = 1; Col <= 9; Col++)
            {
                Val[Col] = 0;
            }
        
            for (Col = 1; Col <= 9; Col++)
            {
                Val[sudoku_block[Row][Col]]++;
            }
        
            for (Col = 1; Col <= 9; Col++)
            {
                if(Val[Col] != 1)
                {
                    System.out.println("Verification Fail Val[Col]" + Val[Col] + " Col = " + Col  + " row = " + Row);
                    
                    Result = 1;
                    break;
                }
            }

            if(Result != 0) break;
        }

        for (Col = 1; Col <= 9; Col++)
        {
            for (Row = 1; Row <= 9; Row++)
            {
                Val[Row] = 0;
            }

            for (Row = 1; Row <= 9; Row++)
            {
                Val[sudoku_block[Row][Col]]++;
            }
        
            for (Col = 1; Col <= 9; Col++)
            {
                if(Val[Col] != 1)
                {
                    Result = 2;
                    break;
                }
            }

            if(Result != 0) break;
        }

        for (int Sqr = 0; Sqr < 9; Sqr++)
        {
            for (Row = 1; Row <= 9; Row++)
            {
                Val[Row] = 0;
            }
            
            for (Row = 1; Row < 4; Row++)
            {
                for (Col = 1; Col < 4; Col++)
                {
                    Val[sudoku_block[((Sqr % 3) * 3) + Row][(Sqr / 3) * 3 + Col]]++;
                }
            }
 
            for (Col = 1; Col <= 9; Col++)
            {
                if(Val[Col] != 1)
                {
                    Result = 3;
                    break;
                }
            }

            if(Result != 0) break;
        }
        return Result;
    }
    
    public void block_input_N_null_prob_row_col()
    {
        String col = ""; int IntCol;
        int cnt;
        System.out.println();
        for(int i = 1; i <= 9; i++)
        {
            cnt = 0;
            col = "";
            while((col.equalsIgnoreCase("X") == false))
            {
                IntCol = 0; col = "";
                ++cnt;
                
                System.out.print('\u000C');
                System.out.println(" Enter the numbers for sudoku!!");////
                System.out.println("For entering a number --- First mention the column number of the specified row where the number has to be entered -- Press Enter");
                System.out.println("Then enter the number --- Press Enter");
                System.out.println("After you have entered all the numbers --- Press 'x' to go to the next row");
                System.out.println("Begin!!!");
                
                sudoku_print(sudoku_block);
                
                System.out.println("ROW Number -- " + i);
                col = in.next();
                
                if((col.equalsIgnoreCase("X") == false) && (((Integer.parseInt(col)) >= 0) && ((Integer.parseInt(col)) <= 9)))
                    IntCol = Integer.parseInt(col);
                
                int PrNo = 0;    
                    
                if(col.equalsIgnoreCase("X") == false)
                {
                    PrNo = sudoku_block[i][IntCol];
                    
                    sudoku_block[i][IntCol] = in.nextInt();
                    
                    null_prob_row[PrNo][i] = 0;
                    null_prob_col[PrNo][IntCol] = 0;
                    
                    if(sudoku_block[i][IntCol] != 0)
                    {
                        null_prob_row[sudoku_block[i][IntCol]][i] = i;
                        null_prob_col[sudoku_block[i][IntCol]][IntCol] = IntCol;
                    }
                }
            }
        }
        
        col = "";
        int row;
        int num;
        while((col.equalsIgnoreCase("X") == false))
        {
            System.out.print('\u000C');
            System.out.println("Any Corrections??? (Press X if there aren't any!!!!)");
            System.out.println("For Any Correction enter -- ");
            System.out.println("Row Number -- Press Enter");
            System.out.println("Column Number -- Press Enter");
            System.out.println("The Number itself-- Press Enter");
            
            sudoku_print(sudoku_block);
            
            col = in.next();
            IntCol = 0;
            if((col.equalsIgnoreCase("X") == false) && (((Integer.parseInt(col)) >= 0) && ((Integer.parseInt(col)) <= 9)))
                IntCol = Integer.parseInt(col);
            
            int PrevNum = 0;
                
            if(col.equalsIgnoreCase("X") == false)
            {
                row = in.nextInt();
                
                PrevNum = sudoku_block[IntCol][row];
                
                sudoku_block[IntCol][row] = in.nextInt();
                
                null_prob_row[PrevNum][IntCol] = 0;
                null_prob_col[PrevNum][row] = 0;
                
                if(sudoku_block[IntCol][row] != 0)
                {
                    null_prob_row[sudoku_block[IntCol][row]][IntCol] = IntCol;
                    null_prob_col[sudoku_block[IntCol][row]][row] = row;
                }
            }
        }
    }
    
    public void solve()
    {
        int check_row[] = new int[4];
        int check_col[] = new int[4];
        int eq_0_no = 0;
        int ans_row = 0, ans_col = 0;
        
        while(sudoku_complete_chk())
        {
            for(int i = 1; i <= 9; i++)
            {
                for(int j = 1; j <= 3; j++)
                {
                    check_row[3] = (j * 3);
                    check_row[1] = ((j * 3) - 2);
                    for(int k = 1; k <= 3; k++)
                    {
                        check_col[3] = (k * 3);
                        check_col[1] = ((k * 3) - 2);
                        
                        int ssssssssss;
                        if(SolAnsChk != 0)
                        {   
                            SolCh[i] = 0;
                            AdvanceRowSolve(i);
                            AdvanceColSolve(i);
                            row_solve(i);
                            column_solve(i);
                            RowColSolve();
                            
                            eq_0_no = 0; ans_row = 0; ans_col = 0;
                            if(box_repeat_no_chk(check_row[1], check_row[3], check_col[1], check_col[3], i))
                                continue;
                                
                            for(int l = check_row[1]; l <= check_row[3]; l++)
                            {
                                if((l == null_prob_row[i][l]) && (check_col[1] != no_cont_col[i][l][check_col[1]]))
                                    continue;
                                        
                                for(int m = check_col[1]; m <= check_col[3]; m++)
                                {
                                    if((m == null_prob_col[i][m]) && (check_row[1] != no_cont_row[i][m][check_row[1]]))
                                        continue;
                                            
                                    if(sudoku_block[l][m] == 0)
                                    {
                                        ++eq_0_no;
                                        if(eq_0_no == 1)
                                        {
                                            ans_row = l;
                                            ans_col = m;
                                        } 
                                    }
                                }
                            }
                            
                            if(eq_0_no == 1)
                            {
                                sudoku_block[ans_row][ans_col] = i;
                                
                                System.out.print('\u0CCC');
                                sudoku_print(sudoku_block);
                                
                                null_prob_row[i][ans_row] = ans_row;
                                null_prob_col[i][ans_col] = ans_col;
                                
                                NoContPosElmnt(i, ans_row, ans_col);
                                PosCalRow(i, ans_row);
                                PosCalCol(i, ans_col);
                                PosCalBox(i, check_row[1], check_row[3], check_col[1], check_col[3]);
                            }
                        }
                        else
                        {
                            PossibilityBoxSolve(check_row[1], check_row[3], check_col[1], check_col[3], i);
                            PossibilityRowSolve(i);
                            PossibilityColSolve(i);
                            
                            PossibilityAdvColSolve(i);
                            PossibilityAdvRowSolve(i);
                            PossibilityAdvBoxSolve();
                            
                            PossibilitySolve();
                            
                            Possibility3RowColBox(i, check_row[1], check_row[3], check_col[1], check_col[3]);
                            Possibility2RowColBox(i, check_row[1], check_row[3], check_col[1], check_col[3]);
                            
                            PossibilityRowWing(i);
                            PossibilityColWing(i);
                            
                            PossibilityAdvColWing(i);
                            PossibilityAdvRowWing(i);
                            
                            // XY_Wing(i);
                            
                            if(PosCnt[4][5] == 2)
                                ssssssssss = 9;
                        }
                    }
                }
                if((i == 9) && (SolCh[1] == 0) && (SolCh[2] == 0) && (SolCh[3] == 0) && (SolCh[4] == 0) && (SolCh[5] == 0) && (SolCh[6] == 0) && (SolCh[7] == 0) && (SolCh[8] == 0) && (SolCh[9] == 0))
                    SolAnsChk =  0;
            }
        }
    }
    
    public void PosCalRow(int ChkNo, int AnsRow)
    {
        for(int z = 1; z <= 9; z++)
        {
            for(int y = 1; y <= 9; y++)
            {
                if(Possibility[AnsRow][z][y] == ChkNo)
                {
                    for(int x = y; x <= PosCnt[AnsRow][z]; x++)
                        Possibility[AnsRow][z][x] = Possibility[AnsRow][z][x + 1];
                    --PosCnt[AnsRow][z];
                }
            }
        }
    }
    
    public void PosCalCol(int ChkNo, int AnsCol)
    {
        for(int z = 1; z <= 9; z++)
        {
            for(int y = 1; y <= 9; y++)
            {
                if(Possibility[z][AnsCol][y] == ChkNo)
                {
                    for(int x = y; x <= PosCnt[z][AnsCol]; x++)
                        Possibility[z][AnsCol][x] = Possibility[z][AnsCol][x + 1];
                    --PosCnt[z][AnsCol];
                }
            }
        }
    }
    
    public void PosCalBox(int ChkNo, int Row1, int Row2, int Col1, int Col2)
    {
        for(int row = Row1; row <= Row2; row++)
        {
            for(int col = Col1; col <= Col2; col++)
            {
                for(int y = 1; y <= 9; y++)
                {
                    if(Possibility[row][col][y] == ChkNo)
                    {
                        for(int x = y; x <= PosCnt[row][col]; x++)
                            Possibility[row][col][x] = Possibility[row][col][x + 1];
                        --PosCnt[row][col];    
                    }
                }
            }
        }
    }
    
    public void NoContPosElmnt(int ChkNo, int AnsRow, int AnsCol)
    {
        for(int r = 0; r <= 9; r++)
        {   
            no_cont_col[ChkNo][AnsRow][r] = 0;
            no_cont_row[ChkNo][AnsCol][r] = 0;
            Possibility[AnsRow][AnsCol][r] = 0;
            PosCnt[AnsRow][AnsCol] = 0;
        }
    }
    
    public void PossibilityAdvColSolve(int ChkCol)
    {
        int Fnd1, Fnd2;
        boolean ans = true;
        int PosChkB;
        for(int row = 1; row <= 9; row++)
        {
            for(int pos1 = 1; pos1 <= PosCnt[row][ChkCol]; pos1++)
            {
                for(int pos2 = pos1 + 1; pos2 <= PosCnt[row][ChkCol]; pos2++)
                {
                    for(int ch_row = row + 1; ch_row <= 9; ch_row++)
                    {
                        for(int ch_pos1 = 1; ch_pos1 <= PosCnt[ch_row][ChkCol]; ch_pos1++)
                        {
                            for(int ch_pos2 = ch_pos1 + 1; ch_pos2 <= PosCnt[ch_row][ChkCol]; ch_pos2++)
                            {
                                ans = true;
                                if((Possibility[row][ChkCol][pos1]) == (Possibility[ch_row][ChkCol][ch_pos1]))
                                {
                                    if((Possibility[row][ChkCol][pos2]) == (Possibility[ch_row][ChkCol][ch_pos2]))
                                    {
                                        Fnd1 = Possibility[row][ChkCol][pos1];
                                        Fnd2 = Possibility[row][ChkCol][pos2];
                                        if(ChkCol == 9)
                                            PosChkB = 6;
                                        for(int z = 1; z <= 9; z++)
                                        {
                                            if(z == row || z == ch_row)
                                                continue;   
                                            for(int y = 1; y <= 9; y++)
                                            {
                                                if((Possibility[z][ChkCol][y] == Fnd1) || (Possibility[z][ChkCol][y] == Fnd2))
                                                {
                                                    ans = false;
                                                    break;
                                                }    
                                            }
                                            if(!(ans))
                                                break;
                                        }
                                        
                                        if(ans)
                                        {
                                            for(int i = 1; i <= 9; i++)
                                            {
                                                Possibility[row][ChkCol][i] = 0;
                                                Possibility[ch_row][ChkCol][i] = 0;
                                            }
                                            
                                            Possibility[row][ChkCol][1] = Fnd1;
                                            Possibility[row][ChkCol][2] = Fnd2;
                                            
                                            Possibility[ch_row][ChkCol][1] = Fnd1;
                                            Possibility[ch_row][ChkCol][2] = Fnd2;
                                            
                                            PosCnt[row][ChkCol] = 2;
                                            PosCnt[ch_row][ChkCol] = 2;
                                            PosChkB = 9;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityAdvBoxSolve()
    {
        int Fnd1, Fnd2;
        boolean ans = true;
        int PosChkB;
        int ChkCol1, ChkCol2;
        int ChkRow1, ChkRow2;
        for(int Row = 1; Row <= 3; Row++)
        {
            ChkRow2 = Row * 3;
            ChkRow1 = (Row * 3) - 2;
            for(int Col = 1; Col <= 3; Col++)
            {
                ChkCol2 = Col * 3;
                ChkCol1 = (Col * 3) - 2;
                for(int ChRow = ChkRow1; ChRow <= ChkRow2; ChRow++)
                {
                    for(int ChCol = ChkCol1; ChCol <= ChkCol2; ChCol++)
                    {
                        for(int pos1 = 1; pos1 <= PosCnt[ChRow][ChCol]; pos1++)
                        {
                            for(int pos2 = pos1 + 1; pos2 <= PosCnt[ChRow][ChCol]; pos2++)
                            {
                                for(int Chk_RowNum = ChRow; Chk_RowNum <= ChkRow2; Chk_RowNum++)
                                {    
                                    for(int Chk_ColNum = ChCol + 1; Chk_ColNum <= ChkCol2; Chk_ColNum++)
                                    {
                                        for(int ch_pos1 = 1; ch_pos1 <= PosCnt[Chk_RowNum][Chk_ColNum]; ch_pos1++)
                                        {
                                            for(int ch_pos2 = ch_pos1 + 1; ch_pos2 <= PosCnt[Chk_RowNum][Chk_ColNum]; ch_pos2++)
                                            {
                                                ans = true;
                                                if((Possibility[ChRow][ChCol][pos1]) == (Possibility[Chk_RowNum][Chk_ColNum][ch_pos1]))
                                                {
                                                    if((Possibility[ChRow][ChCol][pos2]) == (Possibility[Chk_RowNum][Chk_ColNum][ch_pos2]))
                                                    {
                                                        Fnd1 = Possibility[ChRow][ChCol][pos1];
                                                        Fnd2 = Possibility[ChRow][ChCol][pos2];
                                                        if(Row == 2 && Col ==2)
                                                            PosChkB = 8;
                                                        for(int NumRepRow = ChkRow1; NumRepRow <= ChkRow2; NumRepRow++)
                                                        {
                                                            for(int NumRepCol = ChkCol1; NumRepCol <= ChkCol2; NumRepCol++)
                                                            {
                                                                if((NumRepRow == ChRow) && (NumRepCol == ChCol))
                                                                    continue;
                                                                if((NumRepRow == Chk_RowNum) && (NumRepCol == Chk_ColNum))
                                                                    continue;
                                                                    
                                                                for(int y = 1; y <= 9; y++)
                                                                {
                                                                    if((Possibility[NumRepRow][NumRepCol][y] == Fnd1) || (Possibility[NumRepRow][NumRepCol][y] == Fnd2))
                                                                    {
                                                                        ans = false;
                                                                        break;
                                                                    }    
                                                                }
                                                                if(!(ans))
                                                                    break;
                                                            }
                                                        }
                                                        
                                                        if(ans)
                                                        {
                                                            for(int i = 1; i <= 9; i++)
                                                            {
                                                                Possibility[ChRow][ChCol][i] = 0;
                                                                Possibility[Chk_RowNum][Chk_ColNum][i] = 0;
                                                            }
                                                            
                                                            Possibility[ChRow][ChCol][1] = Fnd1;
                                                            Possibility[ChRow][ChCol][2] = Fnd2;
                                                            
                                                            Possibility[Chk_RowNum][Chk_ColNum][1] = Fnd1;
                                                            Possibility[Chk_RowNum][Chk_ColNum][2] = Fnd2;
                                                            
                                                            PosCnt[ChRow][ChCol] = 2;
                                                            PosCnt[Chk_RowNum][Chk_ColNum] = 2;
                                                            PosChkB = 9;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void Possibility3RowColBox(int Chk, int Row1, int Row2, int Col1, int Col2)
    {
        int ColFnd = 0, IncrNoC = 0;
        int ColFndNo[] = new int[10];
        for(int col = 1; col <= 9; col++)
            if((PosCnt[Chk][col] == 3) || (PosCnt[Chk][col] == 2))
            {
                ++IncrNoC;
                ++ColFnd;
                ColFndNo[IncrNoC] = col;
            }
        
        int PosChkC1, PosChkC2, PosChkC3;
        boolean AnsC1, AnsC2, AnsC3;
        for(int chC = 1; chC <= ColFnd; chC++)
        {
            for(int chk_chC = chC + 1; chk_chC <= ColFnd; chk_chC++)
            {
                for(int chk_ChC2 = chk_chC + 1; chk_ChC2 <= ColFnd; chk_ChC2++)
                {
                    PosChkC1 = 0; PosChkC2 = 0; PosChkC3 = 0; 
                    AnsC1 = false; AnsC2 = false; AnsC3 = false;
                
                    if(PosCnt[Chk][ColFndNo[chC]] == 3)
                    { PosChkC1 = Possibility[Chk][ColFndNo[chC]][1]; PosChkC2 = Possibility[Chk][ColFndNo[chC]][2]; PosChkC3 = Possibility[Chk][ColFndNo[chC]][3];   }
                    
                    else if(PosCnt[Chk][ColFndNo[chk_chC]] == 3)
                    { PosChkC1 = Possibility[Chk][ColFndNo[chk_chC]][1]; PosChkC2 = Possibility[Chk][ColFndNo[chk_chC]][2]; PosChkC3 = Possibility[Chk][ColFndNo[chk_chC]][3];   }
                    
                    else if(PosCnt[Chk][ColFndNo[chk_ChC2]] == 3)
                    { PosChkC1 = Possibility[Chk][ColFndNo[chk_ChC2]][1]; PosChkC2 = Possibility[Chk][ColFndNo[chk_ChC2]][2]; PosChkC3 = Possibility[Chk][ColFndNo[chk_ChC2]][3];   }
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[Chk][ColFndNo[chC]]; ChkCond++)
                        if((Possibility[Chk][ColFndNo[chC]][ChkCond] == PosChkC1) || (Possibility[Chk][ColFndNo[chC]][ChkCond] == PosChkC2) || (Possibility[Chk][ColFndNo[chC]][ChkCond] == PosChkC3))
                            AnsC1 = true;
                        else 
                        {
                            AnsC1 = false; 
                            break;
                        } 
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[Chk][ColFndNo[chk_chC]]; ChkCond++)
                        if((Possibility[Chk][ColFndNo[chk_chC]][ChkCond] == PosChkC1) || (Possibility[Chk][ColFndNo[chk_chC]][ChkCond] == PosChkC2) || (Possibility[Chk][ColFndNo[chk_chC]][ChkCond] == PosChkC3))
                            AnsC2 = true;
                        else 
                        {
                            AnsC2 = false; 
                            break;
                        } 
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[Chk][ColFndNo[chk_ChC2]]; ChkCond++)
                        if((Possibility[Chk][ColFndNo[chk_ChC2]][ChkCond] == PosChkC1) || (Possibility[Chk][ColFndNo[chk_ChC2]][ChkCond] == PosChkC2) || (Possibility[Chk][ColFndNo[chk_ChC2]][ChkCond] == PosChkC3))
                            AnsC3 = true;
                        else 
                        {
                            AnsC3 = false; 
                            break;
                        } 
                    
                    if((AnsC1 == true) && (AnsC2 == true) && (AnsC3 == true))
                    {
                        for(int z = 1; z <= 9; z++)
                        {
                            if((z == ColFndNo[chC]) || (z == ColFndNo[chk_chC]) || (z == ColFndNo[chk_ChC2]))
                                continue;
                            for(int y = 1; y <= 9; y++)
                            {
                                if((Possibility[Chk][z][y] == PosChkC1) || (Possibility[Chk][z][y] == PosChkC2) || (Possibility[Chk][z][y] == PosChkC3))
                                {
                                    for(int x = y; x <= PosCnt[Chk][z]; x++)
                                        Possibility[Chk][z][x] = Possibility[Chk][z][x + 1];
                                    --PosCnt[Chk][z];
                                    --y;
                                }   
                            }
                        }
                        int xxx = 8;
                    
                    }
                }
            }
        }
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        int RowFnd = 0, IncrNoR = 0;
        int RowFndNo[] = new int[10];
        if (Chk == 5 && PosCnt[2][5] == 3)
            RowFnd = 0;
        
        for(int row = 1; row <= 9; row++)
            if((PosCnt[row][Chk] == 3) || (PosCnt[row][Chk] == 2))
            {
                ++IncrNoR;
                ++RowFnd;
                RowFndNo[IncrNoR] = row;
            }
        
        int PosChkR1, PosChkR2, PosChkR3;
        boolean AnsR1, AnsR2, AnsR3;
        for(int chR = 1; chR <= RowFnd; chR++)
        {
            for(int chk_chR = chR + 1; chk_chR <= RowFnd; chk_chR++)
            {
                for(int chk_ChR2 = chk_chR + 1; chk_ChR2 <= RowFnd; chk_ChR2++)
                {
                    PosChkR1 = 0; PosChkR2 = 0; PosChkR3 = 0; 
                    AnsR1 = false; AnsR2 = false; AnsR3 = false;
                
                    if(PosCnt[RowFndNo[chR]][Chk] == 3)
                    { PosChkR1 = Possibility[RowFndNo[chR]][Chk][1]; PosChkR2 = Possibility[RowFndNo[chR]][Chk][2]; PosChkR3 = Possibility[RowFndNo[chR]][Chk][3];   }
                    
                    else if(PosCnt[RowFndNo[chk_chR]][Chk] == 3)
                    { PosChkR1 = Possibility[RowFndNo[chk_chR]][Chk][1]; PosChkR2 = Possibility[RowFndNo[chk_chR]][Chk][2]; PosChkR3 = Possibility[RowFndNo[chk_chR]][Chk][3];   }
                    
                    else if(PosCnt[RowFndNo[chk_ChR2]][Chk] == 3)
                    { PosChkR1 = Possibility[RowFndNo[chk_ChR2]][Chk][1]; PosChkR2 = Possibility[RowFndNo[chk_ChR2]][Chk][2]; PosChkR3 = Possibility[RowFndNo[chk_ChR2]][Chk][3];   }
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[RowFndNo[chR]][Chk]; ChkCond++)
                        if((Possibility[RowFndNo[chR]][Chk][ChkCond] == PosChkR1) || (Possibility[RowFndNo[chR]][Chk][ChkCond] == PosChkR2) || (Possibility[RowFndNo[chR]][Chk][ChkCond] == PosChkR3))
                            AnsR1 = true;
                        else 
                        {
                            AnsR1 = false; 
                            break;
                        } 
                            
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[RowFndNo[chk_chR]][Chk]; ChkCond++)
                        if((Possibility[RowFndNo[chk_chR]][Chk][ChkCond] == PosChkR1) || (Possibility[RowFndNo[chk_chR]][Chk][ChkCond] == PosChkR2) || (Possibility[RowFndNo[chk_chR]][Chk][ChkCond] == PosChkR3))
                            AnsR2 = true;
                        else 
                        {
                            AnsR2 = false; 
                            break;
                        } 
                    
                    for(int ChkCond = 1; ChkCond <= PosCnt[RowFndNo[chk_ChR2]][Chk]; ChkCond++)
                        if((Possibility[RowFndNo[chk_ChR2]][Chk][ChkCond] == PosChkR1) || (Possibility[RowFndNo[chk_ChR2]][Chk][ChkCond] == PosChkR2) || (Possibility[RowFndNo[chk_ChR2]][Chk][ChkCond] == PosChkR3))
                            AnsR3 = true;
                        else 
                        {
                            AnsR3 = false; 
                            break;
                        } 
                    
                    if((AnsR1 == true) && (AnsR2 == true) && (AnsR3 == true))
                    {
                        for(int z = 1; z <= 9; z++)
                        {
                            if((z == RowFndNo[chR]) || (z == RowFndNo[chk_chR]) || (z == RowFndNo[chk_ChR2]))
                                continue;
                            for(int y = 1; y <= 9; y++)
                            {
                                if((Possibility[z][Chk][y] == PosChkR1) || (Possibility[z][Chk][y] == PosChkR2) || (Possibility[z][Chk][y] == PosChkR3))
                                {
                                    for(int x = y; x <= PosCnt[z][Chk]; x++)
                                        Possibility[z][Chk][x] = Possibility[z][Chk][x + 1];
                                    --PosCnt[z][Chk];
                                    --y;
                                }   
                            }
                        }
                        int xxx = 8;
                    
                    }
                }
            }
        }
    }
    
    public void dd(int Chk)
    {
        
    }
    
    public void Possibility2RowColBox(int Chk, int Row1, int Row2, int Col1, int Col2)
    {
        int ColFnd = 0, IncrNoC = 0;
        int ColFndNo[] = new int[10];
        int FndCol1, FndCol2;
        for(int col = 1; col <= 9; col++)
            if(PosCnt[Chk][col] == 2)
            {
                ++IncrNoC;
                ++ColFnd;
                ColFndNo[IncrNoC] = col;
            }
            
        for(int chC = 1; chC <= ColFnd; chC++)
        {
            for(int chk_chC = chC + 1; chk_chC <= ColFnd; chk_chC++)
            {
                FndCol1 = 0; FndCol2 = 0;
                if(Possibility[Chk][ColFndNo[chC]][1] == Possibility[Chk][ColFndNo[chk_chC]][1])
                {
                    if(Possibility[Chk][ColFndNo[chC]][2] == Possibility[Chk][ColFndNo[chk_chC]][2])
                    {
                        FndCol1 = Possibility[Chk][ColFndNo[chC]][1];
                        FndCol2 = Possibility[Chk][ColFndNo[chC]][2];
                        
                        for(int z = 1; z <= 9; z++)
                        {
                            if((z == ColFndNo[chC]) || (z == ColFndNo[chk_chC]))
                                continue;
                            for(int y = 1; y <= 9; y++)
                            {
                                if((Possibility[Chk][z][y] == FndCol1) || ((Possibility[Chk][z][y] == FndCol2)))
                                {
                                    for(int x = y; x <= PosCnt[Chk][z]; x++)
                                        Possibility[Chk][z][x] = Possibility[Chk][z][x + 1];
                                    --PosCnt[Chk][z];
                                    --y;
                                }   
                            }
                        }
                        int xxx = 8;
                    }
                }
            }
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        int RowFnd = 0, IncrNoR = 0;
        int RowFndNo[] = new int[10];
        int FndRow1, FndRow2;
        
        for(int row = 1; row <= 9; row++)
            if(PosCnt[row][Chk] == 2)
            {
                ++IncrNoR;
                ++RowFnd;
                RowFndNo[IncrNoR] = row;
            }
            
        for(int chR = 1; chR <= RowFnd; chR++)
        {
            for(int chk_chR = chR + 1; chk_chR <= RowFnd; chk_chR++)
            {
                FndRow1 = 0; FndRow2 = 0;
                if(Possibility[RowFndNo[chR]][Chk][1] == Possibility[RowFndNo[chk_chR]][Chk][1])
                {
                    if(Possibility[RowFndNo[chR]][Chk][2] == Possibility[RowFndNo[chk_chR]][Chk][2])
                    {
                        FndRow1 = Possibility[RowFndNo[chR]][Chk][1];
                        FndRow2 = Possibility[RowFndNo[chR]][Chk][2];
                        
                        for(int z = 1; z <= 9; z++)
                        {
                            if((z == RowFndNo[chR]) || (z == RowFndNo[chk_chR]))
                                continue;
                            for(int y = 1; y <= 9; y++)
                            {
                                if((Possibility[z][Chk][y] == FndRow1) || ((Possibility[z][Chk][y] == FndRow2)))
                                {
                                    for(int x = y; x <= PosCnt[z][Chk]; x++)
                                        Possibility[z][Chk][x] = Possibility[z][Chk][x + 1];
                                    --PosCnt[z][Chk];
                                    --y;
                                }   
                            }
                        }
                    }
                }
            }
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        int BoxFnd = 0, IncrNoB = 0;
        int BoxFndNoR[] = new int[10];
        int BoxFndNoC[] = new int[10];
        int FndBox1, FndBox2;
        for(int row = Row1; row <= Row2; row++)
        {
            for(int col = Col1; col <= Col2; col++)
            {
                if(PosCnt[row][col] == 2)
                {
                    ++IncrNoB;
                    ++BoxFnd;
                    BoxFndNoR[IncrNoB] = row;
                    BoxFndNoC[IncrNoB] = col;
                }
            }
        }
        
        for(int chB = 1; chB <= BoxFnd; chB++)
        {
            for(int chk_chB = chB + 1; chk_chB <= BoxFnd; chk_chB++)
            {
                FndBox1 = 0; FndBox2 = 0;
                if(Possibility [BoxFndNoR[chB]] [BoxFndNoC[chB]] [1] == Possibility [BoxFndNoR[chk_chB]] [BoxFndNoC[chk_chB]] [1])
                {
                    if(Possibility [BoxFndNoR[chB]] [BoxFndNoC[chB]] [2] == Possibility[BoxFndNoR[chk_chB]] [BoxFndNoC[chk_chB]] [2])
                    {
                        FndBox1 = Possibility [BoxFndNoR[chB]] [BoxFndNoC[chB]] [1];
                        FndBox2 = Possibility [BoxFndNoR[chB]] [BoxFndNoC[chB]] [2];
                        
                        for(int row = Row1; row <= Row2; row++)
                        {
                            for(int col = Col1; col <= Col2; col++)
                            {
                                
                                if(((row == BoxFndNoR[chB]) && (col == BoxFndNoC[chB])) || ((row == BoxFndNoR[chk_chB]) && (col == BoxFndNoC[chk_chB])))
                                    continue;
                                for(int y = 1; y <= 9; y++)
                                {
                                    if((Possibility[row][col][y] == FndBox1) || ((Possibility[row][col][y] == FndBox2)))
                                    {
                                        for(int x = y; x <= PosCnt[row][col]; x++)
                                            Possibility[row][col][x] = Possibility[row][col][x + 1];
                                        --PosCnt[row][col];
                                        --y;
                                    }   
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
        public void PossibilityAdvRowSolve(int ChkRow)
    {
        int Fnd1, Fnd2;
        boolean ans = true;
        for(int col = 1; col <= 9; col++)
        {
            for(int pos1 = 1; pos1 <= PosCnt[ChkRow][col]; pos1++)
            {
                for(int pos2 = pos1 + 1; pos2 <= PosCnt[ChkRow][col]; pos2++)
                {
                    for(int ch_col = col + 1; ch_col <= 9; ch_col++)
                    {
                        for(int ch_pos1 = 1; ch_pos1 <= PosCnt[ChkRow][ch_col]; ch_pos1++)
                        {
                            for(int ch_pos2 = ch_pos1 + 1; ch_pos2 <= PosCnt[ChkRow][ch_col]; ch_pos2++)
                            {
                                ans = true;
                                if((Possibility[ChkRow][col][pos1]) == (Possibility[ChkRow][ch_col][ch_pos1]))
                                {
                                    if((Possibility[ChkRow][col][pos2]) == (Possibility[ChkRow][ch_col][ch_pos2]))
                                    {
                                        Fnd1 = Possibility[ChkRow][col][pos1];
                                        Fnd2 = Possibility[ChkRow][col][pos2];
                                        
                                        for(int z = 1; z <= 9; z++)
                                        {
                                            if(z == col || z == ch_col)
                                                continue;
                                            for(int y = 1; y <= 9; y++)
                                            {
                                                if((Possibility[ChkRow][z][y] == Fnd1) || (Possibility[ChkRow][z][y] == Fnd2))
                                                {
                                                    ans = false;
                                                    break;
                                                }
                                            }
                                            if(!(ans))
                                                break;
                                        }
                                        
                                        if(ans)
                                        {
                                            for(int i = 1; i <= 9; i++)
                                            {
                                                Possibility[ChkRow][col][i] = 0;
                                                Possibility[ChkRow][ch_col][i] = 0;
                                            }
                                            
                                            Possibility[ChkRow][col][1] = Fnd1;
                                            Possibility[ChkRow][col][2] = Fnd2;
                                            
                                            Possibility[ChkRow][ch_col][1] = Fnd1;
                                            Possibility[ChkRow][ch_col][2] = Fnd2;
                                            
                                            PosCnt[ChkRow][col] = 2;
                                            PosCnt[ChkRow][ch_col] = 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityColSolve(int ChkNo)
    {
        int NoFnd;
        int AnsRow;
        int Row1 = 0, Row2 = 0, Col1 = 0, Col2 = 0;
        for(int col = 1; col <= 9; col++)
        {
            NoFnd = 0;
            AnsRow = 0;
            for(int row = 1; row <= 9; row++)
                {
                for(int ch = 1; ch <= 9; ch++)
                {
                    if(Possibility[row][col][ch] == ChkNo)
                    {
                        ++NoFnd;
                        if(NoFnd == 1)
                        {
                            AnsRow = row;
                            if     ((col >= 1) && (col <= 3)) { Col1 = 1; Col2 = 3; }
                            else if((col >= 4) && (col <= 6)) { Col1 = 4; Col2 = 6; }
                            else if((col >= 7) && (col <= 9)) { Col1 = 7; Col2 = 9; }
                            if     ((row >= 1) && (row <= 3)) { Row1 = 1; Row2 = 3; }
                            else if((row >= 4) && (row <= 6)) { Row1 = 4; Row2 = 6; }
                            else if((row >= 7) && (row <= 9)) { Row1 = 7; Row2 = 9; }
                        }
                    }
                }
            }
              
            if(NoFnd == 1)
            {
                sudoku_block[AnsRow][col] = ChkNo;
            
                System.out.print('\u000C');
                sudoku_print(sudoku_block);
                
                null_prob_row[ChkNo][AnsRow] = AnsRow;
                null_prob_col[ChkNo][col] = col;
                    
                for(int a = 1; a <= 9; a++)
                    Possibility[AnsRow][col][a] = 0;
                    
                NoContPosElmnt(ChkNo, AnsRow, col);
                PosCalBox(ChkNo, Row1, Row2, Col1, Col2);
                PosCalCol(ChkNo, col);
                PosCalRow(ChkNo, AnsRow);
            }
        }
    }
    
    public void PossibilityRowSolve(int ChkNo)
    {
        int NoFnd;
        int AnsCol;
        int Row1 = 0, Row2 = 0, Col1 = 0, Col2 = 0;
        for(int row = 1; row <= 9; row++)
        {
            NoFnd = 0;
            AnsCol = 0;
            for(int col = 1; col <= 9; col++)
            {
                for(int ch = 1; ch <= 9; ch++)
                {
                    if(Possibility[row][col][ch] == ChkNo)
                    {
                        ++NoFnd;
                        if(NoFnd == 1)
                        {
                            AnsCol = col;
                            if     ((col >= 1) && (col <= 3)) { Col1 = 1; Col2 = 3; }
                            else if((col >= 4) && (col <= 6)) { Col1 = 4; Col2 = 6; }
                            else if((col >= 7) && (col <= 9)) { Col1 = 7; Col2 = 9; }
                            if     ((row >= 1) && (row <= 3)) { Row1 = 1; Row2 = 3; }
                            else if((row >= 4) && (row <= 6)) { Row1 = 4; Row2 = 6; }
                            else if((row >= 7) && (row <= 9)) { Row1 = 7; Row2 = 9; }
                        }
                    }
                }
            }
                
            if(NoFnd == 1)
            {
                sudoku_block[row][AnsCol] = ChkNo;
            
                System.out.print('\u000C');
                sudoku_print(sudoku_block);
                
                null_prob_row[ChkNo][row] = row;
                null_prob_col[ChkNo][AnsCol] = AnsCol;
                
                NoContPosElmnt(ChkNo, row, AnsCol);
                PosCalBox(ChkNo, Row1, Row2, Col1, Col2);
                PosCalCol(ChkNo, AnsCol);
                PosCalRow(ChkNo, row);
            }
        }
    }
    
    public void PossibilitySolve()
    {
        int AnsNo;
        int Row1, Row2, Col1, Col2;
        for(int row  = 1; row <= 9; row++)
        {
            for(int col = 1; col <= 9; col++)
            {
                Row1 = 0; Row2 = 0; Col1 = 0; Col2 = 0;
                AnsNo = 0;
                if(PosCnt[row][col] == 1)
                {
                    if     ((col >= 1) && (col <= 3)) { Col1 = 1; Col2 = 3; }
                    else if((col >= 4) && (col <= 6)) { Col1 = 4; Col2 = 6; }
                    else if((col >= 7) && (col <= 9)) { Col1 = 7; Col2 = 9; }
                    if     ((row >= 1) && (row <= 3)) { Row1 = 1; Row2 = 3; }
                    else if((row >= 4) && (row <= 6)) { Row1 = 4; Row2 = 6; }
                    else if((row >= 7) && (row <= 9)) { Row1 = 7; Row2 = 9; }
                    
                    AnsNo = Possibility[row][col][1];
                    sudoku_block[row][col] = AnsNo;
                
                    System.out.print('\u000C');
                    sudoku_print(sudoku_block);
                    
                    null_prob_row[AnsNo][row] = row;
                    null_prob_col[AnsNo][col] = col;
                    
                    NoContPosElmnt(AnsNo, row, col);
                    PosCalBox(AnsNo, Row1, Row2, Col1, Col2);
                    PosCalCol(AnsNo, col);
                    PosCalRow(AnsNo, row);
                }
            }
        }
    }
    
    public void PossibilityBoxSolve(int Row1, int Row2, int Col1, int Col2, int ChkNo)
    {
        int NoFnd = 0;
        int AnsRow = 0, AnsCol = 0;
        for(int row = Row1; row <= Row2; row++)
        {
            for(int col = Col1; col <= Col2; col++)
            {
                for(int ch = 1; ch <= 9 ; ch++)
                {
                    if(Possibility[row][col][ch] == ChkNo)
                    {
                        ++NoFnd;
                        if(NoFnd == 1)
                        {
                            AnsRow = row;
                            AnsCol = col;
                        }
                    }
                }
            }
        }
        
        if(NoFnd == 1)
        {
            sudoku_block[AnsRow][AnsCol] = ChkNo;
        
            System.out.print('\u000C');
            sudoku_print(sudoku_block);
            
            null_prob_row[ChkNo][AnsRow] = AnsRow;
            null_prob_col[ChkNo][AnsCol] = AnsCol;
            
            NoContPosElmnt(ChkNo, AnsRow, AnsCol);
            PosCalBox(ChkNo, Row1, Row2, Col1, Col2);
            PosCalCol(ChkNo, AnsCol);
            PosCalRow(ChkNo, AnsRow);
        }
    }
    
    public void PossibilityCal()
    {
        int row_ch_1 = 0, row_ch_2 = 0;
        int col_ch_1 = 0, col_ch_2 = 0;
        int IncrNo;
        for(int row = 1; row <= 9; row++)
        {
            if     ((row >= 1) && (row <= 3)) { row_ch_1 = 1; row_ch_2 = 3; }
            else if((row >= 4) && (row <= 6)) { row_ch_1 = 4; row_ch_2 = 6; }
            else if((row >= 7) && (row <= 9)) { row_ch_1 = 7; row_ch_2 = 9; }
            for(int col = 1; col <= 9; col++)
            {
                if     ((col >= 1) && (col <= 3)) { col_ch_1 = 1; col_ch_2 = 3; }
                else if((col >= 4) && (col <= 6)) { col_ch_1 = 4; col_ch_2 = 6; }
                else if((col >= 7) && (col <= 9)) { col_ch_1 = 7; col_ch_2 = 9; }
                
                IncrNo = 0;
                for(int ch = 1; ch <= 9; ch++)
                {   
                    Possibility[row][col][ch] = 0;
                    if(sudoku_block[row][col] == 0)
                    {
                        if(!(((null_prob_row[ch][row] == row) && (col != no_cont_col[ch][row][col])) || ((null_prob_col[ch][col] == col) && (row != no_cont_row[ch][col][row])) || (box_repeat_no_chk(row_ch_1, row_ch_2, col_ch_1, col_ch_2, ch))))
                            
                        // else
                        {
                            ++IncrNo;
                            Possibility[row][col][IncrNo] = ch;
                        }
                        if(ch == 9)
                            PosCnt[row][col] = IncrNo;
                    }
                }
            }
        }
    }
    
    public void RowColSolve()
    {
        int no_fnd, ans_ch;
        int row_ch_1 = 0, row_ch_2 = 0;
        int col_ch_1 = 0, col_ch_2 = 0;
        int Ans_row_ch_1 = 0, Ans_row_ch_2 = 0, Ans_col_ch_1 = 0, Ans_col_ch_2 = 0;
        for(int row = 1; row <= 9; row++)
        {
            if     ((row >= 1) && (row <= 3)) { row_ch_1 = 1; row_ch_2 = 3; }
            else if((row >= 4) && (row <= 6)) { row_ch_1 = 4; row_ch_2 = 6; }
            else if((row >= 7) && (row <= 9)) { row_ch_1 = 7; row_ch_2 = 9; }
            
            for(int col = 1; col <= 9; col++)
            {
                if     ((col >= 1) && (col <= 3)) { col_ch_1 = 1; col_ch_2 = 3; }
                else if((col >= 4) && (col <= 6)) { col_ch_1 = 4; col_ch_2 = 6; }
                else if((col >= 7) && (col <= 9)) { col_ch_1 = 7; col_ch_2 = 9; }
                
                no_fnd = 0; ans_ch = 0;
                for(int ch = 1; ch <= 9; ch++)
                {
                    if(sudoku_block[row][col] == 0)
                    {
                        if(((null_prob_row[ch][row] == row) && (col != no_cont_col[ch][row][col])) || ((null_prob_col[ch][col] == col) && (row != no_cont_row[ch][col][row])) || (box_repeat_no_chk(row_ch_1, row_ch_2, col_ch_1, col_ch_2, ch)))
                            continue;
                        else
                        {
                            ++no_fnd;
                            if(no_fnd == 1)
                            {
                                ans_ch = ch;
                                Ans_row_ch_1 = row_ch_1; Ans_row_ch_2 = row_ch_2; Ans_col_ch_1 = col_ch_1; Ans_col_ch_2 = col_ch_2;
                            }
                        }
                    }
                }
                
                if(no_fnd == 1)
                {
                    SolCh[ans_ch] = 5;
                
                    sudoku_block[row][col] = ans_ch;
                    
                    System.out.print('\u000C');
                    sudoku_print(sudoku_block);
                    
                    null_prob_row[ans_ch][row] = row;
                    null_prob_col[ans_ch][col] = col;
                    
                    NoContPosElmnt(ans_ch, row, col);
                    PosCalBox(ans_ch, Ans_row_ch_1, Ans_row_ch_2, Ans_col_ch_1, Ans_col_ch_2);
                    PosCalCol(ans_ch, col);
                    PosCalRow(ans_ch, row);
                }
            }
        }
    }
    
    public void AdvanceRowSolve(int chk_no)
    {
        int check_row[] = new int[4];
        int check_col[] = new int[4];
        int eq_0_no[] = new int[10], eq_0_tot_no;
        int row_fnd_2, row_fnd_1; 
        boolean row_rep_chk1, row_rep_chk2;
        int ans_row = 0, ans_col = 0;
        
        for(int j = 1; j <= 3; j++)
        {
            check_row[3] = (j * 3);
            check_row[2] = ((j * 3) - 1);
            check_row[1] = ((j * 3) - 2);
            for(int k = 1; k <= 3; k++)
            {
                check_col[3] = (k * 3);
                check_col[2] = ((k * 3) - 1);
                check_col[1] = ((k * 3) - 2);
                
                if(box_repeat_no_chk(check_row[1], check_row[3], check_col[1], check_col[3], chk_no))
                    continue;
                
                for(int d = 1; d <= 9; d++)    
                    eq_0_no[d] = 0;
                row_fnd_2 = 0;
                eq_0_tot_no = 0;
                ans_row = 0;
                row_fnd_1 = 0;
                for(int l = check_row[1]; l <= check_row[3]; l++)
                {
                    if(l == null_prob_row[chk_no][l])
                        continue;
                        
                    row_rep_chk1 = true; row_rep_chk2 = true;
                    for(int m = check_col[1]; m <= check_col[3]; m++)
                    {
                        if(m == null_prob_col[chk_no][m])
                            continue;
                       
                        if(sudoku_block[l][m] == 0)
                        {
                            ++eq_0_no[l];
                            ++eq_0_tot_no;
                            
                            if(eq_0_no[l] > 0 && row_rep_chk1)
                            {
                                ++row_fnd_1; 
                                row_rep_chk1 = false;
                            }
                                
                            if(eq_0_no[l] > 1 && row_rep_chk2)
                            {
                                ++row_fnd_2;
                                row_rep_chk2 = false;
                                if(row_fnd_2 == 1 && eq_0_tot_no >= 2 && eq_0_tot_no <= 3 && row_fnd_1 == 1)
                                {
                                    ans_row = l;
                                }
                            }
                        }
                    }
                }
                
                if(row_fnd_2 == 1 && eq_0_tot_no >= 2 && eq_0_tot_no <= 3 && row_fnd_1 == 1)
                {
                    SolCh[chk_no] = 1;
                    
                    null_prob_row[chk_no][ans_row] = ans_row;
                    
                    no_cont_col[chk_no][ans_row][check_col[1]] = check_col[1];
                    no_cont_col[chk_no][ans_row][check_col[2]] = check_col[2];
                    no_cont_col[chk_no][ans_row][check_col[3]] = check_col[3];
                    
                    for(int z = 1; z <= 9; z++)
                    {
                        if((z == check_col[1]) || (z == check_col[2]) || (z == check_col[3]))
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[ans_row][z][y] == chk_no)
                            {
                                for(int x = y; x <= PosCnt[ans_row][z]; x++)
                                    Possibility[ans_row][z][x] = Possibility[ans_row][z][x + 1];
                                --PosCnt[ans_row][z];    
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityRowWing(int ChkNo)
    {
        int check_row[] = new int[4];
        int check_col[] = new int[4];
        int eq_fnd_no[] = new int[10], eq_fnd_tot_no;
        int row_fnd_2, row_fnd_1; 
        boolean row_rep_chk1, row_rep_chk2;
        int ans_row = 0, ans_col = 0;
        
        for(int j = 1; j <= 3; j++)
        {
            check_row[3] = (j * 3);
            check_row[2] = ((j * 3) - 1);
            check_row[1] = ((j * 3) - 2);
            for(int k = 1; k <= 3; k++)
            {
                check_col[3] = (k * 3);
                check_col[2] = ((k * 3) - 1);
                check_col[1] = ((k * 3) - 2);
                
                if(box_repeat_no_chk(check_row[1], check_row[3], check_col[1], check_col[3], ChkNo))
                    continue;
                
                for(int d = 1; d <= 9; d++)    
                    eq_fnd_no[d] = 0;
                row_fnd_2 = 0;
                eq_fnd_tot_no = 0;
                ans_row = 0;
                row_fnd_1 = 0;
                for(int l = check_row[1]; l <= check_row[3]; l++)
                {
                    if(l == null_prob_row[ChkNo][l])
                        continue;
                        
                    row_rep_chk1 = true; row_rep_chk2 = true;
                    for(int m = check_col[1]; m <= check_col[3]; m++)
                    {
                        if(m == null_prob_col[ChkNo][m])
                            continue;
                        if(sudoku_block[l][m] != 0)
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[l][m][y] == ChkNo)
                            {
                                ++eq_fnd_no[l];
                                ++eq_fnd_tot_no;
                                
                                if(eq_fnd_no[l] > 0 && row_rep_chk1)
                                {
                                    ++row_fnd_1; 
                                    row_rep_chk1 = false;
                                }
                                    
                                if(eq_fnd_no[l] > 1 && row_rep_chk2)
                                {
                                    ++row_fnd_2;
                                    row_rep_chk2 = false;
                                    if(row_fnd_2 == 1 && eq_fnd_tot_no >= 2 && eq_fnd_tot_no <= 3 && row_fnd_1 == 1)
                                    {
                                        ans_row = l;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                
                if(row_fnd_2 == 1 && eq_fnd_tot_no >= 2 && eq_fnd_tot_no <= 3 && row_fnd_1 == 1)
                {
                    null_prob_row[ChkNo][ans_row] = ans_row;
                    
                    for(int z = 1; z <= 9; z++)
                    {
                        if((z == check_col[1]) || (z == check_col[2]) || (z == check_col[3]))
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[ans_row][z][y] == ChkNo)
                            {
                                for(int x = y; x <= PosCnt[ans_row][z]; x++)
                                    Possibility[ans_row][z][x] = Possibility[ans_row][z][x + 1];
                                --PosCnt[ans_row][z];    
                            }
                        }
                    }
                    int xxx = 8;
                }
            }
        }
    }
    
    public void AdvanceColSolve(int chk_no)
    {
        int check_row[] = new int[4];
        int check_col[] = new int[4];
        int eq_0_no[] = new int[10], eq_0_tot_no;
        int col_fnd_2, col_fnd_1; 
        boolean col_rep_chk1, col_rep_chk2;
        int ans_row = 0, ans_col = 0;
        
        for(int j = 1; j <= 3; j++)
        {
            check_row[3] = (j * 3);
            check_row[2] = ((j * 3) - 1);
            check_row[1] = ((j * 3) - 2);
            for(int k = 1; k <= 3; k++)
            {
                check_col[3] = (k * 3);
                check_col[2] = ((k * 3) - 1);
                check_col[1] = ((k * 3) - 2);
                
                if(box_repeat_no_chk(check_row[1], check_row[3], check_col[1], check_col[3], chk_no))
                    continue;
                
                for(int d = 1; d <= 9; d++)    
                    eq_0_no[d] = 0;
                col_fnd_2 = 0;
                eq_0_tot_no = 0;
                ans_row = 0;
                col_fnd_1 = 0;
                for(int l = check_col[1]; l <= check_col[3]; l++)
                {
                    if(l == null_prob_col[chk_no][l])
                        continue;
                        
                    col_rep_chk1 = true; col_rep_chk2 = true;
                    for(int m = check_row[1]; m <= check_row[3]; m++)
                    {
                        if(m == null_prob_row[chk_no][m])
                            continue;
                        
                        if(sudoku_block[m][l] == 0)
                        {
                            ++eq_0_no[l];
                            ++eq_0_tot_no;
                            
                            if(eq_0_no[l] > 0 && col_rep_chk1)
                            {
                                ++col_fnd_1; 
                                col_rep_chk1 = false;
                            }
                                
                            if(eq_0_no[l] > 1 && col_rep_chk2)
                            {
                                ++col_fnd_2;
                                col_rep_chk2 = false;
                                if(col_fnd_2 == 1 && eq_0_tot_no >= 2 && eq_0_tot_no <= 3 && col_fnd_1 == 1)
                                {
                                    ans_col = l;
                                }
                            }
                        }
                    }
                }
                
                if(col_fnd_2 == 1 && eq_0_tot_no >= 2 && eq_0_tot_no <= 3 && col_fnd_1 == 1)
                {
                    SolCh[chk_no] = 2;
                    
                    null_prob_col[chk_no][ans_col] = ans_col;
                    
                    no_cont_row[chk_no][ans_col][check_row[1]] = check_row[1];
                    no_cont_row[chk_no][ans_col][check_row[2]] = check_row[2];
                    no_cont_row[chk_no][ans_col][check_row[3]] = check_row[3];
                    
                    for(int z = 1; z <= 9; z++)
                    {
                        if((z == check_row[1]) || (z == check_row[2]) || (z == check_row[3]))
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[z][ans_col][y] == chk_no)
                            {
                                for(int x = y; x <= PosCnt[z][ans_col]; x++)
                                    Possibility[z][ans_col][x] = Possibility[z][ans_col][x + 1];
                                --PosCnt[z][ans_col];    
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityAdvRowWing(int ChkNo)
    {
        int NoFnd = 0;
        int FndCol[] = new int[10];
        int AnsBox = 0;
        for(int col = 1; col <= 9; col++)
        {
            for(int y = 1; y <= PosCnt[ChkNo][col]; y++)
            {
                if(Possibility[ChkNo][col][y] == ChkNo)
                {
                    ++NoFnd;
                    FndCol[NoFnd] = col;
                }
            }
        }
        
        if(NoFnd == 2)
        {
                 if(FndCol[1] >= 1 && FndCol[1] <= 3 && FndCol[2] >= 1 && FndCol[2] <= 3) AnsBox = 1;
            else if(FndCol[1] >= 4 && FndCol[1] <= 6 && FndCol[2] >= 4 && FndCol[2] <= 6) AnsBox = 2;
            else if(FndCol[1] >= 7 && FndCol[1] <= 9 && FndCol[2] >= 7 && FndCol[2] <= 9) AnsBox = 3;
        }
        else if(NoFnd == 3)
        {
                 if(FndCol[1] >= 1 && FndCol[1] <= 3 && FndCol[2] >= 1 && FndCol[2] <= 3 && FndCol[3] >= 1 && FndCol[3] <= 3) AnsBox = 1;
            else if(FndCol[1] >= 4 && FndCol[1] <= 6 && FndCol[2] >= 4 && FndCol[2] <= 6 && FndCol[3] >= 1 && FndCol[3] <= 3) AnsBox = 2;
            else if(FndCol[1] >= 7 && FndCol[1] <= 9 && FndCol[2] >= 7 && FndCol[2] <= 9 && FndCol[3] >= 1 && FndCol[3] <= 3) AnsBox = 3;
        }
        
        if(AnsBox >= 1)
        {
            for(int row = (((((int)(Math.floor(ChkNo / 3.5))) + 1) * 3) - 2); row <= ((((int)(Math.floor(ChkNo / 3.5))) + 1) * 3); row++)
            {
                if (row == ChkNo)
                    continue;
                    
                for(int col = ((AnsBox * 3) - 2); col <= (AnsBox * 3); col++)
                {
                    for(int y = 1; y <= 9; y++)
                    {
                        if(Possibility[row][col][y] == ChkNo)
                        {
                            for(int x = y; x <= PosCnt[row][col]; x++)
                                Possibility[row][col][x] = Possibility[row][col][x + 1];
                            --PosCnt[row][col];    
                        }
                    }
                }
            }
        }
    }
    
    public void XY_Wing(int RowNo)
    {
        int NoFnd = 0;
        int FndSq[] = new int[10];
        int AnsBoxCS, AnsBoxCE;
        int AnsBoxRS, AnsBoxRE;
        for(int col = 1; col <= 9; col++)
        {
            if(PosCnt[RowNo][col] == 2)
            {
                ++NoFnd;
                FndSq[NoFnd] = col;
            }
        }
        int X, Y; 
        int Ans, OtherAns, OtherNo;
        boolean FrthrChk;
        int BoxSq1, RowSq1;
        for(int col = 1; col <= NoFnd; col++)
        {
            X = Possibility[RowNo][FndSq[col]][1];
            Y = Possibility[RowNo][FndSq[col]][2];
            for(int ch_col = 1; ch_col <= NoFnd; ch_col++)
            {
                if(FndSq[ch_col] == FndSq[col])
                    continue;
                FrthrChk = false; OtherAns = 0; OtherNo = 0; BoxSq1 = 0; RowSq1 = 0;
                     if(((Possibility[RowNo][FndSq[ch_col]][1] == X) || (Possibility[RowNo][FndSq[ch_col]][1] == Y)) && ((Possibility[RowNo][FndSq[ch_col]][2] != X) && (Possibility[RowNo][FndSq[ch_col]][2] != Y)))              
                {
                    Ans = Possibility[RowNo][FndSq[ch_col]][1];
                    OtherNo = Possibility[RowNo][FndSq[ch_col]][2];
                    
                    BoxSq1 = (((int)(Math.floor(FndSq[ch_col] / 3.5))) + 1);
                    
                    if(Ans == X) OtherAns = Y;
                    else if(Ans == Y) OtherAns = X;
                    
                    FrthrChk = true;
                }
                else if(((Possibility[RowNo][FndSq[ch_col]][1] != X) && (Possibility[RowNo][FndSq[ch_col]][1] != Y)) && ((Possibility[RowNo][FndSq[ch_col]][2] == X) || (Possibility[RowNo][FndSq[ch_col]][2] == Y)))
                {
                    Ans = Possibility[RowNo][FndSq[ch_col]][2];
                    OtherNo = Possibility[RowNo][FndSq[ch_col]][1];
                    
                    BoxSq1 = (((int)(Math.floor(FndSq[ch_col] / 3.5))) + 1);
                    
                    if(Ans == X) OtherAns = Y;
                    else if(Ans == Y) OtherAns = X;
                    
                    FrthrChk = true;
                }
                
                if(FrthrChk)
                {
                    AnsBoxCS = (((((int)(Math.floor(FndSq[col] / 3.5))) + 1) * 3) - 2);
                    AnsBoxCE = ((((int)(Math.floor(FndSq[col] / 3.5))) + 1) * 3);
                    
                    AnsBoxRS = (((((int)(Math.floor(RowNo / 3.5))) + 1) * 3) - 2);
                    AnsBoxRE = ((((int)(Math.floor(RowNo / 3.5))) + 1) * 3);
                    
                    for(int BoxR = AnsBoxRS; BoxR <= AnsBoxRE; BoxR++)
                    {
                        for(int BoxC = AnsBoxCS; BoxC <= AnsBoxCE; BoxC++)
                        {
                            if((BoxR == RowNo) && (BoxC == FndSq[col]))
                                continue;
                                
                            if(PosCnt[BoxR][BoxC] == 2)
                            {
                                if(((Possibility[BoxR][BoxC][1] == OtherAns) && (Possibility[BoxR][BoxC][2] == OtherNo)) || ((Possibility[BoxR][BoxC][1] == OtherNo) && (Possibility[BoxR][BoxC][2] == OtherAns)))
                                {
                                    for(int RepCol = ((BoxSq1 * 3) - 2); RepCol <= (BoxSq1 * 3); RepCol++)
                                    {
                                        for(int y = 1; y <= 9; y++)
                                        {
                                            if(Possibility[BoxR][RepCol][y] == OtherNo)
                                            {
                                                for(int x = y; x <= PosCnt[BoxR][RepCol]; x++)
                                                    Possibility[BoxR][RepCol][x] = Possibility[BoxR][RepCol][x + 1];
                                                --PosCnt[BoxR][RepCol];
                                            }
                                        }
                                    }
                                    
                                    for(int RepCol = AnsBoxCS; RepCol <= AnsBoxCE; RepCol++)
                                    {
                                        for(int y = 1; y <= 9; y++)
                                        {
                                            if(Possibility[RowNo][RepCol][y] == OtherNo)
                                            {
                                                for(int x = y; x <= PosCnt[RowNo][RepCol]; x++)
                                                    Possibility[RowNo][RepCol][x] = Possibility[RowNo][RepCol][x + 1];
                                                --PosCnt[RowNo][RepCol];    
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityAdvColWing(int ChkNo)
    {
        int NoFnd = 0;
        int FndRow[] = new int[10];
        int AnsBox = 0;
        for(int row = 1; row <= 9; row++)
        {
            for(int y = 1; y <= PosCnt[row][ChkNo]; y++)
            {
                if(Possibility[row][ChkNo][y] == ChkNo)
                {
                    ++NoFnd;
                    FndRow[NoFnd] = row;
                }
            }
        }
        
        if(NoFnd == 2)
        {
                 if(FndRow[1] >= 1 && FndRow[1] <= 3 && FndRow[2] >= 1 && FndRow[2] <= 3) AnsBox = 1;
            else if(FndRow[1] >= 4 && FndRow[1] <= 6 && FndRow[2] >= 4 && FndRow[2] <= 6) AnsBox = 2;
            else if(FndRow[1] >= 7 && FndRow[1] <= 9 && FndRow[2] >= 7 && FndRow[2] <= 9) AnsBox = 3;
        }
        else if(NoFnd == 3)
        {
                 if(FndRow[1] >= 1 && FndRow[1] <= 3 && FndRow[2] >= 1 && FndRow[2] <= 3 && FndRow[3] >= 1 && FndRow[3] <= 3) AnsBox = 1;
            else if(FndRow[1] >= 4 && FndRow[1] <= 6 && FndRow[2] >= 4 && FndRow[2] <= 6 && FndRow[3] >= 1 && FndRow[3] <= 3) AnsBox = 2;
            else if(FndRow[1] >= 7 && FndRow[1] <= 9 && FndRow[2] >= 7 && FndRow[2] <= 9 && FndRow[3] >= 1 && FndRow[3] <= 3) AnsBox = 3;
        }
        
        if(AnsBox >= 1)
        {
            for(int col = (((((int)(Math.floor(ChkNo / 3.5))) + 1) * 3) - 2); col <= ((((int)(Math.floor(ChkNo / 3.5))) + 1) * 3); col++)
            {
                if (col == ChkNo)
                    continue;
                    
                for(int row = ((AnsBox * 3) - 2); row <= (AnsBox * 3); row++)
                {
                    
                    for(int y = 1; y <= 9; y++)
                    {
                        if(Possibility[row][col][y] == ChkNo)
                        {
                            for(int x = y; x <= PosCnt[row][col]; x++)
                                Possibility[row][col][x] = Possibility[row][col][x + 1];
                            --PosCnt[row][col];    
                        }
                    }
                }
            }
        }
    }
    
    public void PossibilityColWing(int ChkNo)
    {
        int check_row[] = new int[4];
        int check_col[] = new int[4];
        int eq_fnd_no[] = new int[10], eq_fnd_tot_no;
        int col_fnd_2, col_fnd_1; 
        boolean col_rep_chk1, col_rep_chk2;
        int ans_row = 0, ans_col = 0;
        
        for(int j = 1; j <= 3; j++)
        {
            check_row[3] = (j * 3);
            check_row[2] = ((j * 3) - 1);
            check_row[1] = ((j * 3) - 2);
            for(int k = 1; k <= 3; k++)
            {
                check_col[3] = (k * 3);
                check_col[2] = ((k * 3) - 1);
                check_col[1] = ((k * 3) - 2);
                
                if(box_repeat_no_chk(check_row[1], check_row[3], check_col[1], check_col[3], ChkNo))
                    continue;
                
                for(int d = 1; d <= 9; d++)    
                    eq_fnd_no[d] = 0;
                col_fnd_2 = 0;
                eq_fnd_tot_no = 0;
                ans_row = 0;
                col_fnd_1 = 0;
                for(int l = check_col[1]; l <= check_col[3]; l++)
                {
                    if(l == null_prob_col[ChkNo][l])
                        continue;
                        
                    col_rep_chk1 = true; col_rep_chk2 = true;
                    for(int m = check_row[1]; m <= check_row[3]; m++)
                    {
                        if(m == null_prob_row[ChkNo][m])
                            continue;
                        if(sudoku_block[m][l] != 0)
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[m][l][y] == ChkNo)
                            {
                                ++eq_fnd_no[l];
                                ++eq_fnd_tot_no;
                                
                                if(eq_fnd_no[l] > 0 && col_rep_chk1)
                                {
                                    ++col_fnd_1; 
                                    col_rep_chk1 = false;
                                }
                                    
                                if(eq_fnd_no[l] > 1 && col_rep_chk2)
                                {
                                    ++col_fnd_2;
                                    col_rep_chk2 = false;
                                    if(col_fnd_2 == 1 && eq_fnd_tot_no >= 2 && eq_fnd_tot_no <= 3 && col_fnd_1 == 1)
                                    {
                                        ans_col = l;
                                    }
                                }
                                
                                break;
                            }
                        }
                    }
                }
                
                if(col_fnd_2 == 1 && eq_fnd_tot_no >= 2 && eq_fnd_tot_no <= 3 && col_fnd_1 == 1)
                {   
                    null_prob_col[ChkNo][ans_col] = ans_col;
                    
                    for(int z = 1; z <= 9; z++)
                    {
                        if((z == check_row[1]) || (z == check_row[2]) || (z == check_row[3]))
                            continue;
                        for(int y = 1; y <= 9; y++)
                        {
                            if(Possibility[z][ans_col][y] == ChkNo)
                            {
                                for(int x = y; x <= PosCnt[z][ans_col]; x++)
                                    Possibility[z][ans_col][x] = Possibility[z][ans_col][x + 1];
                                --PosCnt[z][ans_col];    
                            }
                        }
                    }
                    
                    int xxxx = 8;
                }
            }
        }
    }
    
    public void row_solve(int chk_no)
    {
        boolean row_repeat_chk;
        int _0_eq_no, ansr_col;
        int row_ch_1 = 0, row_ch_2 = 0, col_ch_1 = 0, col_ch_2 = 0;
        int ans_row_ch_1 = 0, ans_row_ch_2 = 0, ans_col_ch_1 = 0, ans_col_ch_2 = 0;
        for(int row = 1; row <= 9; row++)
        {
            row_repeat_chk = false;
            for(int row_chk = 1; row_chk <= 9; row_chk++)
            
                if(sudoku_block[row][row_chk] == chk_no)
                {
                    row_repeat_chk = true; 
                    break;
                }
            
            if(row_repeat_chk)
                continue;
            
            if     ((row >= 1) && (row <= 3)) { row_ch_1 = 1; row_ch_2 = 3; }
            else if((row >= 4) && (row <= 6)) { row_ch_1 = 4; row_ch_2 = 6; }
            else if((row >= 7) && (row <= 9)) { row_ch_1 = 7; row_ch_2 = 9; }
            
            _0_eq_no = 0; ansr_col = 0;
            for(int col = 1; col <= 9; col++)
            {
                if((col == null_prob_col[chk_no][col]) && (row != no_cont_row[chk_no][col][row]))
                    continue;
                
                if     ((col >= 1) && (col <= 3)) { col_ch_1 = 1; col_ch_2 = 3; }
                else if((col >= 4) && (col <= 6)) { col_ch_1 = 4; col_ch_2 = 6; }
                else if((col >= 7) && (col <= 9)) { col_ch_1 = 7; col_ch_2 = 9; }
                
                if(box_repeat_no_chk(row_ch_1, row_ch_2, col_ch_1, col_ch_2, chk_no))
                    continue;
                
                if(sudoku_block[row][col] == 0)
                {
                    ++_0_eq_no;
                    if(_0_eq_no == 1)
                    {
                        ansr_col = col;
                        ans_row_ch_1 = row_ch_1; ans_row_ch_2 = row_ch_2; ans_col_ch_1 = col_ch_1; ans_col_ch_2 = col_ch_2;
                    }
                }
            }
            
            if(_0_eq_no == 1)
            {
                SolCh[chk_no] = 3;
                
                sudoku_block[row][ansr_col] = chk_no;
                
                System.out.print('\u000C');
                sudoku_print(sudoku_block);
                
                null_prob_row[chk_no][row] = row;
                null_prob_col[chk_no][ansr_col] = ansr_col;
    
                NoContPosElmnt(chk_no, row, ansr_col);
                PosCalBox(chk_no, ans_row_ch_1, ans_row_ch_2, ans_col_ch_1, ans_col_ch_2);
                PosCalCol(chk_no, ansr_col);
                PosCalRow(chk_no, row);
            }
        }
    }
    
    public void column_solve(int ch_no)
    {
        boolean col_repeat_chk;
        int row_ch_1 = 0, row_ch_2 = 0, col_ch_1 = 0, col_ch_2 = 0;
        int _0_eq_no, ansr_row;
        int ans_row_ch_1 = 0, ans_row_ch_2 = 0, ans_col_ch_1 = 0, ans_col_ch_2 = 0;
        for(int col = 1; col <= 9; col++)
        {
            col_repeat_chk = false;
            for(int col_chk = 1; col_chk <= 9; col_chk++)
            
                if(sudoku_block[col_chk][col] == ch_no)
                {
                    col_repeat_chk = true; 
                    break;
                }
            
            if(col_repeat_chk)
                continue;
        
            if     ((col >= 1) && (col <= 3)) { col_ch_1 = 1; col_ch_2 = 3; }
            else if((col >= 4) && (col <= 6)) { col_ch_1 = 4; col_ch_2 = 6; }
            else if((col >= 7) && (col <= 9)) { col_ch_1 = 7; col_ch_2 = 9; }
            
            _0_eq_no = 0; ansr_row = 0;
            for(int row = 1; row <= 9; row++)
            {
                if(row == null_prob_row[ch_no][row] && (col != no_cont_col[ch_no][row][col]))
                    continue;
                
                if     ((row >= 1) && (row <= 3)) { row_ch_1 = 1; row_ch_2 = 3; }
                else if((row >= 4) && (row <= 6)) { row_ch_1 = 4; row_ch_2 = 6; }
                else if((row >= 7) && (row <= 9)) { row_ch_1 = 7; row_ch_2 = 9; }
                
                if(box_repeat_no_chk(row_ch_1, row_ch_2, col_ch_1, col_ch_2, ch_no))
                    continue;
                
                if(sudoku_block[row][col] == 0)
                {
                    ++_0_eq_no;
                    if(_0_eq_no == 1)
                    {
                        ansr_row = row;
                        ans_row_ch_1 = row_ch_1; ans_row_ch_2 = row_ch_2; ans_col_ch_1 = col_ch_1; ans_col_ch_2 = col_ch_2;
                    }
                }
            }
            
            if(_0_eq_no == 1)
            {
                SolCh[ch_no] = 4;
                
                sudoku_block[ansr_row][col] = ch_no;
                
                System.out.print('\u000C');
                sudoku_print(sudoku_block);
                
                null_prob_row[ch_no][ansr_row] = ansr_row;
                null_prob_col[ch_no][col] = col;
                
                NoContPosElmnt(ch_no, ansr_row, col);
                PosCalBox(ch_no, ans_row_ch_1, ans_row_ch_2, ans_col_ch_1, ans_col_ch_2);
                PosCalCol(ch_no, col);
                PosCalRow(ch_no, ansr_row);
            }
        }
    }
    
    public boolean box_repeat_no_chk(int row_ch1, int row_ch2, int col_ch1, int col_ch2, int check_no)
    {
        boolean box_re_no_ch = false;
        
        for(int i = row_ch1; i <= row_ch2; i++)
        
            for(int j = col_ch1; j <= col_ch2; j++)
            
                if(sudoku_block[i][j] == check_no)
                    box_re_no_ch = true;
                    
        return(box_re_no_ch);
    }
    
    public void sudoku_print(int [][] sudoku_bloc)
    {
        for(int i = 1; i <= 9; i++)
        {
            System.out.println();
            if( i == 1 || i == 4 || i == 7)
                System.out.println("=========================================");
            for(int j = 1; j <= 9; j++)
            {
                if( j == 1 || j == 4)
                    System.out.print("[[");
                else if ( j == 7)
                    System.out.print("]]");
                    
                if((sudoku_bloc[i][j] != 0) && (j % 3 != 0))
                    System.out.print(" " + sudoku_bloc[i][j] + " |");
                else if ((sudoku_bloc[i][j] != 0) && (j % 3 == 0))
                    System.out.print(" " + sudoku_bloc[i][j] + " ");
                else if((sudoku_bloc[i][j] == 0) && (j % 3 != 0))
                    System.out.print("   |");
                else if ((sudoku_bloc[i][j] == 0) && (j % 3 == 0))
                    System.out.print("   ");     
                
                if(j == 9)    
                    System.out.print("]]");    
            }
        }
        System.out.println();
        System.out.println("=========================================");
    }
    
    public boolean sudoku_complete_chk()
    {
        boolean check = false;
        for(int i = 1; i <= 9; i++)
        {
            for(int j = 1; j <= 9; j++)
            {
                if(sudoku_block[i][j] == 0)
                {
                    check = true;
                    break;
                }
            }
            if(check)
                break;
        }
        
        return(check);
    }
}