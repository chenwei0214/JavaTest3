package com.hand.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.hand.dataobject.City;

public interface CityDao {

    void getByCuntryId(Connection connection,Integer id) throws SQLException;
}
