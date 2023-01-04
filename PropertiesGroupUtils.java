package gtu.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.tuple.Pair;

public class PropertiesGroupUtils {

    private static final Pattern PROP_KEY_PATTERN = Pattern.compile("(.*)\\_\\d+");
    private static final Pattern PROP_KEY_AND_INDEX_PATTERN = Pattern.compile("(.*)\\_(\\d+)");
    private static final int MAX_PROP_COUNT = 1000;

    Properties configProp = new Properties();
    File configFile;
    int currentIndex = 0;

    public static void main(String[] args) {
        File file = new File("C:/workspace/gtu-test-code/GTU/src/gtu/swing/util/PropertiesGroupTest.properties");
        PropertiesGroupUtils test = new PropertiesGroupUtils(file);
        System.out.println(test.loadConfig());
        test.next();
        System.out.println(test.loadConfig());

        Map<String, String> map = new HashMap<String, String>();
        map.put("user", "AAA");
        map.put("pwd", "DDD");
        map.put("OK", "DDD");

        test.saveConfig(map, Collections.EMPTY_SET);
    }

    public PropertiesGroupUtils(File configFile) {
        this.configFile = configFile;
        init();
    }

    public void init() {
        System.out.println("[configFile] : " + configFile);
        FileInputStream fis = null;
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            fis = new FileInputStream(configFile);
            configProp.load(fis);
            System.out.println("paramConfig : " + configProp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setParametersToTable(int index, Map<String, String> param) {
        for (String column : param.keySet()) {
            String columnKey = column + "_" + index;
            String value = "";
            if (param.containsKey(column)) {
                value = param.get(column);
            } else {
                System.out.println("[setParametersToTable] 找不到Key : " + column);
            }
            configProp.put(columnKey, value);
        }
    }

    private Map<String, String> getParameters(int index) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (Enumeration enu = configProp.keys(); enu.hasMoreElements();) {
            String column = (String) enu.nextElement();
            String value = configProp.getProperty(column);
            Matcher mth = PROP_KEY_PATTERN.matcher(column);
            if (mth.find()) {
                String realColum = mth.group(1);
                map.put(realColum, value);
            }
        }
        return map;
    }

    private Map<String, String> getParameters(int index, Set<String> columnNames) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String column : columnNames) {
            String columnKey = column + "_" + index;
            if (!configProp.containsKey(columnKey)) {
                return new LinkedHashMap<String, String>();
            }
            map.put(column, configProp.getProperty(columnKey));
        }
        return map;
    }

    private Set<String> loadParametersColumnNames() {
        Set<String> columnNames = new LinkedHashSet<String>();
        for (Enumeration enu = configProp.keys(); enu.hasMoreElements();) {
            String key = (String) enu.nextElement();
            Matcher mth = PROP_KEY_PATTERN.matcher(key);
            if (mth.find()) {
                String column = mth.group(1);
                columnNames.add(column);
            }
        }
        return columnNames;
    }

    private boolean isSameMap(Map<String, String> main, Map<String, String> map2, Set<String> ignoreKeys) {
        for (String key : main.keySet()) {
        	if(ignoreKeys.contains(key)) {
        		continue;
        	}
            String val = main.get(key);
            String val2 = map2.get(key);
            if (!StringUtils.equals(val, val2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 回傳-1無須儲存, 傳回int則為應該要存的index
     */
    private Integer findParameterConfigIndex(Map<String, String> currentConfig, Set<String> ignoreKeys) {
        if (currentConfig.isEmpty()) {
            return -1;// 無值無須儲存
        }
        Set<String> columnNames = loadParametersColumnNames();
        for (int ii = 0; ii < MAX_PROP_COUNT; ii++) {
            Map<String, String> param = getParameters(ii, columnNames);
            if (param.isEmpty()) {
                return ii;// 設定為空需要儲存
            }
            if (isSameMap(currentConfig, param, ignoreKeys)) {
                return ii;// 如果找到一樣的救回傳true
            } else {
                continue;// 不相同就比較下一筆
            }
        }
        throw new RuntimeException("超過範圍!");
    }

    private void savePropFile() {
        PropertiesUtil.storeProperties(configProp, configFile, DateFormatUtils.format(System.currentTimeMillis(), "yyyy/MM/dd HH:mm:ss"));
    }

    private void validateColumnNameSame(Map<String, String> currentConfig, boolean forceSave) {
        List<String> currentColumnArry = new ArrayList<String>(loadParametersColumnNames());
        if (currentColumnArry.isEmpty()) {
            return;// 若為空避掉驗證
        }
        List<String> newColumnArry = new ArrayList<String>(currentConfig.keySet());
        Collections.sort(currentColumnArry);
        Collections.sort(newColumnArry);
        if (currentColumnArry.size() != newColumnArry.size() || !currentColumnArry.equals(newColumnArry)) {
            if (!forceSave) {
                throw new RuntimeException("參數不同 \n 目前 : " + currentColumnArry + "\n新參數 : " + newColumnArry);
            } else {
                if (configFile.delete()) {
                    init();
                }
            }
        }
    }
    
    /**
     * 儲存設定
     */
    public void saveConfig(Map<String, String> currentConfig) {
        saveConfig(currentConfig, false, Collections.EMPTY_SET);
    }

    /**
     * 儲存設定
     */
    public void saveConfig(Map<String, String> currentConfig, Set<String> ignoreKeys) {
        saveConfig(currentConfig, false, ignoreKeys);
    }

    /**
     * 儲存設定
     */
    public void saveConfig(Map<String, String> currentConfig, boolean forceSave, Set<String> ignoreKeys) {
        // 比對新舊是否相同
        validateColumnNameSame(currentConfig, forceSave);

        // 判斷要儲存的index
        int saveIndex = findParameterConfigIndex(currentConfig, ignoreKeys);
        System.out.println("找到的index : " + saveIndex);
        if (saveIndex == -1) {
            saveIndex = 0;
        }
        System.out.println("儲存的index : " + saveIndex);

        // 設定到prop
        setParametersToTable(saveIndex, currentConfig);

        // 儲存設定黨
        savePropFile();
    }

    /**
     * 讀取設定黨參數
     * 
     * @return
     */
    public Map<String, String> loadConfig() {
        // 讀欄位
        Set<String> columnNames = loadParametersColumnNames();

        // 讀參數
        Map<String, String> param = null;
        if (columnNames.isEmpty()) {
            param = getParameters(currentIndex);
        } else {
            param = getParameters(currentIndex, columnNames);
        }
        System.out.println("loadConfig currentIndex : " + currentIndex);
        return param;
    }

    /**
     * 移除當前設定
     * 
     * @return
     */
    public void removeConfig() {
        // 讀欄位
        Set<String> columnNames = loadParametersColumnNames();

        Map<String, String> removeMap = new LinkedHashMap<String, String>();

        for (Enumeration enu = configProp.keys(); enu.hasMoreElements();) {
            String column = (String) enu.nextElement();
            String value = configProp.getProperty(column);
            Matcher mth = PROP_KEY_AND_INDEX_PATTERN.matcher(column);
            if (mth.find()) {
                String realColum = mth.group(1);
                int realIndex = Integer.parseInt(mth.group(2));
                if (realIndex == currentIndex) {
                    removeMap.put(column, value);
                }
            }
        }

        for (String key : removeMap.keySet()) {
            configProp.remove(key);
        }

        this.savePropFile();

        System.out.println("removeConfig currentIndex : " + currentIndex);
        System.out.println("removeConfig content : " + removeMap);
    }

    private List<Integer> getIndexRangeLst() {
        // Set<String> columnNames = loadParametersColumnNames();
        Matcher mth = null;
        List<Integer> lst = new ArrayList<Integer>();
        for (Enumeration enu = configProp.keys(); enu.hasMoreElements();) {
            String key = (String) enu.nextElement();
            mth = PROP_KEY_AND_INDEX_PATTERN.matcher(key);
            if (mth.find()) {
                int currentIndex = Integer.parseInt(mth.group(2));
                if (!lst.contains(currentIndex)) {
                    lst.add(currentIndex);
                }
            }
        }
        Collections.sort(lst);
        return lst;
    }

    /**
     * 設定下乙組
     */
    public void next() {
        // 取得index lst
        List<Integer> indicesLst = getIndexRangeLst();
        int start = indicesLst.isEmpty() ? 0 : indicesLst.get(0);
        int end = indicesLst.isEmpty() ? 0 : indicesLst.get(indicesLst.size() - 1);

        for (;;) {
            currentIndex++;
            if (indicesLst.contains(currentIndex)) {
                break;
            }
            if (currentIndex > end) {
                currentIndex = start;
                break;
            }
        }
    }

    public void clear() {
        configProp.clear();
    }
}