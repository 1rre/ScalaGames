package es.tmoor.simearth.graphics

import java.awt.Frame

object SimFrame {

}

class SimFrame extends Frame {
  setName("SimEarth")
  setTitle("SimEarth")
  setSize(600,600)
  add(new SimGraphics)
  addWindowListener(new CloseListener)
  setVisible(true)
}