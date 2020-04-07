
package tsp_genetic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;





public class TSP_GENETIC {
    
    static int sumC1, sumC2, sumC3, sumC4;
    
    public static int fitness(int[][] graph, ArrayList<Integer>child1){
        int sum = 0;
        int current, next;
        
        for(int i=0; i<7; i++){
            current = child1.get(i);
            next = child1.get(i+1);
            
            sum += graph[current][next];
        }
        
        sum += graph[child1.size()-1][0];
        
        return sum;
    }
    
    public static int init(int[][] graph, ArrayList<Integer> c1, ArrayList<Integer> c2, ArrayList<Integer> c3, ArrayList<Integer> c4){
        
        int sum = 0;
        int current, next;
        
        for(int i=0; i<7; i++){
            current = c1.get(i);
            next = c1.get(i+1);
            
            sum += graph[current][next];
        }
               
        sum += graph[c1.size()-1][0];
       
        sumC1 = sum;
        
        sum = 0;
        
        for(int i=0; i<7; i++){
            current = c2.get(i);
            next = c2.get(i+1);
            
            sum += graph[current][next];
        }
        
        sum += graph[c2.size()-1][0];
        
        sumC2 = sum;
        
        sum = 0;
        
        for(int i=0; i<7; i++){
            current = c3.get(i);
            next = c3.get(i+1);
            
            sum += graph[current][next];
        }
        
        sum += graph[c3.size()-1][0];
        
        sumC3 = sum;
        
        sum = 0;
        
        for(int i=0; i<7; i++){
            current = c4.get(i);
            next = c4.get(i+1);
            
            sum += graph[current][next];
        }
        
        sum += graph[c4.size()-1][0];
        
        sumC4 = sum;
        
        
        sum = sumC1 + sumC2 + sumC3 + sumC4;
        
        return sum;
    }
    
   
    
    public static void printCombination(ArrayList<Integer> c1, ArrayList<Integer> c2, ArrayList<Integer> c3, ArrayList<Integer> c4){
        
       for(int i=0; i<8; i++){
            System.out.print(c1.get(i)+" ");
        }
        
        System.out.println("");
        
        for(int i=0; i<8; i++){
            System.out.print(c2.get(i)+" ");
        }
        
        System.out.println("");
        
        for(int i=0; i<8; i++){
            System.out.print(c3.get(i)+" ");
        }
        
        System.out.println("");
        
        for(int i=0; i<8; i++){
            System.out.print(c4.get(i)+" ");
        }
        
    }
    
    
    
    public static void copyArrayList(ArrayList<Integer> one, ArrayList<Integer> two, int size){
        
        for(int i=0; i<size; i++){
            two.add(one.get(i));
        }
        
    }
    
    public static void generateCombination(ArrayList<Integer> combination, ArrayList<Integer> data){
        
        Random rand = new Random();
        
        for(int i=0; i<7; i++){
            int randomIndex = rand.nextInt(data.size());
            
            combination.add(data.get(randomIndex));
            
            data.remove(randomIndex);
        }
        combination.add(0, 0);
    }

    public static void main(String[] args) {
        
        ArrayList<Integer> data = new ArrayList<>();
        ArrayList<Integer> data2 = new ArrayList<>();
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        ArrayList<Integer> c3 = new ArrayList<>();
        ArrayList<Integer> c4 = new ArrayList<>();
        
        int sum;
        
        int[][] graph = {
            {0,   172, 145, 607, 329, 72,  312, 120},
            {172, 0,   192, 494, 209, 158, 216, 92 },
            {145, 192, 0,   490, 237, 75,  205, 100},
            {607, 494, 490, 0,   286, 545, 296, 489},
            {329, 209, 237, 286, 0 ,  421, 49,  208},
            {72,  158, 75,  545, 421, 0,   249, 75 },
            {312, 216, 205, 296, 49,  249, 0,   194},
            {120, 92,  100, 489, 208, 75,  194, 0  },
        };
      
        
        for(int i=1; i<8; i++){
            data.add(i);
        }
        
        copyArrayList(data, data2, 7);
        
        generateCombination(c1, data);
        
        copyArrayList(data2, data, 7);
        
        generateCombination(c2, data);
        
        copyArrayList(data2, data, 7);
        
        generateCombination(c3, data);
        
        copyArrayList(data2, data, 7);
        
        generateCombination(c4, data);
        
        //printCombination(c1, c2, c3,c4);
        
        sum = init(graph, c1, c2, c3, c4);
        
        System.out.println("4 Candidates for selection");
    
        System.out.println("");
        
        float p1 = ((float)sumC1/(float)sum) * 100;
        float p2 = ((float)sumC2/(float)sum) * 100;
        float p3 = ((float)sumC3/(float)sum) * 100;
        float p4 = ((float)sumC4/(float)sum) * 100;
        
        System.out.println(c1 + " --> fitness = " + p1 + "%");
        System.out.println(c2 + " --> fitness = " + p2 + "%");
        System.out.println(c3 + " --> fitness = " + p3 + "%");
        System.out.println(c4 + " --> fitness = " + p4 + "%");

        ArrayList<Integer> parent1 = new ArrayList<>();
        ArrayList<Integer> parent2 = new ArrayList<>();
        
        float[] probArray = new float[4];
       
        probArray[0] = p1;
        probArray[1] = p2;
        probArray[2] = p3;
        probArray[3] = p4;
        
        //Selection
        
        //Take the best one from the arraylist and copy it to parent1 and then remove it from probarray
        
        System.out.println("");
        System.out.println("**Selection**");
        
        int maxIndex = 0;
        float max = probArray[0];
        
        for(int i=0; i<4; i++){
            
            float item = probArray[i];
            
            if(item < max){
                max = item;
                maxIndex = i; //This is actually minimization problem
            }
        }
        
        switch (maxIndex) {
            case 0:
                for(int i=0; i<8; i++){
                    parent1.add(c1.get(i));
                }   break;
            case 1:
                for(int i=0; i<8; i++){
                    parent1.add(c2.get(i));
                }   break;
            case 2:
                for(int i=0; i<8; i++){
                    parent1.add(c3.get(i));
                }   break;
            case 3:
                for(int i=0; i<8; i++){
                    parent1.add(c4.get(i));
                }   break;
            default:
                break;
        }
        
        //System.out.println(Arrays.toString(probArray));
        //System.out.println(maxIndex);
        //System.out.println("");
              
        //Now Take the second best one from the arraylist and copy it to parent2
        probArray[maxIndex] = 1000000000;
        maxIndex = 0;
        max = probArray[0];
        
        for(int i=0; i<4; i++){
            
       
            float item = probArray[i];
            
            if(item < max){
                max = item;
                maxIndex = i;
            }
        }
       // System.out.println(Arrays.toString(probArray));
       // System.out.println(maxIndex);
        
        
        switch (maxIndex) {
            case 0:
                for(int i=0; i<8; i++){
                    parent2.add(c1.get(i));
                }   break;
            case 1:
                for(int i=0; i<8; i++){
                    parent2.add(c2.get(i));
                }   break;
            case 2:
                for(int i=0; i<8; i++){
                    parent2.add(c3.get(i));
                }   break;
            case 3:
                for(int i=0; i<8; i++){
                    parent2.add(c4.get(i));
                }   break;
            default:
                break;
        }
        
        System.out.println("Parents are: ");
        System.out.println("");
        System.out.println(parent1);
        System.out.println(parent2);
        
        ///End of selection
        
        ///CrossOver --> we will use PMX crossover operator
        
        int bestSolution = 100000; //Minimization problem
        
        ArrayList<Integer> bestChild = new ArrayList<>();
        
        
        System.out.println("**Crossover**");
        for(int x=0; x<100; x++){
            
            ArrayList<Integer> child1 = new ArrayList<>();
            ArrayList<Integer> child2 = new ArrayList<>();

            copyArrayList(parent1, child1, 8);
            copyArrayList(parent2, child2, 8);
           

            for(int i=1; i<=3; i++){
                int item = parent2.get(i);

                int swapIndex = child1.indexOf(item);

                Collections.swap(child1, i, swapIndex);
            }

            for(int i=1; i<=3; i++){
                int item = parent1.get(i);

                int swapIndex = child2.indexOf(item);

                Collections.swap(child2, i, swapIndex);
            }

            if(x == 0){
                System.out.println("One iteration of crossover. **PMX operator used**");
                System.out.println("");
                System.out.println(child1);
                System.out.println(child2);
                System.out.println("");
            }

            //End of crossover

            //Mutation
            //We will use swap mutation at random indexes;
            
            

            int index1 = ThreadLocalRandom.current().nextInt(1, 8);
            int index2 = ThreadLocalRandom.current().nextInt(1, 8);

            if(index1 == index2){
                index2++;

                if(index2 >= 8){
                    index2 %= 7;
                }
            }

            Collections.swap(child1, index1, index2);
            Collections.swap(child2, index1, index2);
            
            if(x == 0){
                System.out.println("**Mutation**");
                System.out.println("");
                System.out.println("Index " + index1 + " and Index "  + index2 + " swapped");
                System.out.println(child1);
                System.out.println(child2);
            }

            int fitness1 = fitness(graph, child1);
            int fitness2 = fitness(graph, child2);
            
            if((fitness1 < fitness2) && (fitness1 < bestSolution)){
                bestChild.clear();
                bestSolution = fitness1;
                copyArrayList(child1, bestChild, 8);
            }else if((fitness1 > fitness2) && (fitness2 < bestSolution)){
                bestChild.clear();
                bestSolution = fitness1;
                copyArrayList(child1, bestChild, 8);
            }

         
            
            copyArrayList(child1, parent1, 8);
            copyArrayList(child2, parent2, 8);
           
        }
        
        System.out.println("After 100 iterations: ");
       
        System.out.println("");
        
        System.out.println("Best Child Score --> " + bestSolution + "Km");
        System.out.println(bestChild);
        
    }
    
}
