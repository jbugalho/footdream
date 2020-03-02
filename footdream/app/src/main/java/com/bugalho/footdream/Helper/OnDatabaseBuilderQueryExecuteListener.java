package com.bugalho.footdream.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OnDatabaseBuilderQueryExecuteListener {
    void OnGetResultHandler(Object resultSet) throws SQLException;
}
