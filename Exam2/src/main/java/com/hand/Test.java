package com.hand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    static void getFilmByCustomerId(Integer customerId) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select last_name from customer where customer_id=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("last_name") + "租用的Film");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "select f.film_id,f.title,d.rental_date  " +
                "from (select film_id ,t.rental_date " +
                "from (select inventory_id,rental_date from rental WHERE customer_id=?) t," +
                "inventory i WHERE t.inventory_id=i.inventory_id) d,film f " +
                "WHERE f.film_id=d.film_id " +
                "ORDER BY d.rental_date DESC";
        try {
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

    public static void main(String[] args) {
        Test.getFilmByCustomerId(1);
    }
}
