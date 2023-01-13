package gtu._work.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Highlighter.Highlight;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import au.com.bytecode.opencsv.CSVReader;
import gtu._work.ui.FastDBQueryUI_XlsColumnDefLoader.XlsColumnDefClz;
import gtu._work.ui.FastDBQueryUI_XlsColumnDefLoader.XlsColumnDefType;
import gtu._work.ui.JMenuBarUtil.JMenuAppender;
import gtu.binary.Base64JdkUtil;
import gtu.binary.StringUtil4FullChar_4NotePad;
import gtu.clipboard.ClipboardUtil;
import gtu.collection.ListUtil;
import gtu.collection.MapUtil;
import gtu.date.DateUtil;
import gtu.db.ExternalJDBCDriverJarLoader;
import gtu.db.JdbcDBUtil;
import gtu.db.jdbc.util.DBDateUtil.DBDateFormat;
import gtu.db.sqlMaker.DbSqlCreater;
import gtu.db.sqlMaker.DbSqlCreater.TableInfo;
import gtu.file.FileUtil;
import gtu.file.OsInfoUtil;
import gtu.keyboard_mouse.JnativehookKeyboardMouseHelper;
import gtu.log.LoggerAppender;
import gtu.number.RandomUtil;
import gtu.poi.hssf.ExcelColorCreater;
import gtu.poi.hssf.ExcelUtil_Xls97;
import gtu.poi.hssf.ExcelWriter;
import gtu.poi.hssf.ExcelWriter.CellStyleHandler;
import gtu.properties.PropertiesGroupUtils;
import gtu.properties.PropertiesGroupUtils_ByKey;
import gtu.properties.PropertiesMultiUtil;
import gtu.properties.PropertiesUtil;
import gtu.properties.PropertiesUtilBean;
import gtu.spring.SimilarityUtil;
import gtu.string.StringNumberUtil;
import gtu.string.StringUtilForDb;
import gtu.string.StringUtil_;
import gtu.swing.util.AutoComboBox;
import gtu.swing.util.AutoComboBox.MatchType;
import gtu.swing.util.HideInSystemTrayHelper;
import gtu.swing.util.JButtonGroupUtil;
import gtu.swing.util.JColorUtil;
import gtu.swing.util.JComboBoxUtil;
import gtu.swing.util.JCommonUtil;
import gtu.swing.util.JCommonUtil.HandleDocumentEvent;
import gtu.swing.util.JFrameRGBColorPanel;
import gtu.swing.util.JFrameUtil;
import gtu.swing.util.JListUtil;
import gtu.swing.util.JMouseEventUtil;
import gtu.swing.util.JPopupMenuUtil;
import gtu.swing.util.JProgressBarHelper;
import gtu.swing.util.JTabbedPaneUtil;
import gtu.swing.util.JTableUtil;
import gtu.swing.util.JTableUtil.ColumnSearchFilter;
import gtu.swing.util.JTableUtil.JTooltipTable;
import gtu.swing.util.JTextAreaUtil;
import gtu.swing.util.JTextFieldUtil;
import gtu.swing.util.JTextFieldUtil.JTextComponentSelectPositionHandler;
import gtu.swing.util.JTextUndoUtil;
import gtu.swing.util.JTooltipUtil;
import gtu.swing.util.KeyEventExecuteHandler;
import gtu.swing.util.KeyEventUtil;
import gtu.swing.util.S2T_And_T2S_EventHandler;
import gtu.swing.util.SimpleTextDlg;
import gtu.swing.util.SimpleTextDlg_Ver2;
import gtu.swing.util.SwingTabTemplateUI;
import gtu.swing.util.SwingTabTemplateUI.ChangeTabHandlerGtu001;
import gtu.swing.util.SwingTabTemplateUI.CloneTabInterfaceGtu001;
import gtu.thread.util.ThreadUtil;
import gtu.yaml.util.YamlMapUtil;
import gtu.yaml.util.YamlUtilBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class FastDBQueryUI extends JFrame {

    private static final String QUERY_RESULT_COLUMN_NO = "No.";

    private static final long serialVersionUID = 1L;

    public static File JAR_PATH_FILE = PropertiesUtil.getJarCurrentPath(FastDBQueryUI.class);
    static {
        if (!PropertiesUtil.isClassInJar(FastDBQueryUI.class)) {
            if (OsInfoUtil.isWindows()) {
                JAR_PATH_FILE = new File("D:\\my_tool\\FastDBQueryUI");
            } else if (OsInfoUtil.isLinux()) {
                JAR_PATH_FILE = new File("/media/gtu001/OLD_D/my_tool/FastDBQueryUI");
            } else if (OsInfoUtil.isMac()) {
                JAR_PATH_FILE = new File("/Users/user/Desktop/my_tool/FastDBQueryUI");
            }
        }
    }

    static {
        System.setProperty("db2.jcc.charsetDecoderEncoder", "3");
    }

    private static File sqlIdListFile = new File(JAR_PATH_FILE, "sqlList.properties");
    private static final File sqlIdListDSMappingFile = new File(JAR_PATH_FILE, "sqlList_DS_Mapping.properties");
    private SqlIdConfigBeanHandler sqlIdConfigBeanHandler;
    private SqlIdListDSMappingHandler sqlIdListDSMappingHandler;
    private SqlParameterConfigLoadHandler sqlParameterConfigLoadHandler = new SqlParameterConfigLoadHandler();
    private SqlIdColumnHolder mSqlIdColumnHolder = new SqlIdColumnHolder();
    public LoggerAppender updateLogger = new LoggerAppender(new File(JAR_PATH_FILE, "updateLog_" + DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd") + ".txt"));
    private static final String SQL_PARAM_PTN = "\\:([a-zA-Z]\\w*)";
    private static final int SQL_PARAM_PTN_LENGTH = 2;
    private static PropertiesGroupUtils_ByKey dataSourceConfig = new PropertiesGroupUtils_ByKey(new File(JAR_PATH_FILE, "dataSource.properties"));
    private static PropertiesUtilBean defaultConfig = new PropertiesUtilBean(JAR_PATH_FILE, FastDBQueryUI.class.getSimpleName() + "_default");

    private JPanel contentPane;
    private JList sqlList;
    private JButton sqlSaveButton;
    private JTextArea sqlTextArea;
    private JTextField sqlIdText;
    private JButton clearButton;
    private JScrollPane scrollPane_1;
    private JTable parametersTable;
    private JPanel panel_5;
    private JTooltipTable queryResultTable;
    private JPanel panel_6;
    private JTextField dbUrlText;
    private JTextField dbUserText;
    private JTextField dbPwdText;
    private JTextField dbDriverText;
    private JLabel lblUrl;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JButton saveConnectionBtn;
    private JPanel panel_7;

    private JButton nextParameterBtn;
    private JButton nextConnBtn;
    private JComboBox dbNameIdText;
    private AutoComboBox dbNameIdText_Auto;
    private AutoComboBox sqlMappingFilterText_Auto;
    private AutoComboBox refSearchCategoryCombobox_Auto;

    private JLabel lblDbName;
    private JTextField sqlQueryText;
    private JPanel panel_8;
    private JPanel panel_9;
    private JPanel panel_10;
    private JPanel panel_11;
    private JTextArea queryResultJsonTextArea;
    private JPanel panel_12;
    private JPanel panel_13;
    private JPanel panel_14;
    private JPanel panel_15;
    private JPanel panel_16;
    private JPanel newPanel1;

    private Triple<List<String>, List<Class<?>>, List<Object[]>> queryList = null;
    private Triple<List<String>, List<Class<?>>, List<Object[]>> filterRowsQueryList = null;

    private boolean distinctHasClicked = false;// 是否按過distinct btn

    private JButton excelExportBtn;
    private JRadioButton radio_import_excel;
    private JRadioButton radio_export_excel;
    private JRadioButton radio_export_json;
    private JRadioButton radio_import_clipboard;

    private ButtonGroup btnExcelBtn;
    private JLabel label;
    private JTextField columnFilterText;

    private boolean isResetQuery = true;// 是否重新查詢
    private JPanel panel_17;
    private JButton removeConnectionBtn;
    private JButton loadDBConfigFileBtn;
    private JLabel lblNewLabel_3;
    private JTextField rowFilterText;
    private JButton distinctQueryBtn;
    private JLabel queryResultCountLabel;
    private JButton deleteParameterBtn;
    private JButton saveParameterTableBtn;
    private JButton importYamlConfigBtn;

    private static AtomicReference<ExternalJDBCDriverJarLoader> externalJDBCDriverJarLoader = new AtomicReference<ExternalJDBCDriverJarLoader>();
    private static AtomicReference<JFrameRGBColorPanel> jFrameRGBColorPanel = new AtomicReference<JFrameRGBColorPanel>();
    private static AtomicReference<HideInSystemTrayHelper> hideInSystemTrayHelper = new AtomicReference<HideInSystemTrayHelper>();

    private JButton prevConnBtn;
    private JLabel lblNewLabel_4;
    private JTextField sqlContentFilterText;
    private JLabel lblNewLabel_5;

    private String importExcelSheetName;// 匯入目前的sheet name
    private JButton connTestBtn;
    private JPanel panel_18;
    private JPanel panel_19;
    private JPanel panel_20;
    private JPanel panel_21;
    private JPanel panel_22;
    private JLabel lblNewLabel_6;
    private JTextField refSearchText;
    private JLabel lblNewLabel_7;
    private JTextArea refContentArea;
    private JList refSearchList;
    private RefSearchListConfigHandler refSearchListConfigHandler;
    private JButton refContentConfigSaveBtn;
    private JButton refContentConfigClearBtn;
    private JComboBox refSearchCategoryCombobox;
    private JButton refSearchColorComboBtn;
    private JTextField refConfigPathText;
    private JLabel lbl_config_etc;
    private JPanel panel_23;
    private JButton saveEtcConfigBtn;
    private EtcConfigHandler etcConfigHandler;
    private JPanel panel_24;

    private static SwingTabTemplateUI TAB_UI1;
    private JLabel lblDb;
    private JComboBox sqlMappingFilterText;
    private JButton sqlFilterClearBtn;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JComboBox sqlIdCategoryComboBox;
    private AutoComboBox sqlIdCategoryComboBox_Auto;
    private JLabel lblNewLabel_10;
    private JButton exportYamlConfigBtn;
    private JButton sqlIdFixNameBtn;
    protected SqlIdConfigBean sqlBean;// 當前選則
    private JTextField maxRowsText;
    private JButton executeSqlButton;
    private JLabel label_1;
    private JLabel lbl4SqlTextAreaInfo;
    private JRadioButton updateSqlRadio;
    private JRadioButton querySqlRadio;
    private JButton executeSqlButton2;
    private JButton refConfigPathYamlExportBtn;
    private JTabbedPane tabbedPane;
    private JTextArea sqlParamCommentArea;
    private JTextArea sqlIdCommentArea;
    private EditColumnHistoryHandler editColumnHistoryHandler;
    private JLabel lblNewLabel_11;
    private JLabel queryResultTimeLbl;
    private JLabel lblNewLabel_13;
    private JLabel lblNewLabel_12;
    private JScrollPane sqlTextAreaScroll;
    private JTextComponentSelectPositionHandler mSqlTextAreaJTextAreaSelectPositionHandler;
    private SqlTextAreaPromptHandler mSqlTextAreaPromptHandler;
    private JComboBox tableColumnDefText;
    private AutoComboBox tableColumnDefText_Auto;
    private JButton tableColumnConfigBtn;
    private JLabel lblNewLabel_14;

    private SearchAndReplace mSearchAndReplace = new SearchAndReplace();
    private JPanel panel_25;
    private static final String ICO_FILENAME = "janna_cute_bird.ico";// "big_boobs.ico";//"Pig_SC.ico"
    private JButton setFontSizeBtn;
    private JComboBox sqlPageDbConnCombox;
    private JPanel panel_26;
    private JLabel lblNewLabel_15;
    private JLabel lblNewLabel_16;
    private JTextField compareBeforeXlsText;
    private JTextField compareAfterXlsText;
    private JPanel panel_27;
    private JButton compareXlsExecuteBtn;
    private JButton compareXlsClearBtn;
    private JButton compareTwoTableBtn;
    private JLabel lblNewLabel_17;
    private JTextField compareXlsMiddleNameText;
    private JTextField compareXlsColumnSettingTitleText;
    protected TableColumnDefTextHandler mTableColumnDefTextHandler;
    private JPanel panel_28;
    private JPanel panel_29;
    private JPanel panel_30;
    private JPanel panel_31;
    private JPanel panel_32;
    private JButton tableColumnConfigBtn2;
    private JTextField columnXlsDefTableQryText;
    private JLabel lblNewLabel_18;
    private JTable columnXlsDefTableColumnQryTable;
    private JButton clearParameterBtn;
    private AtomicReference<FastDBQueryUI_RecordWatcher> mRecordWatcher = new AtomicReference<FastDBQueryUI_RecordWatcher>();
    private JButton recordWatcherToggleBtn;
    private JCheckBox rowFilterTextKeepMatchChk;
    private JButton resetQueryBtn;
    private XlsColumnDefDlg mXlsColumnDefDlg;
    private JTextField columnXlsDefColumnQryText;
    private JTextField columnXlsDefOtherQryText;
    private JCheckBox columnXlsDefShowChineseChk;
    private JLabel label_2;
    private JLabel lblNewLabel_19;
    private JLabel columnXlsDefFindRowCountLbl;
    private JCheckBox radio_export_excel_ignoreNull;
    private UndoSaveHanlder mUndoSaveHanlder;
    private FastDBQueryUI_RowPKSettingDlg mFastDBQueryUI_RowDiffWatcherDlg;
    private FastDBQueryUI_RowCompareDlg_Ver2 mFastDBQueryUI_RowCompareDlg_Ver2;
    private JLabel lblNewLabel_20;
    private JComboBox sqlIdCategoryComboBox4Tab1;
    private AutoComboBox sqlIdCategoryComboBox4Tab1_Auto;
    private JLabel lblNewLabel_21;
    private FastDBQueryUI_TwoTableDlgUI mFastDBQueryUI_TwoTableDlgUI;
    private DBNameIdTextHandler mDBNameIdTextHandler;
    private SqlIdExecuteTypeHandler mSqlIdExecuteTypeHandler;
    private JButton sqlIdColorButton;
    private JCheckBox radio_import_excel_isAppend;
    public AtomicReference<String> currentSQL = new AtomicReference<String>();
    private JCheckBox recordWatcherToggleAutoChk;
    private FastDBQueryUI_ReserveSqlDlg mFastDBQueryUI_ReserveSqlDlg;
    private JComboBox sqlListSortCombobox;
    private JLabel lblNewLabel_22;
    private AtomicReference<ColumnSearchFilter> columnFilterHolder = new AtomicReference<ColumnSearchFilter>();
    private static AllTabPageProcess mAllPageProcess;
    private static final String QUERY_RESULT_POOL_KEY = "queryResultPool";
    private JTextField columnXlsDefLblColorQryText;
    private JLabel lblNewLabel_23;
    private JCheckBox queryResultFakeDataChk;
    private AtomicBoolean InitLoadSqlListConfigHolder = new AtomicBoolean(false);
    private AtomicBoolean executeSqlButtonClickHolder = new AtomicBoolean(false);
    private FastDBQueryUI_RefCodeTableDlg mFastDBQueryUI_RefCodeTableDlg;
    private JButton codeTableConfigBtn;
    private FastDBQueryUI_SQLTransparentDlg mFastDBQueryUI_SQLTransparentDlg = null;
    private TextAreaHighLighterProcess mTextAreaHighLighterProcess = null;
    private QuoteMarkController mQuoteMarkController = null;
    private int defaultFontSize = 14;// 系統預設字型大小
    private FastDBQueryUI_CommonToolsDlg mFastDBQueryUI_SimpleSqlMapperDlg = null;
    private JRadioButton radio_export_csv;
    private AtomicReference<JProgressBarHelper> executeSqlButtonClickProg = new AtomicReference<JProgressBarHelper>();
    private static final Integer EXCEL_MAX_ROW_SIZE = 65535;
    private FastDBQueryUI_CrudDlgUI mFastDBQueryUI_CrudDlgUI;

    private final Predicate IGNORE_PREDICT = new Predicate() {
        @Override
        public boolean evaluate(Object input) {
            Class<?>[] igs = new Class[] { JTextField.class, JTextArea.class };
            for (Class<?> c : igs) {
                if (input.getClass() == c) {
                    return true;
                }
            }
            return false;
        }
    };

    private static final ActionListener loadingInfoListener_DEFAULT = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        }
    };
    private static ActionListener loadingInfoListener = loadingInfoListener_DEFAULT;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        System.out.println("start...");
        /*
         * EventQueue.invokeLater(new Runnable() { public void run() { try {
         * FastDBQueryUI frame = new FastDBQueryUI();
         * gtu.swing.util.JFrameUtil.setVisible(true, frame); } catch (Exception
         * e) { e.printStackTrace(); } } });
         */
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            System.out.println("=====" + info.getClassName());
            // javax.swing.UIManager.setLookAndFeel(info.getClassName());
        }
        final SwingTabTemplateUI tabUI = SwingTabTemplateUI.newInstance(null, ICO_FILENAME, FastDBQueryUI.class, true, new SwingTabTemplateUI.SwingTabTemplateUI_Callback() {
            @Override
            public void beforeInit(SwingTabTemplateUI self) {
                if (jFrameRGBColorPanel.get() == null) {
                    jFrameRGBColorPanel.set(new JFrameRGBColorPanel(self.getJframe()));
                }
                if (hideInSystemTrayHelper.get() == null) {
                    hideInSystemTrayHelper.set(HideInSystemTrayHelper.newInstance());
                    hideInSystemTrayHelper.get().apply(self.getJframe());
                }
            }

            @Override
            public void afterInit(SwingTabTemplateUI self) {
                loadExternalJars();
                initApplyAppMenu(self);
            }
        });
        tabUI.setEventAfterChangeTab(new ChangeTabHandlerGtu001() {
            public void afterChangeTab(int tabIndex, List<JFrame> jframeKeeperLst) {
                if (jframeKeeperLst != null && !jframeKeeperLst.isEmpty()) {
                    ((FastDBQueryUI) jframeKeeperLst.get(tabIndex)).reloadAllProperties();
                    ((FastDBQueryUI) jframeKeeperLst.get(tabIndex)).moveTabToQueryResultIfHasRecords();
                }
            }
        });
        tabUI.setCloneTabInterface(new CloneTabInterfaceGtu001() {
            @Override
            public boolean cloneTab(JFrame cloneFromFrame, JFrame cloneToFrame) {
                FastDBQueryUI cloneFromFrame1 = (FastDBQueryUI) cloneFromFrame;
                FastDBQueryUI cloneToFrame1 = (FastDBQueryUI) cloneToFrame;
                SqlIdConfigBean sqlBean1 = new SqlIdConfigBean();
                sqlBean1.category = cloneFromFrame1.sqlIdCategoryComboBox_Auto.getTextComponent().getText();
                sqlBean1.sql = cloneFromFrame1.sqlTextArea.getText();
                sqlBean1.sqlComment = cloneFromFrame1.sqlIdCommentArea.getText();
                sqlBean1.sqlId = cloneFromFrame1.sqlIdText.getText();
                if (StringUtils.isBlank(sqlBean1.sqlId)) {
                    sqlBean1.sqlId = "未命名";
                }
                cloneToFrame1.sqlIdText.setText(sqlBean1.sqlId);
                if (StringUtils.isNotBlank(sqlBean1.sql)) {
                    cloneToFrame1.sqlTextArea.setText(sqlBean1.sql);
                }
                if (cloneFromFrame1.sqlBean != null) {
                    sqlBean1.color = cloneFromFrame1.sqlBean.color;
                }
                // ------------------------------------------------------------------------

                cloneToFrame1.sqlListMouseClicked(null, sqlBean1);
                cloneToFrame1.sqlQueryText.setText(cloneFromFrame1.sqlQueryText.getText());
                cloneToFrame1.sqlContentFilterText.setText(cloneFromFrame1.sqlContentFilterText.getText());
                Map<String, String> params = new ParameterHandler(cloneFromFrame1.parametersTable).getParameters();
                new ParameterHandler(cloneToFrame1.parametersTable).restoreParameters(params);
                JComboBoxUtil.newInstance(cloneToFrame1.sqlIdCategoryComboBox4Tab1).setSelectedItem(cloneFromFrame1.sqlIdCategoryComboBox4Tab1.getSelectedItem());
                JComboBoxUtil.newInstance(cloneToFrame1.sqlListSortCombobox).setSelectedItem(cloneFromFrame1.sqlListSortCombobox.getSelectedItem());
                JComboBoxUtil.newInstance(cloneToFrame1.sqlMappingFilterText).setSelectedItem(cloneFromFrame1.sqlMappingFilterText.getSelectedItem());
                cloneToFrame1.columnFilterText.setText(cloneFromFrame1.columnFilterText.getText());
                cloneToFrame1.rowFilterText.setText(cloneFromFrame1.rowFilterText.getText());
                // ------------------------------------------------------------------------
                return true;
            }
        });
        tabUI.setWindowCloseEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultConfig.getConfigProp().put("frame_w", String.valueOf(tabUI.getJframe().getBounds().width));
                defaultConfig.getConfigProp().put("frame_h", String.valueOf(tabUI.getJframe().getBounds().height));
                defaultConfig.getConfigProp().put("frame_x", String.valueOf(tabUI.getJframe().getBounds().x));
                defaultConfig.getConfigProp().put("frame_y", String.valueOf(tabUI.getJframe().getBounds().y));
                defaultConfig.store();
            }
        });
        if (defaultConfig.getConfigProp().containsKey("frame_w") && defaultConfig.getConfigProp().containsKey("frame_h")) {
            int w = Integer.parseInt(defaultConfig.getConfigProp().getProperty("frame_w"));
            int h = Integer.parseInt(defaultConfig.getConfigProp().getProperty("frame_h"));
            tabUI.setSize(w, h);
        } else {
            java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            tabUI.setSize((int) (scr_size.width * 0.8), (int) (scr_size.height * 0.8));
        }
        if (defaultConfig.getConfigProp().containsKey("frame_x") && defaultConfig.getConfigProp().containsKey("frame_y")) {
            int x = Integer.parseInt(defaultConfig.getConfigProp().getProperty("frame_x"));
            int y = Integer.parseInt(defaultConfig.getConfigProp().getProperty("frame_y"));
            tabUI.setPosition(x, y);
        }
        
        tabUI.getJframe().addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent paramWindowEvent) {
                //縮小還原
                if(paramWindowEvent.getOldState() == 1 && paramWindowEvent.getNewState() == 0) {
                    for(JFrame f1 : tabUI.getJframeKeeperLst()) {
                        FastDBQueryUI f2 = (FastDBQueryUI)f1;
                        if(f2.mFastDBQueryUI_CrudDlgUI != null) {
                            JCommonUtil.setFrameAtop(f2.mFastDBQueryUI_CrudDlgUI, false);
                        }
                    }
                    //縮小
                } else if(paramWindowEvent.getOldState() == 0 && paramWindowEvent.getNewState() == 1) {
                    for(JFrame f1 : tabUI.getJframeKeeperLst()) {
                        FastDBQueryUI f2 = (FastDBQueryUI)f1;
                        if(f2.mFastDBQueryUI_CrudDlgUI != null) {
                            f2.mFastDBQueryUI_CrudDlgUI.setVisible(false);
                        }
                    }
                }
            }
        });

        tabUI.startUI();
        tabUI.getSysTrayUtil().createDefaultTray();
        TAB_UI1 = tabUI;
    }

    /**
     * Create the frame.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public FastDBQueryUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        java.awt.Dimension scr_size = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, (int) (scr_size.width * 0.8), (int) (scr_size.height * 0.8));
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tabbedPane.getSelectedIndex()) {
                case 3:
                    // InitLoadSqlListConfigHolder.set(true);
                    break;
                case 4: // 查詢結果JSON
                    showJsonArry_Ver2(Integer.MAX_VALUE, null);
                default:
                    // InitLoadSqlListConfigHolder.set(false);
                    break;
                }
            }
        });

        JPanel panel = new JPanel();
        tabbedPane.addTab("Sql列表", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        sqlList = new JListUtil.JList4FixToolTip();
        sqlList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final SqlIdConfigBean sqlBean = JListUtil.getLeadSelectionObject(sqlList);
                final List<SqlIdConfigBean> sqlBeanLst = JListUtil.getLeadSelectionArry(sqlList);
                if (sqlBeanLst.size() == 1) {
                    sqlListMouseClicked(e, null);
                }

                if (JMouseEventUtil.buttonRightClick(1, e)) {
                    JPopupMenuUtil popInst = JPopupMenuUtil.newInstance(sqlList);//

                    if (sqlBean != null) {
                        popInst.addJMenuItem("刪除", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                deleteSqlIdConfigBean(sqlBean);
                            }
                        });
                    }
                    popInst.addJMenuItem("匯入SQL", new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            File file = JCommonUtil._jFileChooser_selectFileOnly();
                            if (file == null || !file.exists()) {
                                JCommonUtil._jOptionPane_showMessageDialog_error("檔案必須為yml or properties");
                                return;
                            }
                            sqlListImportSQLConfig(file);
                        }
                    });

                    if (!sqlBeanLst.isEmpty()) {
                        popInst.addJMenuItem("修改類別", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String categoryName = JCommonUtil._jOptionPane_showInputDialog("請輸入群組", "");
                                if (StringUtils.isBlank(categoryName)) {
                                    JCommonUtil._jOptionPane_showMessageDialog_error("未執行修改！");
                                    return;
                                }
                                boolean changeConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("確認修改" + sqlBeanLst.size() + "項目為:" + categoryName, categoryName);
                                if (!changeConfirm) {
                                    JCommonUtil._jOptionPane_showMessageDialog_error("未執行修改！");
                                    return;
                                }
                                sqlIdConfigBeanHandler.saveFixCategory(sqlBeanLst, categoryName);
                                JCommonUtil._jOptionPane_showMessageDialog_error("修改為：" + categoryName);
                            }
                        });
                    }

                    popInst.applyEvent(e).show();
                }
            }
        });
        sqlList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                sqlListKeyPressAction(e);
            }
        });

        JCommonUtil.applyDropFiles(sqlList, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<File> lst = (List<File>) e.getSource();
                    if (!lst.isEmpty()) {
                        File file = lst.get(0);
                        sqlListImportSQLConfig(file);
                    }
                } catch (Exception e1) {
                    JCommonUtil.handleException(e1);
                }
            }
        });

        JListUtil.newInstance(sqlList).applyOnHoverEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlIdConfigBean sqlBean = (SqlIdConfigBean) e.getSource();
                sqlList.setToolTipText(StringUtils.trimToNull(sqlBean.sqlComment));
            }
        });
        JListUtil.newInstance(sqlList).applyOnHoverEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlIdConfigBean bean = (SqlIdConfigBean) e.getSource();
                String comment = "<font color=\"red\">" + bean.sqlComment + "</font><br/>";
                String tooltip = "<html>" + comment + JTooltipUtil.escapeHtml(bean.sql) + "</html>";
                if (!StringUtils.equals(sqlList.getToolTipText(), tooltip)) {
                    sqlList.setToolTipText(tooltip);
                }
            }
        });

        scrollPane.setViewportView(sqlList);

        newPanel1 = new JPanel();
        sqlQueryText = new JTextField();
        sqlQueryText.setToolTipText("SQL ID標籤過濾");
        sqlQueryText.setColumns(10);

        lblNewLabel_22 = new JLabel("排序");
        newPanel1.add(lblNewLabel_22);

        sqlListSortCombobox = new JComboBox();
        sqlListSortCombobox.setModel(SqlListSortCombobox_SortEnum.getModel());
        newPanel1.add(sqlListSortCombobox);
        sqlListSortCombobox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    initLoadSqlListConfig(null);
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        lblNewLabel_21 = new JLabel("類別過濾");
        newPanel1.add(lblNewLabel_21);

        sqlIdCategoryComboBox4Tab1 = new JComboBox();
        JComboBoxUtil.newInstance(sqlIdCategoryComboBox4Tab1).setWidth(100);
        sqlIdCategoryComboBox4Tab1_Auto = AutoComboBox.applyAutoComboBox(sqlIdCategoryComboBox4Tab1);
        newPanel1.add(sqlIdCategoryComboBox4Tab1);

        lblNewLabel_4 = new JLabel("SQL ID過濾");
        newPanel1.add(lblNewLabel_4);
        newPanel1.add(sqlQueryText);

        panel.add(newPanel1, BorderLayout.NORTH);

        lblNewLabel_5 = new JLabel("SQL與欄位過濾");
        newPanel1.add(lblNewLabel_5);

        sqlContentFilterText = new JTextField();
        sqlContentFilterText.setColumns(10);
        sqlContentFilterText.setToolTipText("SQL內所包含文字以及所含欄位過濾");

        newPanel1.add(sqlContentFilterText);

        lblDb = new JLabel("DB名稱過濾");
        newPanel1.add(lblDb);

        sqlMappingFilterText = new JComboBox();
        JComboBoxUtil.newInstance(sqlMappingFilterText).setWidth(100);
        sqlMappingFilterText.setToolTipText("SQL ID標籤過濾");
        // dbNameIdText.setColumns(10);
        sqlMappingFilterText_Auto = AutoComboBox.applyAutoComboBox(sqlMappingFilterText);
        sqlMappingFilterText_Auto.setMatchType(MatchType.Contains);

        newPanel1.add(sqlMappingFilterText);

        for (final JTextComponent text : new JTextComponent[] { sqlQueryText, sqlContentFilterText, sqlMappingFilterText_Auto.getTextComponent(),
                sqlIdCategoryComboBox4Tab1_Auto.getTextComponent() }) {
            text.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    try {
                        // 初始化 sqlList
                        if (sqlIdConfigBeanHandler != null) {
                            sqlIdConfigBeanHandler.setRegisterComponent(text);
                        }
                        initLoadSqlListConfig(null);
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });
            text.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {

                @Override
                public void process(DocumentEvent event) {
                    try {
                        // 初始化 sqlList
                        if (sqlIdConfigBeanHandler != null) {
                            sqlIdConfigBeanHandler.setRegisterComponent(text);
                        }
                        Boolean forceExecute = null;
                        // if (text ==
                        // sqlIdCategoryComboBox4Tab1_Auto.getTextComponent()) {
                        // forceExecute = true;
                        // }
                        initLoadSqlListConfig(forceExecute);
                    } catch (Exception e) {
                        JCommonUtil.handleException(e);
                    }
                }
            }));
        }

        sqlIdCategoryComboBox4Tab1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 初始化 sqlList
                    if (sqlIdConfigBeanHandler != null) {
                        sqlIdConfigBeanHandler.setRegisterComponent(sqlIdCategoryComboBox4Tab1_Auto.getTextComponent());
                    }
                    initLoadSqlListConfig(null);
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        sqlFilterClearBtn = new JButton("清除");
        sqlFilterClearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1) {
                sqlQueryText.setText("");
                sqlContentFilterText.setText("");
                sqlMappingFilterText_Auto.setSelectItemAndText("");
                try {
                    // 初始化 sqlList
                    initLoadSqlListConfig(null);
                } catch (Exception e) {
                    JCommonUtil.handleException(e);
                }
            }
        });

        newPanel1.add(sqlFilterClearBtn);
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("SQL", null, panel_2, null);
        panel_2.setLayout(new BorderLayout(0, 0));

        sqlTextArea = new JTextArea();// sqlTextAreaDoc
        sqlTextArea.setFocusTraversalKeysEnabled(false);

        // JTextAreaUtil.applyCommonSetting(sqlTextArea, false);
        {
            if (true) {
                JTextAreaUtil.applyFont(sqlTextArea);
            }
            JTextUndoUtil.applyUndoProcess2(sqlTextArea);
        }

        sqlTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sqlTextAreaMouseClickedAction(e);
                sqlTextAreaHighLighter();
            }
        });

        sqlTextArea.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                // checkSelectOrUpdateRadioButton();
            }
        });
        sqlTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                boolean isConsume = false;
                if (KeyEventUtil.isMaskKeyPress(e, "c") && e.getKeyCode() == KeyEvent.VK_S) {
                    JCommonUtil.triggerButtonActionPerformed(sqlSaveButton);
                } else if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ENTER && mSqlTextAreaPromptHandler != null) {
                    isConsume = mSqlTextAreaPromptHandler.performSelectTopColumn(e);
                    if (!isConsume) {
                        JTextAreaUtil.triggerTabKey(sqlTextArea, e);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && mSqlTextAreaPromptHandler != null) {
                    isConsume = mSqlTextAreaPromptHandler.performSelectClose();
                } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN && mSqlTextAreaPromptHandler != null) {
                    isConsume = mSqlTextAreaPromptHandler.performSelectUpDown(e);
                } else if (KeyEventUtil.isMaskKeyPress(e, "c") && e.getKeyCode() == KeyEvent.VK_F && mSearchAndReplace != null) {
                    isConsume = mSearchAndReplace.findKey();
                } else if (KeyEventUtil.isMaskKeyPress(e, "s") && e.getKeyCode() == KeyEvent.VK_F3 && mSearchAndReplace != null) {
                    isConsume = mSearchAndReplace.findNext(false);
                } else if (e.getKeyCode() == KeyEvent.VK_F3 && mSearchAndReplace != null) {
                    isConsume = mSearchAndReplace.findNext(true);
                } else if (KeyEventUtil.isMaskKeyPress(e, "c") && e.getKeyCode() == KeyEvent.VK_H && mSearchAndReplace != null) {
                    isConsume = mSearchAndReplace.replaceAll();
                } else if (!isConsume && mSqlTextAreaPromptHandler != null) {
                    mSqlTextAreaPromptHandler.performUpdateLocation();
                    isConsume = mSqlTextAreaPromptHandler.checkPopupListFocus(e);
                }

                sqlTextAreaHighLighter();

                if (isConsume) {
                    e.consume();
                    System.out.println("-----Consume");
                }
            }
        });
        sqlTextArea.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
            @Override
            public void process(DocumentEvent event) {
                sqlTextAreaChange();
                sqlTextAreaPromptProcess("insertUpdate", event);
                sqlTextAreaHighLighter();
            }
        }));

        sqlTextArea.addMouseMotionListener(new MouseMotionAdapter() {
            private String getChinese(String column) {
                if (mTableColumnDefTextHandler != null) {
                    return mTableColumnDefTextHandler.getChinese(column, null);
                }
                return null;
            }

            private void showParagraph(int caretPos) {
                String text = StringUtils.defaultString(sqlTextArea.getText());
                Pattern ptn = Pattern.compile("\\w+", Pattern.DOTALL | Pattern.MULTILINE);
                Matcher mth = ptn.matcher(text);
                while (mth.find()) {
                    if (mth.start() <= caretPos && mth.end() >= caretPos) {
                        sqlTextArea.setToolTipText(getChinese(mth.group()));
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int caretPosition = sqlTextArea.viewToModel(e.getPoint());
                showParagraph(caretPosition);
            }
        });

        sqlTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                sqlTextAreaFocusLost();
            }
        });

        JCommonUtil.applyDropFiles(sqlTextArea, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<File> lst = (List<File>) e.getSource();
                if (!lst.isEmpty()) {
                    String content = FileUtil.loadFromFile(lst.get(0), "UTF8");
                    sqlTextArea.setText(content);
                }
            }
        });
        // DefaultCaret caret = (DefaultCaret)sqlTextArea.getCaret();
        // caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JTextAreaUtil.applyEnterKeyFixPosition(sqlTextArea);
        JTextAreaUtil.applyCloneLine(sqlTextArea);

        mSqlTextAreaJTextAreaSelectPositionHandler = JTextComponentSelectPositionHandler.newInst(sqlTextArea);

        sqlTextAreaScroll = JTextAreaUtil.createLineNumberWrap(sqlTextArea);// JCommonUtil.createScrollComponent(sqlTextArea);
        panel_2.add(sqlTextAreaScroll);

        JPanel sqlIdPanel = new JPanel();
        lblNewLabel_10 = new JLabel("顏色");
        sqlIdPanel.add(lblNewLabel_10);

        sqlIdColorButton = new JButton("色");
        sqlIdColorButtonChangeColor(Color.BLACK);
        sqlIdColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color newColor = JCommonUtil._JColorChooser_showDialog(sqlIdColorButton.getBackground());
                if (newColor != null) {
                    if (sqlBean != null) {
                        sqlIdColorButtonChangeColor(newColor);
                        sqlTextAreaChange();
                    }
                }
            }
        });
        sqlIdPanel.add(sqlIdColorButton);

        lblNewLabel_9 = new JLabel("類別");
        sqlIdPanel.add(lblNewLabel_9);

        sqlIdCategoryComboBox = new JComboBox();
        JComboBoxUtil.newInstance(sqlIdCategoryComboBox).setWidth(100);
        sqlIdCategoryComboBox_Auto = AutoComboBox.applyAutoComboBox(sqlIdCategoryComboBox);
        sqlIdPanel.add(sqlIdCategoryComboBox);
        sqlIdCategoryComboBox_Auto.getTextComponent().getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
            @Override
            public void process(DocumentEvent event) {
                sqlTextAreaChange();
            }
        }));

        lblNewLabel_8 = new JLabel("SQL ID");
        sqlIdPanel.add(lblNewLabel_8);
        sqlIdText = new JTextField();
        sqlIdPanel.add(sqlIdText);
        sqlIdText.setToolTipText("設定SQL ID");
        sqlIdText.setColumns(30);
        sqlIdText.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
            @Override
            public void process(DocumentEvent event) {
                sqlTextAreaChange();
            }
        }));

        {// 多包一層
            JPanel innerPanel1 = new JPanel();
            innerPanel1.setLayout(new BorderLayout(0, 0));
            innerPanel1.add(sqlIdPanel, BorderLayout.NORTH);

            {// 多包一層
                JPanel innerPanel11 = new JPanel();

                sqlIdCommentArea = new JTextArea();
                sqlIdCommentArea.setToolTipText("SQL註解");
                JTextAreaUtil.applyCommonSetting(sqlIdCommentArea, false);
                sqlIdCommentArea.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && //
                        e.getKeyCode() == KeyEvent.VK_S) {
                            JCommonUtil.triggerButtonActionPerformed(sqlSaveButton);
                        }
                    }
                });
                sqlIdCommentArea.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
                    @Override
                    public void process(DocumentEvent event) {
                        sqlTextAreaChange();
                    }
                }));

                innerPanel1.add(innerPanel11, BorderLayout.CENTER);
                innerPanel11.setLayout(new FormLayout(
                        new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
                                FormFactory.RELATED_GAP_COLSPEC, },
                        new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, }));

                lblNewLabel_11 = new JLabel("註解");
                innerPanel11.add(lblNewLabel_11, "2, 2");

                innerPanel11.add(sqlIdCommentArea, "4, 2, fill, fill");
            }

            panel_2.add(innerPanel1, BorderLayout.NORTH);
        }

        sqlIdFixNameBtn = new JButton("選擇功能");
        sqlIdFixNameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPopupMenuUtil.newInstance(sqlIdFixNameBtn)//
                        .addJMenuItem("改名", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                sqlIdFixNameBtnAction("rename");
                            }
                        })//
                        .addJMenuItem("複製", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                sqlIdFixNameBtnAction("clone");
                            }
                        })//
                        .addJMenuItem("刪除", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                deleteSqlIdConfigBean(getCurrentEditSqlIdConfigBean());
                            }
                        })//
                        .addJMenuItem("還原", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                sqlListMouseClicked(null, null);
                            }
                        })//
                        .applyEvent(e)//
                        .show();
            }
        });

        sqlPageDbConnCombox = new JComboBox();
        sqlPageDbConnCombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dbNameIdText_Auto.setSelectItemAndText(String.valueOf(sqlPageDbConnCombox.getSelectedItem()));
                JCommonUtil.triggerButtonActionPerformed(dbNameIdText);

                // 設定新開視窗預設值
                if (TAB_UI1 != null) {
                    TAB_UI1.getResourcesPool().put("sqlPageDbConnCombox", sqlPageDbConnCombox.getSelectedItem());
                }
            }
        });

        sqlIdPanel.add(sqlPageDbConnCombox);
        sqlIdPanel.add(sqlIdFixNameBtn);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(new BorderLayout(0, 0));
        panel_2.add(panel_3, BorderLayout.SOUTH);

        {
            panel_25 = new JPanel();
            panel_3.add(panel_25, BorderLayout.SOUTH);
            panel_3.add(panel_25);

            lbl4SqlTextAreaInfo = new JLabel("");
            panel_25.add(lbl4SqlTextAreaInfo);

            label_1 = new JLabel("max rows :");
            panel_25.add(label_1);

            maxRowsText = new JTextField();
            maxRowsText.setToolTipText("設定最大筆數,小於等於0則無限制");
            maxRowsText.setText("1000");
            maxRowsText.setColumns(5);
            panel_25.add(maxRowsText);

            sqlSaveButton = new JButton("儲存");
            sqlSaveButton.setToolTipText("快速鍵 Ctrl+S");
            panel_25.add(sqlSaveButton);

            clearButton = new JButton("清除");
            clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clearButtonClick();
                }
            });
            panel_25.add(clearButton);

            querySqlRadio = new JRadioButton("查詢");
            querySqlRadio.setSelected(true);
            panel_25.add(querySqlRadio);

            updateSqlRadio = new JRadioButton("修改");

            panel_25.add(updateSqlRadio);

            executeSqlButton = new JButton("執行Sql");
            executeSqlButton.setToolTipText("快速鍵 F5");
            executeSqlButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    executeSqlButtonClick();
                }
            });

            updateBatchSqlRadio = new JRadioButton("批量修改");
            panel_25.add(updateBatchSqlRadio);
            panel_25.add(executeSqlButton);

            JButtonGroupUtil.createRadioButtonGroup(querySqlRadio, updateSqlRadio, updateBatchSqlRadio);
        }

        sqlSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSqlButtonClick(true, true);
            }
        });

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("SQL參數", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        scrollPane_1 = new JScrollPane();

        JPanel innerPanel2 = new JPanel();
        innerPanel2.setLayout(new BorderLayout(0, 0));
        innerPanel2.add(scrollPane_1, BorderLayout.CENTER);

        JTextAreaUtil.applyTextAreaPosition(sqlTextArea, lbl4SqlTextAreaInfo);

        {// 多包一層
            JPanel innerPanel11 = new JPanel();

            sqlParamCommentArea = new JTextArea();
            sqlParamCommentArea.setToolTipText("SQL參數註解");
            JTextAreaUtil.applyCommonSetting(sqlParamCommentArea, false);

            innerPanel11.setLayout(new FormLayout(
                    new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
                            FormFactory.RELATED_GAP_COLSPEC, },
                    new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, }));

            lblNewLabel_12 = new JLabel("註解");
            innerPanel11.add(lblNewLabel_12, "2, 2");
            innerPanel11.add(sqlParamCommentArea, "4, 2, fill, fill");

            innerPanel2.add(innerPanel11, BorderLayout.NORTH);
        }

        {// 多包一層
            JPanel innerPanel11 = new JPanel();

            innerPanel11.setLayout(new FormLayout(
                    new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
                            FormFactory.RELATED_GAP_COLSPEC, },
                    new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, }));

            innerPanel2.add(innerPanel11, BorderLayout.SOUTH);

            lblNewLabel_13 = new JLabel("選填項目用中括號\"[]\"表示, 參數用 :paramKey 表示, 注入式SQL用 _#sqlKey#_ 表示");
            lblNewLabel_13.setForeground(Color.RED);
            innerPanel11.add(lblNewLabel_13, "4, 2");
        }

        panel_1.add(innerPanel2, BorderLayout.CENTER);

        parametersTable = new JTable();
        parametersTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = parametersTable.columnAtPoint(e.getPoint());
                final String name = parametersTable.getColumnName(col);
                System.out.println("Column index selected " + col + " " + name);

                if (JMouseEventUtil.buttonRightClick(1, e)) {
                    JPopupMenuUtil.newInstance(parametersTable)//
                            .addJMenuItem("複製欄位", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    List<String> lst = new ArrayList<String>();
                                    // 取選擇
                                    int[] rows = parametersTable.getSelectedRows();
                                    if (rows != null && rows.length > 0) {
                                        for (int ii = 0; ii < rows.length; ii++) {
                                            int row = JTableUtil.getRealRowPos(rows[ii], parametersTable);
                                            int col = JTableUtil.getRealColumnPos(0, parametersTable);
                                            lst.add((String) parametersTable.getValueAt(row, col));
                                        }
                                    }
                                    // 取全部
                                    if (lst.isEmpty()) {
                                        for (int row = 0; row < parametersTable.getRowCount(); row++) {
                                            int col = JTableUtil.getRealColumnPos(0, parametersTable);
                                            lst.add((String) parametersTable.getValueAt(row, col));
                                        }
                                    }
                                    SimpleTextDlg.newInstance(StringUtils.join(lst, "^"), "", null).show();
                                }
                            })//
                            .applyEvent(e)//
                            .show();
                }
            }
        });

        parametersTable.addMouseListener(new MouseAdapter() {

            Pattern ptn = Pattern.compile("[\\w\\-\\:\\/]+\\s\\d{2}\\:\\d{2}\\:\\d{2}|[\\w\\-\\:\\/]+|\\w+");

            private void updateColumnParameter(List<String> params) {
                DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
                A: for (int ii = 0; ii < model.getRowCount(); ii++) {
                    String column = (String) model.getValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                    int pos = ListUtil.indexOfIgnorecase(column, params);
                    if (pos != -1) {
                        for (int jj = 0; jj < params.size(); jj++) {
                            if (jj > pos) {
                                if (params.get(jj) != null && !"null".equals(params.get(jj))) {
                                    model.setValueAt(params.get(jj), ii, ParameterTableColumnDef.VALUE.idx);
                                    break A;
                                }
                            }
                        }
                    }
                }
            }

            private void updateColumnParameters() {
                BufferedReader reader = null;
                Matcher mth = null;
                try {
                    String content = ClipboardUtil.getInstance().getContents();
                    reader = new BufferedReader(new StringReader(content));
                    for (String line = null; (line = reader.readLine()) != null;) {
                        List<String> lst = new ArrayList<String>();
                        mth = ptn.matcher(line);
                        while (mth.find()) {
                            String word = StringUtils.trimToEmpty(mth.group());
                            if (StringUtils.isNotBlank(word)) {
                                lst.add(word);
                            }
                        }
                        updateColumnParameter(lst);
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (JMouseEventUtil.buttonRightClick(1, e)) {
                    JPopupMenuUtil.newInstance(parametersTable)//
                            .addJMenuItem("從剪貼簿貼上", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    updateColumnParameters();
                                }
                            })//
                            .addJMenuItem("顯示查詢SQL", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    getShowAfterCurrentSQL(true);
                                }
                            })//
                            .addJMenuItem("顯示查詢SQL(快速)", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String sql = getShowAfterCurrentSQL(false);
                                    sql = StringUtil_.trimAllSpace_Ver2(sql);
                                    SimpleTextDlg.newInstance(sql, "", null).show();
                                }
                            })//
                            .addJMenuItem("以ToString()設定參數", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    new ToStringReplaceParameterTable().execute("clipboard");
                                }
                            })//
                            .addJMenuItem("以ToString()設定參數[垂]", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    new ToStringReplaceParameterTable().execute("clipboard_vertical");
                                }
                            })//

                            .applyEvent(e)//
                            .show();
                }
            }
        });

        JTableUtil.newInstance(parametersTable).applyOnHoverEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pair<Integer, Integer> pair = (Pair<Integer, Integer>) e.getSource();
                int row = pair.getLeft();
                int col = pair.getRight();
                String column = (String) parametersTable.getValueAt(row, ParameterTableColumnDef.COLUMN.idx);
                SqlParam bean = parseSqlToParam(sqlBean.sql);
                if (bean instanceof SqlParam_IfExists) {
                    SqlParam_IfExists bean2 = (SqlParam_IfExists) bean;
                    if (bean2.paramSetSentanceMap.containsKey(column)) {
                        String sentance = bean2.paramSetSentanceMap.get(column);
                        parametersTable.setToolTipText("<html>" + JTooltipUtil.escapeHtml(sentance) + "</html>");
                        return;
                    }
                }
                parametersTable.setToolTipText(null);
            }
        });

        scrollPane_1.setViewportView(parametersTable);

        JPanel panel_4 = new JPanel();
        panel_1.add(panel_4, BorderLayout.SOUTH);

        panel_5 = new JPanel();
        tabbedPane.addTab("查詢結果", null, panel_5, null);
        panel_5.setLayout(new BorderLayout(0, 0));

        queryResultTable = new JTooltipTable();
        JTableUtil.newInstance(queryResultTable).columnIsJTextComponent(null, new JTextArea());
        queryResultTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode() + "..." + KeyEvent.VK_ENTER);
                if (e.getKeyCode() == KeyEvent.VK_ENTER && queryResultTable.getSelectedRowCount() > 0) {
                    Component source = queryResultTable;
                    int id = KeyEvent.KEY_PRESSED;
                    long when = System.currentTimeMillis();
                    int modifiers = 0;
                    int x = 0;
                    int y = 0;
                    int clickCount = 2;
                    boolean popupTrigger = false;
                    int button = MouseEvent.BUTTON1;
                    MouseEvent e2 = new MouseEvent(source, id, when, modifiers, x, y, clickCount, popupTrigger, button);
                    queryResultTableMouseClickAction(e2);
                }
            }
        });
        queryResultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                queryResultTableMouseClickAction(e);
            }
        });
        queryResultTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int col = queryResultTable.columnAtPoint(e.getPoint());
                final String name = queryResultTable.getColumnName(col);
                System.out.println("Column index selected " + col + " " + name);

                if (JMouseEventUtil.buttonRightClick(1, e)) {
                    JPopupMenuUtil popUtil = JPopupMenuUtil.newInstance(queryResultTable);//
                    popUtil.addJMenuItem("複製 : " + name, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final String SQL = getCurrentSQL();
                            String alias = getAliasFromSql(SQL, name);
                            if (StringUtils.isNotBlank(alias)) {
                                alias = alias + ".";
                            }
                            ClipboardUtil.getInstance().setContents(alias + name);
                        }
                    }).addJMenuItem("修改欄位 : " + name, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                String newName = JCommonUtil._jOptionPane_showInputDialog("輸入column名稱", name);
                                boolean fixOk = false;
                                if (queryList != null) {
                                    List<String> titles = queryList.getLeft();
                                    if (StringUtils.equals(titles.get(col), name)) {
                                        titles.set(col, newName);
                                        fixOk = true;
                                    }
                                }
                                if (fixOk) {
                                    JTableUtil.newInstance(queryResultTable).setColumTitle(col, newName);
                                }
                            } catch (Exception ex) {
                                JCommonUtil.handleException(ex);
                            }
                        }
                    }).addJMenuItem("SQL 加入標籤 : " + name, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final int colIndex = col;
                            final String columnName = name;
                            String sql = StringUtils.defaultString(sqlTextArea.getText().toString());
                            TitleSetLabel mTitleSetLabel = new TitleSetLabel();
                            boolean fixOk = mTitleSetLabel.fixOneTry(colIndex, columnName, sql);
                            if (!fixOk) {
                                mTitleSetLabel.fixTwoTry(colIndex, columnName, sql);
                            }
                            if (!fixOk) {
                                JCommonUtil._jOptionPane_showMessageDialog_error("找不到欄位!");
                            }
                        }
                    }).addJMenuItem(getShowAllColumnMenu())//
                            .addJMenuItem("Sql Column IN (...) [distinct]", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    doSetColumnSqlInProcess(name, true);
                                }
                            });
                    if (mTableColumnDefTextHandler != null) {
                        List<String> pkLst = mTableColumnDefTextHandler.getPkLst(true);
                        if (CollectionUtils.isNotEmpty(pkLst)) {
                            popUtil.addJMenuItem("參考excel設定PK", new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String pkMsg = StringUtils.join(mTableColumnDefTextHandler.getPkLst(true), "\r\n");
                                    SimpleTextDlg.newInstance(pkMsg, "", null).show();
                                }
                            });
                        }
                        popUtil.addJMenuItem("參考excel設定select欄位", new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String selectColumnString = mTableColumnDefTextHandler.getSelectColumns();
                                SimpleTextDlg.newInstance(selectColumnString, "", null).show();
                            }
                        });
                    }
                    popUtil.applyEvent(e).show();
                }
            }
        });

        panel_12 = new JPanel();

        panel_5.add(panel_12, BorderLayout.CENTER);
        panel_12.setLayout(new BorderLayout(0, 0));

        panel_13 = new JPanel();
        panel_12.add(panel_13, BorderLayout.NORTH);

        distinctQueryBtn = new JButton("Distinct");
        distinctQueryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                distinctQueryBtnActionTotal();
            }
        });

        lblNewLabel_14 = new JLabel();
        panel_13.add(lblNewLabel_14);

        codeTableConfigBtn = new JButton("<html><font color=Blue>CT</font></html>");
        codeTableConfigBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mFastDBQueryUI_RefCodeTableDlg == null) {
                    mFastDBQueryUI_RefCodeTableDlg = new FastDBQueryUI_RefCodeTableDlg();
                }
                if (mFastDBQueryUI_RefCodeTableDlg != null && mFastDBQueryUI_RefCodeTableDlg.isVisible()) {
                    mFastDBQueryUI_RefCodeTableDlg.dispose();
                }
                mFastDBQueryUI_RefCodeTableDlg.show();
            }
        });

        panel_13.add(codeTableConfigBtn);

        queryResultFakeDataChk = new JCheckBox("");
        queryResultFakeDataChk.setToolTipText("查無資料時使用假資料!");
        queryResultFakeDataChk.setSelected(false);
        panel_13.add(queryResultFakeDataChk);

        tableColumnDefText = new JComboBox();
        JComboBoxUtil.newInstance(tableColumnDefText).setWidth(100);
        tableColumnDefText_Auto = AutoComboBox.applyAutoComboBox(tableColumnDefText);
        panel_13.add(tableColumnDefText);

        tableColumnConfigBtn = new JButton("設定");
        panel_13.add(tableColumnConfigBtn);
        tableColumnConfigBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableColumnConfigBtnAction();
            }
        });

        mTableColumnDefTextHandler = new TableColumnDefTextHandler();
        tableColumnDefText_Auto.getTextComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                mTableColumnDefTextHandler.action(false);
            }
        });

        tableColumnDefText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mTableColumnDefTextHandler.action(false);
                updateRecordWatcherChineseMap();
            }
        });

        queryResultCountLabel = new JLabel("          ");
        panel_13.add(queryResultCountLabel);
        panel_13.add(distinctQueryBtn);

        label = new JLabel("欄位過濾");
        panel_13.add(label);

        columnFilterText = new JTextField();
        columnFilterText.setToolTipText("多欄位用\"^\"分隔");
        panel_13.add(columnFilterText);
        columnFilterText.setColumns(10);
        columnFilterText.setToolTipText("分隔符號為\"^\"");

        columnFilterText.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
            ColumnSearchFilter columnFilter;

            @Override
            public void process(DocumentEvent event) {
                try {
                    // if (checkIsNeedResetQueryResultTable(true)) {
                    // return;
                    // }
                    // if (distinctHasClicked) {
                    // queryModeProcess(queryList, true, null, null);
                    // distinctHasClicked = false;
                    // }
                    // if (columnFilter == null || isResetQuery) {
                    // columnFilter = new ColumnSearchFilter(queryResultTable,
                    // "^",
                    // new Object[] { QUERY_RESULT_COLUMN_NO });
                    // isResetQuery = false;
                    // columnFilterHolder.set(columnFilter);
                    // }
                    // columnFilter.filterText(columnFilterText.getText());

                    rowFilterTextDoFilter.run();
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        }));

        lblNewLabel_3 = new JLabel("資料過濾");
        panel_13.add(lblNewLabel_3);

        rowFilterText = new JTextField();
        rowFilterText.setToolTipText("多值用\"^\"分隔, 請按Enter執行");
        panel_13.add(rowFilterText);
        rowFilterText.setColumns(10);

        rowFilterTextKeepMatchChk = new JCheckBox("只保留符合");
        panel_13.add(rowFilterTextKeepMatchChk);

        resetQueryBtn = new JButton("重設");
        resetQueryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                columnFilterText.setText("");
                rowFilterText.setText("");
                rowFilterTextKeepMatchChk.setSelected(false);
                checkIsNeedResetQueryResultTable(true);
                {
                    filterRowsQueryList = null;
                    isResetQuery = true;
                    queryModeProcess(queryList, true, null, null);//
                }
            }
        });
        panel_13.add(resetQueryBtn);

        rowFilterText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                rowFilterTextDoFilter.run();
            }
        });
        rowFilterText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowFilterTextDoFilter.run();
            }
        });
        rowFilterTextKeepMatchChk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowFilterTextDoFilter.run();
            }
        });

        for (final JTextField f : new JTextField[] { rowFilterText, columnFilterText }) {
            f.addMouseListener(new MouseAdapter() {

                private List<String> getTextFieldTextOrClipboard(JTextField f) {
                    String textFrom = f.getSelectedText();
                    if (StringUtils.isBlank(textFrom)) {
                        textFrom = f.getText();
                    }
                    if (StringUtils.isBlank(textFrom)) {
                        textFrom = ClipboardUtil.getInstance().getContents();
                    }
                    List<String> lst = new ArrayList<String>();
                    Scanner scan = new Scanner(textFrom);
                    while (scan.hasNext()) {
                        lst.add(scan.next());
                    }
                    scan.close();
                    return lst;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (JMouseEventUtil.buttonRightClick(1, e)) {
                        JPopupMenuUtil util = JPopupMenuUtil.newInstance(f)//
                                .addJMenuItem("空白換成\"^\"", new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        List<String> texts = getTextFieldTextOrClipboard(f);
                                        List<String> arry = new ArrayList<String>();
                                        for (String x : texts) {
                                            x = StringUtils.trimToEmpty(x);
                                            if (StringUtils.isNotBlank(x)) {
                                                arry.add(x);
                                            }
                                        }
                                        f.setText(StringUtils.join(arry, "^"));
                                    }
                                })//
                                .addJMenuItem("空白換成[準確]\"^\"", new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        List<String> texts = getTextFieldTextOrClipboard(f);
                                        List<String> arry = new ArrayList<String>();
                                        for (String x : texts) {
                                            x = StringUtils.trimToEmpty(x);
                                            if (StringUtils.isNotBlank(x)) {
                                                arry.add("/^" + x + "$/");
                                            }
                                        }
                                        f.setText(StringUtils.join(arry, "^"));
                                    }
                                })//
                        ;
                        util.addJMenuItem(new S2T_And_T2S_EventHandler(f).getMenuItem_STChinese(false));
                        util.addJMenuItem(new S2T_And_T2S_EventHandler(f).getMenuItem_STChinese(true));
                        util.applyEvent(e).show();
                    }
                }
            });
        }

        panel_14 = new JPanel();
        panel_12.add(panel_14, BorderLayout.WEST);

        panel_15 = new JPanel();
        panel_12.add(panel_15, BorderLayout.SOUTH);

        queryResultTimeLbl = new JLabel("        ");
        panel_15.add(queryResultTimeLbl);

        radio_import_excel = new JRadioButton("匯入excel");
        panel_15.add(radio_import_excel);

        radio_import_clipboard = new JRadioButton("匯入clipboard");
        panel_15.add(radio_import_clipboard);

        radio_import_excel_isAppend = new JCheckBox("匯入附加");
        panel_15.add(radio_import_excel_isAppend);

        radio_export_excel = new JRadioButton("匯出excel");
        radio_export_excel.setSelected(true);
        panel_15.add(radio_export_excel);

        excelExportBtn = new JButton("動作");
        excelExportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excelExportBtnAction();
            }
        });

        radio_export_json = new JRadioButton("匯出json");
        panel_15.add(radio_export_json);

        radio_export_csv = new JRadioButton("匯出csv");
        panel_15.add(radio_export_csv);

        btnExcelBtn = JButtonGroupUtil.createRadioButtonGroup(radio_import_excel, radio_export_excel, radio_export_json, radio_import_clipboard, radio_export_csv);

        radio_export_excel_ignoreNull = new JCheckBox("匯出null改為空白");
        panel_15.add(radio_export_excel_ignoreNull);
        panel_15.add(excelExportBtn);

        recordWatcherToggleBtn = new JButton("監聽");
        recordWatcherToggleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startRecordWatcher();
            }
        });
        panel_15.add(recordWatcherToggleBtn);

        recordWatcherToggleAutoChk = new JCheckBox("Auto");
        recordWatcherToggleAutoChk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mRecordWatcher.get() != null && recordWatcherToggleAutoChk.isSelected()) {
                    mRecordWatcher.get().doNotify();
                }
            }
        });
        panel_15.add(recordWatcherToggleAutoChk);

        panel_16 = new JPanel();
        panel_12.add(panel_16, BorderLayout.EAST);

        JScrollPane queryResultTableScrollPane = JTableUtil.getScrollPane(queryResultTable);
        JCommonUtil.applyDropFiles(queryResultTableScrollPane, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<File> fileLst = (List<File>) e.getSource();
                if (!fileLst.isEmpty()) {
                    File xlsfile = fileLst.get(0);
                    System.out.println("Import file : " + xlsfile);//
                    excelImportSheetMaster(xlsfile);
                }
            }
        });

        tabbedPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (JTabbedPaneUtil.newInst(tabbedPane).isSelectedTitle("查詢結果", false)) {
                    if (KeyEventUtil.isMaskKeyPress(e, "c") && e.getKeyCode() == KeyEvent.VK_V) {
                        new ImportFromClipboard().parseMain(null);
                    }
                }
            }
        });

        panel_12.add(queryResultTableScrollPane, BorderLayout.CENTER);

        panel_7 = new JPanel();
        tabbedPane.addTab("查詢結果JSON", null, panel_7, null);
        panel_7.setLayout(new BorderLayout(0, 0));

        panel_8 = new JPanel();
        panel_7.add(panel_8, BorderLayout.NORTH);

        panel_9 = new JPanel();
        panel_7.add(panel_9, BorderLayout.WEST);

        panel_10 = new JPanel();
        panel_7.add(panel_10, BorderLayout.SOUTH);

        panel_11 = new JPanel();
        panel_7.add(panel_11, BorderLayout.EAST);

        queryResultJsonTextArea = new JTextArea();
        JTextAreaUtil.applyCommonSetting(queryResultJsonTextArea, false);
        panel_7.add(JCommonUtil.createScrollComponent(queryResultJsonTextArea), BorderLayout.CENTER);

        panel_18 = new JPanel();
        tabbedPane.addTab("參考備註", null, panel_18, null);
        panel_18.setLayout(new BorderLayout(0, 0));

        panel_19 = new JPanel();
        panel_18.add(panel_19, BorderLayout.NORTH);

        refSearchCategoryCombobox = new JComboBox();
        JComboBoxUtil.newInstance(refSearchCategoryCombobox).setWidth(100);
        refSearchCategoryCombobox_Auto = AutoComboBox.applyAutoComboBox(refSearchCategoryCombobox);
        refSearchCategoryCombobox_Auto.setMatchType(MatchType.Contains);
        refSearchCategoryCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String category = refSearchCategoryCombobox_Auto.getTextComponent().getText();
                    String text = refSearchText.getText();
                    if (refSearchListConfigHandler != null) {
                        refSearchListConfigHandler.find(category, text);
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        refSearchColorComboBtn = new JButton("色");
        refSearchColorComboBtnSetColor(Color.RED, null);
        refSearchColorComboBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color newColor = JCommonUtil._JColorChooser_showDialog(refSearchColorComboBtn.getBackground());
                if (newColor != null) {
                    refSearchColorComboBtnSetColor(newColor, null);
                }
            }
        });

        panel_19.add(refSearchColorComboBtn);
        panel_19.add(refSearchCategoryCombobox);

        lblNewLabel_6 = new JLabel("搜尋條件");
        panel_19.add(lblNewLabel_6);

        refSearchText = new JTextField();
        refSearchText.setToolTipText("儲存時為key");
        refSearchText.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    String category = refSearchCategoryCombobox_Auto.getTextComponent().getText();
                    String text = refSearchText.getText();
                    if (refSearchListConfigHandler != null) {
                        refSearchListConfigHandler.find(category, text);
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        panel_19.add(refSearchText);
        refSearchText.setColumns(15);

        lblNewLabel_7 = new JLabel("內文");
        panel_19.add(lblNewLabel_7);

        refContentArea = new JTextArea();
        refContentArea.setToolTipText("參考備註");
        JTextAreaUtil.applyCommonSetting(refContentArea, false);
        refContentArea.setRows(3);
        refContentArea.setColumns(25);
        panel_19.add(JCommonUtil.createScrollComponent(refContentArea));

        refContentConfigSaveBtn = new JButton("儲存");
        refContentConfigSaveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String category = refSearchCategoryCombobox_Auto.getTextComponent().getText();
                    Color newColor = refSearchColorComboBtn.getBackground();
                    refSearchListConfigHandler.add(category, refSearchText.getText(), refContentArea.getText(), JColorUtil.toHtmlColor(newColor));
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        panel_19.add(refContentConfigSaveBtn);

        refContentConfigClearBtn = new JButton("清除");
        refContentConfigClearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    refSearchCategoryCombobox_Auto.setSelectItemAndText("");
                    refSearchText.setText("");
                    refContentArea.setText("");
                    refSearchListConfigHandler.find("", "");
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        panel_19.add(refContentConfigClearBtn);

        panel_20 = new JPanel();
        panel_18.add(panel_20, BorderLayout.WEST);

        panel_21 = new JPanel();
        panel_18.add(panel_21, BorderLayout.EAST);

        panel_22 = new JPanel();
        panel_18.add(panel_22, BorderLayout.SOUTH);

        lbl_config_etc = new JLabel("參考備註設定擋路徑");
        panel_22.add(lbl_config_etc);

        refConfigPathText = new JTextField();
        JCommonUtil.jTextFieldSetFilePathMouseEvent(refConfigPathText, true);
        panel_22.add(refConfigPathText);
        refConfigPathText.setColumns(30);

        refSearchList = new JList();
        refSearchList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    RefSearchListConfigBean bean = (RefSearchListConfigBean) refSearchList.getSelectedValue();
                    if (bean == null) {
                        refSearchList.setToolTipText(null);
                        return;
                    }
                    if (JMouseEventUtil.buttonLeftClick(1, e)) {
                        refSearchText.setText(bean.searchKey);
                        refContentArea.setText(bean.content);
                        refSearchCategoryCombobox_Auto.setSelectItemAndText(bean.category);
                        refSearchColorComboBtnSetColor(null, bean.categoryColor);
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        refSearchList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    JListUtil.newInstance(refSearchList).defaultJListKeyPressed(e, false);
                    if (e.getKeyCode() == 127) {
                        RefSearchListConfigBean bean = (RefSearchListConfigBean) refSearchList.getSelectedValue();
                        if (bean == null) {
                            return;
                        }
                        refSearchListConfigHandler.delete(bean.category, bean.searchKey);
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        JListUtil.newInstance(refSearchList).applyOnHoverEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RefSearchListConfigBean bean = (RefSearchListConfigBean) e.getSource();
                refSearchList.setToolTipText(StringUtils.trimToNull(bean.content));
            }
        });

        panel_18.add(JCommonUtil.createScrollComponent(refSearchList), BorderLayout.CENTER);

        panel_26 = new JPanel();
        tabbedPane.addTab("匯出檔比對", null, panel_26, null);
        panel_26.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

        lblNewLabel_15 = new JLabel("初始匯出檔xls");
        panel_26.add(lblNewLabel_15, "2, 2, right, default");

        compareBeforeXlsText = new JTextField();
        JCommonUtil.jTextFieldSetFilePathMouseEvent(compareBeforeXlsText, false);
        panel_26.add(compareBeforeXlsText, "4, 2, fill, default");
        compareBeforeXlsText.setColumns(10);

        lblNewLabel_16 = new JLabel("結果匯出檔xls");
        panel_26.add(lblNewLabel_16, "2, 4, right, default");

        compareAfterXlsText = new JTextField();
        JCommonUtil.jTextFieldSetFilePathMouseEvent(compareAfterXlsText, false);
        panel_26.add(compareAfterXlsText, "4, 4, fill, default");
        compareAfterXlsText.setColumns(10);

        lblNewLabel_17 = new JLabel("產出檔中間名");
        panel_26.add(lblNewLabel_17, "2, 6, right, default");

        compareXlsMiddleNameText = new JTextField();
        panel_26.add(compareXlsMiddleNameText, "4, 6, fill, default");
        compareXlsMiddleNameText.setColumns(10);

        lblNewLabel_20 = new JLabel("取得excel中文欄位定義");
        panel_26.add(lblNewLabel_20, "2, 8, right, default");

        compareXlsColumnSettingTitleText = new JTextField();
        panel_26.add(compareXlsColumnSettingTitleText, "4, 8, fill, default");
        compareXlsColumnSettingTitleText.setColumns(10);
        compareXlsColumnSettingTitleText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (mTableColumnDefTextHandler != null) {
                        mTableColumnDefTextHandler.init(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    mXlsColumnDefDlg.show();
                }
            }
        });

        panel_27 = new JPanel();
        panel_26.add(panel_27, "4, 10, fill, fill");

        compareXlsExecuteBtn = new JButton("比對");
        compareXlsExecuteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareXlsExecuteBtnAction();
            }
        });
        panel_27.add(compareXlsExecuteBtn);

        compareXlsClearBtn = new JButton("清除");
        compareXlsClearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareBeforeXlsText.setText("");
                compareAfterXlsText.setText("");
                compareXlsMiddleNameText.setText("");
            }
        });
        panel_27.add(compareXlsClearBtn);

        compareTwoTableBtn = new JButton("比較兩個表");
        compareTwoTableBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareTwoTableBtnAction();
            }
        });
        panel_27.add(compareTwoTableBtn);

        panel_28 = new JPanel();
        tabbedPane.addTab("Excel欄位定義", null, panel_28, null);
        panel_28.setLayout(new BorderLayout(0, 0));

        panel_29 = new JPanel();
        panel_28.add(panel_29, BorderLayout.NORTH);

        tableColumnConfigBtn2 = new JButton("設定");
        tableColumnConfigBtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableColumnConfigBtnAction();
            }
        });

        columnXlsDefFindRowCountLbl = new JLabel("");
        panel_29.add(columnXlsDefFindRowCountLbl);
        panel_29.add(tableColumnConfigBtn2);

        lblNewLabel_18 = new JLabel("表");
        panel_29.add(lblNewLabel_18);

        columnXlsDefTableQryText = new JTextField();
        panel_29.add(columnXlsDefTableQryText);
        columnXlsDefTableQryText.setColumns(15);

        label_2 = new JLabel("欄位");
        panel_29.add(label_2);

        columnXlsDefColumnQryText = new JTextField();
        columnXlsDefColumnQryText.setColumns(15);
        panel_29.add(columnXlsDefColumnQryText);

        lblNewLabel_19 = new JLabel("其他");
        panel_29.add(lblNewLabel_19);

        columnXlsDefOtherQryText = new JTextField();
        columnXlsDefOtherQryText.setColumns(15);
        panel_29.add(columnXlsDefOtherQryText);

        panel_30 = new JPanel();
        panel_28.add(panel_30, BorderLayout.WEST);

        panel_31 = new JPanel();
        panel_28.add(panel_31, BorderLayout.SOUTH);

        panel_32 = new JPanel();
        panel_28.add(panel_32, BorderLayout.EAST);

        lblNewLabel_23 = new JLabel("標色");
        panel_29.add(lblNewLabel_23);

        columnXlsDefLblColorQryText = new JTextField();
        columnXlsDefLblColorQryText.setColumns(15);
        panel_29.add(columnXlsDefLblColorQryText);

        for (final JTextField textField : new JTextField[] { columnXlsDefTableQryText, columnXlsDefColumnQryText, columnXlsDefOtherQryText }) {
            textField.getDocument().addDocumentListener(JCommonUtil.getDocumentListener(new HandleDocumentEvent() {
                @Override
                public void process(DocumentEvent event) {
                    // if (columnXlsDefTableQryText == textField) {
                    // initColumnXlsDefTableColumnQryTable();
                    // }
                }
            }));
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    initColumnXlsDefTableColumnQryTable();
                }
            });
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initColumnXlsDefTableColumnQryTable();
                }
            });
        }

        columnXlsDefLblColorQryText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                JTableUtil.newInstance(columnXlsDefTableColumnQryTable).findSearchTextMatchChangeColor(columnXlsDefLblColorQryText.getText(), Arrays.asList(0));
            }
        });
        columnXlsDefLblColorQryText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableUtil.newInstance(columnXlsDefTableColumnQryTable).findSearchTextMatchChangeColor(columnXlsDefLblColorQryText.getText(), Arrays.asList(0));
            }
        });

        columnXlsDefTableQryText.setToolTipText("分隔\"^\", 正則/.../");
        columnXlsDefColumnQryText.setToolTipText("分隔\"^\", 正則/.../");
        columnXlsDefOtherQryText.setToolTipText("分隔\"^\", 正則/.../");
        columnXlsDefLblColorQryText.setToolTipText("分隔\"^\", 正則/.../");

        columnXlsDefTableQryText.addMouseListener(new S2T_And_T2S_EventHandler(columnXlsDefTableQryText).getEvent());
        columnXlsDefColumnQryText.addMouseListener(new S2T_And_T2S_EventHandler(columnXlsDefColumnQryText).getEvent());
        columnXlsDefOtherQryText.addMouseListener(new S2T_And_T2S_EventHandler(columnXlsDefOtherQryText).getEvent());
        columnXlsDefLblColorQryText.addMouseListener(new S2T_And_T2S_EventHandler(columnXlsDefOtherQryText).getEvent());

        columnXlsDefShowChineseChk = new JCheckBox("顯示中文");
        panel_29.add(columnXlsDefShowChineseChk);

        columnXlsDefTableColumnQryTable = new JTable();
        panel_28.add(JCommonUtil.createScrollComponent(columnXlsDefTableColumnQryTable), BorderLayout.CENTER);
        JTableUtil.defaultSetting(columnXlsDefTableColumnQryTable);

        panel_6 = new JPanel();
        tabbedPane.addTab("DB連線設定", null, panel_6, null);
        panel_6.setLayout(new FormLayout(
                new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
                new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

        saveConnectionBtn = new JButton("儲存連線設定");
        saveConnectionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                saveConnectionBtnClick();
            }
        });

        lblDbName = new JLabel("DB設定名");
        panel_6.add(lblDbName, "4, 2");

        dbNameIdText = new JComboBox();

        // dbNameIdText.setColumns(10);
        dbNameIdText_Auto = AutoComboBox.applyAutoComboBox(dbNameIdText);
        dbNameIdText_Auto.setMatchType(MatchType.Contains);

        mDBNameIdTextHandler = new DBNameIdTextHandler();
        mDBNameIdTextHandler.reload_DataSourceConfig_autoComplete();

        panel_6.add(dbNameIdText, "10, 2, fill, default");
        dbNameIdText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String key = mDBNameIdTextHandler.dbNameIdText_getText();
                    Map<String, String> param = dataSourceConfig.getConfig(key);
                    if (param == null || param.isEmpty()) {
                        JCommonUtil._jOptionPane_showMessageDialog_error("選擇錯誤!");
                        return;
                    }
                    initDataSourceProperties(param);
                    dataSourceConfig.setCurrentIndex(key);
                } catch (Exception e1) {
                    JCommonUtil.handleException(e1);
                }
            }
        });

        lblUrl = new JLabel("連線URL");
        panel_6.add(lblUrl, "4, 6");

        dbUrlText = new JTextField();
        JTextFieldUtil.applyCopyPasteJPopupMenus(dbUrlText, null, null);
        panel_6.add(dbUrlText, "10, 6, fill, default");
        dbUrlText.setColumns(10);

        lblNewLabel = new JLabel("DB帳號");
        panel_6.add(lblNewLabel, "4, 10");

        dbUserText = new JTextField();
        JTextFieldUtil.applyCopyPasteJPopupMenus(dbUserText, null, null);
        panel_6.add(dbUserText, "10, 10, fill, default");
        dbUserText.setColumns(10);

        lblNewLabel_1 = new JLabel("DB密碼");
        panel_6.add(lblNewLabel_1, "4, 14");

        dbPwdText = new JTextField();
        JTextFieldUtil.applyCopyPasteJPopupMenus(dbPwdText, null, null);
        panel_6.add(dbPwdText, "10, 14, fill, default");
        dbPwdText.setColumns(10);

        lblNewLabel_2 = new JLabel("Driver類別");
        panel_6.add(lblNewLabel_2, "4, 18");

        dbDriverText = new JTextField();
        JTextFieldUtil.applyCopyPasteJPopupMenus(dbDriverText, null, null);
        panel_6.add(dbDriverText, "10, 18, fill, default");
        dbDriverText.setColumns(10);
        panel_6.add(saveConnectionBtn, "4, 22");

        panel_17 = new JPanel();
        panel_6.add(panel_17, "10, 22, fill, fill");

        prevConnBtn = new JButton("上一組");
        prevConnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousConnBtnClick();
            }
        });
        panel_17.add(prevConnBtn);

        nextConnBtn = new JButton("下一組");
        panel_17.add(nextConnBtn);

        removeConnectionBtn = new JButton("刪除連線設定");
        removeConnectionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeConnectionBtnAction();
            }
        });
        panel_17.add(removeConnectionBtn);

        loadDBConfigFileBtn = new JButton("讀取DB設定");
        loadDBConfigFileBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File dbFile = JCommonUtil._jFileChooser_selectFileOnly();
                if (dbFile.exists()) {
                    dataSourceConfig.init(dbFile);
                    mDBNameIdTextHandler.reload_DataSourceConfig_autoComplete();
                }
            }
        });
        panel_17.add(loadDBConfigFileBtn);

        nextConnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextConnBtnClick();
            }
        });

        nextParameterBtn = new JButton("下一組參數");
        nextParameterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextParameterBtnClick();
            }
        });

        saveParameterTableBtn = new JButton("儲存參數");
        saveParameterTableBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveParameterTableConfig(true);
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        panel_4.add(saveParameterTableBtn);
        panel_4.add(nextParameterBtn);

        connTestBtn = new JButton("測試連線");
        connTestBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connTestBtnAction();
            }
        });
        panel_6.add(connTestBtn, "4, 24");

        panel_24 = new JPanel();
        panel_6.add(panel_24, "10, 32, fill, fill");

        if (true) {
            panel_24.add(JComboBoxUtil.createLookAndFeelComboBox(new Callable<JFrame>() {
                @Override
                public JFrame call() throws Exception {
                    return TAB_UI1.getJframe();
                }
            }));
        }

        panel_23 = new JPanel();
        panel_6.add(panel_23, "10, 34, fill, fill");

        saveEtcConfigBtn = new JButton("儲存設定組態");
        saveEtcConfigBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveEtcConfigBtnAction();
            }
        });

        exportYamlConfigBtn = new JButton("匯出yaml設定");
        exportYamlConfigBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File sqlIdFile = new File(FileUtil.DESKTOP_PATH, FastDBQueryUI.class.getSimpleName() + "_sqlList.yml");
                    sqlIdConfigBeanHandler.init("");
                    YamlMapUtil.getInstance().saveToFilePlain(sqlIdFile, sqlIdConfigBeanHandler.lst, false, null);
                    JCommonUtil._jOptionPane_showMessageDialog_info("done...");
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        setFontSizeBtn = new JButton("設定字型大小");
        setFontSizeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String val = JCommonUtil._jOptionPane_showInputDialog("輸入字型大小", String.valueOf(defaultFontSize));
                    if (val != null) {
                        setAllFontSize(Integer.parseInt(val));
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        panel_23.add(setFontSizeBtn);
        panel_23.add(exportYamlConfigBtn);

        importYamlConfigBtn = new JButton("匯入yaml設定");
        importYamlConfigBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File yamlFile = JCommonUtil._jFileChooser_selectFileOnly();
                    if (yamlFile == null || !yamlFile.exists()) {
                        JCommonUtil._jOptionPane_showMessageDialog_error("請選擇yml or properties檔");
                        return;
                    }
                    if (yamlFile.getName().endsWith("yml")) {
                        sqlIdConfigBeanHandler.saveYamlToProp(yamlFile, true);
                        initLoadSqlListConfig(null);
                        JCommonUtil._jOptionPane_showMessageDialog_info("匯入成功!");
                    }
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });
        panel_23.add(importYamlConfigBtn);
        panel_23.add(saveEtcConfigBtn);

        deleteParameterBtn = new JButton("刪除當前參數");
        deleteParameterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sqlParameterConfigLoadHandler.deleteParameterBtnAction();
            }
        });

        clearParameterBtn = new JButton("清除參數");
        clearParameterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
                JTableUtil u = JTableUtil.newInstance(parametersTable);
                for (int ii = 0; ii < model.getRowCount(); ii++) {
                    u.setValueAt(false, "", ii, ParameterTableColumnDef.VALUE.idx);
                }
            }
        });
        panel_4.add(clearParameterBtn);
        panel_4.add(deleteParameterBtn);

        executeSqlButton2 = new JButton("執行Sql");
        executeSqlButton2.setToolTipText("快速鍵 F5");
        executeSqlButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeSqlButtonClick();
            }
        });
        panel_4.add(executeSqlButton2);

        refConfigPathYamlExportBtn = new JButton("產生yaml");
        refConfigPathYamlExportBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File yamlFile = JCommonUtil._jFileChooser_selectFileOnly();
                    if (yamlFile == null) {
                        return;
                    }

                    List<RefSearchListConfigBean> lst = new ArrayList<RefSearchListConfigBean>();
                    Properties prop = PropertiesUtil.loadProperties(yamlFile, null, true);
                    for (Enumeration enu = prop.keys(); enu.hasMoreElements();) {
                        String key = (String) enu.nextElement();
                        String value = prop.getProperty(key);
                        RefSearchListConfigBean bean = PropertiesMultiUtil.of(key, value, RefSearchListConfigBean.class);
                        lst.add(bean);
                    }

                    File destYamlFile = new File(FileUtil.DESKTOP_DIR, FastDBQueryUI.class.getSimpleName() + "_Ref.yml");
                    YamlMapUtil.getInstance().saveToFilePlain(destYamlFile, lst, false, null);
                    JCommonUtil._jOptionPane_showMessageDialog_info("ok!");
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });

        queryResultTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
            }
        });

        // -----------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------
        {

            sqlIdConfigBeanHandler = new SqlIdConfigBeanHandler();
            sqlIdListDSMappingHandler = new SqlIdListDSMappingHandler();
            mSqlIdExecuteTypeHandler = new SqlIdExecuteTypeHandler();

            // 初始化datasource
            this.initDataSourceProperties(null);

            // 初始化parameterTable
            initParametersTable();

            // 初始化queryResultTable
            JTableUtil.defaultSetting(queryResultTable);

            // 初始化 sqlList
            initLoadSqlListConfig(null);
            sqlIdListDSMappingHandler.init();

            etcConfigHandler = new EtcConfigHandler();
            etcConfigHandler.reflectInit();

            refSearchListConfigHandler = new RefSearchListConfigHandler(refConfigPathText, refSearchList, refSearchCategoryCombobox);

            panel_22.add(refConfigPathYamlExportBtn);

            KeyEventExecuteHandler.newInstance(FastDBQueryUI.this, "", null, new Runnable() {
                @Override
                public void run() {
                    executeSqlButtonClick();
                }
            }, new Component[] {});

            editColumnHistoryHandler = new EditColumnHistoryHandler();

            // 初始化上次選的datasource
            mDBNameIdTextHandler.init_setup();

            JCommonUtil.setJFrameCenter(this);
            JCommonUtil.defaultToolTipDelay();
            JCommonUtil.setJFrameIcon(this, "resource/images/ico/" + ICO_FILENAME);// big_boobs.ico
            this.setTitle("You Set My World On Fire");

            if (jFrameRGBColorPanel.get() == null) {
                jFrameRGBColorPanel.set(new JFrameRGBColorPanel(this));
            }
            //
            panel_17.add(jFrameRGBColorPanel.get().getToggleButton(false));

            if (hideInSystemTrayHelper.get() == null) {
                hideInSystemTrayHelper.set(HideInSystemTrayHelper.newInstance());
                hideInSystemTrayHelper.get().apply(this);
            }
            panel_17.add(hideInSystemTrayHelper.get().getToggleButton(false));

            new MoveTabsNativeKeyListener();// alt + 左右切換頁籤工具

            mUndoSaveHanlder = new UndoSaveHanlder();

            tabbedPane.setSelectedIndex(1);// 預設為SQL頁籤

            sqlTextArea.setText("\r\n\r\n\r\n                     ");// 初始化輸入位置

            if (TAB_UI1 != null) {
                if (TAB_UI1.getResourcesPool().containsKey("sqlPageDbConnCombox")) {
                    JComboBoxUtil.newInstance(sqlPageDbConnCombox).setSelectedItem(TAB_UI1.getResourcesPool().get("sqlPageDbConnCombox"));
                }
            }
        }
    }

    private void sqlListImportSQLConfig(File file) {
        if (file.getName().endsWith(".yml")) {
            sqlIdConfigBeanHandler.saveYamlToProp(file, true);
            initLoadSqlListConfig(null);
        } else if (file.getName().endsWith(".properties")) {
            sqlIdConfigBeanHandler.saveYamlToProp2(file, true);
            initLoadSqlListConfig(null);
        } else {
            JCommonUtil._jOptionPane_showMessageDialog_error("檔案格式有誤!");
        }
    }

    private void setAllFontSize(int size) {
        defaultFontSize = size;

        // JCommonUtil.setUIFont("Serif", size);
        // JCommonUtil.setUIFontSize(this, 12, size);

        sqlTextArea.setFont(sqlTextArea.getFont().deriveFont((float) size));
        queryResultJsonTextArea.setFont(queryResultJsonTextArea.getFont().deriveFont((float) size));
        refContentArea.setFont(refContentArea.getFont().deriveFont((float) size));
        sqlParamCommentArea.setFont(sqlParamCommentArea.getFont().deriveFont((float) size));
        sqlIdCommentArea.setFont(sqlIdCommentArea.getFont().deriveFont((float) size));
    }

    private void initParametersTable() {
        JTableUtil.defaultSetting_AutoResize(parametersTable);
        DefaultTableModel createModel = JTableUtil.createModel(//
                new int[] { ParameterTableColumnDef.USE.idx, ParameterTableColumnDef.VALUE.idx, ParameterTableColumnDef.TYPE.idx }, //
                new Object[] { "使用", "參數", "值", "類型" });
        parametersTable.setModel(createModel);
        JTableUtil.newInstance(parametersTable).setRowHeightByFontSize();
        JTableUtil.setColumnWidths_Percent(parametersTable, new float[] { 5, 32, 32, 31 });

        // column = "Data Type"
        TableColumn sportColumn = parametersTable.getColumnModel().getColumn(3);
        JComboBox comboBox = new JComboBox();
        for (DataType e : DataType.values()) {
            comboBox.addItem(e);
        }
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        // set value mouse event
        JTextField editJTextField = new JTextField();
        editJTextField.addMouseListener(new S2T_And_T2S_EventHandler(editJTextField).getEvent());
        JTableUtil.newInstance(parametersTable).columnIsComponent(ParameterTableColumnDef.VALUE.idx, editJTextField);
    }

    private enum DataType {
        varchar(new Class<?>[] { String.class }, false) {
        }, //
        date(new Class<?>[] { java.sql.Date.class }, false) {
            protected Object applyDataChange(Object value) {
                System.out.println("-------" + value + " -> " + value.getClass());
                if (value instanceof String && StringUtils.isNotBlank((String) value)) {
                    try {
                        String val = (String) value;
                        java.sql.Date newVal = java.sql.Date.valueOf(val);
                        return newVal;
                    } catch (Exception ex) {
                        throw new RuntimeException("請輸入Date格式 yyyy-MM-dd , value : " + value + ", ERR : " + ex.getMessage(), ex);
                    }
                }
                return value;
            }
        }, //
        timestamp(new Class<?>[] { java.sql.Timestamp.class }, false) {

            protected Object applyDataChange(Object value) {
                System.out.println("-------" + value + " -> " + value.getClass());
                if (value instanceof String && StringUtils.isNotBlank((String) value)) {
                    try {
                        String val = (String) value;
                        java.sql.Timestamp newVal = java.sql.Timestamp.valueOf(val);
                        return newVal;
                    } catch (Exception ex) {
                        throw new RuntimeException("請輸入Timestamp格式 yyyy-MM-dd HH:mm:ss.SSSSS, value : " + value + ", ERR : " + ex.getMessage(), ex);
                    }
                }
                return value;
            }

        }, //
        number(new Class<?>[] { Number.class }, false) {
        }, //
        NULL(new Class<?>[] { void.class }, true) {

            protected Object applyDataChange(Object value) {
                return null;
            }

        }, //
        Empty(new Class<?>[] { String.class }, true) {

            protected Object applyDataChange(Object value) {
                return "";
            }

        }, //
        Varchar_Array(new Class<?>[] { String.class }, true) {

            protected Object applyDataChange(Object value) {
                System.out.println("-------" + value + " -> " + value.getClass());
                if (value instanceof String && StringUtils.isNotBlank((String) value)) {
                    List<Object> lst = new ArrayList<Object>();
                    String strVal = (String) value;
                    String[] valArry = StringUtils.split(strVal, ',');
                    for (String str : valArry) {
                        lst.add(StringUtils.trimToEmpty(str));
                    }
                    return lst.toArray();
                }
                return value;
            }
        }, //
        UNKNOW(new Class<?>[] { void.class }, false) {
        },//
        ;

        final Class<?>[] clz;
        final boolean forceAddColumn;

        DataType(Class<?>[] clz, boolean forceAddColumn) {
            this.clz = clz;
            this.forceAddColumn = forceAddColumn;
        }

        protected Object applyDataChange(Object value) {
            return value;
        }

        protected boolean isForceAddColumn() {
            return forceAddColumn;
        }

    }

    /**
     * 初始化sqlList
     */
    private void initLoadSqlListConfig(Boolean forceExecute) {
        if (InitLoadSqlListConfigHolder.get() == false || (forceExecute != null && forceExecute)) {
            InitLoadSqlListConfigHolder.set(true);
        } else {
            return;
        }
        try {
            if (sqlIdConfigBeanHandler == null) {
                sqlIdConfigBeanHandler = new SqlIdConfigBeanHandler();
            }
            sqlIdConfigBeanHandler.init_withoutUpdate("");
            sqlIdListDSMappingHandler.init();

            String categoryTextFilter = StringUtils.trimToEmpty(sqlIdConfigBeanHandler.getCurrentCategory()).toLowerCase();
            String queryText = StringUtils.trimToEmpty(sqlQueryText.getText()).toLowerCase();
            String contentFilterText = StringUtils.trimToEmpty(sqlContentFilterText.getText()).toLowerCase();
            String mappingFilterText = StringUtils.trimToEmpty(sqlMappingFilterText_Auto.getTextComponent().getText()).toLowerCase();

            List<SqlIdConfigBean> sqlIdList = new ArrayList<SqlIdConfigBean>();
            for (SqlIdConfigBean enu : sqlIdConfigBeanHandler.lst) {
                String sqlId = enu.sqlId;
                String category = StringUtils.trimToEmpty(enu.category).toLowerCase();
                String sqlIdCompare = sqlId.toLowerCase().toLowerCase();
                String content = StringUtils.trimToEmpty(enu.sql).toLowerCase();
                String comment = StringUtils.trimToEmpty(enu.sqlComment).toLowerCase();

                boolean findOk1 = false;
                boolean findOk2 = false;
                boolean findOk3 = false;

                if (StringUtils.isBlank(queryText) && StringUtils.isBlank(contentFilterText)) {
                    findOk1 = true;
                } else {
                    if (StringUtils.isNotBlank(queryText)) {
                        if ((category.contains(queryText) || sqlIdCompare.contains(queryText) || comment.contains(queryText))) {
                            findOk1 = true;
                        }
                    }
                    if (!findOk1 && StringUtils.isNotBlank(contentFilterText)) {
                        if (content.contains(contentFilterText)) {
                            findOk1 = true;
                        } else if (mSqlIdColumnHolder.isColumnExists(sqlId, contentFilterText)) {
                            findOk1 = true;
                        }
                    }
                }

                if (StringUtils.isBlank(mappingFilterText)) {
                    findOk2 = true;
                } else if (StringUtils.isNotBlank(mappingFilterText)) {
                    if (StringUtils.isNotBlank(sqlIdListDSMappingHandler.getProperty(sqlId)) && //
                            sqlIdListDSMappingHandler.getProperty(sqlId).toLowerCase().contains(mappingFilterText)) {
                        findOk2 = true;
                    }
                }

                if (StringUtils.isBlank(categoryTextFilter)) {
                    findOk3 = true;
                } else if (StringUtils.isNotBlank(categoryTextFilter)) {
                    if (StringUtils.isNotBlank(category) && //
                            category.toLowerCase().contains(categoryTextFilter)) {
                        findOk3 = true;
                    }
                }

                if (findOk1 && findOk2 && findOk3) {
                    sqlIdList.add(enu);
                }
            }

            // 資料排序
            sortSqlListProcess(sqlIdList);

            DefaultListModel model = JListUtil.createModel();
            for (SqlIdConfigBean s : sqlIdList) {
                model.addElement(s);
            }
            sqlList.setModel(model);
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        } finally {
            resetInitLoadSqlListConfigHolder(300);
        }
    }

    // ---------------------------------------------db conn combox ↓↓↓↓↓↓

    private class DBNameIdTextHandler {
        private final String LATEST_DATA_SOURCE_NAME = "latestDataSourceName";

        private String dbNameIdText_getText() {
            return StringUtils.defaultString(dbNameIdText_Auto.getTextComponent().getText());
        }

        private void dbNameIdText_setText(String text) {
            // dbNameIdText_Auto.setSelectItemAndText(text);
            dbNameIdText_Auto.setSelectItemAndText(text);
            JComboBoxUtil.newInstance(sqlPageDbConnCombox).setSelectedItem(text);
        }

        private void reload_DataSourceConfig_autoComplete() {
            dbNameIdText_Auto.applyComboxBoxList(dataSourceConfig.getSaveKeys(), dbNameIdText_getText());
            sqlPageDbConnCombox.setModel(JComboBoxUtil.createModel(dataSourceConfig.getSaveKeys()));
            sqlMappingFilterText_Auto.applyComboxBoxList(dataSourceConfig.getSaveKeys(), dbNameIdText_getText());
        }

        private void saveFinalQueryDataSourceUsage() {
            String dataSource = dbNameIdText_getText();
            if (StringUtils.isNotBlank(dataSource)) {
                defaultConfig.getConfigProp().setProperty(LATEST_DATA_SOURCE_NAME, dataSource);
                defaultConfig.store();
            }
        }

        private void init_setup() {
            if (defaultConfig.getConfigProp().containsKey(LATEST_DATA_SOURCE_NAME)) {
                String dataSource = defaultConfig.getConfigProp().getProperty(LATEST_DATA_SOURCE_NAME);
                dbNameIdText_setText(dataSource);
            }
        }
    }

    // ---------------------------------------------db conn combox ↑↑↑↑↑↑

    /**
     * 初始化dataSource
     */
    private void initDataSourceProperties(Map<String, String> param) {
        if (param == null || param.isEmpty()) {
            param = dataSourceConfig.loadConfig();
        }
        if (param.containsKey(PropertiesGroupUtils_ByKey.SAVE_KEYS) && StringUtils.isNotBlank(param.get(PropertiesGroupUtils_ByKey.SAVE_KEYS))) {
            mDBNameIdTextHandler.dbNameIdText_setText(param.get(PropertiesGroupUtils_ByKey.SAVE_KEYS));
        }
        if (param.containsKey("url") && StringUtils.isNotBlank(param.get("url"))) {
            dbUrlText.setText(param.get("url"));
        }
        if (param.containsKey("user")) {// user可空
            dbUserText.setText(param.get("user"));
        }
        if (param.containsKey("pwd")) {// 密碼可以空
            dbPwdText.setText(param.get("pwd"));
        }
        if (param.containsKey("driver") && StringUtils.isNotBlank(param.get("driver"))) {
            dbDriverText.setText(param.get("driver"));
        }
    }

    /**
     * 儲存連線設定
     */
    private void saveConnectionBtnClick() {
        try {
            String dbNameId = mDBNameIdTextHandler.dbNameIdText_getText();
            String url = dbUrlText.getText();
            String user = dbUserText.getText();
            String pwd = dbPwdText.getText();
            String driver = dbDriverText.getText();
            JCommonUtil.isBlankErrorMsg(dbNameId, "DBName empty");
            JCommonUtil.isBlankErrorMsg(url, "url empty");
            // JCommonUtil.isBlankErrorMsg(user, "user empty"); //使用者可空
            // JCommonUtil.isBlankErrorMsg(pwd, "pwd empty");//密碼可以空
            JCommonUtil.isBlankErrorMsg(driver, "driver empty");

            Map<String, String> param = new HashMap<String, String>();
            param.put(PropertiesGroupUtils_ByKey.SAVE_KEYS, dbNameId);
            param.put("url", url);
            param.put("user", user);
            param.put("pwd", pwd);
            param.put("driver", driver);

            dataSourceConfig.saveConfig(param);
            mDBNameIdTextHandler.reload_DataSourceConfig_autoComplete();

            if (externalJDBCDriverJarLoader.get() != null && !externalJDBCDriverJarLoader.get().isEmpty()) {
                System.out.println("## use custom class loader");
                externalJDBCDriverJarLoader.get().registerDriver(driver);
                Class.forName(driver, true, externalJDBCDriverJarLoader.get().getUrlClassLoader());
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    /**
     * 清空text
     */
    private void clearButtonClick() {
        sqlIdText.setText("");
        sqlTextArea.setText("");
        sqlIdCommentArea.setText("");
        sqlIdCategoryComboBox_Auto.setSelectItemAndText("");
        this.sqlBean = null;
        setSqlListSelection(this.sqlBean);
        SqlIdConfigBean emptyBean = new SqlIdConfigBean();
        emptyBean.color = "";
        emptyBean.category = "";
        emptyBean.sqlId = "未命名";
        emptyBean.sql = "";
        emptyBean.sqlComment = "";
        changeTabUITitile(emptyBean);
    }

    private void sqlIdColorButtonChangeColor(Color newColor) {
        sqlIdColorButton.setBackground(newColor);
        // these next two lines do the magic..
        sqlIdColorButton.setContentAreaFilled(false);
        sqlIdColorButton.setOpaque(true);
    }

    private String getSqlBeanColor() {
        Color newColor = sqlIdColorButton.getBackground();
        String colorString = JColorUtil.toHtmlColor(newColor);
        System.out.println("[getSqlBeanColor]" + colorString);
        return colorString;
    }

    private Color getSqlBeanColor2(String color) {
        try {
            if (StringUtils.isBlank(color)) {
                return Color.BLACK;
            }
            Color color1 = JColorUtil.rgb(color);
            System.out.println("[getSqlBeanColor2]" + color1);
            return color1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Color.BLACK;
        }
    }

    private void refSearchColorComboBtnSetColor(Color color, String colorString) {
        if (color != null) {
            refSearchColorComboBtn.setBackground(color);
        } else if (StringUtils.isNotBlank(colorString)) {
            try {
                refSearchColorComboBtn.setBackground(JColorUtil.rgb(colorString));
            } catch (Exception ex) {
                refSearchColorComboBtn.setBackground(Color.RED);
            }
        }
        refSearchColorComboBtn.setContentAreaFilled(false);
        refSearchColorComboBtn.setOpaque(true);
    }

    /**
     * 儲存sql
     */
    private void saveSqlButtonClick(boolean saveSqlIdConfig, boolean isShowErrMsg) {
        try {
            String sqlId = sqlIdText.getText();
            String sql = sqlTextArea.getText();
            if (isShowErrMsg) {
                JCommonUtil.isBlankErrorMsg(sqlId, "請輸入sql Id");
                JCommonUtil.isBlankErrorMsg(sql, "請輸入sql");
            }

            if (isSqlIdChange() && isShowErrMsg) {
                boolean isContinue = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("您輸入SqlID以存在:" + sqlIdText.getText() + ", 是否要繼續?", "已存在SqlID");
                if (!isContinue) {
                    JCommonUtil._jOptionPane_showMessageDialog_error("儲存取消!!!");
                    return;
                }
            }

            if (sqlBean != null) {
                mUndoSaveHanlder.push(sqlBean.sql);
            }

            String category = sqlIdCategoryComboBox_Auto.getTextComponent().getText();
            String sqlComment = sqlIdCommentArea.getText();

            SqlParam param = parseSqlToParam(sql);

            // 更新parameter表
            setParameterTable(param, false);

            // 儲存sqlList Prop
            SqlIdConfigBean bean = new SqlIdConfigBean();
            bean.color = getSqlBeanColor();
            bean.category = category;
            bean.sqlId = sqlId;
            bean.sql = sql;
            bean.sqlComment = sqlComment;
            bean.latestUpdateTime = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

            // 改變TabUI標題
            if (isShowErrMsg) {
                changeTabUITitile(bean);
            }

            if (saveSqlIdConfig) {
                mergeWithSqlListSelection(bean);
                bean = this.saveSqlListProp(bean);
            }
            // 儲存DS設定
            if (saveSqlIdConfig) {
                sqlIdListDSMappingHandler.store(false);// sqlPageDbConnCombox
            }

            // 載入參數設定
            sqlParameterConfigLoadHandler.init(bean.getUniqueKey());

            // 刷新sqlList
            initLoadSqlListConfig(null);
            sqlIdListDSMappingHandler.init();

            // 儲存變更
            setSqlListSelection(bean);

            // 更新sqlList查詢頁面 category 下拉
            sqlIdConfigBeanHandler.updateSqlIdCategoryComboBox4Tab1();
        } catch (Throwable ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private void mergeWithSqlListSelection(SqlIdConfigBean bean) {
        SqlIdConfigBean bean1 = (SqlIdConfigBean) sqlList.getSelectedValue();
        if (bean1 == null) {
            return;
        }
        if (bean.equals(bean1)) {
            bean.setLatestQueryTime(bean1.getLatestQueryTime());
            bean.setLatestUpdateTime(bean1.getLatestUpdateTime());
            bean.setQueryTimes(bean1.getQueryTimes());
        }
    }

    // 儲存變更
    private void setSqlListSelection(SqlIdConfigBean bean) {
        sqlBean = bean;
        sqlList.setSelectedValue(sqlBean, true);
        sqlTextAreaChange();
    }

    /**
     * 載入參數
     */
    private void setParameterTable(SqlParam param, boolean reset) {
        // 取得更新前參數值
        Map<String, Object> valMap = new HashMap<String, Object>();
        for (int ii = 0; ii < parametersTable.getRowCount(); ii++) {
            String column = (String) parametersTable.getValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
            Object val = parametersTable.getValueAt(ii, ParameterTableColumnDef.VALUE.idx);
            if (val != null) {
                valMap.put(column, val);
            }
        }
        // 重設參數列表
        initParametersTable();
        DefaultTableModel createModel = (DefaultTableModel) parametersTable.getModel();
        for (String column : param.getOrderParametersLst()) {
            Object val = "";
            if (!reset && valMap.containsKey(column)) {
                val = valMap.get(column);
            }
            createModel.addRow(new Object[] { true, column, val, DataType.varchar });
        }
    }

    /**
     * 儲存prop
     */
    private SqlIdConfigBean saveSqlListProp(SqlIdConfigBean bean) throws IOException {
        bean.latestUpdateTime = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("#saveSqlListProp = " + ReflectionToStringBuilder.toString(bean));
        sqlIdConfigBeanHandler.save(bean);
        System.out.println("儲存檔案路徑 : " + sqlIdListFile);
        return bean;
    }

    private Object getRealValue(String value, DataType dataType) {
        return dataType.applyDataChange(value);
    }

    private enum ParameterTableColumnDef {
        USE(0), COLUMN(1), VALUE(2), TYPE(3);

        final int idx;

        ParameterTableColumnDef(int idx) {
            this.idx = idx;
        }
    }

    public String getCurrentSQL() {
        String sql = sqlTextArea.getText().toString();
        if (StringUtils.isNotBlank(sqlTextArea.getSelectedText())) {
            sql = sqlTextArea.getSelectedText();
        }
        return sql;
    }

    /**
     * 執行sql
     */
    private void executeSqlButtonClick() {
        long startTime = System.currentTimeMillis();
        try {
            if (executeSqlButtonClickHolder.get() == false) {
                executeSqlButtonClickHolder.set(true);
            } else {
                System.out.println("! executeSqlButtonClick 執行中!!");
                return;
            }

            // init
            {
                isResetQuery = true;
                filterRowsQueryList = null;// rows 過濾清除
                importExcelSheetName = null; // 清除匯入黨名
                queryResultTimeLbl.setText("");
                InitLoadSqlListConfigHolder.set(true);
            }

            JTableUtil util = JTableUtil.newInstance(parametersTable);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Map<String, String> sqlInjectMap = new LinkedHashMap<String, String>();
            Set<String> forceAddColumns = new HashSet<String>();
            final List<Object> parameterList = new ArrayList<Object>();
            SqlParam param = null;

            currentSQL.set(getCurrentSQL());

            if (updateBatchSqlRadio.isSelected()) {
                if (StringUtils.isBlank(currentSQL.get())) {
                    File importFile = JCommonUtil._jFileChooser_selectFileOnly();
                    if (importFile == null || !importFile.exists()) {
                        Validate.isTrue(false, "批量執行sql檔案錯誤!");
                    }

                    createJProgressBarHelper("匯入中...", 100, true);
                    currentSQL.set(FileUtil.loadFromFile(importFile, "UTF8"));
                } else {
                    createJProgressBarHelper("匯入中...", 100, true);
                }
            } else {
                for (int ii = 0; ii < parametersTable.getRowCount(); ii++) {
                    Boolean isInUse = (Boolean) util.getRealValueAt(ii, ParameterTableColumnDef.USE.idx);
                    if (isInUse == null) {
                        isInUse = false;
                    }

                    String columnName = (String) util.getRealValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                    String value = (String) util.getRealValueAt(ii, ParameterTableColumnDef.VALUE.idx);

                    if (SqlParam.sqlInjectionPATTERN.matcher(columnName).matches()) {
                        // sql Injection
                        if (isInUse) {
                            sqlInjectMap.put(columnName, StringUtils.trimToEmpty(value));
                        } else {
                            sqlInjectMap.put(columnName, null);
                        }
                    } else {
                        // 一般處理
                        DataType dataType = (DataType) util.getRealValueAt(ii, ParameterTableColumnDef.TYPE.idx);
                        if (isInUse) {
                            paramMap.put(columnName, getRealValue(value, dataType));
                        } else {
                            paramMap.put(columnName, null);
                        }

                        if (dataType.isForceAddColumn()) {
                            forceAddColumns.add(columnName);
                        }
                    }
                }

                JCommonUtil.isBlankErrorMsg(currentSQL.get(), "請輸入sql");

                // 取得執行sql物件
                param = parseSqlToParam(currentSQL.get());

                // 檢查參數是否異動
                for (String columnName : param.paramSet) {
                    if (!paramMap.containsKey(columnName) && !sqlInjectMap.containsKey(columnName)) {
                        Validate.isTrue(false, "參數有異動!, 請重新按儲存按鈕");
                    }
                }

                // 組參數列
                if (param.getClass() == SqlParam.class) {
                    for (String columnName : param.paramList) {
                        if (!paramMap.containsKey(columnName)) {
                            Validate.isTrue(false, "參數未設定 : " + columnName);
                        }
                        parameterList.add(paramMap.get(columnName));
                    }
                } else if (param.getClass() == SqlParam_IfExists.class) {
                    parameterList.addAll(((SqlParam_IfExists) param).processParamMap(paramMap, sqlInjectMap, forceAddColumns));
                }

                // 設定 sqlInjectionMap
                param.sqlInjectionMap.putAll(sqlInjectMap);

                System.out.println("尚未執行=====================================================");
                System.out.println(param.getQuestionSql());
                for (int i = 0; i < parameterList.size(); i++) {
                    System.out.println("param[" + i + "]:\"" + parameterList.get(i) + "\"  (" + (parameterList.get(i) != null ? parameterList.get(i).getClass().getName() : "NA") + ")");
                }
                System.out.println("尚未執行=====================================================");

                if (updateSqlRadio.isSelected()) {
                    createJProgressBarHelper("更新中...", 100, false);
                } else if (querySqlRadio.isSelected()) {
                    createJProgressBarHelper("查詢中...", 100, false);
                }
            }

            // 判斷執行模式
            if (querySqlRadio.isSelected()) {
                final int maxRowsLimit = StringNumberUtil.parseInt(maxRowsText.getText(), 0);
                final String questionParamSQL = param.getQuestionSql();
                final AtomicReference<Exception> throwableHolder = new AtomicReference<Exception>();

                Triple<List<String>, List<Class<?>>, List<Object[]>> orignQueryResult = ThreadUtil.runUseBlockingQueue(new Callable<Triple<List<String>, List<Class<?>>, List<Object[]>>>() {
                    @Override
                    public Triple<List<String>, List<Class<?>>, List<Object[]>> call() throws Exception {
                        try {
                            Triple<List<String>, List<Class<?>>, List<Object[]>> orignQueryResult = JdbcDBUtil.queryForList_customColumns(questionParamSQL, parameterList.toArray(),
                                    getDataSource().getConnection(), true, maxRowsLimit);
                            return orignQueryResult;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            throwableHolder.set(ex);
                        } finally {
                            if (executeSqlButtonClickProg.get() != null) {
                                executeSqlButtonClickProg.get().dismiss();
                                executeSqlButtonClickProg.set(null);
                            }
                        }
                        return null;
                    }
                }, 60 * 60 * 1000);

                System.out.println("[Query END !!] ==================================================");

                if (throwableHolder.get() != null) {
                    throw throwableHolder.get();
                }

                createRecordWatcher(orignQueryResult, param.getQuestionSql(), parameterList.toArray(), true, maxRowsLimit);

                mSqlIdColumnHolder.setColumns(mSqlIdColumnHolder.getSqlId(), orignQueryResult.getLeft());

                queryList = orignQueryResult;

                // 切換查詢結果
                if (!queryList.getRight().isEmpty()) {
                    tabbedPane.setSelectedIndex(3);
                }

                queryModeProcess(queryList, false, Pair.of(param, parameterList), null);

                showJsonArry(queryList, 1000, null);

                // 過濾欄位apply
                if (StringUtils.isNotBlank(rowFilterText.getText()) || StringUtils.isNotBlank(columnFilterText.getText())) {
                    rowFilterTextDoFilter.run();
                }
            } else if (updateSqlRadio.isSelected()) {
                final AtomicReference<Exception> throwableHolder = new AtomicReference<Exception>();
                final String questionParamSQL = param.getQuestionSql();
                Integer modifyResult = ThreadUtil.runUseBlockingQueue(new Callable<Integer>() {

                    @Override
                    public Integer call() throws Exception {
                        try {
                            int modifyResult = JdbcDBUtil.modify(questionParamSQL, parameterList.toArray(), getDataSource().getConnection(), true);
                            return modifyResult;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            throwableHolder.set(ex);
                        } finally {
                            if (executeSqlButtonClickProg.get() != null) {
                                executeSqlButtonClickProg.get().dismiss();
                                executeSqlButtonClickProg.set(null);
                            }
                        }
                        return null;
                    }
                }, 60 * 60 * 1000);

                System.out.println("[Update END !!] ==================================================");

                if (throwableHolder.get() != null) {
                    throw throwableHolder.get();
                }

                JCommonUtil._jOptionPane_showMessageDialog_info("update : " + modifyResult);
            } else if (updateBatchSqlRadio.isSelected()) {

                final AtomicReference<Exception> throwableHolder = new AtomicReference<Exception>();
                final AtomicReference<String> batchDoneHolder = new AtomicReference<String>();

                String resultMessage = ThreadUtil.runUseBlockingQueue(new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        try {
                            List<String> sqlLst = FastDBQueryUI_SqlSplitUtil.getInstance().execute(currentSQL.get());
                            int totalCount = 0;
                            int successCount = 0;
                            int noUpdateCount = 0;
                            int failCount = 0;
                            for (int ii = 0; ii < sqlLst.size(); ii++) {
                                String sql = sqlLst.get(ii);
                                try {
                                    System.out.print("Index : " + ii + " , ");
                                    int modifyResult = JdbcDBUtil.modify(sql, new Object[0], getDataSource().getConnection(), true);
                                    if (modifyResult != 0) {
                                        successCount++;
                                    } else {
                                        noUpdateCount++;
                                    }
                                } catch (Exception ex) {
                                    failCount++;
                                } finally {
                                    totalCount++;
                                    if (executeSqlButtonClickProg != null) {
                                        executeSqlButtonClickProg.get().addOne();
                                    }
                                }
                            }

                            String rtnMessage = "批量更新結果 : \n" + String.format("全部:%d\r\n成功:%d\r\n未更新到:%d\r\n失敗:%d", //
                                    totalCount, successCount, noUpdateCount, failCount);
                            System.out.println("rtnMessage = " + rtnMessage);
                            batchDoneHolder.set(rtnMessage);
                            return rtnMessage;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            throwableHolder.set(ex);
                            batchDoneHolder.set("error");
                        } finally {
                            if (executeSqlButtonClickProg.get() != null) {
                                executeSqlButtonClickProg.get().dismiss();
                                executeSqlButtonClickProg.set(null);
                            }
                        }
                        return null;
                    }
                }, 60 * 60 * 1000);

                System.out.println("[Batch END !!] ==================================================");

                if (throwableHolder.get() != null) {
                    throw throwableHolder.get();
                }

                JCommonUtil._jOptionPane_showMessageDialog_info(resultMessage);
            }

            // 設定欄位解釋定義
            setupCustomColumnDefExcelChinese();

            // 儲存參數設定
            saveParameterTableConfig(false);

            // 儲存sqlId mapping dataSource 設定
            sqlIdListDSMappingHandler.store(true);

            // 設定預設欄位定義
            setCustomColumnTitleTooltip();
            // 設定預設欄位代碼定義
            setCustomColumnCodeValueTooptip();

            // 儲存最後一個使用的dataSource
            mDBNameIdTextHandler.saveFinalQueryDataSourceUsage();

            // 紀錄sql執行類型
            mSqlIdExecuteTypeHandler.logExecuteType();

            // 更新查詢時間
            sqlIdConfigBeanHandler.setRegisterComponentIgnore();
            sqlIdConfigBeanHandler.updateQueryTime();

            // 設定新開視窗預設值
            if (TAB_UI1 != null) {
                TAB_UI1.getResourcesPool().put("sqlPageDbConnCombox", sqlPageDbConnCombox.getSelectedItem());
            }
        } catch (Exception ex) {
            queryResultTable.setModel(JTableUtil.createModel(true, "ERROR"));
            String category = refSearchCategoryCombobox_Auto.getTextComponent().getText();
            String findMessage = refSearchListConfigHandler.findExceptionMessage(category, ex.getMessage());
            // 一般顯示
            if (StringUtils.isBlank(findMessage)) {
                JCommonUtil.handleException(ex);
            } else {
                // html顯示
                JCommonUtil.handleException(String.format("參考 : %s", findMessage), ex, true, "", "yyyyMMdd", false, true);
            }
        } finally {
            BigDecimal duringTime = new BigDecimal(System.currentTimeMillis() - startTime).divide(new BigDecimal(1000), 3, BigDecimal.ROUND_HALF_EVEN);
            queryResultTimeLbl.setText("查詢耗時:  " + duringTime + " 秒");
            JTableUtil.newInstance(queryResultTable).setRowHeightByFontSize();
            executeSqlButtonClickHolder.set(false);

            resetInitLoadSqlListConfigHolder(500);
        }

    }

    private JProgressBarHelper createJProgressBarHelper(String title, int max, boolean isBlockView) {
        if (executeSqlButtonClickProg.get() != null) {
            executeSqlButtonClickProg.get().dismiss();
            executeSqlButtonClickProg.set(null);
        }
        final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, title);
        executeSqlButtonClickProg.set(prog);
        prog.max(max);
        prog.indeterminate(true);
        prog.limitMoveBound(false);
        prog.modal(false);
        prog.closeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                prog.setExitFlag(true);
                prog.dismiss();
            }
        });
        prog.build();
        // if (!isBlockView) {
        prog.show();
        // } else {
        // prog.showRightNow();
        // }
        // prog.show1();
        System.out.println("prog_visible = " + prog.isVisible());
        return prog;
    }

    private void resetInitLoadSqlListConfigHolder(final long timeout) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InitLoadSqlListConfigHolder.set(false);
                timer.cancel();
            }
        }, timeout);
    }

    /**
     * 執行sql
     */
    private String getShowAfterCurrentSQL(boolean showDlg) {
        String resultSql = "";
        try {
            JTableUtil util = JTableUtil.newInstance(parametersTable);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Map<String, String> sqlInjectMap = new LinkedHashMap<String, String>();
            Map<String, DataType> paramTypeMap = new HashMap<String, DataType>();

            Set<String> forceAddColumns = new HashSet<String>();

            for (int ii = 0; ii < parametersTable.getRowCount(); ii++) {
                Boolean isInUse = (Boolean) util.getRealValueAt(ii, ParameterTableColumnDef.USE.idx);
                if (isInUse == null) {
                    isInUse = false;
                }

                String columnName = (String) util.getRealValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                String value = (String) util.getRealValueAt(ii, ParameterTableColumnDef.VALUE.idx);

                paramTypeMap.put(columnName, (DataType) util.getRealValueAt(ii, ParameterTableColumnDef.TYPE.idx));

                if (SqlParam.sqlInjectionPATTERN.matcher(columnName).matches()) {
                    // sql Injection
                    if (isInUse) {
                        sqlInjectMap.put(columnName, StringUtils.trimToEmpty(value));
                    } else {
                        sqlInjectMap.put(columnName, null);
                    }
                } else {
                    // 一般處理
                    DataType dataType = (DataType) util.getRealValueAt(ii, ParameterTableColumnDef.TYPE.idx);
                    if (isInUse) {
                        paramMap.put(columnName, getRealValue(value, dataType));
                    } else {
                        paramMap.put(columnName, null);
                    }

                    if (dataType.isForceAddColumn()) {
                        forceAddColumns.add(columnName);
                    }
                }
            }

            String currentSQL = getCurrentSQL();

            // 取得執行sql物件
            SqlParam param = parseSqlToParam(currentSQL);

            // 檢查參數是否異動
            for (String columnName : param.paramSet) {
                if (!paramMap.containsKey(columnName) && !sqlInjectMap.containsKey(columnName)) {
                    Validate.isTrue(false, "參數有異動!, 請重新按儲存按鈕");
                }
            }

            // 組參數列
            List<Object> parameterList = new ArrayList<Object>();
            if (param.getClass() == SqlParam.class) {
                for (String columnName : param.paramList) {
                    if (!paramMap.containsKey(columnName)) {
                        Validate.isTrue(false, "參數未設定 : " + columnName);
                    }
                    parameterList.add(paramMap.get(columnName));
                }

                resultSql = SqlParam.parseToSqlParam_ToSQL2222(currentSQL, paramMap, paramTypeMap);
            } else if (param.getClass() == SqlParam_IfExists.class) {
                parameterList.addAll(((SqlParam_IfExists) param).processParamMap(paramMap, sqlInjectMap, forceAddColumns));
                resultSql = ((SqlParam_IfExists) param).processParamMap_ToSQL2222(paramMap, paramTypeMap, sqlInjectMap, forceAddColumns);
            }

            {
                Matcher mth = SqlParam.sqlInjectionPATTERN.matcher(resultSql);
                StringBuffer sb = new StringBuffer();
                while (mth.find()) {
                    String key = mth.group();
                    String replaceStr = StringUtils.trimToEmpty(sqlInjectMap.get(key));
                    mth.appendReplacement(sb, replaceStr);
                }
                mth.appendTail(sb);
                resultSql = sb.toString();
            }

            resultSql = StringUtil_.readContentAgain(resultSql, false, true, false);

            // 判斷執行模式
            if (showDlg) {
                if (mFastDBQueryUI_SQLTransparentDlg != null) {
                    mFastDBQueryUI_SQLTransparentDlg.dispose();
                }
                if (querySqlRadio.isSelected()) {
                    // SimpleTextDlg.newInstance(resultSql, "", null).show();
                    mFastDBQueryUI_SQLTransparentDlg = FastDBQueryUI_SQLTransparentDlg.newInstance(resultSql, this);
                } else if (updateSqlRadio.isSelected()) {
                    // SimpleTextDlg.newInstance(resultSql, "", null).show();
                    mFastDBQueryUI_SQLTransparentDlg = FastDBQueryUI_SQLTransparentDlg.newInstance(resultSql, this);
                }
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        } finally {
        }
        return resultSql;
    }

    private void setupCustomColumnDefExcelChinese() {
        List<String> tabLst = new ArrayList<String>();
        tabLst.add(getRandom_TableNSchema());
        if (mSqlTextAreaPromptHandler != null && mSqlTextAreaPromptHandler.tabMap != null) {
            for (Object tab : mSqlTextAreaPromptHandler.tabMap.keySet()) {
                boolean findOk = false;
                String newTabName = String.valueOf(tab);
                A: for (String tabName : tabLst) {
                    if (StringUtils.equalsIgnoreCase(tabName, newTabName)) {
                        findOk = true;
                        break A;
                    }
                }
                if (!findOk) {
                    tabLst.add(newTabName);
                }
            }
        }
        tabLst.add(FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF);
        tableColumnDefText_Auto.applyComboxBoxList(tabLst, FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF);
    }

    // 儲存參數設定
    private void saveParameterTableConfig(boolean showMsg) {
        if (!sqlParameterConfigLoadHandler.isInitOk()) {
            if (showMsg) {
                JCommonUtil._jOptionPane_showMessageDialog_error("參數設定檔未初始化!");
            }
            return;
        } else {
            Map<String, String> paramMap2 = new HashMap<String, String>();
            JTableUtil util2 = JTableUtil.newInstance(parametersTable);
            DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
            for (int ii = 0; ii < model.getRowCount(); ii++) {
                String col = (String) util2.getRealValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                String val = (String) util2.getRealValueAt(ii, ParameterTableColumnDef.VALUE.idx);
                paramMap2.put(col, StringUtils.trimToEmpty(val));
            }
            try {
                // 一般儲存參數處理
                sqlParameterConfigLoadHandler.saveConfig(paramMap2, sqlParamCommentArea.getText());
            } catch (Exception ex) {
                // 出現異常詢問是否重設
                boolean resetOk = false;
                if (ex.getMessage().contains("參數不同")) {
                    boolean resetConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption(ex.getMessage(), "是否要重設?");
                    if (resetConfirm) {
                        sqlParameterConfigLoadHandler.clear();
                        sqlParameterConfigLoadHandler.saveConfig(paramMap2, sqlParamCommentArea.getText());
                        resetOk = true;
                    }
                }
                if (!resetOk) {
                    throw new RuntimeException(ex);
                }
                if (showMsg) {
                    JCommonUtil._jOptionPane_showMessageDialog_info("參數儲存成功!");
                }
            }
        }
    }

    private String showJsonArry_Ver2(int dataLimit, final JProgressBarHelper prog) {
        String jsonString = "";
        try {
            JTableUtil utl = JTableUtil.newInstance(queryResultTable);
            JSONArray jsonArry = new JSONArray();
            final int DATA_LENGTH = 30;

            if (prog != null) {
                prog.max(queryResultTable.getRowCount());
            }

            for (int rowIdx = 0; rowIdx < queryResultTable.getRowCount(); rowIdx++) {
                int index = rowIdx;

                int realRowIdx = utl.getRealRowPos(rowIdx, queryResultTable);
                TreeMap<Integer, Object> rowMap = new TreeMap<Integer, Object>();
                JSONObject rowMap2 = new JSONObject();
                for (int col = 0; col < queryResultTable.getColumnCount(); col++) {
                    int realCol = utl.getRealColumnPos(col, queryResultTable);
                    boolean visible = utl.isColumnVisible(realCol, queryResultTable);
                    Object columnTitle = utl.getColumnTitle(col);
                    if (visible) {
                        Object value = utl.getModel().getValueAt(realRowIdx, realCol);
                        String strVal = null;
                        if (value != null) {
                            strVal = String.valueOf(value);
                            // if (StringUtils.length(strVal) > DATA_LENGTH) {
                            // strVal = StringUtils.substring(strVal, 0,
                            // DATA_LENGTH) + "...";
                            // }
                        }
                        rowMap.put(realCol, strVal);

                        if (StringUtils.equals(QUERY_RESULT_COLUMN_NO, (String) columnTitle)) {
                            continue;
                        }

                        rowMap2.put(columnTitle, strVal);
                    }
                }

                jsonArry.add(rowMap2);

                if (dataLimit > 0 && index >= dataLimit) {
                    break;
                }
                if (prog != null) {
                    prog.addOne();
                    if (prog.isExitFlag()) {
                        return "";
                    }
                }
                System.out.println("\t[row:" + realRowIdx + "] data : " + rowMap);
            }

            if (prog != null) {
                prog.dismiss();
            }

            jsonString = JSONUtils.valueToString(jsonArry, 8, 4);
            if (prog == null) {
                queryResultJsonTextArea.setText(jsonString);
            } else {
                String filename = FastDBQueryUI.class.getSimpleName() + //
                        "_Export_" + //
                        "_" + StringUtils.trimToEmpty(sqlIdText.getText()) + "_" + //
                        DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd_HHmmss") + //
                        ".json";
                filename = JCommonUtil._jOptionPane_showInputDialog("儲存檔案", filename);
                if (StringUtils.isNotBlank(filename) || !filename.endsWith(".json")) {
                    File exportFile = new File(FileUtil.DESKTOP_DIR, filename);
                    FileUtil.saveToFile(exportFile, jsonString, "UTF8");
                    if (exportFile.exists()) {
                        JCommonUtil._jOptionPane_showMessageDialog_info("匯出成功!");
                    }
                } else {
                    JCommonUtil._jOptionPane_showMessageDialog_info("檔名有誤!");
                }
            }
        } catch (Exception ex) {
            queryResultJsonTextArea.setText("");
            JCommonUtil.handleException(ex);
        }
        return jsonString;
    }

    private String showJsonArry(Triple<List<String>, List<Class<?>>, List<Object[]>> queryList, int dataLimit, final JProgressBarHelper prog) {
        String jsonString = "";
        try {
            List<String> columns = queryList.getLeft();
            List<Map<String, Object>> cloneLst = new ArrayList<Map<String, Object>>();
            int index = 0;

            if (prog != null) {
                prog.max(queryList.getRight().size());
            }

            for (Object[] rows : queryList.getRight()) {
                Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
                for (int ii = 0; ii < columns.size(); ii++) {
                    String col = columns.get(ii);
                    Object val = rows[ii];
                    rowMap.put(col, val);
                }
                cloneLst.add(rowMap);
                for (String key : queryList.getLeft()) {
                    if (rowMap.get(key) != null && (rowMap.get(key) instanceof java.sql.Date || rowMap.get(key) instanceof java.sql.Timestamp)) {
                        rowMap.put(key, String.valueOf(rowMap.get(key)));
                    }
                }
                index++;
                if (dataLimit > 0 && index >= dataLimit) {
                    break;
                }
                if (prog != null) {
                    prog.addOne();
                    if (prog.isExitFlag()) {
                        return "";
                    }
                }
            }

            if (prog != null) {
                prog.dismiss();
            }

            jsonString = JSONUtils.valueToString(JSONArray.fromObject(cloneLst), 8, 4);
            if (prog == null) {
                queryResultJsonTextArea.setText(jsonString);
            } else {
                String filename = FastDBQueryUI.class.getSimpleName() + //
                        "_Export_" + //
                        "_" + StringUtils.trimToEmpty(sqlIdText.getText()) + "_" + //
                        DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd_HHmmss") + //
                        ".json";
                filename = JCommonUtil._jOptionPane_showInputDialog("儲存檔案", filename);
                if (StringUtils.isNotBlank(filename) || !filename.endsWith(".json")) {
                    File exportFile = new File(FileUtil.DESKTOP_DIR, filename);
                    FileUtil.saveToFile(exportFile, jsonString, "UTF8");
                    if (exportFile.exists()) {
                        JCommonUtil._jOptionPane_showMessageDialog_info("匯出成功!");
                    }
                } else {
                    JCommonUtil._jOptionPane_showMessageDialog_info("檔名有誤!");
                }
            }
        } catch (Exception ex) {
            queryResultJsonTextArea.setText("");
            JCommonUtil.handleException(ex);
        }
        return jsonString;
    }

    /**
     * 查詢模式
     * 
     * @param pair
     */
    private void queryModeProcess(Triple<List<String>, List<Class<?>>, List<Object[]>> queryList, boolean silent, Pair<SqlParam, List<Object>> pair,
            Map<Integer, List<Integer>> changeColorRowCellIdxMap) {
        if (queryList.getRight().isEmpty()) {
            if (!silent) {
                System.out.println("fake row----");
                queryResultTable.setModel(getFakeDataModel(pair));
                JCommonUtil._jOptionPane_showMessageDialog_info("查無資料!");
            } else {
                DefaultTableModel createModel = JTableUtil.createModel(true, "");
                queryResultTable.setModel(createModel);
            }
            queryResultCountLabel.setText("0");
            JTableUtil.newInstance(queryResultTable).setRowHeightByFontSize();
            return;
        } else {
            if (!silent) {
                // JCommonUtil._jOptionPane_showMessageDialog_info("size : " +
                // queryList.getRight().size());
            }
            queryResultCountLabel.setText(String.valueOf(queryList.getRight().size()));
        }

        // 查詢結果table
        LinkedList<String> left = new LinkedList<String>(queryList.getLeft());
        LinkedList<Class<?>> middle = new LinkedList<Class<?>>(queryList.getMiddle());
        left.addFirst(QUERY_RESULT_COLUMN_NO);
        middle.addFirst(JButton.class);
        DefaultTableModel createModel = JTableUtil.createModelIndicateType(Arrays.asList(0), left, middle);

        queryResultTable.setModel(createModel);

        JTableUtil.newInstance(queryResultTable).setRowHeightByFontSize();

        // 設定 Value 顯示方式
        JTableUtil.newInstance(queryResultTable).columnUseCommonFormatter(null, false);

        JTableUtil.newInstance(queryResultTable).columnIsButton(QUERY_RESULT_COLUMN_NO);

        for (int ii = 0; ii < queryList.getRight().size(); ii++) {
            Object[] rows = queryList.getRight().get(ii);
            Object[] rows2 = new Object[rows.length + 1];
            System.arraycopy(rows, 0, rows2, 1, rows.length);
            rows2[0] = createSelectionBtn(String.valueOf(ii + 1));// TODO
            createModel.addRow(rows2);
        }

        if (changeColorRowCellIdxMap != null) {
            JTableUtil.newInstance(queryResultTable).setCellBackgroundColor(Color.green.brighter(), changeColorRowCellIdxMap, null);
        }

        setQueryResultTableColumnsWidth();
    }

    private void setQueryResultTableColumnsWidth() {
        if (true) {
            Map<String, Object> preference = new HashMap<String, Object>();
            preference.put("offset", 0.75f);
            preference.put("isCaculateTitle", true);
            preference.put("maxWidth", 500);
            Map<Integer, Integer> presetColumns = new HashMap<Integer, Integer>();
            presetColumns.put(0, String.valueOf(queryList.getRight().size()).length() * 10 + 15);
            preference.put("presetColumns", presetColumns);
            JTableUtil.setColumnWidths_ByDataContent(queryResultTable, preference, getInsets());
        } else {
            JTableUtil.setColumnWidths(queryResultTable, getInsets());
        }
    }

    private JToggleButton createSelectionBtn(String serialNo) {
        final JToggleButton selectionBtn = new JToggleButton(serialNo);
        selectionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Triple<JToggleButton, Integer, Integer> triple = (Triple<JToggleButton, Integer, Integer>) e.getSource();
                JToggleButton btn = triple.getLeft();
                int row = triple.getMiddle();
                int column = triple.getRight();
                JTableUtil.newInstance(queryResultTable).setRowSelection(row);
                String lbl = btn.isSelected() ? "選了" : "取消了";
                System.out.println(lbl + "..." + btn.getText());
            }
        });
        return selectionBtn;
    }

    private DefaultTableModel getFakeDataModel(Pair<SqlParam, List<Object>> pair) {
        boolean isFakeData = queryResultFakeDataChk.isSelected();
        FakeDataModelHandler handler = new FakeDataModelHandler(pair, this.getDataSource(), isFakeData);
        this.queryList = handler.getQueryList();
        return handler.getModel();
    }

    private static Pair<String, Map<Pair<Integer, Integer>, String>> getQuoteSQL_And_ReplaceMap(String sql) {
        String REPLACE_CHAR = "#";
        Map<Pair<Integer, Integer>, String> repMap = new LinkedHashMap<Pair<Integer, Integer>, String>();
        StringBuffer sb = new StringBuffer();
        Pattern ptnQuote = Pattern.compile("\\'.*?\\'");
        Matcher mthq = ptnQuote.matcher(sql);
        while (mthq.find()) {
            String group = mthq.group();
            repMap.put(Pair.of(mthq.start(), mthq.end()), group);
            String tempReplace = StringUtils.leftPad("", group.length(), REPLACE_CHAR);
            mthq.appendReplacement(sb, tempReplace);
        }
        mthq.appendTail(sb);
        return Pair.of(sb.toString(), repMap);
    }

    /**
     * parse Sql
     */
    private SqlParam parseSqlToParam(String sql) {
        String orignSQL = sql.toString();

        // 拿掉 註解
        sql = SqlParam.getIgnoreCommonentSql(orignSQL);

        // 中括號特殊處理
        int matchCount = 0;
        if ((matchCount = StringUtils.countMatches(sql, "[")) == StringUtils.countMatches(sql, "]")) {
            if (matchCount != 0) {
                SqlParam_IfExists sqlParam = SqlParam_IfExists.parseToSqlParam(sql, orignSQL);
                sqlParam.parseToSqlInjectionMap(sql);
                return sqlParam;
            }
        }

        return SqlParam.parseToSqlParam(sql, orignSQL);
    }

    private static class SqlParam {
        String orginialSql;
        String questionSql;
        Set<String> paramSet = new LinkedHashSet<String>();
        List<String> paramList = new ArrayList<String>();

        private static Pattern sqlInjectionPATTERN = Pattern.compile("\\_\\#\\w+\\#\\_");
        Map<String, String> sqlInjectionMap = new LinkedHashMap<String, String>();

        private String sqlInjectionReplace() {
            Matcher mth = sqlInjectionPATTERN.matcher(questionSql);
            StringBuffer sb = new StringBuffer();
            while (mth.find()) {
                String key = mth.group();
                String replaceStr = StringUtils.trimToEmpty(sqlInjectionMap.get(key));
                mth.appendReplacement(sb, replaceStr);
            }
            mth.appendTail(sb);
            return sb.toString();
        }

        public String getQuestionSql() {
            return sqlInjectionReplace();
        }

        public void parseToSqlInjectionMap(String sql) {
            Matcher mth = sqlInjectionPATTERN.matcher(sql);
            while (mth.find()) {
                String key = mth.group();
                sqlInjectionMap.put(key, "");
            }
        }

        public List<String> getOrderParametersLst() {
            List<String> lst = new ArrayList<String>();
            lst.addAll(paramSet);

            Set<String> s1 = new LinkedHashSet<String>();
            for (String k : sqlInjectionMap.keySet()) {
                if (!paramSet.contains(k)) {
                    s1.add(k);
                }
            }
            lst.addAll(s1);
            return lst;
        }

        protected static String getIgnoreCommonentSql(String sql) {
            List<Pair<Integer, Integer>> lst = StringUtil_.caculateQuoteMap(sql, '\'', '\'');

            StringBuffer sb = new StringBuffer();
            Pattern ptn1 = Pattern.compile("\\/\\*.*?\\*\\/", Pattern.MULTILINE | Pattern.DOTALL);
            Matcher mth = ptn1.matcher(sql);
            while (mth.find()) {
                mth.appendReplacement(sb, StringUtil_.replaceToSpaceKeepStructure(mth.group()));
            }
            mth.appendTail(sb);

            Pattern ptn2 = Pattern.compile("\\-{2}.*");
            sql = sb.toString();
            sb.setLength(0);
            Matcher mth2 = ptn2.matcher(sql);
            while (mth2.find()) {
                String tmp = mth2.group();
                boolean isIgnoreThis = false;
                A: for (Pair<Integer, Integer> p : lst) {
                    if (p.getLeft() <= mth2.start() && mth2.start() <= p.getRight()) {
                        isIgnoreThis = true;
                        break A;
                    }
                }
                if (tmp.indexOf("]") == -1 && !isIgnoreThis) {// 如果有對應參數設定則不拿掉
                    mth2.appendReplacement(sb, StringUtils.leftPad("", StringUtils.length(tmp)));
                } else {
                    mth2.appendReplacement(sb, tmp);
                }
            }
            mth2.appendTail(sb);
            return sb.toString();
        }

        public static String parseToSqlParam_ToSQL2222(String sql, Map<String, Object> paramMap, Map<String, DataType> paramTypeMap) {
            String orignSQL = sql.toString();

            // 拿掉 註解
            sql = SqlParam.getIgnoreCommonentSql(orignSQL);

            // 拖曳 字串部分 "'" 前置處理 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            Pair<String, Map<Pair<Integer, Integer>, String>> pair = getQuoteSQL_And_ReplaceMap(sql);
            sql = pair.getLeft();
            Map<Pair<Integer, Integer>, String> repMap = pair.getRight();
            // 拖曳 字串部分 "'" 前置處理 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑

            // 一般處理
            Pattern ptn = Pattern.compile(SQL_PARAM_PTN);
            Matcher mth = ptn.matcher(sql);// <----------------

            List<String> paramList = new ArrayList<String>();
            Set<String> paramSet = new LinkedHashSet<String>();

            StringBuffer sb2 = new StringBuffer();

            while (mth.find()) {
                String key = mth.group(1);
                if (StringUtils.length(key) < SQL_PARAM_PTN_LENGTH) {
                    throw new RuntimeException("參數變數長度不足(" + SQL_PARAM_PTN_LENGTH + ") : " + key);
                }

                int length = mth.group().length();
                if (isNotParam(key)) {
                    continue;
                }
                paramList.add(key);
                paramSet.add(key);
                String questMark = StringUtils.rightPad("?" + (paramList.size() - 1) + "?", length, " ");
                mth.appendReplacement(sb2, questMark);
            }
            mth.appendTail(sb2);

            // -------------------------------------------------------------------------------------------------
            // 拖曳 字串部分 "'" 後置處理 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            for (Pair<Integer, Integer> p : repMap.keySet()) {
                String orignSqlGroup = repMap.get(p);
                sb2 = sb2.replace(p.getLeft(), p.getRight(), orignSqlGroup);
            }
            // 拖曳 字串部分 "'" 後置處理 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑
            // -------------------------------------------------------------------------------------------------

            SqlParam sqlParam = new SqlParam();
            sqlParam.orginialSql = orignSQL;
            sqlParam.paramSet = paramSet;
            sqlParam.questionSql = sb2.toString();
            sqlParam.paramList = paramList;
            sqlParam.parseToSqlInjectionMap(orignSQL);

            // ------------------------------------------------------------
            StringBuffer sb3 = new StringBuffer();

            Pattern ptn2 = Pattern.compile("\\?(\\d+)\\?");
            Matcher mth2 = ptn2.matcher(sb2.toString());

            while (mth2.find()) {
                int index = Integer.parseInt(mth2.group(1));
                String key = paramList.get(index);
                Object value = paramMap.get(key);

                DataType dataType = paramTypeMap.get(key);

                String strValue = "";
                if (value != null) {
                    if (dataType == DataType.number) {
                        strValue = "" + value;
                    } else {
                        strValue = "'" + value + "'";
                    }
                    // try {
                    // Double.parseDouble((String) value);
                    // strValue = String.valueOf(value);
                    // } catch (Exception ex) {
                    // }
                }
                mth2.appendReplacement(sb3, strValue);
            }
            mth2.appendTail(sb3);

            return sb3.toString();
        }

        public static SqlParam parseToSqlParam(String sql, String orignSQL) {
            // 拖曳 字串部分 "'" 前置處理 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            Pair<String, Map<Pair<Integer, Integer>, String>> pair = getQuoteSQL_And_ReplaceMap(sql);
            sql = pair.getLeft();
            Map<Pair<Integer, Integer>, String> repMap = pair.getRight();
            // 拖曳 字串部分 "'" 前置處理 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑

            // 一般處理
            Pattern ptn = Pattern.compile(SQL_PARAM_PTN);
            Matcher mth = ptn.matcher(sql);// <----------------

            List<String> paramList = new ArrayList<String>();
            Set<String> paramSet = new LinkedHashSet<String>();

            StringBuffer sb2 = new StringBuffer();

            while (mth.find()) {
                String key = mth.group(1);
                if (StringUtils.length(key) < SQL_PARAM_PTN_LENGTH) {
                    throw new RuntimeException("參數變數長度不足(" + SQL_PARAM_PTN_LENGTH + ") : " + key);
                }

                int length = mth.group().length();
                if (isNotParam(key)) {
                    continue;
                }
                paramList.add(key);
                paramSet.add(key);
                String questMark = StringUtils.rightPad("?", length, " ");
                mth.appendReplacement(sb2, questMark);
            }
            mth.appendTail(sb2);

            // -------------------------------------------------------------------------------------------------
            // 拖曳 字串部分 "'" 後置處理 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            for (Pair<Integer, Integer> p : repMap.keySet()) {
                String orignSqlGroup = repMap.get(p);
                sb2 = sb2.replace(p.getLeft(), p.getRight(), orignSqlGroup);
            }
            // 拖曳 字串部分 "'" 後置處理 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑
            // -------------------------------------------------------------------------------------------------

            SqlParam sqlParam = new SqlParam();
            sqlParam.orginialSql = orignSQL;
            sqlParam.paramSet = paramSet;
            sqlParam.questionSql = sb2.toString();
            sqlParam.paramList = paramList;
            sqlParam.parseToSqlInjectionMap(orignSQL);
            return sqlParam;
        }

        protected static boolean isNotParam(String sql) {
            return StringUtils.defaultString(sql).matches("\\:?\\d+.*");
        }
    }

    private static class SqlParam_IfExists extends SqlParam {
        List<Pair<List<String>, int[]>> paramListFix = new ArrayList<Pair<List<String>, int[]>>();
        private Map<String, String> paramSetSentanceMap = new HashMap<String, String>();

        private boolean isParametersAllOk(List<String> paramLst, Map<String, Object> paramMap, Map<String, String> sqlInjectionMap, Set<String> forceAddColumns) {
            List<Pair<String, Boolean>> paramBoolLst = new ArrayList<Pair<String, Boolean>>();
            for (String col : paramLst) {
                if (paramMap.containsKey(col)) {
                    if (forceAddColumns.contains(col)) {
                        paramBoolLst.add(Pair.of(col, true));
                    } else if (paramMap.get(col) != null) {
                        if (paramMap.get(col) instanceof String) {
                            String tmpParamVal = StringUtils.trimToEmpty((String) paramMap.get(col));
                            if (StringUtils.isNotBlank(tmpParamVal)) {
                                paramBoolLst.add(Pair.of(col, true));
                            } else {
                                paramBoolLst.add(Pair.of(col, false));
                            }
                        } else {
                            paramBoolLst.add(Pair.of(col, false));
                            System.out.println("false-----特殊型別 : " + col);
                        }
                    } else {
                        paramBoolLst.add(Pair.of(col, false));
                    }
                } else if (sqlInjectionMap.containsKey(col) && StringUtils.isNotBlank(sqlInjectionMap.get(col))) {
                    paramBoolLst.add(Pair.of(col, true));
                } else {
                    paramBoolLst.add(Pair.of(col, false));
                }
            }
            if (paramBoolLst.isEmpty()) {
                return false;
            }
            boolean isOk = true;
            for (Pair<String, Boolean> param : paramBoolLst) {
                System.out.println("param : " + param.getLeft() + "\t" + param.getRight());
                if (!param.getRight()) {
                    isOk = false;
                }
            }
            return isOk;
        }

        public static SqlParam_IfExists parseToSqlParam(String sql, String orignSQL) {
            SqlParam_IfExists sqlParam = new SqlParam_IfExists();
            sqlParam.orginialSql = orignSQL.toString();

            // 拖曳 字串部分 "'" 前置處理 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
            String beforeSQL = sql.toString();
            Pair<String, Map<Pair<Integer, Integer>, String>> pair = getQuoteSQL_And_ReplaceMap(sql);
            sql = pair.getLeft();
            Map<Pair<Integer, Integer>, String> repMap = pair.getRight();
            // 拖曳 字串部分 "'" 前置處理 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑

            // 一般處理
            Pattern ptn = Pattern.compile("(\\[((?:[^\n]|\n)*?)\\]|" + SQL_PARAM_PTN + ")");
            Matcher mth = ptn.matcher(sql);

            while (mth.find()) {
                String quoteLine = mth.group(1);
                // fix 修正回原來的 ↓↓↓↓↓↓↓
                // System.out.println("quote[0] : " + quoteLine);
                quoteLine = StringUtils.substring(beforeSQL, mth.start(1), mth.end(1));
                // System.out.println("quote[1] : " + quoteLine);
                // fix 修正回原來的 ↑↑↑↑↑↑↑

                // 非必填檢查
                if (quoteLine.matches("^\\[([^\n]|\n)*\\]")) {
                    String realQuoteLine = mth.group(2);

                    // fix 修正回原來的 ↓↓↓↓↓↓↓
                    // System.out.println("quote[2] : " + realQuoteLine);
                    // realQuoteLine = StringUtils.substring(beforeSQL,
                    // mth.start(2), mth.end(2));//<---不須修正
                    // System.out.println("quote[3] : " + realQuoteLine);
                    // fix 修正回原來的 ↑↑↑↑↑↑↑

                    Pattern ptn2 = Pattern.compile(SQL_PARAM_PTN);
                    Pattern ptn3 = Pattern.compile("\\_\\#.*?\\#\\_");// -->
                                                                      // _#整個字串替換#_
                    Matcher mth2 = ptn2.matcher(realQuoteLine);
                    Matcher mth3 = ptn3.matcher(realQuoteLine);

                    List<String> params = new ArrayList<String>();
                    while (mth2.find()) {
                        String para = mth2.group(1);
                        if (StringUtils.length(para) < SQL_PARAM_PTN_LENGTH) {
                            throw new RuntimeException("參數變數長度不足(" + SQL_PARAM_PTN_LENGTH + ") : " + para);
                        }

                        if (isNotParam(para)) {
                            continue;
                        }
                        params.add(para);
                        sqlParam.paramSetSentanceMap.put(para, quoteLine);
                    }

                    while (mth3.find()) {
                        String para = mth3.group(0);
                        params.add(para);
                        sqlParam.paramSetSentanceMap.put(para, quoteLine);
                    }
                    sqlParam.paramSet.addAll(params);

                    // if (!params.isEmpty()) {
                    sqlParam.paramListFix.add(Pair.of(params, new int[] { mth.start(), mth.end() }));
                    // }
                }
                // 必填檢查 --> 也就是一班參數 :param
                else {
                    String realQuoteLine = mth.group(3);
                    // fix 修正回原來的 ↓↓↓↓↓↓↓
                    // System.out.println("quote[4] : " + realQuoteLine);
                    // realQuoteLine = StringUtils.substring(beforeSQL,
                    // mth.start(3), mth.end(3));//<---不須修正
                    // System.out.println("quote[5] : " + realQuoteLine);
                    // fix 修正回原來的 ↑↑↑↑↑↑↑

                    sqlParam.paramSet.add(realQuoteLine);
                    sqlParam.paramListFix.add(Pair.of(Arrays.asList(realQuoteLine), new int[] { mth.start(1), mth.end(1) }));
                }
            }
            return sqlParam;
        }

        private String toQuestionMarkSql(String markSql, List<Object> rtnParamLst, Map<String, Object> paramMap, Map<String, String> sqlInjectionMap) {
            // -----------------------------------------------------------------------------
            Pattern ptn = Pattern.compile(SQL_PARAM_PTN);
            Matcher mth = ptn.matcher(markSql);
            StringBuffer sb = new StringBuffer();

            while (mth.find()) {
                String col = mth.group(1);
                if (StringUtils.length(col) < SQL_PARAM_PTN_LENGTH) {
                    throw new RuntimeException("參數變數長度不足(" + SQL_PARAM_PTN_LENGTH + ") : " + col);
                }

                Object value = paramMap.get(col);

                if (isNotParam(col)) {
                    continue;
                }

                rtnParamLst.add(value);
                String replaceVal = StringUtils.rightPad("?", mth.group().length());

                mth.appendReplacement(sb, replaceVal);
            }
            mth.appendTail(sb);

            // ------------------------------------------------------------------------------
            markSql = sb.toString();
            sb.setLength(0);
            Pattern ptn2 = Pattern.compile("\\_\\#.*?\\#\\_");
            Matcher mth2 = ptn2.matcher(markSql);

            while (mth2.find()) {
                String col = mth2.group(0);
                if (sqlInjectionMap.containsKey(col)) {
                    mth2.appendReplacement(sb, (String) sqlInjectionMap.get(col));
                } else {
                    mth2.appendReplacement(sb, col);
                }
            }
            mth2.appendTail(sb);
            String rtnStr = sb.toString().replaceAll("[\\[\\]]", " ");
            // ------------------------------------------------------------------------------
            return rtnStr;
        }

        public List<Object> processParamMap(Map<String, Object> paramMap, Map<String, String> sqlInjectionMap, Set<String> forceAddColumns) {
            String orginialSqlBackup = getIgnoreCommonentSql(this.orginialSql.toString());
            StringBuffer sb = new StringBuffer();

            List<Object> rtnParamLst = new ArrayList<Object>();

            int tempStartPos = 0;

            for (Pair<List<String>, int[]> row : paramListFix) {
                int[] start_end = row.getRight();

                String markSql = orginialSqlBackup.substring(start_end[0], start_end[1]);
                String replaceToSql_FIX = StringUtils.rightPad("", markSql.length());

                if (isParametersAllOk(row.getLeft(), paramMap, sqlInjectionMap, forceAddColumns) || markSql.matches("\\:\\w+")) {
                    replaceToSql_FIX = this.toQuestionMarkSql(markSql, rtnParamLst, paramMap, sqlInjectionMap);
                }

                sb.append(orginialSqlBackup.substring(tempStartPos, start_end[0]));
                sb.append(replaceToSql_FIX);

                tempStartPos = start_end[1];
            }

            if (tempStartPos != 0) {
                sb.append(orginialSqlBackup.substring(tempStartPos));
            }

            this.questionSql = sb.toString();
            return rtnParamLst;
        }

        public String processParamMap_ToSQL2222(Map<String, Object> paramMap, Map<String, DataType> paramTypeMap, Map<String, String> sqlInjectionMap, Set<String> forceAddColumns) {
            String orginialSqlBackup = getIgnoreCommonentSql(this.orginialSql.toString());
            StringBuffer sb = new StringBuffer();

            List<Object> rtnParamLst = new ArrayList<Object>();
            List<String> rtnParamKeyLst = new ArrayList<String>();

            int tempStartPos = 0;

            for (Pair<List<String>, int[]> row : paramListFix) {
                int[] start_end = row.getRight();

                String markSql = orginialSqlBackup.substring(start_end[0], start_end[1]);
                String replaceToSql_FIX = StringUtils.rightPad("", markSql.length());

                if (isParametersAllOk(row.getLeft(), paramMap, sqlInjectionMap, forceAddColumns) || markSql.matches("\\:\\w+")) {
                    replaceToSql_FIX = this.toQuestionMarkSql_ToSQL2222(markSql, rtnParamLst, rtnParamKeyLst, paramMap, sqlInjectionMap);
                }

                sb.append(orginialSqlBackup.substring(tempStartPos, start_end[0]));
                sb.append(replaceToSql_FIX);

                tempStartPos = start_end[1];
            }

            if (tempStartPos != 0) {
                sb.append(orginialSqlBackup.substring(tempStartPos));
            }

            StringBuffer sb2 = new StringBuffer();

            Pattern ptn = Pattern.compile("\\?(\\d+)\\?");
            Matcher mth2 = ptn.matcher(sb.toString());

            while (mth2.find()) {
                int index = Integer.parseInt(mth2.group(1));
                Object value = rtnParamLst.get(index);
                String key = rtnParamKeyLst.get(index);

                DataType dataType = paramTypeMap.get(key);

                String strValue = "";
                if (value != null) {
                    if (dataType == DataType.number) {
                        strValue = "" + value;
                    } else {
                        strValue = "'" + value + "'";
                    }
                    // try {
                    // Double.parseDouble((String) value);
                    // strValue = String.valueOf(value);
                    // } catch (Exception ex) {
                    // }
                }
                mth2.appendReplacement(sb2, strValue);
            }
            mth2.appendTail(sb2);

            return sb2.toString();
        }

        private String toQuestionMarkSql_ToSQL2222(String markSql, List<Object> rtnParamLst, List<String> rtnParamKeyLst, Map<String, Object> paramMap, Map<String, String> sqlInjectionMap) {
            // -----------------------------------------------------------------------------
            Pattern ptn = Pattern.compile(SQL_PARAM_PTN);
            Matcher mth = ptn.matcher(markSql);
            StringBuffer sb = new StringBuffer();

            while (mth.find()) {
                String col = mth.group(1);
                if (StringUtils.length(col) < SQL_PARAM_PTN_LENGTH) {
                    throw new RuntimeException("參數變數長度不足(" + SQL_PARAM_PTN_LENGTH + ") : " + col);
                }

                Object value = paramMap.get(col);

                if (isNotParam(col)) {
                    continue;
                }

                rtnParamLst.add(value);
                rtnParamKeyLst.add(col);

                String replaceVal = StringUtils.rightPad("?" + (rtnParamLst.size() - 1) + "?", mth.group().length());

                mth.appendReplacement(sb, replaceVal);
            }
            mth.appendTail(sb);

            // ------------------------------------------------------------------------------
            markSql = sb.toString();
            sb.setLength(0);
            Pattern ptn2 = Pattern.compile("\\_\\#.*?\\#\\_");
            Matcher mth2 = ptn2.matcher(markSql);

            while (mth2.find()) {
                String col = mth2.group(0);
                if (sqlInjectionMap.containsKey(col)) {
                    mth2.appendReplacement(sb, (String) sqlInjectionMap.get(col));
                } else {
                    mth2.appendReplacement(sb, col);
                }
            }
            mth2.appendTail(sb);
            String rtnStr = sb.toString().replaceAll("[\\[\\]]", " ");
            // ------------------------------------------------------------------------------
            return rtnStr;
        }
    }

    /**
     * 讀取sqlId相對的sql
     */
    private void sqlListMouseClicked(MouseEvent e, SqlIdConfigBean sqlBean2) {
        // if(!JMouseEventUtil.buttonLeftClick(2, e)){
        // return;
        // }
        sqlBean = (SqlIdConfigBean) JListUtil.getLeadSelectionObject(sqlList);
        if (sqlBean2 != null) {
            sqlBean = sqlBean2;
        }
        if (sqlBean == null) {
            System.out.println("sqlId : 為 NULL取消操作");
            return;
        }

        System.out.println("sqlId : " + sqlBean.getUniqueKey());

        sqlIdText.setText(sqlBean.sqlId);
        // ---------------------------------------
        // sqlTextArea.setText(sqlBean.sql);
        JTextAreaUtil.setText_withoutTriggerChange(sqlTextArea, sqlBean.sql);
        sqlTextAreaScroll.invalidate();
        sqlTextAreaFocusLost();

        sqlTextAreaHighLighter();
        // ---------------------------------------
        JComboBoxUtil.newInstance(sqlIdCategoryComboBox).setSelectedItem(sqlBean.category);

        sqlIdColorButtonChangeColor(getSqlBeanColor2(sqlBean.color));

        sqlIdCommentArea.setText(sqlBean.sqlComment);

        // 載入參數設定
        sqlParameterConfigLoadHandler.init(sqlBean.getUniqueKey());
        loadParameterTableConfig(true);

        // 判斷是否要自動切換dataSource
        loadSqlIdMappingDataSourceConfig(sqlBean);

        // trigger 儲存按鈕
        saveSqlButtonClick(false, sqlBean2 == null);

        // 設定 tab標題
        if (sqlBean2 == null) {
            changeTabUITitile(sqlBean);
        }

        // 取得sql執行類型
        mSqlIdExecuteTypeHandler.processExecuteType(sqlBean.sqlId);
    }

    private void sqlTextAreaFocusLost() {
        if (TAB_UI1 != null) {
            String tableName = getRandom_TableNSchema();
            if (StringUtils.isBlank(tableName)) {
                tableName = null;
            }
            TAB_UI1.setToolTipTextAt(null, tableName);
        }
    }

    private void changeTabUITitile(SqlIdConfigBean mSqlIdConfigBean) {
        if (TAB_UI1 != null) {
            TAB_UI1.setTabTitle(null, mSqlIdConfigBean.toString());//
        }
    }

    private void loadSqlIdMappingDataSourceConfig(SqlIdConfigBean bean2) {
        try {
            sqlIdListDSMappingHandler.init();
            SqlIdConfigBean bean = (SqlIdConfigBean) JListUtil.getLeadSelectionObject(sqlList);
            if (bean2 != null) {
                bean = bean2;
            }
            if (sqlIdListDSMappingHandler.containsKey(bean.getUniqueKey())) {
                String saveKey = sqlIdListDSMappingHandler.getProperty(bean.getUniqueKey());
                if (!StringUtils.equals(mDBNameIdTextHandler.dbNameIdText_getText(), saveKey)) {
                    System.out.println("切換為最後一次成功使用的DS :[" + saveKey + "], ");
                    mDBNameIdTextHandler.dbNameIdText_setText(saveKey);
                    /*
                     * Map<String, String> param =
                     * dataSourceConfig.getConfig(saveKey); if
                     * (param.containsKey(PropertiesGroupUtils_ByKey. SAVE_KEYS)
                     * && StringUtils.isNotBlank(param.get(
                     * PropertiesGroupUtils_ByKey.SAVE_KEYS))) {
                     * dbNameIdText_setText(param.get(
                     * PropertiesGroupUtils_ByKey.SAVE_KEYS)); } if
                     * (param.containsKey("url") &&
                     * StringUtils.isNotBlank(param.get("url"))) {
                     * dbUrlText.setText(param.get("url")); } if
                     * (param.containsKey("user") &&
                     * StringUtils.isNotBlank(param.get("user"))) {
                     * dbUserText.setText(param.get("user")); } if
                     * (param.containsKey("pwd")) {// 密碼可以空
                     * dbPwdText.setText(param.get("pwd")); } if
                     * (param.containsKey("driver") &&
                     * StringUtils.isNotBlank(param.get("driver"))) {
                     * dbDriverText.setText(param.get("driver")); }
                     */
                }
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    /**
     * 將設定黨設定到parameterTable
     */
    private void loadParameterTableConfig(boolean isSetEmptyValue) {
        // 按順序載入參數
        List<String> paramSet = Collections.emptyList();
        {
            String sql = sqlTextArea.getText().toString();
            SqlParam param = parseSqlToParam(sql);
            paramSet = param.getOrderParametersLst();
        }

        sqlParamCommentArea.setText(sqlParameterConfigLoadHandler.loadComment());
        Map<String, String> paramMap = sqlParameterConfigLoadHandler.loadConfig();
        initParametersTable();
        DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
        for (String col : paramSet) { // paramMap.keySet()
            String val = paramMap.get(col);
            if (!isSetEmptyValue) {
                model.addRow(new Object[] { true, col, val, DataType.varchar });
            } else {
                model.addRow(new Object[] { true, col, "", DataType.varchar });
            }
        }
    }

    /**
     * 讀取下一組參數設定
     */
    private void nextParameterBtnClick() {
        if (!sqlParameterConfigLoadHandler.isInitOk()) {
            return;
        }
        sqlParameterConfigLoadHandler.next();
        loadParameterTableConfig(false);
    }

    /**
     * 下一組連線設定
     */
    private void nextConnBtnClick() {
        dataSourceConfig.next();
        initDataSourceProperties(null);
    }

    /**
     * 上一組連線設定
     */
    private void previousConnBtnClick() {
        dataSourceConfig.previous();
        initDataSourceProperties(null);
    }

    /**
     * 取得dataSource
     */
    public DataSource getDataSource() {
        String url = dbUrlText.getText();
        String user = dbUserText.getText();
        String pwd = dbPwdText.getText();
        String driver = dbDriverText.getText();
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(url);
        bds.setUsername(user);
        bds.setPassword(pwd);
        bds.setDriverClassName(driver);
        if (externalJDBCDriverJarLoader.get() != null && !externalJDBCDriverJarLoader.get().isEmpty()) {
            System.out.println("## use custom class loader");
            externalJDBCDriverJarLoader.get().registerDriver(driver);
            bds.setDriverClassLoader(externalJDBCDriverJarLoader.get().getUrlClassLoader());
        }
        return bds;
    }

    private static void loadExternalJars() {
        File jarDir = PropertiesUtil.getJarCurrentPath(FastDBQueryUI.class);
        if (jarDir.list() == null) {
            return;
        }
        if (externalJDBCDriverJarLoader.get() == null) {
            externalJDBCDriverJarLoader.set(new ExternalJDBCDriverJarLoader());
        }
        ExternalJDBCDriverJarLoader tool = externalJDBCDriverJarLoader.get();
        for (File jar : jarDir.listFiles()) {
            tool.addJar(jar);
        }
    }

    private void queryResultTableMouseClickAction(MouseEvent e) {
        try {
            class StartEditProcess {
                private FastDBQueryUI_CrudDlgUI fastDBQueryUI_CrudDlgUI;
                private FastDBQueryUI_RowCompareDlg fastDBQueryUI_RowCompareDlg;

                String openType = "";

                StartEditProcess() {
                    if (queryList != null && !queryList.getRight().isEmpty() && StringUtils.isBlank(importExcelSheetName)) {
                        openType = "CRUD";
                    } else {
                        openType = "XLS_COMPARE";
                    }
                    if (fastDBQueryUI_CrudDlgUI != null && fastDBQueryUI_CrudDlgUI.isShowing()) {
                        fastDBQueryUI_CrudDlgUI.dispose();
                    }
                    if (fastDBQueryUI_RowCompareDlg != null && fastDBQueryUI_RowCompareDlg.isShowing()) {
                        fastDBQueryUI_RowCompareDlg.disable();
                    }
                }

                // 一般查詢
                void openCRUD() {
                    JTableUtil jutil = JTableUtil.newInstance(queryResultTable);
                    int[] orignRowPosArry = queryResultTable.getSelectedRows();

                    List<Map<String, Pair<Object, Class>>> rowMapLst = new ArrayList<Map<String, Pair<Object, Class>>>();
                    
                    if(false) {
                    	//舊的寫法 -比較慢
                        for (int orignRowPos : orignRowPosArry) {
                            System.out.println("orignRowPos " + orignRowPos);
                            int rowPos = JTableUtil.getRealRowPos(orignRowPos, queryResultTable);
                            System.out.println("rowPos " + rowPos);

                            int queryLstIndex = transRealRowToQuyerLstIndex(rowPos, queryList.getRight());
                            Map<String, Pair<Object, Class>> rowMap = getDetailToMap(queryLstIndex);
                            rowMapLst.add(rowMap);
                        }                    	
                    } else {
                    	//新的寫法 -比較快
                    	for(int row : orignRowPosArry) {
                        	int row2 = JTableUtil.getRealRowPos(row, queryResultTable);
                        	Map<String, Pair<Object, Class>> rowMap = getDetailToMap(row2);
                        	rowMapLst.add(rowMap);
                        }
                    }

                    Triple<List<String>, List<Class<?>>, List<Object[]>> allRows = null;
                    if (filterRowsQueryList != null) {
                        allRows = filterRowsQueryList;
                    } else {
                        allRows = queryList;
                    }

                    fastDBQueryUI_CrudDlgUI = FastDBQueryUI_CrudDlgUI.newInstance(rowMapLst, getRandom_TableNSchemaLst(), allRows, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mFastDBQueryUI_CrudDlgUI = null;
                        }
                    }, FastDBQueryUI.this);

                    mFastDBQueryUI_CrudDlgUI = fastDBQueryUI_CrudDlgUI;
                }

                void openXLS_COMPARE() throws SQLException, Exception {
                    // 如果是用 excel 匯入 使用excel資料開啟
                    String schemaTable = JCommonUtil._jOptionPane_showInputDialog("請輸\"資料表名稱\",格視為 : Schema.TableName", importExcelSheetName);
                    if (StringUtils.isBlank(schemaTable)) {
                        Validate.isTrue(false, "查詢結果為空!");
                    }

                    // Triple<List<String>, List<Class<?>>, List<Object[]>>
                    // orignQueryResult =
                    // JdbcDBUtil.queryForList_customColumns(//
                    // String.format(" select * from %s where 1=1 ",
                    // shemaTable), //
                    // new Object[0], getDataSource().getConnection(), true, 1);

                    Pair<List<String>, List<Object[]>> excelImportLst = transRealRowToQuyerLstIndex();// orignQueryResult

                    int selectRowIndex = queryResultTable.getSelectedRow();

                    fastDBQueryUI_RowCompareDlg = FastDBQueryUI_RowCompareDlg.newInstance(schemaTable, selectRowIndex, excelImportLst, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    }, FastDBQueryUI.this);
                }

                void start() throws Exception {
                    if ("CRUD".equals(openType)) {
                        openCRUD();
                    } else if ("XLS_COMPARE".equals(openType)) {
                        openXLS_COMPARE();
                    }
                }
            }

            final StartEditProcess d = new StartEditProcess();

            if (JMouseEventUtil.buttonLeftClick(2, e)) {
                // d.start();
                new JMenuItem_BasicMenu().run();
            }

            if (JMouseEventUtil.buttonRightClick(1, e)) {
                JPopupMenuUtil ppap = JPopupMenuUtil.newInstance(queryResultTable);

                ppap.addJMenuItem("複製", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClipboardUtil.getInstance().setContents(JTableUtil.newInstance(queryResultTable).getSelectedValue());
                    }
                });

                ppap.addJMenuItem(new JMenuItem_BasicMenu().getItem());//

                ppap.addJMenuItem("選擇此列", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int[] rows = JTableUtil.newInstance(queryResultTable).getSelectedRows(false);
                        for (int row : rows) {
                            JToggleButton b = (JToggleButton) JTableUtil.newInstance(queryResultTable).getValueAt(true, row, 0);
                            b.setSelected(!b.isSelected());
                        }

                        JTableUtil.newInstance(queryResultTable).setRowSelection();
                    }
                });//

                ppap.addJMenuItem(addQueryResultPoolHandlerMenus());

                addKeepSelectionOnly(ppap);
                addSelectionTitle(ppap);

                ppap.addJMenuItem("進行CRUD操作", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            d.openCRUD();
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem("當前為舊資料比對重新查詢", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            d.openXLS_COMPARE();
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem("任兩筆資料比對", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            List<String> rowLabelLst = new ArrayList<String>();
                            List<List<Object>> rows = new ArrayList<List<Object>>();
                            List<Integer> rowLst = new ArrayList<Integer>();

                            boolean isNotIgnoreFirstColumn = false;
                            final int[] selectRowIdxArry = JTableUtil.newInstance(queryResultTable).getSelectedRows(false);
                            if (selectRowIdxArry != null && selectRowIdxArry.length == 2) {
                                for (int ii : selectRowIdxArry) {
                                    int rowIdx = JTableUtil.getRealRowPos(ii, queryResultTable);
                                    Object v = JTableUtil.newInstance(queryResultTable).getValueAt(false, rowIdx, 0);
                                    if (v instanceof JToggleButton) {
                                        rowLst.add(rowIdx);
                                        rowLabelLst.add(((JToggleButton) v).getText());
                                        rows.add(JTableUtil.getRowData(rowIdx, new int[] { 0 }, queryResultTable));
                                    } else {
                                        rowLst.add(rowIdx);
                                        rowLabelLst.add(String.valueOf(rowIdx));
                                        rows.add(JTableUtil.getRowData(rowIdx, new int[] {}, queryResultTable));
                                        isNotIgnoreFirstColumn = true;
                                    }
                                }
                            } else {
                                for (int ii = 0; ii < queryResultTable.getRowCount(); ii++) {
                                    int rowIdx = JTableUtil.getRealRowPos(ii, queryResultTable);
                                    Object v = JTableUtil.newInstance(queryResultTable).getValueAt(false, rowIdx, 0);
                                    if (v instanceof JToggleButton && ((JToggleButton) v).isSelected()) {
                                        rowLst.add(rowIdx);
                                        rowLabelLst.add(((JToggleButton) v).getText());
                                        rows.add(JTableUtil.getRowData(rowIdx, new int[] { 0 }, queryResultTable));
                                    } else if (!(v instanceof JToggleButton)) {
                                        rowLst.add(rowIdx);
                                        rowLabelLst.add(String.valueOf(rowIdx));
                                        rows.add(JTableUtil.getRowData(rowIdx, new int[] {}, queryResultTable));
                                        isNotIgnoreFirstColumn = true;
                                    }
                                }
                            }

                            if (rowLst.size() == 0) {
                                if (selectRowIdxArry != null && selectRowIdxArry.length == 1) {
                                    for (int ii : selectRowIdxArry) {
                                        int rowIdx = JTableUtil.getRealRowPos(ii, queryResultTable);
                                        Object v = JTableUtil.newInstance(queryResultTable).getValueAt(false, rowIdx, 0);
                                        if (v instanceof JToggleButton) {
                                            rowLst.add(rowIdx);
                                            rowLst.add(-1);
                                            rowLabelLst.add(((JToggleButton) v).getText());
                                            rowLabelLst.add("NA");
                                            rows.add(JTableUtil.getRowData(rowIdx, new int[] { 0 }, queryResultTable));
                                            rows.add(Arrays.asList(new Object[rows.get(0).size()]));
                                        } else if (!(v instanceof JToggleButton)) {
                                            rowLst.add(rowIdx);
                                            rowLst.add(-1);
                                            rowLabelLst.add(String.valueOf(rowIdx));
                                            rowLabelLst.add("NA");
                                            rows.add(JTableUtil.getRowData(rowIdx, new int[] {}, queryResultTable));
                                            rows.add(Arrays.asList(new Object[rows.get(0).size()]));
                                            isNotIgnoreFirstColumn = true;
                                        }
                                    }
                                }
                            }

                            if (rowLst.size() != 2) {
                                JCommonUtil._jOptionPane_showMessageDialog_error("請選擇兩筆!");
                                return;
                            }
                            List<String> titles = JTableUtil.newInstance(queryResultTable).getColumnTitleStringArray(new int[] { 0 });
                            if (isNotIgnoreFirstColumn) {
                                titles = JTableUtil.newInstance(queryResultTable).getColumnTitleStringArray(new int[] {});
                            }
                            if (mFastDBQueryUI_RowCompareDlg_Ver2 != null) {
                                mFastDBQueryUI_RowCompareDlg_Ver2.dispose();
                            }
                            mFastDBQueryUI_RowCompareDlg_Ver2 = FastDBQueryUI_RowCompareDlg_Ver2.newInstance(titles, //
                                    "No." + rowLabelLst.get(0), "No." + rowLabelLst.get(1), //
                                    rows.get(0), rows.get(1), //
                                    new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                        }
                                    }, FastDBQueryUI.this);
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem("逆向產生SelectSQL", new ActionListener() {

                    private Class getValueClz(String column) {
                        for (int ii = 0; ii < queryList.getLeft().size(); ii++) {
                            if (StringUtils.equalsIgnoreCase(column, queryList.getLeft().get(ii))) {
                                return queryList.getMiddle().get(ii);
                            }
                        }
                        return String.class;
                    }

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            DefaultTableModel model = JTableUtil.newInstance(queryResultTable).getModel();

                            List<List<Triple<String, Class, Object>>> sqlLst = new ArrayList<List<Triple<String, Class, Object>>>();
                            for (int row : queryResultTable.getSelectedRows()) {
                                int rowPos = JTableUtil.getRealRowPos(row, queryResultTable);
                                List<Object> columnLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                                List<Triple<String, Class, Object>> innerSqlLst = new ArrayList<Triple<String, Class, Object>>();
                                for (int jj = 0; jj < columnLst.size(); jj++) {
                                    String columnN = String.valueOf(columnLst.get(jj));
                                    if (QUERY_RESULT_COLUMN_NO.equals(columnN)) {
                                        continue;
                                    }
                                    for (int ii = 0; ii < model.getColumnCount(); ii++) {
                                        String column = model.getColumnName(ii);
                                        if (StringUtils.equals(column, columnN)) {
                                            Object value = model.getValueAt(rowPos, ii);
                                            innerSqlLst.add(Triple.of(column, getValueClz(column), value));
                                        }
                                    }
                                }
                                sqlLst.add(innerSqlLst);
                            }

                            if (mFastDBQueryUI_ReserveSqlDlg != null) {
                                mFastDBQueryUI_ReserveSqlDlg.dispose();
                            }
                            mFastDBQueryUI_ReserveSqlDlg = FastDBQueryUI_ReserveSqlDlg.newInstance(getRandom_TableNSchema(), sqlLst);
                            mFastDBQueryUI_ReserveSqlDlg.show();
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem("逆向產生SelectSQL [替換SQL條件]", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new ToStringReplaceOldSql().execute("selectIndex");
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem("逆向設定參數", new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new ToStringReplaceParameterTable().execute("selectIndex");
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        }
                    }
                });//

                ppap.addJMenuItem(addBase64Menus())//
                        .applyEvent(e)//
                        .show();
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private void addKeepSelectionOnly(JPopupMenuUtil ppap) {
        try {
            class SelectRowIdxProc {
                void execute(final int[] selectRowIdxArry) {
                    List<Object[]> sourceLst = queryList.getRight();
                    if (filterRowsQueryList != null) {
                        sourceLst = filterRowsQueryList.getRight();
                    }
                    List<Object[]> newLst = new ArrayList<Object[]>();
                    for (int ii = 0; ii < selectRowIdxArry.length; ii++) {
                        int queryListIdx = transRealRowToQuyerLstIndex(selectRowIdxArry[ii], sourceLst);
                        newLst.add(sourceLst.get(queryListIdx));
                    }
                    Triple<List<String>, List<Class<?>>, List<Object[]>> newLstForChoice = Triple.of(queryList.getLeft(), queryList.getMiddle(), newLst);
                    queryModeProcess(newLstForChoice, true, null, null);//
                    filterRowsQueryList = newLstForChoice;
                    isResetQuery = false;
                }
            }

            final int[] selectRowIdxArry = JTableUtil.newInstance(queryResultTable).getSelectedRows(false);
            final int[] selectColIdxArry = JTableUtil.newInstance(queryResultTable).getSelectedColumns(false);
            JMenuAppender chdMenu = JMenuAppender.newInstance("保留已選欄/列");
            chdMenu.addMenuItem("只保留已選列 :" + selectRowIdxArry.length, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SelectRowIdxProc().execute(selectRowIdxArry);
                }
            });
            chdMenu.addMenuItem("只保留已選欄 :" + selectColIdxArry.length, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        strLst.add("/^" + String.valueOf(colLst.get(selectColIdxArry[jj])) + "$/");
                    }
                    columnFilterText.setText(StringUtils.join(strLst, "^"));
                }
            });
            chdMenu.addMenuItem("*只保留已「勾」選列*", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Integer> rowLst = new ArrayList<Integer>();
                    for (int ii = 0; ii < queryResultTable.getRowCount(); ii++) {
                        int rowIdx = JTableUtil.getRealRowPos(ii, queryResultTable);
                        Object v = JTableUtil.newInstance(queryResultTable).getValueAt(false, rowIdx, 0);
                        if (v instanceof JToggleButton && ((JToggleButton) v).isSelected()) {
                            rowLst.add(rowIdx);
                        }
                    }
                    new SelectRowIdxProc().execute(ArrayUtils.toPrimitive(rowLst.toArray(new Integer[0])));
                }
            });
            ppap.addJMenuItem(chdMenu.getMenu());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getAliasFromSql(String sql, String column) {
        Pattern ptn = Pattern.compile("(\\w+)\\." + column);
        Matcher mth = ptn.matcher(sql);
        if (mth.find()) {
            return mth.group(1);
        }
        return "";
    }

    private void addSelectionTitle(JPopupMenuUtil ppap) {
        try {
            final String SQL = getCurrentSQL();
            final int[] selectColIdxArry = JTableUtil.newInstance(queryResultTable).getSelectedColumns(true);
            JMenuAppender chdMenu = JMenuAppender.newInstance("欄位Column複製");
            chdMenu.addMenuItem("複製欄位逗號隔開", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        String alias = getAliasFromSql(SQL, column);
                        if (StringUtils.isNotBlank(alias)) {
                            alias = alias + ".";
                        }
                        strLst.add(alias + column);
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, ", "), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位換行", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        String alias = getAliasFromSql(SQL, column);
                        if (StringUtils.isNotBlank(alias)) {
                            alias = alias + ".";
                        }
                        strLst.add(alias + column);
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, "\r\n"), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位逗號隔開 java", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        strLst.add(StringUtilForDb.dbFieldToJava_smartCheck(column));
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, ", "), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位換行 java", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        strLst.add(StringUtilForDb.dbFieldToJava_smartCheck(column));
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, "\r\n"), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位setter", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        column = StringUtilForDb.dbFieldToJava_smartCheck(column);
                        column = "set" + StringUtils.capitalize(column) + "( XXXXXXXXXX );";
                        strLst.add("vo." + column);
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, "\r\n"), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位setter copy", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Object> colLst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    List<String> strLst = new ArrayList<String>();
                    for (int jj = 0; jj < selectColIdxArry.length; jj++) {
                        String column = StringUtils.trimToEmpty(String.valueOf(colLst.get(selectColIdxArry[jj])));
                        column = StringUtilForDb.dbFieldToJava_smartCheck(column);
                        String resultStr = "vo.set" + StringUtils.capitalize(column) + "(" + "vo2.get" + StringUtils.capitalize(column) + "()" + ");";
                        strLst.add(resultStr);
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, "\r\n"), "", null).show();
                }
            });
            chdMenu.addMenuItem("複製欄位setter 設值", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTableUtil util = JTableUtil.newInstance(queryResultTable);
                    List<String> strLst = new ArrayList<String>();
                    int rowIdx = queryResultTable.getSelectedRow();
                    TableColumnModel titleModel = queryResultTable.getTableHeader().getColumnModel();
                    for (int ii = 0; ii < titleModel.getColumnCount(); ii++) {
                        if (!ArrayUtils.contains(selectColIdxArry, ii)) {
                            continue;
                        }
                        TableColumn col = titleModel.getColumn(ii);
                        String column = String.valueOf(col.getHeaderValue());
                        if (QUERY_RESULT_COLUMN_NO.equals(column)) {
                            continue;
                        }
                        column = StringUtils.trimToEmpty(column);
                        column = StringUtilForDb.dbFieldToJava_smartCheck(column);
                        Object value = util.getValueAt(false, JTableUtil.getRealRowPos(rowIdx, queryResultTable), ii);
                        String resultStr = "vo.set" + StringUtils.capitalize(column) + "(\"" + value + "\");";
                        strLst.add(resultStr);
                    }
                    SimpleTextDlg.newInstance(StringUtils.join(strLst, "\r\n"), "", null).show();
                }
            });
            chdMenu.addJMenuItem("產生IN (...)", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        JTableUtil utl = JTableUtil.newInstance(queryResultTable);
                        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                        int[] colums = utl.getSelectedColumns(true);
                        int[] rows = utl.getRealSelectedRows(true);
                        for (int ii = 0; ii < rows.length; ii++) {
                            int row = rows[ii];
                            Object val = utl.getValueAt(false, row, colums[0]);
                            String strVal = "";
                            if (val != null) {
                                strVal = String.valueOf(val);
                            }
                            if (ii != rows.length - 1) {
                                sb.append("'" + strVal + "', ");
                            } else {
                                sb.append("'" + strVal + "'  ");
                            }
                        }
                        String sql = "(" + sb.toString() + ")";
                        SimpleTextDlg.newInstance(sql, "", null).show();
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//
            chdMenu.addJMenuItem("產生IN (...) [無引號]", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        JTableUtil utl = JTableUtil.newInstance(queryResultTable);
                        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                        int[] colums = utl.getSelectedColumns(true);
                        int[] rows = utl.getRealSelectedRows(true);
                        for (int ii = 0; ii < rows.length; ii++) {
                            int row = rows[ii];
                            Object val = utl.getValueAt(false, row, colums[0]);
                            String strVal = "";
                            if (val != null) {
                                strVal = String.valueOf(val);
                            }
                            if (ii != rows.length - 1) {
                                sb.append("" + strVal + ", ");
                            } else {
                                sb.append("" + strVal + "  ");
                            }
                        }
                        String sql = "(" + sb.toString() + ")";
                        SimpleTextDlg.newInstance(sql, "", null).show();
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//
            chdMenu.addJMenuItem("加總SUM", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BigDecimal total = BigDecimal.ZERO;
                        JTableUtil utl = JTableUtil.newInstance(queryResultTable);
                        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                        int[] colums = utl.getSelectedColumns(true);
                        int[] rows = utl.getRealSelectedRows(true);
                        for (int ii = 0; ii < rows.length; ii++) {
                            int row = rows[ii];
                            Object val = utl.getValueAt(false, row, colums[0]);
                            String strVal = "";
                            if (val != null) {
                                strVal = String.valueOf(val);
                            }
                            BigDecimal bigVal = new BigDecimal(strVal);
                            total = total.add(bigVal);
                        }
                        SimpleTextDlg.newInstance(total, "", null).show();
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//
            chdMenu.addJMenuItem("加總SUM [整列]", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BigDecimal total = BigDecimal.ZERO;
                        JTableUtil utl = JTableUtil.newInstance(queryResultTable);
                        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                        int[] colums = utl.getSelectedColumns(true);
                        // int[] rows = utl.getRealSelectedRows(true);
                        for (int ii = 0; ii < queryResultTable.getRowCount(); ii++) {
                            int row = ii;// rows[ii]
                            Object val = utl.getValueAt(false, row, colums[0]);
                            String strVal = "";
                            if (val != null) {
                                strVal = String.valueOf(val);
                            }
                            BigDecimal bigVal = new BigDecimal(strVal);
                            total = total.add(bigVal);
                        }
                        SimpleTextDlg.newInstance(total, "", null).show();
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//
            ppap.addJMenuItem(chdMenu.getMenu());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private class JMenuItem_BasicMenu {
        SimpleTextDlg mSimpleTextDlg = null;
        JMenuItem item;

        JMenuItem_BasicMenu() {
            mSimpleTextDlg = SimpleTextDlg.newInstance(JTableUtil.newInstance(queryResultTable).getSelectedValue(), "", null);
            item = new JMenuItem("此資料長度 : " + mSimpleTextDlg.getMessage().getBytes().length);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    run();
                }
            });
        }

        JMenuItem getItem() {
            return item;
        }

        void run() {
            mSimpleTextDlg.show();
        }
    }

    private JMenu addBase64Menus() {
        JMenuAppender chdMenu = JMenuAppender.newInstance("Base64");
        chdMenu.addMenuItem("Encode", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object val = JTableUtil.newInstance(queryResultTable).getSelectedValue();
                if (val != null) {
                    String strVal = String.valueOf(val);
                    String decodeVal = Base64JdkUtil.encode(strVal);
                    SimpleTextDlg_Ver2.newInstance(decodeVal, "Base64Encode:" + strVal, new Dimension(450, 300)).show();
                }
            }
        });
        chdMenu.addMenuItem("Decode", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object val = JTableUtil.newInstance(queryResultTable).getSelectedValue();
                if (val != null) {
                    String strVal = String.valueOf(val);
                    String decodeVal = Base64JdkUtil.decodeToString(strVal);
                    SimpleTextDlg_Ver2.newInstance(decodeVal, "Base64Encode:" + strVal, new Dimension(450, 300)).show();
                }
            }
        });
        return chdMenu.getMenu();
    }

    public String getRandom_TableNSchema() {
        Pattern ptn = Pattern.compile("from\\s+(\\w+[\\.\\w]+|\\w+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        String sql = StringUtils.trimToEmpty(currentSQL.get());
        if (StringUtils.isBlank(sql)) {
            sql = sqlTextArea.getText();
        }
        Matcher mth = ptn.matcher(sql);
        if (mth.find()) {
            return mth.group(1);
        }
        return "";
    }

    public List<String> getRandom_TableNSchemaLst() {
        Pattern ptn = Pattern.compile("from\\s+(\\w+[\\.\\w]+|\\w+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Pattern ptn1 = Pattern.compile("join\\s+(\\w+[\\.\\w]+|\\w+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        String sql = StringUtils.trimToEmpty(currentSQL.get());
        if (StringUtils.isBlank(sql)) {
            sql = sqlTextArea.getText();
        }
        Set<String> rtnLst = new LinkedHashSet<String>();
        Matcher mth = ptn.matcher(sql);
        while (mth.find()) {
            rtnLst.add(mth.group(1));
        }
        mth = ptn1.matcher(sql);
        while (mth.find()) {
            rtnLst.add(mth.group(1));
        }
        return new ArrayList<String>(rtnLst);
    }

    private boolean isColumnNoExists() {
        JTableUtil util = JTableUtil.newInstance(queryResultTable);
        List<Object> cols = util.getColumnTitleArray();
        if (!cols.isEmpty() && QUERY_RESULT_COLUMN_NO.equals(String.valueOf(cols.get(0)))) {
            return true;
        }
        return false;
    }

    private Pair<List<String>, List<Object[]>> transRealRowToQuyerLstIndex() {
        TreeMap<Integer, String> columnMapping = getQueryResult_ColumnDefine();
        List<String> leftLst = new ArrayList<String>(columnMapping.values());
        JTableUtil util = JTableUtil.newInstance(queryResultTable);
        boolean removeNoColumn = isColumnNoExists();
        // 如果是使用 excel 匯入 需要重組 資料
        List<Object[]> rightRowsLit = new ArrayList<Object[]>();
        for (int row = 0; row < queryResultTable.getRowCount(); row++) {
            TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
            A: for (int col = 0; col < queryResultTable.getColumnCount(); col++) {
                int realCol = util.getRealColumnPos(col, queryResultTable);
                int realRow = util.getRealRowPos(row, queryResultTable);
                Object value = util.getModel().getValueAt(realRow, realCol);

                if (removeNoColumn && realCol == 0) {
                    continue A;
                }
                map.put(realCol, value);
            }
            rightRowsLit.add(map.values().toArray());
        }
        return Pair.of(leftLst, rightRowsLit);
    }

    private TreeMap<Integer, String> getQueryResult_ColumnDefine() {
        TreeMap<Integer, String> columnMapping = new TreeMap<Integer, String>();
        JTableUtil util = JTableUtil.newInstance(queryResultTable);

        for (int ii = 0; ii < queryResultTable.getColumnCount(); ii++) {
            TableColumn column = queryResultTable.getTableHeader().getColumnModel().getColumn(ii);
            String columnHeader = (String) column.getHeaderValue();

            if (this.queryList != null && !this.queryList.getLeft().isEmpty()) {
                for (int jj = 0; jj < this.queryList.getLeft().size(); jj++) {
                    String columnHeader2 = this.queryList.getLeft().get(jj);
                    if (!columnMapping.containsKey(jj) && columnHeader.equalsIgnoreCase(columnHeader2)) {
                        columnMapping.put(jj, columnHeader2);
                    }
                }
            } else {
                columnMapping.put(ii, columnHeader);
            }
        }
        System.out.println(columnMapping);
        return columnMapping;
    }

    private int transRealRowToQuyerLstIndex(int realRow, List<Object[]> sourceLst) {
        JTableUtil util = JTableUtil.newInstance(queryResultTable);
        TreeMap<Integer, String> columnMapping = getQueryResult_ColumnDefine();

        // 如果是使用 excel 匯入 需要重組 資料
        TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
        for (int col = 0; col < queryResultTable.getColumnCount(); col++) {
            int realCol = util.getRealColumnPos(col, queryResultTable);
            Object value = util.getModel().getValueAt(realRow, realCol);
            map.put(realCol, value);
        }
        // 移除按第一個鈕欄
        if (isColumnNoExists()) {
            map.remove(0);
        }

        // 用來比較取得row index用
        List<Object[]> newLst = new ArrayList<Object[]>();
        for (int row = 0; row < sourceLst.size(); row++) {
            Object[] oldArry = sourceLst.get(row);
            System.out.println(Arrays.toString(oldArry));
            if (oldArry[0] instanceof JToggleButton) {
                oldArry = ArrayUtils.remove(oldArry, 0);
            }
            List<Object> newArry = new ArrayList<Object>();
            for (int columnPos : columnMapping.keySet()) {
                newArry.add(oldArry[columnPos]);
            }
            newLst.add(newArry.toArray());
        }

        Object[] arry = map.values().toArray();
        return isContainObjectArray_Index(newLst, arry);
    }

    private Triple<List<String>, List<Class<?>>, List<Object[]>> getCurrentQueryResultTableLst() {
        JTableUtil util = JTableUtil.newInstance(queryResultTable);
        TreeMap<Integer, String> columnMapping = getQueryResult_ColumnDefine();

        System.out.println("columnMapping------>>> " + columnMapping);

        List<String> titleLst = new ArrayList<String>();
        List<Class<?>> dataClzLst = new ArrayList<Class<?>>();
        List<Object[]> dataLst = new ArrayList<Object[]>();

        for (int row = 0; row < queryResultTable.getRowCount(); row++) {
            int realRow = util.getRealRowPos(row, queryResultTable);

            // 如果是使用 excel 匯入 需要重組 資料
            TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
            for (int col = 0; col < queryResultTable.getColumnCount(); col++) {
                // int realCol = util.getRealColumnPos(col, queryResultTable);
                int realCol = col;
                Object value = util.getModel().getValueAt(realRow, realCol);
                map.put(realCol, value);
            }

            // 移除按第一個鈕欄
            if (isColumnNoExists()) {
                map.remove(0);
            }

            System.out.println("map------>>> " + map);

            if (titleLst.isEmpty() && dataClzLst.isEmpty()) {
                for (String column : columnMapping.values()) {
                    titleLst.add(column);
                    dataClzLst.add(String.class);
                }
            }
            dataLst.add(map.values().toArray());
        }
        return Triple.of(titleLst, dataClzLst, dataLst);
    }

    private Map<String, Pair<Object, Class>> getDetailToMap(int queryListIndex) {
        Map<String, Pair<Object, Class>> rtnMap = new LinkedHashMap<String, Pair<Object, Class>>();
        List<String> columns = queryList.getLeft();
        if (queryListIndex == -1) {
            if (!columns.isEmpty()) {
                for (String col : columns) {
                    Object obj = StringUtils.EMPTY;
                    Class clz = String.class;
                    rtnMap.put(col, Pair.of(obj, clz));
                }
                return rtnMap;
            }
        }

        Object[] row = queryList.getRight().get(queryListIndex);
        if (row[0] instanceof JToggleButton) {
            row = ArrayUtils.remove(row, 0);
        }
        List<Class<?>> clzOrignLst = queryList.getMiddle();

        Map<String, List<Object>> multiMap = new LinkedHashMap<String, List<Object>>();
        Map<String, List<Class>> multiClzMap = new LinkedHashMap<String, List<Class>>();
        for (int ii = 0; ii < columns.size(); ii++) {
            String col = columns.get(ii);
            Object val = row[ii];
            List<Object> valueLst = new ArrayList<Object>();
            if (multiMap.containsKey(col)) {
                valueLst = multiMap.get(col);
            }
            valueLst.add(val);
            multiMap.put(col, valueLst);

            List<Class> clzLst = new ArrayList<Class>();
            if (multiClzMap.containsKey(col)) {
                clzLst = multiClzMap.get(col);
            }
            clzLst.add(clzOrignLst.get(ii));
            multiClzMap.put(col, clzLst);
        }

        for (String col : multiMap.keySet()) {
            List<Object> valueLst = multiMap.get(col);
            List<Class> clzLst = multiClzMap.get(col);
            if (valueLst.size() == 1) {
                rtnMap.put(col, Pair.of(valueLst.get(0), clzLst.get(0)));
            } else {
                if (!ListUtil.isAllEquals(valueLst)) {
                    Object value = JCommonUtil._JOptionPane_showInputDialog("此欄位[" + col + "]顯示多次,請選擇正確的值:", col, valueLst.toArray(), valueLst.get(0));
                    int pos = valueLst.indexOf(value);
                    Class clz = clzLst.get(pos);
                    rtnMap.put(col, Pair.of(value, clz));
                } else {
                    rtnMap.put(col, Pair.of(valueLst.get(0), clzLst.get(0)));
                }
            }
        }
        return rtnMap;
    }

    private int getColumnIndicateIndex(int columnLstIdx, List<String> columns, String column) {
        for (int ii = 0; ii < columns.size(); ii++) {
            if (StringUtils.equalsIgnoreCase(columns.get(ii), column)) {
                return ii;
            }
        }
        return -1;
    }

    private void excelImportAllSheetsIntoOneModel_CSV(final File csvFile) {
        // /Volumes/Transcend/janna_work/cazp_insert.csv
        final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "import excel");
        prog.max(100);
        prog.limitMoveBound(false);
        prog.modal(false);
        prog.closeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                prog.setExitFlag(true);
            }
        });
        prog.build();
        prog.show();

        new Thread(new Runnable() {

            private String fixForCell(String value) {
                if (value.startsWith("'") && value.endsWith("'")) {
                    return value.substring(1, value.length() - 1);
                }
                if ("(null)".equals(value)) {
                    return "null";
                }
                return value;
            }

            private String[] fixRowData(String[] arry) {
                List<String> lst = new ArrayList<String>();
                for (int ii = 0; ii < arry.length; ii++) {
                    lst.add(fixForCell(arry[ii]));
                }
                return lst.toArray(new String[0]);
            }

            @Override
            public void run() {
                List<String> titles2 = new ArrayList<String>();
                List<Object[]> rowLst = new ArrayList<Object[]>();

                try {
                    CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), "BIG5"));
                    List<String[]> ls = reader.readAll();
                    reader.close();

                    if (!ls.isEmpty()) {
                        String[] ts = ls.get(0);
                        for (String t : ts) {
                            titles2.add(StringUtils.trimToEmpty(t));
                        }

                        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                        model = JTableUtil.createModel(true, titles2.toArray());
                        queryResultTable.setModel(model);

                        if (ls.size() > 2) {
                            for (int ii = 1; ii < ls.size(); ii++) {
                                String[] arry = ls.get(ii);
                                arry = fixRowData(arry);
                                model.addRow(arry);
                                rowLst.add(arry);

                                if (prog.isExitFlag()) {
                                    return;
                                }
                            }
                        }
                    }

                    if (prog.isExitFlag()) {
                        return;
                    }

                    Class<?>[] clzs = new Class[titles2.size()];
                    Arrays.fill(clzs, String.class);
                    List<Class<?>> clzLst = Arrays.asList(clzs);
                    queryList = Triple.of(titles2, clzLst, rowLst);

                    queryResultCountLabel.setText(String.valueOf(queryList.getRight().size()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                prog.dismissByMax();
            }
        }, "---thread1").start();
    }

    private void excelImportAllSheetsIntoOneModel(File xlsfile) {
        final ExcelUtil_Xls97 exlUtl = ExcelUtil_Xls97.getInstance();
        final HSSFWorkbook wk = exlUtl.readExcel(xlsfile);

        final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "import excel");
        prog.max(wk.getNumberOfSheets());
        prog.limitMoveBound(false);
        prog.modal(false);
        prog.closeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                prog.setExitFlag(true);
            }
        });
        prog.build();
        prog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> titles2 = new ArrayList<String>();
                List<Object[]> rowLst = new ArrayList<Object[]>();

                for (int kk = 0; kk < wk.getNumberOfSheets(); kk++) {
                    HSSFSheet sheet = wk.getSheetAt(kk);
                    for (int ii = 0; ii <= 0; ii++) {
                        Row row = sheet.getRow(ii);
                        for (int jj = 0; jj < row.getLastCellNum(); jj++) {
                            String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(jj));
                            if (!titles2.contains(value)) {
                                titles2.add(value);
                            }
                        }
                    }
                }

                DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
                model = JTableUtil.createModel(true, titles2.toArray());
                queryResultTable.setModel(model);

                for (int kk = 0; kk < wk.getNumberOfSheets(); kk++) {
                    HSSFSheet sheet = wk.getSheetAt(kk);

                    Map<String, Integer> titleMap = new LinkedHashMap<String, Integer>();
                    for (String shName : titles2) {
                        titleMap.put(shName, null);
                    }

                    for (int ii = 0; ii <= 0; ii++) {
                        Row row = sheet.getRow(ii);
                        for (int jj = 0; jj < row.getLastCellNum(); jj++) {
                            String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(jj));
                            if (titles2.contains(value)) {
                                titleMap.put(value, titles2.indexOf(value));
                            }
                        }
                    }

                    for (int ii = 1; ii <= sheet.getLastRowNum(); ii++) {
                        Row row = sheet.getRow(ii);
                        if (row == null) {
                            continue;
                        }

                        List<Object> rows = new ArrayList<Object>();

                        for (String title : titleMap.keySet()) {
                            if (titleMap.get(title) == null) {
                                rows.add("");
                            } else {
                                String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(titleMap.get(title)));
                                rows.add(value);
                            }
                        }

                        model.addRow(rows.toArray());
                        rowLst.add(rows.toArray());

                        if (prog.isExitFlag()) {
                            return;
                        }
                    }

                    prog.addOne();
                    if (prog.isExitFlag()) {
                        return;
                    }
                }

                Class<?>[] clzs = new Class[titles2.size()];
                Arrays.fill(clzs, String.class);
                List<Class<?>> clzLst = Arrays.asList(clzs);
                queryList = Triple.of(titles2, clzLst, rowLst);

                queryResultCountLabel.setText(String.valueOf(queryList.getRight().size()));

                prog.dismissByMax();
            }
        }, "---thread1").start();
    }

    private void excelImportSingleSheet(File xlsfile) {
        final ExcelUtil_Xls97 exlUtl = ExcelUtil_Xls97.getInstance();
        // 選擇sheet
        final HSSFWorkbook wk = exlUtl.readExcel(xlsfile);
        List<String> shLst = new ArrayList<String>();
        for (int ii = 0; ii < wk.getNumberOfSheets(); ii++) {
            HSSFSheet sh = wk.getSheetAt(ii);
            shLst.add(sh.getSheetName());
        }
        importExcelSheetName = (String) JCommonUtil._JOptionPane_showInputDialog(//
                "請選擇sheet,共[" + shLst.size() + "]個", "選擇sheet", shLst.toArray(), shLst.get(0));
        if (StringUtils.isBlank(importExcelSheetName)) {
            JCommonUtil._jOptionPane_showMessageDialog_info("sheetname 錯誤!");
            return;
        }

        final HSSFSheet sheet = wk.getSheet(importExcelSheetName);

        final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "import excel");
        prog.max(sheet.getLastRowNum());
        prog.limitMoveBound(false);
        prog.modal(false);
        prog.closeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                prog.setExitFlag(true);
            }
        });
        prog.build();
        prog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();

                List<String> titles2 = new ArrayList<String>();
                List<Object[]> rowLst = new ArrayList<Object[]>();

                for (int ii = 0; ii <= 0; ii++) {
                    Row row = sheet.getRow(ii);
                    List<Object> titles = new ArrayList<Object>();
                    for (int jj = 0; jj < row.getLastCellNum(); jj++) {
                        String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(jj));
                        titles.add(value);
                        titles2.add(value);
                    }
                }

                if (!radio_import_excel_isAppend.isSelected()) {
                    model = JTableUtil.createModel(true, titles2.toArray());
                    queryResultTable.setModel(model);
                    JTableUtil.newInstance(queryResultTable).setRowHeightByFontSize();

                    for (int ii = 1; ii <= sheet.getLastRowNum(); ii++) {
                        Row row = sheet.getRow(ii);
                        if (row == null) {
                            continue;
                        }
                        List<Object> rows = new ArrayList<Object>();
                        for (int jj = 0; jj < row.getLastCellNum(); jj++) {
                            String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(jj));
                            rows.add(value);
                        }
                        for (int fixSize = titles2.size() - rows.size(); fixSize > 0; fixSize--) {
                            rows.add("");
                        }
                        model.addRow(rows.toArray());
                        rowLst.add(rows.toArray());
                        prog.addOne();

                        if (prog.isExitFlag()) {
                            return;
                        }
                    }

                    Class<?>[] clzs = new Class[titles2.size()];
                    Arrays.fill(clzs, String.class);
                    List<Class<?>> clzLst = Arrays.asList(clzs);
                    queryList = Triple.of(titles2, clzLst, rowLst);
                    queryResultCountLabel.setText(String.valueOf(queryList.getRight().size()));

                    setQueryResultTableColumnsWidth();

                } else {
                    List<Object> titles = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                    Map<Integer, Integer> titlesMap = new TreeMap<Integer, Integer>();
                    A: for (int jj = 0; jj < titles2.size(); jj++) {
                        for (int ii = 0; ii < titles.size(); ii++) {
                            if (StringUtils.equalsIgnoreCase(String.valueOf(titles.get(ii)), titles2.get(jj))) {
                                titlesMap.put(ii, jj);
                                continue A;
                            }
                        }
                    }

                    boolean isButtonStart = false;
                    if (!titles.isEmpty() && StringUtils.equals(QUERY_RESULT_COLUMN_NO, String.valueOf(titles.get(0)))) {
                        isButtonStart = true;
                    }

                    for (int ii = 1; ii <= sheet.getLastRowNum(); ii++) {
                        Row row = sheet.getRow(ii);
                        if (row == null) {
                            continue;
                        }
                        List<Object> rows = new ArrayList<Object>();
                        if (isButtonStart) {
                            rows.add(createSelectionBtn("*" + ii));
                        }
                        for (int jj : titlesMap.values()) {
                            String value = ExcelUtil_Xls97.getInstance().readCell(row.getCell(jj));
                            rows.add(value);
                        }
                        model.addRow(rows.toArray());
                        rowLst.add(rows.toArray());
                        prog.addOne();

                        if (prog.isExitFlag()) {
                            return;
                        }
                    }

                    queryList.getRight().addAll(rowLst);
                    queryResultCountLabel.setText(String.valueOf(queryList.getRight().size()));
                }

                prog.dismiss();
            }
        }, "---thread1").start();
    }

    private void excelImportSheetMaster(File xlsfile) {
        if (xlsfile.getName().endsWith(".xls")) {
            List<String> choices = new ArrayList<String>();
            choices.add("單一sheet");
            choices.add("所有sheet匯入在一起");
            String importStyle = (String) JCommonUtil._JOptionPane_showInputDialog(//
                    "請選擇匯入方式?", "單一或全部", choices.toArray(), choices.get(0));
            if (StringUtils.isBlank(importStyle)) {
                JCommonUtil._jOptionPane_showMessageDialog_info("匯入選擇錯誤!");
                return;
            }

            if (choices.get(0).equalsIgnoreCase(importStyle)) {
                excelImportSingleSheet(xlsfile);
            } else if (choices.get(1).equalsIgnoreCase(importStyle)) {
                excelImportAllSheetsIntoOneModel(xlsfile);
            }
        } else if (xlsfile.getName().endsWith(".csv")) {
            excelImportAllSheetsIntoOneModel_CSV(xlsfile);
        } else {
            new ImportFromClipboard().parseMain_fromFile(null, xlsfile);
        }
    }

    private String filterChangeLine(String text) {
        char[] arry = StringUtils.defaultString(text).toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : arry) {
            if (c == '\n') {
                sb.append("_#line#_");// 在word裡取代為 ^l or ^p 可呈現原來樣貌
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void excelExportBtnAction() {
        try {
            final ExcelUtil_Xls97 exlUtl = ExcelUtil_Xls97.getInstance();
            AbstractButton selBtn = JButtonGroupUtil.getSelectedButton(btnExcelBtn);
            if (radio_import_excel == selBtn) {
                File xlsfile = JCommonUtil._jFileChooser_selectFileOnly();
                // if (!xlsfile.exists() || !xlsfile.getName().endsWith(".xls"))
                // {
                // JCommonUtil._jOptionPane_showMessageDialog_info("檔案錯誤(.xls)!");
                // return;
                // }
                this.excelImportSheetMaster(xlsfile);

            } else if (radio_import_clipboard == selBtn) {
                new ImportFromClipboard().parseMain(null);

            } else if (radio_export_excel == selBtn) {
                final AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>> tmpQueryList = new AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>>();
                if (filterRowsQueryList != null && !isResetQuery) {
                    tmpQueryList.set(filterRowsQueryList);
                } else if (queryList != null) {
                    tmpQueryList.set(queryList);
                }

                if (tmpQueryList.get() == null || tmpQueryList.get().getRight().isEmpty()) {
                    JCommonUtil._jOptionPane_showMessageDialog_info("沒有資料!");
                    return;
                }

                final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "export excel");
                prog.max(tmpQueryList.get().getRight().size());
                prog.limitMoveBound(false);
                prog.modal(false);
                prog.closeListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        prog.setExitFlag(true);
                    }
                });
                prog.build();
                prog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> columnLst = new ArrayList<String>();
                        if (StringUtils.isNotBlank(columnFilterText.getText())) {
                            List<Object> lst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                            for (Object v : lst) {
                                String name = String.valueOf(v);
                                if (QUERY_RESULT_COLUMN_NO.equals(name)) {
                                    continue;
                                }
                                columnLst.add(name);
                            }
                        }

                        HSSFWorkbook wk = new HSSFWorkbook();
                        HSSFSheet sheet1 = wk.createSheet("orign value sheet");
                        HSSFSheet sheet0 = wk.createSheet("string value sheet");

                        // 寫sql
                        {
                            appendExcelSQLSheet(wk);
                        }

                        // 寫資料
                        CellStyleHandler titleCs = ExcelWriter.CellStyleHandler.newInstance(wk.createCellStyle())//
                                .setForegroundColor(new HSSFColor.LIGHT_GREEN());
                        List<String> columns = new ArrayList<String>(tmpQueryList.get().getLeft());
                        HSSFRow titleRow0 = sheet0.createRow(0);
                        HSSFRow titleRow1 = sheet1.createRow(0);
                        if (columnLst.isEmpty()) {
                            for (int ii = 0; ii < columns.size(); ii++) {
                                exlUtl.setCellValue(exlUtl.getCellChk(titleRow0, ii), columns.get(ii));
                                titleCs.applyStyle(exlUtl.getCellChk(titleRow0, ii));
                            }
                            for (int ii = 0; ii < columns.size(); ii++) {
                                exlUtl.setCellValue(exlUtl.getCellChk(titleRow1, ii), columns.get(ii));
                                titleCs.applyStyle(exlUtl.getCellChk(titleRow1, ii));
                            }
                        } else {
                            for (int ii = 0; ii < columnLst.size(); ii++) {
                                exlUtl.setCellValue(exlUtl.getCellChk(titleRow0, ii), columnLst.get(ii));
                                titleCs.applyStyle(exlUtl.getCellChk(titleRow0, ii));
                            }
                            for (int ii = 0; ii < columnLst.size(); ii++) {
                                exlUtl.setCellValue(exlUtl.getCellChk(titleRow1, ii), columnLst.get(ii));
                                titleCs.applyStyle(exlUtl.getCellChk(titleRow1, ii));
                            }
                        }

                        // 加入中文解釋
                        int paddingCount = 1;
                        try {
                            if (mTableColumnDefTextHandler != null) {
                                String table = tableColumnDefText_Auto.getTextComponent().getText();
                                Map<String, String> chineseMap = mTableColumnDefTextHandler.getColumnsAndChinese(table, false);
                                if (!chineseMap.isEmpty()) {
                                    paddingCount = 2;
                                    HSSFRow titleRow00 = sheet0.createRow(1);
                                    HSSFRow titleRow11 = sheet1.createRow(1);
                                    if (columnLst.isEmpty()) {
                                        for (int ii = 0; ii < columns.size(); ii++) {
                                            String chinese = StringUtils.trimToEmpty(chineseMap.get(columns.get(ii)));
                                            exlUtl.setCellValue(exlUtl.getCellChk(titleRow00, ii), chinese);
                                            titleCs.applyStyle(exlUtl.getCellChk(titleRow00, ii));
                                        }
                                        for (int ii = 0; ii < columns.size(); ii++) {
                                            String chinese = StringUtils.trimToEmpty(chineseMap.get(columns.get(ii)));
                                            exlUtl.setCellValue(exlUtl.getCellChk(titleRow11, ii), chinese);
                                            titleCs.applyStyle(exlUtl.getCellChk(titleRow11, ii));
                                        }
                                    } else {
                                        for (int ii = 0; ii < columnLst.size(); ii++) {
                                            String chinese = StringUtils.trimToEmpty(chineseMap.get(columns.get(ii)));
                                            exlUtl.setCellValue(exlUtl.getCellChk(titleRow00, ii), chinese);
                                            titleCs.applyStyle(exlUtl.getCellChk(titleRow00, ii));
                                        }
                                        for (int ii = 0; ii < columnLst.size(); ii++) {
                                            String chinese = StringUtils.trimToEmpty(chineseMap.get(columns.get(ii)));
                                            exlUtl.setCellValue(exlUtl.getCellChk(titleRow11, ii), chinese);
                                            titleCs.applyStyle(exlUtl.getCellChk(titleRow11, ii));
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                        }

                        if (columnLst.isEmpty()) {
                            for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                if (EXCEL_MAX_ROW_SIZE != null && ii + paddingCount > EXCEL_MAX_ROW_SIZE) {
                                    break;
                                }

                                Row row_string = sheet0.createRow(ii + paddingCount);
                                Row row_orign$ = sheet1.createRow(ii + paddingCount);

                                Object[] rows = tmpQueryList.get().getRight().get(ii);

                                Object[] rows2 = new Object[rows.length];
                                if (rows[0] instanceof JToggleButton) {
                                    System.arraycopy(rows, 1, rows2, 0, rows.length - 1);
                                    rows = rows2;
                                }

                                prog.addOne();
                                for (int jj = 0; jj < columns.size(); jj++) {
                                    String col = columns.get(jj);
                                    Object value = rows[jj];
                                    if (value == null && radio_export_excel_ignoreNull.isSelected()) {
                                        value = "";
                                    }
                                    
                                    exlUtl.setCellValue(exlUtl.getCellChk(row_string, jj), String.valueOf(value));
                                    exlUtl.setCellValue(exlUtl.getCellChk(row_orign$, jj), parseToNumberValueForExcel(value));

                                    if (prog.isExitFlag()) {
                                        return;
                                    }
                                }
                            }
                        } else {
                            for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                if (EXCEL_MAX_ROW_SIZE != null && ii + paddingCount > EXCEL_MAX_ROW_SIZE) {
                                    break;
                                }

                                Row row_string = sheet0.createRow(ii + paddingCount);
                                Row row_orign$ = sheet1.createRow(ii + paddingCount);

                                Object[] rows = tmpQueryList.get().getRight().get(ii);

                                Object[] rows2 = new Object[rows.length];
                                if (rows[0] instanceof JToggleButton) {
                                    System.arraycopy(rows, 1, rows2, 0, rows.length - 1);
                                    rows = rows2;
                                }

                                prog.addOne();
                                for (int jj = 0; jj < columnLst.size(); jj++) {
                                    String col = columnLst.get(jj);
                                    int newIdx = getColumnIndicateIndex(jj, columns, col);
                                    Object value = rows[newIdx];
                                    if (value == null && radio_export_excel_ignoreNull.isSelected()) {
                                        value = "";
                                    }
                                    exlUtl.setCellValue(exlUtl.getCellChk(row_string, jj), String.valueOf(value));
                                    exlUtl.setCellValue(exlUtl.getCellChk(row_orign$, jj), parseToNumberValueForExcel(value));

                                    if (prog.isExitFlag()) {
                                        return;
                                    }
                                }
                            }
                        }

                        exlUtl.autoCellSize(sheet0);
                        exlUtl.autoCellSize(sheet1);

                        prog.dismiss();

                        String filename = FastDBQueryUI.class.getSimpleName() + //
                        "_" + getRandom_TableNSchema() + "_" + //
                        "_" + StringUtils.trimToEmpty(sqlIdText.getText()) + "_" + //
                        DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd_HHmmss");
                        filename = JCommonUtil._jOptionPane_showInputDialog("儲存檔案", filename);
                        if (!filename.toLowerCase().endsWith(".xls")) {
                            filename += ".xls";
                        }
                        if (StringUtils.isNotBlank(filename) || !filename.endsWith(".xls")) {
                            File exportFile = new File(FileUtil.DESKTOP_DIR, filename);
                            exlUtl.writeExcelConfirmDlg(exportFile, wk, "匯出檔");
                        } else {
                            JCommonUtil._jOptionPane_showMessageDialog_info("檔名有誤!");
                        }
                    }
                }, "---thread1").start();
            } else if (radio_export_json == selBtn) {
                final AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>> tmpQueryList = new AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>>();
                if (filterRowsQueryList != null && !isResetQuery) {
                    tmpQueryList.set(filterRowsQueryList);
                } else if (queryList != null) {
                    tmpQueryList.set(queryList);
                }

                if (tmpQueryList.get() == null || tmpQueryList.get().getRight().isEmpty()) {
                    JCommonUtil._jOptionPane_showMessageDialog_info("沒有資料!");
                    return;
                }

                final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "export excel");
                prog.max(tmpQueryList.get().getRight().size());
                prog.limitMoveBound(false);
                prog.modal(false);
                prog.closeListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        prog.setExitFlag(true);
                    }
                });
                prog.build();
                prog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // showJsonArry(tmpQueryList.get(), Integer.MAX_VALUE,
                        // prog);
                        showJsonArry_Ver2(Integer.MAX_VALUE, prog);
                    }
                }, "---thread1").start();
            } else if (radio_export_csv == selBtn) {
                final AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>> tmpQueryList = new AtomicReference<Triple<List<String>, List<Class<?>>, List<Object[]>>>();
                if (filterRowsQueryList != null && !isResetQuery) {
                    tmpQueryList.set(filterRowsQueryList);
                } else if (queryList != null) {
                    tmpQueryList.set(queryList);
                }

                if (tmpQueryList.get() == null || tmpQueryList.get().getRight().isEmpty()) {
                    JCommonUtil._jOptionPane_showMessageDialog_info("沒有資料!");
                    return;
                }

                final JProgressBarHelper prog = JProgressBarHelper.newInstance(this, "export excel");
                prog.max(tmpQueryList.get().getRight().size());
                prog.limitMoveBound(false);
                prog.modal(false);
                prog.closeListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        prog.setExitFlag(true);
                    }
                });
                prog.build();
                prog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedWriter writer = null;
                        try {
                            List<String> columnLst = new ArrayList<String>();
                            if (StringUtils.isNotBlank(columnFilterText.getText())) {
                                List<Object> lst = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();
                                for (Object v : lst) {
                                    String name = String.valueOf(v);
                                    if (QUERY_RESULT_COLUMN_NO.equals(name)) {
                                        continue;
                                    }
                                    columnLst.add(name);
                                }
                            }

                            List<String> columns = new ArrayList<String>(tmpQueryList.get().getLeft());
                            Map<String, Integer> columnLengthMap = new LinkedHashMap<String, Integer>();

                            File tempFile = File.createTempFile("FastDBQueryUI_", "_txt");
                            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF8"));

                            // 次處計算最大蘭寬
                            if (columnLst.isEmpty()) {
                                for (int ii = 0; ii < columns.size(); ii++) {
                                    String col = columns.get(ii);
                                    columnLengthMap.put(col, StringUtil4FullChar_4NotePad.length(filterChangeLine(String.valueOf(col))));
                                }

                                for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                    Object[] rows = tmpQueryList.get().getRight().get(ii);
                                    for (int jj = 0; jj < columns.size(); jj++) {
                                        String col = columns.get(jj);
                                        Object value = rows[jj];
                                        int currentLen = StringUtil4FullChar_4NotePad.length(filterChangeLine(String.valueOf(value)));
                                        int mapLen = 0;
                                        if (columnLengthMap.containsKey(col)) {
                                            mapLen = columnLengthMap.get(col);
                                        }
                                        mapLen = Math.max(mapLen, currentLen);
                                        columnLengthMap.put(col, mapLen);
                                    }
                                }
                            } else {
                                for (int ii = 0; ii < columnLst.size(); ii++) {
                                    String col = columnLst.get(ii);
                                    columnLengthMap.put(col, StringUtil4FullChar_4NotePad.length(filterChangeLine(String.valueOf(col))));
                                }

                                for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                    Object[] rows = tmpQueryList.get().getRight().get(ii);
                                    for (int jj = 0; jj < columnLst.size(); jj++) {
                                        String col = columns.get(jj);
                                        Object value = rows[jj];
                                        int currentLen = StringUtil4FullChar_4NotePad.length(filterChangeLine(String.valueOf(value)));
                                        int mapLen = 0;
                                        if (columnLengthMap.containsKey(col)) {
                                            mapLen = columnLengthMap.get(col);
                                        }
                                        mapLen = Math.max(mapLen, currentLen);
                                        columnLengthMap.put(col, mapLen);
                                    }
                                }
                            }

                            // 此處才開始塞直
                            if (columnLst.isEmpty()) {
                                StringBuffer sb = new StringBuffer();
                                for (int ii = 0; ii < columns.size(); ii++) {
                                    String col = columns.get(ii);
                                    int length = columnLengthMap.get(col);
                                    String strVal = StringUtil4FullChar_4NotePad.rightPad(filterChangeLine(String.valueOf(col)), length);
                                    if (ii == 0) {
                                        sb.append("|");
                                    }
                                    sb.append(strVal);
                                    sb.append("|");
                                }

                                writer.write(sb.toString());
                                writer.newLine();
                                writer.flush();

                                for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                    sb.setLength(0);
                                    Object[] rows = tmpQueryList.get().getRight().get(ii);
                                    for (int jj = 0; jj < columns.size(); jj++) {
                                        String col = columns.get(jj);
                                        Object value = rows[jj];
                                        if (value == null && radio_export_excel_ignoreNull.isSelected()) {
                                            value = "";
                                        }

                                        int length = columnLengthMap.get(col);
                                        String strVal = StringUtil4FullChar_4NotePad.rightPad(filterChangeLine(String.valueOf(value)), length);
                                        if (jj == 0) {
                                            sb.append("|");
                                        }
                                        sb.append(strVal);
                                        sb.append("|");

                                        prog.addOne();

                                        if (prog.isExitFlag()) {
                                            return;
                                        }
                                    }
                                    writer.write(sb.toString());
                                    writer.newLine();
                                    writer.flush();
                                }
                            } else {
                                StringBuffer sb = new StringBuffer();
                                for (int ii = 0; ii < columnLst.size(); ii++) {
                                    String col = columnLst.get(ii);
                                    int length = columnLengthMap.get(col);
                                    String strVal = StringUtil4FullChar_4NotePad.rightPad(filterChangeLine(String.valueOf(col)), length);
                                    if (ii == 0) {
                                        sb.append("|");
                                    }
                                    sb.append(strVal);
                                    sb.append("|");
                                }

                                writer.write(sb.toString());
                                writer.newLine();
                                writer.flush();

                                for (int ii = 0; ii < tmpQueryList.get().getRight().size(); ii++) {
                                    sb.setLength(0);
                                    Object[] rows = tmpQueryList.get().getRight().get(ii);
                                    for (int jj = 0; jj < columnLst.size(); jj++) {
                                        String col = columnLst.get(jj);
                                        Object value = rows[jj];
                                        if (value == null && radio_export_excel_ignoreNull.isSelected()) {
                                            value = "";
                                        }

                                        int length = columnLengthMap.get(col);
                                        String strVal = StringUtil4FullChar_4NotePad.rightPad(filterChangeLine(String.valueOf(value)), length);
                                        if (jj == 0) {
                                            sb.append("|");
                                        }
                                        sb.append(strVal);
                                        sb.append("|");

                                        prog.addOne();

                                        if (prog.isExitFlag()) {
                                            return;
                                        }
                                    }
                                    writer.write(sb.toString());
                                    writer.newLine();
                                    writer.flush();
                                }
                            }

                            writer.flush();
                            writer.close();

                            prog.dismiss();

                            String filename = FastDBQueryUI.class.getSimpleName() + //
                            "_" + getRandom_TableNSchema() + "_" + //
                            "_" + StringUtils.trimToEmpty(sqlIdText.getText()) + "_" + //
                            DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd_HHmmss");
                            filename = JCommonUtil._jOptionPane_showInputDialog("儲存檔案", filename);
                            if (!filename.toLowerCase().endsWith(".txt")) {
                                filename += ".txt";
                            }
                            if (StringUtils.isNotBlank(filename) || !filename.endsWith(".txt")) {
                                File exportFile = new File(FileUtil.DESKTOP_DIR, filename);
                                tempFile.renameTo(exportFile);
                            } else {
                                JCommonUtil._jOptionPane_showMessageDialog_info("檔名有誤!");
                            }
                        } catch (Exception ex) {
                            JCommonUtil.handleException(ex);
                        } finally {
                            try {
                                writer.flush();
                            } catch (IOException e) {
                            }
                            try {
                                writer.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                }, "---thread1").start();
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }
    
    private Object parseToNumberValueForExcel(Object value) {
    	if(value instanceof Number) {
    		return value;
    	} else if(value instanceof BigDecimal) {
    		BigDecimal b = (BigDecimal)value;
    		return b.doubleValue();
    	} else if(value != null) {
    		String strValue = value.toString();
    		try {
    			Double d = Double.parseDouble(strValue);
    			if(d.compareTo(0D) == 0 || !strValue.startsWith("0")) {
        			return d;
        		}
    		}catch(Exception ex) {
    		}
    	}
    	return value;
    }

    public void appendExcelSQLSheet(HSSFWorkbook wk) {
        final ExcelUtil_Xls97 exlUtl = ExcelUtil_Xls97.getInstance();
        HSSFSheet sheet2 = wk.createSheet("sql");
        ExcelColorCreater mExcelColorCreater = ExcelColorCreater.newInstance(wk);
        JTableUtil paramUtl = JTableUtil.newInstance(parametersTable);
        Row sqlRow = exlUtl.getRowChk(sheet2, 0);
        Cell sqlCell = exlUtl.getCellChk(sqlRow, 0);
        String originSql = currentSQL.get();
        if (StringUtils.isBlank(originSql)) {
            originSql = getCurrentSQL();
        }
        sqlCell.setCellValue(StringUtils.trimToEmpty(originSql));
        Cell sqlCell2 = exlUtl.getCellChk(sqlRow, 1);
        sqlCell2.setCellValue(getShowAfterCurrentSQL(false));
        sqlRow.setHeight((short) -1);
        CellStyle rowHeightStyle = wk.createCellStyle();
        rowHeightStyle.setWrapText(true);
        sqlRow.setRowStyle(rowHeightStyle);

        if (paramUtl.getModel().getRowCount() > 0) {
            int sqlRowPos = 2;
            CellStyleHandler titleCs1 = ExcelWriter.CellStyleHandler.newInstance(wk.createCellStyle())//
                    .setForegroundColor(mExcelColorCreater.of("#678F8D"));
            CellStyleHandler titleCs2 = ExcelWriter.CellStyleHandler.newInstance(wk.createCellStyle())//
                    .setForegroundColor(mExcelColorCreater.of("#77A88D")).setAlignment(HSSFCellStyle.ALIGN_CENTER);
            CellStyleHandler titleCs3 = ExcelWriter.CellStyleHandler.newInstance(wk.createCellStyle())//
                    .setForegroundColor(mExcelColorCreater.of("#FFD000"));
            Cell c00 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 0);
            Cell c01 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 1);
            sqlRowPos++;
            titleCs1.applyStyle(c00);
            titleCs1.applyStyle(c01);
            c00.setCellValue("以下為參數列表");
            Cell c10 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 0);
            Cell c11 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 1);
            titleCs2.applyStyle(c10);
            titleCs2.applyStyle(c11);
            c10.setCellValue("參數名稱");
            c11.setCellValue("值");
            sqlRowPos++;
            for (int ii = 0; ii < paramUtl.getModel().getRowCount(); ii++) {
                int col1 = JTableUtil.getRealColumnPos(ParameterTableColumnDef.COLUMN.idx, parametersTable);
                int val1 = JTableUtil.getRealColumnPos(ParameterTableColumnDef.VALUE.idx, parametersTable);
                Object col = paramUtl.getRealValueAt(JTableUtil.getRealRowPos(ii, parametersTable), col1);
                Object val = paramUtl.getRealValueAt(JTableUtil.getRealRowPos(ii, parametersTable), val1);

                Cell cc1 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 0);
                Cell cc2 = exlUtl.getCellChk(exlUtl.getRowChk(sheet2, sqlRowPos), 1);
                cc1.setCellValue(String.valueOf(col));
                cc2.setCellValue(String.valueOf(val));
                titleCs3.applyStyle(cc1);
                titleCs3.applyStyle(cc2);
                sqlRowPos++;
            }
        }
        exlUtl.setSheetWidth(sheet2, new short[] { 8000, 8000 });
        exlUtl.applyAutoHeight(sheet2, wk);
    }

    private void removeConnectionBtnAction() {
        try {
            String dbNameId = mDBNameIdTextHandler.dbNameIdText_getText();
            boolean confirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("確定要刪除:" + dbNameId, "刪除設定");
            if (confirm) {
                dataSourceConfig.removeConfig(dbNameId);
                JCommonUtil._jOptionPane_showMessageDialog_info("刪除成功! : " + dbNameId);
                mDBNameIdTextHandler.reload_DataSourceConfig_autoComplete();
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    protected void distinctQueryBtnActionTotal() {
        try {
            if (columnFilterHolder.get() == null || !columnFilterHolder.get().isDoHiddenColumn()) {
                distinctQueryBtnAction();
            } else {
                distinctQueryBtnActionAbbr();
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    protected void distinctQueryBtnActionAbbr() {
        distinctHasClicked = true;

        JTableUtil util = JTableUtil.newInstance(queryResultTable);

        List<String> containsLst = new ArrayList<String>();

        List<String> columns = new ArrayList<String>();
        List<Object[]> rows = new ArrayList<Object[]>();

        boolean hiddenNoColumn = false;

        for (int colPos = 0; colPos < queryResultTable.getColumnCount(); colPos++) {
            TableColumn column = queryResultTable.getTableHeader().getColumnModel().getColumn(colPos);
            String colStr = (String) column.getHeaderValue();
            if (!StringUtils.equals(QUERY_RESULT_COLUMN_NO, colStr)) {
                columns.add(colStr);
                hiddenNoColumn = true;
            }
        }

        for (int rowPos = 0; rowPos < queryResultTable.getRowCount(); rowPos++) {
            List<Object> row = new ArrayList<Object>();
            A: for (int colPos = 0; colPos < queryResultTable.getColumnCount(); colPos++) {
                if (hiddenNoColumn && colPos == 0) {
                    continue A;
                }
                Object data = util.getRealValueAt(rowPos, colPos);
                row.add(data);
            }

            String rowStr = row.toString();
            if (!containsLst.contains(rowStr)) {
                containsLst.add(rowStr);
                rows.add(row.toArray());
            }
        }

        Triple<List<String>, List<Class<?>>, List<Object[]>> queryResultFinal = fixPairToTripleQueryResult(Pair.of(columns, rows));
        this.queryModeProcess(queryResultFinal, true, null, null);
    }

    protected void distinctQueryBtnAction() {
        distinctHasClicked = true;

        TreeMap<Integer, String> columnMapping = new TreeMap<Integer, String>();
        JTableUtil util = JTableUtil.newInstance(queryResultTable);

        for (int ii = 0; ii < queryResultTable.getColumnCount(); ii++) {
            TableColumn column = queryResultTable.getTableHeader().getColumnModel().getColumn(ii);
            int pos2 = util.getRealColumnPos(ii, queryResultTable);
            // System.out.println(column.getHeaderValue() + " - " + ii + " -
            // " + pos2);
            columnMapping.put(pos2, (String) column.getHeaderValue());
        }
        System.out.println(columnMapping);
        List<Object[]> queryLst = new ArrayList<Object[]>();
        for (int row = 0; row < util.getModel().getRowCount(); row++) {
            TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
            for (int col = 0; col < queryResultTable.getColumnCount(); col++) {
                int realCol = util.getRealColumnPos(col, queryResultTable);
                int realRow = util.getRealRowPos(row, queryResultTable);
                Object value = util.getModel().getValueAt(realRow, realCol);
                map.put(realCol, value);
            }
            Object[] arry = map.values().toArray();
            if (isContainObjectArray_Index(queryLst, arry) == -1) {
                queryLst.add(arry);
            }
        }

        List<String> matchColumnLst = new ArrayList<String>(columnMapping.values());
        Triple<List<String>, List<Class<?>>, List<Object[]>> queryResultFinal = fixPairToTripleQueryResult(Pair.of(matchColumnLst, queryLst));
        this.queryModeProcess(queryResultFinal, true, null, null);
    }

    private int isContainObjectArray_Index(List<Object[]> allLst, Object[] arry) {
        String compareTo = Arrays.toString(arry);
        System.out.println("[isContainObjectArray_Index] compareTo - \t" + compareTo);
        for (int ii = 0; ii < allLst.size(); ii++) {
            Object[] arry1 = allLst.get(ii);
            String compareFrom = Arrays.toString(arry1);
            if (compareFrom.equals(compareTo)) {
                System.out.println("[isContainObjectArray_Index] ArryIndex[" + ii + "] - \t" + compareFrom);
                return ii;
            }
        }
        return -1;
    }

    private void sqlTextAreaMouseClickedAction(MouseEvent e) {
        if (JMouseEventUtil.buttonRightClick(1, e)) {
            JPopupMenuUtil jpopUtil = JPopupMenuUtil.newInstance(sqlTextArea);//

            JTextFieldUtil.applyCopyPasteJPopupMenus(sqlTextArea, jpopUtil, null);

            jpopUtil.addJMenuItem("SQL 格式化", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (StringUtils.isNotBlank(sqlTextArea.getSelectedText())) {
                        String selection = sqlTextArea.getSelectedText();
                        String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                        String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                        sqlTextArea.setText(prefix + formatSQL(selection) + suffix);
                    } else {
                        sqlTextArea.setText(formatSQL(sqlTextArea.getText()));
                    }
                }

                private String formatSQL(String sql) {
                    sql = StringUtils.defaultString(sql);
                    sql = getSqlFormater(sql);
                    sql = getSelectColumnFormater(sql);
                    return sql;
                }

                Pattern ptn = Pattern.compile("(\\[.*?\\]|\\swhere|\\sand|\\sor|\\sfrom|\\sunion|\\souter\\s+join|\\sinner\\s+join|\\sleft\\s+join|\\sright\\s+join|\\sjoin|\\son)",
                        Pattern.CASE_INSENSITIVE);
                Pattern ptn2 = Pattern.compile("select\\s+((?:[^\n]|\n)*?)from\\s+.*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);

                private String getSqlFormater(String sql) {
                    List<String> lst = StringUtil_.readContentToList(sql, true, false, false);
                    sql = StringUtils.join(lst, "  ");
                    StringBuffer sb = new StringBuffer();
                    Matcher mth = ptn.matcher(sql);
                    String space = JTextAreaUtil.getSpaceOfCaretPositionLine(sqlTextArea);
                    while (mth.find()) {
                        mth.appendReplacement(sb, "\r\n" + space + mth.group(1));
                    }
                    mth.appendTail(sb);
                    return sb.toString();
                }

                private String getSelectColumnFormater(String sql) {
                    StringBuffer sb = new StringBuffer();
                    Matcher mth = ptn2.matcher(sql);
                    int startPos = 0;
                    if (mth.find()) {
                        sb.append(sql.substring(startPos, mth.start(1)));
                        startPos = mth.end(1);
                        String selectDesc = mth.group(1);
                        selectDesc = selectDesc.replaceAll(",", ",\r\n    ");
                        sb.append(selectDesc);
                    }
                    sb.append(sql.substring(startPos));
                    return sb.toString();
                }
            });//

            jpopUtil.addJMenuItem(getBaseSQL_Menus());

            jpopUtil.addJMenuItem(getDateMenus());

            jpopUtil.addJMenuItem("以記事本為Table新開視窗查詢", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FastDBQueryUI cloneFromFrame1 = (FastDBQueryUI) TAB_UI1.getCurrentChildJFrame();
                    String tableName = cloneFromFrame1.sqlTextArea.getSelectedText();
                    if (StringUtils.isBlank(tableName)) {
                        tableName = ClipboardUtil.getInstance().getContents();
                    }
                    if (StringUtils.isBlank(tableName)) {
                        return;
                    }
                    if (TAB_UI1 != null) {
                        FastDBQueryUI cloneToFrame1 = (FastDBQueryUI) TAB_UI1.addTab("未命名", true);
                        if (cloneFromFrame1 != null) {
                            String dbName = cloneFromFrame1.mDBNameIdTextHandler.dbNameIdText_getText();
                            cloneToFrame1.mDBNameIdTextHandler.dbNameIdText_setText(dbName);
                        }
                        SqlIdConfigBean sqlBean1 = new SqlIdConfigBean();
                        sqlBean1.category = "";
                        sqlBean1.sql = "\n\n\n\n\t\t\t\t select * \n" + //
                        "\t\t\t\t from " + tableName + "\n" + //
                        "\t\t\t\t where 1=1 \n";//
                        sqlBean1.sqlComment = "";
                        sqlBean1.sqlId = "";
                        if (StringUtils.isBlank(sqlBean1.sqlId)) {
                            sqlBean1.sqlId = "未命名";
                        }
                        cloneToFrame1.sqlIdText.setText(sqlBean1.sqlId);
                        if (StringUtils.isNotBlank(sqlBean1.sql)) {
                            cloneToFrame1.sqlTextArea.setText(sqlBean1.sql);
                        }
                        cloneToFrame1.sqlListMouseClicked(null, sqlBean1);
                        cloneToFrame1.executeSqlButtonClick();
                    }
                }
            });//

            jpopUtil.addJMenuItem("以ToString()替換SQL條件", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new ToStringReplaceOldSql().execute("clipboard");
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//

            jpopUtil.addJMenuItem("以ToString()替換SQL條件[垂]", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new ToStringReplaceOldSql().execute("clipboard_vertical");
                    } catch (Exception ex) {
                        JCommonUtil.handleException(ex);
                    }
                }
            });//

            jpopUtil.addJMenuItem("顯示查詢SQL", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getShowAfterCurrentSQL(true);
                }
            });//

            jpopUtil.addJMenuItem("顯示查詢SQL(快速)", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String sql = getShowAfterCurrentSQL(false);
                    sql = StringUtil_.trimAllSpace_Ver2(sql);
                    SimpleTextDlg.newInstance(sql, "", null).show();
                }
            });//

            if (mUndoSaveHanlder.hasRecord()) {
                jpopUtil.addJMenuItem("儲存回復!!!", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean isConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("是否要回復上一次儲存內容？", "SQL回覆");
                        if (isConfirm) {
                            String sql = mUndoSaveHanlder.reverse(true);
                            if (StringUtils.isNotBlank(sql)) {
                                sqlTextArea.setText(sql);
                            }
                        }
                    }
                });//
            }

            jpopUtil.addJMenuItem("通用工具", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showCommonToolDlg();
                }
            });//

            jpopUtil.applyEvent(e)//
                    .show();
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private JMenuItem getDateMenus() {
        JMenuAppender jpopUtil = JMenuAppender.newInstance("日期Helper");
        jpopUtil.addJMenuItem("插入系統日", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = sqlTextArea.getSelectedText();

                DBDateFormat e2 = (DBDateFormat) JCommonUtil._JOptionPane_showInputDialog("請選擇日期格式", "日期格式化", DBDateFormat.values(), null);
                if (e2 == null) {
                    return;
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());

                column = e2.sysdate();

                sqlTextArea.setText(prefix + column + suffix);
            }
        });//
        jpopUtil.addJMenuItem("Date 改為字串", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = sqlTextArea.getSelectedText();

                DBDateFormat e2 = (DBDateFormat) JCommonUtil._JOptionPane_showInputDialog("請選擇日期格式", "日期格式化", DBDateFormat.values(), null);
                if (e2 == null) {
                    return;
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());

                column = e2.date2Varchar(column);

                sqlTextArea.setText(prefix + column + suffix);
            }
        });//
        jpopUtil.addJMenuItem("Timestamp 改為字串", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = sqlTextArea.getSelectedText();

                DBDateFormat e2 = (DBDateFormat) JCommonUtil._JOptionPane_showInputDialog("請選擇日期格式", "日期格式化", DBDateFormat.values(), null);
                if (e2 == null) {
                    return;
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());

                column = e2.timestamp2Varchar(column);

                sqlTextArea.setText(prefix + column + suffix);
            }
        });//
        jpopUtil.addJMenuItem("字串改為 Date", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = sqlTextArea.getSelectedText();

                DBDateFormat e2 = (DBDateFormat) JCommonUtil._JOptionPane_showInputDialog("請選擇日期格式", "日期格式化", DBDateFormat.values(), null);
                if (e2 == null) {
                    return;
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());

                column = e2.varchar2Date(column);

                sqlTextArea.setText(prefix + column + suffix);
            }
        });//
        jpopUtil.addJMenuItem("字串改為 Timestamp", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String column = sqlTextArea.getSelectedText();

                DBDateFormat e2 = (DBDateFormat) JCommonUtil._JOptionPane_showInputDialog("請選擇日期格式", "日期格式化", DBDateFormat.values(), null);
                if (e2 == null) {
                    return;
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());

                column = e2.varchar2Timestamp(column);

                sqlTextArea.setText(prefix + column + suffix);
            }
        });//

        jpopUtil.addJMenuItem("Timestamp -> Long", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = (String) new S2T_And_T2S_EventHandler(sqlTextArea).getTransfer_TimestampToLong().transform(true);
                sqlTextArea.setText(sql);
            }
        });//

        jpopUtil.addJMenuItem("Long -> Timestamp", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = (String) new S2T_And_T2S_EventHandler(sqlTextArea).getTransfer_TimestampToLong().transform(false);
                sqlTextArea.setText(sql);
            }
        });//

        return jpopUtil.getMenu();
    }
    // --------------------------------------------------------------------------------------------------------------------------

    private void deleteSqlIdConfigBean(SqlIdConfigBean sqlBean) {
        String sql = sqlBean.sql;

        boolean deleteConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("刪除 : " + sqlBean.getUniqueKey() + "\nSQL : " + sql, "是否刪除 : " + sqlBean.getUniqueKey());
        if (deleteConfirm) {

            // 刪除參數黨
            File paramFile = new File(JAR_PATH_FILE, "param_" + sqlBean.getUniqueKey() + ".properties");
            if (paramFile.exists()) {
                paramFile.delete();
            }

            // 刪除sqlId
            if (!paramFile.exists()) {
                sqlIdConfigBeanHandler.remove(sqlBean);

                JListUtil.removeElement(sqlList, sqlBean);

                // 移除db config mapping
                sqlIdListDSMappingHandler.remove(sqlBean.getUniqueKey());
            }

            // 刪除sqlIdColumn設定
            mSqlIdColumnHolder.remove(mSqlIdColumnHolder.getSqlId());

            JCommonUtil._jOptionPane_showMessageDialog_info("刪除" + (!paramFile.exists() ? "成功" : "失敗"));

            initLoadSqlListConfig(null);
        }
    }

    private void sqlListKeyPressAction(KeyEvent evt) {
        try {
            JListUtil.newInstance(sqlList).defaultJListKeyPressed(evt, false);
            // 刪除
            System.out.println("click del key : " + (evt.getKeyCode() == 127));
            if (evt.getKeyCode() == 127) {
                SqlIdConfigBean sqlBean = JListUtil.getLeadSelectionObject(sqlList);
                this.deleteSqlIdConfigBean(sqlBean);
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    public JFrameRGBColorPanel getjFrameRGBColorPanel() {
        return jFrameRGBColorPanel.get();
    }

    private void connTestBtnAction() {
        Connection conn = null;
        try {
            conn = this.getDataSource().getConnection();
            JCommonUtil._jOptionPane_showMessageDialog_info("連線成功!");
        } catch (Exception ex) {
            JCommonUtil.handleException("測試連線失敗 : " + ex.getMessage(), ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    private class SqlIdConfigBeanHandler {
        Properties sqlIdListProp;
        List<SqlIdConfigBean> lst = new ArrayList<SqlIdConfigBean>();
        JTextComponent registerComponent;
        JTextField ignoreComponent = new JTextField();

        private void setRegisterComponent(JTextComponent registerComponent) {
            this.registerComponent = registerComponent;
        }

        private void setRegisterComponentIgnore() {
            this.registerComponent = ignoreComponent;
        }

        private boolean isOkRegisterComponent() {
            if (registerComponent == null) {
                return true;
            }
            if (registerComponent == ignoreComponent) {
                return false;
            }
            for (JTextComponent comp : new JTextComponent[] { sqlQueryText, //
                    sqlContentFilterText, //
                    sqlMappingFilterText_Auto.getTextComponent(), //
                    sqlIdCategoryComboBox4Tab1_Auto.getTextComponent()//
            }) {//
                if (comp == registerComponent) {
                    return true;
                }
            }
            return false;
        }

        private SqlIdConfigBeanHandler() {
            init("");
        }

        public void updateQueryTime() {
            boolean findOk = false;
            SqlIdConfigBean bean = getCurrentEditSqlIdConfigBean();
            for (SqlIdConfigBean b2 : lst) {
                if (StringUtils.equals(b2.getUniqueKey(), bean.getUniqueKey())) {
                    bean = b2;
                    findOk = true;
                    break;
                }
            }
            if (findOk) {
                String queryTimes = "0";
                if (lst.contains(bean)) {
                    queryTimes = lst.get(lst.indexOf(bean)).queryTimes;
                }
                try {
                    queryTimes = "" + (Integer.parseInt(queryTimes) + 1);
                } catch (Exception ex) {
                    queryTimes = "1";
                }
                bean.queryTimes = queryTimes;
                bean.latestQueryTime = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS");
                if (StringUtils.isNotBlank(bean.sqlId) && StringUtils.isNotBlank(bean.sql)) {
                    save(bean);
                }
            }
        }

        public void remove(SqlIdConfigBean sqlBean) {
            init("");
            boolean removeOk = lst.remove(sqlBean);
            System.out.println("removeOk = " + removeOk);
            store();
        }

        public void saveFixCategory(List<SqlIdConfigBean> lst2, String category) {
            init_withoutUpdate("");
            for (SqlIdConfigBean b : lst2) {
                if (lst.contains(b)) {
                    SqlIdConfigBean b2 = lst.get(lst.indexOf(b));
                    b2.category = category;
                }
            }
            store();
            init(category);
        }

        public void save(SqlIdConfigBean b) {
            b.validate();
            init_withoutUpdate("");
            if (lst.contains(b)) {
                SqlIdConfigBean b2 = lst.get(lst.indexOf(b));
                b2.category = b.category;
                b2.color = b.color;
                b2.sql = b.sql;
                b2.sqlId = b.sqlId;
                b2.sqlComment = b.sqlComment;
                b2.queryTimes = b.queryTimes;
                b2.latestQueryTime = b.latestQueryTime;
                b2.latestUpdateTime = b.latestUpdateTime;
            } else {
                lst.add(b);
            }
            store();
            init(b.category);
        }

        private void saveYamlToProp2(File otherPropFile, boolean replaceCurrentConfigFile) {
            if (replaceCurrentConfigFile) {
                sqlIdListFile = otherPropFile;
                sqlIdListProp = PropertiesUtil.loadProperties(otherPropFile, null, false);
                init("");
            }
        }

        private void saveYamlToProp(File yamlFile, boolean replaceCurrentConfigFile) {
            File propFile = new File(FileUtil.DESKTOP_DIR, "sqlList.properties");
            if (propFile.exists()) {
                if (!JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("檔案已存在於桌面 sqlList.properties, 是否要覆蓋?", "是否要覆蓋?")) {
                    return;
                }
            }
            Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
            List<SqlIdConfigBean> lst = YamlMapUtil.getInstance().loadFromFile(yamlFile, SqlIdConfigBean.class, classMap);
            Properties prop = new Properties();
            for (SqlIdConfigBean bean : lst) {
                prop.setProperty(bean.getKey(), bean.getValue());
            }
            PropertiesUtil.storeProperties(prop, propFile, "");
            if (replaceCurrentConfigFile) {
                sqlIdListFile = propFile;
                sqlIdListProp = prop;
                init("");
            }
        }

        private void store() {
            sqlIdListProp.clear();
            for (SqlIdConfigBean bean : lst) {
                sqlIdListProp.setProperty(bean.getKey(), bean.getValue());
            }
            PropertiesUtil.storeProperties(sqlIdListProp, sqlIdListFile, DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd-HHmmss"));
        }

        private void backupFile() {
            if (!sqlIdListFile.exists()) {
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String fileYmd = sdf.format(cal.getTime());
            File backupFile = new File(sqlIdListFile.getParentFile(), FileUtil.getNameNoSubName(sqlIdListFile) + "_" + fileYmd + ".properties");
            File backupFile2 = new File(sqlIdListFile.getParentFile(), FileUtil.getNameNoSubName(sqlIdListFile) + "_" + fileYmd + ".yaml");
            if (!backupFile.exists()) {
                FileUtil.copyFile(sqlIdListFile, backupFile);
            }
            List<SqlIdConfigBean> lst2 = loadPropFromFile(sqlIdListFile);
            YamlMapUtil.getInstance().saveToFilePlain(backupFile2, lst2, false, null);
        }

        private List<SqlIdConfigBean> loadPropFromFile(File file) {
            List<SqlIdConfigBean> lst = new ArrayList<SqlIdConfigBean>();
            Properties prop = PropertiesUtil.loadProperties(file, null, false);
            for (Enumeration<Object> enu = prop.keys(); enu.hasMoreElements();) {
                String key = (String) enu.nextElement();
                String value = prop.getProperty(key);
                SqlIdConfigBean bean = SqlIdConfigBean.of(key, value);
                if (!lst.contains(bean)) {
                    lst.add(bean);
                }
            }
            ListUtil.sortIgnoreCase(lst);
            return lst;
        }

        private void init_withoutUpdate(String category) {
            if (!sqlIdListFile.exists()) {
                try {
                    sqlIdListFile.createNewFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                backupFile();
            }

            lst.clear();
            sqlIdListProp = PropertiesUtil.loadProperties(sqlIdListFile, null, false);
            for (Enumeration<Object> enu = sqlIdListProp.keys(); enu.hasMoreElements();) {
                String key = (String) enu.nextElement();
                String value = sqlIdListProp.getProperty(key);
                SqlIdConfigBean bean = SqlIdConfigBean.of(key, value);
                if (!lst.contains(bean)) {
                    lst.add(bean);
                }
            }
            ListUtil.sortIgnoreCase(lst);
            List<String> categoryLst = getCategoryLst(lst);
            // sqlIdCategoryComboBox_Auto.applyComboxBoxList(categoryLst,
            // category);
        }

        private void init(String category) {
            if (!sqlIdListFile.exists()) {
                try {
                    sqlIdListFile.createNewFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                backupFile();
            }

            lst.clear();
            sqlIdListProp = PropertiesUtil.loadProperties(sqlIdListFile, null, false);
            for (Enumeration<Object> enu = sqlIdListProp.keys(); enu.hasMoreElements();) {
                String key = (String) enu.nextElement();
                String value = sqlIdListProp.getProperty(key);
                SqlIdConfigBean bean = SqlIdConfigBean.of(key, value);
                if (!lst.contains(bean)) {
                    lst.add(bean);
                }
            }
            ListUtil.sortIgnoreCase(lst);
            List<String> categoryLst = getCategoryLst(lst);
            sqlIdCategoryComboBox_Auto.applyComboxBoxList(categoryLst, category);
            if (isOkRegisterComponent()) {
                updateSqlIdCategoryComboBox4Tab1();
            }
            setRegisterComponent(null);
        }

        private List<String> getCategoryLst(List<SqlIdConfigBean> lst) {
            Scanner scan = null;
            Set<String> categoryLst = new TreeSet<String>();
            for (SqlIdConfigBean bean : lst) {
                scan = new Scanner(StringUtils.defaultString(bean.category));
                while (scan.hasNext()) {
                    String catetory = StringUtils.trimToEmpty(scan.next());
                    categoryLst.add(catetory);
                }
            }
            return new ArrayList<String>(categoryLst);
        }

        private void updateSqlIdCategoryComboBox4Tab1() {
            String defaultText = sqlIdCategoryComboBox4Tab1_Auto.getTextComponent().getText();
            List<String> categoryLst = getCategoryLst(lst);
            sqlIdCategoryComboBox4Tab1_Auto.applyComboxBoxList(categoryLst, defaultText, true);
        }

        private String getCurrentCategory() {
            if (sqlMappingFilterText_Auto.getTextComponent() == this.registerComponent) {
                return StringUtils.trimToEmpty(sqlMappingFilterText_Auto.getTextComponent().getText());
            }
            if (StringUtils.isNotBlank(sqlIdCategoryComboBox4Tab1_Auto.getTextComponent().getText())) {
                return StringUtils.trimToEmpty(sqlIdCategoryComboBox4Tab1_Auto.getTextComponent().getText());
            }
            return "";
        }
    }

    public static class SqlIdConfigBean {
        private static String[] KEYS_DEF = new String[] { "color", "category", "sqlId", "sqlComment", "latestUpdateTime", "latestQueryTime", "queryTimes" };
        private static String[] VALUES_DEF = new String[] { "sql" };

        public static int SHOW_TIME_STATUS = -1;

        String color;
        String category;
        String sqlId;
        String sql;
        String sqlComment;
        String latestUpdateTime;
        String latestQueryTime;
        String queryTimes;

        public String getCategory() {
            return category;
        }

        public String getSqlId() {
            return sqlId;
        }

        public String getSql() {
            return sql;
        }

        private String getUniqueKey() {
            // String prefix = "";
            // if (StringUtils.isNotBlank(category)) {
            // prefix = StringUtils.trimToEmpty(category) + "_";
            // }
            // return prefix + StringUtils.trimToEmpty(sqlId);
            return StringUtils.trimToEmpty(sqlId);
        }

        public static SqlIdConfigBean of(String key, String value) {
            return PropertiesMultiUtil.of(key, value, SqlIdConfigBean.class);
        }

        private String getKey() {
            return PropertiesMultiUtil.getKey(this);
        }

        private String getValue() {
            return PropertiesMultiUtil.getValue(this);
        }

        private void validate() {
            if (StringUtils.isBlank(sqlId) || StringUtils.isBlank(sql)) {
                Validate.isTrue(false, "sqlId, sql 不可為空!");
            }
            if (!FileUtil.validatePath(sqlId, false)) {
                Validate.isTrue(false, "sqlId 不可含有特殊字元 [\\/:*?\"<>|]");
            }
            if (!FileUtil.validatePath(category, false)) {
                Validate.isTrue(false, "category 不可含有特殊字元 [\\/:*?\"<>|]");
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SqlIdConfigBean other = (SqlIdConfigBean) obj;
            if (sqlId == null) {
                if (other.sqlId != null)
                    return false;
            } else if (!sqlId.equals(other.sqlId))
                return false;
            return true;
        }

        public String toString() {
            String prefixStatusString = "";
            if (SHOW_TIME_STATUS == 1) {
                prefixStatusString = StringUtils.trimToEmpty(latestUpdateTime) + "  ";
            } else if (SHOW_TIME_STATUS == 2) {
                prefixStatusString = StringUtils.trimToEmpty(latestQueryTime) + "  ";
            } else if (SHOW_TIME_STATUS == 3) {
                prefixStatusString = StringUtils.trimToEmpty(queryTimes) + "  ";
            }
            if (StringUtils.isNotBlank(category)) {
                return String.format("<html><body %5$s>"//
                        + "<font color=\"BLUE\">%4$s</font>"//
                        + "<font style=\"background-color: YELLOW;\">"//
                        + "<b></b>%2$s</font>"//
                        + "&nbsp;&nbsp;"//
                        + "<font color=\"%1$s\">%3$s</font>"//
                        + "</body></html>", //
                        StringUtils.trimToEmpty(color), //
                        "『" + StringUtils.trimToEmpty(category) + "』  ", //
                        StringUtils.trimToEmpty(sqlId), //
                        prefixStatusString, //
                        "" //
                );
            } else {
                return String.format("<html><body %5$s>"//
                        + "<font color=\"BLUE\">%4$s</font>"//
                        + "<font style=\"background-color: YELLOW;\">"//
                        + "<b></b>%2$s</font>"//
                        + "&nbsp;&nbsp;"//
                        + "<font color=\"%1$s\">%3$s</font>"//
                        + "</body></html>", //
                        StringUtils.trimToEmpty(color), //
                        StringUtils.trimToEmpty(category), //
                        StringUtils.trimToEmpty(sqlId), //
                        prefixStatusString, //
                        "" //
                );
            }
        }

        public boolean equalsForEditor(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SqlIdConfigBean other = (SqlIdConfigBean) obj;
            if (category == null) {
                if (other.category != null)
                    return false;
            } else if (!category.equals(other.category))
                return false;
            if (color == null) {
                if (other.color != null)
                    return false;
            } else if (!color.equals(other.color))
                return false;
            // if (latestQueryTime == null) {
            // if (other.latestQueryTime != null)
            // return false;
            // } else if (!latestQueryTime.equals(other.latestQueryTime))
            // return false;
            // if (latestUpdateTime == null) {
            // if (other.latestUpdateTime != null)
            // return false;
            // } else if (!latestUpdateTime.equals(other.latestUpdateTime))
            // return false;
            // if (queryTimes == null) {
            // if (other.queryTimes != null)
            // return false;
            // } else if (!queryTimes.equals(other.queryTimes))
            // return false;
            if (sql == null) {
                if (other.sql != null)
                    return false;
            } else if (!sql.equals(other.sql))
                return false;
            if (sqlComment == null) {
                if (other.sqlComment != null)
                    return false;
            } else if (!sqlComment.equals(other.sqlComment))
                return false;
            if (sqlId == null) {
                if (other.sqlId != null)
                    return false;
            } else if (!sqlId.equals(other.sqlId))
                return false;
            return true;
        }

        // getter & setter ----------------------------------------------
        public void setColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public String getSqlComment() {
            return sqlComment;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public void setSqlId(String sqlId) {
            this.sqlId = sqlId;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public void setSqlComment(String sqlComment) {
            this.sqlComment = sqlComment;
        }

        public String getLatestUpdateTime() {
            return latestUpdateTime;
        }

        public void setLatestUpdateTime(String latestUpdateTime) {
            this.latestUpdateTime = latestUpdateTime;
        }

        public String getLatestQueryTime() {
            return latestQueryTime;
        }

        public void setLatestQueryTime(String latestQueryTime) {
            this.latestQueryTime = latestQueryTime;
        }

        public String getQueryTimes() {
            return queryTimes;
        }

        public void setQueryTimes(String queryTimes) {
            this.queryTimes = queryTimes;
        }
    }

    public static class RefSearchListConfigBean {
        private static String[] KEYS_DEF = new String[] { "category", "searchKey" };
        private static String[] VALUES_DEF = new String[] { "content", "categoryColor" };

        String category;
        String searchKey;
        String content;
        String categoryColor;

        public void setCategory(String category) {
            this.category = category;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCategoryColor(String categoryColor) {
            this.categoryColor = categoryColor;
        }

        public String getCategory() {
            return category;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public String getContent() {
            return content;
        }

        public String getCategoryColor() {
            return categoryColor;
        }

        private static String getArry(int idx, String[] arry, String defaultVal) {
            if (idx <= arry.length - 1) {
                return StringUtils.trimToEmpty(arry[idx]);
            }
            return defaultVal;
        }

        public static RefSearchListConfigBean of(String key, String value) {
            RefSearchListConfigBean bean = new RefSearchListConfigBean();
            String[] keys = StringUtils.trimToEmpty(key).split(Pattern.quote("#^#"));
            String[] values = StringUtils.trimToEmpty(value).split(Pattern.quote("#^#"));
            bean.category = getArry(0, keys, "NA");
            bean.searchKey = getArry(1, keys, "");
            bean.content = getArry(0, values, "");
            bean.categoryColor = getArry(1, values, "blue");
            return bean;
        }

        public static String getContent(String value) {
            String[] values = StringUtils.trimToEmpty(value).split(Pattern.quote("#^#"));
            return getArry(0, values, "");
        }

        public static String getKey(String category, String searchKey) {
            return StringUtils.trimToEmpty(category) + "#^#" + StringUtils.trimToEmpty(searchKey);
        }

        public static String getValue(String content, String categoryColor) {
            return StringUtils.trimToEmpty(content) + "#^#" + StringUtils.trimToEmpty(categoryColor);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((category == null) ? 0 : category.hashCode());
            result = prime * result + ((searchKey == null) ? 0 : searchKey.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            RefSearchListConfigBean other = (RefSearchListConfigBean) obj;
            if (category == null) {
                if (other.category != null)
                    return false;
            } else if (!category.equals(other.category))
                return false;
            if (searchKey == null) {
                if (other.searchKey != null)
                    return false;
            } else if (!searchKey.equals(other.searchKey))
                return false;
            return true;
        }

        public boolean isMatch(String category, String text) {
            category = StringUtils.trimToEmpty(category).toLowerCase();
            text = StringUtils.trimToEmpty(text);

            boolean isCategoryOk = (StringUtils.trimToEmpty(this.category).toLowerCase().contains(category));
            boolean isSearchKeyOk = (StringUtils.trimToEmpty(this.searchKey).toLowerCase().contains(text));
            boolean isContentOk = (StringUtils.trimToEmpty(this.content).toLowerCase().contains(text));

            if (StringUtils.isBlank(category) && StringUtils.isBlank(text)) {
                return true;
            } else if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(text)) {
                if (isCategoryOk && (isSearchKeyOk || isContentOk)) {
                    return true;
                }
            } else if (StringUtils.isBlank(category) && StringUtils.isNotBlank(text)) {
                if (isSearchKeyOk || isContentOk) {
                    return true;
                }
            } else if (StringUtils.isNotBlank(category) && StringUtils.isBlank(text)) {
                if (isCategoryOk) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            String fixStyle = "style=\"background-color: #000000;\"";
            return String.format("<html><body %4$s><font color=\"%1$s\"><b></b>%2$s</font>&nbsp;&nbsp;  <font color=\"black\">%3$s</font></body></html>", //
                    StringUtils.trimToEmpty(this.categoryColor), //
                    StringUtils.trimToEmpty(this.category), //
                    StringUtils.trimToEmpty(this.searchKey), //
                    StringUtils.trimToEmpty(this.categoryColor).equalsIgnoreCase("YELLOW") ? fixStyle : ""//
            );
        }

        public String toStringInfo() {
            return "RefSearchListConfigBean [category=" + category + ", searchKey=" + searchKey + ", content=" + content + ", categoryColor=" + categoryColor + "]";
        }
    }

    private class RefSearchListConfigHandler {

        private YamlUtilBean<RefSearchListConfigBean> config;
        private JList jList;
        private JComboBox refSearchCategoryCombobox;
        private JTextField refConfigPathText;

        private RefSearchListConfigHandler(JTextField refConfigPathText, JList jList, JComboBox refSearchCategoryCombobox) {
            String fileName = FastDBQueryUI.class.getSimpleName() + "_Ref.yml";
            File configFile = new File(refConfigPathText.getText());
            if (configFile == null || !configFile.exists()) {
                config = new YamlUtilBean<RefSearchListConfigBean>(new File(JAR_PATH_FILE, fileName), RefSearchListConfigBean.class, null);
            } else {
                config = new YamlUtilBean<RefSearchListConfigBean>(configFile, RefSearchListConfigBean.class, null);
            }
            refConfigPathText.setText(config.getPropFile().getAbsolutePath());

            this.refConfigPathText = refConfigPathText;
            this.jList = jList;
            this.refSearchCategoryCombobox = refSearchCategoryCombobox;
            this.find("", "");
        }

        public void reload() {
            config.reload();
        }

        private void find(String category, String text) {
            text = StringUtils.trimToEmpty(text).toLowerCase();
            List<RefSearchListConfigBean> lst = new ArrayList<RefSearchListConfigBean>();
            Set<String> categoryLst = new TreeSet<String>();

            for (RefSearchListConfigBean bean : config.getConfigProp()) {
                if (bean.isMatch(category, text)) {
                    lst.add(bean);
                }
                if (StringUtils.isNotBlank(bean.category)) {
                    categoryLst.add(bean.category);
                }
            }
            ListUtil.sortIgnoreCase(lst);
            DefaultListModel model = JListUtil.createModel();
            for (RefSearchListConfigBean key : lst) {
                model.addElement(key);
            }
            this.jList.setModel(model);

            refSearchCategoryCombobox_Auto.applyComboxBoxList(new ArrayList<String>(categoryLst));
            refSearchCategoryCombobox_Auto.setSelectItemAndText(category);
        }

        private void add(String category, String searchKey, String content, String categoryColor) {
            if (StringUtils.isBlank(category) || StringUtils.isBlank(searchKey) || StringUtils.isBlank(content)) {
                JCommonUtil._jOptionPane_showMessageDialog_error("請輸入內容!");
                return;
            }

            RefSearchListConfigBean bean = new RefSearchListConfigBean();
            bean.category = category;
            bean.searchKey = searchKey;
            bean.content = content;
            bean.categoryColor = categoryColor;

            if (config.contains(bean)) {
                String compareContent = bean.content;
                if (!StringUtils.equals(compareContent, content)) {
                    boolean confirmOk = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("已存在 : " + category + " " + searchKey + ", 是否要蓋掉?", "覆蓋確認");
                    if (!confirmOk) {
                        return;
                    }
                }
            }

            config.setProperty(bean);
            config.store();
            find(category, "");
            JCommonUtil._jOptionPane_showMessageDialog_info("儲存成功!");
        }

        private void delete(String category, String searchKey) {
            RefSearchListConfigBean bean = new RefSearchListConfigBean();
            bean.category = category;
            bean.searchKey = searchKey;
            if (config.contains(bean)) {
                boolean confirmOk = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("是否刪除 : " + category + " " + searchKey + "?", "確認刪除");
                if (!confirmOk) {
                    return;
                }
            } else {
                JCommonUtil._jOptionPane_showMessageDialog_error("找不到 : " + category + " " + searchKey + "!");
                return;
            }
            config.remove(bean);
            config.store();
            find("", "");
        }

        private RefSearchListConfigBean get(String category, String searchKey) {
            RefSearchListConfigBean bean = new RefSearchListConfigBean();
            bean.category = category;
            bean.searchKey = searchKey;
            if (!config.contains(bean)) {
                JCommonUtil._jOptionPane_showMessageDialog_error("找不到 : " + category + " " + searchKey);
                return null;
            }
            return config.getProperty(bean);
        }

        private String findExceptionMessage(String category, String message) {
            if (StringUtils.isBlank(message)) {
                return "";
            }
            Map<Double, List<RefSearchListConfigBean>> compareMap = new TreeMap<Double, List<RefSearchListConfigBean>>();
            for (RefSearchListConfigBean bean : config.getConfigProp()) {
                if (StringUtils.isNotBlank(category) && !StringUtils.equalsIgnoreCase(bean.category, category)) {
                    continue;
                }

                Double score = SimilarityUtil.sim(message.toLowerCase(), bean.searchKey.toLowerCase());
                System.out.println("加入排行 --> 分數 : " + score + "\t" + bean.toStringInfo());

                List<RefSearchListConfigBean> keyLst = new ArrayList<RefSearchListConfigBean>();
                if (compareMap.containsKey(score)) {
                    keyLst = compareMap.get(score);
                }
                keyLst.add(bean);

                compareMap.put(score, keyLst);
            }
            if (compareMap.isEmpty()) {
                return "";
            }
            Double k = Double.MIN_VALUE;
            for (Double k1 : compareMap.keySet()) {
                k = Math.max(k, k1);
            }
            if (k != null || k != 0) {
                List<RefSearchListConfigBean> keyLst = compareMap.get(k);
                if (keyLst == null || keyLst.isEmpty()) {
                    return "";
                }
                Collections.sort(keyLst, new Comparator<RefSearchListConfigBean>() {
                    @Override
                    public int compare(RefSearchListConfigBean o1, RefSearchListConfigBean o2) {
                        String o1Val = RefSearchListConfigBean.getKey(o1.category, o1.searchKey);
                        String o2Val = RefSearchListConfigBean.getKey(o2.category, o2.searchKey);
                        return new Integer(StringUtils.trimToEmpty(o1Val).length()).compareTo(StringUtils.trimToEmpty(o2Val).length());
                    }
                });
                RefSearchListConfigBean refKey = keyLst.get(0);
                if (config.contains(refKey)) {
                    RefSearchListConfigBean bean = config.getProperty(refKey);
                    BigDecimal dd = new BigDecimal(k);
                    dd = dd.setScale(3, BigDecimal.ROUND_HALF_UP);
                    System.out.println("最高分 --> 分數 : " + dd + "\t" + bean.toStringInfo());
                    return String.format("[score:%s] ", dd.toString()) + String.format("<font color=\"%s\"><b>%s</b></font>", bean.categoryColor, bean.content);
                }
            }
            return "";
        }
    }

    class EtcConfigHandler {
        PropertiesUtilBean config = new PropertiesUtilBean(JAR_PATH_FILE, FastDBQueryUI.class.getSimpleName() + "_Etc");
        List<JComponent> containArry = new ArrayList<JComponent>();
        final String FONT_SIZE_KEY = "fontSizeKey";

        EtcConfigHandler() {
            containArry.add(FastDBQueryUI.this.refConfigPathText);
        }

        public void reflectInit() {
            config.reflectInit(FastDBQueryUI.this, containArry);

            defaultFontSize = (Integer) config.getProperty(FONT_SIZE_KEY, defaultFontSize, null);
            setAllFontSize(defaultFontSize);
        }

        public void reflectSetConfig() {
            config.reflectSetConfig(FastDBQueryUI.this, containArry);

            config.setPropertyNullIsEmpty(FONT_SIZE_KEY, defaultFontSize);
        }

        public Object getProperty(String key, Object defaultValue, Class valueClass) {
            return config.getProperty(key, defaultValue, valueClass);
        }

        public String getProperty(String key) {
            return config.getConfigProp().getProperty(key);
        }

        public void setProperty(String key, String value) {
            config.getConfigProp().setProperty(key, value);
        }

        public void store() {
            config.store();
        }

        public void reload() {
            config.reload();
        }
    }

    private void saveEtcConfigBtnAction() {
        try {
            etcConfigHandler.reflectSetConfig();
            etcConfigHandler.store();
            JCommonUtil._jOptionPane_showMessageDialog_info("儲存成功!");
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    public void reloadAllProperties() {
        initLoadSqlListConfig(null);
        sqlIdListDSMappingHandler.init();
        // loadParameterTableConfig();//不需要
        refSearchListConfigHandler.reload();
        etcConfigHandler.reload();
        dataSourceConfig = new PropertiesGroupUtils_ByKey(new File(JAR_PATH_FILE, "dataSource.properties"));
    }

    private static class HardcodeParamDetecter {
        Map<String, String> values = new HashMap<String, String>();
        Pattern ptn1 = Pattern.compile("([\\w\\.]+)[\\s|\\t]*\\=[\\s|\\t]*(\'[^\n]*?\'|[\\-\\d\\.]+)", Pattern.MULTILINE | Pattern.DOTALL);
        Pattern ptn2 = Pattern.compile("(\'[^\n]*?\'|[\\-\\d\\.]+)[\\s|\\t]*\\=[\\s|\\t]*([\\w\\.]+)", Pattern.MULTILINE | Pattern.DOTALL);

        HardcodeParamDetecter(String sql) {
            sql = StringUtils.defaultString(sql);
            Matcher mth = ptn1.matcher(sql);
            while (mth.find()) {
                String param = getParam(mth.group(1));
                String value = getVal(mth.group(2));
                System.out.println("\t detect : K:" + param + " \t V:" + value);
                values.put(param, value);
            }
            mth = ptn2.matcher(sql);
            while (mth.find()) {
                String param = getParam(mth.group(2));
                String value = getVal(mth.group(1));
                System.out.println("\t detect : K:" + param + " \t V:" + value);
                values.put(param, value);
            }
        }

        private String getParam(String value) {
            return StringUtils.defaultString(value).replaceAll("^\\w+\\.", "").toUpperCase();
        }

        private String getVal(String value) {
            String tmpVal = StringUtils.defaultString(value).replaceAll("^\'|\'$", "");
            if (!tmpVal.contains("'")) {
                return tmpVal;
            } else {
                return tmpVal.substring(tmpVal.lastIndexOf("'") + 1);
            }
        }
    }

    private class FakeDataModelHandler {
        private Map<String, String> getParametersTable_Map() {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            JTableUtil util = JTableUtil.newInstance(parametersTable);
            for (int ii = 0; ii < parametersTable.getRowCount(); ii++) {
                String columnName = (String) util.getRealValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                String value = (String) util.getRealValueAt(ii, 1);
                paramMap.put(columnName, value);
            }
            return paramMap;
        }

        private Map<String, String> getHardcodeParameters() {
            return new HardcodeParamDetecter(sqlTextArea.getText()).values;
        }

        TableInfo tabInfo = new TableInfo();
        DefaultTableModel model = JTableUtil.createModel(true, new Object[0]);
        List<Object[]> queryLst = new ArrayList<Object[]>();
        Map<String, String> parameterTableMap;
        Map<String, String> hardcodeMap;
        List<String> columns;
        Triple<List<String>, List<Class<?>>, List<Object[]>> queryList;

        private Object getValue(Object val, char type) {
            switch (type) {
            case 'i':
                try {
                    return Integer.parseInt(String.valueOf(val));
                } catch (Throwable ex) {
                    return 0;
                }
            case 'd':
                try {
                    return java.sql.Date.valueOf(String.valueOf(val));
                } catch (Throwable ex) {
                    return new java.sql.Date(System.currentTimeMillis());
                }
            case 't':
                try {
                    return java.sql.Timestamp.valueOf(String.valueOf(val));
                } catch (Throwable ex) {
                    return new java.sql.Timestamp(System.currentTimeMillis());
                }
            default:
                try {
                    if (val != null) {
                        return String.valueOf(val);
                    } else {
                        return "1";
                    }
                } catch (Throwable ex) {
                    return "1";
                }
            }
        }

        public FakeDataModelHandler(Pair<SqlParam, List<Object>> pair, DataSource ds, boolean isFakeData) {
            try {
                this.parameterTableMap = getParametersTable_Map();
                this.hardcodeMap = getHardcodeParameters();

                tabInfo.execute(pair.getLeft().getQuestionSql(), pair.getRight().toArray(), ds.getConnection());
                columns = new ArrayList<String>(tabInfo.getColumns());
                model = JTableUtil.createModel(true, columns.toArray());

                List<Object> blankArry = new ArrayList<Object>();
                List<Object> arry = new ArrayList<Object>();
                List<Class<?>> typeLst = new ArrayList<Class<?>>();
                for (int ii = 0; ii < columns.size(); ii++) {
                    String col = columns.get(ii);
                    Object val = null;
                    char type = ' ';
                    Class<?> typeClz = null;

                    // 用 參數表的 值來當作預設值
                    if (hardcodeMap.containsKey(col)) {
                        val = hardcodeMap.get(col);
                        editColumnHistoryHandler.addColumnDef(col, val);
                    } else if (parameterTableMap.containsKey(col)) {
                        val = parameterTableMap.get(col);
                        editColumnHistoryHandler.addColumnDef(col, val);
                    } else if (editColumnHistoryHandler.hasColumnDef(col)) {
                        val = editColumnHistoryHandler.getSingleValue(col);
                    }

                    if (tabInfo.getNumberCol().contains(col)) {
                        type = 'i';
                        typeClz = BigDecimal.class;
                    } else if (tabInfo.getDateCol().contains(col)) {
                        type = 'd';
                        typeClz = java.sql.Date.class;
                    } else if (tabInfo.getTimestampCol().contains(col)) {
                        type = 't';
                        typeClz = java.sql.Timestamp.class;
                    } else {
                        type = ' ';
                        typeClz = String.class;
                    }

                    val = getValue(val, type);
                    arry.add(val);
                    typeLst.add(typeClz);
                }
                queryLst.add(arry.toArray());
                if (isFakeData) {
                    model.addRow(arry.toArray());
                } else {
                    model.addRow(blankArry.toArray());
                }
                queryList = Triple.of(columns, typeLst, queryLst);

                // 儲存 欄位編輯歷史紀錄
                editColumnHistoryHandler.store();
            } catch (Exception e) {
                JCommonUtil.handleException(e);
            }
        }

        public Triple<List<String>, List<Class<?>>, List<Object[]>> getQueryList() {
            return queryList;
        }

        public DefaultTableModel getModel() {
            return model;
        }
    }

    private void sqlIdFixNameBtnAction(String mode) {
        try {
            if (sqlBean == null) {
                JCommonUtil._jOptionPane_showMessageDialog_error("請先選擇SQL List");
                return;
            } else if (sqlBean != null && sqlParameterConfigLoadHandler.isInitOk()) {
                String chkName = "param_" + sqlBean.getUniqueKey() + ".properties";
                if (!StringUtils.equals(sqlParameterConfigLoadHandler.configFile.getName(), chkName)) {
                    JCommonUtil._jOptionPane_showMessageDialog_error("檔名不同無法改檔名" + chkName + " <--> " + sqlParameterConfigLoadHandler.configFile.getName());
                    return;
                }
            }

            String sqlId = sqlIdText.getText();
            String color = getSqlBeanColor();
            String category = sqlIdCategoryComboBox_Auto.getTextComponent().getText();
            String sql = sqlTextArea.getText();
            String sqlComment = sqlIdCommentArea.getText();

            JCommonUtil.isBlankErrorMsg(sqlId, "請輸入sql Id");
            JCommonUtil.isBlankErrorMsg(sql, "請輸入sql");

            Validate.isTrue(StringUtils.equals(sql, sqlBean.sql), "sql不可異動!");

            SqlIdConfigBean bean = new SqlIdConfigBean();
            bean.sql = sql;
            bean.sqlId = sqlId;
            bean.category = category;
            bean.color = color;
            bean.sqlComment = sqlComment;
            bean.latestUpdateTime = DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

            File newFile = sqlParameterConfigLoadHandler.getFile(bean.getUniqueKey());
            File oldFile = sqlParameterConfigLoadHandler.getFile(sqlBean.getUniqueKey());
            if (StringUtils.equalsIgnoreCase(newFile.getName(), oldFile.getName())) {
                JCommonUtil._jOptionPane_showMessageDialog_error("檔名相同無須修改 : " + newFile.getName());
                return;
            }

            if (!oldFile.exists()) {
                JCommonUtil._jOptionPane_showMessageDialog_error("原檔案不存在! : " + oldFile);
                return;
            }
            if (newFile.exists()) {
                boolean overwriteConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("目的檔案已存在, 是否覆蓋?! : " + newFile, "目的檔案已存在, 是否覆蓋?!");
                if (!overwriteConfirm) {
                    return;
                }
            }

            if ("rename".equals(mode)) {
                // DS Mapping 修正
                sqlIdListDSMappingHandler.cloneTo(sqlBean, bean, true);

                // 參數設定黨改名
                oldFile.renameTo(newFile);

                // sql設定修正
                sqlIdConfigBeanHandler.remove(sqlBean);
                sqlIdConfigBeanHandler.save(bean);
            } else if ("clone".equals(mode)) {
                // DS Mapping 修正
                sqlIdListDSMappingHandler.cloneTo(sqlBean, bean, false);

                // 參數設定黨改名
                FileUtil.copyFile(oldFile, newFile);

                // sql設定修正
                sqlIdConfigBeanHandler.save(bean);
            }

            initLoadSqlListConfig(null);
            JCommonUtil._jOptionPane_showMessageDialog_info("已修正為 : " + bean.getUniqueKey());

            // 改變TabUI標題
            changeTabUITitile(bean);
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private class SqlIdExecuteTypeHandler {
        PropertiesUtilBean config = new PropertiesUtilBean(JAR_PATH_FILE, FastDBQueryUI.class.getSimpleName() + "_ExecuteType");

        private void logExecuteType() {
            String sqlId = StringUtils.trimToEmpty(sqlIdText.getText());
            if (StringUtils.isBlank(sqlId)) {
                // if (getCurrentEditSqlIdConfigBean() != null) {
                // sqlId = getCurrentEditSqlIdConfigBean().getSqlId();
                // }
            }
            if (StringUtils.isBlank(sqlId)) {
                return;
            }
            if (updateSqlRadio.isSelected()) {
                config.getConfigProp().setProperty(sqlId, "insert");
            } else {
                config.getConfigProp().setProperty(sqlId, "query");
            }
            config.store();
        }

        private void processExecuteType(String sqlId) {
            querySqlRadio.setSelected(true);
            if (config.getConfigProp().containsKey(sqlId)) {
                if ("insert".equals(config.getConfigProp().getProperty(sqlId))) {
                    updateSqlRadio.setSelected(true);
                }
            }
        }
    }

    private class SqlIdListDSMappingHandler {
        private Properties sqlIdListDSMappingProp;

        SqlIdListDSMappingHandler() {
            init();
        }

        public boolean containsKey(String uniqueKey) {
            return sqlIdListDSMappingProp.containsKey(uniqueKey);
        }

        public String getProperty(String sqlId) {
            return sqlIdListDSMappingProp.getProperty(sqlId);
        }

        private void init() {
            try {
                if (!sqlIdListDSMappingFile.exists()) {
                    sqlIdListDSMappingFile.createNewFile();
                }
                sqlIdListDSMappingProp = PropertiesUtil.loadProperties(sqlIdListDSMappingFile, null, false);
            } catch (Exception ex) {
                throw new RuntimeException("SqlIdListDSMappingHandler init ERR : " + ex.getMessage(), ex);
            }
        }

        private void remove(String sqlId) {
            sqlIdListDSMappingProp.remove(sqlId);
            PropertiesUtil.storeProperties(sqlIdListDSMappingProp, sqlIdListDSMappingFile, DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd HHmmss"));
        }

        private void store(boolean fromSqlLst) throws IOException {
            SqlIdConfigBean bean = null;
            if (!fromSqlLst) {
                bean = (SqlIdConfigBean) sqlList.getSelectedValue();
            } else {
                bean = getCurrentEditSqlIdConfigBean();
            }
            if (bean == null) {
                return;
            }
            try {
                bean.validate();
            } catch (Exception ex) {
                return;
            }
            String sqlId = bean.getUniqueKey();
            String dbNameId = mDBNameIdTextHandler.dbNameIdText_getText();
            this.init();
            sqlIdListDSMappingProp.setProperty(sqlId, dbNameId);
            PropertiesUtil.storeProperties(sqlIdListDSMappingProp, sqlIdListDSMappingFile, DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd HHmmss"));
        }

        private void cloneTo(SqlIdConfigBean from, SqlIdConfigBean to, boolean removeOld) {
            this.init();
            if (sqlIdListDSMappingProp.containsKey(from.sqlId)) {
                String moveToValue = sqlIdListDSMappingProp.getProperty(from.sqlId);
                if (removeOld) {
                    sqlIdListDSMappingProp.remove(from.sqlId);
                }
                sqlIdListDSMappingProp.setProperty(to.getUniqueKey(), moveToValue);
                PropertiesUtil.storeProperties(sqlIdListDSMappingProp, sqlIdListDSMappingFile, DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd HHmmss"));
            }
        }
    }

    private static class SqlParameterConfigLoadHandler {
        private static final String PARAM_COMMENT_KEY = "#PARAM_COMMENT_KEY#";

        private PropertiesGroupUtils sqlParameterConfigLoad;
        private File configFile;

        private Map<String, String> loadConfig() {
            Map<String, String> clone = new HashMap<String, String>(sqlParameterConfigLoad.loadConfig());
            clone.remove(PARAM_COMMENT_KEY);
            return clone;
        }

        private String loadComment() {
            return StringUtils.trimToEmpty(sqlParameterConfigLoad.loadConfig().get(PARAM_COMMENT_KEY));
        }

        public void clear() {
            sqlParameterConfigLoad.clear();
        }

        public void next() {
            sqlParameterConfigLoad.next();
        }

        private void saveConfig(Map<String, String> currentConfig, String paramComment) {
            currentConfig.put(PARAM_COMMENT_KEY, StringUtils.trimToEmpty(paramComment));
            Set<String> ignoreKeys = new HashSet<String>();
            ignoreKeys.add(PARAM_COMMENT_KEY);
            sqlParameterConfigLoad.saveConfig(currentConfig, ignoreKeys);
        }

        private boolean isInitOk() {
            return sqlParameterConfigLoad != null && configFile != null;
        }

        private File getFile(String sqlId) {
            return new File(JAR_PATH_FILE, "param_" + sqlId + ".properties");
        }

        private void init(String sqlId) {
            configFile = new File(JAR_PATH_FILE, "param_" + sqlId + ".properties");
            sqlParameterConfigLoad = new PropertiesGroupUtils(configFile);
        }

        private void deleteParameterBtnAction() {
            if (sqlParameterConfigLoad == null) {
                return;
            }
            Map<String, String> configMap = sqlParameterConfigLoad.loadConfig();
            boolean delConfirm = JCommonUtil._JOptionPane_showConfirmDialog_yesNoOption("是否要刪除 :" + configMap, "確認刪除?");
            if (delConfirm) {
                sqlParameterConfigLoad.removeConfig();
            }
        }
    }

    private class SqlIdColumnHolder {
        private PropertiesUtilBean config;
        private File configFile;

        private String getSqlId() {
            if (sqlBean != null) {
                return sqlBean.sqlId;
            }
            return StringUtils.trimToEmpty(sqlIdText.getText());
        }

        private SqlIdColumnHolder() {
            configFile = new File(JAR_PATH_FILE, SqlIdColumnHolder.class.getSimpleName() + ".properties");
            config = new PropertiesUtilBean(configFile);
        }

        private void remove(String sqlId) {
            if (!config.getConfigProp().containsKey(sqlId)) {
                return;
            }
            config.getConfigProp().remove(sqlId);
            config.store();
        }

        private void setColumns(String sqlId, List<String> columns) {
            if (StringUtils.isBlank(sqlId)) {
                return;
            }
            String value = StringUtils.join(columns, "^");
            config.getConfigProp().setProperty(sqlId, value);
            config.store();
        }

        private boolean isColumnExists(String sqlId, String column) {
            column = StringUtils.trimToEmpty(column);
            if (StringUtils.isBlank(column)) {
                return false;
            }
            if (!config.getConfigProp().containsKey(sqlId)) {
                return false;
            }
            String columnStr = config.getConfigProp().getProperty(sqlId);
            String[] columns = StringUtils.split(columnStr, "^");
            for (String col : columns) {
                if (StringUtils.trimToEmpty(col).equalsIgnoreCase(column)) {
                    return true;
                }
            }
            return false;
        }
    }

    private SqlIdConfigBean getCurrentEditSqlIdConfigBean() {
        String sqlId = sqlIdText.getText();
        String color = getSqlBeanColor();
        String category = sqlIdCategoryComboBox_Auto.getTextComponent().getText();
        String sql = sqlTextArea.getText();
        String sqlComment = sqlIdCommentArea.getText();

        SqlIdConfigBean bean = new SqlIdConfigBean();
        bean.sql = sql;
        bean.sqlId = sqlId;
        bean.category = category;
        bean.color = color;
        bean.sqlComment = sqlComment;
        return bean;
    }

    private boolean isSqlIdChange() {
        SqlIdConfigBean currentBean = getCurrentEditSqlIdConfigBean();
        if (sqlBean != null) {
            if (StringUtils.equalsIgnoreCase(sqlBean.sqlId, currentBean.sqlId)) {
                return false;
            }
        }
        sqlIdConfigBeanHandler.init(sqlIdCategoryComboBox_Auto.getTextComponent().getText());
        List<SqlIdConfigBean> lst = sqlIdConfigBeanHandler.lst;
        for (SqlIdConfigBean b : lst) {
            if (StringUtils.equalsIgnoreCase(b.sqlId, currentBean.sqlId)) {
                return true;
            }
        }
        return false;
    }

    private void sqlTextAreaChange() {
        try {
            String text = sqlTextArea.getText();
            boolean isNotEqual = false;
            if (sqlBean != null) {
                if (!sqlBean.equalsForEditor(getCurrentEditSqlIdConfigBean())) {
                    isNotEqual = true;
                }
            } else {
                if (StringUtils.isNotBlank(text) || StringUtils.isNotBlank(getCurrentEditSqlIdConfigBean().getUniqueKey())) {
                    isNotEqual = true;
                }
            }
            if (isNotEqual) {
                sqlSaveButton.setText("<html><font color='RED'>＊儲存</font></html>");
            } else {
                sqlSaveButton.setText("<html><font color='BLACK'>儲存</font></html>");
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    /**
     * 計算欄位型態
     * 
     * @param queryLst
     * @return
     */
    private Triple<List<String>, List<Class<?>>, List<Object[]>> fixPairToTripleQueryResult(Pair<List<String>, List<Object[]>> queryLst) {
        List<Object[]> lst = queryLst.getRight();
        TreeMap<Integer, Class<?>> typeMap = new TreeMap<Integer, Class<?>>();
        A: for (int ii = 0; ii < lst.size(); ii++) {
            if (queryLst.getLeft().size() == typeMap.size()) {
                break A;
            }
            Object[] arry = lst.get(ii);
            B: for (int jj = 0; jj < arry.length; jj++) {
                if (typeMap.containsKey(jj)) {
                    continue;
                }
                if (arry[jj] != null) {
                    typeMap.put(jj, arry[jj].getClass());
                }
            }
        }
        for (int ii = 0; ii < queryLst.getLeft().size(); ii++) {
            if (!typeMap.containsKey(ii)) {
                typeMap.put(ii, Object.class);
            }
        }
        List<Class<?>> typeLst = new ArrayList<Class<?>>(typeMap.values());
        return Triple.of(queryLst.getLeft(), typeLst, queryLst.getRight());
    }

    protected void handleExceptionForExecuteSQL(Exception ex) {
        String category = refSearchCategoryCombobox_Auto.getTextComponent().getText();
        String findMessage = refSearchListConfigHandler.findExceptionMessage(category, ex.getMessage());
        // 一般顯示
        if (StringUtils.isBlank(findMessage)) {
            JCommonUtil.handleException(ex);
        } else {
            // html顯示
            JCommonUtil.handleException(String.format("參考 : %s", findMessage), ex, true, "", "yyyyMMdd", false, true);
        }
    }

    public EtcConfigHandler getEtcConfig() {
        return etcConfigHandler;
    }

    static class EditColumnHistoryHandler {
        String delimit = "#^#";
        PropertiesUtilBean config = new PropertiesUtilBean(JAR_PATH_FILE, FastDBQueryUI.class.getSimpleName() + "_ColumnHis");

        protected void addColumnDef(String column, Object value) {
            column = StringUtils.trimToEmpty(column).toUpperCase();
            String realVal = value == null ? "" : String.valueOf(value);
            realVal = StringUtils.trimToEmpty(realVal);
            String[] values = StringUtils.trimToEmpty(config.getConfigProp().getProperty(column)).split(Pattern.quote(delimit));
            Set<String> vals = new LinkedHashSet<String>();
            vals.add(realVal);
            for (String v : values) {
                vals.add(v);
            }
            String valStr = StringUtils.join(vals, delimit);
            config.getConfigProp().setProperty(column, valStr);
        }

        protected void store() {
            config.store();
        }

        protected List<String> getColumnValues(String column) {
            column = StringUtils.trimToEmpty(column).toUpperCase();
            String[] values = StringUtils.trimToEmpty(config.getConfigProp().getProperty(column)).split(Pattern.quote(delimit));
            Set<String> vals = new LinkedHashSet<String>();
            for (String v : values) {
                vals.add(v);
            }
            return new ArrayList<String>(vals);
        }

        protected String getSingleValue(String column) {
            try {
                return getColumnValues(column).get(0);
            } catch (Exception ex) {
                return "1";
            }
        }

        protected boolean hasColumnDef(String column) {
            column = StringUtils.trimToEmpty(column).toUpperCase();
            return config.getConfigProp().containsKey(column);
        }
    }

    public EditColumnHistoryHandler getEditColumnConfig() {
        return editColumnHistoryHandler;
    }

    private class SqlTextAreaPromptHandler {
        String queryText = "";
        String tableAlias = "";
        String columnPrefix = "";
        LRUMap tabMap = new LRUMap(20);
        LRUMap failMap = new LRUMap(100);
        Pair<Integer, Integer> columnIndex;
        int queryTextPos = -1;
        JPopupMenuUtil util;
        int currentMenuIndex = 0;

        private SqlTextAreaPromptHandler() {
        }

        public boolean performUpdateLocation() {
            Rectangle rect = mSqlTextAreaJTextAreaSelectPositionHandler.getRect();
            if (rect == null || util == null) {
                return false;
            }
            if (StringUtils.isBlank(queryText)) {
                util.dismiss();
            }
            util.setLocation(sqlTextArea, (int) rect.getX(), (int) rect.getY());
            return false;
        }

        public boolean performSelectClose() {
            if (util == null) {
                return false;
            }
            if (util.getJPopupMenu().isShowing()) {
                util.dismiss();
                return true;
            }
            return false;
        }

        public boolean performSelectUpDown(KeyEvent e) {
            if (util == null) {
                return false;
            }
            if (util.getJPopupMenu().isShowing() && !util.getJPopupMenu().isFocusOwner()) {
                JCommonUtil.focusComponent(util.getJPopupMenu(), false, null);
                util.getJPopupMenu().dispatchEvent(e);
                return true;
            }
            return false;
        }

        public boolean performSelectTopColumn(KeyEvent e2) {
            if (util == null) {
                return false;
            }
            if (util.getJPopupMenu().isShowing() && !util.getJPopupMenu().isFocusOwner() && !util.getMenuList().isEmpty()) {
                JCommonUtil.focusComponent(util.getJPopupMenu(), false, null);
                util.getJPopupMenu().dispatchEvent(e2);// 原生的event才會正確
                return true;
            }
            return false;
        }

        private void init(DocumentEvent event) {
            String tmpSql = StringUtils.substring(sqlTextArea.getText(), 0, event.getOffset() + event.getLength());

            Pattern ptn = Pattern.compile("[\\s\n]", Pattern.DOTALL | Pattern.MULTILINE);
            Matcher mth = ptn.matcher(tmpSql);
            queryTextPos = -1;
            while (mth.find()) {
                queryTextPos = mth.end();
            }
            queryText = StringUtils.substring(tmpSql, queryTextPos);
            currentMenuIndex = 0;
            System.out.println("prompt - [" + queryText + "]");
        }

        private void mainProcess() {
            if (queryText.contains(".")) {
                delimitDBTable();
            } else {
                return;
            }
            List<String> tables = getTableName(tableAlias);
            if (tables.isEmpty()) {
                return;
            }
            for (int ii = 0; ii < tables.size(); ii++) {
                String tableName = tables.get(ii);
                if (failMap.containsKey(tableName)) {
                    if (System.currentTimeMillis() - (Long) failMap.get(tableName) < 3 * 60 * 1000) {
                        System.out.println("前次失敗未滿3分鐘 : " + tableName);
                        return;
                    }
                }
                DbSqlCreater.TableInfo tab = querySchema(tableName);
                if (tab != null) {
                    List<String> columnLst = getColumnLst(tab);
                    if (!columnLst.isEmpty()) {
                        showPopup(columnLst, tableName);
                        break;
                    }
                }
            }
        }

        private String getColumAndChinese(String column, String tableName) {
            if (mTableColumnDefTextHandler != null) {
                if (StringUtils.isBlank(tableName)) {
                    tableName = FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF;
                }
                String chinese = mTableColumnDefTextHandler.getChinese(column, tableName);
                if (StringUtils.isNotBlank(chinese)) {
                    return "<html>" + column + "<font color='BLUE'>　" + chinese + "</font></html>";
                }
            }
            return column;
        }

        private void showPopup(List<String> columnLst, String tableName) {
            Rectangle rect = mSqlTextAreaJTextAreaSelectPositionHandler.getRect();
            util = JPopupMenuUtil.newInstance(sqlTextArea, true);
            util.applyEvent(rect);
            // util.getJPopupMenu().setFocusable(false);
            for (int ii = 0; ii < columnLst.size(); ii++) {
                final String col = columnLst.get(ii);
                util.addJMenuItem(getColumAndChinese(col, tableName), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        replaceColumn(col);
                        util.dismiss();
                    }
                });
            }
            util.getJPopupMenu().addMenuKeyListener(new MenuKeyListener() {

                @Override
                public void menuKeyTyped(MenuKeyEvent arg0) {
                }

                @Override
                public void menuKeyReleased(MenuKeyEvent arg0) {
                }

                @Override
                public void menuKeyPressed(MenuKeyEvent arg0) {
                    if (arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40) {// 上下
                    } else if (arg0.getKeyCode() == KeyEvent.VK_ENTER || arg0.getKeyCode() == KeyEvent.VK_TAB) {
                        JMenuItem item = null;
                        if ((item = JPopupMenuUtil.getCurrentSelectItem()) != null) {
                            JCommonUtil.triggerButtonActionPerformed(item);
                        } else {
                            JPopupMenuUtil.setCurrentSelectItem(util.getJPopupMenu(), 0, null);
                            item = JPopupMenuUtil.getCurrentSelectItem();
                            JCommonUtil.triggerButtonActionPerformed(item);
                        }
                    }
                }
            });
            util.show();
            sqlTextArea.requestFocus();

        }

        private void replaceColumn(String column) {
            if (columnIndex == null) {
                return;
            }
            String textOrign = StringUtils.defaultString(sqlTextArea.getText());
            String text = StringUtils.substring(textOrign, 0, columnIndex.getLeft()) + column;
            int afterPos = text.length();
            text = text + StringUtils.substring(textOrign, columnIndex.getRight());

            JTextFieldUtil.setTextIgnoreDocumentListener(sqlTextArea, text);

            sqlTextArea.updateUI();

            sqlTextArea.setSelectionStart(afterPos);
            sqlTextArea.setSelectionEnd(afterPos);
        }

        private List<String> getColumnLst(DbSqlCreater.TableInfo tab) {
            List<String> columnLst = new ArrayList<String>();
            if (StringUtils.isNotBlank(columnPrefix)) {
                String _columnPrefix = columnPrefix.toLowerCase();
                for (String col : tab.getColumns()) {
                    if (col.toLowerCase().startsWith(_columnPrefix)) {
                        columnLst.add(col);
                    }
                }
            } else {
                columnLst.addAll(tab.getColumns());
            }
            Collections.sort(columnLst);
            return columnLst;
        }

        private List<String> getTableName(String tableAlias) {
            List<String> tables = new ArrayList<String>();
            String tmpSql = " " + sqlTextArea.getText() + " ";
            Pattern ptn = Pattern.compile("[,\\s\r\n\t]([\\.\\w\\_]+)[\\s\t]+" + tableAlias + "[,\\s\r\n\t]", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher mth = ptn.matcher(tmpSql);
            while (mth.find()) {
                String tableName = mth.group(1);
                System.out.println("@@ tableName = " + tableName);
                tables.add(tableName);
            }
            return tables;
        }

        private DbSqlCreater.TableInfo querySchema(String tableName) {
            if (tabMap.containsKey(tableName)) {
                return (DbSqlCreater.TableInfo) tabMap.get(tableName);
            } else {
                DbSqlCreater.TableInfo tab = new DbSqlCreater.TableInfo();
                try {
                    tab.execute("select * from " + tableName + " where 1!=1 ", getDataSource().getConnection());
                    tabMap.put(tableName, tab);
                    failMap.remove(tableName);
                } catch (Throwable e) {
                    e.printStackTrace();
                    failMap.put(tableName, System.currentTimeMillis());
                }
                return tab;
            }
        }

        private void delimitDBTable() {
            Pattern ptn2 = Pattern.compile("(.*)\\.(.*)");
            Matcher mth2 = ptn2.matcher(queryText);
            if (mth2.find()) {
                tableAlias = mth2.group(1);
                columnPrefix = mth2.group(2);
                columnIndex = Pair.of(queryTextPos + mth2.start(2), queryTextPos + mth2.end(2));
            } else {
                tableAlias = queryText.replaceAll("\\.+$", "");
                columnIndex = null;
            }
        }

        private boolean checkPopupListFocus(KeyEvent arg0) {
            if (arg0.getKeyCode() == 38 || arg0.getKeyCode() == 40) {// 上下
                if (util.getJPopupMenu().isShowing()) {
                    util.getJPopupMenu().dispatchEvent(arg0);
                }
                sqlTextArea.requestFocus();
                return true;
            } else if (!sqlTextArea.isFocusOwner()) {
                if (util.getJPopupMenu().isShowing()) {
                    util.getJPopupMenu().dispatchEvent(arg0);
                }
                sqlTextArea.requestFocus();
            }
            return false;
        }

    }

    private void doSetColumnSqlInProcess(String columnName, boolean distinct) {
        try {
            Pair<List<String>, List<Object[]>> queryResultX = transRealRowToQuyerLstIndex();
            JTableUtil util = JTableUtil.newInstance(queryResultTable);

            int[] rows = queryResultTable.getSelectedRows();

            List<String> lst = new ArrayList<String>();
            int index = -1;
            for (int idx = 0; idx <= queryList.getLeft().size(); idx++) {
                if (StringUtils.equalsIgnoreCase(columnName, queryList.getLeft().get(idx))) {
                    index = idx;
                    break;
                }
            }
            if (index == -1) {
                Validate.isTrue(false, "找不到欄位 :" + columnName);
            }

            for (int row = 0; row < queryResultX.getRight().size(); row++) {
                Object[] arry = queryResultX.getRight().get(row);

                if (rows != null && rows.length != 0) {
                    if (!ArrayUtils.contains(rows, row)) {
                        continue;
                    }
                }

                Object val = arry[index];
                if (val == null) {
                    continue;
                }
                String strVal = StringUtils.trimToEmpty(String.valueOf(val));
                if (distinct) {
                    if (!lst.contains(strVal)) {
                        lst.add(strVal);
                    }
                } else {
                    lst.add(strVal);
                }
            }

            String resultSql = StringUtils.join(lst, "','");
            resultSql = "'" + resultSql + "'";
            SimpleTextDlg.newInstance(resultSql, "", null).show();
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private void sqlTextAreaPromptProcess(String label, DocumentEvent event) {
        if (mSqlTextAreaPromptHandler == null) {
            mSqlTextAreaPromptHandler = new SqlTextAreaPromptHandler();
        }
        mSqlTextAreaPromptHandler.init(event);
        mSqlTextAreaPromptHandler.mainProcess();
    }

    private void moveTabToQueryResultIfHasRecords() {
        DefaultTableModel model = (DefaultTableModel) queryResultTable.getModel();
        if (model.getRowCount() != 0) {
            // JTabbedPaneUtil.newInst(tabbedPane).setSelectedIndexByTitle("查詢結果");//
            // TODO
        }
    }

    private class SearchAndReplace {
        String findKey;
        String lastestStatusArea;
        List<Pair<Integer, Integer>> findLst = new ArrayList<Pair<Integer, Integer>>();

        public boolean replaceAll() {
            if (StringUtils.isBlank(findKey)) {
                findKey = JCommonUtil._jOptionPane_showInputDialog("搜尋:", "");
            }
            if (StringUtils.isBlank(findKey)) {
                return true;
            }
            String replaceKey = JCommonUtil._jOptionPane_showInputDialog("將" + findKey + "取代為:", "");
            if (replaceKey == null) {
                JCommonUtil._jOptionPane_showMessageDialog_error("錯誤！");
                return true;
            }
            replaceKey = StringUtils.defaultString(replaceKey);

            Pattern findPtn = Pattern.compile(Pattern.quote(findKey), Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher findMth = findPtn.matcher(StringUtils.defaultString(sqlTextArea.getText()));
            StringBuffer sb = new StringBuffer();
            while (findMth.find()) {
                findMth.appendReplacement(sb, replaceKey);
            }
            findMth.appendTail(sb);
            sqlTextArea.setText(sb.toString());
            sqlTextArea.setSelectionStart(StringUtils.defaultString(sqlTextArea.getText()).length());
            sqlTextArea.updateUI();
            return true;
        }

        public boolean findKey() {
            findKey = JCommonUtil._jOptionPane_showInputDialog("搜尋:", "");
            if (StringUtils.isBlank(findKey)) {
                return true;
            }

            String tempTextAreaString = StringUtils.defaultString(sqlTextArea.getText());
            if (StringUtils.isNotBlank(lastestStatusArea)) {
                if (!StringUtils.equals(tempTextAreaString, lastestStatusArea)) {
                    findLst.clear();
                }
            }

            lastestStatusArea = tempTextAreaString;

            Pattern findPtn = Pattern.compile(Pattern.quote(findKey), Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher findMth = findPtn.matcher(lastestStatusArea);

            boolean isFirst = true;
            while (findMth.find()) {
                findLst.add(Pair.of(findMth.start(), findMth.end()));

                if (isFirst) {
                    sqlTextArea.setSelectionStart(findMth.start());
                    sqlTextArea.setSelectionEnd(findMth.end());
                    isFirst = false;
                }
            }
            if (isFirst) {
                JCommonUtil._jOptionPane_showMessageDialog_error("找不到 : " + findKey);
            }
            return true;
        }

        public boolean findNext(boolean isForward) {
            String tmpAreaText = StringUtils.defaultString(sqlTextArea.getText());
            if (StringUtils.isNotBlank(tmpAreaText) && StringUtils.equals(tmpAreaText, lastestStatusArea) && !findLst.isEmpty()) {
                int idx = 0;

                for (int ii = 0; ii < findLst.size(); ii++) {
                    Pair<Integer, Integer> p = findLst.get(ii);
                    if (p.getLeft() == sqlTextArea.getSelectionStart() && p.getRight() == sqlTextArea.getSelectionEnd()) {
                        idx = ii;
                        break;
                    }
                }
                if (isForward) {
                    idx++;
                    if (idx >= findLst.size()) {
                        idx = 0;
                    }
                } else {
                    idx--;
                    if (idx < 0) {
                        idx = findLst.size() - 1;
                    }
                }

                Pair<Integer, Integer> pos = findLst.get(idx);
                sqlTextArea.setSelectionStart(pos.getLeft());
                sqlTextArea.setSelectionEnd(pos.getRight());
            }
            return true;
        }
    }

    private void startRecordWatcher() {
        try {
            boolean allOk = false;
            // 啟動
            if (ArrayUtils.contains(new String[] { "監聽", "監聽off" }, recordWatcherToggleBtn.getText())) {
                if (this.queryList != null) {
                    if (mRecordWatcher.get() != null && //
                            (mRecordWatcher.get().getState() == Thread.State.NEW)) {
                        allOk = true;
                        if (mFastDBQueryUI_RowDiffWatcherDlg != null) {
                            mFastDBQueryUI_RowDiffWatcherDlg.setVisible(false);
                        }
                        mFastDBQueryUI_RowDiffWatcherDlg = FastDBQueryUI_RowPKSettingDlg.newInstance(this.queryList.getLeft(), new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                FastDBQueryUI_RowPKSettingDlg dlg = (FastDBQueryUI_RowPKSettingDlg) e.getSource();
                                List<Integer> pkIndexLst = new ArrayList<Integer>();
                                for (int ii = 0; ii < queryList.getLeft().size(); ii++) {
                                    String column = queryList.getLeft().get(ii);
                                    for (String mColumn : dlg.getPkLst()) {
                                        if (StringUtils.equals(column, mColumn)) {
                                            pkIndexLst.add(ii);
                                        }
                                    }
                                }
                                if (pkIndexLst.isEmpty()) {
                                    JCommonUtil._jOptionPane_showMessageDialog_error("請選擇主鍵!");
                                } else {
                                    mRecordWatcher.get().setPkIndexLst(pkIndexLst);
                                    mRecordWatcher.get().start();
                                    recordWatcherToggleBtn.setText("監聽ing");
                                }
                            }
                        }, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                            }
                        });
                    }
                }
                if (!allOk) {
                    JCommonUtil._jOptionPane_showMessageDialog_error("請重新查詢");
                }
            } else if (ArrayUtils.contains(new String[] { "監聽ing" }, recordWatcherToggleBtn.getText())) {
                if (mRecordWatcher.get() != null) {
                    mRecordWatcher.get().doStop(true);
                }
            } else {
                System.out.println("怪怪的....");
            }
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private void createRecordWatcher(Triple<List<String>, List<Class<?>>, List<Object[]>> orignQueryResult, String sql, Object[] params, boolean b, int maxRowsLimit) {
        // 停掉上階段
        if (mRecordWatcher.get() != null) {
            mRecordWatcher.get().doStop(true);
        }
        String fileMiddleName = sqlIdText.getText();
        if (StringUtils.isBlank(fileMiddleName)) {
            fileMiddleName = getRandom_TableNSchema();
        }

        Map<String, String> columnsAndChinese = new HashMap<String, String>();
        if (StringUtils.isNotBlank(tableColumnDefText_Auto.getTextComponent().getText())) {
            String tableName = tableColumnDefText_Auto.getTextComponent().getText();
            columnsAndChinese = mTableColumnDefTextHandler.getColumnsAndChinese(tableName, true);
        }

        mRecordWatcher.set(new FastDBQueryUI_RecordWatcher(orignQueryResult, sql, params, maxRowsLimit, new Callable<Connection>() {
            @Override
            public Connection call() throws Exception {
                return getDataSource().getConnection();
            }
        }, 1000, fileMiddleName, TAB_UI1.getSysTrayUtil(), new Transformer() {
            @Override
            public Object transform(Object input) {
                recordWatcherToggleBtn.setText("監聽off");
                Map<String, Object> map = (Map<String, Object>) input;
                Throwable ex = (Throwable) map.get("ex");
                String msg = (String) map.get("msg");
                if (ex == null && StringUtils.isNotBlank(msg)) {
                    JCommonUtil._jOptionPane_showMessageDialog_error(msg);
                } else if (ex != null) {
                    JCommonUtil.handleException(msg, ex);
                }
                return null;
            }
        }, columnsAndChinese, this.recordWatcherToggleAutoChk));
        // 初始化狀態
        if (mRecordWatcher.get() != null) {
            mRecordWatcher.get().doStop(false);
            recordWatcherToggleBtn.setText("監聽");
        }
    }

    private void updateRecordWatcherChineseMap() {
        if (mRecordWatcher.get() != null) {
            Map<String, String> columnsAndChinese = new HashMap<String, String>();
            if (StringUtils.isNotBlank(tableColumnDefText_Auto.getTextComponent().getText())) {
                String tableName = tableColumnDefText_Auto.getTextComponent().getText();
                columnsAndChinese = mTableColumnDefTextHandler.getColumnsAndChinese(tableName, true);
                if (!columnsAndChinese.isEmpty()) {
                    mRecordWatcher.get().setColumnsAndChinese(columnsAndChinese);
                }
            }
        }
    }

    private boolean checkIsNeedResetQueryResultTable(boolean isCheckColumnFilterText) {
        if (true) {
            return false;
        }
        boolean isNeedReset = false;
        if (isCheckColumnFilterText && StringUtils.isBlank(columnFilterText.getText())) {
            isNeedReset = true;
        } else if (!isCheckColumnFilterText && StringUtils.isBlank(rowFilterText.getText())) {
            isNeedReset = true;
        }
        if (isNeedReset) {
            filterRowsQueryList = null;
            isResetQuery = true;
            queryModeProcess(queryList, true, null, null);//
        }
        return isNeedReset;
    }

    private final Runnable rowFilterTextDoFilter = new Runnable() {

        private FastDBQueryUI_ColumnSearchFilterVer2 columnFilter;

        // 是否重設按鈕
        public void resetIfNeed() {
            if (StringUtils.isBlank(rowFilterText.getText())) {
                // JCommonUtil.triggerButtonActionPerformed(resetQueryBtn);
                // checkIsNeedResetQueryResultTable(true);
                filterRowsQueryList = null;
                isResetQuery = true;
                queryModeProcess(queryList, true, null, null);//
            }
        }

        private Triple<List<String>, List<Class<?>>, List<Object[]>> getQuery() {
            // if (StringUtils.isNotBlank(columnFilterText.getText())) {
            // return getCurrentQueryResultTableLst();
            // }
            if (!isResetQuery && filterRowsQueryList != null) {
                return filterRowsQueryList;
            } else if (queryList != null) {
                return queryList;
            }
            return null;
        }

        private void runProcess() {
            System.out.println("rowDataFilter搜尋[34821]");
            if (columnFilter == null || isResetQuery) {
                columnFilter = new FastDBQueryUI_ColumnSearchFilterVer2(queryResultTable, "^", new Object[] { QUERY_RESULT_COLUMN_NO });
                isResetQuery = false;
            }
            columnFilter.filterColumnText(columnFilterText.getText());

            columnFilter.filterRowText(rowFilterText.getText(), isColumnNoExists(), rowFilterTextKeepMatchChk.isSelected());

            filterRowsQueryList = queryList;
            isResetQuery = false;
        }

        @Override
        public void run() {
            try {
                if (!checkIsNeedResetQueryResultTable(false)) {
                    runProcess();
                }

                // 檢查是否要重設
                // resetIfNeed();
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }
    };

    // 是否重設按鈕
    public void resetIfNeed() {
        if (StringUtils.isBlank(columnFilterText.getText()) && StringUtils.isBlank(rowFilterText.getText())) {
            JCommonUtil.triggerButtonActionPerformed(resetQueryBtn);
        }
    }

    // 設定預設欄位定義
    // 格式為 /*中文解釋 */
    private void setCustomColumnTitleTooltip() {
        queryResultTable.setTitleTooltipTransformer(new Transformer() {

            private int getSerialIndex(Pair<Integer, Object> p, List<String> titles) {
                int columnIdx = p.getLeft();
                String column = (String) p.getRight();
                int serialIndex = 0;
                List<String> subLst = titles.subList(0, columnIdx + 1);
                for (String col : subLst) {
                    if (StringUtils.equalsIgnoreCase(col, column)) {
                        serialIndex++;
                    }
                }
                return serialIndex;
            }

            private String getBaseIndexInfo(Pair<Integer, Object> p, List<String> titles) {
                int columnIdx = p.getLeft();
                if (QUERY_RESULT_COLUMN_NO.equals(titles.get(0))) {
                    if (columnIdx == 0) {
                        return null;
                    }
                    return String.valueOf(columnIdx);
                }
                return String.valueOf(columnIdx + 1);
            }

            @Override
            public Object transform(Object input) {
                Pair<Integer, Object> p = (Pair<Integer, Object>) input;
                int columnIdx = p.getLeft();
                List<String> titles = JTableUtil.newInstance(queryResultTable).getColumnTitleStringArray();
                String column = (String) p.getRight();
                int serialIndex = this.getSerialIndex(p, titles);
                String sql = currentSQL.get();
                Pattern ptn = Pattern.compile(column + "[\\]\\'\"]?[\\s\\t\n\r]*\\/\\*(.*?)\\*\\/", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                Matcher mth = ptn.matcher(sql);
                String tmpTip = null;
                String baseIndexInfo = getBaseIndexInfo(p, titles);
                int idx = 0;
                while (mth.find()) {
                    int startPos = mth.start();
                    tmpTip = mth.group(1);
                    String prefix = StringUtils.substring(sql, startPos - 1, startPos);
                    if (prefix.matches("[a-zA-Z]")) {
                        continue;
                    }
                    idx++;
                    if (serialIndex == idx) {
                        return baseIndexInfo + "." + tmpTip;
                    }
                }
                return baseIndexInfo;
            }
        });
    }

    // 設定預設欄位代碼定義
    // 格式為 /*中文解釋 code=label code=label etc..*/
    // 要在逗號前面加才有用
    private void setCustomColumnCodeValueTooptip() {
        try {
            Pattern ptn3 = Pattern.compile("\\s+(.*)", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            String sql = currentSQL.get();
            final Map<String, Map<String, String>> columnCodeValueMap = new HashMap<String, Map<String, String>>();
            final Map<Integer, Map<String, String>> columnCodeValueMap2 = new HashMap<Integer, Map<String, String>>();
            final List<String> titles = JTableUtil.newInstance(queryResultTable).getColumnTitleStringArray();
            for (int ii = 0; ii < titles.size(); ii++) {
                String column = titles.get(ii);
                int serialIndex = 0;
                for (int jj = 0; jj <= ii; jj++) {
                    String column2 = titles.get(jj);
                    if (StringUtils.equalsIgnoreCase(column, column2)) {
                        serialIndex++;
                    }
                }
                Pattern ptn = Pattern.compile(Pattern.quote(column) + "[\\]\\'\"]?[\\s\\t\n\r]*\\/\\*(.*?)\\*\\/", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                Matcher mth = ptn.matcher(sql);
                String tmpTip = null;
                int idx = 0;
                while (mth.find()) {
                    int startPos = mth.start();
                    tmpTip = mth.group(1);
                    String prefix = StringUtils.substring(sql, startPos - 1, startPos);
                    if (prefix.matches("[a-zA-Z]")) {
                        continue;
                    }
                    idx++;
                    if (idx == serialIndex) {
                        break;
                    }
                }
                if (StringUtils.isNotBlank(tmpTip)) {
                    Matcher mth21 = ptn3.matcher(tmpTip);
                    if (mth21.find()) {
                        String tmpStr = mth21.group(1);
                        if (StringUtils.contains(tmpStr, "=")) {
                            String coleValueString = tmpStr.replaceAll("[\\r\\n]", " ");
                            Map<String, String> codeValMap = new HashMap<String, String>();
                            String[] arry = coleValueString.split(" ", -1);
                            for (String keyVal : arry) {
                                if (keyVal.contains("=")) {
                                    String[] arry2 = keyVal.split("=", -1);
                                    String code = StringUtils.trimToEmpty(arry2[0]);
                                    if (StringUtils.isNotBlank(code)) {
                                        String value = StringUtils.trimToEmpty(arry2[1]);
                                        codeValMap.put(code, value);
                                    }
                                }
                            }
                            columnCodeValueMap.put(column, codeValMap);
                            columnCodeValueMap2.put(ii, codeValMap);
                        }
                    }
                }
            }

            JTableUtil.newInstance(queryResultTable).applyOnHoverEvent(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Pair<Integer, Integer> pair = (Pair<Integer, Integer>) e.getSource();

                        Object title = JTableUtil.newInstance(queryResultTable).getColumnTitle(pair.getRight());

                        /*
                         * if (title == null) {
                         * queryResultTable.setToolTipText(null); return; }
                         * String column = (String) title; if
                         * (columnCodeValueMap.containsKey(column)) { Object val
                         * =
                         * JTableUtil.newInstance(queryResultTable).getValueAt(
                         * true, pair.getLeft(), pair.getRight()); Map<String,
                         * String> codeValueMap = MapUtil.getIgnorecase(column,
                         * columnCodeValueMap); if (val != null) { String value
                         * = StringUtils.trimToEmpty(String.valueOf(val));
                         * String mappingLabel = MapUtil.getIgnorecase(value,
                         * codeValueMap);
                         * queryResultTable.setToolTipText(mappingLabel);
                         * return; } } queryResultTable.setToolTipText(null);
                         */

                        queryResultTable.setToolTipText(null);

                        if (!columnCodeValueMap.isEmpty()) {
                            if (columnCodeValueMap2.containsKey(pair.getRight())) {
                                Map<String, String> codeValueMap = columnCodeValueMap2.get(pair.getRight());
                                Object val = JTableUtil.newInstance(queryResultTable).getValueAt(true, pair.getLeft(), pair.getRight());
                                if (val != null) {
                                    String value = StringUtils.trimToEmpty(String.valueOf(val));
                                    String mappingLabel = MapUtil.getIgnorecase(value, codeValueMap);
                                    queryResultTable.setToolTipText(mappingLabel);
                                    return;
                                }
                            }
                        }

                        // ↓↓↓↓↓↓ 參考 codeTable
                        if (mFastDBQueryUI_RefCodeTableDlg != null) {
                            Object val = JTableUtil.newInstance(queryResultTable).getValueAt(true, pair.getLeft(), pair.getRight());
                            String referenceValue = mFastDBQueryUI_RefCodeTableDlg.getTooltipReference((String) title, val, getDataSource());
                            if (referenceValue != null) {
                                queryResultTable.setToolTipText(referenceValue);
                            } else {
                                queryResultTable.setToolTipText(null);
                            }
                        }
                        // ↑↑↑↑↑↑ 參考 codeTable
                    } catch (Exception ex) {
                        System.out.println("[424983] setCustomColumnCodeValueTooptip ERR : " + ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    class TableColumnDefTextHandler {
        FastDBQueryUI_XlsColumnDefLoader xlsLoader = null;
        int fromIndex = -1;
        int toIndex = -1;
        int pkIndex = -1;
        int fkIndex = -1;
        String xlsLoaderResourceKey = TableColumnDefTextHandler.class.getName() + "_xlsLoaderResourceKey";
        String xlsColumnDefDlgKey = TableColumnDefTextHandler.class.getName() + "_xlsColumnDefDlgKey";

        private boolean checkXlsLoader(boolean reset, boolean showErrMsg) {
            if (mXlsColumnDefDlg == null) {
                if (TAB_UI1 != null) {
                    mXlsColumnDefDlg = (XlsColumnDefDlg) TAB_UI1.getResourcesPool().get(xlsColumnDefDlgKey);
                }
                if (mXlsColumnDefDlg == null) {
                    mXlsColumnDefDlg = new XlsColumnDefDlg();
                }
            }
            if (TAB_UI1 != null) {
                xlsLoader = (FastDBQueryUI_XlsColumnDefLoader) TAB_UI1.getResourcesPool().get(xlsLoaderResourceKey);
            }
            boolean isNeedExecute = false;
            if (!isXlsLoaderInit() || reset) {
                xlsLoader = new FastDBQueryUI_XlsColumnDefLoader(null, mXlsColumnDefDlg.getConfig());
                xlsLoader.setLoadingInfoListener(loadingInfoListener);
                isNeedExecute = true;
            }
            if (mXlsColumnDefDlg.getConfig() == null || mXlsColumnDefDlg.getConfig().isEmpty()) {
                if (showErrMsg) {
                    Validate.isTrue(false, "請先按設定");
                }
            } else {
                xlsLoader.setMappingConfig(mXlsColumnDefDlg.getConfig());
            }
            if (isNeedExecute) {
                xlsLoader.execute();
            }
            if (TAB_UI1 != null) {
                TAB_UI1.getResourcesPool().put(xlsLoaderResourceKey, xlsLoader);
                TAB_UI1.getResourcesPool().put(xlsColumnDefDlgKey, mXlsColumnDefDlg);
            }
            return true;
        }

        private boolean init(boolean reset) {
            File dir = new File(FileUtil.DESKTOP_DIR, "FastColumnDef");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!isXlsLoaderInit() || reset) {
                checkXlsLoader(reset, true);
            }
            if (tableColumnDefText.getSelectedItem() != null && StringUtils.isNotBlank((String) tableColumnDefText.getSelectedItem())) {
                return true;
            }
            return false;
        }

        private boolean isXlsLoaderInit() {
            if (xlsLoader != null && xlsLoader.isInitOk()) {
                return true;
            }
            xlsLoader = (FastDBQueryUI_XlsColumnDefLoader) TAB_UI1.getResourcesPool().get(xlsLoaderResourceKey);
            if (xlsLoader != null && xlsLoader.isInitOk()) {
                return true;
            }
            return false;
        }

        private void init2(boolean showErrMsg) {
            File dir = new File(FileUtil.DESKTOP_DIR, "FastColumnDef");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!isXlsLoaderInit()) {//
                checkXlsLoader(false, false);
            }
        }

        public void action(boolean reset) {
            try {
                if (init(reset)) {
                    String table = String.valueOf(tableColumnDefText.getSelectedItem());
                    if (FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF.equals(table)) {
                        queryResultTable.setTitleTooltipTransformer(xlsLoader.getTableTitleTransformer_NA());
                    } else {
                        queryResultTable.setTitleTooltipTransformer(xlsLoader.getTableTitleTransformer(table));
                    }
                }
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }

        public String getChinese(String column, String table) {
            try {
                init2(false);
                if (!isXlsLoaderInit()) {
                    return null;
                }
                if (StringUtils.isBlank(table)) {
                    table = String.valueOf(tableColumnDefText.getSelectedItem());
                }
                if (StringUtils.isBlank(table)) {
                    table = FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF;
                }
                if (FastDBQueryUI_XlsColumnDefLoader.FIND_TABLE_NAME_NA_DEF.equals(table)) {
                    return xlsLoader.getDBColumnChinese_NA(column, true);
                }
                return xlsLoader.getDBColumnChinese(column, true, table);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
            return null;
        }

        private String getTableAlias(String table, boolean appendDot) {
            String sql = currentSQL.get();
            Pattern ptn = Pattern.compile(table + "\\s+(\\w+)", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher mth = ptn.matcher(sql);
            if (mth.find()) {
                return StringUtils.trimToEmpty(mth.group(1)) + (appendDot ? "." : "");
            }
            return "";
        }

        public String getSelectColumns() {
            StringBuffer sb = new StringBuffer();
            try {
                if (StringUtils.isBlank((String) tableColumnDefText.getSelectedItem())) {
                    JCommonUtil._jOptionPane_showMessageDialog_error("請輸入表格名稱");
                }
                if (init(false)) {
                    String table = String.valueOf(tableColumnDefText.getSelectedItem());
                    String tableAlias = getTableAlias(table, true);
                    List<String> colLst = xlsLoader.getColumnLst(table);
                    if (colLst == null) {
                        colLst = Collections.EMPTY_LIST;
                    }
                    for (Object tit : JTableUtil.newInstance(queryResultTable).getColumnTitleArray()) {
                        String column = (String) tit;
                        if (ListUtil.constainIgnorecase(column, colLst)) {
                            String chinese = xlsLoader.getDBColumnChinese(column, false, table);
                            sb.append(tableAlias + column + " /*" + chinese + "*/,\r\n");
                        } else {
                            sb.append(tableAlias + column + " /*" + "" + "*/,\r\n");
                        }
                    }
                }
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
            return sb.toString();
        }

        public Map<String, String> getColumnsAndChinese(String table, boolean showErrMsg) {
            Map<String, String> rtnMap = new LinkedHashMap<String, String>();
            try {
                init2(showErrMsg);
                List<String> colLst = xlsLoader.getColumnLst(table);
                if (colLst == null) {
                    colLst = Collections.EMPTY_LIST;
                }
                for (String column : colLst) {
                    if (ListUtil.constainIgnorecase(column, colLst)) {
                        String chinese = xlsLoader.getDBColumnChinese(column, false, table);
                        rtnMap.put(column, chinese);
                    }
                }
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
            return rtnMap;
        }

        public List<String> getPkLst(boolean slient) {
            try {
                if (init(false)) {
                    String table = String.valueOf(tableColumnDefText.getSelectedItem());
                    return xlsLoader.getPkList(table);
                }
            } catch (Exception ex) {
                if (!slient) {
                    JCommonUtil.handleException(ex);
                } else {
                    ex.printStackTrace();
                }
            }
            return Collections.emptyList();
        }

        public Triple<DefaultTableModel, Integer, ActionListener> query(String tableQry, String columnQry, String otherQry, boolean hasChinese, JTable jtable, JFrame jframe) {
            init2(true);
            return xlsLoader.query(tableQry, columnQry, otherQry, hasChinese, jtable, jframe);
        }
    }

    // ===========================================================================================================================

    public List<String> getCompareXlsColumnLst(File xlsFile) {
        ExcelUtil_Xls97 xlsUtil = ExcelUtil_Xls97.getInstance();
        HSSFWorkbook wb = xlsUtil.readExcel(xlsFile);
        HSSFSheet sheet = wb.getSheetAt(0);
        if (wb.getNumberOfSheets() == 2) {
            sheet = wb.getSheetAt(1);
        }
        List<String> columnLst = new ArrayList<String>();
        for (int jj = 0; jj < sheet.getRow(0).getLastCellNum(); jj++) {
            String value = ExcelUtil_Xls97.getInstance().readCell(sheet.getRow(0).getCell(jj));
            columnLst.add(value);
        }
        return columnLst;
    }

    private void compareTwoTableBtnAction() {
        try {
            if (mFastDBQueryUI_TwoTableDlgUI != null) {
                mFastDBQueryUI_TwoTableDlgUI.dispose();
            }
            mFastDBQueryUI_TwoTableDlgUI = FastDBQueryUI_TwoTableDlgUI.newInstance(this.getRandom_TableNSchema(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                }
            }, this);
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    private void compareXlsExecuteBtnAction() {
        try {
            final String fileMiddleName = StringUtils.trimToEmpty(compareXlsMiddleNameText.getText());
            final File beforeXlsFile = JCommonUtil.filePathCheck(compareBeforeXlsText.getText(), "初始XLS檔案錯誤", "xls");
            final File afterXlsFile = JCommonUtil.filePathCheck(compareAfterXlsText.getText(), "結果XLS檔案錯誤", "xls");
            JCommonUtil.isBlankErrorMsg(fileMiddleName, "中間檔名不可為空");
            final List<String> columnLst = getCompareXlsColumnLst(beforeXlsFile);

            final AtomicReference<Map<String, String>> columnsAndChinese = new AtomicReference<Map<String, String>>();
            if (StringUtils.isNotBlank(compareXlsColumnSettingTitleText.getText())) {
                String tableName = compareXlsColumnSettingTitleText.getText();
                columnsAndChinese.set(mTableColumnDefTextHandler.getColumnsAndChinese(tableName, true));
            }

            final FastDBQueryUI_RowPKSettingDlg mFastDBQueryUI_RowDiffWatcherDlg = (FastDBQueryUI_RowPKSettingDlg.newInstance(columnLst, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FastDBQueryUI_RowPKSettingDlg dlg = (FastDBQueryUI_RowPKSettingDlg) e.getSource();
                    try {
                        final List<Integer> pkIndexLst = new ArrayList<Integer>();
                        for (int ii = 0; ii < columnLst.size(); ii++) {
                            String column = columnLst.get(ii);
                            for (String mColumn : dlg.getPkLst()) {
                                if (StringUtils.equals(column, mColumn)) {
                                    pkIndexLst.add(ii);
                                }
                            }
                        }
                        if (pkIndexLst.isEmpty()) {
                            JCommonUtil._jOptionPane_showMessageDialog_error("請選擇主鍵!");
                        } else {
                            // -------------------------------------------------↓↓↓↓↓↓

                            FastDBQueryUI_RecordWatcherDirectXls mFastDBQueryUI_RecordWatcherDirectXls = new FastDBQueryUI_RecordWatcherDirectXls(fileMiddleName, pkIndexLst, columnsAndChinese.get());
                            Pair<File, String> result = mFastDBQueryUI_RecordWatcherDirectXls.run(beforeXlsFile, afterXlsFile);
                            File reulstFile = result.getLeft();
                            String errMsg = result.getRight();
                            if (StringUtils.isNotBlank(errMsg)) {
                                dlg.dispose();
                                JCommonUtil._jOptionPane_showMessageDialog_error(errMsg);
                            }
                            if (reulstFile != null && reulstFile.exists()) {
                                dlg.dispose();
                                JCommonUtil._jOptionPane_showMessageDialog_error("檔案產生成功\n" + reulstFile);
                            }
                            // -------------------------------------------------↑↑↑↑↑↑
                        }
                    } catch (Exception ex) {
                        dlg.dispose();
                        JCommonUtil.handleException(ex);
                    }
                }
            }, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            }));
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    // ==============================================================================================
    private void tableColumnConfigBtnAction() {
        if (mXlsColumnDefDlg == null) {
            mXlsColumnDefDlg = new XlsColumnDefDlg();
        }
        mXlsColumnDefDlg.show();
    }

    private class XlsColumnDefDlg {
        private PropertiesUtilBean config = new PropertiesUtilBean(JAR_PATH_FILE, FastDBQueryUI.class.getSimpleName() + "_XlsColumnDefDlg");
        List<XlsColumnDefClz> lst;
        final JDialog dlg;
        final JLabel lbl;
        final JButton btn;
        JTable table;

        private List<XlsColumnDefClz> getConfig() {
            return lst;
        }

        private DefaultTableModel loadConfig() {
            List<XlsColumnDefClz> lst = new ArrayList<XlsColumnDefClz>();
            Properties prop = config.getConfigProp();
            XlsColumnDefClz c1 = new XlsColumnDefClz();
            XlsColumnDefClz c2 = new XlsColumnDefClz();
            XlsColumnDefClz c3 = new XlsColumnDefClz();
            XlsColumnDefClz c4 = new XlsColumnDefClz();
            if (prop.containsKey("column")) {
                c1.fromConfig(prop.getProperty("column"));
            } else {
                c1 = XlsColumnDefType.COLUMN.getConfig();
            }
            if (prop.containsKey("chinese")) {
                c2.fromConfig(prop.getProperty("chinese"));
            } else {
                c2 = XlsColumnDefType.CHINESE.getConfig();
            }
            if (prop.containsKey("pk")) {
                c3.fromConfig(prop.getProperty("pk"));
            } else {
                c3 = XlsColumnDefType.PK.getConfig();
            }
            if (prop.containsKey("table")) {
                c4.fromConfig(prop.getProperty("table"));
            } else {
                c4 = XlsColumnDefType.TABLE.getConfig();
            }
            lst.add(c1);
            lst.add(c2);
            lst.add(c3);
            lst.add(c4);
            for (Enumeration enu = prop.keys(); enu.hasMoreElements();) {
                String key = (String) enu.nextElement();
                if (key.contains("TAG")) {
                    XlsColumnDefClz cx = new XlsColumnDefClz();
                    cx.fromConfig(prop.getProperty(key));
                    lst.add(cx);
                }
            }
            DefaultTableModel model = JTableUtil.createModel(false, "類型", "標籤字", "index", "含有文字", "顏色", "refIndex");
            for (XlsColumnDefClz cx : lst) {
                model.addRow(cx.toArray());
            }
            return model;
        }

        private List<XlsColumnDefClz> saveAction() {
            Properties prop = config.getConfigProp();
            prop.clear();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            List<XlsColumnDefClz> lst = new ArrayList<XlsColumnDefClz>();
            for (int ii = 0; ii < model.getRowCount(); ii++) {
                XlsColumnDefClz c1 = new XlsColumnDefClz();
                c1.type = (XlsColumnDefType) model.getValueAt(ii, 0);
                c1.label = (String) model.getValueAt(ii, 1);
                c1.index = (Integer) model.getValueAt(ii, 2);
                c1.containText = (String) model.getValueAt(ii, 3);
                c1.color = (String) model.getValueAt(ii, 4);
                c1.refIndex = (Integer) model.getValueAt(ii, 5);
                if (c1.type == XlsColumnDefType.COLUMN) {
                    prop.setProperty("column", c1.toConfig());
                } else if (c1.type == XlsColumnDefType.CHINESE) {
                    prop.setProperty("chinese", c1.toConfig());
                } else if (c1.type == XlsColumnDefType.PK) {
                    prop.setProperty("pk", c1.toConfig());
                } else if (c1.type == XlsColumnDefType.TABLE) {
                    prop.setProperty("table", c1.toConfig());
                } else {
                    prop.setProperty("TAG" + ii, c1.toConfig());
                }
                lst.add(c1);
            }
            config.store();
            return lst;
        }

        private void initTable() {
            TableColumn sportColumn = table.getColumnModel().getColumn(0);
            JComboBox comboBox = new JComboBox();
            for (XlsColumnDefType e : XlsColumnDefType.values()) {
                comboBox.addItem(e);
            }
            sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
        }

        public XlsColumnDefDlg() {
            dlg = new JDialog() {
                public Dimension getPreferredSize() {
                    return new Dimension(600, 350);
                }
            };
            dlg.setModal(true);
            final JPanel pan = new JPanel();
            pan.setLayout(new BorderLayout(0, 0));
            lbl = new JLabel("");
            lbl.setText("設定EXCEL欄位定義");
            pan.add(lbl, BorderLayout.NORTH);
            table = new JTable();
            table.setModel(loadConfig());
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (JMouseEventUtil.buttonRightClick(1, e)) {
                        JPopupMenuUtil.newInstance(table).addJMenuItem(JTableUtil.newInstance(table).getDefaultJMenuItems()).applyEvent(e).show();
                    }
                }
            });
            JTableUtil.defaultSetting(table);
            initTable();
            pan.add(JCommonUtil.createScrollComponent(table), BorderLayout.CENTER);
            btn = new JButton("確定");
            pan.add(btn, BorderLayout.SOUTH);
            dlg.getContentPane().add(pan);
            dlg.pack();
            JCommonUtil.setJFrameCenter(dlg);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lst = saveAction();
                    dlg.dispose();

                    // 重設
                    if (mTableColumnDefTextHandler != null) {
                        final JProgressBarHelper prog = JProgressBarHelper.newInstance(FastDBQueryUI.this, "開始讀取xls欄位設定");

                        loadingInfoListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    Map<String, Object> info = (Map<String, Object>) e.getSource();
                                    String message = "";
                                    if (info.get("file") != null) {
                                        message += ((File) info.get("file")).getName();
                                    }
                                    message += info.get("message");
                                    prog.setStateText(message);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        };

                        prog.indeterminate(true);
                        prog.build();
                        prog.show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mTableColumnDefTextHandler.action(true);
                                prog.dismiss();
                                loadingInfoListener = loadingInfoListener_DEFAULT;
                            }
                        }, "---thread1").start();
                    }
                }
            });
        }

        public void show() {
            dlg.setVisible(true);
        }
    }

    // ======================================================================================================================
    private void initColumnXlsDefTableColumnQryTable() {
        try {
            if (mTableColumnDefTextHandler == null) {
                mTableColumnDefTextHandler.init(false);
            }
            String tableQry = columnXlsDefTableQryText.getText();
            String columnQry = columnXlsDefColumnQryText.getText();
            String otherQry = columnXlsDefOtherQryText.getText();
            boolean hasChinese = columnXlsDefShowChineseChk.isSelected();

            Triple<DefaultTableModel, Integer, ActionListener> result = mTableColumnDefTextHandler.query(tableQry, columnQry, otherQry, hasChinese, columnXlsDefTableColumnQryTable, this);
            DefaultTableModel model = result.getLeft();
            columnXlsDefFindRowCountLbl.setText(String.valueOf(result.getMiddle()));
            columnXlsDefTableColumnQryTable.setModel(model);
            result.getRight().actionPerformed(new ActionEvent("", -1, ""));
        } catch (Exception ex) {
            JCommonUtil.handleException(ex);
        }
    }

    // ======================================================================================================================
    // ======================================================================================================================
    // alt + 左右切換頁籤工具
    private class MoveTabsNativeKeyListener implements NativeKeyListener {
        MoveTabsNativeKeyListener() {
            initialize();
        }

        private void initialize() {
            if (OsInfoUtil.isMac()) {
                return;
            }
            try {
                if (!GlobalScreen.isNativeHookRegistered()) {
                    GlobalScreen.registerNativeHook();
                }
            } catch (NativeHookException e) {
                JCommonUtil.handleException(e);
                // throw new RuntimeException(e);
            }
            GlobalScreen.removeNativeKeyListener(this);// 記得她媽先移除否則會多掛listener
            GlobalScreen.addNativeKeyListener(this);
            JnativehookKeyboardMouseHelper.getInstance().disableLogger();
        }

        public void close() {
            if (!GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.removeNativeKeyListener(this);
            }
        }

        @Override
        public void nativeKeyTyped(NativeKeyEvent paramNativeKeyEvent) {
        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent paramNativeKeyEvent) {
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent e) {
            try {
                if (TAB_UI1 != null && JCommonUtil.isOnTop(TAB_UI1.getJframe())) {
                    boolean altClick = (e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0;
                    boolean ctlClick = (e.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0;
                    if (altClick) {
                        if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) {
                            System.out.println("tab --------LEFT");
                            if (tabbedPane.getSelectedIndex() > 0) {
                                // tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex()
                                // - 1);
                            }
                        } else if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
                            System.out.println("tab --------RIGHT");
                            if (tabbedPane.getSelectedIndex() <= tabbedPane.getTabCount() - 1) {
                                // tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex()
                                // + 1);
                            }
                        }
                    }
                    if (ctlClick) {
                        if (e.getKeyCode() == NativeKeyEvent.VC_LEFT) {
                            System.out.println("tab --------LEFT");
                            if (TAB_UI1.getSelectTabIndex() > 0) {
                                // TAB_UI1.setSelectTabIndex(TAB_UI1.getSelectTabIndex()
                                // - 1);
                            }
                        } else if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT) {
                            System.out.println("tab --------RIGHT");
                            if (TAB_UI1.getSelectTabIndex() <= TAB_UI1.getTabCount() - 1) {
                                // TAB_UI1.setSelectTabIndex(TAB_UI1.getSelectTabIndex()
                                // + 1);
                            }
                        }
                    }

                    // f1-f4
                    if (e.getKeyCode() == NativeKeyEvent.VC_F1) {
                        // tabbedPane.setSelectedIndex(0);
                    } else if (e.getKeyCode() == NativeKeyEvent.VC_F2) {
                        // tabbedPane.setSelectedIndex(1);
                    } else if (e.getKeyCode() == NativeKeyEvent.VC_F3) {
                        // tabbedPane.setSelectedIndex(2);
                    } else if (e.getKeyCode() == NativeKeyEvent.VC_F4) {
                        // tabbedPane.setSelectedIndex(3);
                    } else if (e.getKeyCode() == NativeKeyEvent.VC_F6) {
                        // showCommonToolDlg();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // ======================================================================================================================
    // 儲存回復工具
    private class UndoSaveHanlder {
        private LinkedList<String> sqlLst = new LinkedList<String>();

        private boolean hasRecord() {
            if (sqlLst.isEmpty()) {
                return false;
            }
            String text = sqlLst.getFirst();
            if (StringUtils.equals(text, sqlTextArea.getText())) {
                return false;
            }
            return true;
        }

        private void push(String text) {
            if (StringUtils.isBlank(text)) {
                return;
            }
            String sql = "";
            if (!sqlLst.isEmpty()) {
                sql = sqlLst.getFirst();
            }
            if (!StringUtils.equals(text, sql)) {
                sqlLst.push(text);
            }
        }

        private String reverse(boolean consume) {
            if (!sqlLst.isEmpty()) {
                String sql = sqlLst.getFirst();
                if (consume) {
                    sqlLst.removeFirst();
                }
                return sql;
            }
            return "";
        }
    }

    // ======================================================================================================================
    // 記事本匯入
    private class ImportFromClipboard {
        private List<String> titles;
        private List<Object[]> rowLst = new ArrayList<Object[]>();
        private QueryResultPoolHandler mQueryResultPoolHandler;
        private SapHanaDateHandler mSapHanaDateHandler = new SapHanaDateHandler();

        public void setQueryResultPoolHandler(QueryResultPoolHandler mQueryResultPoolHandler) {
            this.mQueryResultPoolHandler = mQueryResultPoolHandler;
        }

        private Map<String, String> parseMain2(String text) {
            String[] dropdownLst = new String[] { "分行處理", //
                    "完整Json", //
                    "Sap Hana"//
            };

            String choice = (String) JCommonUtil._JOptionPane_showInputDialog("請選擇匯入類型!", "匯入類型", dropdownLst, "分行處理");

            if ("分行處理".equals(choice)) {
                String[] multLine = text.split("\n");
                for (String line : multLine) {
                    if (StringUtils.isNotBlank(line)) {
                        Map<String, String> map = null;
                        try {
                            map = _parseJSON(line);
                        } catch (JSONException e) {
                            map = _parseToString(line);
                            if (map == null || map.isEmpty()) {
                                map = FastDBQueryUI_InsertSqlParseToMap.parseSql(line);
                            }
                        }

                        if (titles == null && map != null) {
                            List<String> titLst = new ArrayList<String>();
                            for (String tit : map.keySet()) {
                                titLst.add(tit);
                            }
                            titles = titLst;
                        }

                        if (titles != null && !titles.isEmpty()) {
                            Object[] row = new Object[titles.size()];
                            for (int ii = 0; ii < titles.size(); ii++) {
                                String tit = titles.get(ii);
                                String value = "";
                                if (map.containsKey(tit)) {
                                    value = map.get(tit);
                                }
                                row[ii] = value;
                            }
                            rowLst.add(row);
                        }
                    }
                }
            } else if ("完整Json".equals(choice)) {
                Object[] data = this._parseJSONMain(text);
                titles = (List<String>) data[0];
                rowLst.addAll((List<Object[]>) data[1]);
            } else if ("Sap Hana".equals(choice)) {
                String[] multLine = text.split("\n");
                Pattern digitalPtn = Pattern.compile("^[\\d\\,]+$");
                if (multLine.length > 0) {
                    String[] ttt1 = multLine[0].split("\\;", -1);
                    List<String> titles2 = new ArrayList<String>();
                    List<Pair<String, Integer>> titleLst = new ArrayList<Pair<String, Integer>>();
                    for (String t : ttt1) {
                        String t2 = StringUtils.trimToEmpty(t);
                        int tLen = StringUtils.length(t);
                        titleLst.add(Pair.of(t2, tLen));
                        if (t2.length() == 0) {
                            continue;
                        }
                        titles2.add(t2);
                    }
                    titles = titles2;
                    for (int ii = 1; ii < multLine.length; ii++) {
                        String line = multLine[ii];
                        int startPos = 0;
                        List<Object> row = new ArrayList<Object>();
                        for (int jj = 0; jj < titleLst.size(); jj++) {
                            Pair<String, Integer> p = titleLst.get(jj);
                            int len = p.getRight();
                            String title = p.getLeft();
                            String value = StringUtils.substring(line, startPos, startPos + len);
                            value = StringUtils.trimToEmpty(value);
                            if (value.equals("?")) {
                                value = "";
                            }
                            if (digitalPtn.matcher(value).matches()) {
                                value = value.replaceAll("\\,", "");
                            } else {
                                value = mSapHanaDateHandler.sapHanaDateFormat(value);
                                value = mSapHanaDateHandler.sapHanaDateFormat2(value);
                            }
                            startPos += (len + 1);
                            if (StringUtils.isEmpty(title)) {
                                continue;
                            }
                            row.add(value);
                        }
                        rowLst.add(row.toArray());
                    }
                }
            }
            return null;
        }

        private Map<String, String> parseMain2共用池(String text) {
            if (mQueryResultPoolHandler != null) {
                if (titles == null || titles.isEmpty()) {
                    titles = mQueryResultPoolHandler.excelImportLst.getLeft();
                    Object[] row = mQueryResultPoolHandler.excelImportLst.getRight().get(mQueryResultPoolHandler.selectRowIndex);
                    rowLst.add(row);
                } else {
                    Object[] rowData = new Object[titles.size()];
                    List<String> titles2 = mQueryResultPoolHandler.excelImportLst.getLeft();
                    Object[] row = mQueryResultPoolHandler.excelImportLst.getRight().get(mQueryResultPoolHandler.selectRowIndex);
                    for (int ii = 0; ii < titles.size(); ii++) {
                        String col1 = titles.get(ii);
                        A: for (int jj = 0; jj < titles2.size(); jj++) {
                            String col2 = titles2.get(jj);
                            if (StringUtils.equalsIgnoreCase(col1, col2)) {
                                rowData[ii] = row[jj];
                                break A;
                            }
                        }
                    }
                    rowLst.add(rowData);
                }
            }
            return null;
        }

        private Map<String, String> _parseToString(String text) {
            Map<String, String> treeMap = new LinkedHashMap<String, String>();
            {
                Pattern ptn = Pattern.compile("(\\w+)\\=([\u4e00-\u9fa5\\w\\.○\\-\\_\\:\\s]*)");
                Matcher mth = ptn.matcher(text);
                while (mth.find()) {
                    String key = mth.group(1);
                    String val = mth.group(2);
                    val = DateUtil.filterUSADateString(val);
                    treeMap.put(key, val);
                }
            }
            {
                Pattern ptn = Pattern.compile("(\\w+)\\=((?:^\n|\n|\\*)*?)\\,\\s(?=\\w+\\=)");
                Matcher mth = ptn.matcher(text);
                while (mth.find()) {
                    String key = mth.group(1);
                    String val = mth.group(2);
                    val = DateUtil.filterUSADateString(val);
                    treeMap.put(key, val);
                }
            }
            return treeMap;
        }

        private Map<String, String> _parseJSON(String text) {
            Map<String, String> rtnMap = new HashMap<String, String>();
            JSONObject json = JSONObject.fromObject(text);
            for (Iterator it = json.keys(); it.hasNext();) {
                String key = (String) it.next();
                String value = json.getString(key);
                value = DateUtil.filterUSADateString(value);
                rtnMap.put(key, value);
            }
            return rtnMap;
        }

        private Object[] _parseJSONMain(String text) {
            Set<String> titles1 = new LinkedHashSet<String>();
            JSONArray arry = new JSONArray();
            try {
                arry = JSONArray.fromObject(text);
            } catch (Exception ex) {
            }
            try {
                JSONObject json = JSONObject.fromObject(text);
                arry.add(json);
            } catch (Exception ex) {
            }
            List<Object[]> rtnLst = new ArrayList<Object[]>();
            for (int ii = 0; ii < arry.size(); ii++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                JSONObject obj = arry.getJSONObject(ii);
                if (titles1.isEmpty()) {
                    for (Iterator it = obj.keys(); it.hasNext();) {
                        String key = (String) it.next();
                        titles1.add(key);
                    }
                }
                List<Object> values = new ArrayList<Object>();
                for (String key : titles1) {
                    String value = obj.getString(key);
                    value = DateUtil.filterUSADateString(value);
                    values.add(value);
                }
                rtnLst.add(values.toArray());
            }
            return new Object[] { new ArrayList<String>(titles1), rtnLst };
        }

        public void parseMain_fromFile(Boolean isAppend, File txtFile) {
            try {
                String text = FileUtil.loadFromFile(txtFile, "UTF8");

                /**
                 * 主要匯入資料處理
                 */
                parseMain2(text);

                /**
                 * 將處理好的 titles, rowLst 加到 QueryResult
                 */
                appendToQueryResult(isAppend);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }

        public void parseMain(Boolean isAppend) {
            try {
                String text = ClipboardUtil.getInstance().getContents();

                /**
                 * 主要匯入資料處理
                 */
                parseMain2(text);

                /**
                 * 將處理好的 titles, rowLst 加到 QueryResult
                 */
                appendToQueryResult(isAppend);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }

        public void parseMain共用池(Boolean isAppend) {
            try {
                String text = ClipboardUtil.getInstance().getContents();

                /**
                 * 主要匯入資料處理
                 */
                parseMain2共用池(text);

                /**
                 * 將處理好的 titles, rowLst 加到 QueryResult
                 */
                appendToQueryResult(isAppend);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }

        private void appendToQueryResult(Boolean isAppend) {
            DefaultTableModel model = (DefaultTableModel) getQueryResultTable().getModel();
            boolean useNewModel = false;
            if (getQueryList() == null || getQueryList().getRight() == null) {
                useNewModel = true;
            } else if (isAppend != null) {
                useNewModel = !isAppend;
            } else if (!get_radio_import_excel_isAppend().isSelected()) {
                useNewModel = true;
            }

            if (useNewModel) {
                model = JTableUtil.createModel(true, titles.toArray());
                getQueryResultTable().setModel(model);

                JTableUtil.newInstance(getQueryResultTable()).setRowHeightByFontSize();
                for (int ii = 0; ii < rowLst.size(); ii++) {
                    model.addRow(rowLst.get(ii));
                }
                JTableUtil.newInstance(getQueryResultTable()).setColumnWidths_ByDataContent(getQueryResultTable(), null, getInsets());

                Class<?>[] clzs = new Class[titles.size()];
                Arrays.fill(clzs, String.class);
                List<Class<?>> clzLst = Arrays.asList(clzs);

                setQueryList(Triple.of(titles, clzLst, rowLst));
            } else {

                if (getQueryList() == null || getQueryList().getRight() == null) {
                    JCommonUtil._jOptionPane_showMessageDialog_error("附加模式必須先有查詢結果!");
                    return;
                }

                List<Object> titlesN = JTableUtil.newInstance(queryResultTable).getColumnTitleArray();

                Map<Integer, Integer> titlesMap = new TreeMap<Integer, Integer>();
                A: for (int jj = 0; jj < titles.size(); jj++) {
                    for (int ii = 0; ii < titlesN.size(); ii++) {
                        String fastQueryTitle = (String) titlesN.get(ii);
                        String[] compareArry = new String[] { fastQueryTitle, //
                                StringUtilForDb.javaToDbField(fastQueryTitle), //
                                StringUtilForDb.dbFieldToJava(fastQueryTitle), //
                        };
                        for (String compareTo : compareArry) {
                            if (StringUtils.equalsIgnoreCase(String.valueOf(titles.get(jj)), compareTo)) {
                                titlesMap.put(ii, jj);
                                System.out.println("[O]找到對應title : " + titlesN.get(ii) + " , " + titles.get(jj));
                                continue A;
                            }
                        }
                    }
                    System.out.println("[X]找到對應title : " + titles.get(jj));
                }

                boolean isButtonStart = false;
                if (!titlesN.isEmpty() && StringUtils.equals(QUERY_RESULT_COLUMN_NO, String.valueOf(titlesN.get(0)))) {
                    isButtonStart = true;
                }

                List<Object[]> appendRowsLst = new ArrayList<Object[]>();
                for (int ii = 0; ii < rowLst.size(); ii++) {
                    TreeMap<Integer, Object> rowMap = new TreeMap<Integer, Object>();
                    for (int jj = 0; jj < titlesN.size(); jj++) {
                        if (titlesMap.containsKey(jj)) {
                            int colMappingIdx = titlesMap.get(jj);
                            rowMap.put(jj, rowLst.get(ii)[colMappingIdx]);
                        } else {
                            rowMap.put(jj, null);
                        }
                    }

                    if (isButtonStart) {
                        rowMap.put(0, createSelectionBtn("*" + ii));
                    }

                    Object[] realRow = rowMap.values().toArray();
                    model.addRow(realRow);
                    appendRowsLst.add(realRow);
                }

                getQueryList().getRight().addAll(appendRowsLst);
            }
        }

        // ====================================== 以下要從TAB_UI1拿
        public JTable getQueryResultTable() {
            if (TAB_UI1 != null) {
                FastDBQueryUI mFastDBQueryUI = (FastDBQueryUI) TAB_UI1.getCurrentChildJFrame();
                return mFastDBQueryUI.queryResultTable;
            }
            return queryResultTable;
        }

        public Triple<List<String>, List<Class<?>>, List<Object[]>> getQueryList() {
            if (TAB_UI1 != null) {
                FastDBQueryUI mFastDBQueryUI = (FastDBQueryUI) TAB_UI1.getCurrentChildJFrame();
                return mFastDBQueryUI.queryList;
            }
            return queryList;
        }

        public void setQueryList(Triple<List<String>, List<Class<?>>, List<Object[]>> queryList1) {
            if (TAB_UI1 != null) {
                FastDBQueryUI mFastDBQueryUI = (FastDBQueryUI) TAB_UI1.getCurrentChildJFrame();
                mFastDBQueryUI.queryList = queryList1;
            }
        }

        public JCheckBox get_radio_import_excel_isAppend() {
            if (TAB_UI1 != null) {
                FastDBQueryUI mFastDBQueryUI = (FastDBQueryUI) TAB_UI1.getCurrentChildJFrame();
                return mFastDBQueryUI.radio_import_excel_isAppend;
            }
            return radio_import_excel_isAppend;
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private class TitleSetLabel {
        private int getIndex(String columnName, int colIndex) {
            List<String> lst = JTableUtil.newInstance(queryResultTable).getColumnTitleStringArray();
            int index = 0;
            for (int ii = 0; ii <= colIndex; ii++) {
                if (StringUtils.equals(columnName, lst.get(ii))) {
                    index++;
                }
            }
            return index;
        }

        private boolean fixOneTry(final int colIndex, final String columnName, final String sql) {
            Pattern ptn = Pattern.compile(Pattern.quote(columnName), Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
            Matcher mth = ptn.matcher(sql);
            int realIndex = getIndex(columnName, colIndex);
            int index = 1;
            StringBuffer sb = new StringBuffer();
            boolean findOk = false;
            while (mth.find()) {
                if (realIndex == index) {
                    String label = JCommonUtil._jOptionPane_showInputDialog("請輸入標題");
                    if (StringUtils.isNotBlank(label)) {
                        label = " /*" + StringUtils.trimToEmpty(label) + "*/";
                        mth.appendReplacement(sb, mth.group() + label);
                    }
                    findOk = true;
                }
                index++;
            }
            mth.appendTail(sb);
            if (findOk) {
                sqlTextArea.setText(sb.toString());
            }
            return findOk;
        }

        private boolean fixTwoTry(final int colIndex, final String columnName, final String sql) {
            int pos = sql.indexOf(Pattern.quote(columnName));
            if (pos != -1) {
                pos += columnName.length();
                String beforeStr = sql.substring(0, pos);
                String afterStr = sql.substring(pos);
                String label = JCommonUtil._jOptionPane_showInputDialog("請輸入標題");
                if (StringUtils.isNotBlank(label)) {
                    label = " /*" + StringUtils.trimToEmpty(label) + "*/";
                    sqlTextArea.setText(beforeStr + label + afterStr);
                }
                return true;
            }
            return false;
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private enum SqlListSortCombobox_SortEnum {
        NAME_ASC("名子 asc"), //
        NAME_DESC("名子 desc"), //
        SAVETIME_DESC("修改日期 desc"), //
        SAVETIME_ASC("修改日期 asc"), //
        QUERYTIME_DESC("查詢日期 desc"), //
        QUERYTIME_ASC("查詢日期 asc"), //
        QUERYTIME_COUNT_DESC("查詢次數 desc"), //
        QUERYTIME_COUNT_ASC("查詢次數 asc"),//
        ;

        String label;

        SqlListSortCombobox_SortEnum(String label) {
            this.label = label;
        }

        public String toString() {
            return label;
        }

        static DefaultComboBoxModel getModel() {
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            for (SqlListSortCombobox_SortEnum e : SqlListSortCombobox_SortEnum.values()) {
                model.addElement(e);
            }
            return model;
        }
    }

    private void sortSqlListProcess(List<SqlIdConfigBean> sqlIdList) {
        SqlListSortCombobox_SortEnum sortType = (SqlListSortCombobox_SortEnum) sqlListSortCombobox.getSelectedItem();
        if (sortType != null) {
            switch (sortType) {
            case NAME_DESC:
                SqlIdConfigBean.SHOW_TIME_STATUS = -1;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = -1 * StringUtils.trimToEmpty(o1.category).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.category).toLowerCase());
                        int compare2 = -1 * StringUtils.trimToEmpty(o1.sqlId).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.sqlId).toLowerCase());
                        if (compare1 != 0) {
                            return compare1;
                        }
                        return compare2;
                    }
                });
                break;
            case NAME_ASC:
                SqlIdConfigBean.SHOW_TIME_STATUS = -1;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = StringUtils.trimToEmpty(o1.category).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.category).toLowerCase());
                        int compare2 = StringUtils.trimToEmpty(o1.sqlId).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.sqlId).toLowerCase());
                        if (compare1 != 0) {
                            return compare1;
                        }
                        return compare2;
                    }
                });
                break;
            case SAVETIME_DESC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 1;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = -1 * StringUtils.trimToEmpty(o1.latestUpdateTime).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.latestUpdateTime).toLowerCase());
                        return compare1;
                    }
                });
                break;
            case SAVETIME_ASC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 1;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = StringUtils.trimToEmpty(o1.latestUpdateTime).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.latestUpdateTime).toLowerCase());
                        return compare1;
                    }
                });
                break;
            case QUERYTIME_DESC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 2;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = -1 * StringUtils.trimToEmpty(o1.latestQueryTime).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.latestQueryTime).toLowerCase());
                        return compare1;
                    }
                });
                break;
            case QUERYTIME_ASC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 2;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = StringUtils.trimToEmpty(o1.latestQueryTime).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.latestQueryTime).toLowerCase());
                        return compare1;
                    }
                });
                break;
            case QUERYTIME_COUNT_DESC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 3;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = -1 * toCompareInt(o1.queryTimes).compareTo(toCompareInt(o2.queryTimes));
                        return compare1;
                    }
                });
                break;
            case QUERYTIME_COUNT_ASC:
                SqlIdConfigBean.SHOW_TIME_STATUS = 3;
                Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {
                    @Override
                    public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                        int compare1 = toCompareInt(o1.queryTimes).compareTo(toCompareInt(o2.queryTimes));
                        return compare1;
                    }
                });
                break;
            }
        } else {
            SqlIdConfigBean.SHOW_TIME_STATUS = -1;
            Collections.sort(sqlIdList, new Comparator<SqlIdConfigBean>() {

                @Override
                public int compare(SqlIdConfigBean o1, SqlIdConfigBean o2) {
                    int compare1 = StringUtils.trimToEmpty(o1.category).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.category).toLowerCase());
                    int compare2 = StringUtils.trimToEmpty(o1.sqlId).toLowerCase().compareTo(StringUtils.trimToEmpty(o2.sqlId).toLowerCase());
                    if (compare1 != 0) {
                        return compare1;
                    }
                    return compare2;
                }

            });
        }
    }

    private static Integer toCompareInt(String strVal) {
        try {
            return Integer.parseInt(StringUtils.trimToEmpty(strVal));
        } catch (Exception ex) {
            return 0;
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private class ToStringReplaceOldSql {
        Pattern fieldEqualValuePtn = Pattern.compile("(\\w+)\\s*\\=\\s*(\\'.+?\\'|\\-?[\\.\\d]+|\\:\\w+)");

        protected boolean isQuoteStartEnd(String replaceBefore) {
            if (replaceBefore.length() > 0 && //
                    replaceBefore.substring(0, 1).contentEquals("'")//
                    && StringUtils.substring(replaceBefore, -1).equals("'")) {
                return true;
            }
            return false;
        }

        protected String replaceGroup(Matcher mth, int groupIndex, String replaceStr) {
            int offset = mth.start();
            int start = mth.start(groupIndex) - offset;
            int end = mth.end(groupIndex) - offset;
            String replaceBefore = mth.group(groupIndex);
            if (// isQuoteStartEnd(replaceBefore) && //
            !isQuoteStartEnd(replaceStr)) {
                replaceStr = "'" + replaceStr + "'";
            }
            String groupOrigin = mth.group();
            // groupOrigin = StringUtils.rightPad(groupOrigin,
            // mth.group().length());
            StringBuilder sb = new StringBuilder(groupOrigin);
            sb.replace(start, end, StringUtils.defaultString(replaceStr));
            return sb.toString();
        }

        protected Object[] parseForVertical(String content) {
            List<String> lst = StringUtil_.readContentToList(StringUtils.defaultString(content), true, true, true);
            List<String> titles = new ArrayList<String>();
            List<Object> values = new ArrayList<Object>();
            for (String splitString : lst) {
                for (String ptn : new String[] { "\\t", "\\s+" }) {
                    String[] arry = splitString.split(ptn);
                    if (arry.length >= 2) {
                        String columnName = StringUtils.trimToEmpty(arry[0]);
                        String value = StringUtils.trimToEmpty(arry[1]);
                        titles.add(columnName);
                        values.add(value);
                        continue;
                    }
                }
            }
            return new Object[] { titles, values.toArray() };
        }

        protected Object[] beforeProcess(String fromType) {
            String sql = StringUtils.defaultString(sqlTextArea.getText());

            ImportFromClipboard mImportFromClipboard = new ImportFromClipboard();
            String clipboardText = ClipboardUtil.getInstance().getContents();

            List<String> titles = null;
            List<Object[]> rowDataLst = new ArrayList<Object[]>();

            if ("clipboard".equalsIgnoreCase(fromType)) {
                mImportFromClipboard.parseMain2(clipboardText);
                titles = mImportFromClipboard.titles;
                rowDataLst.addAll(mImportFromClipboard.rowLst);

            } else if ("clipboard_vertical".equalsIgnoreCase(fromType)) {
                Object[] data1 = parseForVertical(clipboardText);
                titles = (List<String>) data1[0];
                Object[] row = (Object[]) data1[1];
                rowDataLst.add(row);

            } else if ("selectIndex".equalsIgnoreCase(fromType)) {
                Pair<List<String>, List<Object[]>> excelImportLst = transRealRowToQuyerLstIndex();// orignQueryResult
                titles = excelImportLst.getLeft();
                for (int selectRowIndex : queryResultTable.getSelectedRows()) {
                    rowDataLst.add(excelImportLst.getRight().get(selectRowIndex));
                }
            }

            if (titles == null || rowDataLst.isEmpty()) {
                JCommonUtil._jOptionPane_showMessageDialog_error("無法選擇套用SQL[0893] : " + fromType);
                return null;
            }
            return new Object[] { titles, rowDataLst, sql };
        }

        protected void execute(String fromType) {
            try {
                Object[] data = this.beforeProcess(fromType);
                if (data == null) {
                    return;
                }

                List<String> titles = (List<String>) data[0];
                List<Object[]> rowDataLst = (List<Object[]>) data[1];
                String sql = (String) data[2];

                this.finalProcess(titles, rowDataLst, sql);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }

        protected void finalProcess(List<String> titles, List<Object[]> rowDataLst, String sql) {
            StringBuffer sb2 = new StringBuffer();

            for (Object[] rowData : rowDataLst) {
                StringBuffer sb = new StringBuffer();
                Matcher mth = fieldEqualValuePtn.matcher(sql);
                while (mth.find()) {
                    String column = mth.group(1);
                    String valueStr = "";

                    boolean findOk = false;
                    A: for (int ii = 0; ii < titles.size(); ii++) {
                        String column2 = titles.get(ii);
                        String column3 = StringUtilForDb.javaToDbField(column2);
                        if (StringUtils.equalsIgnoreCase(column, column2) || //
                                StringUtils.equalsIgnoreCase(column, column3)) {
                            Object value = rowData[ii];
                            if (value != null) {
                                valueStr = String.valueOf(value);
                            }
                            findOk = true;
                            break A;
                        }
                    }

                    String groupStr = mth.group();
                    if (findOk) {
                        groupStr = this.replaceGroup(mth, 2, valueStr);
                    }
                    mth.appendReplacement(sb, groupStr);
                }
                mth.appendTail(sb);
                sb2.append(sb.toString());
                sb2.append("\r\n\r\n");
            }

            sqlTextArea.setText(sb2.toString());
        }
    }

    private class ToStringReplaceParameterTable extends ToStringReplaceOldSql {

        protected void finalProcess(List<String> titles, List<Object[]> rowDataLst, String sql) {
            if (rowDataLst.isEmpty()) {
                return;
            }

            Object[] dataRow = rowDataLst.get(0);

            JTableUtil util = JTableUtil.newInstance(parametersTable);
            A: for (int ii = 0; ii < parametersTable.getRowCount(); ii++) {
                Boolean isInUse = (Boolean) util.getRealValueAt(ii, ParameterTableColumnDef.USE.idx);
                if (isInUse == null) {
                    isInUse = false;
                }

                String columnName = (String) util.getRealValueAt(ii, ParameterTableColumnDef.COLUMN.idx);
                for (int jj = 0; jj < titles.size(); jj++) {
                    String columnName2 = titles.get(jj);
                    if (StringUtils.equalsIgnoreCase(columnName, columnName2)) {
                        util.setValueAt(true, dataRow[jj], ii, ParameterTableColumnDef.VALUE.idx);
                        continue A;
                    }
                }
            }
        }

        protected void execute(String fromType) {
            try {
                Object[] data = this.beforeProcess(fromType);
                if (data == null) {
                    return;
                }

                List<String> titles = (List<String>) data[0];
                List<Object[]> rowDataLst = (List<Object[]>) data[1];
                String sql = (String) data[2];

                this.finalProcess(titles, rowDataLst, sql);
            } catch (Exception ex) {
                JCommonUtil.handleException(ex);
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    public static class SqlIdConfigBeanAndParameters extends SqlIdConfigBean {
        Map<String, String> params = new LinkedHashMap<String, String>();
        String sqlParamCommentArea;
        String columnFilterText;
        String rowFilterText;
        String sqlQueryText;
        String sqlContentFilterText;
        String sqlIdCategoryComboBox4Tab1;

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }

        public String getSqlParamCommentArea() {
            return sqlParamCommentArea;
        }

        public void setSqlParamCommentArea(String sqlParamCommentArea) {
            this.sqlParamCommentArea = sqlParamCommentArea;
        }

        public String getColumnFilterText() {
            return columnFilterText;
        }

        public void setColumnFilterText(String columnFilterText) {
            this.columnFilterText = columnFilterText;
        }

        public String getRowFilterText() {
            return rowFilterText;
        }

        public void setRowFilterText(String rowFilterText) {
            this.rowFilterText = rowFilterText;
        }

        public String getSqlQueryText() {
            return sqlQueryText;
        }

        public void setSqlQueryText(String sqlQueryText) {
            this.sqlQueryText = sqlQueryText;
        }

        public String getSqlContentFilterText() {
            return sqlContentFilterText;
        }

        public void setSqlContentFilterText(String sqlContentFilterText) {
            this.sqlContentFilterText = sqlContentFilterText;
        }

        public String getSqlIdCategoryComboBox4Tab1() {
            return sqlIdCategoryComboBox4Tab1;
        }

        public void setSqlIdCategoryComboBox4Tab1(String sqlIdCategoryComboBox4Tab1) {
            this.sqlIdCategoryComboBox4Tab1 = sqlIdCategoryComboBox4Tab1;
        }

        private void copyFrom(SqlIdConfigBean bean) {
            try {
                BeanUtils.copyProperties(this, bean);
            } catch (Exception e) {
            }
        }
    }

    private static class ParameterHandler {
        JTable parametersTable;

        public ParameterHandler(JTable parametersTable) {
            this.parametersTable = parametersTable;
        }

        private Map<String, String> getParameters() {
            Map<String, String> params = new LinkedHashMap<String, String>();
            DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
            JTableUtil u = JTableUtil.newInstance(parametersTable);
            for (int ii = 0; ii < model.getRowCount(); ii++) {
                String column = (String) u.getValueAt(false, ii, ParameterTableColumnDef.COLUMN.idx);
                String value = (String) u.getValueAt(false, ii, ParameterTableColumnDef.VALUE.idx);
                params.put(column, value);
            }
            return params;
        }

        private void restoreParameters(Map<String, String> params) {
            DefaultTableModel model = (DefaultTableModel) parametersTable.getModel();
            JTableUtil u = JTableUtil.newInstance(parametersTable);
            for (String key : params.keySet()) {
                for (int ii = 0; ii < model.getRowCount(); ii++) {
                    String column = (String) u.getValueAt(false, ii, ParameterTableColumnDef.COLUMN.idx);
                    String value = params.get(key);
                    if (StringUtils.equalsIgnoreCase(key, column)) {
                        u.setValueAt(false, value, ii, ParameterTableColumnDef.VALUE.idx);
                    }
                }
            }
        }
    }

    private static class AllTabPageProcess {
        private String FILE_END_NAME = "_(tabs).yml";

        SwingTabTemplateUI TAB_UI1;

        public AllTabPageProcess(SwingTabTemplateUI TAB_UI1) {
            this.TAB_UI1 = TAB_UI1;
        }

        public void save() {
            List<JFrame> list = TAB_UI1.getJframeKeeperLst();
            List<SqlIdConfigBeanAndParameters> list2 = new ArrayList<SqlIdConfigBeanAndParameters>();
            for (int ii = 0; ii < list.size(); ii++) {
                FastDBQueryUI frame = (FastDBQueryUI) list.get(ii);
                SqlIdConfigBean bean = frame.getCurrentEditSqlIdConfigBean();
                SqlIdConfigBeanAndParameters bean2 = new SqlIdConfigBeanAndParameters();
                bean2.copyFrom(bean);
                bean2.params = new ParameterHandler(frame.parametersTable).getParameters();
                // 欄位 ↓↓↓↓↓
                bean2.sqlParamCommentArea = StringUtils.defaultString(frame.sqlParamCommentArea.getText());
                bean2.columnFilterText = StringUtils.defaultString(frame.columnFilterText.getText());
                bean2.rowFilterText = StringUtils.defaultString(frame.rowFilterText.getText());
                bean2.sqlQueryText = StringUtils.defaultString(frame.sqlQueryText.getText());
                bean2.sqlContentFilterText = StringUtils.defaultString(frame.sqlContentFilterText.getText());
                bean2.sqlIdCategoryComboBox4Tab1 = StringUtils.defaultString(frame.sqlIdCategoryComboBox4Tab1_Auto.getTextComponent().getText());
                // 欄位↑↑↑↑↑
                list2.add(bean2);
            }
            String filename = FastDBQueryUI.class.getSimpleName() + "_頁籤_" + DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmmss");
            filename = JCommonUtil._jOptionPane_showInputDialog("頁籤暫存檔", filename);
            File ymlFile = new File(FileUtil.DESKTOP_DIR, filename + FILE_END_NAME);
            YamlMapUtil.getInstance().saveToFilePlain(ymlFile, list2, false, null);
            JCommonUtil._jOptionPane_showMessageDialog_info("儲存檔案：" + ymlFile.getName());
        }

        public void restore(File file) {
            if (!file.getName().endsWith(FILE_END_NAME)) {
                JCommonUtil._jOptionPane_showMessageDialog_error("檔案格式必須為*" + FILE_END_NAME);
                return;
            }
            List<SqlIdConfigBeanAndParameters> list2 = YamlMapUtil.getInstance().loadFromFile(file, SqlIdConfigBeanAndParameters.class, null);
            if (list2.isEmpty()) {
                JCommonUtil._jOptionPane_showMessageDialog_error("沒有任何頁！");
                return;
            }
            TAB_UI1.removeAllTabs();
            for (int ii = 0; ii < list2.size(); ii++) {
                FastDBQueryUI newFrame = new FastDBQueryUI();
                SqlIdConfigBeanAndParameters sqlBean1 = list2.get(ii);
                if (StringUtils.isBlank(sqlBean1.sqlId)) {
                    sqlBean1.sqlId = "未命名";
                }
                TAB_UI1.addTab(sqlBean1.toString(), (JFrame) newFrame, false);
                newFrame.sqlIdText.setText(sqlBean1.sqlId);
                if (StringUtils.isNotBlank(sqlBean1.sql)) {
                    newFrame.sqlTextArea.setText(sqlBean1.sql);
                }
                newFrame.sqlListMouseClicked(null, sqlBean1);
                new ParameterHandler(newFrame.parametersTable).restoreParameters(sqlBean1.params);
                // 欄位 ↓↓↓↓↓
                newFrame.sqlParamCommentArea.setText(sqlBean1.sqlParamCommentArea);
                newFrame.columnFilterText.setText(sqlBean1.columnFilterText);
                newFrame.rowFilterText.setText(sqlBean1.rowFilterText);
                newFrame.sqlQueryText.setText(sqlBean1.sqlQueryText);
                newFrame.sqlContentFilterText.setText(sqlBean1.sqlContentFilterText);
                newFrame.sqlIdCategoryComboBox4Tab1_Auto.setSelectItemAndText(sqlBean1.sqlIdCategoryComboBox4Tab1);
                // 欄位↑↑↑↑↑
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private static void initApplyAppMenu(SwingTabTemplateUI TAB_UI1) {
        mAllPageProcess = new AllTabPageProcess(TAB_UI1);

        JMenu menu1 = JMenuAppender.newInstance("暫存頁籤")//
                .addMenuItem("讀取", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        File file = JCommonUtil._jFileChooser_selectFileOnly();
                        mAllPageProcess.restore(file);
                    }
                })//
                .addMenuItem("儲存", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mAllPageProcess.save();
                    }
                })//
                .getMenu();
        JMenu mainMenu = JMenuAppender.newInstance("file")//
                .addMenuItem("item1", null)//
                .addChildrenMenu(menu1)//
                .getMenu();
        JMenuBarUtil.newInstance().addMenu(mainMenu).apply(TAB_UI1.getJframe());
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private JMenu addQueryResultPoolHandlerMenus() {
        final QueryResultPoolHandler mQueryResultPoolHandler = new QueryResultPoolHandler();
        JMenuAppender appender = JMenuAppender.newInstance("重現共用池資料");
        List<QueryResultPoolHandler> lst = mQueryResultPoolHandler.get();
        for (QueryResultPoolHandler vo : lst) {
            appender.addChildrenMenu(vo.getMenu());
        }
        JMenu mainMenu = JMenuAppender.newInstance(//
                "資料共用池(" + mQueryResultPoolHandler.count() + ")")//
                .addMenuItem("複製此筆至池", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mQueryResultPoolHandler.put();
                    }
                })//
                .addChildrenMenu(appender.getMenu())//
                .addMenuItem("清除共用池", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mQueryResultPoolHandler.remove();
                    }
                })//
                .getMenu();
        return mainMenu;
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private JMenu getShowAllColumnMenu() {
        JMenuAppender appender = JMenuAppender.newInstance("顯示全部");
        appender.addMenuItem("逗號", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableUtil tabUtil = JTableUtil.newInstance(queryResultTable);
                List<Object> lst = tabUtil.getColumnTitleArray();
                SimpleTextDlg.newInstance(StringUtils.join(lst, " , "), "", null).show();
            }
        });
        appender.addMenuItem("逗號多行", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableUtil tabUtil = JTableUtil.newInstance(queryResultTable);
                List<Object> lst = tabUtil.getColumnTitleArray();
                SimpleTextDlg.newInstance(StringUtils.join(lst, "\r\n"), "", null).show();
            }
        });
        appender.addMenuItem("多行逗號", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTableUtil tabUtil = JTableUtil.newInstance(queryResultTable);
                List<Object> lst = tabUtil.getColumnTitleArray();
                SimpleTextDlg.newInstance(StringUtils.join(lst, ",\r\n"), "", null).show();
            }
        });
        return appender.getMenu();
    }
    // --------------------------------------------------------------------------------------------------------------------------

    private JMenu getBaseSQL_Menus() {
        final AtomicReference<String> space = new AtomicReference<String>();
        final List<String> lst = new ArrayList<String>();
        try {
            String selectText = StringUtils.defaultString(sqlTextArea.getSelectedText());
            if (StringUtils.isBlank(selectText)) {
                selectText = ClipboardUtil.getInstance().getContents();
            }
            selectText = StringUtils.defaultString(selectText);
            space.set(JTextAreaUtil.getSpaceOfCaretPositionLine(sqlTextArea));
            Matcher mth = null;
            Pattern ptn = Pattern.compile("[\\w\\.]+");
            BufferedReader reader = new BufferedReader(new StringReader(selectText));
            for (String line = null; (line = reader.readLine()) != null;) {
                mth = ptn.matcher(line);
                while (mth.find()) {
                    lst.add(mth.group());
                }
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JMenuAppender appender = JMenuAppender.newInstance("SQL 基礎");
        appender.addMenuItem("Select", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StringBuffer sb = new StringBuffer();
                    if (!lst.isEmpty()) {
                        sb.append("select * \r\n");
                        sb.append(space.get()).append("from " + lst.get(0) + " t \r\n");
                        sb.append(space.get()).append("where 1=1 \r\n");
                        if (lst.size() > 1) {
                            for (int ii = 1; ii < lst.size(); ii++) {
                                String column = lst.get(ii);
                                sb.append(space.get()).append("    and t." + column + " = 'XXXXXXXX' \r\n");
                            }
                        }
                    }
                    String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                    String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                    sqlTextArea.setText(prefix + sb + suffix);
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });//
        appender.addMenuItem("Update", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    sb.append(space.get()).append("update ").append(lst.get(0)).append(" t \r\n");
                    if (lst.size() > 1) {
                        for (int ii = 1; ii < lst.size(); ii++) {
                            String column = lst.get(ii);
                            sb.append(space.get()).append("    and t." + column + " = 'XXXXXXXX' \r\n");
                            sb.append(space.get()).append("set t.AAAAAAA = 'xxxxxx' ").append(" t \r\n");
                        }
                    }
                    sb.append(space.get()).append("where t.AAAAAAA = 'xxxxxx' \r\n");
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//
        appender.addMenuItem("left join", new ActionListener() {

            private String getRandomAlias() {
                char a = (char) RandomUtil.rangeInteger((int) 'a', (int) 'z');
                int b = RandomUtil.rangeInteger(0, 9);
                return String.valueOf(a) + String.valueOf(b);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAlias = StringUtils.trimToEmpty(JCommonUtil._jOptionPane_showInputDialog("輸入來源alias (Def: TableName a column1 column2 ...)", "t"));
                if (StringUtils.isNotBlank(fromAlias)) {
                    fromAlias = fromAlias + ".";
                }
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    String alais = lst.get(1);
                    sb.append(space.get()).append("left join " + lst.get(0) + " " + alais + " on ");//
                    if (lst.size() >= 3) {
                        List<String> condLst = new ArrayList<String>();
                        for (int ii = 2; ii < lst.size(); ii++) {
                            condLst.add(" " + alais + "." + lst.get(ii) + " = " + fromAlias + lst.get(ii));//
                        }
                        sb.append(StringUtils.join(condLst, " and ") + "\r\n");
                    }
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//

        appender.addMenuItem("Where[IN](换行)", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    sb.append("(");
                    for (int ii = 0; ii < lst.size(); ii++) {
                        String cond = lst.get(ii);
                        if (ii != 0) {
                            sb.append(space.get());
                        }
                        sb.append("'").append(cond).append("'");
                        if (ii != lst.size() - 1) {
                            sb.append(" , \r\n");
                        }
                    }
                    sb.append(")");
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//

        appender.addMenuItem("Where[IN]", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    sb.append("(");
                    for (int ii = 0; ii < lst.size(); ii++) {
                        String cond = lst.get(ii);
                        sb.append("'").append(cond).append("'");
                        if (ii != lst.size() - 1) {
                            sb.append(" , ");
                        }
                    }
                    sb.append(")");
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//

        appender.addMenuItem("Where[硬]", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    for (String cond : lst) {
                        sb.append(space.get()).append(" and ").append(cond).append(" = 'XXXXXXXXXX'   \r\n");
                    }
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//
        appender.addMenuItem("Where[代]", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    for (String cond : lst) {
                        sb.append(space.get()).append(" [  and ").append(cond).append(" = :").append(cond).append("  ] \r\n");
                    }
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//
        appender.addMenuItem("where [加]", new ActionListener() {
            Pattern ptn = Pattern.compile("[\\w+\\.]+");

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = StringUtils.trimToEmpty(JCommonUtil._jOptionPane_showInputDialog("輸入alias (Def: 表1_alias  表2_alias)", "t a"));
                Matcher mth = ptn.matcher(text);
                String fromAlias = "";
                String toAlias = "";
                while (mth.find()) {
                    if (StringUtils.isBlank(fromAlias)) {
                        fromAlias = mth.group();
                    } else {
                        toAlias = mth.group();
                    }
                }
                StringBuilder sb = new StringBuilder();
                if (!lst.isEmpty()) {
                    List<String> condLst = new ArrayList<String>();
                    for (String cond : lst) {
                        condLst.add(" " + fromAlias + "." + cond + " = " + toAlias + "." + cond);//
                    }
                    sb.append(StringUtils.join(condLst, " and ") + "\r\n");
                }
                String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                sqlTextArea.setText(prefix + sb + suffix);
            }
        });//

        appender.addMenuItem("Select PK [Oracle]", new ActionListener() {

            private Pair<String, String> getTableNSchema(String tableNameAndSchema) {
                String tableName = "";
                String schema = "";
                String[] arry = tableNameAndSchema.split("\\.", -1);
                if (arry.length == 1) {
                    tableName = StringUtils.trimToEmpty(arry[0]);
                } else if (arry.length >= 2) {
                    schema = StringUtils.trimToEmpty(arry[0]);
                    tableName = StringUtils.trimToEmpty(arry[1]);
                }
                return Pair.of(tableName, schema);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String tableNameAndSchema = "";
                    if (StringUtils.isNotBlank(sqlTextArea.getSelectedText())) {
                        tableNameAndSchema = StringUtils.trimToEmpty(sqlTextArea.getSelectedText());
                    }
                    if (StringUtils.isBlank(tableNameAndSchema)) {
                        tableNameAndSchema = StringUtils.trimToEmpty(JCommonUtil._jOptionPane_showInputDialog("schema.table", ""));
                        if (StringUtils.isBlank(tableNameAndSchema)) {
                            return;
                        }
                    }

                    Pair<String, String> tabNSch = getTableNSchema(tableNameAndSchema);
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append("   SELECT cols.table_name, cols.column_name, cols.position, cons.status, cons.owner, cons.CONSTRAINT_NAME   \n");//
                    sb1.append("   FROM all_constraints cons, all_cons_columns cols  \n");//
                    sb1.append("   WHERE 1=1  \n");//
                    sb1.append("     and   cols.table_name = upper('" + StringUtils.trimToEmpty(tabNSch.getLeft()) + "')   \n");//
                    if (StringUtils.isNotBlank(tabNSch.getRight())) {
                        sb1.append("     and   cols.owner = upper('" + StringUtils.trimToEmpty(tabNSch.getRight()) + "')  \n");
                    }
                    sb1.append("   AND cons.constraint_type = 'P'  \n");//
                    sb1.append("   AND cons.constraint_name = cols.constraint_name  \n");//
                    sb1.append("   AND cons.owner = cols.owner  \n");//
                    sb1.append("   ORDER BY cols.table_name, cols.position  \n");//

                    List<Map<String, Object>> qLst = JdbcDBUtil.queryForList(sb1.toString(), getDataSource().getConnection(), true);

                    String schemaPrefix = "";
                    if (StringUtils.isNotBlank(tabNSch.getRight())) {
                        schemaPrefix = StringUtils.trimToEmpty(tabNSch.getRight()) + ".";
                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append(space.get()).append(" select * \r\n");
                    sb.append(space.get()).append(" from " + schemaPrefix + StringUtils.trimToEmpty(tabNSch.getLeft()) + " t \r\n");
                    sb.append(space.get()).append(" where 1=1 \r\n");
                    for (Map<String, Object> map : qLst) {
                        String cond = (String) map.get("COLUMN_NAME");
                        sb.append(space.get()).append("     and " + "t" + "." + cond + " = 'XXXXXXXXXXXX' \r\n");//
                    }

                    String prefix = StringUtils.substring(sqlTextArea.getText(), 0, sqlTextArea.getSelectionStart());
                    String suffix = StringUtils.substring(sqlTextArea.getText(), sqlTextArea.getSelectionEnd());
                    sqlTextArea.setText(prefix + sb + suffix);
                } catch (Exception ex) {
                    JCommonUtil.handleException(ex);
                }
            }
        });//
        return appender.getMenu();
    }
    // --------------------------------------------------------------------------------------------------------------------------

    private class QueryResultPoolHandler {
        LRUMap map = new LRUMap(15);
        Pair<List<String>, List<Object[]>> excelImportLst;
        int selectRowIndex;
        String name;
        long key;

        private JMenu getMenu() {
            final ImportFromClipboard mImportFromClipboard = new ImportFromClipboard();
            mImportFromClipboard.setQueryResultPoolHandler(this);
            JMenuAppender appender = JMenuAppender.newInstance(name);
            appender.addMenuItem("新開", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mImportFromClipboard.parseMain共用池(false);
                }
            });
            appender.addMenuItem("附加", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mImportFromClipboard.parseMain共用池(true);
                }
            });
            appender.addMenuItem("移除", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (TAB_UI1 != null) {
                        if (TAB_UI1.getResourcesPool().containsKey(QUERY_RESULT_POOL_KEY)) {
                            map = (LRUMap) TAB_UI1.getResourcesPool().get(QUERY_RESULT_POOL_KEY);
                            map.remove(key);
                        }
                    }
                }
            });
            return appender.getMenu();
        }

        private List<QueryResultPoolHandler> get() {
            List<QueryResultPoolHandler> returnLst = new ArrayList<QueryResultPoolHandler>();
            if (TAB_UI1 != null) {
                if (TAB_UI1.getResourcesPool().containsKey(QUERY_RESULT_POOL_KEY)) {
                    map = (LRUMap) TAB_UI1.getResourcesPool().get(QUERY_RESULT_POOL_KEY);
                    List<Long> keys = new ArrayList<Long>(map.keySet());
                    Collections.sort(keys);
                    Collections.reverse(keys);
                    for (Long key : keys) {
                        returnLst.add((QueryResultPoolHandler) map.get(key));
                    }
                }
            }
            return returnLst;
        }

        private void put() {
            if (TAB_UI1 != null) {
                excelImportLst = transRealRowToQuyerLstIndex();// orignQueryResult
                selectRowIndex = queryResultTable.getSelectedRow();
                key = System.currentTimeMillis();

                {
                    name = getCurrentEditSqlIdConfigBean().getSqlId();
                    if (StringUtils.isBlank(name)) {
                        name = getRandom_TableNSchema();
                    }
                    if (StringUtils.isBlank(name)) {
                        name = DateFormatUtils.format(System.currentTimeMillis(), "yyyy/MM/dd HH:mm:ss");
                    }
                    name += "(" + (selectRowIndex + 1) + ")";
                }

                if (TAB_UI1.getResourcesPool().containsKey(QUERY_RESULT_POOL_KEY)) {
                    map = (LRUMap) TAB_UI1.getResourcesPool().get(QUERY_RESULT_POOL_KEY);
                } else {
                    TAB_UI1.getResourcesPool().put(QUERY_RESULT_POOL_KEY, map);
                }
                map.put(key, this);
            }
        }

        private int count() {
            if (TAB_UI1 != null) {
                if (TAB_UI1.getResourcesPool().containsKey(QUERY_RESULT_POOL_KEY)) {
                    LRUMap map = (LRUMap) TAB_UI1.getResourcesPool().get(QUERY_RESULT_POOL_KEY);
                    return map.size();
                }
            }
            return 0;
        }

        private void remove() {
            if (TAB_UI1 != null) {
                TAB_UI1.getResourcesPool().remove(QUERY_RESULT_POOL_KEY);
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    final static String KEYWORD_DEF = "(select|insert|update|delete|from|where|distinct|union|all|and|set|group|by|order|desc|asc|create|table|left|right|outter|inner|join|having|or)";

    private static DefaultStyledDocument sqlTextAreaDoc = new DefaultStyledDocument() {
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        final Pattern keywordDefPtn = Pattern.compile("(\\W)*" + KEYWORD_DEF, Pattern.CASE_INSENSITIVE);

        private int findLastNonWordChar(String text, int index) {
            while (--index >= 0) {
                if (String.valueOf(text.charAt(index)).matches("\\W")) {
                    break;
                }
            }
            return index;
        }

        private int findFirstNonWordChar(String text, int index) {
            while (index < text.length()) {
                if (String.valueOf(text.charAt(index)).matches("\\W")) {
                    break;
                }
                index++;
            }
            return index;
        }

        public String getFinalLineBeforeSpace(String text1) {
            String finalLine = null;
            BufferedReader reader = new BufferedReader(new StringReader(text1));
            try {
                for (String line = null; (line = reader.readLine()) != null;) {
                    finalLine = line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (Exception e) {
            }
            Pattern ptn = Pattern.compile("^[\\s\t]+");
            Matcher mth = ptn.matcher(StringUtils.defaultString(finalLine));
            if (mth.find()) {
                return mth.group();
            }
            return "";
        }

        public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
            // 加入 Enter padding
            if (str.toCharArray()[0] == '\n') {
                String text1 = getText(0, offset);
                String strPadding = getFinalLineBeforeSpace(text1);
                str = str + strPadding;
            }

            super.insertString(offset, str, a);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offset);
            if (before < 0)
                before = 0;
            int after = findFirstNonWordChar(text, offset + str.length());
            int wordL = before;
            int wordR = before;

            while (wordR <= after) {
                if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                    if (keywordDefPtn.matcher(text.substring(wordL, wordR)).matches())
                        setCharacterAttributes(wordL, wordR - wordL, attr, false);
                    else
                        setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                    wordL = wordR;
                }
                wordR++;
            }
        }

        public void remove(int offs, int len) throws BadLocationException {
            super.remove(offs, len);

            String text = getText(0, getLength());
            int before = findLastNonWordChar(text, offs);
            if (before < 0)
                before = 0;
            int after = findFirstNonWordChar(text, offs);

            if (keywordDefPtn.matcher(text.substring(before, after)).matches()) {
                setCharacterAttributes(before, after - before, attr, false);
            } else {
                setCharacterAttributes(before, after - before, attrBlack, false);
            }
        }
    };

    // --------------------------------------------------------------------------------------------------------------------------

    final static Pattern SQL_HIGH_LIGHTER_PTN = Pattern.compile("([^\\w]?)" + KEYWORD_DEF + "([^\\w])", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
    final static Pattern REMARK_1 = Pattern.compile("\\/\\*(?:[^\n]|\n)*?\\*\\/", Pattern.DOTALL | Pattern.MULTILINE);
    final static Pattern REMARK_2 = Pattern.compile("\\-{2}.*");
    final static Pattern MIDDLE_CARET_PTN = Pattern.compile("[\\[\\]]", Pattern.DOTALL | Pattern.MULTILINE);
    private JRadioButton updateBatchSqlRadio;

    private static class TextAreaHighLighterProcess {

        private enum MarkType {
            Keyword, //
            Remark,//
            ;
        }

        private List<Triple<MarkType, Integer, Integer>> markLst = new ArrayList<Triple<MarkType, Integer, Integer>>();
        DefaultHighlighter.DefaultHighlightPainter yellowPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        DefaultHighlighter.DefaultHighlightPainter orangePainter = new DefaultHighlighter.DefaultHighlightPainter(JColorUtil.rgb("#e7cdac"));
        DefaultHighlighter.DefaultHighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);

        private boolean isAllowPut(int start, int end) {
            for (Triple<MarkType, Integer, Integer> tp : markLst) {
                if (tp.getMiddle() >= start && tp.getRight() <= end) {
                    return false;
                }
            }
            return true;
        }

        private void keywordHightlight(JTextArea sqlTextArea) {
            markLst.clear();

            String sql = sqlTextArea.getText();

            Matcher mth = null;

            mth = REMARK_1.matcher(sql);
            while (mth.find()) {
                int start = mth.start();
                int end = mth.end();
                if (isAllowPut(start, end)) {
                    markLst.add(Triple.of(MarkType.Remark, start, end));
                }
            }

            mth = REMARK_2.matcher(sql);
            while (mth.find()) {
                int start = mth.start();
                int end = mth.end();
                if (isAllowPut(start, end)) {
                    markLst.add(Triple.of(MarkType.Remark, start, end));
                }
            }

            mth = SQL_HIGH_LIGHTER_PTN.matcher(sql);
            while (mth.find()) {
                String m1 = mth.group(1);
                String m3 = mth.group(3);
                if (StringUtils.length(m1) != 0 || StringUtils.length(m3) != 0) {
                    int start = mth.start(2);
                    int end = mth.end(2);
                    if (isAllowPut(start, end)) {
                        markLst.add(Triple.of(MarkType.Keyword, start, end));
                    }
                }
            }

            JTextAreaUtil.cleanHighlightPainters(sqlTextArea, yellowPainter);
            JTextAreaUtil.cleanHighlightPainters(sqlTextArea, cyanPainter);

            for (Triple<MarkType, Integer, Integer> tp : markLst) {
                int start = tp.getMiddle();
                int end = tp.getRight();
                switch (tp.getLeft()) {
                case Keyword:
                    try {
                        sqlTextArea.getHighlighter().addHighlight(start, end, cyanPainter); // DefaultHighlighter.DefaultPainter
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                    break;
                case Remark:
                    try {
                        sqlTextArea.getHighlighter().addHighlight(start, end, yellowPainter); // DefaultHighlighter.DefaultPainter
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                    break;
                }
            }
        }

        private void middleCaretHightlight(JTextArea sqlTextArea) {
            JTextAreaUtil.cleanHighlightPainters(sqlTextArea, orangePainter);
            String sql = sqlTextArea.getText();
            Matcher mth = MIDDLE_CARET_PTN.matcher(sql);
            while (mth.find()) {
                int start = mth.start();
                int end = mth.end();
                try {
                    sqlTextArea.getHighlighter().addHighlight(start, end, orangePainter); // DefaultHighlighter.DefaultPainter
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
            }
        }

        private void process(JTextArea sqlTextArea) {
            keywordHightlight(sqlTextArea);
            middleCaretHightlight(sqlTextArea);
        }
    }

    private void sqlTextAreaHighLighter() {
        try {
            if (mTextAreaHighLighterProcess == null) {
                mTextAreaHighLighterProcess = new TextAreaHighLighterProcess();
            }
            mTextAreaHighLighterProcess.process(sqlTextArea);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (mQuoteMarkController == null) {
                mQuoteMarkController = new QuoteMarkController(sqlTextArea);
            }
            mQuoteMarkController.processMain();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------

    private class QuoteMarkController {
        private JTextArea sqlTextArea;

        QuoteMarkController(JTextArea sqlTextArea) {
            this.sqlTextArea = sqlTextArea;
        }

        DefaultHighlighter.DefaultHighlightPainter grayPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.gray);
        DefaultHighlighter.DefaultHighlightPainter orangePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE);

        private void processMain() {
            process('(', ')', grayPainter);
            process('[', ']', orangePainter);
        }

        private void removeDuplicPainter(Pair<Integer, Integer> pair) {
            if (mTextAreaHighLighterProcess == null) {
                return;
            }
            Highlight[] infos = sqlTextArea.getHighlighter().getHighlights();
            for (Highlight info : infos) {
                if (info.getPainter() == mTextAreaHighLighterProcess.orangePainter) {
                    System.out.println("startOffset = " + info.getStartOffset());
                    System.out.println("endOffset = " + info.getEndOffset());
                    if (pair.getLeft() == info.getStartOffset() || pair.getRight() == info.getStartOffset()) {
                        sqlTextArea.getHighlighter().removeHighlight(info);
                    }
                }
            }
        }

        private void process(char LEFT, char RIGHT, DefaultHighlighter.DefaultHighlightPainter layerPainter) {
            JTextAreaUtil.cleanHighlightPainters(sqlTextArea, layerPainter);

            String sql = StringUtils.defaultString(sqlTextArea.getText());
            int pos = sqlTextArea.getCaretPosition();
            String tmpQuote = "";
            String quote1 = StringUtils.substring(sql, pos, pos + 1);
            String quote2 = StringUtils.substring(sql, pos - 1, pos);
            if (StringUtils.equals("" + LEFT, quote1) || //
                    StringUtils.equals("" + RIGHT, quote1)) {
                pos = sqlTextArea.getCaretPosition();
                tmpQuote = quote1;
            } else if (StringUtils.equals("" + LEFT, quote2) || //
                    StringUtils.equals("" + RIGHT, quote2)) {
                pos = sqlTextArea.getCaretPosition() - 1;
                tmpQuote = quote2;
            } else {
                return;
            }

            List<Pair<Integer, Integer>> pairLst = StringUtil_.caculateQuoteMap(sql, LEFT, RIGHT);
            for (Pair<Integer, Integer> pair : pairLst) {
                try {
                    System.out.println("left  ------" + pair.getLeft());
                    System.out.println("right ------" + pair.getRight());
                    if (StringUtils.equals("" + LEFT, tmpQuote)) {
                        if (pair.getLeft() == pos) {
                            this.removeDuplicPainter(pair);
                            sqlTextArea.getHighlighter().addHighlight(pair.getLeft(), pair.getLeft() + 1, layerPainter);
                            sqlTextArea.getHighlighter().addHighlight(pair.getRight(), pair.getRight() + 1, layerPainter);
                        }
                    } else if (StringUtils.equals("" + RIGHT, tmpQuote)) {
                        if (pair.getRight() == pos) {
                            this.removeDuplicPainter(pair);
                            sqlTextArea.getHighlighter().addHighlight(pair.getLeft(), pair.getLeft() + 1, layerPainter);
                            sqlTextArea.getHighlighter().addHighlight(pair.getRight(), pair.getRight() + 1, layerPainter);
                        }
                    }
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private void showCommonToolDlg() {
        if (mFastDBQueryUI_SimpleSqlMapperDlg != null) {
            mFastDBQueryUI_SimpleSqlMapperDlg.dispose();
        }
        mFastDBQueryUI_SimpleSqlMapperDlg = FastDBQueryUI_CommonToolsDlg.newInstance(this);
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private void checkSelectOrUpdateRadioButton() {
        System.out.println("[checkSelectOrUpdateRadioButton]  ---  start ");
        if (StringUtils.isBlank(sqlTextArea.getSelectedText())) {
            System.out.println("[checkSelectOrUpdateRadioButton]  ---  rtn ");
            return;
        }
        String sql = StringUtils.trimToEmpty(sqlTextArea.getSelectedText());
        sql = sql.toLowerCase();
        System.out.println("[checkSelectOrUpdateRadioButton] : " + sql);
        if (sql.startsWith("select")) {
            querySqlRadio.setSelected(true);
        } else {
            updateSqlRadio.setSelected(true);
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------
    private static class SapHanaDateHandler {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Pattern sapHanaPtn = Pattern.compile("\\w+\\s\\d+\\,\\s\\d{4}\\s\\d+\\:\\d+\\:\\d+?(\\.\\d{1,3})\\s(?:AM|PM)");
        Pattern sapHana_hms_ptn = Pattern.compile("(\\d+)\\:(\\d+)\\:(\\d+)\\.(\\d+)");

        private String sapHanaDateFormat(String value) {
            if (sapHanaPtn.matcher(value).find()) {
                // Dec 9, 2021 5:18:33.341 PM
                String[] vals = value.split(" ", -1);
                String month = vals[0];
                int month1 = 0;
                int day = Integer.parseInt(vals[1].substring(0, vals[1].length() - 1));
                int year = Integer.parseInt(vals[2]);
                if ("Jan".equals(month)) {
                    month1 = 1;
                } else if ("Feb".equals(month)) {
                    month1 = 2;
                } else if ("Mar".equals(month)) {
                    month1 = 3;
                } else if ("Api".equals(month)) {
                    month1 = 4;
                } else if ("May".equals(month)) {
                    month1 = 5;
                } else if ("Jul".equals(month)) {
                    month1 = 6;
                } else if ("Jun".equals(month)) {
                    month1 = 7;
                } else if ("Aug".equals(month)) {
                    month1 = 8;
                } else if ("Sep".equals(month)) {
                    month1 = 9;
                } else if ("Oct".equals(month)) {
                    month1 = 10;
                } else if ("Nov".equals(month)) {
                    month1 = 11;
                } else if ("Dec".equals(month)) {
                    month1 = 12;
                }
                Matcher mth = sapHana_hms_ptn.matcher(vals[3]);
                int hour = 0;
                int minute = 0;
                int second = 0;
                int SSS = 0;
                if (mth.find()) {
                    hour = Integer.parseInt(mth.group(1));
                    minute = Integer.parseInt(mth.group(2));
                    second = Integer.parseInt(mth.group(3));
                    SSS = Integer.parseInt(mth.group(4));
                    if (vals[4].equals("PM")) {
                        hour += 12;
                    }
                }
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month1 - 1);
                cal.set(Calendar.DATE, day);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, second);
                cal.set(Calendar.MILLISECOND, SSS);
                return format1.format(cal.getTime());
            }
            return value;
        }

        private String sapHanaDateFormat2(String value) {
            value = StringUtils.defaultString(value);
            if (value.contains("上午") || value.contains("下午")) {
                // 1980/6/6 上午 12:00:00.0
                String[] arry = value.split("\\s", -1);
                String[] ds = arry[0].split("\\/", -1);

                int year = Integer.parseInt(ds[0]);
                int month1 = Integer.parseInt(ds[1]);
                int day = Integer.parseInt(ds[2]);
                Matcher mth = sapHana_hms_ptn.matcher(arry[2]);
                int hour = 0;
                int minute = 0;
                int second = 0;
                int SSS = 0;
                if (mth.find()) {
                    hour = Integer.parseInt(mth.group(1));
                    minute = Integer.parseInt(mth.group(2));
                    second = Integer.parseInt(mth.group(3));
                    SSS = Integer.parseInt(mth.group(4));
                    if (arry[1].equals("下午")) {
                        hour += 12;
                    }
                }

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month1 - 1);
                cal.set(Calendar.DATE, day);
                cal.set(Calendar.HOUR_OF_DAY, hour);
                cal.set(Calendar.MINUTE, minute);
                cal.set(Calendar.SECOND, second);
                cal.set(Calendar.MILLISECOND, SSS);
                return format1.format(cal.getTime());
            }
            return value;
        }
    }
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------------
}