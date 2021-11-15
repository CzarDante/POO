package br.iesb.navigatorapi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataSelection

{
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NavigatorDBA","postgres", "12345");
            System.out.println("Successfully Connected.");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from public.\"player\" ;");
            while ( rs.next() ) {
                String username = rs.getString("username");
                String  token = rs.getString("Token");
                System.out.printf( "username = %s , Title = %s, ArtistId = %s ", username,token );
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println(" Data Retrieved Successfully ..");
    }
}

