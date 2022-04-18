import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class AddNewProductDialog extends Dialog implements DBInfo {

    ImageView add_image = new ImageView(new Image(getClass().getResourceAsStream("/images/add-image.png")));
    ImageView add_product = new ImageView(new Image(getClass().getResourceAsStream("/images/add-product.png")));

    Pane pane = new Pane();
    Label nameLabel = new Label("Name");
    Label productImage = new Label("No Image Selected");
    Label priceLabel = new Label("Price ( $ )");
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    Button chooseImageButton = new Button("choose Image", add_image);
    Button addButton = new Button("Add Product", add_product);




    ObservableList data;

    SpecialAlert alert = new SpecialAlert();
    
    File selectedFile = null;


    ///////////////////////////////////////////////////////////////////////


    public AddNewProductDialog(ObservableList data)
    {


        this.data = data;

        pane.setPrefSize(610, 390);
        productImage.setPrefSize(244, 224);
        nameLabel.setPrefSize(80, 40);
        nameField.setPrefSize(270, 40);
        priceLabel.setPrefSize(80, 40);
        priceField.setPrefSize(270, 40);
        chooseImageButton.setPrefSize(274, 45);
        addButton.setPrefSize(538, 60);




        productImage.setTranslateX(36);
        productImage.setTranslateY(40);
        nameLabel.setTranslateX(300);
        nameLabel.setTranslateY(30);
        nameField.setTranslateX(300);
        nameField.setTranslateY(70);
        priceLabel.setTranslateX(300);
        priceLabel.setTranslateY(120);
        priceField.setTranslateX(300);
        priceField.setTranslateY(160);
        chooseImageButton.setTranslateX(298);
        chooseImageButton.setTranslateY(220);
        addButton.setTranslateX(34);
        addButton.setTranslateY(310);





        chooseImageButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        addButton.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        priceField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        productImage.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        nameField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        priceField.setStyle("-fx-border-color: lightgray; -fx-border-width: 2;");
        addButton.setStyle("-fx-background-color: #444; -fx-text-fill:white; -fx-cursor: hand;");
        productImage.setAlignment(Pos.CENTER);






        pane.getChildren().add(productImage);
        pane.getChildren().add(chooseImageButton);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(priceLabel);
        pane.getChildren().add(nameField);
        pane.getChildren().add(priceField);
        pane.getChildren().add(addButton);





        // this is used to let dialog close correctly
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = this.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);



        this.setTitle("Add New Product");
        this.getDialogPane().setContent(pane);  // without it , we cant see the content inside the pane
        nameField.requestFocus();






        chooseImageButton.setOnAction(Action -> {
            chooseImage();
        });

        addButton.setOnAction(Action -> {
            insertProduct();
        });


    }  // end of constructor




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    
    private void resetWindow()
    {
        nameField.setText("");
        priceField.setText("");
        productImage.setText("No image selected");
        productImage.setGraphic(null);
        selectedFile = null;
        nameField.requestFocus();
    }



    ////////////////////////////////////////////////////////////////



    public void display(boolean value)
    {
        if(value == true) {
            resetWindow();
            this.showAndWait();
        }
        else {
            this.hide();
        }
    }


    /////////////////////////////////////////////////////////////////



    private boolean checkInputs() {

        if (nameField.getText().equals("")) {
            alert.show("Required Field Is Missing!", "Please Enter Product Name", AlertType.WARNING);
            nameField.requestFocus();
            return false;
        } else if (priceField.getText().equals("")) {
            alert.show("Required Field Is Missing!", "Please Enter Product Price", AlertType.WARNING);
            priceField.requestFocus();
            return false;
        }
        /*
        else if (Double.parseDouble(priceField.getText())<0)
        {
            alert.show("Invalid Number!", "Please Enter Valid Number", AlertType.WARNING);
            priceField.requestFocus();
            return false;
        }
        // it gives me an error :(
        */
        try {
            Double.parseDouble(priceField.getText());
            return true;
        } catch (NumberFormatException ex) {
            alert.show("Invalid Input!", "Price should be a decimal number (eg: 40, 10.5)", AlertType.ERROR);
            priceField.requestFocus();
            return false;
        }


    }




    //////////////////////////////////////////////////////////////////





    private void chooseImage()
    {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter exFilter = new ExtensionFilter("Select a .JPG .PNG .GIF image" , "*.jpg", "*.png", "*.gif" );
        fileChooser.getExtensionFilters().add(exFilter);

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            Image selectedImage = new Image(selectedFile.toURI().toString() , 224 , 224 , true , true);
            ImageView img = new ImageView(selectedImage);
            try {
                productImage.setText("");
                productImage.setGraphic(img);
            }
            catch (Exception e) {
                alert.show("Error!", "Failed to add Image", AlertType.ERROR);
            }

        }

    }





    //////////////////////////////////////////////////////////////////




    private void insertProduct()
    {
        if (checkInputs()) {
            try {
                Connection con = Common.getConnection();
                
                if(con == null) {
                    alert.show("Connection Error!", "Failed to connect to database server", AlertType.ERROR);
                }

                PreparedStatement ps;

                if (selectedFile == null) {
                    ps = con.prepareStatement("INSERT INTO products(name, price, added_date) values(?,?,?)");
                }
                else {
                    String createImagePath = Common.saveSelectedImage(selectedFile);
            
                    ps = con.prepareStatement("INSERT INTO products(name, price, added_date, image_url) values(?,?,?,?)");
                    ps.setString(4, createImagePath);
                }

                ps.setString(1, nameField.getText());
                ps.setDouble(2, Double.parseDouble(priceField.getText()));

                Date date = Date.valueOf(LocalDate.now());

                ps.setDate(3, date);

                ps.executeUpdate();
                con.close();
                resetWindow();

                viewProductInTheTable();
            }
            catch (Exception e) {
                alert.show("Error!", e.getMessage(), AlertType.ERROR);
            }
        }

    }





    ////////////////////////////////////////////////////////////////////





    private void viewProductInTheTable() throws SQLException
    {
        Connection con = Common.getConnection();
        String query = "SELECT * FROM products ORDER BY ID DESC LIMIT 1";

        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            rs.next();

            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(Double.parseDouble(rs.getString("price")));
            product.setAddedDate(rs.getDate("added_date").toString());
            product.setImageUrl(rs.getString("image_url"));

            data.add(product);

            con.close();
        }
        catch (SQLException e) {
            alert.show("Error!", e.getMessage(), AlertType.ERROR);
        }

    }

}
