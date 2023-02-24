

package listfix.view.dialogs;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DualProgressDialog extends javax.swing.JDialog
{
  /** Creates new form DualProgressDialog
   */
  public DualProgressDialog(java.awt.Frame parent, String title, boolean modal)
  {
    super(parent, title, modal);
    initComponents();
  }

  public DualProgressDialog(java.awt.Frame parent, String title, String taskMsg, String overallMsg)
  {
    this(parent, title, true);

    _taskLabel.setText(taskMsg);

    _overallLabel.setText(overallMsg);

    pack();

    setSize(400, getHeight());
    setLocationRelativeTo(parent);

    addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowOpened(WindowEvent e)
      {
        _worker.execute();
      }
    });
  }

  /**
   *
   * @param worker
   */
  public void show(SwingWorker worker)
  {
    _worker = worker;

    PropertyChangeSupport pcs = _worker.getPropertyChangeSupport();

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


    setVisible(true);
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        _middlePanel = new javax.swing.JPanel();
        _taskLabel = new javax.swing.JLabel();
        _taskProgress = new javax.swing.JProgressBar();
        _overallLabel = new javax.swing.JLabel();
        _overallProgress = new javax.swing.JProgressBar();
        _bottomPanel = new javax.swing.JPanel();
        _cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        _middlePanel.setLayout(new java.awt.GridBagLayout());

        _taskLabel.setText("Task Progress");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 4, 10);
        _middlePanel.add(_taskLabel, gridBagConstraints);

        _taskProgress.setMinimumSize(new java.awt.Dimension(250, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        _middlePanel.add(_taskProgress, gridBagConstraints);

        _overallLabel.setText("Overall Progress");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 4, 10);
        _middlePanel.add(_overallLabel, gridBagConstraints);

        _overallProgress.setMinimumSize(new java.awt.Dimension(250, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        _middlePanel.add(_overallProgress, gridBagConstraints);

        getContentPane().add(_middlePanel, java.awt.BorderLayout.CENTER);

        _bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        _cancelButton.setText("Cancel");
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
        _bottomPanel.add(_cancelButton);

        getContentPane().add(_bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void _cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event__cancelButtonActionPerformed
  {//GEN-HEADEREND:event__cancelButtonActionPerformed
    _worker.cancel(true);
  }//GEN-LAST:event__cancelButtonActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
  {//GEN-HEADEREND:event_formWindowClosing
    _worker.cancel(true);
  }//GEN-LAST:event_formWindowClosing

  private void _cancelButtonKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event__cancelButtonKeyPressed
  {//GEN-HEADEREND:event__cancelButtonKeyPressed
    if (evt.getKeyCode() == evt.VK_ESCAPE)
    {
      _worker.cancel(true);
    }
  }//GEN-LAST:event__cancelButtonKeyPressed

  /**
   *
   * @return
   */
  public JLabel getTaskLabel()
  {
    return _taskLabel;
  }

  public JProgressBar getTaskProgressBar()
  {
    return _taskProgress;
  }

  public JLabel getOverallLabel()
  {
    return _overallLabel;
  }

  public JProgressBar getOverallProgressBar()
  {
    return _overallProgress;
  }

  SwingWorker _worker;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel _bottomPanel;
    private javax.swing.JButton _cancelButton;
    private javax.swing.JPanel _middlePanel;
    private javax.swing.JLabel _overallLabel;
    private javax.swing.JProgressBar _overallProgress;
    private javax.swing.JLabel _taskLabel;
    private javax.swing.JProgressBar _taskProgress;
    // End of variables declaration//GEN-END:variables
}
