package com.leetcode.practice;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=5;
		foo(i);
		System.out.println(i);
		TreeNode iNode = new TreeNode(5);
		foo(iNode);
		System.out.println(iNode.val);
		Integer in = new Integer(5);
		in = new Integer(15);
		B b = new B();
		b.foo();
    	Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
    	queue1.add(null);
    	queue1.add(null);
    	System.out.println(queue1.remove() + String.valueOf(queue1.isEmpty()));
    	System.out.println(queue1.remove() + String.valueOf(queue1.isEmpty()));
    	System.out.println(queue1.poll() + String.valueOf(queue1.isEmpty()));
    	System.out.println(queue1.remove() + String.valueOf(queue1.isEmpty()));
	}

	public static void foo(int i) {
		i++;
	}
	public static void foo(TreeNode i) {
		i.val++;
	}
	public static void foo(Integer i) {
		
	}
}
class B extends A {
	void foo(){
		System.out.println("B");
	}
}
abstract class A {
//	void foo() {
//		System.out.println("A");
//	}
}
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { 
		val = x; 
	}
}
 
class Solution {
    public boolean isSymmetric(TreeNode root) {
//    	Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
    	Queue<TreeNode>[] queue1 = new Queue[2];
//    	Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
    	Queue<TreeNode>[] queue2 = new Queue[2];
    	queue1[0] = new LinkedList<TreeNode>();
    	queue1[1] = new LinkedList<TreeNode>();
    	queue2[0] = new LinkedList<TreeNode>();
    	queue2[1] = new LinkedList<TreeNode>();
    	
    	int count1=0, count2=0, level1=1, level2=1;
    	queue1[level1%2].add(root.left);
    	queue2[level2%2].add(root.right);
    	while(!queue1[level1%2].isEmpty() || !queue2[level2%2].isEmpty()){
			TreeNode node1 = queue1[level1%2].peek();
			TreeNode node2 = queue2[level2%2].peek();
    		if(level1 == level2 && areMirrorNodes(node1, node2)) {
    			node1 = queue1[level1%2].poll();
    			node2 = queue1[level1%2].poll();
//    			if((queue1[level1%2].isEmpty() && !queue2[level1%2].isEmpty()) || (!queue1[level1%2].isEmpty() && queue2[level1%2].isEmpty())) {
//    				return false;
//    			}
    			// node1 == null is true if and only if node2 == null true
    			//hence just check only one node
    			if(node1 != null) {
    				queue1[(level1+1)%2].add(node1.left);
    				queue1[(level1+1)%2].add(node1.right);
    				queue2[(level2+1)%2].add(node1.right);
    				queue2[(level2+1)%2].add(node1.left);
    			}
    			
    			if(queue1[level1%2].isEmpty() && queue2[level2%2].isEmpty()) {
					level1++;
					level2++;
				} else if (queue1[level1 % 2].isEmpty() || queue2[level2 % 2].isEmpty()) {
						return false;
				}
				

    		} else {
    			return false;
    		}
    	}
//        Queue<TreeNode> queue;
        if(root == null) {
            return true;
        } else {
        	return false;
        }

        // if(root == null) {
        //     return true;
        // } else {
        //     return isSymmetricBoth(root.left, root.right);
        // }
    }
    public boolean areMirrorNodes(TreeNode root1, TreeNode root2){
    	if(root1 == null && root2 == null ) {
    		return true;
    	} else if(root1 == null || root2== null) {
    		return false;
    	} else {
    		return root1.val == root2.val;
    	}
    }
    public boolean isSymmetricBoth(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) {
            return true;
        } else if(root1 == null || root2 == null){
            return false;
        }else if(root1.val != root2.val) {
            return false;
        } else {
            return isSymmetricBoth(root1.left, root2.right) && isSymmetricBoth(root1.right, root2.left);
        }
    }
}