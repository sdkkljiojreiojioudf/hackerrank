package problem.datastructures;




public class AVLTree {
    private Node root;
    private int size;

    public double getMedian(boolean even) {
        if (!even) {
            return root.key;
        }
        return -1;
    }


    /**
     * Computes the amount of child nodes in the left subtree of {@code node}.
     * Runs in constant time.
     *
     * @param  node the node whose left subtree size to compute.
     * @return the amount of child nodes in the left subtree.
     */
    private int getLeftSubtreeSize(Node node) {
        int tmp = node.childCount;

        if (node.right != null) {
            tmp -= (node.right.childCount + 1);
        }

        return tmp;
    }

    /**
     * Returns the value of {@code index}th (in order) entry. Runs in
     * logarithmic time.
     *
     * @param root  the root of the tree.
     * @param index the index of the entry whose value to get.
     * @return the value of {@code index}th value.
     */
    private Node getNode(Node root, int index) {
        Node current = root;

        for (;;) {
            int leftSubtreeSize = getLeftSubtreeSize(current);

            if (index == leftSubtreeSize) {
                return current;
            }

            if (index > leftSubtreeSize) {
                index -= (leftSubtreeSize + 1);
                current = current.right;
            } else {
                current = current.left;
            }
        }
    }

    /**
     * Computes the median of this tree. Makes at most two calls to logarithmic
     * time methods.
     *
     * @return the median of this tree.
     */
    public double getMedian() {
        if (size == 0) {
            throw new IllegalStateException(
                    "Asking for median from an empty tree.");
        }

        if (size % 2 == 0) {
            int b = getNode(root, size / 2 - 1).key;
            int a = getNode(root, size / 2).key;
            return 0.5 * (a + b);
        } else {
            return getNode(root, size / 2).key;
        }
    }



    private class Node {
        int key;
        int height;
        Node left;
        Node right;

        private int childCount;

        Node(int key) {
            this.key = key;
        }
    }



    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            ++size;
            return new Node(key);
        } else if (node.key >= key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        }
        return rebalance(node);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                size--;
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node mostLeftChild(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
        n.childCount = 0;
        if (n.left != null) {
            n.childCount = n.left.childCount + 1;
        }
        if (n.right != null) {
            n.childCount += n.right.childCount + 1;
        }
    }

    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }
}

// contributed by Arnab Kundu
