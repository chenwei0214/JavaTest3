package com.hand;

import java.sql.Connection;
import java.sql.SQLException;

import com.hand.dao.CityDao;
import com.hand.dao.impl.CityDaoImpl;
import com.hand.util.DBUtils;

public class Test {
    public static void main(String[] args) {
        DBUtils dbUtils = new DBUtils();
        CityDao cityDao = new CityDaoImpl();
        Connection connection = dbUtils.getConnection();
        try {
            cityDao.getByCuntryId(connection,1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
