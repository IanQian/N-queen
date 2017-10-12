/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ShenglanQian
 */
public class DFS {
    Stack<Node> frontier;
    Stack<tNode> tfrontier;
    Problem pro;
    String result;
    
    DFS(Problem p){
        this.pro=p;
        this.frontier=new Stack();
        this.tfrontier=new Stack();
        this.result="FAIL";
    }
    public void run(){
        if(pro.tree.size()==0){
            if(pro.lizard_num<=pro.board_size){
                run_notree();
            }
            else{
                 write_result(null);
            }
        }
        else{
                 run_tree();
        }
   
    }
    
     public void run_tree(){
         tNode t=new tNode(0,pro.board);
         tfrontier.add(t);
         while(!tfrontier.empty()){ 
             tNode cur=tfrontier.pop();
             if(cur.depth==pro.lizard_num){
                this.result="OK";
                twrite_result(cur);
                return;
            }
            for(int i=0;i<pro.board_size;i++){
                for(int j=0;j<pro.board_size;j++){
                    if(cur.cur_board[i][j]==0){
                        
                        tNode next=new tNode(cur.depth,cur.cur_board);
                        next=putliz(next,i,j);
                        tfrontier.push(next);
                       
                    }
                }
            }
         }
         twrite_result(null);
        
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
  
    
    
    public void run_notree(){
        for(int i=0;i<pro.board_size;i++){

            Node temp=new Node(0,i);
            temp.depth=1;
            frontier.add(temp);
            
        }
      
        while(!frontier.empty()){
            Node cur=frontier.pop();
            
            if(cur.depth==pro.lizard_num){
                this.result="OK";
                write_result(cur);
                return;
            }
            else{
                for(int i=0;i<pro.board_size;i++){
                    if(cur.depth<pro.board_size+1&&check(i,cur.depth,cur)){
                        Node temp=cur;
                        temp.cur_state.put(temp.depth,i);               
                        temp.depth++;
                        frontier.push(temp);
                    }
                  
                }
            }
        }
        write_result(null);
    }
    public boolean check(int x,int y, Node cur){
        for(int i=0;i<cur.depth;i++){
            if(cur.cur_state.containsValue(x)){
            return false;
            }
        }
        for(int i=0;i<cur.cur_state.size();i++){
            if(abs(x-cur.cur_state.get(i))==abs(y-i)){
                return false;
            }
        }
        return true;
    }
    
    public void write_result(Node res){
        try{
            File outf= new File("output.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(outf));
                bw.write(result);
                bw.newLine();
                if(result.equals("OK")){
                for(int i=0;i<res.depth;i++){
                    StringBuilder temp= new StringBuilder(pro.board[i]);
                    temp.setCharAt(res.cur_state.get(i), '1');
                    bw.write(temp.toString());
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (IOException e) {  
            e.printStackTrace();  
        } 
    }
      public void twrite_result(tNode res){
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
                        else{bw.write("0");}
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
