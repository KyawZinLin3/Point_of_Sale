/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import View.Point_of_Sale;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.DatabaseConnection;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import static View.Point_of_Sale.stage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.Scene;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class LoginFxmlController implements Initializable {

    @FXML
    private TextField txtuname;
    @FXML
    private RadioButton rdAdmin;
    @FXML
    private RadioButton rdCashier;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtpwd;
    @FXML
    private ToggleGroup rdtype;

    /**
     * Initializes the controller class.
     */
    Connection conn;
    public static String cashierName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            DatabaseConnection db = new DatabaseConnection();
            conn = db.getConnection();
        } catch (SQLException ex) {
            System.out.println("database not connect");
        }
    }

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException, SQLException {

        stage = View.Point_of_Sale.stage;
        Stage stage1 = new Stage();
        Parent root = null;
        if (rdAdmin.isSelected()) {
            if (txtuname.getText().equals("MST") && txtpwd.getText().equals("mst123")) {
                root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            }
        } else {
            String sql = "select * from cashier where name = ? and password =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtuname.getText());
            pst.setString(0, txtpwd.getText());
            ResultSet rs = pst.executeQuery();
            cashierName = rs.getString(1);
            if (rs.next()) {
                FXMLLoader.load(getClass().getResource("/View/Sale.fxml"));
            } else {
                JOptionPane.showMessageDialog(null, "User and Password is not matched");
            }
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }

}
