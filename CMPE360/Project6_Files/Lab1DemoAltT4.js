var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.clearColor(0.9, 0.9, 0.9, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var vertices = [
        -2.5, 2.5, 1.0, 0.75, 0.8, // top left
        -2.5, -2.5, 1.0, 0.75, 0.8, // bottom left
        2.5, -2.5, 1.0, 0.75, 0.8, // bottom right
        2.5, 2.5, 1.0, 0.75, 0.8, // top right

        // Two black squares (center, side-by-side, space 1)
        -0.5, -1.0, 0.0, 0.0, 0.0, // left square, bottom left
        -0.5, 0.0, 0.0, 0.0, 0.0, // left square, top left
        0.5, 0.0, 0.0, 0.0, 0.0, // right square, top right
        0.5, -1.0, 0.0, 0.0, 0.0, // right square, bottom right

        // Two bold pink rectangles (bottom-center, space 1)
        -3.0, -3.5, 1.0, 0.4, 0.6, // left rectangle, bottom left
        -3.0, -2.5, 1.0, 0.4, 0.6, // left rectangle, top left
        -1.0, -2.5, 1.0, 0.4, 0.6, // left rectangle, top right
        -1.0, -3.5, 1.0, 0.4, 0.6, // left rectangle, bottom right
        1.0, -3.5, 1.0, 0.4, 0.6, // right rectangle, bottom left
        1.0, -2.5, 1.0, 0.4, 0.6, // right rectangle, top left
        3.0, -2.5, 1.0, 0.4, 0.6, // right rectangle, top right
        3.0, -3.5, 1.0, 0.4, 0.6, // right rectangle, bottom right
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

    // Draw the light pink square
    gl.drawArrays(gl.TRIANGLE_FAN, 0, 4);

    // Draw the two black squares
    gl.drawArrays(gl.TRIANGLE_STRIP, 4, 4);

    // Draw the two bold pink rectangles
    gl.drawArrays(gl.TRIANGLE_FAN, 8, 4);

    gl.drawArrays(gl.TRIANGLE_FAN, 12, 4);
    
}
