/**
 * This is my implementation of a Binary Search Tree
 * Name: Ethan Lin
 * Email: etl003@ucsd.edu
 * Sources used: Zybooks
 * 
 * This is a class file that contains the framework of MyBST. It contains the
 * variables, constructors, and methods required to implement my version of
 * a BST
 */

import java.util.ArrayList;


public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * Return the number of nodes in the tree 
     * @return the number of nodes in the tree
     */
    public int size(){
        return size;
    }

    /**
     * Insert a new node containing the arguments key and value into the binary 
     * search tree according to the binary search tree properties.
     * @param key of the inserted node
     * @param value of the inserted node
     * @return the replaced value
     */
    public V insert(K key, V value){
        if (key == null) {
            throw new NullPointerException();
        }
        MyBSTNode<K,V> curr = root;
        V oldValue = null;
        if (curr == null) {
            root = new MyBSTNode(key, value, null);
            size++;
        }
        else if (curr.key.equals(key)) {
            oldValue = curr.getValue();
            root.setValue(value);
        }
        else if (curr != null) {
            //Iterate through the BST to find where to insert the node
            while (curr != null) {
                //Check the right side if the key is higher
                if (key.compareTo(curr.key) > 0) {
                    if (curr.right == null) {
                        curr.right = new MyBSTNode(key, value, curr);
                        size++;
                        curr = null;
                    }
                    else if (curr.right.key.equals(key)) {
                        oldValue = curr.right.getValue();
                        curr = null;
                    }
                    else {
                        curr = curr.right;
                    }

                }
                //Check the left side if the key is lower
                else if (key.compareTo(curr.key) < 0) {
                    if (curr.left == null) {
                        curr.left = new MyBSTNode(key, value, curr);
                        size++;
                        curr = null;
                    }
                    else if (curr.left.key.equals(key)) {
                        oldValue = curr.left.getValue();
                        curr = null;
                    }
                    else {
                        curr = curr.left;
                    }
                }
            }

        }
        return oldValue;
    }

    /**
     * Search for a node with key equal to key and return the value associated
     * with that node.
     * @param key of the node being searched for
     * @return the value associated with the searched key
     */
    public V search(K key){
        if (key == null) {
            return null;
        }
        MyBSTNode<K,V> curr = root;
        //Iterate through the BST
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            //Check the right side if the key is higher
            else if (curr.key.compareTo(key) < 0) {
                curr = curr.right;
            }
            //Check the left side if the key is lower
            else if (curr.key.compareTo(key) > 0) {
                curr = curr.left;
            }
        }
        return null;
    }

    /**
     * Search for a node with key equal to key and return the value associated 
     * with that node, then removing the node
     * @param key of the node being removed
     * @return the value of the removed node
     */
    public V remove(K key){
        MyBSTNode<K,V> parent = null;
        MyBSTNode<K,V> curr = root;
        //Search for node
        while (curr != null) {
            //Node found
            if (curr.key.equals(key)) {
                V oldValue = curr.getValue();
                //Remove leaf
                if (curr.left == null && curr.right == null) {
                    //Node is root
                    if (parent == null) {
                        root = null;
                    }
                    else if (parent.left == curr) {
                        parent.left = null;
                    }
                    else {
                        parent.right = null;
                    }
                }
                //Remove node with only left child
                else if (curr.right == null) {
                    if (parent == null) {
                        root = curr.left;
                    }
                    else if (parent.left == curr) {
                        parent.left = curr.left;
                    }
                    else {
                        parent.right = curr.left;
                    }
                }
                //Remove node with only right child
                else if (curr.left == null) {
                    if (parent == null) {
                        root = curr.right;
                    }
                    else if (parent.left == curr) {
                        parent.left = curr.right;
                    }
                    else {
                        parent.right = curr.right;
                    }
                }
                //Remove node with two children
                else {
                    MyBSTNode<K,V> succ = curr.successor();
                    while (succ.left != null) {
                        succ = succ.left;
                    }
                    K succKey = succ.getKey();
                    V succValue = succ.getValue();
                    //Remove the successor
                    remove(succ.getKey());                
                    curr.setValue(succValue);
                    curr.setKey(succKey);
                    size--;
                }
                return oldValue;
            }
            //Look to the right if the key is too large
            else if (curr.key.compareTo(key) < 0) {
                parent = curr;
                curr = curr.right;
            }
            //Look to the left if the key is too small
            else {
                parent = curr;
                curr = curr.left;
            }
        }
        return null;
    }
    
    /**
     * Do an in-order traversal of the tree, adding each node to the end of an 
     * ArrayList, which will be returned.
     * @return an Arraylist of each node, in order.
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList ordered = new ArrayList<>();
        MyBSTNode<K,V> curr = root;
        if (curr == null) {
            return ordered;
        }
        while (curr.predecessor() != null) {
            curr = curr.predecessor();
        }
        ordered.add(curr);
        while (curr.successor() != null) {
            curr = curr.successor();
            ordered.add(curr);
        }
        return ordered;
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /** This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            //Check if the node to the right of the current node is empty
            if(this.getRight() != null){
                // Iterate down and left of the right node, returning the
                // left-most node
                MyBSTNode<K,V> curr = this.getRight();
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // Iterate upwards and to the right, as long as the current 
                // node is equal to the right child of the parent node and the 
                // parent node is not null
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method returns the in order predecessor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            //Check if the node to the left of the current node is empty
            if(this.getLeft() != null){
                // Iterate down and right of the right node, returning the
                // right-most node
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                // Iterate upwards and to the left, as long as the current 
                // node is equal to the left child of the parent node and the 
                // parent node is not null
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}