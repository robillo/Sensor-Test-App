package com.appbusters.robinkamboj.senseitall.view.tool_activity.image_tools.draw.draw_files

import android.graphics.Path
import java.io.Writer
import java.security.InvalidParameterException

class Move(val x: Float, val y: Float) : Action {

    override fun perform(path: Path) {
        path.moveTo(x, y)
    }

    override fun perform(writer: Writer) {
        writer.write("M$x,$y")
    }
}