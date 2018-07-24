package com.hand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.hand.util.DBUtils;

public class Test {

    static void getFilmByCustomerId(Integer customerId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select last_name from customer where customer_id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("last_name") + "租用的Film");
            }
            sql = "select f.film_id,f.title,d.rental_date  " +
                    "from (select film_id ,t.rental_date " +
                    "from (select inventory_id,rental_date from rental WHERE customer_id=?) t," +
                    "inventory i WHERE t.inventory_id=i.inventory_id) d,film f " +
                    "WHERE f.film_id=d.film_id " +
                    "ORDER BY d.rental_date DESC";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getShort("film_id") + "  |  " + rs.getString("title") + "  |  " + rs.getTimestamp("rental_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void getCityByCountryId(Integer id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select country_id,country from country where country_id=?";
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("country_id") + " | "
                        + rs.getString("country"));
            }
            System.out.println();
            sql = "select city_id,city from city "
                    + "where "
                    + "city.country_id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("city_id") + " | "
                        + rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Map<String, String> map = System.getenv();
        String countryId = map.get("countryId");
        String customerId = map.get("customerId");
//        Test.getCityByCountryId(Integer.parseInt(countryId));
//        Test.getFilmByCustomerId(Integer.parseInt(customerId));
        Test.getCityByCountryId(1);
        Test.getFilmByCustomerId(1);

    }
}
