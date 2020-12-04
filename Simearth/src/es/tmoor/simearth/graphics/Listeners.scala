package es.tmoor.simearth.graphics

import java.awt.event.WindowEvent
import java.awt.event.ActionEvent
import java.awt.AWTEvent
import java.awt.event.AdjustmentEvent
import java.awt.event.MouseEvent
import java.awt.event.FocusEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeEvent

class CloseListener extends java.awt.event.WindowListener{

  override def windowOpened(x: WindowEvent): Unit = println(x)

  override def windowClosing(x: WindowEvent): Unit = sys.exit(0)

  override def windowClosed(x: WindowEvent): Unit = sys.exit(0)

  override def windowIconified(x: WindowEvent): Unit = println(x)

  override def windowDeiconified(x: WindowEvent): Unit = println(x)

  override def windowActivated(x: WindowEvent): Unit = println(x)

  override def windowDeactivated(x: WindowEvent): Unit = println(x)
}