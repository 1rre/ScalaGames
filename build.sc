import mill._, scalalib._

object Simearth extends ScalaModule{
	def scalaVersion = "2.13.3"
}

object Nim extends ScalaModule{
	def scalaVersion = "2.13.3"
	def ivyDeps = Agg(
		ivy"com.regblanc.sgl::sgl-core:0.0.1",
		ivy"com.regblanc.sgl::sgl-desktop-awt:0.0.1"
	)
}

object Snake extends ScalaModule{
	def scalaVersion = "2.13.3"
	def ivyDeps = Agg(
		ivy"com.regblanc.sgl::sgl-core:0.0.1",
		ivy"com.regblanc.sgl::sgl-desktop-awt:0.0.1"
	)
}

object _2048 extends ScalaModule{
	def scalaVersion = "2.13.3"
	def ivyDeps = Agg(
		ivy"com.github.haifengl::smile-scala:2.4.0",
		ivy"com.regblanc.sgl::sgl-core:0.0.1",
		ivy"com.regblanc.sgl::sgl-desktop-awt:0.0.1"
	)
}