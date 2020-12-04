package re._1r
package nim
package core

import sgl._
import util._

trait AbstractApp extends MainScreenComponent with PartsResourcePathProvider {
  this: GameApp with InputHelpersComponent =>

  override def startingScreen: GameScreen = new MainScreen

}