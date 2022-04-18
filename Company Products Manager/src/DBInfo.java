public interface DBInfo {
 
    String DB_NAME = "jdbc:mysql://localhost/products_db";  // name of db and connecting way
    String ENCODING = "?useUnicode=yes&characterEncoding=UTF-8";  // encoding to read arabic
    String DB_NAME_WITH_ENCODING = DB_NAME + ENCODING;  // just for minimizing
    String USER = "root";  // user of db
    String PASSWORD = "";  // password
    
    String UPLOADED_FILE_PATH = "C:/Users/TRO-JAN/Desktop/FX/DB1/";  // saving pass
 
}
