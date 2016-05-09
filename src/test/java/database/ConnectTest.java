package database;

import org.junit.Test;

import javax.persistence.Table;

import static org.junit.Assert.*;

/**
 * Created by zcy on 2016/5/4.
 */
public class ConnectTest {
    @Test
    public void getPreparedStatementTest(){
        Connect connect = new Connect();
        String sql = "select * from indexinfo";
        assertNotNull(connect.getPreparedStatement(sql));
        connect.closeConnection();
    }

    @Test
    public void getResultSetTest(){
        Connect connect = new Connect();
        String sql = "select * from indexinfo";
        assertNotNull(connect.getResultSet(sql));
        connect.closeConnection();
    }
}