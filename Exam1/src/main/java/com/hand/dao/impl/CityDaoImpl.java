package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.hand.dao.CityDao;
import com.hand.dataobject.City;

public class CityDaoImpl implements CityDao {

    @Override
    public void getByCuntryId(Connection connection, Integer id) throws SQLException {
        String sql = "select city_id,city from city,country "
                + "where "
                + "city.country_id = country.country_id and country.country_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getInt("city_id")+"  ");
            System.out.println(rs.getString("city")+"  ");
        }
        rs.close();
        ps.close();
    }
}
