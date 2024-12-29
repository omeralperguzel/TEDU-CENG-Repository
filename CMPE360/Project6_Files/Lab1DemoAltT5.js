var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.clearColor(0.9, 0.9, 0.9, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Vertices: position (x, y) and color (r, g, b)
    var vertices = [
        // Light blue square (centered, length 10)
        -5.0, 5.0, 0.68, 0.85, 0.9, // top left
        -5.0, -5.0, 0.68, 0.85, 0.9, // bottom left
        5.0, -5.0, 0.68, 0.85, 0.9, // bottom right
        5.0, 5.0, 0.68, 0.85, 0.9, // top right

        // Light pink square (center, length 5)
        -2.5, 2.5, 1.0, 0.71, 0.76, // top left
        -2.5, -2.5, 1.0, 0.71, 0.76, // bottom left
        2.5, -2.5, 1.0, 0.71, 0.76, // bottom right
        2.5, 2.5, 1.0, 0.71, 0.76, // top right

        // Two black squares (bottom-center, side-by-side, space 1)
        -1.5, -4.5, 0.0, 0.0, 0.0, // left square, bottom left
        -1.5, -3.5, 0.0, 0.0, 0.0, // left square, top left
        -0.5, -3.5, 0.0, 0.0, 0.0, // right square, top right
        -0.5, -4.5, 0.0, 0.0, 0.0, // right square, bottom right

        0.5, -4.5, 0.0, 0.0, 0.0, // left square, bottom left
        0.5, -3.5, 0.0, 0.0, 0.0, // left square, top left
        1.5, -3.5, 0.0, 0.0, 0.0, // right square, top right
        1.5, -4.5, 0.0, 0.0, 0.0, // right square, bottom right
    ];

    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);

    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 5 * Float32Array.BYTES_PER_ELEMENT, 0);
    gl.enableVertexAttribArray(vPosition);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 3, gl.FLOAT, false, 5 * Float32Array.BYTES_PER_ELEMENT, 2 * Float32Array.BYTES_PER_ELEMENT);
    gl.enableVertexAttribArray(vColor);

    render();
};

function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);

    // Draw the light blue square
    gl.drawArrays(gl.TRIANGLE_FAN, 0, 4);

    // Draw the light pink square
    gl.drawArrays(gl.TRIANGLE_FAN, 4, 4);

    // Draw the two black squares
    gl.drawArrays(gl.TRIANGLE_STRIP, 8, 4);
    gl.drawArrays(gl.TRIANGLE_STRIP, 12, 4);
}
