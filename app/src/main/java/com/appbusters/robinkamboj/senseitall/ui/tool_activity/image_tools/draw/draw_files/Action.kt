package com.appbusters.robinkamboj.senseitall.ui.tool_activity.image_tools.draw.draw_files

import android.graphics.Path
import java.io.Serializable
import java.io.Writer

interface Action : Serializable {
    fun perform(path: Path)

    fun perform(writer: Writer)
}
