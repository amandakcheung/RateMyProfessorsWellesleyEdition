package com.example.demo;

import java.util.LinkedList;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import com.example.demo.javafoundations.ArrayIterator;
import com.example.demo.javafoundations.LinkedQueue;
/**
 * AdjListsDiGraph.java
 * Uses a Vector of LinkedLists to keep track of the adjacent vertices.
 * @author ac119, dh4, hw4, ac111
 * @version 04-14-2021
 */

public class AdjListsDiGraph<T> 
{
    private Vector<T> vertices; //Vector to hold the vertices in the graph
    private Vector<LinkedList<T>> arcs;   // Lists of adjacent vertices

    /**
     * Constructor. Creates an empty graph.
     * 
     */
    public AdjListsDiGraph() {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    /**
     * Creates and returns a new graph of Strings using the data found
     * in the input tgf file.
     * If the file does not exist, a message is printed.
     *
     * @param String The name of the tgf file to read the graph from
     * @return AdjListsDiGraph<String> the graph of Strings created
     */
    public static AdjListsDiGraph<String> AdjListsGraphFromFile(String tgf_fName) {
        //create an empty graph of Strings
        AdjListsDiGraph<String> g = new AdjListsDiGraph<String>();

        try{
            Scanner scanner = new Scanner(new File(tgf_fName));
            //read vertices
            while (!scanner.next().equals("#")){ //discard special symbol (#)
                String token = scanner.next(); //read next String
                g.addVertex(token); //add it a vertex to the graph
            }

            //read arcs
            while (scanner.hasNext()){
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                g.addArc(from-1, to-1); //uses helper method
            }
            scanner.close();
        } catch (IOException ex) {
            System.out.println(" ***ERROR***  " +  tgf_fName + " file could not be read. ");
        }
        return g;
    }

    /**
     * Helper. Inserts an arc between two vertices of the graph.
     *
     * @param int The index of the first vertex in the vertices vector (starting from 0)
     * @param int The index of the second vertex in the vertices vector (starting from 0)
     */
    private void addArc (int index1, int index2) {
        T source = vertices.get(index1);
        T destination = vertices.get(index2);
        addArc(source, destination);
    }

    /**
     * Saves the current graph into a .tgf file.
     * @param the name of the file to write to
     */
    public void saveToTGF(String fName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fName));
            //notice that indexing in the tgf format starts at 1 (not 0)

            //write vertices by iterating through vector "vertices"
            for (int i = 0; i < vertices.size(); i++) {
                writer.print((i+1) + " " + vertices.get(i));
                writer.println("");
            }
            writer.println("#"); // # symbol separates the vertices from the arcs
            writer.println("");
            //write arcs by iterating through arcs vector
            for (int i = 0; i < arcs.size(); i++){ //for each adjacent list
                for (T destinationVertex :arcs.get(i)) { //for each destination vertex in that list
                    int destinationIndex = vertices.indexOf(destinationVertex); //find the index of that vertex
                    writer.println((i+1) + " " + (destinationIndex+1));
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" +  fName + " could not be written");
        }
    }    
    // END OF GIVEN

    // START OF LAB
    /**
     * Returns true if the graph is empty and false otherwise.
     * @return boolean True iff the graph is empty
     * 
     */
    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    /**
     * Returns the number of vertices in the graph
     * @return int The number of vertices in the graph
     * 
     */
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * Returns the number of arcs in the graph
     * @return int The number of arcs in the graph
     * 
     */
    public int getNumArcs() {
        int totalArcs = 0;
        for (int i = 0; i < vertices.size(); i++) //for each vertex
        //add the number of its connections
            totalArcs = totalArcs + arcs.get(i).size();

        return totalArcs;
    }

    /**
     * Adds the input vertex to the graph.
     * If the vertex already exists in the graph, the graph is not changed.
     * @param T the vertex to be added to the graph
     * 
     */
    public void addVertex (T vertex) {
        if (vertices.indexOf(vertex) == -1) { //the vertex is not already in the graph
            // add it to the vertices vector
            vertices.add(vertex);

            //Create its List of adjacent vertices to be empty
            arcs.add(new LinkedList<T>());
        }
    }

    /**
     * Adds an arc to the graph, from source to destination.
     * If source or destination do not exist in the graph,
     * the graph is not changed.
     * Verifies that source and destination are valid vertices in the graph,
     * and that the newly added arc does not already belong in the graph.
     *
     * @param T the source of the arc
     * @param T the destination of the arc
     * 
     */
    public void addArc (T source, T destination){
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        //if source or destination do not exist, do nothing
        if ((sourceIndex == -1) || (destinationIndex == -1))
            return;

        //find the list of adjacents  for the source
        LinkedList<T> list = arcs.get(sourceIndex);

        //if destination is already there, do nothing
        if (list.indexOf(destination) != -1)
            return;

        //at this point, it is safe to add the arc
        list.add(destination);

    }
    // END OF LAB

    // START OF ASSIGNMENT
    /**
     *  Returns a string representation of the graph.
     *
     *  @return String a string representation of this graph
     */
    public String toString() {
        String s = " ";
        s+= vertices + "\n";
        for (int j = 0; j< arcs.size(); j++){
            if (arcs.get(j)!= null){
                s += "from " + vertices.elementAt(j) + ": " + arcs.get(j) + "\n";
            }
        }
        return s;
    }

    /**
     * Returns true if a directed connection exists between the two input vertices
     * @param T the first vertex
     * @param T the second vertex
     * @return boolean true if a directed connection exists from the first vertex to the second
     */
    public boolean isArc (T v1, T v2){
        for(int i =0; i<vertices.size();i++){
            if(vertices.elementAt(i).equals(v1)){     
                if ( arcs.elementAt(i).contains(v2) ){
                    return true;
                }}
        }
        return false;
    }

    /**
     * Returns true if a directed connection exists between the two input vertices
     * @param T the first index of the vertex
     * @param T the second index of the vertex
     * 
     * @return boolean true iff a directed connection exists from the first vertex to the second
     */
    public boolean isArc (int v1, int v2){
        for(int i =0; i<vertices.size();i++){
            if(vertices.elementAt(i).equals(vertices.elementAt(v1))){     
                if ( arcs.elementAt(i).contains(vertices.elementAt(v2))){
                    return true;
                }}
        }
        return false;
    }

    /**
     * Removes the input vertex from the graph.
     * If the input vertex does not belong in the graph, the graph is not changed.
     * Uses equals() for identidying the vertex to be removed.
     * 
     * @param T The vertex to be removed.
     */
    public void removeVertex (T vertex) {
        for(int i =0; i<vertices.size();i++){
            if(vertices.elementAt(i).equals(vertex)){
                arcs.remove(i);
                vertices.remove(vertex);
                for(int j = 0; j< arcs.size(); j++){
                    if(arcs.elementAt(j).contains(vertex)){
                        arcs.elementAt(j).remove(vertex);
                    }
                }
            } 
        }
    }

    /**
     * Removes the arc between v1 and v2.
     * If v1 or v2, or the arc from v1 to v2 does not exist,
     * the graph does not change.
     *
     * @param T the source of the arc to be removed
     * @param T the destination of the arc to be removed
     */
    public void removeArc (T v1, T v2) {
        for(int i =0; i<vertices.size();i++){
            if(vertices.elementAt(i).equals(v1)){
                for(int j = 0; j< arcs.size(); j++){
                    if(arcs.elementAt(j).contains(v2)){
                        arcs.elementAt(j).remove(v2);
                    }
                }
            } 
        }

    }

    /**
     * checks if the given index is in the range
     * 
     * @return true is the index is valid, false if not valid
     */
    private boolean indexIsValid(int index) {
        return (0 <= index) && (index < vertices.size());
    }

    /**
     * iteratorBFS conducts a breadth first search starting from the "old person" inputted
     * 
     * @param the ancestor vertex
     * @return an iterator that performs a breadth-first search traversal starting at the given index.
     */

    public Iterator<T> iteratorBFS(T oldPerson)
    {
        int currentVertex;
        int startIndex = vertices.indexOf(oldPerson);
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayIterator<T> iter = new ArrayIterator<T>();
        if (!indexIsValid(startIndex)){
            return iter;
        }
        boolean[] visited = new boolean[vertices.size()];
        //System.out.println(vertices.size());
        //System.out.println(visited.length);
        for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++)
            visited[vertexIndex] = false;
        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty())
        {
            currentVertex = traversalQueue.dequeue();
            iter.add(vertices.get(currentVertex));
            for (int vertexIndex = 0; vertexIndex < vertices.size();vertexIndex++)
                if (isArc(currentVertex,vertexIndex) && !visited[vertexIndex])
                {
                    traversalQueue.enqueue(vertexIndex);
                    visited[vertexIndex] = true;
                }
        }
        return iter;
    }

    // END OF ASSIGNMENT

    /**
     * Very Basic Driver program.
     */

    public static void main (String args[]){
        System.out.println("TESTING METHODS");
        System.out.println("_________________");
        AdjListsDiGraph<String> stellaTree = new AdjListsDiGraph<String>();
        stellaTree.addVertex("Wellesley");
        stellaTree.addVertex("Econ");
        stellaTree.addVertex("CS");
        stellaTree.addVertex("Math");
        stellaTree.addVertex("Stella");
        stellaTree.addVertex("Takis");
        stellaTree.addVertex("Christine");

        stellaTree.addArc("Wellesley", "Econ");
        stellaTree.addArc("Wellesley", "CS");
        stellaTree.addArc("Wellesley", "Math");
        stellaTree.addArc("Econ", "Stella");
        stellaTree.addArc("CS", "Takis");
        stellaTree.addArc("Math", "Christine");

        stellaTree.saveToTGF("stellaTree.tgf");
        
        System.out.println(stellaTree);

    }
}