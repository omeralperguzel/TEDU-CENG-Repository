var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(1.0, 1.0, 1.0, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var vertices = [
        // Light blue square (centered at origin, size 10x10)
        -5, 5, 0.68, 0.85, 0.90,  // Top left
        -5, -5, 0.68, 0.85, 0.90, // Bottom left
         5, -5, 0.68, 0.85, 0.90, // Bottom right
         5, 5, 0.68, 0.85, 0.90,  // Top right

        // Light pink square (centered within blue, size 5x5)
        -2.5, 2.5, 1.0, 0.71, 0.75, // Top left
        -2.5, -2.5, 1.0, 0.71, 0.75, // Bottom left
         2.5, -2.5, 1.0, 0.71, 0.75, // Bottom right
         2.5, 2.5, 1.0, 0.71, 0.75, // Top right

        // Bold pink rectangles (width 2, length 1)
        -3, -0.5, 1.0, 0.08, 0.58, // Left rectangle top left
        -3, 0.5, 1.0, 0.08, 0.58,  // Left rectangle bottom left
        -1, 0.5, 1.0, 0.08, 0.58,  // Left rectangle bottom right
        -1, -0.5, 1.0, 0.08, 0.58, // Left rectangle top right
         1, -0.5, 1.0, 0.08, 0.58, // Right rectangle top left
         1, 0.5, 1.0, 0.08, 0.58,  // Right rectangle bottom left
         3, 0.5, 1.0, 0.08, 0.58,  // Right rectangle bottom right
         3, -0.5, 1.0, 0.08, 0.58, // Right rectangle top right

        // Black squares (size 1x1 each, centered in pink square)
        -0.5, -0.5, 0.0, 0.0, 0.0, // Left square top left
        -0.5,  0.5, 0.0, 0.0, 0.0, // Left square bottom left
         0.5,  0.5, 0.0, 0.0, 0.0, // Left square bottom right
         0.5, -0.5, 0.0, 0.0, 0.0, // Left square top right
         1.5, -0.5, 0.0, 0.0, 0.0, // Right square top left
         1.5,  0.5, 0.0, 0.0, 0.0, // Right square bottom left
         2.5,  0.5, 0.0, 0.0, 0.0, // Right square bottom right
         2.5, -0.5, 0.0, 0.0, 0.0, // Right square top right
    ];

    // Create a buffer and load the vertices into it
    var bufferId = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW);

    // Link vertex position attribute from shader
    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);

    // Render the shapes
    render();
}

// Function to render the shapes
function render() {
    gl.clear(gl.COLOR_BUFFER_BIT);

    // Draw the light blue square
    gl.drawArrays(gl.TRIANGLE_FAN, 0, 4);

    // Draw the light pink square
    gl.drawArrays(gl.TRIANGLE_FAN, 4, 4);

    // Draw the two bold pink rectangles
    gl.drawArrays(gl.TRIANGLE_FAN, 8, 4);
    gl.drawArrays(gl.TRIANGLE_FAN, 12, 4);

    // Draw the two black squares
    gl.drawArrays(gl.TRIANGLE_STRIP, 16, 4);
    gl.drawArrays(gl.TRIANGLE_STRIP, 20, 4);
}

// Start the WebGL application
startWebGL();
