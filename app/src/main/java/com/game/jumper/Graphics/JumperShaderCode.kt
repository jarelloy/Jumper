package com.game.jumper.Graphics

class JumperShaderCode {
    companion object {
        val vtxShaderCode: String =
            "attribute vec4 vPos;" +
                    "void main() {" +
                    "gl_Position = vPos;" +
                    "}"
        val fragShaderCode: String =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "gl_FragColor = vColor;" +
                    "}"

    }
}