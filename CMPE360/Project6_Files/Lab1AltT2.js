// This variable will store the WebGL rendering context
var gl;

window.onload = function init() {
    var canvas = document.getElementById("gl-canvas");
    gl = canvas.getContext('webgl2');
    if (!gl) { alert("WebGL 2.0 isn't available"); }

    gl.clearColor(0.9, 0.9, 0.9, 1.0);

    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // Updated vertices and colors data
    var vertices = new Float32Array([
        // Points data
        0.0, 0.0, 1.0, 0.0, 0.0, // Red point
        // Line data
        -0.5, -0.5, 0.0, 1.0, 0.0, // Green start
        0.5, 0.5, 0.0, 1.0, 0.0,   // Green end
        // Triangle data
        -0.5, -0.2, 0.0, 0.0, 1.0, // Blue vertex 1
        0.0, 0.5, 0.0, 0.0, 1.0,   // Blue vertex 2
        0.5, -0.2, 0.0, 0.0, 1.0   // Blue vertex 3
    ]);

    var vBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, vertices, gl.STATIC_DRAW);

    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 5 * 4, 0);
    gl.enableVertexAttribArray(vPosition);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 3, gl.FLOAT, false, 5 * 4, 2 * 4);
    gl.enableVertexAttribArray(vColor);

    // Define colors
    var colors = {
        'points': [1.0, 0.0, 0.0],  // Red
        'line': [0.0, 1.0, 0.0],    // Green
        'triangle': [0.0, 0.0, 1.0] // Blue
    };

    // Define point size and line width
    var pointSize = 10.0;
    var lineWidth = 5.0;

    // Create and bind buffers
    var buffers = {};
    for (var primitive in vertices) {
        var buffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices[primitive]), gl.STATIC_DRAW);
        buffers[primitive] = buffer;
    }

    var vPosition = gl.getAttribLocation(program, "vPosition");
    var uColor = gl.getUniformLocation(program, "uColor");
    var uPointSize = gl.getUniformLocation(program, "uPointSize");

    function renderPrimitive(primitive) {
        gl.bindBuffer(gl.ARRAY_BUFFER, buffers[primitive]);
        gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(vPosition);

        gl.uniform3fv(uColor, new Float32Array(colors[primitive]));

        if (primitive === 'points') {
            gl.uniform1f(uPointSize, pointSize);
            gl.drawArrays(gl.POINTS, 0, vertices[primitive].length / 2);
        } else if (primitive === 'line') {
            gl.lineWidth(lineWidth);
            gl.drawArrays(gl.LINES, 0, vertices[primitive].length / 2);
        } else if (primitive === 'triangle') {
            gl.drawArrays(gl.TRIANGLES, 0, vertices[primitive].length / 2);
        }
    }

    function render() {
        gl.clear(gl.COLOR_BUFFER_BIT);
        ['points', 'line', 'triangle'].forEach(renderPrimitive);
    }

    render();
};
