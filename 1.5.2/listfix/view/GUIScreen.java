/*
 * listFix() - Fix Broken Playlists!
 * Copyright (C) 2001-2008 Jeremy Caron
 * 
 * This file is part of listFix().
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, please see http://www.gnu.org/licenses/
 */

package listfix.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Enumeration;
import java.util.Vector;

import listfix.io.*;
import listfix.model.*;
import listfix.tasks.*;
import listfix.controller.GUIDriver;
import listfix.exceptions.UnsupportedPlaylistFormat;
import listfix.util.ArrayFunctions;

public class GUIScreen extends JFrame 
{
	private static final long serialVersionUID = 7691786927987534889L;
	private final JFileChooser jM3UChooser;
	private final JFileChooser jFileChooser;
	private final JFileChooser jMediaDirChooser;
	private final JFileChooser jCopyToDirChooser;
	private final JFileChooser jSaveFileChooser;	
    private final listfix.view.support.ProgressPopup locateProgressDialog;
    private final listfix.view.support.ProgressPopup copyFilesProgressDialog;
    private final listfix.view.support.ProgressPopup updateMediaLibraryProgressDialog;    
    private final listfix.view.support.ProgressPopup openM3UProgressDialog;
    private GUIDriver guiDriver = null;
    private int currentlySelectedRow;
    private int currentRightClick;

    /** Creates new form GUIScreen */
    public GUIScreen() 
    {        
        initComponents();
        jM3UChooser = new JFileChooser();
        jFileChooser = new JFileChooser();
        jMediaDirChooser = new JFileChooser();
        jCopyToDirChooser = new JFileChooser();
        jSaveFileChooser = new JFileChooser();
        locateProgressDialog = new listfix.view.support.ProgressPopup(this, "Finding Files", true, 250, 25, false);
        copyFilesProgressDialog = new listfix.view.support.ProgressPopup(this, "Copying Files", true, 250, 25, false);
        updateMediaLibraryProgressDialog = new listfix.view.support.ProgressPopup(this, "Updating Media Library", true, 450, 40, true); 
        openM3UProgressDialog = new listfix.view.support.ProgressPopup(this, "Opening Playlist", true, 250, 25, false); 
        guiDriver = new GUIDriver();
		this.setLookAndFeel(guiDriver.getAppOptions().getLookAndFeel());
        jM3UChooser.setDialogTitle("Choose a Playlist...");
        jM3UChooser.setAcceptAllFileFilterUsed(false);
        jM3UChooser.setFileFilter(new M3UFilter());
        jFileChooser.setDialogTitle("Choose a file to append");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setFileFilter(new ValidM3UFileRefFileChooserFilter());
        jMediaDirChooser.setDialogTitle("Specify a media directory...");
        jMediaDirChooser.setAcceptAllFileFilterUsed(false);
        jMediaDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jCopyToDirChooser.setDialogTitle("Choose a destination directory...");
        jCopyToDirChooser.setAcceptAllFileFilterUsed(false);
        jCopyToDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jSaveFileChooser.setDialogTitle("Save File:");
        jSaveFileChooser.setAcceptAllFileFilterUsed(false);
        jSaveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jSaveFileChooser.setFileFilter(new M3UFilter()); 
        playlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playlistTable.setModel(new CustomTableModel());
        playlistTable.getTableHeader().setFont(new Font("Verdana", 0, 9));
        if (guiDriver.getShowMediaDirWindow())
        {
            JOptionPane.showMessageDialog(this, "You need to add a media directory before you can find the new locations of your files.  See help for more information.", "Reminder", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            mediaLibraryList.setListData(guiDriver.getMediaDirs());
        }
        updateButtons();
        updateRecentMenu();
        updateStatusLabel();
		(new FileWriter()).writeIni(guiDriver.getMediaDirs(), guiDriver.getMediaLibraryDirectoryList(), guiDriver.getMediaLibraryFileList(), guiDriver.getAppOptions());
    }
	
	public AppOptions getOptions()
	{
		return guiDriver.getAppOptions();
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entryRightClickMenu = new javax.swing.JPopupMenu();
        deleteRCMenuItem = new javax.swing.JMenuItem();
        editFileNameRCMenuItem = new javax.swing.JMenuItem();
        replaceFileRCMenuItem = new javax.swing.JMenuItem();
        closestMatchesRCMenuItem = new javax.swing.JMenuItem();
        openRCMenuItem = new javax.swing.JMenuItem();
        buttonPanel = new javax.swing.JPanel();
        openIconButton = new javax.swing.JButton();
        closeIconButton = new javax.swing.JButton();
        saveIconButton = new javax.swing.JButton();
        upIconButton = new javax.swing.JButton();
        downIconButton = new javax.swing.JButton();
        deleteIconButton = new javax.swing.JButton();
        openPlaylistButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        splitPane = new javax.swing.JSplitPane();
        mediaLibraryPanel = new javax.swing.JPanel();
        mediaLibraryButtonPanel = new javax.swing.JPanel();
        addMediaDirButton = new javax.swing.JButton();
        removeMediaDirButton = new javax.swing.JButton();
        refreshMediaDirsButton = new javax.swing.JButton();
        mediaLibraryScrollPane = new javax.swing.JScrollPane();
        mediaLibraryList = new javax.swing.JList();
        playlistPanel = new javax.swing.JPanel();
        playlistButtonPanel = new javax.swing.JPanel();
        locateButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        playlistTablePanel = new javax.swing.JPanel();
        playlistScrollPanel = playlistScrollPanel = new javax.swing.JScrollPane(playlistTable);
        playlistTable = new javax.swing.JTable();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        recentMenu = new javax.swing.JMenu();
        clearHistoryMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        appOptionsMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        exit = new javax.swing.JMenuItem();
        actionsMenu = new javax.swing.JMenu();
        appendFileMenuItem = new javax.swing.JMenuItem();
        insertFileMenuItem = new javax.swing.JMenuItem();
        editFilenameMenuItem = new javax.swing.JMenuItem();
        findClosestMatchesMenuItem = new javax.swing.JMenuItem();
        playFileMenuItem = new javax.swing.JMenuItem();
        removeMenuItem = new javax.swing.JMenuItem();
        removeMissingMenuItem = new javax.swing.JMenuItem();
        removeDuplicatesMenuItem = new javax.swing.JMenuItem();
        replaceFileMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        appendPlaylistMenuItem = new javax.swing.JMenuItem();
        insertPlaylistMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        copyToDirMenuItem = new javax.swing.JMenuItem();
        sortMenu = new javax.swing.JMenu();
        randomizeMenuItem = new javax.swing.JMenuItem();
        reverseMenuItem = new javax.swing.JMenuItem();
        filenameSortMenu = new javax.swing.JMenu();
        ascendingFilenameMenuItem = new javax.swing.JMenuItem();
        descendingFilenameMenuItem = new javax.swing.JMenuItem();
        statusSortMenu = new javax.swing.JMenu();
        ascendingStatusMenuItem = new javax.swing.JMenuItem();
        descendingStatusMenuItem = new javax.swing.JMenuItem();
        pathSortMenu = new javax.swing.JMenu();
        ascendingPathMenuItem = new javax.swing.JMenuItem();
        descendingPathMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();

        deleteRCMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        deleteRCMenuItem.setMnemonic('D');
        deleteRCMenuItem.setText("Delete");
        deleteRCMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRCMenuItemActionPerformed(evt);
            }
        });
        entryRightClickMenu.add(deleteRCMenuItem);

        editFileNameRCMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        editFileNameRCMenuItem.setMnemonic('E');
        editFileNameRCMenuItem.setText("Edit Filename");
        editFileNameRCMenuItem.setEnabled(false);
        editFileNameRCMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFileNameRCMenuItemActionPerformed(evt);
            }
        });
        entryRightClickMenu.add(editFileNameRCMenuItem);

        replaceFileRCMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        replaceFileRCMenuItem.setText("Replace Selected Entry");
        replaceFileRCMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceFileRCMenuItemActionPerformed(evt);
            }
        });
        entryRightClickMenu.add(replaceFileRCMenuItem);

        closestMatchesRCMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        closestMatchesRCMenuItem.setMnemonic('C');
        closestMatchesRCMenuItem.setText("Find Closest Matches");
        closestMatchesRCMenuItem.setEnabled(false);
        closestMatchesRCMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closestMatchesRCMenuItemActionPerformed(evt);
            }
        });
        entryRightClickMenu.add(closestMatchesRCMenuItem);

        openRCMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        openRCMenuItem.setText("Open");
        openRCMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openRCMenuItemActionPerformed(evt);
            }
        });
        entryRightClickMenu.add(openRCMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("listFix( ) - v1.5.1");
        setName("mainFrame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        buttonPanel.setAlignmentX(0.0F);
        buttonPanel.setAlignmentY(0.0F);
        buttonPanel.setFont(buttonPanel.getFont());
        buttonPanel.setMaximumSize(null);
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 2));

        openIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open.gif"))); // NOI18N
        openIconButton.setToolTipText("Open Playlist");
        openIconButton.setBorder(null);
        openIconButton.setFocusable(false);
        openIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        openIconButton.setMinimumSize(null);
        openIconButton.setPreferredSize(null);
        openIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openIconButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(openIconButton);

        closeIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.gif"))); // NOI18N
        closeIconButton.setToolTipText("Close Playlist");
        closeIconButton.setBorder(null);
        closeIconButton.setFocusable(false);
        closeIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        closeIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        closeIconButton.setMinimumSize(null);
        closeIconButton.setPreferredSize(null);
        closeIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeIconButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeIconButton);

        saveIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
        saveIconButton.setToolTipText("Save");
        saveIconButton.setBorder(null);
        saveIconButton.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save_off.gif"))); // NOI18N
        saveIconButton.setFocusable(false);
        saveIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        saveIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        saveIconButton.setMinimumSize(null);
        saveIconButton.setPreferredSize(null);
        saveIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(saveIconButton);

        upIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow-up.gif"))); // NOI18N
        upIconButton.setToolTipText("Move Up");
        upIconButton.setBorder(null);
        upIconButton.setFocusable(false);
        upIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        upIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        upIconButton.setMinimumSize(null);
        upIconButton.setPreferredSize(null);
        upIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upIconButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(upIconButton);

        downIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_down.gif"))); // NOI18N
        downIconButton.setToolTipText("Move Down");
        downIconButton.setBorder(null);
        downIconButton.setFocusable(false);
        downIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        downIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        downIconButton.setMinimumSize(null);
        downIconButton.setPreferredSize(null);
        downIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downIconButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(downIconButton);

        deleteIconButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.gif"))); // NOI18N
        deleteIconButton.setToolTipText("Remove Selected Entry");
        deleteIconButton.setBorder(null);
        deleteIconButton.setFocusable(false);
        deleteIconButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        deleteIconButton.setMaximumSize(new java.awt.Dimension(50, 50));
        deleteIconButton.setMinimumSize(null);
        deleteIconButton.setPreferredSize(null);
        deleteIconButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteIconButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(deleteIconButton);

        openPlaylistButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play.gif"))); // NOI18N
        openPlaylistButton.setToolTipText("Play this list (available when list is in a saved state)");
        openPlaylistButton.setBorder(null);
        openPlaylistButton.setFocusable(false);
        openPlaylistButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        openPlaylistButton.setMaximumSize(new java.awt.Dimension(50, 50));
        openPlaylistButton.setMinimumSize(null);
        openPlaylistButton.setPreferredSize(null);
        openPlaylistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPlaylistButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(openPlaylistButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.NORTH);

        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setLayout(new java.awt.BorderLayout());

        statusLabel.setFont(new java.awt.Font("Verdana", 0, 9));
        statusLabel.setForeground(new java.awt.Color(153, 153, 153));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        statusLabel.setText("Untitled List     Number of entries in list: 0     Number of lost entries: 0     Number of URLs: 0");
        statusPanel.add(statusLabel, java.awt.BorderLayout.WEST);

        getContentPane().add(statusPanel, java.awt.BorderLayout.SOUTH);

        splitPane.setDividerSize(7);
        splitPane.setMaximumSize(null);
        splitPane.setOneTouchExpandable(true);
        splitPane.setPreferredSize(new java.awt.Dimension(785, 489));

        mediaLibraryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Media Directories", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 9)));
        mediaLibraryPanel.setLayout(new java.awt.BorderLayout());

        mediaLibraryButtonPanel.setMinimumSize(new java.awt.Dimension(223, 31));

        addMediaDirButton.setFont(new java.awt.Font("Verdana", 0, 9));
        addMediaDirButton.setText("Add");
        addMediaDirButton.setFocusable(false);
        addMediaDirButton.setMinimumSize(new java.awt.Dimension(53, 25));
        addMediaDirButton.setPreferredSize(null);
        addMediaDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMediaDirButtonActionPerformed(evt);
            }
        });
        mediaLibraryButtonPanel.add(addMediaDirButton);

        removeMediaDirButton.setFont(new java.awt.Font("Verdana", 0, 9));
        removeMediaDirButton.setText("Remove");
        removeMediaDirButton.setFocusable(false);
        removeMediaDirButton.setMinimumSize(new java.awt.Dimension(73, 25));
        removeMediaDirButton.setPreferredSize(null);
        removeMediaDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMediaDirButtonActionPerformed(evt);
            }
        });
        mediaLibraryButtonPanel.add(removeMediaDirButton);

        refreshMediaDirsButton.setFont(new java.awt.Font("Verdana", 0, 9));
        refreshMediaDirsButton.setText("Refresh");
        refreshMediaDirsButton.setFocusable(false);
        refreshMediaDirsButton.setMinimumSize(new java.awt.Dimension(71, 25));
        refreshMediaDirsButton.setPreferredSize(null);
        refreshMediaDirsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMediaDirsButtonActionPerformed(evt);
            }
        });
        mediaLibraryButtonPanel.add(refreshMediaDirsButton);

        mediaLibraryPanel.add(mediaLibraryButtonPanel, java.awt.BorderLayout.SOUTH);

        mediaLibraryList.setFont(new java.awt.Font("Verdana", 0, 9));
        mediaLibraryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        mediaLibraryList.setPreferredSize(null);
        mediaLibraryScrollPane.setViewportView(mediaLibraryList);

        mediaLibraryPanel.add(mediaLibraryScrollPane, java.awt.BorderLayout.CENTER);

        splitPane.setLeftComponent(mediaLibraryPanel);

        playlistPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Playlist Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 9)));
        playlistPanel.setLayout(new java.awt.BorderLayout());

        locateButton.setFont(new java.awt.Font("Verdana", 0, 9));
        locateButton.setText("Locate Files");
        locateButton.setFocusable(false);
        locateButton.setMinimumSize(new java.awt.Dimension(93, 25));
        locateButton.setPreferredSize(null);
        locateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locateButtonActionPerformed(evt);
            }
        });
        playlistButtonPanel.add(locateButton);

        saveButton.setFont(new java.awt.Font("Verdana", 0, 9));
        saveButton.setText("Save Repaired List");
        saveButton.setFocusable(false);
        saveButton.setMinimumSize(new java.awt.Dimension(127, 25));
        saveButton.setPreferredSize(null);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        playlistButtonPanel.add(saveButton);

        playlistPanel.add(playlistButtonPanel, java.awt.BorderLayout.SOUTH);

        playlistTablePanel.setBackground(java.awt.Color.white);
        playlistTablePanel.setLayout(new java.awt.BorderLayout());

        playlistScrollPanel.setBackground(new java.awt.Color(255, 255, 255));
        playlistScrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        playlistScrollPanel.setOpaque(false);
        playlistScrollPanel.setPreferredSize(new java.awt.Dimension(600, 400));

        playlistTable.setFont(new java.awt.Font("Verdana", 0, 9));
        playlistTable.setModel(new CustomTableModel());
        playlistTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        playlistTable.setGridColor(new java.awt.Color(153, 153, 153));
        playlistTable.setIntercellSpacing(new java.awt.Dimension(1, 3));
        playlistTable.setMaximumSize(new java.awt.Dimension(32767, 32767));
        playlistTable.setMinimumSize(new java.awt.Dimension(5, 5));
        playlistTable.setPreferredSize(null);
        playlistTable.setRowHeight(20);
        playlistTable.setFillsViewportHeight(true);
        playlistTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlistTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playlistTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlistTableMouseReleased(evt);
            }
        });
        playlistTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                playlistTableMouseDragged(evt);
            }
        });
        playlistScrollPanel.setViewportView(playlistTable);

        playlistTablePanel.add(playlistScrollPanel, java.awt.BorderLayout.CENTER);

        playlistPanel.add(playlistTablePanel, java.awt.BorderLayout.CENTER);

        splitPane.setRightComponent(playlistPanel);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        mainMenuBar.setBorder(null);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
        fileMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        loadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        loadMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        loadMenuItem.setMnemonic('L');
        loadMenuItem.setText("Open Playlist");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openIconButtonActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        closeMenuItem.setMnemonic('C');
        closeMenuItem.setText("Close Playlist");
        closeMenuItem.setEnabled(false);
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        saveAsMenuItem.setMnemonic('V');
        saveAsMenuItem.setText("Save As");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        jSeparator3.setForeground(new java.awt.Color(102, 102, 153));
        fileMenu.add(jSeparator3);

        recentMenu.setText("Recent Playlists");
        recentMenu.setToolTipText("Recently Opened Playlists");
        recentMenu.setFont(new java.awt.Font("Verdana", 0, 9));
        fileMenu.add(recentMenu);

        clearHistoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        clearHistoryMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        clearHistoryMenuItem.setMnemonic('H');
        clearHistoryMenuItem.setText("Clear Playlist History");
        clearHistoryMenuItem.setToolTipText("");
        clearHistoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearHistoryMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(clearHistoryMenuItem);

        jSeparator1.setForeground(new java.awt.Color(102, 102, 153));
        fileMenu.add(jSeparator1);

        appOptionsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        appOptionsMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        appOptionsMenuItem.setMnemonic('H');
        appOptionsMenuItem.setText("Options...");
        appOptionsMenuItem.setToolTipText("");
        appOptionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appOptionsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(appOptionsMenuItem);

        jSeparator2.setForeground(new java.awt.Color(102, 102, 153));
        fileMenu.add(jSeparator2);

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exit.setFont(new java.awt.Font("Verdana", 0, 9));
        exit.setMnemonic('x');
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileMenu.add(exit);

        mainMenuBar.add(fileMenu);

        actionsMenu.setMnemonic('A');
        actionsMenu.setText("Actions");
        actionsMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        appendFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        appendFileMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        appendFileMenuItem.setMnemonic('A');
        appendFileMenuItem.setText("Append File");
        appendFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendFileMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(appendFileMenuItem);

        insertFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        insertFileMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        insertFileMenuItem.setMnemonic('I');
        insertFileMenuItem.setText("Insert File Below Selected Row");
        insertFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertFileMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(insertFileMenuItem);

        editFilenameMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        editFilenameMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        editFilenameMenuItem.setMnemonic('E');
        editFilenameMenuItem.setText("Edit Filename");
        editFilenameMenuItem.setEnabled(false);
        editFilenameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFilenameMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(editFilenameMenuItem);

        findClosestMatchesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        findClosestMatchesMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        findClosestMatchesMenuItem.setMnemonic('C');
        findClosestMatchesMenuItem.setText("Find Closest Matches");
        findClosestMatchesMenuItem.setEnabled(false);
        findClosestMatchesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findClosestMatchesMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(findClosestMatchesMenuItem);

        playFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        playFileMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        playFileMenuItem.setText("Play Selected Entry");
        playFileMenuItem.setEnabled(false);
        playFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playFileMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(playFileMenuItem);

        removeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        removeMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        removeMenuItem.setText("Remove");
        removeMenuItem.setEnabled(false);
        removeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(removeMenuItem);

        removeMissingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        removeMissingMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        removeMissingMenuItem.setMnemonic('R');
        removeMissingMenuItem.setText("Remove Missing Entries");
        removeMissingMenuItem.setEnabled(false);
        removeMissingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMissingMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(removeMissingMenuItem);

        removeDuplicatesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        removeDuplicatesMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        removeDuplicatesMenuItem.setMnemonic('m');
        removeDuplicatesMenuItem.setText("Remove Duplicates");
        removeDuplicatesMenuItem.setEnabled(false);
        removeDuplicatesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDuplicatesMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(removeDuplicatesMenuItem);

        replaceFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        replaceFileMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        replaceFileMenuItem.setMnemonic('I');
        replaceFileMenuItem.setText("Replace Selected Entry");
        replaceFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceFileMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(replaceFileMenuItem);

        jSeparator4.setForeground(new java.awt.Color(102, 102, 153));
        actionsMenu.add(jSeparator4);

        appendPlaylistMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        appendPlaylistMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        appendPlaylistMenuItem.setMnemonic('p');
        appendPlaylistMenuItem.setText("Append Playlist");
        appendPlaylistMenuItem.setEnabled(false);
        appendPlaylistMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendPlaylistMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(appendPlaylistMenuItem);

        insertPlaylistMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        insertPlaylistMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        insertPlaylistMenuItem.setMnemonic('n');
        insertPlaylistMenuItem.setText("Insert Playlist Below Selected Row");
        insertPlaylistMenuItem.setEnabled(false);
        insertPlaylistMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertPlaylistMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(insertPlaylistMenuItem);

        jSeparator5.setForeground(new java.awt.Color(102, 102, 153));
        actionsMenu.add(jSeparator5);

        copyToDirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyToDirMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        copyToDirMenuItem.setMnemonic('C');
        copyToDirMenuItem.setText("Copy Files to New Location");
        copyToDirMenuItem.setEnabled(false);
        copyToDirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyToDirMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(copyToDirMenuItem);

        mainMenuBar.add(actionsMenu);

        sortMenu.setMnemonic('S');
        sortMenu.setText("Sort");
        sortMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        randomizeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        randomizeMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        randomizeMenuItem.setMnemonic('R');
        randomizeMenuItem.setText("Randomize List");
        randomizeMenuItem.setEnabled(false);
        randomizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomizeMenuItemActionPerformed(evt);
            }
        });
        sortMenu.add(randomizeMenuItem);

        reverseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        reverseMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        reverseMenuItem.setMnemonic('e');
        reverseMenuItem.setText("Reverse");
        reverseMenuItem.setEnabled(false);
        reverseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reverseMenuItemActionPerformed(evt);
            }
        });
        sortMenu.add(reverseMenuItem);

        filenameSortMenu.setMnemonic('F');
        filenameSortMenu.setText("Sort By Filename");
        filenameSortMenu.setEnabled(false);
        filenameSortMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        ascendingFilenameMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        ascendingFilenameMenuItem.setMnemonic('A');
        ascendingFilenameMenuItem.setText("Ascending");
        ascendingFilenameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascendingFilenameMenuItemActionPerformed(evt);
            }
        });
        filenameSortMenu.add(ascendingFilenameMenuItem);

        descendingFilenameMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        descendingFilenameMenuItem.setMnemonic('D');
        descendingFilenameMenuItem.setText("Descending");
        descendingFilenameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descendingFilenameMenuItemActionPerformed(evt);
            }
        });
        filenameSortMenu.add(descendingFilenameMenuItem);

        sortMenu.add(filenameSortMenu);

        statusSortMenu.setMnemonic('S');
        statusSortMenu.setText("Sort by Status");
        statusSortMenu.setEnabled(false);
        statusSortMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        ascendingStatusMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        ascendingStatusMenuItem.setMnemonic('A');
        ascendingStatusMenuItem.setText("Ascending");
        ascendingStatusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascendingStatusMenuItemActionPerformed(evt);
            }
        });
        statusSortMenu.add(ascendingStatusMenuItem);

        descendingStatusMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        descendingStatusMenuItem.setMnemonic('D');
        descendingStatusMenuItem.setText("Descending");
        descendingStatusMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descendingStatusMenuItemActionPerformed(evt);
            }
        });
        statusSortMenu.add(descendingStatusMenuItem);

        sortMenu.add(statusSortMenu);

        pathSortMenu.setMnemonic('P');
        pathSortMenu.setText("Sort By Path");
        pathSortMenu.setEnabled(false);
        pathSortMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        ascendingPathMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        ascendingPathMenuItem.setMnemonic('A');
        ascendingPathMenuItem.setText("Ascending");
        ascendingPathMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascendingPathMenuItemActionPerformed(evt);
            }
        });
        pathSortMenu.add(ascendingPathMenuItem);

        descendingPathMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        descendingPathMenuItem.setMnemonic('D');
        descendingPathMenuItem.setText("Descending");
        descendingPathMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descendingPathMenuItemActionPerformed(evt);
            }
        });
        pathSortMenu.add(descendingPathMenuItem);

        sortMenu.add(pathSortMenu);

        mainMenuBar.add(sortMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");
        helpMenu.setFont(new java.awt.Font("Verdana", 0, 9));

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        aboutMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        helpMenuItem.setFont(new java.awt.Font("Verdana", 0, 9));
        helpMenuItem.setMnemonic('e');
        helpMenuItem.setText("Help");
        helpMenuItem.setEnabled(false);
        helpMenu.add(helpMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertFileMenuItemActionPerformed
        int response = jFileChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File fileToInsert = jFileChooser.getSelectedFile();
                if(playlistTable.getSelectedRowCount() != 0)
                {
                    int entryIndex = playlistTable.getSelectedRow();
                    ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.insertFile(fileToInsert, entryIndex) );
                    updateButtons();
                    updateStatusLabel();
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, file was not inserted.");
                e.printStackTrace();
            }
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
}//GEN-LAST:event_insertFileMenuItemActionPerformed

    private void insertPlaylistMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertPlaylistMenuItemActionPerformed
        int response = jM3UChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File m3uToInsert = jM3UChooser.getSelectedFile();
                if(playlistTable.getSelectedRowCount() != 0)
                {
                    int entryIndex = playlistTable.getSelectedRow();
                    ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.insertPlaylist(m3uToInsert, entryIndex) );
                    updateButtons();
                    updateStatusLabel();
                }
            }
			catch (UnsupportedPlaylistFormat upf)
			{
				JOptionPane.showMessageDialog(this, "Playlist was not in M3U format.  Please check the file and try again.");
                upf.printStackTrace();
			}
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, playlist was not inserted.");
                e.printStackTrace();
            }
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
    }//GEN-LAST:event_insertPlaylistMenuItemActionPerformed

    private void clearHistoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearHistoryMenuItemActionPerformed
        guiDriver.clearM3UHistory();
        updateRecentMenu();
    }//GEN-LAST:event_clearHistoryMenuItemActionPerformed

    private void removeDuplicatesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDuplicatesMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.removeDuplicates() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_removeDuplicatesMenuItemActionPerformed

    private void reverseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reverseMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.reversePlaylist() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_reverseMenuItemActionPerformed

    private void copyToDirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyToDirMenuItemActionPerformed
        int response = jCopyToDirChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File destDir = jCopyToDirChooser.getSelectedFile();                      
                copyFilesProgressDialog.go();
                CopyFilesTask thisTask = new CopyFilesTask(guiDriver.getEntries(), destDir);
                copyFilesProgressDialog.track(thisTask);
                guiDriver.copyFilesToNewNewDirectory(thisTask);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, 1 or more files were not copied.");
                e.printStackTrace();
            }
        }
        else
        {
            jMediaDirChooser.cancelSelection();
        }
        if (copyFilesProgressDialog.isEnabled())
        {                            
            copyFilesProgressDialog.setEnabled(false);
        }
    }//GEN-LAST:event_copyToDirMenuItemActionPerformed

    private void descendingPathMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendingPathMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.descendingPathSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_descendingPathMenuItemActionPerformed

    private void ascendingPathMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendingPathMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.ascendingPathSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_ascendingPathMenuItemActionPerformed

    private void descendingStatusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendingStatusMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.descendingStatusSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_descendingStatusMenuItemActionPerformed

    private void ascendingStatusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendingStatusMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.ascendingStatusSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_ascendingStatusMenuItemActionPerformed

    private void descendingFilenameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descendingFilenameMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.descendingFilenameSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_descendingFilenameMenuItemActionPerformed

    private void ascendingFilenameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascendingFilenameMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.ascendingFilenameSort() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_ascendingFilenameMenuItemActionPerformed

    private void removeMissingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMissingMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.removeMissing() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_removeMissingMenuItemActionPerformed

    private void randomizeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomizeMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.randomizePlaylist() );
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_randomizeMenuItemActionPerformed

    private void deleteRCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRCMenuItemActionPerformed
        deleteIconButtonActionPerformed(evt);
        updateStatusLabel();
}//GEN-LAST:event_deleteRCMenuItemActionPerformed

    private void closestMatchesRCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closestMatchesRCMenuItemActionPerformed
        findClosestMatchesMenuItemActionPerformed(evt);
        updateStatusLabel();
}//GEN-LAST:event_closestMatchesRCMenuItemActionPerformed

    private void editFileNameRCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFileNameRCMenuItemActionPerformed
        editFilenameMenuItemActionPerformed(evt);
        updateStatusLabel();
}//GEN-LAST:event_editFileNameRCMenuItemActionPerformed

    private void playlistTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistTableMouseClicked
        if ((evt.getModifiers() == MouseEvent.BUTTON2_MASK) || (evt.getModifiers() == MouseEvent.BUTTON3_MASK))
        {
            int releasedRow = playlistTable.rowAtPoint(evt.getPoint());    
            if ((currentRightClick == releasedRow) && (releasedRow != -1))
            {
                entryRightClickMenu.show(playlistTable, (int)evt.getPoint().getX(), (int)evt.getPoint().getY());
            }
        }
        updateButtons();        
        updateStatusLabel();
}//GEN-LAST:event_playlistTableMouseClicked

    private void playlistTableMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistTableMouseDragged
        if (evt.getModifiers() == MouseEvent.BUTTON1_MASK)
        {
            int releasedRow = playlistTable.rowAtPoint(evt.getPoint());    
            if ((currentlySelectedRow != releasedRow) && (releasedRow != -1) && (releasedRow < guiDriver.getPlaylist().getEntryCount()))
            {
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.moveTo(currentlySelectedRow, releasedRow) );
                currentlySelectedRow = releasedRow;
            }        
            updateStatusLabel();
        }
}//GEN-LAST:event_playlistTableMouseDragged

    private void findClosestMatchesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findClosestMatchesMenuItemActionPerformed
        locateProgressDialog.go();
        int row = playlistTable.getSelectedRow();
        PlaylistEntry entryToFind = guiDriver.getEntryAt(row);
        if (!entryToFind.isFound())
        {
            LocateClosestMatchesTask thisTask = new LocateClosestMatchesTask(entryToFind, guiDriver.getMediaLibraryFileList());
            locateProgressDialog.track(thisTask);
            Vector<MatchedPlaylistEntry> response = guiDriver.findClosestMatches(thisTask);
            locateProgressDialog.setEnabled(false);
            ClosestMatchChooserDialog tempDialog = new ClosestMatchChooserDialog(this, response, true);
            tempDialog.center();
            tempDialog.setVisible(true);
            if (tempDialog.getResultCode() == ClosestMatchChooserDialog.OK)
            {
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.updateEntryAt(row, ((MatchedPlaylistEntry)response.elementAt(tempDialog.getChoice())).getPlaylistFile() ) );
            }
            updateButtons();
            updateStatusLabel();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "The file you selected is already found in the location specified by your playlist.");
        }
    }//GEN-LAST:event_findClosestMatchesMenuItemActionPerformed

    private void editFilenameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFilenameMenuItemActionPerformed
        int row = playlistTable.getSelectedRow();
        EditFilenameResult response = EditFilenameDialog.showDialog(this, "Edit Filename", true, guiDriver.getEntryFileName(row));
        if (response.getResultCode() == EditFilenameDialog.OK)
        {
            try
            {
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.updateFileName(row, response.getFileName()) );
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, filename was not changed.");
                e.printStackTrace();
            }
            updateButtons();
            updateStatusLabel();
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
    }//GEN-LAST:event_editFilenameMenuItemActionPerformed

    private void appendPlaylistMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendPlaylistMenuItemActionPerformed
        int response = jM3UChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File m3uToAppend = jM3UChooser.getSelectedFile();
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.appendPlaylist(m3uToAppend) );                
            }
			catch (UnsupportedPlaylistFormat upf)
			{
				JOptionPane.showMessageDialog(this, "Playlist was not in M3U format.  Please check the file and try again.");
				upf.printStackTrace();
			}
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, playlist was not appended.");
                e.printStackTrace();
            }
            updateButtons();
            updateStatusLabel();
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
        updateStatusLabel();
    }//GEN-LAST:event_appendPlaylistMenuItemActionPerformed

    private void deleteIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteIconButtonActionPerformed
        if(playlistTable.getSelectedRowCount() != 0)
        {
            int entryIndex = playlistTable.getSelectedRow();
            ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.delete(entryIndex) );
            updateButtons();
            updateStatusLabel();
        }
    }//GEN-LAST:event_deleteIconButtonActionPerformed

    private void downIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downIconButtonActionPerformed
        if(playlistTable.getSelectedRowCount() != 0)
        {
            int entryIndex = playlistTable.getSelectedRow();            
            ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.moveDown(entryIndex) );
            playlistTable.changeSelection(entryIndex + 1, 0, false, false);
            updateButtons();
            updateStatusLabel();
        }
    }//GEN-LAST:event_downIconButtonActionPerformed

    private void upIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upIconButtonActionPerformed
        if(playlistTable.getSelectedRowCount() != 0)
        {
            int entryIndex = playlistTable.getSelectedRow();            
            ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.moveUp(entryIndex) );
            playlistTable.changeSelection(entryIndex - 1, 0, false, false);
            updateButtons();
            updateStatusLabel();
        }
    }//GEN-LAST:event_upIconButtonActionPerformed

    private void appendFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendFileMenuItemActionPerformed
        int response = jFileChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File fileToAppend = jFileChooser.getSelectedFile();
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.addEntry(fileToAppend) );                
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, file was not appended.");
                e.printStackTrace();
            }
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
        updateButtons(); 
        updateStatusLabel();
        updateTableSize();
}//GEN-LAST:event_appendFileMenuItemActionPerformed

    private void playlistTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistTableMousePressed
        if (evt.getModifiers() == MouseEvent.BUTTON1_MASK)
        {
            currentlySelectedRow = playlistTable.rowAtPoint(evt.getPoint());
        }
        else if ((evt.getModifiers() == MouseEvent.BUTTON2_MASK) || (evt.getModifiers() == MouseEvent.BUTTON3_MASK))
        {
            currentRightClick = playlistTable.rowAtPoint(evt.getPoint());
        }
        updateButtons();
        updateStatusLabel();
}//GEN-LAST:event_playlistTableMousePressed

    private void playlistTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlistTableMouseReleased
        if (evt.getModifiers() == java.awt.event.MouseEvent.BUTTON1_MASK)
        {
            int releasedRow = playlistTable.rowAtPoint(evt.getPoint());                
            // if this point is in a row different than where it was clicked, move the row...
            if ((currentlySelectedRow != releasedRow) && (releasedRow != -1))
            {
                ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.moveTo(currentlySelectedRow, releasedRow) ); 
                currentlySelectedRow = releasedRow;           
            }
        }
        updateButtons();        
        updateStatusLabel();
}//GEN-LAST:event_playlistTableMouseReleased

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        jSaveFileChooser.setSelectedFile(guiDriver.getPlaylist().getFile());
        int response = jSaveFileChooser.showSaveDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                File playlist = jSaveFileChooser.getSelectedFile();
                if (!playlist.getName().endsWith(".m3u"))
                {
                    playlist = new File(playlist.getPath() + ".m3u");
                }				
				PlaylistEntry.basePath = playlist.getParent();
                guiDriver.saveM3U(playlist);                
                guiDriver.setPlaylistFile(playlist);
				((CustomTableModel)playlistTable.getModel()).updateData(guiDriver.guiTableUpdate());
                updateRecentMenu();
                updateButtons();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            catch (Exception e)
            {            
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(this, "An error has occured, playlist could not be saved.");
                e.printStackTrace();
            }
        }
        else
        {
            jSaveFileChooser.cancelSelection();
        }
        updateStatusLabel();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.closePlaylist() );
        PlaylistEntry.basePath = "";
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void closeIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeIconButtonActionPerformed
        ((CustomTableModel)playlistTable.getModel()).updateData( guiDriver.closePlaylist() );
        PlaylistEntry.basePath = "";
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_closeIconButtonActionPerformed

    private void openIconButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openIconButtonActionPerformed
        if (guiDriver.getPlaylist() != null)
        {
            jM3UChooser.setSelectedFile(guiDriver.getPlaylist().getFile());
        }
        int response = jM3UChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
			openM3UProgressDialog.go();
            File playlist = jM3UChooser.getSelectedFile();
            OpenM3UTask thisTask = new OpenM3UTask(guiDriver, playlist); 
            try
            {
                PlaylistEntry.basePath = playlist.getParent();
                openM3UProgressDialog.track(thisTask);
                openM3UProgressDialog.setEnabled(false);
                ((CustomTableModel)playlistTable.getModel()).updateData(guiDriver.guiTableUpdate());
                updateRecentMenu();
                updateButtons();
                updateStatusLabel();
                this.initColumnSizes(playlistTable);
                if (guiDriver.getPlaylist().getFile() == null)
                {
                    JOptionPane.showMessageDialog(this, "An error has occured, playlist could not be opened.");
                }
                if (guiDriver.getAppOptions().getAutoLocateEntriesOnPlaylistLoad())
                {
                    locateButtonActionPerformed(evt);
                }
            }
            catch (Exception e)
            {
                PlaylistEntry.basePath = "";
                JOptionPane.showMessageDialog(this, "An error has occured, playlist could not be opened.");
                e.printStackTrace();
            }
			
			if (thisTask.notM3UFormat)
			{
				JOptionPane.showMessageDialog(this, "Playlist was not in M3U format.  Please check the file and try again.");
			}
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
    }//GEN-LAST:event_openIconButtonActionPerformed

    private void addMediaDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMediaDirButtonActionPerformed
        int response = jMediaDirChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {                
                UNCFile mediaDir = new UNCFile(jMediaDirChooser.getSelectedFile());
				if (guiDriver.getAppOptions().getAlwaysUseUNCPaths())
				{					
					if (mediaDir.onNetworkDrive())
					{
						mediaDir = new UNCFile(mediaDir.getUNCPath());
					}
				}
                String dir = mediaDir.getPath();     
                if (mediaDir != null)
                {
                    // first let's see if this is a subdirectory of any of the media directories already in the list, and error out if so...
                    if (ArrayFunctions.ContainsStringWithPrefix(guiDriver.getMediaDirs(), dir))
                    {                
                        JOptionPane.showMessageDialog(this, "The directory you attempted to add is a subdirectory of one already in your media library, no change was made.", "Reminder", JOptionPane.INFORMATION_MESSAGE);                        
                        return;
                    }
                    else
                    {
                        // Now check if any of the media directories is a subdirectory of the one we're adding and remove the media directory if so.                        
                        String[] dirsToCheck = guiDriver.getMediaDirs();
                        for (int i = 0; i < dirsToCheck.length; i++)
                        {
                            int matchCount = 0;
                            if (dirsToCheck[i].startsWith(dir))
                            {
                                if (matchCount == 0)
                                {
                                    JOptionPane.showMessageDialog(this, "One or more of your existing media directories is a subdirectory of the directory you just added.  These directories will be removed from your list automatically.", "Reminder", JOptionPane.INFORMATION_MESSAGE);                        
                                }
                                removeMediaDirByIndex(evt, i);
                                matchCount++;
                            }
                        }
                    }
                }
                updateMediaLibraryProgressDialog.go();          
                AddMediaDirectoryTask thisTask = new AddMediaDirectoryTask(dir, guiDriver);
                updateMediaLibraryProgressDialog.setBusyCursor(true);
                updateMediaLibraryProgressDialog.track(thisTask);
                updateMediaLibraryProgressDialog.setBusyCursor(false);
                updateMediaLibraryProgressDialog.setEnabled(false);
                mediaLibraryList.setListData(guiDriver.getMediaDirs());
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, media directory could not be added.");
                e.printStackTrace();
            }
        }
        else
        {
            jMediaDirChooser.cancelSelection();
        }
        updateButtons();    
}//GEN-LAST:event_addMediaDirButtonActionPerformed

    private void removeMediaDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMediaDirButtonActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try
        {
            mediaLibraryList.setListData(guiDriver.removeMediaDir((String)mediaLibraryList.getSelectedValue()));            
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        catch (Exception e)
        {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "An error has occured, files in the media directory you removed may not have been completely removed from the library.  Please refresh the library.");
            e.printStackTrace();
        }
        updateButtons();
    }//GEN-LAST:event_removeMediaDirButtonActionPerformed

    private void removeMediaDirByIndex(java.awt.event.ActionEvent evt, int index) {                                                     
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try
        {
            mediaLibraryList.setListData(guiDriver.removeMediaDir((String)mediaLibraryList.getModel().getElementAt(index)));            
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        catch (Exception e)
        {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "An error has occured, files in the media directory you removed may not have been completely removed from the library.  Please refresh the library.");
            e.printStackTrace();
        }
        updateButtons();
    }                        
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (guiDriver.getPlaylist() != null && guiDriver.getPlaylist().getFile() != null)
        {			
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			PlaylistEntry.basePath = guiDriver.getPlaylist().getFile().getParent();
            guiDriver.saveM3U();
            updateStatusLabel();
			updateButtons();
			((CustomTableModel)playlistTable.getModel()).updateData(guiDriver.guiTableUpdate());
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        else
        {
            saveAsMenuItemActionPerformed(evt);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "listFix( ) v1.5.1\nBy: Jeremy Caron (firewyre at users dot sourceforge dot net)", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void locateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locateButtonActionPerformed
        locateProgressDialog.go();
        LocateFilesTask thisTask = new LocateFilesTask(guiDriver.getEntries(), guiDriver.getMediaLibraryFileList());
        locateProgressDialog.track(thisTask);
        if(!guiDriver.getPlaylist().isListEmpty())
        {
            String[][] dataModel = guiDriver.locateFiles(thisTask);
            ((CustomTableModel)playlistTable.getModel()).updateData( dataModel );       
        }
        locateProgressDialog.setEnabled(false);
        updateButtons();
        updateStatusLabel();
    }//GEN-LAST:event_locateButtonActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void refreshMediaDirsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshMediaDirsButtonActionPerformed
        updateMediaLibraryProgressDialog.go();
        UpdateMediaLibraryTask thisTask = new UpdateMediaLibraryTask(guiDriver);
        updateMediaLibraryProgressDialog.setBusyCursor(true);
        updateMediaLibraryProgressDialog.track(thisTask);
        updateMediaLibraryProgressDialog.setBusyCursor(false);
}//GEN-LAST:event_refreshMediaDirsButtonActionPerformed

	private void refreshMediaDirs() 
	{                                                       
        updateMediaLibraryProgressDialog.go();
        UpdateMediaLibraryTask thisTask = new UpdateMediaLibraryTask(guiDriver);
        updateMediaLibraryProgressDialog.setBusyCursor(true);
        updateMediaLibraryProgressDialog.track(thisTask);
        updateMediaLibraryProgressDialog.setBusyCursor(false);
	} 
	
    private void openRCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openRCMenuItemActionPerformed
        int row = playlistTable.getSelectedRow();
        PlaylistEntry entryToPlay = guiDriver.getEntryAt(row);
        try
        {
            entryToPlay.play();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Could not open this playlist entry, error is as follows: \n\n" + e.toString());
        }
}//GEN-LAST:event_openRCMenuItemActionPerformed

    private void openPlaylistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPlaylistButtonActionPerformed
        guiDriver.playPlaylist();
}//GEN-LAST:event_openPlaylistButtonActionPerformed

    private void removeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMenuItemActionPerformed
        deleteIconButtonActionPerformed(evt);
    }//GEN-LAST:event_removeMenuItemActionPerformed

    private void playFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playFileMenuItemActionPerformed
        openRCMenuItemActionPerformed(evt);
}//GEN-LAST:event_playFileMenuItemActionPerformed

    private void replaceFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceFileMenuItemActionPerformed
        int response = jFileChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File fileToInsert = jFileChooser.getSelectedFile();
                if (playlistTable.getSelectedRowCount() != 0)
                {
                    int entryIndex = playlistTable.getSelectedRow();
                    ((CustomTableModel) playlistTable.getModel()).updateData(guiDriver.replaceFile(fileToInsert, entryIndex));
                    updateButtons();
                    updateStatusLabel();
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "An error has occured, file was not inserted.");
                e.printStackTrace();
            }
        }
        else
        {
            jM3UChooser.cancelSelection();
        }
}//GEN-LAST:event_replaceFileMenuItemActionPerformed

    private void replaceFileRCMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceFileRCMenuItemActionPerformed
        replaceFileMenuItemActionPerformed(evt);
}//GEN-LAST:event_replaceFileRCMenuItemActionPerformed

    private void appOptionsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appOptionsMenuItemActionPerformed
        AppOptionsDialog optDialog = new AppOptionsDialog(this, "listFix() options", true, guiDriver.getAppOptions());
        AppOptions options = optDialog.showDialog();
        guiDriver.setAppOptions(options);
        if (optDialog.getResultCode() == AppOptionsDialog.OK)
        {
            (new FileWriter()).writeIni(guiDriver.getMediaDirs(), guiDriver.getMediaLibraryDirectoryList(), guiDriver.getMediaLibraryFileList(), options);
        }
        guiDriver.getHistory().setCapacity(options.getMaxPlaylistHistoryEntries());
		if (options.getAlwaysUseUNCPaths())
		{
			guiDriver.switchMediaLibraryToUNCPaths();
			mediaLibraryList.setListData(guiDriver.getMediaDirs());
		}
        updateRecentMenu();
		this.setLookAndFeel(guiDriver.getAppOptions().getLookAndFeel());
}//GEN-LAST:event_appOptionsMenuItemActionPerformed

    private void updateButtons()
    {
        if (guiDriver.getPlaylist().getFile() == null)
        {
            closeMenuItem.setEnabled(false);
            closeIconButton.setEnabled(false);            
            appendPlaylistMenuItem.setEnabled(false);
            openPlaylistButton.setEnabled(false);
        }
        else if(guiDriver.getPlaylist().playlistModified())
        {
            closeMenuItem.setEnabled(true);
            closeIconButton.setEnabled(true);            
            appendPlaylistMenuItem.setEnabled(true);
			openPlaylistButton.setEnabled(false);
        }
        else
        {
            closeMenuItem.setEnabled(true);
            closeIconButton.setEnabled(true);            
            appendPlaylistMenuItem.setEnabled(true);
            openPlaylistButton.setEnabled(true);
        }
        if (guiDriver.getPlaylist().getEntryCount() == 0)
        {                        
            upIconButton.setEnabled(false);
            downIconButton.setEnabled(false);
            removeMenuItem.setEnabled(false);
            deleteIconButton.setEnabled(false);
            playFileMenuItem.setEnabled(false);
            deleteRCMenuItem.setEnabled(false);
            locateButton.setEnabled(false);
            randomizeMenuItem.setEnabled(false);
            reverseMenuItem.setEnabled(false);
            filenameSortMenu.setEnabled(false);
            statusSortMenu.setEnabled(false);
            pathSortMenu.setEnabled(false);
            removeMissingMenuItem.setEnabled(false);
            removeDuplicatesMenuItem.setEnabled(false);
            copyToDirMenuItem.setEnabled(false);
            editFileNameRCMenuItem.setEnabled(false);
            openRCMenuItem.setEnabled(false);
            closestMatchesRCMenuItem.setEnabled(false);
            findClosestMatchesMenuItem.setEnabled(false);
            insertFileMenuItem.setEnabled(false);
        }
        else
        {            
            upIconButton.setEnabled(true);
            downIconButton.setEnabled(true);
            if (playlistTable.getSelectedRow() == 0)
            {
                upIconButton.setEnabled(false);
            }
            if (playlistTable.getSelectedRow() == guiDriver.getPlaylist().getEntryCount() - 1)
            {
                downIconButton.setEnabled(false);
            }
            removeMenuItem.setEnabled(true);
            deleteIconButton.setEnabled(true);
            playFileMenuItem.setEnabled(true);
            deleteRCMenuItem.setEnabled(true);
            locateButton.setEnabled(true); 
            randomizeMenuItem.setEnabled(true);
            reverseMenuItem.setEnabled(true);
            filenameSortMenu.setEnabled(true);
            statusSortMenu.setEnabled(true);
            pathSortMenu.setEnabled(true);
            removeMissingMenuItem.setEnabled(true);
            removeDuplicatesMenuItem.setEnabled(true);
            copyToDirMenuItem.setEnabled(true);
            editFileNameRCMenuItem.setEnabled(true);
            openRCMenuItem.setEnabled(true);
            closestMatchesRCMenuItem.setEnabled(true);
            findClosestMatchesMenuItem.setEnabled(true);
            insertFileMenuItem.setEnabled(true);
        }
        if (playlistTable.getSelectedRowCount() == 0)
        {
            upIconButton.setEnabled(false);
            removeMenuItem.setEnabled(false);
            deleteIconButton.setEnabled(false);
            playFileMenuItem.setEnabled(false);
            deleteRCMenuItem.setEnabled(false);  
            editFileNameRCMenuItem.setEnabled(false);
            openRCMenuItem.setEnabled(false);
            closestMatchesRCMenuItem.setEnabled(false);
            findClosestMatchesMenuItem.setEnabled(false);
            insertPlaylistMenuItem.setEnabled(false);
            insertFileMenuItem.setEnabled(false);
            replaceFileMenuItem.setEnabled(false);
            replaceFileRCMenuItem.setEnabled(false);
        }
        else if ((playlistTable.getSelectedRowCount() != 0) && (guiDriver.getPlaylist().getEntryCount() != 0))
        {
            upIconButton.setEnabled(true);
            downIconButton.setEnabled(true);
            if (playlistTable.getSelectedRow() == 0)
            {
                upIconButton.setEnabled(false);
            }
            if (playlistTable.getSelectedRow() == guiDriver.getPlaylist().getEntryCount() - 1)
            {
                downIconButton.setEnabled(false);
            }
            removeMenuItem.setEnabled(true);
            deleteRCMenuItem.setEnabled(true);
            deleteIconButton.setEnabled(true);
            playFileMenuItem.setEnabled(true);
            if (!guiDriver.getEntryAt(playlistTable.getSelectedRow()).isURL())
            {
                editFileNameRCMenuItem.setEnabled(true);
                editFilenameMenuItem.setEnabled(true);
                closestMatchesRCMenuItem.setEnabled(true);
                findClosestMatchesMenuItem.setEnabled(true);
            }
            else
            {
                editFileNameRCMenuItem.setEnabled(false);
                editFilenameMenuItem.setEnabled(false);
                closestMatchesRCMenuItem.setEnabled(false);
                findClosestMatchesMenuItem.setEnabled(false);
            }
            openRCMenuItem.setEnabled(true);
            insertPlaylistMenuItem.setEnabled(true);            
            insertFileMenuItem.setEnabled(true);
            replaceFileMenuItem.setEnabled(true);
            replaceFileRCMenuItem.setEnabled(true);
        }                
        if (mediaLibraryList.getModel().getSize() == 0)
        {
            removeMediaDirButton.setEnabled(false);
            refreshMediaDirsButton.setEnabled(false);
        }
        else if (mediaLibraryList.getModel().getSize() != 0)
        {
            removeMediaDirButton.setEnabled(true);
            refreshMediaDirsButton.setEnabled(true);
        }
    }
    
    private void updateStatusLabel()
    {
        String filename = guiDriver.getPlaylist().getFilename();
        if ( !filename.equals("") )
        {
            statusLabel.setText("Currently Open: " + filename + (guiDriver.getPlaylist().playlistModified() ? "*" : "" ) + "     Number of entries in list: " + guiDriver.getPlaylist().getEntryCount() + "     Number of lost entries: " + guiDriver.getPlaylist().getLostEntryCount() + "     Number of URLs: " + guiDriver.getPlaylist().getURLCount());            
        }
        else if ( filename.equals(""))
        {
            statusLabel.setText("Untitled List     Number of entries in list: " + guiDriver.getPlaylist().getEntryCount() + "     Number of lost entries: " + guiDriver.getPlaylist().getLostEntryCount() + "     Number of URLs: " + guiDriver.getPlaylist().getURLCount());
        }
    }
    
    private void updateTableSize()
    {
        if (playlistTable.getSize().height < playlistTablePanel.getSize().height)
        {
            playlistTable.setSize(new java.awt.Dimension(playlistScrollPanel.getSize().width, playlistScrollPanel.getSize().height - 20));
            playlistTable.revalidate();
        }
        else
        {
            playlistTable.setSize(new java.awt.Dimension(0, 0));
            playlistTable.revalidate();
        }
    }
    
    private void updateRecentMenu()
    {
        recentMenu.removeAll();
        String[] files = guiDriver.getRecentM3Us();
        if (files.length == 0)
        {
            JMenuItem temp = new JMenuItem("Empty");
            temp.setEnabled(false);
            recentMenu.add(temp);
        }
        else
        {
            for (int i = 0; i < files.length; i++)
            {
                JMenuItem temp = new JMenuItem(files[i]);
                temp.setFont(new java.awt.Font("Verdana", 0, 9));
                temp.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(java.awt.event.ActionEvent evt)
                    {
                        recentPlaylistActionPerformed(evt);
                    }
                });
                recentMenu.add(temp);
            }
        }
    }
    
    private void recentPlaylistActionPerformed(java.awt.event.ActionEvent evt)
    {
        JMenuItem temp = (JMenuItem)evt.getSource();
		openM3UProgressDialog.go();
        File playlist = new File(temp.getText());
        OpenM3UTask thisTask = new OpenM3UTask(guiDriver, playlist);
        try
        {
            PlaylistEntry.basePath = playlist.getParent();
            openM3UProgressDialog.track(thisTask);
            openM3UProgressDialog.setEnabled(false);
            ((CustomTableModel) playlistTable.getModel()).updateData(guiDriver.guiTableUpdate());
            updateRecentMenu();
            updateButtons();
            updateStatusLabel();
            this.initColumnSizes(playlistTable);
            if (guiDriver.getAppOptions().getAutoLocateEntriesOnPlaylistLoad())
            {
                locateButtonActionPerformed(evt);
            }
        }
        catch (Exception e)
        {
            // eat the error and continue
            PlaylistEntry.basePath = "";
            JOptionPane.showMessageDialog(this, "An error has occured, playlist could not be opened.");
            e.printStackTrace();
        }
		
		if (thisTask.notM3UFormat)
		{
			JOptionPane.showMessageDialog(this, "Playlist was not in M3U format.  Please check the file and try again.");
        }
    }

    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
            SwingUtilities.updateComponentTreeUI(jM3UChooser);
            SwingUtilities.updateComponentTreeUI(jFileChooser);
            SwingUtilities.updateComponentTreeUI(jMediaDirChooser);
            SwingUtilities.updateComponentTreeUI(jCopyToDirChooser);
            SwingUtilities.updateComponentTreeUI(jSaveFileChooser);
            SwingUtilities.updateComponentTreeUI(locateProgressDialog);
            SwingUtilities.updateComponentTreeUI(copyFilesProgressDialog);
            SwingUtilities.updateComponentTreeUI(updateMediaLibraryProgressDialog);
            SwingUtilities.updateComponentTreeUI(openM3UProgressDialog);
            SwingUtilities.updateComponentTreeUI(entryRightClickMenu);
        }
        catch (ClassNotFoundException e)
        {
        }
        catch (InstantiationException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }
        catch (UnsupportedLookAndFeelException e)
        {
        }
    }
    
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) 
    {
        GUIScreen mainWindow = new GUIScreen();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension labelSize = mainWindow.getPreferredSize();
        mainWindow.setLocation(screenSize.width/2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));
		mainWindow.setVisible(true);
		if (mainWindow.getOptions().getAutoRefreshMediaLibraryOnStartup())
		{
			mainWindow.refreshMediaDirs();
		}
	}
    
    private void initColumnSizes(JTable table) 
    {
        CustomTableModel model = (CustomTableModel) table.getModel();
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longestValues();
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        while(columns.hasMoreElements())
        {
            TableColumn column = columns.nextElement();            
            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);            
            headerWidth = comp.getPreferredSize().width;
            if ( ((String)column.getHeaderValue()).equalsIgnoreCase("status") )
            {
                comp = table.getDefaultRenderer(model.getColumnClass(1)).getTableCellRendererComponent(table, longValues[1], false, false, 0, 1);
            }
            else if ( ((String)column.getHeaderValue()).equalsIgnoreCase("file name") )
            {
                comp = table.getDefaultRenderer(model.getColumnClass(0)).getTableCellRendererComponent(table, longValues[0], false, false, 0, 0);
            }
            else
            {
                comp = table.getDefaultRenderer(model.getColumnClass(2)).getTableCellRendererComponent(table, longValues[2], false, false, 0, 2);
            }            
            cellWidth = comp.getPreferredSize().width;            
            if ( ((String)column.getHeaderValue()).equalsIgnoreCase("status") )
            {
                column.setPreferredWidth(Math.max(headerWidth, cellWidth) + 40);
            }
            else
            {
                column.setPreferredWidth(Math.max(headerWidth, cellWidth)); 
            }   
        }
    }

// <editor-fold defaultstate="collapsed" desc="Generated Code">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu actionsMenu;
    private javax.swing.JButton addMediaDirButton;
    private javax.swing.JMenuItem appOptionsMenuItem;
    private javax.swing.JMenuItem appendFileMenuItem;
    private javax.swing.JMenuItem appendPlaylistMenuItem;
    private javax.swing.JMenuItem ascendingFilenameMenuItem;
    private javax.swing.JMenuItem ascendingPathMenuItem;
    private javax.swing.JMenuItem ascendingStatusMenuItem;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JMenuItem clearHistoryMenuItem;
    private javax.swing.JButton closeIconButton;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenuItem closestMatchesRCMenuItem;
    private javax.swing.JMenuItem copyToDirMenuItem;
    private javax.swing.JButton deleteIconButton;
    private javax.swing.JMenuItem deleteRCMenuItem;
    private javax.swing.JMenuItem descendingFilenameMenuItem;
    private javax.swing.JMenuItem descendingPathMenuItem;
    private javax.swing.JMenuItem descendingStatusMenuItem;
    private javax.swing.JButton downIconButton;
    private javax.swing.JMenuItem editFileNameRCMenuItem;
    private javax.swing.JMenuItem editFilenameMenuItem;
    private javax.swing.JPopupMenu entryRightClickMenu;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu filenameSortMenu;
    private javax.swing.JMenuItem findClosestMatchesMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem insertFileMenuItem;
    private javax.swing.JMenuItem insertPlaylistMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JButton locateButton;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mediaLibraryButtonPanel;
    private javax.swing.JList mediaLibraryList;
    private javax.swing.JPanel mediaLibraryPanel;
    private javax.swing.JScrollPane mediaLibraryScrollPane;
    private javax.swing.JButton openIconButton;
    private javax.swing.JButton openPlaylistButton;
    private javax.swing.JMenuItem openRCMenuItem;
    private javax.swing.JMenu pathSortMenu;
    private javax.swing.JMenuItem playFileMenuItem;
    private javax.swing.JPanel playlistButtonPanel;
    private javax.swing.JPanel playlistPanel;
    private javax.swing.JScrollPane playlistScrollPanel;
    private javax.swing.JTable playlistTable;
    private javax.swing.JPanel playlistTablePanel;
    private javax.swing.JMenuItem randomizeMenuItem;
    private javax.swing.JMenu recentMenu;
    private javax.swing.JButton refreshMediaDirsButton;
    private javax.swing.JMenuItem removeDuplicatesMenuItem;
    private javax.swing.JButton removeMediaDirButton;
    private javax.swing.JMenuItem removeMenuItem;
    private javax.swing.JMenuItem removeMissingMenuItem;
    private javax.swing.JMenuItem replaceFileMenuItem;
    private javax.swing.JMenuItem replaceFileRCMenuItem;
    private javax.swing.JMenuItem reverseMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveIconButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenu sortMenu;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenu statusSortMenu;
    private javax.swing.JButton upIconButton;
    // End of variables declaration//GEN-END:variables
// </editor-fold>
}
