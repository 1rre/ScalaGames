package re._1r
package _2048
package core

import sgl._
import util._

trait AbstractApp extends MainScreenComponent with PartsResourcePathProvider {
  this: GameApp with InputHelpersComponent =>

  override def startingScreen: GameScreen = new MainScreen

}