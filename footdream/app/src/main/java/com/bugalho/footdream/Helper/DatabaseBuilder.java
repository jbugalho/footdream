package com.bugalho.footdream.Helper;

import com.bugalho.footdream.Tasks.QueryTask;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseBuilder {

    public final static String DB_HOST = "65.19.141.67";
    public final static String DB_DATABASE = "bugalho_FootDream";
    public final static String DB_USERNAME = "bugalho_admin";
    public final static String DB_PASSWORD = "bugalho123";

    private static Connection _connection;
    private QueryMode queryMode;
    private String query;

    public DatabaseBuilder(QueryMode queryMode, String query) {
        this.query = query;
        this.queryMode = queryMode;
    }

    public static Connection getInstance() {
        if (_connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                _connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_DATABASE, DB_USERNAME, DB_PASSWORD);

                if(_connection == null)
                    throw new Exception("Something went wrong - connection object is null! Check DatabaBuilder variables");
            }
            catch (Exception ex) {
                throw new Error("Unable to connect to database - check credentials and try again!");
            }
        }

        return _connection;
    }

    public void execute(OnDatabaseBuilderQueryExecuteListener onDatabaseBuilderQueryExecuteListener) {
        new QueryTask(queryMode, query, onDatabaseBuilderQueryExecuteListener).execute();
    }
}
