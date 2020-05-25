package dev.sinhnx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.sinhnx.persitance.Item;

public class ItemDAL {
    public Item getById(int itemId) {
        try (Connection con = DbUtil.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("select * from Items where item_id=?;");
            pstm.setInt(1, itemId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                Item item = getItemByResultSet(rs);
                rs.close();
                pstm.close();
                return item;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public int insertItem(Item item) {
        try (Connection con = DbUtil.getConnection()) {
            PreparedStatement pstm = con
                    .prepareStatement("insert into Items(item_name, unit_price, amount, item_status) values (?,?,?,?)");
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
        try (Connection con = DbUtil.getConnection()) {
            return getItems(con.createStatement(), "select * from items");
        } catch (SQLException ex) {
            return null;
        }
    }

    private List<Item> getItems(Statement stm, String sql) throws SQLException {
        List<Item> lst = new ArrayList<>();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            lst.add(getItemByResultSet(rs));
        }
        return lst;
    }

    private Item getItemByResultSet(ResultSet rs) throws SQLException {
        Item i = new Item();
        i.setItemId(rs.getInt("item_id"));
        i.setItemName(rs.getString("item_name"));
        i.setUnitPrice(rs.getDouble("unit_price"));
        i.setAmount(rs.getInt("amount"));
        i.setItemStatus(rs.getShort("item_status"));
        i.setDescription(rs.getString("item_description"));
        return i;
    }
}