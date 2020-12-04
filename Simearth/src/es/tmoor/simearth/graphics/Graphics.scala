package es.tmoor.simearth.graphics

import java.awt.{Component, Graphics, Graphics2D}

class SimGraphics extends Component {
  override def paint(g: Graphics): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]

    val x = 10
    val y = 10
    val w = getSize.width / 2
    val h = getSize.height / 2
    
    g2d.drawLine(x,y,w,h)
  }
}