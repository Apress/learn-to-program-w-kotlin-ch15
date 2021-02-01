package lpk.imaging.test

import org.junit.Assert
import org.junit.Test
import lpk.imaging.Picture
import lpk.imaging.loadPictureFromFile
import java.awt.Color
import java.nio.file.Paths

private val IMAGES = "src/test/resources/images/"

class PictureTest {

    @Test
    fun loadPictureFromFileTest() {
        val file = Paths.get(IMAGES + "green_h50_w100.png").toFile()
        val loaded = loadPictureFromFile(file)
        Assert.assertEquals(loaded.height(), 50)
        Assert.assertEquals(loaded.width(), 100)
        val green = Color(0, 255, 0)
        for (row in 0..49) {
            for (column in 0..99) {
                Assert.assertEquals(loaded.pixelByRowColumn(row, column), green)
            }
        }
    }

    @Test
    fun loadYellowPicture() {
        val file = Paths.get(IMAGES + "yellow_h80_w30.png").toFile()
        val loaded = loadPictureFromFile(file)
        Assert.assertEquals(loaded.height(), 80)
        Assert.assertEquals(loaded.width(), 30)
        val yellow = Color(255, 255, 0)
        for (row in 0..79) {
            for (column in 0..29) {
                Assert.assertEquals(loaded.pixelByRowColumn(row, column), yellow)
            }
        }
    }

    @Test
    fun flipInVerticalAxisTest() {
        val fileBR = Paths.get(IMAGES + "blue_red.png").toFile()
        val blueRed = loadPictureFromFile(fileBR)

        val fileRB = Paths.get(IMAGES + "red_blue.png").toFile()
        val redBlue = loadPictureFromFile(fileRB)

        val flipped = blueRed.flipInVerticalAxis()
        checkPicture(flipped, redBlue)
    }

    @Test
    fun flipInHorizontalAxisTest() {
        val fileGR = Paths.get(IMAGES + "green_red.png").toFile()
        val greenRed = loadPictureFromFile(fileGR)

        val fileRG = Paths.get(IMAGES + "red_green.png").toFile()
        val redGreen = loadPictureFromFile(fileRG)

        val flipped = greenRed.flipInHorizontalAxis()
        checkPicture(flipped, redGreen)
    }

    fun checkPicture(picture: Picture, expected: Picture) {
        Assert.assertEquals(picture.height(), expected.height())
        Assert.assertEquals(picture.width(), expected.width())
        for (row in 0..picture.height() - 1) {
            for (column in 0..picture.width() - 1) {
                val actualPixel = picture.pixelByRowColumn(row, column)
                val expectedPixel = expected.pixelByRowColumn(row, column)
                Assert.assertEquals(actualPixel, expectedPixel)
            }
        }
    }

}