import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.embed.swing.SwingFXUtils
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.stage.Stage
import tornadofx.*
import kotlin.system.exitProcess


var x = 1612
var y = 766
val wid get() = 1920 - x
val hei get() = 1080 - y

class Main : View() {
    val shownImage = SimpleObjectProperty<Image>()
    var xField: TextField by singleAssign()
    var yField: TextField by singleAssign()
    var widthNameField: TextField by singleAssign()
    var heightNameField: TextField by singleAssign()
    init {
        Thread {
            while (true) {
                apply {
                    Thread.sleep(100)
                    try{
                        val xx = xField.text.toInt()
                        val yy = yField.text.toInt()
                        if (xx.toDouble()/yy <= 0.2) {
                            return@apply
                        }
                        x = xx
                        y = yy
                        widthNameField.text = wid.toString()
                        heightNameField.text = hei.toString()
                    }catch (e: Exception){
                        x = 1612
                        y = 766
                        e.printStackTrace()
                    }
                    val img = getScreenShot(x, y, wid, hei) ?: return@apply
                    val writeAbleImage = WritableImage(img.getWidth(null), img.getHeight(null))
                    shownImage.value = SwingFXUtils.toFXImage(img, writeAbleImage)
                }
            }
        }.start()
    }

    override val root = hbox {
        imageview(shownImage) {
            fitHeight = 900.0
            fitWidth = 900.0
        }
        vbox {
            hbox {
                label("x")
                xField = textfield()
                xField.text = x.toString()
            }
            hbox {
                label("y")
                yField = textfield()
                yField.text = y.toString()
            }
            hbox {
                label("宽")
                widthNameField = textfield()
                widthNameField.text = wid.toString()
            }
            hbox {
                label("高")
                heightNameField = textfield()
                heightNameField.text = hei.toString()
            }
            button("设置") {

            }
        }
    }
}

class HelloWorldApp : App(Main::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        val a = 10.0
        stage.x = 1920 + a
        stage.y = a
        stage.setOnCloseRequest {
            Platform.exit()
            exitProcess(0)
        }
    }
}

class Styles : Stylesheet() {
    init {
        textField{
            padding = box(10.px, 10.px, 10.px, 10.px)
        }
        label{
            padding = box(10.px, 10.px, 10.px, 10.px)
        }
    }
}

fun main(args: Array<String>) {
    launch<HelloWorldApp>(args)
}