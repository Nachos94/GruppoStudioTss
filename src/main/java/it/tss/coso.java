package it.tss;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

public class coso implements Initializable {

    @FXML
    private Label label;
    private Connection con;
    private TableView output;
    String customerName;
    String contactFirstName;
    String contactLastName;
    String phone;

    @FXML
    private void Stampatutto(ActionEvent event) {
        System.out.println(customerName);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DataSource db = DsFactory.getMySQLDataSource();

      try {
            con = db.getConnection();
            Statement cmd = con.createStatement();
            ResultSet q1 = cmd.executeQuery("select * from V_per_alfonso ");

            while (q1.next()) {
                customerName = q1.getString("customerName");
                contactFirstName = q1.getString("contactFirstName");
                contactLastName = q1.getString("contactLastName");
                phone = q1.getString("phone");

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "CAZZO FAI??!" + ex.getMessage());

        }
    }
}