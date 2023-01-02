/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package gtu.swing.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.JViewport;
import javax.swing.RowFilter;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import gtu.clipboard.ClipboardUtil;
import gtu.string.StringUtil_;
import sun.swing.DefaultLookup;

public class JTableUtil {

	JTable table;

	public JTableUtil(JTable table) {
		this.table = table;
	}

	public void debugShowData() {
		List<Integer> xlist = getTableColumnModelIndex();
		List<Integer> ylist = getTableColumnModelIndex_realRowIndex();
		System.out.println("X=>" + xlist);
		System.out.println("Y=>" + ylist);
		for (int yy = 0; yy < ylist.size(); yy++) {
			int y = ylist.get(yy);
			System.out.format("y[%d]", y);
			for (int xx = 0; xx < xlist.size(); xx++) {
				int x = xlist.get(xx);
				System.out.format("\tx%d[%s]", x, table.getValueAt(y, x));
			}
			System.out.println();
		}
	}

	/**
	 * @param table
	 */
	public static void defaultSetting(JTable table) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoscrolls(true);
		table.setAutoCreateRowSorter(true);
		table.setUpdateSelectionOnSort(true);
		table.setAutoCreateColumnsFromModel(true);
		table.setColumnSelectionAllowed(true);
		table.getTableHeader().setAutoscrolls(true);
	}

	/**
	 * //設定為展開的JTeatArea
	 */
	public void columnIsJTextArea(final String columnTitle, final Integer fontSize) {
		class CellArea1 extends DefaultTableCellRenderer {
			private String text;
			protected int rowIndex;
			protected int columnIndex;
			protected JTable table;
			protected Font font;
			protected boolean isSelected;
			protected boolean hasFocus;

			private int paragraphStart, paragraphEnd;
			private LineBreakMeasurer lineMeasurer;

			public void paintComponent(Graphics gr) {
				// super.paintComponent(gr);

				if (text != null && !text.isEmpty()) {
					Graphics2D g = (Graphics2D) gr;
					if (lineMeasurer == null) {
						AttributedString mAttributedString = new AttributedString(text);
						if (fontSize != null) {
							mAttributedString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.PLAIN, fontSize));
						}
						if (isSelected) {
							mAttributedString.addAttribute(TextAttribute.BACKGROUND,
									DefaultLookup.getColor(this, ui, "Table.dropCellBackground"));
						} else {
							mAttributedString.addAttribute(TextAttribute.BACKGROUND, null);
						}

						AttributedCharacterIterator paragraph = mAttributedString.getIterator();
						paragraphStart = paragraph.getBeginIndex();
						paragraphEnd = paragraph.getEndIndex();
						FontRenderContext frc = g.getFontRenderContext();
						lineMeasurer = new LineBreakMeasurer(paragraph, BreakIterator.getWordInstance(), frc);
					}
					float breakWidth = (float) table.getColumnModel().getColumn(columnIndex).getWidth();
					float drawPosY = 0;
					// Set position to the index of the first character in
					// the paragraph.
					lineMeasurer.setPosition(paragraphStart);
					// Get lines until the entire paragraph has been
					// displayed.
					while (lineMeasurer.getPosition() < paragraphEnd) {
						// Retrieve next layout. A cleverer program would
						// also cache
						// these layouts until the component is re-sized.
						TextLayout layout = lineMeasurer.nextLayout(breakWidth);
						// Compute pen x position. If the paragraph is
						// right-to-left we
						// will align the TextLayouts to the right edge of
						// the panel.
						// Note: this won't occur for the English text in
						// this sample.
						// Note: drawPosX is always where the LEFT of the
						// text is placed.
						float drawPosX = layout.isLeftToRight() ? 0 : breakWidth - layout.getAdvance();
						// Move y-coordinate by the ascent of the layout.
						drawPosY += layout.getAscent();
						// Draw the TextLayout at (drawPosX, drawPosY).
						layout.draw(g, drawPosX, drawPosY);
						// Move y-coordinate in preparation for next layout.
						drawPosY += layout.getDescent() + layout.getLeading();
					}
					table.setRowHeight(rowIndex, (int) drawPosY);
				}

				getTableCellRendererComponent(table, text, isSelected, hasFocus, rowIndex, columnIndex);
			}
		}
		table.getColumn(columnTitle).setCellRenderer(new DefaultTableCellRenderer() {

			private List<CellArea1> lst = new ArrayList<CellArea1>();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				CellArea1 tmp = null;
				for (CellArea1 c : lst) {
					if (c.columnIndex == column && c.rowIndex == row) {
						tmp = c;
						break;
					}
				}
				if (tmp == null) {
					CellArea1 d = new CellArea1();
					d.columnIndex = column;
					d.rowIndex = row;
					d.table = table;
					tmp = d;
					lst.add(tmp);
				}
				tmp.text = value == null ? "" : value.toString();
				tmp.isSelected = isSelected;
				tmp.hasFocus = hasFocus;
				tmp.lineMeasurer = null;
				return tmp;
			}
		});
	}

	/**
	 * @param table
	 */
	public static void defaultSetting_AutoResize(JTable table) {
		defaultSetting(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	/**
	 * @param table
	 */
	public static void defaultSetting_Indicate(JTable table, int autoResizeMode) {
		defaultSetting(table);
		table.setAutoResizeMode(autoResizeMode);
	}

	/**
	 * 滑鼠點標題的事件
	 */
	public static void tableHeaderClickEvent(final JTable table, final ActionListener listener) {
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);
				System.out.println("Column index selected " + col + " " + name);

				Object source = e;
				int id = col;
				String command = name;
				int modifiers = e.getModifiers();
				ActionEvent event = new ActionEvent(source, id, command, modifiers);

				listener.actionPerformed(event);
			}
		});
	}

	/**
	 * 若第一列為checkbox
	 */
	public void columnOneIsCheckbox() {
		// 定義column name
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Vector<String> vec = new Vector<String>();
		for (int ii = 0; ii < model.getColumnCount(); ii++) {
			if (ii == 0) {
				vec.add("select");
			} else {
				vec.add("column" + ii);
			}
		}
		model.setColumnIdentifiers(vec);

		// 定義checkbox
		class MyCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
			private static final long serialVersionUID = 1L;

			public MyCheckBoxRenderer() {
				this.setBorderPainted(true);
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				return this;
			}
		}
		final MyCheckBoxRenderer check = new MyCheckBoxRenderer();
		table.getColumn("select").setHeaderRenderer(check);

		// 設定checkbox效果
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {// 如果點擊的是第0列，即checkbox這一列
					JCheckBox checkbox = (JCheckBox) check;
					boolean b = !check.isSelected();
					check.setSelected(b);
					table.getTableHeader().repaint();
					for (int i = 0; i < table.getRowCount(); i++) {
						table.getModel().setValueAt(b, i, 0);// 把這一列都設成和表頭一样
					}
				}
			}
		});
	}

	/**
	 * 設定某欄為button
	 */
	public void columnIsButton(String columnName) {
		/**
		 * 定義欄位為按鈕用
		 */
		class _ColumnButtonMouseAdapter extends MouseAdapter {
			JTable table;

			private _ColumnButtonMouseAdapter(JTable table) {
				this.table = table;
			}

			public void mouseClicked(MouseEvent e) {
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
				int row = e.getY() / table.getRowHeight();
				System.out.println(String.format("tableClick row[%d],col[%d]", row, column));
				if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
					Object value = table.getValueAt(row, column);
					if (value instanceof AbstractButton) {
						((AbstractButton) value).setSelected(!((AbstractButton) value).isSelected());
						ActionListener[] listeners = ((AbstractButton) value).getActionListeners();
						if (listeners != null && listeners.length > 0) {
							for (ActionListener listener : listeners) {
								listener.actionPerformed(
										new ActionEvent(Triple.of(value, row, column), -1, "btn_row_column"));
							}
						}
					}
				}
			}
		}

		TableColumn columConfig = table.getColumn(columnName);
		System.out.println("columnIsButton " + columConfig.getHeaderValue());
		columConfig.setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value == null || !(value instanceof AbstractButton)) {
					System.err.println("Warning : 無法被設定為Button : " + value + " , row " + row + " , column " + column
							+ " , isSelected " + isSelected);
				}
				AbstractButton button = (AbstractButton) value;
				return button;
			}
		});
		boolean findOk = false;
		for (int ii = 0; ii < table.getMouseListeners().length; ii++) {
			if (table.getMouseListeners()[ii] instanceof _ColumnButtonMouseAdapter) {
				findOk = true;
				break;
			}
		}
		if (findOk == false) {
			table.addMouseListener(new _ColumnButtonMouseAdapter(table));
		}
	}

	/**
	 * 重要 : 該 column 必須為可編輯狀態
	 */
	public void columnIsButton(final String columnName, final ActionListener clickListener) {
		class ButtonRenderer extends JButton implements TableCellRenderer {
			private static final long serialVersionUID = 1L;

			public ButtonRenderer() {
				setOpaque(true);
			}

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (isSelected) {
					setForeground(table.getSelectionForeground());
					setBackground(table.getSelectionBackground());
				} else {
					setForeground(table.getForeground());
					setBackground(UIManager.getColor("Button.background"));
				}
				setText((value == null) ? "" : value.toString());
				return this;
			}
		}

		class ButtonEditor extends DefaultCellEditor {
			private static final long serialVersionUID = 1L;
			protected JButton button;
			private String label;
			private boolean isPushed;

			Object latestValue;
			int latestRow;
			int latestCol;

			public ButtonEditor(JCheckBox checkBox) {
				super(checkBox);
				button = new JButton();
				button.setOpaque(true);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fireEditingStopped();
					}
				});
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				latestValue = value;
				latestRow = row;
				latestCol = column;

				if (isSelected) {
					button.setForeground(table.getSelectionForeground());
					button.setBackground(table.getSelectionBackground());
				} else {
					button.setForeground(table.getForeground());
					button.setBackground(table.getBackground());
				}
				label = (value == null) ? "" : value.toString();
				button.setText(label);
				isPushed = true;
				return button;
			}

			public Object getCellEditorValue() {
				if (isPushed) {
					if (clickListener != null) {
						clickListener.actionPerformed(
								new ActionEvent(Triple.of(latestValue, latestRow, latestCol), -1, "click btn"));
					}
				}
				isPushed = false;
				return new String(label);
			}

			public boolean stopCellEditing() {
				isPushed = false;
				return super.stopCellEditing();
			}

			protected void fireEditingStopped() {
				super.fireEditingStopped();
			}
		}

		table.getColumn(columnName).setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn(columnName).setCellRenderer(new ButtonRenderer());
	}

	public void setRowHeight(int rowPos, int height) {
		table.setRowHeight(rowPos, height);
	}

	/**
	 * 設定欄位高度by Font
	 */
	public void setRowHeightByFontSize() {
		Dimension dim = JTextFieldUtil.getTextBoundary("測", table.getFont());
		table.setRowHeight((int) dim.getHeight());
	}

	/**
	 * 取得某筆資料
	 */
	public static List<Object> getRowData(int realRowIdx, int[] ignoreColIdxs, JTable jtable) {
		Vector vector = (Vector) (((DefaultTableModel) jtable.getModel()).getDataVector().get(realRowIdx));
		List<Object> lst = new ArrayList<Object>();
		for (int ii = 0; ii < vector.size(); ii++) {
			if (ignoreColIdxs != null && ArrayUtils.contains(ignoreColIdxs, ii)) {
				continue;
			}
			lst.add(vector.get(ii));
		}
		return lst;
	}

	public void columnIsComponent(int index, JComponent component) {
		TableColumn comboCol1 = table.getColumnModel().getColumn(index);
		if (component instanceof JComboBox) {
			comboCol1.setCellEditor(new DefaultCellEditor((JComboBox) component));
		} else if (component instanceof JCheckBox) {
			comboCol1.setCellEditor(new DefaultCellEditor((JCheckBox) component));
		} else if (component instanceof JTextField) {
			comboCol1.setCellEditor(new DefaultCellEditor((JTextField) component));
		} else {
			throw new RuntimeException("必須是JComboBox, JCheckBox, JTextField");
		}
	}

	public void columnUseCommonFormatter(Integer index, final boolean debug) {
		class DataFormatteProcessRenderer extends DefaultTableCellRenderer {
			DateFormat formatterD = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat formatterT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			DecimalFormat formatterN = new DecimalFormat("#.#############");

			public DataFormatteProcessRenderer() {
				super();
			}

			public void setValue(Object value) {
				if (debug) {
					System.out.println("formatter-setValue : " + value + " - "
							+ (value != null ? value.getClass().getSimpleName() : ""));
				}
				if (value == null) {
					setText("");
				} else if (value.getClass() == java.sql.Date.class) {
					setText(formatterD.format(value));
				} else if (value.getClass() == java.sql.Timestamp.class || value.getClass() == java.util.Date.class) {
					setText(formatterT.format(value));
				} else if (value.getClass() == BigDecimal.class) {
					setText(formatterN.format(value));
				} else {
					setText(String.valueOf(value));
				}
			}
		}
		DataFormatteProcessRenderer renderer = new DataFormatteProcessRenderer();
		if (index != null) {
			TableColumn comboCol1 = table.getColumnModel().getColumn(index);
			comboCol1.setCellRenderer(renderer);
		} else {
			for (int ii = 0; ii < table.getColumnCount(); ii++) {
				int realCol = JTableUtil.getRealColumnPos(ii, table);
				TableColumn comboCol1 = table.getColumnModel().getColumn(realCol);
				comboCol1.setCellRenderer(renderer);
			}
		}
	}

	public void defaultToolTipText(MouseEvent event) {
		ToolTipManager toolTipManager = ToolTipManager.sharedInstance();
		toolTipManager.setInitialDelay(0);
		String content = this.getSelectedValue() == null ? "empty" : this.getSelectedValue().toString();
		if (event.getButton() == 1) {
			table.setToolTipText(content);
		}
	}

	public static DefaultTableModel createModelIndicateType(final List<Integer> notReadOnlyColumnLst, List<?> header,
			final List<Class<?>> typeLst) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, header.toArray()) {
			private static final long serialVersionUID = 1L;

			// 設定column class
			@Override
			public Class<?> getColumnClass(int c) {
				return typeLst.get(c);
			}

			// 設定可否編輯
			public boolean isCellEditable(int row, int column) {
				if (!notReadOnlyColumnLst.contains(column)) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		return model;
	}

	public static DefaultTableModel createModelIndicateType(final boolean readonly, List<?> header,
			final List<Class<?>> typeLst) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, header.toArray()) {
			private static final long serialVersionUID = 1L;

			// 設定column class
			@Override
			public Class<?> getColumnClass(int c) {
				return typeLst.get(c);
			}

			// 設定可否編輯
			public boolean isCellEditable(int row, int column) {
				if (readonly) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		return model;
	}

	public void setColumnSortComparator(int columnIndex, Comparator<?> mComparator) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		if (table.getRowSorter() == null || table.getRowSorter().getClass() != TableRowSorter.class) {
			table.setRowSorter(sorter);
		} else {
			sorter = (TableRowSorter<TableModel>) table.getRowSorter();
		}
		table.setRowSorter(sorter);
		sorter.setSortable(columnIndex, true);

		System.out
				.println("setColumnSortComparator Count = " + table.getModel().getColumnCount() + " , " + columnIndex);
		sorter.setComparator(columnIndex, mComparator);

		sorter.addRowSorterListener(new RowSorterListener() {
			@Override
			public void sorterChanged(RowSorterEvent evt) {
				int indexOfNoColumn = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					// System.out.println("sorterChanged - " + i + " -> " +
					// table.getValueAt(i, columnIndex));
				}
			}
		});
	}

	public static DefaultTableModel createModel(final boolean readonly, Object... header) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, header) {
			private static final long serialVersionUID = 1L;

			// 設定column class
			@Override
			public Class<?> getColumnClass(int c) {
				try {

					List<Class<?>> lst = new ArrayList<Class<?>>();
					for (int ii = 0; ii < this.getRowCount(); ii++) {
						Object value = getValueAt(ii, c);
						if (value != null) {
							if (!lst.contains(value.getClass())) {
								lst.add(value.getClass());
							}
						}
					}
					if (lst.isEmpty() || lst.size() > 1) {
						return Object.class;
					} else {
						return lst.get(0);
					}
				} catch (Exception ex) {
					return Object.class;
				}
			}

			// 設定可否編輯
			public boolean isCellEditable(int row, int column) {
				if (readonly) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		return model;
	}

	public static DefaultTableModel createModel(final int[] editableColumns, Object... header) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, header) {
			private static final long serialVersionUID = 1L;

			// 設定column class
			@Override
			public Class<?> getColumnClass(int c) {
				try {
					List<Class<?>> lst = new ArrayList<Class<?>>();
					for (int ii = 0; ii < this.getRowCount(); ii++) {
						Object value = getValueAt(ii, c);
						if (value != null) {
							if (!lst.contains(value.getClass())) {
								lst.add(value.getClass());
							}
						}
					}
					if (lst.isEmpty() || lst.size() > 1) {
						return Object.class;
					} else {
						return lst.get(0);
					}
				} catch (Exception ex) {
					return Object.class;
				}
			}

			// 設定可否編輯
			public boolean isCellEditable(int row, int column) {
				if (!ArrayUtils.contains(editableColumns, column)) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		return model;
	}

	public static DefaultTableModel createModel(final int[] editableColumns, final Object[] header,
			final Class<?>[] typeLst) {
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, header) {
			private static final long serialVersionUID = 1L;

			// 設定column class
			@Override
			public Class<?> getColumnClass(int c) {
				return typeLst[c];
			}

			// 設定可否編輯
			public boolean isCellEditable(int row, int column) {
				if (!ArrayUtils.contains(editableColumns, column)) {
					return false;
				} else {
					return super.isCellEditable(row, column);
				}
			}
		};
		return model;
	}

	/**
	 * 設定最大寬度 , 若設-1則無限制
	 * 
	 * @param maxWidth
	 * @return
	 */
	public JTableUtil setMaxWidth(int... maxWidth) {
		DefaultTableColumnModel model1 = new DefaultTableColumnModel();
		TableColumn tableColumn = null;
		for (int ii = 0; ii < maxWidth.length; ii++) {
			tableColumn = new TableColumn();
			tableColumn.setHeaderValue(table.getModel().getColumnName(ii));
			if (maxWidth[ii] != -1) {
				tableColumn.setMaxWidth(maxWidth[ii]);
				tableColumn.setPreferredWidth(maxWidth[ii]);
			}
			tableColumn.setModelIndex(ii);
			tableColumn.setResizable(true);
			model1.addColumn(tableColumn);
		}
		table.setColumnModel(model1);
		return this;
	}

	/**
	 * 可垂直與橫向卷軸的Scroll
	 */
	public static JScrollPane getScrollPane(JTable myTable) {
		JScrollPane scroll = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return scroll;
	}

	public static JTableUtil newInstance(JTable table) {
		return new JTableUtil(table);
	}

	/**
	 * if all column's cell is empty , hidden this column
	 */
	public void hiddenAllEmptyColumn() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Set<String> colName = new HashSet<String>();
		for (int col = 0; col < model.getColumnCount(); col++) {
			for (int row = 0; row < model.getRowCount(); row++) {
				if (StringUtils.isNotBlank((String) model.getValueAt(row, col))) {
					colName.add(model.getColumnName(col));
					break;
				}
			}
		}
		for (Enumeration<TableColumn> enu = table.getColumnModel().getColumns(); enu.hasMoreElements();) {
			TableColumn column = enu.nextElement();
			if (!colName.contains(column.getHeaderValue())) {
				table.removeColumn(column);
			}
		}
	}

	public void hiddenColumn(String columnTitle) {
		TableColumnManager tcm = new TableColumnManager(table);
		tcm.hideColumn(columnTitle);
	}

	public void showColumnByHeaderValue(Object... title) {
		List<TableColumn> list = new ArrayList<TableColumn>();
		TableColumnModel columnModel = table.getColumnModel();
		for (int ii = 0; ii < columnModel.getColumnCount(); ii++) {
			if (!ArrayUtils.contains(title, columnModel.getColumn(ii).getHeaderValue())) {
				list.add(columnModel.getColumn(ii));
			}
		}
		for (TableColumn col : list) {
			table.removeColumn(col);
		}
	}

	public void sizeWidthToFitHeader() {
		for (Enumeration<TableColumn> enu = table.getColumnModel().getColumns(); enu.hasMoreElements();) {
			TableColumn column = enu.nextElement();
			if (column.getHeaderRenderer() == null) {
				column.setHeaderRenderer(DEFAULT_TABLE_CELL_RENDERER);
			}
			column.sizeWidthToFit();
		}
	}

	static final DefaultTableCellRenderer DEFAULT_TABLE_CELL_RENDERER = createDefaultTableCellRenderer();

	private static DefaultTableCellRenderer createDefaultTableCellRenderer() {
		DefaultTableCellRenderer label = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (table != null) {
					JTableHeader header = table.getTableHeader();
					if (header != null) {
						setForeground(header.getForeground());
						setBackground(header.getBackground());
						setFont(header.getFont());
					}
				}

				setText((value == null) ? "" : value.toString());
				setBorder(UIManager.getBorder("TableHeader.cellBorder"));
				return this;
			}
		};
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	public DefaultTableModel getModel() {
		if (table.getRowSorter() == null) {
			return (DefaultTableModel) table.getModel();
		}
		return (DefaultTableModel) table.getRowSorter().getModel();
	}

	/**
	 * 已修正 pos
	 */
	public Object getSelectedValue() {
		int row = JTableUtil.getRealRowPos(getSelectedRow(), table);
		int col = JTableUtil.getRealColumnPos(getSelectedColumn(), table);
		if (row == -1 || col == -1) {
			return null;
		}
		return getModel().getValueAt(row, col);
	}

	/**
	 * 已修正 pos
	 */
	public JTableUtil setValueAtSelectedCell(Object value) {
		int row = JTableUtil.getRealRowPos(getSelectedRow(), table);
		int col = JTableUtil.getRealColumnPos(getSelectedColumn(), table);
		System.out.format("setValueAtSelectedCell - row : %d, col : %d\n", row, col);
		if (row == -1 || col == -1) {
			return this;
		}
		getModel().setValueAt(value, row, col);
		return this;
	}

	/**
	 * 未變更原始col
	 */
	public int getSelectedColumn() {
		return table.getSelectedColumn();
	}

	/**
	 * 檢查欄位是否隱藏
	 * 
	 * @param col
	 * @param table
	 * @return
	 */
	public static boolean isColumnVisible(int col, JTable table) {
		if (table.convertColumnIndexToView(col) == -1) {
			return false;
		}
		return true;
	}

	/**
	 * 已修正col (記得傳入未修正 pos , 否則反而錯誤)
	 */
	public static int getRealColumnPos(int colPos, JTable table) {
		// System.out.println("getRealColumnPos ==> colPos == " + colPos);
		return table.convertColumnIndexToModel(colPos);
	}

	/**
	 * 已修正 row (記得傳入未修正 pos , 否則反而錯誤)
	 */
	public static int getRealRowPos(int rowPos, JTable table) {
		if (rowPos == -1) {
			// System.out.println("getRealRowPos => " + rowPos);
			return rowPos;
		}
		if (table.getRowSorter() == null) {
			// System.out.println("getRealRowPos[no sort] => " + rowPos);
			return rowPos;
		}
		int fixRowPos = table.getRowSorter().convertRowIndexToModel(rowPos);
		// System.out.println(String.format("getRealRowPos[fix] => before[%d],
		// after[%d]", rowPos, fixRowPos));
		return fixRowPos;
	}

	/**
	 * 未變更原始row
	 */
	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public int getRealSelectedRow() {
		return getRealRowPos(table.getSelectedRow(), table);
	}

	public int[] getRealSelectedRows(boolean isReal) {
		int[] rows = table.getSelectedRows();
		if (!isReal) {
			return rows;
		}
		int[] rowOk = new int[rows.length];
		for (int ii = 0; ii < rows.length; ii++) {
			rowOk[ii] = getRealRowPos(rows[ii], table);
		}
		return rowOk;
	}

	/**
	 * (記得傳入未修正 pos , 否則反而錯誤)
	 */
	public Object getRealValueAt(int rowPos, int colPos) {
		rowPos = getRealRowPos(rowPos, table);
		colPos = getRealColumnPos(colPos, table);
		return getModel().getValueAt(rowPos, colPos);
	}

	public Object getValueAt(boolean covertRealPos, int rowPos, int colPos) {
		if (covertRealPos) {
			rowPos = getRealRowPos(rowPos, table);
			colPos = getRealColumnPos(colPos, table);
			return getModel().getValueAt(rowPos, colPos);
		} else {
			return getModel().getValueAt(rowPos, colPos);
		}
	}

	public void setValueAt(boolean covertRealPos, Object value, int rowPos, int colPos) {
		if (covertRealPos) {
			table.setValueAt(value, rowPos, colPos);
		} else {
			getModel().setValueAt(value, rowPos, colPos);
		}
	}

	/**
	 * 取得已修正row arry
	 */
	public int[] getSelectedRows(boolean isReal) {
		int[] rowPos = table.getSelectedRows();
		if (!isReal) {
			return rowPos;
		}
		if (table.getRowSorter() == null) {
			return rowPos;
		}
		int[] row2 = new int[rowPos.length];
		for (int ii = 0; ii < rowPos.length; ii++) {
			row2[ii] = JTableUtil.getRealRowPos(rowPos[ii], table);
		}
		return row2;
	}

	public int[] getSelectedColumns(boolean isReal) {
		int[] colPos = table.getSelectedColumns();
		if (!isReal) {
			return colPos;
		}
		int[] rtnArry = new int[colPos.length];
		for (int ii = 0; ii < colPos.length; ii++) {
			rtnArry[ii] = JTableUtil.getRealColumnPos(colPos[ii], table);
		}
		return rtnArry;
	}

	public void addRow(Object[] data) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(data);
	}

	/**
	 * 取得真實被選擇的欄
	 */
	public List<Integer> getTableColumnModelIndex_selectedColumns() {
		int[] tabSelectColumns = table.getSelectedColumns();
		List<Integer> list = new ArrayList<Integer>();
		for (int col : tabSelectColumns) {
			list.add(col);
		}
		return list;
	}

	/**
	 * 取得Table列Row的真實index
	 */
	public List<Integer> getTableColumnModelIndex_realRowIndex() {
		List<Integer> rowList = new ArrayList<Integer>();
		for (int ii = 0; ii < getModel().getRowCount(); ii++) {
			if (table.getRowSorter() != null) {
				rowList.add(table.getRowSorter().convertRowIndexToModel(ii));
			} else {
				rowList.add(ii);
			}
		}
		return rowList;
	}

	/**
	 * 取得Table的column的index
	 */
	public List<Integer> getTableColumnModelIndex() {
		List<Integer> list = new ArrayList<Integer>();
		Map<Object, TableColumn> tableCModel = getTableColumnModel();
		for (Object key : tableCModel.keySet()) {
			list.add(tableCModel.get(key).getModelIndex());
		}
		return list;
	}

	/**
	 * 取得Table的column的 定義檔 key=欄名,value=定義檔
	 */
	public Map<Object, TableColumn> getTableColumnModel() {
		TableColumn col = null;
		TableColumn clone = null;
		Map<Object, TableColumn> tableCModel = new LinkedHashMap<Object, TableColumn>();
		for (int ii = 0; ii < table.getColumnModel().getColumnCount(); ii++) {
			col = table.getColumnModel().getColumn(ii);
			clone = new TableColumn();
			clone.setModelIndex(-1);
			clone.setHeaderValue(col.getHeaderValue());
			tableCModel.put(col.getHeaderValue(), clone);
		}
		TableColumnModel tableColumnModel = table.getTableHeader().getColumnModel();
		for (int ii = 0; ii < tableColumnModel.getColumnCount(); ii++) {
			for (Object key : tableCModel.keySet()) {
				int index = tableColumnModel.getColumnIndex(key);
				tableCModel.get(key).setModelIndex(index);
				// System.out.println(key + "..." + index);
			}
		}
		return tableCModel;
	}

	public <T> KeyAdapter defaultKeyAdapter() {
		return new KeyAdapter() {
			T temp = null;

			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
					pasteFromObject_singleValueToSelectedCell(null);
				}
				if (evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_C) {
					temp = (T) getSelectedValue();
				}
				if (evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_V) {
					pasteFromObject_singleValueToSelectedCell(temp);
				}
				if (evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_X) {
					temp = (T) getSelectedValue();
					setValueAtSelectedCell(null);
				}
			}
		};
	}

	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// paste function
	public JTableUtil pasteFromClipboard_multiRowData(boolean autoRowExtendReadch) {
		return pasteFromClipboard_multiRowData(autoRowExtendReadch, defaultTransformer);
	}

	static Transformer defaultTransformer = new Transformer() {
		public Object transform(Object paramObject) {
			return paramObject;
		}
	};

	@SuppressWarnings("unchecked")
	public JTableUtil pasteFromClipboard_multiRowData(boolean autoRowExtendReadch, Transformer transformer) {
		List<Integer> colRealArray = getTableColumnModelIndex();
		List<Integer> rowRealArray = getTableColumnModelIndex_realRowIndex();
		int startColPos = table.getSelectedColumn();
		int realColumnCount = -1;
		try {
			realColumnCount = ((Vector<?>) (getModel().getDataVector().get(0))).size();
		} catch (Exception ex) {
		}

		try {
			BufferedReader reader = new BufferedReader(new StringReader(ClipboardUtil.getInstance().getContents()));
			StringTokenizer tok = null;
			List<Object> list = new ArrayList<Object>();
			Vector<Object> rowVector = null;

			int rowPos = table.getSelectedRow();
			for (String line = null; (line = reader.readLine()) != null;) {
				list.clear();
				tok = new StringTokenizer(line);
				for (; tok.hasMoreElements();) {
					list.add(transformer.transform(tok.nextElement()));
				}

				if (rowPos >= rowRealArray.size()) {
					if (!autoRowExtendReadch) {
						break;
					} else {
						if (realColumnCount != -1) {
							getModel().addRow(new Object[realColumnCount]);
							rowRealArray = getTableColumnModelIndex_realRowIndex();
						}
					}
				}

				rowVector = (Vector<Object>) getModel().getDataVector().get(rowRealArray.get(rowPos));
				for (int ii = startColPos, index = 0; ii < colRealArray.size(); ii++, index++) {
					if (index == list.size()) {
						break;
					}
					// System.out.format("set %d = %s\n", ii, list.get(index));
					rowVector.set(colRealArray.get(ii), list.get(index));
				}
				rowPos++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public JTableUtil pasteFromClipboard_singleValueToSelectedCell() {
		int rowPos = -1;
		int colPos = -1;
		String value = ClipboardUtil.getInstance().getContents();
		DefaultTableModel model = (DefaultTableModel) getModel();
		for (int row : table.getSelectedRows()) {
			if (table.getRowSorter() != null) {
				rowPos = table.getRowSorter().convertRowIndexToModel(row);
			} else {
				rowPos = row;
			}
			for (int col : table.getSelectedColumns()) {
				colPos = table.convertColumnIndexToModel(col);
				model.setValueAt(value, rowPos, colPos);
			}
		}
		return this;
	}

	public JTableUtil pasteFromObject_singleValueToSelectedCell(Object value) {
		int rowPos = -1;
		int colPos = -1;
		DefaultTableModel model = (DefaultTableModel) getModel();
		for (int row : table.getSelectedRows()) {
			if (table.getRowSorter() != null) {
				rowPos = table.getRowSorter().convertRowIndexToModel(row);
			} else {
				rowPos = row;
			}
			for (int col : table.getSelectedColumns()) {
				colPos = table.convertColumnIndexToModel(col);
				model.setValueAt(value, rowPos, colPos);
			}
		}
		return this;
	}

	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	// jmenu_item

	public List<JMenuItem> getDefaultJMenuItems() {
		return getDefaultJMenuItems_Mask(//
				JTableUtil_DefaultJMenuItems_Mask._加欄 | //
						JTableUtil_DefaultJMenuItems_Mask._移除欄 | //
						JTableUtil_DefaultJMenuItems_Mask._加列 | //
						JTableUtil_DefaultJMenuItems_Mask._加多筆列 | //
						JTableUtil_DefaultJMenuItems_Mask._移除列 | //
						JTableUtil_DefaultJMenuItems_Mask._移除所有列 | //
						JTableUtil_DefaultJMenuItems_Mask._清除已選儲存格 | //
						JTableUtil_DefaultJMenuItems_Mask._貼上多行記事本 | //
						JTableUtil_DefaultJMenuItems_Mask._貼上單格記事本 //
		);
	}

	public interface JTableUtil_DefaultJMenuItems_Mask {
		int _加欄 = 1 << 0;
		int _移除欄 = 1 << 1;
		int _加列 = 1 << 2;
		int _加多筆列 = 1 << 3;
		int _移除列 = 1 << 4;
		int _移除所有列 = 1 << 5;
		int _清除已選儲存格 = 1 << 6;
		int _貼上多行記事本 = 1 << 7;
		int _貼上單格記事本 = 1 << 8;
	}

	public List<JMenuItem> getDefaultJMenuItems_Mask(int flag) {
		List<JMenuItem> lst = new ArrayList<JMenuItem>();
		if ((JTableUtil_DefaultJMenuItems_Mask._加欄 & flag) != 0) {
			lst.add(jMenuItem_addColumn(""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._移除欄 & flag) != 0) {
			lst.add(jMenuItem_removeColumn(""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._加列 & flag) != 0) {
			lst.add(jMenuItem_addRow(true, ""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._加多筆列 & flag) != 0) {
			JMenuItem a3_1 = jMenuItem_addRow(true, "input row count!");
			a3_1.setText("add multi row");
			lst.add(a3_1);
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._移除列 & flag) != 0) {
			lst.add(jMenuItem_removeRow(""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._移除所有列 & flag) != 0) {
			lst.add(jMenuItem_removeAllRow(""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._清除已選儲存格 & flag) != 0) {
			lst.add(jMenuItem_clearSelectedCell(""));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._貼上多行記事本 & flag) != 0) {
			lst.add(jMenuItem_pasteFromClipboard_multiRowData(true));
		}
		if ((JTableUtil_DefaultJMenuItems_Mask._貼上單格記事本 & flag) != 0) {
			lst.add(jMenuItem_pasteFromClipboard_singleValueToSelectedCell());
		}
		return lst;
	}

	public JMenuItem jMenuItem_pasteFromClipboard_multiRowData(final boolean autoRowExtendReadch) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasteFromClipboard_multiRowData(autoRowExtendReadch);
			}
		});
		menuItem.setText("paste from clipboard (multi row data)");
		return menuItem;
	}

	public JMenuItem jMenuItem_pasteFromClipboard_singleValueToSelectedCell() {
		JMenuItem menuItem = new JMenuItem();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasteFromClipboard_singleValueToSelectedCell();
			}
		});
		menuItem.setText("paste from clipboard (single value to selected cell)");
		return menuItem;
	}

	public JMenuItem jMenuItem_addColumn(final String confirmMessage) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String columnName = UUID.randomUUID().toString();
				if (StringUtils.isNotEmpty(confirmMessage)) {
					Object tmpColumnName = JOptionPaneUtil.newInstance().iconPlainMessage()
							.showInputDialog(confirmMessage, "ADD COLUMN", columnName);
					columnName = tmpColumnName != null ? String.valueOf(tmpColumnName) : null;
					if (StringUtils.isEmpty(columnName)) {
						JOptionPaneUtil.newInstance().iconErrorMessage()
								.showMessageDialog("column title can't be empty!", "ERROR");
						return;
					}
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addColumn(columnName);
			}
		});
		menuItem.setText("add column");
		return menuItem;
	}

	public JMenuItem jMenuItem_removeColumn(final String confirmMessage) {
		JMenuItem menuItem = new JMenuItem();
		final int colPos = table.getSelectedColumn();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isNotBlank(confirmMessage) && //
				JOptionPaneUtil.ComfirmDialogResult.YES_OK_OPTION != //
				JOptionPaneUtil.newInstance().iconWaringMessage().confirmButtonYesNo().showConfirmDialog(confirmMessage,
						"REMOVE ROW")) {
					return;
				}
				TableColumn tableColumn = null;
				StringBuilder sb = new StringBuilder();
				for (int colPos : table.getSelectedColumns()) {
					tableColumn = table.getColumnModel().getColumn(colPos);
					sb.append(String.format("%d[%s],", tableColumn.getModelIndex(), tableColumn.getHeaderValue()));
					table.removeColumn(tableColumn);
				}
				if (sb.length() > 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
				System.out.println(sb);
			}
		});
		if (colPos == -1) {
			menuItem.setEnabled(false);
		}
		menuItem.setText("remove column");
		return menuItem;
	}

	public JMenuItem jMenuItem_addRow(final boolean insertBeforeCurrentRow, final String confirmMessage) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowPos = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (!insertBeforeCurrentRow) {
					rowPos++;
				}
				int rowCount = 1;
				if (StringUtils.isNotBlank(confirmMessage)) {
					try {
						rowCount = Integer.parseInt(JOptionPaneUtil.newInstance().iconInformationMessage()
								.showInputDialog(confirmMessage, "ADD ROWS"));
					} catch (Exception ex) {
						JCommonUtil.handleException(ex);
						return;
					}
				}
				for (int ii = 0; ii < rowCount; ii++) {
					Object[] emptyArry = new Object[model.getRowCount()];
					if (rowPos == -1) {
						model.addRow(emptyArry);
						continue;
					}
					model.insertRow(rowPos, emptyArry);
				}
			}
		});
		menuItem.setText("add row");
		return menuItem;
	}

	public JMenuItem jMenuItem_removeRow(final String confirmMessage) {
		JMenuItem delRowItem = new JMenuItem();
		final int rowPos = table.getSelectedRow();
		delRowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isNotBlank(confirmMessage) && //
				JOptionPaneUtil.ComfirmDialogResult.YES_OK_OPTION != //
				JOptionPaneUtil.newInstance().iconWaringMessage().confirmButtonYesNo().showConfirmDialog(confirmMessage,
						"REMOVE ROW")) {
					return;
				}
				DefaultTableModel model = getModel();
				for (int rowPos : table.getSelectedRows()) {
					int realPos = rowPos;
					if (table.getRowSorter() != null) {
						realPos = table.getRowSorter().convertRowIndexToModel(rowPos);
					}
					model.removeRow(realPos);
				}
			}
		});
		if (rowPos == -1) {
			delRowItem.setEnabled(false);
		}
		delRowItem.setText("remove row");
		return delRowItem;
	}

	public JMenuItem jMenuItem_removeAllRow(final String confirmMessage) {
		JMenuItem delRowItem = new JMenuItem();
		final int rowPos = table.getSelectedRow();
		delRowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isNotBlank(confirmMessage) && //
				JOptionPaneUtil.ComfirmDialogResult.YES_OK_OPTION != //
				JOptionPaneUtil.newInstance().iconWaringMessage().confirmButtonYesNo().showConfirmDialog(confirmMessage,
						"REMOVE ROW")) {
					return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (; model.getRowCount() > 0;) {
					model.removeRow(0);
				}
			}
		});
		if (rowPos == -1) {
			delRowItem.setEnabled(false);
		}
		delRowItem.setText("remove all row");
		return delRowItem;
	}

	public JMenuItem jMenuItem_clearSelectedCell(final String confirmMessage) {
		JMenuItem delRowItem = new JMenuItem();
		final int rowPos = table.getSelectedRow();
		delRowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isNotBlank(confirmMessage) && //
				JOptionPaneUtil.ComfirmDialogResult.YES_OK_OPTION != //
				JOptionPaneUtil.newInstance().iconWaringMessage().confirmButtonYesNo().showConfirmDialog(confirmMessage,
						"REMOVE ROW")) {
					return;
				}
				int rowPos = -1;
				int colPos = -1;
				DefaultTableModel model = (DefaultTableModel) table.getRowSorter().getModel();
				for (int row : table.getSelectedRows()) {
					rowPos = table.getRowSorter().convertRowIndexToModel(row);
					for (int col : table.getSelectedColumns()) {
						colPos = table.convertColumnIndexToModel(col);
						// System.out.format("%d %d = %s\n", rowPos, colPos,
						// table.getRowSorter().getModel().getValueAt(rowPos,
						// colPos));
						model.setValueAt("", rowPos, colPos);
					}
				}

			}
		});
		if (rowPos == -1) {
			delRowItem.setEnabled(false);
		}
		delRowItem.setText("clear selected cell");
		return delRowItem;
	}

	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

	/**
	 * 全部row的cell寬度來計算寬度
	 */
	public static int calculateColumnWidth(JTable table, int columnIndex) {
		int width = 0; // The return value
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			TableCellRenderer renderer = table.getCellRenderer(i, columnIndex);
			Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(i, columnIndex), false,
					false, i, columnIndex);
			int thisWidth = comp.getPreferredSize().width;
			if (thisWidth > width) {
				width = thisWidth;
			}
		}
		return width;
	}

	/**
	 * 用column title計算寬度
	 */
	public static int calculateColumnWidth_1(JTable table, int pos) {
		for (int ii = 0; ii < table.getColumnModel().getColumnCount(); ii++) {
			TableColumn col = table.getColumnModel().getColumn(ii);
			if (ii == pos) {
				return col.getHeaderValue().toString().getBytes().length * 10;
			}
		}
		throw new RuntimeException("error!!");
	}

	public static void setColumnWidths_Percent(JTable table, float[] widthPercentArry) {
		setColumnWidths_Percent(table, null, widthPercentArry);
	}

	/**
	 */
	public static Double getTableWidth(JTable table) {
		Double wholeSize = table.getSize().getWidth();
		if (table.getParent() instanceof JViewport) {
			JViewport view = ((JViewport) table.getParent());
			wholeSize = view.getSize().getWidth();

			if (wholeSize == null || wholeSize == 0) {
				wholeSize = view.getBounds().getWidth();
			}
			wholeSize -= 5;
		}
		if (wholeSize == null || wholeSize == 0) {
			wholeSize = table.getBounds().getWidth();
		}
		return wholeSize;
	}

	public static void setColumnWidths_Percent(JTable table, Double tableFullWidth, float[] widthPercentArry) {
		int columnCount = table.getColumnCount();
		if (columnCount != widthPercentArry.length) {
			throw new RuntimeException("陣列長度必須為 : " + columnCount);
		}

		Double wholeSize = tableFullWidth;
		if (wholeSize == null) {
			wholeSize = getTableWidth(table);
		}

		TableColumnModel tcm = table.getColumnModel();
		System.out.println("table wholeSize : " + wholeSize);
		for (int i = 0; i < columnCount; i++) {
			int width = (int) (wholeSize * (widthPercentArry[i] / 100));
			TableColumn column = tcm.getColumn(i);
			column.setPreferredWidth(width);

			boolean setMinimum = true;// 設最小
			boolean setMaximum = false;// 設最大

			if (setMinimum == true) {
				column.setMinWidth(width);
			}
			if (setMaximum == true) {
				column.setMaxWidth(width);
			}
		}
	}

	/**
	 * 設定蘭寬
	 * 
	 * @param table
	 * @param insets
	 * @param setMinimum
	 * @param setMaximum
	 */
	public static void setColumnWidths(JTable table, Insets insets) {
		int columnCount = table.getColumnCount();
		TableColumnModel tcm = table.getColumnModel();
		int spare = (insets == null ? 0 : insets.left + insets.right);
		for (int i = 0; i < columnCount; i++) {
			int width = calculateColumnWidth_1(table, i);
			width += spare;
			TableColumn column = tcm.getColumn(i);
			column.setPreferredWidth(width + 1);

			boolean setMinimum = true;// 設最小
			boolean setMaximum = false;// 設最大

			if (setMinimum == true) {
				column.setMinWidth(width);
			}
			if (setMaximum == true) {
				column.setMaxWidth(width);
			}
		}
	}

	public static void setColumnWidths_ByDataContent(JTable table, Map<String, Object> preferences, Insets insets) {
		setColumnWidths_ByDataContent(table, preferences, insets, false);
	}

	/**
	 * 設定蘭寬
	 * 
	 * @param table
	 * @param insets     使用這個即可 JFrame.getInsets()
	 * @param setMinimum
	 * @param setMaximum
	 */
	public static void setColumnWidths_ByDataContent(JTable table, Map<String, Object> preferences, Insets insets,
			boolean isFullByPercent) {
		preferences = preferences == null ? Collections.EMPTY_MAP : preferences;
		float offset = (Float) (preferences.containsKey("offset") ? preferences.get("offset") : 1f);
		boolean isCaculateTitle = (Boolean) (preferences.containsKey("isCaculateTitle")
				? preferences.get("isCaculateTitle")
				: true);
		Integer maxWidth = (Integer) (preferences.containsKey("maxWidth") ? preferences.get("maxWidth") : 0);
		Map<Integer, Integer> presetColumns = (Map<Integer, Integer>) (preferences.containsKey("presetColumns")
				? preferences.get("presetColumns")
				: Collections.EMPTY_MAP);
		// ----------------------------------------------------------------------------------------
		int columnCount = table.getColumnCount();
		TableColumnModel tcm = table.getColumnModel();
		int spare = (insets == null ? 0 : insets.left + insets.right);
		Map<Integer, Integer> widthPosMap = new HashMap<Integer, Integer>();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		if (isCaculateTitle) {
			List<Object> titleArry = JTableUtil.newInstance(table).getColumnTitleArray();
			for (int colPos = 0; colPos < titleArry.size(); colPos++) {
				String val = titleArry.get(colPos) != null ? String.valueOf(titleArry.get(colPos)) : "";
				int width = 0;
				if (StringUtil_.hasChineseWord2(val)) {
					width = val.toString().getBytes().length * 10;
				} else {
					width = val.length() * 10;
				}
				width = (int) (((float) width) * offset);
				if (maxWidth != 0 && width > maxWidth) {
					width = maxWidth;
				}
				widthPosMap.put(colPos, width);
			}
		}

		for (int rowPos = 0; rowPos < table.getRowCount(); rowPos++) {
			for (int colPos = 0; colPos < table.getColumnCount(); colPos++) {
				int realColPos = table.convertColumnIndexToModel(colPos);
				String val = String.valueOf(model.getValueAt(rowPos, realColPos));
				int width = 0;
				if (StringUtil_.hasChineseWord2(val)) {
					width = val.toString().getBytes().length * 10;
				} else {
					width = val.length() * 10;
				}
				width = (int) (((float) width) * offset);
				Integer currentWidth = 0;
				if (widthPosMap.containsKey(realColPos)) {
					currentWidth = widthPosMap.get(realColPos);
				}
				currentWidth = Math.max(currentWidth, width);
				if (maxWidth != 0 && currentWidth > maxWidth) {
					currentWidth = maxWidth;
				}
				widthPosMap.put(realColPos, currentWidth);
			}
		}

		System.out.println("---" + widthPosMap);
		if (widthPosMap.isEmpty()) {
			return;
		}

		for (int i = 0; i < columnCount; i++) {
			int width = widthPosMap.get(i);
			if (presetColumns.containsKey(i)) {
				width = presetColumns.get(i);
			}
			width += spare;
			TableColumn column = tcm.getColumn(i);
			column.setPreferredWidth(width + 1);

			System.out.println("set preferedWidth - " + width);
		}

		if (isFullByPercent && table.getParent() instanceof JViewport) {
			JViewport view = ((JViewport) table.getParent());
			Double wholeSize = view.getSize().getWidth();
			int divideMother = table.getWidth();
			if (wholeSize != 0 && divideMother != 0) {
				for (int i = 0; i < columnCount; i++) {
					if (presetColumns.containsKey(i)) {
						divideMother -= presetColumns.get(i);
					}
				}
				for (int i = 0; i < columnCount; i++) {
					TableColumn column = tcm.getColumn(i);
					int width = column.getPreferredWidth();
					if (!presetColumns.containsKey(i)) {
						width = (int) (((double) width / (double) divideMother) * wholeSize * 0.95);
						column.setPreferredWidth(width);
					}
				}
			}
		}
	}

	/**
	 * 設定欄被景色
	 */
	public void setColumnColor(Color color, int colPos) {
		TableColumnModel columnmodel = table.getColumnModel();
		DefaultTableCellRenderer cr00 = new DefaultTableCellRenderer();
		cr00.setBackground(color);
		columnmodel.getColumn(colPos).setCellRenderer(cr00);
	}

	/**
	 * 設定欄被景色
	 */
	public void setColumnColor_byCondition(int colPos, TableColorDef tableColorDef) {
		TableColumn column = table.getColumnModel().getColumn(colPos);
		column.setCellRenderer(new MyCellRenderer(table, colPos, tableColorDef));
	}

	private class MyCellRenderer implements TableCellRenderer {
		protected TableCellRenderer wrappedRenderer;
		protected TableColorDef tableColorDef;

		public MyCellRenderer(JTable table, int columPos, TableColorDef tableColorDef) {
			TableColumn column = table.getColumnModel().getColumn(columPos);
			this.wrappedRenderer = column.getCellRenderer();
			if (wrappedRenderer == null) {
				wrappedRenderer = new DefaultTableCellRenderer();
			}
			this.tableColorDef = tableColorDef;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component ret = wrappedRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
					column);
			Pair<Color, Color> colorPair = tableColorDef.getTableColour(table, value, isSelected, hasFocus, row,
					column);
			if (colorPair == null) {
				colorPair = Pair.of(null, Color.BLACK);
			}
			Color bgColor = colorPair.getRight() != null ? colorPair.getRight() : Color.BLACK;
			ret.setBackground(colorPair.getLeft());
			ret.setForeground(bgColor);
			return ret;
		}
	}

	/**
	 * 設定欄被景色, 前景色
	 */
	public interface TableColorDef {
		Pair<Color, Color> getTableColour(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
				int column);
	}

	public Object getColumnTitle(int col) {
		TableColumnModel titleModel = table.getTableHeader().getColumnModel();
		if (titleModel.getColumnCount() > col) {
			TableColumn col2 = titleModel.getColumn(col);
			return col2.getHeaderValue();
		}
		return null;
	}

	public boolean setColumTitle(int colIdx, Object titleObj) {
		TableColumnModel titleModel = table.getTableHeader().getColumnModel();
		for (int ii = 0; ii < titleModel.getColumnCount(); ii++) {
			TableColumn colX = titleModel.getColumn(ii);
			if (ii == colIdx) {
				colX.setHeaderValue(titleObj);
				return true;
			}
		}
		return false;
	}

	public List<Object> getColumnTitleArray() {
		List<Object> titles = new ArrayList<Object>();
		TableColumnModel titleModel = table.getTableHeader().getColumnModel();
		for (int ii = 0; ii < titleModel.getColumnCount(); ii++) {
			TableColumn col = titleModel.getColumn(ii);
			titles.add(col.getHeaderValue());
		}
		return titles;
	}

	public List<String> getColumnTitleStringArray() {
		return getColumnTitleStringArray(null);
	}

	public List<String> getColumnTitleStringArray(int[] ignoreColIdxes) {
		List<String> titles = new ArrayList<String>();
		List<Object> titles2 = getColumnTitleArray();
		for (int ii = 0; ii < titles2.size(); ii++) {
			if (ignoreColIdxes != null && ArrayUtils.contains(ignoreColIdxes, ii)) {
				continue;
			}
			Object t = titles2.get(ii);
			titles.add(String.valueOf(t));
		}
		return titles;
	}

	// onblur 修改
	public void applyOnCellBlurEvent(DefaultTableModel model, final ActionListener listener) {
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		if (model == null) {
			model = (DefaultTableModel) table.getModel();
		}
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				Object orignVal = null;
				String strVal = "ERR";
				try {
					orignVal = JTableUtil.newInstance(table).getValueAt(false, row, col);
					strVal = orignVal != null ? (orignVal + " -> " + orignVal.getClass()) : "null";
				} catch (Exception ex) {
					ex.getMessage();
				}
				System.out.println(String.format("## table change -> row[%d], col[%d] -----> %s", row, col, strVal));
				// 刷新table紀錄！！！ onBlur !!!!!
				if (listener != null) {
					Map<String, Object> data = new LinkedHashMap<String, Object>();
					data.put("row", row);
					data.put("col", col);
					data.put("value", orignVal);
					listener.actionPerformed(new ActionEvent(data, -1, "Map"));
				}
				table.invalidate();
			}
		});
	}

	public DefaultTableModel cloneModelSimpleV1(boolean cloneData) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<Object> titles = JTableUtil.newInstance(table).getColumnTitleArray();
		DefaultTableModel cloneModel = new DefaultTableModel(titles.toArray(), 0);
		Vector vec = model.getDataVector();
		if (cloneData) {
			for (int ii = 0; ii < vec.size(); ii++) {
				Vector row = (Vector) vec.get(ii);
				cloneModel.addRow(row.toArray());
			}
		}
		return cloneModel;
	}

	public static class ColumnSearchFilter {
		protected JTable table;
		protected String delimit;
		protected Object[] alwaysMatchColumns;
		protected boolean doHiddenColumn = false;

		public ColumnSearchFilter(JTable table, String delimit, Object[] alwaysMatchColumns) {
			this.table = table;
			this.delimit = (delimit == null || StringUtils.isBlank(delimit)) ? "," : delimit;
			this.alwaysMatchColumns = alwaysMatchColumns;
			this.initTableColumns();
		}

		protected Pair<List<Object>, List<TableColumn>> headerDef = null;

		protected void initTableColumns() {
			if (headerDef == null) {
				TableColumnModel columnModel = this.table.getTableHeader().getColumnModel();
				List<Object> headerLst = new ArrayList<Object>();
				List<TableColumn> headerLst2 = new ArrayList<TableColumn>();
				for (int ii = 0; ii < columnModel.getColumnCount(); ii++) {
					headerLst.add(columnModel.getColumn(ii).getHeaderValue());
					headerLst2.add(columnModel.getColumn(ii));
					System.out.println("Def Add : " + columnModel.getColumn(ii).getHeaderValue());
				}
				headerDef = Pair.of(headerLst, headerLst2);
			}
		}

		public void addAll() {
			if (headerDef == null) {
				throw new RuntimeException("headerDef 為空 [361]");
			}
			TableColumnModel columnModel = table.getTableHeader().getColumnModel();
			for (int ii = 0; ii < headerDef.getRight().size(); ii++) {
				TableColumn tabcol = headerDef.getRight().get(ii);
				columnModel.addColumn(tabcol);
			}
		}

		protected void removeAll() {
			TableColumnModel columnModel = this.table.getTableHeader().getColumnModel();
			A: for (int ii = 0; ii < columnModel.getColumnCount(); ii++) {
				Object colName = columnModel.getColumn(ii).getHeaderValue();
				System.out.println("clear : " + colName);
				columnModel.removeColumn(columnModel.getColumn(ii));
				ii--;
			}
		}

		class InnerMatch {
			Pattern ptn;

			InnerMatch(String singleText) {
				singleText = singleText.replaceAll(Pattern.quote("*"), ".*");
				ptn = Pattern.compile(singleText, Pattern.CASE_INSENSITIVE);
			}

			boolean find(String value) {
				Matcher mth = ptn.matcher(value);
				return mth.find();
			}
		}

		/**
		 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX new fix
		 * @param filterText
		 */
		protected void __filterText(String filterText) {
			doHiddenColumn = false;

			TableColumnModel columnModel = table.getTableHeader().getColumnModel();

			// 解析 regex ptn
			Pair<String, List<Pattern>> afterFilterProc = filterPattern(filterText);

			String[] params = StringUtils.trimToEmpty(afterFilterProc.getLeft()).toUpperCase()
					.split(Pattern.quote(delimit), -1);
//			Map<String, List<TableColumn>> addColumns = new LinkedHashMap<String, List<TableColumn>>();
			
			Set<TableColumn> containsSet = new LinkedHashSet<TableColumn>();

			for (int ii = 0; ii < headerDef.getLeft().size(); ii++) {
				Object key = headerDef.getLeft().get(ii);
				String headerColumn = String.valueOf(key);
				String headerColumnUpper = headerColumn.toUpperCase();
				
				for (String param : params) {
					param = StringUtils.trimToEmpty(param);
					InnerMatch m = new InnerMatch(param);

					boolean findOk = false;

					if (this.alwaysMatchColumns != null) {
						for (Object v : this.alwaysMatchColumns) {
							if (StringUtils.equals(String.valueOf(headerColumn), String.valueOf(v))) {
								findOk = true;
								break;
							}
						}
					}

					if (!findOk) {
						if (StringUtils.isNotBlank(param) && headerColumnUpper.contains(param)) {
							System.out.println("Match------------" + headerColumn + " --> " + param);
							findOk = true;
						} else if (param.contains("*")) {
							if (m.find(headerColumnUpper)) {
								findOk = true;
							}
						} else if (!afterFilterProc.getRight().isEmpty()) {
							for (Pattern p : afterFilterProc.getRight()) {
								if (p.matcher(headerColumn).find()) {
									findOk = true;
									break;
								}
							}
						} else if (StringUtils.isBlank(filterText)) {
							findOk = true;
						}
					}

					if (findOk && !containsSet.contains(headerDef.getRight().get(ii))) {// && !addColumns.containsKey(headerColumn)
						System.out.println("Add------------" + key);
						containsSet.add(headerDef.getRight().get(ii));
					} else {
						doHiddenColumn = true;
					}
				}
			}

			for (TableColumn column : containsSet) {
				columnModel.addColumn(column);
			}
		}

		public boolean isDoHiddenColumn() {
			return doHiddenColumn;
		}

		public void filterText(String filterText) {
			removeAll();
			if (StringUtils.isNotBlank(filterText)) {
				__filterText(filterText);
			} else {
				addAll();
			}
		}
	}

	/**
	 * 設定選項hover事件
	 */
	public void applyOnHoverEvent(final ActionListener listener) {
		table.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (listener == null) {
					return;
				}
				Pair<Integer, Integer> pair = getPositionFromMouseEvent(e);
				if (pair != null) {
					listener.actionPerformed(new ActionEvent(pair, pair.getLeft(), "left:row, right:col"));
				}
			}
		});
	}

	/**
	 * 取得滑鼠點擊位置
	 */
	public Pair<Integer, Integer> getPositionFromMouseEvent(MouseEvent e) {
		int rowPos = table.rowAtPoint(e.getPoint());
		if (rowPos > -1) {
			for (int col = 0; col < table.getColumnCount(); col++) {
				Rectangle bounds = table.getCellRect(rowPos, col, true);
				if (bounds.contains(e.getPoint())) {
					return Pair.of(rowPos, col);
				}
			}
		}
		return null;
	}

	public static void setColumnAlign(JTable table, int columnIndex, int JLabel_RIGNH_LEFT_CENTER_ETC) {
		DefaultTableCellRenderer rightRenderer = null;
		if (table.getColumnModel().getColumn(columnIndex).getCellRenderer() != null && table.getColumnModel()
				.getColumn(columnIndex).getCellRenderer() instanceof DefaultTableCellRenderer) {
			rightRenderer = (DefaultTableCellRenderer) table.getColumnModel().getColumn(columnIndex).getCellRenderer();
		} else {
			rightRenderer = new DefaultTableCellRenderer();
		}
		rightRenderer.setHorizontalAlignment(JLabel_RIGNH_LEFT_CENTER_ETC);
		table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
	}

	public void setRowsBackgroundColor(java.awt.Color color, Integer cellIdx, final List<Integer> colorRowIndexLst) {
		class MyRenderer extends DefaultTableCellRenderer {
			java.awt.Color color;

			MyRenderer(java.awt.Color color) {
				this.color = color;
			}

			Color backgroundColor = getBackground();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// row = JTableUtil.getRealRowPos(row, table);
				column = JTableUtil.getRealColumnPos(column, table);
				if (colorRowIndexLst.contains(row)) {
					c.setBackground(this.color);
				} else if (!isSelected) {
					c.setBackground(backgroundColor);
				}
				return c;
			}
		}
		MyRenderer mMyRenderer = new MyRenderer(color);
		if (cellIdx == null) {
			for (int ii = 0; ii < table.getColumnModel().getColumnCount(); ii++) {
				table.getColumnModel().getColumn(ii).setCellRenderer(mMyRenderer);
			}
		} else {
			table.getColumnModel().getColumn(cellIdx).setCellRenderer(mMyRenderer);
		}
		table.updateUI();
	}

	public void setCellBackgroundColor(Color color, final Map<Integer, List<Integer>> changeColorRowCellIdxMap,
			List<Integer> ignoreColLst) {
		class MyRenderer extends DefaultTableCellRenderer {
			java.awt.Color color;

			MyRenderer(java.awt.Color color) {
				this.color = color;
			}

			Color backgroundColor = getBackground();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// row = JTableUtil.getRealRowPos(row, table);
				column = JTableUtil.getRealColumnPos(column, table);
				boolean findOk = false;
				if (changeColorRowCellIdxMap.containsKey(row)) {
					List<Integer> cellLst = changeColorRowCellIdxMap.get(row);
					if (cellLst.contains(column)) {
						c.setBackground(this.color);
						findOk = true;
					}
				}
				if (!isSelected && !findOk) {
					c.setBackground(backgroundColor);
				}
				return c;
			}
		}
		MyRenderer mMyRenderer = new MyRenderer(color);
		for (int ii = 0; ii < table.getColumnModel().getColumnCount(); ii++) {
			if (ignoreColLst != null && ignoreColLst.contains(ii)) {
				continue;
			}
			table.getColumnModel().getColumn(ii).setCellRenderer(mMyRenderer);
		}
		table.updateUI();
	}
	
	public void setCellBackgroundColor2(Color color, Color theOtherColor, final Map<Integer, List<Integer>> changeColorRowCellIdxMap,
			List<Integer> ignoreColLst) {
		class MyRenderer extends DefaultTableCellRenderer {
			java.awt.Color color;
			java.awt.Color theOtherColor;

			MyRenderer(java.awt.Color color, java.awt.Color theOtherColor) {
				this.color = color;
				this.theOtherColor = theOtherColor;
			}

			Color backgroundColor = getBackground();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// row = JTableUtil.getRealRowPos(row, table);
				column = JTableUtil.getRealColumnPos(column, table);
				boolean findOk = false;
				if (changeColorRowCellIdxMap.containsKey(row)) {
					List<Integer> cellLst = changeColorRowCellIdxMap.get(row);
					if (cellLst.contains(column)) {
						c.setBackground(this.color);
						findOk = true;
					}
					//同個row的其他cell顏色
					if(!findOk) {
						c.setBackground(this.theOtherColor);
						findOk = true;
					}
				}
				if (!isSelected && !findOk) {
					c.setBackground(backgroundColor);
				}
				return c;
			}
		}
		MyRenderer mMyRenderer = new MyRenderer(color, theOtherColor);
		for (int ii = 0; ii < table.getColumnModel().getColumnCount(); ii++) {
			if (ignoreColLst != null && ignoreColLst.contains(ii)) {
				continue;
			}
			table.getColumnModel().getColumn(ii).setCellRenderer(mMyRenderer);
		}
		table.updateUI();
	}

	public void setRowSelection() {
		int[] rows = JTableUtil.newInstance(table).getSelectedRows(false);
		int minPos = Integer.MAX_VALUE;
		int maxPos = Integer.MIN_VALUE;
		for (int i : rows) {
			maxPos = Math.max(maxPos, i);
			minPos = Math.min(minPos, i);
		}
		table.setRowSelectionInterval(minPos, maxPos);
		table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
	}

	public void setRowSelection(int rowIdx) {
		table.setRowSelectionInterval(rowIdx, rowIdx);
		table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
	}

	public static class JTooltipTable extends JTable {
		private static final long serialVersionUID = 1L;
		private Transformer titleTooltipTransformer;

		public void setTitleTooltipTransformer(Transformer titleTooltipTransformer) {
			this.titleTooltipTransformer = titleTooltipTransformer;
		}

		public String getColumnTooltips(int index, Object headerValue) {
			if (titleTooltipTransformer != null) {
				return (String) titleTooltipTransformer.transform(Pair.of(index, headerValue));
			}
			return null;
		}

		// Implement table header tool tips.
		protected JTableHeader createDefaultTableHeader() {
			return new JTableHeader(columnModel) {
				public String getToolTipText(MouseEvent e) {
					try {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int index = columnModel.getColumnIndexAtX(p.x);
						int realIndex = columnModel.getColumn(index).getModelIndex();
						Object headerValue = columnModel.getColumn(index).getHeaderValue();
						return getColumnTooltips(realIndex, headerValue);
					} catch (Throwable ex) {
						// ex.printStackTrace();
						return null;
					}
				}
			};
		}
		
		@Override
        public JToolTip createToolTip() {
            return JTooltipUtil.createToolTip(null, null, 0);
        }

        public Point getToolTipLocation(MouseEvent e) {
            return new Point(e.getPoint().x + 10, e.getPoint().y);// r.width
        }
	}

	public static abstract class OnBlurCellEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;

		public OnBlurCellEditor(JCheckBox checkBox, boolean true_is_onblur_false_is_onchange) {
			super(checkBox);
			this.true_is_onblur_false_is_onchange = true_is_onblur_false_is_onchange;
		}

		public OnBlurCellEditor(JComboBox comboBox, boolean true_is_onblur_false_is_onchange) {
			super(comboBox);
			this.true_is_onblur_false_is_onchange = true_is_onblur_false_is_onchange;
		}

		public OnBlurCellEditor(JTextField textField, boolean true_is_onblur_false_is_onchange) {
			super(textField);
			this.true_is_onblur_false_is_onchange = true_is_onblur_false_is_onchange;
		}

		boolean true_is_onblur_false_is_onchange;
		int lastestRow = -1;
		int lastestCol = -1;
		Object beforeValue = null;

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			lastestRow = row;
			lastestCol = column;
			beforeValue = value;
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}

		public abstract void onblur(int row, int col, Object value);

		public boolean stopCellEditing() {
			Object currentValue = this.getCellEditorValue();
			if (true_is_onblur_false_is_onchange) {
				onblur(lastestRow, lastestCol, currentValue);
			} else {
				if (currentValue != beforeValue) {
					onblur(lastestRow, lastestCol, currentValue);
				}
			}
			return super.stopCellEditing();
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------

	private static Pair<String, List<Pattern>> filterPattern(String filterText) {
		Pattern ptn = Pattern.compile("\\/(.*?)\\/");
		Matcher mth = ptn.matcher(filterText);
		StringBuffer sb = new StringBuffer();
		List<Pattern> lst = new ArrayList<Pattern>();
		while (mth.find()) {
			String temp = mth.group(1);
			Pattern tmpPtn = null;
			if (StringUtils.isNotBlank(temp)) {
				try {
					tmpPtn = Pattern.compile(temp, Pattern.CASE_INSENSITIVE);
				} catch (Exception ex) {
				}
			}
			if (tmpPtn != null) {
				lst.add(tmpPtn);
				mth.appendReplacement(sb, "");
			} else {
				mth.appendReplacement(sb, mth.group(0));
			}
		}
		mth.appendTail(sb);
		return Pair.of(sb.toString(), lst);
	}

	public void findSearchTextMatchChangeColor(String text11, List<Integer> ignoreColLst) {
		try {
			Map<Integer, List<Integer>> changeColorMap = new HashMap<Integer, List<Integer>>();
			if (StringUtils.isBlank(text11)) {
				JTableUtil.newInstance(table).setCellBackgroundColor(Color.green.brighter(), changeColorMap,
						ignoreColLst);
				return;
			}

			Pair<String, List<Pattern>> mthPtn = filterPattern(text11);

			String text1 = StringUtils.trimToEmpty(mthPtn.getLeft());
			String text = text1.toLowerCase();
			List<String> textLst = new ArrayList<String>();
			for (String t : text1.split("\\^", -1)) {
				t = StringUtils.trimToEmpty(t).toLowerCase();
				if (StringUtils.isNotBlank(t)) {
					textLst.add(t);
				}
			}

			JTableUtil util = JTableUtil.newInstance(table);
			DefaultTableModel model = util.getModel();

			for (int ii = 0; ii < model.getRowCount(); ii++) {
				List<Integer> lst = new ArrayList<Integer>();
				changeColorMap.put(ii, lst);
				A: for (int jj = 0; jj < table.getColumnCount(); jj++) {
					Object val = util.getValueAt(true, ii, jj);
					if (val instanceof String) {
						String strVal = (String) val;
						for (String txt : textLst) {
							if (strVal.toLowerCase().contains(txt)) {
								lst.add(jj);
								continue A;
							}
						}
						for (Pattern pp : mthPtn.getRight()) {
							if (pp != null && pp.matcher(strVal).find()) {
								lst.add(jj);
								continue A;
							}
						}
					}
				}
			}
			JTableUtil.newInstance(table).setCellBackgroundColor(Color.green.brighter(), changeColorMap, ignoreColLst);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// ----------------------------------------------------------------------------------------------------------------------

	public void setRowFilter(final ActionListener mListener) {
		RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
			@Override
			public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Object> entry) {
				RowFilter.Entry entry2 = (RowFilter.Entry) entry;
				ActionEvent event = new ActionEvent(entry2, (Integer) entry2.getIdentifier(), "");
				mListener.actionPerformed(event);
				if (event.getSource() instanceof Boolean) {
					return (Boolean) event.getSource();
				}
				return true;
			}
		};
		((TableRowSorter) table.getRowSorter()).setRowFilter(filter);
	}

	// ----------------------------------------------------------------------------------------------------------------------
	public static class JTable4FixToolTip extends JTable {
		@Override
		public JToolTip createToolTip() {
			return JTooltipUtil.createToolTip(null, null, 100);
		}

		public Point getToolTipLocation(MouseEvent e) {
			return new Point(e.getPoint().x + 100, e.getPoint().y);// r.width
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------
	public void columnIsJTextComponent(Integer index, final JTextComponent component2) {
		class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
			JTextComponent component;

			private MyTableCellEditor() {
				component = component2;
			}

			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex,
					int vColIndex) {
				((JTextComponent) component).setText((String) value);
				return component;
			}

			public Object getCellEditorValue() {
				return ((JTextComponent) component).getText();
			}

			public boolean isCellEditable(EventObject anEvent) {
				if (anEvent instanceof MouseEvent) {
					return ((MouseEvent) anEvent).getClickCount() >= 2;
				}
				return true;
			}
		}
		MyTableCellEditor mMyTableCellEditor = new MyTableCellEditor();
		if (index == null) {
			table.setCellEditor(mMyTableCellEditor);
		} else {
			TableColumn comboCol1 = table.getColumnModel().getColumn(index);
			comboCol1.setCellEditor(mMyTableCellEditor);
		}
	}
	// ----------------------------------------------------------------------------------------------------------------------

	public void debugShowCurrentTable() {
		final int DATA_LENGTH = 30;
		for (int rowIdx = 0; rowIdx < table.getRowCount(); rowIdx++) {
			int realRowIdx = getRealRowPos(rowIdx, table);
			TreeMap<Integer, Object> rowMap = new TreeMap<Integer, Object>();
			for (int col = 0; col < table.getColumnCount(); col++) {
				int realCol = getRealColumnPos(col, table);
				boolean visible = isColumnVisible(realCol, table);
				Object columnTitle = getColumnTitle(col);
				if (visible) {
					Object value = getModel().getValueAt(realRowIdx, realCol);
					String strVal = null;
					if (value != null) {
						strVal = String.valueOf(value);
						if (StringUtils.length(strVal) > DATA_LENGTH) {
							strVal = StringUtils.substring(strVal, 0, DATA_LENGTH) + "...";
						}
					}
					rowMap.put(realCol, strVal);
				}
			}
			System.out.println("\t[row:" + realRowIdx + "] data : " + rowMap);
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------------------------------------
}
