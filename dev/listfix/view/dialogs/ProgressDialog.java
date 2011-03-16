/*
 * listFix() - Fix Broken Playlists!
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

package listfix.view.dialogs;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import listfix.view.support.ProgressWorker;

public class ProgressDialog extends javax.swing.JDialog
{
	public ProgressDialog(java.awt.Frame parent, boolean modal, ProgressWorker worker, String label)
	{
		this(parent, modal, worker, label, false);
	}

    public ProgressDialog(java.awt.Frame parent, boolean modal, ProgressWorker worker, String label, boolean textMode)
    {
        super(parent, modal);
        initComponents();

        Dimension sz = getSize();
        sz.width = 400;
        setSize(sz);

        initWorker(worker);

        _progressTitle.setText(label);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                _worker.execute();
            }
        });

		if (textMode)
		{
			_progressBar.setVisible(false);
			_progressMessage.setVisible(true);

			sz.width = 500;
			sz.height = 120;
			setSize(sz);
		}
		else
		{
			_progressBar.setVisible(true);
			_progressMessage.setVisible(false);
		}

        setLocationRelativeTo(parent);
    }

    public JProgressBar getProgressBar()
    {
        return _progressBar;
    }

    public JLabel getProgressLabel()
    {
        return _progressTitle;
    }
    
    private void initWorker(ProgressWorker worker)
    {
        _worker = worker;
        PropertyChangeSupport pcs = _worker.getPropertyChangeSupport();
        
        // update progress bar when progress changes
        pcs.addPropertyChangeListener("progress", new PropertyChangeListener() 
        {
            public void propertyChange(PropertyChangeEvent evt)
            {
                int progress = (Integer)evt.getNewValue();
                _progressBar.setValue(progress);
            }
        });

		// Update progress message when message changes
		pcs.addPropertyChangeListener("message", new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent evt)
            {
                _progressMessage.setText(_worker.getMessage());
            }
        });
        
        // close dialog when state changes to done
        pcs.addPropertyChangeListener("state", new PropertyChangeListener() 
        {
            public void propertyChange(PropertyChangeEvent evt)
            {
                if (_worker.isDone())
                {
                    setVisible(false);
                }
            }
        });
        
    }

    ProgressWorker _worker;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        _progressTitle = new javax.swing.JLabel();
        _progressBar = new javax.swing.JProgressBar();
        _progressMessage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        _cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(270, 45));
        jPanel1.setPreferredSize(new java.awt.Dimension(166, 45));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        _progressTitle.setText("Title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 4, 10);
        jPanel1.add(_progressTitle, gridBagConstraints);

        _progressBar.setMinimumSize(new java.awt.Dimension(250, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel1.add(_progressBar, gridBagConstraints);

        _progressMessage.setText("Message");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 4, 10);
        jPanel1.add(_progressMessage, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setMinimumSize(new java.awt.Dimension(77, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(77, 30));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 0));

        _cancelButton.setText("Cancel");
        _cancelButton.setAlignmentY(-5.0F);
        _cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _cancelButtonActionPerformed(evt);
            }
        });
        _cancelButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                _cancelButtonKeyPressed(evt);
            }
        });
        jPanel2.add(_cancelButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void _cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__cancelButtonActionPerformed
	{//GEN-HEADEREND:event__cancelButtonActionPerformed
		_worker.cancel(true);
	}//GEN-LAST:event__cancelButtonActionPerformed

	private void _cancelButtonKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event__cancelButtonKeyPressed
	{//GEN-HEADEREND:event__cancelButtonKeyPressed
		if (evt.getKeyCode() == evt.VK_ESCAPE)
		{
			_worker.cancel(true);
		}
	}//GEN-LAST:event__cancelButtonKeyPressed

	private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
	{//GEN-HEADEREND:event_formWindowClosing
		_worker.cancel(true);
	}//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _cancelButton;
    private javax.swing.JProgressBar _progressBar;
    private javax.swing.JLabel _progressMessage;
    private javax.swing.JLabel _progressTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
