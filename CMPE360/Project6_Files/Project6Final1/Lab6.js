var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.clearColor(0.9, 0.9, 0.9, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var vertices = [

        // Light blue square (background)
        -5, 5, 0.68, 0.85, 1.0,   // Top left
        -5, -5, 0.68, 0.85, 1.0,  // Bottom left
        5, -5, 0.68, 0.85, 1.0,   // Bottom right
        5, 5, 0.68, 0.85, 1.0,    // Top right

        // Light pink square
        -0.5, 0.5, 1.0, 0.75, 0.8,  // Top left
        -0.5, -0.5, 1.0, 0.75, 0.8, // Bottom left
        0.5, -0.5, 1.0, 0.75, 0.8,  // Bottom right
        0.5, 0.5, 1.0, 0.75, 0.8,   // Top right

        // Bold pink rectangles
        -0.3, -0.5, 1.0, 0.08, 0.58,  // Left rectangle, bottom left
        -0.3, -0.8, 1.0, 0.08, 0.58,   // Left rectangle, top left
        -0.1, -0.5, 1.0, 0.08, 0.58, // Left rectangle, top right
        -0.1, -0.5, 1.0, 0.08, 0.58, // Left rectangle, bottom right

        0.1, -0.5, 1.0, 0.08, 0.58,  // Right rectangle, bottom left
        0.1, -0.5, 1.0, 0.08, 0.58,   // Right rectangle, top left
        0.3, -0.8, 1.0, 0.08, 0.58,    // Right rectangle, top right
        0.3, -0.5, 1.0, 0.08, 0.58,   // Right rectangle, bottom right

        // Black squares
        -0.3, 0.2, 0.0, 0.0, 0.0,  // Left square, bottom left
        -0.3, 0.1, 0.0, 0.0, 0.0,  // Left square, top left
        -0.2, 0.1, 0.0, 0.0, 0.0,  // Left square, top right
        -0.2, 0.2, 0.0, 0.0, 0.0,  // Left square, bottom right

        0.2, 0.2, 0.0, 0.0, 0.0,   // Right square, bottom left
        0.2, 0.1, 0.0, 0.0, 0.0,   // Right square, top left
        0.3, 0.1, 0.0, 0.0, 0.0,   // Right square, top right
        0.3, 0.2, 0.0, 0.0, 0.0,    // Right square, bottom right
    
        //1.0, 1.0, 0.0, 0.0, 1.0, 1.0,  // New point at top-right (blue)
        //-0.1, -0.5, 1.0, 0.0, 1.0, 1.0, // Start of new line (pink)
        //0.3, -0.5, 1.0, 0.0, 1.0, 1.0,  // End of new line (pink)
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
    
    // Draw the new point at the top-right
    //gl.drawArrays(gl.POINTS, 32, 1);

    // Draw the new line at the bottom of the pink triangles
    //gl.drawArrays(gl.LINES, 33, 2);
    //gl.drawArrays(gl.TRIANGLE_FAN, 0, 4);

    // Draw the light pink square
    gl.drawArrays(gl.TRIANGLE_FAN, 4, 4);

    // Draw the two bold pink rectangles
    gl.drawArrays(gl.TRIANGLE_FAN, 8, 4);
    gl.drawArrays(gl.TRIANGLE_FAN, 12, 4);

    // Draw the two black squares
    gl.drawArrays(gl.TRIANGLE_FAN, 16, 4);
    gl.drawArrays(gl.TRIANGLE_FAN, 20, 4);
}

// Start the WebGL application
startWebGL();
