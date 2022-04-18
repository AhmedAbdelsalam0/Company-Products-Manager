import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common implements DBInfo {




    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);
    }



    //////////////////////////////////////////////////

    

    public static String generateImagePath(File selectedFile)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d-hh-mm-ss");
        
        String fileExtension = selectedFile.getName()
                .substring(selectedFile.getName().lastIndexOf("."));

        return UPLOADED_FILE_PATH + sdf.format(date) + fileExtension;
    }




    /////////////////////////////////////////////////////



    
    public static String saveSelectedImage(File selectedFile)
    {
        String createImagePath = Common.generateImagePath(selectedFile);
        try {
            FileInputStream in = new FileInputStream(selectedFile);

            FileOutputStream out = new FileOutputStream(createImagePath);
            
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        }
        catch(Exception e) {}
        
        return createImagePath;
    }




    /////////////////////////////////////////////////////////




    
    public static void deleteImage(String filePath)
    {
        try {
            File imageToDelete = new File(filePath);
            imageToDelete.delete();
        }
        catch(Exception e) {}
    }
    
}
