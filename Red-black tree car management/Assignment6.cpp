//************************************************************************
// ASU CSE310 Assignment #6 Spring 2024
// Author: Harsh Nirmal Dhiran
// ASU ID: 1225851161
// Description: this is the main program that reads input from keyboard,
// it then execute various red-black tree operations which is given in the input.
//**************************************************************************

#include "RedBlackTree.h"
#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

// This function used to get the key of a Car which is the combination of vin, model & make
void getCarKey(string oneLine, int &vin, string &model, string &make);

// This function used to get all info. of a Car
void getCarInfo(string oneLine, int &vin, string &model, string &make, double &price);

int main()
{

    int vin;
    string model, make;
    double price;
    string command, oneLine, token;
    string delimiter = ",";

    int pos = 0;

    // Create a RedBlackTree object here, use it throughout the program
    RedBlackTree redBlackTree;

    do
    {
        getline(cin, oneLine);
        pos = oneLine.find(delimiter);
        token = oneLine.substr(0, pos);
        command = token;
        oneLine.erase(0, pos + delimiter.length());

        if (command.compare("quit") == 0)
        {
            cout << "\nCommand: quit" << endl;
            // call the relevant function on the red black tree
            //----
            break;
        }
        else if (command.compare("tree_preorder") == 0)
        {
            cout << "\nCommand: tree_preorder" << endl;

            // call the relevant function on the red black tree
            redBlackTree.treePreorder();
        }
        else if (command.compare("tree_inorder") == 0)
        {
            cout << "\nCommand: tree_inorder" << endl;

            // call the relevant function on the red black tree
            redBlackTree.treeInorder();
        }
        else if (command.compare("tree_postorder") == 0)
        {
            cout << "\nCommand: tree_postorder" << endl;

            // call the relevant function on the red black tree
            redBlackTree.treePostorder();
        }
        else if (command.compare("tree_minimum") == 0)
        {
            cout << "\nCommand: tree_minimum" << endl;
            cout << "\nThe MINIMUM is:" << endl;
            // call the relevant function on the red black tree
            redBlackTree.treeMinimum();
        }
        else if (command.compare("tree_maximum") == 0)
        {
            cout << "\nCommand: tree_maximum" << endl;
            cout << "\nThe MAXIMUM is:" << endl;
            // call the relevant function on the red black tree
            redBlackTree.treeMaximum();
        }
        else if (command.compare("tree_predecessor") == 0)
        {
            // call the getCarKey function to get the Car key first
            int vin;
            string model, make;
            getCarKey(oneLine, vin, model, make);
            cout << "\nCommand: tree_predecessor" << endl;

            // call the relevant function on the red black tree
            Node *predecessor = redBlackTree.findPredecessorNode(redBlackTree.treeSearch(vin, model, make));
            if (redBlackTree.treeSearch(vin, model, make) != nullptr)
            {
                cout << vin << "  " << model << "  " << make << "  is FOUND." << endl;
            }
            if (predecessor != nullptr)
            {
                cout << "Its Predecessor is: " << endl;
                redBlackTree.print(predecessor);
            }
            else
            {
                cout << "Its Predecessor does NOT EXIST" << endl;
            }
        }
        else if (command.compare("tree_successor") == 0)
        {
            // call the getCarKey function to get the Car key first
            int vin;
            string model, make;
            getCarKey(oneLine, vin, model, make);
            cout << "\nCommand: tree_successor" << endl;

            // call the relevant function on the red black tree
            Node *successor = redBlackTree.findSuccessorNode(redBlackTree.treeSearch(vin, model, make));
            if (redBlackTree.treeSearch(vin, model, make) != nullptr)
            {
                cout << vin << "  " << model << "  " << make << "  is FOUND." << endl;
            }
            if (successor != nullptr)
            {
                cout << "Its Successor is: " << endl;
                redBlackTree.print(successor);
            }
            else
            {
                cout << "Its Successor does NOT EXIST" << endl;
            }
        }

        else if (command.compare("tree_search") == 0)
        {
            // call the getCarKey function to get the Car key first
            int vin;
            string model, make;
            getCarKey(oneLine, vin, model, make);
            cout << "\nCommand: tree_search" << endl;
            // call the relevant function on the red black tree
            if (redBlackTree.treeSearch(vin, model, make) != nullptr)
            {
                cout << vin << " " << model << " " << make << " is FOUND." << endl;
            }
            else
            {
                cout << vin << " " << model << " " << make << " is NOT FOUND." << endl;
            }
        }

        else if (command.compare("tree_insert") == 0)
        {
            // call the getCarInfo function to get the Car key first
            int vin;
            string model, make;
            double price;
            getCarInfo(oneLine, vin, model, make, price);
            cout << "\nCommand: tree_insert" << endl;
            cout << left;
            cout << setw(8) << vin
                 << setw(12) << model
                 << setw(12) << make
                 << setw(10) << fixed << setprecision(2) << price << endl;

            // call the relevant function to insert the Car
            redBlackTree.treeInsert(vin, model, make, price);
        }
    } while (true); // continue until 'quit'
    return 0;
}

//*********************************************************************************
// This function from one line, extracts the vin, model, make of a Car.
// You should already know how to extract tokens from Assignment5.cpp file
void getCarKey(string oneLine, int &vin, string &model, string &make)
{
    // Design your own code here
    string delimiter = ",";
    int pos = oneLine.find(delimiter);
    string token = oneLine.substr(0, pos);
    vin = stoi(token);
    oneLine.erase(0, pos + delimiter.length());

    pos = oneLine.find(delimiter);
    token = oneLine.substr(0, pos);
    model = token;
    oneLine.erase(0, pos + delimiter.length());

    pos = oneLine.find(delimiter);
    token = oneLine.substr(0, pos);
    make = token;
}

//************************************************************************************************
// This function from one line, extract tokens and get all info. of a Car
void getCarInfo(string oneLine, int &vin, string &model, string &make, double &price)
{
    // Design your own code here
    string delimiter = ",";
    int pos = oneLine.find(delimiter);
    string token = oneLine.substr(0, pos);
    vin = stoi(token);
    oneLine.erase(0, pos + delimiter.length());

    pos = oneLine.find(delimiter);
    token = oneLine.substr(0, pos);
    model = token;
    oneLine.erase(0, pos + delimiter.length());

    pos = oneLine.find(delimiter);
    token = oneLine.substr(0, pos);
    make = token;
    oneLine.erase(0, pos + delimiter.length());

    price = stod(oneLine);
}
