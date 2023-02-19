package com.game.jumper.graphics

class JumperShaderCode {
    companion object {
        val vtxShaderCode: String =
                    // This matrix member variable provides a hook to manipulate
                    // the coordinates of the objects that use this vertex shader
                    "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 a_TexCoord;" + // Per-vertex texture coordinate information we will pass in
                    "varying vec2 v_TexCoord;" + // This will be passed into the fragment shader
                    "void main() {" +
                    "   v_TexCoord = vec2(a_TexCoord.x, (1.0 - (a_TexCoord.y)));" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}"
        val fragShaderCode: String =
                    "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "uniform sampler2D u_Texture;" +
                    "varying vec2 v_TexCoord;" +
                    "void main() {" +
                    "  gl_FragColor = texture2D(u_Texture, v_TexCoord);" +
                    "}"
    }
}