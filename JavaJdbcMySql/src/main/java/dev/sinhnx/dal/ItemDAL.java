package dev.sinhnx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.sinhnx.persitance.Item;

public class ItemDAL implements DAL<Item> {
    public Item getById(int itemId) {
        Item item = null;
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("select * from Items where item_id=?;");) {
            pstm.setInt(1, itemId);
            try (ResultSet rs = pstm.executeQuery();) {
                if (rs.next()) {
                    item = getItem(rs);
                }
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
        }
        return item;
    }

    public int insertItem(Item item) {
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

    public List<Item> getAll() {
        String sql = "select * from items";
        List<Item> lst = new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getItem(rs));
            }
        } catch (SQLException ex) {
            lst = null;
        }
        return lst;
    }

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
    public int insert(Item e) {
        return insertItem(e);
    }
}