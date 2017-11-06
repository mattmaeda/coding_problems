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


if __name__ == "__main__":
    a1 = [3,5,2,1,4,6,7] # 3
    a2 = [20,50,35,44,9,15,62,11,13] # 4
    a3 = [25,39,12,19,9,23,55,31,60,35,41,70,90] # 5

    bt = BinaryTree()
    root = None
    for i in a1:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 3

    bt = BinaryTree()
    root = None
    for i in a2:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 4

    bt = BinaryTree()
    root = None
    for i in a3:
        root = bt.insert(root, i)
    assert bt.get_height(root) == 5

    print("All tests passed!")
