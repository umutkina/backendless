package com.umutkina.backendlessexample.comment;

/**
 * Created by mac on 01/03/16.
 */
public class Comment
{
    private String message;
    private String authorEmail;

    public Comment()
    {}

    public Comment( String message, String authorEmail )
    {
        this.message = message;
        this.authorEmail = authorEmail;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getAuthorEmail()
    {
        return authorEmail;
    }

    public void setAuthorEmail( String authorEmail )
    {
        this.authorEmail = authorEmail;
    }
};
