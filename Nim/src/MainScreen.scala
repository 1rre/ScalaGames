package re._1r
package nim
package core

import scala.annotation.meta.param
import scala.io.Source._
import scala.io.BufferedSource
import scala.util.Properties.userDir
import sgl._
import geometry._
import scene._
import util._
import java.awt.Font._
import ai._


trait MainScreenComponent {
	self: GraphicsProvider with InputProvider with GameStateComponent with WindowProvider
	with InputHelpersComponent with LoggingProvider with PartsResourcePathProvider with SystemProvider =>
	import Graphics._
	implicit def filestring(s:String):java.io.File = new java.io.File(s)
	override val ResourcesRoot = PartsResourcePath(Vector("~", "Documents", "Games"))
	val TotalWidth = 1067
	val TotalHeight = 600
	var wins = (0,0)
	private implicit val LogTag = Logger.Tag("main-screen")
	class MainScreen extends GameScreen{
		var whoseGo = true //t for computer, f for player
		val rand = scala.util.Random
		var pencils = 20
		def win = {
			if(whoseGo){
				println("Player 1 wins")
				wins = (wins._1 +  1, wins._2)
			}
			else{
				println("Player 2 wins")
				wins = (wins._1, wins._2 + 1)
			}
			gameState.newScreen(new MainScreen())
		}
		override def name:String = "Nim"
		var v = pencils
		def done = (v != pencils)
		override def update(dt: Long): Unit = {
			if(whoseGo){ //Computer's turn
				pencils -= 1 + rand.nextInt(3)
				println(pencils + "remain")
				whoseGo = !whoseGo
				v = pencils
			}
			else{
				pencils -= 1 + rand.nextInt(3)
				println(pencils + "remain")
				whoseGo = !whoseGo
				v = pencils
			}
			if(pencils <= 0) win
		}
		override def render(canvas: Graphics.Canvas): Unit = {
			canvas.drawColor(Color.White)
			var offset = 33
			for(i <- 0 until pencils){
				canvas.drawRect(offset,100,40,400,defaultPaint)
				offset += 50
			}
		}
	}
}
