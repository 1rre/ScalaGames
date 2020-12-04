package re._1r
package _2048
package core

import scala.annotation.meta.param
import smile.math.matrix.matrix
import sgl._
import geometry._
import scene._
import util._
import scala.util.Properties.userDir
import java.awt.Font._
import io.Source._
import io.BufferedSource


trait MainScreenComponent {
	self: GraphicsProvider with InputProvider with GameStateComponent with WindowProvider
	with InputHelpersComponent with LoggingProvider with PartsResourcePathProvider with SystemProvider =>
	import Graphics._
	implicit def filestring(s:String):java.io.File = new java.io.File(s)
  override val ResourcesRoot = PartsResourcePath(Vector("~", "Documents", "Games"))
	val NRows = 4
	val NCols = 4
	val SqSize = 200
	val TotalWidth = NCols*SqSize
	val TotalHeight = NRows*SqSize
	private implicit val LogTag = Logger.Tag("main-screen")
	class MainScreen extends GameScreen{
		var rand = scala.util.Random
		override def name:String = "2048"
		var score = 0d
		var blocks = matrix(4,4,0d)
		addBlock
		println("added1st")
		val Up     = (true, true)
    val Down   = (true, false)
    val Left   = (false, true)
		val Right  = (false, false)
		val f = createFont(TRUETYPE_FONT,userDir + "/_2048/assets/font.ttf")
		val textCol = {
			defaultPaint.withColor(Color.rgb(106,103,97))
				.withFont(Font.create(f.getFamily,Font.Normal,30))
				.withAlignment(Alignments.Center)
		}
		val blockCol = defaultPaint.withColor(Color.rgb(189,148,91))
		def gameOver:Unit = {
			println("Game over\n" + "Your score was: " + score.toString)
		}
		def addBlock = {
			val blockVal = (rand.between(1,2.25).floor.toInt) * 2 //Add a rand with a 25% chance of 4 & 75% chance of 1
			val cCnt = blocks.data.count(_ > 0)
			while(blocks.data.count(_ > 0) == cCnt){
				val potLoc = (rand.between(0,4), rand.between(0,4))
				if(blocks(potLoc._1, potLoc._2) == 0d){
					blocks(potLoc._1, potLoc._2) = blockVal
				}
			}
		}
		def move(movementDir:(Boolean, Boolean)) = {
			val temp = blocks.toString(true)
			if(movementDir._1){
				blocks = blocks.t
			}
			if(movementDir._2){
				blocks = matrix(moveDown(blocks.toArray.reverse).reverse)
			}
			else{
				blocks = matrix(moveDown(blocks.toArray))
			}
			if(movementDir._1){
				blocks = blocks.t
			}
			if(temp != blocks.toString(true)) addBlock
		}
		def moveDown(mat:Array[Array[Double]]):Array[Array[Double]] = {
			var movement:Boolean = false
			do{
				movement = false
				mat.zipWithIndex.init.foreach((row) =>
					row._1.zipWithIndex.foreach((index) =>
						if(mat(row._2 + 1)(index._2) == 0d && mat(row._2)(index._2) != 0d){
							mat(row._2 + 1)(index._2) = index._1
							mat(row._2)(index._2) = 0d
							movement = true
						}
						else if(mat(row._2 + 1)(index._2) == mat(row._2)(index._2) && mat(row._2)(index._2) != 0d){
							score = score + 2d * index._1
							mat(row._2 + 1)(index._2) = 2d * index._1 + 1d / math.pow(math.E, ((row._2) * 4d + index._2 + 1d))
							mat(row._2)(index._2) = 0d
							movement = true
						}
					)
				)
			} while(movement == true)
			mat.foldLeft(Array[Array[Double]]())((acc, row) => acc :+ row.foldLeft(Array[Double]())((acc1, index) =>
				acc1 :+ index.round.toDouble
			))
		}
		override def update(dt: Long): Unit = {
			var movementDir:(Boolean,Boolean) = null
			var keyPress = false
			Input.processEvents(e => e match {
      	case Input.KeyDownEvent(Input.Keys.Up)    => {
					movementDir = Up
					keyPress = true
				}
      	case Input.KeyDownEvent(Input.Keys.Down)  => {
					movementDir = Down
					keyPress = true
				}
      	case Input.KeyDownEvent(Input.Keys.Left)  => {
					movementDir = Left
					keyPress = true
				}
      	case Input.KeyDownEvent(Input.Keys.Right) => {
					movementDir = Right
					keyPress = true
				}
      	case _ => ()
			})
			if(keyPress){
				move(movementDir)
			}

			if(blocks.data.count(_ > 0) > 15){
				gameOver
				sys.exit(0)
			}
		}
		def drawGrid(canvas: Canvas){
			blocks.toArray.zipWithIndex.foreach((row) =>
				row._1.zipWithIndex.foreach((index) =>
					if(index._1 > 0d){
						drawSquare(canvas, Point(row._2, index._2))
					}
				)
			)
		}
		def drawSquare(canvas: Canvas, point: Point) = {
			canvas.drawRect(point.x * SqSize, point.y * SqSize, SqSize, SqSize, blockCol)
			canvas.drawString(blocks(point.x.toInt, point.y.toInt).toString,
				point.x * SqSize + SqSize / 2,
				point.y * SqSize + SqSize / 2,
				textCol
			)
    }
		override def render(canvas: Graphics.Canvas): Unit = {
			canvas.drawRect(0, 0, Window.width, Window.height, defaultPaint.withColor(Color.rgb(70,54,39)))
			drawGrid(canvas)
		}
	}
}
