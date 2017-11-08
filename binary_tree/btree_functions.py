class Node(object):
    def __init__(self, data):
        self.right=self.left=None
        self.data = data


class BinaryTree(object):
    def insert(self, root, data):
        if root is None:
            return Node(data)
        else:
            if data <= root.data:
                cur = self.insert(root.left, data)
                root.left = cur
            else:
                cur = self.insert(root.right, data)
                root.right = cur
        return root


    def get_height(self, root):
        if root is None:
            return -1
        return 1 + max(self.get_height(root.left), self.get_height(root.right))


    def level_order_traversal(self, root):
        if root is not None:
            arr = [root]
            vals = []
            while len(arr) != 0:
                node = arr.pop(0)
                vals.append(node.data)

                if node.left is not None:
                    arr.append(node.left)
                if node.right is not None:
                    arr.append(node.right)

            return vals
        return None


if __name__ == "__main__":
    a1 = [3,5,2,1,4,6,7] # 3
    a2 = [20,50,35,44,9,15,62,11,13] # 4
    a3 = [25,39,12,19,9,23,55,31,60,35,41,70,90] # 5

    bt = BinaryTree()
    root = None
    for i in a1:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 3
    assert bt.level_order_traversal(root) == [3, 2, 5, 1, 4, 6, 7]

    bt = BinaryTree()
    root = None
    for i in a2:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 4
    assert bt.level_order_traversal(root) == [20, 9, 50, 15, 35, 62, 11, 44, 13]

    bt = BinaryTree()
    root = None
    for i in a3:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 5
    bt.level_order_traversal(root) == [25, 12, 39, 9, 19, 31, 55, 23, 35, 41, 60, 70, 90]

    print("All tests passed!")
