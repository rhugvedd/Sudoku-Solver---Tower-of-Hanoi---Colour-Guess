import java.util.Scanner;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sudoku_Solver extends Frame implements MouseListener, MouseMotionListener, KeyListener
{
    Scanner in = new Scanner(System.in);
    int DupSudokuBlock[][] = new int[10][10];
    
    int SudokuStrtX = 480, SudokuStrtY = 210, SudokuW = 540, SudokuH = 540;
    int box_side = SudokuW / 9;
    int StrtButnX = 630, StrtButnY = 780, ButnW = 50, ButnH = 30;
    int TimeBxX = 50, TimeBxY = 200, TimeBxW = 350, TimeBxH = 100;
    
    boolean BoxClicked = false, InputDone = false, StrtButnPresd = true;
    int Row = 0, Col = 0, Num = 0;
    Color SelBoxC = Color.yellow;

    int sudoku_block[][] = new int[10][10];

    // Sudokus for Testing:
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,4,0,0,0,0,9,0,2,0},{0,3,9,0,5,0,0,8,7,0},{0,0,0,0,0,2,4,0,0,0},{0,0,6,7,0,0,0,9,0,0},{0,0,0,0,2,0,8,0,0,0},{0,0,0,3,0,0,0,1,4,0},{0,0,0,0,6,5,0,0,0,0},{0,0,5,1,0,0,7,0,3,6},{0,0,3,0,8,0,0,0,0,5}};
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,1,4,0,0,3},{0,0,0,3,2,0,0,0,1,0},{0,0,2,1,9,8,0,4,0,0},{0,2,8,0,0,9,5,0,0,4},{0,0,0,0,4,2,8,0,0,0},{0,9,0,0,6,7,0,0,2,5},{0,0,0,5,0,4,6,7,8,0},{0,0,4,0,0,0,2,6,0,0},{0,6,0,0,8,3,0,0,0,0}};    //Done Easy Sudoku
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,7,0},{0,9,8,2,1,0,0,0,0,0},{0,0,0,4,0,2,5,0,9,0},{0,0,0,0,0,8,0,0,5,0},{0,0,0,3,7,0,4,2,0,0},{0,0,4,0,0,9,0,0,0,0},{0,0,6,0,9,7,0,3,0,0},{0,0,0,0,0,0,2,6,1,4},{0,0,2,0,0,0,0,0,0,0}};    //Done Sudoku -- 692                    
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,1,3,0,7,0,4,0,2,5},{0,0,0,0,0,0,0,0,0,0},{0,0,0,6,2,0,1,4,0,0},{0,4,0,8,0,1,0,7,0,6},{0,0,5,0,0,0,0,0,9,0},{0,3,0,1,0,9,0,8,0,2},{0,0,0,3,5,0,6,9,0,0},{0,0,0,0,0,0,0,0,0,0},{0,5,6,0,8,0,9,0,1,3}};    //Done Sudoku -- 719                             
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,1,0,0,4,8,9,0,0,6},{0,7,3,0,0,0,0,0,4,0},{0,0,0,0,0,0,1,2,9,5},{0,0,0,7,1,2,0,6,0,0},{0,5,0,0,7,0,3,0,0,8},{0,0,0,6,0,9,5,7,0,0},{0,9,1,4,6,0,0,0,0,0},{0,0,2,0,0,0,0,0,3,7},{0,8,0,0,5,1,2,0,0,4}};    //Done                                      
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,2,4,7,5,0,3,0},{0,0,0,0,0,2,6,0,0,4},{0,0,0,0,0,8,9,0,6,0},{0,0,0,9,2,0,0,0,0,0},{0,0,1,5,0,0,0,7,2,0},{0,0,0,0,0,0,8,4,0,0},{0,0,5,0,8,9,0,0,0,0},{0,4,0,0,6,0,0,0,0,0},{0,0,2,0,0,3,1,9,0,0}};    //Done                                           
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,4,0,5,0,3,0,9,0},{0,6,0,0,0,0,0,0,0,5},{0,0,0,0,9,8,7,0,0,0},{0,0,6,0,0,0,0,0,2,0},{0,1,0,3,0,0,0,9,0,6},{0,0,7,0,0,0,0,0,8,0},{0,0,0,0,7,1,9,0,0,0},{0,7,0,0,0,0,0,0,0,2},{0,0,5,0,4,0,6,0,3,0}};    //Done Sudoku -- 712                     
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,7,4,0,0,0,0,0},{0,0,0,0,0,5,9,0,0,3},{0,0,9,8,0,0,0,0,6,0},{0,1,0,0,0,7,5,3,2,0},{0,0,0,0,0,0,0,0,0,0},{0,0,7,3,9,6,0,0,0,4},{0,0,3,0,0,0,0,2,5,0},{0,8,0,0,1,2,0,0,0,0},{0,0,0,0,0,0,3,4,0,0}};    //Done                                         
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,9,0,0,0,6,0,0,0,0},{0,0,5,0,0,0,2,0,9,0},{0,0,7,2,4,0,0,0,0,0},{0,3,0,5,0,0,0,0,0,7},{0,1,0,0,0,4,0,0,0,5},{0,6,0,0,0,0,0,9,0,2},{0,0,0,0,0,0,6,8,5,0},{0,0,3,0,1,0,0,0,6,0},{0,0,0,0,0,8,0,0,0,9}};    //Done Sudoku -- 703                         
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,2,0,0,0,0,0,4,0},{0,0,0,0,6,0,7,0,0,0},{0,0,4,7,2,0,8,6,9,0},{0,0,0,4,0,5,0,7,0,0},{0,1,8,0,0,0,0,0,2,4},{0,0,0,6,0,8,0,9,0,0},{0,0,9,2,8,0,5,3,7,0},{0,0,0,0,1,0,4,0,0,0},{0,0,7,0,0,0,0,0,6,0}};    //Done Sudoku -- 71                       
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,5},{0,3,0,9,0,0,0,4,0,8},{0,5,0,2,0,3,1,0,0,0},{0,1,0,0,5,0,0,0,6,0},{0,0,0,0,2,9,6,0,0,0},{0,0,6,0,0,0,4,0,0,9},{0,0,0,0,4,2,0,1,0,3},{0,4,0,7,0,0,0,9,0,2},{0,8,0,0,0,0,0,0,5,0}};    //Done Sudoku --71                  
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,4,3,2,6,0,0,0,0},{0,0,0,6,0,0,0,0,0,3},{0,0,8,9,1,0,5,0,0,0},{0,0,3,0,6,0,0,0,0,4},{0,4,7,0,0,1,0,0,3,8},{0,8,0,0,0,0,4,0,7,0},{0,0,0,0,7,0,6,1,8,0},{0,1,0,0,0,0,0,9,0,0},{0,0,0,0,0,9,1,3,2,0}};    //Done -- internet                 
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,2,0,6,0,8,0,0,0},{0,5,8,0,0,0,9,7,0,0},{0,0,0,0,0,4,0,0,0,0},{0,3,7,0,0,0,0,5,0,0},{0,6,0,0,0,0,0,0,0,4},{0,0,0,8,0,0,0,0,1,3},{0,0,0,0,0,2,0,0,0,0},{0,0,0,9,8,0,0,0,3,6},{0,0,0,0,3,0,6,0,9,0}};    //Done - Internet --- Intermediate                                                
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,2,0,0,3,0,0,0,0,0},{0,8,0,4,0,6,2,0,0,3},{0,0,1,3,8,0,0,2,0,0},{0,0,0,0,0,2,0,3,9,0},{0,5,0,7,0,0,0,6,2,1},{0,0,3,2,0,0,6,0,0,0},{0,0,2,0,0,0,9,1,4,0},{0,6,0,1,2,5,0,8,0,9},{0,0,0,0,0,0,1,0,0,2}};    //internet --- Difficult                                                   
    // int sudoku_block[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,2,0,0,0,0,0,0,0},{0,0,0,0,6,0,0,0,0,3},{0,0,7,4,0,8,0,0,0,0},{0,0,0,0,0,0,3,0,0,2},{0,0,8,0,0,4,0,0,1,0},{0,6,0,0,5,0,0,0,0,0},{0,0,0,0,0,1,0,7,8,0},{0,5,0,0,0,0,9,0,0,0},{0,0,0,0,0,0,0,0,4,0}};    //Done - Internet --- Not Fun                                        
    
    int null_prob_row[][] = new int[10][10];
    int null_prob_col[][] = new int[10][10];
    int no_cont_col[][][] = new int[10][10][10];
    int no_cont_row[][][] = new int[10][10][10];
    int Possibility[][][] = new int[10][10][10];
    int PosCnt[][] = new int[10][10];
    int SolAnsChk = 1, SolCh[] = {1,1,1,1,1,1,1,1,1,1};

    public Sudoku_Solver()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        setSize(toolkit.getScreenSize().width, toolkit.getScreenSize().height);
        setLayout(new BorderLayout());
        setVisible(true);
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e)
    {
        int MouseX = e.getX();
        int MouseY = e.getY();
        
        if((MouseX >= StrtButnX) && (MouseX <= (StrtButnX + ButnW)))
            if((MouseY >= StrtButnY) && (MouseY <= (StrtButnY + ButnH)))
            {
                InputDone = true;
                StrtButnPresd = true;
            }
        repaint();
    }
    
    public void mouseReleased(MouseEvent e)
    {
        int MouseX = e.getX();
        int MouseY = e.getY();
        
        if((MouseX >= StrtButnX) && (MouseX <= (StrtButnX + ButnW)))
            if((MouseY >= StrtButnY) && (MouseY <= (StrtButnY + ButnH)))
            {
                InputDone = true;
                StrtButnPresd = true;
            }
        repaint();
    }
    
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void mousePressed(MouseEvent e)
    {
        int MouseX = e.getX();
        int MouseY = e.getY();
        
        SudokuBlockIn(MouseX, MouseY);
        StrtButnPresdChk(MouseX, MouseY);
        repaint();
    }
    
    public void keyPressed(KeyEvent e)
    {
        BoxNumIn(e);
        repaint();
    }
    
    public void HeadingPaint(Graphics g)
    {
        Font f = new Font("fff", Font.ITALIC, 100);
        g.setFont(f);
        
        g.setColor(Color.red);
        g.drawString("Sudoku Solver!!!", 370, 150);
        
        Font Name = new Font("fff", Font.ROMAN_BASELINE, 30);
        g.setFont(Name);
        
        g.setColor(Color.RED);
        g.drawString("-- By Rhugved Chaudhari", 720, 813);
    }
    
    public void StrtButnPaint(Graphics g)
    {
        g.setColor(Color.yellow);
        g.fillRect(StrtButnX - 150, StrtButnY - 10, ButnW + 160, ButnH + 20);
        
        g.setColor(Color.BLUE);
        Font f = new Font("FF", Font.PLAIN, 30);
        g.setFont(f);
        g.drawString("Start -- ", StrtButnX - 130, StrtButnY + 25);
        for(int i = 0; i <= 4; i++)
            g.drawRect((StrtButnX - 150) - i, (StrtButnY - 10) - i, (ButnW + 160) + (i * 2), (ButnH + 20) + (i * 2));
        
        g.setColor(Color.green);
        g.fill3DRect(StrtButnX, StrtButnY, ButnW, ButnH, StrtButnPresd);
    }
    
    public void StrtButnPresdChk(int x, int y)
    {
        if((x >= StrtButnX) && (x <= (StrtButnX + ButnW)))
            if((y >= StrtButnY) && (y <= (StrtButnY + ButnH)))
            {
                InputDone = true;
                StrtButnPresd = false;
            }
    }
    
    public void BoxNumIn(KeyEvent e)
    {
        char InNum = e.getKeyChar();
        if(BoxClicked)
            if(((int)(InNum) >= 48) && ((int)(InNum) <= 57))
            {
                Num = (int)InNum - 48;
                sudoku_block[Row][Col] = Num;
            }
    }
    
    public void SelBoxPaint(Graphics g)
    {
        g.setColor(SelBoxC);
        if(BoxClicked)
            g.fillRect(((SudokuStrtX + (box_side * (Col - 1))) + 3), ((SudokuStrtY + (box_side * (Row - 1))) + 3), (box_side - 5), (box_side - 5));
    }
    
    public void SudokuBlockIn(int x, int y)
    {
        Row = 0; Col = 0;
        if((x >= SudokuStrtX) && (x <= (SudokuStrtX + SudokuW)))
            if((y >= SudokuStrtY) && (y <= (SudokuStrtY + SudokuH)))
            {
                Col = (((x - SudokuStrtX) / box_side) + 1);
                Row = (((y - SudokuStrtY) / box_side) + 1);
                BoxClicked = true;
            }
            else
            {
                BoxClicked = false;
                Num = 0;
            }
        else
        {
            BoxClicked = false;
            Num = 0;
        }
    }
    
    public void BoxEnteredPaint(Graphics g)
    {
        for(int i = 1; i <= 9; i++)
        {
            for(int j = 1; j <= 9; j++)
            {
                if(InputDone == false)
                {
                    if(sudoku_block[i][j] != 0)
                        g.fillRect(((SudokuStrtX + (box_side * (j - 1))) + 3), ((SudokuStrtY + (box_side * (i - 1))) + 3), (box_side - 5), (box_side - 5));                    
                }    
                else
                    if(DupSudokuBlock[i][j] != 0)
                        g.fillRect(((SudokuStrtX + (box_side * (j - 1))) + 3), ((SudokuStrtY + (box_side * (i - 1))) + 3), (box_side - 5), (box_side - 5));                    
            }
        }
    }
    
    public void SudokuBasePaint(Graphics g)
    {   
        g.setColor(Color.CYAN);
        g.fillRect(SudokuStrtX, SudokuStrtY, SudokuW, SudokuH);
        
        g.setColor(Color.BLUE);
        for(int i = 0; i <= 9; i++)
            g.drawRect(SudokuStrtX - i, SudokuStrtY - i, SudokuW + (i * 2), SudokuH + (i * 2));
        
        for(int i = 1; i <= 2; i++)  
            for(int j = -2; j <= 2; j++) 
                g.drawLine(SudokuStrtX + ((box_side * 3 * i) + j), SudokuStrtY, SudokuStrtX + ((box_side * 3 * i) + j), SudokuStrtY + SudokuH);
        
        for(int i = 1; i <= 2; i++)
            for(int j = -2; j <= 2; j++)
                g.drawLine(SudokuStrtX, SudokuStrtY + ((box_side * 3 * i) + j), SudokuStrtX + SudokuW, SudokuStrtY + ((box_side * 3 * i) + j));
        
        for(int i = 1; i <= 9; i++)
            g.drawLine(SudokuStrtX + (box_side * i), SudokuStrtY, SudokuStrtX + (box_side * i), SudokuStrtY + SudokuH);
        
        for(int i = 1; i <= 9; i++)
            g.drawLine(SudokuStrtX, SudokuStrtY + (box_side * i), SudokuStrtX + SudokuW, SudokuStrtY + (box_side * i));        
    }
    
    long pre_t; boolean Done = false; long curr_t;
    public static void main(String args[])
    {
        Sudoku_Solver ob = new Sudoku_Solver();
        for(;;)
        {
            if(ob.InputDone == true)
                break;
        }
        ob.NullProbRowCol();
        ob.SudokuCopy();
        ob.PossibilityCal();
    
        ob.pre_t = System.currentTimeMillis();
    
        ob.solve();

        ob.curr_t = System.currentTimeMillis();
        
        ob.Done = true;
    }
    
    @Override
    public void update(Graphics g) {
        paint(g); 
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        HeadingPaint(g);
        SudokuBasePaint(g);
        SelBoxPaint(g);
        BoxEnteredPaint(g);
        StrtButnPaint(g);
        SudokuPrint(sudoku_block, g);
        VerifAnsNTimePrint(g);
    }
    
    private void VerifAnsNTimePrint(Graphics g)
    {
        Font f1 = new Font("rr",Font.ITALIC, 16);
        g.setFont(f1);
        if(Done == true)
        {
            Sudoku_Solver ob1 = new Sudoku_Solver();
            
            if(ob1.Verification() == 0)
                g.drawString("Verification Pass", 100, 300);
            else
                g.drawString("Verification Fail", 100, 300);
                
            f1 = new Font("dd", Font.ITALIC, 35);  
            g.setFont(f1); 
            
            g.setColor(Color.yellow);
            g.fillRect(TimeBxX, TimeBxY, TimeBxW, TimeBxH);
            
            g.setColor(Color.blue);
            g.drawString(" -- Time Taken -- ", TimeBxX + ((TimeBxW / 8) - 20), TimeBxY + (TimeBxH / 4) + 10);
            g.drawString(Long.toString(curr_t - pre_t) + " MilliSeconds ", TimeBxX + (TimeBxW / 4 - 20), TimeBxY + (TimeBxH - (TimeBxH / 4 ) + 10));
            
            g.setColor(Color.RED);
            for(int i = 0; i <= 4; i++)
                g.drawRect(TimeBxX - i, TimeBxY - i, TimeBxW + (i * 2), TimeBxH + (i * 2));
        }
    }
    
    private  void SudokuCopy()
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
    
    private int Verification()
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
    
    private  void NullProbRowCol()
    {
        for(int i = 1; i <= 9; i++)
        {
            for(int j = 1; j <= 9; j++)
            {
                if(sudoku_block[i][j] != 0)
                {
                    null_prob_row[sudoku_block[i][j]][i] = i;
                    null_prob_col[sudoku_block[i][j]][j] = j;
                }
            }
        }
    }
    
    private void solve()
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
                                
                                // repaint();
                                
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
                            
                            Possibility2RowColBox(i, check_row[1], check_row[3], check_col[1], check_col[3]);
                            Possibility3RowColBox(i);
                            
                            PossibilityRowWing(i);
                            PossibilityColWing(i);
                            
                            PossibilityAdvColWing(i);
                            PossibilityAdvRowWing(i);
                            
                            XY_Wing(i);
                        }
                    }
                }

                if((i == 9) && (SolCh[1] == 0) && (SolCh[2] == 0) && (SolCh[3] == 0) && (SolCh[4] == 0) && (SolCh[5] == 0) && (SolCh[6] == 0) && (SolCh[7] == 0) && (SolCh[8] == 0) && (SolCh[9] == 0))
                    SolAnsChk =  0;
            }
        }

        repaint();
    }
    
    private  void PosCalRow(int ChkNo, int AnsRow)
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
    
    private  void PosCalCol(int ChkNo, int AnsCol)
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
    
    private  void PosCalBox(int ChkNo, int Row1, int Row2, int Col1, int Col2)
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
    
    private  void NoContPosElmnt(int ChkNo, int AnsRow, int AnsCol)
    {
        for(int r = 0; r <= 9; r++)
        {   
            no_cont_col[ChkNo][AnsRow][r] = 0;
            no_cont_row[ChkNo][AnsCol][r] = 0;
            Possibility[AnsRow][AnsCol][r] = 0;
            PosCnt[AnsRow][AnsCol] = 0;
        }
    }
    
    private  void PossibilityAdvColSolve(int ChkCol)
    {
        int Fnd1, Fnd2;
        boolean ans = true;
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
    
    private  void Possibility2RowColBox(int Chk, int Row1, int Row2, int Col1, int Col2)
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
    
    private  void PossibilityAdvRowSolve(int ChkRow)
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
    
    private  void PossibilityColSolve(int ChkNo)
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
            
                
                // repaint();
                
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
    
    private  void PossibilityRowSolve(int ChkNo)
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
            
                // repaint();
                
                null_prob_row[ChkNo][row] = row;
                null_prob_col[ChkNo][AnsCol] = AnsCol;
                
                NoContPosElmnt(ChkNo, row, AnsCol);
                PosCalBox(ChkNo, Row1, Row2, Col1, Col2);
                PosCalCol(ChkNo, AnsCol);
                PosCalRow(ChkNo, row);
            }
        }
    }
    
    private  void PossibilitySolve()
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
                
                    
                    // repaint();
                    
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
    
    private  void PossibilityBoxSolve(int Row1, int Row2, int Col1, int Col2, int ChkNo)
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
        
            // repaint();
            
            null_prob_row[ChkNo][AnsRow] = AnsRow;
            null_prob_col[ChkNo][AnsCol] = AnsCol;
            
            NoContPosElmnt(ChkNo, AnsRow, AnsCol);
            PosCalBox(ChkNo, Row1, Row2, Col1, Col2);
            PosCalCol(ChkNo, AnsCol);
            PosCalRow(ChkNo, AnsRow);
        }
    }
    
    private void PossibilityCal()
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
    
    private  void RowColSolve()
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
                    
                    // repaint();
                    
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
    
    private void AdvanceRowSolve(int chk_no)
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
    
    private void PossibilityAdvBoxSolve()
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
    
    private void Possibility3RowColBox(int Chk)
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
    
    private void PossibilityAdvRowWing(int ChkNo)
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
    
    private void XY_Wing(int RowNo)
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
    
    private void PossibilityAdvColWing(int ChkNo)
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
     
    private  void PossibilityRowWing(int ChkNo)
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
    
    private  void AdvanceColSolve(int chk_no)
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
    
    private  void PossibilityColWing(int ChkNo)
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
    
    private  void row_solve(int chk_no)
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
                
                // repaint();
                
                null_prob_row[chk_no][row] = row;
                null_prob_col[chk_no][ansr_col] = ansr_col;
    
                NoContPosElmnt(chk_no, row, ansr_col);
                PosCalBox(chk_no, ans_row_ch_1, ans_row_ch_2, ans_col_ch_1, ans_col_ch_2);
                PosCalCol(chk_no, ansr_col);
                PosCalRow(chk_no, row);
            }
        }
    }
    
    private  void column_solve(int ch_no)
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
                
                // repaint();
                
                null_prob_row[ch_no][ansr_row] = ansr_row;
                null_prob_col[ch_no][col] = col;
                
                NoContPosElmnt(ch_no, ansr_row, col);
                PosCalBox(ch_no, ans_row_ch_1, ans_row_ch_2, ans_col_ch_1, ans_col_ch_2);
                PosCalCol(ch_no, col);
                PosCalRow(ch_no, ansr_row);
            }
        }
    }
    
    private boolean box_repeat_no_chk(int row_ch1, int row_ch2, int col_ch1, int col_ch2, int check_no)
    {
        boolean box_re_no_ch = false;
        
        for(int i = row_ch1; i <= row_ch2; i++)
        
            for(int j = col_ch1; j <= col_ch2; j++)
            
                if(sudoku_block[i][j] == check_no)
                    box_re_no_ch = true;
                    
        return(box_re_no_ch);
    }
    
    private void SudokuPrint(int [][] sudoku_bloc, Graphics g)
    {
        Font f = new Font("Dialog", Font.PLAIN, 30);
        g.setFont(f);
        
        g.setColor(Color.RED);
        String AnsNum = "";
        int X = 0, Y = 0;
        for(int i = 1; i <= 9; i++)
        {
            for(int j = 1; j <= 9; j++)
            {
                if(sudoku_bloc[i][j] != 0)
                {
                    AnsNum = Integer.toString(sudoku_block[i][j]);
                    X = (SudokuStrtX + (box_side * (j - 1)) + ((box_side / 2) - 2)) - 7;
                    Y = (SudokuStrtY + (box_side * (i)) - (box_side / 2) + 4) + 8;
                    g.drawString(AnsNum, X, Y);
                }
            }
        }
    }
    
    private  boolean sudoku_complete_chk()
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