/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author ShenglanQian
 */
public class Node {
    HashMap<Integer,Integer> cur_state;
    int depth;
    int row;
    int col;
    Node(int index,int value){
        cur_state=new HashMap<Integer,Integer>();
        
        this.cur_state.put(index,value );
        depth=1;
    }
}
