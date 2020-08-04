package dev.sinhnx.threelayer.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.sinhnx.threelayer.persitance.Item;

class ItemDAL implements DAL<Item> {

    private Item getItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setItemName(rs.getString("item_name"));
        item.setUnitPrice(rs.getDouble("unit_price"));
        item.setAmount(rs.getInt("amount"));
        item.setItemStatus(rs.getShort("item_status"));
        item.setDescription(rs.getString("item_description"));
        return item;
    }

    @Override
    public int insert(Item item) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement(
                        "insert into Items(item_name, unit_price, amount, item_status) values (?,?,?,?)");) {
            pstm.setString(1, item.getItemName());
            pstm.setDouble(2, item.getUnitPrice());
            pstm.setInt(3, item.getAmount());
            pstm.setShort(4, item.getItemStatus());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public List<Item> search(Item item) {
        String sql = "";
        if (item == null) {
            sql = "select * from items";
        } else if (item.getItemName() != null && !item.getItemName().equals("")) {
            sql = "select * from items where item_name='" + item.getItemName() + "'";
        }
        return getItemsList(sql);
    }

    @Override
    public Item getById(Item item) {
        Item result = null;
        if (item == null)
            return result;
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("select * from Items where item_id=?;");) {
            pstm.setInt(1, item.getItemId());
            try (ResultSet rs = pstm.executeQuery();) {
                if (rs.next()) {
                    result = getItem(rs);
                }
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
        }
        return result;
    }

    @Override
    public List<Item> search(String whereClause) {
        String sql = "select * from items where " + whereClause;
        return getItemsList(sql);
    }

    private List<Item> getItemsList(String sql) {
        List<Item> lst = new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getItem(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            lst = null;
        }
        return lst;
    }
}