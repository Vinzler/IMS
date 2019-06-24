package com.example.imsproject;

public class Idea {
    String idea;
    String type;
    String category;
    String status;
    Comment[] comments;

    public Idea(){
        idea = new String();
        type = new String();
        category = new String();
        status = new String();
        for(int i=0;i<20;i++)
        {
            comments[i] = new Comment();
        }
    }
}
