

// This variable will store the WebGL rendering context
var gl;

window.onload = function init() {
   // Set up a WebGL Rendering Context in an HTML5 Canvas
   var canvas = document.getElementById("gl-canvas");
   gl = canvas.getContext('webgl2');
   if (!gl) alert("WebGL 2.0 isn't available");

   //  Configure WebGL
   //  eg. - set a clear color
   //      - turn on depth testing
   // This light gray clear colour will help you see your canvas
   gl.clearColor(0.9, 0.9, 0.9, 1.0);

   //  Load shaders and initialize attribute buffers
   var program = initShaders(gl, "vertex-shader", "fragment-shader");
   gl.useProgram(program);

   // Set up data to draw
   // Here, 2D vertex positions and RGB colours are loaded into arrays.
   var positions = [
         -0.5, -0.5, // point 1
         0.5, -0.5, // point 2
         0.0,  0.5   // point 2
      ];
   var colors = [
         1, 0, 0, // red
         0, 1, 0, // green
         0, 0, 1  // blue
      ];

   // Load the data into GPU data buffers
   // The vertex positions are copied into one buffer
   var vertex_buffer = gl.createBuffer();
   gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);
   gl.bufferData(gl.ARRAY_BUFFER, flatten(positions), gl.STATIC_DRAW);

   // The colours are copied into another buffer
   var color_buffer = gl.createBuffer();
   gl.bindBuffer(gl.ARRAY_BUFFER, color_buffer);
   gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

   // Associate shader attributes with corresponding data buffers
   // Create a connection manager for the data, a Vertex Array Object
   // These are typically made global so you can swap what you draw in the
   //    render function.
   var triangleVAO = gl.createVertexArray();
   gl.bindVertexArray(triangleVAO);

   //Here we prepare the "vPosition" shader attribute entry point to
   //receive 2D float vertex positions from the vertex buffer
   var vPosition = gl.getAttribLocation(program, "vPosition");
   gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);
   gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
   gl.enableVertexAttribArray(vPosition);

   //Here we prepare the "vColor" shader attribute entry point to
   //receive RGB float colours from the colour buffer
   var vColor = gl.getAttribLocation(program, "vColor");
   gl.bindBuffer(gl.ARRAY_BUFFER, color_buffer);
   gl.vertexAttribPointer(vColor, 3, gl.FLOAT, false, 0, 0);
   gl.enableVertexAttribArray(vColor);


   // Get addresses of shader uniforms
   // None in this program...

   //Either draw once as part of initialization
  render();

   //Or schedule a draw just before the next repaint event
   //requestAnimationFrame(render);
};


function render() {
   // clear the screen
   // Actually, the  WebGL automatically clears the screen before drawing.
   // This command will clear the screen to the clear color instead of white.
   gl.clear(gl.COLOR_BUFFER_BIT);

   // draw
   // Draw the data from the buffers currently associated with shader variables
   // Our triangle has three vertices that start at the beginning of the buffer.
   gl.drawArrays(gl.TRIANGLES, 0, 3);
}