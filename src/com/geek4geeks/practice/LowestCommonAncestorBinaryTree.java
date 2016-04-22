package com.geek4geeks.practice;

public class LowestCommonAncestorBinaryTree {

	public static void main(String[] args) {

	}

}

class TreeNode {
	private TreeNode left, right;
	private int info;
	int visitedCount;

	public TreeNode(int info) {
		left = null;
		right = null;
		this.info = info;
		visitedCount = 0;
	}

	public int getInfo() {
		return info;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}
}

class Tree {

	private TreeNode root;

	public String lowestCommonAncestor(final int node1, final int node2) {
		return lca(root, node1, node2, false, false);
	}

	private String lca(final TreeNode currNode, final int node1, final int node2, final boolean isNode1Visited,
			final boolean isNode2Visited) {
		if (null == currNode) {
			return null;
		} else if (isNode1Visited == true || isNode2Visited == true) {
			if ((currNode.getInfo() == node2 && isNode1Visited == true)
					|| (currNode.getInfo() == node1 && isNode2Visited == true)) {
				return Integer.toString(currNode.getInfo());
			} else {
				String leftLCA = isNode1Visited ? lca(currNode.getLeft(), node1, node2, true, false)
						: lca(currNode.getLeft(), node1, node2, false, true);
				String rightLCA = isNode1Visited ? lca(currNode.getRight(), node1, node2, true, false)
						: lca(currNode.getRight(), node1, node2, false, true);
				if (null != leftLCA) {
					return leftLCA;
				} else if (null != rightLCA) {
					return rightLCA;
				} else {
					return null;
				}
			}
		} else if (currNode.getInfo() == node1 || currNode.getInfo() == node2) {
			String leftLCA = currNode.getInfo() == node1 ? lca(currNode.getLeft(), node1, node2, true, false)
					: lca(currNode.getLeft(), node1, node2, false, true);
			String rightLCA = currNode.getInfo() == node1 ? lca(currNode.getRight(), node1, node2, true, false)
					: lca(currNode.getLeft(), node1, node2, false, true);
			if (null != leftLCA) {
				return leftLCA;
			} else if (null != rightLCA) {
				return rightLCA;
			} else {
				return null;
			}
			// if (isPresent(node2, currNode)) {
			// return Integer.toString(currNode.getInfo());
			// } else {
			// return null;
			// }
		} else {
			String leftLCA = lca(currNode.getLeft(), node1, node2, false, false);
			String rightLCA = lca(currNode.getRight(), node1, node2, false, false);
			if (null != leftLCA) {
				return leftLCA;
			} else if (null != rightLCA) {
				return rightLCA;
			} else {
				return null;
			}
		}
	}

//	private String complexLCA(final TreeNode currNode, final int node1, final int node2, final boolean isNode1Visited,
//			final boolean isNode2Visited) {
//		
//	}
	
	public boolean isPresent(int node) {
		return false;
	}

//	private boolean isPresent(int node, TreeNode CurrNode) {
//		return false;
//	}
}