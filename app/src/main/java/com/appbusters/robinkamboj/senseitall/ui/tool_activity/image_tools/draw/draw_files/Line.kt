package com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.draw.draw_files

import android.graphics.Path
import java.io.Writer

class Line(val x: Float, val y: Float) : Action {

    override fun perform(path: Path) {
        path.lineTo(x, y)
    }

    override fun perform(writer: Writer) {
        writer.write("L$x,$y")
    }
}