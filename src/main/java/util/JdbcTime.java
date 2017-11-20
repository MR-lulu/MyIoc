package util;

import java.sql.*;

/**
 * Created by blue on 2017/11/15.
 */
public class JdbcTime {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://dev.rdsmaster.cnhz.shishike.com:3306/calm_wallet_dev?useUnicode=true&characterEncoding=utf-8";

    //  Database credentials -- 数据库名和密码自己修改
    static final String USER = "dev_stf_wt";
    static final String PASS = "yk0GeXVTboygwmjymR5X";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        long begin = 0;
        int i = 0;
        int j = 0;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            begin = System.currentTimeMillis();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM checkout_bank_branch_info";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                j++;
            }
            //STEP 6: Clean-up environment
            System.out.println("Clean-up environment");
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        long end = System.currentTimeMillis();
        float time = ((end - begin))/1000f;
        System.out.println(j+"条数据的时间为："+ time+"秒");
        //System.gc();
    }//end main
}
