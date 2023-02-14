package com.game.jumper.Graphics

class JumperShaderCode {
    companion object {
        val vtxShaderCode: String =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPos;" +
            "void main() {" +
                "gl_Position = uMVPMatrix * vPosition;" +
            "}"
        val fragShaderCode: String =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
                "gl_FragColor = vColor;" +
            "}"
    }
}