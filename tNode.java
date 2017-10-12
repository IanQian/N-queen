/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ShenglanQian
 */
public class tNode implements Cloneable{
    int[][] cur_board;
    int depth;
    tNode(int d,String[] str){
        this.depth=d;
        this.cur_board=new int[str.length][str.length];
        for(int i=0;i<str.length;i++){
            for(int j=0;j<str[i].length();j++){
                cur_board[i][j]=str[i].charAt(j)-'0';
            }
        }
    }
    tNode(int d, int[][] board){
        this.cur_board=new int[board.length][board.length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                this.cur_board[i][j]=board[i][j];
            }
        }
        this.depth=d;
    }
    
   
    
}
