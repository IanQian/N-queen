/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ShenglanQian
 */
public class SA {
    public class point{
        int x;
        int y;
        point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    Problem pro;
    String result;tNode confilct;
    SA(Problem p){
        pro=p;
        result="FAIL";
        confilct=new tNode(0,pro.board);
    }
    
    
    public void run(){   
       
        for(int p=0;p<10000 ;p++){     
            double T=pro.lizard_num;
            int liz_num=pro.lizard_num;
            tNode temp=new tNode(0,pro.board);
            while(liz_num>0){  //initial
                    int i=(int)(Math.random()*pro.board_size);
                    int j=(int)(Math.random()*pro.board_size);
                    if(temp.cur_board[i][j]==0){
                        temp.cur_board[i][j]=1;
                        liz_num--;continue;
                    }
            }
          
            while(T>1.0E-10){          
                T*=0.95;
                int e_cur=eval(temp);
                if(e_cur==pro.lizard_num){
                    result="OK";
                    write_res(temp);
                    return;
                }
                tNode next=get_suc(temp);
                int e_next=eval(next);
                int delta=e_next-e_cur;
                //System.out.println(e_next);
                if (delta>0){
                    temp=next;
                }
                else if(Math.exp(delta/T) > Math.random()){
                    temp=next;
                }
            }
        }
        write_res(null);
       
    }
    
    public int eval(tNode n){
        int safe_liz=0;
        tNode test=new tNode(0,pro.board);
        for(int i=0;i<pro.board_size;i++){
            for(int j=0;j<pro.board_size;j++){
                if(n.cur_board[i][j]==1&&test.cur_board[i][j]==0){
                    test=putliz(test,i,j);
                    safe_liz++;
                    
                }
            }
        }
        return safe_liz;
    }
    
     public tNode putliz(tNode parent,int x,int y){

        tNode next=parent;
        next.cur_board[x][y]=1;
        next.depth++;
        for(int i=y+1;i<pro.board_size;i++){        //right
            if(next.cur_board[x][i]==2){break;}
            else{next.cur_board[x][i]=-1;}
        }
        for(int i=y-1;i>=0;i--){        //left
            if(next.cur_board[x][i]==2){break;}
            else{
                next.cur_board[x][i]=-1;
            }
        }
        for(int i=x+1;i<pro.board_size;i++){        //down
            if(next.cur_board[i][y]==2){break;}
            else{
                next.cur_board[i][y]=-1;
            }
        }
        for(int i=x-1;i>=0;i--){        //up
            if(next.cur_board[i][y]==2){break;}
            else{
                next.cur_board[i][y]=-1;
            }
        }
        int i=x-1;int j=y-1;
        while(i>=0&&j>=0){              //up left
             if(next.cur_board[i][j]==2){break;}
             else{next.cur_board[i][j]=-1;i--;j--;}
        }
        i=x-1;j=y+1;
        while(i>=0&&j<pro.board_size){              //up right
             if(next.cur_board[i][j]==2){break;}
             else{next.cur_board[i][j]=-1;i--;j++;}
        }
         i=x+1;j=y-1;
        while(i<pro.board_size&&j>=0){              //down left
             if(next.cur_board[i][j]==2){break;}
             else{next.cur_board[i][j]=-1;i++;j--;}
        }
         i=x+1;j=y+1;
        while(i<pro.board_size&&j<pro.board_size){              //down right
             if(next.cur_board[i][j]==2){break;}
             else{next.cur_board[i][j]=-1;i++;j++;}
        }
        return next;
    }
  
    public tNode get_suc(tNode n){
        tNode next=new tNode(0,pro.board);
        List<point> safe=get_safe_liz(n);
        int liz_new=pro.lizard_num-safe.size();
        while(!safe.isEmpty()){
            point temp=safe.get(0);
            safe.remove(0);
            next.cur_board[temp.x][temp.y]=1;
        }
         for(int i=0;i<pro.board_size;i++){
             for(int j=0;j<pro.board_size;j++){
                 if(confilct.cur_board[i][j]==0){
                     confilct=putliz(confilct,i,j);
                     next.cur_board[i][j]=1;
                     liz_new--;
                 }
             }
         }
        while(liz_new>0){
            int i=(int)(Math.random()*pro.board_size);
            int j=(int)(Math.random()*pro.board_size);
            if(next.cur_board[i][j]==0){
                next.cur_board[i][j]=1;
                liz_new--;continue;
            }
        }
        return next;
        
    }
    public List<point> get_safe_liz(tNode n){
        List<point> res=new LinkedList();
        tNode test=new tNode(0,pro.board);
        for(int i=0;i<pro.board_size;i++){
            for(int j=0;j<pro.board_size;j++){
                if(n.cur_board[i][j]==1&&test.cur_board[i][j]==0){
                    test=putliz(test,i,j);
                    res.add(new point(i,j));
                    
                }
            }
        }
        this.confilct=test;
        return res;
    }
    public void write_res(tNode res){
        try{
            File outf= new File("output.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(outf));
                bw.write(result);
                bw.newLine();
                if(result.equals("OK")){
                for(int i=0;i<pro.board_size;i++){
                    for(int j=0;j<pro.board_size;j++){
                        if(res.cur_board[i][j]==1){
                            bw.write("1");
                        }
                        else if(res.cur_board[i][j]==2){
                            bw.write("2");
                        }
                        else{
                            bw.write("0");
                        }
                    }
                    bw.newLine();
                   
                }
            }
                
            bw.close();
        }
        catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
    
}
