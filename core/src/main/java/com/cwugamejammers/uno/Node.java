//Beta Build: 5/3

package com.cwugamejammers.uno;

public class Node {

    //id stores the player id
    Player num;

    //next and prev store the next and previous nodes
    Node prev;
    Node next;


    // This constructor makes a new node
    // Next and prev are default initialized as null
    public Node(Player x)
    {
        num = x;
    }

    public void setNext(Node node)
    {
        next = node;
    }

    public void setPrev(Node node)
    {
        this.prev = node;
    }

    public Node getNext()
    {
        return this.next;
    }

    public Node getPrev()
    {
        return this.prev;
    }

    public Player getNum()
    {
        return num;
    }
}

