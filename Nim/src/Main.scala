package re._1r
package nim
package desktop

import core._
import sgl.{InputHelpersComponent, GameLoopStatisticsComponent}
import sgl.awt._
import sgl.awt.util._

object Main extends AWTApp with AbstractApp
  with InputHelpersComponent with VerboseStdErrLoggingProvider {

  override val TargetFps = Some(144)

  override val frameDimension = (TotalWidth, TotalHeight)

}
