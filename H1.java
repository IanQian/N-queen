/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





/**
 *
 * @author ShenglanQian
 */
public class H1 {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Problem p=new Problem("input.txt");
    
        if(p.method.equals("DFS")){
            DFS testd=new DFS(p);
            testd.run();
        }
        if(p.method.equals("BFS")){
            BFS testb=new BFS(p);
            testb.run();
        }
        if(p.method.equals("SA")){
         
            SA testc=new SA(p);
            testc.run();
           
        }
        //System.out.println(p.board);
        
    }
    
    
}
