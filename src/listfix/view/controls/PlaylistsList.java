/*
 *  listFix() - Fix Broken Playlists!
 *  Copyright (C) 2001-2014 Jeremy Caron
 * 
 *  This file is part of listFix().
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, please see http://www.gnu.org/licenses/
 */

/*
 * PlaylistsList.java
 *
 * Created on Apr 2, 2011, 12:11:40 PM
 */

package listfix.view.controls;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import listfix.model.BatchRepair;
import listfix.model.BatchRepairItem;
import listfix.model.playlists.Playlist;
import listfix.util.ExStack;
import listfix.view.dialogs.PlaylistsTableModel;
import listfix.view.support.ZebraJTable;
import org.apache.log4j.Logger;

/**
 *
 * @author jcaron
 */
public class PlaylistsList extends javax.swing.JPanel
{
	private BatchRepair _batch;

	private static final Logger _logger = Logger.getLogger(PlaylistsList.class);

    /** Creates new form PlaylistsList */
    public PlaylistsList()
	{
		initComponents();
    }

	/**
	 *
	 * @param batch
	 */
	public PlaylistsList(BatchRepair batch)
	{
		_batch = batch;
		initComponents();

		_uiLists.setShowHorizontalLines(false);
		_uiLists.setShowVerticalLines(false);

		_uiLists.initFillColumnForScrollPane(_uiScrollLists);	

		// selections
		_uiLists.setColumnSelectionAllowed(false);
		_uiLists.setCellSelectionEnabled(false);
		_uiLists.setRowSelectionAllowed(true);
	}
	
	private ZebraJTable createTable()
	{
		return new ZebraJTable()
		{
			@Override
			public String getToolTipText(MouseEvent event)
			{
				Point point = event.getPoint();
				int rawRowIx = rowAtPoint(point);
				int rawColIx = columnAtPoint(point);
				if (rawRowIx >= 0 && rawColIx >= 0)
				{
					int rowIx = convertRowIndexToModel(rawRowIx);
					int colIx = convertColumnIndexToModel(rawColIx);
					if (rowIx >= 0 && rowIx < _batch.getItems().size() && (colIx == 1))
					{
						return _batch.getItem(rowIx).getPath();
					}
				}
				return super.getToolTipText(event);
			}
		};
	}

	/**
	 *
	 */
	public void initPlaylistsList()
	{
		resizeAllColumns();
		
		// sort playlists by filename
		RowSorter sorter = _uiLists.getRowSorter();
		List<RowSorter.SortKey> keys = new ArrayList<>();
		keys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sorter.setSortKeys(keys);
	}
	
	private void resizeAllColumns()
	{
		// resize columns to fit
		int cwidth = 0;
		cwidth += _uiLists.autoResizeColumn(0, true);
		cwidth += _uiLists.autoResizeColumn(1, false, 160);
		TableColumnModel cm = _uiLists.getColumnModel();
		TableCellRenderer renderer = _uiLists.getDefaultRenderer(Integer.class);
		Component comp = renderer.getTableCellRendererComponent(_uiLists, (_uiLists.getRowCount() + 1) * 10, false, false, 0, 0);
		int width = comp.getPreferredSize().width;
		TableColumn col = cm.getColumn(0);
		col.setMinWidth(width);
		col.setMaxWidth(width);
		col.setPreferredWidth(width);
		cwidth += width;
	}

	/**
	 *
	 * @return
	 */
	public ListSelectionModel getSelectionModel()
	{
		return _uiLists.getSelectionModel();
	}

	/**
	 *
	 * @return
	 */
	public int getSelectedModelRow()
	{
		return _uiLists.getSelectedRow() < 0 ? -1 : _uiLists.convertRowIndexToModel(_uiLists.getSelectedRow());
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        _labListCount = new javax.swing.JLabel();
        _uiScrollLists = new javax.swing.JScrollPane();
        _uiLists = _uiLists = createTable();

        setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Playlists");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel5.add(jLabel2, gridBagConstraints);

        _labListCount.setForeground(javax.swing.UIManager.getDefaults().getColor("controlShadow"));
        _labListCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        _labListCount.setText("0 lists");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel5.add(_labListCount, gridBagConstraints);

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        _uiScrollLists.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        _uiLists.setAutoCreateRowSorter(true);
        _uiLists.setModel(new PlaylistsTableModel(_batch));
        _uiLists.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        _uiLists.setRowHeight(20);
        _uiLists.getTableHeader().setReorderingAllowed(false);
        _uiScrollLists.setViewportView(_uiLists);

        add(_uiScrollLists, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel _labListCount;
    private listfix.view.support.ZebraJTable _uiLists;
    private javax.swing.JScrollPane _uiScrollLists;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables

	/**
	 *
	 * @param listCountTxt
	 */
	public void setText(String listCountTxt)
	{
		_labListCount.setText(listCountTxt);
	}

	/**
	 *
	 * @param list
	 */
	public void playlistModified(Playlist list)
	{
		int uiIndex = _uiLists.getSelectedRow();
		int selIx = _uiLists.getSelectedRow();
		if (selIx >= 0)
		{
			selIx = _uiLists.convertRowIndexToModel(selIx);
			BatchRepairItem item = _batch.getItem(selIx);
			try
			{
				((PlaylistsTableModel) _uiLists.getModel()).fireTableDataChanged();
			}
			catch (Exception e)
			{
				_logger.warn(ExStack.toString(e));
			}
			_uiLists.setRowSelectionInterval(uiIndex, uiIndex);
		}
	}

	/**
	 *
	 */
	public void anchorLeft()
	{
		_uiScrollLists.getHorizontalScrollBar().setValue(0);
	}
}
