package lpk.imaging

import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun loadPictureFromFile(imageFile: File): Picture {
    val image = ImageIO.read(imageFile)
    val width = image.width
    val height = image.height
    val pixels = Array(height) { row ->
        Array(width) { column ->
            Color(image.getRGB(column, row))
        }
    }
    return Picture(pixels)
}

class Picture(val pixels: Array<Array<Color>>) {
    fun height(): Int {
        return pixels.size
    }

    fun width(): Int {
        return pixels[0].size
    }

    fun pixelByRowColumn(row: Int, column: Int): Color {
        return pixels[row][column]
    }

    fun flipInVerticalAxis(): Picture {
        val pixels = Array(height()) { row ->
            Array(width()) { column ->
                pixelByRowColumn(row, width() - 1 - column)
            }
        }
        return Picture(pixels)
    }

    fun flipInHorizontalAxis(): Picture {
        val pixels = Array(height()) { row ->
            Array(width()) { column ->
                pixelByRowColumn(height() - 1 - row, column)
            }
        }
        return Picture(pixels)
    }
}