package io.github.rank.api.lookat

import org.bukkit.Location
import org.bukkit.entity.Player
import kotlin.math.cos
import kotlin.math.sin

fun Player.forward(radius: Double): Location {
    val yaw = this.location.yaw
    val pitch = this.location.yaw

    val x = sin(yaw.toDouble())
    val z = cos(yaw.toDouble())
    val y = -sin(pitch.toDouble())

    val realY = radius * y
    val left = radius * radius - (radius * y) * (radius * y)
    val realX = left * x
    val realZ = left * z

    return this.location.add(realX, realY, realZ)
}