var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.clearColor(0.9, 0.9, 0.9, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var vertices = [
        // Point vertices and colors
        0.0, 0.0, 1.0, 0.0, 0.0, // Red point
        -0.5, -0.5, 0.0, 1.0, 0.0, // Green point
        0.5, 0.5, 0.0, 0.0, 1.0, // Blue point
        // Triangle vertices and colors
        -0.5, -0.2, 1.0, 1.0, 0.0, // Yellow vertex
        0.0, 0.5, 0.0, 1.0, 1.0, // Cyan vertex
        0.5, -0.2, 1.0, 0.0, 1.0 // Magenta vertex
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
    gl.drawArrays(gl.POINTS, 0, 3); // Draw the first 3 vertices as points
    gl.drawArrays(gl.TRIANGLES, 3, 3); // Draw the next 3 vertices as a triangle
}
