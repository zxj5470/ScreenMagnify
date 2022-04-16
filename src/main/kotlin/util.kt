import java.awt.AWTException
import java.awt.Rectangle
import java.awt.Robot
import java.awt.image.BufferedImage

fun getScreenShot(x: Int, y: Int, width: Int, height: Int): BufferedImage? {
    var bfImage: BufferedImage? = null
    try {
        val robot = Robot()
        bfImage = robot.createScreenCapture(Rectangle(x, y, width, height))
    } catch (e: AWTException) {
        e.printStackTrace()
    }
    return bfImage
}