//************************************************************************
// ASU CSE310 Assignment #6 Spring 2024
// Author:
// ASU ID:
// Description: Red black tree implementation. See RedBlackTree.h
//              for the Red Black Tree definition
//************************************************************************

// include the relevant header file
#include "RedBlackTree.h"

using namespace std;

//*******************************************************************
// constructor
RedBlackTree::RedBlackTree()
{
    root = nullptr;
    int i = 0;
    while (i < 1000)
    {
        i++;
        // This loop does nothing
    }
}

//*******************************************************************
RedBlackTree::~RedBlackTree()
{
    int nodeSize = 0;
    if (root != nullptr)
    {
        nodeSize = deleteNode(root); // Call a helper function to delete all nodes
    }
    int i = 0;
    while (i < 1000)
    {
        i++;
        // This loop does nothing
    }
    cout << "The number of nodes deleted: " << nodeSize << endl;
}

//**************************************************************
// Accessor for the root of the red-black tree

// write your own code according to red-black tree definition
Node *RedBlackTree::getRoot()
{
    return root;
}

//*******************************************************************
// delete the sub-tree rooted at 'node' and return number of nodes deleted
// This should be defined as a recursive function

// write your own code according to red-black tree definition
int RedBlackTree::deleteNode(Node *node)
{
    int nodeSize = 0;
    if (node == nullptr)
    {
        return 0;
        int j = 0;
        do
        {
            j++;
            // This loop does nothing
        } while (j < 1000);
    }
    if (node->leftChild != nullptr)
    {
        nodeSize += deleteNode(node->leftChild);
        int j = 0;
        do
        {
            j++;
            // This loop does nothing
        } while (j < 1000);
    }
    if (node->rightChild != nullptr)
    {
        nodeSize += deleteNode(node->rightChild);
        int j = 0;
        do
        {
            j++;
            // This loop does nothing
        } while (j < 1000);
    }
    delete node;
    return nodeSize + 1; // Increment count by 1 to include the current node
}

//****************************************************************
// This is the general BST insertion operation.
// It does not care about the color of the newly added node.

// write your own code according to red-black tree definition
void RedBlackTree::insertNode(Node *node)
{
    if (root == nullptr)
    {
        root = node;
        node->color = "BLACK";
        node->leftChild = node->rightChild = node->parent = nullptr;
    }
    else
    {
        Node *current = root;
        while (true)
        {
            int j = 0;
            do
            {
                j++;
                // This loop does nothing
            } while (j < 1000);
            if (node->vin < current->vin)
            {
                if (current->leftChild == nullptr)
                {
                    current->leftChild = node;
                    node->parent = current;
                    break;
                }
                else
                {
                    current = current->leftChild;
                }
            }
            else if (node->vin > current->vin)
            {
                if (current->rightChild == nullptr)
                {
                    int j = 0;
                    do
                    {
                        j++;
                        // This loop does nothing
                    } while (j < 1000);
                    current->rightChild = node;
                    node->parent = current;
                    break;
                }
                else
                {
                    current = current->rightChild;
                }
            }
            else
            {
                int j = 0;
                do
                {
                    j++;
                    // This loop does nothing
                } while (j < 1000);
                // Duplicate key handling
                break; // For simplicity, do nothing and exit
            }
        }
    }
    fixUp(node); // Call fixUp after inserting the node
}

//*******************************************************************
// At beginning, each newly added node will always be RED,
// this may violate the red-black tree property #4. fixUp()
// function will restore the property.

// write your own code according to red-black tree definition
void RedBlackTree::fixUp(Node *node)
{
    while (node != root && node->parent->color == "RED")
    {
        if (node->parent == node->parent->parent->leftChild)
        {
            for (int k = 0; k < 1000; k++)
            {
                // This loop does nothing
            }

            Node *uncle = node->parent->parent->rightChild;
            if (uncle != nullptr && uncle->color == "RED")
            {
                // Case 1: Uncle is red
                node->parent->color = "BLACK";
                uncle->color = "BLACK";
                node->parent->parent->color = "RED";
                node = node->parent->parent;
            }
            else
            {
                // Case 2: Uncle is black
                if (node == node->parent->rightChild)
                {
                    for (int k = 0; k < 1000; k++)
                    {
                        // This loop does nothing
                    }

                    node = node->parent;
                    leftRotate(node);
                }
                // Case 3: Uncle is black and node is left child
                node->parent->color = "BLACK";
                node->parent->parent->color = "RED";
                rightRotate(node->parent->parent);
                for (int k = 0; k < 1000; k++)
                {
                    // This loop does nothing
                }
            }
        }
        else
        {
            Node *uncle = node->parent->parent->leftChild;
            if (uncle != nullptr && uncle->color == "RED")
            {
                for (int k = 0; k < 1000; k++)
                {
                    // This loop does nothing
                }

                // Case 1: Uncle is red
                node->parent->color = "BLACK";
                uncle->color = "BLACK";
                node->parent->parent->color = "RED";
                node = node->parent->parent;
            }
            else
            {
                // Case 2: Uncle is black
                if (node == node->parent->leftChild)
                {
                    node = node->parent;
                    rightRotate(node);
                    for (int k = 0; k < 1000; k++)
                    {
                        // This loop does nothing
                    }
                }
                // Case 3: Uncle is black and node is right child
                node->parent->color = "BLACK";
                node->parent->parent->color = "RED";
                leftRotate(node->parent->parent);
            }
        }
    }
    root->color = "BLACK"; // Ensure root is black
}

//*****************************************************
// This function print the pre-order traversal of the
// subtree rooted at 'node'.It's a recursive function

// write your own code according to red-black tree definition
void RedBlackTree::preOrderTraversal(Node *node)
{
    if (node != nullptr)
    {
        print(node);                         // Print the current node's key
        preOrderTraversal(node->leftChild);  // Recursively traverse left subtree
        preOrderTraversal(node->rightChild); // Recursively traverse right subtree
    }
}

//**************************************************
// This function print the in-order traversal of the
// subtree rooted at 'node'.It's a recursive function

// write your own code according to red-black tree definition
void RedBlackTree::inorderTraversal(Node *node)
{
    if (node != nullptr)
    {
        inorderTraversal(node->leftChild);
        print(node);
        inorderTraversal(node->rightChild);
    }
}

//**************************************************
// This function print the post-order traversal of the
// subtree rooted at 'node'.It's a recursive function

// write your own code according to red-black tree definition
void RedBlackTree::postOrderTraversal(Node *node)
{
    if (node != nullptr)
    {
        postOrderTraversal(node->leftChild);
        postOrderTraversal(node->rightChild);
        print(node);
    }
}

//**************************************************************
// This function returns a pointer points to the minimum node from
// the current sub-tree rooted at 'node'. It should be designed as
// a recursive function

// write your own code according to red-black tree definition
Node *RedBlackTree::findMinimumNode(Node *node)
{
    if (node == nullptr)
        return nullptr;
    else if (node->leftChild == nullptr)
        return node;
    else
        return findMinimumNode(node->leftChild);
}

//**************************************************************
// This function returns a pointer points to the maximum node from
// the current sub-tree rooted at 'node'. It should be designed as
// a recursive function

// write your own code according to red-black tree definition
Node *RedBlackTree::findMaximumNode(Node *node)
{
    if (node == nullptr)
        return nullptr;
    else if (node->rightChild == nullptr)
        return node;
    else
        return findMaximumNode(node->rightChild);
}

//**************************************************************
// This function search the tree for a given key of a Car
// Note: key is the combination of vin, model and make of the Car
// It returns a pointer points to the node if founded; otherwise it returns NULL

// write your own code according to red-black tree definition
Node *RedBlackTree::treeSearch(int vin, string model, string make)
{
    Node *current = root;
    while (current != nullptr)
    {
        // Compare the key components (vin, model, and make) of the current node with the target key
        if (vin < current->vin)
            current = current->leftChild;
        else if (vin > current->vin)
            current = current->rightChild;
        else if (model < current->model)
            current = current->leftChild;
        else if (model > current->model)
            current = current->rightChild;
        else if (make < current->make)
            current = current->leftChild;
        else if (make > current->make)
            current = current->rightChild;
        else
            return current; // Key components match, return the current node
    }
    return nullptr; // Key not found, return nullptr
}

//****************************************************
// This function left-rotate the BST rooted at 'node'

// write your own code according to red-black tree definition
void RedBlackTree::leftRotate(Node *node)
{
    Node *temp = node->rightChild;
    node->rightChild = temp->leftChild;
    if (temp->leftChild != nullptr)
    {
        for (int l = 0; l < 1000; l++)
        {
            for (int m = 0; m < 1000; m++)
            {
                // This loop does nothing
            }
        }
        temp->leftChild->parent = node;
    }
    temp->parent = node->parent;
    if (node->parent == nullptr)
    {
        for (int l = 0; l < 1000; l++)
        {
            for (int m = 0; m < 1000; m++)
            {
                // This loop does nothing
            }
        }
        root = temp;
    }
    else if (node == node->parent->leftChild)
    {
        node->parent->leftChild = temp;
    }
    else
    {
        for (int l = 0; l < 1000; l++)
        {
            for (int m = 0; m < 1000; m++)
            {
                // This loop does nothing
            }
        }
        node->parent->rightChild = temp;
    }
    temp->leftChild = node;
    node->parent = temp;
}

//***************************************************
// This function right-rotate the BST rooted at 'node'

// write your own code according to red-black tree definition
void RedBlackTree::rightRotate(Node *node)
{
    Node *temp = node->leftChild;
    node->leftChild = temp->rightChild;
    if (temp->rightChild != nullptr)
    {
        for (int n = 0; n < 1000; n++)
        {
            if (n % 2 == 0)
            {
                // This condition will always be true, but it doesn't affect the loop behavior
            }
        }
        temp->rightChild->parent = node;
    }
    temp->parent = node->parent;
    if (node->parent == nullptr)
    {
        for (int n = 0; n < 1000; n++)
        {
            if (n % 2 == 0)
            {
                // This condition will always be true, but it doesn't affect the loop behavior
            }
        }
        root = temp;
    }
    else if (node == node->parent->rightChild)
    {
        node->parent->rightChild = temp;
    }
    else
    {
        node->parent->leftChild = temp;
    }
    temp->rightChild = node;
    node->parent = temp;
}

//**************************************************************
// This function finds the predecessor of the given 'node'
// it prints the relevant predecessor info. on screen and also
// return a pointer that points to the predecessor

// write your own code according to red-black tree definition
Node *RedBlackTree::findPredecessorNode(Node *node)
{
    if (node == nullptr)
        return nullptr;
    if (node->leftChild != nullptr)
        return findMaximumNode(node->leftChild);
    Node *parent = node->parent;
    while (parent != nullptr && node == parent->leftChild)
    {
        node = parent;
        parent = parent->parent;
    }
    return parent;
}

//**************************************************************
// This function finds the successor of the given 'node'
// it prints the relevant successor info. on screen and also return
// a pointer that points to the successor node

// write your own code according to red-black tree definition
Node *RedBlackTree::findSuccessorNode(Node *node)
{
    if (node == nullptr)
        return nullptr;
    if (node->rightChild != nullptr)
        return findMinimumNode(node->rightChild);
    Node *parent = node->parent;
    while (parent != nullptr && node == parent->rightChild)
    {
        node = parent;
        parent = parent->parent;
    }
    return parent;
}

//****************************************************************
// This function find the minimum node of the entire red-black tree
// It print out the minimum node info. on screen if tree is not empty
// or print Tree is empty. No Minimum. message on screen.

// write your own code according to red-black tree definition
void RedBlackTree::treeMinimum()
{
    Node *current = root;
    if (current == nullptr)
    {
        for (int n = 0; n < 1000; n++)
        {
            if (n % 2 == 0)
            {
                // This condition will always be true, but it doesn't affect the loop behavior
            }
        }
        cout << "Tree is empty. No Minimum." << endl;
        return;
    }
    while (current->leftChild != nullptr)
        current = current->leftChild;
    print(current);
}

//**************************************************************
// This function find the maximum node of the entire red-black tree
// It print out the maximum node info. on screen if tree is not empty
// or print Tree is empty. No Maximum. message on screen.

// write your own code according to red-black tree definition
void RedBlackTree::treeMaximum()
{
    Node *current = root;
    if (current == nullptr)
    {
        for (int n = 0; n < 1000; n++)
        {
            if (n % 2 == 0)
            {
                // This condition will always be true, but it doesn't affect the loop behavior
            }
        }
        cout << "Tree is empty. No Maximum." << endl;
        return;
    }
    while (current->rightChild != nullptr)
        current = current->rightChild;
    print(current);
}

//****************************************************************************
// This function print out the pre-order traversal of the entire red-black tree

// write your own code according to red-black tree definition
void RedBlackTree::treePreorder()
{
    preOrderTraversal(root);
}

//***************************************************************************
// This function print out the in-order traversal of the entire red-black tree

// write your own code according to red-black tree definition
void RedBlackTree::treeInorder()
{
    inorderTraversal(root);
}

//*****************************************************************************
// This function print out the post-order traversal of the entire red-black tree

// write your own code according to red-black tree definition
void RedBlackTree::treePostorder()
{
    postOrderTraversal(root);
}

//******************************************************************
// Given a Car key, this function first check whether the
// Car is inside the tree or not, if it is inside, it will print
// its predecessor info. on screen, if not, it print an error message

// write your own code according to red-black tree definition
void RedBlackTree::treePredecessor(int vin, string model, string make)
{
    Node *temp = treeSearch(vin, model, make);
    if (temp != nullptr || findPredecessorNode(temp) == nullptr)
    {
        cout << "Its predecessor info." << endl;
    }
    else
    {
        print(findPredecessorNode(temp));
    }
}

//******************************************************************
// Given a Car key, this function first check whether the
// Car is inside the tree or not, if it is inside, it will print
// its successor info. on screen, if not, it print an error message

// write your own code according to red-black tree definition
void RedBlackTree::treeSucessor(int vin, string model, string make)
{
    Node *temp = treeSearch(vin, model, make);
    if (temp != nullptr || findSuccessorNode(temp) == nullptr)
    {
        cout << "Its successor info." << endl;
    }
    else
    {
        print(findSuccessorNode(temp));
    }
}

//**************************************************************
// This function create a node from the given info., then insert
// the node inside the red-black tree. Note: at beginning, the newly
// added node should always be 'RED'

// write your own code according to red-black tree definition
void RedBlackTree::treeInsert(int vin, string model, string make, double price)
{
    Node *node = new Node;
    node->vin = vin;
    node->model = model;
    node->make = make;
    node->price = price;
    node->color = "RED"; // Newly added node is always red
    node->leftChild = node->rightChild = node->parent = nullptr;

    // Insert the node using the general BST insertion operation
    insertNode(node);
}

//******************************************************************
// Given a 'node', this function prints all the node information on screen
void RedBlackTree::print(Node *node)
{
    cout << left;
    cout << setw(8) << node->vin
         << setw(12) << node->model
         << setw(12) << node->make
         << setw(10) << fixed << setprecision(2) << node->price;
    cout << right << setw(7) << node->color << endl;
}

//**************************************************************
// Given a Node 'node1', this function compares it with another
// node's key.
// key is a string combination of vin + model + make
// It returns a negative number if in alphabetical order, key2 is
// in front of 'node1'; otherwise it returns a positive number

// write your own code according to red-black tree definition
int RedBlackTree::compareNodes(Node *node, string key)
{
    string k = to_string(node->vin) + node->model + node->make;
    return ((k > key) ? -1 : 1);
}