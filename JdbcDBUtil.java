package zTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.tuple.Triple;

import zTest.DBTypeMapping_tradevan.JdbcTypeMappingToJava;

public class JdbcDBUtil {

    public static void main(String[] args) throws SQLException {
        // Spring test
        String springConfigFile = "beans-sit2-test.xml";
        // Resource resource = new FileSystemResource(springConfigFile);
        // BeanFactory beanFactory = new XmlBeanFactory(resource);
        // DataSource dataSource = (DataSource)
        // beanFactory.getBean("dataSource");
        //
        // Connection conn = dataSource.getConnection();
        //
        // List<String> list =
        // JdbcDBUtil.queryColumnName("select * from V_AI_CO_SITE_FIND ", conn);
        // System.out.println(list);

        // Connection conn = DbConstant.getTestConnection_CTBC();
        // List<String> list = JdbcDBUtil.queryColumnName("select * from
        // [dbo].[ZT_SystemLog] ", conn);
        // System.out.println(list);

    }

    /**
     * 取得Clob
     * 
     * @param rs
     * @param index
     * @return
     */
    public static String getClob(ResultSet rs, int index) {
        Reader reader = null;
        try {
            reader = new BufferedReader(rs.getClob(index).getCharacterStream());
            StringBuffer sb = new StringBuffer();
            char[] buf = new char[1024];
            int len = 0;
            while ((len = reader.read(buf)) != -1) {
                sb.append(buf, 0, len);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static Connection getConnection(String driver, String url, String username, String password) {
        BasicDataSource dbs = new BasicDataSource();
        dbs.setDriverClassName(driver);
        dbs.setUrl(url);
        dbs.setUsername(username);
        dbs.setPassword(password);
        dbs.setMaxActive(100);
        dbs.setMinIdle(30);
        Connection con = null;
        try {
            con = dbs.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    /**
     * 要使 ResultSet 具有這樣的功能，首先 JDBC 的 Driver 必須有支援。痞子測試的環境是使用 M$ SQL 2000。<br/>
     * 使用 JTDS 0.8.1 並不支援，而使用 M$ 本身提供的 Driver 才有提供。使用上要特別注意。<br/>
     * 再者，在 create Statement 這個物件時，必須使用 createStatement(int resultSetType, int
     * resultSetConcurrency)<br/>
     * 這個 method，並在 resultSetConcurrency 這個引數傳入
     * ResultSet.CONCUR_UPDATABLE，如此才能使用此功能。<br/>
     * 另外，在取得 ResultSet 之後，可以透過 ResultSet.getConcurrency() 這個 method 得知目前的 ResultSet
     * 物件<br/>
     * 是否有支援此功能。<br/>
     * 在上面的範例中，就 update 而言，變更的動作是即時的。痞子在這裡使用 JOptionPane.showMessageDialog()
     * 來使程式暫停，<br/>
     * 可以趁暫停的空檔查看 DB 當中的資料是否也有作對應的改變。至於新增、刪除 row 的功能，ResultSet 也有提供，不過在此暫不介紹。<br/>
     * （路人：是因為你懶得測試吧... 痞子：...[逃]）<br/>
     * 這樣子的功能，並不適合應用在 JSP 上（所以之前完全不知道有這樣子的功能... [泣]）。<br/>
     * 而針對一般資料庫應用程式而言，不用組 SQL 語法、直接以目前的操作而對資料庫作變更，實在非常省時省力，<br/>
     * 寫 application 的 PR 們，是一定要熟悉這項功能的啦... :)<br/>
     * 〔測試環境〕 DBMS：M$ SQL 2000 | JDK：1.4.2 | JDBC Driver：JTDS 0.8.1, M$ JDBC Driver
     * 1.2<br/>
     * 
     * @param driver
     * @param url
     * @param user
     * @param password
     */
    private void testUpdatableResultSetTest(String driver, String url, String user, String password) {
        int tmpNum;
        try {
            // 資料庫連線
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, password);

            // 在 create statement 的時候，就要指明 ResultSet 的型態，後詳述
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE // 重點是這個引數，必須使用這個值
            );
            ResultSet rs = stmt.executeQuery("select BoardID from board");
            // 資料庫連線

            // 判斷是否有支援此功能，如果沒有就不作任何事情
            if (rs.getConcurrency() == ResultSet.CONCUR_UPDATABLE) {
                System.out.println("更新前\t+更新後");
                System.out.println("--------+-----------------");
                while (rs.next()) {
                    tmpNum = (int) (Math.random() * 10);
                    System.out.println(rs.getString("BoardID") + "\t\t|" + tmpNum);
                    rs.updateInt("BoardID", tmpNum);
                    JOptionPane.showMessageDialog(null, "程式暫停中...");
                }
            }
        } catch (Exception e) {
            System.out.println("XD... 出錯了");
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * 取得DB欄位名稱
     * 
     * @param aSql query的語法
     * @param conn 必須取得連線
     * @return 欄位名稱
     */
    public static List<String> queryColumnName(String aSql, Connection conn) {
        PreparedStatement aStmt = null;
        boolean aConnError = false;
        Date aStartTime = new Date();
        ArrayList<String> rtnArray = new ArrayList<String>();
        do {
            try {
                if (conn == null)
                    return null;
                aStmt = conn.prepareStatement(aSql);
                ResultSet rs = aStmt.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    rtnArray.add(rsmd.getColumnName(i)); // fix error
                    rtnArray.add(rsmd.getColumnLabel(i));
                }
                rs.close();
                aConnError = false;
                return rtnArray;
            } catch (Throwable e) {
                e.printStackTrace();
                aConnError = true;
                try {
                    Thread.sleep(1000);
                } catch (Throwable ex) {
                }
            } finally {
                try {
                    aStmt.close();
                } catch (Throwable e) {
                }
            }
        } while (aConnError && new Date().getTime() - aStartTime.getTime() < 60000);
        return null;
    }

    /**
     * 查詢
     * 
     * @param sql
     * @param conn
     * @return
     */
    public static List<Map<String, Object>> queryForList(String sql, Connection conn, boolean closeConn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            List<String> fieldList = queryColumnName(sql, conn);
            String[] fields = fieldList.toArray(new String[0]);

            List<Map<String, Object>> rtnArray = new ArrayList<Map<String, Object>>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rtnArray.add(getRowMap(rs, fields));
            }
            rs.close();
            return rtnArray;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
            } catch (Throwable e) {
            }
            if (closeConn) {
                try {
                    conn.close();
                } catch (Throwable e) {
                }
            }
        }
    }

    public static List<Map<String, Object>> queryForList(String sql, Object param[], Connection con, boolean isCloseConn) {
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        java.sql.ResultSet rs = null;
        System.out.println("sql : " + sql);
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            doSettingParameters(con, ps, param);

            List<String> colList = new ArrayList<String>();
            List<Class<?>> typeList = new ArrayList<Class<?>>();

            rs = ps.executeQuery();
            java.sql.ResultSetMetaData mdata = rs.getMetaData();
            int cols = mdata.getColumnCount();
            for (int i = 1; i <= cols; i++) {
//              rtnArray.add(rsmd.getColumnName(i)); // fix error
                colList.add(mdata.getColumnLabel(i));
                typeList.add(JdbcTypeMappingToJava.getMappingClass(mdata.getColumnType(i)));
            }

            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (int ii = 0; ii < colList.size(); ii++) {
                    String col = colList.get(ii);
                    Object value = null;
                    if (typeList.get(ii) == java.sql.Clob.class) {
                        value = rs.getString(ii + 1);
                    } else {
                        value = rs.getObject(ii + 1);
                    }
                    map.put(col, value);
                }
                rsList.add(map);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            if (isCloseConn) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
        return rsList;
    }

    private static String getCharStream(java.io.Reader reader1) {
        BufferedReader reader = null;
        try {
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(reader1);
            for (String line = null; (line = reader.readLine()) != null;) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException("getCharStream", ex);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public static Triple<List<String>, List<Class<?>>, List<Object[]>> queryForList_customColumns(String sql, Object param[], Connection con, boolean isCloseConn, int maxRowsLimit) throws Exception {
        List<String> colList = new ArrayList<String>();
        List<Object[]> rsList = new ArrayList<Object[]>();
        List<Class<?>> typeList = new ArrayList<Class<?>>();
        java.sql.ResultSet rs = null;
        System.out.println("sql : " + sql);
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            if (maxRowsLimit > 0) {
                ps.setMaxRows(maxRowsLimit);
            }
            doSettingParameters(con, ps, param);

            rs = ps.executeQuery();
            java.sql.ResultSetMetaData mdata = rs.getMetaData();
            int cols = mdata.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                // colList.add(mdata.getColumnName(i));
                colList.add(mdata.getColumnLabel(i));
                typeList.add(JdbcTypeMappingToJava.getMappingClass(mdata.getColumnType(i)));
            }

            A: while (rs.next()) {
                List<Object> lst = new ArrayList<Object>();
                for (int ii = 0; ii < colList.size(); ii++) {
                    String col = colList.get(ii);
                    try {
                        Object value = null;
                        if (typeList.get(ii) == java.sql.Clob.class) {
                            value = rs.getString(ii + 1);
                        } else {
                            value = rs.getObject(ii + 1);
                        }
                        lst.add(value);
                    } catch (Exception ex) {
                        String errorMsg = String.format("getColumn ERROR [%d][%s] : ", ii, col) + ex.getMessage();
                        System.out.println(errorMsg);
                        ex.printStackTrace();
                        JCommonUtil.handleException(errorMsg, ex, true, "", "yyyyMMdd.HHmm", true, false);
                        lst.add("__#ERROR#__ : " + ex.getMessage());
                    }
                }
                rsList.add(lst.toArray());

                if (maxRowsLimit > 0 && rsList.size() >= maxRowsLimit) {
                    break A;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            if (isCloseConn) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
        return Triple.of(colList, typeList, rsList);
    }

    public static void queryForList_customColumns_everyRows(String sql, Object param[], Connection con, boolean isCloseConn, int maxRowsLimit, ActionListener mActionListener) throws Exception {
        if (mActionListener == null) {
            throw new RuntimeException("必須處理 mActionListener");
        }

        List<String> colList = new ArrayList<String>();
        List<Class<?>> typeList = new ArrayList<Class<?>>();
        java.sql.ResultSet rs = null;
        System.out.println("sql : " + sql);
        try {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            con.setReadOnly(true);

            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            if (maxRowsLimit > 0) {
                ps.setMaxRows(maxRowsLimit);
            }
            doSettingParameters(con, ps, param);

            rs = ps.executeQuery();
            java.sql.ResultSetMetaData mdata = rs.getMetaData();
            int cols = mdata.getColumnCount();
            for (int i = 1; i <= cols; i++) {
//              rtnArray.add(rsmd.getColumnName(i)); // fix error
                colList.add(mdata.getColumnLabel(i));
                typeList.add(JdbcTypeMappingToJava.getMappingClass(mdata.getColumnType(i)));
            }

            long currentPosition = 0;

            A: while (rs.next()) {
                List<Object> lst = new ArrayList<Object>();
                for (int ii = 0; ii < colList.size(); ii++) {
                    String col = colList.get(ii);
                    try {
                        Object value = null;
                        if (typeList.get(ii) == java.sql.Clob.class) {
                            value = rs.getString(ii + 1);
                        } else {
                            value = rs.getObject(ii + 1);
                        }
                        lst.add(value);
                    } catch (Exception ex) {
                        String errorMsg = String.format("getColumn ERROR [%d][%s] : ", ii, col) + ex.getMessage();
                        System.out.println(errorMsg);
                        ex.printStackTrace();
                        JCommonUtil.handleException(errorMsg, ex, true, "", "yyyyMMdd.HHmm", true, false);
                        lst.add("__#ERROR#__ : " + ex.getMessage());
                    }
                }

                Object source = Triple.of(colList, typeList, lst.toArray());
                ActionEvent mActionEvent = new ActionEvent(source, -1, String.valueOf(currentPosition));
                mActionListener.actionPerformed(mActionEvent);
                currentPosition++;

                if (maxRowsLimit > 0 && (currentPosition + 1) >= maxRowsLimit) {
                    break A;
                } else if (mActionEvent.getSource() instanceof Boolean) {
                    if (((Boolean) mActionEvent.getSource()) == false) {
                        break A;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            if (isCloseConn) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     * 設定使用者
     * 
     * @throws SQLException
     */
    public static void callPSetUser(Connection con) throws SQLException {
        try {
            CallableStatement call = con.prepareCall("{call pkg_pub_app_context.P_SET_APP_USER_ID(401)}");
            call.execute();
        } catch (Throwable ex) {
            System.out.println("callPSetUser : " + ex.getMessage());
        }
    }

    public static String callFunction(String functionSql, Object[] params, Connection conn) {
        String resultString = null;
        try {
            String callSql = "{ call ? := " + functionSql + " }";

            conn.setAutoCommit(true);
            CallableStatement stmt = conn.prepareCall(callSql);
            stmt.registerOutParameter(1, java.sql.Types.NVARCHAR);

            int ii = 2;
            for (Object param : params) {
                stmt.setObject(ii, param);
                ii++;
            }

            int result = stmt.executeUpdate();
            System.out.println("updateResult : " + result);
            resultString = stmt.getString(1);
            System.out.println("Result : " + resultString);

            conn.close();
            conn = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 可以供新增修改刪除使用
     */
    public static int modify(String sql, Object param[], Connection con, boolean isCloseConn) throws Exception {
        int rsCount = 0;
        System.out.println("sql:" + sql);
        try {
            // callPSetUser(con);// 全球人壽測試用 FIXME

            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            if (param != null) {
                doSettingParameters(con, ps, param);
            }
            rsCount = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (isCloseConn) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
        return rsCount;
    }

    private static void doSettingParameters(java.sql.Connection conn, java.sql.PreparedStatement ps, Object param[]) throws SQLException {
        for (int i = 0; i < param.length; i++) {
            if (param[i] != null) {
                if (param[i].getClass().isArray()) {
                    java.sql.Array array = conn.createArrayOf("VARCHAR", (Object[]) param[i]);
                    System.out.println("param[" + i + "]:\"" + Arrays.toString((Object[]) param[i]) + "\"  (Array)");
                    ps.setArray(i + 1, array);
                } else if (param[i] instanceof String) {
                    System.out.println("param[" + i + "]:\"" + param[i] + "\"  (String)");
                    ps.setObject(i + 1, param[i]);
                } else {
                    System.out.println("param[" + i + "]:" + param[i] + "  (" + param[i].getClass().getSimpleName() + ")");
                    ps.setObject(i + 1, param[i]);
                }
            } else {
                System.out.println("param[" + i + "]:" + param[i] + "  (Null)");
                ps.setObject(i + 1, param[i]);
            }
        }
    }

    /**
     * 只供新增使用 並回傳回新增後所產生的 key 值
     */
    public static java.math.BigDecimal insert(String sql, Object param[], Connection con, boolean isCloseConn) throws Exception {
        java.math.BigDecimal key = java.math.BigDecimal.ZERO;
        System.out.println("sql:" + sql);
        java.sql.ResultSet rs = null;
        try {
            java.sql.PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            doSettingParameters(con, ps, param);

            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                key = rs.getBigDecimal(1);
                // Get automatically generated key
                // value
                System.out.println("Automatically generated key value = " + key);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;// 為了讓外面的 rollback 機制知道有錯誤發生必須把錯誤物件往外拋
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            if (isCloseConn) {
                try {
                    con.close();
                } catch (Exception ex) {
                }
            }
        }
        return key;
    }

    /**
     * 將Blob資料形態轉為String ==>若是塞資料進DB 用 Hibernate.createBlob()
     * 
     * @param contents
     * @return
     */
    public static String blobToString(java.sql.Blob contents) {
        String content_str = null;
        try {
            // org.hibernate.lob.SerializableBlob content =
            // (org.hibernate.lob.SerializableBlob) contents;
            // InputStream ins = content.getBinaryStream();
            InputStream ins = contents.getBinaryStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int pos = 0;
            while ((pos = ins.read(temp)) != -1) {
                baos.write(temp, 0, pos);
            }
            baos.close();
            ins.close();
            content_str = new String(baos.toByteArray(), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content_str;
    }

    /**
     * 印出欄位名稱與型態
     * 
     * @param rs
     */
    public static void printResultMetaData(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("Name\t\tTypeName");

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + "\t\t" + rsmd.getColumnTypeName(i)); // fix error
            	System.out.println(rsmd.getColumnLabel(i) + "\t\t" + rsmd.getColumnTypeName(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得這筆資料的Map
     * 
     * @param rs
     * @param fields
     * @return
     * @throws SQLException
     */
    public static Map<String, Object> getRowMap(ResultSet rs, String[] fields) throws SQLException {
        Map<String, Object> rtn = new HashMap<String, Object>();
        for (int ii = 0; ii < fields.length; ii++) {
            rtn.put(fields[ii], rs.getObject(fields[ii]));
        }
        return rtn;
    }

    public static List<Map<String, Object>> queryForMap(String sql, List<Object> paramList, Connection conn) throws SQLException {
        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
        java.sql.ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            for (int ii = 0; ii < paramList.size(); ii++) {
                stmt.setObject(ii + 1, paramList.get(ii));
            }

            rs = stmt.executeQuery();
            java.sql.ResultSetMetaData mdata = rs.getMetaData();
            int cols = mdata.getColumnCount();
            List<String> colList = new ArrayList<String>();
            for (int i = 1; i <= cols; i++) {
//                colList.add(mdata.getColumnName(i).toUpperCase()); // fix error
                colList.add(mdata.getColumnLabel(i));
            }

            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (String col : colList) {
                    map.put(col, rs.getObject(col));
                }
                rsList.add(map);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return rsList;
    }

    public static int executeUpdate(String sql, Object[] params, Connection conn) {
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);

            for (int ii = 0; ii < params.length; ii++) {
                stmt.setObject(ii + 1, params[ii]);
            }

            int result = stmt.executeUpdate();
            System.out.println("update rows = " + result);

            conn.commit();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBCommon_tradevan.closeConnection(null, stmt, null);
        }
    }

    public static int[] executeBatchUpdate_Simple(List<String> sqlLst, Connection conn) {
        Statement stmt = null;
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (String sql : sqlLst) {
                stmt.addBatch(sql);
            }

            int[] result = stmt.executeBatch();
            System.out.println("update rows = " + Arrays.toString(result));

            conn.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBCommon_tradevan.closeConnection(null, stmt, null);
        }
    }

    public static int[] executeBatchUpdate_Complex(String sql, List<Object[]> paramsLst, Connection conn) {
        PreparedStatement stmt = null;
        try {
            conn.setAutoCommit(false);
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);

            for (Object[] params : paramsLst) {
                for (int ii = 0; ii < params.length; ii++) {
                    stmt.setObject(ii + 1, params[ii]);
                }
                stmt.addBatch();
            }

            int[] result = stmt.executeBatch();
            System.out.println("update rows = " + Arrays.toString(result));

            conn.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBCommon_tradevan.closeConnection(null, stmt, null);
        }
    }
}
