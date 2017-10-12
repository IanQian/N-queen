/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ShenglanQian
 */
public class Problem {
    
        String method;
        int board_size;
        int lizard_num;
        String[] board;
        Map<Integer,List<Integer>> tree;
        Problem(String name){
            try {
                File f=new File(name);
                BufferedReader reader = new BufferedReader(new FileReader(f)); 
                this.method= reader.readLine();
                this.board_size=Integer.parseInt(reader.readLine());
                this.lizard_num=Integer.parseInt(reader.readLine());
                this.board=new String[board_size];
                this.tree=new HashMap<Integer,List<Integer>>();
                for(int i=0;i<board_size;i++){
                    board[i]=reader.readLine();
                    int j=0;
                    while((j=board[i].indexOf("2", j))!=-1){
                        List temp=tree.getOrDefault(i, new LinkedList<Integer>());
                        temp.add(j);
                        tree.put(i, temp);
                        j++;
                    }  
                }
                
            } catch (IOException e) {  
            e.printStackTrace();  
            } 
        }
    
}
