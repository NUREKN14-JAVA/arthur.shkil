package com.shkil.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shkil.usermanagement.User;
import com.shkil.usermanagement.db.DaoFactory;
import com.shkil.usermanagement.db.UserDao;
import com.shkil.usermanagement.util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	
	private UserDao dao;
	
	private JPanel contentPanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
	private EditPanel editPanel;
	private DeletePanel deletePanel;
	private DetailsPanel detailsPanel;

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}
	
	public UserDao getDao() {
		return dao;
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}

	public static void main() {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	private JPanel getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}

	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	public void showEditPanel(){
		showPanel(getEditPanel());
	}
	public void showDeletePanel(){
		showPanel(getDeletePanel());
	}
	public void showDetailsPanel(){
		showPanel(getDetailsPanel());
	}
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}
	
	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}
	private EditPanel getEditPanel(){
		if (editPanel == null){
			editPanel = new EditPanel(this);
		}
		editPanel.setFields();
		return editPanel;
	}
	private DeletePanel getDeletePanel(){
		if (deletePanel == null){
			deletePanel = new DeletePanel(this);
		}
		deletePanel.setFields();
		return deletePanel;
	}
	private DetailsPanel getDetailsPanel(){
		if (detailsPanel == null){
			detailsPanel = new DetailsPanel(this);
		}
		detailsPanel.setFields();
		return detailsPanel;
	}
	User getSelectedUser() {
		return ((BrowsePanel) browsePanel).getSelectedUser();
	}
	
}
