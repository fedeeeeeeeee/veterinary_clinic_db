package view;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String nameButton;
	//private JButton button;
	public ButtonRenderer(String nameButton) {
		this.nameButton = nameButton;
		//this.button = button;
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		/*
		ImageIcon icon = new ImageIcon("resources"+ System.getProperty("file.separator") + "images"+ System.getProperty("file.separator") + "information-button.png");
		setIcon(icon);
		*/
		setText((value == null) ? this.nameButton : value.toString());
		//setText((value == null) ? "Modify" : value.toString());
		return this;
	}

}

class ButtonEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String label;
	private JButton button;
	private String nameButton;
	
	public ButtonEditor(JCheckBox checkBox, JButton button, String nameButton) {
		super(checkBox);
		this.button = button;
		this.nameButton = nameButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		/*
		ImageIcon icon = new ImageIcon("resources"+ System.getProperty("file.separator") + "images"+ System.getProperty("file.separator") + "information-button.png");
		button.setIcon(icon);
		*/
		//label = (value == null) ? "Modify" : value.toString();
		label = (value == null) ? this.nameButton : value.toString();
		button.setText(label);
		
		return button;
	}

	public Object getCellEditorValue() {
		return new String(label);
	}

}